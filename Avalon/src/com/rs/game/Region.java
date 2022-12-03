package com.rs.game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.rs.Settings;
import com.rs.cache.Cache;
import com.rs.cache.loaders.ClientScriptMap;
import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.item.FloorItem;
import com.rs.game.player.Player;
import com.rs.io.InputStream;
import com.rs.utils.Logger;
import com.rs.utils.MapArchiveKeys;
import com.rs.utils.NPCSpawns;
import com.rs.utils.ObjectSpawns;
import com.rs.utils.Utils;

public class Region {
	public static final int[] OBJECT_SLOTS = new int[] { 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			2, 3 };
	public static final int OBJECT_SLOT_WALL = 0;
	public static final int OBJECT_SLOT_WALL_DECORATION = 1;
	public static final int OBJECT_SLOT_FLOOR = 2;
	public static final int OBJECT_SLOT_FLOOR_DECORATION = 3;

	protected int regionId;
	protected RegionMap map;
	protected RegionMap clipedOnlyMap;

	protected List<Integer> playersIndexes;
	protected List<Integer> npcsIndexes;
	protected List<WorldObject> spawnedObjects;
	protected List<WorldObject> removedOriginalObjects;
	private List<FloorItem> groundItems;
	protected WorldObject[][][][] objects;
	private volatile int loadMapStage;
	private boolean loadedNPCSpawns;
	private boolean loadedObjectSpawns;
	private boolean loadedItemSpawns;
	private int[] musicIds;

	public Region(int regionId) {
		this.regionId = regionId;
		this.spawnedObjects = new CopyOnWriteArrayList<WorldObject>();
		this.removedOriginalObjects = new CopyOnWriteArrayList<WorldObject>();
		this.projectiles = new CopyOnWriteArrayList<Projectile>();
		loadMusicIds();
		// indexes null by default cuz we dont want them on mem for regions that
		// players cant go in
	}

