package com.rs.game.player.content.quest.impl.cooksassistant.dialogues;


import com.rs.Settings;
import com.rs.game.player.dialogues.Dialogue;

public class GillieGroats extends Dialogue {
	
	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, HAPPY, "Hello, I'm Gillie. What can I do for you?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			sendOptionsDialogue(DEFAULT, "Who are you?", "Can you tell me how to milk a cow?", "Can I buy milk off you?", "I'm fine, thanks.");
			stage = 0;
			break;
		case 0:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, HAPPY, "My name's Gillie Groats. My father is a farmer and I milk", "the cows for him.");
				stage = 1;
				break;
			case OPTION_2:
				sendNPCDialogue(npcId, HAPPY, "It's very easy. First, you need an empty bucket to hold", "the milk.");
				stage = 4;
				break;
			case OPTION_3:
				sendNPCDialogue(npcId, SAD, "I'm afraid not. My husmand has already taken all of our", "stock to the market.");
				stage = 9;
				break;
			case OPTION_4:
				end();
				break;
			}
			break;
		case 1:
			sendPlayerDialogue(CONFUSED, "Do you have any buckets of milk spare?");
			stage = 2;
			break;
		case 2:
			sendNPCDialogue(npcId, HAPPY, "I'm afraid not. We need all of our milk to sell to market,", "but you can milk the cow yourself if you need milk.");
			stage = 3;
			break;
		case 3:
			sendPlayerDialogue(CONFUSED, "Thanks.");
			stage = 10;
			break;
		case 4:
			sendNPCDialogue(npcId, HAPPY, "You can buy empty buckets from the general store in", "Lumbridge, south-west of here, or from most general", "stores in " + Settings.SERVER_NAME + ". You can also buy them from the", "Grand Exchange in Edgeville.");
			stage = 5;
			break;
		case 5:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Then find a dairy cow to milk - you can't milk just any", "cow.");
			stage = 6;
			break;
		case 6:
			sendPlayerDialogue(NORMAL, "How do I find a dairy cow?");
			stage = 7;
			break;
		case 7:
			sendNPCDialogue(npcId, HAPPY, "They are easy to spot - they have a cowbell around their", "neck and are tethered to a post to stop them wandering", "around all over the place. There are a couple in this field.");
			stage = 8;
			break;
		case 8:
			sendNPCDialogue(npcId, HAPPY, "Then you just need to use your bucket on the cow and", "you'll get some tasty, nutritious milk.");
			stage = 10;
			break;
		case 9:
			sendNPCDialogue(npcId, PLAIN_TALKING, "You could get some by milking the dairy cows yourself. If", "you would still rather buy it, you can probably get some at", "the Grand Exchange in Varrock, just north of here. A lot", "of adventurers sell their goods there.");
			stage = 10;
			break;
		case 10:
			end();
			break;
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}

}
