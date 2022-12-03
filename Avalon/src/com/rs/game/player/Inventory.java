package com.rs.game.player;

import java.io.Serializable;
import java.util.List;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.content.ItemConstants;
import com.rs.utils.EconomyPrices;
import com.rs.utils.ItemExamines;
import com.rs.utils.Utils;
import com.rs.utils.Weights;

public final class Inventory implements Serializable {

	private static final long serialVersionUID = 8842800123753277093L;

	public ItemsContainer<Item> items;

	private transient Player player;

	public static final int INVENTORY_INTERFACE = 679;

	public Inventory() {
		items = new ItemsContainer<Item>(28, false);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	

	public void init() {
		int RUNE_POUCH = 24497;
		Item[] finalised = new Item[32];
		for (int i = 0; i < 28; i++) {
			finalised[i] = items.get(i);
		}
		if (player.getInventory().containsOneItem(RUNE_POUCH)) {
			Item rune1 = player.getRunePouch().get(0);
			Item rune2 = player.getRunePouch().get(1);
			Item rune3 = player.getRunePouch().get(2);
			if (rune1 != null)
				finalised[29] = new Item(rune1.getId(),
						rune1.getAmount() - player.getInventory().getNumberOf(rune1.getId()));
			if (rune2 != null)
				finalised[30] = new Item(rune2.getId(),
						rune2.getAmount() - player.getInventory().getNumberOf(rune2.getId()));
			if (rune3 != null)
				finalised[31] = new Item(rune3.getId(),
						rune3.getAmount() - player.getInventory().getNumberOf(rune3.getId()));
		} else {
			finalised[29] = null;
			finalised[30] = null;
			finalised[31] = null;
		}
		player.getPackets().sendItems(93, finalised);
	}

	public void unlockInventoryOptions() {
		player.getPackets().sendIComponentSettings(INVENTORY_INTERFACE, 0, 0, 27, 4554126);
		player.getPackets().sendIComponentSettings(INVENTORY_INTERFACE, 0, 28, 55, 2097152);
	}

	public void reset() {
		items.reset();
		init();
	}

	public void refresh(int... slots) {
		int RUNE_POUCH = 24497;
		Item[] finalised = new Item[32];
		for (int i = 0; i < 28; i++) {
			finalised[i] = items.get(i);
		}
		if (player.getInventory().containsOneItem(RUNE_POUCH)) {
		Item rune1 = player.getRunePouch().get(0);
		Item rune2 = player.getRunePouch().get(1);
		Item rune3 = player.getRunePouch().get(2);
		if (rune1 != null)
			finalised[29] = new Item(rune1.getId(),
					rune1.getAmount() - player.getInventory().getNumberOf(rune1.getId()));
		if (rune2 != null)
			finalised[30] = new Item(rune2.getId(),
					rune2.getAmount() - player.getInventory().getNumberOf(rune2.getId()));
		if (rune3 != null)
			finalised[31] = new Item(rune3.getId(),
					rune3.getAmount() - player.getInventory().getNumberOf(rune3.getId()));
		} else {
			finalised[29] = null;
			finalised[30] = null;
			finalised[31] = null;
		}
		player.getPackets().sendUpdateItems(93, finalised, 29);
		player.getPackets().sendUpdateItems(93, finalised, 30);
		player.getPackets().sendUpdateItems(93, finalised, 31);
		player.getPackets().sendUpdateItems(93, finalised, slots);
	}

	public long getInventoryValue() {
		long value = 0;
		for (Item inventory : player.getInventory().getItems().toArray()) {
			if (inventory == null)
				continue;
			long amount = inventory.getAmount();
			value += inventory.getDefinitions().getTipitPrice() * amount;
		}
		return value;
	}

	public boolean addItemDrop(final int itemId, final int amount, final WorldTile tile) {
		if (itemId < 0 || amount < 1 || !Utils.itemExists(itemId)
				|| !player.getControlerManager().canAddInventoryItem(itemId, amount))
			return false;
		Item[] itemsBefore = items.getItemsCopy();
		if (!items.add(new Item(itemId, amount))) {
			World.updateGroundItem(new Item(itemId, amount), player, player, 60, 0);
			player.getPackets().sendGameMessage("Not enough space in your inventory.");
			refreshItems(itemsBefore);
			return false;
		}
		refreshItems(itemsBefore);
		return true;
	}

	public boolean addItemDrop(int itemId, int amount) {
		return addItemDrop(itemId, amount, new WorldTile(player));
	}

	public boolean addItemFromEquipment(final int itemId, final int amount) {
		if (itemId < 0 || amount < 0 || !Utils.itemExists(itemId)
				|| !player.getControlerManager().canAddInventoryItem(itemId, amount))
			return false;
		Item[] itemsBefore = items.getItemsCopy();
		if (!items.add(new Item(itemId, amount))) {
			items.add(new Item(itemId, items.getFreeSlots()));
			player.getPackets().sendGameMessage("Not enough space in your inventory.");
			refreshItems(itemsBefore);
			return false;
		}
		refreshItems(itemsBefore);
		return true;
	}

	public boolean addItemFromBob(final int itemId, final int amount) {
		if (itemId < 0 || amount < 0 || !Utils.itemExists(itemId)
				|| !player.getControlerManager().canAddInventoryItem(itemId, amount))
			return false;
		Item[] itemsBefore = items.getItemsCopy();
		if (!items.add(new Item(itemId, amount))) {
			items.add(new Item(itemId, items.getFreeSlots()));
			player.getPackets().sendGameMessage("Not enough space in your inventory.");
			refreshItems(itemsBefore);
			return false;
		}
		refreshItems(itemsBefore);
		return true;
	}

	public boolean addItem(final int itemId, final int amount) {
		if (itemId < 0 || amount < 1 || !Utils.itemExists(itemId)
				|| !player.getControlerManager().canAddInventoryItem(itemId, amount))
			return false;
		Item[] itemsBefore = items.getItemsCopy();
		if (!items.add(new Item(itemId, amount))) {
			items.add(new Item(itemId, items.getFreeSlots()));
			player.getPackets().sendGameMessage("Not enough space in your inventory.");
			refreshItems(itemsBefore);
			return false;
		}
		refreshItems(itemsBefore);
		return true;
	}

	public boolean containsItemToolBelt(int id) {
		return containsOneItem(id);
	}

	public boolean containsItemToolBelt(int id, int amount) {
		return containsItem(id, amount);
	}

	public boolean addItem(Item item) {
		if (item.getId() < 0 || item.getAmount() < 0 || !Utils.itemExists(item.getId())
				|| !player.getControlerManager().canAddInventoryItem(item.getId(), item.getAmount()))
			return false;
		Item[] itemsBefore = items.getItemsCopy();
		if (!items.add(item)) {
			items.add(new Item(item.getId(), items.getFreeSlots()));
			player.getPackets().sendGameMessage("Not enough space in your inventory.");
			refreshItems(itemsBefore);
			return false;
		}
		refreshItems(itemsBefore);
		return true;
	}

	public void deleteItem(int slot, Item item) {
		if (!player.getControlerManager().canDeleteInventoryItem(item.getId(), item.getAmount()))
			return;
		Item[] itemsBefore = items.getItemsCopy();
		items.remove(slot, item);
		refreshItems(itemsBefore);
	}

	public boolean removeItems(Item... list) {
		for (Item item : list) {
			if (item == null)
				continue;
			deleteItem(item);
		}
		return true;
	}

	public boolean removeItems(List<Item> list) {
		for (Item item : list) {
			if (item == null)
				continue;
			deleteItem(item);
		}
		return true;
	}

	public void deleteItem(int itemId, int amount) {
		if (!player.getControlerManager().canDeleteInventoryItem(itemId, amount))
			return;
		Item[] itemsBefore = items.getItemsCopy();
		items.remove(new Item(itemId, amount));
		refreshItems(itemsBefore);
		if (itemId == 995)
			player.getPackets().sendGameMessage((amount == 1 ? "One coin" : Utils.getFormattedNumber(amount, ',') + " coins") + " have been removed from your inventory.");
	}

	public void deleteItems(Item[] item) {
		Item[] itemsBefore = items.getItemsCopy();
		for (int index = 0; index < item.length; index++) {
			items.remove(item[index]);
		}
		refreshItems(itemsBefore);

	}

	public void deleteItem(Item item) {
		if (!player.getControlerManager().canDeleteInventoryItem(item.getId(), item.getAmount()))
			return;
		Item[] itemsBefore = items.getItemsCopy();
		items.remove(item);
		refreshItems(itemsBefore);
	}

	/*
	 * No refresh needed its client to who does it :p
	 */
	public void switchItem(int fromSlot, int toSlot) {
		Item[] itemsBefore = items.getItemsCopy();
		Item fromItem = items.get(fromSlot);
		Item toItem = items.get(toSlot);
		items.set(fromSlot, toItem);
		items.set(toSlot, fromItem);
		refreshItems(itemsBefore);
	}

	public void refreshItems(Item[] itemsBefore) {
		int[] changedSlots = new int[itemsBefore.length];
		int count = 0;
		for (int index = 0; index < itemsBefore.length; index++) {
			if (itemsBefore[index] != items.getContainerItems()[index])
				changedSlots[count++] = index;
		}
		int[] finalChangedSlots = new int[count];
		System.arraycopy(changedSlots, 0, finalChangedSlots, 0, count);
		refresh(finalChangedSlots);
		Weights.updateWeight(player);
	}

	public ItemsContainer<Item> getItems() {
		return items;
	}

	public boolean hasFreeSlots() {
		return items.getFreeSlot() != -1;
	}

	public int getFreeSlots() {
		return items.getFreeSlots();
	}

	public int getNumberOf(int itemId) {
		return items.getNumberOf(itemId);
	}

	public int getAmountOf(int itemId) {
		return items.getNumberOf(itemId);
	}

	public Item getItem(int slot) {
		return items.get(slot);
	}

	public int getItemsContainerSize() {
		return items.getSize();
	}

	public boolean containsItems(List<Item> list) {
		for (Item item : list)
			if (!items.contains(item))
				return false;
		return true;
	}

	public boolean containsItems(Item[] item) {
		for (int i = 0; i < item.length; i++)
			if (!items.contains(item[i]))
				return false;
		return true;
	}

	public boolean containsItems(int[] itemIds, int[] ammounts) {
		int size = itemIds.length > ammounts.length ? ammounts.length : itemIds.length;
		for (int i = 0; i < size; i++)
			if (!items.contains(new Item(itemIds[i], ammounts[i])))
				return false;
		return true;
	}

	public boolean containsItem(int itemId, int ammount) {
		return items.contains(new Item(itemId, ammount));
	}

	public int getCoinsAmount() {
		int coins = items.getNumberOf(995);
		return coins < 0 ? Integer.MAX_VALUE : coins;
	}

	public boolean containsOneItem(int... itemIds) {
		for (int itemId : itemIds) {
			if (items.containsOne(new Item(itemId, 1)))
				return true;
		}
		return false;
	}

	public void sendExamine(int slotId) {
		if (slotId >= getItemsContainerSize())
			return;
		Item item = items.get(slotId);
		if (item == null)
			return;
		long price;
		long totalPrice;
		long amount;
		// player.getPackets().sendGameMessage("High Alchemy Value: " +
		// item.getDefinitions().getHighAlchValue());
		Item storeItem = new Item(item.getId());
		if (storeItem.getDefinitions().isNoted())
			storeItem = new Item(storeItem.getDefinitions().getCertId());
		if (item.getDefinitions().isMembersOnly() && Settings.FREE_TO_PLAY) {
			player.getPackets().sendGameMessage("This is a members object.");
			return;
		}
		if (!ItemConstants.isTradeable(item)) {
			player.getPackets()
					.sendGameMessage("[Price Checker] " + item.getDefinitions().getName() + " is untradeable.");
			player.getPackets()
					.sendGameMessage("[Price Checker] "
							+ (player.isDeveloper() ? "ItemId: " + item.getId() + ", " : "") + "Store value: "
							+ Utils.getFormattedNumber(storeItem.getDefinitions().getPrice(), ',') + ", Alch Value: "
							+ Utils.getFormattedNumber(storeItem.getDefinitions().getHighAlchPrice(), ',') + ".");
			player.getPackets().sendGameMessage("[Description]" + ItemExamines.getExamine(item));
			return;
		}
		if (item.getId() == 995) {
			player.getPackets().sendGameMessage("[Price Checker] " + Utils.getFormattedNumber(item.getAmount(), ',')
					+ " x " + item.getDefinitions().getName() + ".");
			return;
		}
		amount = item.getAmount();
		totalPrice = EconomyPrices.getPrice(item.getId()) * amount;
		price = EconomyPrices.getPrice(item.getId());
		if (price == 1) {
			player.getPackets().sendGameMessage("[Price Checker] " + item.getDefinitions().getName() + " is free.");
			player.getPackets().sendGameMessage("[Description]" + ItemExamines.getExamine(item));
			return;
		}
		if ((item.getDefinitions().isNoted() || item.getDefinitions().isStackable()) && item.getAmount() > 1) {
			player.getPackets()
					.sendGameMessage("[Price Checker] " + Utils.getFormattedNumber(item.getAmount(), ',') + " x "
							+ item.getDefinitions().getName() + ": " + Utils.formatMillionAmount(totalPrice)
							+ " coins. (" + Utils.getFormattedNumber(EconomyPrices.getPrice(item.getId()), ',')
							+ " coins each)");
		} else {
			player.getPackets().sendGameMessage("[Price Checker] " + item.getDefinitions().getName() + ": "
					+ (Utils.getFormattedNumber(EconomyPrices.getPrice(item.getId()), ',')) + " coins.");
		}
		player.getPackets()
				.sendGameMessage("[Price Checker] " + (player.isDeveloper() ? "ItemId: " + item.getId() + ", " : "")
						+ "Store value: " + Utils.getFormattedNumber(storeItem.getDefinitions().getPrice(), ',')
						+ ", Alch Value: "
						+ Utils.getFormattedNumber(storeItem.getDefinitions().getHighAlchPrice(), ',') + ".");
		player.getPackets().sendGameMessage("[-Description-] " + ItemExamines.getExamine(item));
	}

	public void refresh() {
		int RUNE_POUCH = 24497;
		Item[] finalised = new Item[32];
		for (int i = 0; i < 28; i++) {
			finalised[i] = items.get(i);
		}
		if (player.getInventory().containsOneItem(RUNE_POUCH)) {
		Item rune1 = player.getRunePouch().get(0);
		Item rune2 = player.getRunePouch().get(1);
		Item rune3 = player.getRunePouch().get(2);
		if (rune1 != null)
			finalised[29] = new Item(rune1.getId(),
					rune1.getAmount() - player.getInventory().getNumberOf(rune1.getId()));
		if (rune2 != null)
			finalised[30] = new Item(rune2.getId(),
					rune2.getAmount() - player.getInventory().getNumberOf(rune2.getId()));
		if (rune3 != null)
			finalised[31] = new Item(rune3.getId(),
					rune3.getAmount() - player.getInventory().getNumberOf(rune3.getId()));
		
		} else {
			finalised[29] = null;
			finalised[30] = null;
			finalised[31] = null;
		}
		player.getPackets().sendItems(93, finalised);
		Weights.updateWeight(player);
	}

	public void replaceItem(int id, int amount, int slot) {
		Item item = items.get(slot);
		if (item == null)
			return;
		item.setId(id);
		item.setAmount(amount);
		refresh(slot);
	}
	
	public boolean containsItem(Item... items) {
		for (Item item : items) {
			if (item != null && !containsItem(item.getId(), item.getAmount())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean contains(Item... items) {
		for (Item item : items) {
			if (item != null && !containsItem(item.getId(), item.getAmount())) {
				return false;
			}
		}
		return true;
	}
}
