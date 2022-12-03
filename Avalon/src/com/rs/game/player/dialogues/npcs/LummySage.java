package com.rs.game.player.dialogues.npcs;

import java.util.Locale;

import com.rs.game.player.dialogues.Dialogue;

public class LummySage extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Greetings, " + player.getUsername().toUpperCase(Locale.getDefault()) + ".",
				"Can I help you at all?");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Who are you?", "What is this place?", "Why are you here?");
			break;
		case 0:
			switch (componentId) {
			case 2:
				stage = 1;
				sendNPCDialogue(npcId, 9827, "I am the Lumbridge Sage, you may consider",
						"me as your first guide in this world.");
				break;
			case 3:
				stage = 1;
				sendNPCDialogue(npcId, 9827, "This is where your adventure starts,",
						"the mighty city of Lumbridge my friend!");
				break;
			case 4:
				stage = 1;
				sendNPCDialogue(npcId, 9827, "I make sure the newcomers are introduced",
						"in this world in a correct and fashionable way.");
				break;
			}
		case 1:
			stage = -1;
			sendNPCDialogue(npcId, 9827, "Is there anything else you would like to ask me?");
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
