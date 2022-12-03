package com.rs.game.player.dialogues.player;

import com.rs.game.player.dialogues.Dialogue;

public class SwitchSpellBook extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Select an Option", "Modern", "Ancient", "Lunar");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			switch (componentId) {
			case OPTION_1:
				player.getCombatDefinitions().setSpellBook(0);
				player.getPackets().sendFilteredGameMessage(true, "You are now using the Modern Spellbook.");
				break;
			case OPTION_2:
				player.getCombatDefinitions().setSpellBook(1);
				player.getPackets().sendFilteredGameMessage(true, "You are now using the Ancient Spellbook.");
				break;
			case OPTION_3:
				player.getPackets().sendFilteredGameMessage(true, "You are now using the Lunar Spellbook.");
				player.getCombatDefinitions().setSpellBook(2);
				break;
			}
		}
		end();

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
