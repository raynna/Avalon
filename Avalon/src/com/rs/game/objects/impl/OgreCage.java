package com.rs.game.objects.impl;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;

public class OgreCage extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 19171 };
	}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
			DoorsAndGates.handleDoorTemporary(player, object, 1200);
		return true;
	}
}
