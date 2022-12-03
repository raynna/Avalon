package com.rs.game.player.dialogues.familiars;

import com.rs.Settings;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class GeyserTitan extends Dialogue {

	/**
	 * @Author Phillip
	 */

	public static final int NPC = 7339;// FAM ID
	private static final int NPCCONFIG = 9827;// Render Anim
	private static final int PLAYERCONFIG = 9827;// Render Anim

	private int stage;

	@Override
	public void start() {
		if (player.isDeveloper() && Utils.random(1) == 0) {// if a player has
																// more than 5
																// sharks in
																// their
																// inventory
			switch (Utils.random(4)) {
			case 0:
				sendNPCDialogue(NPC, NPCCONFIG,
						"Over the course of their lifetime a shark may grow and use 20,000 teeth.");
				stage = -3;
				break;
			case 1:
				sendNPCDialogue(NPC, NPCCONFIG,
						"Unlike most animals, both the shark's upper and lower jaws move when they bite.");
				stage = -1;
				break;
			case 2:
				sendNPCDialogue(NPC, NPCCONFIG,
						"Did you know that sharks have the most powerful jaws of any animal on the planet?");
				stage = 2;
				break;
			case 3:
			case 4:
				sendNPCDialogue(NPC, NPCCONFIG, "Did you know that sharks normally eat alone?");
				stage = 5;
				break;
			}
		} else {

			switch (Utils.random(4)) {
			case 0:
				sendNPCDialogue(NPC, NPCCONFIG, "Did you know a snail can sleep up to three years?");
				stage = 10;
				break;
			case 1:
				sendNPCDialogue(NPC, NPCCONFIG,
						"Did you know that in one feeding a mosquito can absorb one-and-a-half times its own body weight in blood?");
				stage = 12;
				break;
			case 2:
				sendNPCDialogue(NPC, NPCCONFIG,
						"Did you know that " + Settings.SERVER_NAME + " gets 100 tons heavier everyday, due to dust falling from space?");
				stage = 14;
				break;
			case 3:
				sendNPCDialogue(NPC, NPCCONFIG, "Hey mate, how are you?");
				stage = 16;
				break;
			}

		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {

		// Sharks in inventory dialogues

		case -3:
			sendPlayerDialogue(PLAYERCONFIG,
					"Wow! That is a whole load of teeth. I wonder what the Tooth Fairy would give for those?");
			break;
		case -2:
			end();
			break;

		case -1:
			sendPlayerDialogue(PLAYERCONFIG, "Really?");
			break;
		case 0:
			sendNPCDialogue(NPC, NPCCONFIG, "Yup. Chomp chomp.");
			break;
		case 1:
			end();
			break;

		case 2:
			sendPlayerDialogue(PLAYERCONFIG, "No, I didn't.");
			break;
		case 3:
			sendNPCDialogue(NPC, NPCCONFIG, "Full of facts, me.");
			break;
		case 4:
			end();
			break;

		case 5:
			sendPlayerDialogue(PLAYERCONFIG, "I see.");
			break;
		case 6:
			sendNPCDialogue(NPC, NPCCONFIG,
					"Sometimes one feeding shark attracts others and they all try and get a piece of the prey.");
			break;
		case 7:
			sendNPCDialogue(NPC, NPCCONFIG, "They take a bite at anything in their way and may even bite each other!");
			break;
		case 8:
			sendPlayerDialogue(PLAYERCONFIG, "Ouch!");
			break;
		case 9:
			end();
			break;

		// Start of Normal Dialogues

		case 10:
			sendPlayerDialogue(PLAYERCONFIG, "I wish I could do that. Ah...sleep.");
			break;
		case 11:
			end();
			break;

		case 12:
			sendPlayerDialogue(PLAYERCONFIG, "Eugh.");
			break;
		case 13:
			end();
			break;

		case 14:
			sendPlayerDialogue(PLAYERCONFIG, "What a fascinating fact.");
			break;
		case 15:
			end();
			break;

		case 16:
			sendPlayerDialogue(PLAYERCONFIG, "Not so bad.");
			break;
		case 17:
			sendNPCDialogue(NPC, NPCCONFIG,
					"Did you know that during the average human life-span the heart will beat approximately 2.5 billion times?");
			break;
		case 18:
			sendPlayerDialogue(PLAYERCONFIG, "Wow, that is a lot of non-stop work!");
			break;
		case 19:
			end();
			break;
		}
		stage++;
	}

	@Override
	public void finish() {

	}
}
