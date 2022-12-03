package com.rs.game.npc.combat.impl.dung;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.dungeonnering.SkeletalAdventurer;
import com.rs.game.npc.dungeonnering.YkLagorMage;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class ForgottenMage extends CombatScript {

	private static final int[][] ATTACK_TYPES =
		{ { 0, 9, 10 },
		{ 10, 11, 0, 12, 1 },
		{ 12, 13, 0, 1, 3, 2, 14 },
		{ 14, 15, 0, 3, 16 },
		{ 17, 18, 0, 1, 2, 3, 19 },
		{ 20, 21, 0, 1, 2, 22, 4 },
		{ 22, 23, 0, 1, 4, 6, 24 },
		{ 24, 25, 0, 6, 7, 5, 26 },
		{ 26, 27, 6, 7, 8, 5, 28 },
		{ 28, 29, 6, 7, 8, 5, 30 },
		{ 30, 6, 7, 8, 5 }, };

	private static final int[] ATTACK_ANIMATIONS =
		{ 711, 716, 724, 710, 710, 710, 729, 729, 729, 14221, 14222, 14221, 14221, 14220, 14220, 14222, 14223, 14221, 14220, 14222, 14223, 14221, 14220, 14222, 14223, 10546, 10542, 14209, 2791 };

	private static final int[] START_GRAPHICS =
		{ 102, 105, 108, 177, 177, 177, 167, 170, 173, -1, 2701, 2713, 2728, -1, 2707, 2709, 2714, 2728, -1, 2701, 2715, 2728, -1, 2702, 2716, 2728, 457, 2701, 2717, 2728 };

	private static final int[] HIT_GRAPHICS =
		{ 104, 107, 110, 181, 180, 179, 169, 172, 107, 2700, 2708, 2723, 2737, 2700, 2704, 2724, 2738, 2700, 2710, 2725, 2739, 2700, 2710, 2726, 2740, 2700, 2712, 2727, 2741 };

	private static final int[] PROJECTILES =
		{ 103, 106, 109, 178, 178, 178, 168, 171, 174, 2699, 2703, 2718, 2729, 2699, 2704, 2719, 2731, 2699, 2705, 2720, 2733, 2699, 2706, 2721, 2735, 462, 2707, 2722, -1 };

	private static final int[] SKILLS =
		{ Skills.ATTACK, Skills.STRENGTH, Skills.DEFENCE, 0, 1, 2, Skills.DEFENCE, Skills.STRENGTH, Skills.ATTACK };

	@Override
	public Object[] getKeys() {
		return new Object[]
				{ "Forgotten mage" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final int TIER = npc instanceof YkLagorMage ? 3 : npc instanceof SkeletalAdventurer ? 8 : npc.getId() == 10560 ? 0 : ((npc.getId() - 10560) / 5) - 1;
		final int[] POSSIBLE_ATTACKS = ATTACK_TYPES[TIER];
		int attack = POSSIBLE_ATTACKS[Utils.random(POSSIBLE_ATTACKS.length)];
		if ((npc instanceof YkLagorMage || npc instanceof SkeletalAdventurer) && attack <= 8)
			return 0;
		if (attack >= 0 && attack <= 8) {
			double drainRate = attack < 2 ? .95 : attack > 6 ? .90 : 0.0;
			sendWeaken(npc, target, ATTACK_ANIMATIONS[attack], START_GRAPHICS[attack], HIT_GRAPHICS[attack], PROJECTILES[attack], SKILLS[attack], drainRate);
		} else
			sendCombatSpell(npc, target, ATTACK_ANIMATIONS[attack], START_GRAPHICS[attack], HIT_GRAPHICS[attack], PROJECTILES[attack]);
		return 5;
	}

	private void sendCombatSpell(NPC npc, final Entity target, int attack, int start, final int hit, int projectileId) {
		npc.animate(new Animation(attack));
		if (start != -1)
			npc.gfx(new Graphics(start, 0, 0));
		if (attack == 2722) {
			World.sendProjectile(npc, target, 2735, 18, 18, 50, 50, 3, 0);
			World.sendProjectile(npc, target, 2736, 18, 18, 50, 50, 20, 0);
			World.sendProjectile(npc, target, 2736, 18, 18, 50, 50, 110, 0);
		} else
			World.sendProjectile(npc, target, projectileId, 18, 18, 50, 50, 3, 0);
		delayHit(npc, 2, target, getMagicHit(npc, getRandomMaxHit(npc, NPCCombatDefinitions.MAGE, projectileId, target)));
		if (hit == -1)
			return;
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				target.gfx(new Graphics(hit, 0, 85));
			}
		}, 2);
	}

	private void sendWeaken(NPC npc, final Entity target, int attack, int start, final int hit, int projectileId, final int skill, final double percentDrain) {
		npc.animate(new Animation(attack));
		npc.gfx(new Graphics(start, 0, 50));
		World.sendProjectile(npc, target, projectileId, 39, 18, 55, 70, 5, 0);
		if (hit > 0) {
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					if (target instanceof Player) {
						Player player = (Player) target;
						if (percentDrain == 0) {
							player.setFreezeDelay(skill == 0 ? 8 : skill == 1 ? 12 : 16);
						}else
							player.getSkills().set(skill, (int) (player.getSkills().getLevel(skill) * percentDrain));
					}
				}
			}, 2);
			target.gfx(new Graphics(hit, 140, 85));
		}
	}
}
