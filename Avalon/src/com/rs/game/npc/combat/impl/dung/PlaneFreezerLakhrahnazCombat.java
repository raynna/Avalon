package com.rs.game.npc.combat.impl.dung;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

public class PlaneFreezerLakhrahnazCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[]
		{ "Plane-freezer Lakhrahnaz" };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (Utils.random(8) == 0) {
			npc.resetWalkSteps();
			npc.addWalkSteps(npc.getX() + Utils.random(3) - 2, npc.getY() + Utils.random(3) - 2);
		}
		if (Utils.random(3) == 0) {
			int attackStyle = Utils.random(2);
			if (attackStyle == 1 && !Utils.isOnRange(target.getX(), target.getY(), target.getSize(), npc.getX(), npc.getY(), npc.getSize(), 0))
				attackStyle = 0;
			switch (attackStyle) {
			case 0:
				npc.animate(new Animation(13775));
				for (Entity t : npc.getPossibleTargets()) {
					World.sendProjectile(npc, t, 2577, 16, 16, 41, 30, 0, 0);
					t.gfx(new Graphics(2578, 70, 0));
					delayHit(npc, 1, t, getMagicHit(npc, getRandomMaxHit(npc, 100, NPCCombatDefinitions.MAGE, target)));
				}
				break;
			case 1:
				npc.animate(new Animation(defs.getAttackEmote()));
				int dir = Utils.random(Utils.DIRECTION_DELTA_X.length);
				target.addWalkSteps(target.getX() + Utils.DIRECTION_DELTA_X[dir], target.getY() + Utils.DIRECTION_DELTA_Y[dir], 1);
				delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, 100, NPCCombatDefinitions.MELEE, target)));
				break;
			}
			return npc.getAttackSpeed();
		}
		npc.animate(new Animation(13775));
		npc.gfx(new Graphics(2574));
		World.sendProjectile(npc, target, 2595, 16, 16, 41, 30, 0, 0);
		target.gfx(new Graphics(2576, 70, 0));
		delayHit(npc, 1, target, getRangeHit(npc, getRandomMaxHit(npc, 100, NPCCombatDefinitions.RANGE, target)));
		return npc.getAttackSpeed();
	}
}
