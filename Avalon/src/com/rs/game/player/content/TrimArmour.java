package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public class TrimArmour {

	public enum TrimmedPieces {

		RUNE_PLATEBODY(1127, 100000, new Item(2615), new Item(2623), new Item(2653), new Item(2661), new Item(2669),
				new Item(3481), new Item(19179), new Item(19200), new Item(19221), new Item(19242), new Item(19263)),
		
		GREEN_DHIDE_BODY(1135, 50000, new Item(7370), new Item(7372));

		private int itemId;

		/**
		 * The possible loot.
		 */
		private final Item[] loot;
		private int price;

		private TrimmedPieces(int itemId, int price, Item... loot) {
			this.itemId = itemId;
			this.price = price;
			this.loot = loot;
		}

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		public Item[] getPieces() {
			return loot;
		}

		public int getPrice() {
			return price;
		}

	}

	public static void trimArmour(Player player, int id) {
		for (TrimmedPieces pieces : TrimmedPieces.values()) {
			if (id == pieces.getItemId()) {
				if (player.getMoneyPouch().getTotal() < pieces.getPrice()
						&& player.getInventory().getNumberOf(995) < pieces.getPrice()) {
					player.getPackets().sendGameMessage("You don't have enough coins to trim "
							+ ItemDefinitions.getItemDefinitions(pieces.getItemId()).getName());
					return;
				}
				if (player.getMoneyPouch().getTotal() >= pieces.getPrice())
					player.getMoneyPouch().removeMoneyMisc(pieces.getPrice());
				else
					player.getInventory().deleteItem(995, pieces.getPrice());
				player.getInventory().deleteItem(pieces.getItemId(), 1);
				Item item = pieces.getPieces()[Utils.random(pieces.getPieces().length)];
				player.getInventory().addItem(item.getId(), item.getAmount());
			}
		}
	}

	public static int getPrice(Player player, int id) {
		for (TrimmedPieces pieces : TrimmedPieces.values()) {
			if (id == pieces.getItemId()) {
				return pieces.getPrice();
			}
		}
		return 0;
	}

}
