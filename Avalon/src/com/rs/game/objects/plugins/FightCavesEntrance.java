package com.rs.game.objects.plugins;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.controlers.FightCaves;

public class FightCavesEntrance extends ObjectPlugin {

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
