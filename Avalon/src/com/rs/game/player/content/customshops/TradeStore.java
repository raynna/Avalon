package com.rs.game.player.content.customshops;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import com.rs.Settings;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Player;
import com.rs.game.player.content.ItemConstants;
import com.rs.utils.EconomyPrices;
import com.rs.utils.ItemExamines;
import com.rs.utils.Utils;

public class TradeStore {

	/**
	 * @Author -Andreas 2019-11
	 */

	private transient Player player;
	private ItemsContainer<Item> items;
	private ItemsContainer<Item> rewardItems;
	private boolean tradeModified;
	private boolean accepted;

	public TradeStore(Player player) {
		this.player = player; // player reference
		items = new ItemsContainer<Item>(28, false);
		rewardItems = new ItemsContainer<Item>(28, false);
	}

	/*
	 * called to both players
	 */
	public void openTrade() {
		synchronized (this) {
			player.getPackets().sendIComponentText(335, 17, "Trading With: " + Settings.SERVER_NAME);
			player.getPackets().sendGlobalString(203, Settings.SERVER_NAME);
			sendInterItems();
			sendOptions();
			sendTradeModified();
			refreshTradeWealth();
			refreshStageMessage(true);
			player.getInterfaceManager().sendInterface(335);
			player.getInterfaceManager().sendInventoryInterface(336);
			player.getPackets().sendHideIComponent(335, 53, true);
			player.getPackets().sendHideIComponent(335, 37, true);
			player.getPackets().sendHideIComponent(335, 48, true);
			player.getPackets().sendHideIComponent(335, 46, true);
			player.getPackets().sendHideIComponent(335, 49, true);
			player.getPackets().sendHideIComponent(335, 59, true);
			player.getPackets().sendHideIComponent(335, 62, true);
			player.getPackets().sendHideIComponent(335, 55, true);
			player.getPackets().sendHideIComponent(335, 58, true);
			player.getTemporaryAttributes().put("CUSTOM_TRADE", this);
			player.setCloseInterfacesEvent(new Runnable() {
				@Override
				public void run() {
					player.getTemporaryAttributes().remove("CUSTOM_TRADE");
					closeTrade(CloseTradeStage.CANCEL);
				}
			});
		}
	}

	public int getPrice(Item item) {
		return (int) ((EconomyPrices.getPrice(item.getId()) * item.getAmount()) * 0.33);
	}

	public int getPrice(Item item, int amount) {
		return (int) ((EconomyPrices.getPrice(item.getId()) * amount) * 0.33);
	}

	public void removeItem(final int slot, int amount) {
		synchronized (this) {
			Item item = items.get(slot);
			if (item == null)
				return;
			Item[] itemsBefore = items.getItemsCopy();
			int maxAmount = items.getNumberOf(item);
			if (amount < maxAmount)
				item = new Item(item.getId(), amount);
			else
				item = new Item(item.getId(), maxAmount);
			items.remove(slot, item);
			int wealth = 0;
			rewardItems.clear();
			for (Item tradeItems : items.getContainerItems()) {
				if (tradeItems == null)
					continue;
				wealth += getPrice(tradeItems);
			}
			if (wealth > 0)
				rewardItems.set(0, new Item(995, wealth));
			if (item.getId() != 995)
				player.getInventory().addItem(item);
			else
				player.getMoneyPouch().addMoney(item.getAmount(), false);
			refreshItems(rewardItems.getContainerItems());
			refreshItems(itemsBefore);
			cancelAccepted();
			setTradeModified(true);
		}
	}

	public void sendFlash(int slot) {
		player.getPackets().sendInterFlashScript(335, 33, 4, 7, slot);
	}

	public void cancelAccepted() {
		boolean canceled = false;
		if (accepted) {
			accepted = false;
			canceled = true;
		}
		if (canceled)
			refreshBothStageMessage(canceled);
	}

	public void addItem(int slot, int amount) {
		synchronized (this) {
			Item item = player.getInventory().getItem(slot);
			if (item == null)
				return;
			if (!ItemConstants.isTradeable(item)) {
				player.getPackets().sendGameMessage("That item isn't tradeable.");
				return;
			}
			if (item.getId() == 995) {
				player.sm("You can't trade coins.");
				return;
			}
			if (getPrice(item, 1) > 10000000) {
				player.sm("You can't trade items worth over 10,000,000 coins.");
				return;
			}
			Item newItem = new Item(item.getId(), item.getAmount());
			if (getPrice(newItem) == 0) {
				player.sm("You can't trade free items to sigmund.");
				return;
			}
			if (getPrice(newItem) + rewardItems.getNumberOf(995) < 0) {
				player.sm("There is not enough space for this much coins.");
				return;
			}
			Item[] itemsBefore = items.getItemsCopy();
			int maxAmount = player.getInventory().getItems().getNumberOf(item);
			int itemAmount = amount;
			if (itemAmount > maxAmount)
				itemAmount = maxAmount;
			for (Item tradeItems : items.getContainerItems()) {
				if (tradeItems == null)
					continue;
				if (tradeItems.getAmount() == Integer.MAX_VALUE) {
					player.getPackets().sendGameMessage("You can't trade more of that item.");
					return;
				}
				if (tradeItems.getAmount() + itemAmount < 0) {
					itemAmount = Integer.MAX_VALUE - tradeItems.getAmount();
				}
			}
			items.add(new Item(item.getId(), itemAmount));
			int wealth = 0;
			rewardItems.clear();
			for (Item tradeItems : items.getContainerItems()) {
				if (tradeItems == null)
					continue;
				wealth += getPrice(tradeItems);
			}
			rewardItems.set(0, new Item(995, wealth));
			player.getInventory().deleteItem(slot, new Item(item.getId(), itemAmount));
			refreshItems(itemsBefore, false);
			refreshItems(rewardItems.getContainerItems(), false);
			cancelAccepted();
		}
	}

