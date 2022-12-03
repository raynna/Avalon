package com.rs.game.player.dialogues;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.content.unlockables.UnlockableItems;
import com.rs.game.player.content.unlockables.UnlockableManager;
import com.rs.utils.Utils;

public class PurchaseItem extends Dialogue {

	int itemId;
	int cheapestPrice;

	@Override
	public void start() {
		itemId = (Integer) parameters[0];
			UnlockableItems toUnlock = UnlockableItems.forId(itemId);
			if (toUnlock != null) {
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(itemId).name,
							ItemDefinitions.getItemDefinitions(itemId).getName() + " Price: "
									+ Utils.getFormattedNumber(toUnlock.getPrice(), ',')
									+ " coins<br>" },
					IS_ITEM, itemId, -1);
			}
		stage = 1;

	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case END:
			end();
			break;
		case 1:
			sendOptionsDialogue(TITLE, "Yes", "No");
			stage = 2;
			break;
		case 2:
			switch (componentId) {
			case OPTION_1:
				UnlockableManager.unlockItemForPlayer(player, itemId);
				end();
				break;
			case OPTION_2:
				end();
				break;

			}
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
