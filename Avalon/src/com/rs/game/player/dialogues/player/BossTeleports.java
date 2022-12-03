package com.rs.game.player.dialogues.player;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class BossTeleports extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Boss Teleports", "God Wars Dungeon.", "Corporeal Beast.", "Kalphite Queen.",
				"Tormented Demons.", "Next.");

	}

	public void sendTeleport(final WorldTile coord) {
		player.lock(4);
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					player.animate(new Animation(7082));
					player.gfx(new Graphics(1229));
				} else if (loop == 2) {
					player.setNextWorldTile(coord);
					player.animate(new Animation(7084));
					player.gfx(new Graphics(1229));
				} else if (loop == 4) {
					player.animate(new Animation(-1));
				}
				loop++;
			}
		}, 0, 1);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			switch (componentId) {
			case OPTION_1:
				player.getDialogueManager().startDialogue("GWDOptions");
				break;
			case OPTION_2:
				end();
				sendTeleport(new WorldTile(2966, 4383, 2));// corp
				break;
			case OPTION_3:
				end();
				sendTeleport(new WorldTile(3507, 9493, 0));// kalphite queen
				break;
			case OPTION_4:
				end();
				sendTeleport(new WorldTile(2572, 5735, 0));// tds
				break;
			case OPTION_5:
				componentId = -1;
				stage = 1;
				sendOptionsDialogue("Boss Teleports", "Daganoth Kings.", "Back.");
				break;
			}
		}

		if (stage == 1) {
			switch (componentId) {
			case OPTION_1:
				end();
				sendTeleport(new WorldTile(2544, 10143, 0));// dag kings
				break;
			case OPTION_2:
				player.getDialogueManager().startDialogue("WiseOldManOptions");
				break;
			}
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

	// add Kalphites to the Low Level Teleports
	// add lava titans KBD tele

}
