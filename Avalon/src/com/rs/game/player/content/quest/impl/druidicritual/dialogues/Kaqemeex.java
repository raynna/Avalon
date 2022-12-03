package com.rs.game.player.content.quest.impl.druidicritual.dialogues;

import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;

public class Kaqemeex extends Dialogue {

	private int npcId;

	@Override
	public int getNPCID() {
		return 455;
	}

	@Override
	public void start() {
		stageInt = (Integer) parameters[0];

		int qStage = player.getQuestManager().get(Quests.DRUIDIC_RITUAL).getStage();
		//System.out.println(qStage);
		if (qStage == 1) {
			sendPlayerChat(Mood.NORMAL, "Hello there.");
			stageInt = 50;
		} else if (qStage == 20) {
			sendPlayerChat(Mood.NORMAL, "Hello there.");
			stageInt = 50;
		} else if (qStage == 99) {
			sendPlayerChat(Mood.NORMAL, "Hello there.");
			stageInt = 51;
		} else if (qStage == 100) {
			player.getPackets().sendGameMessage("Kaqemeex seems very busy.");
		} else {
			switch (stageInt) {
			/*
			 * Default.
			 */
			case 0:
				sendPlayerChat(Mood.NORMAL, "Hello there.");
				stageInt = -1;
				break;
			/*
			 * Decline
			 */
			case -4:
				sendPlayerChat(Mood.ASKING, "No, that doesn't sound very interesting.");
				stageInt = 39;
				break;
			/*
			 * Accept.
			 */
			case -3:
				sendNPCChat(Mood.HAPPY, "Excellent. Go to the village south of this place and speak",
						"to my fellow Sanfew who is who is working on the purification",
						"ritual. He knows better than I what is required to", "complete it.");
				stageInt = 46;
				break;
			}
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stageInt) {
		case -1:
			sendNPCChat(Mood.ASKING, "What brings you to our holy monument?");
			stageInt = 0;
			break;
		case 0:
			sendOptionsDialogue(DEFAULT, "Who are you?", "I'm in search of a quest.", "Did you build this?");
			stageInt = 1;
			break;
		case 1:
			switch (componentId) {
			case OPTION_1:
				sendNPCChat(Mood.NORMAL, "We are the druids of Guthix. We worship our god at our",
						"famous stone circle.");
				stageInt = 13;
				break;
			case OPTION_2:
				sendPlayerChat(Mood.NORMAL, "I'm in search of a quest.");
				stageInt = 2;
				break;
			case OPTION_3:
				sendNPCChat(Mood.NORMAL, "What, personally? No, of course I didn't. However, our",
						"forfathers did. The first druids of Guthix built many",
						"stone circles across these lands over eight hundred years", "ago.");
				stageInt = 32;
				break;
			}
			break;
		case 2:
			sendNPCChat(Mood.HAPPY, "Hmm. I think I may have a worthwhile quest for you",
					"actually. I don't know if you are familiar with the stone",
					"circle south of Varrock or not, but...");
			stageInt = 35;
			break;
		case 13:
			sendOptionsDialogue(DEFAULT, "What about the stone circle full of dark wizards?",
					"So what's so good about guthix?", "Well, I'll be on my way now.");
			stageInt = 14;
			break;
		case 14:
			switch (componentId) {
			case OPTION_1:
				sendNPCChat(Mood.NORMAL, "What about the stone circle full of dark wizards?");
				stageInt = 35;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "So what's so good about guthix?");
				stageInt = 41;
				break;
			case OPTION_3:
				sendNPCChat(Mood.NORMAL, "Well, I'll be on my way now.");
				stageInt = 40;
				break;
			}
			break;
		case 15:
			sendPlayerDialogue(NORMAL, "But doesnt that make things confusing? How do you get", "anything done?");
			stageInt = 16;
			break;
		case 16:
			sendNPCChat(Mood.NORMAL, "While it may be confusing to the uninitiated like yourself,",
					"it works quite well in practice.");
			stageInt = 17;
			break;
		case 17:
			sendNPCChat(Mood.NORMAL, "Consider the mighty oak tree...");
			stageInt = 18;
			break;
		case 18:
			sendPlayerDialogue(NORMAL, "The oak tree?");
			stageInt = 19;
			break;
		case 19:
			sendNPCChat(Mood.NORMAL, "Exactly. he tree is strong only because the rains",
					"replenish it and the earth nourishes it.");
			stageInt = 20;
			break;
		case 20:
			sendNPCChat(Mood.NORMAL, "If the earth was dry, it would not grow, and in turn would",
					"not support the insects, the birds, the squirrels...");
			stageInt = 21;
			break;
		case 21:
			sendPlayerDialogue(NORMAL, "The lumberjacks?");
			stageInt = 22;
			break;
		case 22:
			sendNPCDialogue(npcId, CONFUSED, "Well..in a way you are right...as the felling of the trees",
					"opens space for fresh growth.");
			stageInt = 23;
			break;
		case 23:
			sendNPCChat(Mood.NORMAL, "I feel this description is getting a little away from me.");
			stageInt = 24;
			break;
		case 24:
			sendNPCChat(Mood.NORMAL, "In the end, if I issue an order it will be obeyed only if my",
					"peers feel that it is a worthy order to follow.");
			stageInt = 25;
			break;
		case 25:
			sendOptionsDialogue(DEFAULT, "Who are you?", "I'm in search of a quest.", "Did you build this?");
			stageInt = 26;
			break;
		case 26:
			switch (componentId) {
			case OPTION_1:
				sendNPCChat(Mood.NORMAL, "We are the druids of Guthix. We worship our god at our",
						"famous stone circle.");
				stageInt = 13;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "I have a worthwhile quest for you, actually. You have",
						"about you the air of a wandering hero, and I sense you", "can aid us.");
				stageInt = 2;
				break;
			case OPTION_3:
				sendNPCChat(Mood.NORMAL, "What, personally? No, of course I didn't. However, our",
						"forfathers did. The first druids of Guthix built many",
						"stone circles across these lands over eight hundred years", "ago.");
				stageInt = 32;
				break;
			}
			break;
		case 27:
			sendPlayerDialogue(HAPPY, "Cakemix? Your name is Cakemix?");
			stageInt = 28;
			break;
		case 28:
			sendNPCDialogue(npcId, CONFUSED, "No...My name is Kaqemeex. Ka-Q-e-ME-ex. The emphasis",
					"is on the 'q' and the 'me'. I can't understand how so many", "people get that wrong.");
			stageInt = 29;
			break;
		case 29:
			sendNPCChat(Mood.NORMAL, "Tell me, why are you trying not to laugh?");
			stageInt = 30;
			break;
		case 30:
			sendPlayerDialogue(HAPPY, "I'm not! I just...thought I was going to sneeze is all.");
			stageInt = 31;
			break;
		case 31:
			sendNPCChat(Mood.NORMAL, "The woods are in bloom; maybe you have hay fever. I",
					"think we have a potion for that somewhere.");
			stageInt = 25;
			break;
		case 32:
			sendNPCDialogue(npcId, SAD, "Unfortunately, we only know of two remaining, and of",
					"those only one is usable by us.");
			stageInt = 33;
			break;
		case 33:
			sendOptionsDialogue(DEFAULT, "What about the stone circle full of dark wizards?",
					"I'm in search of a quest.", "Well, I'll be on my way now.");
			stageInt = 34;
			break;
		case 34:
			switch (componentId) {
			case OPTION_1:
				sendPlayerChat(Mood.ASKING, "What about the stone circle full of dark wizards?");
				stageInt = 35;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "Goodbye adventurer. I forsee we shall meet again.");
				stageInt = 50;
				break;
			case OPTION_3:
				sendPlayerChat(Mood.NORMAL, "Well, I'll be on my way now.");
				stageInt = 40;
				break;
			}
			break;
		case 35:
			sendNPCChat(Mood.ANGRY, "That used to be OUR stone circle. Unfortunately,",
					"many many years ago, dark wizards cast a wicked spell",
					"upon it so that they could corrupt its power for their", "own evil ends.");
			stageInt = 36;
			break;
		case 36:
			sendNPCChat(Mood.NORMAL, "When they cursed the rocks for their rituals they made",
					"them useless to us and our magics.", "We require a brave",
					"adventurer to go on a quest for us to help purify the", "circle of Varrock.");
			stageInt = 37;
			break;
		case 37:
			sendOptionsDialogue(DEFAULT, "Okay, I will try and help.", "No, that doesn't sound very interesting.");
			stageInt = 38;
			break;
		case 38:
			switch (componentId) {
			case OPTION_1:
				/*
				 * Add the Quest start here
				 */
				player.getQuestManager().get(Quests.DRUIDIC_RITUAL).sendStartOption();
				end();
				break;
			case OPTION_2:
				sendPlayerChat(Mood.ASKING, "No, that doesn't sound very interesting.");
				stageInt = 39;
				break;
			}
			break;
		case 39:
			sendNPCChat(Mood.NORMAL, "I will not try and change your mind adventurer. Some",
					"day when you have matured you may reconsider your", "position. We will wait until then.");
			stageInt = -2;
			break;
		case 40:
			sendNPCChat(Mood.NORMAL, "Goodbye adventurer. I feel we shall meet again.");
			stageInt = -2;
			break;
		case 41:
			sendNPCChat(Mood.NORMAL, "Guthix is the oldest and most powerful god in FrostBlades. His",
					"existence is vital to this world. He is the god of balance,", "and nature.");
			stageInt = 42;
			break;
		case 42:
			sendNPCChat(Mood.NORMAL, "He exsists in the trees, and the flowers, the water and",
					"the rocks. He is everywhere. His purpose is to ensure",
					"balance in everything in this world, and as such we", "worship him.");
			stageInt = 43;
			break;
		case 43:
			sendPlayerChat(Mood.NORMAL, "He sounds kind of boring...");
			stageInt = 44;
			break;
		case 44:
			sendNPCChat(Mood.NORMAL, "Some day when your mind achieves enlightenment you",
					"will see the true beauty of his power.");
			stageInt = -2;
			break;
		case 45:
			sendNPCChat(Mood.HAPPY, "Excellent. Go to the village south of this place and speak",
					"to my fellow Sanfew who is who is working on the purification",
					"ritual. He knows better than I what is required to", "complete it.");
			stageInt = 46;
			break;
		case 46:
			sendPlayerChat(Mood.HAPPY, "Will do.");
			stageInt = -2;
			break;
		case 50:
			sendNPCChat(Mood.NORMAL, "You will need to speak to my fellow druid Sanfew in",
					"the village south of here to continue in your quest", "adventurer.");
			stageInt = -2;
			break;
		case 51:
			sendNPCChat(Mood.NORMAL, "I have word from Sanfew that you have been very",
					"helpful in assisting him with his preparations for the",
					"purification ritual. As promised I will now teach you the", "ancient arts of Herblore.");
			stageInt = 52;
			break;
		case 52:
			sendNPCChat(Mood.HAPPY, "Herblore is the skill of working with herbs and other",
					"ingredients, to make useful potions and poison.");
			stageInt = 53;
			break;
		case 53:
			sendNPCChat(Mood.NORMAL, "First you will need a vial, which can be found or made",
					"with the crafting skill.");
			stageInt = 54;
			break;
		case 54:
			sendNPCChat(Mood.NORMAL, "Then you must gather the herbs needed to make the", "potion you want.");
			stageInt = 55;
			break;
		case 55:
			sendNPCChat(Mood.NORMAL, "Refer to the Council's instructions in the Skills section",
					"of the website for the items needed to make a particular", "kind of potion.");
			stageInt = 56;
			break;
		case 56:
			sendNPCChat(Mood.NORMAL, "You must fill your vial with water and add the",
					"ingredients you need. There are normally 2 ingredients", "to each type of potion.");
			stageInt = 57;
			break;
		case 57:
			sendNPCChat(Mood.NORMAL, "Bear in mind you must first identify each herb, to see", "what it is.");
			stageInt = 58;
			break;
		case 58:
			sendNPCChat(Mood.NORMAL, "You may also have to grind some herbs before you can",
					"use them. You will need a pestle and mortar in order", "to do this.");
			stageInt = 59;
			break;
		case 59:
			sendNPCChat(Mood.NORMAL, "Herbs can be found on the ground, and are also",
					"dropped by some monsters when you kill them.");
			stageInt = 60;
			break;
		case 60:
			sendNPCChat(Mood.NORMAL, "Mix these in your water-filled vial, and you will produce", "an Attack potion.");
			stageInt = 61;
			break;
		case 61:
			sendNPCChat(Mood.NORMAL, "Drink this poition to increase your Attack level.");
			stageInt = 62;
			break;
		case 62:
			sendNPCChat(Mood.NORMAL, "Different potions also require different Herblore levels",
					"before you can make them.");
			stageInt = 63;
			break;
		case 63:
			sendNPCChat(Mood.NORMAL, "Once again, check the instructions found on the",
					"Council's website for the levels needed to make a", "particulur potion.");
			stageInt = 64;
			break;
		case 64:
			sendNPCChat(Mood.NORMAL, "Good luck with your Herblore practices, Good day", "Adventurer.");
			/*
			 * Send the quest complete here.
			 */
			stageInt = 65;
			break;
		case 65:
			sendPlayerChat(Mood.NORMAL, "Thanks for your help.");
			stageInt = 100;
			break;
		case 100:
			player.getQuestManager().get(Quests.DRUIDIC_RITUAL).setStage(100);
			player.getQuestManager().get(Quests.DRUIDIC_RITUAL).finish();
			end();
			break;
		case -2:
			end();
			break;
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
