package com.rs.game.npc.dungeonnering;

import com.rs.game.Entity;
import com.rs.game.WorldTile;
import com.rs.game.player.content.dungeoneering.DungeonManager;

@SuppressWarnings("serial")
public class SkeletalMinion extends DungeonNPC {

	private NecroLord boss;

	public SkeletalMinion(NecroLord boss, int id, WorldTile tile, DungeonManager manager, double multiplier) {
		super(id, tile, manager, multiplier);
		setForceAgressive(true);
		this.boss = boss;
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 0.6;
	}

	@Override
	public void drop() {

	}

	@Override
	public int getMaxHit() {
		return super.getMaxHit() * 2;
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		boss.removeSkeleton(this);
	}
}
