package com.rs.game.npc.familiar;

import java.io.Serializable;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Player;
import com.rs.game.player.content.ItemConstants;
import com.rs.utils.EconomyPrices;

public class BeastOfBurden implements Serializable {

	private static final int ITEMS_KEY = 530;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2090871604834210257L;

	private transient Player player;
	private transient Familiar familiar;

	public ItemsContainer<Item> beastItems;

	public BeastOfBurden(int size) {
		beastItems = new ItemsContainer<Item>(size, false);
	}

	public void setEntitys(Player player, Familiar familiar) {
		this.player = player;
		this.familiar = familiar;
	}

	public void open() {
		player.getInterfaceManager().sendInterface(671);
		player.getInterfaceManager().sendInventoryInterface(665);
		sendInterItems();
		sendOptions();
	}

	public void dropBob() {
		int size = familiar.getSize();
		WorldTile WorldTile = new WorldTile(familiar.getCoordFaceX(size), familiar.getCoordFaceY(size),
				familiar.getPlane());
		for (int i = 0; i < beastItems.getSize(); i++) {
			Item item = beastItems.get(i);
			if (item != null)
				World.addGroundItem(item, WorldTile, player, false, -1, 0);
		}
		beastItems.reset();
	}

	public void takeBob() {
		Item[] itemsBefore = beastItems.getItemsCopy();
		for (int i = 0; i < beastItems.getSize(); i++) {
			Item item = beastItems.get(i);
			if (item != null) {
				if (!player.getInventory().addItemFromBob(item.getId(), item.getAmount()))
					break;
				beastItems.remove(i, item);
			}
		}
		player.animate(new Animation(827));
		beastItems.shift();
		refreshItems(itemsBefore);
	}

	public void removeItem(int slot, int amount) {
		Item item = beastItems.get(slot);
		if (item == null)
			return;
		Item[] itemsBefore = beastItems.getItemsCopy();
		int maxAmount = beastItems.getNumberOf(item);
		if (amount < maxAmount)
			item = new Item(item.getId(), amount);
		else
			item = new Item(item.getId(), maxAmount);
		player.animate(new Animation(827));
		int freeSpace = player.getInventory().getFreeSlots();
		if (!item.getDefinitions().isStackable()) {
			if (freeSpace == 0) {
				player.getPackets().sendGameMessage("Not enough space in your inventory.");
				return;
			}
			if (freeSpace < item.getAmount()) {
				item.setAmount(freeSpace);
				player.getPackets().sendGameMessage("Not enough space in your inventory.");
			}
		} else {
			if (freeSpace == 0 && !player.getInventory().containsItem(item.getId(), 1)) {
				player.getPackets().sendGameMessage("Not enough space in your inventory.");
				return;
			}
		}
		beastItems.remove(slot, item);
		beastItems.shift();
		player.getInventory().addItemFromBob(item.getId(), item.getAmount());
		refreshItems(itemsBefore);
	}

	public void addItem(int slot, int amount) {
		Item item = player.getInventory().getItem(slot);
		if (item == null)
			return;
		if (EconomyPrices.getPrice(item.getId()) > 50000) {
			player.getPackets().sendGameMessage("You can't store items with the value of 50,000 or higher.");
			return;
		}
		if (!ItemConstants.isTradeable(item)) {
			player.getPackets().sendGameMessage("You can't store untradeable items.");
			return;
		}
		if (item.getDefinitions().isStackable() || item.getDefinitions().isNoted()) {
			player.getPackets().sendGameMessage("You can't store stackable or noted items.");
			return;
		}
		if (item.getId() == 4049) {
			player.getPackets().sendGameMessage(
					"You can't store " + ItemDefinitions.getItemDefinitions(item.getId()).getName() + ".");
			return;
		}
		if ((!familiar.canStoreEssOnly() && (item.getId() == 1436 || item.getId() == 7936))
				|| (familiar.canStoreEssOnly() && item.getId() != 1436 && item.getId() != 7936)) {
			player.getPackets().sendGameMessage(
					"You can only store " + ItemDefinitions.getItemDefinitions(item.getId()).getName() + " in Abyssal familiars.");
			return;
			
		}
		Item[] itemsBefore = beastItems.getItemsCopy();
		int maxAmount = player.getInventory().getItems().getNumberOf(item);
		if (amount < maxAmount)
			item = new Item(item.getId(), amount);
		else
			item = new Item(item.getId(), maxAmount);
		player.animate(new Animation(827));
		int freeSpace = beastItems.getFreeSlots();
		if (!item.getDefinitions().isStackable()) {
			if (freeSpace == 0) {
				player.getPackets().sendGameMessage("Not enough space in your Familiar Inventory.");
				return;
			}

			if (freeSpace < item.getAmount()) {
				item.setAmount(freeSpace);
				player.getPackets().sendGameMessage("Not enough space in your Familiar Inventory.");
			}
		} else {
			if (freeSpace == 0 && !beastItems.containsOne(item)) {
				player.getPackets().sendGameMessage("Not enough space in your Familiar Inventory.");
				return;
			}
		}
		beastItems.add(item);
		beastItems.shift();
		player.getInventory().deleteItem(slot, item);
		refreshItems(itemsBefore);
	}

