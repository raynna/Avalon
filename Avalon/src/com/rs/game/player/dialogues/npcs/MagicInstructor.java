package com.rs.game.player.dialogues.npcs;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.dialogues.Dialogue;

public class MagicInstructor extends Dialogue {
	
	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		stageName = "start";
		sendOptionsDialogue("Choose an Option", "Do you got any magic runes?", "Goodbye.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stageName == "start") {
			switch (componentId) {
			case OPTION_1:
				stageName = "giveItems";
				sendPlayerDialogue(Happy, "Do you got any magic runes?");
				break;
			case OPTION_2:
				sendPlayerDialogue(Unsure,
						"Goodbye.");
				end();
				break;
			}
		} else if (stageName == "giveItems") {
			if ((player.getInventory().containsItem(556, 30) && player.getInventory().containsItem(558, 30))) {
				stageName = "end";
				sendNPCDialogue(npcId, Thinking,
						"You already have some runes.");
				return;
			}
			if (player.getInventory().containsItem(556, 30) && !player.getInventory().containsItem(558, 30)) {
				stageName = "end";
				player.getInventory().addItem(558, 30 - player.getInventory().getNumberOf(558));
				sendEntityDialogue(SEND_ITEM_DIALOGUE,
						new String[] { ItemDefinitions.getItemDefinitions(558).name,
								"You already have air runes, but heres some mind runes." },
						IS_ITEM, 558, -1);
				return;
			}
			if (!player.getInventory().containsItem(556, 30) && player.getInventory().containsItem(558, 30)) {
				stageName = "end";
				player.getInventory().addItem(556, 30 - player.getInventory().getNumberOf(556));
				sendEntityDialogue(SEND_ITEM_DIALOGUE,
						new String[] { ItemDefinitions.getItemDefinitions(556).name,
								"You already have mind runes, but heres some air runes." },
						IS_ITEM, 556, -1);
				return;
			}
			stageName = "runes";
			sendNPCDialogue(npcId, Thinking,
					"Yes Of course! Heres some runes!");
		} else if (stageName == "runes") {
			stageName = "runes2";
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(558).name,
							NPCDefinitions.getNPCDefinitions(npcId).getName() + " hands you " + (30 - player.getInventory().getNumberOf(558)) + " mind runes." },
					IS_ITEM, 558, -1);
			player.getInventory().addItem(558, 30 - player.getInventory().getNumberOf(558));
		} else if (stageName == "runes2") {
			stageName = "thanks";
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(556).name,
							NPCDefinitions.getNPCDefinitions(npcId).getName() + " hands you " + (30 - player.getInventory().getNumberOf(556)) + " air runes." },
					IS_ITEM, 556, -1);
			player.getInventory().addItem(556, 30 - player.getInventory().getNumberOf(556));
		} else if (stageName == "thanks") {
			stageName = "end";
			sendPlayerDialogue(Happy,
					"Thank you!");
		} else if (stageName == "end")
			end();

	}

	@Override
	public void finish() {

	}
}