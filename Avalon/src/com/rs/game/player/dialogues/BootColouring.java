package com.rs.game.player.dialogues;

public class BootColouring extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		stage = 0;
		sendNPCDialogue(570, 9827, "Hello, what colour do you want of your boots?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 0) {
			stage = 1;
			sendOptionsDialogue("Choose an option", "Next Colour", "Previous Colour", "Enter a Colour Id",
					"Check Colour Id");
		} else if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.getAppearence().getBootsColor() == 500) {
					sendNPCDialogue(570, 9827, "There are no more colours, please choose 'Previous Colour' option.");
					stage = 0;
				} else {
					player.getAppearence().setBootsColor(player.getAppearence().getBootsColor() + 1);
					player.getAppearence().generateAppearenceData();
					sendOptionsDialogue("Choose an option", "Next Colour", "Previous Colour", "Enter a Colour Id",
							"Check Colour Id");
				}
			} else if (componentId == OPTION_2) {
				if (player.getAppearence().getBootsColor() == 0) {
					sendNPCDialogue(570, 9827, "There are no more colours, please choose 'Next Colour' option.");
					stage = 0;
				} else {
					player.getAppearence().setBootsColor(player.getAppearence().getBootsColor() - 1);
					player.getAppearence().generateAppearenceData();
					sendOptionsDialogue("Choose an option", "Next Colour", "Previous Colour", "Enter a Colour Id",
							"Check Colour Id");
				}
			} else if (componentId == OPTION_3) {
				player.temporaryAttribute().put("boot_colour", Boolean.TRUE);
				player.getPackets().sendRunScript(108, new Object[] { "Enter a id 1-500: " });
				end();
			} else if (componentId == OPTION_4) {
				sendNPCDialogue(570, 9827, "Your currently boot id is " + player.getAppearence().getBootsColor());
				stage = 0;
			}
		}
	}

	@Override
	public void finish() {

	}

}
