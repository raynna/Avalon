package com.rs.game.player.dialogues;

public class Punishment extends Dialogue {

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Jail: <col=ff0000>" + player.getDisplayName() + "</col>", "Minutes (1-60)", "Hours (1-48)",
				"Permanent", "Cancel");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case 1:
			if (componentId == OPTION_1) {

			} else if (componentId == OPTION_2) {

			} else if (componentId == OPTION_3) {

			} else if (componentId == OPTION_4) {
				player.sm("Jail Selection Canceled.");
				end();
			}
		}
	}

	@Override
	public void finish() {
		player.getDialogueManager().finishDialogue();
	}
}