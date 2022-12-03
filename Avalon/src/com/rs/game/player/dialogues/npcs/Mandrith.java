package com.rs.game.player.dialogues.npcs;

import com.rs.game.player.content.WildernessArtefacts;
import com.rs.game.player.dialogues.Dialogue;

public class Mandrith extends Dialogue {

	/*
	 * Written by Tristam
	 */

	private int Mandrith = 6537;
	private final int[] artefacts = { 14876, 14877, 14878, 14879, 14880, 14881, 14882, 14883, 14884, 14885, 14886,
			14887, 14888, 14889, 14890, 14891, 14892 };

	@Override
	public void start() {
		if (player.getInventory().containsOneItem(artefacts)) {
			sendNPCDialogue(Mandrith, Happy,
					"Glorious, brave warrior! I see you have found some of the ancient artefacts my brother and I are seeking.");
			stage = 26;
		} else {
			sendNPCDialogue(Mandrith, Plain, "How can I help you?");
			stage = 1;
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			end();
			break;
		case 1:
			sendOptionsDialogue("CHOOSE AN OPTION:", "Do you have anything for sale?", "Who are you?", "Oh, sorry, I thought you were someone else.");
			stage = 2;
			break;
		case 2:
			switch (componentId) {
			case OPTION_1:
				sendNPCDialogue(Mandrith, Happy,
						"Ah! Yes.. here is what i sell.");
				stage = 30;
				break;
			case OPTION_2:
				sendPlayerDialogue(Thinking, "Who are you?");
				stage = 3;
				break;
			case OPTION_3:
				sendPlayerDialogue(Plain, "Oh, sorry, I thought you were someone else.");
				stage = -1;
			}
			break;
		case 3:
			sendNPCDialogue(Mandrith, ListenAndLaugh,
					"Why, I'm Mandrith! Inspiration to combatants both mighty and puny!");
			stage = 4;
			break;
		case 4:
			sendPlayerDialogue(Plain, "Okay...fair enough");
			stage = 5;
			break;
		case 5:
			sendOptionsDialogue("Select an option", "What do you do here?", "Erm, what's with the outfit?",
					"I have to go now.");
			stage = 6;
			break;
		case 6:
			switch (componentId) {
			case OPTION_1:
				sendPlayerDialogue(Thinking, "What do you do here?");
				stage = 7;
				break;
			case OPTION_2:
				sendPlayerDialogue(Unsure, "Erm, what's with the outfit?");
				stage = 23;
				break;
			case OPTION_3:
				sendPlayerDialogue(Plain, "I have to go now.");
				stage = -1;
			}
			break;
		case 7:
			sendNPCDialogue(Mandrith, ListenAndLaugh,
					"I am here to collect ancient artefacts acquired by adventurers in return for some well-deserved rewards.");
			stage = 8;
			break;
		case 8:
			sendOptionsDialogue("Select an option", "What ancient artefacts?", "That sounds great, goodbye.");
			stage = 9;
			break;
		case 9:
			switch (componentId) {
			case OPTION_1:
				sendPlayerDialogue(Thinking, "What ancient artefacts?");
				stage = 10;
				break;
			case OPTION_2:
				sendPlayerDialogue(Plain, "That sounds great, goodbye.");
				stage = -1;
			}
			break;
		case 10:
			sendNPCDialogue(Mandrith, Happy, "Haha! I can tell you are new to these parts.");
			stage = 11;
			break;
		case 11:
			sendNPCDialogue(Mandrith, Plain, "As the blood of warriors is spilled on the ground, as it once "
					+ "was during the God Wars, relics of that age feel the call of battle and drawn into "
					+ "the rays of the sun once more. If, you happen to come across any of these ancient items,");
			stage = 12;
			break;
		case 12:
			sendNPCDialogue(Mandrith, Plain,
					"bring them to me or my brother Nastroth, "
							+ "and we will pay you a fair price for them. We don't accept them noted, though, so remember"
							+ " that. Also, we don't want to buy any weapons or armour.");
			stage = 13;
			break;
		case 13:
			sendOptionsDialogue("Select an option", "You have a brother?", "Why won't you buy weapons or armour?",
					"That sounds great. Goodbye.");
			stage = 14;
			break;
		case 14:
			switch (componentId) {
			case OPTION_1:
				sendPlayerDialogue(Thinking, "You have a brother?");
				stage = 15;
				break;
			case OPTION_2:
				sendPlayerDialogue(Thinking, "Why won't you buy weapons or armour?");
				stage = 19;
				break;
			case OPTION_3:
				sendPlayerDialogue(Plain, "That sounds great, goodbye.");
				stage = -1;
			}
			break;
		case 15:
			sendNPCDialogue(Mandrith, Plain, "Yes, why else would I have referred to him as such?");
			stage = 16;
			break;
		case 16:
			sendPlayerDialogue(Plain, "You make a good point.");
			stage = 17;
			break;
		case 17:
			sendOptionsDialogue("Select an Option", "Why won't you buy weapons or armour?",
					"That sounds great. Goodbye");
			stage = 18;
			break;
		case 18:
			switch (componentId) {
			case OPTION_1:
				sendPlayerDialogue(Thinking, "Why won't you buy weapons or armour?");
				stage = 19;
				break;
			case OPTION_2:
				sendPlayerDialogue(Plain, "That sounds great. Goodbye.");
				stage = -1;
			}
			break;
		case 19:
			sendNPCDialogue(Mandrith, Plain,
					"They should be used as they were meant to be used, and not traded in for rewards.");
			stage = 20;
			break;
		case 20:
			sendOptionsDialogue("Select an option", "You have a brother?", "That sounds great. Goodbye");
			stage = 21;
			break;
		case 21:
			switch (componentId) {
			case OPTION_1:
				sendNPCDialogue(Mandrith, Plain, "Yes, why else would I have referred to him as such?");
				stage = 22;
				break;
			case OPTION_2:
				sendPlayerDialogue(Plain, "That sounds great. Goodbye");
				stage = -1;
			}
			break;
		case 22:
			sendPlayerDialogue(Plain, "You make a good point.");
			stage = -1;
			break;
		case 23:
			sendNPCDialogue(Mandrith, Plain, "You like not my kingly robes? They were my father's, and"
					+ " his father's before him, and his father's before him, and his father's before him, and-");
			stage = 24;
			break;
		case 24:
			sendPlayerDialogue(Plain, "Okay! Okay! I get the picture.");
			stage = -1;
			break;
		case 25:
			sendNPCDialogue(Mandrith, Angry, "I'm not sure how you could confuse ME with anyone!");
			stage = -1;
			break;
		case 26:
			sendNPCDialogue(Mandrith, Thinking, "You wouldn't be willing to sell them to me?");
			stage = 27;
			break;
		case 27:
			sendOptionsDialogue("Select an option", "Sure I can do that", "Who are you?",
					"Oh, sorry, I thought you were someone else.", "No, sorry.");
			stage = 28;
			break;
		case 28:
			switch (componentId) {
			case OPTION_1:
				sendPlayerDialogue(Happy, "Sure I can do that.");
				stage = 29;
				break;
			case OPTION_2:
				sendPlayerDialogue(Thinking, "Who are you?");
				stage = 3;
				break;
			case OPTION_3:
				sendPlayerDialogue(Plain, "Oh sorry, I thought you were someone else.");
				stage = 25;
				break;
			case OPTION_4:
				sendPlayerDialogue(Plain, "No sorry.");
				stage = -1;
			}
			break;
		case 29:
			if (player.getMoneyPouch().cantAdd() && player.getInventory().containsItem(995, Integer.MAX_VALUE)) {
				sendNPCDialogue(Mandrith, Sad,
						"Sorry but it seems like you have max cash in both inventory and money pouch.");
				stage = -1;
			} else {
				WildernessArtefacts.trade(player);
				stage = -1;
			}
			break;
		case 30:
			player.getInterfaceManager().closeChatBoxInterface();
			player.getCustomStore().sendInterface(player, 0, 102);
			break;
		}

	}

}