package com.rs.game.objects.impl;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;

public class RunespanPortal extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 38279 };
	}
	
	@Override
	public boolean processObject(Player player, WorldObject object) {
		player.getDialogueManager().startDialogue("RunespanPortalD");
		return true;
	}
}
