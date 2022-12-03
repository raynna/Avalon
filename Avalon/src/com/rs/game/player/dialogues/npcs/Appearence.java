package com.rs.game.player.dialogues.npcs;

import com.rs.game.player.content.PlayerLook;
import com.rs.game.player.dialogues.Dialogue;

public class Appearence extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("What would you like to change?", "Gender/Skin", "Hairstyle", "Clothes", "Shoes",
				"No Thanks.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				end();
				PlayerLook.openMageMakeOver(player);
			} else if (componentId == OPTION_2) {
				end();
				PlayerLook.openHairdresserSalon(player);
			} else if (componentId == OPTION_3) {
				end();
				PlayerLook.openThessaliasMakeOver(player);
			} else if (componentId == OPTION_4) {
				end();
				PlayerLook.openYrsaShop(player);
			} else if (componentId == OPTION_5) {
				end();
			}
		}

	}

	@Override
	public void finish() {

	}
}