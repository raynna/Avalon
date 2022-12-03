package com.rs.game.player.dialogues.npcs;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.dialogues.Dialogue;

public class MeleeInstructor extends Dialogue {
	
	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		stageName = "start";
		sendOptionsDialogue("Choose an Option", "Do you got any spare sword & shield?", "Goodbye.");
	}
	
	/*
	 * sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(995).name,
							"...Congratulations adventurer, you have been deemed worthy of this reward." },
					IS_ITEM, 1003, -1);
	 */

	@Override
	public void run(int interfaceId, int componentId) {
		if (stageName == "start") {
			switch (componentId) {
			case OPTION_1:
				stageName = "giveItems";
				sendPlayerDialogue(Happy, "Do you got any spare sword & shield?");
				break;
			case OPTION_2:
				sendPlayerDialogue(Unsure,
						"Goodbye.");
				end();
				break;
			}
		} else if (stageName == "giveItems") {
			if ((player.getInventory().containsItem(9703, 1) && player.getInventory().containsItem(9704, 1))
					|| (player.getEquipment().getWeaponId() == 9703 && player.getEquipment().getShieldId() == 9704)) {
				stageName = "end";
				sendNPCDialogue(npcId, Thinking,
						"You already have both the sword and the shield.");
				return;
			}
			if (player.getInventory().containsItem(9703, 1) && !player.getInventory().containsItem(9704, 1)) {
				stageName = "end";
				player.getInventory().addItem(9704, 1);
				sendEntityDialogue(SEND_ITEM_DIALOGUE,
						new String[] { ItemDefinitions.getItemDefinitions(9704).name,
								"You already have the sword, but heres the shield." },
						IS_ITEM, 9704, -1);
				return;
			}
			if (!player.getInventory().containsItem(9703, 1) && player.getInventory().containsItem(9704, 1)) {
				stageName = "end";
				player.getInventory().addItem(9703, 1);
				sendEntityDialogue(SEND_ITEM_DIALOGUE,
						new String[] { ItemDefinitions.getItemDefinitions(9703).name,
								"You already have the shield, but heres the sword." },
						IS_ITEM, 9703, -1);
				return;
			}
			stageName = "sword";
			sendNPCDialogue(npcId, Thinking,
					"Yes Of course! Heres the sword!");
		} else if (stageName == "sword") {
			stageName = "sword2";
			player.getInventory().addItem(9703, 1);
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(9703).name,
							NPCDefinitions.getNPCDefinitions(npcId).getName() + " hands you the training sword." },
					IS_ITEM, 9703, -1);
		} else if (stageName == "sword2") {
			stageName = "shield";
			sendNPCDialogue(npcId, Thinking,
					"And heres the shield!");
		} else if (stageName == "shield") {
			stageName = "thanks";
			player.getInventory().addItem(9704, 1);
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(9704).name,
							NPCDefinitions.getNPCDefinitions(npcId).getName() + " hands you the training shield." },
					IS_ITEM, 9704, -1);
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