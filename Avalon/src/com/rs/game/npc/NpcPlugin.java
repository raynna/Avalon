package com.rs.game.npc;

import com.rs.game.item.Item;
import com.rs.game.player.Player;

public abstract class NpcPlugin {
	
	public abstract Object[] getKeys();

	public boolean processNpc(Player player, NPC npc) {
		return false;
	}
	
	public boolean processNpc2(Player player, NPC npc) {
		return false;
	}
	
	public boolean processNpc3(Player player, NPC npc) {
		return false;
	}
	
	public boolean processNpc4(Player player, NPC npc) {
		return false;
	}
	
	public boolean processNpc5(Player player, NPC npc) {
		return false;
	}
	
	public boolean processItemOnNpc(Player player, NPC npc, Item item) {
		return false;
	}
	

	public int getDistance() {
		return 0;
	}

}
