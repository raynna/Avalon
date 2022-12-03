package com.rs.game.player.dialogues.npcs;

import com.rs.game.item.itemdegrading.ArmourRepair;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class Bob extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827,
				"Hello, " + player.getDisplayName() + ". I can repair all your broken barrows pieces.");
	}

	@Override
	public void run(int interfaceId, int option) {
		switch (stage) {
		case -1:
			if (ArmourRepair.getTotalPrice(player) != 0) {
				sendOptionsDialogue(
						"Do you want to have your items repaired for "
								+ Utils.getFormattedNumber(ArmourRepair.getTotalPrice(player), ',') + " coins?",
						"Yes", "No");
				stage = 0;
			} else {
				sendNPCDialogue(npcId, 9827, "You don't have any items to repair.");
				stage = -2;
			}
		case 0:
			switch (option) {
			case OPTION_1:
				ArmourRepair.repairAllItems(player);
				end();
				break;
			case OPTION_2:
				end();
				break;
			}
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