package com.rs.game.objects.impl;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;
import com.rs.game.player.content.Canoes;

public class CanoeStation extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 12163, 12164, 12165, 12166, "Canoe station" };
	}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		if (player.getTemporaryAttributtes().get("canoe_shaped") != null
				&& (boolean) player.getTemporaryAttributtes().get("canoe_shaped"))
			Canoes.openTravelInterface(player, object.getId() - 12163);
		else if (player.getTemporaryAttributtes().get("canoe_chopped") != null
				&& (boolean) player.getTemporaryAttributtes().get("canoe_chopped"))
			Canoes.openSelectionInterface(player);
		else
			Canoes.chopCanoeTree(player, object.getId() - 12163);
		return true;
	}
}
