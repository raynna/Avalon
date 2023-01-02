package com.rs.game.objects.scripts;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;

public class SaradominShortcuts extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 26445, 26298, 26444 };
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		if (!player.getSavedVarBits().containsKey(3934)) {
			player.sm("You have to tie a rope around the rock.");
			return false;
		}
		if (object.getId() == 26444) {
			player.setNextWorldTile(new WorldTile(2914, 5300, 1));
			return false;
		}
		player.setNextWorldTile(new WorldTile(2920, 5276, 1));
		return true;
	}

	@Override
	public boolean processObject2(Player player, WorldObject object) {
		player.setNextWorldTile(new WorldTile(2919, 5273, 0));
		return true;
	}

	@Override
	public boolean processItemOnObject(Player player, WorldObject object, Item item) {
		if (item.getId() != 954)
			return false;
		if (player.getSavedVarBits().containsKey(3934)) {
			player.sm("There is already a rope tied to this rock.");
			return false;
		}
		player.getInventory().deleteItem(item.getId(), 1);
		player.getVarsManager().sendVarBit(3934, 1, true);
		return true;
	}
}
