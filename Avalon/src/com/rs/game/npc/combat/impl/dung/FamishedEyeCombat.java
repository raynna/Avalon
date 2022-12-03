package com.rs.game.npc.combat.impl.dung;

import java.util.LinkedList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.dungeonnering.FamishedEye;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class FamishedEyeCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[]
		{ 12436, 12451, 12466 };
	}

	@Override
	public int attack(NPC npc, final Entity target) {
		final FamishedEye eye = (FamishedEye) npc;

		if (eye.isInactive())
			return 0;
		else if (!eye.isFirstHit()) {
			eye.setFirstHit(true);
			return Utils.random(5, 15);
		}

		npc.animate(new Animation(14916));
		WorldTasksManager.schedule(new WorldTask() {

			private List<WorldTile> tiles;
			private WorldTile targetTile;

			int cycles;

			@Override
			public void run() {
				cycles++;
				if (cycles == 1) {
					tiles = new LinkedList<WorldTile>();
					targetTile = new WorldTile(target);
					World.sendProjectile(eye, targetTile, 2849, 35, 30, 41, 0, 15, 0);
				} else if (cycles == 2) {
					for (int x = -1; x < 2; x++) {
						for (int y = -1; y < 2; y++) {
							WorldTile attackedTile = targetTile.transform(x, y, 0);
							if (x != y)
								World.sendProjectile(eye, targetTile, attackedTile, 2851, 35, 0, 26, 40, 16, 0);
							tiles.add(attackedTile);
						}
					}
				} else if (cycles == 3) {
					for (WorldTile tile : tiles) {
						if (!tile.matches(targetTile))
							World.sendGraphics(eye, new Graphics(2852, 35, 5), tile);
						for (Entity t : eye.getPossibleTargets()) {
							if (t.matches(tile))
								t.applyHit(new Hit(eye, (int) Utils.random(eye.getMaxHit() * .25, eye.getMaxHit()), HitLook.REGULAR_DAMAGE));
						}
					}
					tiles.clear();
					stop();
					return;
				}
			}
		}, 0, 0);
		return (int) Utils.random(5, 35);
	}
}
