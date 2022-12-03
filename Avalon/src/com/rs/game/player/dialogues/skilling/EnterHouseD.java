package com.rs.game.player.dialogues.skilling;

import com.rs.game.player.dialogues.Dialogue;

public class EnterHouseD extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Select an option", "Go to your house.", "Go to your house (building mode).", "Go to a friend's house.", "Never mind.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (componentId == OPTION_1 || componentId == OPTION_2) {
			//this method checks location u entering from 
			if (componentId == OPTION_2)
				player.getHouse().setBuildMode(true);
			player.getHouse().enterMyHouse();
		} else if (componentId == OPTION_3) {
			player.getTemporaryAttributtes().put("enterhouse", Boolean.TRUE);
			player.getPackets().sendInputNameScript("Enter the name of the house you'd like to enter:");
		}
		end();

	}

	@Override
	public void finish() {

	}

}
