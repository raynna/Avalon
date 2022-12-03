package com.rs.game.npc.combat.impl.dung;

import java.util.LinkedList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.dungeonnering.RuneboundBehemoth;
import com.rs.game.player.Player;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class RuneboundBehemothCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[]
		{ "Runebound behemoth" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final RuneboundBehemoth boss = (RuneboundBehemoth) npc;
		final DungeonManager manager = boss.getManager();

		boolean trample = false;
		for (Entity t : npc.getPossibleTargets()) {
			if (Utils.colides(t.getX(), t.getY(), t.getSize(), npc.getX(), npc.getY(), npc.getSize())) {
				trample = true;
				delayHit(npc, 0, t, getRegularHit(npc, getRandomMaxHit(npc, npc.getMaxHit(), NPCCombatDefinitions.MELEE, t)));
				if (t instanceof Player)
					((Player) t).getPackets().sendGameMessage("The beast tramples you.");
			}
		}
		if (trample) {
			npc.animate(new Animation(14426));
			return 5;
		}

		if (Utils.random(15) == 0) {// Special attack
			final List<WorldTile> explosions = new LinkedList<WorldTile>();
			boss.setNextForceTalk(new ForceTalk("Raaaaaaaaaaaaaaaaaaaaaaaaaawr!"));
			WorldTasksManager.schedule(new WorldTask() {

				int cycles;

				@Override
				public void run() {
					cycles++;
					if (cycles == 1) {
						boss.gfx(new Graphics(2769));
					} else if (cycles == 4) {
						boss.gfx(new Graphics(2770));
					} else if (cycles == 5) {
						boss.gfx(new Graphics(2771));
						for (Entity t : boss.getPossibleTargets()) {
							tileLoop: for (int i = 0; i < 4; i++) {
								WorldTile tile = Utils.getFreeTile(t, 2);
								if (!manager.isAtBossRoom(tile))
									continue tileLoop;
								explosions.add(tile);
								World.sendProjectile(boss, tile, 2414, 120, 0, 20, 0, 20, 0);
							}
						}
					} else if (cycles == 8) {
						for (WorldTile tile : explosions)
							World.sendGraphics(boss, new Graphics(2399), tile);
						for (Entity t : boss.getPossibleTargets()) {
							tileLoop: for (WorldTile tile : explosions) {
								if (t.getX() != tile.getX() || t.getY() != tile.getY())
									continue tileLoop;
								t.applyHit(new Hit(boss, (int) Utils.random(boss.getMaxHit() * .6, boss.getMaxHit()), HitLook.REGULAR_DAMAGE));
							}
						}
						boss.resetTransformation();
						stop();
						return;
					}
				}
			}, 0, 0);
			return 8;
		}
		int[] possibleAttacks = new int[]
		{ 0, 1, 2 };
		if (target instanceof Player) {
			Player player = (Player) target;
			if (player.getPrayer().isMeleeProtecting())
				possibleAttacks = new int[]
				{ 1, 2 };
			else if (player.getPrayer().isRangeProtecting())
				possibleAttacks = new int[]
				{ 0, 1 };
			else if (player.getPrayer().isMageProtecting())
				possibleAttacks = new int[]
				{ 0, 2 };
		}
		boolean distanced = !Utils.isOnRange(npc.getX(), npc.getY(), npc.getSize(), target.getX(), target.getY(), target.getSize(), 0);
		int attack = possibleAttacks[Utils.random(possibleAttacks.length)];
		if (attack == 0 && distanced)
			attack = possibleAttacks[1];
		switch (attack) {
		case 0://melee
			boss.animate(new Animation(14423));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, npc.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			break;
		case 1://green exploding blob attack (magic)
			boss.animate(new Animation(14427));
			//boss.setNextGraphics(new Graphics(2413));
			World.sendProjectile(npc, target, 2414, 41, 16, 50, 40, 0, 0);
			delayHit(npc, 1, target, getMagicHit(npc, getRandomMaxHit(npc, npc.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			target.gfx(new Graphics(2417, 80, 0));
			break;
		case 2://green blob attack (range)
			boss.animate(new Animation(14424));
			boss.gfx(new Graphics(2394));
			World.sendProjectile(npc, target, 2395, 41, 16, 50, 40, 0, 2);
			delayHit(npc, 1, target, getRangeHit(npc, getRandomMaxHit(npc, npc.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			target.gfx(new Graphics(2396, 80, 0));
			break;
		}
		return 6;
	}
}
