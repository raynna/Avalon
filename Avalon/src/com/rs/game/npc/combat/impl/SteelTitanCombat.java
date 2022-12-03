package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class SteelTitanCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 7344, 7343 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int distanceX = target.getX() - npc.getX();
		int distanceY = target.getY() - npc.getY();
		boolean distant = false;
		int size = npc.getSize();
		Familiar familiar = (Familiar) npc;
		boolean usingSpecial = familiar.hasSpecialOn();
		int damage = 0;
		int[] damages = { 0, 0, 0, 0 };
		if (distanceX > size || distanceX < -1 || distanceY > size || distanceY < -1)
			distant = true;
		if (usingSpecial) {// priority over regular attack
			npc.animate(new Animation(8190));
			npc.gfx(new Graphics(1449));
			if (distant) {// range hit
				for (Integer appliedDamage : damages) {
					if (target instanceof Player) {
						Player p2 = (Player) target;
						appliedDamage = getRandomMaxHit(npc,
								p2.getPrayer().usingPrayer(1, 6) ? 244 / 10
										: p2.getPrayer().usingPrayer(0, 16) ? 244 / 10 : 244,
								NPCCombatDefinitions.RANGE, target);
					} else {
						appliedDamage = getRandomMaxHit(npc, 244, NPCCombatDefinitions.RANGE, target);
					}
					delayHit(npc, 2, target, getRangeHit(npc, appliedDamage));
					long familiarDelay = 3000;
					familiar.getOwner().addFamiliarDelay(familiarDelay);
					if (familiar.getOwner().toggles("ONEXPPERHIT", false)) {
						familiar.getOwner().getSkills().addXpNoBonus(Skills.RANGE, (familiar.getOwner().toggles("ONEXHITS", false) ? Math.round(appliedDamage) / 10 : appliedDamage));
					} else
					familiar.getOwner().getSkills().addXp(Skills.RANGE, appliedDamage / 3);
				}
			} else {// melee hit
				for (Integer appliedDamage : damages) {
					if (target instanceof Player) {
						Player p2 = (Player) target;
						appliedDamage = getRandomMaxHit(npc,
								p2.getPrayer().usingPrayer(1, 6) ? 244 / 10
										: p2.getPrayer().usingPrayer(0, 16) ? 244 / 10 : 244,
								NPCCombatDefinitions.MELEE, target);
					} else {
						appliedDamage = getRandomMaxHit(npc, 244, NPCCombatDefinitions.MELEE, target);
					}
					delayHit(npc, 2, target, getMeleeHit(npc, appliedDamage));
					long familiarDelay = 3000;
					familiar.getOwner().addFamiliarDelay(familiarDelay);
					if (familiar.getOwner().toggles("ONEXPPERHIT", false)) {
						familiar.getOwner().getSkills().addXpNoBonus(Skills.STRENGTH, (familiar.getOwner().toggles("ONEXHITS", false) ? Math.round(appliedDamage) / 10 : appliedDamage));
					} else
					familiar.getOwner().getSkills().addXp(Skills.STRENGTH, appliedDamage / 3);
				}
			}
		} else {
			if (distant) {
				int attackStage = Utils.getRandom(1);// 2
				switch (attackStage) {
				case 0:// magic
					damage = getRandomMaxHit(npc, 255, NPCCombatDefinitions.MAGE, target);
					npc.animate(new Animation(7694));
					World.sendSlowBowProjectile(npc, target, 1451);
					delayHit(npc, Utils.getDistance(npc, target) > 3 ? 2 : 1, target, getMagicHit(npc, damage));
					if (familiar.getOwner().toggles("ONEXPPERHIT", false)) {
						familiar.getOwner().getSkills().addXpNoBonus(Skills.MAGIC, (familiar.getOwner().toggles("ONEXHITS", false) ? Math.round(damage) / 10 : damage));
					} else
					familiar.getOwner().getSkills().addXp(Skills.MAGIC, damage / 3);
					break;
				case 1:// range
					damage = getRandomMaxHit(npc, 244, NPCCombatDefinitions.RANGE, target);
					npc.animate(new Animation(8190));
					World.sendSlowBowProjectile(npc, target, 1445);
					delayHit(npc, Utils.getDistance(npc, target) > 3 ? 2 : 1, target, getRangeHit(npc, damage));
					if (familiar.getOwner().toggles("ONEXPPERHIT", false)) {
						familiar.getOwner().getSkills().addXpNoBonus(Skills.STRENGTH, (familiar.getOwner().toggles("ONEXHITS", false) ? Math.round(damage) / 10 : damage));
					} else
					familiar.getOwner().getSkills().addXp(Skills.STRENGTH, damage / 3);
					break;
				}
			} else {// melee
				damage = getRandomMaxHit(npc, 244, NPCCombatDefinitions.MELEE, target);
				npc.animate(new Animation(8183));
				delayHit(npc, 0, target, getMeleeHit(npc, damage));
				if (familiar.getOwner().toggles("ONEXPPERHIT", false)) {
					familiar.getOwner().getSkills().addXpNoBonus(Skills.STRENGTH, (familiar.getOwner().toggles("ONEXHITS", false) ? Math.round(damage) / 10 : damage));
				} else
				familiar.getOwner().getSkills().addXp(Skills.STRENGTH, damage / 3);
			}
		}
		return defs.getAttackDelay();
	}
}
