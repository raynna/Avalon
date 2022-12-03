package com.rs.game.player.content.grandexchange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.rs.cache.loaders.ItemDefinitions.FileUtilities;

/**
 * @author Arno
 */
public class UnlimitedGEReader {

	private static ArrayList<Integer> items = new ArrayList<Integer>();
	private final static String TXT_PATH = "./data/GE/unlimitedItems.txt";
	private static FileReader fr;

	public static void init() {
		try {
			initReaders();
			readToStoreCollection();
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	private static void readToStoreCollection() throws Exception {
		// StringBuffer names = new StringBuffer();
		for (String lines : FileUtilities.readFile(TXT_PATH)) {
			items.add(Integer.parseInt(lines));
			// names.append("\n[Unlimited
			// Items]"+ItemDefinitions.getItemDefinitions(Integer.parseInt(lines)).getName()).append(",
			// ");
		}
		// System.out.println("[Launcher] Initiated " + items.size() + " Unlimited
		// items.");
		// System.out.println("[Launcher] Initiated items: " +
		// names.replace(names.length() - 2, names.length(), "").toString());
	}

	private static void initReaders() throws Exception {
		fr = new FileReader(TXT_PATH);
		new BufferedReader(fr);
	}

	public static ArrayList<Integer> getLimitedItems() {
		return items;
	}

	public static void reloadLimiteditems() {
		try {
			items.clear();
			reloadReaders();
			readToStoreCollection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void reloadReaders() throws Exception {
		fr = null;
		initReaders();

	}

	public static boolean itemIsLimited(int itemId) {
		for (int i = 0; i < items.size(); i++) {
			if (itemId == items.get(i)) {
				return true;
			}
		}
		return false;
	}

}
