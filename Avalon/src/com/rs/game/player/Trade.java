package com.rs.game.player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.content.ItemConstants;
import com.rs.utils.EconomyPrices;
import com.rs.utils.ItemExamines;

public class Trade {

	private transient Player player, target;
	private ItemsContainer<Item> items;
	private boolean tradeModified;
	private boolean accepted;

	public Trade(Player player) {
		this.player = player; // player reference
		items = new ItemsContainer<Item>(28, false);
	}

	/*
	 * called to both players
	 */
	public void openTrade(Player target) {
		synchronized (this) {
			synchronized (target.getTrade()) {
				this.target = target;
				player.getPackets().sendIComponentText(335, 17, "Trading With: " + target.getDisplayName());
				player.getPackets().sendGlobalString(203, target.getDisplayName());
				sendInterItems();
				sendOptions();
				sendTradeModified();
				refreshFreeInventorySlots();
				refreshTradeWealth();
				refreshStageMessage(true);
				player.getInterfaceManager().sendInterface(335);
				player.getInterfaceManager().sendInventoryInterface(336);
				player.setCloseInterfacesEvent(new Runnable() {
					@Override
					public void run() {
						closeTrade(CloseTradeStage.CANCEL);
					}
				});
			}
		}
	}

	public void removeItem(final int slot, int amount) {
		synchronized (this) {
			if (!isTrading())
				return;
			synchronized (target.getTrade()) {
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
				if (item.getId() != 995)
					player.getInventory().addItem(item);
				else
					player.getMoneyPouch().addMoney(item.getAmount(), false);
				refreshItems(itemsBefore);
				cancelAccepted();
				setTradeModified(true);
			}
		}
	}

	public void sendFlash(int slot) {
		player.getPackets().sendInterFlashScript(335, 33, 4, 7, slot);
		target.getPackets().sendInterFlashScript(335, 36, 4, 7, slot);
	}

	public void cancelAccepted() {
		boolean canceled = false;
		if (accepted) {
			accepted = false;
			canceled = true;
		}
		if (target.getTrade().accepted) {
			target.getTrade().accepted = false;
			canceled = true;
		}
		if (canceled)
			refreshBothStageMessage(canceled);
	}

