package com.rs.game.player.dialogues.npcs;

import com.rs.Settings;
import com.rs.game.player.dialogues.Dialogue;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 24 Mar 2016
 *
 **/

public class TownCrier extends Dialogue {

	int npc = 6135;

	@Override
	public void start() {
		sendNPCDialogue(npc, Happy, "Hello! How may I help you?");
		stage = 1;

	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case END:
			end();
			break;
		case 1:
			sendOptions(TITLE, "What are you doing here?", "Can you tell me about the latest update?");
			stage = 2;
			break;
		case 2:
			switch (componentId) {
			case OPTION_1:
				sendPlayerDialogue(Thinking, "What are you doing here?");
				stage = 3;
				break;
			case OPTION_2:
				sendNPCDialogue(npc, Happy, Settings.LASTEST_UPDATE);
				stage = END;
				break;
			}
			break;
		case 3:
			sendNPCDialogue(npc, Happy,
					"I am here to let all the players know about the latest news, past updates and upcoming updates.");
			stage = 4;
			break;
		case 4:
			sendPlayerDialogue(Happy, "Oh okay! That sounds great.");
			stage = END;
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
