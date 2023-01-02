package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.item.Item;
import com.rs.game.minigames.duel.DuelArena;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.LunarMagicks;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class Dream {

	
	
	public void cast(Player player, Item[] runes, double xp) {
		if (player.isInCombat(10000) || player.getControlerManager().getControler() instanceof DuelArena) {
			player.getPackets().sendGameMessage("You can't dream right now.");
			return;
		}
		if (player.getTemporaryAttributtes().get("Dreaming") == Boolean.TRUE) {
			player.getPackets().sendGameMessage("You are already dreaming!");
			return;
		}
		if ((Long) player.temporaryAttribute().get("Dream") != null
				&& (Long) player.temporaryAttribute().get("Dream") + 30000 > Utils.currentTimeMillis()) {
			player.getPackets().sendGameMessage("You can only cast this spell every 30 seconds.");
			return;
		}
		player.stopAll();
		player.lock(6);
		LunarMagicks.removeRunes(player, runes);
		player.animate(new Animation(6295));
		player.gfx(new Graphics(277));
		player.getSkills().addXp(Skills.MAGIC, xp);
		player.temporaryAttribute().put("Dream", Utils.currentTimeMillis());
		player.temporaryAttribute().put("Dreaming", Boolean.TRUE);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				if (player.getTemporaryAttributtes().get("Dreaming") == Boolean.TRUE) {
					player.setNextForceTalk(new ForceTalk("Zzzzz..."));
					player.animate(new Animation(6296));
					player.gfx(new Graphics(277));
				} else {
					stop();
				}
			}
		}, 5, 15);
	}
}
