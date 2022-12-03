package com.rs.game.player.dialogues.dungeoneering;

import com.rs.game.player.dialogues.Dialogue;

public class CustomSeedD extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Would you like to use custom seed? [Warning: Expert Players Only!]", "What is custom seed?", "Yes.", "No.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				sendDialogue("Custom seed changes map generation in a way that using the same seed will always result in the same map.");
				stage = 1;
			} else if (componentId == OPTION_2 || componentId == OPTION_3) {
				if (componentId == OPTION_3) {
					end();
					player.getDungManager().enterDungeon(true, false);
				}
				else if (componentId == OPTION_2) {
					player.getPackets().sendInputIntegerScript(true, "Enter custom seed (0-999999):");
					sendDialogue("Beware that using custom seed will prevent you from getting any xp or tokens.");
					stage = 3;
				}
			}
		} else if (stage == 1) {
			sendOptionsDialogue("Would you like to use custom seed? [Warning: Expert Players Only!]", "Yes.", "No.");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_2) {
				end();
				player.getDungManager().enterDungeon(true, false);
			}
			else if (componentId == OPTION_1) {
				player.getPackets().sendInputIntegerScript(true, "Enter custom seed (0-999999):");
				sendDialogue("Beware that using custom seed will prevent you from getting xp or tokens.");
				stage = 3;
			}
		} else if (stage == 3) {
			end();
			player.getDungManager().enterDungeon(true, false);
		} else if (stage == 4) {
			end();
		}
	}
	
	
	public void redo() {
		if (player.getDungManager().getParty() != null)
			player.getDungManager().getParty().setHasShownCustomSeedDialog(false);
		sendDialogue("Invalid seed, please enter number between 0-999999.");
		stage = 4;
	}

	@Override
	public void finish() {

	}
}
