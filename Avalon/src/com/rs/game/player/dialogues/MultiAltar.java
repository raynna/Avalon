package com.rs.game.player.dialogues;

import com.rs.game.player.dialogues.Dialogue;

public class MultiAltar extends Dialogue {

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Change spellbooks or prayerbooks?", "Change spellbooks", "Change prayer books");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -2) {
			end();
			return;
		}
		if (stage == 1) {
			if (componentId == OPTION_1) {
				stage = 10;
				sendOptionsDialogue("Which spellbook?", "Moderns", "Ancients", "Lunars");
			} else {
				stage = 20;
				sendOptionsDialogue("Which prayer book?", "Regular", "Curses");
			}
		} else if (stage == 10) {
			if (componentId == OPTION_1) {
				stage = -2;
				player.getCombatDefinitions().setSpellBook(0);
				sendDialogue("You switch to the modern spellbook.");
			} else if (componentId == OPTION_2) {
				stage = -2;
				player.getCombatDefinitions().setSpellBook(1);
				sendDialogue("You switch to the ancient spellbook.");
			} else if (componentId == OPTION_3) {
				stage = -2;
				player.getCombatDefinitions().setSpellBook(2);
				sendDialogue("You switch to the lunar spellbook.");
			}
		} else if (stage == 20) {
			if (componentId == OPTION_1) {
				stage = -2;
				player.getPrayer().setPrayerBook(false);
				sendDialogue("You switch to the regular prayer book.");
			} else if (componentId == OPTION_2) {
				stage = -2;
				player.getPrayer().setPrayerBook(true);
				sendDialogue("You switch to the curse prayer book.");
			}
		}

	}

	@Override
	public void finish() {

	}

}
