package com.rs.game.player.content;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.ItemDefinitions.FileUtilities;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.ItemExamines;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

/**
 * 
 * @Improved Andreas - AvalonPK
 * 
 */

public class Shop {

	private static int MAIN_STOCK_ITEMS_KEY = 3;

	private static final int MAX_SHOP_ITEMS = 40;
	public static final int RUSTY_COINS = 18201;
	public static final int COINS = 995;

	private String name;
	private Item[] mainStock;
	private int[] defaultQuantity;
	private Item[] generalStock;
	private int money;
	private int amount;
	private int shopId;

	// private CopyOnWriteArrayList<Player> viewingPlayers;

	private transient final Map<Integer, List<Player>> viewingPlayers = new HashMap<>();

	public Shop(String name, int money, Item[] mainStock, boolean isGeneralStore) {
		this.name = name;
		this.money = money;
		this.mainStock = mainStock;
		defaultQuantity = new int[mainStock.length];
		for (int i = 0; i < defaultQuantity.length; i++)
			defaultQuantity[i] = mainStock[i].getAmount();
		if (isGeneralStore && mainStock.length < MAX_SHOP_ITEMS)
			generalStock = new Item[MAX_SHOP_ITEMS - mainStock.length];
	}

	public boolean isGeneralStore() {
		return generalStock != null;
	}

	public void addPlayer(final Player player, int shopId) {
		this.shopId = shopId;
		MAIN_STOCK_ITEMS_KEY = isGeneralStore() ? 3 : 0;
		viewingPlayers.computeIfAbsent(shopId, id -> new CopyOnWriteArrayList<>());
		List<Player> players = viewingPlayers.get(shopId);
		players.add(player);
		player.temporaryAttribute().put("Shop", this);
		player.setCloseInterfacesEvent(new Runnable() {
			@Override
			public void run() {
				players.remove(player);
				player.temporaryAttribute().remove("Shop");
				player.temporaryAttribute().remove("shop_buying");
				player.temporaryAttribute().remove("amount_shop");
			}
		});
		player.getPackets().sendConfig(118, MAIN_STOCK_ITEMS_KEY);
		player.getPackets().sendConfig(1496, 51);
		player.getPackets().sendConfig(532, money);
		player.getPackets().sendConfig(2565, 0);
		player.getPackets().sendConfig(2563, 0);
		sendStore(player);
		player.getPackets().sendGlobalConfig(199, -1);
		player.getInterfaceManager().sendInterface(1265);
		for (int i = 0; i < MAX_SHOP_ITEMS; i++)
			player.getPackets().sendGlobalConfig(946 + i,
					i < defaultQuantity.length ? defaultQuantity[i] : generalStock != null ? 0 : -1);// prices
		player.getPackets().sendGlobalConfig(1241, 16750848);
		player.getPackets().sendGlobalConfig(1242, 15439903);
		player.getPackets().sendGlobalConfig(741, -1);
		player.getPackets().sendGlobalConfig(743, -1);
		player.getPackets().sendGlobalConfig(744, 1);
		if (generalStock != null) {
			player.getPackets().sendHideIComponent(1265, 19, false);
		}
		player.getPackets().sendIComponentSettings(1265, 20, 0, getStoreSize() * 6, 1150);
		player.getPackets().sendIComponentSettings(1265, 26, 0, getStoreSize() * 6, 82903066);
		sendInventory(player);
		player.getPackets().sendIComponentText(1265, 85, name);
		player.getTemporaryAttributes().put("shop_buying", Boolean.TRUE);
		player.temporaryAttribute().put("amount_shop", 1);
	}

	public void sendInventory(Player player) {
		player.getInterfaceManager().sendInventoryInterface(1266);
		player.getPackets().sendItems(93, player.getInventory().getItems());
		player.getPackets().sendUnlockIComponentOptionSlots(1266, 0, 0, 27, 0, 1, 2, 3, 4, 5);
		player.getPackets().sendInterSetItemsOptionsScript(1266, 0, 93, 4, 7, "Value", "Sell 1", "Sell 5", "Sell 10",
				"Sell 50", "Examine");
	}

