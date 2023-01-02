package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 26 Apr 2016
 *
 **/

public class PlankMake {

	public static enum Planks {

		REGULAR_PLANK(1511, 960, 175),

		OAK_PLANK(1521, 8778, 225),

		TEAK_PLANK(6333, 8780, 225),

		MAHOGANY_PLANK(6332, 8782, 225),

		LIVID_FARM_PLANK(20702, 20703, -1);

		private int baseId;
		private int newId;
		private int cost;

		private Planks(int baseId, int newId, int cost) {
			this.baseId = baseId;
			this.newId = newId;
			this.cost = cost;
		}

		public static Planks getPlank(int i) {
			for (Planks s : Planks.values()) {
				if (s.getBaseId() == i)
					return s;
			}
			return null;
		}

		public int getBaseId() {
			return baseId;
		}

		public int getNewId() {
			return newId;
		}

		public int getCost() {
			return cost;
		}
	}

	public static boolean isLog(int log) {
		return (log == 1511 || log == 1521 || log == 6333 || log == 6332);
	}

	public static boolean cast(Player player, double xp, int itemId, int slotId, boolean lividFarm) {
		if ((Long) player.getTemporaryAttributes().get("LAST_SPELL") != null
				&& (long) player.getTemporaryAttributes().get("LAST_SPELL") + 1800 > Utils.currentTimeMillis()) {
			return false;
		}
		Planks plank = Planks.getPlank(itemId);
		if (plank == null) {
			if (!lividFarm)
				player.sm("You can only convert: plain, oak, teak and mahogany logs into planks.");
			return false;
		}
		if (player.canBuy(plank.getCost())) {
			player.lock(1);
			player.animate(new Animation(6298));
			player.gfx(new Graphics(1063));
			player.getInventory().deleteItem(slotId, new Item(plank.getBaseId()));
			player.addItem(plank.getNewId(), 1);
			if (!lividFarm) {
				player.getInterfaceManager().openGameTab(7);
				player.addXp(Skills.MAGIC, xp);
			}
			player.getTemporaryAttributes().put("LAST_SPELL", Utils.currentTimeMillis());
			return true;
		} else {
			player.sm("You need at least " + plank.getCost() + " coins to cast this spell on this log.");
			return false;
		}
	}

}
