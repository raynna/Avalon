package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.minigames.duel.DuelArena;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class NPCContact { 
	
	public static boolean cast(Player player, double xp) {
		if (player.isInCombat(10000) || player.getControlerManager().getControler() instanceof DuelArena) {
			player.getPackets().sendGameMessage("You can't npc contact right now.");
			return false;
		}
		if ((Long) player.temporaryAttribute().get("NPC_Contact") != null
				&& (Long) player.temporaryAttribute().get("NPC_Contact") + 15000 > Utils.currentTimeMillis()) {
			player.getPackets().sendGameMessage("You can only cast this spell every 15 seconds.");
			return false;
		} 
		player.stopAll();
		player.lock(3);
		player.addXp(Skills.MAGIC, xp);
		player.temporaryAttribute().put("NPC_Contact", Utils.currentTimeMillis());
		player.getInterfaceManager().sendInterface(88);
		return true;
	}
}