	// cid 67
	public void buy(Player player, int clickSlot, int quantity) {
		if (clickSlot >= getStoreSize())
			return;
		Item item = clickSlot >= mainStock.length ? generalStock[clickSlot - mainStock.length] : mainStock[clickSlot];
		if (item == null)
			return;
		if (quantity == 0) {
			player.getPackets().sendGameMessage("You must choose a quantity first.");
			return;
		}
		int price = getBuyPrice(item);
		int inventoryMoney = player.getInventory().getNumberOf(995);
		int moneyPouch = player.getMoneyPouch().getTotal();
		int totalMoney = inventoryMoney + moneyPouch;
		int totalCost = 0;
		int buyMethod = -1;
		int leftOver = 0;
		int totalBought = 0;
		boolean cantBuyAll = false;
		boolean outOfStock = false;
		boolean noSpace = false;
		if (quantity > item.getAmount()) {
			quantity = item.getAmount();
		}
		if (item.getAmount() == 0) {
			player.getPackets().sendGameMessage(item.getName() + " is out of stock.");
			return;
		}
		for (int i = 0; i < quantity; i++) {
			if (!player.getInventory().hasFreeSlots() && !item.getDefinitions().isStackable()
					&& player.getInventory().containsOneItem(item.getId())) {
				noSpace = true;
				continue;
			}
			if (item.getAmount() == 0) {
				outOfStock = true;
				continue;
			}
			if ((totalCost > 0 ? moneyPouch - totalCost : moneyPouch) >= price) {
				totalCost += price;
				buyMethod = 0;
				totalBought++;
				item.setAmount(item.getAmount() - 1);
				if (item.getAmount() <= 0 && clickSlot >= mainStock.length)
					generalStock[clickSlot - mainStock.length] = null;
			} else if ((totalCost > 0 ? inventoryMoney - totalCost : inventoryMoney) >= price) {
				totalCost += price;
				buyMethod = 1;//inventory only
				totalBought++;
				item.setAmount(item.getAmount() - 1);
				if (item.getAmount() <= 0 && clickSlot >= mainStock.length)
					generalStock[clickSlot - mainStock.length] = null;
			} else if ((totalCost > 0 ? totalMoney - totalCost : totalMoney) >= price) {
				totalCost += price;
				leftOver = totalCost - moneyPouch;
				buyMethod = 2;//inventory && money pouch combined
				totalBought++;
				item.setAmount(item.getAmount() - 1);
				if (item.getAmount() <= 0 && clickSlot >= mainStock.length)
					generalStock[clickSlot - mainStock.length] = null;
			} else {
				cantBuyAll = true;
			}
		}
		if (buyMethod == 0) {
			player.getPackets().sendRunScript(5561, 0, totalCost);
			player.getMoneyPouch().takeMoneyFromPouch(totalCost);
		} else if (buyMethod == 1) {
			player.getInventory().deleteItem(995, totalCost);
		} else if (buyMethod == 2) {
			player.getPackets().sendRunScript(5561, 0, moneyPouch);
			player.getMoneyPouch().takeMoneyFromPouch(moneyPouch);
			player.getInventory().deleteItem(995, leftOver);
		}
		if (outOfStock) {
			player.getPackets().sendGameMessage(item.getName() + " is out of stock.");
		}
		refreshShop();
		sendInventory(player);
		player.getInventory().addItem(item.getId(), totalBought);
		if (cantBuyAll) {
			player.getPackets().sendGameMessage("You don't have enough coins to buy " + item.getName() + ".");
		}
		if (noSpace) {
			player.getPackets().sendGameMessage("You don't have enough inventory space.");
		}
	}

	public void restoreItems() {
		boolean needRefresh = false;
		for (int i = 0; i < mainStock.length; i++) {
			if (mainStock[i].getAmount() < defaultQuantity[i]) {
				mainStock[i].setAmount(mainStock[i].getAmount() + 1);
				needRefresh = true;
			}
		}
		if (needRefresh)
			refreshShop();
	}

	public void degradeItems() {
		boolean needRefresh = false;
		for (int i = 0; i < mainStock.length; i++) {
			if (mainStock[i].getAmount() > defaultQuantity[i]) {
				mainStock[i].setAmount(mainStock[i].getAmount() - 1);
				needRefresh = true;
			}
		}
		if (generalStock != null) {
			for (int i = 0; i < generalStock.length; i++) {
				Item item = generalStock[i];
				if (item == null)
					continue;
				item.setAmount(item.getAmount() - 1);
				if (item.getAmount() <= 0)
					generalStock[i] = null;
				needRefresh = true;
			}
		}
		if (needRefresh)
			refreshShop();
	}

