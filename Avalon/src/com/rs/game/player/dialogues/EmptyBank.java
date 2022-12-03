package com.rs.game.player.dialogues;

import com.rs.game.player.actions.ClearBank;

public class EmptyBank extends Dialogue {

	/**
	 * @author Phillip
	 */

	@Override
	public void start() {
		sendDialogue("Are you sure you wish to empty your ENTIRE bank? This CANNOT be undone!");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("This CANNOT be undone!", "Delete my bank <col=ff0000>PERMANENTLY", "Nevermind");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				ClearBank.clearBank(player);
				player.getDialogueManager().startDialogue("SimpleMessage", "Your bank has been emptied!");

			} else if (componentId == OPTION_2) {
				player.getDialogueManager().startDialogue("SimpleMessage", "Action Aborted");
			}
		}
	}

	@Override
	public void finish() {

	}
}