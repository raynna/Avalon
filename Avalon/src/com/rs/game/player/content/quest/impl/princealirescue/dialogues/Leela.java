package com.rs.game.player.content.quest.impl.princealirescue.dialogues;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.game.player.content.quest.impl.princealirescue.PrinceAliRescue;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;


/**
 * 
 * @author Frostbite
 *<email@frostbitersps@gmail.com><skype:frostbiterps>
 */
public class Leela extends Dialogue {

	@Override
	public int getNPCID() {
		return 915;
	}

	@Override
	public void start() {
		stageInt = (Integer) parameters[0];
		int progress = player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).getStage();
		QuestState state = player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).getState();
		player.getPackets().sendGameMessage("Quest: " + state + " " + progress);
		switch(state) {
		case NOT_STARTED:
			stageInt = 5;
			break;
		case STARTED:
			if(progress > 1)
				stageInt = 1;
			else 
				stageInt = 5;
			break;
		case COMPLETED:
			stageInt = 4;
			break;
		default:
			stageInt = 5;
			break;
		}
		switch(progress) {
		case 3:
			stageInt = 2;
			break;
		case 4:
		case 5:
		case 6:
		case 7:
			stageInt = 3;
			break;
		default:
			break;
		}

		switch(stageInt) {
		case 1:
			sendPlayerChat(Mood.NORMAL, "I am here to help you free the prince.");
			stageInt = -1;
			break;
		case 2:
			sendNPCChat(Mood.NORMAL, "My father sent this key for you. "
					+ "Be careful not to lose it.");
			stageInt = 16;
			break;
		case 3:
			if(!player.getInventory().containsOneItem(PrinceAliRescue.BRONZE_KEY)) {
				sendPlayerChat(Mood.SAD, "I'm afraid I lost that key you gave me.");
				stageInt = 18;
			} else 
				sendNPCChat(Mood.NORMAL, "Ok now, you have all the basic equipment. What are"
						+ " your plans to stop the guard interfering?");
			stageInt = 21;
			break;

		case 4:
			sendNPCChat(Mood.HAPPY, "Thank you, Al-Kharid will forever owe you for your", "help. I think that if there is every anything that needs to", "be done, you will be someone they can rely on.");
			stageInt = -2;
			break;

		case 5:
			sendNPCChat(Mood.NORMAL, "Please leave me alone adventurer, I am", "a very busy woman.");
			stageInt = -2;
			break;
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stageInt) {
		case -1:
			sendNPCChat(Mood.HAPPY, "Your employment is known to me. Now, do you know "
					+ "all that we need to make the break?");
			stageInt = 1;
			break;

		case 1:
			sendOptionsDialogue(DEFAULT, "I must make a disguise. What do you suggest?", "I need to get the key made.", "What can I do with the guards?",
					"I will go and get the rest of the escape equipment.");
			stageInt = 2;
			break;

		case 2:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.NORMAL, "Only the lady Keli, can wander about outside the jail." + 
						" The guards will shoot to kill if they see the prince out" + 
						" so we need a disguise good enough to fool them at a "
						+ "distance.");
				stageInt = 3;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "Yes, that is most important. There is no way you can "
						+ "get the real key. It is on a chain around Keli's neck. "
						+ "Almost impossible to steal.");
				stageInt = 9;
				break;
			case OPTION_3:
				sendNPCChat(Mood.NORMAL, "Most of the guards will be easy. The disgusie will get "
						+ "past them. The only guard who will be a problem will be "
						+ "the one at the door.");
				stageInt = 12;
				break;
			case OPTION_4:
				sendNPCChat(Mood.HAPPY, "Good, I shall wait your return with everything.");
				stageInt = -2;
				break;
			}
			break;

		case 3:
			if(player.getInventory().containsItem(PrinceAliRescue.DYED_WIG, 1)) {
				sendNPCChat(Mood.HAPPY, "The wig you have got, well done.");
				stageInt = 4;
			} else {
				sendNPCChat(Mood.NORMAL, "You need a wig, maybe from wool. If you find "
						+ "someone who can work with wool ask them about it. "
						+ "There's a witch nearby may be able to help you dye it.");
				stageInt = 4;
			}
			break;

		case 4:
			if(player.getInventory().containsItem(PrinceAliRescue.PINK_SKIRT, 1)) {
				sendNPCChat(Mood.HAPPY, "You have got the skirt, good.");
				stageInt = 5;
			} else {
				sendNPCChat(Mood.NORMAL, "You will need to get a pink skirt, same as Keli's.");
				stageInt = 5;
			}
			break;

		case 5:
			if(player.getInventory().containsItem(PrinceAliRescue.SKIN_PASTE, 1)) {
				sendNPCChat(Mood.HAPPY, "You have the skin paint, well done. I thought you would "
						+ "struggle to make that.");
				stageInt = 6;
			} else {
				sendNPCChat(Mood.NORMAL, "We still need something to colour the Prince's skin "
						+ "lighter. There's a witch close to here. She knows about "
						+ "many things. She may know some way to make the "
						+ "skin lighter.");
				stageInt = 6;
			}
			break;

		case 6:
			if(player.getInventory().containsItem(PrinceAliRescue.DYED_WIG, 1) ||
					player.getInventory().containsItem(PrinceAliRescue.PINK_SKIRT, 1) ||
					player.getInventory().containsItem(PrinceAliRescue.SKIN_PASTE, 1)) {
				sendNPCChat(Mood.HAPPY, "You do have everything for the disguise.");
				stageInt = 15;
			}
			if(player.getInventory().containsItem(PrinceAliRescue.ROPE, 1)) {
				sendNPCChat(Mood.HAPPY, "You have rope I see, to tie up Keli. That will be the "
						+ "most dangerious part of the plan.");
				stageInt = 7;
			} else {
				sendNPCChat(Mood.NORMAL, "You will still need some rope to tie up Keli, of course. I "
						+ "heard that there's a good rope maker around here.");
				stageInt = 7;
			}
			break;

		case 7:
			sendOptionsDialogue(DEFAULT, "I need to get the key made.", "What can I do with the guards?",
					"I will go and get the rest of the escape equipment.");
			stageInt = 8;
			break;

		case 8:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.NORMAL, "Yes, that is most important. There is no way you can "
						+ "get the real key. It is on a chain around Keli's neck. "
						+ "Almost impossible to steal.");
				stageInt = 9;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "Most of the guards will be easy. The disgusie will get "
						+ "past them. The only guard who will be a problem will be "
						+ "the one at the door.");
				stageInt = 12;
				break;
			case OPTION_3:
				sendNPCChat(Mood.HAPPY, "Good, I shall wait your return with everything.");
				stageInt = -2;
				break;
			}
			break;

		case 9:
			sendNPCChat(Mood.NORMAL, "Get some soft clay and get her to show you the key "
					+ "somehow. Then take the print, with bronze, to my "
					+ "father.");
			stageInt = 10;
			break;

		case 10:
			sendOptionsDialogue(DEFAULT, "I must make a disguise. What do you suggest?", "What can I do with the guards?", "I will go and get the rest of the escape equipment");
			stageInt = 11;
			break;

		case 11: 
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.NORMAL, "Only the lady Keli, can wander about outside the jail." + 
						" The guards will shoot to kill if they see the prince out" + 
						" so we need a disguise good enough to fool them at a "
						+ "distance.");
				stageInt = 3;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "Most of the guards will be easy. The disgusie will get "
						+ "past them. The only guard who will be a problem will be "
						+ "the one at the door.");
				stageInt = 12;
				break;
			case OPTION_3:
				sendNPCChat(Mood.HAPPY, "Good, I shall wait your return with everything.");
				stageInt = -2;
				break;
			}
			break;

		case 12:
			sendNPCChat(Mood.NORMAL, "We can discuss this more when you have the rest of "
					+ "the escape kit.");
			stageInt = 13;
			break;

		case 13:
			sendOptionsDialogue(DEFAULT, "I must make a disguise. What do you suggest?", "I need to get the key made.", "I will go and get the rest of the escape equipment.");
			stageInt = 14;
			break;

		case 14:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.NORMAL, "Only the lady Keli, can wander about outside the jail." + 
						" The guards will shoot to kill if they see the prince out" + 
						" so we need a disguise good enough to fool them at a "
						+ "distance.");
				stageInt = 3;
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "Yes, that is most important. There is no way you can "
						+ "get the real key. It is on a chain around Keli's neck. "
						+ "Almost impossible to steal.");
				stageInt = 9;
				break;
			case OPTION_3:
				sendNPCChat(Mood.HAPPY, "Good, I shall wait your return with everything.");
				stageInt = -2;
				break;
			}
			break;

		case 15:
			if(player.getInventory().containsItem(PrinceAliRescue.ROPE, 1)) {
				sendNPCChat(Mood.HAPPY, "You have rope I see, to tie up Keli. That will be the "
						+ "most dangerious part of the plan.");
				stageInt = 7;
			} else {
				sendNPCChat(Mood.NORMAL, "You will still need some rope to tie up Keli, of course. I "
						+ "heard that there's a good rope maker around here.");
				stageInt = 7;
			}
			break;

		case 16:
			if(player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(new Item(PrinceAliRescue.BRONZE_KEY));
			} else {
				World.addGroundItem(new Item(PrinceAliRescue.BRONZE_KEY), new WorldTile(player.getX(), player.getY(), player.getPlane()));
			}
			sendDialogue("Leela gives you a copy of the key to the prince's door.");
			player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).setStage(4);
			stageInt = 17;
			break;

		case 17:
			sendNPCChat(Mood.NORMAL, "Good, you have all the basic equipment. Next to deal "
					+ "with the guard on the door. He is talkative, try to find "
					+ "a weakness in him.");
			stageInt = -2;
			break;

		case 18:
			sendNPCChat(Mood.ANGRY_YELLING, "Foolish man!", "A new key will cost 15 coins.");
			stageInt = 19;
			break;

		case 19:
			if (player.getMoneyPouch().getTotal() >= 15) {
				sendNPCChat(Mood.NORMAL, "Here, I have 15 coins.");
				stageInt = 20;
			} else {
				end();
				player.getPackets().sendGameMessage("You don't have enough coins.");
			}
			break;

		case 20:
			if(player.getInventory().containsOneItem(PrinceAliRescue.BRONZE_KEY)) 
				return;
			if(player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(PrinceAliRescue.BRONZE_KEY, 1);
				player.getMoneyPouch().removeMoneyMisc(15);
				end();
				player.getPackets().sendGameMessage("Leela gives you another key.");
			}
			break;

		case 21:
			sendOptionsDialogue(DEFAULT, "I haven't spoken to him yet.", "I was going to attack him.", "I hoped to get him drunk.", "Maybe I could bribe him to leave.");
			stageInt = 22;
			break;

		case 22:
			switch(componentId) {
			case OPTION_1:
				sendPlayerChat(Mood.NORMAL, "I haven't spoken to him yet.");
				stageInt = 23;
				break;
			case OPTION_2:
				sendPlayerChat(Mood.NORMAL, "I was going to attack him.");
				stageInt = 24;
				break;
			case OPTION_3:
				sendNPCChat(Mood.NORMAL, "I hoped to get him drunk.");
				stageInt = 25;
				break;
			case OPTION_4:
				sendNPCChat(Mood.NORMAL, "Maybe I could bribe him to leave.");
				break;
			}
			break;

		case 23:
			sendNPCChat(Mood.NORMAL, "Well, speaking to him may find a weakness he has. See", "if there's something that could stop him bothering us.");
			stageInt = -2;
			break;

		case 24:
			sendNPCChat(Mood.NORMAL, "I don't thik you should. If you do the rest of the", "gang and Keli would attack you. The door guard", "should be removed first, to make it easy.");
			stageInt = -2;
			break;

		case 25:
			sendNPCChat(Mood.NORMAL, "Well, that's possible. These guards do like a drink. I", "would think it will take at least 3 beers to do it well.", "You would probably have to do it all at the same time", "too. The effects of the local bear off quickly.");
			stageInt = -2;
			break;

		case 26:
			sendNPCChat(Mood.NORMAL, "Maybe I could bribe him to leave.");
			stageInt = -2;
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
