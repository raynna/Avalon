package com.rs.game.player.dialogues.player;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class DungeonTeleports extends Dialogue {

	@Override
	public void start() {
		stage = 0;
		sendOptionsDialogue("Dungeon Teleports", "Taverly Dungeon", "Brimhaven Dungeon", "Waterbirth Dungeon.",
				"Fremmennik Slayer Dungeon", "More");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case 0:
			switch (componentId) {
			case OPTION_1:
				teleport("taverlydungeon");
				break;
			case OPTION_2:
				teleport("brimhavendungeon");
				break;
			case OPTION_3:
				teleport("waterbirthdungeon");
				break;
			case OPTION_4:
				teleport("fremmennikdungeon");
				break;
			case OPTION_5:
				stage = 1;
				sendOptionsDialogue("Dungeon Teleports", "Ancient cavern", "Asgarnian Ice Dungeon", "Slayer Tower",
						"Kaphite Lair", "Back");
				break;
			}
			break;
		case 1:
			switch (componentId) {
			case OPTION_1:
				teleport("ancientcavern");
				break;
			case OPTION_2:
				teleport("asgarniandungeon");
				break;
			case OPTION_3:
				teleport("slayertower");
				break;
			case OPTION_4:
				teleport("kalphite");
				break;
			case OPTION_5:
				stage = 0;
				sendOptionsDialogue("Dungeon Teleports", "Taverly Dungeon", "Brimhaven Dungeon", "Waterbirth Dungeon.",
						"Fremmennik Slayer Dungeon", "More");
				break;
			}
			break;
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

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

	private void teleport(String key) {
		switch (key.toLowerCase()) {
		case "ancientcavern":
			sendTeleport(new WorldTile(2511, 3514, 0));
			break;
		case "waterbirthdungeon":
			sendTeleport(new WorldTile(2442, 10147, 0));
			break;
		case "taverlydungeon":
			sendTeleport(new WorldTile(2885, 9795, 0));
			break;
		case "brimhavendungeon":
			sendTeleport(new WorldTile(2710, 9564, 0));
			break;
		case "slayertower":
			sendTeleport(new WorldTile(3429, 3538, 0));
			break;
		case "fremmennikdungeon":
			sendTeleport(new WorldTile(2808, 10002, 0));
			break;
		case "asgarniandungeon":
			sendTeleport(new WorldTile(3009, 3150, 0));
			break;
		case "kalphite":
			sendTeleport(new WorldTile(3485, 9510, 2));
			break;
		default:
			player.getPackets()
					.sendGameMessage("This teleport appears to have no destination. Report this to a developer.");
			break;
		}
		end();
	}

}
