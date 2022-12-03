package com.rs.game.player.content;

import java.io.Serializable;
import java.text.DecimalFormat;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * 
 * @Improved Nathan - Wilderness PK
 * 
 */

public class MoneyPouch implements Serializable {

	private static final long serialVersionUID = -3847090682601697992L;

	private transient Player player;

	public MoneyPouch(Player player) {
		this.player = player;
	}

	public void addMoneyFromInventory(int amount, boolean delete) {
		if (player.getInventory().getNumberOf(995) > Integer.MAX_VALUE - getTotal()) {
			amount = Integer.MAX_VALUE - getTotal();
		}
		if (getTotal() == Integer.MAX_VALUE) {
			player.getPackets().sendGameMessage("You can't store more in your money pouch.");
			return;
		}
		if (amount > 1) {
			player.getPackets().sendGameMessage(
					Utils.getFormattedNumber(amount, ',') + " coins have been added to your money pouch.");
		} else {
			player.getPackets().sendGameMessage("One coin has been added to your money pouch.");
		}
		player.getPackets().sendRunScript(5561, 1, amount);
		player.getMoneyPouch().setTotal(player.getMoneyPouch().getTotal() + amount);
		if (delete) {
			player.getInventory().deleteItem(new Item(995, amount));
		}
		refresh();
	}

	public void addMoneyFromBank(int amount, int bankSlot) {
		// if (!player.getControlerManager().processMoneyPouch())
		// return;
		int leftOver = 0;
		if (player.getMoneyPouchValue() + amount < 0 || player.getMoneyPouchValue() + amount >= Integer.MAX_VALUE) {
			leftOver = Integer.MAX_VALUE - player.getMoneyPouchValue();
			if (player.getMoneyPouchValue() != Integer.MAX_VALUE) {
				player.getPackets().sendRunScript(5561, 1, leftOver);
				player.getMoneyPouch().setTotal(Integer.MAX_VALUE);
			}
			player.getBank().removeItem2(bankSlot, leftOver, true, false);
			amount = amount - leftOver;
			refresh();
			if (player.getInventory().getNumberOf(995) == Integer.MAX_VALUE) {
				player.getPackets().sendGameMessage("You don't have enough inventory space.");
				return;
			}
			// player.getPackets().sendGameMessage("Full pouch:amount - " +
			// amount + ":leftOver - " + leftOver);
			if (player.getInventory().getNumberOf(995) + amount < 0
					|| player.getInventory().getNumberOf(995) + amount >= Integer.MAX_VALUE) {
				leftOver = Integer.MAX_VALUE - player.getInventory().getNumberOf(995);
				player.getInventory().deleteItem(995, Integer.MAX_VALUE);
				player.getInventory().addItem(995, Integer.MAX_VALUE);
				player.getBank().removeItem2(bankSlot, leftOver, true, false);
				// player.getPackets().sendGameMessage("Full inventory:amount -
				// " + amount + ":leftOver - " + leftOver);
				return;
			} else
				player.getBank().removeItem2(bankSlot, amount, true, false);
			player.getInventory().addItem(995, amount);
			refresh();
			// player.getPackets().sendGameMessage("Regular add inventory");
		} else {
			player.getBank().removeItem2(bankSlot, amount, true, false);
			player.getPackets().sendRunScript(5561, 1, amount);
			player.getMoneyPouch().setTotal(player.getMoneyPouch().getTotal() + amount);
			// player.getPackets().sendGameMessage("Regular add pouch");
			refresh();
		}
	}

	public void addMoney(int amount, boolean delete) {
		// if (!player.getControlerManager().processMoneyPouch())
		// return;
		if (delete) {
			if (player.getInventory().getNumberOf(995) > Integer.MAX_VALUE - getTotal()) {
				amount = Integer.MAX_VALUE - getTotal();
			}
		}
		int leftOver = 0;
		int inventoryLeftOver = 0;
		if (getTotal() + amount > Integer.MAX_VALUE || getTotal() + amount < 0) {
			if (getTotal() != Integer.MAX_VALUE)
				player.getPackets().sendGameMessage("Your money pouch is not big enough to hold that much cash.");
			leftOver = Integer.MAX_VALUE - getTotal();
			amount = amount - leftOver;
			if (getTotal() != Integer.MAX_VALUE) {
				player.getPackets().sendRunScript(5561, 1, leftOver);
				player.getMoneyPouch().setTotal(Integer.MAX_VALUE);
				refresh();
			}
			if (player.getInventory().getNumberOf(995) + amount > Integer.MAX_VALUE
					|| player.getInventory().getNumberOf(995) + amount < 0) {
				inventoryLeftOver = Integer.MAX_VALUE - player.getInventory().getNumberOf(995);
				amount = amount - inventoryLeftOver;
				if (player.getInventory().getNumberOf(995) != Integer.MAX_VALUE)
					player.getInventory().addItem(995, inventoryLeftOver);
				if (delete) {
					World.addGroundItem(new Item(995, amount), player, player, true, 60);
					player.getInventory().deleteItem(995, amount);
				} else {
					World.updateGroundItem(new Item(995, amount), new WorldTile(player), player, 60, 0);
				}
				return;
			} else {
				if (delete) {
					player.getInventory().addItem(new Item(995, amount));
					player.getInventory().deleteItem(995, amount);
				} else
					player.getInventory().addItem(new Item(995, amount));
				return;
			}
		}
		if (getTotal() == Integer.MAX_VALUE) {
			return;
		}
		if (amount > 1) {
			player.getPackets().sendGameMessage(
					Utils.getFormattedNumber(amount, ',') + " coins have been added to your money pouch.");
		} else {
			player.getPackets().sendGameMessage("One coin has been added to your money pouch.");
		}
		player.getPackets().sendRunScript(5561, 1, amount);
		player.getMoneyPouch().setTotal(player.getMoneyPouch().getTotal() + amount);
		if (delete) {
			player.getInventory().deleteItem(new Item(995, amount));
		}
		refresh();
	}

