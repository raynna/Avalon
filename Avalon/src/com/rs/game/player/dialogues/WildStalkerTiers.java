package com.rs.game.player.dialogues;

import com.rs.game.player.Player;

public class WildStalkerTiers extends Dialogue {

	@Override
	public void start() {
		if (player.getKillCount() > 99)
			sendOptionsDialogue("Choose which hat to change to:", "Tier 1", "Tier 2");
		if (player.getKillCount() > 499)
			sendOptionsDialogue("Choose which hat to change to:", "Tier 1", "Tier 2", "Tier 3");
		if (player.getKillCount() > 1999)
			sendOptionsDialogue("Choose which hat to change to:", "Tier 1", "Tier 2", "Tier 3", "Tier 4");
	}

	private void removeWildStalkers(Player player) {
		for (int itemId = 20801; itemId < 20806; itemId++) {
			player.getInventory().deleteItem(itemId, 1);
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				end();
				removeWildStalkers(player);
				player.getInventory().addItem(20801, 1);
			} else if (componentId == OPTION_2) {
				end();
				removeWildStalkers(player);
				player.getInventory().addItem(20802, 1);
			} else if (componentId == OPTION_3) {
				end();
				removeWildStalkers(player);
				player.getInventory().addItem(20803, 1);
			} else if (componentId == OPTION_4) {
				end();
				removeWildStalkers(player);
				player.getInventory().addItem(20804, 1);
			}
		}

	}

	@Override
	public void finish() {

	}
}