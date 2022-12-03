package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.TormentedDemon;
import com.rs.utils.Utils;

public class TormentedDemonCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Tormented demon" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		TormentedDemon torm = (TormentedDemon) npc;
		int hit = 0;
		int attackStyle = torm.getCurrentCombatType();
		if (attackStyle == 0 && !torm.withinDistance(target, 1)) {
			// if melee & cant reach
			// cant pick same as previous combat type
			int random = Utils.random(1, 2);
			while (random == torm.getPreviousCombatType())
				random = Utils.random(1, 2);
			attackStyle = random;
			torm.setCurrentCombatType(attackStyle);
		}
		switch (attackStyle) {
		case 0:
			hit = getRandomMaxHit(npc, 189, NPCCombatDefinitions.MELEE, target);
			npc.animate(new Animation(10922));
			npc.gfx(new Graphics(1886, 2, 0));
			delayHit(npc, 1, target, getMeleeHit(npc, hit));
			return defs.getAttackDelay();
		case 1:
			hit = getRandomMaxHit(npc, 269, NPCCombatDefinitions.MAGE, target);
			npc.animate(new Animation(10918));
			World.sendNPCProjectile(npc, target, 1884);
			delayHit(npc, 1, target, getMagicHit(npc, hit));
			break;
		case 2:
			hit = getRandomMaxHit(npc, 269, NPCCombatDefinitions.RANGE, target);
			npc.animate(new Animation(10919));
			npc.gfx(new Graphics(1888));
			World.sendNPCProjectile(npc, target, 1887);
			delayHit(npc, 1, target, getRangeHit(npc, hit));
			break;
		}
		return defs.getAttackDelay();
	}
}
