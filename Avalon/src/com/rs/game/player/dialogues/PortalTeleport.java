package com.rs.game.player.dialogues;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class PortalTeleport extends Dialogue {

	int EMOTE = 9603, GFX = 1684;

	@Override
	public void start() {
		sendNPCDialogue(/*3636*/5195, 9827, "Would you like to teleport somewhere?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stageName == null) {
			stageName = "end";
			player.getDialogueManager().startDialogue("WiseOldManOptions");
			/*
			 * sendOptionsDialogue("Choose an option", "High level monsters",
			 * "Wizard Teleports", "Minigames", "Boss Teleports", " - ");
			 */
		} else if (stageName == "start") {
			if (componentId == OPTION_1) {
				stageName = "highlevel";
				sendOptionsDialogue("Choose a High Level Location", "Jungle strykewyrm", "Desert strykewyrm",
						"Ice strykewyrm", "Jadinko Cave");
			} else if (componentId == OPTION_2) {
				stageName = "end";
				player.getDialogueManager().startDialogue("WiseOldManOptions");
			} else if (componentId == OPTION_3) {
				stageName = "end";
				player.getDialogueManager().startDialogue("MinigameTeles");
			} else if (componentId == OPTION_4) {
				player.getDialogueManager().startDialogue("PvMTeles");
			} else if (componentId == OPTION_5) {
				closeInterface();
				stageName = "end";
			}
		} else if (stageName == "highlevel") {
			if (componentId == OPTION_1) {
				closeInterface();
				stageName = "end";
				sendTeleport(new WorldTile(2447, 2891, 0));// jungle strykewyrm
			} else if (componentId == OPTION_2) {
				closeInterface();
				stageName = "end";
				sendTeleport(new WorldTile(3366, 3156, 0));// desert strykewyrm
			} else if (componentId == OPTION_3) {
				closeInterface();
				stageName = "end";
				sendTeleport(new WorldTile(3435, 5647, 0));// ice strykewyrm
			} else if (componentId == OPTION_4) {
				closeInterface();
				stageName = "end";
				sendTeleport(new WorldTile(3012, 9275, 0));// jadinko cave
			}
		} else if (stageName == "dungeons") {
			if (componentId == OPTION_1) {
				closeInterface();
				stageName = "end";
				// sendTeleport(new WorldTile(2884, 9797, 0));//taverly
			} else if (componentId == OPTION_2) {
				closeInterface();
				stageName = "end";
				// sendTeleport(new WorldTile(2713, 9564, 0));//brimhaven
			} else if (componentId == OPTION_3) {
				closeInterface();
				stageName = "end";
				// sendTeleport(new WorldTile(2806, 10002, 0));//fremennik
			} else if (componentId == OPTION_4) {
				closeInterface();
				stageName = "end";
				// sendTeleport(new WorldTile(2443, 10146, 0));//waterbirth
			} else if (componentId == OPTION_5) {
				closeInterface();
				stageName = "end";
				// sendTeleport(new WorldTile(2511, 3520, 0));//whirlpool to
				// Ancient Cavern
			}
		} else if (stageName == "minigames") {
			if (componentId == OPTION_1) {
				closeInterface();
				stageName = "end";
				sendTeleport(new WorldTile(2994, 9679, 0));// clan wars
				// need to start controler here
			} else if (componentId == OPTION_2) {
				closeInterface();
				stageName = "end";
				sendTeleport(new WorldTile(2442, 3090, 0));// castle wars
			} else if (componentId == OPTION_3) {
				closeInterface();
				stageName = "end";
				sendTeleport(new WorldTile(2662, 2648, 0));// pest control
			} else if (componentId == OPTION_4) {
				closeInterface();
				stageName = "end";
				sendTeleport(new WorldTile(3744, 6420, 0));// fight caves
			} else if (componentId == OPTION_5) {
				closeInterface();
				stageName = "end";
				sendTeleport(new WorldTile(3744, 6420, 0));// fight kiln
			}
		} else if (stageName == "end") {
			end();
		}
	}

	@Override
	public void finish() {

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

	public void closeInterface() {
		player.getInterfaceManager().closeChatBoxInterface();
	}

}
