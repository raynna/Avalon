package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class VengeanceOther {
	
	public static boolean cast(Player player, Entity target, double xp) {
		if (target instanceof Player) {
			Player other = (Player) target;
			if (player.getVengDelay() >= Utils.currentTimeMillis()) {
				player.sm("You can only cast vengeance every 30 seconds.");
				return false;
			}
			if (other.getVengDelay() >= Utils.currentTimeMillis()) {
				player.sm(other.getDisplayName() + " can only cast vengeance every 30 seconds.");
				return false;
			}
			if (!other.isAcceptAid()) {
				player.sm(other.getDisplayName() + " doesn't have aid on.");
				return false;
			}
			if (!other.isAtMultiArea()) {
				player.sm("You can only cast this spell in a multi-area.");
				return false;
			}
			player.animate(new Animation(4411));
			other.gfx(new Graphics(725, 0, 100));
			other.sm(player.getDisplayName() + " cast an vengeance spell on you.");
			other.setVengeance(true);
			other.setVengeance(30000);
			player.setVengeance(30000);
			player.addXp(Skills.MAGIC, xp);
			return true;
		}
		return false;
	}

}
