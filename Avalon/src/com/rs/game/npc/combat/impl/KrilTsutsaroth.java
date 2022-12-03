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
import com.rs.utils.Utils;

public class KrilTsutsaroth extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 6203 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (Utils.getRandom(4) == 0) {
			switch (Utils.getRandom(8)) {
			case 0:
				npc.setNextForceTalk(new ForceTalk("Attack them, you dogs!"));
				npc.playSound(3282, 2);
				break;
			case 1:
				npc.setNextForceTalk(new ForceTalk("Forward!"));
				npc.playSound(3276, 2);
				break;
			case 2:
				npc.setNextForceTalk(new ForceTalk("Death to Saradomin's dogs!"));
				npc.playSound(3277, 2);
				break;
			case 3:
				npc.setNextForceTalk(new ForceTalk("Kill them, you cowards!"));
				npc.playSound(3290, 2);
				break;
			case 4:
				npc.setNextForceTalk(new ForceTalk("The Dark One will have their souls!"));
				npc.playSound(3229, 2);
				break;
			case 5:
				npc.setNextForceTalk(new ForceTalk("Zamorak curse them!"));
				npc.playSound(3270, 2);
				break;
			case 6:
				npc.setNextForceTalk(new ForceTalk("Rend them limb from limb!"));
				npc.playSound(3273, 2);
				break;
//			case 7:
//				npc.setNextForceTalk(new ForceTalk("No retreat!")); //TODO: Get missing id
//				break;
			case 8:
				npc.setNextForceTalk(new ForceTalk("Flay them all!"));
				npc.playSound(3279, 2);
				break;
			}
		}
		int attackStyle = Utils.getRandom(2);
		switch (attackStyle) {
		case 0:// magic flame attack
			npc.animate(new Animation(14962));
			npc.gfx(new Graphics(1210));
			for (Entity t : npc.getPossibleTargets()) {
				delayHit(npc, 1, t, getMagicHit(npc, getRandomMaxHit(npc, 300, NPCCombatDefinitions.MAGE, t)));
				World.sendProjectile(npc, t, 1211, 41, 16, 41, 35, 16, 0);
				if (Utils.getRandom(4) == 0)
					t.getPoison().makePoisoned(168);
			}
			break;
		case 1:// main attack
			int[] attackEmote = { 14374, 14375 };
			npc.animate(new Animation(attackEmote[Utils.getRandom(attackEmote.length - 1)]));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, 463, NPCCombatDefinitions.MELEE, target)));
			break;
		case 2:// melee attack
			int damage = 463;// normal
			if (target instanceof Player) {
				Player p2 = (Player) target;
				if ((p2.getPrayer().usingPrayer(0, 19) || p2.getPrayer().usingPrayer(1, 9))
						&& Utils.getRandom(2) == 0) {
					p2.getPrayer().drainPrayer((Math.round(damage / 2)));
					damage = 597;
					npc.setNextForceTalk(new ForceTalk("YARRRRRRR!"));
					p2.getPackets().sendGameMessage(
							"K'ril Tsutsaroth slams through your protection prayer, leaving you feeling drained.");
				}
			}
			npc.animate(new Animation(damage > 463 ? 14968 : 14963));
			delayHit(npc, 0, target,
					getMeleeHit(npc, getRandomMaxHit(npc, damage, NPCCombatDefinitions.MELEE, target)));
			break;
		}
		return defs.getAttackDelay();
	}
}
