package com.rs.game.player.actions.skills.firemaking;

import java.util.HashMap;
import java.util.Map;

import com.rs.game.item.Item;
import com.rs.game.player.Player;

public class FireLighter {

	public enum Lighters { 
		RED(7329, 7404),
		GREEN(7330, 7405),
		BLUE(7331, 7406),
		PURPLE(10326, 10329),
		WHITE(10327, 10328);
		

		private int lighterId;
		private int logId;

		private Lighters(int lighterId, int logId) {
			this.lighterId = lighterId;
			this.logId = logId;
		}

		private static Map<Integer, Lighters> firelighters = new HashMap<Integer, Lighters>();

		public static Lighters forId(int itemId) {
			return firelighters.get(itemId);
		}

		static {
			for (Lighters lighter : Lighters.values()) {
				firelighters.put(lighter.getLighterId(), lighter);
			}
		}

		public int getLighterId() {
			return lighterId;
		}

		public int getLogId() {
			return logId;
		}
	}

	public static void handleLighter(Player player, Lighters lighters) {
		if (lighters != null) {
				player.getInventory().deleteItem(lighters.getLighterId(), 1);
				player.getInventory().deleteItem(1511, 1);
				player.getInventory().addItem(new Item(lighters.getLogId(), 1));
		}
	}

	public static Lighters getColoredLog(int itemUsedId, int itemUsedWithId) {
		Lighters lighters = Lighters.forId(itemUsedId);
		int selected;
		if (lighters != null)
			selected = itemUsedWithId;
		else {
			lighters = Lighters.forId(itemUsedWithId);
			selected = itemUsedId;
		}
		return lighters != null && 1511 == selected ? lighters : null;
	}

}
