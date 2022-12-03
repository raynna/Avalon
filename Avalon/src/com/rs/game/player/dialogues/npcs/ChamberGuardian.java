package com.rs.game.player.dialogues.npcs;

import com.rs.game.item.Item;
import com.rs.game.player.controlers.GodCapes;
import com.rs.game.player.dialogues.Dialogue;

public class ChamberGuardian extends Dialogue {
	private int npcId = 904;

	@Override
	public void start() {
		stage = 1;
		sendPlayerDialogue(Thinking, "Hi.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case 1:
			sendNPCDialogue(npcId, Thinking, "Hello adventurer, have you made your choice?");
			stage = 2;
		case 2:
			if (GodCapes.hasGodCape(player)) {
				sendPlayerDialogue(Normal, "I have.");
				stage = 3;
			} else {
				sendPlayerDialogue(Normal, "No, I haven't.");
				stage = 5;
			}
			break;
		case 3:
			sendNPCDialogue(npcId, Happy,
					"Good, good, I hope you have chosen well."
							+ " I will now present you with a magical staff. This, along with "
							+ "the cape awarded to you by your chosen god, are all the weapons"
							+ " and armour you will need here.");
			stage = 4;
			break;
		case 4:
			int cape = ((player.getInventory().containsItem(2413, 1) || player.getBank().getItem(2413) != null) ? 2416
					: (player.getInventory().containsItem(2414, 1) || player.getBank().getItem(2414) != null) ? 2417
							: 2415);
			sendItemDialogue(cape, 1, "The guardian hands you an ornate magic staff.");
			player.getInventory().addItem(new Item(cape, 1));
			stage = 5;
			break;
		case 5:
			end();
		}
	}

	@Override
	public void finish() {

	}

}
