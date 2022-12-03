package com.rs.game.player.actions;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public class ClearBank {

	public static void clearBank(Player player) {
		for (int id = 0; id < Utils.getItemDefinitionsSize(); id++) {
			Item item = player.getBank().getItem(id);
			if (item == null)
				continue;
			player.getBank().removeItem(player.getBank().getItemSlot(item.getId()), item.getAmount(), true, false);
			player.getBank().refreshItems();
		}
	}
}
