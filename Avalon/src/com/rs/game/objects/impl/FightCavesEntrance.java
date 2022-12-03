package com.rs.game.objects.impl;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;
import com.rs.game.player.controlers.FightCaves;

public class FightCavesEntrance extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 68107 };
	}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		FightCaves.enterFightCaves(player);
		return true;
	}
}
