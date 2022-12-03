package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class VengeanceGroup {

	public static boolean cast(Player player, double xp) {
		if (player.getVengDelay() >= Utils.currentTimeMillis()) {
			player.sm("You can only cast vengeance every 30 seconds.");
			return false;
		}
		if (!player.isAtMultiArea()) {
			player.sm("You need to be in a mutli area for this spell.");
			return false;
		}
		int count = 0;
		for (Player other : World.getPlayers()) {
			if (other.withinDistance(player, 6) && other.isAcceptAid() && other.isAtMultiArea()
					&& other.getVengDelay() < Utils.currentTimeMillis()) {
				other.sm(player.getDisplayName() + " cast the Group Vengeance spell and you were affected!");
				other.gfx(new Graphics(725, 0, 100));
				other.setVengeance(true);
				other.setVengeance(30000);
				count++;
			}
		}
		player.addXp(Skills.MAGIC, xp);
		player.sm("The spell affected " + count + " nearby people.");
		player.gfx(new Graphics(725, 0, 100));
		player.animate(new Animation(4411));
		player.setVengeance(true);
		player.setVengeance(30000);
		return true;
	}

}
