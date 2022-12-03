package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;

public class SpinolypCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Spinolyp" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.animate(new Animation(defs.getAttackEmote()));
		World.sendNPCProjectile(npc, target, npc.getCombatDefinitions().getAttackProjectile());
		// range based magic attack
		int damage = getRandomMaxHit(npc, 10, NPCCombatDefinitions.RANGE, target);
		delayHit(npc, 1, target, getMagicHit(npc, damage));
		// drain prayer points on sucessfull hit
		if (damage > 0) {
			if (target instanceof Player) {
				Player p2 = (Player) target;
				boolean spectral = p2.getEquipment().getShieldId() == 13744;
				p2.getPrayer().drainPrayer(spectral ? 5 : 10);
			}
		}
		return defs.getAttackDelay();
	}
}
