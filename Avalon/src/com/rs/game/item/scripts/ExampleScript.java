package com.rs.game.item.scripts;

import com.rs.game.item.Item;
import com.rs.game.player.Player;

public class ExampleScript extends com.rs.game.item.ItemScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { -1, null };
	}
	@Override
	public boolean processItem(Player player, Item item, int slotId) {
		return true;
	}
	@Override
	public boolean processItem2(Player player, Item item, int slotId) {
		return true;
	}
	@Override
	public boolean processItem3(Player player, Item item, int slotId) {
		return true;
	}
	@Override
	public boolean processItem4(Player player, Item item, int slotId) {
		return true;
	}
	@Override
	public boolean processItem5(Player player, Item item, int slotId) {
		return true;
	}
	@Override
	public boolean processItem6(Player player, Item item, int slotId) {
		return true;
	}
	@Override
	public boolean processItemOnItem(Player player, Item item, Item item2, int itemUsed, int usedWith) {
		return true;
	}
	@Override
	public boolean processDrop(Player player, Item item, int slotId) {
		return true;
	}
	@Override
	public boolean processDestroy(Player player, Item item, int slotId) {
		return true;
	}
}