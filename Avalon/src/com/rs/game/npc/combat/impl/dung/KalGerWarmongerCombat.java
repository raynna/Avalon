package com.rs.game.npc.combat.impl.dung;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.dungeonnering.KalGerWarmonger;
import com.rs.game.player.Player;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class KalGerWarmongerCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[]
				{ "Kal'Ger the Warmonger" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final KalGerWarmonger boss = (KalGerWarmonger) npc;
		final DungeonManager manager = boss.getManager();
		if (boss.getType() == 0 || boss.isMaximumPullTicks())
			return 0;
		if (boss.isUsingMelee()) {
			boolean smash = false;
			
			for (Player player : manager.getParty().getTeam()) {
				if (Utils.colides(player.getX(), player.getY(), player.getSize(), boss.getX(), boss.getY(), 5)) {
					smash = true;
					break;
				}
			}
			if (smash) {
				boss.animate(new Animation(14968));
				boss.gfx(new Graphics(2867));
				for (Player player : manager.getParty().getTeam()) {
					if (!manager.isAtBossRoom(player))
						continue;
					player.getPackets().sendCameraShake(3, 25, 50, 25, 50);//everyone's camera shakes
					if (Utils.inCircle(player, boss, 5))//5 square radius (imperfect circle)
						player.applyHit(new Hit(boss, Utils.random(300, boss.getMaxHit()), HitLook.REGULAR_DAMAGE));
				}
				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {
						boss.setPullTicks(0);
						for (Player player : manager.getParty().getTeam())//we obv need to reset the camera ^.^
							player.getPackets().sendStopCameraShake();
					}
				});
			} else if (!Utils.isOnRange(npc.getX(), npc.getY(), npc.getSize(), target.getX(), target.getY(), target.getSize(), 0))
				return 0;
		}
		boss.setPullTicks(0);
		if (boss.getAnnoyanceMeter() == 8) {//This part is essentially done
			//boss.playSoundEffect(2986);
			boss.setNextForceTalk(new ForceTalk("GRRRR!"));
		} else if (boss.getAnnoyanceMeter() == 10) {
			//boss.playSoundEffect(3012);
			boss.setNextForceTalk(new ForceTalk("ENOUGH!"));
		}
		if (boss.getType() == 1) {//NO WEAPONS HUR
			npc.animate(new Animation(14392));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, boss.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
		} else if (boss.getType() == 2) {//LONG
			npc.animate(new Animation(14416));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, boss.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
		} else if (boss.getType() == 3) {//STAFF
			npc.animate(new Animation(14996));
			npc.gfx(new Graphics(2874));
			for (Entity t : boss.getPossibleTargets()) {
				World.sendProjectile(boss, t, 2875, 65, 10, 50, 0, 5, 1);
				t.gfx(new Graphics(2873));
				delayHit(npc, 0, t, getMagicHit(npc, getRandomMaxHit(npc, boss.getMaxHit(), NPCCombatDefinitions.MAGE, t)));
			}
		} else if (boss.getType() == 4) {//2H
			npc.animate(new Animation(14450));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, boss.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
		} else if (boss.getType() == 5) {//BOW
			npc.animate(new Animation(14537));
			npc.gfx(new Graphics(2885));
			for (Entity t : boss.getPossibleTargets()) {
				World.sendProjectile(boss, t, 2886, 75, 30, 50, 55, 2, 0);
				delayHit(npc, 2, t, getRangeHit(npc, getRandomMaxHit(npc, boss.getMaxHit(), NPCCombatDefinitions.RANGE, t)));
			}
		} else if (boss.getType() == 6) {//MAUL
			npc.animate(new Animation(14963));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, boss.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			return 3;//SUPER OP MODE!
		}
		return 4;
	}
}
