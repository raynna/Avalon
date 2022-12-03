package com.rs.game.player.actions.skills.runecrafting;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class CombinationRunes extends Runecrafting {

	public enum CombinationRunesStore {

		MIST_RUNES(556, 555, 2478, 2480, 4695, 6, 8.0, 8.5, AIR_TALISMAN, WATER_TALISMAN),

		DUST_RUNE(556, 557, 2478, 2481, 4696, 10, 8.3, 9.0, AIR_TALISMAN, EARTH_TALISMAN),

		MUD_RUNE(555, 557, 2480, 2481, 4698, 13, 9.3, 9.5, WATER_TALISMAN, EARTH_TALISMAN),

		SMOKE_RUNE(556, 554, 2478, 2482, 4697, 15, 8.5, 9.5, AIR_TALISMAN, FIRE_TALISMAN),

		STEAM_RUNE(555, 554, 2480, 2482, 4694, 19, 9.3, 10.0, WATER_TALISMAN, FIRE_TALISMAN),

		LAVA_RUNE(557, 554, 2481, 2482, 4699, 23, 10.0, 10.5, EARTH_TALISMAN, FIRE_TALISMAN);

		public int itemId;
		public int itemId2;
		public int objectId;
		public int objectId2;
		public int finalItem;
		public int level;
		public double exp;
		public double exp2;
		public int talisman;
		public int talisman2;

		private CombinationRunesStore(int itemId, int itemId2, int objectId, int objectId2, int finalItem, int level,
				double exp, double exp2, int talisman, int talisman2) {
			this.itemId = itemId;
			this.itemId2 = itemId2;
			this.objectId = objectId;
			this.objectId2 = objectId2;
			this.finalItem = finalItem;
			this.level = level;
			this.exp = exp;
			this.exp2 = exp2;
			this.talisman = talisman;
			this.talisman2 = talisman2;
		}

	}

	public static void craftComboRune(Player player, int runeId, int level, double exp, int finalItem, int talisman) {
		ItemDefinitions runeDef = ItemDefinitions.getItemDefinitions(runeId);
		ItemDefinitions def = ItemDefinitions.getItemDefinitions(finalItem);
		ItemDefinitions talismanDef = ItemDefinitions.getItemDefinitions(talisman);
		int actualLevel = player.getSkills().getLevel(Skills.RUNECRAFTING);
		if (actualLevel < level) {
			player.sm("You need a runecrafting level of " + level + " to craft this rune.");
			return;
		}
		if (!player.getInventory().containsItem(talisman, 1)) {
			player.sm(
					"You need a " + talismanDef.getName() + " to craft this.");
			return;
		}
		int pureEssence = player.getInventory().getItems().getNumberOf(PURE_ESSENCE);
		if (pureEssence == 0) {
			player.sm("You don't have pure essence.");
			return;
		}
		if (player.getInventory().getNumberOf(runeId) == 0) {
			player.sm(
					"You don't have any " + runeDef.getName() + "s.");
			return;
		}
		int amount = pureEssence;
		if (player.getInventory().getNumberOf(runeId) < pureEssence)
			amount = player.getInventory().getNumberOf(runeId);
		player.gfx(new Graphics(186));
		player.animate(CRAFT_ANIMATION);
		player.lock(1);
		Boolean magicImbue = (Boolean) player.getTemporaryAttributtes().get("LAST_IMBUE");
		if (magicImbue == null || magicImbue == Boolean.FALSE)
			player.removeItem(talisman, 1);
		player.removeItem(runeId, amount);
		player.removeItem(PURE_ESSENCE, amount);
		boolean hasBinding = player.getEquipment().getAmuletId() == 5521;
		if (Utils.getRandomDouble(100) <= 50 && !hasBinding) {
			player.sm("You fail to craft "
							+ runeDef.getName().toLowerCase() + "s into "
							+ def.getName().toLowerCase() + "s.");
			return;
		}
		player.addItem(finalItem, amount);
		double totalXp = exp * amount;
		if (hasRcingSuit(player))
			totalXp *= 1.025;
		player.addXp(Skills.RUNECRAFTING, totalXp);
		player.sm("You bind the temple's power into "
				+ def.getName().toLowerCase() + "s.");
	}

}
