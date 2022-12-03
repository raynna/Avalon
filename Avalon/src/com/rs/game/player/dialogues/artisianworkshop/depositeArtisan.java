package com.rs.game.player.dialogues.artisianworkshop;

import com.rs.game.player.dialogues.Dialogue;

public class depositeArtisan extends Dialogue {

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
				player.getArtisanWorkshop().depositOres(454);
				
				player.sm("You have succesfully depost this ores.");
				end();
			} else if (componentId == OPTION_2) {
				player.getArtisanWorkshop().depositOres(440);
				player.getArtisanWorkshop().depositOres(441);
				player.sm("You have succesfully depost this ores.");
				end();
			}else if (componentId == OPTION_3) {
				player.getArtisanWorkshop().depositOres(447);
				player.getArtisanWorkshop().depositOres(448);
				player.sm("You have succesfully depost this ores.");
				end();
			}else if (componentId == OPTION_4) {
				player.getArtisanWorkshop().depositOres(449);
				player.getArtisanWorkshop().depositOres(450);
				player.sm("You have succesfully depost this ores.");
				end();
			} else {
				player.getArtisanWorkshop().depositOres(451);
				player.getArtisanWorkshop().depositOres(452);
				player.sm("You have succesfully depost this ores.");
				end();
			}
		}
	}

	@Override
	public void finish() {

	}

}
