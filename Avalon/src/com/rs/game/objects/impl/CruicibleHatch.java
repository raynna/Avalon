package com.rs.game.objects.impl;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;

public class CruicibleHatch extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 67051, "Hatch" };
	}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public boolean processObject2(Player player, WorldObject object) {
		player.getDialogueManager().startDialogue("Marv", true);
		return true;
	}
}

