package com.rs.game.player.content.quest.impl.goblindiplomacy.dialogues;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;
import com.rs.utils.Utils;

/**
 * @Author arrow
 * @Contact<arrowrsps@gmail.com;skype:arrowrsps>
 */
public class GeneralWartface extends Dialogue {

    public static final int BENTNOZE = 4493;

    @Override
    public int getNPCID() {
        return 4494;
    }


	@Override
	public void start() {
		stageInt = (Integer) parameters[0];
		int randomStage = Utils.random(0, 3);
		if (player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getState() == QuestState.COMPLETED) {
			player.sm(NPCDefinitions.getNPCDefinitions(getNPCID()).getName() + " is to busy to talk to you.");
			return;
		}
		if (player.getInventory().containsOneItem(286)) {
			sendPlayerChat(Mood.NORMAL, "I have some orange armour here.");
			stageInt = 100;
		} else {
			if (stageInt == 0) {
				stageInt = randomStage;
			}
			switch (stageInt) {
			case 0:
				sendNPCDialogue(BENTNOZE, ANGRY, "Green armour best.");
				stageInt = -1;
				break;
			case 1:
				sendNPCDialogue(BENTNOZE, ANGRY, "We should wear green amour!");
				stageInt = 50;
				break;
			case 2:
				sendNPCChat(Mood.ANGRY, "Red armour best.");
				stageInt = 60;
				break;
			case 3:
				sendNPCChat(Mood.ANGRY, "All goblins should wear red armour!");
				stageInt = 65;
				break;
			case 4:
				sendPlayerChat(Mood.NORMAL, "I have some orange armour here.");
				stageInt = 100;
				break;
			}
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		int progress = player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage();
		switch (stageInt) {
		case 100:
			sendNPCChat(Mood.ANGRY, "Excellent! Thank you human.");
			stageInt = 101;
			break;
		case 101:
			end();
			player.getInventory().deleteItem(286, 1);
			player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).finish();
			break;
		case -1:
			sendNPCChat(Mood.ANGRY, "No no red every time.");
			stageInt = 1;
			break;

		case 1:
			sendNPCDialogue(BENTNOZE, ANGRY, "Go away human, we busy.");
			stageInt = 2;
			break;

		case 2:
			if (progress > 0) {
				sendOptionsDialogue(DEFAULT, "Wouldn't you prefer peace?", "Where am I meant to get orange armour?");
				stageInt = 25;
			} else {
				sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Why are you arguing about the colour of your armour?",
						"Wouldn't you prefer peace?", "Do you want me to pick an armour colour for you?");
				stageInt = 3;
			}
			break;

		case 3:
			switch (componentId) {
			case OPTION_1:
				sendNPCChat(Mood.PLAIN_TALKING, "We decide to celebrate goblin new century by changing "
						+ "colour of our armour, brown get boring after a bit. " + " We want change.");
				stageInt = 17;
				break;
			case OPTION_2:
				sendNPCDialogue(BENTNOZE, PLAIN_TALKING,
						"Yeah peace is good as long as it peace wearing green " + "armour.");
				stageInt = 14;
				break;
			case OPTION_3:
				sendNPCDialogue(BENTNOZE, HAPPY, "Yes as long as you pick green.");
				stageInt = 4;
				break;
			}
			break;

		case 4:
			sendNPCChat(Mood.ANGRY, "No you have to pick red!");
			stageInt = 5;
			break;

		case 5:
			sendOptionsDialogue(DEFAULT, "You should wear red", "You should wear green",
					"What about a different colour?");
			stageInt = 6;
			break;

		case 6:
			switch (componentId) {
			case OPTION_1:
				sendNPCChat(Mood.PLAIN_TALKING, "See? Even stupid human think red best. Nowe we all" + " wear red!");
				stageInt = 7;
				break;
			case OPTION_2:
				sendNPCDialogue(BENTNOZE, HAPPY, "Green! We all wear green now, human has decided!");
				stageInt = 9;
				break;
			case OPTION_3:
				sendNPCChat(Mood.CONFUSED, "That would mean me wrong... but at least BENTNOZE " + " not right!");
				stageInt = 20;
				break;
			}
			break;

		case 7:
			sendNPCDialogue(BENTNOZE, ANGRY,
					"Human not know anything! If we wear red then whole " + "village be ugly like YOU!");
			stageInt = 8;
			break;

		case 8:
			sendNPCDialogue(BENTNOZE, PLAIN_TALKING, "Go away human. You not helping.");
			stageInt = -2;
			break;

		case 9:
			sendNPCChat(Mood.ASKING, "Why we have to do what human say? He not boss of" + " us!");
			stageInt = 10;
			break;

		case 10:
			sendNPCDialogue(BENTNOZE, ANGRY, "No but he agree with me!");
			stageInt = 11;
			break;

		case 11:
			sendNPCChat(Mood.MAD, "That prove you a filthy human-lover!");
			stageInt = 12;
			break;

		case 12:
			sendNPCDialogue(BENTNOZE, ANGRY, "Me hate humans! This human just happen to be right!");
			stageInt = 13;
			break;

		case 13:
			sendNPCChat(Mood.MILDLY_ANGRY, "Go away human. You not know anything.");
			stageInt = -2;
			break;

		case 14:
			sendNPCChat(Mood.PLAIN_TALKING, "But green too much like skin. Nearly make you look " + "naked!");
			stageInt = 15;
			break;

		case 15:
			if (progress > 0) {
				sendOptionsDialogue(DEFAULT, "Wouldn't you prefer peace?", "Where am I meant to get orange armour?");
				stageInt = 25;
			} else {
				sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Why are you arguing about the colour of your armour?",
						"Wouldn't you prefer peace?", "Do you want me to pick an armour colour for you?");
				stageInt = 3;
			}
			break;

