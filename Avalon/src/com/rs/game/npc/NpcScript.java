package com.rs.game.npc;

import com.rs.Settings;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Logger;

public abstract class NpcScript {
	
	public abstract Object[] getKeys();

	public boolean processNpc(Player player, NPC npc) {
		if (Settings.DEBUG)
			Logger.log("NpcScripts", this.getClass().getSimpleName()
					+ ", NpcClick 1 - NpcId: " + npc.getId());
		return true;
	}
	
	public boolean processNpc2(Player player, NPC npc) {
		if (Settings.DEBUG)
			Logger.log("NpcScripts", this.getClass().getSimpleName()
					+ ", NpcClick 2 - NpcId: " + npc.getId());
		return true;
	}
	
	public boolean processNpc3(Player player, NPC npc) {
		if (Settings.DEBUG)
			Logger.log("NpcScripts", this.getClass().getSimpleName()
					+ ", NpcClick 3 - NpcId: " + npc.getId());
		return true;
	}
	
	public boolean processNpc4(Player player, NPC npc) {
		if (Settings.DEBUG)
			Logger.log("NpcScripts", this.getClass().getSimpleName()
					+ ", NpcClick 4 - NpcId: " + npc.getId());
		return true;
	}
	
	public boolean processNpc5(Player player, NPC npc) {
		if (Settings.DEBUG)
			Logger.log("NpcScripts", this.getClass().getSimpleName()
					+ ", NpcClick 5 - NpcId: " + npc.getId());
		return true;
	}
	
	public boolean processItemOnNpc(Player player, NPC npc, Item item) {
		if (Settings.DEBUG)
			Logger.log("NpcScripts", this.getClass().getSimpleName()
					+ ", ItemOnNpc - NpcId: " + npc.getId() + ", ItemId: " + item.getId());
		return true;
	}
	

	public int getDistance() {
		return 0;
	}

}
