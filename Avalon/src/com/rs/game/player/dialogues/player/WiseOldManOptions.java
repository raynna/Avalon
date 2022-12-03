package com.rs.game.player.dialogues.player;

import com.rs.game.WorldTile;
import com.rs.game.player.actions.combat.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class WiseOldManOptions extends Dialogue {

	public static final int NPC = 2253;

	private int stage;

	@Override
	public void start() {
		stage = 5;
		sendOptionsDialogue("Take me to...", "Minigame Teleports.", "Dungeon Teleports.", "Boss Teleports.",
				"City Teleports.", "Skilling Teleports");
	}

	@Override
	public void run(int interfaceId, int componentId) {

		if (stage == 5) {
			switch (componentId) {
			case OPTION_1:
				player.getDialogueManager().startDialogue("MinigameTeles");
				break;
			case OPTION_2:
				player.getDialogueManager().startDialogue("DungeonTeleports");
				break;
			case OPTION_3:
				player.getDialogueManager().startDialogue("BossTeleports");
				break;
			case OPTION_4:
				player.getDialogueManager().startDialogue("CityTeleports");
				// stage = 7;
				// sendOptionsDialogue("Agility Teleport Options...", "Gnome
				// Agility Course.", "Barbarian Agility Course.", "Ape Atoll
				// Agility Course.", "Return.");
				break;
			case OPTION_5:
				player.getDialogueManager().startDialogue("Skillingz");
				// stage = 8;
				// sendOptionsDialogue("Mining Teleport Options...", "Essence
				// Mine.", "Falador Mine.", "Living Rock Caverns.", "Return.");
				break;

			}
			return;
		}

		if (stage == 8) {
			switch (componentId) {
			case OPTION_1:
				teleport(4);
				break;
			case OPTION_2:
				teleport(5);
				break;
			case OPTION_3:
				teleport(6);
				break;
			case OPTION_4:
				stage = 5;
				sendOptionsDialogue("Take me to...", "Low-level Teleports.", "High-level Teleports.", "City Teleports.",
						"Agility Teleports.", "Mining Teleports");
				break;
			}
		}

		switch (stage) {
		case 7:// agility teleports
			switch (componentId) {
			case OPTION_1:
				teleport(1);
				break;
			case OPTION_2:
				teleport(2);
				break;
			case OPTION_3:
				teleport(3);
				break;
			case OPTION_4:
				stage = 5;
				sendOptionsDialogue("Take me to...", "Low-level Teleports.", "High-level Teleports.", "City Teleports.",
						"Agility Teleports.", "Mining Teleports");
				break;
			}
			break;
		}
	}

	@Override
	public void finish() {

	}

	public void teleport(int i) {
		switch (i) {
		case 1:// Gnome Agility
			Magic.sendObjectTeleportSpell(player, false, new WorldTile(2471, 3437, 0));
			break;
		case 2:// Barbarian Agility
			Magic.sendObjectTeleportSpell(player, false, new WorldTile(2542, 3569, 0));
			break;
		/*
		 * case 3://Ape Atoll Agility //Magic.sendObjectTeleportSpell(player,
		 * false, new WorldTile(player)); player.getPackets().sendGameMessage(
		 * "This teleport is temporarily disabled."); break;
		 */
		case 4:// Ess mine
			Magic.sendObjectTeleportSpell(player, false, new WorldTile(2911, 4833, 0));
			break;
		case 5:// fally mine
			Magic.sendObjectTeleportSpell(player, false, new WorldTile(3061, 3373, 0));
			break;
		case 6:// lrc
			Magic.sendObjectTeleportSpell(player, false, new WorldTile(3655, 5113, 0));
			break;

		default:
			player.getPackets()
					.sendGameMessage("This teleport appears to have no destination. Report this to a developer.");
			break;
		}
		end();
		player.getDialogueManager().finishDialogue();
	}
}