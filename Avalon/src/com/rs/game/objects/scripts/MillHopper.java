package com.rs.game.objects.scripts;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;

public class MillHopper extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 24072, 2718, 67775, 67774 };
	}

	private final int WHEAT = 1947;

	@Override
	public boolean processObject(Player player, WorldObject object) {
		if (object.getId() == 67774) {
			if (player.getTemporaryAttributes().containsKey(("HOPPER_WHEAT" + object.getId()))) {
				player.getPackets().sendGameMessage("You have already put down some wheat inside this hopper.");
				return false;
			}
			if (!player.getInventory().containsOneItem(WHEAT)) {
				player.getPackets().sendGameMessage("You need some wheat to use this hopper.");
				return false;
			}
			player.getInventory().deleteItem(WHEAT, 1);
			player.getPackets().sendGameMessage("You put some wheat inside the hopper, maybe i should pull the lever?");
			player.getTemporaryAttributes().put(("HOPPER_WHEAT" + object.getId()), true);
			return true;
		}
		player.getTemporaryAttributes().remove(("HOPPER_WHEAT" + object.getId()));
		player.getPackets().sendGameMessage("You operate the hopper. The grain slides down the chute.");
		if (object.getId() == 67775)
			player.getVarsManager().sendVarBit(10712, 1, true);
		else
			player.getVarsManager().sendVar(695, 1);
		return true;
	}
}
