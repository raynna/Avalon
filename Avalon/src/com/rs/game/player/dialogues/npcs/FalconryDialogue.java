package com.rs.game.player.dialogues.npcs;

import com.rs.game.player.controlers.Falconry;
import com.rs.game.player.dialogues.Dialogue;

public class FalconryDialogue extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9836, "Hello, I'm Matthias the hunter expert.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendNPCDialogue(npcId, 9837, "Would you like to start falconry?");
		} else if (stage == 0) {
			stage = 1;
			sendPlayerDialogue(9838, "Yes, please.");
		} else if (stage == 1) {
			if (player.getEquipment().getItem(3) != null || player.getEquipment().getItem(5) != null) {
				end();
				sendNPCDialogue(npcId, 9839, "You cant wear anything in your hands while falconry.");
				return;
			} else {
				stage = 2;
				sendNPCDialogue(npcId, 9840, "Okay lets start.");
			}
		} else if (stage == 2) {
			end();
			Falconry.beginFalconry(player);
		} else if (stage == 25) {
			end();
		} else
			end();
	}

	@Override
	public void finish() {

	}

}
