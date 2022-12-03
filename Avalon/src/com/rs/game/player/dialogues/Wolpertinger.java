package com.rs.game.player.dialogues;

public class Wolpertinger extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Whurf, whurf!");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		default:
			end();
			break;
		}
	}

	@Override
	public void finish() {

	}

}