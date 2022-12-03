package com.rs.game.player.dialogues;

import com.rs.game.player.actions.skills.slayer.Slayer.SlayerMaster;

public class ContactNPC extends Dialogue {

	@Override
	public void start() {
		sendOptions("Contact....", "Slayer Master", "Explorer Jack", "Account manager", "Town Crier", "Adventure log");
		stage = 1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case END:
			end();
			break;
		case 1:
			switch (componentId) {
			case OPTION_1:
				for (SlayerMaster master : SlayerMaster.values()) {
					player.getDialogueManager().startDialogue("SlayerMasterD", master);
				}
				break;
			case OPTION_2:
				player.getDialogueManager().startDialogue("ExplorerJack");
				break;
			case OPTION_3:
				player.getDialogueManager().startDialogue("AccountMan");
				break;
			case OPTION_4:
				player.getDialogueManager().startDialogue("Town");
				break;
			case OPTION_5:
				player.getDialogueManager().startDialogue("AdventureLog");
				break;
			}
			break;
		}
	}

	@Override
	public void finish() {

	}

}
