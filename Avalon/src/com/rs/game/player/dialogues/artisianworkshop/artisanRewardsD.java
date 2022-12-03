package com.rs.game.player.dialogues.artisianworkshop;

import com.rs.game.player.dialogues.Dialogue;

public class artisanRewardsD extends Dialogue {

	
	String param;
	int cost;
	@Override
	public void start() {
		param = (String) parameters[0];
		cost = (int) parameters[1];
		if(player.getArtisanWorkshop().getRespect() < cost)
			end();
		else {
			 sendOptions("Select an option","Buy", "No thanks");
			 stage = 1;
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		 if(stage == 1){
			if(componentId == OPTION_1){
				if(param == "quick" && !player.getArtisanWorkshop().quick_Learner){
				player.getArtisanWorkshop().setRespect(player.getArtisanWorkshop().getRespect() - cost);
				player.getArtisanWorkshop().quick_Learner = true;
				player.getDialogueManager().startDialogue("SimpleMessage", "You've unlocked a new feature.");
				player.getArtisanWorkshop().sendRewardInterface();
			    stage = 100;
				} else if(param == "budding" && !player.getArtisanWorkshop().budding_Student){
					player.getArtisanWorkshop().setRespect(player.getArtisanWorkshop().getRespect() - cost);
					player.getArtisanWorkshop().budding_Student = true;
					player.getDialogueManager().startDialogue("SimpleMessage", "You've unlocked a new feature.");
					player.getArtisanWorkshop().sendRewardInterface();
				    stage = 100;
				}else if(param == "master" && !player.getArtisanWorkshop().master_Student){
					player.getArtisanWorkshop().setRespect(player.getArtisanWorkshop().getRespect() - cost);
					player.getArtisanWorkshop().master_Student = true;
					player.getDialogueManager().startDialogue("SimpleMessage", "You've unlocked a new feature.");
					player.getArtisanWorkshop().sendRewardInterface();
				    stage = 100;
				}else if(param == "golden" && !player.getArtisanWorkshop().golden_Cannon){
					player.getArtisanWorkshop().setRespect(player.getArtisanWorkshop().getRespect() - cost);
					player.getArtisanWorkshop().golden_Cannon = true;
					player.getDialogueManager().startDialogue("SimpleMessage", "You've unlocked a new feature.");
					player.getArtisanWorkshop().sendRewardInterface();
				    stage = 100;
				}else if(param == "royal" && !player.getArtisanWorkshop().royal_Cannon){
					player.getArtisanWorkshop().setRespect(player.getArtisanWorkshop().getRespect() - cost);
					player.getArtisanWorkshop().royal_Cannon = true;
					player.getDialogueManager().startDialogue("SimpleMessage", "You've unlocked a new feature.");
					player.getArtisanWorkshop().sendRewardInterface();
				    stage = 100;
				}else if(param == "restock" && !player.getArtisanWorkshop().restocking){
					player.getArtisanWorkshop().setRespect(player.getArtisanWorkshop().getRespect() - cost);
					player.getArtisanWorkshop().restocking = true;
					player.getDialogueManager().startDialogue("SimpleMessage", "You've unlocked a new feature.");
					player.getArtisanWorkshop().sendRewardInterface();
				    stage = 100;
				} else {
					player.getDialogueManager().startDialogue("SimpleMessage", "You have already unlocked this feature.");
				}
			} else {
			
				end();
			}
		} else if (stage == 100)
			end();
	}

	@Override
	public void finish() {

	}

}
