package com.rs.game.player.dialogues;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.item.Item;

public class StarSprite extends Dialogue {

	private int npcId = 8091;

	@Override
	public void start() {

		sendEntityDialogue(SEND_2_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "Greetings Earthling." }, IS_NPC, npcId,
				9827);

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendPlayerDialogue(9827, "Ahhhh, What!? who are you!?!?");
			stage = 1;
		} else if (stage == 1) {
			sendNPCDialogue(npcId, 9827, "Me called Star Sprite, I come to give you reward for freeeeing me.");
			stage = 2;
		} else if (stage == 2) {
			sendPlayerDialogue(9827, "Whoaaa! What do I get?");
			stage = 3;
		} else if (stage == 3) {
			sendNPCDialogue(npcId, 9827,
					"You can have whatever is in my pocket... but I want your stardust in return.");
			stage = 4;
		} else if (stage == 4) {
			sendOptions("Select an option", "Small fallen star (120)", "Starfury cosmetic set (1500)",
					"Starfury Sword (1000)", "Starfury Bow (1000)", "Starfury Staff (1000)");
			stage = 5;
		} else if (stage == 5) {
			if (componentId == OPTION_1) {
				if (player.getInventory().getAmountOf(13727) >= 120) {
					player.getInventory().addItem(new Item(30550, 1));
					player.getInventory().deleteItem(new Item(13727, 120));
				} else {
					sendNPCDialogue(npcId, 9827, "You don't have enough stardust for that item.");
				}
				stage = 99;
			} else if (componentId == OPTION_2) {
				if (player.getInventory().getAmountOf(13727) >= 1500) {
					player.message("TODO");
					// player.getInventory().addItem(new Item(30524,1));
					// player.getInventory().deleteItem(new Item(13727,1500));
				} else {
					sendNPCDialogue(npcId, 9827, "You don't have enough stardust for that item.");
				}
				stage = 99;
			} else if (componentId == OPTION_3) {
				if (player.getInventory().getAmountOf(13727) >= 1000) {
					player.getInventory().addItem(new Item(28107, 1));
					player.getInventory().deleteItem(new Item(13727, 1000));
				} else {
					sendNPCDialogue(npcId, 9827, "You don't have enough stardust for that item.");
				}
				stage = 99;
			} else if (componentId == OPTION_4) {
				if (player.getInventory().getAmountOf(13727) >= 1000) {
					player.getInventory().addItem(new Item(28111, 1));
					player.getInventory().deleteItem(new Item(13727, 1000));
				} else {
					sendNPCDialogue(npcId, 9827, "You don't have enough stardust for that item.");
				}
				stage = 99;
			} else if (componentId == OPTION_5) {
				if (player.getInventory().getAmountOf(13727) >= 1000) {
					player.getInventory().addItem(new Item(28116, 1));
					player.getInventory().deleteItem(new Item(13727, 1000));
				} else {
					sendNPCDialogue(npcId, 9827, "You don't have enough stardust for that item.");
				}
				stage = 99;
			}
		} else if (stage == 99)
			end();
	}

	@Override
	public void finish() {

	}
}