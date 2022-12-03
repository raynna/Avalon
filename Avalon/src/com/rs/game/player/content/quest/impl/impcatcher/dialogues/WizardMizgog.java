package com.rs.game.player.content.quest.impl.impcatcher.dialogues;


import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;

public class WizardMizgog extends Dialogue {
	
	/**
	 * Represents the black bead item.
	 */
	private static final int BLACK_BEAD = 1474;

	/**
	 * Represents the red bead item.
	 */
	private static final int RED_BEAD = 1470;

	/**
	 * Represents the white bead item.
	 */
	@SuppressWarnings("unused")
	private static final int WHITE_BEAD = 1476;

	/**
	 * Represents the yellow bead item.
	 */
	private static final int YELLOW_BEAD = 1472;
	
	@Override
	public int getNPCID() {
		return 706;
	}


	@Override
	public void start() {
		stageInt = (Integer) parameters[0];
		int progress = player.getQuestManager().get(Quests.IMP_CATCHER).getStage();
		/*
		 * Starting Quest If Not Done
		 */
		if (stageInt == 0 && progress == 0) {
			sendPlayerChat(Mood.NORMAL, "Give me a quest!");
			stageInt = -1;
		} 
		if (progress == 1) {
			sendNPCChat(Mood.NORMAL, "So how are you doing finding my beads?");
			stageInt = 16;
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stageInt) {
		case -1:
			sendNPCChat(Mood.NORMAL, "Give me a quest what?");
			stageInt = 1;
			break;
		case 1:
			sendOptionsDialogue(DEFAULT, "Give me a quest please.", "Give me a quest or else!", "Just stop messing around and give me a quest!");
			stageInt = 2;
			break;
		case 2:
			switch(componentId) {
			case OPTION_1:
				sendPlayerChat(Mood.NORMAL, "Give me a quest please.");
				stageInt = 3;
				break;
			case OPTION_2:
				sendPlayerChat(Mood.MAD, "Give me a quest or else!");
				stageInt = 10;
				break;
			case OPTION_3:
				sendPlayerChat(Mood.MAD, "Just stop messing around and give me a quest!");
				stageInt = 12;
				break;
			}
			break;
			
		/****************************************************************************************************************************************************/	
			
		/*First Option*/
		case 3:
			sendNPCChat(Mood.NORMAL, "Well seeing as you asked nicely... I could do with some", "help.");
			stageInt = 4;
			break;
		case 4:
			sendNPCChat(Mood.NORMAL, "The wizard grayzag next door decided he didn't like", "me so he enlisted an army of hundreds of imps.");
			stageInt = 5;
			break;
		case 5:
			sendNPCChat(Mood.NORMAL, "These imps stole all sorts of my things. Most of these", "things I don't really care about, just eggs and balls of", "string and things.");
			stageInt = 6;
			break;
		case 6:
			sendNPCChat(Mood.NORMAL, "But they stole my four magical beads. There was a red", "one, a yellow one, a black one, and a white one.");
			stageInt = 7;
			break;
		case 7:
			sendNPCChat(Mood.NORMAL, "These imps have now spread out all over the kingdom.", "Could you get my beads back for me?");
			stageInt = 8;
			break;
		case 8:
			sendOptionsDialogue(DEFAULT, "I'll try.", "I've better things to do than chase imps.");
			stageInt = 9;
			break;
		case 9:
			switch(componentId) {
			/*Accepting Quest - (Imp Catcher)*/	
			case OPTION_1:
				sendPlayerChat(Mood.NORMAL, "I'll try.");
				stageInt = 13;
				break;
			/*Decling Quest - (Imp Catcher)*/	
			case OPTION_2:
				sendPlayerChat(Mood.NORMAL, "I've better things to do than chase imps.");
				stageInt = 15;
				break;
			}
			break;
			
	/****************************************************************************************************************************************************/
			
		/*Second Option*/
		case 10:
			sendNPCChat(Mood.NORMAL, "Or else what? You'll attack me?");
			stageInt = 11;
			break;
		case 11:
			sendNPCChat(Mood.LAUGHING, "Hahaha!");
			stageInt = -2;
			break;
			
	/****************************************************************************************************************************************************/
			
		/*Third Option*/
		case 12:
			sendNPCChat(Mood.NORMAL, "Ah now you're assuming I have one to give.");
			stageInt = -2;
			break;
			
	/****************************************************************************************************************************************************/			
		
		case 13:
			sendNPCChat(Mood.HAPPY, "That's great, thank you.");
			stageInt = 14;
			break;
		case 14:
			player.getQuestManager().get(Quests.IMP_CATCHER).setState(QuestState.STARTED);
	        player.getQuestManager().get(Quests.IMP_CATCHER).setStage(1);
	        player.getQuestManager().update();
			end();
			break;
		case 15:
			sendNPCChat(Mood.NORMAL, "Well if you're not interested in the quests I have to", "give you, don't waste my time by asking me for them.");
			stageInt = -2;
			break;
			
	/****************************************************************************************************************************************************/		
		
		/*Quest - (Imp Catcher) - State: In Progress*/
		case 16:
			if (player.getInventory().containsItem(BLACK_BEAD, 1) && player.getInventory().containsItem(RED_BEAD, 1) && player.getInventory().containsItem(RED_BEAD, 1) && player.getInventory().containsItem(YELLOW_BEAD, 1)) {
				sendPlayerChat(Mood.NORMAL, "");
			} else {
				sendPlayerChat(Mood.NORMAL, "I've not found all of them yet.");
				stageInt = 17;
			}
			break;
		case 17:
			sendNPCChat(Mood.NORMAL, "Well get on with it. I've lost a white bead, a red bead, a", "black bead, and a yellow bead. Go kill some imps!");
			stageInt = -2;
			break;
			
		/*Closing Dialogue*/	
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