	private boolean addItem(Player player, int itemId, int quantity) {
		if (mainStock.length >= MAX_SHOP_ITEMS) {
			return false;
		}
		for (Item item : mainStock) {
			if (item.getId() == itemId) {
				item.setAmount(item.getAmount() + quantity);
				refreshShop();
				return true;
			}
		}
		if (generalStock != null) {
			for (Item item : generalStock) {
				if (item == null)
					continue;
				if (item.getId() == itemId) {
					item.setAmount(item.getAmount() + quantity);
					refreshShop();
					return true;
				}
			}
			for (int i = 0; i < generalStock.length; i++) {
				if (generalStock[i] == null) {
					generalStock[i] = new Item(itemId, quantity);
					ShopsHandler.openShop(player, shopId);
					refreshShop();
					return true;
				}
			}
		}
		return false;
	}

	public void sell(Player player, int slotId, int quantity) {
		if (player.getInventory().getItemsContainerSize() < slotId)
			return;
		Item item = player.getInventory().getItem(slotId);
		if (item == null)
			return;
		if (quantity == 0) {
			player.getPackets().sendGameMessage("You must choose a quantity first.");
			return;
		}
		int originalId = item.getId();
		if (item.getDefinitions().isNoted())
			item = new Item(item.getDefinitions().getCertId(), item.getAmount());
		if (!ItemConstants.isTradeable(item) || item.getDefinitions().isDestroyItem() || name.equalsIgnoreCase("(deg)")
				|| item.getId() == money) {
			player.getPackets().sendGameMessage("You can't sell this item.");
			return;
		}
		if (Settings.ECONOMY_MODE == 1 && item.getDefinitions().getValue() == 0) {
			player.getPackets().sendGameMessage("You can't sell free items.");
			return;
		}
		int dq = getDefaultQuantity(originalId);
		if (dq == -1 && generalStock == null) {
			player.getPackets().sendGameMessage("You can't sell " + item.getDefinitions().getName() + " to this shop.");
			return;
		}
		int numberOff = player.getInventory().getItems().getNumberOf(originalId);
		if (quantity > numberOff)
			quantity = numberOff;
		if (!addItem(player, item.getId(), quantity)) {
			player.getPackets().sendGameMessage("Shop is currently full.");
			return;
		}
		int price = getSellPrice(item);
		player.getInventory().deleteItem(originalId, quantity);
		if (price > 0)
			player.getMoneyPouch().addMoney(price * quantity, false);
		refreshShop();
	}

	public void sendValue(Player player, int slotId) {
		if (player.getInventory().getItemsContainerSize() < slotId)
			return;
		Item item = player.getInventory().getItem(slotId);
		if (item == null)
			return;
		int originalId = item.getId();
		if (item.getDefinitions().isNoted())
			item = new Item(item.getDefinitions().getCertId(), item.getAmount());
		if (!ItemConstants.isTradeable(item) || item.getDefinitions().isDestroyItem() || name.equalsIgnoreCase("(deg)")
				|| item.getId() == money) {
			player.getPackets().sendGameMessage("You can't sell this item.");
			return;
		}
		if (Settings.ECONOMY_MODE == 1 && item.getDefinitions().getValue() == 0) {
			player.getPackets().sendGameMessage("You can't sell free items.");
			return;
		}
		int dq = getDefaultQuantity(originalId);
		if (dq == -1 && !isGeneralStore()) {
			player.getPackets().sendGameMessage("You can't sell " + item.getDefinitions().getName() + " to this shop.");
			return;
		}
		int price = getSellPrice(item);
		player.getPackets().sendGameMessage(
				item.getDefinitions().getName() + ": shop will buy for: " + Utils.getFormattedNumber(price, ',') + " "
						+ ItemDefinitions.getItemDefinitions(money).getName().toLowerCase()
						+ ". Right-click the item to sell.");
	}

	public int getDefaultQuantity(int itemId) {
		for (int i = 0; i < mainStock.length; i++)
			if (mainStock[i].getId() == itemId)
				return defaultQuantity[i];
		return -1;
	}

