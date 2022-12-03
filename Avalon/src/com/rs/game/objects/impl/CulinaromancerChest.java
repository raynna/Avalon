package com.rs.game.objects.impl;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;
import com.rs.utils.ShopsHandler;

public class CulinaromancerChest extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 12309 };
	}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		player.getBank().openBank();
		return true;
	}
	
	@Override
	public boolean processObject2(Player player, WorldObject object) {
		ShopsHandler.openShop(player, 34);
		return true;
	}
	
	@Override
	public boolean processObject3(Player player, WorldObject object) {
		ShopsHandler.openShop(player, 34);
		return true;
	}
}
