package com.rs.game.player.dialogues;

import com.rs.game.item.Item;

public class ItemBanking extends Dialogue {

	/*
	 * @Author Tristam <Hassan> - TODO: BUG FIX If you bank noted items, it will
	 * stay noted inside the bank. * HAPPENS WITH QUICK BANKING ASWELL - TODO:
	 * CHECK IF PLAYER HAS BANK SPACE / MAX AMOUNT OF ITEM (QUICK BANKING)
	 */

	int amount;
	int itemId;

	@Override
	public void start() {
		itemId = (Integer) parameters[0];
		amount = (Integer) parameters[1];
		sendOptions("HOW MANY ITEMS WOULD YOU LIKE TO BANK?", "ONE", "FIVE", "X", "QUICK BANKING");
		stage = 1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		Item bankedItem = player.getBank().getItem(itemId);
		int check = player.getInventory().getAmountOf(itemId);
		switch (stage) {
		case END:
			end();
			break;
		case 1:
			switch (componentId) {
			case OPTION_1:
				if (bankedItem != null) {
					if (bankedItem.getDefinitions().isNoted()) {
						end();
						player.sm("You can't bank this item.");
						return;
					}
				}
				if (!player.getBank().hasBankSpace()) {
					player.sm("Not enough bank space.");
					end();
					return;
				}
				if (bankedItem != null) {
					if (bankedItem.getAmount() + amount <= 0 || bankedItem.getAmount() + check <= 0) {
						player.sm("Not enough space for " + bankedItem.getName() + ".");
						end();
						return;
					}
				}
				player.getInventory().deleteItem(itemId, 1);
				player.getBank().addItem(itemId, 1, true);
				end();
				break;
			case OPTION_2:
				if (bankedItem != null) {
					if (bankedItem.getDefinitions().isNoted()) {
						end();
						player.sm("You can't bank this item.");
						return;
					}
				}
				if (!player.getBank().hasBankSpace()) {
					player.sm("Not enough bank space.");
					end();
					return;
				}
				if (bankedItem != null) {
					if (bankedItem.getAmount() + amount <= 0 || bankedItem.getAmount() + check <= 0) {
						player.sm("Not enough space for " + bankedItem.getName() + ".");
						end();
						return;
					}
				}
				if (amount < 5) {
					player.getInventory().deleteItem(itemId, amount);
					player.getBank().addItem(itemId, amount, true);
				} else {
					player.getInventory().deleteItem(itemId, 5);
					player.getBank().addItem(itemId, 5, true);
				}
				end();
				break;
			case OPTION_3:
				player.temporaryAttribute().put("bankAmount", itemId);
				player.getPackets().sendInputIntegerScript(true, "ENTER THE AMOUNT YOU WISH TO BANK: ");
				end();
				break;
			case OPTION_4:
				sendDialogue(
						"Quick banking will allow you to use an item on any bank to <col=ff0000>Quick bank it.</col><br>However, you can only bank one item at once. "
								+ "You can disable this at any time with the account manager.");
				stage = 2;
			}
			break;
		case 2:
			sendOptions("Do you wish to use quick banking?", "Yes", "No");
			stage = 3;
			break;
		case 3:
			switch (componentId) {
			case OPTION_1:
				sendDialogue("You will now Quick bank items.");
				stage = END;
				break;
			case OPTION_2:
				end();
			}
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}