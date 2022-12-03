package com.rs.game.npc.combat.impl.dung;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.dungeonnering.Gravecreeper;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class GravecreeperCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[]
		{ 11708 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final Gravecreeper boss = (Gravecreeper) npc;
		if (boss.getSpecialDelay() != -2 && (boss.getSpecialDelay() == -1 || (Utils.random(10) == 0 && boss.getSpecialDelay() <= Utils.currentTimeMillis()))) { //might change this chance here
			if (boss.getSpecialDelay() != -1 && Utils.random(5) != 0) {
				boss.setNextForceTalk(new ForceTalk("Burrnnn!"));
				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {
						boss.createBurnTiles(new WorldTile(boss));
					}
				}, 1);
				boss.setSpecialDelay(Utils.currentTimeMillis() + Gravecreeper.BURN_DELAY);
				if (Utils.isOnRange(npc.getX(), npc.getY(), npc.getSize(), target.getX(), target.getY(), target.getSize(), 0)) {
					boss.setForceFollowClose(true);
					WorldTasksManager.schedule(new WorldTask() {

						@Override
						public void run() {
							boss.setForceFollowClose(false);
						}
					}, 7);
				}
				return 4;
			} else {
				boss.useSpecial();
				return 4;
			}
		}

		boolean atDistance = !Utils.isOnRange(npc.getX(), npc.getY(), npc.getSize(), target.getX(), target.getY(), target.getSize(), 0);
		int attack = Utils.random(!atDistance ? 2 : 1);
		switch (attack) {
		case 0://range
			npc.animate(new Animation(14504));
			World.sendProjectile(npc, target, 2753, 65, 65, 30, 0, 0, 0);
			delayHit(npc, 1, target, getRangeHit(npc, getRandomMaxHit(npc, npc.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			break;
		case 1://melee
			npc.animate(new Animation(14503));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, npc.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			break;
		}
		return 4;
	}
}
