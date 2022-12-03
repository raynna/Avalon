package com.rs.game.player.dialogues.npcs;

import com.rs.game.WorldObject;
import com.rs.game.objects.impl.DoorsAndGates;
import com.rs.game.player.dialogues.Dialogue;

public class BorderGuard extends Dialogue {

	int npcId;
	WorldObject object;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		object = (WorldObject) parameters[1];
		sendPlayerDialogue(9827, "Can i come trough this gate?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendNPCDialogue(925, 9827, "You must pay a toll of 10 gold coins to pass.");
		} else if (stage == 0) {
			if (player.getTotalCoins() >= 10) {
				stage = 1;
				sendOptionsDialogue("Choose an option", "Okay, I'll pay.", "Who does my money go to?",
						"No thanks, I'll walk around.");
			} else {
				stage = 100;
				sendPlayerDialogue(9827, "I haven't got that much.");
				return;
			}
		} else if (stage == 1) {
			if (componentId == OPTION_1) {
				stage = 25;
				sendPlayerDialogue(9827, "Okay, I'll pay.");
			} else if (componentId == OPTION_2) {
				stage = 50;
				sendPlayerDialogue(9827, "Who does my money go to?");
			} else if (componentId == OPTION_3) {
				stage = 75;
				sendPlayerDialogue(9827, "No thanks, I'll walk around.");
			}

		} else if (stage == 25) {
			stage = 100;
			sendNPCDialogue(925, 9827, "You may pass.");
			if (player.canBuy(10)) {
				player.lock(2);
				DoorsAndGates.handleDoorTemporary(player, object, 1200);
			}
		} else if (stage == 50) {
			stage = 0;
			sendNPCDialogue(925, 9827, "The money goes to the city of Al-Kharid.");
		} else if (stage == 75) {
			stage = 100;
			sendNPCDialogue(925, 9827, "As you wish. Don't go too near the scopions.");
		} else if (stage == 100) {
			end();
		}
	}

	@Override
	public void finish() {

	}

}
