package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class HealOther {
	
	public static boolean cast(Player player, Entity target, double xp) {
		if (target instanceof Player) {
			Player other = (Player) target;
			player.faceEntity(target);
			if ((Long) player.getTemporaryAttributtes().get("LAST_SPELL") != null
					&& (long) player.getTemporaryAttributtes().get("LAST_SPELL") + 4800 > Utils.currentTimeMillis()) {
				player.getPackets().sendGameMessage("You can't do this yet.");
				return false;
			}
			if (!other.isAcceptAid()) {
				player.getPackets().sendGameMessage(other.getDisplayName() + " doesn't have aid on.");
				return false;
			}
			if (!other.isAtMultiArea()) {
				player.sm("You can only cast this spell in a multi-area.");
				return false;
			}
			if (other.getHitpoints() != other.getMaxHitpoints()) {
				player.animate(new Animation(4411));
				other.gfx(new Graphics(744, 0, 100));
				player.getSkills().addXp(Skills.MAGIC, xp);
				other.getPackets().sendGameMessage("You have been healed by player " + player.getDisplayName() + ".");
				int damage = (int) (player.getHitpoints() * 0.75);
				player.applyHit(new Hit(player, damage, HitLook.REGULAR_DAMAGE)); 
				player.getTemporaryAttributtes().put("LAST_SPELL", Utils.currentTimeMillis());
				other.heal(damage);
				return true;
			} else {
				player.getPackets().sendGameMessage(other.getDisplayName() + " has already full hitpoints.");
				return false;
			}
		}
		return false;
	}

}
