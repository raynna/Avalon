package com.rs.game.npc.scripts;

import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.NpcScript;
import com.rs.game.player.Player;

public class ExampleScript extends NpcScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { -1, null };
	}

	@Override
	public boolean processNpc(Player player, NPC npc) {
		return true;
	}

	@Override
	public boolean processNpc2(Player player, NPC npc) {
		return true;
	}
}
