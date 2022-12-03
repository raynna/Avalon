package com.rs.game.player.dialogues.npcs;

import com.rs.game.player.dialogues.Dialogue;

public class Kolodion extends Dialogue {

	private int npcId = 905;

	@Override
	public void start() {
		sendNPCDialogue(npcId, Happy, "Hello friend what brings you here today?");
		stage = 1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {

		case 1:
			sendPlayerDialogue(Happy, "I want to go in that spring!");
			break;
		case 2:
			sendNPCDialogue(npcId, Plain, "Oh great another one... Be gone mortal!");
			break;
		case 3:
			sendPlayerDialogue(Thinking, "What do you mean another one? That is not very polite.");
			break;
		case 4:
			sendNPCDialogue(npcId, Snobby,
					"Ugg... To use my sacred pool one must prove themselves worthy in a duel of magical skill.");
			break;
		case 5:
			sendOptionsDialogue("What do you want to do?", "He doesn't look so tough.",
					"He looks kinda tough right now...");
			break;
		case 6:
			switch (componentId) {
			case OPTION_1:
				sendPlayerDialogue(Angry, "Yeah, yeah, let's fight!");
				break;
			case OPTION_2:
				sendPlayerDialogue(Unsure,
						"Well... I have some other things I should probably get back to doing. Skilling and stuff...");
				end();
				break;
			}
			break;
		case 7:
			startMinigame();
			break;
		}
		stage++;

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

	public void startMinigame() {
		// minigame controler
		player.getDialogueManager().finishDialogue();
	}

}
