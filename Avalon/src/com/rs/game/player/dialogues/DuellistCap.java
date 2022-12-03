package com.rs.game.player.dialogues;

public class DuellistCap extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Choose an option:", "Hide my current kill streak and kill/death ratio.",
				"Reset my Duel Arena kills and deaths.", "Show my current statistics.", "Nothing.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				stage = 15;
				sendDialogue("Hide kdr and ratio here.");
			} else if (componentId == OPTION_2) {
				stage = 25;
				sendOptionsDialogue("Are you sure?", "Yes, I want to reset my Duel arena kills and deaths.",
						"No, I wan't to keep it!");
			} else if (componentId == OPTION_3) {
				stage = 15;
				sendDialogue("Current stats (Duel Arena):</col><br>" + player.getDuelkillCount() + " kills, "
						+ player.getDueldeathCount() + " deaths.<br>" + "Current kills in a row: "
						+ player.getDuelkillStreak() + ".<br><br>" + "<br>");
			} else if (componentId == OPTION_4) {
				stage = 15;
			} else if (componentId == OPTION_5) {
				end();
			}
		} else if (stage == 15) {
			end();
		} else if (stage == 25) {
			stage = 15;
			player.setDuelkillCount(0);
			player.setDueldeathCount(0);
			player.setDuelkillStreak(0);
			removeDuellistCaps();
			sendDialogue("You reset your Duel arena kills and deaths.");
			player.getInventory().addItem(20795, 1);
		}
	}

	public void removeDuellistCaps() {
		for (int itemId = 20795; itemId < 20800; itemId++) {
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