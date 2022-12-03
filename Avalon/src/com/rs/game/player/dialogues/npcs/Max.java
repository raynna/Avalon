package com.rs.game.player.dialogues.npcs;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.World;
import com.rs.game.player.dialogues.Dialogue;

public class Max extends Dialogue {

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
			sendOptionsDialogue("What would you like to say?", "Who are you?", "Nice cape you have there...",
					"Do you have any other capes?", "Nothing, thanks.");
		} else if (stage == 1) {
			if (componentId == OPTION_1) {
				stage = 10;
				sendPlayerDialogue(9827, "Who are you?");
			} else if (componentId == OPTION_2) {
				stage = 20;
				sendPlayerDialogue(9827, "Nice cape you have there...");
			} else if (componentId == OPTION_3) {
				stage = 40;
				sendPlayerDialogue(9827, "Do you have any other capes?");
			} else if (componentId == OPTION_3) {
				stage = 50;
				sendPlayerDialogue(9827, "Nothing, thanks.");
			}
		} else if (stage == 10) {
			stage = 11;
			sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
					"Oh, sorry - I'm Max. Some say I'm a bit obsessed with skilling." }, IS_NPC, npcId, 9827);
		} else if (stage == 11) {
			stage = 12;
			sendPlayerDialogue(9827, "And I'm " + player.getUsername() + ", nice to meet you.");
		} else if (stage == 12) {
			stage = 0;
			sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
					"Indeed, so can I help you with anything?" }, IS_NPC, npcId, 9827);
		} else if (stage == 20) {
			stage = 21;
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"This? Thanks! It's a symbol that I've trained all of my skills to level 99." },
					IS_NPC, npcId, 9827);
		} else if (stage == 21) {
			if (player.hasMaxCapeRequirements()) {
				stage = 30;
				sendEntityDialogue(SEND_1_TEXT_CHAT,
						new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
								"I can see that you actually have the requirements for one, Would you like to buy one for 5m?" },
						IS_NPC, npcId, 9827);
			} else {
				stage = 22;
				sendPlayerDialogue(9827, "Wow, that's quite impressive.");
			}
		} else if (stage == 22) {
			stage = 23;
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"Thanks. I have faith in you, " + player.getUsername()
									+ " - one day you'll be here and I'll sell you one for yourself!" },
					IS_NPC, npcId, 9827);
			sendRequirementMessages();
		} else if (stage == 23) {
			stage = 24;
			sendPlayerDialogue(9827, "We'll see about that, thanks.");
		} else if (stage == 24) {
			stage = 50;
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "Farewell and good luck." }, IS_NPC,
					npcId, 9827);
		} else if (stage == 30) {
			stage = 31;
			sendOptionsDialogue("Choose an option.", "Yes, please", "No thanks.");
		} else if (stage == 31) {
			if (componentId == OPTION_1) {
				if (player.getInventory().getFreeSlots() < 2) {
					stage = 50;
					sendEntityDialogue(SEND_2_TEXT_CHAT,
							new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
									"I'm afraid you don't have enough inventory space to do that." },
							IS_NPC, npcId, 9827);
				} else if (player.hasMoney(5000000)) {
					stage = 50;
					sendEntityDialogue(SEND_ITEM_DIALOGUE, new String[] {
							ItemDefinitions.getItemDefinitions(20767).name, "Max hands over the max cape." }, IS_ITEM,
							20767, -1);
					player.takeMoney(5000000);
					player.getInventory().addItem(20767, 1);
					player.getInventory().addItem(20768, 1);
					if (!player.boughtMaxCape) {
						player.boughtMaxCape = true;
						World.sendWorldMessage(
								"<img=6><col=ff0000>News: " + player.getDisplayName() + " has just achieved Max cape.",
								false);
					}
				} else {
					stage = 50;
					sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"I'm afraid you don't have enough enough money to do that." }, IS_NPC, npcId, 9827);
				}
			} else if (componentId == OPTION_2) {
				stage = 50;
				sendPlayerDialogue(9827, "Nothing thanks.");
			}
		} else if (stage == 40) {
			stage = 41;
			sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
					"I do have capes for people who achieved 99 in a skill." }, IS_NPC, npcId, 9827);
		} else if (stage == 41) {
			stage = 50;
			player.stopAll();
			player.getCustomStore().sendInterface(3);
		} else if (stage == 50)
			end();
	}

	public void sendRequirementMessages() {
		if (!player.hasMaxCapeRequirements()) {
			player.getPackets().sendGameMessage("You need level 99 in the following: ");
			for (int skill = 0; skill < 25; skill++) {
				if (player.getSkills().getLevelForXp(skill) >= 99)
					continue;
				player.getPackets().sendGameMessage(player.getSkills().getSkillName(skill));
			}
		}
	}

	@Override
	public void finish() {
	}
}
