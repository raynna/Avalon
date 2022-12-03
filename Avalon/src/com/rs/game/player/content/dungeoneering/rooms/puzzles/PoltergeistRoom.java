package com.rs.game.player.content.dungeoneering.rooms.puzzles;

import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.dungeonnering.DungeonNPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.dungeoneering.DungeonConstants;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.game.player.content.dungeoneering.rooms.PuzzleRoom;
import com.rs.utils.Utils;

public class PoltergeistRoom extends PuzzleRoom {

	public static final int POLTERGEIST_ID = 11245;

	public static final int CONSECRATED_HERB = 19659;
	public static final int[] HERBS =
	{ 19653, 19654, 19655, 19656, 19657, 19658 };

	private static final int[][] CENSERS =
	{
	{ -1, -1, -1 },
	{ 54095, 54099, 54103 },
	{ 54096, 54100, 54104 },
	{ 54097, 54101, 54105 },
	{ 39847, 39850, 39851 }, };

	private static final int[][] SARCOPHAGUS =
	{
	{ -1, -1, -1 },
	{ 54079, 54083 },
	{ 54080, 54084 },
	{ 54081, 54085 },
	{ 39526, 39840 }, };

	private NPC poltergeist;
	private Item requiredHerb;
	private int censersLit;
	private int herbsAvailable = 4;

	@Override
	public void openRoom() {
		manager.spawnRandomNPCS(reference);
		poltergeist = manager.spawnNPC(reference, POLTERGEIST_ID, 5, 5, false, DungeonConstants.PUZZLE_NPC);
		requiredHerb = new Item(HERBS[Utils.random(HERBS.length)]);
	}

	public boolean canTakeHerb() {
		return herbsAvailable > 0;
	}

	public void takeHerb(Player player, WorldObject object, int index) {
		if (requiredHerb.getId() == HERBS[index]) {
			if (player.getInventory().addItem(HERBS[index], 1)) {
				player.lock(1);
				player.getPackets().sendGameMessage("With great care, you pick a clump of the herb.");
				giveXP(player, Skills.HERBLORE);
				herbsAvailable--;
				if (herbsAvailable == 0) {
					WorldObject o = new WorldObject(object);
					o.setId(DungeonConstants.EMPTY_FARMING_PATCH);
					World.spawnObject(o);
				}
			}
		} else {
			player.applyHit(new Hit(player, (int) (player.getMaxHitpoints() * .3), HitLook.REGULAR_DAMAGE));
		}
	}

	@Override
	public boolean processObjectClick1(Player player, WorldObject object) {
		String name = object.getDefinitions().name;
		if (name.equals("Sarcophagus")) {
			player.getDialogueManager().startDialogue("SimpleMessage", "The inscription reads: 'Here lies Leif, posthumously honoured with the discovery of " + requiredHerb.getName() + ".");
			return false;
		} else if (name.equals("Censer") && object.getDefinitions().containsOption("Light")) {
			int requiredFiremaking = getRequirement(Skills.FIREMAKING);
			if (!player.getInventory().containsItemToolBelt(DungeonConstants.TINDERBOX)) {
				player.getPackets().sendGameMessage("You need a tinderbox in order to light a censer.");
				return false;
			} else if (requiredFiremaking > player.getSkills().getLevel(Skills.FIREMAKING)) {
				player.getPackets().sendGameMessage("You need a firemaking level of " + requiredFiremaking + " to light this.");
				return false;
			}
			giveXP(player, Skills.FIREMAKING);
			player.lock(1);
			censersLit++;
			if (censersLit == 4) {
				poltergeist.finish();
			}
			World.spawnObject(new WorldObject(CENSERS[manager.getParty().getFloorType()][2], object.getType(), object.getRotation(), object));
			return false;
		} else if (name.equals("Censer") && object.getDefinitions().containsOption("Inspect")) {
			player.getPackets().sendGameMessage("This censer would be ideal for burning something in.");
			return false;
		} else if (name.equals("Herb patch") && object.getDefinitions().containsOption("Harvest")) {
			int requiredHerblore = getRequirement(Skills.HERBLORE);
			if (requiredHerblore > player.getSkills().getLevel(Skills.HERBLORE)) {
				player.getPackets().sendGameMessage("You need a herblore level of " + requiredHerblore + " to harvest these herbs.");
				return false;
			}
			player.getDialogueManager().startDialogue("PoltergeistFarmD", this, object);
			return false;
		}
		return true;
	}

	@Override
	public boolean handleItemOnObject(Player player, WorldObject object, Item item) {
		if (object.getDefinitions().name.equals("Censer") && object.getDefinitions().containsOption("Inspect")) {
			if (item.getId() == CONSECRATED_HERB) {
				player.lock(1);
				player.getPackets().sendGameMessage("You pile the herbs into the censer.");
				player.getInventory().deleteItem(item);
				World.spawnObject(new WorldObject(CENSERS[manager.getParty().getFloorType()][1], object.getType(), object.getRotation(), object));
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean processObjectClick2(Player player, WorldObject object) {
		if (object.getDefinitions().name.equals("Sarcophagus")) {
			if (censersLit == 4) {
				int requiredThieving = getRequirement(Skills.THIEVING);
				if (requiredThieving > player.getSkills().getLevel(Skills.THIEVING)) {
					player.getPackets().sendGameMessage("You need a thieving level of " + requiredThieving + " to open the sarcophagus.");
					return false;
				}
				giveXP(player, Skills.THIEVING);
				player.getPackets().sendGameMessage("You successfully open the sarcophagus.");
				World.spawnObject(new WorldObject(SARCOPHAGUS[manager.getParty().getFloorType()][1], object.getType(), object.getRotation(), object));
				setComplete();
				return false;
			} else {
				player.getPackets().sendGameMessage("lit: " + censersLit + "/4");
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("serial")
	public static class Poltergeist extends DungeonNPC {

		private WorldTile[] corners;
		private int ptr;

		public Poltergeist(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
			super(id, tile, manager, 1);
			corners = new WorldTile[4];
			corners[0] = manager.getTile(reference, 5, 5);
			corners[1] = manager.getTile(reference, 5, 10);
			corners[2] = manager.getTile(reference, 10, 10);
			corners[3] = manager.getTile(reference, 10, 5);
		}

		@Override
		public void processNPC() {
			if (getWalkSteps().isEmpty()) {
				addWalkSteps(corners[ptr].getX(), corners[ptr].getY());
				if (++ptr == corners.length) {
					ptr = 0;
				}
			}
			super.processNPC();
		}

	}

	public void consecrateHerbs(Player player, int id) {
		int requiredPrayer = getRequirement(Skills.PRAYER);
		if (requiredPrayer > player.getSkills().getLevel(Skills.PRAYER)) {
			player.getPackets().sendGameMessage("You need a prayer level of " + requiredPrayer + " to consecrate the herbs.");
			return;
		}
		giveXP(player, Skills.PRAYER);
		player.lock(2);
		player.getInventory().deleteItem(id, 1);
		player.getInventory().addItem(CONSECRATED_HERB, 1);
		player.getPackets().sendGameMessage("You consecrate the herbs.");
	}

	@Override
	public String getCompleteMessage() {
		return "You hear a clunk as the doors unlock.";
	}

	@Override
	public String getLockMessage() {
		return "The door is locked. You can't see any obvious keyhole or mechanism.";
	}

}
