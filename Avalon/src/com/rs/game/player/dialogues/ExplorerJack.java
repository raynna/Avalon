package com.rs.game.player.dialogues;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 14 Apr 2016
 *
 **/

public class ExplorerJack extends Dialogue {

	int npc = 13295;

	@Override
	public void start() {
		sendNPCDialogue(npc, Happy,
				"Aha! Hello " + player.getDisplayName() + ". Do you have a question about the Task System?");
		stage = 1;

	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case END:
			end();
			break;
		case 1:
			sendOptions(TITLE, "What is the Task System?", "How can I view my progress?",
					"I would like to claim my rewards.");
			stage = 2;
			break;
		case 2:
			switch (componentId) {
			case OPTION_1:
				sendNPCDialogue(npc, Happy,
						"The Task System is a guide in some sort, the purpose of it is to prove your skills, techniques and such around the world of Rain.");
				stage = 1;
				break;
			case OPTION_2:
				sendNPCDialogue(npc, Plain,
						"To view your progress, have a look in your Task Tab.");
				stage = END;
				break;
			case OPTION_3:
				if (player.getTaskManager().hasCompletedTasks("easy")) {
					player.getTaskManager().claimRewards();
					sendNPCDialogue(npc, Happy, "Here you go! They are well deserved.");
				} else {
					sendNPCDialogue(npc, Unsure, "You have nothing to claim.");
				}
				stage = END;
				break;
			}
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
