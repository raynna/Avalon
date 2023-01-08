package com.rs.game.item.plugins;

import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemPlugin;
import com.rs.game.player.Player;
import com.rs.utils.Logger;

public class ExamplePlugin extends ItemPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] {ItemId.ABYSSAL_WHIP, "containsname" };
	}
	@Override
	public boolean processItem(Player player, Item item, int slotId, String option) {
		switch (option) {
			case "activate":
				//do activate
				return true;
			case "drop":
				//do drop
				return true;
		}
		return false;
	}
	@Override
	public boolean processItemOnItem(Player player, Item item, Item item2, int itemUsed, int usedWith) {
		return true;
	}
	@Override
	public boolean processDestroy(Player player, Item item, int slotId) {
		return true;
	}
}