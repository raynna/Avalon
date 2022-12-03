package com.rs.game.player.dialogues.dungeoneering;

import com.rs.game.player.controlers.DungeonControler;
import com.rs.game.player.dialogues.Dialogue;

public class DungeonExit extends Dialogue {

	private DungeonControler dungeon;

	@Override
	public void start() {
		dungeon = (DungeonControler) parameters[0];
		sendDialogue("This ladder leads back to the surface. You will not be able", "to come back to this dungeon if you leave.");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Leave the dungeon and return to the surface?", "Yes.", "No.");
			stage = 0;
		} else if (stage == 0) {
			if (componentId == OPTION_1)
				dungeon.leaveDungeon();
			end();
		}
	}

	@Override
	public void finish() {

	}

}
