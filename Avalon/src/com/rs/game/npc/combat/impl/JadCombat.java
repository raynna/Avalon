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

public class JadCombat extends CombatScript {

	@Override
	public Object[] getKeys() {

		return new Object[] { 2745, 15208 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int attackStyle = Utils.random(3);
		if (attackStyle == 2) { // melee
			int distanceX = target.getX() - npc.getX();
			int distanceY = target.getY() - npc.getY();
			int size = npc.getSize();
			if (distanceX > size || distanceX < -1 || distanceY > size || distanceY < -1)
				attackStyle = Utils.random(2); // set mage
			else {
				npc.animate(new Animation(defs.getAttackEmote()));
				delayHit(npc, 1, target,
						getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
				return defs.getAttackDelay();
			}
		}
		if (attackStyle == 1) {
			npc.animate(new Animation(16202));
			npc.gfx(new Graphics(2994));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					delayHit(npc, 2, target, getRangeHit(npc,
							getRandomMaxHit(npc, defs.getMaxHit() - 2, NPCCombatDefinitions.RANGE, target)));
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							target.gfx(new Graphics(3000));
						}
					}, 1);
				}
			}, 3);
		} else {
			npc.animate(new Animation(16195));
			npc.gfx(new Graphics(2995));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					delayHit(npc, 2, target, getMagicHit(npc,
							getRandomMaxHit(npc, defs.getMaxHit() - 2, NPCCombatDefinitions.MAGE, target)));
							World.sendJadProjectile(npc, target, 2996);
				}
			}, 3);
		}

		return defs.getAttackDelay() + 2;
	}

}
