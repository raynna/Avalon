package com.rs.game.npc.combat.impl.dung;

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

public class AnimatedBookCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[]
		{ 10744 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		boolean meleeAttack = Utils.random(2) == 0;
		if (meleeAttack) { // melee
			if (!Utils.isOnRange(npc.getX(), npc.getY(), npc.getSize(), target.getX(), target.getY(), target.getSize(), 0)) {
				magicAttack(npc, target);
				return npc.getAttackSpeed();
			} else {
				meleeAttack(npc, target);
				return npc.getAttackSpeed();
			}
		} else {
			magicAttack(npc, target);
			return npc.getAttackSpeed();
		}
	}

	private void meleeAttack(NPC npc, Entity target) {
		npc.animate(new Animation(13479));
		delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, 100, NPCCombatDefinitions.MELEE, target)));
	}

	private void magicAttack(NPC npc, final Entity target) {
		npc.animate(new Animation(13480));
		npc.gfx(new Graphics(2728));
		delayHit(npc, 1, target, getMagicHit(npc, getRandomMaxHit(npc, 100, NPCCombatDefinitions.MAGE, target)));
		World.sendProjectile(npc, target, 2731, 34, 16, 30, 35, 16, 0);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				target.gfx(new Graphics(2738, 0, 80));
			}
		}, 2);
	}
}
