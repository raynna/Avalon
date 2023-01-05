package com.rs.game.item;

import com.rs.game.player.Player;

public abstract class ItemPlugin {
	
	public abstract Object[] getKeys();

	public boolean processItem(Player player, Item item, int slotId) {
		return false;
	}
	
	public boolean processItem2(Player player, Item item, int slotId) {
		return false;
	}
	
	public boolean processItem3(Player player, Item item, int slotId) {
		return false;
	}
	
	public boolean processItem4(Player player, Item item, int slotId) {
		return false;
	}
	
	public boolean processItem5(Player player, Item item, int slotId) {
		return false;
	}
	public boolean processItem6(Player player, Item item, int slotId) {
		return false;
	}
	public boolean processDrop(Player player, Item item, int slotId) {
		return false;
	}

	public boolean processDestroy(Player player, Item item, int slotId) {
		return false;
	}

	public boolean processItemOnItem(Player player, Item item, Item item2, int fromSlot, int toSlot) {
		return false;
	}

	public boolean isItem(Item item, Item item2, int itemId) {
		return item.getId() == itemId || item2.getId() == itemId;
	}
	public boolean isCompatible(Item item, Item item2, String string) {
		return item.getName().contains(string) || item2.getName().contains(string);
	}

	public boolean isCompatible(Item item, Item item2, String string, String exclude) {
		System.out.println(item.getName() + " -  "+item2.getName() +  " " + exclude + "");
		return (item.getName().contains(string.toLowerCase()) && !item.getName().toLowerCase().contains(exclude.toLowerCase())) || item2.getName().toLowerCase().contains(string.toLowerCase()) && !item2.getName().toLowerCase().contains(exclude.toLowerCase());
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