package com.rs.game.item.plugins.misc;

import com.rs.game.item.Item;
import com.rs.game.item.ItemPlugin;
import com.rs.game.player.Player;

public class WildstalkerHelmet extends ItemPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Wildstalker" };
	}
	@Override
	public boolean processItem(Player player, Item item, int slotId, String option) {
		switch (option) {
			case "options":
				player.getDialogueManager().startDialogue("WildStalkerHelmet");
				return true;
			case "change-look":
				if (player.getKillCount() < 10) {
					player.message("You can't change the look of your wildstalker helmet until you earned additional tiers.");
					player.message("You need at least ten wilderness kills.");
					return true;
				} else if (player.getKillCount() > 99) {
					player.getDialogueManager().startDialogue("WildStalkerTier1");
					return true;
				}
				player.getInventory().deleteItem(slotId, item);
				player.getInventory().addItem(new Item(item.getId() + 1, item.getAmount()));
				return true;
		}
		return false;
	}
}