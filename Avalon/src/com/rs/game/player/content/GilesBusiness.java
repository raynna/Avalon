package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Rain
 * @Date - 11 Mar 2016
 *
 **/

public class GilesBusiness {

	private static int getPrices(Player player, int itemId) {
		ItemDefinitions defs = ItemDefinitions.getItemDefinitions(itemId);
		return (player.getTaskManager().hasCompletedAllTasks()
				? defs.getTipitPrice() * player.getInventory().getFreeSlots() - 50000
				: defs.getTipitPrice() * player.getInventory().getFreeSlots() + 50000);
	}

	public static boolean unnote(Player player, int itemId) {
		ItemDefinitions item = ItemDefinitions.getItemDefinitions(itemId);
		if (item != null) {
			if (!item.isNoted()) {
				player.getDialogueManager().startDialogue("SimpleMessage", "This item is already unnoted.");
				return false;
			}
			if (!player.getInventory().hasFreeSlots()) {
				player.getDialogueManager().startDialogue("SimpleMessage", "You need free inventory space.");
				return false;
			}
			if (item.getTipitPrice() >= 500000) {
				player.getDialogueManager().startDialogue("SimpleMessage",
						"This item is way too expensive to unnote, do it via the bank instead.");
				return false;
			}
			if (!player.hasMoney(getPrices(player, itemId))) {
				player.getDialogueManager().startDialogue("SimpleMessage", "You do not have enough money.");
				return false;
			}
			if (item.isNoted()) {
				if (player.hasMoney(getPrices(player, itemId))) {
					player.getPackets().sendFilteredGameMessage(true,
							player.getTaskManager().hasCompletedAllTasks()
									? "Giles lets you pay " + (Utils.getFormattedNumber(getPrices(player, itemId), ','))
											+ " GP, since you completed all Rains' tasks."
									: "Giles smiles while he unnotes " + player.getInventory().getFreeSlots() + " "
											+ item.getName());
					player.getMoneyPouch().removeMoneyMisc(getPrices(player, itemId));
					player.getInventory().deleteItem(itemId, player.getInventory().getFreeSlots());
					player.getInventory().addItem(itemId - 1, player.getInventory().getFreeSlots());
					return true;
				}
			}
		}
		return false;
	}

}
