package com.rs.game.objects.scripts;

import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;
import com.rs.game.player.content.PartyRoom;

public class PartyRoomChest extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 2418 };
	}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public boolean processObject2(Player player, WorldObject object) {
		PartyRoom.openPartyChest(player);
		return true;
	}
}

