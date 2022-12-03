package com.rs.game.objects.impl;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;

public class BrokenStairsDungeoneering extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 50552 };
	}
	
	@Override
	public boolean processObject(Player player, WorldObject object) {
		player.setNextWorldTile(new WorldTile(player.getX(), player.getY() + 2, player.getPlane() - 1));
		return true;
	}
}
