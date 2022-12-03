package com.rs.game.player.dialogues;

import java.text.DecimalFormat;

import com.rs.utils.Utils;

public class WildStalkerHelmet extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Choose an option:", "Show my current kill streak and kill/death ratio.",
				"Reset my Wilderness kills and deaths.", "Reset my highest value Wilderness kill.",
				"Show my current statistics.", "Nothing.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		int ks = player.killStreak;
		int ksr = player.killStreakRecord;
		double kills = player.getKillCount();
		double deaths = player.getDeathCount();
		double dr = (kills / deaths);
		if (stage == -1) {
			if (componentId == OPTION_1) {
				stage = 15;
				sendDialogue("Kill streak & Kill/Death ratio (KDR):</col><br>" + "Current kills in a row: " + ks
						+ ".<br>" + "Current kills in a row record: " + ksr + ".<br>" + "Current total kills: "
						+ player.getKillCount() + ".<br>" + "Current total deaths: " + player.getDeathCount()
						+ ".<br>".replace(".0", "") + "Current ratio: " + new DecimalFormat("##.#").format(dr)
						+ ".<br>");
			} else if (componentId == OPTION_2) {
				stage = 25;
				sendOptionsDialogue("Are you sure?", "Yes, I want to reset my Wilderness kills and deaths.",
						"No, I wan't to keep it!");
			} else if (componentId == OPTION_3) {
				stage = 30;
				sendOptionsDialogue("Are you sure?", "Yes, I want to reset my highest value Wilderness kill.",
						"No, I wan't to keep it!");
			} else if (componentId == OPTION_4) {
				stage = 15;
				sendDialogue("Current stats (Wilderness):</col><br>" + player.killCount + " kills, " + player.deathCount
						+ " deaths.<br>" + "Current kills in a row: " + ks + ".<br>" + "Current kills in a row record: "
						+ ksr + ".<br>" + "Most valuable kill: "
						+ (player.getHighestValuedKill() == Integer.MAX_VALUE ? "Lots!"
								: Utils.getFormattedNumber(player.getHighestValuedKill(), ',') + ".")
						+ "<br>" + "<br>");
			} else if (componentId == OPTION_5) {
				end();
			}
		} else if (stage == 15) {
			end();
		} else if (stage == 25) {
			stage = 15;
			player.setKillCount(0);
			player.setDeathCount(0);
			player.killStreak = 0;
			removeWildStalkers();
			sendDialogue("You reset your Wilderness kills and deaths.");
			player.getInventory().addItem(20801, 1);
		} else if (stage == 30) {
			stage = 15;
			player.setHighestValuedKill(0);
			player.reachedMaxValue = false;
			sendDialogue("You reset your highest value Wilderness kill.");
		}
	}

	public void removeWildStalkers() {
		for (int itemId = 20801; itemId < 20806; itemId++) {
			player.getInventory().deleteItem(itemId, 1);
			int[] bankItem = player.getBank().getItemSlot(itemId);
			if (bankItem != null)
			player.getBank().removeItem(bankItem, 1, true, false);
		}
	}

	@Override
	public void finish() {

	}
}