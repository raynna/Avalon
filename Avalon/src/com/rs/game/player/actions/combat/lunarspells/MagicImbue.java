package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class MagicImbue {

	public static boolean cast(Player player, double xp) {
		Boolean lastImbue = (Boolean) player.temporaryAttribute().get("LAST_IMBUE");
		if (lastImbue != null && lastImbue == Boolean.TRUE) {
			player.getPackets().sendGameMessage("You can only cast this spell every 12 seconds.");
			return false;
		}
		player.getTemporaryAttributtes().put("LAST_IMBUE", Boolean.TRUE);
		player.getPackets().sendGameMessage("You are charged to combine runes.");
		WorldTasksManager.schedule(new WorldTask() {

			int time;

			@Override
			public void run() {
				time++;
				if (time == 12) {
					player.getPackets().sendGameMessage("Magic Imbue spell has run out.");
					player.getTemporaryAttributtes().put("LAST_IMBUE", Boolean.FALSE);
					stop();
					return;
				}
				if (time == 10) {
					player.getPackets().sendGameMessage("Magic Imbue spell charge running out...");
				}
			}
		}, 1, 1);
		return true;
	}

}
