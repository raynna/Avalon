package com.rs.game.player.content.grandexchange;

import com.rs.Launcher;
import com.rs.Settings;
import com.rs.cache.loaders.ClientScriptMap;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.AccountCreation;
import com.rs.game.player.Player;
import com.rs.net.sql.DatabaseUtility;
import com.rs.net.sql.impl.sendGEOfferRemoval;
import com.rs.utils.EconomyPrices;
import com.rs.utils.Utils;

public class Offer extends Item {

	private static final long serialVersionUID = -4065899425889989474L;

	public int COINS = 995;

	public Offer(int id, int ammount, int price, boolean buy) {
		// item id buying or selling
		// total amount buying or selling
		super(id, ammount);
		this.price = price;
		buying = buy;
		receivedItems = new ItemsContainer<Item>(2, true);
	}

	// offer will contain the owner, its the most efficient way to update in
	// terms speed
	// owner needs to be deleted when logs out from here else it caches player
	// on memory
	private transient Player owner;
	private transient int slot;

	private long UID;
	private int price; // price per item selling or buying
	private int totalPriceSoFar; // total profit received so far or spent
	private int totalAmmountSoFar; // amt of items sold or bought
	private ItemsContainer<Item> receivedItems;
	private boolean canceled;
	private boolean buying;
	private String username;
	@SuppressWarnings("unused")
	private long data;

	public void link(int slot, Player owner) {
		this.slot = slot;
		this.setOwner(owner);
		this.data = Utils.currentTimeMillis();
		setRandomUID();
	}

	public void unlink() {
		setOwner(null);
	}

	public void update() {
		if (getOwner() == null)
			return;
		if (getOwner().getSession() != null) {
			getOwner().getPackets().sendGrandExchangeOffer(this);
			sendItems();
		}
	}

	public void setRandomUID() {
		long uid = Utils.RANDOM.nextLong();
		while (GrandExchange.OFFERS.containsKey(uid)) {
			uid = Utils.RANDOM.nextLong();
		}
		this.UID = uid;
	}

	public long getUID() {
		return UID;
	}

	public void sendItems() {
		if (getOwner() == null)
			return;
		getOwner().getPackets().sendItems(ClientScriptMap.getMap(1079).getIntValue(slot), receivedItems);
	}

	public int getPrice() {
		return price;
	}

	public boolean forceRemove() {
		return isCompleted() && !hasItemsWaiting();
	}

	protected boolean isCompleted() {
		return canceled || totalAmmountSoFar >= getAmount();
	}

	public int getPercentage() {
		return totalAmmountSoFar * getAmount() / 100;
	}

	public int getTotalAmmountSoFar() {
		return totalAmmountSoFar;
	}

	public int getTotalPriceSoFar() {
		return totalPriceSoFar;
	}

	public int getSlot() {
		return slot;
	}

	public int getStage() {
		if (forceRemove())
			return 0;
		if (isCompleted())
			return buying ? 5 : 13;
		return buying ? 2 : 10;
	}

	public boolean isBuying() {
		return buying;
	}

	// TODO canceling message
	public boolean cancel() {
		if (isCompleted())
			return false;
		canceled = true;
		if (buying)
			receivedItems.add(new Item(COINS, (getAmount() - totalAmmountSoFar) * price));
		else
			receivedItems.add(new Item(getId(), getAmount() - totalAmmountSoFar));
		update();
		return true;
	}

	public void sendUpdateWarning(Offer offer) {
		if (getOwner() == null)
			return;
		if (!isCompleted()) {
			if (offer.isBuying()) {
				getOwner().getPackets().sendGameMessage("<col=00a52c>Grand Exchange: Bought "
						+ Utils.getFormattedNumber(offer.getTotalAmmountSoFar(), ',') + "/"
						+ Utils.getFormattedNumber(offer.getAmount(), ',') + " x " + offer.getDefinitions().getName());
			} else {
				getOwner().getPackets().sendGameMessage("<col=00a52c>Grand Exchange: Sold "
						+ Utils.getFormattedNumber(offer.getTotalAmmountSoFar(), ',') + "/"
						+ Utils.getFormattedNumber(offer.getAmount(), ',') + " x " + offer.getDefinitions().getName());
			}
		} else {
			getOwner().getPackets()
					.sendGameMessage("<col=00a52c>One or more of your Grand Exchange offers have been updated.");
		}
		getOwner().getPackets().sendMusicEffect(284);
		update();
	}

