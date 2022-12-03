package com.rs.game.player.dialogues.familiars;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class UnicornStallion extends Dialogue {

	/**
	 * @Author Phillip
	 */

	public static final int NPC = 6829;// FAM ID
	private static final int NPCCONFIG = 9827;// Render Anim
	private static final int PLAYERCONFIG = 9827;// Render Anim

	private int stage;

	@Override
	public void start() {
		if (player.getHitpoints() < player.getMaxHitpoints()) {

			sendNPCDialogue(NPC, NPCCONFIG,
					"Whicker snort! Whinny whinny whinny. (You're hurt! Let me try to heal you.)");
			stage = -1;

		} else {

			switch (Utils.random(4)) {
			case 0:
				sendNPCDialogue(NPC, NPCCONFIG,
						"Neigh neigh neighneigh snort? (Isn't everything so awesomely wonderful?)");
				stage = 5;
				break;
			case 1:
				sendNPCDialogue(NPC, NPCCONFIG,
						"Whicker whicker. Neigh, neigh, whinny. (I feel so, like, enlightened. Let's meditate and enhance our auras.)");
				stage = 8;
				break;
			case 2:
				sendNPCDialogue(NPC, NPCCONFIG, "Whinny whinny whinny. (I think I'm astrally projecting.)");
				stage = 12;
				break;
			case 3:
				sendNPCDialogue(NPC, NPCCONFIG, "Whinny, neigh! (Oh, happy day!)");
				stage = 16;
				break;
			}

		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {

		case -1:
			sendPlayerDialogue(PLAYERCONFIG, "Yes, please do!");
			break;
		case 0:
			sendNPCDialogue(NPC, NPCCONFIG,
					"Snuffle whicker whicker neigh neigh... (Okay, we'll begin with acupuncture and some reiki, then I'll get my crystals...)");
			break;
		case 1:
			sendPlayerDialogue(PLAYERCONFIG, "Or you could use some sort of magic... like the other unicorns...");
			break;
		case 2:
			sendNPCDialogue(NPC, NPCCONFIG,
					"Whicker whinny whinny neigh. (Yes, but I believe in alternative medicine.)");
			break;
		case 3:
			sendPlayerDialogue(PLAYERCONFIG, "Don't worry about it, then; I'll be fine.");
			break;
		case 4:
			end();
			break;

		case 5:
			sendPlayerDialogue(PLAYERCONFIG, "Err...yes?");
			break;
		case 6:
			sendNPCDialogue(NPC, NPCCONFIG,
					"Whicker whicker snuffle. (I can see you're not tuning in, " + player.getDisplayName() + ".)");
			break;
		case 7:
			sendPlayerDialogue(PLAYERCONFIG, "No, no, I'm completely at one with...you know...everything.");
			break;
		case 8:
			sendNPCDialogue(NPC, NPCCONFIG, "Whicker! (Cosmic.)");
			break;
		case 9:
			end();
			break;

		case 10:
			sendPlayerDialogue(PLAYERCONFIG, "I can't do that! I barely even know you.");
			break;
		case 11:
			sendNPCDialogue(NPC, NPCCONFIG, "Whicker... (Bipeds...)");
			break;
		case 12:
			end();
			break;

		case 13:
			sendPlayerDialogue(PLAYERCONFIG,
					"Okay... Hang on. Seeing as I summoned you here, wouldn't that mean you are physically projecting instead?");
			break;
		case 14:
			sendNPCDialogue(NPC, NPCCONFIG, "Whicker whicker whicker. (You're, like, no fun at all, man.)");
			break;
		case 15:
			end();
			break;

		case 16:
			sendPlayerDialogue(PLAYERCONFIG, "Happy day? Is that some sort of holiday or something?");
			break;
		case 17:
			sendNPCDialogue(NPC, NPCCONFIG,
					"Snuggle whicker (Man, you're totally, like, uncosmic, " + player.getDisplayName() + ".)");
			break;
		case 18:
			end();
			break;
		}
		stage++;
	}

	@Override
	public void finish() {

	}
}
