package com.rs.game.npc.dagannoth;

import com.rs.game.Entity;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;

@SuppressWarnings("serial")
public class DagannothKings extends NPC {

	public DagannothKings(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea,
			boolean spawned) {
		super(id, tile, mapAreaNameHash, true, spawned);
		setIntelligentRouteFinder(false);
		setForceTargetDistance(32);
		setCantFollowUnderCombat(false);
		setForceAgressiveDistance(8);
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (isDead())
			return;
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
	}

}
