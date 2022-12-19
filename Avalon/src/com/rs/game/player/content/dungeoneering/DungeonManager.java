package com.rs.game.player.content.dungeoneering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.map.MapBuilder;
import com.rs.game.npc.dungeonnering.AnimatedBook;
import com.rs.game.npc.dungeonnering.AnimatedPickaxe;
import com.rs.game.npc.dungeonnering.Ankou;
import com.rs.game.npc.dungeonnering.AsteaFrostweb;
import com.rs.game.npc.dungeonnering.BalLakThePummeler;
import com.rs.game.npc.dungeonnering.Bat;
import com.rs.game.npc.dungeonnering.Blink;
import com.rs.game.npc.dungeonnering.Brute;
import com.rs.game.npc.dungeonnering.BulwarkBeast;
import com.rs.game.npc.dungeonnering.Demon;
import com.rs.game.npc.dungeonnering.DivineSkinweaver;
import com.rs.game.npc.dungeonnering.Dreadnaut;
import com.rs.game.npc.dungeonnering.DungeonBoss;
import com.rs.game.npc.dungeonnering.DungeonFishSpot;
import com.rs.game.npc.dungeonnering.DungeonHunterNPC;
import com.rs.game.npc.dungeonnering.DungeonNPC;
import com.rs.game.npc.dungeonnering.DungeonSkeletonBoss;
import com.rs.game.npc.dungeonnering.DungeonSlayerNPC;
import com.rs.game.npc.dungeonnering.DungeonSpider;
import com.rs.game.npc.dungeonnering.EarthWarrior;
import com.rs.game.npc.dungeonnering.FireGiant;
import com.rs.game.npc.dungeonnering.FleshspoilerHaasghenahk;
import com.rs.game.npc.dungeonnering.ForgottenWarrior;
import com.rs.game.npc.dungeonnering.Ghost;
import com.rs.game.npc.dungeonnering.GiantBat;
import com.rs.game.npc.dungeonnering.GiantRat;
import com.rs.game.npc.dungeonnering.GiantSkeleton;
import com.rs.game.npc.dungeonnering.GluttonousBehemoth;
import com.rs.game.npc.dungeonnering.Gravecreeper;
import com.rs.game.npc.dungeonnering.GuardDog;
import com.rs.game.npc.dungeonnering.Guardian;
import com.rs.game.npc.dungeonnering.Hellhound;
import com.rs.game.npc.dungeonnering.HillGiant;
import com.rs.game.npc.dungeonnering.HobgoblinGeomancer;
import com.rs.game.npc.dungeonnering.HopeDevourer;
import com.rs.game.npc.dungeonnering.Hydra;
import com.rs.game.npc.dungeonnering.IceElemental;
import com.rs.game.npc.dungeonnering.IceSpider;
import com.rs.game.npc.dungeonnering.IceTroll;
import com.rs.game.npc.dungeonnering.IceWarrior;
import com.rs.game.npc.dungeonnering.Icefiend;
import com.rs.game.npc.dungeonnering.IcyBones;
import com.rs.game.npc.dungeonnering.IronDragon;
import com.rs.game.npc.dungeonnering.KalGerWarmonger;
import com.rs.game.npc.dungeonnering.LakkTheRiftSplitter;
import com.rs.game.npc.dungeonnering.LeatherDragon;
import com.rs.game.npc.dungeonnering.LexicusRunewright;
import com.rs.game.npc.dungeonnering.LuminscentIcefiend;
import com.rs.game.npc.dungeonnering.MastyxTrap;
import com.rs.game.npc.dungeonnering.MysteriousShade;
import com.rs.game.npc.dungeonnering.NecroLord;
import com.rs.game.npc.dungeonnering.Necromancer;
import com.rs.game.npc.dungeonnering.NightGazerKhighorahk;
import com.rs.game.npc.dungeonnering.PlaneFreezerLakhrahnaz;
import com.rs.game.npc.dungeonnering.Rammernaut;
import com.rs.game.npc.dungeonnering.RebornMage;
import com.rs.game.npc.dungeonnering.RebornWarrior;
import com.rs.game.npc.dungeonnering.RuneboundBehemoth;
import com.rs.game.npc.dungeonnering.Sagittare;
import com.rs.game.npc.dungeonnering.ShadowForgerIhlakhizan;
import com.rs.game.npc.dungeonnering.SkeletalAdventurer;
import com.rs.game.npc.dungeonnering.SkeletonMage;
import com.rs.game.npc.dungeonnering.SkeletonMelee;
import com.rs.game.npc.dungeonnering.SkeletonRange;
import com.rs.game.npc.dungeonnering.Stomp;
import com.rs.game.npc.dungeonnering.ThrowerTroll;
import com.rs.game.npc.dungeonnering.ToKashBloodChiller;
import com.rs.game.npc.dungeonnering.UnholyCursebearer;
import com.rs.game.npc.dungeonnering.WarpedGulega;
import com.rs.game.npc.dungeonnering.WorldGorgerShukarhazh;
import com.rs.game.npc.dungeonnering.YkLagorThunderous;
import com.rs.game.npc.dungeonnering.ZombieMelee;
import com.rs.game.npc.dungeonnering.ZombieRange;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.OwnedObjectManager;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.newmining.DungeoneeringMining;
import com.rs.game.player.content.ItemConstants;
import com.rs.game.player.content.dungeoneering.DungeonConstants.KeyDoors;
import com.rs.game.player.content.dungeoneering.DungeonConstants.MapRoomIcon;
import com.rs.game.player.content.dungeoneering.DungeonConstants.SkillDoors;
import com.rs.game.player.content.dungeoneering.rooms.BossRoom;
import com.rs.game.player.content.dungeoneering.rooms.HandledPuzzleRoom;
import com.rs.game.player.content.dungeoneering.rooms.StartRoom;
import com.rs.game.player.content.dungeoneering.rooms.puzzles.PoltergeistRoom;
import com.rs.game.player.content.dungeoneering.rooms.puzzles.PoltergeistRoom.Poltergeist;
import com.rs.game.player.content.dungeoneering.skills.DungeoneeringFishing;
import com.rs.game.player.controlers.DungeonControler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.net.decoders.handlers.ButtonHandler;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

public class DungeonManager {

	private static final Map<Object, DungeonManager> cachedDungeons = Collections
			.synchronizedMap(new HashMap<Object, DungeonManager>());
	public static final AtomicLong keyMaker = new AtomicLong();

	private DungeonPartyManager party;
	private Dungeon dungeon;
	private VisibleRoom[][] visibleMap;
	private int[] boundChuncks;
	private int stage; // 0 - not loaded. 1 - loaded. 2 - new one not loaded, old one loaded(rewards
						// screen)
	public RewardsTimer rewardsTimer;
	private DestroyTimer destroyTimer;
	private long time;
	private List<KeyDoors> keyList;
	private List<String> farmKeyList;
	private String key;

	private WorldTile groupGatestone;
	private List<MastyxTrap> mastyxTraps;

	// force saving deaths
	private Map<String, Integer> partyDeaths;

	private boolean tutorialMode;
	private int hsType = 0;

	private DungeonBoss temporaryBoss; // must for gravecreeper, cuz... it gets removed from npc list :/

	// 7554
	public DungeonManager(DungeonPartyManager party) {
		this(party, (long) Utils.random(1000000));
	}

	public DungeonManager(DungeonPartyManager party, long seed) {
		this.party = party;
		tutorialMode = party.getMaxComplexity() < 6;
		load(seed);
		keyList = new CopyOnWriteArrayList<KeyDoors>();
		farmKeyList = new CopyOnWriteArrayList<String>();
		mastyxTraps = new CopyOnWriteArrayList<MastyxTrap>();
		partyDeaths = new ConcurrentHashMap<String, Integer>();
	}

	public void clearKeyList() {
		for (Player player : party.getTeam()) {
			player.getPackets().sendRunScript(6072);
			player.getPackets().sendCSVarInteger(1875, 0); // forces refresh
		}
	}

	public void setKey(KeyDoors key, boolean add) {
		if (add) {
			keyList.add(key);
			for (Player player : party.getTeam())
				player.getPackets().sendGameMessage("<col=D2691E>Your party found a key: "
						+ ItemDefinitions.getItemDefinitions(key.getKeyId()).getName());
		} else {
			keyList.remove(key);
			for (Player player : party.getTeam())
				player.getPackets().sendGameMessage("<col=D2691E>Your party used a key: "
						+ ItemDefinitions.getItemDefinitions(key.getKeyId()).getName());
		}
		for (Player player : party.getTeam()) {
			player.getPackets().sendCSVarInteger(1812 + key.getIndex(), add ? 1 : 0);
			if (key.getIndex() != 64)
				player.getPackets().sendCSVarInteger(1875, keyList.contains(KeyDoors.GOLD_SHIELD) ? 1 : 0);
		}
	}

	public boolean isAtBossRoom(WorldTile tile) {
		return isAtBossRoom(tile, -1, -1, false);
	}

	public boolean isAtBossRoom(WorldTile tile, int x, int y, boolean check) {
		Room room = getRoom(getCurrentRoomReference(tile));
		if (room == null || !(room.getRoom() instanceof BossRoom))
			return false;
		if (check) {
			BossRoom bRoom = (BossRoom) room.getRoom();
			if (x != bRoom.getChunkX() || y != bRoom.getChunkY())
				return false;
		}
		return true;
	}

	public boolean isBossOpen() {
		for (int x = 0; x < visibleMap.length; x++) {
			for (int y = 0; y < visibleMap[x].length; y++) {
				VisibleRoom room = visibleMap[x][y];
				if (room == null || !room.isLoaded())
					continue;
				if (isAtBossRoom(getRoomCenterTile(room.reference)))
					return true;
			}
		}
		return false;
	}

	/*
	 * dont use
	 */
	public void refreshKeys(Player player) {
		for (KeyDoors key : keyList)
			player.getPackets().sendCSVarInteger(1812 + key.getIndex(), 1);
		player.getPackets().sendCSVarInteger(1875, keyList.contains(KeyDoors.GOLD_SHIELD) ? 1 : 0);
	}

	public boolean hasKey(KeyDoors key) {
		return keyList.contains(key);
	}

	public boolean isKeyShare() {
		return party.isKeyShare();
	}

	/*
	 * when dung ends to make sure no1 dies lo, well they can die but still..
	 */
	public void clearGuardians() {
		for (int x = 0; x < visibleMap.length; x++)
			for (int y = 0; y < visibleMap[x].length; y++)
				if (visibleMap[x][y] != null)
					visibleMap[x][y].forceRemoveGuardians();
	}

