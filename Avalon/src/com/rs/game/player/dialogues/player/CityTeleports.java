package com.rs.game.player.dialogues.player;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class CityTeleports extends Dialogue {

	@Override
	public void start() {
		stage = 0;
		sendOptionsDialogue("Choose an Area...", "East Ardougne.", "Varrock.", "Burthope.", "Canifis.", "More...");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case 0:
			switch (componentId) {
			case OPTION_1:
				teleport("eastardy");
				break;
			case OPTION_2:
				teleport("varrock");
				break;
			case OPTION_3:
				teleport("burthope");
				break;
			case OPTION_4:
				teleport("canifis");
				break;
			case OPTION_5:
				stage = 1;
				sendOptionsDialogue("Choose an Area...", "Catherby.", "Draynor Village.", "Rellekka.",
						"Seers' Village.", "More...");
				break;
			}
			break;

		case 1:
			switch (componentId) {
			case OPTION_1:
				teleport("catherby");
				break;
			case OPTION_2:
				teleport("draynor");
				break;
			case OPTION_3:
				teleport("rellekka");
				break;
			case OPTION_4:
				teleport("seers");
				break;
			case OPTION_5:
				stage = 2;
				sendOptionsDialogue("Choose an Area...", "Al Kharid.", "TzHaar Dungeon.", "Void Knights' Outpost.",
						"Neitiznot", "Back.");
				break;
			}
			break;

		case 2:
			switch (componentId) {
			case OPTION_1:
				teleport("alkarid");
				break;
			case OPTION_2:
				teleport("tzhaar");
				break;
			case OPTION_3:
				teleport("void");
				break;
			case OPTION_4:
				teleport("neitz");
				break;
			case OPTION_5:
				player.getDialogueManager().startDialogue("WiseOldManOptions");
				break;
			}
			break;
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

	private void teleport(String key) {
		switch (key.toLowerCase()) {
		case "eastardy":
			sendTeleport(new WorldTile(2663, 3305, 0));
			break;
		case "varrock":
			sendTeleport(new WorldTile(3212, 3424, 0));
			break;
		case "burthope":
			sendTeleport(new WorldTile(2890, 3527, 0));
			break;
		case "canifis":
			sendTeleport(new WorldTile(3495, 3491, 0));
			break;
		case "catherby":
			sendTeleport(new WorldTile(2804, 3434, 0));
			break;
		case "draynor":
			sendTeleport(new WorldTile(3104, 3249, 0));
			break;
		case "rellekka":
			sendTeleport(new WorldTile(2674, 3694, 0));
			break;
		case "seers":
			sendTeleport(new WorldTile(2725, 3491, 0));
			break;
		case "alkarid":
			sendTeleport(new WorldTile(3293, 3183, 0));
			break;
		case "tzhaar":
			sendTeleport(new WorldTile(4611, 5129, 0));
			break;
		case "void":
			sendTeleport(new WorldTile(2658, 2663, 0));
			break;
		case "neitz":
			sendTeleport(new WorldTile(2318, 3811, 0));
			break;

		default:
			player.getPackets().sendGameMessage("No teleport found for key: %s. Notify a developer.", key);
			break;
		}
		end();
		player.getDialogueManager().finishDialogue();
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

}
