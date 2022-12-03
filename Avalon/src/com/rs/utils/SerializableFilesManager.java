package com.rs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.rs.game.player.content.clans.Clan;
import com.rs.game.player.content.grandexchange.Offer;
import com.rs.game.player.content.grandexchange.OfferHistory;

public class SerializableFilesManager {

	private static final String CLAN_PATH = "data/clans/";
	public static final String GE_OFFERS = "data/GE/grandexchange/grandExchangeOffers.ser";
	private static final String GE_OFFERS_HISTORY = "data/GE/grandexchange/grandExchangeOffersTrack.ser";
	private static final String GE_PRICES = "data/GE/grandexchange/grandExchangePrices.ser";
	private static final String DISPLAY_NAMES = "displayNames.ser";

	@SuppressWarnings("unchecked")
	public static synchronized HashMap<Long, Offer> loadGEOffers() {
		if (new File(GE_OFFERS).exists()) {
			try {
				return (HashMap<Long, Offer>) loadSerializedFile(new File(GE_OFFERS));
			} catch (Throwable t) {
				Logger.handle(t);
				return null;
			}
		} else {
			return new HashMap<Long, Offer>();
		}
	}

	@SuppressWarnings("unchecked")
	public static synchronized ArrayList<OfferHistory> loadGEHistory() {
		if (new File(GE_OFFERS_HISTORY).exists()) {
			try {
				return (ArrayList<OfferHistory>) loadSerializedFile(new File(GE_OFFERS_HISTORY));
			} catch (Throwable t) {
				Logger.handle(t);
				return null;
			}
		} else {
			return new ArrayList<OfferHistory>();
		}
	}

	@SuppressWarnings("unchecked")
	public static synchronized HashMap<Integer, Integer> loadGEPrices() {
		if (new File(GE_PRICES).exists()) {
			try {
				return (HashMap<Integer, Integer>) loadSerializedFile(new File(GE_PRICES));
			} catch (Throwable t) {
				Logger.handle(t);
				return null;
			}
		} else {
			return new HashMap<Integer, Integer>();
		}
	}

	public synchronized static boolean containsClan(String name) {
		return new File(CLAN_PATH + name + ".c").exists();
	}

	public synchronized static Clan loadClan(String name) {
		try {
			return (Clan) loadSerializedFile(new File(CLAN_PATH + name + ".c"));
		} catch (Throwable e) {
			Logger.handle(e);
		}
		return loadClan(name);
	}

	public synchronized static void saveClan(Clan clan) {

		if (new File(CLAN_PATH + clan.getClanName() + ".c").exists()) {
			try {
				storeSerializableClass(clan, new File(CLAN_PATH + clan.getClanName() + ".c"));
			} catch (Throwable t) {
				Logger.handle(t);
			}
		} else {
			try {
				storeSerializableClass(clan, new File(CLAN_PATH + clan.getClanName() + ".c"));
			} catch (Throwable t) {
				Logger.handle(t);
			}
		}
	}

	public synchronized static void deleteClan(Clan clan) {
		try {
			new File(CLAN_PATH + clan.getClanName() + ".c").delete();
		} catch (Throwable t) {
			Logger.handle(t);
		}
	}

	public synchronized static void deleteOffers() {
		try {
			new File(GE_OFFERS).delete();
		} catch (Throwable t) {
			Logger.handle(t);
		}
	}

	public static synchronized void saveGEOffers(final HashMap<Long, Offer> offers) {
		try {
			storeSerializableClass(offers, new File(GE_OFFERS));
		} catch (Throwable t) {
			Logger.handle(t);
		}
	}

	public static synchronized void saveGEHistory(ArrayList<OfferHistory> history) {
		try {
			storeSerializableClass(history, new File(GE_OFFERS_HISTORY));
		} catch (Throwable t) {
			Logger.handle(t);
		}
	}

	public static synchronized void saveGEPrices(HashMap<Integer, Integer> prices) {
		try {
			storeSerializableClass(prices, new File(GE_PRICES));
		} catch (Throwable t) {
			Logger.handle(t);
		}
	}


	@SuppressWarnings("unchecked")
	public static synchronized ArrayList<String> loadDisplayNames() {
		if (new File(DISPLAY_NAMES).exists()) {
			try {
				return (ArrayList<String>) loadSerializedFile(new File(DISPLAY_NAMES));
			} catch (Throwable t) {
				Logger.handle(t);
				return null;
			}
		} else {
			return new ArrayList<String>();
		}
	}

	public static synchronized void saveDisplayNames(ArrayList<String> names) {
		try {
			SerializableFilesManager.storeSerializableClass(names, new File(DISPLAY_NAMES));
		} catch (Throwable t) {
			Logger.handle(t);
		}
	}

	public static final Object loadSerializedFile(File f) throws IOException, ClassNotFoundException {
		if (!f.exists())
			return null;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
		Object object = in.readObject();
		in.close();
		return object;
	}

	public static final void storeSerializableClass(Serializable o, File f) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
		out.writeObject(o);
		out.close();
	}

	private SerializableFilesManager() {

	}
}
