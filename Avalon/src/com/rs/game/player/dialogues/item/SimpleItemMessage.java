package com.rs.game.player.dialogues.item;

import com.rs.game.player.dialogues.Dialogue;

public class SimpleItemMessage extends Dialogue {

	@Override
	public void start() {
		sendHandedItem((Integer) parameters[0], (String) parameters[1]);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		end();
	}

	@Override
	public void finish() {

	}

}