	public void addMoneyMisc(int amount) {
		if (getTotal() + amount > Integer.MAX_VALUE || getTotal() + amount < 0) {
			player.getPackets().sendGameMessage("Your money pouch is not big enough to hold that much cash.");
			World.addGroundItem(new Item(995, amount), player, player, true, 60);
			return;
		}
		if (amount > 1) {
			player.getPackets().sendGameMessage(
					Utils.getFormattedNumber(amount, ',') + " coins have been added to your money pouch.");
		} else {
			player.getPackets().sendGameMessage("One coin has been added to your money pouch.");
		}
		player.getPackets().sendRunScript(5561, 1, amount);
		player.getMoneyPouch().setTotal(player.getMoneyPouch().getTotal() + amount);
		refresh();
	}

	public void addOverFlowMoney(int amount) {
		if (getTotal() + amount > Integer.MAX_VALUE || getTotal() + amount < 0) {
			player.getPackets().sendGameMessage("Your money pouch is not big enough to hold that much cash.");
			World.addGroundItem(new Item(995, amount), player, player, true, 60);
			return;
		}
		if (getTotal() != Integer.MAX_VALUE) {
			if (amount + getTotal() < 0) {
				amount = Integer.MAX_VALUE - getTotal();
			}
			if (amount > 1) {
				player.getPackets().sendGameMessage(
						Utils.getFormattedNumber(amount, ',') + " coins have been added to your money pouch.");
			} else {
				player.getPackets().sendGameMessage("One coin has been added to your money pouch.");
			}
			player.getPackets().sendRunScript(5561, 1, amount);
			player.getMoneyPouch().setTotal(player.getMoneyPouch().getTotal() + amount);
			refresh();
		} else {
			player.getPackets().sendGameMessage("Money pouch overflow.");
		}
	}

	@SuppressWarnings("unused")
	private String getFormattedNumber(int amount) {
		return new DecimalFormat("#,###,##0").format(amount).toString();
	}

	public int getTotal() {
		return player.getMoneyPouchValue();
	}

	public void refresh() {
		player.getPackets().sendRunScript(5560, player.getMoneyPouchValue());
	}

	public boolean removeMoneyMisc(int amount) {
		if (amount <= 0 || getTotal() == 0) {
			return false;
		}
		if (getTotal() < amount) {
			amount = getTotal();
		}
		player.getPackets().sendGameMessage(
				Utils.getFormattedNumber(amount, ',') + " coins have been removed from your money pouch.");
		player.getMoneyPouch().setTotal(player.getMoneyPouch().getTotal() - amount);
		player.getPackets().sendRunScript(5561, 0, amount);
		refresh();
		return true;
	}

	public boolean takeMoneyFromPouch(int amount) {
		if (getTotal() - amount < 0)
			amount = getTotal();
		if (amount == 0)
			return false;
		setTotal(getTotal() - amount);
		player.getPackets().sendRunScript(5561, 0, amount);
		player.getPackets()
				.sendGameMessage((amount == 1 ? "One" : Utils.getFormattedNumber(amount, ',')) + " coin"
						+ (amount == 1 ? "" : "s") + " " + (amount == 1 ? "has" : "have")
						+ " been withdrawn to your money pouch.");
		refresh();
		return true;
	}

	public void sendExamine() {
		player.getPackets().sendGameMessage("Your money pouch current contains "
				+ Utils.getFormattedNumber(player.getMoneyPouchValue(), ',') + " coins.");
	}

	public void setTotal(int amount) {
		player.setMoneyPouchValue(amount);
	}

	public void withdrawPouch(int amount) {
		if (amount <= 0)
			return;
		if (getTotal() <= 0) {
			player.getPackets().sendGameMessage("You don't have any money stored in your money pouch.");
			return;
		}
		if (player.getInventory().getFreeSlots() == 0 && player.getInventory().getNumberOf(995) < 1
				|| player.getInventory().getNumberOf(995) == Integer.MAX_VALUE) {
			player.getPackets().sendGameMessage("You don't have enough inventory space.");
			return;
		}
		if (player.getInventory().getNumberOf(995) > Integer.MAX_VALUE - amount) {
			amount = Integer.MAX_VALUE - player.getInventory().getNumberOf(995);
		}
		if (amount > getTotal()) {
			amount = getTotal();
		}
		if (amount > 1) {
			player.getPackets().sendGameMessage(
					Utils.getFormattedNumber(amount, ',') + " coins have been removed from your money pouch.");
		} else {
			player.getPackets().sendGameMessage("One coin has been removed from your money pouch.");
		}
		player.getMoneyPouch().setTotal(player.getMoneyPouch().getTotal() - amount);
		player.getInventory().addItem(new Item(995, amount));
		player.getPackets().sendRunScript(5561, 0, amount);
		refresh();
	}

	public boolean cantAdd() {
		return getTotal() == Integer.MAX_VALUE;
	}
}