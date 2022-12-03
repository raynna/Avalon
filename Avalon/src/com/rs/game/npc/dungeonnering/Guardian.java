package com.rs.game.npc.dungeonnering;

import com.rs.game.Entity;
import com.rs.game.WorldTile;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;

@SuppressWarnings("serial")
public class Guardian extends DungeonNPC {

	private RoomReference reference;

	public Guardian(int id, WorldTile tile, DungeonManager manager, RoomReference reference, double multiplier) {
		super(id, tile, manager, multiplier);
		this.reference = reference;
		setForceAgressive(true);
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		getManager().updateGuardian(reference);
	}

}
