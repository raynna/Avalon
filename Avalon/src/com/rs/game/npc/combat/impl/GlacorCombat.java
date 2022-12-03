package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.glacior.Glacor;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class GlacorCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 14301 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Glacor glacor = (Glacor) npc;
		NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (Utils.random(4) == 0)
			glacor.setRangeAttack(!glacor.isRangeAttack());
		if (target instanceof Player) {
			Player player = (Player) target;
			if (glacor.getEffect() == 1)
				player.getPrayer().drainPrayer((int) (player.getPrayer().getPrayerpoints() * .1));
			switch (Utils.getRandom(5)) {
			case 0:
			case 1:
			case 2:
				sendDistancedAttack(glacor, target);
				break;
			case 3:
				if (Utils.isOnRange(npc.getX(), npc.getY(), npc.getSize(), target.getX(), target.getY(),
						target.getSize(), 0)) {
					npc.animate(new Animation(9955));
					delayHit(npc, 0, target,
							getMeleeHit(npc, getRandomMaxHit(npc, 350, NPCCombatDefinitions.MELEE, target)));
				} else
					sendDistancedAttack(glacor, target);
				break;
			case 4:
				final WorldTile tile = new WorldTile(target);
				npc.animate(new Animation(9955));
				World.sendProjectile(npc, tile, 2314, 50, 0, 46, 20, 0, 10);
				glacor.setRangeAttack(true);
				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {
						for (Entity e : npc.getPossibleTargets()) {
							if (e instanceof Player) {
								Player player = (Player) e;
								if (player.withinDistance(tile, 0))
									player.applyHit(new Hit(npc, player.getHitpoints() / 2, HitLook.RANGE_DAMAGE));
								player.getPackets().sendGraphics(new Graphics(2315), tile);
							}
						}
					}
				}, 3);
				break;
			}
		}
		return defs.getAttackDelay();
	}

	private void sendDistancedAttack(Glacor npc, final Entity target) {
		boolean isRangedAttack = npc.isRangeAttack();
		if (isRangedAttack) {
			delayHit(npc, 2, target, getRangeHit(npc, getRandomMaxHit(npc, 294, NPCCombatDefinitions.RANGE, target)));
			World.sendProjectile(npc, target, 962, 50, 30, 46, 30, 0, 10);
		} else {
			delayHit(npc, 2, target, getMagicHit(npc, getRandomMaxHit(npc, 264, NPCCombatDefinitions.MAGE, target)));
			World.sendProjectile(npc, target, 634, 50, 30, 46, 30, 5, 10);
			if (Utils.random(5) == 0) {
				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {
						target.gfx(new Graphics(369));
						target.setFreezeDelay(10000); // ten seconds
					}
				});
			}
		}
		npc.animate(new Animation(isRangedAttack ? 9968 : 9967));
	}

}
