package com.rs.game.player.dialogues;

import com.rs.game.player.controlers.construction.SawmillController;

public class Overseer extends Dialogue {

	private int npcId;
	private SawmillController sawmill;

	@Override
	public void start() {
		npcId = (int) parameters[0];
		sawmill = (SawmillController) parameters[1];
		if (sawmill != null) {
			sendOptionsDialogue(
					"ARE YOU READY TO COMPLETE THE ORDER?<br>(ALL PLANKS REMAINING IN THE WAGON WILL BE SUBMITTED)",
					"Yes.", "No.");
			stage = 0;
		} else
			sendNPCDialogue(npcId, MAD, "Yes? What do you want? I'm very busy!");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 1;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "What is this place?",
					"Who are you?", "What should I be doing?",
					"Nothing, thanks.");
			break;
		case 0:
			if (componentId == OPTION_1) {
				if (!sawmill.isOrderCompleted()) {
					sendNPCDialogue(
							npcId,
							CONFUSED,
							"I don't think you've got all the planks you need for this order. Come back when you're done.");
				} else {
					sendNPCDialogue(npcId, NORMAL,
							"Good job! I'll fill in the paperwork and send them on their way.");
					sawmill.finishJob();
				}
				stage = -2;
			} else
				end();
			break;
		case 1:
			if (componentId == OPTION_1) {
				stage = 2;
				sendPlayerDialogue(NORMAL, "What is this place?");
			} else if (componentId == OPTION_2) {
				stage = 9;
				sendPlayerDialogue(NORMAL, "Who are you?");
			} else if (componentId == OPTION_3) {
				stage = 12;
				sendPlayerDialogue(NORMAL, "What should I be doing?");
			} else {
				stage = -2;
				sendPlayerDialogue(NORMAL, "Nothing, thanks.");
			}
			break;
		case 2:
			stage = 3;
			sendNPCDialogue(
					npcId,
					NORMAL,
					"This is the sawmill training area, where we take more qualified woodcutters on work experience.");
			break;
		case 3:
			stage = 4;
			sendPlayerDialogue(NORMAL, "Work experience?");
			break;
		case 4:
			stage = 5;
			sendNPCDialogue(npcId, NORMAL,
					"Yeah. You work, you gain experience.");
			break;
		case 5:
			stage = 6;
			sendNPCDialogue(npcId, NORMAL,
					"You DO NOT gain planks, as I have to tell everyone.");
			break;
		case 6:
			stage = 7;
			sendPlayerDialogue(NORMAL, "Not even a few...");
			break;
		case 7:
			stage = 8;
			sendNPCDialogue(npcId, MAD, "NO PLANKS!");
			break;
		case 8:
			stage = -2;
			sendPlayerDialogue(NORMAL, "Okay, okay.");
			break;
		case 9:
			stage = 10;
			sendNPCDialogue(npcId, NORMAL,
					"The name's Jill. I'm the sawmill overseer.");
			break;
		case 10:
			stage = 11;
			sendPlayerDialogue(NORMAL, "Pleased to meet y-");
			break;
		case 11:
			stage = -2;
			sendNPCDialogue(npcId, MAD,
					"I'm in charge of this place, soo no fooling around!");
			break;
		case 12:
			stage = 13;
			sendNPCDialogue(
					npcId,
					NORMAL,
					"Well, first grab a job from the board. There's short and long jobs: pick whichever you fancy.");
			break;
		case 13:
			stage = 14;
			sendNPCDialogue(npcId, NORMAL,
					"Then you stock up on wood from the piles and stack it in the hopper.");
			break;
		case 14:
			stage = 15;
			sendNPCDialogue(
					npcId,
					NORMAL,
					"The machine will do its magic and, before you know it, you'll have a stack of planks at the other end.");
			break;
		case 15:
			stage = 16;
			sendNPCDialogue(npcId, NORMAL,
					"Take these, with a saw, to the workbench to cut the planks you need.");
			break;
		case 16:
			stage = 17;
			sendNPCDialogue(
					npcId,
					NORMAL,
					"All the finished planks go straight into the cart, but you can take out any excess planks and use them for the next batch, if you like.");
			break;
		case 17:
			stage = -2;
			sendNPCDialogue(npcId, NORMAL,
					"Just let me know when you're ready to send an order.");
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
