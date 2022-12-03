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

public class HybridRange extends CombatScript {

	@Override
	public Object[] getKeys() {

		return new Object[] { 3229, 19000 };
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
				if (npc.getId() == 19000)
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
			if (npc.getId() == 19000)
				npc.transformIntoNPC(19001);
			else
				npc.transformIntoNPC(6367);
			npc.setCombatLevel(83);
			npc.setCantFollowUnderCombat(false);
			npc.setAttackedByDelay(100);
			npc.setRandomWalk(npc.getDefinitions().walkMask);
			npc.setForceAgressive(true);
			npc.setNoDistanceCheck(false);
			npc.setTarget(target);
			return 2;
		} else {
			npc.animate(new Animation(4230));
			World.sendCBOWProjectile(npc, target, 740);
			delayHit(npc, getRangeDelay(npc, target), target,
					getRangeHit(npc, getRandomMaxHit(npc, 450, NPCCombatDefinitions.RANGE, target)));
			if (HybridMelee.specialAttack <= 95)
				HybridMelee.specialAttack += 5;
			if (getRandom >= 3 && getRandom <= 5) {
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						if (npc.getId() == 19000)
							npc.transformIntoNPC(19001);
						else
							npc.transformIntoNPC(6367);
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
