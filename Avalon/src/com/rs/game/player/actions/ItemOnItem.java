package com.rs.game.player.actions;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Player;

public class ItemOnItem {

	public enum ItemCombinations {

		AMULET_OF_FURY(19335, 6585, 19333),

		SLAYER_HELMET(13263, 8921, 4164, 4166, 4168);

		private int itemUsed;
		private int usedWith;
		private int finalItem;
		private int[] itemReqs;

		private ItemCombinations(int finalItem, int itemUsed, int usedWith) {
			this.setFinalItem(finalItem);
			this.setItemUsed(itemUsed);
			this.setUsedWith(usedWith);
		}

		private ItemCombinations(int finalItem, int... itemReqs) {
			this.setItemUsed(itemUsed);
			this.setItemReqs(itemReqs);
		}

		public int getItemUsed() {
			return itemUsed;
		}

		public void setItemUsed(int itemUsed) {
			this.itemUsed = itemUsed;
		}

		public int getUsedWith() {
			return usedWith;
		}

		public void setUsedWith(int usedWith) {
			this.usedWith = usedWith;
		}

		public int getFinalItem() {
			return finalItem;
		}

		public void setFinalItem(int finalItem) {
			this.finalItem = finalItem;
		}

		public int[] getItemReqs() {
			return itemReqs;
		}

		public void setItemReqs(int[] itemReqs) {
			this.itemReqs = itemReqs;
		}

	}

	public static boolean contains(int id1, int id2, Item... items) {
		boolean containsId1 = false;
		boolean containsId2 = false;
		for (Item item : items) {
			if (item.getId() == id1)
				containsId1 = true;
			else if (item.getId() == id2)
				containsId2 = true;
		}
		return containsId1 && containsId2;
	}
	
	public static boolean isValidCombination(Player player, Item itemUsed, Item usedWith, int fromSlot, int toSlot) {
		for (ItemCombinations items : ItemCombinations.values()) {
			if (items != null) {
				if (items.getItemReqs() != null) {
					for (int itemReqs : items.getItemReqs()) {
						if (itemUsed.getId() == itemReqs) {
							handleCombination(player, items, items.getItemUsed(), items.getUsedWith(), items.getFinalItem(), fromSlot, toSlot, items.getItemReqs());
							return true;
						}
					}
				}
				if (items.getItemUsed() == itemUsed.getId() && items.getUsedWith() == usedWith.getId()) {
					handleCombination(player, items, items.getItemUsed(), items.getUsedWith(), items.getFinalItem(), fromSlot, toSlot, items.getItemReqs());
					return true;
				}
			}
		}
		return false;
	}

	private static void handleCombination(Player player, ItemCombinations item, int itemUsed, int usedWith, int finalItem, int fromSlot, int toSlot,
			int[] itemReqs) {
		boolean hasItems = false;
		if (itemReqs != null) {
			for (int reqs : itemReqs) {
				hasItems = !player.getInventory().containsItem(reqs, 1);
				if (!player.getInventory().containsItem(reqs, 1)) {
					player.getPackets()
							.sendGameMessage("You don't have " + ItemDefinitions.getItemDefinitions(reqs).getName()
									+ " to combine a "
									+ ItemDefinitions.getItemDefinitions(finalItem).getName() + ".");
					return;
				}
			}
			if (hasItems) {
				for (int reqs : itemReqs)
				player.getInventory().deleteItem(reqs, 1);
			}
		}
		player.getInventory().deleteItem(fromSlot, new Item(itemUsed));
		player.getInventory().deleteItem(toSlot, new Item(usedWith));
		player.getInventory().addItem(new Item(finalItem, 1));
		player.getPackets().sendGameMessage(combineMessage(player, item));
	}

	private static String combineMessage(Player player, ItemCombinations combinations) {
		switch (combinations) {
		case AMULET_OF_FURY:
			return "";
		default:
			break;
		}
		return "null";
	}

}
