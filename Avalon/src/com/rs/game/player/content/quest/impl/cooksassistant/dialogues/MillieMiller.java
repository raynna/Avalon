package com.rs.game.player.content.quest.impl.cooksassistant.dialogues;


import com.rs.game.player.dialogues.Dialogue;

/**
 * The dialogue for Millie the Miller in lumbridge,
 * Used during Cooks Assistant.
 * @author Ethan Kyle Millard
 * @version 1.0 3/1/2015
 */
public class MillieMiller extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, HAPPY_FACE, "Hello, adventurer. Welcome to Mill lane Mill. Can I help", "you?");
		stage = -1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			sendOptionsDialogue("Select an Option", "Who are you?", "What is this place?", "How do I mill flour?", "I'm fine, thanks.");
			stage = 1;
			break;
		case 1:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, HAPPY_FACE, "I'm Miss Millicent Miller, the Miller of Mill Lane Mill. Our", "family has been milling flour for generations.");
				stage = 2;
				break;
			case OPTION_2:
				sendNPCDialogue(npcId, HAPPY_FACE, "This is Mill Lane Mill, source of the finest flour in", "Arrow, and home to the Miller family for many", "generations.");
				stage = 4;
				break;
			case OPTION_3:
				sendNPCDialogue(npcId, HAPPY_FACE, "Making flour is pretty easy. First of all, you need to get", "some wheat. You can pick some from wheat fields. There", "is one just outside the mill, but there are many others", "scattered across Arrow.");
				stage = 4;
				break;
			case OPTION_4:
				end();
				break;
			}
			break;
		case 2:
			sendPlayerDialogue(ASKING_FACE, "Don't you ever get fed up with flour?");
			stage = 3;
			break;
		case 3:
			sendNPCDialogue(npcId, PLAIN_TALKING_FACE, "It's a good business to be in. People will always need flour.");
			stage = -1;
			break;
		case 4:
			sendNPCDialogue(npcId, HAPPY_FACE, "We take wheat from the field nearby and mill it into flour.");
			stage = -1;
			break;
		case 5:
			sendNPCDialogue(npcId, HAPPY_FACE, "Feel free to pick wheat from our field! There always", "seems to be plenty of wheat there.");
			stage = 6;
			break;
		case 6:
			sendPlayerDialogue(ASKING_FACE, "Then I bring my wheat here?");
			stage = 7;
			break;
		case 7:
			sendNPCDialogue(npcId, HAPPY_FACE, "Yes, or to one of the other mills in Arrow. They all", "work the same way.");
			stage = 8;
			break;
		case 8:
			sendNPCDialogue(npcId, HAPPY_FACE, "Just take your wheat up two levels to the top floor of the", "mill and place some into the hopper.");
			stage = 9;
			break;
		case 9:
			sendNPCDialogue(npcId, HAPPY_FACE, "Then you need to start the grinding process by pulling the", "level near the hopper. You can add more wheat, but each", "time you add wheat you'll have to pull the hopper lever", "again.");
			stage = 10;
			break;
		case 10:
			sendPlayerDialogue(ASKING_FACE, "So, where does the flour go then?");
			stage = 11;
			break;
		case 11:
			sendNPCDialogue(npcId, HAPPY_FACE, "The flour appears in this room here. You'll need an empty", "pot to put the flour into. One pot will hold the flour made", "by one load of wheat.");
			stage = 12;
			break;
		case 12:
			sendNPCDialogue(npcId, HAPPY_FACE, "You can buy empty pots from the general store in", "Lumbridge, south-east of here. You can also buy them", "from the Grand Exchange in Varrock or make them","yourself.");
			stage = 13;
			break;
		case 13:
			sendNPCDialogue(npcId, HAPPY_FACE, "That's all there is to it and you'll have a pot of flour.");
			stage = 14;
			break;
		case 14:
			sendPlayerDialogue(HAPPY_FACE, "Great! Thanks for your help.");
			stage = -1;
			break;
		}
	}

	@Override
	public void finish() {
		
	}

}
