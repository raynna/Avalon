package com.rs.game.player.dialogues.skilling;

import com.rs.Settings;
import com.rs.game.item.Item;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.construction.HouseConstants.POHLocation;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class EstateAgentD extends Dialogue {

	private static final POHLocation[] LOCATIONS = { POHLocation.HOME };
	private static final int[] REDECORATE_PRICE = { 5000, 5000, 7000, 10000, 15000, 25000, 0 };
	private static final int[] REDECORATE_BUILDS = { /* basic wood */3, 0
			/* basic stone */, 3, 1
			/* whitewash */, 1, 2
			/* fremmy wood */, 2, 3
			/* tropical wood */, 3, 4
			/* fancy stone */, 3, 5
			/* zenviva dark */, 3, 6 };

	private int npcId;

	@Override
	public void start() {
		npcId = (int) this.parameters[0];
		sendNPCDialogue(npcId, NORMAL,
				"Hello. Welcome to " + Settings.SERVER_NAME + " Housing Agency! What can I do you for?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stageInt == -1) {
			sendOptionsDialogue("SELECT AN OPTION", "Can you redecorate my house please?",
					"Could I have a Construction guidebook?", "Tell me about houses.",
					"Can you get me a servant to talk to?",
					player.getSkills().getLevel(Skills.CONSTRUCTION) == 99
							? "Can you sell me a Skillcape of Construction?"
							: "Tell me about that skillcape you're wearing.");
			stageInt = 0;
		} else if (stageInt == 0) {
			if (componentId == OPTION_1) {
				sendPlayerDialogue(NORMAL, "Can you redecorate my house please?");
				stageInt = 5;
			} else if (componentId == OPTION_2) {
				sendPlayerDialogue(NORMAL, "Could I have a Construction guidebook?");
				stageInt = 14;
			} else if (componentId == OPTION_3) {
				sendPlayerDialogue(NORMAL, "Tell me about houses.");
				stageInt = 9;
			} else if (componentId == OPTION_4) {
				sendPlayerDialogue(NORMAL, "Can you get me a servant?");
				stageInt = 25;
			} else if (componentId == OPTION_5) {
				boolean is99Con = player.getSkills().getLevel(Skills.CONSTRUCTION) == 99;
				sendPlayerDialogue(NORMAL, is99Con ? "Can you sell me a Skillcape of Construction?"
						: "Tell me about that cape you're wearing.");
				stageInt = (int) (is99Con ? 17 : 15);
			}
		} else if (stageInt == 1) {
			sendNPCDialogue(npcId, NORMAL, "Certainly. Where would you like it moved to?");
			stageInt = 2;
		} else if (stageInt == 2) {
			sendOptionsDialogue("SELECT AN OPTION", "Rimmington (5,000)", "Taverly (5,000)", "Pollnivneach (7,500)",
					"Relleka (10,000)", "More");
			stageInt = 3;
		} else if (stageInt == 3) {
			if (componentId >= OPTION_1 && componentId <= OPTION_4) {
				moveHouse(LOCATIONS[componentId == 11 ? 0 : componentId - 12]);
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("SELECT AN OPTION", "Brimhaven (15,000)", "Yannile (25,000)", "Previous");
				stageInt = 4;
			}
		} else if (stageInt == 4) {
			if (componentId == OPTION_1 || componentId == OPTION_2) {
				moveHouse(LOCATIONS[componentId == OPTION_1 ? 4 : 5]);
			} else if (componentId == OPTION_3) {
				sendOptionsDialogue("SELECT AN OPTION", "Rimmington (5,000)", "Taverly (5,000)", "Pollnivneach (7,500)",
						"Relleka (10,000)", "More");
				stageInt = 3;
			}
		} else if (stageInt == 5) {
			sendNPCDialogue(npcId, NORMAL,
					"Certainly. My magic can rebuild the house in a completely new style! What style would you like?");
			stageInt = 6;
		} else if (stageInt == 6) {
			sendOptionsDialogue("SELECT AN OPTION", "Basic wood (5,000)", "Basic stone (5,000)",
					"Whitewashed stone (7,500)", "Fremenik-style wood (10,000)", "More");
			stageInt = 7;
		} else if (stageInt == 7) {
			if (componentId >= OPTION_1 && componentId <= OPTION_4) {
				redecorateHouse(componentId == 11 ? 0 : componentId - 12);
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("SELECT AN OPTION", "Tropical Wood (15,000)", "Fancy stone (25,000)",
						"Zeneviva's dark stone (Free)", "Previous");
				stageInt = 8;
			}
		} else if (stageInt == 8) {
			if (componentId == OPTION_1 || componentId == OPTION_2 || componentId == OPTION_3) {
				redecorateHouse(componentId == OPTION_1 ? 4 : componentId == OPTION_2 ? 5 : 6);
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("SELECT AN OPTION", "Basic wood (5,000)", "Basic stone (5,000)",
						"Whitewashed stone (7,500)", "Fremenik-style wood (10,000)", "More");
				stageInt = 7;
			}
		} else if (stageInt == 9) {
			sendNPCDialogue(npcId, NORMAL,
					"It all came out of the wizards' experiments. They found a way to fold space, so that they could pack many acres of land into an area only a foot across.");
			stageInt = 10;
		} else if (stageInt == 10) {
			sendNPCDialogue(npcId, NORMAL, "They created serveral folded-space regions across " + Settings.SERVER_NAME
					+ ". Each one contains hundreds of small plots where people can build houses.");
			stageInt = 11;
		} else if (stageInt == 11) {
			sendPlayerDialogue(NORMAL,
					"Ah, so that's how everyone can have a house without them cluttering up the world!");
			stageInt = 12;
		} else if (stageInt == 12) {
			sendNPCDialogue(npcId, NORMAL,
					"Quite. The wizards didn't want to get bogged down in the business side of things so they hired me to sell the houses.");
			stageInt = 13;
		} else if (stageInt == 13) {
			sendNPCDialogue(npcId, NORMAL, "There are various other people across " + Settings.SERVER_NAME
					+ " who can help you furnish your house. You should start by buying planks from the sawmill operator in Varrock.");
			stageInt = 30;
		} else if (stageInt == 14) {
			sendNPCDialogue(npcId, NORMAL, "Certainly.");
			player.getInventory().addItem(new Item(8463));
			stageInt = 30;
		} else if (stageInt == 15) {
			sendNPCDialogue(npcId, NORMAL,
					"As you may know, skillcapes are only avaiable to maters in a skill. I have spent my entire life building houses and now I spend my time selling them! As a sign of my abilities I wear this Skillcape of Construction. If you ever have");
			stageInt = 16;
		} else if (stageInt == 16) {
			sendNPCDialogue(npcId, NORMAL,
					"enough skill to build a demonic throne, come and talk to me and i'll sell you a skillcape like mine.");
			stageInt = 30;
		} else if (stageInt == 17) {
			sendNPCDialogue(npcId, NORMAL, "Alright, that'll be 99,000 coins.");
			stageInt = 18;
		} else if (stageInt == 18) {
			sendOptionsDialogue("Select an option", "I'm not paying that!", "Certainly, that sounds fair.");
			stageInt = 19;
		} else if (stageInt == 19) {
			if (componentId == OPTION_1) {
				sendPlayerDialogue(NORMAL, "I'm not paying that!");
				stageInt = 30;
			} else if (componentId == OPTION_2) {
				sendPlayerDialogue(NORMAL, "Certainly, that sounds fair.");
				stageInt = 20;
			}
		} else if (stageInt == 20) {
			if (player.canBuy(99000)) {
				sendNPCDialogue(npcId, NORMAL, "Excellent, wear that cape with pride my friend.");
				player.getInventory().addItem(new Item(9791, 1));
				player.getInventory().addItem(new Item(9790, 1));
			} else {
				sendNPCDialogue(npcId, NORMAL, "You don't have enough coins to cover the cost of the cape.");
			}
			stageInt = 30;
		} else if (stageInt == 30) {
			end();

			// else if (npc.getId() == 15513 || npc.getId() >= 11303 && npc.getId() <=
			// 11307)
			// player.getDialogueManager().startDialogue("ServantDialogue", npc.getId());
		} else if (stageInt == 25) {
			sendNPCDialogue(npcId, NORMAL, "Certainly. What servant do you want to talk to?");
			stageInt = 26;
		} else if (stageInt == 26) {
			sendOptionsDialogue("SELECT AN OPTION", "Rick", "Maid", "Cook", "Butler", "Demon butler");
			stageInt = 27;
		} else if (stageInt == 27) {
			player.getDialogueManager().startDialogue("ServantDialogue",
					componentId == 11 ? 11303 : 11303 + ((componentId - 10) - 2));
		}
	}

	private void redecorateHouse(int index) {
		int cost = REDECORATE_PRICE[index];
		if (player.getHouse().getLook() == index) {
			player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
					"Your house is already in that style!");
			return;
		}
		if (player.canBuy(cost)) {
			player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId, "Your house has been redecorated.");
			player.getHouse().redecorateHouse(REDECORATE_BUILDS[(index * 2) + 1]);
		} else {
			player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
					"You don't have enough capital to cover the costs. Please return when you do!");
			return;
		}
	}

	private void moveHouse(POHLocation location) {
		int cost = location.getCost();
		String regionalName = Utils.formatPlayerNameForDisplay(location.toString().toLowerCase());

		if (player.getHouse().getLocation() == location) {
			player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
					"Your home is already located in " + regionalName + "!");
			return;
		}
		if (player.canBuy(cost)) {
			player.getHouse().setLocation(null);
			player.getHouse().setLocation(location);
			player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
					"Your home has been moved to " + regionalName + "!");
		} else {
			player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
					"You don't have enough capital to cover the costs. Please return when you do!");
			return;
		}
	}

	@Override
	public void finish() {

	}
}