	public void addItem(int slot, int amount) {
		synchronized (this) {
			if (!isTrading())
				return;
			synchronized (target.getTrade()) {
				Item item = player.getInventory().getItem(slot);
				if (item == null)
					return;
				if (!ItemConstants.isTradeable(item) && !player.isDeveloper()) {
					player.getPackets().sendGameMessage("That item isn't tradeable.");
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
				player.getInventory().deleteItem(slot, new Item(item.getId(), itemAmount));
				refreshItems(itemsBefore);
				cancelAccepted();
			}
		}
	}

	public void addPouch(int slot, int amount) {
		synchronized (this) {
			if (!isTrading())
				return;
			synchronized (target.getTrade()) {
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
	}

	public void refreshItems(Item[] itemsBefore) {
		int[] changedSlots = new int[itemsBefore.length];
		int count = 0;
		for (int index = 0; index < itemsBefore.length; index++) {
			Item item = items.getContainerItems()[index];
			if (itemsBefore[index] != item) {
				if (itemsBefore[index] != null && (item == null || item.getId() != itemsBefore[index].getId()
						|| item.getAmount() < itemsBefore[index].getAmount()))
					sendFlash(index);
				changedSlots[count++] = index;
			}
		}
		int[] finalChangedSlots = new int[count];
		System.arraycopy(changedSlots, 0, finalChangedSlots, 0, count);
		refresh(finalChangedSlots);
		refreshFreeInventorySlots();
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

	public boolean isTrading() {
		return target != null;
	}

	public void setTradeModified(boolean modified) {
		if (modified == tradeModified)
			return;
		tradeModified = modified;
		sendTradeModified();
	}

	public void sendInterItems() {
		player.getPackets().sendItems(90, items);
		target.getPackets().sendItems(90, true, items);
	}

	public void refresh(int... slots) {
		player.getPackets().sendUpdateItems(90, items, slots);
		target.getPackets().sendUpdateItems(90, true, items.getContainerItems(), slots);
	}

	public void accept(boolean firstStage) {
		synchronized (this) {
			if (!isTrading())
				return;
			synchronized (target.getTrade()) {
				if (firstStage) {
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
	}

	public void sendValue(int slot, boolean traders) {
		if (!isTrading())
			return;
		Item item = traders ? target.getTrade().items.get(slot) : items.get(slot);
		if (item == null)
			return;
		if (!ItemConstants.isTradeable(item)) {
			player.getPackets().sendGameMessage("That item isn't tradeable.");
			return;
		}
		int price = EconomyPrices.getPrice(item.getId());
		player.getPackets().sendGameMessage(item.getDefinitions().getName() + ": market price is " + price + " coins.");
	}

	public void sendValue(int slot) {
		Item item = player.getInventory().getItem(slot);
		if (item == null)
			return;
		if (!ItemConstants.isTradeable(item)) {
			player.getPackets().sendGameMessage("That item isn't tradeable.");
			return;
		}
		int price = EconomyPrices.getPrice(item.getId());
		player.getPackets().sendGameMessage(item.getDefinitions().getName() + ": market price is " + price + " coins.");
	}

	public void sendExamine(int slot, boolean traders) {
		if (!isTrading())
			return;
		Item item = traders ? target.getTrade().items.get(slot) : items.get(slot);
		if (item == null)
			return;
		player.getPackets().sendGameMessage(ItemExamines.getExamine(item));
	}

	public boolean nextStage() {
		if (!isTrading())
			return false;
		if (player.getInventory().getItems().getUsedSlots() + target.getTrade().items.getUsedSlots() > 28) {
			player.setCloseInterfacesEvent(null);
			player.closeInterfaces();
			closeTrade(CloseTradeStage.NO_SPACE);
			return false;
		}
		accepted = false;
		player.getInterfaceManager().sendInterface(334);
		player.getInterfaceManager().closeInventoryInterface();
		player.getPackets().sendHideIComponent(334, 55, !(tradeModified || target.getTrade().tradeModified));
		refreshBothStageMessage(false);
		return true;
	}

	public void refreshBothStageMessage(boolean firstStage) {
		refreshStageMessage(firstStage);
		target.getTrade().refreshStageMessage(firstStage);
	}

	public void refreshStageMessage(boolean firstStage) {
		player.getPackets().sendIComponentText(firstStage ? 335 : 334, firstStage ? 39 : 34,
				getAcceptMessage(firstStage));
	}

	public String getAcceptMessage(boolean firstStage) {
		if (accepted)
			return "Waiting for other player...";
		if (target.getTrade().accepted)
			return "Other player has accepted.";
		return firstStage ? "" : "Are you sure you want to make this trade?";
	}

	public void sendTradeModified() {
		player.getPackets().sendConfig(1042, tradeModified ? 1 : 0);
		target.getPackets().sendConfig(1043, tradeModified ? 1 : 0);
	}

	public void refreshTradeWealth() {
		int wealth = getTradeWealth();
		player.getPackets().sendGlobalConfig(729, wealth);
		target.getPackets().sendGlobalConfig(697, wealth);
	}

	public void refreshFreeInventorySlots() {
		int freeSlots = player.getInventory().getFreeSlots();
		target.getPackets().sendIComponentText(335, 23,
				"has " + (freeSlots == 0 ? "no" : freeSlots) + " free" + "<br>inventory slots");
	}

	public int getTradeWealth() {
		int wealth = 0;
		for (Item item : items.getContainerItems()) {
			if (item == null)
				continue;
			wealth += EconomyPrices.getPrice(item.getId()) * item.getAmount();
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
		synchronized (this) {
			archiveTrade(player, target, items, target.getTrade().items);
			synchronized (target.getTrade()) {
				Player oldTarget = target;
				target = null;
				tradeModified = false;
				accepted = false;
				if (CloseTradeStage.DONE != stage) {
					player.getInventory().getItems().addAll(items);
					player.getInventory().init();
					items.clear();
				} else {
					player.getPackets().sendGameMessage("Accepted trade.");
					ItemsContainer<Item> items = oldTarget.getTrade().items;
					for (Item tradedItems : items.getContainerItems()) {
						if (tradedItems == null)
							continue;
						if (tradedItems.getId() == 995)
							player.getMoneyPouch().addMoney(tradedItems.getAmount(), false);
						else
							player.getInventory().addItem(tradedItems);
					}
					/*
					 * player.getInventory().getItems() .addAll(oldTarget.getTrade().items);
					 */
					player.getInventory().init();
					oldTarget.getTrade().items.clear();
				}
				if (oldTarget.getTrade().isTrading()) {
					oldTarget.setCloseInterfacesEvent(null);
					oldTarget.closeInterfaces();
					oldTarget.getTrade().closeTrade(stage);
					if (CloseTradeStage.CANCEL == stage)
						oldTarget.getPackets().sendGameMessage("<col=ff0000>Other player declined trade!");
					else if (CloseTradeStage.NO_SPACE == stage) {
						player.getPackets()
								.sendGameMessage("You don't have enough space in your inventory for this trade.");
						oldTarget.getPackets().sendGameMessage(
								"Other player doesn't have enough space in their inventory for this trade.");
					}
				}
			}
		}
	}

}
