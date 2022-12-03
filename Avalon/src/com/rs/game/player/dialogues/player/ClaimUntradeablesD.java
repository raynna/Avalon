package com.rs.game.player.dialogues.player;

import com.rs.game.item.Item;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.net.decoders.handlers.ButtonHandler;
import com.rs.utils.Utils;

public class ClaimUntradeablesD extends Dialogue {

	int itemId;
	int slotId;
	int price;
	boolean bank;

	public static final BuyBackPrice[] items = BuyBackPrice.values();

	@Override
	public void start() {
		stage = 0;
		itemId = (int) parameters[0];
		slotId = (int) parameters[1];
		bank = (boolean) parameters[2];
		Item item = new Item(itemId);
		/*
		 * for (BuyBackPrice items : BuyBackPrice.values()) { if (items == null)
		 * continue; if (items.getItemId() == itemId) { price = items.getPrice(); }
		 * }//UNUSED, GRABS PRICE FROM CACHE
		 */
		sendOptionsDialogue(
				item.getName() + " will cost you : "
						+ Utils.getFormattedNumber(item.getDefinitions().getHighAlchPrice(), ',') + " gp.",
				"Buy back.", "Dont buy back.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		Item item = new Item(itemId);
		int alchPrice = item.getDefinitions().getHighAlchPrice();
		switch (stage) {
		case 0:
			switch (componentId) {
			case OPTION_1:
				if (item.getDefinitions().getHighAlchPrice() > 0) {
					if (player.canBuy(alchPrice)) {
						boolean added = true;
						if (!item.getDefinitions().isStackable() || item.getAmount() < 2) {
							if (bank)
								player.getBank().addItem(item, true);
							else
								player.getInventory().addItem(item);
							player.getPackets().sendGameMessage(item.getDefinitions().getName() + " has been added to "
									+ (bank ? "bank." : "inventory."));
							player.getUntradeables().toArray()[slotId] = null;
							player.getUntradeables().shift();
							end();
						} else {
							for (int i = 0; i < item.getAmount(); i++) {
								Item single = new Item(item.getId());
								if (!player.getInventory().addItem(single)) {
									added = false;
									break;
								}
								player.getUntradeables().remove(single);
							}
						}
						if (!added) {
							player.getPackets().sendGameMessage(
									"You only had enough space in your inventory to accept some of the items.");
							break;
						}
						ButtonHandler.refreshUntradeables(player);
					} else {
						player.getPackets()
								.sendGameMessage("You don't have enough coins to buy " + item.getName() + " back.");
						end();
						break;
					}
				} else {
					player.getPackets().sendGameMessage(
							"This item has no price therefor you're unable to buy it, contact developer.");
					end();
				}
				break;
			case OPTION_2:
				end();
				break;
			}
			break;
		}
	}

	public enum BuyBackPrice {// UNUSED, GRABS PRICE FROM CACHE

		TOKHAAR_KAL(23659, 100000), ARCANE_STREAM_NECKLACE(18335, 150000), FIRE_CAPE(6570, 50000);

		private int itemId;

		private int price;

		private BuyBackPrice(int itemId, int price) {
			this.itemId = itemId;
			this.price = price;
		}

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
