package com.rs.game.player.dialogues;

public class Allround extends Dialogue {

	@Override
	public void start() {
		player.lock();
		sendNPCDialogue(945, 9828, "Welcome to Enraged X!", "Now what should I call you?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendPlayerDialogue(9828, "You can call me " + player.getDisplayName() + "!");
			break;
		case 0:
			stage = 1;
			sendNPCDialogue(945, 9828, "Alright", "Hello, " + player.getDisplayName() + ".");
			break;
		case 1:
			stage = 2;
			sendNPCDialogue(945, 9828, "My name is Neggorf and I am here to guide you trough Enraged-X!");
			break;
		case 2:
			stage = 3;
			sendNPCDialogue(945, 9828,
					"First of all, you can set your levels by going to the skill tab then clicking on the skill you want to set. Remember, you can only set combat stats. ");
			break;
		case 3:
			stage = 5;
			sendNPCDialogue(945, 9828,
					"When you start off you get 2.5M coins added into your Money Vault which can be accessed by going to the bank.");
			break;
		case 5:
			stage = 6;
			sendNPCDialogue(945, 9828, "I advise you to set a bank pin too, in case you get hacked!");
			break;
		case 6:
			stage = 7;
			sendPlayerDialogue(9828, "Okay that seems nice, what else is there I should know?");
			break;
		case 7:
			stage = 8;
			sendNPCDialogue(945, 9828, "Hmmm nothing that I can think of right now...");
			break;
		case 8:
			stage = 9;
			sendNPCDialogue(945, 9828,
					"Oh yeah, if you want to pk people with only welfare gear you can use the portal near home!");
			break;
		case 9:
			stage = 10;
			sendNPCDialogue(945, 9828,
					"If you click on the quest tab you can also see how many players there currently are in the wilderness, how many voting points you got and how many players there are online.");
			break;
		case 10:
			stage = 11;
			sendPlayerDialogue(9828, "Oh seems nice, now I will have to continue my adventure");
			break;
		case 11:
			stage = 12;
			sendNPCDialogue(945, 9828, "I understand that, goodbye!");
			player.getPackets().sendGameMessage("NOTICE: Starter money went directly to your Money pouch!");
			break;
		case 12:
			player.unlock();
			player.getDialogueManager().startDialogue("StarterSkills");
			break;
		default:
			end();
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