		case 16:
			switch (componentId) {
			case OPTION_1:
				sendNPCChat(Mood.PLAIN_TALKING, "We decide to celebrate goblin new century by changing "
						+ "colour of our armour, brown get boring after a bit. " + " We want change.");
				stageInt = 17;
				break;
			case OPTION_2:
				sendOptionsDialogue(DEFAULT, "You should wear red", "You should wear green",
						"What about a different colour?");
				stageInt = 6;
				break;
			}
			break;

		case 17:
			sendNPCDialogue(BENTNOZE, PLAIN_TALKING, "Problem is they want different change to us.");
			stageInt = 18;
			break;

		case 18:
			sendOptionsDialogue(DEFAULT, "Wouldn't you prefer peace?",
					"Do you want me to pick an armour colour for you?");
			stageInt = 19;
			break;

		case 19:
			switch (componentId) {
			case OPTION_1:
				sendNPCDialogue(BENTNOZE, PLAIN_TALKING,
						"Yeah peace is good as long as it peace wearing green " + "armour.");
				stageInt = 14;
				break;
			case OPTION_2:
				sendOptionsDialogue(DEFAULT, "You should wear red", "You should wear green",
						"What about a different colour?");
				stageInt = 6;
				break;
			}
			break;

		case 20:
			sendNPCDialogue(BENTNOZE, CONFUSED,
					"Me dunno what that look like. Have to see armour " + "before we decide.");
			stageInt = 21;
			break;

		case 21:
			sendNPCChat(Mood.ASKING, "Human! You bring us armour in new colour!");
			stageInt = 22;
			break;

		case 22:
			sendNPCDialogue(BENTNOZE, ASKING, "What colour we try?");
			stageInt = 23;
			break;

		case 23:
			sendNPCChat(Mood.PLAIN_TALKING, "Orange armour might be good.");
			stageInt = 24;
			break;

