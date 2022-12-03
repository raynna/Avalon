package com.rs.game.objects.impl;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.thieving.Thieving;
import com.rs.game.player.content.PirateHut;

public class PirateHutDoor extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 65717 };
	}

	@Override
	public int getDistance() {
		return 0;
	}
	
	@Override
	public boolean processObject(Player player, WorldObject object) {
		if (player.getY() >= 3955 && player.getY() <= 3957 && player.getX() >= 3043
				&& player.getX() <= 3044) {
			PirateHut.LeaveEastDoor(player, object);
			return true;
		} else if (player.getY() >= 3955 && player.getY() <= 3957 && player.getX() >= 3038
				&& player.getX() <= 3039) {
			PirateHut.LeaveWestDoor(player, object);
			return true;
		} else if (player.getX() >= 3040 && player.getX() <= 3042 && player.getY() >= 3958
				&& player.getY() <= 3959) {
			PirateHut.LeaveNorthDoor(player, object);
			return true;
		} else if (player.getX() >= 3040 && player.getX() <= 3042 && player.getY() >= 3960
				&& player.getY() <= 3961
				|| player.getX() >= 3045 && player.getX() <= 3946 && player.getY() >= 3955
						&& player.getY() <= 3957
				|| player.getX() >= 3036 && player.getX() <= 3037 && player.getY() >= 3955
						&& player.getY() <= 3957) {
			player.getPackets().sendGameMessage("This door is locked from the inside.");
			return false;
		}
		return true;
	}

	@Override
	public boolean processObject2(Player player, WorldObject object) {
		if (!player.getInventory().containsItem(1523, 1)) {
			player.getDialogueManager().startDialogue("SimpleMessage",
					"You need an Lockpick to picklock this gate.");
			return false;
		}
		if (player.getSkills().getLevelForXp(Skills.THIEVING) < 70) {
			player.getDialogueManager().startDialogue("SimpleMessage",
					"You need atleast an level of 70 thieving to picklock this door.");
			return false;
		}
		if (player.getX() == 3045) {
			player.setNextFaceWorldTile(new WorldTile(player.getX() - 1, player.getY(), 0));
			Thieving.pickEastDoor(player, object);
			return true;
		}
		if (player.getX() == 3037) {
			player.setNextFaceWorldTile(new WorldTile(player.getX() + 1, player.getY(), 0));
			Thieving.pickWestDoor(player, object);
			return true;
		}
		if (player.getY() == 3960) {
			player.setNextFaceWorldTile(new WorldTile(player.getX(), player.getY() - 1, 0));
			Thieving.pickNorthDoor(player, object);
			return true;
		}
		if (player.getX() >= 3038 && player.getX() <= 3044 && player.getY() >= 3949
				&& player.getY() <= 3959) {
			player.getPackets().sendGameMessage("You can't picklock from the inside.");
			return false;
		}
		return true;
	}
}