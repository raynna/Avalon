package com.rs.game.player.dialogues.npcs;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.dialogues.Dialogue;

public class RangeInstructor extends Dialogue {
	
	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		stageName = "start";
		sendOptionsDialogue("Choose an Option", "Do you got any bow and arrows?", "Goodbye.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stageName == "start") {
			switch (componentId) {
			case OPTION_1:
				stageName = "giveItems";
				sendPlayerDialogue(Happy, "Do you got any bow and arrows?");
				break;
			case OPTION_2:
				sendPlayerDialogue(Unsure,
						"Goodbye.");
				end();
				break;
			}
		} else if (stageName == "giveItems") {
			if ((player.getInventory().containsItem(9705, 1) && player.getInventory().containsItem(9706, 25))
					|| (player.getEquipment().getWeaponId() == 9705 && player.getEquipment().getAmmoId() == 9704)) {
				stageName = "end";
				sendNPCDialogue(npcId, Thinking,
						"You already have both the bow and arrows.");
				return;
			}
			if (player.getInventory().containsItem(9705, 1) && !player.getInventory().containsItem(9706, 25)) {
				stageName = "end";
				player.getInventory().addItem(9706, 25 - player.getInventory().getNumberOf(9706));
				sendEntityDialogue(SEND_ITEM_DIALOGUE,
						new String[] { ItemDefinitions.getItemDefinitions(9706).name,
								"You already have the bow, but heres some arrows." },
						IS_ITEM, 9710, -1);
				return;
			}
			if (!player.getInventory().containsItem(9705, 1) && player.getInventory().containsItem(9706, 25)) {
				stageName = "end";
				player.getInventory().addItem(9705, 1);
				sendEntityDialogue(SEND_ITEM_DIALOGUE,
						new String[] { ItemDefinitions.getItemDefinitions(9705).name,
								"You already have some arrows, but heres the bow." },
						IS_ITEM, 9705, -1);
				return;
			}
			stageName = "bow";
			sendNPCDialogue(npcId, Thinking,
					"Yes Of course! Heres the bow!");
		} else if (stageName == "bow") {
			stageName = "bow2";
			player.getInventory().addItem(9705, 1);
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(9705).name,
							NPCDefinitions.getNPCDefinitions(npcId).getName() + " hands you the training bow." },
					IS_ITEM, 9705, -1);
		} else if (stageName == "bow2") {
			stageName = "arrows";
			sendNPCDialogue(npcId, Thinking,
					"And heres the arrows!");
		} else if (stageName == "arrows") {
			stageName = "thanks";
			player.getInventory().addItem(9706, 25 - player.getInventory().getNumberOf(9706));
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(9706).name,
							NPCDefinitions.getNPCDefinitions(npcId).getName() + " hands you some training arrows." },
					IS_ITEM, 9710, -1);
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