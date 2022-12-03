package com.rs.game.player.dialogues.artisianworkshop;

import com.rs.game.player.dialogues.Dialogue;

public class SuakD extends Dialogue {

	int npcId = 6654;
	
	@Override
	public void start() {
		sendNPCDialogue(npcId, 9827,"Shouldn't you be smithing?");
		stage = 0;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 0) {
			sendOptions("Select an option", "What is this place?", "Points Shop");
			stage = 1;
		} else if(stage == 1){
			if(componentId == OPTION_1){
				sendNPCDialogue(npcId, 9827,"This is the Artisan workshop the best place for training smithing, I will reward you so don't worry.");
			    stage = 100;
			} else {
				player.getArtisanWorkshop().sendRewardInterface();
				end();
			}
		} else if (stage == 100)
			end();
	}

	@Override
	public void finish() {

	}

}
