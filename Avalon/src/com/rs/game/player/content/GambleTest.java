package com.rs.game.player.content;

import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 28 Apr 2016 - Test purposes, however can be used if you want.
 * 
 */

public class GambleTest {

	/** Chance of winning (Currently 1/15) */
	private static int chance = 85;

	/** The constant integer, Gamble. */
	private static int gamble;

	/**
	 * Intiaties a gamble.
	 *
	 * @param player
	 *            the player
	 * @param money
	 *            the amount
	 */
	public static void Gamble(Player player, int money) {
		if (money < 1000000 || money > 100000000) {
			player.sm("Min: 1m | Max: 100m");
			return;
		}
		if (!player.getInventory().containsItem(995, money) && player.getMoneyPouch().getTotal() < money) {
			player.sm("You don't have the money to do that.");
			return;
		}
		if (player.getInventory().containsItem(995, (Integer.MAX_VALUE - money))
				|| player.getInventory().containsItem(995, Integer.MAX_VALUE)) {
			player.sm("You cannot do that, bank some money first.");
			return;
		}
		if (player.isLocked()) {
			player.sm("Please finish with what you are doing.");
			return;
		}
		if (player.isInCombat(10000)) {
			player.sm("You can't do this while in combat.");
			return;
		}
		if (player.getInventory().getFreeSlots() < 3) {
			player.sm("Please have at least 3 free inventory spaces.");
			return;
		}
		if (player.getMoneyPouch().getTotal() >= money)
			player.getMoneyPouch().removeMoneyMisc(money);
		else
		player.getInventory().deleteItem(995, money);
		player.lock(4);
		player.sm("<col=ff0000>Rolling.... Goodluck!");
		WorldTasksManager.schedule(new WorldTask() {
			int loop = 3;

			@Override
			public void run() {
				if (loop == 0) {
					stop();
					Roll(100);
					Check(player, money);
				}
				loop--;
			}
		}, 0, 1);
	}

	/**
	 * Checks if player won.
	 *
	 * @param player
	 *            the player
	 * @param money
	 *            the money
	 */
	public static void Check(Player player, int money) {
		if (!isWinner()) {
			player.sm("You lost! :( You rolled <col=ff0000>" + getNumberRolled()
					+ "</col>. <br>Had you rolled <col=ff0000>" + (chance - getNumberRolled())
					+ "</col> more you would have won.");
			return;
		}
		player.gfx(new Graphics(743));
		player.getMoneyPouch().addMoney(Prize(money), false);
		player.sm("<col=ff000>You rolled " + getNumberRolled() + ", Congratulations. You have won "
				+ Utils.getFormattedNumber((Prize(money) / 2), ',') + " million GP.");
		if (money >= 50000000) {
			World.sendWorldMessage("<img=7><col=ff000>News: " + player.getDisplayName() + " has just won "
					+ Utils.formatDoubledAmount(money) + " from gambling!", false);
		}
		return;
	}

	/**
	 * Rolls between numbers.
	 *
	 * @param amount
	 *            the numbers rolled
	 * @return the int
	 */
	public static int Roll(int amount) {
		return gamble = Utils.random(amount);
	}

	/**
	 * Gets the number rolled.
	 *
	 * @return the number rolled
	 */
	public static int getNumberRolled() {
		return gamble;
	}

	/**
	 * Checks if gamble is greater thag)=
	 * n chance.
	 *
	 * @return true, if gamble is greater.
	 */
	public static boolean isWinner() {
		return gamble >= chance;
	}

	/**
	 * The money gambled * 2.
	 * 
	 *
	 * @param value
	 *            the money
	 * @return the int
	 */
	public static int Prize(int value) {
		return value * 2;
	}

}
