package com.rs.game.player.dialogues;

public class recoverPin extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("What would you like to do?", "Recover my account!", "Change password.",
				"Get a recovery pin.", "Nothing.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (componentId == OPTION_1) {
			player.temporaryAttribute().put("recover_username", true);
			player.getPackets().sendRunScript(109, "Enter target username:");
		} else if (componentId == OPTION_2) {
			player.getInterfaceManager().closeChatBoxInterface();
		}
	}

	@Override
	public void finish() {

	}

}
