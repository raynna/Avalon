package com.rs.game.npc.combat.impl.dung;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

public class FleshSpoilerSpawnCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[]
		{ 11910 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		npc.animate(new Animation(Utils.random(3) == 0 ? 14474 : 14475));
		delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, npc.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
		return 3;
	}
}
