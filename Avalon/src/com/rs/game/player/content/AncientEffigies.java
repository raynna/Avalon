package com.rs.game.player.content;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class AncientEffigies {

	/*
	 * @Improved by Tristam
	 */

	private static final int Final_Animation = 14177, Final_Graphics = 2692;

	public static int[] SKILL_1 = { Skills.AGILITY, Skills.CONSTRUCTION, Skills.COOKING, Skills.FISHING,
			Skills.FLETCHING, Skills.HERBLORE, Skills.MINING, Skills.SUMMONING };

	public static int[] SKILL_2 = { Skills.CRAFTING, Skills.THIEVING, Skills.FIREMAKING, Skills.FARMING,
			Skills.WOODCUTTING, Skills.HUNTER, Skills.SMITHING, Skills.RUNECRAFTING };

	public static final int STARVED_ANCIENT_EFFIGY = 18778, NOURISHED_ANCIENT_EFFIGY = 18779,
			SATED_ANCIENT_EFFIGY = 18780, GORGED_ANCIENT_EFFIGY = 18781, DRAGONKIN_LAMP = 18782;

	public static int getRequiredLevel(int id) {
		switch (id) {
		case STARVED_ANCIENT_EFFIGY:
			return 91;
		case NOURISHED_ANCIENT_EFFIGY:
			return 93;
		case SATED_ANCIENT_EFFIGY:
			return 95;
		case GORGED_ANCIENT_EFFIGY:
			return 97;
		}
		return -1;
	}

	public static String getMessage(int skill) {
		switch (skill) {
		case Skills.AGILITY:
			return "deftness and precision";
		case Skills.CONSTRUCTION:
			return "buildings and security";
		case Skills.COOKING:
			return "fire and preparation";
		case Skills.FISHING:
			return "life and cultivation";
		case Skills.FLETCHING:
			return "lumber and woodworking";
		case Skills.HERBLORE:
			return "flora and fuana";
		case Skills.MINING:
			return "metalwork and minerals";
		case Skills.SUMMONING:
			return "binding essence and spirits";
		}
		return null;
	}

	public static int getExp(int itemId) {
		switch (itemId) {
		case STARVED_ANCIENT_EFFIGY:
			return 15000;
		case NOURISHED_ANCIENT_EFFIGY:
			return 20000;
		case SATED_ANCIENT_EFFIGY:
			return 25000;
		case GORGED_ANCIENT_EFFIGY:
			return 30000;
		}
		return -1;
	}

	public static boolean continueEffigy(Player player, int itemId) {
		if (itemId == STARVED_ANCIENT_EFFIGY) {
			player.getInventory().deleteItem(STARVED_ANCIENT_EFFIGY, 1);
			player.getInventory().addItem(NOURISHED_ANCIENT_EFFIGY, 1);
		} else if (itemId == NOURISHED_ANCIENT_EFFIGY) {
			player.getInventory().deleteItem(NOURISHED_ANCIENT_EFFIGY, 1);
			player.getInventory().addItem(SATED_ANCIENT_EFFIGY, 1);
		} else if (itemId == SATED_ANCIENT_EFFIGY) {
			player.getInventory().deleteItem(SATED_ANCIENT_EFFIGY, 1);
			player.getInventory().addItem(GORGED_ANCIENT_EFFIGY, 1);
		} else if (itemId == GORGED_ANCIENT_EFFIGY) {
			player.getInventory().deleteItem(GORGED_ANCIENT_EFFIGY, 1);
			player.getInventory().addItem(DRAGONKIN_LAMP, 1);
		}
		player.lock(5);
		player.animate(new Animation(Final_Animation));
		player.gfx(new Graphics(Final_Graphics));
		return true;
	}
}
