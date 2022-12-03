package com.rs.game.npc.combat.impl.dung;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.dungeonnering.BalLakThePummeler;
import com.rs.game.player.Player;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class BalLakThePummelerCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[]
		{ "Bal'lak the Pummeller" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final BalLakThePummeler boss = (BalLakThePummeler) npc;
		final DungeonManager manager = boss.getManager();

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();

		boolean smash = Utils.random(5) == 0 && boss.getPoisionPuddles().size() == 0;
		for (Player player : manager.getParty().getTeam()) {
			if (Utils.colides(player.getX(), player.getY(), player.getSize(), npc.getX(), npc.getY(), npc.getSize())) {
				smash = true;
				delayHit(npc, 0, player, getRegularHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, player)));
			}
		}
		if (smash) {
			npc.animate(new Animation(14384));
			npc.setNextForceTalk(new ForceTalk("Rrrraargh!"));
			//npc.playSoundEffect(3038);
			final WorldTile center = manager.getRoomCenterTile(boss.getReference());
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					for (int i = 0; i < 3; i++)
						boss.addPoisionBubble(Utils.getFreeTile(center, 6));
				}
			}, 1);
			return npc.getAttackSpeed();
		}

		if (Utils.random(5) == 0) {
			boss.animate(new Animation(14383));
			for (Entity t : boss.getPossibleTargets()) {
				if (!Utils.isOnRange(npc.getX(), npc.getY(), npc.getSize(), t.getX(), t.getY(), t.getSize(), 0))
					continue;
				int damage = getRandomMaxHit(npc, defs.getMaxHit(), (int) NPCCombatDefinitions.MELEE, t);
				int damage2 = getRandomMaxHit(npc, defs.getMaxHit(), (int) NPCCombatDefinitions.MELEE, t);
				if (t instanceof Player) {
					Player player = (Player) t;
					if ((damage > 0 || damage2 > 0)) {
						player.setPrayerDelay(1000);
						player.getPackets().sendGameMessage("You are injured and currently cannot use protection prayers.");
					}
				}
				delayHit(npc, 0, t, getRegularHit(npc, damage), getRegularHit(npc, damage2));
			}
			return npc.getAttackSpeed();
		}

		switch (Utils.random(2)) {
		case 0://reg melee left

			final boolean firstHand = Utils.random(2) == 0;

			boss.animate(new Animation(firstHand ? defs.getAttackEmote() : defs.getAttackEmote() + 1));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, (int) (npc.getMaxHit() * 0.8), NPCCombatDefinitions.MELEE, target)));
			delayHit(npc, 2, target, getMeleeHit(npc, getRandomMaxHit(npc, (int) (npc.getMaxHit() * 0.8), NPCCombatDefinitions.MELEE, target)));
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					boss.animate(new Animation(firstHand ? defs.getAttackEmote() + 1 : defs.getAttackEmote()));
				}

			}, 1);
			break;
		case 1://magic attack multi
			boss.animate(new Animation(14380));
			boss.gfx(new Graphics(2441));
			for (Entity t : npc.getPossibleTargets()) {
				World.sendProjectile(npc, t, 2872, 50, 30, 41, 40, 0, 0);
				delayHit(npc, 1, t, getMagicHit(npc, getRandomMaxHit(npc, (int) (boss.getMaxHit() * 0.6), NPCCombatDefinitions.MAGE, t)));
			}
			return npc.getAttackSpeed() - 2;
		}

		return npc.getAttackSpeed();
	}
}
