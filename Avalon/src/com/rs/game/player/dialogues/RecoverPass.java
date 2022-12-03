package com.rs.game.player.dialogues;

public class RecoverPass extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(8000, 9827, "Pin was entered correctly.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendNPCDialogue(8000, 9827, "What password would you like to set on " + player.otherPlayer + "?");
		} else if (stage == 0) {
			player.temporaryAttribute().put("recover_pass", true);
			player.getPackets().sendRunScript(109, "Enter new password on " + player.otherPlayer + ": ");
			end();
		} else
			end();
	}

	@Override
	public void finish() {

	}

}
