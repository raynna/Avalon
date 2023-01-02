package com.rs.game.item;

import com.rs.Settings;
import com.rs.game.player.Player;
import com.rs.utils.Logger;

public abstract class ItemScripts {
	
	public abstract Object[] getKeys();

	public boolean processItem(Player player, Item item, int slotId) {
		if (Settings.DEBUG)
			System.out.println("ItemScript: ItemClick 1 - ItemId: " + item.getId());
		return false;
	}
	
	public boolean processItem2(Player player, Item item, int slotId) {
		if (Settings.DEBUG)
			Logger.log("ItemScript", this.getClass().getSimpleName()
					+ ", ItemClick 2 - ItemId: " + item.getId());
		return false;
	}
	
	public boolean processItem3(Player player, Item item, int slotId) {
		if (Settings.DEBUG)
			Logger.log("ItemScript", this.getClass().getSimpleName()
					+ ", ItemClick 3 - ItemId: " + item.getId());
		return false;
	}
	
	public boolean processItem4(Player player, Item item, int slotId) {
		if (Settings.DEBUG)
			Logger.log("ItemScript", this.getClass().getSimpleName()
					+ ", ItemClick 4 - ItemId: " + item.getId());
		return false;
	}
	
	public boolean processItem5(Player player, Item item, int slotId) {
		if (Settings.DEBUG)
			Logger.log("ItemScript", this.getClass().getSimpleName()
					+ ", ItemClick 5 - ItemId: " + item.getId());
		return false;
	}
	public boolean processItem6(Player player, Item item, int slotId) {
		if (Settings.DEBUG)
			Logger.log("ItemScript", this.getClass().getSimpleName()
					+ ", ItemClick 6 - ItemId: " + item.getId());
		return false;
	}
	public boolean processDrop(Player player, Item item, int slotId) {
		if (Settings.DEBUG)
			Logger.log("ItemScript", this.getClass().getSimpleName()
					+ ", ItemClick 7 - ItemId: " + item.getId());
		return false;
	}

	public boolean processDestroy(Player player, Item item, int slotId) {
		if (Settings.DEBUG)
			Logger.log("ItemScript", this.getClass().getSimpleName()
					+ ", ItemClick 7 - ItemId: " + item.getId());
		return false;
	}

	public boolean processItemOnItem(Player player, Item item, Item item2, int fromSlot, int toSlot) {
		if (Settings.DEBUG)
			System.out.println("ItemOnItem - ItemId: " + item.getId() + ", ItemId2: " + item2.getId());
		return false;
	}

	public boolean contains(int id1, int id2, Item... items) {
		boolean containsId1 = false;
		boolean containsId2 = false;
		for (Item item : items) {
			if (item.getId() == id1)
				containsId1 = true;
			else if (item.getId() == id2)
				containsId2 = true;
		}
		return containsId1 && containsId2;
	}

}
