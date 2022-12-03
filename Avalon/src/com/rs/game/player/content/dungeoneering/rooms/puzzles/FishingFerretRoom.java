package com.rs.game.player.content.dungeoneering.rooms.puzzles;

import java.util.LinkedList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.npc.dungeonnering.DungeonFishSpot;
import com.rs.game.npc.dungeonnering.DungeonNPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.VisibleRoom;
import com.rs.game.player.content.dungeoneering.rooms.PuzzleRoom;
import com.rs.game.player.content.dungeoneering.skills.DungeoneeringFishing;
import com.rs.game.player.content.dungeoneering.skills.DungeoneeringFishing.Fish;
import com.rs.game.player.controlers.DungeonControler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class FishingFerretRoom extends PuzzleRoom {

	private static final int FERRET_ID = 11007, VILE_FISH = 17375;
	private static final int[] PRESSURE_PLATE =
	{ 49555, 49557, 49559, 54296, 54297 };
	private static final int[] EMPTY_PLATE =
	{ 49546, 49547, 49548, 54293, 35293 };

	private WorldTile pressurePlate;
	private List<FloorItem> vileFishes;
	private DungeonFishSpot psuedoFishingSpot;// Cheap hax

	@SuppressWarnings("serial")
	public class Ferret extends DungeonNPC {

		public Ferret(int id, WorldTile tile, DungeonManager manager, double multiplier) {
			super(id, tile, manager, multiplier);
		}

		@Override
		public void processNPC() {
			if (isComplete())//We will keep it spawned but it won't do shit :D
				return;
			if (getWalkSteps().isEmpty()) {
				if (getX() == pressurePlate.getX() && getY() == pressurePlate.getY()) {
					setComplete();
					psuedoFishingSpot.finish();
					psuedoFishingSpot = null;
					removeAllVileFish();
					return;
				} else if (vileFishes.size() > 0) {
					FloorItem item = vileFishes.get(0);//Goes in chronological order
					WorldTile tile = item.getTile();
					if (matches(tile)) {
						removeVileFish();
						return;
					}
					addWalkSteps(tile.getX(), tile.getY(), -1, false);
				}
			} else {// Should be fine won't be checked often anyways
				WorldObject o = World.getObjectWithType(this, 22);
				if (o != null && o.getDefinitions().name.equals("Hole")) {
					animate(new Animation(13797));
					WorldTasksManager.schedule(new WorldTask() {

						@Override
						public void run() {
							resetWalkSteps();
							setNextWorldTile(getRespawnTile());
							animate(new Animation(-1));
							removeAllVileFish();
						}
					});
				}
			}
			super.processNPC();
		}
	}

	private void removeVileFish() {
		World.removeGroundItem(null, vileFishes.remove(0));
	}

	public void removeAllVileFish() {
		for (FloorItem fish : vileFishes)
			World.removeGroundItem(null, fish);
		vileFishes.clear();
	}

	@Override
	public void openRoom() {
		int[][] possibleCorners = null;
		outer: for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				WorldObject object = manager.getObjectWithType(reference, 22, x, y);
				if (object != null && (object.getDefinitions().name.equals("Tile") || object.getDefinitions().name.equals("Hole"))) {
					possibleCorners = new int[][]
					{
					{ x + 6, y, x, y + 6 },
					{ x, y, x + 6, y + 6 } };
					break outer;
				}
			}
		}

		vileFishes = new LinkedList<FloorItem>();
		boolean invertChunks = Utils.random(2) == 0;
		int[] cornerChunks = possibleCorners[Utils.random(possibleCorners.length)];
		pressurePlate = manager.getRotatedTile(reference, cornerChunks[invertChunks ? 2 : 0], cornerChunks[invertChunks ? 3 : 1]);
		Ferret puzzleNPC = new Ferret(FERRET_ID, manager.getRotatedTile(reference, cornerChunks[invertChunks ? 0 : 2], cornerChunks[invertChunks ? 1 : 3]), manager, 0.0);
		psuedoFishingSpot = new DungeonFishSpot(1957, manager.getRotatedTile(reference, 7, 13), manager, Fish.VILE_FISH);
		int floorType = manager.getParty().getFloorType();
		World.spawnObject(new WorldObject(PRESSURE_PLATE[floorType], 22, 0, pressurePlate));
		World.spawnObject(new WorldObject(EMPTY_PLATE[floorType], 22, 0, puzzleNPC));
	}

	@Override
	public boolean processObjectClick1(Player player, WorldObject object) {
		if (object.getDefinitions().name.equals("Fishing spot")) {
			int requiredFishing = getRequirement(Skills.FISHING);
			if (psuedoFishingSpot == null)
				return true;
			else if (requiredFishing > player.getSkills().getLevel(Skills.FISHING)) {
				player.getPackets().sendGameMessage("You need a Fishing level of " + requiredFishing + " to catch a raw vile fish.");
				return false;
			}
			//giveXP(player, Skills.FISHING); //People can abuse this so we shouldn't add it FOR THIS PUZZLE
			player.getActionManager().setAction(new DungeoneeringFishing(psuedoFishingSpot));
			return false;
		} else if (object.getDefinitions().name.equals("Fire")) {
			int requiredCooking = getRequirement(Skills.COOKING);
			if (player.getSkills().getLevel(Skills.COOKING) < requiredCooking) {
				player.getPackets().sendGameMessage("You need a Cooking level of " + requiredCooking + " to cook a raw vile fish.");
				return false;
			}
			//giveXP(player, Skills.COOKING); People can abuse this so we shouldn't add it FOR THIS PUZZLE
			return true;
		}
		return true;
	}
	
	public static boolean handleFerretThrow(final Player player, final WorldObject object, final Item item) {
		if (!object.getDefinitions().name.equals("Tile") && !object.getDefinitions().name.equals("Pressure plate") || item.getId() != VILE_FISH || player.getControlerManager().getControler() == null || !(player.getControlerManager().getControler() instanceof DungeonControler)) {
			return false;
		}
		DungeonManager manager = player.getDungManager().getParty().getDungeon();
		VisibleRoom room = manager.getVisibleRoom(manager.getCurrentRoomReference(player));
		if (room == null) {
			return false;
		}
		if (!(room instanceof FishingFerretRoom)) {
			return false;
		}
		final FishingFerretRoom puzzle = (FishingFerretRoom) room;
		if (puzzle.isComplete()) {
			player.getPackets().sendGameMessage("I know it smells, but littering is wrong!");
			return false;
		}
		int requiredRange = puzzle.getRequirement(Skills.RANGE);
		if (player.getSkills().getLevel(Skills.RANGE) < requiredRange) {
			player.getPackets().sendGameMessage("You need a Range level of " + requiredRange + " to throw a vile fish.");
			return false;
		}
		puzzle.giveXP(player, Skills.RANGE);
		player.lock(2);
		player.animate(new Animation(13325));
		player.gfx(new Graphics(2521));
		player.getInventory().deleteItem(item);
		player.faceObject(object);
		player.getPackets().sendGameMessage("You throw the fish.");
		World.sendProjectile(player, object, 2522, 65, 0, 60, 50, 16, 1);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				World.sendGraphics(player, new Graphics(2523), object);
				World.addGroundItem(item, object, null, false, 0, 2, 40);
				puzzle.getVileFishes().add(World.getRegion(player.getRegionId()).getGroundItem(item.getId(), object, player));
			}
		}, 2);
		return true;
	}

	protected List<FloorItem> getVileFishes() {
		return vileFishes;
	}

	@Override
	public String getCompleteMessage() {
		return "You hear a click as the ferret steps on the pressure plate. All the doors in the room are now unlocked.";
	}
	
}
