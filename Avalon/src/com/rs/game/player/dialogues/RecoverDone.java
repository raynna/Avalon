package com.rs.game.player.dialogues;

public class RecoverDone extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(8000, 9827, "Recovery Complete!");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendNPCDialogue(8000, 9827, "Your password is now: " + player.playerPass + ".");
		} else if (stage == 0) {
			end();
		} else
			end();
	}

	@Override
	public void finish() {

	}

}
