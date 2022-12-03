package com.rs.game.player.content.unlockables;

import java.util.ArrayList;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;

/**
 * @author Arno
 *
 */
public class UnlockableManager {

	public static boolean hasUnlockedItem(Player player, String itemName) {
		int id = ItemDefinitions.forName(itemName).getId();
		ArrayList<UnlockableItems> unlockedList = player.getUnlockedItems();
		return checkById(unlockedList, id);
	}

	public static boolean hasUnlockedItem(Player player, int itemId) {
		return checkById(player.getUnlockedItems(), itemId);
	}

	private static boolean checkById(ArrayList<UnlockableItems> unlockedList, int itemId) {
		for (int i = 0; i < unlockedList.size(); i++) {
			UnlockableItems unlockable = unlockedList.get(i);
			int[] items = unlockable.getItemIds();
			for (int u : items) {
				if (itemId == u || itemId == ItemDefinitions.getItemDefinitions(u).getCertId()) {
					return true;
				}
			}
		}
		return true;
	}

	public static boolean isUnlockableItem(int itemId) {
		UnlockableItems[] unlockables = UnlockableItems.values();
		for (UnlockableItems unlockable : unlockables) {
			for (int u : unlockable.getItemIds()) {
				if (itemId == u || itemId == ItemDefinitions.getItemDefinitions(u).getCertId()) {
					return true;
				}
			}
		}
		return false;
	}

	public static void unlockItemForPlayer(Player player, int item) { 
		ItemDefinitions defs = ItemDefinitions.getItemDefinitions(item);
		if (!isUnlockableItem(item)) {
			player.getPackets().sendGameMessage(defs.getName() + " is not an unlockable item.");
		}
		if (hasUnlockedItem(player, item)) {
			player.getPackets().sendGameMessage("You have already unlocked " + defs.getName() + ".");
			return;
		}
		UnlockableItems toUnlock = UnlockableItems.forId(item);
		if (toUnlock != null) {
			if (player.getInventory().getNumberOf(18201) >= toUnlock.getPrice()) {
				player.getUnlockedItems().add(toUnlock);
				player.getInventory().addItem(item, 1);
				player.getInventory().deleteItem(18201, toUnlock.getPrice());
				player.getPackets().sendGameMessage("You purchased " + defs.getName() + " for " + toUnlock.getPrice() + " rusty coins.");		
				if (player.getInterfaceManager().containsInterface(275))
					showList(player);
			} else {
				int modulus = toUnlock.getPrice() - player.getInventory().getAmountOf(18201);
				player.getPackets().sendGameMessage("To purchase this item you need at least " + toUnlock.getPrice() + " rusty coins." + "<br>You still need another " + modulus + " rusty coins to purchase this item.");
			}
		}
	}

	public static void unlockItemNoCheck(Player player, int item) {
		ItemDefinitions defs = ItemDefinitions.getItemDefinitions(item);
		UnlockableItems toUnlock = UnlockableItems.forId(item);
		if (toUnlock != null) {
			player.getUnlockedItems().add(toUnlock);
			player.getPackets().sendGameMessage(
					"You unlocked " + defs.getName() + ".");
			if (player.getInterfaceManager().containsInterface(275))
				showList(player);
		}
	}

	public static ArrayList<String> getUnlockedList(Player player) {
		ArrayList<String> nameList = new ArrayList<>();
		ArrayList<UnlockableItems> unlocked = player.getUnlockedItems();
		for (UnlockableItems item : unlocked) {
			for (int id : item.getItemIds()) {
				String itemName = ItemDefinitions.getItemDefinitions(id).getName();
				nameList.add("[".concat(itemName).concat("]").concat(" - ").concat(Integer.toString(id)));
			}

		}
		return nameList;
	}

	public static void showList(Player player) {
		if (!player.getInterfaceManager().containsInterface(275)) {
			player.getPackets().sendGameMessage("You currently have " + player.getPKP() + " pk points.");
			player.getPackets().sendGameMessage("To unlock an item, type ::unlockitem itemId");
		}
		player.getInterfaceManager().sendInterface(275);
		int number = 0;
		for (int i = 0; i < 100; i++) {
			player.getPackets().sendIComponentText(275, i, "");
		}
		for (UnlockableItems items : UnlockableItems.values()) {
			if (items == null)
				continue;
			number++;
			int firstItem = items.getItemIds()[0];
			String itemName = ItemDefinitions.getItemDefinitions(firstItem).getName();
			String text = (hasUnlockedItem(player, firstItem) ? "<col=009933>You have unlocked " + itemName
					: "<col=660066>" + itemName + " - costs (" + items.getPrice() + ") pk points");
			player.getPackets().sendIComponentText(275, (13 + number), text);
		}
		player.getPackets().sendIComponentText(275, 1, "Pk Points: " + player.getPKP());
		player.getPackets().sendIComponentText(275, 10, " ");
		player.getPackets().sendIComponentText(275, 11, "Items Unlocks");
		player.getPackets().sendIComponentText(275, 12, " ");
	}

}
