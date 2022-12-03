package com.rs.game.player.dialogues.player;

import com.rs.game.player.dialogues.Dialogue;

public class SkillTeleports extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Skilling Teleport Options", "Agility", "Cooking", "Crafting", "Fishing", "Next.");
	}

	@Override
	public void run(int interfaceId, int componentId) {

		if (stage == -1) {
			switch (componentId) {
			case OPTION_1:
				action("option-agility");
				break;
			case OPTION_2:
				action("option-cooking");
				break;
			case OPTION_3:
				action("option-crafting");
				break;
			case OPTION_4:
				action("option-fishing");
				break;
			case OPTION_5:
				componentId = -1;
				sendOptionsDialogue("Skilling Teleport Options", "Farming", "Hunter", "Mining", "RuneCrafting",
						"Next.");
				stage = 1;
				break;
			}
		}

		switch (stage) {
		case 1:
			switch (componentId) {
			case OPTION_1:
				action("option-farming");
				break;
			case OPTION_2:
				action("option-hunter");
				break;
			case OPTION_3:
				action("option-mining");
				break;
			case OPTION_4:
				action("option-runecrafting");
				break;
			case OPTION_5:
				componentId = -1;
				stage = 2;
				sendOptionsDialogue("Skilling Teleport Options", "Summoning", "Thieving", "Woodcutting",
						"Back to Start", "Back to Home.");
				break;
			}
			break;

		case 2:
			switch (componentId) {
			case OPTION_1:
				action("option-summoning");
				break;
			case OPTION_2:
				action("option-thieving");
				break;
			case OPTION_3:
				action("option-woodcutting");
				break;
			case OPTION_4:
				componentId = -1;
				sendOptionsDialogue("Skilling Teleport Options", "Agility", "Cooking", "Crafting", "Fishing", "Next.");
				stage = -1;
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

	void action(String action) {
		switch (action.toLowerCase().replace("option-", "")) {
		case "agility":
			// break;
		case "cooking":
			// break;
		case "crafting":
			// break;
		case "fishing":
			// break;
		case "farming":
			// break;
		case "hunter":
			// break;
		case "mining":
			// break;
		case "runecrafting":
			// break;
		case "summoning":
			// break;
		case "thieving":
			// break;
		case "woodcutting":
			player.sm("Skilling Teleports not avalible at this time, check back later.");
			break;
		default:
			break;
		}
		end();
	}

	// RuneCrafting - ("Tip: You can talk to the Mage of Zamorak located level 5
	// wilderness to teleport you to the Abyss")
	// Agility - Teleports { }
	// Thieving - Teleports { }
	// Crafting - ("Tip: You can make skilling urns with the Pottery Wheel near
	// the Varrock East Bank by Aubury.")
	// Mining - Teleports { }
	// Fishing - Teleports { }
	// Cooking ("Tip: A good place to cook food is Catherby.")
	// WoodCutting - Teleports { }
	// Farming ("Farming is not yet introduced into the game, coming soon.")
	// Summoning - Teleport to Neitzinot
	// Hunter ("Hunter is not yet introduced into the game, coming soon.")

}
