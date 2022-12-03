package com.rs.game.player.dialogues;

import com.rs.game.player.content.unlockables.UnlockableManager;

public class UnlockItemsD extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		stage = 0;
		sendNPCDialogue(13955, 9827, "Hello, how can i help you?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 0) {
			stage = 1;
			sendOptionsDialogue("Choose an option", "Show list", "Unlock item", "Spawn item", "Nothing");
		} else if (stage == 1) {
			if (componentId == OPTION_1) {
				UnlockableManager.showList(player);
				end();
			} else if (componentId == OPTION_2) {
				player.temporaryAttribute().put("unlock_item", Boolean.TRUE);
				player.getPackets().sendRunScript(108, new Object[] { "Enter itemId you wish to unlock: " });
				end();
			} else if (componentId == OPTION_3) {
				player.temporaryAttribute().put("spawn_item", Boolean.TRUE);
				player.getPackets().sendRunScript(108, new Object[] { "Enter itemId you wish to spawn: " });
				end();
			} else if (componentId == OPTION_4) {
				sendNPCDialogue(13955, 9827, "Nothing? fine..");
				stage = 10;
			}
		} else if (stage == 10) {
			end();
		}
	}

	@Override
	public void finish() {

	}

}
