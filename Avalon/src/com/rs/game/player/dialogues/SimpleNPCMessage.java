package com.rs.game.player.dialogues;

public class SimpleNPCMessage extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		String[] message = new String[parameters.length - 1];
		for (int i = 0; i < message.length; i++)
			message[i] = (String) parameters[i + 1];
		sendNPCDialogue(npcId, 9827, message);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		end();
	}

	@Override
	public void finish() {

	}

}
