package com.rs.game.player.dialogues.artisianworkshop;

import com.rs.game.player.dialogues.Dialogue;

public class returnIngotsArtisan extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Which ore would you like to deposite?", "Coal", "Iron", "Mithril", "Adamant", "Runite");
		stage = 0;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 0) {
			if (componentId == OPTION_1) {
				player.getArtisanWorkshop().depositOres(453);
				end();
			} else if (componentId == OPTION_2) {
				player.getArtisanWorkshop().depositOres(440);
				end();
			}else if (componentId == OPTION_4) {
				player.getArtisanWorkshop().depositOres(447);
				end();
			}else if (componentId == OPTION_5) {
				player.getArtisanWorkshop().depositOres(449);
				end();
			} else {
				player.getArtisanWorkshop().depositOres(451);
				end();
			}
		}
	}

	@Override
	public void finish() {

	}

}
