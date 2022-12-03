package com.rs.game.objects.impl;

import com.rs.game.Hit;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.Hit.HitLook;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class GodWarsBoulder extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 35390, null };
	}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public boolean processObject2(Player player, WorldObject object) {
		if (player.getSkills().getLevelForXp(Skills.AGILITY) < 60) {
			player.getDialogueManager().startDialogue("SimpleMessage",
					"You need at least level 60 agility to clear this obstacle.");
			return false;
		} else {
			if (player.getY() > 3709) {
				player.setNextWorldTile(new WorldTile(2907, 3708, 0));
			} else if (player.getY() < 3709) {
				player.setNextWorldTile(new WorldTile(2907, 3712, 0));
			}
			if (Utils.random(1, 50) > 40) {
				player.applyHit(new Hit(player, 70, HitLook.REGULAR_DAMAGE));
				player.sm("You scrape yourself against the rock while trying to pass!");
				return false;
			} else {
				player.getPackets().sendGameMessage("You slide past the rock with great skill.");
				return true;
			}
		}
	}
}