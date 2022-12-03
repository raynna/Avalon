package com.rs.game.player.actions.combat.lunarspells;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class StringJewellery {

	public static enum Amulets {

		GOLD_AMULET(1673, 1692),

		SAPPHIRE_AMULET(1675, 1694),

		EMERALD_AMULET(1677, 1696),

		RUBY_AMULET(1679, 1698),

		DIAMOND_AMULET(1681, 1700),

		DRAGONSTONE_AMULET(1683, 1702),

		ONYX_AMULET(6579, 6581),

		LIVID_PLANT(20704, 20705);

		private int baseId;
		private int newId;

		private Amulets(int baseId, int newId) {
			this.baseId = baseId;
			this.newId = newId;
		}

		public int getBaseId() {
			return baseId;
		}

		public int getNewId() {
			return newId;
		}
	}

	public static boolean cast(Player player, double xp) {
		boolean hasAmulet = false;
		for (Amulets amulet : Amulets.values()) {
			if (amulet == null)
				continue;
			if (player.getInventory().containsItem(amulet.getBaseId(), 1)) {
				hasAmulet = true;
				break;
			}
		}
		for (Amulets amulets : Amulets.values()) {
			if (amulets == null)
				continue;
			if (!hasAmulet) {
				player.getPackets().sendGameMessage("You don't have any amulets to string.");
				break;
			}
			if (hasAmulet && !player.getInventory().containsItem(amulets.getBaseId(), 1))
				continue;
			player.lock(2);
			player.animate(new Animation(4412));
			player.gfx(new Graphics(742, 0, 96 << 16));
			player.getSkills().addXp(Skills.MAGIC, xp);
			if (amulets == Amulets.LIVID_PLANT) {
				if (player.getInventory().getNumberOf(20704) >= 10) {
					player.getInventory().deleteItem(20704, 10);
					player.getInventory().addItem(20705, 2);
				} else {
					player.getInventory().deleteItem(20704, 5);
					player.getInventory().addItem(20705, 1);
				}
			} else {
				player.getInventory().deleteItem(amulets.getBaseId(), 1);
				player.getInventory().addItem(amulets.getNewId(), 1);
			}
			player.getPackets().sendGameMessage("Your spell strings the "
					+ ItemDefinitions.getItemDefinitions(amulets.getBaseId()).getName() + ".");
			return true;
		}
		return false;
	}
}
