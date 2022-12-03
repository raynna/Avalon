package com.rs.game.player.dialogues;

import com.rs.utils.ShopsHandler;

public class SlayerShop extends Dialogue {

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("What store would you like to view?", "Slayer Shop", "Slayer Points Shop");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				end();
				ShopsHandler.openShop(player, 20);
			} else if (componentId == OPTION_2) {
				end();
				ShopsHandler.openShop(player, 19);
			}
		}
	}

	@Override
	public void finish() {

	}
}