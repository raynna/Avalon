package com.rs.game.player.dialogues.skilling;

import com.rs.game.player.actions.skills.construction.Sawmill;
import com.rs.game.player.content.customshops.CustomStoreData;
import com.rs.game.player.dialogues.Dialogue;

public class SawmillOperator extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (int) parameters[0];
		sendNPCDialogue(npcId, NORMAL, "Hello. Do you want me to make some planks for you or would you be interested in some other house supplies?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue("Select an option", "Planks, please.", "What kind of planks can you make?", "Can I buy some housing supplies?", "Nothing, thanks.");
			break;
		case 0:
			if (componentId == OPTION_1) {
				stage = 2;
				sendPlayerDialogue(NORMAL, "Planks, please.");
			} else if (componentId == OPTION_2) {
				stage = 4;
				sendPlayerDialogue(NORMAL, "What kind of planks can you make?");
			} else if (componentId == OPTION_3) {
				player.getCustomStore().sendInterface(player, 0, CustomStoreData.CONSTRUCTION);
				end();
			} else {
				stage = 1;
				sendPlayerDialogue(NORMAL, "Nothing thanks.");
			}
			break;
		case 1:
			stage = -2;
			sendNPCDialogue(npcId, NORMAL, "Well, come back when you want some. You can't get good quality planks anywhere but here!");
			break;
		case 2:
			stage = 3;
			sendNPCDialogue(npcId, NORMAL, "What kind of planks do you want?");
			break;
		case 3:
			Sawmill.openPlanksConverter(player);
			end();
			break;
		case 4:
			stage = 5;
			sendNPCDialogue(npcId, NORMAL, "I can make planks from wood, oak, teak and mahogany. I don't make planks from other woods as they're no good for making furnite.");
			break;
		case 5:
			stage = -2;
			sendNPCDialogue(npcId, NORMAL, "Wood and oak are all over the place, but teak and mahogany can only be found in a few places like Karamja and Etceteria.");
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
