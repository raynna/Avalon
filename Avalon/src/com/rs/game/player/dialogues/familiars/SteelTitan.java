package com.rs.game.player.dialogues.familiars;

import com.rs.Settings;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class SteelTitan extends Dialogue {

	/**
	 * @Author Phillip
	 */

	public static final int NPC = 7343;// FAM ID
	private static final int NPCCONFIG = 9827;// Render Anim
	private static final int PLAYERCONFIG = 9827;// Render Anim

	private int stage;

	@Override
	public void start() {
		if (player.getEquipment().hasWeapon() && Utils.random(1) == 0) {

			sendNPCDialogue(NPC, NPCCONFIG,
					"Let us go forth to battle, my " + (player.getAppearence().isMale() ? "Lord" : "Lady") + "!");
			stage = -2;

		} else {

			switch (Utils.random(4)) {
			case 0:
				sendNPCDialogue(NPC, NPCCONFIG, "Forward, master, to a battle that will waken the gods!");
				stage = 5;
				break;
			case 1:
				sendNPCDialogue(NPC, NPCCONFIG, "How do you wish to meet your end, master?");
				stage = 8;
				break;
			case 2:
				sendNPCDialogue(NPC, NPCCONFIG, "Why must we dawdle when glory awaits?");
				stage = 12;
				break;
			case 3:
				sendNPCDialogue(NPC, NPCCONFIG, "Master, we should be marching into glorious battle!");
				stage = 16;
				break;
			}

		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {

		case -2:
			sendPlayerDialogue(PLAYERCONFIG, "Why do you like fighting so much? It's not very nice to kill things.");
			break;
		case -1:
			sendNPCDialogue(NPC, NPCCONFIG, "It is the most honourable thing in life.");
			break;
		case 0:
			sendPlayerDialogue(PLAYERCONFIG, "But I summoned you, I'm not sure I can even say that you're alive...");
			break;
		case 1:
			sendNPCDialogue(NPC, NPCCONFIG, "Alas, you have discovered the woe of all summoned creatures' existence.");
			break;
		case 2:
			sendPlayerDialogue(PLAYERCONFIG, "Really? I was right?");
			break;
		case 3:
			sendNPCDialogue(NPC, NPCCONFIG, "Oh, woe...woe!");
			break;
		case 4:
			end();
			break;

		case 5:
			sendPlayerDialogue(PLAYERCONFIG, "I'd rather not, if it's all the same to you.");
			break;
		case 6:
			sendNPCDialogue(NPC, NPCCONFIG, "I shall never meet my end at this rate...");
			break;
		case 7:
			end();
			break;

		case 8:
			sendPlayerDialogue(PLAYERCONFIG, "Hopefully not for a very long time.");
			break;
		case 9:
			sendNPCDialogue(NPC, NPCCONFIG,
					"You do not wish to be torn asunder by the thousand limbs of a horde of demons?");
			break;
		case 10:
			sendPlayerDialogue(PLAYERCONFIG, "No! I'm quite happy picking flax and turning unstrung bows into gold...");
			break;
		case 11:
			end();
			break;

		case 12:
			sendPlayerDialogue(PLAYERCONFIG, "I'm beginning to think you just want me to die horribly...");
			break;
		case 13:
			sendNPCDialogue(NPC, NPCCONFIG, "We could have deaths that bards sing of for a thousand years.");
			break;
		case 14:
			sendPlayerDialogue(PLAYERCONFIG, "That's not much compensation.");
			break;
		case 15:
			end();
			break;

		case 16:
			sendPlayerDialogue(PLAYERCONFIG, "You know, I think you're onto something.");
			break;
		case 17:
			sendNPCDialogue(NPC, NPCCONFIG, "We could find a death befitting such heroes of " + Settings.FORMAL_SERVER_NAME + "!");
			break;
		case 18:
			sendPlayerDialogue(PLAYERCONFIG, "Ah. You know, I'd prefer not to die...");
			break;
		case 19:
			sendNPCDialogue(NPC, NPCCONFIG,
					"Beneath the claws of a mighty foe shall I be sent into the embrace of death!");
			break;
		case 20:
			end();
			break;
		}
		stage++;
	}

	@Override
	public void finish() {

	}
}