	public void addItem(Item item) {
		if (item == null)
			return;
		if (EconomyPrices.getPrice(item.getId()) > 50000) {
			player.getPackets().sendGameMessage("You can't store items with the value of 50,000 or higher.");
			return;
		}
		if (!ItemConstants.isTradeable(item)) {
			player.getPackets().sendGameMessage("You can't store untradeable items.");
			return;
		}
		if (item.getDefinitions().isStackable() || item.getDefinitions().isNoted()) {
			player.getPackets().sendGameMessage("You can't store stackable or noted items.");
			return;
		}
		if (item.getId() == 4049) {
			player.getPackets().sendGameMessage(
					"You can't store " + ItemDefinitions.getItemDefinitions(item.getId()).getName() + ".");
			return;
		}
		if ((!familiar.canStoreEssOnly() && (item.getId() == 1436 || item.getId() == 7936))
				|| (familiar.canStoreEssOnly() && item.getId() != 1436 && item.getId() != 7936)) {
			player.getPackets().sendGameMessage(
					"You can only store " + ItemDefinitions.getItemDefinitions(item.getId()).getName() + " in Abyssal familiars.");
			return;
			
		}
		Item[] itemsBefore = beastItems.getItemsCopy();
		item = new Item(item.getId(), 1);
		int freeSpace = beastItems.getFreeSlots();
		if (!item.getDefinitions().isStackable()) {
			if (freeSpace == 0) {
				player.getPackets().sendGameMessage("Not enough space in your Familiar Inventory.");
				return;
			}

			if (freeSpace < item.getAmount()) {
				item.setAmount(freeSpace);
				player.getPackets().sendGameMessage("Not enough space in your Familiar Inventory.");
			}
		} else {
			if (freeSpace == 0 && !beastItems.containsOne(item)) {
				player.getPackets().sendGameMessage("Not enough space in your Familiar Inventory.");
				return;
			}
		}
		beastItems.add(item);
		beastItems.shift();
		player.getInventory().deleteItem(item);
		refreshItems(itemsBefore);
		player.animate(new Animation(827));
	}

	public void refreshItems(Item[] itemsBefore) {
		int[] changedSlots = new int[itemsBefore.length];
		int count = 0;
		for (int index = 0; index < itemsBefore.length; index++) {
			Item item = beastItems.getContainerItems()[index];
			if (itemsBefore[index] != item) {
				changedSlots[count++] = index;
			}

		}
		int[] finalChangedSlots = new int[count];
		System.arraycopy(changedSlots, 0, finalChangedSlots, 0, count);
		refresh(finalChangedSlots);
	}

	public void refresh(int... slots) {
		player.getPackets().sendUpdateItems(ITEMS_KEY, beastItems, slots);
	}

	public void sendOptions() {
		player.getPackets().sendUnlockIComponentOptionSlots(665, 0, 0, 27, 0, 1, 2, 3, 4, 5);
		player.getPackets().sendInterSetItemsOptionsScript(665, 0, 93, 4, 7, "Store", "Store-5", "Store-10",
				"Store-All", "Store-X", "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(671, 27, 0, ITEMS_KEY, 0, 1, 2, 3, 4, 5);
		player.getPackets().sendInterSetItemsOptionsScript(671, 27, ITEMS_KEY, 6, 5, "Withdraw", "Withdraw-5",
				"Withdraw-10", "Withdraw-All", "Withdraw-X", "Examine");
	}

	public boolean containsOneItem(int... itemIds) {
		for (int itemId : itemIds) {
			if (beastItems.containsOne(new Item(itemId, 1)))
				return true;
		}
		return false;
	}

	public void sendInterItems() {
		player.getPackets().sendItems(ITEMS_KEY, beastItems);
		player.getPackets().sendItems(93, player.getInventory().getItems());
	}

	public ItemsContainer<Item> getBeastItems() {
		return beastItems;
	}
}
