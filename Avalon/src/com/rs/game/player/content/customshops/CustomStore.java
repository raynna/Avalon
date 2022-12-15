package com.rs.game.player.content.customshops;

import java.util.HashMap;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.EconomyPrices;
import com.rs.utils.HexColours;
import com.rs.utils.HexColours.Colour;
import com.rs.utils.Utils;

public class CustomStore extends CustomStoreData {

	/**
	 * @Author -Andreas 2019-11
	 */

	public transient static HashMap<Player, Integer> viewingPlayers = new HashMap<>();

	private int shopId;
	private transient Player player;

	public CustomStore(Player player) {
		this.player = player;
	}

	public void sendInterface(int type) {
		if (type == 3) {
			SkillcapeStore.generateCapes(player);
			if (SkillcapeStore.capes.size() == 0) {
				player.sm("You don't have any level 99s therefor Max won't sell you any.");
				return;
			}
		}
		player.getInterfaceManager().closeScreenInterface();
		player.getTemporaryAttributtes().put("CUSTOM_STORE_TYPE", type);
		player.getTemporaryAttributtes().put("CUSTOM_STORE", -1);
		for (int i = 25; i <= COMPONENTS[COMPONENTS.length - 1]; i += 6)
			player.getPackets().sendHideIComponent(INTERFACE_ID, i, true);
		refreshInterface();
		player.getInterfaceManager().sendInterface(INTERFACE_ID);
		player.setCloseInterfacesEvent(new Runnable() {

			@Override
			public void run() {
				player.getTemporaryAttributtes().remove("CUSTOM_STORE_TYPE");
				player.getTemporaryAttributtes().remove("CUSTOM_STORE_TYPE");
			}
		});
	}

	private boolean isGeneralStore(int store) {
		return store == 1;

	}

	public void sendInterface(Player player, int type, int store) {
		this.shopId = store;
		viewingPlayers.put(player, shopId);
		if (type == 3) {
			SkillcapeStore.generateCapes(player);
			if (SkillcapeStore.capes.size() == 0) {
				player.sm("You don't have any level 99s therefor Max won't sell you any.");
				return;
			}
		}
		player.getInterfaceManager().closeScreenInterface();
		player.getTemporaryAttributtes().put("CUSTOM_STORE_TYPE", type);
		player.temporaryAttribute().put("customStore", this);
		for (int i = 25; i <= COMPONENTS[COMPONENTS.length - 1]; i += 6)// hides all containers
			player.getPackets().sendHideIComponent(INTERFACE_ID, i, true);
		player.getPackets().sendHideIComponent(INTERFACE_ID, 17, true);// hides total coins text
		refreshInterface();
		player.getInterfaceManager().sendInterface(INTERFACE_ID);
		player.setCloseInterfacesEvent(new Runnable() {

			@Override
			public void run() {
				viewingPlayers.remove(player);
				player.getTemporaryAttributtes().remove("customStore");
				player.getTemporaryAttributtes().remove("CUSTOM_STORE");
				player.getTemporaryAttributtes().remove("CUSTOM_STORE_TYPE");
			}
		});
	}

	public int getShopId() {
		return shopId;
	}

	private static int getShopType(Player player) {
		int shopType = (Integer) player.getTemporaryAttributtes().get("CUSTOM_STORE_TYPE");
		if (player.getTemporaryAttributtes().get("CUSTOM_STORE_TYPE") == null)
			return -1;
		return shopType;
	}

	public void sendInventory(Player player) {
		player.getInterfaceManager().sendInventoryInterface(1266);
		player.getPackets().sendItems(93, player.getInventory().getItems());
		player.getPackets().sendUnlockIComponentOptionSlots(1266, 0, 0, 27, 0, 1, 2, 3, 4, 5);
		player.getPackets().sendInterSetItemsOptionsScript(1266, 0, 93, 4, 7, "Value", "Sell 1", "Sell 5", "Sell 10",
				"Sell 50", "Examine");
	}

	private void refreshInterface() {
		for (int i = 0; i < COMPONENTS.length; i++) {
			if (getShopType(player) == SKILLCAPE) {// skill cape custom shops
				if (SkillcapeStore.capes.size() <= i)
					continue;
			} else if (getShopType(player) == COINS) {// coin shops
				if (getItems(getShopType(player), getShopId()).size() <= i)
					continue;
			} else {
				if (getShopType(getShopType(player)).length <= i) // point shops
					continue;
			}
			sendOptions(i);
			sendItemAndInfo(i);
		}
	}

