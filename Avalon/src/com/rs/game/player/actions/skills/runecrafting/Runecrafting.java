package com.rs.game.player.actions.skills.runecrafting;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.runecrafting.RunecraftingOutfit.Pieces;
import com.rs.game.player.content.tasksystem.TaskManager.Tasks;

public class Runecrafting {

	protected static Animation CRAFT_ANIMATION = new Animation(789);

	public final static int RUNE_ESSENCE = 1436, PURE_ESSENCE = 7936,

			AIR_TIARA = 5527, MIND_TIARA = 5529, WATER_TIARA = 5531, BODY_TIARA = 5533, EARTH_TIARA = 5535,
			FIRE_TIARA = 5537, COSMIC_TIARA = 5539, NATURE_TIARA = 5541, CHAOS_TIARA = 5543, LAW_TIARA = 5545,
			DEATH_TIARA = 5547, BLOOD_TIARA = 5549, SOUL_TIARA = 5551, ASTRAL_TIARA = 9106, OMNI_TIARA = 13655,

			AIR_TALISMAN = 1438, MIND_TALISMAN = 1448, WATER_TALISMAN = 1444, EARTH_TALISMAN = 1440,
			FIRE_TALISMAN = 1442, BODY_TALISMAN = 1446, LAW_TALISMAN = 1458, NATURE_TALISMAN = 1462,
			CHAOS_TALISMAN = 1452, BLOOD_TALISMAN = 1450, DEATH_TALISMAN = 1456, COSMIC_TALISMAN = 1454,
			SOUL_TALISMAN = 1460;

	public static boolean isTiara(int id) {
		return id == AIR_TIARA || id == MIND_TIARA || id == WATER_TIARA || id == BODY_TIARA || id == EARTH_TIARA
				|| id == FIRE_TIARA || id == COSMIC_TIARA || id == NATURE_TIARA || id == CHAOS_TIARA || id == LAW_TIARA
				|| id == DEATH_TIARA || id == BLOOD_TIARA || id == SOUL_TIARA || id == ASTRAL_TIARA || id == OMNI_TIARA;
	}

	protected static RunecraftingStore[] storeData = RunecraftingStore.values();
	protected static MultiRunes[] multiData = MultiRunes.values();
	protected static Pieces[] piece = Pieces.values();

	/**
	 * Store all runecrafting runes, level, exp along with talismans & tiaras
	 *
	 */

	public enum RunecraftingStore {

		AIR_RUNE(2478, 556, 1, 5.0, AIR_TALISMAN, false, AIR_TIARA, OMNI_TIARA),

		MIND_RUNE(2479, 558, 2, 5.5, MIND_TALISMAN, false, MIND_TIARA, OMNI_TIARA),

		WATER_RUNE(2480, 555, 5, 6.0, WATER_TALISMAN, false, WATER_TIARA, OMNI_TIARA),

		EARTH_RUNE(2481, 557, 9, 6.5, EARTH_TALISMAN, false, EARTH_TIARA, OMNI_TIARA),

		FIRE_RUNE(2482, 554, 14, 7.0, FIRE_TALISMAN, false, FIRE_TIARA, OMNI_TIARA),

		BODY_RUNE(2483, 559, 20, 7.5, BODY_TALISMAN, false, BODY_TIARA, OMNI_TIARA),

		COSMIC_RUNE(2484, 564, 27, 8.0, COSMIC_TALISMAN, true, COSMIC_TIARA, OMNI_TIARA),

		CHAOS_RUNE(2487, 562, 35, 8.5, CHAOS_TALISMAN, true, CHAOS_TIARA, OMNI_TIARA),

		ASTRAL_RUNE(17010, 9075, 40, 8.7, -1, true, -1),

		NATURE_RUNE(2486, 561, 44, 9.0, NATURE_TALISMAN, true, NATURE_TIARA, OMNI_TIARA),

		LAW_RUNE(2485, 563, 54, 9.5, LAW_TALISMAN, true, LAW_TIARA, OMNI_TIARA),

		DEATH_RUNE(2488, 560, 65, 10.0, DEATH_TALISMAN, true, DEATH_TIARA, OMNI_TIARA),

		BLOOD_RUNE(30624, 565, 77, 10.5, BLOOD_TALISMAN, true, BLOOD_TIARA, OMNI_TIARA),

