package com.rs.game.item.plugins.skilling;

import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.runecrafting.RunecraftingPouches;

public class RunecraftingPouch extends ItemPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] {ItemId.SMALL_POUCH, ItemId.MEDIUM_POUCH, ItemId.LARGE_POUCH, ItemId.GIANT_POUCH };
	}

	public int getPouchSize(Item item) {
		int size = -1;
		if (item.getId() == ItemId.SMALL_POUCH)
			size = 0;
		if (item.getId() == ItemId.MEDIUM_POUCH)
			size = 1;
		if (item.getId() == ItemId.LARGE_POUCH)
			size = 2;
		if (item.getId() == ItemId.GIANT_POUCH)
			size = 3;
		return size;
	}

	@Override
	public boolean processItem(Player player, Item item, int slotId, String option) {
		int pouch = getPouchSize(item);
		switch (option) {
			case "check":
				RunecraftingPouches.checkPouch(player, pouch);
				return true;
			case "fill":
				RunecraftingPouches.fillPouch(player, pouch);
				return true;
			case "empty":
				RunecraftingPouches.emptyPouch(player, pouch);
				return true;
		}
		return false;
	}
}
