package com.rs.game.player.dialogues;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.content.TrimArmour;
import com.rs.utils.Utils;

public class TrimArmourD extends Dialogue {

	private int itemId;

	@Override
	public void start() {
		itemId = (Integer) parameters[0];
		sendOptionsDialogue("Trim " + ItemDefinitions.getItemDefinitions(itemId).getName() + " for " + Utils.getFormattedNumber(TrimArmour.getPrice(player, itemId), ',') + " coins?", "Yes", "No, thanks.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			if (componentId == OPTION_1) {
				TrimArmour.trimArmour(player, itemId);
				end();
			} else if (componentId == OPTION_2) {
				end();
			}
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
