package com.rs.game.player.dialogues.npcs;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;

public class MagicStore extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_1_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "Hello, which store would you like to open?" }, IS_NPC, npcId,
				9827);

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Runes store", "Magic armour store", "Magic weapon store");
		} else if (stage == 0) {
			if (componentId == OPTION_1) {
				end();
				ShopsHandler.openShop(player, 9);
			} else if (componentId == OPTION_2) {
				end();
				ShopsHandler.openShop(player, 10);
			} else {
				end();
				ShopsHandler.openShop(player, 12);
			}
		} else if (stage == 2) {
			stage = -2;
			end();
		} else
			end();

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
