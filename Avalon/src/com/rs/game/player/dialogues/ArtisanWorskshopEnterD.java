package com.rs.game.player.dialogues;

public class ArtisanWorskshopEnterD extends Dialogue {

	int npcId = 15231;

	@Override
	public void start() {
		sendNPCDialogue(npcId, 9827, "What do you want giant?");
		stage = 0;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 0) {
			sendOptionsDialogue("","Why is this area blocked off?", "Enter Artisan Workshop", "Nevermind.");
			stage = 1;
		} else if (stage == 1) {
			if (componentId == OPTION_1) {
				sendNPCDialogue(npcId, 9827,
						"This is the Artisan workshop the best place for training Smithing. Only Avalon members can access this area.");
				stage = 0;
			} else if (componentId == OPTION_2){
				if (player.isMember() || player.isDeveloper()) {
					player.getControlerManager().startControler("ArtisanControler");
					end();
				} else {
					player.sm("You need to be a Avalon Member to access this area.");
					end();
				}
			} else {
				end();
			}
		}
	}

	@Override
	public void finish() {
	}
}