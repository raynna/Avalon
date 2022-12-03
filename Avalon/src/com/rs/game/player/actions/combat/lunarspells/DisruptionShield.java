package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class DisruptionShield {
	
	public static boolean cast(Player player, double xp) {
		if (player.getDisruptionDelay() >= Utils.currentTimeMillis()) {
			player.sm("You can't cast this spell again yet.");
			return false;
		}
		player.addXp(Skills.MAGIC, xp);
		player.gfx(new Graphics(1320, 0, 100));
		player.animate(new Animation(8770));
		player.setDisruption(true);
		player.setDisruption(60000);
		player.sm("You cast a Disruption Shield.");
		return true;
	}
}
