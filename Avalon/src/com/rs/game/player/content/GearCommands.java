package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.summoning.Summoning;
import com.rs.game.player.actions.skills.summoning.Summoning.Pouch;
import com.rs.net.decoders.handlers.ButtonHandler;
import com.rs.utils.Utils;

public class GearCommands {

	/**
	 * @author _Andy @rune-server.org
	 */

	final static int MODERN = 0, ANCIENT = 1, LUNAR = 2;

	public static enum Gears {
		MAIN2("main2", new int[][]
		{{ 23255, 1 },
		{ 23279, 1 },
		{ 23351, 2 },
		{ 15272, 2 },
		{ 23567, 2 },
		{ 6737, 1 },
		{ 11732, 1 },
		{ 15272, 2 },
		{ 6585, 1 },
		{ 19748, 1 },
		{ 15272, 2 },
		{ 4736, 1 },
		{ 12681, 1 },
		{ 4720, 1 },
		{ 4722, 1 },
		{ 5698, 1 },
		{ 4151, 1 },
		{ 20072, 1 },
		{ 8013, 500 },
		{ 555, 6000 },
		{ 560, 4000 },
		{ 565, 3000 },
		{ 12437, 500 }},
		new int[][]
		{{ 2412, 1 },
		{ 4708, 1 },
		{ 7462, 1 },
		{ 6731, 1 },
		{ 6920, 1 },
		{ 15486, 1 },
		{ 4712, 1 },
		{ 4714, 1 },
		{ 6889, 1 },
		{ 18335, 1 }}, true, ANCIENT, true);
		private int[][] inventory;
		private int[][] equipment;
		private int[][] stats;
		private boolean curses;
		private int spellbook;
		private boolean spawnFamiliar;
		private String name;

		private Gears(String name, int[][] inventory, int[][] equipment, int[][] stats, boolean curses, int spellbook, boolean spawnFamiliar) {
			this.name = name;
			this.inventory = inventory;
			this.equipment = equipment;
			this.stats = stats;
			this.curses = curses;
			this.spellbook = spellbook;
			this.spawnFamiliar = spawnFamiliar;
		}

		private Gears(String name, int[][] inventory, int[][] equipment, boolean curses, int spellbook, boolean spawnFamiliar) {
			this(name, inventory, equipment, new int[][]
			{
			{ 0, 99 } }, curses, spellbook, spawnFamiliar);
		}

		public String getName() {
			return name;
		}
	}

	private final static int[][] forSwitch =
	{{ 6109, 656, 658, 660, 662, 664, 2900, 2910, 2920, 2930, 6860, 6858, 6856, 6862, 13101 }};

	private static int getRandomSwitchedItem(Player player, int init) {
		int switchCape = player.isCompletedFightKiln() ? 23659 : player.isCompletedFightCaves() ? 6570 : init == 10499 ? 10499 : 19748, imbueZerk = 15220, imbueSeers = 15018, randomTeamCape = 4316 + Utils.random(4413 - 4316);
		switch (init) {
		case 6737:
			return imbueZerk;
		case 10499:
		case 19748:
			return switchCape;
		case 6731:
			return imbueSeers;
		}
		if (init >= 4316 && init < 4414)
			return ItemDefinitions.getItemDefinitions(randomTeamCape).isNoted() ? randomTeamCape - 1 : randomTeamCape;
		for (int[] array : forSwitch) {
			for (int member : array) {
				if (member == init)
					return array[Utils.random(array.length)];
			}
		}
		return init;
	}

	private static void refresh(Player player) {
		player.getInventory().init();
		player.getEquipment().init();
		player.getSkills().restoreSkills();
		player.setHitpoints(player.getMaxHitpoints());
		player.refreshHitPoints();
		player.getPrayer().restorePrayer(player.getSkills().getLevel(Skills.PRAYER) * 10);
		player.getInterfaceManager().closeXPDisplay();
		player.getInterfaceManager().sendXPDisplay();
		ButtonHandler.refreshEquipBonuses(player);
		player.getAppearence().generateAppearenceData();
	}

	private static void setStats(Player player, Gears gear) {
		for (int id = 0; id < player.getSkills().getLevels().length; id++) {
			if (id >= 7 && id < 23 || id > 23)
				continue;
			player.getSkills().setXp(id, Skills.getXPForLevel(99));
		}
		for (int[] skills : gear.stats) {
			player.getSkills().setXp(skills[0], Skills.getXPForLevel(skills[1]));
		}
	}

	private static void setInventory(Player player, Gears gear) {
		player.getInventory().reset();
		for (int[] id : gear.inventory) {
			int switchedItem = getRandomSwitchedItem(player, id[0]);
			player.getInventory().addItem(switchedItem, id[1]);
		}
	}

