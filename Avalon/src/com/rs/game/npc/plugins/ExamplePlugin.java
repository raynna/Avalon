package com.rs.game.npc.plugins;

import com.rs.game.npc.NPC;
import com.rs.game.npc.NpcPlugin;
import com.rs.game.player.Player;

public class ExamplePlugin extends NpcPlugin {

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
