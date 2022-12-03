package com.rs.game.player.dialogues.npcs;

import com.rs.game.player.dialogues.Dialogue;

public class WiseOldMan extends Dialogue {

	public static final int NPC = 2253;

	private int stage;

	@Override
	public void start() {
		stage = 0;
		sendNPCDialogue(NPC, Happy, "Hello fellow citizen, would you like me to teleport you somewhere? "
				+ "I am getting very good at it.");
	}

	@Override
	public void run(int interfaceId, int componentId) {

		switch (stage) {
		case 0:
			sendPlayerDialogue(Unsure, "Did you just say you were getting good at it?");
			stage = 1;
			break;
		case 1:
			sendNPCDialogue(NPC, Unsure,
					"Did I say that... I meant I am an expert magic man who can teleport you anywhere!");
			stage = 2;
			break;
		case 2:
			sendPlayerDialogue(Normal, "Well in that case I guess i'll give it a shot. Take me to...");
			stage = 3;
			break;
		case 3:
			player.getDialogueManager().startDialogue("WiseOldManOptions");
			break;
		}
	}

	@Override
	public void finish() {

	}
}