package com.rs.game.player.dialogues;

public class ClanDialogue extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Select an option.",
				player.getAppearence().getTitle() != 5000 ? "Enable clan title" : "Disable clan title",
				"Just passing by.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				end();
				player.getAppearence().setTitle(player.getAppearence().getTitle() != 5000 ? 5000 : -1);
				player.getAppearence().generateAppearenceData();
			} else if (componentId == OPTION_2) {
				end();
			}
		}

	}

	@Override
	public void finish() {

	}
}