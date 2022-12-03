package com.rs.game.npc.combat.impl.dung;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;

public class TomeOfLexicus extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[]
		{ 9856, 9857, 9858 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		int type = npc.getId() - 9856;
		switch (type) {
		case 0:
			npc.animate(new Animation(13479));
			delayHit(npc, 0, target, getMagicHit(npc, getRandomMaxHit(npc, npc.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			break;
		case 1:
		case 2:
			boolean range_style = type == 1;
			npc.animate(new Animation(13480));
			npc.gfx(new Graphics(range_style ? 2408 : 2424));
			World.sendProjectile(npc, target, range_style ? 2409 : 2425, 40, 40, 54, 35, 5, 0);
			if (range_style)
				delayHit(npc, 1, target, getRangeHit(npc, getRandomMaxHit(npc, npc.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			else
				delayHit(npc, 1, target, getMagicHit(npc, getRandomMaxHit(npc, npc.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			target.gfx(new Graphics(range_style ? 2410 : 2426, 75, 0));
			break;
		}
		return 4;
	}
}
