package com.rs.game.player.dialogues.npcs;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.VariableKeys;
import com.rs.game.player.content.tasksystem.TaskManager.Tasks;
import com.rs.game.player.dialogues.Dialogue;

public class MrEx extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		if (!player.get(VariableKeys.BooleanKey.TALKED_TO_MR_EX)) {
			stageName = "FirstTimeStage2";
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"Are you heading out into the Wilderness? If so, I have a gift for you." },
					IS_NPC, npcId, 9827);
		} else if (player.get(VariableKeys.BooleanKey.TALKED_TO_MR_EX) && !player.hasWildstalker()) {
			stageName = "LostHatStage2";
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "You've lost your hat, it seems." },
					IS_NPC, npcId, 9827);
		} else if (player.get(VariableKeys.BooleanKey.TALKED_TO_MR_EX) && player.hasWildstalker()) {
			stageName = "TellMoreOptions";
			sendOptionsDialogue("Choose an option.", "Tell me more about this hat, then.", "Farewell!");
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stageName == "LostHatStage2") {
			stageName = "LostHatStage3";
			sendPlayerDialogue(9827, "Yes, somewhere out there.");
		} else if (stageName == "LostHatStage3") {
			stageName = "LostHatStage4";
			sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
					"Well, I don't mind getting it back for you..." }, IS_NPC, npcId, 9827);
		} else if (stageName == "LostHatStage4") {
			stageName = "LostHatStage5";
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "It's not in the Wilderness, is it?" },
					IS_NPC, npcId, 9827);
		} else if (stageName == "LostHatStage5") {
			if (!player.getInventory().hasFreeSlots()) {
				stageName = "end";
				sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"You don't have enough space in your inventory for me to give you a wildstalker helmet." },
						IS_NPC, npcId, 9827);
			} else {
				stageName = "LostHatStage6";
				player.getInventory().addItem(20801, 1);
				sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Ah, here we go, it's come home to me. Look after it." }, IS_NPC, npcId, 9827);
			}
		} else if (stageName == "LostHatStage6") {
			stageName = "TellMoreOption";
			sendPlayerDialogue(9827, "Thanks!");
		} else if (stageName == "FirstTimeStage2") {
			stageName = "Option1";
			sendOptionsDialogue("Choose an option.", "Yes, I am.", "Erm, no.");
		} else if (stageName == "Option1") {
			if (componentId == OPTION_1) {
				stageName = "FirstTimeStage3";
				sendPlayerDialogue(9827, "Yes, I am.");
			} else if (componentId == OPTION_2) {
				stageName = "end";
				sendPlayerDialogue(9827, "Erm, no.");
			}
		} else if (stageName == "FirstTimeStage3") {
			stageName = "FirstTimeStage4";
			sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
					"Wouldn't catch me doing that, oh no. I'll just stay here and give wildstalker helmets to fighters like you." },
					IS_NPC, npcId, 9827);
		} else if (stageName == "FirstTimeStage4") {
			if (!player.getInventory().hasFreeSlots()) {
				stageName = "end";
				sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"You don't have enough space in your inventory for me to give you a wildstalker helmet." },
						IS_NPC, npcId, 9827);
			} else {
				stageName = "FirstTimeStage5";
				player.set(VariableKeys.BooleanKey.TALKED_TO_MR_EX, true);
				player.getTaskManager().checkComplete(Tasks.CLAIM_WILDSTALKER_HELMET);
				player.getInventory().addItem(20801, 1);
				sendEntityDialogue(SEND_ITEM_DIALOGUE, new String[] { ItemDefinitions.getItemDefinitions(20801).name,
						"Mr Ex gives you an armoured helmet." }, IS_ITEM, 20801, -1);
			}
		} else if (stageName == "FirstTimeStage5") {
			stageName = "TellMoreOption";
			sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
					"I hope this looks after you out there. Wear it with pride." }, IS_NPC, npcId, 9827);
		} else if (stageName == "TellMoreOption") {
			stageName = "TellMoreOptions";
			sendOptionsDialogue("Choose an option.", "Tell me more about this hat, then.", "Farewell!");
		} else if (stageName == "TellMoreOptions") {
			if (componentId == OPTION_1) {
				stageName = "TellMoreStage1";
				sendPlayerDialogue(9827, "Tell me more about this hat, then.");
			} else if (componentId == OPTION_2) {
				stageName = "end";
				sendPlayerDialogue(9827, "Farewell!");
			}
		} else if (stageName == "TellMoreStage1") {
			stageName = "TellMoreStage2";
			sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
					"While you own this hat, Your kills and deaths in the Wilderness will be recorded, as will yours most valuable kill and your current kill streak." },
					IS_NPC, npcId, 9827);
		} else if (stageName == "TellMoreStage2") {
			stageName = "TellMoreStage3";
			sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
					"You can use your hat to display your achievements by turning yourself into a statue. Others will be able to read your statue's plaque and see your statistics." },
					IS_NPC, npcId, 9827);
		} else if (stageName == "TellMoreStage3") {
			stageName = "TellMoreStage4";
			sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
					"If you wish to, you can cause the hat to forget the carnage you've caused, or instruct it not to show certain elements." },
					IS_NPC, npcId, 9827);
		} else if (stageName == "TellMoreStage4") {
			stageName = "end";
			sendPlayerDialogue(9827, "Handy, thanks!");
		} else if (stageName == "end")
			end();

	}

	@Override
	public void finish() {

	}
}