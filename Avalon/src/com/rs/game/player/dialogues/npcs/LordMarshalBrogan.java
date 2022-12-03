package com.rs.game.player.dialogues.npcs;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.dialogues.Dialogue;

public class LordMarshalBrogan extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		stage = 0;
		sendEntityDialogue(SEND_1_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "How can I help you?" }, IS_NPC, npcId,
				9827);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 0) {
			stage = 1;
			sendPlayerDialogue(9827, "Who are you?");
		} else if (stage == 1) {
			stage = 2;
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"Oh, sorry - I'm Lord Marshal Brogan. I sell titles, auras and enchant rings." },
					IS_NPC, npcId, 9827);
		} else if (stage == 2) {
			stage = 3;
			sendOptionsDialogue("What would you like to say?", "Buy Titles", "Buy Auras", "Imbue rings", "Nothing");
		} else if (stage == 3) {
			if (componentId == OPTION_1) {
				stage = -1;
				player.getDialogueManager().startDialogue("Titles", npcId);
			} else if (componentId == OPTION_2) {
				stage = -1;
				player.getDialogueManager().startDialogue("Auras", npcId);
			} else if (componentId == OPTION_3) {
				player.getDialogueManager().startDialogue("ImbuedRings", npcId);
			} else if (componentId == OPTION_4) {
				stage = 4;
				sendPlayerDialogue(9827, "No thanks.");
			}
		} else if (stage == 4) {
			stage = 5;
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "Alright, bye!" }, IS_NPC, npcId,
					9827);
		} else if (stage == 5)
			end();
	}

	@Override
	public void finish() {
	}
}