	public void sendInfo(Player player, int slotId, boolean isBuying) {
		if (slotId >= getStoreSize())
			return;
		Item[] stock = isBuying ? mainStock : player.getInventory().getItems().getContainerItems();
		Item item = slotId >= stock.length ? generalStock[slotId - stock.length] : stock[slotId];
		if (item == null)
			return;
		int originalId = item.getId();
		int price = getBuyPrice(item);
		int dq = getDefaultQuantity(originalId);
		int sellPrice = getSellPrice(item);
		if (dq == -1 && generalStock == null) {
			player.getPackets().sendGameMessage("You can't sell " + item.getDefinitions().getName() + " to this shop.");
			return;
		}
		player.getPackets().sendIComponentText(1265, 40, ItemExamines.getExamine(item));
		player.getPackets().sendIComponentText(1265, 43, "This is " + getItemType(item) + ".");
		if (item.getDefinitions().isWearItem()) {
			player.getPackets().sendIComponentText(1265, 44, "It is " + getEquipType(item) + ".");
		}
		if (price <= 0) {
			if (isBuying)
				player.getPackets()
						.sendGameMessage(item.getDefinitions().getName() + ": shop will sell this item for free.");
			else
				player.getPackets().sendGameMessage(item.getDefinitions().getName() + " is free.");
		} else
			player.getPackets().sendGameMessage(item.getDefinitions().getName() + ": shop will "
					+ (isBuying ? "sell" : "buy") + " for "
					+ (isBuying ? Utils.getFormattedNumber(price, ',') : Utils.getFormattedNumber(sellPrice, ',')) + " "
					+ ItemDefinitions.getItemDefinitions(money).getName().toLowerCase() + ".");
	}

	public String getEquipType(Item item) {
		if (item.getDefinitions().name.contains("sword") || item.getDefinitions().name.contains("dagger")
				|| item.getDefinitions().name.contains("scimitar") || item.getDefinitions().name.contains("whip")
				|| item.getDefinitions().name.contains("spear") || item.getDefinitions().name.contains("mace")
				|| item.getDefinitions().name.contains("battleaxe") || item.getDefinitions().name.contains("staff")
				|| item.getDefinitions().name.contains("Staff") || item.getDefinitions().name.contains("battleaxe")
				|| item.getDefinitions().name.contains("hatchet") || item.getDefinitions().name.contains("pickaxe")) {
			return "wielded in the right hand";
		}
		if (item.getDefinitions().name.contains("gloves") || item.getDefinitions().name.contains("bracelet")
				|| item.getDefinitions().name.contains("vambr")) {
			return "worn on the hands";
		}
		if (item.getDefinitions().name.contains("boots")) {
			return "worn on the feet";
		}
		if (item.getDefinitions().name.contains("skirt") || item.getDefinitions().name.contains("legs")
				|| item.getDefinitions().name.contains("chaps") || item.getDefinitions().name.contains("cuisse")) {
			return "worn on the legs";
		}
		if (item.getDefinitions().name.contains("platebody") || item.getDefinitions().name.contains(" plate")
				|| item.getDefinitions().name.contains("body") || item.getDefinitions().name.contains("apron")
				|| item.getDefinitions().name.contains("chest") || item.getDefinitions().name.contains("hauberk")) {
			return "worn on the torso";
		}
		if (item.getDefinitions().name.contains("helm") || item.getDefinitions().name.contains("hat")
				|| item.getDefinitions().name.contains("coif") || item.getDefinitions().name.contains("mask")
				|| item.getDefinitions().name.contains("bandana") || item.getDefinitions().name.contains("sallet")) {
			return "worn on the head";
		}
		if (item.getDefinitions().name.contains("shield") || item.getDefinitions().name.contains("-xil")) {
			return "held in the left hand";
		}
		if (item.getDefinitions().name.contains("shield") || item.getDefinitions().name.contains("2h")
				|| item.getDefinitions().name.contains("maul") || item.getDefinitions().name.contains("claws")) {
			return "wielded in both hands";
		}
		if (item.getDefinitions().name.contains("cape") || item.getDefinitions().name.contains("Cape")) {
			return "worn on the back";
		}
		return "an item";
	}

