package com.rs.game.player.dialogues;

public class AnimTest extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9836, "Don't know");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendNPCDialogue(npcId, 9840, "Laughing/Happy");
		} else if (stage == 0) {
			stage = 1;
			sendNPCDialogue(npcId, 9841, "Laughing/Hilarious");
		} else if (stage == 1) {
			stage = 2;
			sendNPCDialogue(npcId, 9842, "Laughs Evil");
		} else if (stage == 2) {
			stage = 3;
			sendNPCDialogue(npcId, 9843, "Talks Really Chill");
		} else if (stage == 3) {
			stage = 4;
			sendNPCDialogue(npcId, 9847, "Talks with good mood");
		} else if (stage == 4) {
			stage = 5;
			sendNPCDialogue(npcId, 9851, "Talks like you said something dumb/funny");
		} else if (stage == 5) {
			end();
		} else
			end();
	}

	@Override
	public void finish() {

	}

}
