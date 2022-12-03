package com.rs.game.player.actions.skills.runecrafting;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class Tiaras extends Runecrafting {

	private static int EMPTY_TIARA = 5525;

	public enum RunecraftingTiaraStore {

		AIR_TIARA(1438, 2478, 5527, 1, 25.0),

		MIND_TIARA(1448, 2479, 5529, 2, 27.5),

		WATER_TIARA(1444, 2480, 5531, 5, 30.0),

		EARTH_TIARA(1440, 2481, 5535, 9, 32.5),

		FIRE_TIARA(1442, 2482, 5537, 14, 35.0),

		BODY_TIARA(1446, 2483, 5533, 20, 37.5),

		COSMIC_TIARA(1454, 2484, 5539, 27, 40.0),

		CHAOS_TIARA(1452, 2487, 5543, 35, 42.5),

		NATURE_TIARA(1462, 2486, 5541, 44, 45.0),

		LAW_TIARA(1458, 2485, 5545, 54, 47.5),

		DEATH_TIARA(1456, 2488, 5547, 65, 50.0),

		BLOOD_TIARA(1450, 30624, 5549, 77, 52.5);

		public int talismanId;
		public int altarId;
		public int tiaraId;
		public int level;
		public double exp;

		private RunecraftingTiaraStore(int talismanId, int altarId, int tiaraId, int level, double exp) {
			this.talismanId = talismanId;
			this.altarId = altarId;
			this.tiaraId = tiaraId;
			this.level = level;
			this.exp = exp;

		}
	}

	public static void enchantTiara(Player player, int talismanId, int tiaraId, int level, double exp) {
		if (player.getSkills().getLevelForXp(Skills.RUNECRAFTING) < level) {
			player.getPackets().sendGameMessage("You need a level of " + level + " Runecrafting to create "
					+ ItemDefinitions.getItemDefinitions(tiaraId).getName() + ".");
			return;
		}
		if (!player.getInventory().containsItem(EMPTY_TIARA, 1)) {
			player.getPackets().sendGameMessage(
					"You need a empty tiara to create " + ItemDefinitions.getItemDefinitions(tiaraId).getName() + ".");
			return;
		}
		player.gfx(new Graphics(186));
		player.animate(new Animation(791));
		player.lock(1);
		player.getInventory().deleteItem(talismanId, 1);
		player.getInventory().deleteItem(EMPTY_TIARA, 1);
		player.getInventory().addItem(tiaraId, 1);
		player.getSkills().addXp(Skills.RUNECRAFTING, exp);
		player.getPackets().sendGameMessage("You bind the power of the talisman into your tiara.");
		return;
	}
}