	public void sendOptions(int i) {
		player.getPackets().sendInterSetItemsOptionsScript(INTERFACE_ID, COMPONENTS[i] + 2, i, 1, 1, "Info", "Buy 1",
				"Buy 10", "Buy 100", "Buy X", "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(INTERFACE_ID, COMPONENTS[i] + 2, 0, 160, 0, 1, 2, 3, 4, 5);
	}

	private String formatPrice(int price) {
		if (price >= 10000000)
			return price / 1000000 + "m";
		if (price >= 1000)
			return price / 1000 + "k";
		return "" + price;
	}

	public void sendItemAndInfo(int container) {
		Integer TYPE = getShopType(player);
		Integer SHOP = getShopId();
		int totalCurrency = -1;
		if (TYPE != null)
			totalCurrency = SHOP == PK_POINTS ? player.getPKP()
					: SHOP == AVALON_POINTS ? player.getAvalonPoints() : player.getTotalCoins();
		if (TYPE == COINS) {
			if (container >= getItems(TYPE, SHOP).size())
				return;
			int price = (SHOP == PK_POINTS || SHOP == AVALON_POINTS ? getItems(TYPE, SHOP).get(container).getAmount()
					: EconomyPrices.getPrice(getItems(TYPE, SHOP).get(container).getId()));
			player.getPackets().sendHideIComponent(INTERFACE_ID, COMPONENTS[container], false);// unhides all boxes
			player.getPackets().sendItems(container,
					new Item[] { new Item(getItems(TYPE, SHOP).get(container).getId(),
							(SHOP == PK_POINTS || SHOP == AVALON_POINTS ? 1
									: getItems(TYPE, SHOP).get(container).getAmount())) });// sends items
			player.getPackets().sendIComponentText(INTERFACE_ID, COMPONENTS[container] + 4,
					getItems(TYPE, SHOP).get(container).getAmount() == 0 ? "Out<br>of<br>stock<br><br><br><br><br>"
							: price == 0 ? HexColours.getShortMessage(Colour.GREEN, "Free")
									: totalCurrency >= price ? HexColours.getMessage(Colour.YELLOW, formatPrice(price))
											: HexColours.getMessage(Colour.RED, formatPrice(price)));// sends price
			player.getPackets().sendSpriteOnIComponent(INTERFACE_ID, COMPONENTS[container] + 5,
					getCurrencySprite(TYPE, SHOP));// sends sprite next to price
			// price
			player.getPackets().sendHideIComponent(INTERFACE_ID, COMPONENTS[container] + 3, true);// hides background of
																									// price
			if (SHOP == PK_POINTS || SHOP == AVALON_POINTS)
				player.getPackets().sendHideIComponent(INTERFACE_ID, 17, false);// unhides total currency
			// price
			player.getPackets().sendIComponentText(INTERFACE_ID, 17,
					getPointsName(TYPE, SHOP) + ": " + Utils.getFormattedNumber(totalCurrency, ','));// sends your total
			// currency
			player.getPackets().sendIComponentText(INTERFACE_ID, 16, getShopTitle(TYPE, SHOP));// sends shop title
			return;
		}
		if (TYPE == SKILLCAPE) {
			if (container >= SkillcapeStore.capes.size())
				return;
			boolean hasTwo99s = player.getSkills().hasTwo99s();
			int skillcapeId = SkillcapeStore.capes.get(container).getId();
			player.getPackets().sendHideIComponent(INTERFACE_ID, COMPONENTS[container], false);// unhides all boxes
			player.getPackets().sendItems(container,
					new Item[] { new Item(
							!hasTwo99s && skillcapeId != 20767 && skillcapeId != 19709 ? skillcapeId - 1 : skillcapeId,
							SkillcapeStore.capes.get(container).getAmount()) });// sends items
			int price = SkillcapeStore.capes.get(container).getId() == 20767 ? 2475000
					: SkillcapeStore.capes.get(container).getId() == 19709 ? 120000 : 99000;
			player.getPackets().sendIComponentText(INTERFACE_ID, COMPONENTS[container] + 4,
					player.getTotalCoins() >= price ? HexColours.getMessage(Colour.YELLOW, formatPrice(price))
							: HexColours.getMessage(Colour.RED, formatPrice(price)));// sends
			// price
			// of
			// each
			// item
			player.getPackets().sendSpriteOnIComponent(INTERFACE_ID, COMPONENTS[container] + 5,
					getCurrencySprite(TYPE, SHOP));// sends sprite next to price
			player.getPackets().sendHideIComponent(INTERFACE_ID, COMPONENTS[container] + 3, true);// hides background of
																									// price
			player.getPackets().sendIComponentText(INTERFACE_ID, 17,
					getPointsName(TYPE, SHOP) + ": " + Utils.getFormattedNumber(totalCurrency, ','));// sends your total
			// currency
			player.getPackets().sendIComponentText(INTERFACE_ID, 16, getShopTitle(TYPE, SHOP));// sends shop title
		} else {
			player.getPackets().sendHideIComponent(INTERFACE_ID, COMPONENTS[container], false);
			player.getPackets().sendItems(container,
					new Item[] { new Item(getShopType(TYPE)[container][0], getShopType(TYPE)[container][1]) });
			player.getPackets().sendHideIComponent(INTERFACE_ID, COMPONENTS[container] + 3, true);
			player.getPackets().sendIComponentText(INTERFACE_ID, COMPONENTS[container] + 4,
					formatPrice(getShopType(TYPE)[container][2]) + "");
			player.getPackets().sendSpriteOnIComponent(INTERFACE_ID, COMPONENTS[container] + 5,
					getCurrencySprite(TYPE, SHOP));
			player.getPackets().sendIComponentText(INTERFACE_ID, 17,
					getPointsName(TYPE, SHOP) + ": " + Utils.getFormattedNumber(totalCurrency, ','));
			player.getPackets().sendIComponentText(INTERFACE_ID, 16, getShopTitle(TYPE));
		}
	}

	public void sendValue(Player player, int slotId) {
		if (player.getInventory().getItemsContainerSize() < slotId)
			return;
		Item item = player.getInventory().getItem(slotId);
		if (item == null)
			return;
		int originalId = item.getId();
		if (!isGeneralStore(getShopId())) {
			player.getPackets().sendGameMessage("You can't sell " + item.getDefinitions().getName() + " to this shop.");
			return;
		}
		int price = (int) (EconomyPrices.getPrice(originalId) * 0.33);
		if (price == -1) {
			player.getPackets().sendGameMessage("You can't sell free items.");
			return;
		}
		player.getPackets().sendGameMessage(item.getDefinitions().getName() + ": shop will buy for: "
				+ Utils.getFormattedNumber(price, ',') + " "
				+ ItemDefinitions.getItemDefinitions(995).getName().toLowerCase() + ". Right-click the item to sell.");
	}

	public void sendInfo(int itemId) {
		Integer TYPE = getShopType(player);
		Integer SHOP = getShopId();
		int price = -1;
		if (TYPE == SKILLCAPE) {
			for (Item item : SkillcapeStore.capes)
				if (itemId == item.getId()) {
					price = itemId == 20767 ? 2475000 : itemId == 19709 ? 120000 : 99000;
					player.getPackets()
							.sendGameMessage(ItemDefinitions.getItemDefinitions(itemId).getName()
									+ (price == 0 ? " is " + HexColours.getShortMessage(Colour.DARK_GREEN, "free!")
											: (" costs "
													+ HexColours.getShortMessage(Colour.RED,
															Utils.getFormattedNumber(price, ','))
													+ " " + getPointsName(TYPE, SHOP) + ".")));
				}
		} else if (TYPE == COINS) {
			for (Item item : getItems(TYPE, SHOP))
				if (itemId == item.getId()) {
					price = SHOP == PK_POINTS || SHOP == AVALON_POINTS ? item.getAmount()
							: EconomyPrices.getPrice(itemId);
					player.getPackets()
							.sendGameMessage(ItemDefinitions.getItemDefinitions(itemId).getName()
									+ (price == 0 ? " is " + HexColours.getShortMessage(Colour.DARK_GREEN, "free!")
											: (" costs "
													+ HexColours.getShortMessage(Colour.RED,
															Utils.getFormattedNumber(price, ','))
													+ " " + getPointsName(TYPE, SHOP) + ".")));
				}
		} else {
			for (int i = 0; i < getShopType(TYPE).length; i++)
				if (itemId == getShopType(TYPE)[i][0]) {
					price = getShopType(TYPE)[i][2];
					player.getPackets()
							.sendGameMessage(ItemDefinitions.getItemDefinitions(itemId).getName()
									+ (price == 0 ? " is " + HexColours.getShortMessage(Colour.DARK_GREEN, "free!")
											: (" costs "
													+ HexColours.getShortMessage(Colour.RED,
															Utils.getFormattedNumber(price, ','))
													+ " " + getPointsName(TYPE, SHOP) + ".")));
				}
		}
	}

	public void sendBuy(int itemId, int amount) {
		Integer TYPE = getShopType(player);
		Integer SHOP = getShopId();
		int price = -1;
		int defaultPrice = -1;
		int points = -1;
		boolean canBuy = false;
		if (TYPE == SKILLCAPE) {
			for (Item item : SkillcapeStore.capes) {
				if (item.getId() == itemId) {
					canBuy = true;
					defaultPrice = itemId == 20767 ? 2475000 : itemId == 19709 ? 120000 : 99000;
					continue;
				}
			}
		} else if (TYPE == COINS) {
			for (Item item : getItems(TYPE, SHOP)) {
				if (item.getId() == itemId) {
					canBuy = true;
					defaultPrice = SHOP == PK_POINTS || SHOP == AVALON_POINTS ? item.getAmount()
							: EconomyPrices.getPrice(itemId);
					points = (SHOP == PK_POINTS ? player.getPKP()
							: SHOP == AVALON_POINTS ? player.getAvalonPoints() : -1);
					continue;
				}
			}
		} else {
			for (int i = 0; i < getShopType(TYPE).length; i++)
				if (itemId == getShopType(TYPE)[i][0]) {
					canBuy = true;
					defaultPrice = getShopType(TYPE)[i][2];
					points = (SHOP == PK_POINTS ? player.getPKP() : player.getAvalonPoints());
					continue;
				}
		}
		if (canBuy) {
			if (defaultPrice * amount < 0 || defaultPrice * amount > 1000000000) {
				player.sm("You can't buy that many.");
				return;
			}
			if (amount * defaultPrice > (points != -1 ? points : player.getTotalCoins())) {
				amount = (points != -1 ? points : player.getTotalCoins()) / defaultPrice;
				price = defaultPrice * amount;
			} else
				price = defaultPrice * amount;
			if (amount < 1) {
				player.getPackets()
						.sendGameMessage("You don't have enough " + getPointsName(TYPE, SHOP) + " to buy this.");
				return;
			}
			ItemDefinitions defs = ItemDefinitions.getItemDefinitions(itemId);
			if (defs.canBeNoted() && !defs.isStackable()) {
				itemId = amount > 1 ? defs.getCertId() : itemId;
				defs = ItemDefinitions.getItemDefinitions(itemId);
			}
			if (!defs.isNoted() && !defs.isStackable()) {
				if (amount > player.getInventory().getFreeSlots()) {
					amount = player.getInventory().getFreeSlots();
					price = defaultPrice * amount;
				}
			}
			defs = ItemDefinitions.getItemDefinitions(itemId);
			if (!player.getInventory().hasFreeSlots() && !defs.isStackable() || (!player.getInventory().hasFreeSlots()
					&& defs.isStackable() && !player.getInventory().containsOneItem(itemId))) {
				player.sm("You don't have enough inventory space to buy this.");
				return;
			}
			if (itemId >= 24201 && itemId <= 24203 && (player.getInventory().containsOneItem(24201, 24202, 24203)
					|| player.getBank().containsOneItem(24201, 24202, 24203))) {
				player.sm("You can only have one greater runic staff at once.");
				return;
			}
			player.addItem(itemId, amount);
			removeCurrency(player, SHOP, price);
			player.sm("You bought " + HexColours.getShortMessage(Colour.RED, Utils.getFormattedNumber(amount, ','))
					+ " x " + defs.getName() + " for "
					+ (price == 0 ? "free!"
							: HexColours.getShortMessage(Colour.RED, Utils.getFormattedNumber(price, ',')) + " "
									+ getPointsName(TYPE, SHOP) + "."));
			refreshInterface();
		}
	}
}
