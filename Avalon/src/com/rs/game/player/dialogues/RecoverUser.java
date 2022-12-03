package com.rs.game.player.dialogues;

public class RecoverUser extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendPlayerDialogue(9827, "I want to recover username: " + player.otherPlayer + ".");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendNPCDialogue(8000, 9827, "Please type in " + player.otherPlayer + "'s recovery pin.");
		} else if (stage == 0) {
			player.temporaryAttribute().put("recover_pin", true);
			player.getPackets().sendRunScript(108, "Enter " + player.otherPlayer + "'s  pin:");
			end();
		} else
			end();
	}

	@Override
	public void finish() {

	}

}
