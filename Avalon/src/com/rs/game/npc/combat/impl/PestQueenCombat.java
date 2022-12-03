package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

/**
 * 
 * @author Jae <jae@xiduth.com>
 * 
 *         Last modified: <Oct 17, 2013>
 *
 */
public class PestQueenCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 6358 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (Utils.getRandom(4) == 0) {
			switch (Utils.getRandom(0)) {
			case 0:
				npc.setNextForceTalk(new ForceTalk("BEGONE!"));
				break;
			}
		} else if (Utils.getRandom(5) == 1) {
			npc.animate(new Animation(1344));
			npc.setNextForceTalk(new ForceTalk("BLURGH"));
			for (Entity targets : npc.getPossibleTargets()) {
				delayHit(npc, 1, targets, getMagicHit(npc, getRandomMaxHit(npc, 320, NPCCombatDefinitions.MAGE, target)));
			}
		}

		if (Utils.getRandom(2) == 0) {
			npc.animate(new Animation(14801));
			for (Entity targets : npc.getPossibleTargets()) {
				delayHit(npc, 1, targets, getMeleeHit(npc, getRandomMaxHit(npc, 500, NPCCombatDefinitions.MELEE, target)));
			}
		} else {
			npc.animate(new Animation(defs.getAttackEmote()));
			delayHit(npc, 0, target,
					getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
		}
		return defs.getAttackDelay();
	}
}