	public String getItemType(Item item) {
		if (item.getDefinitions().name.contains("sword") || item.getDefinitions().name.contains("dagger")
				|| item.getDefinitions().name.contains("scimitar") || item.getDefinitions().name.contains("maul")
				|| item.getDefinitions().name.contains("whip") || item.getDefinitions().name.contains("claws")
				|| item.getDefinitions().name.contains("spear") || item.getDefinitions().name.contains("mace")
				|| item.getDefinitions().name.contains("battleaxe")) {
			return "a melee weapon";
		}
		if (item.getDefinitions().name.contains("Staff") || item.getDefinitions().name.contains("staff")) {
			return "a weapon for mages";
		}
		if (item.getDefinitions().name.contains("body") || item.getDefinitions().name.contains("legs")
				|| item.getDefinitions().name.contains("robe") || item.getDefinitions().name.contains("priest")
				|| item.getDefinitions().name.toLowerCase().contains("helm")
				|| item.getDefinitions().name.contains("chest") || item.getDefinitions().name.contains("chain")
				|| item.getDefinitions().name.contains("tassets") || item.getDefinitions().name.contains("boots")
				|| item.getDefinitions().name.contains("coif") || item.getDefinitions().name.contains("chaps")
				|| item.getDefinitions().name.contains("bandana") || item.getDefinitions().name.contains("skirt")
				|| item.getDefinitions().name.contains("sallet") || item.getDefinitions().name.contains("hauberk")
				|| item.getDefinitions().name.contains("cuisse") || item.getDefinitions().name.contains(" plate")) {
			return "a piece of apparel";
		}
		if (item.getDefinitions().name.toLowerCase().contains("amulet")
				|| item.getDefinitions().name.contains("necklace")) {
			return "a piece of amulet apparel";
		}
		if (item.getDefinitions().name.toLowerCase().contains("ring")) {
			return "a piece of ring apparel";
		}
		if (item.getDefinitions().name.toLowerCase().contains("gloves")
				|| item.getDefinitions().name.toLowerCase().contains("bracelet")
				|| item.getDefinitions().name.toLowerCase().contains("vambr")) {
			return "a piece of glove apparel";
		}
		if (item.getDefinitions().name.contains("shield") || item.getDefinitions().name.contains("-xil")) {
			return "a shield";
		}
		if (item.getDefinitions().name.contains("arrow") || item.getDefinitions().name.contains("bolt")
				|| item.getDefinitions().name.contains("ball") || item.getDefinitions().name.contains("shot")) {
			return "ammunition for a ranged weapon";
		}
		if (item.getDefinitions().name.contains("bow") || item.getDefinitions().name.contains("javelin")
				|| item.getDefinitions().name.contains("throwing") || item.getDefinitions().name.contains("dart")
				|| item.getDefinitions().name.contains(" knife")
				|| item.getDefinitions().name.contains("Hand cannon")) {
			return "a ranged weapon";
		}
		return "an item";
	}

