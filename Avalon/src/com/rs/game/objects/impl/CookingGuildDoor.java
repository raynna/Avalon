package com.rs.game.objects.impl;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class CookingGuildDoor extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 2712, null };
	}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		if (player.getY() <= object.getY()) {
			if (player.getSkills().getLevelForXp(Skills.COOKING) < 32) {
				player.getPackets().sendGameMessage("You need a level of 32 cooking to enter this guild.");
				return false;
			}
			if (!player.getEquipment().containsOneItem(1949)) {
				player.getPackets().sendGameMessage("You need to wear "
						+ ItemDefinitions.getItemDefinitions(1949).getName() + " to enter this guild.");
				return false;
			}
		}
		DoorsAndGates.handleDoorTemporary(player, object, 1200);
		return true;
	}
}
