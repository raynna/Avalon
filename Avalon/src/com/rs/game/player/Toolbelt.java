package com.rs.game.player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.rs.game.item.Item;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 14 Apr 2016
 * 
 */

public class Toolbelt implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8785635409322322662L;

	/** The player. */
	private transient Player player;

	/** The Constant TOOLBELT_ITEMS. */
	private static final int[] TOOLBELT_ITEMS = new int[] { 946, 1735, 1595, 1755, 1599, 1597, 1733, 1592, 5523, 13431,
			307, 309, 311, 301, 303, 1265, 2347, 1351, 590, -1, 8794, 4, 9434, 11065, 1785, 2976, 1594, 5343, 5325,
			5341, 5329, 233, 952, 305, 975, 11323, 2575, 2576, 13153, 10150 };

	/** The Constant Config_IDS. */
	private static final int[] Config_IDS = new int[] { 2438, 2439 };

	/** The toolbelt items. */
	public Map<Integer, Boolean> toolbeltItems;

	/**
	 * Instantiates a new toolbelt.
	 */
	public Toolbelt() {
		if (toolbeltItems == null)
			toolbeltItems = new HashMap<Integer, Boolean>();
		for (int items : TOOLBELT_ITEMS)
			toolbeltItems.put(items, false);

	}

	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	public void init() {
		refreshItems();
	}

	public void reset() {
		toolbeltItems.clear();
		toolbeltItems = new HashMap<Integer, Boolean>();
		for (int items : TOOLBELT_ITEMS)
			toolbeltItems.put(items, false);
		player.sm("Toolbelt items reset.");
	}

	/**
	 * Adds the item.
	 *
	 * @param item the item
	 * @return true, if successful
	 */
	public boolean addItem(Item item) {
		if (toolbeltItems.get(item.getId()) == null) {
			player.sm("item is null");
			return false;
		}
		if (toolbeltItems.get(item.getId())) {
			refreshItems();
			player.sm(item.getName() + " is already in your toolbelt.");
			return false;
		}
		player.getInventory().deleteItem(item);
		toolbeltItems.put(item.getId(), true);
		player.sm("The " + item.getName() + " has been added to your toolbelt.");
		refreshItems();
		return true;
	}

	public void printToolbelt() {
		player.sm("" + toolbeltItems.get(946));
	}

	/**
	 * Check storage.
	 *
	 * @param item the item
	 * @return true, if successful
	 */
	public static boolean checkStorage(int item) {
		for (int i = 0; i < TOOLBELT_ITEMS.length - 1; i++) {
			if (item == TOOLBELT_ITEMS[i])
				return true;
		}
		return false;
	}

	/**
	 * Check if the item is within the players' toolbelt.
	 *
	 * @param item the item
	 * @return true, if successful
	 */
	public boolean contains(int item) {
		if (toolbeltItems.get(item) == null) {
			return false;
		}
		return toolbeltItems.get(item);
	}

	/**
	 * Refresh items.
	 */
	public void refreshItems() {
		int[] configValue = new int[Config_IDS.length];
		for (int i = 0; i < TOOLBELT_ITEMS.length; i++) {
			if (toolbeltItems.get(TOOLBELT_ITEMS[i]) == null)
				continue;
			boolean inToolbelt = toolbeltItems.get(TOOLBELT_ITEMS[i]);
			if (!inToolbelt)
				continue;
			int index = (i / 20);
			configValue[index] |= 1 << (i - (index * 20));
		}
		for (int i = 0; i < Config_IDS.length; i++) {
			if (configValue[i] == 0)
				continue;
			player.getPackets().sendConfig(Config_IDS[i], configValue[i]);
		}
	}

	/**
	 * Open toolbelt.
	 */
	public void openToolbelt() {
		refreshItems();
		player.getInterfaceManager().sendInterface(1178);
	}

}
