package com.rs.game.player.dialogues;

import com.rs.Settings;
import com.rs.game.player.controlers.WelcomeTutorial;

public class NewPlayer extends Dialogue {

	// - Written by Phillip :: Unfinished

	// Bugs- Still needs to check the makeover mage part and the other sht and
	// needs to update so idk...

	int params;
	WelcomeTutorial controler;

	@Override
	public void start() {
		params = (Integer) parameters[0];
		controler = (WelcomeTutorial) parameters[1];

		if (controler == null) {
			sendDialogue("Please use the '" + Settings.HELP_CC_NAME + "' friends chat if you need help.");
			stage = 14;// finish
		} else {
			switch (params) {
			case 0:
				sendDialogue("Welcome to " + Settings.FORMAL_SERVER_NAME
						+ ", before we begin please choose some options for your account set up.");
				stage = -3;
				break;
			case 1:
				sendDialogue("Now that your appearance is set up let's quickly discuss game funadmentals.");
				stage = 4;
				break;
			case 2:
				sendDialogue("You are making great progress so far!");
				stage = 9;
				break;
			}
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {

		if (stage == -3) {
			stage = -2;
			sendOptionsDialogue("Tutorial Type", "Classic Tutorial (Starter Package)", "Skip Tutorial (Mini Package)");
		}

		if (stage == -2) {
			switch (componentId) {
			case OPTION_1:
				stage = 0;
				sendDialogue(
						"This is a server where you must earn your items and skills. Join the " + Settings.HELP_CC_NAME
								+ " friends chat channel if you require any help or get stuck on something. "
								+ "Our staff and players are all friendly and willing to help!");
				break;
			case OPTION_2:
				stage = 100;
				sendDialogue("You will now recieve your starter kit. Good luck.");
				break;
			}
		}

		if (stage == 14) {
			end();
		}

		switch (stage) {
		case 100:
			controler.quickTutorialOption();
			end();
			break;
		case 0:
			stage = 1;
			sendDialogue(
					"To start off, chances are you will want some basic items. We will provide you with a simple starter kit."
							+ " If you need anything however you can almost always find it on the Grand Exchange located inside the bank."
							+ " Some items will be unlimited such as foods, potions, and armours common to pking.");
			break;
		case 1:
			stage = 2;
			sendDialogue(
					"More rare items will be obtainable in a few other ways, items such as Armadyl Godswords, Dragon Claws, Divine Spirit Shields,"
							+ " etc. will be items you must kill the corisponding bosses for or purchase from a player who has done so either via direct trade"
							+ " or Grand Exchange. Another way would be to go to the fully functional duel arena. The fastest way to get there is with a "
							+ "ring of duelling.");
			break;
		case 2:
			stage = 3;
			sendDialogue("Now go and talk to the Makeover Mage to learn about how to change your appearance.");
			break;
		case 3:
			controler.setStage(1);
			// controler.updateProgress();
			end();
			break;
		case 4:
			stage = 5;
			sendDialogue(
					"To teleport you can talk to the Wise Old Man located near the general store, you should be able to see him.");
			break;
		case 5:
			stage = 6;
			sendDialogue(
					"To train your Slayer skill please talk to Kuradal located South of the Edgeville bank near the coffins.");
			break;
		case 6:
			stage = 7;
			sendDialogue(
					"Training your combat skills should be easy enough, you can set your combat xp rate by talking to "
							+ "the Experience Manager in the Edgeville bank.");
			break;
		case 7:
			stage = 8;
			sendDialogue("Why don't you try talking to the Experience Manager now and setting your xp rate to x500?");
			break;
		case 8:
			controler.updateProgress();
			end();
			break;
		case 9:
			stage = 10;
			sendDialogue("To reset a level talk to Mandrith. Mandrith will charge 1,000,000 coins to reset"
					+ " a combat level and can reset your Attack, Strength, Defence, Ranged, Magic, or Prayer levels.");
			break;
		case 10:
			stage = 11;
			sendDialogue(
					"To earn dungeoneering rewards from the rewards trader you must obtain Avalon Tokens by killing NPCs or Skilling in the"
							+ " wilderness. Another way to earn these tokens is by purchasing them from the store or killing high level boss npcs.");
			break;
		case 11:
			stage = 12;
			sendDialogue("Well, that is all you need to learn about " + Settings.FORMAL_SERVER_NAME
					+ " for now. Remember you can always ask for help"
					+ " if you get stuck on something. Now you will recieve a starter kit and be sent on your way.");
			break;
		case 12:
			stage = 13;
			sendDialogue("Good luck.");
			break;
		case 13:
			controler.updateProgress();
			end();
			break;
		}
	}

	@Override
	public void finish() {

	}

}
