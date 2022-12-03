package com.rs.game.player.dialogues;

import com.rs.game.player.Player;

public class DuellistTier4 extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Choose which hat to change to:", "Tier 1", "Tier 2", "Tier 3", "Tier 5", "Tier 6");
	}

	private void removeDuellists(Player player) {
		for (int itemId = 20795; itemId < 20801; itemId++) {
			player.getInventory().deleteItem(itemId, 1);
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				end();
				removeDuellists(player);
				player.getInventory().addItem(20795, 1);
			} else if (componentId == OPTION_2) {
				end();
				removeDuellists(player);
				player.getInventory().addItem(20796, 1);
			} else if (componentId == OPTION_3) {
				end();
				if (player.getDuelkillCount() < 100) {
					player.getPackets().sendGameMessage(
							"You can't change the look of your duellists' cap until you earned additional tiers.");
					player.getPackets().sendGameMessage("You need at least 100 duel arena kills.");
					return;
				}
				removeDuellists(player);
				player.getInventory().addItem(20797, 1);
			} else if (componentId == OPTION_4) {
				end();
				if (player.getDuelkillCount() < 2000) {
					player.getPackets().sendGameMessage(
							"You can't change the look of your duellists' cap until you earned additional tiers.");
					player.getPackets().sendGameMessage("You need at least 2000 duel arena kills.");
					return;
				}
				removeDuellists(player);
				player.getInventory().addItem(20799, 1);
			} else if (componentId == OPTION_5) {
				end();
				if (player.getDuelkillCount() < 5000) {
					player.getPackets().sendGameMessage(
							"You can't change the look of your duellists' cap until you earned additional tiers.");
					player.getPackets().sendGameMessage("You need at least 5000 duel arena kills.");
					return;
				}
				removeDuellists(player);
				player.getInventory().addItem(20800, 1);
			}
		}

	}

	@Override
	public void finish() {

	}
}