	public boolean isOfferTooHigh(Offer fromOffer) {
		int left = getAmount() - totalAmmountSoFar;
		int leftFrom = fromOffer.getAmount() - fromOffer.totalAmmountSoFar;
		int exchangeAmt = left > leftFrom ? leftFrom : left;
		int totalPrice = exchangeAmt * fromOffer.price;
		int amtCoins = receivedItems.getNumberOf(COINS);

		if (buying) {
			if (fromOffer.receivedItems.getNumberOf(COINS) + totalPrice <= 0)
				return true;
			int leftcoins = exchangeAmt * price - totalPrice;
			if (leftcoins > 0 && amtCoins + leftcoins <= 0)
				return true;
		} else {
			if (amtCoins + totalPrice <= 0)
				return true;
		}
		return false;

	}

	public void sellOffer(Offer fromOffer) {
		int exchangeAmt = getAmount();
		int totalPrice = getPrice() * exchangeAmt;
		// Item item = new Item(fromOffer.getId());
		int fiveProcentPrice = (int) Math.floor((GrandExchange.getPrice(fromOffer.getId()) * 0.95) * exchangeAmt);
		receivedItems.add(new Item(COINS, fiveProcentPrice));
		totalAmmountSoFar += exchangeAmt;
		totalPriceSoFar += totalPrice;
		sendUpdateWarning(fromOffer);
	}

	public void buyOffer(Offer fromOffer) {
		int left = getAmount() - totalAmmountSoFar;
		int leftFrom = fromOffer.getAmount() - fromOffer.totalAmmountSoFar;
		int exchangeAmt = left > leftFrom ? leftFrom : left;
		int totalPrice = exchangeAmt * GrandExchange.getPrice(fromOffer.getId());
		int leftcoins = exchangeAmt * price - totalPrice;
		if (leftcoins > 0) {
			receivedItems.add(new Item(COINS, leftcoins));
		}
		receivedItems.add(buying ? new Item(getId(), exchangeAmt) : new Item(getId(), exchangeAmt));
		totalAmmountSoFar += exchangeAmt;
		totalPriceSoFar += totalPrice;
		sendUpdateWarning(fromOffer);
	}

	public void instaOffer(Offer fromOffer) {
		int left = getAmount() - totalAmmountSoFar;
		int leftFrom = fromOffer.getAmount() - fromOffer.totalAmmountSoFar;
		int exchangeAmt = left > leftFrom ? leftFrom : left;
		int totalPrice = exchangeAmt * fromOffer.price;
		if (buying) {
			int leftcoins = exchangeAmt * price - totalPrice;
			if (leftcoins > 0) {
				receivedItems.add(new Item(COINS, leftcoins));
			}
			receivedItems.add(buying ? new Item(getId(), exchangeAmt) : new Item(getId(), exchangeAmt));
		} else {
			receivedItems.add(new Item(COINS, totalPrice));
		}
		totalAmmountSoFar += exchangeAmt;
		totalPriceSoFar += totalPrice;
	}