	public int getBuyPrice(Item item) {
		ItemDefinitions unNoted = ItemDefinitions.getItemDefinitions(item.getId());
		ItemDefinitions Noted = ItemDefinitions.getItemDefinitions(unNoted.getCertId());
		try {
			for (String lines : FileUtilities.readFile("./prices/prices.txt")) {
				if (lines.contains("originalPrices: false")) {
					return unNoted.isNoted() ? Noted.getValue() : unNoted.getValue();
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return item.getDefinitions().getPrice();
	}

	public int getBuyPrice(Item item, int dq) {
		try {
			for (String lines : FileUtilities.readFile("./prices/prices.txt")) {
				if (lines.contains("originalPrices: false")) {
					return item.getDefinitions().getValue();
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		switch (item.getId()) {

		}
		return item.getDefinitions().getPrice();
	}

	public int getSellPrice(Item item) {
		@SuppressWarnings("unused")
		boolean storeHasItem = false;
		for (Item stock : mainStock) {
			if (stock == null)
				continue;
			if (stock.getId() == item.getId()) {
				storeHasItem = true;
			}
		}
		switch (item.getId()) {
		}
		int price = item.getDefinitions().getLowAlchPrice();
		if (item.getDefinitions().isNoted())
			item = new Item(item.getDefinitions().getCertId());
		try {
			for (String lines : FileUtilities.readFile("./prices/prices.txt")) {
				String[] data = lines.split(" - ");
				if (lines.contains("originalPrices: false")) {
					return (int) (item.getDefinitions().getValue() * 0.3);
				}
				if (Integer.parseInt(data[0]) == item.getId()) {
					return (int) (item.getDefinitions().getPrice() * 0.3);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return price <= 0 ? 1 : item.getDefinitions().getLowAlchPrice();
	}

	public void sendExamine(Player player, int slotId) {
		if (slotId >= getStoreSize())
			return;
		Item item = slotId >= mainStock.length ? generalStock[slotId - mainStock.length] : mainStock[slotId];
		if (item == null)
			return;
		player.getPackets().sendGameMessage(ItemExamines.getExamine(item));
	}

	public void refreshShop() {
		viewingPlayers.computeIfAbsent(shopId, id -> new CopyOnWriteArrayList<>());
		for (Player p : viewingPlayers.get(shopId)) {
			sendStore(p);
			p.getPackets().sendIComponentSettings(620, 25, 0, getStoreSize() * 6, 1150);
			customcs2configstring(p);
		}
	}

	public int getStoreSize() {
		return mainStock.length + (generalStock != null ? generalStock.length : 0);
	}

	public void sendStore(Player player) {
		Item[] stock = new Item[mainStock.length + (generalStock != null ? generalStock.length : 0)];
		System.arraycopy(mainStock, 0, stock, 0, mainStock.length);
		if (generalStock != null)
			System.arraycopy(generalStock, 0, stock, mainStock.length, generalStock.length);
		player.getPackets().sendItems(MAIN_STOCK_ITEMS_KEY, stock);
	}

	public void sendSellStore(Player player, Item[] inventory) {
		Item[] stock = new Item[inventory.length + (generalStock != null ? generalStock.length : 0)];
		System.arraycopy(inventory, 0, stock, 0, inventory.length);
		if (generalStock != null)
			System.arraycopy(generalStock, 0, stock, inventory.length, generalStock.length);
		player.getPackets().sendItems(MAIN_STOCK_ITEMS_KEY, stock);
	}

	/**
	 * Checks if the player is buying an item or selling it.
	 * 
	 * @param player The player
	 * @param slotId The slot id
	 * @param amount The amount
	 */
	public void handleShop(Player player, int slotId, int amount) {
		boolean isBuying = player.temporaryAttribute().get("shop_buying") != null;
		if (isBuying)
			buy(player, slotId, amount);
		else
			sell(player, slotId, amount);
	}

	public Item[] getMainStock() {
		return this.mainStock;
	}

	public int getAmount() {
		return this.amount;
	}

	public void addAmount(Player player, int amount) {
		this.amount += amount;
		player.getPackets().sendConfig(2564, this.amount);
	}

	public void removeAmount(Player player, int amount) {
		this.amount -= amount;
	}
	
	private String customcs2configstring(Player player) {
		String str = "";

		for (int i = 0; i < player.getInventory().getItemsContainerSize(); i++) {	
			Item item = player.getInventory().getItem(i);
			if (item == null) {
				String id = Integer.toString(-1, 16);
				while (id.length() < 8)
					id = "0" + id;
				String price = Integer.toString(0, 16);
				while (price.length() < 8)
					price = "0" + price;

				str += id + price;
				continue;
			}

			String id = Integer.toString(item.getId(), 16);
			while (id.length() < 8)
				id = "0" + id;
			String price = Integer.toString(getSellPrice(item), 16);
			while (price.length() < 8)
				price = "0" + price;

			str += id + price;
		}

		int written = 28;
		for (int i = 0; i < mainStock.length; i++) {
			if (written >= 100)
				break;

			Item item = mainStock[i];
			if (item == null)
				continue;

			String id = Integer.toString(item.getId(), 16);
			while (id.length() < 8)
				id = "0" + id;
			String price = Integer.toString(getBuyPrice(item), 16);
			while (price.length() < 8)
				price = "0" + price;

			str += id + price;
			written++;
		}
		if (generalStock != null) {
			for (int i = 0; i < generalStock.length; i++) {
				if (written >= 100)
					break;
				Item item = generalStock[i];
				if (item == null)
					continue;

				String id = Integer.toString(item.getId(), 16);
				while (id.length() < 8)
					id = "0" + id;
				String price = Integer.toString(item.getId(), 16);
				while (price.length() < 8)
					price = "0" + price;

				str += id + price;
				written++;
			}
		}
		return str.toUpperCase();
	}

	public void setAmount(Player player, int newAmount) {
		this.amount = newAmount;
		player.getVarsManager().sendVar(2564, getAmount());
	}
}