	public void checkLoadMap() {
		if (getLoadMapStage() == 0) {
			setLoadMapStage(1);
			CoresManager.slowExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						loadRegionMap();
						setLoadMapStage(2);
						if (!isLoadedObjectSpawns()) {
							loadObjectSpawns();
							setLoadedObjectSpawns(true);
						}
						if (!isLoadedNPCSpawns()) {
							loadNPCSpawns();
							setLoadedNPCSpawns(true);
						}
					} catch (Throwable e) {
						Logger.handle(e);
					}
				}
			});
		}
	}

	private void loadNPCSpawns() {
		NPCSpawns.loadNPCSpawns(regionId);
	}

	private void loadObjectSpawns() {
		ObjectSpawns.loadObjectSpawns(regionId);
	}

	/**
	 * Unload's map from memory.
	 */
	public void unloadMap() {
		if (getLoadMapStage() == 2 && (playersIndexes == null || playersIndexes.isEmpty())
				&& (npcsIndexes == null || npcsIndexes.isEmpty())) {
			objects = null;
			map = null;
			setLoadMapStage(0);
		}
	}

	public RegionMap forceGetRegionMapClipedOnly() {
		if (clipedOnlyMap == null)
			clipedOnlyMap = new RegionMap(regionId, true);
		return clipedOnlyMap;
	}

	public RegionMap forceGetRegionMap() {
		if (map == null)
			map = new RegionMap(regionId, false);
		return map;
	}

	public RegionMap getRegionMap() {
		return map;
	}

	public int getMask(int plane, int localX, int localY) {
		if (map == null || getLoadMapStage() != 2) {
			return -1; // cliped tile
		}
		return map.getMasks()[plane][localX][localY];
	}

	public int getMaskClipedOnly(int plane, int localX, int localY) {
		if (clipedOnlyMap == null || getLoadMapStage() != 2)
			return -1; // cliped tile
		return clipedOnlyMap.getMasks()[plane][localX][localY];
	}

	public void setMask(int plane, int localX, int localY, int mask) {
		if (map == null || getLoadMapStage() != 2)
			return; // cliped tile

		if (localX >= 64 || localY >= 64 || localX < 0 || localY < 0) {
			WorldTile tile = new WorldTile(map.getRegionX() + localX, map.getRegionY() + localY, plane);
			int regionId = tile.getRegionId();
			int newRegionX = (regionId >> 8) * 64;
			int newRegionY = (regionId & 0xff) * 64;
			World.getRegion(tile.getRegionId()).setMask(plane, tile.getX() - newRegionX, tile.getY() - newRegionY,
					mask);
			return;
		}

		map.setMask(plane, localX, localY, mask);
	}

	public void clip(WorldObject object, int x, int y) {
		if (object.getId() == -1) // dont clip or noclip with id -1
			return;
		if (map == null)
			map = new RegionMap(regionId, false);
		if (clipedOnlyMap == null)
			clipedOnlyMap = new RegionMap(regionId, true);
		int plane = object.getPlane();
		int type = object.getType();
		int rotation = object.getRotation();
		if (x < 0 || y < 0 || x >= map.getMasks()[plane].length || y >= map.getMasks()[plane][x].length)
			return;
		ObjectDefinitions objectDefinition = ObjectDefinitions.getObjectDefinitions(object.getId()); // load
		// here

		if (type == 22 ? objectDefinition.getClipType() != 1 : objectDefinition.getClipType() == 0)
			return;
		if (type >= 0 && type <= 3) {
			if (!objectDefinition.ignoreClipOnAlternativeRoute) // disabled those walls for now since theyre guard
																// corners, temporary fix
				map.addWall(plane, x, y, type, rotation, objectDefinition.isProjectileCliped(),
						!objectDefinition.ignoreClipOnAlternativeRoute);
			if (objectDefinition.isProjectileCliped())
				clipedOnlyMap.addWall(plane, x, y, type, rotation, objectDefinition.isProjectileCliped(),
						!objectDefinition.ignoreClipOnAlternativeRoute);
		} else if (type >= 9 && type <= 21) {
			int sizeX;
			int sizeY;
			if (rotation != 1 && rotation != 3) {
				sizeX = objectDefinition.getSizeX();
				sizeY = objectDefinition.getSizeY();
			} else {
				sizeX = objectDefinition.getSizeY();
				sizeY = objectDefinition.getSizeX();
			}
			map.addObject(plane, x, y, sizeX, sizeY, objectDefinition.isProjectileCliped(),
					!objectDefinition.ignoreClipOnAlternativeRoute);
			if (objectDefinition.isProjectileCliped())
				clipedOnlyMap.addObject(plane, x, y, sizeX, sizeY, objectDefinition.isProjectileCliped(),
						!objectDefinition.ignoreClipOnAlternativeRoute);
		} else if (type == 22) {
			map.addFloor(plane, x, y); // dont ever fucking think about removing it..., some floor deco objects DOES
										// BLOCK WALKING
		}
	}

	public void unclip(int plane, int x, int y) {
		if (map == null)
			map = new RegionMap(regionId, false);
		if (clipedOnlyMap == null)
			clipedOnlyMap = new RegionMap(regionId, true);
		map.setMask(plane, x, y, 0);
	}

	public void unclip(WorldObject object, int x, int y) {
		if (object.getId() == -1)
			return;
		if (map == null)
			map = new RegionMap(regionId, false);
		if (clipedOnlyMap == null)
			clipedOnlyMap = new RegionMap(regionId, true);
		int plane = object.getPlane();
		int type = object.getType();
		int rotation = object.getRotation();
		if (x < 0 || y < 0 || x >= map.getMasks()[plane].length || y >= map.getMasks()[plane][x].length)
			return;
		ObjectDefinitions objectDefinition = ObjectDefinitions.getObjectDefinitions(object.getId()); // load
		// here
		if (type == 22 ? objectDefinition.getClipType() != 1 : objectDefinition.getClipType() == 0)
			return;
		if (type >= 0 && type <= 3) {
			map.removeWall(plane, x, y, type, rotation, objectDefinition.isProjectileCliped(),
					!objectDefinition.ignoreClipOnAlternativeRoute);
			if (objectDefinition.isProjectileCliped())
				clipedOnlyMap.removeWall(plane, x, y, type, rotation, objectDefinition.isProjectileCliped(),
						!objectDefinition.ignoreClipOnAlternativeRoute);
		} else if (type >= 9 && type <= 21) {
			int sizeX;
			int sizeY;
			if (rotation != 1 && rotation != 3) {
				sizeX = objectDefinition.getSizeX();
				sizeY = objectDefinition.getSizeY();
			} else {
				sizeX = objectDefinition.getSizeY();
				sizeY = objectDefinition.getSizeX();
			}
			map.removeObject(plane, x, y, sizeX, sizeY, objectDefinition.isProjectileCliped(),
					!objectDefinition.ignoreClipOnAlternativeRoute);
			if (objectDefinition.isProjectileCliped())
				clipedOnlyMap.removeObject(plane, x, y, sizeX, sizeY, objectDefinition.isProjectileCliped(),
						!objectDefinition.ignoreClipOnAlternativeRoute);

		} else if (type == 22) {
			map.removeFloor(plane, x, y);
		}
	}

	public void spawnObject(WorldObject object, int plane, int localX, int localY, boolean original) {
		if (objects == null)
			objects = new WorldObject[4][64][64][4];
		int slot = OBJECT_SLOTS[object.getType()];
		if (original) {
			objects[plane][localX][localY][slot] = object;
			clip(object, localX, localY);
		} else {
			WorldObject spawned = getSpawnedObjectWithSlot(plane, localX, localY, slot);
			if (spawned != null) {
				spawnedObjects.remove(spawned);
				unclip(spawned, localX, localY);
			}
			WorldObject removed = getRemovedObjectWithSlot(plane, localX, localY, slot);
			if (removed != null) {
				object = removed;
				removedOriginalObjects.remove(object);
			} else if (objects[plane][localX][localY][slot] != object) {
				spawnedObjects.add(object);
				if (objects[plane][localX][localY][slot] != null)
					unclip(objects[plane][localX][localY][slot], localX, localY);
			} else if (spawned == null) {
				if (Settings.DEBUG)
					Logger.log(this, object.getId() + " " + object.getX() + "-" + object.getY()
							+ ". Requested object to spawn is already spawned.(Shouldnt happen)");
				return;
			}
			clip(object, localX, localY);
			for (Player p2 : World.getPlayers()) {
				if (p2 == null || !p2.hasStarted() || p2.hasFinished() || !p2.getMapRegionsIds().contains(regionId))
					continue;
				p2.getPackets().sendSpawnedObject(object);
			}
		}
	}

	public void removeObject(WorldObject object, int plane, int localX, int localY) {
		if (objects == null)
			objects = new WorldObject[4][64][64][4];
		int slot = OBJECT_SLOTS[object.getType()];
		WorldObject removed = getRemovedObjectWithSlot(plane, localX, localY, slot);
		if (removed != null) {
			removedOriginalObjects.remove(object);
			clip(removed, localX, localY);
		}
		WorldObject original = null;
		// found non original object on this slot. removing it since we
		// replacing with real one or none if none
		WorldObject spawned = getSpawnedObjectWithSlot(plane, localX, localY, slot);
		if (spawned != null) {
			object = spawned;
			spawnedObjects.remove(object);
			unclip(object, localX, localY);
			WorldObject o = objects[plane][localX][localY][slot];
			if (o != null) {// original
				// unclips non original to clip original above
				clip(o, localX, localY);
				original = o;
			}
			// found original object on this slot. removing it since requested
		} else if (objects[plane][localX][localY][slot] == object) { // removes original
			unclip(object, localX, localY);
			removedOriginalObjects.add(object);
		} else if (spawned == null) {
			if (Settings.DEBUG)
				Logger.log(this, object.getId() + " " + object.getX() + "-" + object.getY()
						+ ". Requested object to remove wasnt found.(Shouldnt happen)");
			return;
		}
		for (Player p2 : World.getPlayers()) {
			if (p2 == null || !p2.hasStarted() || p2.hasFinished() || !p2.getMapRegionsIds().contains(regionId))
				continue;
			if (original != null)
				p2.getPackets().sendSpawnedObject(original);
			else
				p2.getPackets().sendRemoveObject(object);
		}

	}

	public void removeObject(WorldObject object, int plane, int localX, int localY, boolean removeClip) {
		if (objects == null)
			objects = new WorldObject[4][64][64][4];
		int slot = OBJECT_SLOTS[object.getType()];
		WorldObject removed = getRemovedObjectWithSlot(plane, localX, localY, slot);
		if (removed != null) {
			removedOriginalObjects.remove(object);
			if (removeClip)
				clip(removed, localX, localY);
		}
		WorldObject original = null;
		WorldObject spawned = getSpawnedObjectWithSlot(plane, localX, localY, slot);
		if (spawned != null) {
			object = spawned;
			spawnedObjects.remove(object);
			if (removeClip)
				unclip(object, localX, localY);
			if (objects[plane][localX][localY][slot] != null) {
				if (removeClip)
					clip(objects[plane][localX][localY][slot], localX, localY);
				original = objects[plane][localX][localY][slot];
			}
		} else if (objects[plane][localX][localY][slot] == object) {
			if (removeClip)
				unclip(object, localX, localY);
			removedOriginalObjects.add(object);
		} else {
			if (Settings.DEBUG)
				Logger.log(this, object.getId() + " " + object.getX() + "-" + object.getY()
						+ ". Requested object to remove wasnt found.(Shouldnt happen)");
			return;
		}

		for (Player p2 : World.getPlayers()) {
			if (p2 == null || !p2.hasStarted() || p2.hasFinished() || !p2.getMapRegionsIds().contains(regionId))
				continue;
			if (original != null)
				p2.getPackets().sendSpawnedObject(original);
			else
				p2.getPackets().sendDestroyObject(object);
		}

	}

	/*
	 * for (Player p2 : World.getPlayers()) { if (p2 == null || !p2.hasStarted() ||
	 * p2.hasFinished() || !p2.getMapRegionsIds().contains(regionId)) continue; if
	 * (original != null) p2.getPackets().sendSpawnedObject(original); else
	 * p2.getPackets().sendDestroyObject(object); }
	 */

	public WorldObject getStandardFloorObject(int plane, int x, int y) {
		return getObjectWithSlot(plane, x, y, OBJECT_SLOT_FLOOR);
	}

	public WorldObject getStandardWallObject(int plane, int x, int y) {
		return getObjectWithSlot(plane, x, y, OBJECT_SLOT_WALL);
	}
	
	public WorldObject getStandardWallDecoration(int plane, int x, int y) {
		return getObjectWithSlot(plane, x, y, OBJECT_SLOT_WALL_DECORATION);
	}
	
	public WorldObject getStandardFloorDecoration(int plane, int x, int y) {
		return getObjectWithSlot(plane, x, y, OBJECT_SLOT_FLOOR_DECORATION);
	}

	public WorldObject getObjectWithType(int plane, int x, int y, int type) {
		WorldObject object = getObjectWithSlot(plane, x, y, OBJECT_SLOTS[type]);
		return object != null && object.getType() == type ? object : null;
	}

	public WorldObject getObjectWithSlot(int plane, int x, int y, int slot) {
		if (objects == null)
			return null;
		WorldObject o = getSpawnedObjectWithSlot(plane, x, y, slot);
		if (o == null) {
			if (getRemovedObjectWithSlot(plane, x, y, slot) != null)
				return null;
			return objects[plane][x][y][slot];
		}
		return o;
	}

	public WorldObject getRealObject(int plane, int x, int y, int type) {
		return objects[plane][x][y][OBJECT_SLOTS[type]];
	}

	public WorldObject getSpawnedObjectWithSlot(int plane, int x, int y, int slot) {
		for (WorldObject object : spawnedObjects) {
			if (object.getXInRegion() == x && object.getYInRegion() == y && object.getPlane() == plane
					&& OBJECT_SLOTS[object.getType()] == slot)
				return object;
		}
		return null;
	}

	public WorldObject getRemovedObjectWithSlot(int plane, int x, int y, int slot) {
		for (WorldObject object : removedOriginalObjects) {
			if (object.getXInRegion() == x && object.getYInRegion() == y && object.getPlane() == plane
					&& OBJECT_SLOTS[object.getType()] == slot)
				return object;
		}
		return null;
	}

	public WorldObject[] getAllObjects(int plane, int x, int y) {
		if (objects == null)
			return null;
		return objects[plane][x][y];
	}

	public List<WorldObject> getAllObjects() {
		if (objects == null)
			return null;
		List<WorldObject> list = new ArrayList<WorldObject>();
		for (int z = 0; z < 4; z++)
			for (int x = 0; x < 64; x++)
				for (int y = 0; y < 64; y++) {
					if (objects[z][x][y] == null)
						continue;
					for (WorldObject o : objects[z][x][y])
						if (o != null)
							list.add(o);
				}
		return list;
	}

	public List<WorldObject> getAllObjectsByType(int type) {
		if (objects == null)
			return null;
		List<WorldObject> list = new ArrayList<WorldObject>();
		for (int z = 0; z < 4; z++)
			for (int x = 0; x < 64; x++)
				for (int y = 0; y < 64; y++) {
					if (objects[z][x][y] == null)
						continue;
					for (WorldObject o : objects[z][x][y]) {
						if (o != null) {
							if (o.getType() != type)
								continue;
							list.add(o);
						}
					}
				}
		return list;
	}

	public boolean containsObjectWithId(int plane, int x, int y, int id) {
		WorldObject object = getObjectWithId(plane, x, y, id);
		return object != null && object.getId() == id;
	}

	public WorldObject getObjectWithId(int plane, int x, int y, int id) {
		if (objects == null)
			return null;
		for (WorldObject object : removedOriginalObjects) {
			if (object.getId() == id && object.getXInRegion() == x && object.getYInRegion() == y
					&& object.getPlane() == plane)
				return object;
		}
		for (int i = 0; i < 4; i++) {
			WorldObject object = objects[plane][x][y][i];
			if (object != null && object.getId() == id) {
				WorldObject spawned = getSpawnedObjectWithSlot(plane, x, y, OBJECT_SLOTS[object.getType()]);
				return spawned == null ? object : spawned;
			}
		}
		for (WorldObject object : spawnedObjects) {
			if (object.getXInRegion() == x && object.getYInRegion() == y && object.getPlane() == plane
					&& object.getId() == id)
				return object;
		}
		return null;
	}

	public WorldObject getObjectWithId(int id, int plane) {
		if (objects == null)
			return null;
		for (WorldObject object : spawnedObjects) {
			if (object.getId() == id && object.getPlane() == plane)
				return object;
		}
		for (int x = 0; x < 64; x++) {
			for (int y = 0; y < 64; y++) {
				for (int slot = 0; slot < objects[plane][x][y].length; slot++) {
					WorldObject object = objects[plane][x][y][slot];
					if (object != null && object.getId() == id)
						return object;
				}
			}
		}
		return null;
	}

	public List<WorldObject> getSpawnedObjects() {
		return spawnedObjects;
	}

	public List<WorldObject> getRemovedOriginalObjects() {
		return removedOriginalObjects;
	}

	public void loadRegionMap() {
		int regionX = (regionId >> 8) * 64;
		int regionY = (regionId & 0xff) * 64;
		int landArchiveId = Cache.STORE.getIndexes()[5]
				.getArchiveId("l" + ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8));
		byte[] landContainerData = landArchiveId == -1 ? null
				: Cache.STORE.getIndexes()[5].getFile(landArchiveId, 0, MapArchiveKeys.getMapKeys(regionId));
		int mapArchiveId = Cache.STORE.getIndexes()[5]
				.getArchiveId("m" + ((regionX >> 3) / 8) + "_" + ((regionY >> 3) / 8));
		byte[] mapContainerData = mapArchiveId == -1 ? null : Cache.STORE.getIndexes()[5].getFile(mapArchiveId, 0);
		byte[][][] mapSettings = mapContainerData == null ? null : new byte[4][64][64];
		if (mapContainerData != null) {
			InputStream mapStream = new InputStream(mapContainerData);
			for (int plane = 0; plane < 4; plane++) {
				for (int x = 0; x < 64; x++) {
					for (int y = 0; y < 64; y++) {
						while (true) {
							int value = mapStream.readUnsignedByte();
							if (value == 0) {
								break;
							} else if (value == 1) {
								mapStream.readByte();
								break;
							} else if (value <= 49) {
								mapStream.readByte();

							} else if (value <= 81) {
								mapSettings[plane][x][y] = (byte) (value - 49);
							}
						}
					}
				}
			}
			for (int plane = 0; plane < 4; plane++) {
				for (int x = 0; x < 64; x++) {
					for (int y = 0; y < 64; y++) {
						if ((mapSettings[plane][x][y] & 0x1) == 1) {
							int realPlane = plane;
							if ((mapSettings[1][x][y] & 2) == 2)
								realPlane--;
							if (realPlane >= 0)
								forceGetRegionMap().addUnwalkable(realPlane, x, y);
						}
					}
				}
			}
		} else {
			for (int plane = 0; plane < 4; plane++) {
				for (int x = 0; x < 64; x++) {
					for (int y = 0; y < 64; y++) {
						forceGetRegionMap().addUnwalkable(plane, x, y);
					}
				}
			}
		}
		if (landContainerData != null) {
			InputStream landStream = new InputStream(landContainerData);
			int objectId = -1;
			int incr;
			while ((incr = landStream.readSmart2()) != 0) {
				objectId += incr;
				int location = 0;
				int incr2;
				while ((incr2 = landStream.readUnsignedSmart()) != 0) {
					location += incr2 - 1;
					int localX = (location >> 6 & 0x3f);
					int localY = (location & 0x3f);
					int plane = location >> 12;
					int objectData = landStream.readUnsignedByte();
					int type = objectData >> 2;
					int rotation = objectData & 0x3;
					if (localX < 0 || localX >= 64 || localY < 0 || localY >= 64)
						continue;
					int objectPlane = plane;
					if (mapSettings != null && (mapSettings[1][localX][localY] & 2) == 2)
						objectPlane--;
					if (objectPlane < 0 || objectPlane >= 4 || plane < 0 || plane >= 4)
						continue;
					spawnObject(
							new WorldObject(objectId, type, rotation, localX + regionX, localY + regionY, objectPlane),
							objectPlane, localX, localY, true);
				}
			}
		}
		if (Settings.DEBUG && landContainerData == null && landArchiveId != -1
				&& MapArchiveKeys.getMapKeys(regionId) != null)
			Logger.log(this, "Missing xteas for region " + regionId + ".");
	}

	public int getRotation(int plane, int x, int y) {
		return 0;
	}

	/**
	 * Get's ground item with specific id on the specific location in this region.
	 */
	public FloorItem getGroundItem(int id, WorldTile tile, Player player) {
		if (groundItems == null)
			return null;
		for (FloorItem item : groundItems) {
			if (item.hasOwner() && item.isInvisible()) {
				if (!player.getUsername().equals(item.getOwner()))
					continue;
			}
			if (item.getId() == id && tile.getX() == item.getTile().getX() && tile.getY() == item.getTile().getY()
					&& tile.getPlane() == item.getTile().getPlane())
				return item;
		}
		return null;
	}

	/**
	 * Return's list of ground items that are currently loaded. List may be null if
	 * there's no ground items. Modifying given list is prohibited.
	 * 
	 * @return
	 */
	public List<FloorItem> getGroundItems() {
		return groundItems;
	}

	/**
	 * Return's list of ground items that are currently loaded. This method ensures
	 * that returned list is not null. Modifying given list is prohibited.
	 * 
	 * @return
	 */
	public List<FloorItem> getGroundItemsSafe() {
		if (groundItems == null)
			groundItems = new CopyOnWriteArrayList<FloorItem>();
		return groundItems;
	}

	public List<Integer> getPlayerIndexes() {
		return playersIndexes;
	}

	public int getPlayerCount() {
		return playersIndexes == null ? 0 : playersIndexes.size();
	}

	public List<Integer> getNPCsIndexes() {
		return npcsIndexes;
	}

	public void addPlayerIndex(int index) {
		// creates list if doesnt exist
		if (playersIndexes == null)
			playersIndexes = new CopyOnWriteArrayList<Integer>();
		playersIndexes.add(index);
	}

	public void addNPCIndex(int index) {
		// creates list if doesnt exist
		if (npcsIndexes == null)
			npcsIndexes = new CopyOnWriteArrayList<Integer>();
		npcsIndexes.add(index);
	}

	public void removePlayerIndex(Integer index) {
		if (playersIndexes == null) // removed region example cons or dung
			return;
		playersIndexes.remove(index);
	}

	public boolean removeNPCIndex(Object index) {
		if (npcsIndexes == null) // removed region example cons or dung
			return false;
		return npcsIndexes.remove(index);
	}

	public void loadMusicIds() {
		int musicId1 = getMusicId(getMusicName1(regionId));
		if (musicId1 != -1) {
			int musicId2 = getMusicId(getMusicName2(regionId));
			if (musicId2 != -1) {
				int musicId3 = getMusicId(getMusicName3(regionId));
				if (musicId3 != -1)
					musicIds = new int[] { musicId1, musicId2, musicId3 };
				else
					musicIds = new int[] { musicId1, musicId2 };
			} else
				musicIds = new int[] { musicId1 };
		}
	}

	public int getRandomMusicId() {
		if (musicIds == null)
			return -1;
		if (musicIds.length == 1)
			return musicIds[0];
		return musicIds[Utils.getRandom(musicIds.length - 1)];
	}

	public int getLoadMapStage() {
		return loadMapStage;
	}

	public void setLoadMapStage(int loadMapStage) {
		this.loadMapStage = loadMapStage;
	}

	public boolean isLoadedObjectSpawns() {
		return loadedObjectSpawns;
	}

	public void setLoadedObjectSpawns(boolean loadedObjectSpawns) {
		this.loadedObjectSpawns = loadedObjectSpawns;
	}

	public boolean isLoadedNPCSpawns() {
		return loadedNPCSpawns;
	}

	public void setLoadedNPCSpawns(boolean loadedNPCSpawns) {
		this.loadedNPCSpawns = loadedNPCSpawns;
	}

	public int getRegionId() {
		return regionId;
	}

	public static final String getMusicName3(int regionId) {
		switch (regionId) {
		case 13152: // crucible
			return "Steady";
		case 13151: // crucible
			return "Hunted";
		case 12895: // crucible
			return "Target";
		case 12896: // crucible
			return "I Can See You";
		case 11575: // burthope
			return "Spiritual";
		case 18512:
		case 18511:
		case 19024:
			return "Tzhaar City III";
		case 18255: // fight pits
			return "Tzhaar Supremacy III";
		case 14948:
			return "Dominion Lobby III";
		default:
			return null;
		}
	}

	public static final String getMusicName2(int regionId) {
		switch (regionId) {
		case 12342: // edge
			return "I Pk You";
		case 13152: // crucible
			return "I Can See You";
		case 13151: // crucible
			return "You Will Know Me";
		case 12895: // crucible
			return "Steady";
		case 12896: // crucible
			return "Hunted";
		case 12853:
			return "Cellar Song";
		case 11573: // taverley
			return "Taverley Enchantment";

		case 11575: // burthope
			return "Taverley Adventure";
		/*
		 * kalaboss
		 */
		case 13626:
		case 13627:
		case 13882:
		case 13881:
			return "Daemonheim Fremenniks";
		case 18512:
		case 18511:
		case 19024:
			return "Tzhaar City II";
		case 18255: // fight pits
			return "Tzhaar Supremacy II";
		case 14948:
			return "Dominion Lobby II";
		default:
			return null;
		}
	}

	public static final String getMusicName1(int regionId) {
		switch (regionId) {
		case 8774: // taverly slayer dungeon
			return "Taverley Lament";
		case 11576:
			return "Kingdom";
		case 11320:
			return "Tremble";
		case 12616: // tarns lair
			return "Undead Dungeon";
		case 10388:
			return "Cavern";
		case 12107:
			return "Into the Abyss";
		case 11164:
			return "The Slayer";
		case 10908:
		case 10907:
			return "Masquerade";
		case 4707:
		case 4451:
		case 5221:
		case 5220:
		case 5219:
		case 4453:
		case 4709:
			return "Hunting Dragons";
		case 12115:
			return "Dimension X";
		case 8527: // braindeath island
			return "Aye Car Rum Ba";
		case 8528: // braindeath mountain
			return "Blistering Barnacles";
		case 13206: // goblin mines under lumby
			return "The Lost Tribe";
		case 12949:
		case 12950:
			return "Cave of the Goblins";
		case 12948:
			return "The Power of Tears";
		case 11416: // dramen tree
			return "Underground";
		case 14638: // mosleharms
			return "In the Brine";
		case 14637:
		case 14894:
			return "Life's a Beach!";
		case 14494: // mosleharms cave
			return "Little Cave of Horrors";
		case 11673: // taverly dungeon musics
			return "Courage";
		case 11672:
			return "Dunjun";
		case 11417:
			return "Arabique";
		case 11671:
			return "Royale";
		case 13977:
			return "Stillness";
		case 13622:
			return "Morytania";
		case 13722:
			return "Mausoleum";
		case 10906:
			return "Twilight";
		case 12181: // Asgarnian Ice Dungeon's wyvern area
			return "Woe of the Wyvern";
		case 11925: // Asgarnian Ice Dungeon
			return "Starlight";
		case 13617: // abbey
			return "Citharede Requiem";
		case 13361: // desert verms
			return "Valerio's Song";
		case 13910: // The Tale of the Muspah cave entrance
		case 13654:
			return "Rest for the Weary";
		case 13656: // The Tale of the Muspah cave ice verms area
			return "The Muspah's Tomb";
		case 11057: // brimhaven and arroundd
			return "High Seas";
		case 10802:
			return "Jungly2";
		case 10801:
			return "Landlubber";
		case 11058:
			return "Jolly-R";
		case 10901: // brimhaven dungeon entrance
			return "Pathways";
		case 10645: // brimhaven dungeon
		case 10644:
		case 10900:
			return "7th Realm";
		case 11315: // crandor
		case 11314:
			return "The Shadow";
		case 11414: // karanja underground
		case 11413:
			return "Dangerous Road";
		case 7505: // strongholf of security war
			return "Dogs of War";
		case 8017: // strongholf of security famine
			return "Food for Thought";
		case 8530: // strongholf of security pestile
			return "Malady";
		case 9297: // strongholf of security death
			return "Dance of Death";
		case 10040:
			return "Lighthouse";
		case 10140: // inside lighthouse
			return "Out of the Deep";
		case 9797:
			return "Crystal Cave";
		case 9541:
			return "Faerie";
		case 11927: // gamers grotto
			return "Cave Background";
		case 10301: // dz
			return "Skyfall";
		case 14646:// Port Phasmatys
			return "The Other Side";
		case 14746:// Ectofuntus
			return "Phasmatys";
		case 14747:// Port Phasmatys brewery
			return "Brew Hoo Hoo";
		case 15967:// Runespan
			return "Runespan";
		case 15711:// Runespan
			return "Runearia";
		case 15710:// Runespan
			return "Runebreath";
		case 13152: // crucible
			return "Hunted";
		case 13151: // crucible
			return "Target";
		case 12895: // crucible
			return "I Can See You";
		case 12896: // crucible
			return "You Will Know Me";
		case 12597:
			return "Spirit";
		case 13109:
			return "Medieval";
		case 13110:
			return "Honkytonky Parade";
		case 10658:
			return "Espionage";
		case 13899: // water altar
			return "Zealot";
		case 10039:
			return "Legion";
		case 11319: // warriors guild
			return "Warriors' Guild";
		case 11575: // burthope
			return "Spiritual";
		case 11573: // taverley
			return "Taverley Ambience";
		case 7473:
			return "The Waiting Game";
		case 18512:
		case 18511:
		case 19024:
			return "Tzhaar City I";
		case 18255: // fight pits
			return "Tzhaar Supremacy I";
		case 14672:
		case 14671:
		case 14415:
		case 14416:
			return "Living Rock";
		case 11157: // Brimhaven Agility Arena
			return "Aztec";
		case 15446:
		case 15957:
		case 15958:
			return "Dead and Buried";
		case 12848:
			return "Arabian3";
		case 12954:
		case 12442:
		case 12441:
			return "Scape Cave";
		case 12185:
		case 11929:
			return "Dwarf Theme";
		case 12184:
			return "Workshop";
		case 6992:
		case 6993: // mole lair
			return "The Mad Mole";
		case 9776: // castle wars
			return "Melodrama";
		case 10029:
		case 10285:
			return "Jungle Hunt";
		case 14231: // barrows under
			return "Dangerous Way";
		case 12856: // chaos temple
			return "Faithless";
		case 13104:
		case 12847: // arround desert camp
		case 13359:
		case 13102:
			return "Desert Voyage";
		case 13103:
			return "Lonesome";
		case 12589: // granite mine
			return "The Desert";
		case 18517: // polipore dungeon
		case 18516:
		case 18773:
		case 18775:
		case 13407: // crucible entrance
		case 13360: // dominion tower outside
			return "";
		case 14948:
			return "Dominion Lobby I";
		case 11836: // lava maze near kbd entrance
			return "Attack3";
		case 12091: // lava maze west
			return "Wilderness2";
		case 12092: // lava maze north
			return "Wild Side";
		case 9781:
			return "Gnome Village";
		case 11339: // air altar
			return "Serene";
		case 11083: // mind altar
			return "Miracle Dance";
		case 10827: // water altar
			return "Zealot";
		case 10571: // earth altar
			return "Down to Earth";
		case 10315: // fire altar
			return "Quest";
		case 8523: // cosmic altar
			return "Stratosphere";
		case 9035: // chaos altar
			return "Complication";
		case 8779: // death altar
			return "La Mort";
		case 10059: // body altar
			return "Heart and Mind";
		case 9803: // law altar
			return "Righteousness";
		case 9547: // nature altar
			return "Understanding";
		case 9804: // blood altar
			return "Bloodbath";
		case 13107:
			return "Arabian2";
		case 13105:
			return "Al Kharid";
		case 12342: // edge
			return "Forever";
		case 10806:
			return "Overture";
		case 10899:
			return "Karamja Jam";
		case 13623:
			return "The Terrible Tower";
		case 12374:
			return "The Route of All Evil";
		case 9802:
			return "Undead Dungeon";
		case 10809: // east rellekka
			return "Borderland";
		case 10553: // Rellekka
			return "Rellekka";
		case 10552: // south
			return "Saga";
		case 10296: // south west
			return "Lullaby";
		case 10828: // south east
			return "Legend";
		case 9275:
			return "Volcanic Vikings";
		case 11061:
		case 11317:
			return "Fishing";
		case 9551:
			return "TzHaar!";
		case 12345:
			return "Eruption";
		case 12089:
			return "Dark";
		case 12446:
		case 12445:
			return "Wilderness";
		case 12343:
			return "Dangerous";
		case 14131:
			return "Dance of the Undead";
		case 11844:
		case 11588:
			return "The Vacant Abyss";
		case 13363: // duel arena hospital
			return "Shine";
		case 13362: // duel arena
			return "Duel Arena";
		case 12082: // port sarim
			return "Sea Shanty2";
		case 12081: // port sarim south
			return "Tomorrow";
		case 11602:
			return "Strength of Saradomin";
		case 12590:
			return "Bandit Camp";
		case 10329:
			return "The Sound of Guthix";
		case 9033:
			return "Attack5";
		// godwars
		case 11603:
			return "Zamorak Zoo";
		case 11346:
			return "Armadyl Alliance";
		case 11347:
			return "Armageddon";
		case 13114:
			return "Wilderness";
		// black kngihts fortess
		case 12086:
			return "Knightmare";
		// tzaar
		case 9552:
			return "Fire and Brimstone";
		// kq
		case 13972:
			return "Insect Queen";
		// clan wars free for all:
		case 11094:
			return "Clan Wars";
		/*
		 * tutorial island
		 */
		case 12336:
			return "Newbie Melody";
		/*
		 * darkmeyer
		 */
		case 14644:
			return "Darkmeyer";
		/*
		 * kalaboss
		 */
		case 13626:
		case 13627:
		case 13882:
		case 13881:
			return "Daemonheim Entrance";
		/*
		 * Lumbridge, falador and region.
		 */
		case 11574: // heroes guild
			return "Splendour";
		case 12851:
			return "Autumn Voyage";
		case 12338: // draynor and market
			return "Unknown Land";
		case 12339: // draynor up
			return "Start";
		case 12340: // draynor mansion
			return "Spooky";
		case 12850: // lumbry castle
			return "Harmony";
		case 12849: // east lumbridge swamp
			return "Yesteryear";
		case 12593: // at Lumbridge Swamp.
			return "Book of Spells";
		case 12594: // on the path between Lumbridge and Draynor.
			return "Dream";
		case 12595: // at the Lumbridge windmill area.
			return "Flute Salad";
		case 12854: // at Varrock Palace.
			return "Adventure";
		case 12853: // at varrock center
			return "Garden";
		case 12852: // varock mages
			return "Expanse";
		case 13108:
			return "Still Night";
		case 12083:
			return "Wander";
		case 11828:
			return "Fanfare";
		case 11829:
			return "Scape Soft";
		case 11577:
			return "Mad Eadgar";
		case 10293: // at the Fishing Guild.
			return "Mellow";
		case 11824:
			return "Mudskipper Melody";
		case 11570:
			return "Wandar";
		case 12341:
			return "Barbarianims";
		case 12855:
			return "Crystal Sword";
		case 12344:
			return "Dark";
		case 12599:
			return "Doorways";
		case 12598:
			return "The Trade Parade";
		case 11318:
			return "Ice Melody";
		case 12600:
			return "Scape Wild";
		case 10032: // west yannile:
			return "Big Chords";
		case 10288: // east yanille
			return "Magic Dance";
		case 11826: // Rimmington
			return "Long Way Home";
		case 11825: // rimmigton coast
			return "Attention";
		case 11827: // north rimmigton
			return "Nightfall";
		/*
		 * Camelot and region.
		 */
		case 11062:
		case 10805:
			return "Camelot";
		case 10550:
			return "Talking Forest";
		case 10549:
			return "Lasting";
		case 10548:
			return "Wonderous";
		case 10547:
			return "Baroque";
		case 10291:
		case 10292:
			return "Knightly";
		case 11571: // crafting guild
			return "Miles Away";
		case 11595: // ess mine
			return "Rune Essence";
		case 10294:
			return "Theme";
		case 12349:
			return "Mage Arena";
		case 13365: // digsite
			return "Venture";
		case 13364: // exams center
			return "Medieval";
		case 13878: // canifis
			return "Village";
		case 13877: // canafis south
			return "Waterlogged";
		/*
		 * Mobilies Armies.
		 */
		case 9516:
			return "Command Centre";
		case 12596: // champions guild
			return "Greatness";
		case 10804: // legends guild
			return "Trinity";
		case 11601:
			return "Zaros Zeitgeist"; // zaros godwars
		default:
			return null;
		}
	}

	public static final int getMusicId(String musicName) {
		if (musicName == null)
			return -1;
		if (musicName.equals(""))
			return -2;
		if (musicName.equals("Skyfall"))
			return 2000;
		if (musicName.equals("Stronger (What Doesn't Kill You)"))
			return 2001;
		int musicIndex = (int) ClientScriptMap.getMap(1345).getKeyForValue(musicName);
		return ClientScriptMap.getMap(1351).getIntValue(musicIndex);
	}

	public boolean isLoadedItemSpawns() {
		return loadedItemSpawns;
	}

	public void setLoadedItemSpawns(boolean loadedItemSpawns) {
		this.loadedItemSpawns = loadedItemSpawns;
	}

	public int getMusicId() {
		if (musicIds == null)
			return -1;
		if (musicIds.length == 1)
			return musicIds[0];
		return musicIds[Utils.getRandom(musicIds.length - 1)];
	}

	protected List<Projectile> projectiles;
	

	public boolean addProjectile(Projectile projectile) {
		return projectiles.add(projectile);
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public void removeProjectiles() {
		projectiles.clear();
	}
}