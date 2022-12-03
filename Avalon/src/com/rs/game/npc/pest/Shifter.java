package com.rs.game.npc.pest;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.minigames.pest.PestControl;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class Shifter extends PestMonsters {

	public Shifter(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned,
			int index, PestControl manager) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned, index, manager);
	}

	@Override
	public void processNPC() {
		super.processNPC();
		Entity target = this.getPossibleTargets().get(0);
		if (this.getCombat().process() && !this.withinDistance(target, 10) || Utils.random(15) == 0)
			teleportSpinner(target);
	}

	private void teleportSpinner(WorldTile tile) { // def 3902, death 3903
		setNextWorldTile(tile);
		animate(new Animation(3904));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				gfx(new Graphics(654));// 1502
			}
		});
	}
}
