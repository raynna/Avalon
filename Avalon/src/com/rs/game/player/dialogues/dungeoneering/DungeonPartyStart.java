package com.rs.game.player.dialogues.dungeoneering;

import com.rs.game.player.dialogues.Dialogue;

public class DungeonPartyStart extends Dialogue {

	@Override
	public void start() {
		sendDialogue("You must be in a party to enter a dungeon.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Would you like to start a dungeon?", "Yes.", "No.");
			stage = 0;
		} else if (stage == 0) {
			end();
			if (componentId == OPTION_1)
				player.getDungManager().formParty();
		}
	}

	@Override
	public void finish() {

	}

}
