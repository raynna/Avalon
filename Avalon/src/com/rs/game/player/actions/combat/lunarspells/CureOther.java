package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class CureOther {
	
	public static boolean cast(Player player, Entity target, double xp) {
		if (target instanceof Player) {
			Player other = (Player) target;
			player.faceEntity(target);
			if (!other.isAcceptAid()) {
				player.message(other.getDisplayName() + " doesn't have aid on.");
				return false;
			}
			if (!other.getPoison().isPoisoned()) {
				player.message(other.getDisplayName() + " is not poisoned.");
				return false;
			}
			if (!other.isAtMultiArea()) {
				player.message("You can only cast this spell in a multi-area.");
				return false;
			}
				player.animate(new Animation(4411));
				other.gfx(new Graphics(744, 0, 100));
				player.addXp(Skills.MAGIC, xp);
				other.message("You have been cured by player " + player.getDisplayName() + ".");
				other.getPoison().reset();
				return true;
		}
		return false;
	}

}