	private static void setEquipment(Player player, Gears gear) {
		player.getEquipment().reset();
		for (int[] id : gear.equipment) {
			int switchedItem = getRandomSwitchedItem(player, id[0]);
			player.getEquipment().getItems().set(ItemDefinitions.getItemDefinitions(switchedItem).equipSlot, new Item(switchedItem, id[1]));
		}
	}

	private static void setMisc(Player player, Gears gear) {
		player.getPrayer().setPrayerBook(gear.curses);
		player.getCombatDefinitions().setSpellBook(gear.spellbook);
		if (player.getFamiliar() == null && gear.spawnFamiliar)
			Summoning.spawnFamiliar(player, Pouch.WOLPERTINGER);
	}

	private static void set(Player p, Gears g) {
		setInventory(p, g);
		setEquipment(p, g);
		setStats(p, g);
		setMisc(p, g);
		refresh(p);
	}

	public static String getSetupNamesC(Player player) {
		String setups = "";
		if (player.getSetups() != null) {
			for (CustomGear setup : player.getSetups()) {
				if (setup == null)
					continue;
				setups += setup.getName() + (", ");
			}
		}
		return " " + setups;
	}

	private static String getSetupNamesG() {
		String loadouts = "";
		int count = 0;
		for (Gears gear : Gears.values()) {
			count++;
			loadouts += gear.getName() + (count < Gears.values().length ? ", " : ".");
		}
		return loadouts;
	}

	public static void removeCustomGear(Player player, String name) {
		if (player.removeSetup(name))
			player.getPackets().sendGameMessage("Removed gear setup: " + name + ". Remaining setups:" + getSetupNamesC(player));
		else
			player.getPackets().sendGameMessage(name + " doesnt exist.");
	}
	
	public static void removeCustomGear(Player player, int id) {
		for (CustomGear gear : player.getSetups()) {
			if (gear != null) {
				if (gear.getId() == id) {
					player.getPackets().sendGameMessage("Removed gear setup: " + gear.getName() + ".");
					player.getSetups()[id] = null;
				}
			}
		}
	}

	public static void saveCustomGear(Player player, String name) {
		if (!getSetupNamesC(player).replace(", ", "@ ").contains(" " + name + "@") && !getSetupNamesG().replace(", ", "@ ").replace(".", "@").contains(" " + name + "@")) {
			if (player.addSetup(new CustomGear(player, name)))
				player.getPackets().sendGameMessage("Added gear setup: " + name + ". Current setups:" + getSetupNamesC(player));
			else
				player.getPackets().sendGameMessage("You've reached your maximum amount of gearsetups!");
		} else
			player.getPackets().sendGameMessage("You've already got a setup with this name!");
	}
	
	public static void saveCustomGear(Player player, String name, int icon) {
		if (!getSetupNamesC(player).replace(", ", "@ ").contains(" " + name + "@") && !getSetupNamesG().replace(", ", "@ ").replace(".", "@").contains(" " + name + "@")) {
			if (player.addSetup(new CustomGear(player, name, icon)))
				player.getPackets().sendGameMessage("Added gear setup: " + name + ". Current setups:" + getSetupNamesC(player));
			else
				player.getPackets().sendGameMessage("You've reached your maximum amount of gearsetups!");
		} else
			player.getPackets().sendGameMessage("You've already got a setup with this name!");
	}
	
	public static boolean isGearOtherSet(Player p, Player target, String type) {
		for (Gears gear : Gears.values()) {
			if (gear == null)
				continue;
			if (gear.name.toLowerCase().equals(type)) {
				set(p, gear);
				return true;
			}
		}
		if (target.getSetups() != null) {
			for (CustomGear gear : target.getSetups()) {
				if (gear == null)
					continue;
				if (!gear.getName().equals(type))
					continue;
				gear.set(p);
				return true;
			}
		}
		p.getPackets().sendGameMessage("Gear setup not found. " + target.getDisplayName() + " loadouts:" + getSetupNamesC(target));
		return false;
	}

	public static boolean isGearSet(Player p, String type) {
		for (Gears gear : Gears.values()) {
			if (gear == null)
				continue;
			if (gear.name.toLowerCase().equals(type)) {
				set(p, gear);
				return true;
			}
		}
		if (p.getSetups() != null) {
			for (CustomGear gear : p.getSetups()) {
				if (gear == null)
					continue;
				if (!gear.getName().equals(type))
					continue;
				gear.set(p);
				return true;
			}
		}
		p.getPackets().sendGameMessage("Gear setup not found. Your loadouts" + getSetupNamesC(p));
		p.getPackets().sendGameMessage("Server loadouts: " + getSetupNamesG());
		return false;
	}

		public static void saveCustomGearNoCheck(Player player, String name) {
		if (player.addSetup(new CustomGear(player, name)))
				player.getPackets().sendGameMessage("Added gear setup: " + name + ". Current setups:" + getSetupNamesC(player));
			else
				player.getPackets().sendGameMessage("You've reached your maximum amount of gearsetups!");
	}
}