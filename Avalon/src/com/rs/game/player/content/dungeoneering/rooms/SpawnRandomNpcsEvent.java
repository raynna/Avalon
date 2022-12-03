package com.rs.game.player.content.dungeoneering.rooms;

import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;

public class SpawnRandomNpcsEvent implements RoomEvent {

	@Override
	public void openRoom(DungeonManager dungeon, RoomReference reference) {
		dungeon.spawnRandomNPCS(reference);
	}

}
