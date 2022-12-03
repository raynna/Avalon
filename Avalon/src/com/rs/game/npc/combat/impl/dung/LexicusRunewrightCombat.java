package com.rs.game.npc.combat.impl.dung;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.dungeonnering.DungeonNPC;
import com.rs.game.npc.dungeonnering.LexicusRunewright;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class LexicusRunewrightCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[]
		{ 9842 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		LexicusRunewright boss = (LexicusRunewright) npc;
		if (boss.getAttackStage() == 0) {
			boss.sendAlmanacArmyAttack(target);
			boss.incrementAttackStage();
			return 3;
		}

		if (boss.getAttackStage() == 1 && Utils.random(4) == 0) {
			sendBookBarrage(boss);
			boss.incrementAttackStage();
			return 5;
		}
		if (boss.getAttackStage() == 2 && Utils.random(4) == 0) {
			boss.sendTeleport();
			boss.resetAttackStage();
			return 9;
		}

		int attack = Utils.random(Utils.isOnRange(target.getX(), target.getY(), target.getSize(), npc.getX(), npc.getY(), npc.getSize(), 0) ? 5 : 4);
		switch (attack) {
		case 0://Range
		case 1:
		case 2:
		case 3://Magic
			boolean range_style = attack == 0 || attack == 1;
			boss.animate(new Animation(13470));
			boss.gfx(new Graphics(range_style ? 2408 : 2424));
			World.sendProjectile(npc, target, range_style ? 2409 : 2425, 40, 40, 54, 35, 5, 0);
			if (range_style)
				delayHit(npc, 1, target, getRangeHit(npc, getRandomMaxHit(npc, npc.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			else
				delayHit(npc, 1, target, getMagicHit(npc, getRandomMaxHit(npc, npc.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			target.gfx(new Graphics(range_style ? 2410 : 2426, 75, 0));
			break;
		case 4://MELEE
			boss.animate(new Animation(13469));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, npc.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			break;
		}
		return 5;
	}

	public static void sendBookBarrage(final LexicusRunewright npc) {

		final List<WorldObject> cases = new ArrayList<WorldObject>();
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				WorldObject o = npc.getManager().getObjectWithType(npc.getReference(), 10, x, y);
				if (o != null && o.getId() >= 49280 && o.getId() <= 49282)
					cases.add(o);
			}
		}

		npc.setNextForceTalk(new ForceTalk("Book barrage!"));
		WorldTasksManager.schedule(new WorldTask() {

			private int cycle = 0;
			private LinkedList<WorldTile> targets = new LinkedList<WorldTile>();

			@Override
			public void run() {
				cycle++;

				if (npc == null || npc.isDead()) {
					stop();
					return;
				}

				if (cycle == 1) {
					for (Entity entity : npc.getPossibleTargets(true, true)) {
						if (entity instanceof DungeonNPC)
							continue;
						WorldTile tile = new WorldTile(entity);
						targets.add(tile);

						for (int i = 0; i < 3; i++) {
							if (cases.isEmpty())
								break;
							WorldObject c = cases.get(Utils.random(cases.size()));
							cases.remove(c);
							World.sendProjectile(npc, c, tile, 2422, 60, 75, 30, 0, 0, 0);
						}
					}
				} else if (cycle == 4) {

					for (WorldTile tile : targets)
						World.sendGraphics(npc, new Graphics(2423), tile);

					for (Entity entity : npc.getPossibleTargets(true, true)) {
						if (entity instanceof DungeonNPC)
							continue;
						tileLoop: for (WorldTile tile : targets) {
							if (entity.getX() != tile.getX() || entity.getY() != tile.getY())
								continue tileLoop;
							entity.applyHit(new Hit(npc, (int) (entity instanceof Familiar ? 1000 : Utils.random(entity.getMaxHitpoints() * .6, entity.getMaxHitpoints() * .9)), HitLook.REGULAR_DAMAGE));
						}
					}
					targets.clear();
					stop();
				}

			}
		}, 0, 0);
		//GFX 2421+
	}
}
