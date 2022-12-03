package com.rs.game.player.dialogues.player;

import com.rs.game.WorldTile;
import com.rs.game.player.actions.combat.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LowLevelTeleports extends Dialogue {

	@Override
	public void start() {
		stage = 0;
		sendOptionsDialogue("Low-level Teleport Options...", "Cows.", "Rock Crabs (Rellekka).", "Hill Giants.",
				"Experiments.", "More...");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case 0:
			switch (componentId) {
			case OPTION_1:
				teleport("cows");
				break;
			case OPTION_2:
				teleport("rcs");
				break;
			case OPTION_3:
				teleport("hills");
				break;
			case OPTION_4:
				teleport("experiments");
				break;
			case OPTION_5:
				stage = 1;
				sendOptionsDialogue("Low-level Teleport Options...", "Fire Giants.", "Ogres.", "Shades.",
						"Jungle Spiders.", "Back.");
				break;
			}
			break;

		case 1:
			switch (componentId) {
			case OPTION_1:
				teleport("fires");
				break;
			case OPTION_2:
				teleport("ogres");
				break;
			case OPTION_3:
				teleport("shades");
				break;
			case OPTION_4:
				teleport("jungle");
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
		case "cows":
			Magic.sendObjectTeleportSpell(player, false, new WorldTile(3256, 3266, 0));
			break;
		case "rcs":// rock crabs
			Magic.sendObjectTeleportSpell(player, false, new WorldTile(2674, 3696, 0));
			break;
		case "hills":// hill giants
			Magic.sendObjectTeleportSpell(player, false, new WorldTile(3115, 3450, 0));
			break;
		case "experiments":
			Magic.sendObjectTeleportSpell(player, false, new WorldTile(3555, 9946, 0));
			break;
		case "fires":// fire giants
			Magic.sendObjectTeleportSpell(player, false, new WorldTile(2575, 9862, 0));
			break;
		case "ogres":
			Magic.sendObjectTeleportSpell(player, false, new WorldTile(2480, 2882, 0));
			break;
		/*
		 * case "shades": player.getPackets().sendGameMessage(
		 * "This teleport is temporarily disabled.");
		 * //Magic.sendObjectTeleportSpell(player, false, new
		 * WorldTile(player)); break; case "jungle"://jungle spiders
		 * player.getPackets().sendGameMessage(
		 * "This teleport is temporarily disabled.");
		 * //Magic.sendObjectTeleportSpell(player, false, new
		 * WorldTile(player)); break;
		 */

		default:
			player.getPackets()
					.sendGameMessage("This teleport appears to have no destination. Report this to a developer.");
			break;
		}
		end();
		player.getDialogueManager().finishDialogue();
	}

}
