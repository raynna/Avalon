package com.rs.game.objects.impl;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;
import com.rs.game.player.content.DwarfMultiCannon;

public class DwarfCannon extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 6, 7, 8, 9 };
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		if (object.getId() != 6)
			DwarfMultiCannon.pickupCannon(player, object.getId() - 6, object, 0);
		else
			DwarfMultiCannon.fire(player, object);
		return true;
	}

	@Override
	public boolean processObject2(Player player, WorldObject object) {
		DwarfMultiCannon.pickupCannon(player, 4, object, 0);
		return true;
	}
}
