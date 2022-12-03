package com.rs.game.player.dialogues.dungeoneering;

import com.rs.game.player.content.dungeoneering.DungeonConstants;
import com.rs.game.player.content.dungeoneering.DungeonPartyManager;
import com.rs.game.player.dialogues.Dialogue;

public class DungeonSize extends Dialogue {

	@Override
	public void start() {
		//DungeonPartyManager party = player.getDungManager().getParty();
		//if (party == null || party.getTeam().size() < 3)
		//	sendOptionsDialogue("What dungeon size would you like?", "Small.", "Medium.");
		//else
			sendOptionsDialogue("What dungeon size would you like?", "Small.", "Medium.", "Large.");
		stage = 0;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		DungeonPartyManager party = player.getDungManager().getParty();
		if (stage == 0) {
			end();
			if (party != null) {
				if (componentId == OPTION_1)
					player.getDungManager().setSize(DungeonConstants.SMALL_DUNGEON);
				else if (componentId == OPTION_2)
					player.getDungManager().setSize(DungeonConstants.MEDIUM_DUNGEON);
				//else if (componentId == OPTION_3 && party.getTeam().size() >= 3)
				else if (componentId == OPTION_3)
					player.getDungManager().setSize(DungeonConstants.LARGE_DUNGEON);
				player.getDungManager().enterDungeon(false, false);
			}

		}
	}

	@Override
	public void finish() {

	}

}
