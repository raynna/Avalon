package com.rs.game.player.dialogues;

public class ForfeitDialouge extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Forfeit Duel?", "Yes.", "No.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (componentId) {
		case OPTION_1:
			if (!player.getLastDuelRules().getRule(7)) {
				sendDialogue("You can't forfeit in a duel.");
			} else {
				sendDialogue("You can't forfeit during this duel.");
			}
			break;
		}
		end();
	}

	@Override
	public void finish() {

	}

}
