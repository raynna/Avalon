package com.rs.game.player.content.pos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PoSManager implements Serializable {

	/*protected static int INTERFACE_ID = 3010;
	protected static final int CURRENCY_SPRITE = 1371;

	private static final long serialVersionUID = -866326987352331696L;

	private transient Player player;

	private long[] offerUIds;

	public PoSManager() {
		offerUIds = new long[6];
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void init() {
		PlayerOwnedShops.linkOffers(player);
	}

	public void stop() {
	}

	public long[] getOfferUIds() {
		return offerUIds;
	}

	public boolean isSlotFree(int slot) {
		return offerUIds[slot] == 0;
	}

	public int getItemAmount(Item item) {
		int notedId = item.getDefinitions().certId;
		return player.getInventory().getAmountOf(item.getId()) + player.getInventory().getAmountOf(notedId);
	}

	public static Player getStore(Player player) {
		Player store = (Player) player.getTemporaryAttributtes().get("POS_STORE");
		if (player.getTemporaryAttributtes().get("POS_STORE") == null)
			return null;
		return store;
	}

	public static int[] COMPONENTS = { 25, 31, 37, 43, 49, 55, 61, 67, 73, 79, 85, 91, 97, 103, 109, 115, 121, 127, 133,
			139, 145, 151, 157, 163, 169, 175, 181, 187, 193, 199, 205, 211, 217, 223, 229, 235, 241, 247, 253, 259,
			265, 271, 277, 283, 289, 295, 301, 307, 313, 319, 325, 331, 337, 343, 349, 355, 361, 367, 373, 379, 385,
			391, 397, 403, 409, 415, 421, 427, 433, 439, 445, 451, 457, 463, 469, 475, 481, 487 };

	public void openPoS(Player store) {
		player.getInterfaceManager().closeScreenInterface();
		player.getTemporaryAttributtes().put("POS_STORE", store);
		for (int i = 25; i <= COMPONENTS[COMPONENTS.length - 1]; i += 6)
			player.getPackets().sendHideIComponent(INTERFACE_ID, i, true);
		refreshInterface();
		player.getInterfaceManager().sendInterface(INTERFACE_ID);
		player.setCloseInterfacesEvent(new Runnable() {

			@Override
			public void run() {
				player.getTemporaryAttributtes().remove("POS_STORE");
			}
		});
	}

	private static ArrayList<Item> itemsMap = new ArrayList<>();// items
	private static HashMap<Integer, Integer> offersMap = new HashMap<>();// slotId, price

	public List<Item> getItems(Player store) {
		itemsMap.clear();
		offersMap.clear();
		for (PoSOffer offer : PlayerOwnedShops.OFFERS.values()) {
			if (offer == null)
				continue;
			if (offer.getOwner() == store) {
				offersMap.put(offer.getSlot(), offer.getPrice());
				itemsMap.add(new Item(offer.getId(), offer.getAmount()));
			}
		}
		return itemsMap;
	}

	private int getPrice(int container) {
		int price = -1;
		for (Map.Entry<Integer, Integer> entry : offersMap.entrySet()) {
			System.out.println("Entry: " + entry.getKey() + " : container: " + container);
			if (container == entry.getKey())
				price = entry.getValue();
		}
		return price;
	}

	public int getSlot(int itemId) {
		int index = 0;
		for (Item items : itemsMap) {
			if (items == null)
				continue;
			if (items.getId() == itemId) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public void sendOptions(int i) {
		player.getPackets().sendInterSetItemsOptionsScript(INTERFACE_ID, COMPONENTS[i] + 2, i, 1, 1, "Info", "Buy 1",
				"Buy 10", "Buy 100", "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(INTERFACE_ID, COMPONENTS[i] + 2, 0, 160, 0, 1, 2, 3, 4);
	}

	private void refreshInterface() {
		Player store = getStore(player);
		for (int i = 0; i < COMPONENTS.length; i++) {
			if (getItems(store).size() <= i)
				continue;
			sendOptions(i);
			sendItemAndInfo(i);
		}
		player.getPackets().sendIComponentText(INTERFACE_ID, 16,
				store.getDisplayName() + (store.getDisplayName().endsWith("s") ? "'" : "'s") + " shop");
		player.getPackets().sendHideIComponent(INTERFACE_ID, 17, true);
	}

	private String formatPrice(int price) {
		if (price >= 10000000)
			return price / 1000000 + "m";
		if (price >= 1000)
			return price / 1000 + "k";
		return "" + price;
	}

	public void sendItemAndInfo(int container) {
		Player store = getStore(player);
		int totalCurrency = player.getTotalCoins();
		if (container >= getItems(store).size())
			return;
		List<Item> items = getItems(store);
		int price = getPrice(container);
		player.getPackets().sendHideIComponent(INTERFACE_ID, COMPONENTS[container], false);
		player.getPackets().sendItems(container,
				new Item[] { new Item(items.get(container).getId(), items.get(container).getAmount()) });
		player.getPackets().sendIComponentText(INTERFACE_ID, COMPONENTS[container] + 4,
				totalCurrency >= price ? HexColours.getMessage(Colours.YELLOW, formatPrice(price))
						: HexColours.getMessage(Colours.RED, formatPrice(price)));
		player.getPackets().sendSpriteOnIComponent(INTERFACE_ID, COMPONENTS[container] + 5, CURRENCY_SPRITE);
		player.getPackets().sendHideIComponent(INTERFACE_ID, COMPONENTS[container] + 3, true);
		return;
	}

	public void sendInfo(int itemId) {
		int price = -1;
		for (Item item : getItems(player))
			if (itemId == item.getId()) {
				price = getPrice(getSlot(itemId));
				System.out.println("Slot: " + getSlot(itemId) + ", price: " + price);
				player.getPackets()
						.sendGameMessage(ItemDefinitions.getItemDefinitions(itemId).getName() + (price == 0
								? " is " + HexColours.getShortMessage(Colours.GREEN, "free!")
								: (" costs "
										+ HexColours.getShortMessage(Colours.RED, Utils.getFormattedNumber(price, ','))
										+ " coins.")));
			}
	}

	public void sendBuy(int itemId, int amount) {
		for (PoSOffer offer : PlayerOwnedShops.OFFERS.values()) {
			if (offer.getOwner() == getStore(player)) {
				if (offer.getId() == itemId) {
					offer.setAmount(offer.getAmount() - amount);
					if (offer.getAmount() == 0) {
						player.sm("You bought out " + offer.getOwner().getDisplayName() + " " + offer.getName() + ".");
						offer.unlink();
					}
				}
			}
		}
		openPoS(getStore(player));
	}*/
}
