package com.rs.game.objects.scripts;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class CraftingGuildDoor extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 2647, "Guild Door" };
	}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		if (player.getY() >= object.getY()) {
			if (player.getSkills().getLevelForXp(Skills.CRAFTING) < 40) {
				player.getPackets().sendGameMessage("You need a level of 40 crafting to enter this guild.");
				return false;
			}
			if (!player.getEquipment().containsOneItem(1005, 1757)) {
				player.getPackets().sendGameMessage("You need to wear an apron to enter this guild.");
				return false;
			}
		}
		DoorsAndGates.handleDoorTemporary(player, object, 1200);
		return true;
	}
}
