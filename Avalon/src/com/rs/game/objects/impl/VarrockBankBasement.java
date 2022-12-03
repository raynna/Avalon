package com.rs.game.objects.impl;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;

public class VarrockBankBasement extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 24360, 24365, null };
	}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		player.movePlayer(object.getId() == 24360 ? new WorldTile(3190, 9833, 0) : new WorldTile(3188, 3432, 0), 1, 2);
		return true;
	}
}
