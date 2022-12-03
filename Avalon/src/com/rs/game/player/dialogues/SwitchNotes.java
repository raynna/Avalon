package com.rs.game.player.dialogues;

import com.rs.game.player.dialogues.Dialogue;

public class SwitchNotes extends Dialogue {

	@Override
	public void start() {
		stage = 0;
		sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Switch withdraw notes", "Switch withdraw placeholders");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case 0:
			stage = -1;
			if (componentId == OPTION_1) {
				player.getBank().switchWithdrawNotes();
				end();
			} else if (componentId == OPTION_2) {
				player.getBank().switchPlaceholders();
				end();
			}
			break;
		case -1:
			end();
			break;
		default:
			end();
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
