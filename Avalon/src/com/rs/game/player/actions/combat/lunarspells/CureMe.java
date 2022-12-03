package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class CureMe {

	public static boolean cast(Player player, double xp) {
		if ((Long) player.temporaryAttribute().get("LAST_SPELL") != null
				&& (Long) player.temporaryAttribute().get("LAST_SPELL") + 4000 > Utils.currentTimeMillis())
			return false;
		if (!player.getPoison().isPoisoned()) {
			player.getPackets().sendGameMessage("You are not poisoned.");
			return false;
		}
		player.temporaryAttribute().put("LAST_SPELL", Utils.currentTimeMillis());
		player.animate(new Animation(4411));
		player.addXp(Skills.MAGIC, xp);
		player.gfx(new Graphics(748, 0, 100));
		player.getPoison().reset();
		player.sm("You have been cured of poison");
		return true;
	}

}