	public void addPouch(int slot, int amount) {
		synchronized (this) {
			Item[] itemsBefore = items.getItemsCopy();
			if (player.getMoneyPouch().getTotal() == 0) {
				player.getPackets().sendGameMessage("You don't have enough money to do that.");
				return;
			}
			int itemAmount;
			itemAmount = amount;
			for (Item tradeItem : items.getContainerItems()) {
				if (tradeItem == null)
					continue;
				if (tradeItem.getAmount() == Integer.MAX_VALUE) {
					player.getPackets().sendGameMessage("You can't trade more of that item.");
					return;
				}
				if (tradeItem.getAmount() + amount < 0) {
					itemAmount = Integer.MAX_VALUE - tradeItem.getAmount();
					player.getPackets().sendGameMessage("You can't trade more of that item.");
				}
			}
			if (itemAmount > player.getMoneyPouch().getTotal())
				itemAmount = player.getMoneyPouch().getTotal();
			items.add(new Item(995, itemAmount));
			player.getMoneyPouch().removeMoneyMisc(itemAmount);
			refreshItems(itemsBefore);
			cancelAccepted();
		}
	}

	public void refreshItems(Item[] itemsBefore) {
		int[] changedSlots = new int[itemsBefore.length];
		int count = 0;
		for (int index = 0; index < itemsBefore.length; index++) {
			Item item = items.getContainerItems()[index];
			if (itemsBefore[index] != item) {
				if (itemsBefore[index] != null && (item == null || item.getId() != itemsBefore[index].getId()
						|| item.getDefinitions().isStackable() && item.getAmount() < itemsBefore[index].getAmount()))
					sendFlash(index);
				changedSlots[count++] = index;
			}
		}
		int[] finalChangedSlots = new int[count];
		System.arraycopy(changedSlots, 0, finalChangedSlots, 0, count);
		refresh(finalChangedSlots);
		refreshTradeWealth();
	}

	public void refreshItems(Item[] itemsBefore, boolean flash) {
		int[] changedSlots = new int[itemsBefore.length];
		int count = 0;
		for (int index = 0; index < itemsBefore.length; index++) {
			Item item = items.getContainerItems()[index];
			if (itemsBefore[index] != item) {
				if (flash && (itemsBefore[index] != null && (item == null || item.getId() != itemsBefore[index].getId()
						|| item.getDefinitions().isStackable() && item.getAmount() < itemsBefore[index].getAmount())))
					sendFlash(index);
				changedSlots[count++] = index;
			}
		}
		int[] finalChangedSlots = new int[count];
		System.arraycopy(changedSlots, 0, finalChangedSlots, 0, count);
		refresh(finalChangedSlots);
		refreshTradeWealth();
	}

	public void sendOptions() {
		player.getPackets().sendInterSetItemsOptionsScript(336, 0, 93, 4, 7, "Offer", "Offer-5", "Offer-10",
				"Offer-All", "Offer-X", "Value<col=FF9040>", "Lend");
		player.getPackets().sendIComponentSettings(336, 0, 0, 27, 1278);
		player.getPackets().sendInterSetItemsOptionsScript(335, 32, 90, 4, 7, "Remove", "Remove-5", "Remove-10",
				"Remove-All", "Remove-X", "Value");
		player.getPackets().sendIComponentSettings(335, 32, 0, 27, 1150);
		player.getPackets().sendInterSetItemsOptionsScript(335, 35, 90, true, 4, 7, "Value");
		player.getPackets().sendIComponentSettings(335, 35, 0, 27, 1026);

	}

	public void setTradeModified(boolean modified) {
		if (modified == tradeModified)
			return;
		tradeModified = modified;
		sendTradeModified();
	}

	public void sendInterItems() {
		player.getPackets().sendItems(90, items);
		player.getPackets().sendItems(90, true, rewardItems);
	}

	public void refresh(int... slots) {
		player.getPackets().sendUpdateItems(90, items, slots);
		player.getPackets().sendUpdateItems(90, true, rewardItems.getContainerItems(), slots);
	}

