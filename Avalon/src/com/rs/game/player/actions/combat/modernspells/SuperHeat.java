package com.rs.game.player.actions.combat.modernspells;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class SuperHeat {

	private static int COPPER = 436, TIN = 438, IRON = 440, SILVER = 442, GOLD = 444, COAL = 453, MITHRIL = 447, ADAMANT = 449,
			RUNE = 451;
	private static int BRONZE_BAR = 2349, IRON_BAR = 2351, STEEL_BAR = 2353, SILVER_BAR = 2355, GOLD_BAR = 2357, MITHRIL_BAR = 2359,
			ADAMANT_BAR = 2361, RUNE_BAR = 2363;

	public static enum Ores {

		COPPER_ORE(COPPER, new Item(TIN, 1), BRONZE_BAR, 6.25, 1),

		TIN_ORE(TIN, new Item(COPPER, 1), BRONZE_BAR, 6.25, 1),

		IRON_ORE(IRON, IRON_BAR, 12.5, 20),

		SILVER_ORE(SILVER, SILVER_BAR, 13.67, 20),

		COAL_ORE(COAL, new Item(IRON, 2), STEEL_BAR, 17.5, 10),
		
		GOLD_ORE(GOLD, GOLD_BAR, 22.5, 10),

		MITHIRL_ORE(MITHRIL, new Item(COAL, 4), MITHRIL_BAR, 30, 10),

		ADAMANT_ORE(ADAMANT, new Item(COAL, 6), ADAMANT_BAR, 37.5, 10),

		RUNE_ORE(RUNE, new Item(COAL, 8), RUNE_BAR, 50, 10);

		private int baseId;
		private int newId;
		private Item reqs;
		private double xp;
		private int level;

		private Ores(int baseId, Item requirements, int newId, double xp, int level) {
			this.baseId = baseId;
			this.setReqs(requirements);
			this.newId = newId;
			this.xp = xp;
			this.level = level;
		}

		private Ores(int baseId, int newId, double xp, int level) {
			this.baseId = baseId;
			this.newId = newId;
			this.xp = xp;
			this.level = level;
		}

		public int getBaseId() {
			return baseId;
		}

		public int getNewId() {
			return newId;
		}

		public double getXp() {
			return xp;
		}

		public int getLevel() {
			return level;
		}

		public Item getReqs() {
			return reqs;
		}

		public void setReqs(Item reqs) {
			this.reqs = reqs;
		}
	}

	public static boolean cast(Player player, double xp, int itemId, int slotId) {
		if (player.hasSpellDelay()) {
			return false;
		}
		if (!new Item(itemId).getName().toLowerCase().contains(" ore") && !new Item(itemId).getName().toLowerCase().contains("coal")) {
			player.getPackets().sendGameMessage("You can only cast this spell on ores.");
			return false;
		}
		for (Ores ores : Ores.values()) {
			if (ores.getBaseId() == itemId) {
				if (player.getSkills().getLevel(Skills.SMITHING) < ores.getLevel()) {
					player.getPackets().sendGameMessage(
							"You need at least a level of " + ores.getLevel() + " smithing to melt this.");
					return false;
				}
				if (ores.getReqs() != null) {
					if (!player.getInventory().containsItem(ores.getReqs().getId(), ores.getReqs().getAmount())) {
						player.getPackets().sendGameMessage("You don't have enough " +  ores.getReqs().getName() + ".");
						return false;
					}
					player.getInventory().deleteItem(ores.getReqs().getId(), ores.getReqs().getAmount());
				}
				player.castSpellDelay(3);
				player.lock(1);
				Item base = new Item(ores.getBaseId());
				player.getInventory().deleteItem(slotId, base);
				player.getInventory().addItem(ores.getNewId(), 1);
				player.getSkills().addXp(Skills.MAGIC, xp);
				player.getSkills().addXp(Skills.SMITHING, ores.getXp());
				player.animate(new Animation(722));
				player.gfx(new Graphics(148, 0, 100));
				return true;
			}
		}
		return false;
	}

}
