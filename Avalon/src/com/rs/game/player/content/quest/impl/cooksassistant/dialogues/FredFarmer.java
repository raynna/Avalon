package com.rs.game.player.content.quest.impl.cooksassistant.dialogues;


import com.rs.game.item.Item;
import com.rs.game.player.dialogues.Dialogue;

/**
 * Fred the Farmer, growing a greener world.
 * @author Ethan Kyle Millard
 * @version 1.0 2/28/2015
 */
public class FredFarmer extends Dialogue {
	
	private static final int BLACK_BALL_WOOL = 15416;
	
	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		if (player.isHiredByFred() >= 1) {
			//Change to rs
			sendOptionsDialogue("Choose an Option:", "How many more balls of black wool do you need?", "Can you remind me how to get balls of wool?");
			stage = 25;
		} else {
			sendNPCDialogue(npcId, ANGRY_FACE, "What are you doing on my land? You're not the one who", "keeps leaving all my gates open and letting out all my", "chickens are you?");
			stage = 1;
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case 1:
			sendOptionsDialogue("Choose an Option:", "I'm looking for something to kill.", "I'm lost.", "I'm looking for work.");
			stage = 2;
			break;
		case 2:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, ANGRY_FACE, "What, on my land? Leave my livestock alone!");
				stage = 100;
				break;
			case OPTION_2:
				sendNPCDialogue(npcId, ASKING_FACE, "How can you be lost? Just follow the road east and south.", "You'll end up in Lumbridge fairly quickly.");
				stage = 100;
				break;
			case OPTION_3:
				sendNPCDialogue(npcId, HAPPY_FACE, "Oh? Well, I could do with a bit of help, since you're", "offering.");
				stage = 3;
				break;
			}
			break;
		case 3:
			sendNPCDialogue(npcId, PLAIN_TALKING_FACE, "I need to collect some black wool from my sheep and I'd", "be much obliged if you could shear them for me. While", "you're at it, spin the wool into ball for me too.");
			stage = 4;
			break;
		case 4:
			sendPlayerDialogue(ASKING_FACE, "Does it have to be black wool?");
			stage = 5;
			break;
		case 5:
			sendNPCDialogue(npcId, PLAIN_TALKING_FACE, "Has to be. I'm doing business with some guy after black", "clothing - something to do withblack looking 'cool'.");
			stage = 6;
			break;
		case 6:
			sendOptionsDialogue("Choose an Option:", "It takes all sorts, I suppose.", "Black clothing cool? I'm not sure that's true.");
			stage = 7;
			break;
		case 7:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, PLAIN_TALKING_FACE, "Indeed. So if you bring me twenty balls of black wool, I'm", "sure I could sort out some sort of payment.");
				stage = 10;
				break;
			case OPTION_2:
				sendPlayerDialogue(PLAIN_TALKING_FACE, "Black clothing cool? I'm not sure that's true.");
				stage = 8;
				break;
			}
			break;
		case 8:
			sendNPCDialogue(npcId, PLAIN_TALKING_FACE, "That's what I though, but I'm certainly not going to turn", "down the business. So if you bring me twenty balls of", "black wool, I'm sure I could sort out some sort of", "payment.");
			stage = 9;
			break;
		case 9:
			sendPlayerDialogue(ASKING_FACE, "So is this a quest?");
			stage = 10;
			break;
		case 10:
			sendNPCDialogue(npcId, THINKING_THEN_TALKING_FACE, "No, it isn't it's work.", "You do what I say, then you get paid.");
			stage = 11;
			break;
		case 11:
			sendOptionsDialogue("Choose an Option:", "That doesn't sounds very exciting.", "I'll take the job.");
			stage = 12;
			break;
		case 12:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, ANGRY_FACE, "Well, what do you expect if you ask a farmer?");
				stage = 100;
				break;
			case OPTION_2:
				sendPlayerDialogue(PLAIN_TALKING_FACE, "Good.. Hopefully, you'll be safe from. 'The Thing'! Do you", "actually know how to shear a sheep?");
				player.setHiredByFred(1);
				stage = 13;
				break;
			}
			break;
		case 13:
			sendOptionsDialogue("Choose an Option:", "Of course!", "Actually, no, I don't.");
			stage = 14;
			break;
		case 14:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, HAPPY_FACE, "Alright, please return when you have the wool.");
				stage = 100;
				break;
			case OPTION_2:
				sendNPCDialogue(npcId, HAPPY_FACE, "Well, you're halfway there already. You have some shears", "in your inventory. Just use those on a sheep to shear it.");
				stage = 15;
				break;
			}
			break;
		case 15:
			sendPlayerDialogue(PLAIN_TALKING_FACE, "That's all I have to do?");
			stage = 16;
			break;
		case 16:
			sendNPCDialogue(npcId, PLAIN_TALKING_FACE, "Well, once you've collected some wool you'll need to spin it", "into balls.");
			stage = 17;
			break;
		case 17:
			sendNPCDialogue(npcId, ASKING_FACE, "Do you know how to spin wool?");
			stage = 18;
			break;
		case 18:
			sendOptionsDialogue("Choose an Option:", "I don't know how to spin wool, sorry.", "I'm something of an expert, actually.");
			stage = 19;
			break;
		case 20:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, PLAIN_TALKING_FACE, "Don't worry, it's quite simple.");
				stage = 21;
				break;
			case OPTION_2:
				sendNPCDialogue(npcId, HAPPY_FACE, "Well, you're good to go then, please return soon.");
				stage = 100;
				break;
			}
			break;
		case 21:
			sendNPCDialogue(npcId, PLAIN_TALKING_FACE, "The nearest spinning wheel can be found on the first floor", "of Lumbridge Castle, south-east of here.");
			stage = 22;
			break;
		case 22:
			sendPlayerDialogue(HAPPY_FACE, "Thank you.");
			stage = 100;
			break;
		case 23:
			if (player.getInventory().containsItem(new Item(BLACK_BALL_WOOL, 20))) {
				sendPlayerDialogue(HAPPY_FACE, "Yes I have them right here.");
				stage = 24;
			} else {
				sendPlayerDialogue(HAPPY_FACE, "No I don't.");
				stage = 100;
			}
			break;
		case 24:
				if(player.getInventory().removeItems(new Item(BLACK_BALL_WOOL, 20)) && player.getInventory().hasFreeSlots()) {
					player.getInventory().addItem(new Item(995, 2000));
				} else {
					player.getMoneyPouch().addMoneyMisc(2000);
				}
				sendNPCDialogue(npcId, HAPPY_FACE, "Thank you here is your payment.");
				player.setHiredByFred(0);
				stage = 100;
			break;
		case 25:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, PLAIN_TALKING_FACE, "You need to collect 20 more balls of black wool.", "Do you have them?");
				stage = 23;
				break;
			case OPTION_2:
				sendNPCDialogue(npcId, HAPPY_FACE, "Sure. You need to shear sheep and then spin the wool on a spinning wheel.");
				stage = 100;
				break;
			}
			break;
		case 100:
			end();
			break;
		}
	}

	@Override
	public void finish() {
		
	}

}