	public void accept(boolean firstStage) {
		synchronized (this) {
			if (firstStage) {
				if (getTradeWealth() == 0) {
					player.getPackets().sendGameMessage("You don't have any items put in for sale.");
					sendInterItems();
					return;
				}
				nextStage();
			} else {
				player.setCloseInterfacesEvent(null);
				player.closeInterfaces();
				closeTrade(CloseTradeStage.DONE);
			}
			accepted = true;
			refreshBothStageMessage(firstStage);
		}
	}

	public void sendValue(int slot, boolean traders) {
		Item item = items.get(slot);
		if (item == null)
			return;
		if (!ItemConstants.isTradeable(item)) {
			player.getPackets().sendGameMessage("That item isn't tradeable.");
			return;
		}
		int price = getPrice(item);
		player.getPackets().sendGameMessage(item.getDefinitions().getName() + ": Merchant will buy this for "
				+ Utils.getFormattedNumber(price, ',') + " coins.");
	}

	public void sendValue(int slot) {
		Item item = player.getInventory().getItem(slot);
		if (item == null)
			return;
		if (!ItemConstants.isTradeable(item)) {
			player.getPackets().sendGameMessage("That item isn't tradeable.");
			return;
		}
		int price = getPrice(item);
		if (price > 10000000) {
			player.sm("Merchant won't buy items above 10,000,000 coins.");
			return;
		}
		player.getPackets().sendGameMessage(item.getDefinitions().getName() + ": Merchant will buy this item for "
				+ Utils.getFormattedNumber(price, ',') + " coins.");
	}

	public void sendExamine(int slot, boolean traders) {
		Item item = items.get(slot);
		if (item == null)
			return;
		player.getPackets().sendGameMessage(ItemExamines.getExamine(item));
	}

	public boolean nextStage() {
		accepted = false;
		player.getInterfaceManager().sendInterface(334);
		player.getInterfaceManager().closeInventoryInterface();
		player.getPackets().sendHideIComponent(334, 33, true);
		player.getPackets().sendHideIComponent(334, 53, true);
		player.getPackets().sendHideIComponent(334, 54, true);
		player.getPackets().sendHideIComponent(334, 55, !(tradeModified));
		refreshBothStageMessage(false);
		return true;
	}

	public void refreshBothStageMessage(boolean firstStage) {
		refreshStageMessage(firstStage);
	}

	public void refreshStageMessage(boolean firstStage) {
		player.getPackets().sendIComponentText(firstStage ? 335 : 334, firstStage ? 39 : 34,
				getAcceptMessage(firstStage));
	}

	public String getAcceptMessage(boolean firstStage) {
		return firstStage ? "" : "Are you sure you want to make this trade?";
	}

	public void sendTradeModified() {
		player.getPackets().sendConfig(1042, tradeModified ? 1 : 0);
	}

	public void refreshTradeWealth() {
		int wealth = getTradeWealth();
		player.getPackets().sendGlobalConfig(729, wealth);
	}

	public int getTradeWealth() {
		int wealth = 0;
		for (Item item : items.getContainerItems()) {
			if (item == null)
				continue;
			wealth += EconomyPrices.getPrice(item.getId()) * item.getAmount() * 0.33;
		}
		return wealth;
	}

	private static enum CloseTradeStage {
		CANCEL, NO_SPACE, DONE
	}

	public static String currentTime(String dateFormat) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	public static void archiveTrade(Player player, Player p2, ItemsContainer<Item> items, ItemsContainer<Item> items2) {
		try {
			String location = "";
			location = "data/logs/trade/" + player.getUsername() + ".txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter(location, true));
			writer.write("[" + currentTime("dd MMMMM yyyy 'at' hh:mm:ss z") + "] - " + player.getUsername() + " traded "
					+ p2.getUsername());
			writer.newLine();
			writer.write(player.getUsername() + " items:");
			writer.newLine();
			for (Item item : items.getContainerItems()) {
				if (item == null)
					continue;
				writer.write(item.getDefinitions().getName() + " x " + item.getAmount());
				writer.newLine();
			}
			writer.write("for " + p2.getUsername() + "'s:");
			writer.newLine();
			for (Item item : items2.getContainerItems()) {
				if (item == null)
					continue;
				writer.write(item.getDefinitions().getName() + " x " + item.getAmount());
				writer.newLine();
			}
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeTrade(CloseTradeStage stage) {
		int totalWealth = 0;
		synchronized (this) {
			tradeModified = false;
			accepted = false;
			if (CloseTradeStage.DONE != stage) {
				player.getInventory().getItems().addAll(items);
				player.getInventory().init();
				items.clear();
				rewardItems.clear();
			} else {
				for (Item tradedItems : rewardItems.getContainerItems()) {
					if (tradedItems == null)
						continue;
					totalWealth += tradedItems.getAmount();
				}
				items.clear();
				rewardItems.clear();
				player.getMoneyPouch().addMoney(totalWealth, false);
				player.getInventory().init();
			}
		}
	}

}
