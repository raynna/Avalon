package com.rs.game.player.dialogues.dungeoneering;

import com.rs.game.player.dialogues.Dialogue;

public class PreShareD extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Would you like to enable pre-share? [Warning: Expert Players Only!]", "What is pre-share?", "Yes.", "No.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				sendDialogue("Pre-share is the removal of key sharing which increases teamwork and dungeon difficulty.");
				stage = 1;
			} else if (componentId == OPTION_2 || componentId == OPTION_3) {
				player.getDungManager().setKeyShare(componentId == OPTION_3);
				end();
				player.getDungManager().enterDungeon(true, false);

			}
		} else if (stage == 1) {
			sendOptionsDialogue("Would you like to enable pre-share? [Warning: Expert Players Only!]", "Yes.", "No.");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1 || componentId == OPTION_2) {
				player.getDungManager().setKeyShare(componentId == OPTION_2);
				end();
				player.getDungManager().enterDungeon(true, false);

			}
		}
	}

	@Override
	public void finish() {

	}
}
