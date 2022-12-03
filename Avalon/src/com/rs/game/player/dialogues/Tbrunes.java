package com.rs.game.player.dialogues;

public class Tbrunes extends Dialogue {

	int npcId;
	int[] TB_RUNES = { 557, 561, 555, 563, 562 };

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Good day, Would you like to purchase TB Runes?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendOptionsDialogue("What would you like to say?", "I would love to buy TB Runes!.",
					"I don't want to buy them! EW", "");
		} else if (stage == 0) {
			if (componentId == OPTION_1) {
				end();
			} else if (componentId == OPTION_2) {
				end();
			} else
				end();
		} else if (stage == 0) {
			stage = 2;
			player.getInventory().containsItem(995, 30000);
			player.getInventory().deleteItem(995, 30000);
			player.getInventory().addItem(557, 1000);
			player.getInventory().addItem(561, 1000);
			player.getInventory().addItem(555, 1000);
			player.getInventory().addItem(563, 1000);
			player.getInventory().addItem(562, 1000);
		} else
			end();
	}

	@Override
	public void finish() {

	}

}
