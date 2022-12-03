package com.rs.game.player.actions.combat.lunarspells;

import java.util.concurrent.TimeUnit;

import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.World;
import com.rs.game.Hit.HitLook;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.Combat;
import com.rs.utils.Utils;

public class HealGroup {

	public static boolean cast(Player player, double xp) {
		Long lastHealGroup = (Long) player.temporaryAttribute().get("LAST_HEALGROUP");
		if (lastHealGroup != null && lastHealGroup + 20000 > Utils.currentTimeMillis()) {
			player.sm("You can only cast this every 20 seconds.");
			return false;
		}
		if (!player.isAtMultiArea()) {
			player.sm("You need to be in a mutli area for this spell.");
			return false;
		}
		int playersAround = 0;
		for (Player other : World.getPlayers()) {
			if (other == null)
				continue;
			if (other.withinDistance(player, 4) && other.isAcceptAid()) {
				if (!other.getUsername().equalsIgnoreCase(player.getUsername())) {
					other.sm("Your health has been healed.");
					other.gfx(new Graphics(745, 0, 100));
					other.temporaryAttribute().put("LAST_HEALGROUP", Utils.currentTimeMillis());
					playersAround++;
					other.heal((int) (player.getHitpoints() * .75 / playersAround));
				}
			}
			player.sm("The spell affected " + playersAround + " player(s).");
			CoresManager.slowExecutor.schedule(new Runnable() {
				@Override
				public void run() {
					player.applyHit(new Hit(player, ((int) (player.getHitpoints() * .75)), HitLook.REGULAR_DAMAGE));
					player.animate(new Animation(Combat.getDefenceEmote(player)));
				}
			}, 1200, TimeUnit.MILLISECONDS);
		}
		player.getSkills().addXp(Skills.MAGIC, xp);
		player.gfx(new Graphics(745, 0, 100));
		player.animate(new Animation(4411));
		player.temporaryAttribute().put("LAST_HEALGROUP", Utils.currentTimeMillis());
		return true;
	}
}
