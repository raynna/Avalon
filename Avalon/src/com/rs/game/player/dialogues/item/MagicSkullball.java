package com.rs.game.player.dialogues.item;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.dialogues.Dialogue;

public class MagicSkullball extends Dialogue {

	private int itemId;
	private int slotId;

	@Override
	public void start() {
		stage = 0;
		itemId = (Integer) parameters[0];
		slotId = (Integer) parameters[1];
		sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Long", "Yes/No", "Activities", "Colours");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case 0:
			stage = -1;
			if (componentId == OPTION_1) {
				sendEntityDialogue(SEND_ITEM_DIALOGUE, new String[] { ItemDefinitions.getItemDefinitions(itemId + 2).name,
						"You chose " + ItemDefinitions.getItemDefinitions(itemId + 2).name }, IS_ITEM, itemId + 2, -1);
				player.getInventory().getItems().set(slotId, new Item(itemId + 2));
				player.getInventory().refresh();
			} else if (componentId == OPTION_2) {
				sendEntityDialogue(SEND_ITEM_DIALOGUE, new String[] { ItemDefinitions.getItemDefinitions(itemId + 4).name,
						"You chose " + ItemDefinitions.getItemDefinitions(itemId + 4).name }, IS_ITEM, itemId + 4, -1);
				player.getInventory().getItems().set(slotId, new Item(itemId + 4));
				player.getInventory().refresh();
			} else if (componentId == OPTION_3) {
				sendEntityDialogue(SEND_ITEM_DIALOGUE, new String[] { ItemDefinitions.getItemDefinitions(itemId + 6).name,
						"You chose " + ItemDefinitions.getItemDefinitions(itemId + 6).name }, IS_ITEM, itemId + 6, -1);
				player.getInventory().getItems().set(slotId, new Item(itemId + 6));
				player.getInventory().refresh();
			} else if (componentId == OPTION_4) {
				sendEntityDialogue(SEND_ITEM_DIALOGUE, new String[] { ItemDefinitions.getItemDefinitions(itemId + 8).name,
						"You chose " + ItemDefinitions.getItemDefinitions(itemId + 8).name }, IS_ITEM, itemId + 8, -1);
				player.getInventory().getItems().set(slotId, new Item(itemId + 8));
				player.getInventory().refresh();
			}
			break;
		case -1:
			end();
			break;
		default:
			end();
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