	public int getVisibleRoomsCount() {
		int count = 0;
		for (int x = 0; x < visibleMap.length; x++)
			for (int y = 0; y < visibleMap[x].length; y++)
				if (visibleMap[x][y] != null)
					count++;
		return count;
	}

	public int getVisibleBonusRoomsCount() {
		int count = 0;
		for (int x = 0; x < visibleMap.length; x++)
			for (int y = 0; y < visibleMap[x].length; y++)
				if (visibleMap[x][y] != null && !dungeon.getRoom(new RoomReference(x, y)).isCritPath())
					count++;
		return count;
	}

	public int getLevelModPerc() {
		int totalGuardians = 0;
		int killedGuardians = 0;

		for (int x = 0; x < visibleMap.length; x++)
			for (int y = 0; y < visibleMap[x].length; y++)
				if (visibleMap[x][y] != null) {
					totalGuardians += visibleMap[x][y].getGuardiansCount();
					killedGuardians += visibleMap[x][y].getKilledGuardiansCount();
				}

		return totalGuardians == 0 ? 100 : killedGuardians * 100 / totalGuardians;
	}

	public boolean enterRoom(final Player player, final int x, final int y) {
		if (x < 0 || y < 0 || x >= visibleMap.length || y >= visibleMap[0].length) {
			// System.out.println("ivalid room");
			return false;
		}
		final RoomReference roomReference = getCurrentRoomReference(player);
		if (visibleMap[x][y] != null) {
			if (!visibleMap[x][y].isLoaded()) {
				player.lock();
				WorldTasksManager.schedule(new WorldTask() {

					private int count;

					@Override
					public void run() {
						count++;
						if (!player.isLocked() || isDestroyed() || player.hasFinished()
								|| !player.getDungManager().isInside() || count > 10) {
							stop();
							player.unlock();
							return;
						}
						if (visibleMap[x][y].isLoaded()) {
							stop();
							nextRoom(player, roomReference, x, y);
							return;
						}
					}
				}, 0, 0);
			} else
				nextRoom(player, roomReference, x, y);
			return true;
		} else {
			loadRoom(x, y);
			return false;
		}
	}

	private void nextRoom(Player player, RoomReference roomReference, int x, int y) {
		int xOffset = x - roomReference.getX();
		int yOffset = y - roomReference.getY();
		player.lock(1);
		player.setNextWorldTile(new WorldTile(player.getX() + xOffset * 3, player.getY() + yOffset * 3, 0));
		playMusic(player, new RoomReference(x, y));
	}

	public void loadRoom(int x, int y) {
		loadRoom(new RoomReference(x, y));
	}

