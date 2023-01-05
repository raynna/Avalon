package com.rs.game.objects.plugins;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectPlugin;
import com.rs.game.player.Player;

public class EnterDungeoneering extends ObjectPlugin {

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
