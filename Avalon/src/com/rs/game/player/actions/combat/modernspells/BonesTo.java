package com.rs.game.player.actions.combat.modernspells;

import com.rs.game.Animation;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class BonesTo {
	
	public static boolean cast(Player player, double xp, boolean peaches) {
			if (!player.getInventory().containsItem(526, 1)) {
				player.getPackets().sendGameMessage("You don't have any bones.");
				return false;
			}
			int amount = player.getInventory().getNumberOf(526);
			player.getInventory().deleteItem(526, amount);
			player.getInventory().addItem(peaches ? 6883 : 1963, amount);
			player.getSkills().addXp(Skills.MAGIC, xp);
			player.animate(new Animation(712));
			return true;
	}

}
