package com.rs.game.player.dialogues.player;

import com.rs.game.WorldTile;
import com.rs.game.player.dialogues.Dialogue;

public class GWDOptions extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("GodWars Dungeon.", "Armadyl.", "Saradomin.", "Zamorak.", "Bandos.", "Back.");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			switch (componentId) {
			case OPTION_1:
				teleport("arma");
				break;
			case OPTION_2:
				teleport("sara");
				break;
			case OPTION_3:
				teleport("zamorak");
				break;
			case OPTION_4:
				teleport("bandos");
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

	}

	void teleport(String key) {
		switch (key.toLowerCase()) {
		case "arma":
			player.setNextWorldTile(new WorldTile(2872, 5268, 2));
			player.stopAll();
			player.getControlerManager().startControler("GodWars");
			break;

		case "sara":
			player.setNextWorldTile(new WorldTile(2920, 5277, 1));
			player.stopAll();
			player.getControlerManager().startControler("GodWars");
			break;

		case "bandos":
			player.setNextWorldTile(new WorldTile(2848, 5334, 2));
			player.stopAll();
			player.getControlerManager().startControler("GodWars");
			break;

		case "zamorak":
			player.setNextWorldTile(new WorldTile(2885, 5345, 2));
			player.stopAll();
			player.getControlerManager().startControler("GodWars");
			break;
		}
	}

}