	public void updateOffer(Offer fromOffer) {
		int left = getAmount() - totalAmmountSoFar;
		int leftFrom = fromOffer.getAmount() - fromOffer.totalAmmountSoFar;
		int exchangeAmt = left > leftFrom ? leftFrom : left;
		int totalPrice = exchangeAmt * fromOffer.price;
		if (buying) {
			int leftcoins = exchangeAmt * price - totalPrice;
			if (leftcoins > 0) {
				receivedItems.add(new Item(COINS, leftcoins));
			}
			receivedItems.add(buying ? new Item(getId(), exchangeAmt) : new Item(getId(), exchangeAmt));
			fromOffer.receivedItems.add(new Item(COINS, totalPrice));
		} else {
			fromOffer.receivedItems.add(new Item(getId(), exchangeAmt));
			receivedItems.add(new Item(COINS, totalPrice));
		}
		totalAmmountSoFar += exchangeAmt;
		fromOffer.totalAmmountSoFar += exchangeAmt;
		totalPriceSoFar += totalPrice;
		fromOffer.totalPriceSoFar += totalPrice;
		if (Settings.discordEnabled && !UnlimitedGEReader.itemIsLimited(fromOffer.getId())
				&& EconomyPrices.getPrice(fromOffer.getId()) >= Settings.LOWPRICE_LIMIT) {
			Player fromOwner = fromOffer.getOwner();
			if (fromOwner == null)
				fromOwner = AccountCreation.loadPlayer(fromOffer.getUsername());
			if (!fromOffer.isCompleted()) {
				DatabaseUtility.sendTask(fromOffer, new sendGEOfferRemoval(), true);
				if (!fromOffer.isBuying()) {
					Launcher.getDiscordBot().getChannelByName("exchange")
							.sendMessage(fromOwner.getDisplayName() + " has sold total of "
									+ Utils.getFormattedNumber(fromOffer.getTotalAmmountSoFar(), ',') + "/"
									+ Utils.getFormattedNumber(fromOffer.getAmount(), ',') + " "
									+ fromOffer.getDefinitions().getName() + "!");
				}
			} else {
				DatabaseUtility.sendTask(fromOffer, new sendGEOfferRemoval(), false);
				if (!fromOffer.isBuying())
					Launcher.getDiscordBot().getChannelByName("exchange")
							.sendMessage(fromOwner.getDisplayName() + " finished selling "
									+ Utils.getFormattedNumber(fromOffer.getAmount(), ',') + " x "
									+ fromOffer.getDefinitions().getName() + "!");
			}
		}
		sendUpdateWarning(this);
		fromOffer.sendUpdateWarning(fromOffer);
	}

	public boolean collectItems(int slot, int option) {
		if (getOwner() == null)
			return false;
		int freeSlots = getOwner().getInventory().getFreeSlots();
		Item item = receivedItems.get(slot);
		if (item == null)
			return false;
		if (item.getDefinitions().isStackable()) {
			if (!getOwner().getInventory().hasFreeSlots() && !getOwner().getInventory().containsOneItem(item.getId())) {
				getOwner().getPackets().sendGameMessage("Not enough space in your inventory.");
				return false;
			}
		} else {
			if (!getOwner().getInventory().hasFreeSlots()) {
				getOwner().getPackets().sendGameMessage("Not enough space in your inventory.");
				return false;
			}
		}
		ItemDefinitions defs = item.getDefinitions();
		if (defs.getId() == COINS) {
			getOwner().getMoneyPouch().addMoney(item.getAmount(), false);
			receivedItems.remove(item);
		} else if (defs.isStackable() && getOwner().getInventory().getNumberOf(item.getId()) + item.getAmount() < 0) {
			Item add = new Item(item.getId(), Integer.MAX_VALUE - getOwner().getInventory().getNumberOf(item.getId()));
			Item add1 = new Item(item.getId(), Integer.MAX_VALUE);
			receivedItems.remove(add);
			getOwner().getInventory().deleteItem(add1);
			getOwner().getInventory().addItem(add1);
			getOwner().getPackets().sendGameMessage("Not enough space in your inventory.");
		} else if (!defs.isStackable() && option == (item.getAmount() == 1 ? 0 : 1)) {
			Item add = new Item(item.getId(), item.getAmount() > freeSlots ? freeSlots : item.getAmount());
			getOwner().getInventory().addItem(add);
			receivedItems.remove(add);
		} else {
			getOwner().getInventory()
					.addItem(new Item(defs.certId != -1 ? defs.certId : item.getId(), item.getAmount()));
			receivedItems.remove(item);
		}
		update();
		return true;
	}

	public boolean hasItemsWaiting() {
		return receivedItems.getFreeSlots() != 2;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
