package com.rs.game.player.dialogues;

public class AdventureLogD extends Dialogue {

	int npc;

	@Override
	public void start() {
		sendOptions(TITLE, "Open my adventure log", "Look up a players adventure log", "Settings");
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
				player.getAdventureLog().OpenAdventureLog();
				end();
				break;
			case OPTION_2:
				player.getPackets().sendRunScript(109, "Enter the player name:");
				player.temporaryAttribute().put("AdventureLog", true);
				end();
				break;
			case OPTION_3:
				sendOptions("Make my adventures log...", "Public", "Friends only", "Private");
				stage = 2;
				break;
			}
			break;
		case 2:
			switch (componentId) {
			case OPTION_1:
				player.getAdventureLog().PrivacySettings = 0;
				sendDialogue("Your adventure log is now public. Anyone who searches your name can view it.");
				stage = END;
				break;
			case OPTION_2:
				player.getAdventureLog().PrivacySettings = 1;
				sendDialogue("Your adventure log is now friends only. Any friend, that you got added can view it.");
				stage = END;
				break;
			case OPTION_3:
				player.getAdventureLog().PrivacySettings = 2;
				sendDialogue("Your adventure log is now private. No one can view it except for you.");
				stage = END;
				break;
			}
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
	}

}
