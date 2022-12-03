package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class Karamel extends CombatScript {

	@Override
	public Object[] getKeys() {

		return new Object[] { 3495 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int attackStyle = Utils.getRandom(2);
		if (attackStyle == 0) { // range
			npc.animate(new Animation(defs.getAttackEmote()));
			delayHit(npc, 1, target,
					getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			return defs.getAttackDelay();
		}
		if (attackStyle == 2 || attackStyle == 1) {
			World.sendCBOWProjectile(npc, target, 362);
			npc.animate(new Animation(1979));
			npc.setNextForceTalk(new ForceTalk("Semolina-Go!"));
			Player p2 = (Player) target;
			p2.addFreezeDelay(5000, false);
			delayHit(npc, 2, target, getMagicHit(npc, getRandomMaxHit(npc, 100, NPCCombatDefinitions.MAGE, target)));
			delayHit(npc, 2, target, getMagicHit(npc, getRandomMaxHit(npc, 100, NPCCombatDefinitions.MAGE, target)));
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
