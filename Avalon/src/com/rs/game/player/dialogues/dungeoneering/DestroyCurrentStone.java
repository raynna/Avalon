package com.rs.game.player.dialogues.dungeoneering;

import com.rs.game.player.content.dungeoneering.DungeonConstants;
import com.rs.game.player.controlers.Controler;
import com.rs.game.player.controlers.DungeonControler;
import com.rs.game.player.dialogues.Dialogue;

public class DestroyCurrentStone extends Dialogue {

	@Override
	public void start() {
		if (!player.getDungManager().isInside()) {
			end();
			return;
		}
		sendPlayerDialogue(DungeonConstants.GATESTONE, "You have already placed a gatestone. Creating another will destroy your current gatestone. Do you wish to continue?");
		stage = -1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Select an Option.", "Create a new gatestone.", "Cancel.");
			stage = 0;
		} else if (stage == 0) {
			if (componentId == OPTION_1) {
				Controler c = player.getControlerManager().getControler();
				if (c != null && c instanceof DungeonControler) {
					DungeonControler dc = (DungeonControler) c;
					dc.removeCurrentGatestone();
					dc.addGatestone();
				}
			}
			end();
		}
	}

	@Override
	public void finish() {
		
	}
}
