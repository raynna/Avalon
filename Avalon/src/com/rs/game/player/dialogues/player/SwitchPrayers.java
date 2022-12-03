package com.rs.game.player.dialogues.player;

import com.rs.game.player.dialogues.Dialogue;

public class SwitchPrayers extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("What would you like to do?", "Switch Spellbook.", "Switch Prayerbook.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			switch (componentId) {
			case OPTION_1:
				player.getDialogueManager().startDialogue("SwitchSpellBook");
				break;
			case OPTION_2:
				player.getDialogueManager().startDialogue("SwitchPrayersTrue");
				break;
			}
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
