package com.rs.game.minigames.lividfarm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.LunarMagicks;
import com.rs.game.player.actions.combat.LunarMagicks.RSLunarSpellStore;
import com.rs.game.player.actions.combat.lunarspells.CurePlant;
import com.rs.game.player.actions.combat.lunarspells.FertileSoil;
import com.rs.game.player.actions.combat.lunarspells.PlankMake;
import com.rs.game.player.actions.combat.lunarspells.StringJewellery;
import com.rs.game.player.controlers.Controler;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Zed & -Andreas
 *
 */
public class LividFarmControler extends Controler {

	/**
	 * NPC id for Pauline Polaris
	 */
	private transient int PAULINE = 13619;

	/*
	 * VarBit stages
	 */
	private int EMPTY_STAGE = 0, FERTILISED_STAGE = 1, GROWING_STAGE = 2, DISEASED_STAGE = 3, GROWN_STAGE = 4;

	/**
	 * Variables
	 */
	private int gathered;

	private transient int ticks;
	private transient int flowerTick;

	/**
	 * Player enters Livid Farm
	 * 
	 * @param player - starts controler
	 */
	public static void enterLividFarm(Player player) {
		player.setNextWorldTile(new WorldTile(2110, 3946, 0));
		player.getControlerManager().startControler("LividFarmHandler");
		player.sm("The minigame will start soon.");
	}

	/**
	 * 
	 * @param object  - flower clicked
	 * @param success - if cure is success or not Cures disease of the flower
	 *                clicked, checks for lunar magic requirements Gives 20 livid
	 *                points Gives 140 farming experience
	 */

	public void cureDisease(WorldObject object, Player player, boolean success) {
		if (player.getCombatDefinitions().getSpellBook() != 430) {
			player.sm("You need to be on Lunar Magicks to do this.");
			return;
		}
		RSLunarSpellStore spell = RSLunarSpellStore.CURE_PLANT;
		if (LunarMagicks.simpleRequirementCheck(player, spell.getSpellId())) {
			if (!success) {
				LunarMagicks.removeRunes(player, spell.getRune());
				player.getDialogueManager().startDialogue("SimpleNPCMessage", PAULINE,
						"Sorry, but that is the wrong flower type.");
				return;
			}
			if (CurePlant.cast(player, spell.getXp(), object)) {
				LunarMagicks.removeRunes(player);
				player.getSkills().addXp(Skills.FARMING, 140.0);
				player.getLivid().addProduce(20);
			}
		}
	}

	/**
	 * 
	 * @param object - patch clicked Fertilises the patch, checks for lunar magic
	 *               requirements. Gives 20 livid points Gives 140 farming
	 *               experience
	 */
	public void fertilisePatch(WorldObject object, Player player) {
		if (player.getCombatDefinitions().getSpellBook() != 430) {
			player.sm("You need to be on Lunar Magicks to do this.");
			return;
		}
		RSLunarSpellStore spell = RSLunarSpellStore.FERTILE_SOUL;
		if (LunarMagicks.simpleRequirementCheck(player, spell.getSpellId())) {
			if (FertileSoil.cast(player, spell.getXp(), object)) {
				LunarMagicks.removeRunes(player);
				flowers.put(object.getTile(), 1);
				player.getSkills().addXp(Skills.FARMING, 140.0);
				player.getLivid().addProduce(20);
			}
		}
	}

	/**
	 * 
	 * @param amount - amount of logs to give @param player
	 */

	public void takeLog(Player player, int amount) {
		if (!player.getInventory().hasFreeSlots()) {
			player.sm("You don't have any inventory space.");
			return;
		}
		player.getInventory().addItem(20702, amount);
	}

	/**
	 * 
	 * @param slotId - inventory slot to replace with crafted log
	 * @param item   - item to replace with crafted log
	 */

	public static void makePlank(int slotId, Item item, Player player) {
		if (player.getCombatDefinitions().getSpellBook() != 430) {
			player.sm("You need to be on Lunar Magicks to do this.");
			return;
		}
		RSLunarSpellStore spell = RSLunarSpellStore.PLANK_MAKE;
		if (LunarMagicks.simpleRequirementCheck(player, spell.getSpellId())) {
			if (PlankMake.cast(player, spell.getXp(), item.getId(), slotId, true)) {
				LunarMagicks.removeRunes(player);
			}
		}
	}

	/**
	 * 
	 * Creates bunchs' with 5 livid plants each Gives 20 points
	 */

