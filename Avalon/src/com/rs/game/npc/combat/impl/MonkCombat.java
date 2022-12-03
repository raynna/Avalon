package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

public class MonkCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 7727 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int attackStyle = 0;
		switch (attackStyle) {
		case 0: // melee
			if (Utils.getRandom(2) == 0 && npc.getHitpoints() < npc.getMaxHitpoints()) {
					npc.heal(20);
					npc.animate(new Animation(805));
				break;
			}
			delayHit(npc, 0, target,
					getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			npc.animate(new Animation(defs.getAttackEmote()));
			break;
		}
		return defs.getAttackDelay();
	}
}
