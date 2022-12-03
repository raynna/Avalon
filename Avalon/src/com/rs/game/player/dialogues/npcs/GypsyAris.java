package com.rs.game.player.dialogues.npcs;

import com.rs.Settings;
import com.rs.game.player.dialogues.Dialogue;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 22 Apr 2016
 *
 **/
public class GypsyAris extends Dialogue {

	int npc;

	@Override
	public void start() {
		npc = (Integer) parameters[0];
		//if (player.getQuestManager().get(Quests.RECIPE_FOR_DISASTER).getStage() == Integer.MAX_VALUE) {
		//	sendNPCDialogue(npc, Happy, "Thank you so much for helping me " + player.getDisplayName() + "!");
		//	stage = END;
		//} else if (player.getQuestManager().get(Quests.RECIPE_FOR_DISASTER).getStage() == 1) {
		//	sendNPCDialogue(npc, Happy, "You still got some bosses to go! Enter the portal and end it!");
		//	stage = END;
			sendNPCDialogue(npc, Sad, "Please help!");
			stage = 1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case END:
			end();
			break;
		case 1:
			sendPlayerDialogue(Thinking, "What is wrong Gypsy?");
			stage = 2;
			break;
		case 2:
			sendNPCDialogue(npc, Sad, "The evil Culinaromancer is taking over!");
			stage = 3;
			break;
		case 3:
			sendPlayerDialogue(Thinking, "Taking over what?");
			stage = 4;
			break;
		case 4:
			sendNPCDialogue(npc, Plain, "The world of " + Settings.SERVER_NAME
					+ "! Please help me defeat this monster, you will be rewarded with special gloves!");
			stage = 5;
			break;
		case 5:
			end();
			//player.getQuestManager().get(Quests.RECIPE_FOR_DISASTER).sendStartOption();
			break;
		}
	}

	@Override
	public void finish() {

	}

}