	public static void makeBunch(Player player) {
		if (player.getCombatDefinitions().getSpellBook() != 430) {
			player.sm("You need to be on Lunar Magicks to do this.");
			return;
		}
		RSLunarSpellStore spell = RSLunarSpellStore.STRING_JEWELLERY;
		if (LunarMagicks.simpleRequirementCheck(player, spell.getSpellId())) {
			if (StringJewellery.cast(player, spell.getXp())) {
				LunarMagicks.removeRunes(player);
				player.getLivid().addProduce(20);
			}
		}
	}

	/**
	 * Deposits the bunchs' in your inventory Gives 10 points each
	 */

	private void depositWagon(Player player) {
		if (!player.getInventory().containsOneItem(20705)) {
			player.sm("You don't have any plant bunch to trade in.");
			return;
		}
		player.getInventory().deleteItem(20705, 1);
		player.getLivid().addProduce(10);
	}

	/**
	 * @param object - broken fence clicked Checks if you have any planks to repair
	 *               fence with. Repairs the fence if you have planks Gives 137
	 *               magic experience + 83.5 construction experience Gives 20 points
	 */

	public void repairFence(Player player, WorldObject object) {
		if (!player.getInventory().containsOneItem(20703)) {
			player.getPackets().sendGameMessage("You don't have any planks to repair the fence with.");
			return;
		}
		player.sm("You begin to repair the fence..");
		player.lock(2);
		player.animate(new Animation(11191));
		WorldTasksManager.schedule(new WorldTask() {
			int tick;

			@Override
			public void run() {
				if (tick == 1) {
					player.getInventory().deleteItem(new Item(20703));
					player.getVarsManager().sendVarBit(object.getConfigByFile(), 0);
					player.getSkills().addXp(Skills.MAGIC, 137.0);
					player.getSkills().addXp(Skills.CONSTRUCTION, 83.5);
					player.getLivid().addProduce(20);
					player.sm("You've repaired the fence!");
				}
				tick++;
			}
		}, 0, 1);

	}

	public ArrayList<WorldObject> plantList = new ArrayList<WorldObject>();

	public void sendPlantInterface(WorldObject object, Player player) {
		player.getDialogueManager().startDialogue(new Dialogue() {

			@Override
			public void start() {
				player.getInterfaceManager().sendInventoryInterface(1081);
			}

			@Override
			public void run(int interfaceId, int componentId) {
				finish();
				cureDisease(object, player, getCompByType(object) == componentId);
			}

			private int getCompByType(WorldObject object) {
				int id = object.getId();
				switch (id) {
				case 40460:
				case 40461:
				case 40462:
				case 40463:// done
					return 3;
				case 40464:
				case 40486:
				case 40487:
				case 40488:// done
					return 4;
				case 40492:
				case 40499:
				case 40504:// done
					return 5;
				case 40505:
				case 40534:
				case 40532:
				case 40533:// done
					return 6;
				}
				return -1;
			}

			@Override
			public void finish() {
				if (player.getInterfaceManager().containsInventoryInter())
					player.getInterfaceManager().closeInventoryInterface();
			}
		});
	}

	@Override
	public boolean processObjectClick1(final WorldObject object) {
		for (Entry<WorldTile, Integer> flower : flowers.entrySet()) {
			if (object.getTile().matches(flower.getKey())) {
				if (flower.getValue() == 3) {
					sendPlantInterface(object, player);
					return false;
				}
				if (flower.getValue() == 0) {
					fertilisePatch(object, player);
					return false;
				}
			}
		}
		switch (object.getId()) {
		case 40432:
		case 40433:
		case 40434:
		case 40435:
		case 40436:
		case 40437:
			repairFence(player, object);
			break;
		case 40444:
			takeLog(player, 1);
			break;
		case 40441:
			emptyBin(object, player);
			break;
		case 40443:
			depositWagon(player);
			break;
		}
		return false;
	}

	private transient WorldTile binTile = new WorldTile(2114, 3951, 0);

	public void spawnFilledBin(Player player) {
		WorldObject bin = World.getObjectWithType(binTile, 10);
		player.getVarsManager().sendVarBit(bin.getConfigByFile(), 1);
	}

	public void emptyBin(WorldObject object, Player player) {
		if (gathered == 2) {
			player.sm("You can't empty the bin more than 2 times each round.");
			return;
		}
		if (player.getInventory().getFreeSlots() < 10) {
			player.sm("You need at least 10 inventory slots free to empty the bin.");
			return;
		}
		player.getVarsManager().sendVarBit(object.getConfigByFile(), 0);
		gathered++;
		player.getInventory().addItem(20704, 10);
		player.getLivid().addProduce(20);
	}

	private transient WorldTile fenceTile[] = { new WorldTile(2108, 3953, 0), new WorldTile(2116, 3953, 0),
			new WorldTile(2118, 3953, 0), new WorldTile(2120, 3951, 0), new WorldTile(2120, 3946, 0),
			new WorldTile(2120, 3945, 0) };

