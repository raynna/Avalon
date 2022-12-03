package com.rs.game.player.actions.skills.runecrafting;

import com.rs.game.WorldTile;
import com.rs.game.player.Player;

public class Altars extends Runecrafting {

	/**
	 * Store all altars, Enter objectId, objectId for craft rune, talisman, location
	 * for altar, tiaras
	 */

	public enum Altar {

		AIR_ALTAR(2452, 2478, AIR_TALISMAN, new WorldTile(2841, 4829, 0), AIR_TIARA, OMNI_TIARA),

		MIND_ALTAR(2453, 2479, MIND_TALISMAN, new WorldTile(2793, 4828, 0), MIND_TIARA, OMNI_TIARA),

		WATER_ALTAR(2454, 2480, WATER_TALISMAN, new WorldTile(3494, 4832, 0), WATER_TIARA, OMNI_TIARA),

		EARTH_ALTAR(2455, 2481, EARTH_TALISMAN, new WorldTile(2655, 4830, 0), EARTH_TIARA, OMNI_TIARA),

		FIRE_ALTAR(2456, 2482, FIRE_TALISMAN, new WorldTile(2577, 4846, 0), FIRE_TIARA, OMNI_TIARA),

		BODY_ALTAR(2457, 2483, BODY_TALISMAN, new WorldTile(2521, 4834, 0), BODY_TIARA, OMNI_TIARA),

		COSMIC_ALTAR(2458, 2484, COSMIC_TALISMAN, new WorldTile(2162, 4833, 0), COSMIC_TIARA, OMNI_TIARA),

		LAW_ALTAR(2459, 2485, LAW_TALISMAN, new WorldTile(2464, 4818, 0), LAW_TIARA, OMNI_TIARA),

		NATURE_ALTAR(2460, 2486, NATURE_TALISMAN, new WorldTile(2400, 4835, 0), NATURE_TIARA, OMNI_TIARA),

		CHAOS_ALTAR(2461, 2487, CHAOS_TALISMAN, new WorldTile(2281, 4837, 0), CHAOS_TIARA, OMNI_TIARA),

		DEATH_ALTAR(2462, 2488, DEATH_TALISMAN, new WorldTile(2208, 4830, 0), DEATH_TIARA, OMNI_TIARA),

		BLOOD_ALTAR(2464, 30624, BLOOD_TALISMAN, new WorldTile(2468, 4889, 1), BLOOD_TIARA, OMNI_TIARA),

		ASTRAL_ALTAR(-1, 17010, -1, null, ASTRAL_TIARA, OMNI_TIARA);

		int entranceId;
		int craftId;
		WorldTile location;
		int inventory;
		int[] items;

		Altar(int entranceId, int craftId, int inventory, WorldTile location, int... items) {
			this.entranceId = entranceId;
			this.craftId = craftId;
			this.location = location;
			this.inventory = inventory;
			this.items = items;
		}
	}

	private static Altar[] data = Altar.values();

	public static void handleAltar(Player player, int objectId) {
		for (Altar store : data) {
			if (store == null)
				continue;
			if (store.entranceId == objectId) {
				if (player.getInventory().containsOneItem(store.inventory)
						|| player.getEquipment().containsOneItem(store.items))
					enterAltar(player, new WorldTile(store.location));
				else
					player.getPackets()
							.sendGameMessage("You need an " + store.name().toLowerCase().replace("_altar", "")
									+ (store.inventory != -1 ? " talisman/" : " ") + "tiara to enter this altar.");
				return;
			}
			if (store.craftId == objectId) {
				for (RunecraftingStore rune : storeData) {
					if (rune == null)
						continue;
					if (rune.altarId == objectId) {
						if ((player.getInventory().containsOneItem(rune.inventory)
								|| player.getEquipment().containsOneItem(rune.items)) && rune.inventory != -1)
							craftRune(player, rune.runeId, rune.level, rune.exp, rune.pureEssence);
						else
							player.getPackets()
									.sendGameMessage("You need an " + rune.name().toLowerCase().replace("_rune", "")
											+ (store.inventory != -1 ? " talisman/" : " ") + "tiara to craft "
											+ rune.name().toLowerCase().replace('_', ' ') + "s.");
					}
				}
			}
		}
	}

	private static void enterAltar(Player player, WorldTile dest) {
		player.getPackets().sendGameMessage("A mysterious force grabs hold of you.");
		player.useStairs(-1, dest, 0, 1);
	}

}
