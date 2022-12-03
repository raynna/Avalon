package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public class ThunderCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 11872 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (Utils.getRandom(4) == 0) {
			switch (Utils.getRandom(3)) {
			case 0:
			npc.setNextForceTalk(new ForceTalk("RAAAAAARRRRRRGGGGHHHH"));
			break;
		case 1:
			npc.setNextForceTalk(new ForceTalk("You're going straight to hell!"));
			break;
		case 2:
			String name = "";
			if (target instanceof Player)
				name = ((Player) target).getDisplayName();
			npc.setNextForceTalk(new ForceTalk("I'm going to crush you, " + name));
			break;
		case 3:
			name = "";
			if (target instanceof Player)
				name = ((Player) target).getDisplayName();
			npc.setNextForceTalk(new ForceTalk("Die with pain, " + name));
			break;
		}
		}
		//TODO: Get right gfx
		if (Utils.getRandom(1) == 0) { // mage magical attack
			npc.setCapDamage(800);
			npc.animate(new Animation(14525));
			npc.setNextForceTalk(new ForceTalk("FUS RO DAH"));
			npc.playSound(168, 2);
			for (Entity t : npc.getPossibleTargets()) {
				if (!t.withinDistance(npc, 18))
					continue;
				int damage = getRandomMaxHit(npc, defs.getMaxHit(),
						NPCCombatDefinitions.MAGE, t);
				if (damage > 0) {
					delayHit(npc, 1, t, getMagicHit(npc, damage));
					t.gfx(new Graphics(3428));
				}
			}

		} else { // melee attack
			npc.animate(new Animation(defs.getAttackEmote()));
			npc.setCapDamage(800);
			delayHit(
					npc,
					0,
					target,
					getMeleeHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.MELEE, target)));
		}
		return defs.getAttackDelay();
	}
}