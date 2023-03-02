package com.rs.game.player;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;

public class PkPointsManager {

	private transient Player player;
	private ItemsContainer<Item> pkpItems;

	public enum PkPointStore {

		ARMDAYL_GODSWORD(11694, 150),

		BANDOS_GODSWORD(11696, 100),

		SARADOMIN_GODSWORD(11698, 100),

		ZAMORAK_GODSWORD(11700, 100),

		DRAGON_CLAWS(14484, 200),

		FIRE_CAPE(6570, 15),

		TOKHAAR_KAL(23659, 75),

		RUNE_DEFENDER(8850, 10),

		DRAGON_DEFENDER(20072, 20),

		VESTA_LONGSWORD(13899, 100),

		STATIUS_WARHAMMER(13902, 90);

		int itemId;
		int price;

		private PkPointStore(int itemId, int price) {
			this.itemId = itemId;
			this.price = price;
		}
	}

	public PkPointsManager(Player player) {
		this.player = player;
		pkpItems = new ItemsContainer<Item>(28, false);
	}

	public void openPKPointShop() {
		pkpItems.clear();
		player.getPackets().sendIComponentText(3007, 14, "Pk Point Store");
		player.getInterfaceManager().sendInterface(3007);
		player.getInterfaceManager().sendInventoryInterface(207);
		sendInterItems();
		sendOptions();
		player.getPackets().sendGlobalConfig(728, 0);
		player.getTemporaryAttributtes().put("PkPointsStore", Boolean.TRUE);
		for (PkPointStore items : PkPointStore.values()) {
			if (items != null) {
				addItem(items);
			}
		}
		player.setCloseInterfacesEvent(new Runnable() {
			@Override
			public void run() {
				pkpItems.clear();
				player.getTemporaryAttributtes().remove("PkPointsStore");
			}
		});
	}

	public int getSlotId(int clickSlotId) {
		return clickSlotId / 1;
	}

	public void sendInfo(int slotId, boolean sell) {
		int slot = getSlotId(slotId);
		Item item = sell ? player.getInventory().getItem(slot) : pkpItems.get(slot);
		if (item == null)
			return;
		item = new Item(item.getId());
		boolean sellable = false;
		for (PkPointStore store : PkPointStore.values()) {
			if (store != null) {
				int sellPrice = (int) (store.price * 0.6);
				if (store.itemId == item.getId()) {
					sellable = true;
					if (!sell)
						player.getPackets().sendGameMessage(ItemDefinitions.getItemDefinitions(item.getId()).getName()
								+ " costs " + store.price + " pk points.");
					else
						player.getPackets()
								.sendGameMessage("You can sell back "
										+ ItemDefinitions.getItemDefinitions(item.getId()).getName() + " for "
										+ sellPrice + " pk points.");

				}
			}
		}
		if (!sellable) {
			player.getPackets().sendGameMessage(
					"You can't sell " + ItemDefinitions.getItemDefinitions(item.getId()).getName() + " to this store.");
			return;
		}
	}

	public void sellItem(int slotId, int x) {
		int slot = getSlotId(slotId);
		int amount = x;
		Item item = player.getInventory().getItem(slot);
		if (item == null)
			return;
		item = new Item(item.getId());
		if (amount > player.getInventory().getNumberOf(item.getId()))
			amount = player.getInventory().getNumberOf(item.getId());
		boolean sellable = false;
		for (PkPointStore store : PkPointStore.values()) {
			if (store != null) {
				int price = (int) (store.price * 0.6);
				if (store.itemId == item.getId()) {
					sellable = true;
					player.setPKP(player.getPKP() + (price * amount));
					if (amount > 1)
						player.getInventory().deleteItem(item.getId(), amount);
					else
						player.getInventory().deleteItem(slot, item);
					player.getPackets()
							.sendGameMessage("You sold " + (amount > 1 ? amount + " x " : "") + " "
									+ ItemDefinitions.getItemDefinitions(item.getId()).getName() + " for "
									+ (price * amount) + " pk points.");
					refresh();
				}
			}
		}
		if (!sellable) {
			player.getPackets().sendGameMessage(
					"You can't sell " + ItemDefinitions.getItemDefinitions(item.getId()).getName() + " to this store.");
			return;
		}
	}

	public void buyItem(int slotId) {
		int slot = getSlotId(slotId);
		Item item = pkpItems.get(slot);
		if (item == null)
			return;
		item = new Item(item.getId());
		for (PkPointStore store : PkPointStore.values()) {
			if (store != null) {
				if (store.itemId == item.getId()) {
					if (player.getPKP() >= store.price) {
						if (!player.getInventory().hasFreeSlots()) {
							player.getPackets()
									.sendGameMessage("You don't have enough inventory space to purchase this.");
							return;
						}
						player.setPKP(player.getPKP() - store.price);
						player.getInventory().addItem(item.getId(), 1);
						player.getPackets().sendGameMessage(
								"You purchased " + ItemDefinitions.getItemDefinitions(item.getId()).getName() + " for "
										+ store.price + " pk points.");
						refresh();
					} else {
						player.getPackets().sendGameMessage("You don't have enough pk points.");
						return;
					}
				}
			}
		}
	}

	public void sendExamine(int slotId) {
		int slot = getSlotId(slotId);
		Item item = pkpItems.get(slot);
		if (item == null)
			return;
		item = new Item(item.getId());
		for (PkPointStore store : PkPointStore.values()) {
			if (store != null) {
				if (store.itemId == item.getId()) {
					player.getPackets().sendGameMessage(ItemDefinitions.getItemDefinitions(item.getId()).getExamine());
				}
			}
		}
	}

	public void addItem(PkPointStore item) {
		Item items = new Item(item.itemId);
		Item[] itemsBefore = pkpItems.getItemsCopy();
		items = new Item(items.getId());
		pkpItems.add(items);
		refreshItems(itemsBefore);
	}

	public void refreshItems(Item[] itemsBefore) {
		int[] changedSlots = new int[itemsBefore.length];
		int count = 0;
		for (int index = 0; index < itemsBefore.length; index++) {
			Item item = pkpItems.getContainerItems()[index];
			if (itemsBefore[index] != item) {
				changedSlots[count++] = index;
				for (PkPointStore store : PkPointStore.values()) {
					if (store != null) {
						if (store.itemId == item.getId())
							player.getPackets().sendGlobalConfig(700 + index, item == null ? 0 : store.price);
					}
				}
			}

		}
		int[] finalChangedSlots = new int[count];
		System.arraycopy(changedSlots, 0, finalChangedSlots, 0, count);
		refresh();
		// player.getPackets().sendGlobalConfig(728, player.getPKP());
	}

	public void refresh() {
		for (int i = 0; i < pkpItems.getSize(); i++)
			player.getPackets().sendUpdateItems(90, pkpItems, i);
		player.message("Total Pk Points:<br>" + player.getPKP());
	}

	public void sendOptions() {
		player.getPackets().sendUnlockIComponentOptionSlots(3007, 15, 0, 54, 0, 1, 2);
		player.getPackets().sendInterSetItemsOptionsScript(3007, 15, 90, 7, 5, "Info", "Buy", "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(207, 0, 0, 27, 0, 1, 2, 3, 4);
		player.getPackets().sendInterSetItemsOptionsScript(207, 0, 93, 5, 7, "Info", "Sell 1", "Sell 5", "Sell 10",
				"Examine");
	}

	public void sendInterItems() {
		player.getPackets().sendItems(90, pkpItems);// its the container, container id should be in this
	}

}
