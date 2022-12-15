package com.rs.game.player.content.grandexchange;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.rs.Launcher;
import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.net.sql.DatabaseUtility;
import com.rs.net.sql.impl.sendGEOffer;
import com.rs.net.sql.impl.sendGEOfferRemoval;
import com.rs.utils.EconomyPrices;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class GrandExchange {

	private static final Object LOCK = new Object();
	// offer uid
	public static HashMap<Long, Offer> OFFERS;
	private static ArrayList<OfferHistory> OFFERS_TRACK;
	private static HashMap<Integer, Integer> PRICES;

	private static boolean edited;

	public static void init() {
		OFFERS = SerializableFilesManager.loadGEOffers();
		setOFFERS_TRACK(SerializableFilesManager.loadGEHistory());
		PRICES = SerializableFilesManager.loadGEPrices();
	}

	public static long getTotalOfferValues(Player player) {
		long grandexchangeValue = 0;
		for (Entry<Long, Offer> entry : OFFERS.entrySet()) {
			Offer offer = entry.getValue();
			if (offer.getUsername().equals(player.getUsername())) {
				if (offer.isCompleted())
					continue;
				grandexchangeValue += ItemDefinitions.getItemDefinitions(offer.getId()).getTipitPrice()
						* offer.getAmount();
			}
		}
		return grandexchangeValue;

	}

	public static long getTotalCollectionValue(Player player) {
		long collectionValue = 0;
		for (Entry<Long, Offer> entry : OFFERS.entrySet()) {
			Offer offer = entry.getValue();
			if (offer.getUsername().equals(player.getUsername())) {
				if (!offer.isCompleted())
					continue;
				collectionValue += (offer.getPrice()) * (offer.getTotalAmmountSoFar());
			}
		}
		return collectionValue;

	}

	public static void removeOffers(Player player) {
		for (long uid : player.getGeManager().getOfferUIds()) {
			if (uid == 0) {
				continue;
			}
			edited = true;
			OFFERS.remove(uid);
		}
		for (Entry<Long, Offer> entry : OFFERS.entrySet()) {
			Offer offer = entry.getValue();
			if (offer.getUsername().equals(player.getUsername())) {
				edited = true;
				OFFERS.remove(entry.getKey());
			}
		}
	}

	public static void removeAllOffers() {
		for (Entry<Long, Offer> entry : OFFERS.entrySet()) {
			Offer offer = entry.getValue();
			// for (int i = 0; i < 5; i++)
			// offer.collectItems(i, 0);
			offer.forceRemove();
			offer.cancel();
			edited = true;
			// OFFERS.remove(entry.getKey());
		}
		// SerializableFilesManager.deleteOffers();
		// OFFERS.clear();
	}

	public static int getTotalBuyQuantity(int itemId) {
		int quantity = 0;
		for (Offer offer : OFFERS.values()) {
			if (!offer.isBuying() || offer.getId() != itemId || offer.isCompleted())
				continue;
			quantity += offer.getAmount() - offer.getTotalAmmountSoFar();
		}
		return quantity;
	}

	public static int getTotalSellQuantity(int itemId) {
		int quantity = 0;
		for (Offer offer : OFFERS.values()) {
			if (offer.isBuying() || offer.getId() != itemId || offer.isCompleted())
				continue;
			quantity += offer.getAmount() - offer.getTotalAmmountSoFar();
		}
		return quantity;
	}

	public static void priceCheckItem(Player player, int itemId) {
		Item item = new Item(itemId);
		int cheapestPrice = GrandExchange.getCheapestSellPrice(itemId);
		player.getDialogueManager().startDialogue("PriceChecker", item.getId(), cheapestPrice);
	}

	public static void sendOfferTracker(Player player) {
		player.getInterfaceManager().sendInterface(275);
		int number = 0;
		for (int i = 0; i < 100; i++) {
			player.getPackets().sendIComponentText(275, i, "");
		}
		for (Offer offer : OFFERS.values()) {
			if (offer == null)
				continue;
			player.getPackets().sendIComponentText(275, 11, "Amount of Offers: " + number
					+ "<br>It does only show items were price is over " + Settings.LOWPRICE_LIMIT + "<br><br>");
			ItemDefinitions defs = ItemDefinitions.getItemDefinitions(offer.getId());
			if (GrandExchange.getPrice(offer.getId()) < Settings.LOWPRICE_LIMIT
					&& !LimitedGEReader.itemIsLimited(offer.getId()))
				continue;
			if (offer.isCompleted())
				continue;
			player.getPackets().sendIComponentText(275, 1, "Grand Exchange Offers");
			int totalAmount = offer.getAmount() - offer.getTotalAmmountSoFar();
			player.getPackets().sendIComponentText(275, (13 + number++),
					Utils.formatPlayerNameForDisplay(offer.getUsername()) + " ["
							+ (offer.isBuying() ? "Buying" : "Selling") + "] " + defs.getName()
							+ (totalAmount > 1 ? " x " + Utils.getFormattedNumber(totalAmount, ',') : "") + " :  Price "
							+ Utils.getFormattedNumber(offer.getPrice(), ',') + " " + (totalAmount > 1 ? "each" : ""));
			if (number >= 100) {
				break;
			}
		}
	}

	public static int getBestBuyPrice(int itemId) {
		int price = 0;
		for (Offer offer : OFFERS.values()) {
			if (!offer.isBuying() || offer.getId() != itemId || offer.isCompleted())
				continue;
			if (offer.getPrice() > price || price == 0) {
				price = offer.getPrice();
			}
		}
		return price;
	}

	public static int getBuyQuantity(int itemId) {
		int quantity = 0;
		for (Offer offer : OFFERS.values()) {
			if (!offer.isBuying() || offer.getId() != itemId || offer.isCompleted())
				continue;
			if (offer.getPrice() == getBestBuyPrice(itemId))
				quantity += offer.getAmount() - offer.getTotalAmmountSoFar();
		}
		return quantity;
	}

	public static int getCheapestSellPrice(int itemId) {
		int price = 0;
		for (Offer offer : OFFERS.values()) {
			if (offer.isBuying() || offer.getId() != itemId || offer.isCompleted())
				continue;
			if (offer.getPrice() < price || price == 0) {
				price = offer.getPrice();
			}
		}
		return price;
	}

	public static int getSellQuantity(int itemId) {
		int quantity = 0;
		for (Offer offer : OFFERS.values()) {
			if (offer.isBuying() || offer.getId() != itemId || offer.isCompleted())
				continue;
			if (offer.getPrice() == getCheapestSellPrice(itemId))
				quantity += offer.getAmount() - offer.getTotalAmmountSoFar();
		}
		return quantity;
	}

	public static void reset(boolean track, boolean price) {
		if (track)
			getOFFERS_TRACK().clear();
		if (price)
			PRICES.clear();
		recalcPrices();
	}

	public static void recalcPrices() {
		ArrayList<OfferHistory> track = new ArrayList<OfferHistory>(getOFFERS_TRACK());
		HashMap<Integer, BigInteger> averagePrice = new HashMap<Integer, BigInteger>();
		HashMap<Integer, BigInteger> averageQuantity = new HashMap<Integer, BigInteger>();
		for (OfferHistory o : track) {
			BigInteger price = averagePrice.get(o.getId());
			if (price != null) {
				BigInteger quantity = averageQuantity.get(o.getId());
				averagePrice.put(o.getId(), price.add(BigInteger.valueOf(o.getPrice())));
				averageQuantity.put(o.getId(), quantity.add(BigInteger.valueOf(o.getQuantity())));
			} else {
				averagePrice.put(o.getId(), BigInteger.valueOf(o.getPrice()));
				averageQuantity.put(o.getId(), BigInteger.valueOf(o.getQuantity()));
			}
		}

		for (int id : averagePrice.keySet()) {
			BigInteger price = averagePrice.get(id);
			BigInteger quantity = averageQuantity.get(id);

			long oldPrice = getPrice(id);
			long newPrice = price.divide(quantity).longValue();
			long min = (long) ((double) oldPrice * 0.8D) - 100;
			long max = (long) ((double) oldPrice * 1.2D) + 100;
			if (newPrice < min)
				newPrice = min;
			else if (newPrice > max)
				newPrice = max;

			if (newPrice < 1)
				newPrice = 1;
			else if (newPrice > Integer.MAX_VALUE)
				newPrice = Integer.MAX_VALUE;

			PRICES.put(id, (int) newPrice);
		}
		savePrices();
	}

	private static void savePrices() {
		SerializableFilesManager.saveGEPrices(PRICES);
	}

	public static final void save() {
		if (!edited)
			return;
		SerializableFilesManager.saveGEOffers(OFFERS);
		SerializableFilesManager.saveGEHistory(getOFFERS_TRACK());
		edited = false;
	}

	public static void linkOffers(Player player) {
		boolean itemsWaiting = false;
		for (int slot = 0; slot < player.getGeManager().getOfferUIds().length; slot++) {
			Offer offer = getOffer(player, slot);
			if (offer == null)
				continue;
			offer.link(slot, player);
			offer.update();
			if (!itemsWaiting && offer.hasItemsWaiting()) {
				itemsWaiting = true;
				if (player.getSession() != null) {
					player.getPackets().sendGameMessage(
							"You have items from the Grand Exchange waiting on you in your collection box.");
				}
			}
		}
	}

	public static Offer getOffer(Player player, int slot) {
		synchronized (LOCK) {
			long uid = player.getGeManager().getOfferUIds()[slot];
			if (uid == 0)
				return null;
			Offer offer = OFFERS.get(uid);
			if (offer == null) {
				player.getGeManager().getOfferUIds()[slot] = 0;
				return null;
			}
			return offer;
		}
	}

	public static void sellOffer(Player player, int slot, int itemId, int amount, int price, boolean buy) {
		synchronized (LOCK) {
			Offer offer = new Offer(itemId, amount, price, buy);
			offer.setUsername(player.getUsername());
			player.getGeManager().getOfferUIds()[slot] = createOffer(offer);
			offer.link(slot, player);
			offer.update();
		}
	}

	public static void buyOffer(Player player, int slot, int itemId, int amount, int price, boolean buy) {
		synchronized (LOCK) {
			Offer offer = new Offer(itemId, amount, price, buy);
			offer.setUsername(player.getUsername());
			player.getGeManager().getOfferUIds()[slot] = createOffer(offer);
			offer.link(slot, player);
			offer.update();
		}
	}

	public static String format(int price) {
		if (price >= 10000000) {
			return price / 1000000 + "m";
		}
		return Utils.getFormattedNumber(price, ',');
	}

	public static void sendOffer(final Player player, final int slot, final int itemId, final int amount,
			final int price, final boolean buy) {
		synchronized (LOCK) {
			final Offer offer = new Offer(itemId, amount, price, buy);
			offer.setUsername(player.getUsername());
			player.getGeManager().getOfferUIds()[slot] = createOffer(offer);
			offer.link(slot, player);
			if (!offer.isBuying()) {
				if (Settings.ECONOMY_MODE == 0) {// full eco
					if ((GrandExchange.getPrice(offer.getId()) < Settings.LOWPRICE_LIMIT
							|| UnlimitedGEReader.itemIsLimited(offer.getId()))
							&& (offer.getPrice() <= Math.floor(GrandExchange.getPrice(offer.getId()) * 0.95))) {
						offer.sellOffer(offer);
					} else {
						findBuyerSeller(offer);
					}
				} else {// spawn/halfeco
					findBuyerSeller(offer);
				}
			} else {
				if (Settings.ECONOMY_MODE == 0) {// full eco
					if ((GrandExchange.getPrice(offer.getId()) < Settings.LOWPRICE_LIMIT
							|| UnlimitedGEReader.itemIsLimited(offer.getId()))
							&& offer.getPrice() >= GrandExchange.getPrice(offer.getId())) {
						offer.buyOffer(offer);
					} else {
						findBuyerSeller(offer);
					}
				} else {// spawn/halfeco
					if (GrandExchange.getPrice(offer.getId()) == 0)
						offer.buyOffer(offer);
					else {
						findBuyerSeller(offer);
					}
				}
			}
		}
	}

	public static void abortOffer(Player player, int slot) {
		synchronized (LOCK) {
			Offer offer = getOffer(player, slot);
			if (offer == null)
				return;
			edited = true;
			if (offer.cancel() && offer.forceRemove())
				deleteOffer(player, slot);
			if (!UnlimitedGEReader.itemIsLimited(offer.getId()) && EconomyPrices.getPrice(offer.getId()) >= Settings.LOWPRICE_LIMIT) {
				DatabaseUtility.sendTask(offer, new sendGEOfferRemoval(), false);
				//if (!offer.isBuying() && Settings.discordEnabled)
					//Launcher.getDiscordBot().getChannelByName("exchange").sendMessage(offer.getOwner().getDisplayName()
				//	+ " aborted selling " + offer.getDefinitions().getName() + "!");
			}
		}
	}

	public static void collectItems(Player player, int slot, int invSlot, int option) {
		synchronized (LOCK) {
			Offer offer = getOffer(player, slot);
			if (offer == null)
				return;
			edited = true;
			if (offer.collectItems(invSlot, option) && offer.forceRemove()) {
				deleteOffer(player, slot);
				if (offer.getTotalAmmountSoFar() != 0) {
					OfferHistory o = new OfferHistory(offer.getId(), offer.getTotalAmmountSoFar(),
							offer.getTotalPriceSoFar(), offer.isBuying());
					player.getGeManager().addOfferHistory(o);
				}
			}
		}
	}

	private static void deleteOffer(Player player, int slot) {
		player.getGeManager().cancelOffer();
		OFFERS.remove(player.getGeManager().getOfferUIds()[slot]);
		player.getGeManager().getOfferUIds()[slot] = 0;
	}

	private static void findBuyerSeller(final Offer offer) {
		while (!offer.isCompleted()) {
			Offer bestOffer = null;
			for (Offer o : OFFERS.values()) {
				if (o.isBuying() == offer.isBuying() || o.getId() != offer.getId() || o.isCompleted()
						|| (offer.isBuying() && o.getPrice() > offer.getPrice())
						|| (!offer.isBuying() && o.getPrice() < offer.getPrice()) || offer.isOfferTooHigh(o))
					continue;
				if (bestOffer == null || (offer.isBuying() && o.getPrice() < bestOffer.getPrice())
						|| (!offer.isBuying() && o.getPrice() > bestOffer.getPrice()))
					bestOffer = o;
			}
			if (bestOffer == null) {
				if (!UnlimitedGEReader.itemIsLimited(offer.getId()) && EconomyPrices.getPrice(offer.getId()) >= Settings.LOWPRICE_LIMIT) {
					//DatabaseUtility.sendTask(offer, new sendGEOffer(), false);
					//if (Settings.discordEnabled)
					//Launcher.getDiscordBot().getChannelByName("exchange")
						//		.sendMessage(offer.getOwner().getDisplayName() + " is now "
						//				+ (offer.isBuying() ? "buying " : "selling ")
						//				+ (offer.getAmount() > 1 ? format(offer.getAmount()) + " x " : " ")
						//				+ offer.getDefinitions().getName() + " for " + format(offer.getPrice()) + ""
					//				+ (offer.getAmount() > 1 ? " each" : "") + "!");
				}
				break;
			}
			offer.updateOffer(bestOffer);
		}
		offer.update();
	}

	private static long createOffer(Offer offer) {
		edited = true;
		long uid = getUId();
		OFFERS.put(uid, offer);
		return uid;
	}

	private static long getUId() {
		while (true) {
			long uid = Utils.RANDOM.nextLong();
			if (OFFERS.containsKey(uid))
				continue;
			return uid;
		}
	}

	public static int getPrice(int itemId) {
		return EconomyPrices.getPrice(itemId);
	}

	public static void unlinkOffers(Player player) {
		for (int slot = 0; slot < player.getGeManager().getOfferUIds().length; slot++) {
			Offer offer = getOffer(player, slot);
			if (offer == null)
				continue;
			offer.unlink();
		}
	}

	public static List<OfferHistory> getHistory() {
		return getOFFERS_TRACK();
	}

	public static ArrayList<OfferHistory> getOFFERS_TRACK() {
		return OFFERS_TRACK;
	}

	public static void setOFFERS_TRACK(ArrayList<OfferHistory> oFFERS_TRACK) {
		OFFERS_TRACK = oFFERS_TRACK;
	}
}