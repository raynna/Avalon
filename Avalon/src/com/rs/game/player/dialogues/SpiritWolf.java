package com.rs.game.player.dialogues;

import com.rs.utils.Utils;

public class SpiritWolf extends Dialogue {

	/**
	 * @Author Phillip
	 */

	public static final int NPC = 6829;
	private static final int NPCCONFIG = 9827;// Render Anim
	private static final int PLAYERCONFIG = 9827;// Render Anim

	private int stage;

	@Override
	public void start() {
		if (player.isDeveloper() && Utils.random(2) == 0) {

			sendNPCDialogue(NPC, NPCCONFIG, "Throw the bone! I want to chase it!");
			stage = -2;

		} else {

			switch (Utils.random(4)) {
			case 0:
				sendNPCDialogue(NPC, NPCCONFIG, "What are you doing?");
				stage = 0;
				break;

			case 1:
			case 2:
				sendNPCDialogue(NPC, NPCCONFIG, "Danger!");
				stage = 2;
				break;

			case 3:
				sendNPCDialogue(NPC, NPCCONFIG, "I smell something good! Hunting time!");
				stage = 5;
				break;

			case 4:
				sendNPCDialogue(NPC, NPCCONFIG, "When am I going to get to chase something?");
				stage = 7;
				break;
			}

		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -2:
			sendPlayerDialogue(PLAYERCONFIG, "I can't just throw bones away - I need them to train my Prayer!");
			break;
		case -1:
			end();
			break;

		case 0:
			sendPlayerDialogue(PLAYERCONFIG, "Oh, just some...biped things. I'm sure it would bore you.");
			break;
		case 1:
			end();
			break;

		case 2:
			sendPlayerDialogue(PLAYERCONFIG, "Where?!");
			break;
		case 3:
			sendNPCDialogue(NPC, NPCCONFIG, "False alarm...");
			break;
		case 4:
			end();
			break;

		case 5:
			sendPlayerDialogue(PLAYERCONFIG,
					"We can go hunting in a moment. I just have to take care of something first.");
			break;
		case 6:
			end();
			break;

		case 7:
			sendPlayerDialogue(PLAYERCONFIG, "Oh I'm sure we'll find something for you in a bit.");
			break;
		case 8:
			end();
			break;
		}
		stage++;
	}

	@Override
	public void finish() {

	}
}
