package com.rs.game.player.dialogues.npcs;

import com.rs.game.player.dialogues.Dialogue;

public class BobLumbridge extends Dialogue {
	private int npcId;

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Select an option", "Give me a quest!", "Do you anything to sell?",
				"Can you repair my items for me?");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				sendPlayerDialogue(9827, "Give me a quest!");
				stage = 5;
			}
			if (stage == 5) {
				sendNPCDialogue(npcId, 9827, "Get yer own!");
				stage = 99;
			} else if (componentId == OPTION_2) {
				sendPlayerDialogue(9827, "Do you have anything to sell?");
				stage = 10;
			}
			if (stage == 10) {
				// TODO: openShop();
			} else if (componentId == OPTION_3) {
				sendPlayerDialogue(9827, "Can you repair my items for me?");
				stage = 15;
			}
			if (stage == 15) {
				// TODO: repair items.
			}

		} else if (stage == 99) {
			end();
		}
	}

	@Override
	public void finish() {

	}

}
