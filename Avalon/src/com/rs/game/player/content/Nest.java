package com.rs.game.player.content;

import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class Nest {

	/**
	 * @Credits CX
	 */

	public static final int[][] SEEDS = { { 5312, 5283, 5284, 5313, 5285, 5286 }, { 5314, 5288, 5287, 5315, 5289 },
			{ 5316, 5290 }, { 5317 } };
	private static final int[] RINGS = { 1635, 1637, 1639, 1641, 1643 };

	public static boolean isNest(int id) {
		return id == 5070 || id == 5071 || id == 5072 || id == 5073 || id == 5074 || id == 7413 || id == 11966;
	}

	public static void searchNest(final Player player, final int slot) {
		player.getPackets().sendGameMessage("You search the nest...and find something in it!");
		player.lock(1);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.getInventory().addItemDrop(getRewardForId(player.getInventory().getItem(slot).getId()), 1);
				player.getInventory().replaceItem(5075, 1, slot);
			}
		});
	}

	private static int getRewardForId(int id) {
		if (id == 5070)
			return 5076;
		else if (id == 11966)
			return 11964;
		else if (id == 5071)
			return 5078;
		else if (id == 5072)
			return 5077;
		else if (id == 5074)
			return RINGS[Utils.random(RINGS.length)];
		else if (id == 7413 || id == 5073) {
			double random = Utils.random(0, 100);
			final int reward = random <= 39.69 ? 0 : random <= 56.41 ? 1 : random <= 76.95 ? 2 : random <= 96.4 ? 3 : 1;
			return SEEDS[reward][Utils.random(SEEDS[reward].length)];
		}
		return -1;
	}
}