	public void loadRoom(final RoomReference reference) {
		final Room room = dungeon.getRoom(reference);
		if (room == null)
			return;
		VisibleRoom vr;
		if (room.getRoom() instanceof HandledPuzzleRoom) {
			HandledPuzzleRoom pr = (HandledPuzzleRoom) room.getRoom();
			vr = pr.createVisibleRoom();
		} else {
			vr = new VisibleRoom();
		}
		visibleMap[reference.getX()][reference.getY()] = vr;
		vr.init(this, reference, party.getFloorType(), room.getRoom());
		CoresManager.slowExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					openRoom(room, reference, visibleMap[reference.getX()][reference.getY()]);
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		});
	}

	public boolean isDestroyed() {
		return dungeon == null;
	}

	public void openRoom(final Room room, final RoomReference reference, final VisibleRoom visibleRoom) {
		final int toChunkX = boundChuncks[0] + reference.getX() * 2;
		final int toChunkY = boundChuncks[1] + reference.getY() * 2;
		MapBuilder.copy2RatioSquare(room.getChunkX(party.getComplexity()), room.getChunkY(party.getFloorType()),
				toChunkX, toChunkY, room.getRotation(), 0, 1);
		int regionId = (((toChunkX / 8) << 8) + (toChunkY / 8));
		for (Player player : party.getTeam()) {
			if (!player.getMapRegionsIds().contains(regionId))
				continue;
			player.setForceNextMapLoadRefresh(true);
			player.loadMapRegions();
		}
		World.executeAfterLoadRegion(regionId, new Runnable() {

			@Override
			public void run() {
				if (isDestroyed())
					return;
				room.openRoom(DungeonManager.this, reference);
				visibleRoom.openRoom();
				for (int i = 0; i < room.getRoom().getDoorDirections().length; i++) {
					Door door = room.getDoor(i);
					if (door == null)
						continue;
					int rotation = (room.getRoom().getDoorDirections()[i] + room.getRotation()) & 0x3;
					if (door.getType() == DungeonConstants.KEY_DOOR) {
						KeyDoors keyDoor = KeyDoors.values()[door.getId()];
						setDoor(reference, keyDoor.getObjectId(), keyDoor.getDoorId(party.getFloorType()), rotation);
					} else if (door.getType() == DungeonConstants.GUARDIAN_DOOR) {
						setDoor(reference, -1, DungeonConstants.DUNGEON_GUARDIAN_DOORS[party.getFloorType()], rotation);
						if (visibleRoom.roomCleared()) // remove referene since done
							room.setDoor(i, null);
					} else if (door.getType() == DungeonConstants.SKILL_DOOR) {
						SkillDoors skillDoor = SkillDoors.values()[door.getId()];
						int type = party.getFloorType();
						int closedId = skillDoor.getClosedObject(type);
						int openId = skillDoor.getOpenObject(type);
						setDoor(reference, openId == -1 ? closedId : -1, openId != -1 ? closedId : -1, rotation);
					}
				}
				if (room.getRoom().allowResources())
					setResources(room, reference, toChunkX, toChunkY);

				if (room.getDropId() != -1)
					setKey(room, reference, toChunkX, toChunkY);
				visibleRoom.setLoaded();
			}

		});
	}

	public void setDoor(RoomReference reference, int lockObjectId, int doorObjectId, int rotation) {
		if (lockObjectId != -1) {
			int[] xy = DungeonManager.translate(1, 7, rotation, 1, 2, 0);
			World.spawnObject(
					new WorldObject(lockObjectId, 10, rotation, ((boundChuncks[0] * 8) + reference.getX() * 16) + xy[0],
							((boundChuncks[1] * 8) + reference.getY() * 16) + xy[1], 0));
		}
		if (doorObjectId != -1) {
			int[] xy = DungeonManager.translate(0, 7, rotation, 1, 2, 0);
			World.spawnObject(
					new WorldObject(doorObjectId, 10, rotation, ((boundChuncks[0] * 8) + reference.getX() * 16) + xy[0],
							((boundChuncks[1] * 8) + reference.getY() * 16) + xy[1], 0));
		}
	}

	public void setKey(Room room, RoomReference reference, int toChunkX, int toChunkY) {

		int[] loc = room.getRoom().getKeySpot();
		if (loc != null) {
			spawnItem(reference, new Item(room.getDropId()), loc[0], loc[1]);
			return;
		}

		spawnItem(reference, new Item(room.getDropId()), 7, 1); // spawn it on the entrance door
	}

	public void setResources(Room room, RoomReference reference, int toChunkX, int toChunkY) {
		if (party.getComplexity() >= 5 && Utils.random(3) == 0) { // sets thief chest
			for (int i = 0; i < DungeonConstants.SET_RESOURCES_MAX_TRY; i++) {
				int rotation = Utils.random(DungeonConstants.WALL_BASE_X_Y.length);
				int[] b = DungeonConstants.WALL_BASE_X_Y[rotation];
				int x = b[0] + Utils.random(b[2]);
				int y = b[1] + Utils.random(b[3]);
				if (((x >= 6 && x <= 8) && b[2] != 0) || ((y >= 6 && y <= 8) && b[3] != 0))
					continue;
				if (!World.isFloorFree(0, toChunkX * 8 + x, toChunkY * 8 + y)
						|| !World.isTileFree(0, toChunkX * 8 + x - Utils.DOOR_ROTATION_DIR_X[((rotation + 3) & 0x3)],
								toChunkY * 8 + y - Utils.DOOR_ROTATION_DIR_Y[((rotation + 3) & 0x3)], 0))
					continue;
				room.setThiefChest(Utils.random(10));
				World.spawnObject(new WorldObject(DungeonConstants.THIEF_CHEST_LOCKED[party.getFloorType()], 10,
						((rotation + 3) & 0x3), toChunkX * 8 + x, toChunkY * 8 + y, 0));
				if (Settings.DEBUG)
					System.out.println("Added chest spot.");
				break;
			}
		}
		if (party.getComplexity() >= 4 && Utils.random(3) == 0) { // sets flower
			for (int i = 0; i < DungeonConstants.SET_RESOURCES_MAX_TRY; i++) {
				int rotation = Utils.random(DungeonConstants.WALL_BASE_X_Y.length);
				int[] b = DungeonConstants.WALL_BASE_X_Y[rotation];
				int x = b[0] + Utils.random(b[2]);
				int y = b[1] + Utils.random(b[3]);
				if (((x >= 6 && x <= 8) && b[2] != 0) || ((y >= 6 && y <= 8) && b[3] != 0))
					continue;
				if (!World.isFloorFree(0, toChunkX * 8 + x, toChunkY * 8 + y))
					continue;
				World.spawnObject(
						new WorldObject(DungeonUtils.getFarmingResource(Utils.random(10), party.getFloorType()), 10,
								rotation, toChunkX * 8 + x, toChunkY * 8 + y, 0));
				if (Settings.DEBUG)
					System.out.println("Added flower spot.");
				break;
			}
		}
		if (party.getComplexity() >= 3 && Utils.random(3) == 0) { // sets rock
			for (int i = 0; i < DungeonConstants.SET_RESOURCES_MAX_TRY; i++) {
				int rotation = Utils.random(DungeonConstants.WALL_BASE_X_Y.length);
				int[] b = DungeonConstants.WALL_BASE_X_Y[rotation];
				int x = b[0] + Utils.random(b[2]);
				int y = b[1] + Utils.random(b[3]);
				if (((x >= 6 && x <= 8) && b[2] != 0) || ((y >= 6 && y <= 8) && b[3] != 0))
					continue;
				if (!World.isFloorFree(0, toChunkX * 8 + x, toChunkY * 8 + y))
					continue;
				World.spawnObject(new WorldObject(DungeonUtils.getMiningResource(
						Utils.random(DungeoneeringMining.DungeoneeringRocks.values().length), party.getFloorType()), 10,
						rotation, toChunkX * 8 + x, toChunkY * 8 + y, 0));
				if (Settings.DEBUG)
					System.out.println("Added rock spot.");
				break;
			}
		}
		if (party.getComplexity() >= 2 && Utils.random(3) == 0) { // sets tree
			for (int i = 0; i < DungeonConstants.SET_RESOURCES_MAX_TRY; i++) {
				int rotation = Utils.random(DungeonConstants.WALL_BASE_X_Y.length);
				int[] b = DungeonConstants.WALL_BASE_X_Y[rotation];
				int x = b[0] + Utils.random(b[2]);
				int y = b[1] + Utils.random(b[3]);
				if (((x >= 6 && x <= 8) && b[2] != 0) || ((y >= 6 && y <= 8) && b[3] != 0))
					continue;
				if (!World.isWallsFree(0, toChunkX * 8 + x, toChunkY * 8 + y)
						|| !World.isFloorFree(0, toChunkX * 8 + x, toChunkY * 8 + y))
					continue;
				x -= Utils.DOOR_ROTATION_DIR_X[rotation];
				y -= Utils.DOOR_ROTATION_DIR_Y[rotation];
				if (Settings.DEBUG)
					System.out.println("Added tree spot");
				World.spawnObject(
						new WorldObject(DungeonUtils.getWoodcuttingResource(Utils.random(10), party.getFloorType()), 10,
								rotation, toChunkX * 8 + x, toChunkY * 8 + y, 0));
				break;
			}
		}
		if (party.getComplexity() >= 2) { // sets fish spot
			List<int[]> fishSpots = new ArrayList<int[]>();
			for (int x = 0; x < 16; x++) {
				for (int y = 0; y < 16; y++) {
					WorldObject o = World.getObjectWithType(new WorldTile(toChunkX * 8 + x, toChunkY * 8 + y, 0), 10);
					if (o == null || o.getId() != DungeonConstants.FISH_SPOT_OBJECT_ID)
						continue;
					fishSpots.add(new int[] { x, y });
				}
			}
			if (!fishSpots.isEmpty()) {
				int[] spot = fishSpots.get(Utils.random(fishSpots.size()));
				spawnNPC(DungeonConstants.FISH_SPOT_NPC_ID, room.getRotation(),
						new WorldTile(toChunkX * 8 + spot[0], toChunkY * 8 + spot[1], 0), reference,
						DungeonConstants.FISH_SPOT_NPC, 1);
				if (Settings.DEBUG)
					System.out.println("Added fish spot");
			}
		}
	}

	public WorldTile getRoomCenterTile(RoomReference reference) {
		return getRoomBaseTile(reference).transform(8, 8, 0);
	}

	public WorldTile getRoomBaseTile(RoomReference reference) {
		return new WorldTile(((boundChuncks[0] << 3) + reference.getX() * 16),
				((boundChuncks[1] << 3) + reference.getY() * 16), 0);
	}

	public RoomReference getCurrentRoomReference(WorldTile tile) {
		return new RoomReference((tile.getChunkX() - boundChuncks[0]) / 2, ((tile.getChunkY() - boundChuncks[1]) / 2));
	}

	public Room getRoom(RoomReference reference) {
		return dungeon == null ? null : dungeon.getRoom(reference);
	}

	public VisibleRoom getVisibleRoom(RoomReference reference) {
		if (reference.getX() >= visibleMap.length || reference.getY() >= visibleMap[0].length)
			return null;
		return visibleMap[reference.getX()][reference.getY()];
	}

	public WorldTile getHomeTile() {
		return getRoomCenterTile(dungeon.getStartRoomReference());
	}

	public void telePartyToRoom(RoomReference reference) {
		WorldTile tile = getRoomCenterTile(reference);
		for (Player player : party.getTeam()) {
			player.setNextWorldTile(tile);
			playMusic(player, reference);
		}
	}

	public void playMusic(Player player, RoomReference reference) {
		if (reference.getX() >= visibleMap.length || reference.getY() >= visibleMap[reference.getX()].length)
			return;
		player.getMusicsManager().forcePlayMusic(visibleMap[reference.getX()][reference.getY()].getMusicId()); // fixed
	}

	public void linkPartyToDungeon() {
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				for (Player player : party.getTeam()) {
					resetItems(player, false, false);
					sendSettings(player);
					if (party.getComplexity() >= 5 && party.isLeader(player))
						player.getInventory().addItem(new Item(DungeonConstants.GROUP_GATESTONE));
					removeMark(player);
					sendStartItems(player);
					player.getPackets().sendGameMessage("");
					player.getPackets().sendGameMessage("-Welcome to Daemonheim-");
					player.getPackets().sendGameMessage("Floor <col=641d9e>" + party.getFloor()
							+ "    <col=ffffff>Complexity <col=641d9e>" + party.getComplexity());
					String[] sizeNames = new String[] { "Small", "Medium", "Large", "Test" };
					player.getPackets().sendGameMessage("Dungeon Size: " + "<col=641d9e>" + sizeNames[party.getSize()]);
					player.getPackets().sendGameMessage(
							"Party Size:Difficulty <col=641d9e>" + party.getTeam().size() + ":" + party.getDificulty());
					player.getPackets().sendGameMessage("Pre-Share: <col=641d9e>" + (isKeyShare() ? "OFF" : "ON"));

					if (party.isGuideMode())
						player.getPackets().sendGameMessage("<col=641d9e>Guide Mode ON");
					player.getPackets().sendGameMessage("");
					player.lock(2);
				}
				resetGatestone();
			}
		});
	}

	public void setTableItems(RoomReference room) {
		addItemToTable(room, new Item(16295)); // novite pickaxe, cuz of boss aswell so 1+
		if (party.getComplexity() >= 2) {
			addItemToTable(room, new Item(DungeonConstants.RUSTY_COINS, 5000 + Utils.random(10000)));
			addItemToTable(room, new Item(17678)); // tinderbox
			addItemToTable(room, new Item(16361)); // novite hatcher
			addItemToTable(room, new Item(17794)); // fish rods
		}
		if (party.getComplexity() >= 3) { // set weap/gear in table
			int rangeTier = DungeonUtils.getTier(party.getMaxLevel(Skills.RANGE));
			if (rangeTier > 8)
				rangeTier = 8;
			addItemToTable(room, new Item(DungeonUtils.getArrows(1 + Utils.random(rangeTier)), 90 + Utils.random(30))); // arround
																														// 100
																														// arrows,
																														// type
																														// random
																														// up
																														// to
																														// best
																														// u
																														// can,
																														// limited
																														// to
																														// tier
																														// 8
			addItemToTable(room, new Item(DungeonConstants.RUNES[0], 90 + Utils.random(30))); // arround 100 air runes
			addItemToTable(room, new Item(DungeonConstants.RUNE_ESSENCE, 90 + Utils.random(30))); // arround 100 essence
			addItemToTable(room, new Item(17754)); // knife
			addItemToTable(room, new Item(17883)); // hammer
			if (party.getComplexity() >= 4)
				addItemToTable(room, new Item(17446)); // needle
		}
		for (@SuppressWarnings("unused")
		Player player : party.getTeam()) {
			for (int i = 0; i < 7 + Utils.random(4); i++)
				// 9 food
				addItemToTable(room, new Item(DungeonUtils.getFood(1 + Utils.random(8))));
			if (party.getComplexity() >= 3) { // set weap/gear in table
				for (int i = 0; i < 1 + Utils.random(3); i++)
					// 1 up to 3 pieces of gear or weap
					addItemToTable(room, new Item(DungeonUtils.getRandomGear(1 + Utils.random(8)))); // arround 100
																										// essence
			}
		}
	}

	public void addItemToTable(RoomReference room, Item item) {
		int slot = Utils.random(10); // 10 spaces for items
		if (slot < 6)
			spawnItem(room, item, 9 + Utils.random(3), 10 + Utils.random(2));
		else if (slot < 8)
			spawnItem(room, item, 10 + Utils.random(2), 14);
		else
			spawnItem(room, item, 14, 10 + Utils.random(2));
	}

	public void sendStartItems(Player player) {
		if (party.getComplexity() == 1) {
			player.getDialogueManager().startDialogue("SimpleMessage", "<col=0000FF>Complexity 1", "Combat only",
					"Armour and weapons allocated", "No shop stock");
		} else if (party.getComplexity() == 2) {
			player.getDialogueManager().startDialogue("SimpleMessage", "<col=0000FF>Complexity 2",
					"+ Fishing, Woodcutting, Firemaking, Cooking", "Armour and weapons allocated",
					"Minimal shop stock");
		} else if (party.getComplexity() == 3) {
			player.getDialogueManager().startDialogue("SimpleMessage", "<col=0000FF>Complexity 3",
					"+ Mining, Smithing weapons, Fletching, Runecrafting", "Armour allocated", "Increased shop stock");
		} else if (party.getComplexity() == 4) {
			player.getDialogueManager().startDialogue("SimpleMessage", "<col=0000FF>Complexity 4",
					"+ Smithing armour, Hunter, Farming textiles, Crafting", "Increased shop stock");
		} else if (party.getComplexity() == 5) {
			player.getDialogueManager().startDialogue("SimpleMessage", "<col=0000FF>Complexity 5",
					"All skills included", "+ Farming seeds, Herblore, Thieving, Summoning", "Complete shop stock",
					"Challenge rooms + skill doors");
		}
		if (party.getComplexity() <= 3) {
			int defenceTier = DungeonUtils.getTier(player.getSkills().getLevelForXp(Skills.DEFENCE));
			if (defenceTier > 8)
				defenceTier = 8;
			player.getInventory().addItem(new Item(DungeonUtils.getPlatebody(defenceTier)));
			player.getInventory()
					.addItem(new Item(DungeonUtils.getPlatelegs(defenceTier, player.getAppearence().isMale())));
			if (party.getComplexity() <= 2) {
				int attackTier = DungeonUtils.getTier(player.getSkills().getLevelForXp(Skills.ATTACK));
				if (attackTier > 8)
					attackTier = 8;
				player.getInventory().addItem(new Item(DungeonUtils.getRapier(defenceTier)));
				player.getInventory().addItem(new Item(DungeonUtils.getBattleaxe(defenceTier)));
			}
			int magicTier = DungeonUtils.getTier(player.getSkills().getLevelForXp(Skills.MAGIC));
			if (magicTier > 8)
				magicTier = 8;
			player.getInventory()
					.addItem(new Item(DungeonUtils.getRobeTop(defenceTier < magicTier ? defenceTier : magicTier)));
			player.getInventory()
					.addItem(new Item(DungeonUtils.getRobeBottom(defenceTier < magicTier ? defenceTier : magicTier)));
			if (party.getComplexity() <= 2) {
				player.getInventory().addItem(new Item(DungeonConstants.RUNES[0], 90 + Utils.random(30)));
				player.getInventory()
						.addItem(new Item(DungeonUtils.getStartRunes(player.getSkills().getLevelForXp(Skills.MAGIC)),
								90 + Utils.random(30)));
				player.getInventory().addItem(new Item(DungeonUtils.getElementalStaff(magicTier)));
			}
			int rangeTier = DungeonUtils.getTier(player.getSkills().getLevelForXp(Skills.RANGE));
			if (rangeTier > 8)
				rangeTier = 8;
			player.getInventory()
					.addItem(new Item(DungeonUtils.getLeatherBody(defenceTier < rangeTier ? defenceTier : rangeTier)));
			player.getInventory()
					.addItem(new Item(DungeonUtils.getChaps(defenceTier < rangeTier ? defenceTier : rangeTier)));
			if (party.getComplexity() <= 2) {
				player.getInventory().addItem(new Item(DungeonUtils.getShortbow(rangeTier)));
				player.getInventory().addItem(new Item(DungeonUtils.getArrows(rangeTier), 90 + Utils.random(30)));
			}
		}
		if (party.getComplexity() == 6) {
			player.getInventory().addItem(new Item(16909));
			player.getInventory().addItem(new Item(17259));
			player.getInventory().addItem(new Item(17295));
			player.getInventory().addItem(new Item(16477, 500));
		}
	}

	public void sendSettings(Player player) {
		// TODO int prayerRenewal = player.getPrayerRenewalDelay();
		player.reset();
		;
		// player.setPrayerRenewalDelay(prayerRenewal);
		if (player.getControlerManager().getControler() instanceof DungeonControler)
			((DungeonControler) player.getControlerManager().getControler()).reset();
		else {
			player.getControlerManager().startControler("DungeonControler", DungeonManager.this);
			player.setForceMultiArea(true);
			player.setLargeSceneView(true);
			player.getCombatDefinitions().setSpellBook(3);
			// TODO player.getToolbelt().switchDungeonneringToolbelt();
			setWorldMap(player, true);
		}
		sendRing(player);
		sendBindItems(player);
		wearInventory(player);
	}

	public void rejoinParty(Player player) {
		player.stopAll();
		player.lock(2);
		party.add(player);
		sendSettings(player);
		refreshKeys(player);
		player.setNextWorldTile(getHomeTile());
		playMusic(player, dungeon.getStartRoomReference());
	}

	public void sendBindItems(Player player) {
		Item ammo = player.getDungManager().getBindedAmmo();
		if (ammo != null)
			player.getInventory().addItem(ammo);
		for (Item item : player.getDungManager().getBindedItems().getContainerItems()) {
			if (item == null)
				continue;
			player.getInventory().addItem(item);
		}
	}

	public void resetItems(Player player, boolean drop, boolean logout) {
		if (drop) {
			for (Item item : player.getEquipment().getItems().getContainerItems()) {
				if (item == null || !ItemConstants.isTradeable(item))
					continue;
				World.addGroundItem(item, new WorldTile(player));
			}
			for (Item item : player.getInventory().getItems().getContainerItems()) {
				if (item == null || !ItemConstants.isTradeable(item))
					continue;
				if (hasLoadedNoRewardScreen() & item.getId() == DungeonConstants.GROUP_GATESTONE)
					setGroupGatestone(new WorldTile(player));
				World.addGroundItem(item, new WorldTile(player));
			}
		}
		player.getEquipment().reset();
		player.getInventory().reset();
		if (!logout)
			player.getAppearence().generateAppearenceData();
	}

	public void sendRing(Player player) {
		if (player.getInventory().containsItem(15707, 1)) // deletes current normal ring
			player.getInventory().deleteItem(15707, 1);
		player.getInventory().addItem(new Item(15707)); // todo in future if not leave set new ring
	}

	public void wearInventory(Player player) {
		boolean worn = false;
		for (int slotId = 0; slotId < 28; slotId++) {
			Item item = player.getInventory().getItem(slotId);
			if (item == null)
				continue;
			if (ButtonHandler.sendWear2(player, slotId, item.getId()))
				worn = true;
		}
		if (worn) {
			player.getAppearence().generateAppearenceData();
			player.getInventory().getItems().shift();
			player.getInventory().refresh();
		}

	}

	public void spawnRandomNPCS(RoomReference reference) {
		int floorType = party.getFloorType();
		for (int i = 0; i < 1 + Utils.random(3); i++) { // guardian creature
			spawnNPC(reference, DungeonUtils.getGuardianCreature(), 2 + Utils.getRandom(13), 2 + Utils.getRandom(13),
					true, DungeonConstants.GUARDIAN_DOOR, 0.5);
		}

		for (int i = 0; i < 1 + Utils.random(2); i++) { // guardian creature
			spawnNPC(reference, DungeonUtils.getGuardianCreature(floorType), 2 + Utils.getRandom(13),
					2 + Utils.getRandom(13), true, DungeonConstants.GUARDIAN_DOOR, Utils.random(2) == 0 ? 0.8 : 1);
		}
		if (Utils.random(3) == 0) // forgotten warrior
			spawnNPC(reference, DungeonUtils.getForgottenWarrior(), 2 + Utils.getRandom(13), 2 + Utils.getRandom(13),
					true, DungeonConstants.FORGOTTEN_WARRIOR);

		if (Utils.random(2) == 0) // hunter creature
			spawnNPC(reference, DungeonUtils.getHunterCreature(), 2 + Utils.getRandom(13), 2 + Utils.getRandom(13),
					true, DungeonConstants.HUNTER_NPC, 0.5);
		if (Utils.random(5) == 0) // slayer creature
			spawnNPC(reference, DungeonUtils.getSlayerCreature(), 2 + Utils.getRandom(13), 2 + Utils.getRandom(13),
					true, DungeonConstants.SLAYER_NPC);
	}

	public int[] getRoomPos(WorldTile tile) {
		int chunkX = tile.getX() / 16 * 2;
		int chunkY = tile.getY() / 16 * 2;
		int x = tile.getX() - chunkX * 8;
		int y = tile.getY() - chunkY * 8;
		Room room = getRoom(getCurrentRoomReference(tile));
		if (room == null)
			return null;
		return DungeonManager.translate(x, y, (4 - room.getRotation()) & 0x3, 1, 1, 0);
	}

	public static int[] translate(int x, int y, int mapRotation, int sizeX, int sizeY, int objectRotation) {
		int[] coords = new int[2];
		if ((objectRotation & 0x1) == 1) {
			int prevSizeX = sizeX;
			sizeX = sizeY;
			sizeY = prevSizeX;
		}
		if (mapRotation == 0) {
			coords[0] = x;
			coords[1] = y;
		} else if (mapRotation == 1) {
			coords[0] = y;
			coords[1] = 15 - x - (sizeX - 1);
		} else if (mapRotation == 2) {
			coords[0] = 15 - x - (sizeX - 1);
			coords[1] = 15 - y - (sizeY - 1);
		} else if (mapRotation == 3) {
			coords[0] = 15 - y - (sizeY - 1);
			coords[1] = x;
		}
		return coords;
	}

	public DungeonNPC spawnNPC(RoomReference reference, int id, int x, int y) {
		return spawnNPC(reference, id, x, y, false, DungeonConstants.NORMAL_NPC);
	}

	public DungeonNPC spawnNPC(RoomReference reference, int id, int x, int y, boolean check, int type) {
		return spawnNPC(reference, id, x, y, check, type, 1);
	}

	/*
	 * x 0-15, y 0-15
	 */

	public DungeonNPC spawnNPC(final RoomReference reference, final int id, int x, int y, boolean check, final int type,
			final double multiplier) {
		final int rotation = dungeon.getRoom(reference).getRotation();
		final int size = NPCDefinitions.getNPCDefinitions(id).size;
		int[] coords = translate(x, y, rotation, size, size, 0);
		final WorldTile tile = new WorldTile(((boundChuncks[0] * 8) + reference.getX() * 16) + coords[0],
				((boundChuncks[1] * 8) + reference.getY() * 16) + coords[1], 0);
		if (check && !World.isTileFree(0, tile.getX(), tile.getY(), size))
			return null;
		return spawnNPC(id, rotation, tile, reference, type, multiplier);
	}

	public WorldObject spawnObject(RoomReference reference, int id, int type, int rotation, int x, int y) {
		final int mapRotation = dungeon.getRoom(reference).getRotation();
		ObjectDefinitions defs = ObjectDefinitions.getObjectDefinitions(id);
		int[] coords = translate(x, y, mapRotation, defs.sizeX, defs.sizeY, rotation);
		WorldObject object = new WorldObject(id, type, (rotation + mapRotation) & 0x3,
				((boundChuncks[0] * 8) + reference.getX() * 16) + coords[0],
				((boundChuncks[1] * 8) + reference.getY() * 16) + coords[1], 0);
		World.spawnObject(object);
		return object;
	}

	public WorldObject spawnObjectForMapRotation(RoomReference reference, int id, int type, int rotation, int x, int y,
			int mapRotation) {
		ObjectDefinitions defs = ObjectDefinitions.getObjectDefinitions(id);
		int[] coords = translate(x, y, mapRotation, defs.sizeX, defs.sizeY, rotation);
		WorldObject object = new WorldObject(id, type, (rotation + mapRotation) & 0x3,
				((boundChuncks[0] * 8) + reference.getX() * 16) + coords[0],
				((boundChuncks[1] * 8) + reference.getY() * 16) + coords[1], 0);
		World.spawnObject(object);
		return object;
	}

	public WorldObject spawnObjectTemporary(RoomReference reference, int id, int type, int rotation, int x, int y,
			long time) {
		final int mapRotation = dungeon.getRoom(reference).getRotation();
		ObjectDefinitions defs = ObjectDefinitions.getObjectDefinitions(id);
		int[] coords = translate(x, y, mapRotation, defs.sizeX, defs.sizeY, rotation);
		WorldObject object = new WorldObject(id, type, (rotation + mapRotation) & 0x3,
				((boundChuncks[0] * 8) + reference.getX() * 16) + coords[0],
				((boundChuncks[1] * 8) + reference.getY() * 16) + coords[1], 0);
		World.spawnObjectTemporary(object, time);
		return object;
	}

	public void removeObject(RoomReference reference, int id, int type, int rotation, int x, int y) {
		final int mapRotation = dungeon.getRoom(reference).getRotation();
		ObjectDefinitions defs = ObjectDefinitions.getObjectDefinitions(id);
		int[] coords = translate(x, y, mapRotation, defs.sizeX, defs.sizeY, rotation);
		World.removeObject(new WorldObject(id, type, (rotation + mapRotation) & 0x3,
				((boundChuncks[0] * 8) + reference.getX() * 16) + coords[0],
				((boundChuncks[1] * 8) + reference.getY() * 16) + coords[1], 0));
	}

	public WorldObject getObject(RoomReference reference, int id, int x, int y) {
		final int mapRotation = dungeon.getRoom(reference).getRotation();
		ObjectDefinitions defs = ObjectDefinitions.getObjectDefinitions(id);
		int[] coords = translate(x, y, mapRotation, defs.sizeX, defs.sizeY, 0);
		return World.getObjectWithId(new WorldTile(((boundChuncks[0] * 8) + reference.getX() * 16) + coords[0],
				((boundChuncks[1] * 8) + reference.getY() * 16) + coords[1], 0), id);
	}

	public WorldObject getObjectWithType(RoomReference reference, int id, int type, int x, int y) {
		final int mapRotation = dungeon.getRoom(reference).getRotation();
		ObjectDefinitions defs = ObjectDefinitions.getObjectDefinitions(id);
		int[] coords = translate(x, y, mapRotation, defs.sizeX, defs.sizeY, 0);
		return World.getObjectWithType(new WorldTile(((boundChuncks[0] * 8) + reference.getX() * 16) + coords[0],
				((boundChuncks[1] * 8) + reference.getY() * 16) + coords[1], 0), type);
	}

	public WorldObject getObjectWithType(RoomReference reference, int type, int x, int y) {
		Room room = dungeon.getRoom(reference);
		if (room == null)
			return null;
		final int mapRotation = room.getRotation();
		int[] coords = translate(x, y, mapRotation, 1, 1, 0);
		return World.getObjectWithType(new WorldTile(((boundChuncks[0] * 8) + reference.getX() * 16) + coords[0],
				((boundChuncks[1] * 8) + reference.getY() * 16) + coords[1], 0), type);
	}

	public WorldTile getTile(RoomReference reference, int x, int y, int sizeX, int sizeY) {
		Room room = dungeon.getRoom(reference);
		if (room == null)
			return null;
		final int mapRotation = room.getRotation();
		int[] coords = translate(x, y, mapRotation, sizeX, sizeY, 0);
		return new WorldTile(((boundChuncks[0] * 8) + reference.getX() * 16) + coords[0],
				((boundChuncks[1] * 8) + reference.getY() * 16) + coords[1], 0);
	}

	public WorldTile getTile(RoomReference reference, int x, int y) {
		return getTile(reference, x, y, 1, 1);
	}

	public WorldTile getRotatedTile(RoomReference reference, int x, int y) {
		final int mapRotation = dungeon.getRoom(reference).getRotation();
		int[] coords = translate(x, y, mapRotation, 1, 1, 0);
		return new WorldTile(((boundChuncks[0] * 8) + reference.getX() * 16) + coords[0],
				((boundChuncks[1] * 8) + reference.getY() * 16) + coords[1], 0);
	}

	public void spawnItem(RoomReference reference, Item item, int x, int y) {
		final int mapRotation = dungeon.getRoom(reference).getRotation();
		int[] coords = translate(x, y, mapRotation, 1, 1, 0);
		World.addGroundItem(item, new WorldTile(((boundChuncks[0] * 8) + reference.getX() * 16) + coords[0],
				((boundChuncks[1] * 8) + reference.getY() * 16) + coords[1], 0));
	}

	public boolean isFloorFree(RoomReference reference, int x, int y) {
		final int mapRotation = dungeon.getRoom(reference).getRotation();
		int[] coords = translate(x, y, mapRotation, 1, 1, 0);
		return World.isFloorFree(0, ((boundChuncks[0] * 8) + reference.getX() * 16) + coords[0],
				((boundChuncks[1] * 8) + reference.getY() * 16) + coords[1]);
	}

	public WorldTile getRoomTile(RoomReference reference) {
		final int mapRotation = dungeon.getRoom(reference).getRotation();
		int[] coords = translate(0, 0, mapRotation, 1, 1, 0);
		return new WorldTile(((boundChuncks[0] * 8) + reference.getX() * 16) + coords[0],
				((boundChuncks[1] * 8) + reference.getY() * 16) + coords[1], 0);
	}

	public DungeonNPC spawnNPC(int id, int rotation, WorldTile tile, RoomReference reference, int type,
			double multiplier) {
		DungeonNPC n = null;
		if (type == DungeonConstants.BOSS_NPC) {
			if (id == 9965)
				n = new AsteaFrostweb(id, tile, this, reference);
			else if (id == 9948)
				n = new GluttonousBehemoth(id, tile, this, reference);
			else if (id == 9912)
				n = new LuminscentIcefiend(id, tile, this, reference);
			else if (id == 10059)
				n = new HobgoblinGeomancer(id, tile, this, reference);
			else if (id == 10040)
				n = new IcyBones(id, tile, this, reference);
			else if (id == 10024)
				n = new ToKashBloodChiller(id, tile, this, reference);
			else if (id == 9929)
				n = new PlaneFreezerLakhrahnaz(id, tile, this, reference);
			else if (id == 10058)
				n = new DivineSkinweaver(id, tile, this, reference);
			else if (id == 10630 || id == 10680 || id == 10693)
				n = new DungeonSkeletonBoss(id, tile, this, reference, multiplier);
			else if (id == 10073)
				n = new BulwarkBeast(id, tile, this, reference);
			else if (id == 9767)
				n = new Rammernaut(id, tile, this, reference);
			else if (id == 9782)
				n = new Stomp(id, tile, this, reference);
			else if (id == 10111)
				n = new UnholyCursebearer(id, tile, this, reference);
			else if (id == 9898)
				n = new LakkTheRiftSplitter(id, tile, this, reference);
			else if (id == 9753)
				n = new Sagittare(id, tile, this, reference);
			else if (id == 9725)
				n = new NightGazerKhighorahk(id, tile, this, reference);
			else if (id == 9842)
				n = new LexicusRunewright(id, tile, this, reference);
			else if (id == 10128)
				n = new BalLakThePummeler(id, tile, this, reference);
			else if (id == 10143)
				n = new ShadowForgerIhlakhizan(id, tile, this, reference);
			else if (id == 11940 || id == 11999 || id == 12044)
				n = new SkeletalAdventurer(id, tile, this, reference, multiplier);
			else if (id == 11812)
				n = new RuneboundBehemoth(id, tile, this, reference);
			else if (id == 11708)
				n = new Gravecreeper(id, tile, this, reference);
			else if (id == 11737 || id == 11895)
				n = new NecroLord(id, tile, this, reference);
			else if (id == 11925)
				n = new FleshspoilerHaasghenahk(id, tile, this, reference);
			else if (id == 11872)
				n = new YkLagorThunderous(id, tile, this, reference);
			else if (id == 12737)
				n = new WarpedGulega(id, tile, this, reference);
			else if (id == 12848)
				n = new Dreadnaut(id, tile, this, reference);
			else if (id == 12886)
				n = new HopeDevourer(id, tile, this, reference);
			else if (id == 12478)
				n = new WorldGorgerShukarhazh(id, tile, this, reference);
			else if (id == 12865)
				n = new Blink(id, tile, this, reference);
			else if (id == 12752)
				n = new KalGerWarmonger(id, tile, this, reference);
			else
				n = new DungeonBoss(id, tile, this, reference);
		} else if (type == DungeonConstants.GUARDIAN_NPC) {
			n = new Guardian(id, tile, this, reference, multiplier);
			visibleMap[reference.getX()][reference.getY()].addGuardian(n);
		} else if (type == DungeonConstants.FORGOTTEN_WARRIOR) {
			n = new ForgottenWarrior(id, tile, this, reference, multiplier);
			visibleMap[reference.getX()][reference.getY()].addGuardian(n);
		} else if (type == DungeonConstants.FISH_SPOT_NPC)
			n = new DungeonFishSpot(id, tile, this,
					DungeoneeringFishing.Fish.values()[Utils.random(DungeoneeringFishing.Fish.values().length - 1)]);
		else if (type == DungeonConstants.SLAYER_NPC)
			n = new DungeonSlayerNPC(id, tile, this, multiplier);
		else if (type == DungeonConstants.HUNTER_NPC)
			n = new DungeonHunterNPC(id, tile, this, multiplier);
		else if (type == DungeonConstants.PUZZLE_NPC) {
			if (id == PoltergeistRoom.POLTERGEIST_ID)
				n = new Poltergeist(id, tile, this, reference);
		} else {
			if (id >= 10225 && id <= 10235)
				n = new IceWarrior(id, tile, this, multiplier);
			else if (id >= 10157 && id <= 10167)
				n = new IceSpider(id, tile, this, multiplier);
			else if (id >= 10791 && id <= 10796)
				n = new ThrowerTroll(id, tile, this, multiplier);
			else if (id >= 10782 && id <= 10790)
				n = new IceTroll(id, tile, this, multiplier);
			else if (id >= 10460 && id <= 10468)
				n = new IceElemental(id, tile, this, multiplier);
			else if (id >= 10212 && id <= 10218)
				n = new Icefiend(id, tile, this, multiplier);
			else if (id >= 10610 && id <= 10618)
				n = new Hydra(id, tile, this, multiplier);
			/*
			 * else if (id >= 10770 && id <= 10775) //TODO rework this later. n = new
			 * FrostDragon(id, tile, this, multiplier);
			 */
			else if (id >= 10178 && id <= 10187)
				n = new EarthWarrior(id, tile, this, multiplier);
			else if (id >= 10496 && id <= 10505)
				n = new DungeonSpider(id, tile, this, multiplier);
			else if (id >= 10629 && id <= 10669)
				n = new SkeletonMelee(id, tile, this, multiplier);
			else if (id >= 10670 && id <= 10681)
				n = new SkeletonRange(id, tile, this, multiplier);
			else if (id >= 10682 && id <= 10693)
				n = new SkeletonMage(id, tile, this, multiplier);
			else if (id >= 10364 && id <= 10377)
				n = new ZombieMelee(id, tile, this, multiplier);
			else if (id >= 10364 && id <= 10377)
				n = new ZombieRange(id, tile, this, multiplier);
			else if ((id >= 10604 && id <= 10609) || (id >= 10815 && id <= 10820) || (id >= 10219 && id <= 10224))
				n = new LeatherDragon(id, tile, this, multiplier);
			else if (id >= 10906 && id <= 10909)
				n = new Bat(id, tile, this, multiplier);
			else if (id >= 10910 && id <= 10914)
				n = new GiantBat(id, tile, this, multiplier);
			else if (id >= 10706 && id <= 10717)
				n = new HillGiant(id, tile, this, multiplier);
			else if (id >= 10469 && id <= 10479)
				n = new GiantSkeleton(id, tile, this, multiplier);
			else if (id >= 10168 && id <= 10177)
				n = new AnimatedPickaxe(id, tile, this, multiplier);
			else if (id >= 10726 && id <= 10735)
				n = new GuardDog(id, tile, this, multiplier);
			else if (id >= 10797 && id <= 10814)
				n = new Brute(id, tile, this, multiplier);
			else if (id >= 10776 && id <= 10781)
				n = new IronDragon(id, tile, this, multiplier);
			else if (id >= 10754 && id <= 10761)
				n = new FireGiant(id, tile, this, multiplier);
			else if (id >= 10744 && id <= 10753)
				n = new AnimatedBook(id, tile, this, multiplier);
			else if (id >= 10188 && id <= 10195)
				n = new Hellhound(id, tile, this, multiplier);
			else if ((id >= 10492 && id <= 10495) || (id >= 10718 && id <= 10725))
				n = new Demon(id, tile, this, multiplier);
			else if (id >= 10196 && id <= 10206)
				n = new Necromancer(id, tile, this, multiplier);
			else if (id >= 10821 && id <= 10830)
				n = new Ghost(id, tile, this, multiplier);
			else if (id >= 10831 && id <= 10842)
				n = new MysteriousShade(id, tile, this, multiplier);
			else if (id >= 10480 && id <= 10480)
				n = new GiantRat(id, tile, this, multiplier);
			else if (id >= 12903 && id <= 12913)
				n = new RebornMage(id, tile, this, multiplier);
			else if (id >= 12941 && id <= 12951)
				n = new RebornWarrior(id, tile, this, multiplier);
			else if (id >= 10736 && id <= 10743)
				n = new Ankou(id, tile, this, multiplier);
			else
				n = new DungeonNPC(id, tile, this, multiplier);
		}
		n.setDirection(Utils.getAngle(Utils.DOOR_ROTATION_DIR_X[(rotation + 3) & 0x3],
				Utils.DOOR_ROTATION_DIR_Y[(rotation + 3) & 0x3]));
		return n;
	}

	public int getTargetLevel(int id, boolean boss, double multiplier) {
		double lvl = boss ? party.getCombatLevel() : party.getAverageCombatLevel();
		int randomize = party.getComplexity() * 2 * party.getTeam().size();
		lvl -= randomize;
		lvl += Utils.random(randomize * 2);
		lvl *= party.getDificultyRatio();
		lvl *= multiplier;
		lvl *= 1D - ((double) (6D - party.getComplexity()) * 0.07D);
		if (party.getTeam().size() > 2 && id != 12752 && id != 11872 && id != 11708 && id != 12865) // blink
			lvl *= 0.7;
		return (int) (lvl < 1 ? 1 : lvl);
	}

	public int[] getBonuses(boolean boss, int level) {
		int[] bonuses = new int[17];
		for (int i = 0; i < bonuses.length; i++) {
			if (i >= 5 && i <= 9 || i >= 15)
				bonuses[i] = 0;
			else if (i >= 10 && i <= 14)
				bonuses[i] = level / 3;
			else
				bonuses[i] = level - (level / 4);
		}
		bonuses[CombatDefinitions.NPC_RANGE_LEVEL] = (int) ((party.getRangeLevel() + (level / 3)) * (boss ? 1 : 0.5));
		bonuses[CombatDefinitions.NPC_ATTACK_LEVEL] = (int) ((party.getAttackLevel() + (level / 3) / 1.2)
				* (boss ? 1 : 0.5));
		bonuses[CombatDefinitions.NPC_MAGIC_LEVEL] = (int) ((party.getAttackLevel() + (level / 3) / 1.5)
				* (boss ? 1 : 0.5));
		int npcDefenceLevel = party.getAverageCombatLevel() / (boss ? 1 : 2);
		bonuses[CombatDefinitions.NPC_DEFENCE_LEVEL] = npcDefenceLevel + (level / (boss ? 2 : 3));
		bonuses[CombatDefinitions.NPC_STAB_DEFENCE] = bonuses[CombatDefinitions.NPC_CRUSH_DEFENCE] = bonuses[CombatDefinitions.NPC_SLASH_DEFENCE] = (int) ((npcDefenceLevel
				+ (level / (boss ? 2 : 3))));
		bonuses[CombatDefinitions.NPC_RANGE_DEFENCE] = (int) ((npcDefenceLevel + (level / (boss ? 2 : 3))) * 0.5);
		bonuses[CombatDefinitions.NPC_MAGIC_DEFENCE] = (int) ((npcDefenceLevel + (level / (boss ? 2 : 3))) * 0.5);
		if (Settings.DEBUG) {
			System.out.println("Dungeoneering Monster Bonuses");
			System.out.println("Monster Bonuses: " + Arrays.toString(bonuses));
		}
		return bonuses;
	}

	public void updateGuardian(RoomReference reference) {
		if (visibleMap[reference.getX()][reference.getY()].removeGuardians()) {
			getRoom(reference).removeGuardianDoors();
			for (Player player : party.getTeam()) {
				RoomReference playerReference = getCurrentRoomReference(player);
				if (playerReference.getX() == reference.getX() && playerReference.getY() == reference.getY())
					playMusic(player, reference);
			}
		}
	}

	public void exitDungeon(final Player player, final boolean logout) {
		if (player.getTemporaryAttributes().get("DUNGEXP") != null) {
			Double exp = (Double) player.getTemporaryAttributes().remove("DUNGEXP");
			player.getSkills().addXpNoBonus(Skills.DUNGEONEERING, exp);
		}
		party.remove(player, logout);
		player.stopAll();
		player.reset();
		player.getControlerManager().removeControlerWithoutCheck();
		player.getControlerManager().startControler("Kalaboss");
		resetItems(player, true, logout);
		resetTraps(player);
		sendRing(player);
		if (player.getFamiliar() != null)
			player.getFamiliar().sendDeath(player);
		if (logout)
			player.setLocation(new WorldTile(DungeonConstants.OUTSIDE, 2));
		else {
			// TODO player.getDungManager().setRejoinKey(null);
			player.useStairs(-1, new WorldTile(DungeonConstants.OUTSIDE, 2), 0, 3);
			player.getCombatDefinitions().removeDungeonneringBook();
			// TODO player.getToolbelt().switchDungeonneringToolbelt();
			setWorldMap(player, false);
			removeMark(player);
			player.getInterfaceManager().closeOverlay(true);
			player.setForceMultiArea(false);
			player.setLargeSceneView(false);
			player.getMusicsManager().reset();
			player.getAppearence().setRenderEmote(-1);
		}

	}

	public void setWorldMap(Player player, boolean dungIcon) {
		player.getVarsManager().sendVarBit(6090, dungIcon ? 1 : 0);
	}

	public void endFarming() {
		for (String key : farmKeyList)
			OwnedObjectManager.cancel(key);
		farmKeyList.clear();
	}

	private void resetTraps(Player player) {
		for (MastyxTrap trap : mastyxTraps) {
			if (!trap.getPlayerName().equals(player.getDisplayName()))
				continue;
			trap.finish();
		}
	}

	public void endMastyxTraps() {
		for (MastyxTrap trap : mastyxTraps) {
			trap.finish();
		}
		mastyxTraps.clear();
	}

	public void removeDungeon() {
		cachedDungeons.remove(key);
	}

	public void destroy() {
		synchronized (this) {
			if (isDestroyed()) // to prevent issues when shutting down forcing
				return;
			for (Player player : party.getTeam()) {
				if (player.getTemporaryAttributes().get("DUNGEXP") != null) {
					Double exp = (Double) player.getTemporaryAttributes().remove("DUNGEXP");
					player.getSkills().addXpNoBonus(Skills.DUNGEONEERING, exp);
				}
			}
			endRewardsTimer();
			endDestroyTimer();
			endFarming();
			endMastyxTraps();
			removeDungeon();
			partyDeaths.clear();
			final int[] oldboundChuncks = boundChuncks;
			for (int x = 0; x < visibleMap.length; x++) {
				for (int y = 0; y < visibleMap[x].length; y++) {
					if (visibleMap[x][y] != null) {
						visibleMap[x][y].destroy();
					}
				}
			}
			final Dungeon oldDungeon = dungeon;
			dungeon = null;
			CoresManager.slowExecutor.schedule(new Runnable() {
				@Override
				public void run() {
					try {
						MapBuilder.destroyMap(oldboundChuncks[0], oldboundChuncks[1], oldDungeon.getMapWidth() * 2,
								oldDungeon.getMapHeight() * 2);
					} catch (Throwable e) {
						Logger.handle(e);
					}
				}
			}, 5000, TimeUnit.MILLISECONDS); // just to be safe
		}
	}

	public void nextFloor() {
		// int maxFloor = DungeonUtils.getMaxFloor(party.getDungeoneeringLevel());
		if (party.getMaxFloor() > party.getFloor())
			party.setFloor(party.getFloor() + 1);
		if (tutorialMode) {
			int complexity = party.getComplexity();
			if (party.getMaxComplexity() > complexity)
				party.setComplexity(complexity + 1);
		}
		destroy();
		hsType = 0;
		// load((long) (Math.random() * Long.MAX_VALUE)); // old way of seed
		load((long) Utils.random(1000000));
	}

	public Integer[] getAchievements(Player player) { // TODO
		List<Integer> achievements = new ArrayList<Integer>();

		DungeonControler controller = (DungeonControler) player.getControlerManager().getControler();

		// solo achievements
		if (controller.isKilledBossWithLessThan10HP())
			achievements.add(6);
		if (controller.getDeaths() == 8)
			achievements.add(8);
		else if (controller.getDeaths() == 0)
			achievements.add(14);
		if (controller.getDamage() != 0 && controller.getDamageReceived() == 0)
			achievements.add(25);

		if (party.getTeam().size() > 1) { // party achievements
			boolean mostMeleeDmg = true;
			boolean mostMageDmg = true;
			boolean mostRangeDmg = true;
			boolean leastDamage = true;
			boolean mostDmgReceived = true;
			boolean mostDeaths = true;
			boolean mostHealedDmg = true;
			for (Player teamMate : party.getTeam()) {
				if (teamMate == player)
					continue;
				DungeonControler tmController = (DungeonControler) teamMate.getControlerManager().getControler();
				if (tmController.getMeleeDamage() >= controller.getMeleeDamage())
					mostMeleeDmg = false;
				if (tmController.getMageDamage() >= controller.getMageDamage())
					mostMageDmg = false;
				if (tmController.getRangeDamage() >= controller.getRangeDamage())
					mostRangeDmg = false;
				if (controller.getDamage() >= tmController.getDamage())
					leastDamage = false;
				if (controller.getDamageReceived() <= tmController.getDamageReceived())
					mostDmgReceived = false;
				if (controller.getDeaths() <= tmController.getDeaths())
					mostDeaths = false;
				if (controller.getHealedDamage() <= tmController.getHealedDamage())
					mostHealedDmg = false;
			}
			if (mostMeleeDmg && mostMageDmg && mostRangeDmg)
				achievements.add(1);
			if (leastDamage && mostDeaths) // leecher
				achievements.add(2);
			if (mostMeleeDmg)
				achievements.add(3);
			if (mostRangeDmg)
				achievements.add(4);
			if (mostMageDmg)
				achievements.add(5);
			if (leastDamage)
				achievements.add(7);
			if (mostDmgReceived)
				achievements.add(13);
			if (mostDeaths)
				achievements.add(15);
			if (mostHealedDmg)
				achievements.add(38);
		}
		if (achievements.size() == 0)
			achievements.add(33);
		return achievements.toArray(new Integer[achievements.size()]);

	}

	public void loadRewards() {
		stage = 2;
		for (Player player : party.getTeam()) {
			if (party.getComplexity() == 5 && player.getDungManager().getMaxFloor() < 60)
				player.getDungManager().setMaxFloor(60);
			player.stopAll();
			double multiplier = 1;
			player.getInterfaceManager().sendOverlay(933, false);
			player.getPackets().sendRunScript(2275); // clears interface data from last run
			for (int i = 0; i < 5; i++) {
				Player partyPlayer = i >= party.getTeam().size() ? null : party.getTeam().get(i);
				player.getPackets().sendCSVarInteger(1198 + i, partyPlayer != null ? 1 : 0);
				if (partyPlayer == null)
					continue;
				player.getPackets().sendGlobalString(310 + i, partyPlayer.getDisplayName());
				Integer[] achievements = getAchievements(partyPlayer);
				for (int i2 = 0; i2 < (achievements.length > 6 ? 6 : achievements.length); i2++)
					player.getPackets().sendCSVarInteger(1203 + (i * 6) + i2, achievements[i2]);
			}
			player.getPackets().sendIComponentText(933, 331, Utils.formatTime((Utils.currentTimeMillis() - time)));
			player.getPackets().sendCSVarInteger(1187, party.getFloor());
			player.getPackets().sendCSVarInteger(1188, party.getSize() + 1); // dungeon size, sets bonus aswell
			multiplier += DungeonConstants.DUNGEON_SIZE_BONUS[party.getSize()];
			player.getPackets().sendCSVarInteger(1191, party.getTeam().size() * 10 + party.getDificulty()); // teamsize:dificulty
			multiplier += DungeonConstants.DUNGEON_DIFFICULTY_RATIO_BONUS[party.getTeam().size() - 1][party
					.getDificulty() - 1];
			int v = 0;
			if (getVisibleBonusRoomsCount() != 0) { // no bonus rooms in c1, would be divide by 0
				v = getVisibleBonusRoomsCount() * 10000 / (dungeon.getRoomsCount() - dungeon.getCritCount());
			}
			player.getPackets().sendCSVarInteger(1195, v); // dungeons rooms opened, sets bonus aswell
			multiplier += DungeonConstants.MAX_BONUS_ROOM * v / 10000;
			v = (getLevelModPerc() * 20) - 1000;
			player.getPackets().sendCSVarInteger(1236, v); // sets level mod
			multiplier += ((double) v) / 10000;
			player.getPackets().sendCSVarInteger(1196, party.isGuideMode() ? 1 : 0); // sets guidemode
			if (party.isGuideMode())
				multiplier -= 0.05;
			player.getPackets().sendCSVarInteger(1319,
					DungeonUtils.getMaxFloor(player.getSkills().getLevelForXp(Skills.DUNGEONEERING)));
			player.getPackets().sendCSVarInteger(1320, party.getComplexity());
			if (party.getComplexity() != 6)
				multiplier -= (DungeonConstants.COMPLEXIYY_PENALTY_BASE[party.getSize()]
						+ (new Double(5 - party.getComplexity())) * 0.06);
			double levelDiffPenalty = party.getLevelDiferencePenalty(player);
			player.getPackets().sendCSVarInteger(1321, (int) (levelDiffPenalty * 10000));
			multiplier -= levelDiffPenalty;
			double countedDeaths = Math.min(player.getVarsManager().getBitValue(7554), 6);
			multiplier *= (1.0 - (countedDeaths * 0.1));
			double floorXP = getXPForFloor(party.getFloor(), party.getSize()) * getVisibleRoomsCount()
					/ dungeon.getRoomsCount();
			boolean tickedOff = player.getDungManager().isTickedOff(party.getFloor());
			if (!tickedOff)
				player.getDungManager().tickOff(party.getFloor());
			else {
				int[] range = DungeonUtils.getFloorThemeRange(party.getFloor());
				for (int floor = range[0]; floor <= range[1]; floor++) {
					if (player.getDungManager().getMaxFloor() < floor)
						break;
					if (!player.getDungManager().isTickedOff(floor)) {
						player.getPackets().sendGameMessage("Since you have previously completed this floor, floor "
								+ floor + " was instead ticked-off.");
						player.getDungManager().tickOff(floor);
						floorXP = getXPForFloor(floor, party.getSize()) * getVisibleRoomsCount()
								/ dungeon.getRoomsCount();
						tickedOff = false;
						break;
					}
				}
			}
			double prestigeXP = tickedOff ? 0
					: getXPForFloor(player.getDungManager().getPrestige(), party.getSize()) * getVisibleRoomsCount()
							/ dungeon.getRoomsCount();
			player.getVarsManager().sendVarBit(7550, player.getDungManager().getCurrentProgres());
			player.getVarsManager().sendVarBit(7551, player.getDungManager().getPreviousProgress());
			double averageXP = (floorXP + prestigeXP) / 2;
			if (party.getTeam().size() == 1 && party.getSize() != DungeonConstants.LARGE_DUNGEON)
				averageXP *= 1.3;
			multiplier = Math.max(0.1, multiplier);
			double totalXp = averageXP * multiplier;
			double xpBoost = Settings.DUNGEONEERING_EXP_RATE;// <-- 25
			totalXp *= xpBoost;// x25 the total exp
			int tokens = (int) ((totalXp * xpBoost) / 10);// give (xp / 10) tokens
			player.getPackets().sendCSVarInteger(1237, (int) floorXP * 6250);// * 6250
			player.getPackets().sendCSVarInteger(1238, (int) prestigeXP * 6250);// * 6250
			player.getPackets().sendCSVarInteger(1239, (int) averageXP * 6250);// * 6250
			if (Settings.DEBUG)
				System.out.println("floorXp: " + floorXP + ", prestigeXp: " + prestigeXP + ", averageXp: " + averageXP
						+ ", multiplier: " + multiplier + ", TotalXp: " + totalXp * xpBoost + ", Total tokens: "
						+ (totalXp * xpBoost) / 10);
			if (hsType != 2) {
				player.getTemporaryAttributes().put("DUNGEXP", totalXp * xpBoost);
				// player.getSkills().addXpNoBonus(Skills.DUNGEONEERING, totalXp * xpBoost);//
				// add (xp * xpRate)
				player.getDungManager().addTokens(tokens);// add tokens
			}
			player.getMusicsManager().forcePlayMusic(767); // fixed
			if (!tickedOff) {
				if (DungeonUtils.getMaxFloor(player.getSkills().getLevelForXp(Skills.DUNGEONEERING)) < party.getFloor()
						+ 1)
					player.getPackets().sendGameMessage(
							"The next floor is not available at your Dungeonnering level. Consider resetting your progress to gain best ongoing rate of xp.");
			} else {
				player.getPackets().sendGameMessage("<col=D80000>Warning");
				player.getPackets().sendGameMessage(":");
				player.getPackets().sendGameMessage(
						"You have already completed all the available floors of this theme and thus cannot be awarded prestige xp until you reset your progress or switch theme.");
			}
			player.getPackets().sendGameMessage("Pre-Share: <col=641d9e>" + (isKeyShare() ? "OFF" : "ON"));
			if (party.getFloor() == player.getDungManager().getMaxFloor() && party.getFloor() < DungeonUtils
					.getMaxFloor(player.getSkills().getLevelForXp(Skills.DUNGEONEERING)))
				player.getDungManager().increaseMaxFloor();
			if (player.getDungManager().getMaxComplexity() < 6)
				player.getDungManager().increaseMaxComplexity();
			if (player.getFamiliar() != null)
				player.getFamiliar().sendDeath(player);
		}
		clearGuardians();
	}

	public static int getXPForFloor(int floor, int type) {
		int points = 0;
		for (int i = 1; i <= floor; i++)
			points += Math.floor(i + 100.0 * Math.pow(1.3, i / 10));
		if (type == DungeonConstants.MEDIUM_DUNGEON)
			points *= 4;
		else if (type == DungeonConstants.LARGE_DUNGEON)
			points *= 8;
		return points;
	}

	public void voteToMoveOn(Player player) {
		// loadRewards();
		if (rewardsTimer == null)
			setRewardsTimer();
		rewardsTimer.increaseReadyCount();
	}

	public void ready(Player player) {
		int index = party.getIndex(player);
		rewardsTimer.increaseReadyCount();
		for (Player p2 : party.getTeam())
			p2.getPackets().sendCSVarInteger(1397 + index, 1);
	}

	public void leave(Player player) {
		player.getDungManager().checkLeaveParty();
	}

	public DungeonPartyManager getParty() {
		return party;
	}

	public void setRewardsTimer() {
		CoresManager.fastExecutor.schedule(rewardsTimer = new RewardsTimer(), 1000, 5000);
	}

	public void setDestroyTimer() {
		// cant be already instanced before anyway, afterall only isntances hwen party
		// is 0 and remvoes if party not 0
		CoresManager.fastExecutor.schedule(destroyTimer = new DestroyTimer(), 1000, 5000);
	}

	public void setMark(Entity target, boolean mark) {
		if (mark) {
			for (Player player : party.getTeam())
				player.getHintIconsManager().addHintIcon(target, 0, -1, true); // 6th slot
		} else
			removeMark();
		if (target instanceof DungeonNPC)
			((DungeonNPC) target).setMarked(mark);
	}

	public void setGroupGatestone(WorldTile groupGatestone) {
		this.groupGatestone = groupGatestone;
	}

	public WorldTile getGroupGatestone() {
		if (groupGatestone == null) {
			Player player = party.getGateStonePlayer();
			if (player != null)
				return player;
		}
		return groupGatestone;
	}

	public void resetGatestone() {
		groupGatestone = null;
	}

	public void removeMark() {
		for (Player player : party.getTeam())
			removeMark(player);
	}

	public void removeMark(Player player) {
		player.getHintIconsManager().removeHintIcon(6);
	}

	public void endDestroyTimer() {
		if (destroyTimer != null) {
			destroyTimer.cancel();
			destroyTimer = null;
		}
	}

	public void endRewardsTimer() {
		if (rewardsTimer != null) {
			rewardsTimer.cancel();
			rewardsTimer = null;
		}
	}

	private class DestroyTimer extends TimerTask {
		private long timeLeft;

		public DestroyTimer() {
			timeLeft = 600; // 10min
		}

		@Override
		public void run() {
			try {
				if (timeLeft > 0) {
					timeLeft -= 5;
					return;
				}
				destroy();
			} catch (Throwable e) {
				Logger.handle(e);
			}
		}

	}

	public class RewardsTimer extends TimerTask {

		private long timeLeft;
		private boolean gaveRewards;

		public RewardsTimer() {
			timeLeft = party.getTeam().size() * 60;
		}

		public void increaseReadyCount() {
			int reduce = (int) (gaveRewards ? ((double) 45 / (double) party.getTeam().size()) : 60);
			timeLeft = timeLeft > reduce ? timeLeft - reduce : 0;
		}

		@Override
		public void run() {
			try {
				if (timeLeft > 0) {
					for (Player player : party.getTeam())
						player.getPackets().sendGameMessage(gaveRewards ? ("Time until next dungeon: " + timeLeft)
								: (timeLeft + " seconds until dungeon ends."));
					timeLeft -= 5;
				} else {
					if (!gaveRewards) {
						gaveRewards = true;
						timeLeft = 45;
						loadRewards();
					} else {
						nextFloor();
					}
				}
			} catch (Throwable e) {
				Logger.handle(e);
			}
		}

	}

	public void setDungeon() {
		key = party.getLeader() + "_" + keyMaker.getAndIncrement();
		cachedDungeons.put(key, this);
		for (Player player : party.getTeam())
			player.getDungManager().setRejoinKey(key);
	}

	public static void checkRejoin(Player player) {
		Object key = player.getDungManager().getRejoinKey();
		if (key == null)
			return;
		DungeonManager dungeon = cachedDungeons.get(key);
		// either doesnt exit / ur m8s moving next floor(reward screen)
		if (dungeon == null || !dungeon.hasLoadedNoRewardScreen()) {
			player.getDungManager().setRejoinKey(null);
			return;
		}
		dungeon.rejoinParty(player);
	}

	public void load(long seed_) {
		party.lockParty();
		Utils.currentTimeMillis();
		party.getTeam().size();
		visibleMap = new VisibleRoom[DungeonConstants.DUNGEON_RATIO[party
				.getSize()][0]][DungeonConstants.DUNGEON_RATIO[party.getSize()][1]];
		// slow executor loads dungeon as it may take up to few secs
		CoresManager.slowExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					clearKeyList();
					// generates dungeon structure
					dungeon = new Dungeon(DungeonManager.this, party.getFloor(), party.getComplexity(),
							party.getSize());
					time = Utils.currentTimeMillis();
					// finds an empty map area bounds
					boundChuncks = MapBuilder.findEmptyChunkBound(dungeon.getMapWidth() * 2,
							(dungeon.getMapHeight() * 2));
					// reserves all map area
					MapBuilder.cutMap(boundChuncks[0], boundChuncks[1], dungeon.getMapWidth() * 2,
							(dungeon.getMapHeight() * 2), 0);
					// set dungeon
					setDungeon();
					// loads start room
					loadRoom(dungeon.getStartRoomReference());
					stage = 1;
				} catch (Throwable e) {

					e.printStackTrace();
					Logger.handle(e);
				}
			}
		});
	}

	public boolean hasStarted() {
		return stage != 0;
	}

	public boolean isAtRewardsScreen() {
		return stage == 2;
	}

	public boolean hasLoadedNoRewardScreen() {
		return stage == 1;
	}

	public void openMap(Player player) {
		if (party.getSize() == DungeonConstants.TEST_DUNGEON) {
			player.getPackets().sendGameMessage("Dungeon is too big to be displayed.");
			return;
		}
		player.getInterfaceManager().sendInterface(942);
		player.getPackets().sendRunScript(3277); // clear the map if theres any setted
		int protocol = party.getSize() == DungeonConstants.LARGE_DUNGEON ? 0
				: party.getSize() == DungeonConstants.MEDIUM_DUNGEON ? 2 : 1;
		for (int x = 0; x < visibleMap.length; x++) {
			for (int y = 0; y < visibleMap[x].length; y++) {
				if (visibleMap[x][y] != null) { // means exists
					Room room = getRoom(new RoomReference(x, y));
					boolean highLight = party.isGuideMode() && room.isCritPath();
					player.getPackets().sendRunScript(3278, protocol, getMapIconSprite(room, highLight), y, x);
					if (room.getRoom() instanceof StartRoom)
						player.getPackets().sendRunScript(3280, protocol, y, x);
					else if (room.getRoom() instanceof BossRoom)
						player.getPackets().sendRunScript(3281, protocol, y, x);
					if (room.hasNorthDoor() && visibleMap[x][y + 1] == null) {
						Room unknownR = getRoom(new RoomReference(x, y + 1));
						highLight = party.isGuideMode() && unknownR.isCritPath();
						player.getPackets().sendRunScript(3278, protocol,
								getMapIconSprite(DungeonConstants.SOUTH_DOOR, highLight), y + 1, x);
					}
					if (room.hasSouthDoor() && visibleMap[x][y - 1] == null) {
						Room unknownR = getRoom(new RoomReference(x, y - 1));
						highLight = party.isGuideMode() && unknownR.isCritPath();
						player.getPackets().sendRunScript(3278, protocol,
								getMapIconSprite(DungeonConstants.NORTH_DOOR, highLight), y - 1, x);
					}
					if (room.hasEastDoor() && visibleMap[x + 1][y] == null) {
						Room unknownR = getRoom(new RoomReference(x + 1, y));
						highLight = party.isGuideMode() && unknownR.isCritPath();
						player.getPackets().sendRunScript(3278, protocol,
								getMapIconSprite(DungeonConstants.WEST_DOOR, highLight), y, x + 1);
					}
					if (room.hasWestDoor() && visibleMap[x - 1][y] == null) {
						Room unknownR = getRoom(new RoomReference(x - 1, y));
						highLight = party.isGuideMode() && unknownR.isCritPath();
						player.getPackets().sendRunScript(3278, protocol,
								getMapIconSprite(DungeonConstants.EAST_DOOR, highLight), y, x - 1);
					}
				}
			}
		}
		int index = 1;
		for (Player p2 : party.getTeam()) {
			RoomReference reference = getCurrentRoomReference(p2);
			player.getPackets().sendRunScript(3279, p2.getDisplayName(), protocol, index++, reference.getY(),
					reference.getX());
		}
	}

	public int getMapIconSprite(int direction, boolean highLight) {
		for (MapRoomIcon icon : MapRoomIcon.values()) {
			if (icon.isOpen())
				continue;
			if (icon.hasDoor(direction))
				return icon.getSpriteId() + (highLight ? MapRoomIcon.values().length : 0);
		}
		return 2879;
	}

	public int getMapIconSprite(Room room, boolean highLight) {
		for (MapRoomIcon icon : MapRoomIcon.values()) {
			if (!icon.isOpen())
				continue;
			if (icon.hasNorthDoor() == room.hasNorthDoor() && icon.hasSouthDoor() == room.hasSouthDoor()
					&& icon.hasWestDoor() == room.hasWestDoor() && icon.hasEastDoor() == room.hasEastDoor())
				return icon.getSpriteId() + (highLight ? MapRoomIcon.values().length : 0);
		}
		return 2878;
	}

	public void openStairs(RoomReference reference) {
		Room room = getRoom(reference);
		int type = 0;

		if (room.getRoom().getChunkX() == 26 && room.getRoom().getChunkY() == 640) // unholy cursed
			type = 1;
		else if (room.getRoom().getChunkX() == 30 && room.getRoom().getChunkY() == 656) // stomp
			type = 2;
		else if ((room.getRoom().getChunkX() == 30 && room.getRoom().getChunkY() == 672)
				|| (room.getRoom().getChunkX() == 24 && room.getRoom().getChunkY() == 690)) // necromancer)
			type = 3;
		else if (room.getRoom().getChunkX() == 26 && room.getRoom().getChunkY() == 690) // world-gorger
			type = 4;
		else if (room.getRoom().getChunkX() == 24 && room.getRoom().getChunkY() == 688) // blink
			type = 5;
		spawnObject(reference, DungeonConstants.LADDERS[party.getFloorType()], 10, (type == 2 || type == 3) ? 0 : 3,
				type == 4 ? 11 : type == 3 ? 15 : type == 2 ? 14 : 7,
				type == 5 ? 14 : (type == 3 || type == 2) ? 3 : type == 1 ? 11 : 15);
		getVisibleRoom(reference).setNoMusic();
		for (Player player : party.getTeam()) {
			if (!isAtBossRoom(player))
				continue;
			player.getPackets().sendMusicEffect(415);
			playMusic(player, reference);
		}
	}

	public List<String> getFarmKeyList() {
		return farmKeyList;
	}

	public void addMastyxTrap(MastyxTrap mastyxTrap) {
		mastyxTraps.add(mastyxTrap);
	}

	public List<MastyxTrap> getMastyxTraps() {
		return mastyxTraps;
	}

	public void removeMastyxTrap(MastyxTrap mastyxTrap) {
		mastyxTraps.remove(mastyxTrap);
		mastyxTrap.finish();
	}

	public void message(RoomReference reference, String message) {
		for (Player player : party.getTeam()) {
			if (reference.equals(getCurrentRoomReference(player))) {
				player.getPackets().sendGameMessage(message);
			}
		}
	}

	public void showBar(RoomReference reference, String name, int percentage) {
		for (Player player : party.getTeam()) {
			RoomReference current = getCurrentRoomReference(player);
			if (reference.getX() == current.getX() && reference.getY() == current.getY()
					&& player.getControlerManager().getControler() instanceof DungeonControler) {
				DungeonControler c = (DungeonControler) player.getControlerManager().getControler();
				c.showBar(true, name);
				c.sendBarPercentage(percentage);
			}
		}
	}

	public void hideBar(RoomReference reference) {
		for (Player player : party.getTeam()) {
			RoomReference current = getCurrentRoomReference(player);
			if (reference.getX() == current.getX() && reference.getY() == current.getY()
					&& player.getControlerManager().getControler() instanceof DungeonControler) {
				DungeonControler c = (DungeonControler) player.getControlerManager().getControler();
				c.showBar(false, null);
			}
		}
	}

	public Map<String, Integer> getPartyDeaths() {
		return partyDeaths;
	}

	/*
	 * Use get npc instead this being used because gravecreeper gets removed when
	 * using special :/
	 */
	@Deprecated
	public DungeonBoss getTemporaryBoss() {
		return temporaryBoss;
	}

	public void setTemporaryBoss(DungeonBoss temporaryBoss) {
		this.temporaryBoss = temporaryBoss;
	}

	public void setHsType(int type) {
		this.hsType = type;
	}
}
