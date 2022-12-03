package com.rs.game.objects.impl;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;

public class EnterDungeoneering extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 48496 };
	}
	
	@Override
	public boolean processObject(Player player, WorldObject object) {
		player.getDungManager().enterDungeon(true, false);
		return true;
	}
}
