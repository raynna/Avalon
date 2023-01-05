package com.rs.game.objects.plugins;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectPlugin;
import com.rs.game.player.Player;

public class PottersWheel extends ObjectPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] { 2642, "Potter's Wheel" };
	}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		player.getDialogueManager().startDialogue("PotterWheelD", object);
		return true;
	}
}
