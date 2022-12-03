package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class TuneBaneOre {

	public enum Ores {
		DRAGONBANE_ORE(21778, 21779,
				new Item[] { new Item(536), new Item(534), new Item(243), new Item(1753), new Item(1751),
						new Item(1749), new Item(1747) }),

		WALLASALKIBANE_ORE(21778, 21780, new Item[] { new Item(6163), new Item(6165), new Item(6167) }),

		BASILISKBANE_ORE(21778, 21781, new Item[] { new Item(7977) }),

		ABYSSALBANE_ORE(21778, 21782, new Item[] { new Item(7979) })

		;

		private int baseOreId;
		private int newOreId;
		private Item[] reqs;

		private Ores(int baseOreId, int newOreId, Item[] reqs) {
			this.setBaseOreId(baseOreId);
			this.setNewOreId(newOreId);
			this.setReqs(reqs);

		}

		public int getBaseOreId() {
			return baseOreId;
		}

		public void setBaseOreId(int baseOreId) {
			this.baseOreId = baseOreId;
		}

		public int getNewOreId() {
			return newOreId;
		}

		public void setNewOreId(int newOreId) {
			this.newOreId = newOreId;
		}

		public Item[] getReqs() {
			return reqs;
		}

		public void setReqs(Item[] reqs) {
			this.reqs = reqs;
		}
	}

	static Item finalItem = null;

	private static boolean hasReqs(Player player, int itemId) {
		for (Ores ores : Ores.values()) {
			for (Item item : ores.getReqs()) {
				if (item == null)
					continue;
				if (itemId == item.getId()) {
					finalItem = item;
					return true;
				} else
					continue;
			}
		}
		return false;
	}

	public static boolean cast(Player player, double xp, int itemId, int slotId) {
		if (!hasReqs(player, itemId)) {
			player.sm("You can't use this spell on that item.");
			return false;
		}
		for (Ores ores : Ores.values()) {
			if (ores == null)
				continue;
			for (Item item : ores.getReqs()) {
				if (item == null)
					continue;
				if (finalItem.getId() == item.getId()) {
					int amount = player.getInventory().getNumberOf(ores.getBaseOreId());
					player.removeItem(ores.getBaseOreId(), amount);
					player.addItem(ores.getNewOreId(), amount);
					player.addXp(Skills.MAGIC, xp);
					return true;
				}
			}
		}
		return false;
	}

}
