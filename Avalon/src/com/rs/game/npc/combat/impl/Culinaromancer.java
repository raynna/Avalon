package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class Culinaromancer extends CombatScript {

	@Override
	public Object[] getKeys() {

		return new Object[] { 3491 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int attackStyle = Utils.random(2);
		if (attackStyle == 0 || attackStyle == 1) { // Melee
			npc.animate(new Animation(defs.getAttackEmote()));
			delayHit(npc, 0, target,
					getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			return defs.getAttackDelay();
		}
		if (attackStyle == 2) {
			World.sendCBOWProjectile(npc, target, 362);
			npc.animate(new Animation(1979));
			target.addFreezeDelay(5000, false);
			delayHit(npc, 1, target,
					getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					target.gfx(new Graphics(369, 0, 0));
				}
			}, 1);
			return defs.getAttackDelay() + 2;
		}
		return attackStyle;
	}
}
