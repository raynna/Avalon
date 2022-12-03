package com.rs.game.player.dialogues.artisianworkshop;

import com.rs.game.player.dialogues.Dialogue;

public class WithdrawArtisanD extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Which ore would you like to withdraw?", "Coal", "Iron", "Mithril", "Adamant", "Runite");
		stage = 0;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 0) {
			if (componentId == OPTION_1) {
				player.getArtisanWorkshop().returnOres(453);
				end();
			} else if (componentId == OPTION_2) {
				player.getArtisanWorkshop().returnOres(440);
				end();
			}else if (componentId == OPTION_3) {
				player.getArtisanWorkshop().returnOres(447);
				end();
			}else if (componentId == OPTION_4) {
				player.getArtisanWorkshop().returnOres(449);
				end();
			} else {
				player.getArtisanWorkshop().returnOres(451);
				end();
			}
		}
	}

	@Override
	public void finish() {

	}

}
