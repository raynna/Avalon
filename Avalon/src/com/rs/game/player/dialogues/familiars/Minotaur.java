package com.rs.game.player.dialogues.familiars;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class Minotaur extends Dialogue {

	/**
	 * @Author Phillip
	 */

	public static final int NPC = 6829;// FAM ID
	private static final int NPCCONFIG = 9827;// Render Anim
	private static final int PLAYERCONFIG = 9827;// Render Anim

	private int stage;

	@Override
	public void start() {
		if (player.isDeveloper() && Utils.random(1) == 0) {// if player has a
																// hat with
																// horns on it
																// [Guthans
																// Helm, Archer,
																// Berserker]

			sendNPCDialogue(NPC, NPCCONFIG, "Are you using that iron bar, boss?");
			stage = -2;

		} else {

			switch (Utils.random(4)) {
			case 0:
				sendPlayerDialogue(PLAYERCONFIG, "Titan?");
				stage = 5;
				break;
			case 1:
				sendNPCDialogue(NPC, NPCCONFIG, "Boss!");
				stage = 13;
				break;
			case 2:
				sendNPCDialogue(NPC, NPCCONFIG, "Boss?");
				stage = 21;
				break;
			case 3:
				sendPlayerDialogue(PLAYERCONFIG, "How are you today, titan?");
				stage = 28;
				break;
			}

		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {

		case -3:
			sendPlayerDialogue(PLAYERCONFIG, "Well, not right now, why?");
			break;
		case -2:
			sendNPCDialogue(NPC, NPCCONFIG, "Can I have it, then?");
			break;
		case -1:
			sendPlayerDialogue(PLAYERCONFIG, "What for?");
			break;
		case 0:
			sendNPCDialogue(NPC, NPCCONFIG, "I've got a cunning plan.");
			break;
		case 1:
			sendPlayerDialogue(PLAYERCONFIG, "Involving my iron bar?");
			break;
		case 2:
			sendNPCDialogue(NPC, NPCCONFIG, "No, but if I sell your iron bar I'll have enough money to buy a new hat.");
			break;
		case 3:
			sendNPCDialogue(NPC, NPCCONFIG,
					"You can't go through with a cunning plan without the right headgear, boss!");
			break;
		case 4:
			end();
			break;

		case 5:
			sendNPCDialogue(NPC, NPCCONFIG, "Yes, boss?");
			break;
		case 6:
			sendPlayerDialogue(PLAYERCONFIG, "What's that in your hand?");
			break;
		case 7:
			sendNPCDialogue(NPC, NPCCONFIG, "I'm glad you asked that, boss.");
			break;
		case 8:
			sendNPCDialogue(NPC, NPCCONFIG,
					"This is the prototype for the Iron Titan (tm) action figure. You just pull this string here and he fights crime with real action sounds.");
			break;
		case 9:
			sendPlayerDialogue(PLAYERCONFIG, "Titan?");
			break;
		case 10:
			sendNPCDialogue(NPC, NPCCONFIG, "Yes, boss?");
			break;
		case 11:
			sendPlayerDialogue(PLAYERCONFIG, "Never mind.");
			break;
		case 12:
			end();
			break;

		case 13:
			sendPlayerDialogue(PLAYERCONFIG, "What?");
			break;
		case 14:
			sendNPCDialogue(NPC, NPCCONFIG, "I've just had a vision of the future.");
			break;
		case 15:
			sendPlayerDialogue(PLAYERCONFIG, "I didn't know you were a fortune teller. Let's hear it then..");
			break;
		case 16:
			sendNPCDialogue(NPC, NPCCONFIG, "Just imagine, boss, an Iron Titan (tm) on every desk.");
			break;
		case 17:
			sendPlayerDialogue(PLAYERCONFIG, "That doesn't even make sense.");
			break;
		case 18:
			sendNPCDialogue(NPC, NPCCONFIG,
					"Hmm. It was a bit blurry, perhaps the future is having technical issues at the moment.");
			break;
		case 19:
			sendPlayerDialogue(PLAYERCONFIG, "Riiight.");
			break;
		case 20:
			end();
			break;

		case 21:
			sendPlayerDialogue(PLAYERCONFIG, "Yes, titan?");
			break;
		case 22:
			sendNPCDialogue(NPC, NPCCONFIG, "You know how you're the boss and I'm the titan?");
			break;
		case 23:
			sendPlayerDialogue(PLAYERCONFIG, "Yes?");
			break;
		case 24:
			sendNPCDialogue(NPC, NPCCONFIG, "Do you think we could swap for a bit?");
			break;
		case 25:
			sendPlayerDialogue(PLAYERCONFIG, "Oh hell no! Bitch did I just hear you say that?");
			break;
		case 26:
			sendNPCDialogue(NPC, NPCCONFIG, "No sir, sorry sir.");
			break;
		case 27:
			end();
			break;

		case 28:
			sendNPCDialogue(NPC, NPCCONFIG, "I'm very happy.");
			break;
		case 29:
			sendPlayerDialogue(PLAYERCONFIG, "That's marvellous, why are you so happy?");
			break;
		case 30:
			sendNPCDialogue(NPC, NPCCONFIG, "Because I love the great taste of Iron Titan (tm) cereal!");
			break;
		case 31:
			sendPlayerDialogue(PLAYERCONFIG, "?");
			break;
		case 32:
			sendPlayerDialogue(PLAYERCONFIG, "You're supposed to be working for me, not promoting yourself.");
			break;
		case 33:
			sendNPCDialogue(NPC, NPCCONFIG, "Sorry, boss.");
			break;
		case 34:
			end();
			break;
		}
		stage++;
	}

	@Override
	public void finish() {

	}
}
