package com.rs.game.player.dialogues.npcs;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;

public class Oziach extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Can I help you at all?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Yes, please. What are you selling?",
					"What can you do for me?", "No, thanks.");
			break;
		case 0:
			if (componentId == OPTION_1) {
				ShopsHandler.openShop(player, 35);
				end();
			} else if (componentId == OPTION_2) {
				stage = 1;
				sendNPCDialogue(npcId, 9827, "I'm glad you ask! You can give me a rune platebody",
						"or green d'hide body that i can exchange for a random trimmed piece. The price of these items are diffrent",
						", rune platebody trim, 100,000 & green d'hide body 50,000.");
			} else if (componentId == OPTION_3)
				end();
			break;
		case 1:
			stage = -2;
			sendNPCDialogue(npcId, 9827, "You can also view my store which includes the items needed.");
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
