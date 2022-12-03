package com.rs.game.player.dialogues.npcs;

import com.rs.game.player.dialogues.Dialogue;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Rain
 * @Date - 11 Mar 2016
 *
 **/
public class Giles extends Dialogue {

	int Giles = 8635;

	@Override
	public void start() {
		if (!player.spokenWithGiles) {
			sendPlayerDialogue(Thinking, "Giles...? What are you doing here?");
			stage = 1;
		} else {
			sendNPCDialogue(Giles, Happy, "Ah, You're back! What can I do for you?");
			stage = 5;
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case END:
			end();
			break;
		case 1:
			sendNPCDialogue(Giles, Happy, "Wow! " + player.getDisplayName() + "! It's been a while. ");
			stage = 2;
			break;
		case 2:
			sendPlayerDialogue(Unsure, "Yeah! So what are you doing here?");
			stage = 3;
			break;
		case 3:
			sendNPCDialogue(Giles, Happy,
					"I am glad you asked! I can unnote any item that you use on me for a small fee of GP.");
			stage = 4;
			break;
		case 4:
			stage = 5;
			sendPlayerDialogue(Happy, "Cool!");
			break;
		case 5:
			sendOptions(TITLE, "Unnote my item", "Who are you?");
			player.spokenWithGiles = true;
			stage = 6;
			break;
		case 6:
			switch (componentId) {
			case OPTION_1:
				sendNPCDialogue(Giles, Plain, "Use the noted item you want me to unnote and I will do it.");
				stage = END;
				break;
			case OPTION_2:
				sendNPCDialogue(Giles, Unsure, "You don't remember? We used to be best buddies!");
				stage = END;
				break;
			}
			break;

		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
