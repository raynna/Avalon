package com.rs.game.objects.impl;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;

public class KalphiteLairEntrance extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 48802, 48803 };
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		if (object.getId() == 48802)
			player.setNextWorldTile(new WorldTile(3484, 9510, 2));
		else
			player.setNextWorldTile(new WorldTile(3508, 9493, 0));
		return true;
	}

	@Override
	public boolean processItemOnObject(Player player, WorldObject object, Item item) {
		if (item.getId() != 954)
			return false;
		player.getInventory().deleteItem(954, 1);
		if (object.getId() == 48803)
			player.getVarsManager().sendVarBit(7263, 1, true);
		else
			player.getVarsManager().sendVarBit(7262, 1, true);
		return true;

	}
}
