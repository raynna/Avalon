package com.rs.game.player.dialogues.dungeoneering;

import com.rs.game.player.dialogues.Dialogue;

public class DungeonDificulty extends Dialogue {

	@Override
	public void start() {
		int partySize = (int) parameters[0];
		String[] options = new String[partySize];
		for (int i = 0; i < options.length; i++)
			options[i] = "" + (i + 1);
		options[partySize / 2] += " (recommended)";
		sendOptionsDialogue("What difficulty of dungeon would you like?", options);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		end();
		if (componentId == OPTION_1)
			player.getDungManager().setDificulty(1);
		else if (componentId == OPTION_2)
			player.getDungManager().setDificulty(2);
		else if (componentId == OPTION_3)
			player.getDungManager().setDificulty(3);
		else if (componentId == OPTION_4)
			player.getDungManager().setDificulty(4);
		else if (componentId == OPTION_5)
			player.getDungManager().setDificulty(5);
		player.getDungManager().enterDungeon(true, false);

	}

	@Override
	public void finish() {

	}

}
