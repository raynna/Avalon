package com.rs.game.player.content.pos;

import java.util.HashMap;

public class PlayerOwnedShops {

	@SuppressWarnings("unused")
	private static final Object LOCK = new Object();

	public static HashMap<Long, PoSOffer> OFFERS;

	/*public static void init() {
		OFFERS = SerializableFilesManager.loadPlayerOwnedShops();
	}

	public static final void save() {
		SerializableFilesManager.savePOSOffers(OFFERS);
	}

	public static void linkOffers(Player player) {
		for (int slot = 0; slot < player.getPOSManager().getOfferUIds().length; slot++) {
			PoSOffer offer = getOffer(player, slot);
			if (offer == null)
				continue;
			offer.link(slot, player);
			offer.update();
		}
	}

	public static void unlinkOffers(Player player) {
		for (int slot = 0; slot < player.getPOSManager().getOfferUIds().length; slot++) {
			PoSOffer offer = getOffer(player, slot);
			if (offer == null)
				continue;
			offer.unlink();
		}
	}

	public static PoSOffer getOffer(Player player, int slot) {
		synchronized (LOCK) {
			long uid = player.getPOSManager().getOfferUIds()[slot];
			if (uid == 0)
				return null;
			PoSOffer offer = OFFERS.get(uid);
			if (offer == null) {
				player.getPOSManager().getOfferUIds()[slot] = 0;
				return null;
			}
			return offer;
		}
	}

	public static void printOffers() {
		for (PoSOffer offers : OFFERS.values()) {
			if (offers == null)
				continue;
			System.out.println(
					offers.getName() + " x " + offers.getAmount() + ", Price Each: " + Utils.getFormattedNumber(offers.getPrice(), ',') + ", slot: " + offers.getSlot() + ", owner: " + offers.getOwner().getDisplayName());
		}
	}

	public static void testAddOffer(Player player, int slot, Item item, int price) {
		PoSOffer offer = new PoSOffer(item.getId(), item.getAmount(), price);
		offer.link(slot, player);
		player.getPOSManager().getOfferUIds()[slot] = createOffer(offer);
		System.out.println("Added offer: " + offer.getName() + " x " + offer.getAmount() + ", slot: " + slot + ", price: " + price);
	}

	public static void testRemoveOffer(Player player, int slot) {
		for (PoSOffer offer : OFFERS.values()) {
			if (offer == null)
				continue;
			if (offer.getSlot() == slot) {
				System.out.println("Removed offer: " + offer.getName() + " x " + offer.getAmount() + " + "
						+ offer.getOwner().getDisplayName());
				OFFERS.remove(player.getGeManager().getOfferUIds()[slot]);
				player.getPOSManager().getOfferUIds()[slot] = 0;
				offer.link(slot, player);
			}
		}
	}

	public static void removeAllOffers() {
		for (Entry<Long, PoSOffer> entry : OFFERS.entrySet()) {
			if (entry == null)
				continue;
			PoSOffer offer = entry.getValue();
			offer.forceRemove();
			offer.unlink();
		}
		System.out.println("All PlayerOwnedShops offers has been removed.");
	}

	private static long createOffer(PoSOffer offer) {
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
	}*/

}
