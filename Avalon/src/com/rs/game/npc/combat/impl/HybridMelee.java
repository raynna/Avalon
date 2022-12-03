package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class HybridMelee extends CombatScript {

	@Override
	public Object[] getKeys() {

		return new Object[] { 1919, 19002 };
	}

	static int specialAttack = 100;

	@Override
	public int attack(final NPC npc, final Entity target) {
		specialAttack -= 25;
		npc.animate(new Animation(1062));
		npc.gfx(new Graphics(252, 0, 100));
		delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, 340, NPCCombatDefinitions.MELEE, target)),
				getMeleeHit(npc, getRandomMaxHit(npc, 340, NPCCombatDefinitions.MELEE, target)));
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				if (npc.getId() == 19002)
					npc.transformIntoNPC(Utils.getRandom(1) == 0 ? 19000 : 19001);
				else
					npc.transformIntoNPC(Utils.getRandom(1) == 0 ? 6367 : 3229);
				npc.setCombatLevel(83);
				npc.setCantFollowUnderCombat(false);
				npc.setAttackedByDelay(100);
				npc.setRandomWalk(npc.getDefinitions().walkMask);
				npc.setForceAgressive(true);
				npc.setNoDistanceCheck(false);
				npc.setTarget(target);
			}
		}, 1);
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
