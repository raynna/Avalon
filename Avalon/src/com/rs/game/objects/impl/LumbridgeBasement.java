package com.rs.game.objects.impl;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;

public class LumbridgeBasement extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 36687, 29355 };
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		if (object.getId() == 29355 && object.getX() == 3209 && object.getY() == 9616)
			player.useStairs(828, new WorldTile(3210, 3216, 0), 1, 2);
		else
			player.useStairs(828, new WorldTile(3208, 9616, 0), 1, 2);
		return true;
	}
}
