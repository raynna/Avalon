package com.rs.game.player.dialogues.skilling;

import com.rs.game.WorldObject;
import com.rs.game.player.dialogues.Dialogue;

public class ChallengeModeLeverD extends Dialogue {

	private WorldObject object;

	@Override
	public void start() {
		object = (WorldObject) this.parameters[0];
		sendOptionsDialogue("Select an option", "Activate challenge mode.", "Activate pvp mode.", "Nevermind.");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (componentId != OPTION_3) {
			player.getHouse().switchChallengeMode(componentId == OPTION_2);
			player.getHouse().sendPullLeverEmote(object);
		}
		end();
	}

	@Override
	public void finish() {

	}

}