		;

		int altarId;
		int runeId;
		int level;
		double exp;
		int inventory;
		boolean pureEssence;
		int[] items;

		RunecraftingStore(int altarId, int runeId, int level, double exp, int inventory, boolean pureEssence,
				int... items) {
			this.altarId = altarId;
			this.runeId = runeId;
			this.level = level;
			this.exp = exp;
			this.inventory = inventory;
			this.pureEssence = pureEssence;
			this.items = items;

		}

	}

	/**
	 * Multiple runes depending on level
	 *
	 */

	public enum MultiRunes {

		AIR(556, 11, 2, 22, 3, 33, 4, 44, 5, 55, 6, 66, 7, 77, 8, 88, 9, 99, 10),

		MIND(558, 14, 2, 28, 3, 42, 4, 56, 5, 70, 6, 84, 7, 98, 8),

		WATER(555, 19, 2, 38, 3, 57, 4, 76, 5, 95, 6),

		EARTH(557, 26, 2, 52, 3, 78, 4),

		FIRE(554, 35, 2, 70, 3),

		BODY(559, 46, 2, 92, 3),

		COSMIC(564, 59, 2),

		CHAOS(562, 74, 2),

		ASTRAL(9075, 82, 2),

		NATURE(561, 91, 2);

		int runeId;
		/**
		 * level, amount, level, amount...
		 */
		int[] multipliers;

		MultiRunes(int runeId, int... multipliers) {
			this.runeId = runeId;
			this.multipliers = multipliers;
		}
	}

	public static void craftRune(Player player, int runeId, int level, double exp, boolean pureEssence) {
		int actualLevel = player.getSkills().getLevel(Skills.RUNECRAFTING);
		if (actualLevel < level) {
			player.getPackets().sendGameMessage("You need a runecrafting level of " + level + " to craft this rune.");
			return;
		}
		int runes = player.getInventory().getItems().getNumberOf(PURE_ESSENCE);
		if (runes > 0)
			player.getInventory().deleteItem(PURE_ESSENCE, runes);
		if (!pureEssence) {
			int normalEss = player.getInventory().getItems().getNumberOf(RUNE_ESSENCE);
			if (normalEss > 0) {
				player.getInventory().deleteItem(RUNE_ESSENCE, normalEss);
				runes += normalEss;
			}
		}
		if (runes == 0) {
			player.getPackets().sendGameMessage("You don't have " + (pureEssence ? "pure" : "rune") + " essence.");
			return;
		}
		for (MultiRunes multi : multiData) {
			if (multi != null) {
				if (multi.runeId == runeId) {
					for (int i = multi.multipliers.length - 2; i >= 0; i -= 2) {
						if (actualLevel >= multi.multipliers[i]) {
							runes *= multi.multipliers[i + 1];
							break;
						}
					}
				}
			}
		}
		double totalXp = exp * runes;
		totalXp *= getRunecraftingBoost(player);
		player.getSkills().addSkillingXp(Skills.RUNECRAFTING, totalXp, getRunecraftingBoost(player));
		player.gfx(new Graphics(186));
		player.animate(new Animation(791));
		player.lock(1);
		player.getInventory().addItem(runeId, runes);
		for (int i = 0; i < runes; i++) {
			RunecraftingOutfit.addPiece(player, level);
		}
		if (runeId == 556)
			player.getTaskManager().checkComplete(Tasks.CRAFT_AIR_RUNE, runes);
		if (runeId == 565)
			player.getTaskManager().checkComplete(Tasks.CRAFT_BLOOD_RUNE, runes);
		player.getPackets().sendGameMessage("You bind the temple's power into "
				+ ItemDefinitions.getItemDefinitions(runeId).getName().toLowerCase() + "s.");
	}

	public static boolean hasRcingSuit(Player player) {
		for (Pieces piece : piece) {
			if (!player.getEquipment().containsOneItem(piece.getItemId())) {
				return false;
			}
		}
		return true;
	}

	public static double getRunecraftingBoost(Player player) {
		double boost = 1.0;
		for (Pieces piece : piece) {
			if (player.getEquipment().containsOneItem(piece.getItemId())) {
				boost += 0.01;
			}
		}
		if (hasRcingSuit(player))
			boost += 0.01;
		return boost;
	}
}