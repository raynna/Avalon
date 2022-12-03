package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class HybridMage extends CombatScript {

	@Override
	public Object[] getKeys() {

		return new Object[] { 6367, 19001 };
	}

	private int random(int random) {
		return Utils.getRandom(random);
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final int getRandom = random(5);
		if (Utils.random(1) == 0 && target.getHitpoints() <= target.getMaxHitpoints() * 0.50
				&& HybridMelee.specialAttack >= 25) {
			if ((npc.getFreezeDelay() < Utils.currentTimeMillis())) {
				if (npc.getId() == 19001)
					npc.transformIntoNPC(19002);
				else
					npc.transformIntoNPC(1919);
				npc.setCombatLevel(83);
				npc.setCantFollowUnderCombat(false);
				npc.setAttackedByDelay(100);
				npc.setRandomWalk(npc.getDefinitions().walkMask);
				npc.setForceAgressive(true);
				npc.setNoDistanceCheck(false);
				npc.setTarget(target);
				return 2;
			}
		}
		if (random(3) == 0) {
			if (npc.getId() == 19001)
				npc.transformIntoNPC(19000);
			else
				npc.transformIntoNPC(3229);
			npc.setCombatLevel(83);
			npc.setCantFollowUnderCombat(false);
			npc.setAttackedByDelay(100);
			npc.setRandomWalk(npc.getDefinitions().walkMask);
			npc.setForceAgressive(true);
			npc.setNoDistanceCheck(false);
			npc.setTarget(target);
			return 2;
		} else {
			npc.animate(new Animation(1979));
			World.sendProjectile(npc, target, 368, 60, 32, 50, 50, 0);
			delayHit(npc, getMageDelay(npc, target), target,
					getMagicHit(npc, getRandomMaxHit(npc, 340, NPCCombatDefinitions.MAGE, target)));
			if (HybridMelee.specialAttack <= 95)
				HybridMelee.specialAttack += 5;
			if (getRandom >= 3 && getRandom <= 5) {
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						if (npc.getId() == 19001)
							npc.transformIntoNPC(19000);
						else
							npc.transformIntoNPC(3229);
						npc.setCombatLevel(83);
						npc.setCantFollowUnderCombat(false);
						npc.setAttackedByDelay(100);
						npc.setRandomWalk(npc.getDefinitions().walkMask);
						npc.setForceAgressive(true);
						npc.setNoDistanceCheck(false);
						npc.setTarget(target);
					}
				}, 1);
			}
		}
		return 4;
	}

	public int getMageDelay(NPC npc, Entity target) {
		if (Utils.getDistance(npc, target) > 3)
			return 4;
		if (Utils.getDistance(npc, target) == 2 || Utils.getDistance(npc, target) == 3)
			return 2;
		return 1;
	}

	public int getRangeDelay(NPC npc, Entity target) {
		if (Utils.getDistance(npc, target) > 3)
			return 2;
		return 1;
	}

}
