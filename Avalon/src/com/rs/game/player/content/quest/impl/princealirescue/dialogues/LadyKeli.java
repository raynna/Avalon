package com.rs.game.player.content.quest.impl.princealirescue.dialogues;

import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.game.player.content.quest.impl.princealirescue.PrinceAliRescue;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

/**
 * 
 * @author Frostbite
 *<email@frostbitersps@gmail.com><skype:frostbiterps>
 */

public class LadyKeli extends Dialogue {


	@Override
	public int getNPCID() {
		return 919;
	}


	@Override
	public void start() {
		stageInt = (Integer) parameters[0];
		int progress = player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).getStage();
		QuestState state = player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).getState();
		switch(state) {
		case COMPLETED:
			callGuards();
			break;

		case STARTED:
		case NOT_STARTED:
			if(progress < 6)
				stageInt = 1;
			break;
		}
		switch(progress) {
		case 7:
		case 8:
			callGuards();
			break;
		}

		switch(stageInt) {

		case 1:
			sendPlayerChat(Mood.ASKING, "Are you the famous Lady Keli? Leader of the toughest "
					+ "gang of mercenary killers around?");
			stageInt = 2;
			break;

		case -2:
			end();
			break;
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stageInt) {

		case 2:
			sendNPCChat(Mood.NORMAL, "I am Keli, you have heard of me then?");
			stageInt = 3;
			break;

		case 3:
			sendOptionsDialogue(DEFAULT, "Heard of you? You are famous in Runescape!", "I have heard a little, but I think Katrine is tougher.", 
					"I have heard rumours that you kill people.", "No I have never really heard of you.");
			stageInt = 4;
			break;

		case 4:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.HAPPY, "That's very kind of you to say. Reputations are "
						+ "not easily earned. I have managed to scceed "
						+ "where many fail.");
				stageInt = 27;
				break;
			case OPTION_2:
				sendNPCChat(Mood.ANGRY, "Well you can think that all you like. I know those "
						+ "blackarm cowards dare not leave the city. Out here, I "
						+ "am toughest. You can tell them that! Now get out of "
						+ "my sigh, before i call my guards.");
				stageInt = -2;
				break;
			case OPTION_3:
				sendNPCChat(Mood.LAUGHING, "There's always someone ready to spread rumours. I "
						+ "hear al sort of ridiculous things these days.");
				stageInt = 5;
				break;
			case OPTION_4:
				sendNPCChat(Mood.CONFUSED, "You must be new to this land then. EVERYONE "
						+ "knows of Lady Keli and her prowess with sword.");
				stageInt = 21;
				break;
			}
			break;

		case 5:
			sendOptionsDialogue(DEFAULT, "I have heard a little, but I think Katrine is tougher.", "What is your latest plan then?",
					"I have heard rumours that you kill people.", "No I have never really heard of you.");
			stageInt = 6;
			break;

		case 6:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.ANGRY, "Well you can think that all you like. I know those "
						+ "blackarm cowards dare not leave the city. Out here, I "
						+ "am toughest. You can tell them that! Now get out of "
						+ "my sigh, before i call my guards.");
				stageInt = -2;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "Well, I can tell you I have a valuable prisoner here "
						+ "in my cells.");
				stageInt = 7;
				break;
			case OPTION_3:
				sendNPCChat(Mood.CONFUSED, "You must be new to this land then. EVERYONE "
						+ "knows of Lady Keli and her prowess with sword.");
				stageInt = 21;
				break;
			case OPTION_4:
				sendNPCChat(Mood.CONFUSED, "You must be new to this land then. EVERYONE "
						+ "knows of Lady Keli and her prowess with sword.");
				stageInt = 21;
				break;
			}
			break;

		case 7:
			sendNPCChat(Mood.NORMAL, "I can expect a high reward to be paid very soon for "
					+ "this guy. I can't tell you who he is, but he is a lot "
					+ "colder now.");
			stageInt = 8;
			break;

		case 8:
			sendOptionsDialogue(DEFAULT, "Ah, I see. You must have been very skillful.", "That's great, are you sure the will pay?", 
					"Can you be sure they will not try to get him out?", "I should not disturb someone as tough as you.");
			stageInt = 9;
			break;

		case 9:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.NORMAL, "Yes, I did most of the work. We had to grab the Pr...");
				stageInt = 10;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "They will pay, or we will cut his hair off and send it to "
						+ "them.");
				stageInt = 13;
				break;
			case OPTION_3:
				sendNPCChat(Mood.NORMAL, "There is no way to release him. The only key to the "
						+ "door is on a chain around my neck and the locksmith "
						+ "who made the lock died suddenly When he had finished.");
				stageInt = 17;
				break;
			case OPTION_4:
				sendNPCChat(Mood.NORMAL, "I need to do alot of work, goodbye. When you get a "
						+ "little tougher, maybe I will give you a job.");
				stageInt = -2;
				break;
			}
			break;

		case 10:
			sendNPCChat(Mood.NORMAL, "Er, we had to grab him without his ten bodyguards "
					+ "noticing. It was a stroke of genius.");
			stageInt = 11;
			break;

		case 11:
			sendOptionsDialogue(DEFAULT, "Are you sure they will pay?", "Can you be sure they will not try to get him out?", "I should not disturb someone as tough as you.");
			stageInt = 12;
			break;

		case 12:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.NORMAL, "They will pay, or we will cut his hair off and send it to "
						+ "them.");
				stageInt = 13;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "There is no way to release him. The only key to the "
						+ "door is on a chain around my neck and the locksmith "
						+ "who made the lock died suddenly When he had finished.");
				stageInt = 17;
				break;
			case OPTION_3:
				sendNPCChat(Mood.NORMAL, "I need to do alot of work, goodbye. When you get a "
						+ "little tougher, maybe I will give you a job.");
				stageInt = -2;
				break;
			}
			break;

		case 13:
			sendPlayerChat(Mood.ASKING, "How about trying something tougher? Maybe cut his "
					+ "finger off?");
			stageInt = 14;
			break;

		case 14:
			sendNPCChat(Mood.HAPPY, "That's a good idea, I could use a talented people like you. "
					+ "I may call on you if I need work doing.");
			stageInt = 15;
			break;

		case 15:
			sendOptionsDialogue(DEFAULT, "You must have been very skillful.", "Can you be sure they will not try to get him out?", "I should not disturb someone as tough as you.");
			stageInt = 16;
			break;

		case 16:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.NORMAL, "Yes, I did most of the work. We had to grab the Pr...");
				stageInt = 10;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "There is no way to release him. The only key to the "
						+ "door is on a chain around my neck and the locksmith "
						+ "who made the lock died suddenly When he had finished.");
				stageInt = 17;
				break;
			case OPTION_3:
				sendNPCChat(Mood.NORMAL, "I need to do alot of work, goodbye. When you get a "
						+ "little tougher, maybe I will give you a job.");
				stageInt = -2;
				break;
			}
			break;

		case 17:
			sendNPCChat(Mood.NORMAL, "There is not another key in this world.");
			stageInt = 18;
			break;

		case 18:
			sendOptionsDialogue(DEFAULT, "Could I see the key please?", "That is a good way to keep secrets.", "I should not disturb someone as tough as you.");
			stageInt = 19;
			break;

		case 19:
			switch(componentId) {
			case OPTION_1:
				sendPlayerChat(Mood.ASKING, "Could I see the key please? Just for a moment. It "
						+ "would be something I can tell my grandchildren. When "
						+ "you are ven more famous than you are now.");
				stageInt = 29;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "It is the best way I know. Dead men tell no tales.");
				stageInt = 20;
				break;
			case OPTION_3:
				sendNPCChat(Mood.NORMAL, "I need to do alot of work, goodbye. When you get a "
						+ "little tougher, maybe I will give you a job.");
				stageInt = -2;
				break;
			}
			break;

		case 20:
			sendPlayerChat(Mood.NORMAL, "I am glad I know none of your secrets, Keli.");
			stageInt = -2;
			break;

		case 21:
			sendOptionsDialogue(DEFAULT, "No, still doesn't ring a bell.", "Yes, of course I have heard of you.", "You must have trained alot for this work.");
			stageInt = 22;
			break;

		case 22:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.NORMAL, "Well, you know of me now. I will wring your neck if "
						+ "you do not show respect.");
				stageInt = 23;
				break;
			case OPTION_2:
				sendPlayerChat(Mood.HAPPY, "Great Lady Keli, of course I have heard of you. "
						+ "You are famous in Runescape!");
				stageInt = 36;
				break;
			case OPTION_3:
				sendNPCChat(Mood.NORMAL, "I have used a sword since I was a small girl. I stabbed "
						+ "three people before I was 6 years old.");
				stageInt = 25;
				break;
			}
			break;

		case 23:
			sendOptionsDialogue(DEFAULT, "I do not show respect to killers and hoodlums.", "You must have trained a lot for this work.", "I should not disturb someone as tough as you, great lady.");
			stageInt = 24;
			break;

		case 24:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.MAD, "You should, you really should. I am wealthy enough to "
						+ "place a bounty on your head. Or just remove your "
						+ "head my self. Now go, I am busy, too busy to fight a "
						+ "would-be hoodlum.");
				stageInt = -2;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "I have used a sword since I was a small girl. I stabbed "
						+ "three people before I was 6 years old.");
				stageInt = 25;
				break;
			case OPTION_3:
				sendNPCChat(Mood.NORMAL, "I need to do alot of work, goodbye. When you get a "
						+ "little tougher, maybe I will give you a job.");
				stageInt = -2;
				break;
			}
			break;

		case 25:
			sendOptionsDialogue(DEFAULT, "What is your latest plan then?", "You must have trained alot for this work.", "I think Katrine is still tougher.", "I should not disturb someone as tough as you.");
			stageInt = 26;
			break;

		case 26:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.NORMAL, "Well, I can tell you I have a valuable prisoner here "
						+ "in my cells.");
				stageInt = 7;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "I have used a sword since I was a small girl. I stabbed "
						+ "three people before I was 6 years old.");
				stageInt = 25;
				break;
			case OPTION_3:
				sendNPCChat(Mood.ANGRY, "Well you can think that all you like. I know those "
						+ "blackarm cowards dare not leave the city. Out here, I "
						+ "am toughest. You can tell them that! Now get out of "
						+ "my sigh, before i call my guards.");
				stageInt = -2;
				break;
			case OPTION_4:
				sendNPCChat(Mood.NORMAL, "I need to do alot of work, goodbye. When you get a "
						+ "little tougher, maybe I will give you a job.");
				stageInt = -2;
				break;
			}
			break;

		case 27:
			sendOptionsDialogue(DEFAULT, "I think Katrine is still tougher.", "What is your latest plan then?", "You must have trained a lot for this work.", 
					"I should not disturb someone as tough as you.");
			stageInt = 28;
			break;

		case 28:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.ANGRY, "Well you can think that all you like. I know those "
						+ "blackarm cowards dare not leave the city. Out here, I "
						+ "am toughest. You can tell them that! Now get out of "
						+ "my sigh, before i call my guards.");
				stageInt = -2;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "Well, I can tell you I have a valuable prisoner here "
						+ "in my cells.");
				stageInt = 7;
				break;
			case OPTION_3:
				sendNPCChat(Mood.NORMAL, "I have used a sword since I was a small girl. I stabbed "
						+ "three people before I was 6 years old.");
				stageInt = 25;
				break;
			case OPTION_4:
				sendNPCChat(Mood.NORMAL, "I need to do alot of work, goodbye. When you get a "
						+ "little tougher, maybe I will give you a job.");
				stageInt = -2;
				break;
			}
			break;

		case 29:
			sendNPCChat(Mood.NORMAL, "As you put it that way I am sure you can see it. You "
					+ "cannot steal the key, it is on a Runite chain.");
			stageInt = 30;
			break;

		case 30:
			sendDialogue("Keli shows you a small key on a strong looking chain.");
			stageInt = 31;
			break;

		case 31:
			sendOptionsDialogue(DEFAULT, "Could I touch the key for a moment please?", "I should not disturb someone as tough as you.");
			stageInt = 32;
			break;

		case 32:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.NORMAL, "Only for a moment then.");
				stageInt = 33;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "I need to do alot of work, goodbye. When you get a "
						+ "little tougher, maybe I will give you a job.");
				stageInt = -2;
				break;
			}
			break;

		case 33:
			if(player.getInventory().containsItem(PrinceAliRescue.SOFT_CLAY, 1)) {
				sendDialogue("You put a piece of soft clay in your hand. As you touch the "
						+ "key, you take an imprint of it.");
				if(!player.getInventory().containsOneItem(PrinceAliRescue.KEY_PRINT)) {
					player.getInventory().deleteItem(PrinceAliRescue.SOFT_CLAY, 1);
					if(player.getInventory().getFreeSlots() > 0) {
						player.getInventory().addItem(PrinceAliRescue.KEY_PRINT, 1);
					} else {
						World.addGroundItem(new Item(PrinceAliRescue.KEY_PRINT), new WorldTile(player.getX(), player.getY(), player.getPlane()));
					}
				}
				stageInt = 34;
			} else {
				sendPlayerChat(Mood.HAPPY, "Thank you so much, you are too kind, o great Keli.");
				stageInt = 35;
			}
			break;

		case 34:
			sendPlayerChat(Mood.HAPPY, "Thank you so much, you are too kind, o great Keli.");
			stageInt = 35;
			break;

		case 35:
			sendNPCChat(Mood.HAPPY, "You are welcome, run along, I am very busy.");
			stageInt = -2;
			break;

		case 36:
			sendNPCChat(Mood.HAPPY, "That's very kind of you to say. Reputations are "
					+ "not easily earned. I have managed to succeed "
					+ "where many fail.");
			stageInt = 27;
			break;

		case -2:
			end();
			break;

		}
	}

	public void callGuards() {
		for (final NPC n : World.getNPCs()) {
			if (n == null || n.getId() != 919)
				continue;
			n.setNextForceTalk(new ForceTalk("You tricked me, and tied me up, Guards kill this stranger!"));
			end();
		}
		/**
		 * Represents Guard
		 */
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				for (final NPC n : World.getNPCs()) {
					if (n == null || n.getId() != 917)
						continue;
					n.setNextForceTalk(new ForceTalk("Yes M'lady"));
					n.getCombat().setTarget(player);
				}
			}
		}, 1);
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
