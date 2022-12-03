package com.rs.game.player.content;

import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Rain
 * @Date - 6 Mar 2016
 *
 **/

public class Update {

	private static final int REPEAT_DELAY = 35;

	public static final String[] ALL_UPDATES = { "Need help or information about updates? Talk to me!",
			"Ancient effigies are dropped by any NPC above level 10. Drop chance is 5%!",
			"Hardcore ironman & Normal ironman mode is out!", "Remember to give us your feedback!",
			"Security is important! Visit the security manager to protect your account.",
			"Adventure logs are now ingame! Make sure to check it out by the Adventure log NPC!" };

	public static void ProcessUpdates() {
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				for (NPC npc : World.getNPCs()) {
					if (npc.getId() >= 6135 && npc.getId() <= 6139) {
						npc.animate(new Animation(6865));
						npc.setNextForceTalk(new ForceTalk(GrabUpdates()));
					}
				}
			}
		}, 0, REPEAT_DELAY);
	}

	public static String GrabUpdates() {
		int random = Utils.random(0, ALL_UPDATES.length - 1);
		return ALL_UPDATES[random];
	}

}
