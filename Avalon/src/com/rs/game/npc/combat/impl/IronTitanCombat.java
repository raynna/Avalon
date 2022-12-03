package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class IronTitanCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 7376, 7375 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int distanceX = target.getX() - npc.getX();
		int distanceY = target.getY() - npc.getY();
		boolean distant = false;
		int size = npc.getSize();
		Familiar familiar = (Familiar) npc;
		boolean usingSpecial = familiar.hasSpecialOn();
		int damage = 0;
		int[] damages = { 0, 0, 0 };
		if (distanceX > size || distanceX < -1 || distanceY > size || distanceY < -1)
			distant = true;
		if (usingSpecial) {// priority over regular attack
			npc.animate(new Animation(7954));
			npc.gfx(new Graphics(1450));
			if (distant) {// Mage Hit & Magic Xp
				for (Integer appliedDamage : damages) {
					if (target instanceof Player) {
						Player p2 = (Player) target;
						appliedDamage = getRandomMaxHit(npc,
								p2.getPrayer().usingPrayer(1, 6) ? 230 / 10
										: p2.getPrayer().usingPrayer(0, 16) ? 230 / 10 : 230,
								NPCCombatDefinitions.MAGE, target);
					} else {
						appliedDamage = getRandomMaxHit(npc, 230, NPCCombatDefinitions.MAGE, target);
					}
					delayHit(npc, 2, target, getMagicHit(npc, appliedDamage));
					long familiarDelay = 3000;
					familiar.getOwner().addFamiliarDelay(familiarDelay);
					familiar.getOwner().getSkills().addXp(Skills.MAGIC, appliedDamage / 3);
				}
			} else {// Melee Hit & Defence Xp
				for (Integer appliedDamage : damages) {
					if (target instanceof Player) {
						Player p2 = (Player) target;
						appliedDamage = getRandomMaxHit(npc,
								p2.getPrayer().usingPrayer(1, 6) ? 230 / 10
										: p2.getPrayer().usingPrayer(0, 16) ? 230 / 10 : 230,
								NPCCombatDefinitions.MELEE, target);
					} else {
						appliedDamage = getRandomMaxHit(npc, 230, NPCCombatDefinitions.MELEE, target);
					}
					delayHit(npc, 2, target, getMeleeHit(npc, appliedDamage));
					long familiarDelay = 3000;
					familiar.getOwner().addFamiliarDelay(familiarDelay);
					familiar.getOwner().getSkills().addXp(Skills.DEFENCE, appliedDamage / 3);
				}
			}
		} else {
			if (distant) {
				damage = getRandomMaxHit(npc, 255, NPCCombatDefinitions.MAGE, target);
				npc.animate(new Animation(7694));
				World.sendSlowBowProjectile(npc, target, 1452);
				delayHit(npc, Utils.getDistance(npc, target) > 3 ? 3 : 2, target, getMagicHit(npc, damage));
				familiar.getOwner().getSkills().addXp(Skills.MAGIC, damage / 3);
			} else {// melee
				damage = getRandomMaxHit(npc, 244, NPCCombatDefinitions.MELEE, target);
				npc.animate(new Animation(7946));
				npc.gfx(new Graphics(1447));
				delayHit(npc, 1, target, getMeleeHit(npc, damage));
				familiar.getOwner().getSkills().addXp(Skills.DEFENCE, damage / 3);
			}
		}
		return defs.getAttackDelay();
	}

}