	public void spawnBrokenFences() {
		for (WorldTile tile : fenceTile) {
			WorldObject object = World.getStandardWallObject(tile);
			player.getVarsManager().sendVarBit(object.getConfigByFile(), 0);
		}
		for (int i = 0; i <= 1; i++) {
			WorldTile randomTile = fenceTile[Utils.getRandom(fenceTile.length - 1)];
			WorldObject fence = World.getStandardWallObject(randomTile);
			player.getVarsManager().sendVarBit(fence.getConfigByFile(), 1);
		}
	}

	/**
	 * All patch tiles in order north-west to south-east
	 */
	private transient WorldTile patchTile[] = {
			// first row
			new WorldTile(2098, 3949, 0), new WorldTile(2100, 3949, 0), new WorldTile(2102, 3949, 0),
			new WorldTile(2104, 3949, 0), new WorldTile(2106, 3949, 0),

			// second row
			new WorldTile(2098, 3949 - 3, 0), new WorldTile(2100, 3949 - 3, 0), new WorldTile(2102, 3949 - 3, 0),
			new WorldTile(2104, 3949 - 3, 0), new WorldTile(2106, 3949 - 3, 0),

			// third row
			new WorldTile(2098, 3949 - 6, 0), new WorldTile(2100, 3949 - 6, 0), new WorldTile(2102, 3949 - 6, 0),
			new WorldTile(2104, 3949 - 6, 0), new WorldTile(2106, 3949 - 6, 0) };

	public void spawnPlants() {
		flowers.clear();
		for (WorldTile tile : patchTile) {
			WorldObject livid = World.getStandardFloorObject(tile);
			int stage = Utils.random(1, 3) == 1 ? DISEASED_STAGE : Utils.random(1, 4) == 1 ? EMPTY_STAGE : GROWN_STAGE;
			flowers.put(tile, stage);
			player.getVarsManager().sendVarBit(livid.getConfigByFile(), stage);
		}
	}

	private transient HashMap<WorldTile, Integer> flowers = new HashMap<>();

	@Override
	public boolean processNPCClick3(NPC npc) {
		if (npc.getId() == PAULINE)
			spawnFilledBin(player);
		return false;

	}

	@Override
	public boolean processNPCClick2(NPC npc) {
		if (npc.getId() == PAULINE)
			LividStore.openInterface(player);
		return false;

	}

	@Override
	public boolean processNPCClick1(NPC npc) {
		if (npc.getId() == PAULINE)
			LividStore.openInterface(player);
		return false;

	}

	@Override
	public boolean processObjectClick2(final WorldObject object) {
		if (object.getId() == 40444)
			takeLog(player, 5);
		return false;
	}

	@Override
	public boolean logout() {
		plantList.clear();
		return false;
	}

	public static void sendPointsInterface(Player player) {
		player.getInterfaceManager().sendTab(getOverlayId(player), 3046);
	}

	public static int getOverlayId(Player player) {
		return player.getInterfaceManager().isResizableScreen() ? 8 : 11;
	}

	@Override
	public void moved() {
		if (!player.getInterfaceManager().containsTab(getOverlayId(player))) {
			sendPointsInterface(player);
			LividFarm.updateProduce(player);
		}
	}

	@Override
	public void start() {
		sendPointsInterface(player);
		LividFarm.updateProduce(player);
		ticks = 48;
		player.sm("Livid farm minigame will start in 30 seconds.");
	}

	@Override
	public boolean login() {
		sendPointsInterface(player);
		LividFarm.updateProduce(player);
		ticks = 48;
		player.sm("Livid farm minigame will start in 30 seconds.");
		return false;
	}

	@Override
	public void magicTeleported(int teleType) {
		flowers.clear();
		player.getControlerManager().forceStop();
	}

	@Override
	public void process() {
		if (flowerTick >= 60) {// 15 seconds
			for (Entry<WorldTile, Integer> flower : flowers.entrySet()) {
				if (flower.getValue() == FERTILISED_STAGE) {
					WorldObject object = World.getStandardFloorObject(flower.getKey());
					player.getVarsManager().sendVarBit(object.getConfigByFile(), GROWING_STAGE);
				}
			}
		}
		if (ticks >= 96) {
			ticks = 0;
			flowerTick = 0;
			gathered = 0;
			spawnPlants();
			spawnBrokenFences();
		}
		flowerTick++;
		ticks++;
	}

	@Override
	public void forceClose() {
		player.getInterfaceManager().closeTab(getOverlayId(player));
	}
}
