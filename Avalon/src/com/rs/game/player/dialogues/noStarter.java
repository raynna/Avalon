package com.rs.game.player.dialogues;

public class noStarter extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9828, "Hello there, " + player.getDisplayName() + ".");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			player.lock();
			sendNPCDialogue(npcId, 9828,
					"I see you have already made an account, and therefore you get no starter bonus!");
			break;
		case 0:
			stage = 1;
			sendPlayerDialogue(9828, "It's fine, I can make money other ways!");
			player.unlock();
			break;
		default:
			end();
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
