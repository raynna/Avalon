package com.rs.game.objects.plugins;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectPlugin;
import com.rs.game.player.Player;

public class Mill extends ObjectPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] { 36880, 954, 67770 };
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		if (!player.getInventory().containsItem(1931, 1)) {
			player.getPackets().sendGameMessage("You need an empty pot to fill.");
			return false;
		}
		player.getInventory().deleteItem(1931, 1);
		player.getInventory().addItem(1933, 1);
		if (object.getId() == 67770)
			player.getVarsManager().sendVarBit(10712, 0, true);
		else
			player.getVarsManager().sendVar(695, 0);
		return true;
	}
}
