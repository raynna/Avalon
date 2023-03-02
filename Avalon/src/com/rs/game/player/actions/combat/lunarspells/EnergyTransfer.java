package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class EnergyTransfer {
	
	public static boolean cast(Player player, Entity target, double xp) {
		if (target instanceof Player) {
			Player other = (Player) target;
			player.faceEntity(target);
			if (!other.isAcceptAid()) {
				player.getPackets().sendGameMessage(other.getDisplayName() + " doesn't have aid on.");
				return false;
			}
			if (other.getCombatDefinitions().getSpecialAttackPercentage() == 100) {
				player.getPackets().sendGameMessage(other.getDisplayName() + " has full special attack.");
				return false;
			}
			if (player.getCombatDefinitions().getSpecialAttackPercentage() == 0) {
				player.getPackets().sendGameMessage("You don't have any special attack left.");
				return false;
			}
			if (!other.isAtMultiArea()) {
				player.message("You can only cast this spell in a multi-area.");
				return false;
			}
			int amount = (100 - other.getCombatDefinitions().getSpecialAttackPercentage());
			if (amount > player.getCombatDefinitions().getSpecialAttackPercentage())
				amount = player.getCombatDefinitions().getSpecialAttackPercentage();
				player.getCombatDefinitions().desecreaseSpecialAttack(amount);
				other.getCombatDefinitions().increaseSpecialAttack(amount);
				player.animate(new Animation(4411));
				other.gfx(new Graphics(744, 0, 100));
				player.getSkills().addXp(Skills.MAGIC, xp);
				other.getPackets().sendGameMessage("You got an energy transfer from player " + player.getDisplayName() + ".");
				other.getPoison().reset();
				return true;
		}
		return false;
	}

}
