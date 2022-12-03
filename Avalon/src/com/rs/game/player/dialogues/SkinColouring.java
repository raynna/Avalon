package com.rs.game.player.dialogues;

public class SkinColouring extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		stage = 0;
		sendNPCDialogue(4493, 9827, "Hello, what colour do you want of your skin?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 0) {
			stage = 1;
			sendOptionsDialogue("Choose an option", "Next Colour", "Previous Colour", "Enter a Colour Id",
					"Check Skincolour Id");
		} else if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.getAppearence().getBootsColor() == 500) {
					sendNPCDialogue(4493, 9827, "There are no more colours, please choose 'Previous Colour' option.");
					stage = 0;
				} else {
					player.getAppearence().setSkinColor(player.getAppearence().getSkinColor() + 1);
					player.getAppearence().generateAppearenceData();
					sendOptionsDialogue("Choose an option", "Next Colour", "Previous Colour", "Enter a Colour Id",
							"Check Colour Id");
				}
			} else if (componentId == OPTION_2) {
				if (player.getAppearence().getSkinColor() == 0) {
					sendNPCDialogue(4493, 9827, "There are no more colours, please choose 'Next Colour' option.");
					stage = 0;
				} else {
					player.getAppearence().setSkinColor(player.getAppearence().getSkinColor() - 1);
					player.getAppearence().generateAppearenceData();
					sendOptionsDialogue("Choose an option", "Next Colour", "Previous Colour", "Enter a Colour Id",
							"Check Colour Id");
				}
			} else if (componentId == OPTION_3) {
				player.temporaryAttribute().put("skin_colour", Boolean.TRUE);
				player.getPackets().sendRunScript(108, new Object[] { "Enter a id 1-500: " });
				end();
			} else if (componentId == OPTION_4) {
				sendNPCDialogue(4493, 9827, "Your currently skin id is " + player.getAppearence().getSkinColor());
				stage = 0;
			}
		}
	}

	@Override
	public void finish() {

	}

}
