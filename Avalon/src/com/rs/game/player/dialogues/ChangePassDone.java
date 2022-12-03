package com.rs.game.player.dialogues;

public class ChangePassDone extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(8000, 9827, "Change password complete!");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendNPCDialogue(8000, 9827, "Your password is now changed to: " + player.changedPass + ".");
			end();
		} else
			end();
	}

	@Override
	public void finish() {

	}

}
