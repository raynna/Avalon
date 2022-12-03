package com.rs.game.player.dialogues.npcs;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.dialogues.Dialogue;

public class Estocada extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		if (!player.talkedToEstocada) {
			stageName = "FirstTimeStage2";
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"Ah, a new face. You are here to fight, yes? Honour and blood under the Code Duello?" },
					IS_NPC, npcId, 9827);
		} else if (player.talkedToEstocada && !player.hasDuellist()) {
			stageName = "LostHatStage2";
			sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
					"My friend, you have not got your hat. Where is it gone to?" }, IS_NPC, npcId, 9827);
		} else if (player.talkedToEstocada && player.hasDuellist()) {
			stageName = "TellMoreOptions";
			sendOptionsDialogue("Choose an option.", "Tell me more about this hat, then.", "Farewell!");
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stageName == "LostHatStage2") {
			stageName = "LostHatStage3";
			sendPlayerDialogue(9827, "I lost it.");
		} else if (stageName == "LostHatStage3") {
			stageName = "LostHatStage4";
			sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
					"Well, it is not a problem, these things can be overcome." }, IS_NPC, npcId, 9827);
		} else if (stageName == "LostHatStage4") {
			stageName = "LostHatStage5";
			player.getInventory().addItem(20795, 1);
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"I don't often replace hats, but, when I do, I choose to replace duellists' caps." },
					IS_NPC, npcId, 9827);
		} else if (stageName == "LostHatStage5") {
			stageName = "TellMoreOption";
			sendPlayerDialogue(9827, "Thanks");
		} else if (stageName == "FirstTimeStage2") {
			stageName = "Option1";
			sendOptionsDialogue("Choose an option.", "Yes, that's right.", "Erm, no.");
		} else if (stageName == "Option1") {
			if (componentId == OPTION_1) {
				stageName = "FirstTimeStage3";
				sendPlayerDialogue(9827, "Yes, that's right.");
			} else if (componentId == OPTION_2) {
				stageName = "end";
				sendPlayerDialogue(9827, "Erm, no.");
			}
		} else if (stageName == "FirstTimeStage3") {
			stageName = "FirstTimeStage4";
			sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
					"Glad to hear it! Then I have something for you." }, IS_NPC, npcId, 9827);
		} else if (stageName == "FirstTimeStage4") {
			stageName = "TellMoreOption";
			player.talkedToEstocada = true;
			player.getInventory().addItem(20795, 1);
			sendEntityDialogue(SEND_ITEM_DIALOGUE, new String[] { ItemDefinitions.getItemDefinitions(20795).name,
					"Estocada gives you a wide-brimmed hat." }, IS_ITEM, 20795, -1);
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
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"All the time you have this hat on your person or in your inventory, it will see you win or fall in the dual arena and take note." },
					IS_NPC, npcId, 9827);
		} else if (stageName == "TellMoreStage2") {
			stageName = "TellMoreStage3";
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"By it's magic you can turn into a statue, which shows those who read it's plaque how well you have fought - how many you defeat, your win streak, and the like." },
					IS_NPC, npcId, 9827);
		} else if (stageName == "TellMoreStage3") {
			stageName = "TellMoreStage4";
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"If you wish to, you can cause the hat to forget all it has seen of your prowness, or instruct it not to show certain elements." },
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