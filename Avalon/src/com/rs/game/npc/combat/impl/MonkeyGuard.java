package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;

public class MonkeyGuard extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 1459, 1460 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int timesHealed = 0;
		int attackStyle = 0;
		switch (attackStyle) {
		case 0: // melee
			if (npc.getHitpoints() < Math.ceil(npc.getMaxHitpoints() * 0.20) && timesHealed < 15) {
				timesHealed++;
				npc.heal(250);
				npc.animate(new Animation(1405));
				return defs.getAttackDelay();
			}
			delayHit(npc, 0, target,
					getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			npc.animate(new Animation(defs.getAttackEmote()));
			break;
		}
		return defs.getAttackDelay();
	}
}
