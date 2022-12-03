package com.rs.game.player.dialogues;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;

public class ImbuedRings extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		stage = 0;
		sendEntityDialogue(SEND_1_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"To imbue rings you need, a ring to imbue, 50K Avalon points and 5 million coins." },
				IS_NPC, npcId, 9827);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 0) {
			stage = 1;
			sendOptionsDialogue("What ring would you like to imbue?", "Berserker ring", "Archers' ring", "Seers' ring",
					"Warrior ring", "Onyx ring");
		}
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (!player.getInventory().containsItem(6737, 1)) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have a Berserker ring to imbue first." }, IS_NPC, npcId, 9827);
				} else if (player.getAvalonPoints() < 50000) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have at least 50,000 Avalon points." }, IS_NPC, npcId, 9827);
				} else if (!player.hasMoney(5000000)) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have at least 5,000,000 coins." }, IS_NPC, npcId, 9827);
				} else {
					stage = 2;
					sendEntityDialogue(SEND_ITEM_DIALOGUE,
							new String[] { ItemDefinitions.getItemDefinitions(15220).name,
									"Mashal Brogan imbues your Berserker ring" },
							IS_ITEM, 15220, -1);
					player.takeMoney(5000000);
					player.setAvalonPoints(player.getAvalonPoints() - 50000);
					player.getInventory().deleteItem(6737, 1);
					player.getInventory().addItem(15220, 1);
				}
			} else if (componentId == OPTION_2) {
				if (!player.getInventory().containsItem(6733, 1)) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have a Archers' ring to imbue first." }, IS_NPC, npcId, 9827);
				} else if (player.getAvalonPoints() < 50000) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have at least 50,000 Avalon points." }, IS_NPC, npcId, 9827);
				} else if (!player.hasMoney(5000000)) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have at least 5,000,000 coins." }, IS_NPC, npcId, 9827);
				} else {
					stage = 2;
					sendEntityDialogue(SEND_ITEM_DIALOGUE, new String[] {
							ItemDefinitions.getItemDefinitions(15019).name, "Mashal Brogan imbues your Archers' ring" },
							IS_ITEM, 15019, -1);
					player.takeMoney(5000000);
					player.setAvalonPoints(player.getAvalonPoints() - 50000);
					player.getInventory().deleteItem(6733, 1);
					player.getInventory().addItem(15019, 1);
				}
			} else if (componentId == OPTION_3) {
				if (!player.getInventory().containsItem(6731, 1)) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have a Seers' ring to imbue first." }, IS_NPC, npcId, 9827);
				} else if (player.getAvalonPoints() < 50000) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have at least 50,000 Avalon points." }, IS_NPC, npcId, 9827);
				} else if (!player.hasMoney(5000000)) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have at least 5,000,000 coins." }, IS_NPC, npcId, 9827);
				} else {
					stage = 2;
					sendEntityDialogue(SEND_ITEM_DIALOGUE, new String[] {
							ItemDefinitions.getItemDefinitions(15018).name, "Mashal Brogan imbues your Seers' ring" },
							IS_ITEM, 15018, -1);
					player.takeMoney(5000000);
					player.setAvalonPoints(player.getAvalonPoints() - 50000);
					player.getInventory().deleteItem(6731, 1);
					player.getInventory().addItem(15018, 1);
				}
			} else if (componentId == OPTION_4) {
				if (!player.getInventory().containsItem(6735, 1)) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have a Warrior ring to imbue first." }, IS_NPC, npcId, 9827);
				} else if (player.getAvalonPoints() < 50000) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have at least 50,000 Avalon points." }, IS_NPC, npcId, 9827);
				} else if (!player.hasMoney(5000000)) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have at least 5,000,000 coins." }, IS_NPC, npcId, 9827);
				} else {
					stage = 2;
					sendEntityDialogue(SEND_ITEM_DIALOGUE, new String[] {
							ItemDefinitions.getItemDefinitions(15020).name, "Mashal Brogan imbues your Warrior ring" },
							IS_ITEM, 15020, -1);
					player.takeMoney(5000000);
					player.setAvalonPoints(player.getAvalonPoints() - 50000);
					player.getInventory().deleteItem(6735, 1);
					player.getInventory().addItem(15020, 1);
				}
			} else if (componentId == OPTION_5) {
				if (!player.getInventory().containsItem(6575, 1)) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have a Onyx ring to imbue first." }, IS_NPC, npcId, 9827);
				} else if (player.getAvalonPoints() < 50000) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have at least 50,000 Avalon points." }, IS_NPC, npcId, 9827);
				} else if (!player.hasMoney(5000000)) {
					stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"You need to have at least 5,000,000 coins." }, IS_NPC, npcId, 9827);
				} else {
					stage = 2;
					sendEntityDialogue(SEND_ITEM_DIALOGUE, new String[] {
							ItemDefinitions.getItemDefinitions(15017).name, "Mashal Brogan imbues your Onyx ring" },
							IS_ITEM, 15017, -1);
					player.takeMoney(5000000);
					player.setAvalonPoints(player.getAvalonPoints() - 50000);
					player.getInventory().deleteItem(6575, 1);
					player.getInventory().addItem(15017, 1);
				}
			}
		} else if (stage == 2) {
			stage = 10;
			sendPlayerDialogue(9827, "Thanks!");
		} else if (stage == 10)
			end();
	}

	@Override
	public void finish() {
	}
}