		case 24:
			player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).sendStartOption();
			break;

		case 25:
			switch (componentId) {
			case OPTION_1:
				sendNPCDialogue(BENTNOZE, PLAIN_TALKING,
						"Yeah peace is good as long as it peace wearing green " + "armour.");
				stageInt = 14;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "You go north of here into wilderness. There you find " + "many ways to die!");
				stageInt = 31;
				break;
			}
			break;

		case 26:
			sendNPCDialogue(BENTNOZE, NORMAL, "...And then you dye it orange!");
			stageInt = 27;
			break;

		case 27:
			sendNPCChat(Mood.PLAIN_TALKING, "Even human should be able to work that out!");
			stageInt = 28;
			break;

		case 28:
			sendOptionsDialogue(DEFAULT, "But where do I get goblin armour?", "But where do I get dye?");
			stageInt = 29;
			break;

		case 29:
			switch (componentId) {
			case OPTION_1:
				sendNPCDialogue(BENTNOZE, NORMAL,
						"There some spare armour around village somewhere." + " You can take that.");
				stageInt = 30;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "You go north of here into wilderness. There you find " + "many ways to die!");
				stageInt = 31;
				break;
			}
			break;

		case 30:
			sendNPCChat(Mood.NORMAL, "It in crates somewhere. Can't remember which crates " + "now.");
			stageInt = -2;// TODO
			break;

		case 31:
			sendNPCDialogue(BENTNOZE, ASKING, "No, D-Y-E, not D-I-E.");
			stageInt = 32;
			break;

		case 32:
			sendNPCDialogue(BENTNOZE, PLAIN_TALKING, "Stupid Bentnoze, you not know how to spell!");
			stageInt = 33;
			break;

		case 33:
			sendNPCChat(Mood.ANGRY, "Shut up BENTNOZE!");
			stageInt = 34;
			break;

		case 34:
			sendPlayerChat(Mood.ASKING, "Do you know where I can get dye though?");
			stageInt = 35;
			break;

		case 35:
			sendNPCChat(Mood.CONFUSED, "Me not know where dye come from.");
			stageInt = 36;
			break;

		case 36:
			sendPlayerChat(Mood.ASKING, "Well where did you get your red and green dye from?");
			stageInt = 37;
			break;

		case 37:
			sendNPCDialogue(BENTNOZE, NORMAL,
					"Some goblin or other, he steal it. Say he steal it from " + "old witch in Draynor village.");
			stageInt = 38;
			break;

		case 38:
			sendNPCChat(Mood.ASKING, "Maybe you can get more dye from her?");
			stageInt = -2;
			break;

		case -2:
			end();
			break;

		/**
		 * Random Dialogues upon start.
		 */

		case 50:
			sendNPCChat(Mood.ASKING, "Green armour? Are you stupid?");
			stageInt = 51;
			break;

		case 51:
			sendNPCDialogue(BENTNOZE, ANGRY, "You stupid! Only stupid goblins think red armour " + "better!");
			stageInt = 52;
			break;

		case 52:
			sendNPCChat(Mood.ANGRY, "No they don't! Me think red armour better!");
			stageInt = 53;
			break;

		case 53:
			sendNPCDialogue(BENTNOZE, ANGRY, "That because you stupid!");
			stageInt = 54;
			break;

		case 54:
			sendNPCChat(Mood.MAD, "Me not stupid!");
			stageInt = 55;
			break;

		case 55:
			sendNPCDialogue(BENTNOZE, ASKING, "Then why you not like green armour?");
			stageInt = 56;
			break;

		case 56:
			sendNPCChat(Mood.ANGRY, "Because red armour better!");
			stageInt = 57;
			break;

		case 57:
			sendNPCDialogue(BENTNOZE, ANGRY, "Only stupid goblins think that! You stupid!");
			stageInt = 2;
			break;

		/*
		 * Another random dialogue...
		 */

		case 60:
			sendNPCDialogue(BENTNOZE, ANGRY, "No it has to be green!");
			stageInt = 61;
			break;

		case 61:
			sendNPCChat(Mood.PLAIN_TALKING, "Go away human, we busy.");
			stageInt = 2;
			break;

		/*
		 * And another...
		 */

		case 65:
			sendNPCDialogue(BENTNOZE, ANGRY, "No red! Red armour makes you look fat.");
			stageInt = 66;
			break;

		case 66:
			sendNPCChat(Mood.ANGRY, "Everything makes YOU look fat!");
			stageInt = 67;
			break;

		case 67:
			sendNPCDialogue(BENTNOZE, MAD, "Shut up!");
			stageInt = 68;
			break;

		case 68:
			sendNPCChat(Mood.ANGRY, "Fatty!");
			stageInt = 69;
			break;

		case 69:
			sendNPCDialogue(BENTNOZE, VERY_ANGRY_FACE, "SHUT UP!");
			stageInt = 70;
			break;

		case 70:
			sendNPCChat(Mood.ASKING, "Even this human think you look fat! Don't you, human?");
			stageInt = 71;
			break;

		case 71:
			sendNPCDialogue(BENTNOZE, CONFUSED, "Um...");
			stageInt = 72;
			break;

		case 72:
			sendOptionsDialogue(DEFAULT, "Yes, BENTNOZE looks fat", "No, he doesn't look fat");
			stageInt = 73;
			break;

		case 73:
			switch (componentId) {
			case OPTION_1:
				sendNPCChat(Mood.LAUGHING, "Ha ha! See, fatty? Even human think you fat!");
				stageInt = 74;
				break;
			case OPTION_2:
				sendNPCChat(Mood.ANGRY, "Shut up human! BENTNOZE fat and human stupid!");
				stageInt = 75;
				break;
			}
			break;

		case 74:
			sendNPCDialogue(BENTNOZE, ANGRY, "Me not care what human think! Human ugly!");
			stageInt = 2;
			break;

		case 75:
			sendNPCDialogue(BENTNOZE, ANGRY, "Shut up Bentnoze!");
			stageInt = 2;
			break;

		}
	}

	@Override
	public void finish() {

	}
}