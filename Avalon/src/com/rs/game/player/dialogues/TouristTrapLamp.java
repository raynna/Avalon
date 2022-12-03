package com.rs.game.player.dialogues;

import com.rs.game.player.Skills;

public class TouristTrapLamp extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Choose a skill", "Agility", "Fletching", "Thieving", "Smithing");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				end();
				player.getInventory().deleteItem(2528, 1);
				player.getSkills().addXpNoBonus(Skills.AGILITY, 4350);
			} else if (componentId == OPTION_2) {
				end();
				player.getInventory().deleteItem(2528, 1);
				player.getSkills().addXpNoBonus(Skills.FLETCHING, 4350);
			} else if (componentId == OPTION_3) {
				end();
				player.getInventory().deleteItem(2528, 1);
				player.getSkills().addXpNoBonus(Skills.THIEVING, 4350);
			} else if (componentId == OPTION_4) {
				end();
				player.getInventory().deleteItem(2528, 1);
				player.getSkills().addXpNoBonus(Skills.SMITHING, 4350);
			}
		}

	}

	@Override
	public void finish() {

	}
}