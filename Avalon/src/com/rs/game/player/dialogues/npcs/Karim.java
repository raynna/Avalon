package com.rs.game.player.dialogues.npcs;

import com.rs.game.player.dialogues.Dialogue;

public class Karim extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Would you like to buy a nice kebab? Only one gold.");
	}

	@Override
	public void run(int interfaceId, int option) {
		switch (stage) {
		case -1:
			sendOptionsDialogue("Select an Option", "I think I'll give it a miss.", "Yes please.");
			stage = 0;
		case 0:
			switch (option) {
			case OPTION_1:
				stage = 3;
				sendPlayerDialogue(9838, "I think I'll give it a miss.");
				break;
			case OPTION_2:
				stage = 1;
				sendPlayerDialogue(9838, "Yes please.");
				break;
			}
			break;
		case 1:
			if (player.getTotalCoins() >= 1) {
				stage = 2;
				sendPlayerDialogue(9838, "Oops, I forgot to bring any money with me.");
				break;
			}
			stage = 3;
			player.closeInterfaces();
			if (player.canBuy(1)) {
				player.getInventory().addItem(1971, 1);
				player.getPackets().sendGameMessage("You buy a kebab.");
			}
			break;
		case 2:
			stage = 3;
			sendNPCDialogue(npcId, 9827, "Come back when you have some.");
			break;
		case 3:
			end();
			break;
		default:
			end();
			break;
		}
	}

	@Override
	public void finish() {

	}
}