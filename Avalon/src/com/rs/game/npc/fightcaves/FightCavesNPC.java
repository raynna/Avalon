package com.rs.game.npc.fightcaves;

import java.util.ArrayList;
import java.util.List;

import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;

@SuppressWarnings("serial")
public class FightCavesNPC extends NPC {

	public FightCavesNPC(int id, WorldTile tile) {
		super(id, tile, -1, true, true);
		setForceMultiArea(true);
		setNoDistanceCheck(true);
		setForceAgressive(true);
		setForceTargetDistance(64);
		setForceAgressiveDistance(64);
	}

	@Override
	public void sendDeath(Entity source) {
		gfx(new Graphics(2924 + getSize()));
		super.sendDeath(source);
	}
}
