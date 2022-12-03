package com.rs.game.player.dialogues;

public class ChangePass extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendPlayerDialogue(9827, "I want to change my password, please.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendNPCDialogue(8000, 9827, "Okey what password do you want?");
		} else if (stage == 0) {
			player.temporaryAttribute().put("change_pass", true);
			player.getPackets().sendRunScript(109, "Enter new password: ");
			end();
		} else
			end();
	}

	@Override
	public void finish() {

	}

}
