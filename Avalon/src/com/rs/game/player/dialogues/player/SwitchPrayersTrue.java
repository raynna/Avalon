package com.rs.game.player.dialogues.player;

import com.rs.game.player.dialogues.Dialogue;

public class SwitchPrayersTrue extends Dialogue {

	@Override
	public void start() {
		// sendDoubleItemSelection(player, new int[] { 681, 1 }, new int[] {
		// 1714, 1 }, "Curses", "Prayers");
		// components off ^
		if (!player.getPrayer().isAncientCurses()) {
			stage = 1;
			sendOptionsDialogue(TITLE, "Yes, replace my prayers with curses.", "Nevermind.");
		} else {
			stage = 2;
			sendOptionsDialogue(TITLE, "Yes, replace my curses with prayers.", "Nevermind.");
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case 1:
			switch (componentId) {
			case OPTION_1:
				sendItemDialogue(681, 1, "The altar fills your head with dark thoughts, purging the ",
						"prayers from your memory and leaving only curses in their place.");
				player.getPrayer().setPrayerBook(true);
				stage = 3;
				break;
			case OPTION_2:
				end();
				break;
			}
			break;

		case 2:
			switch (componentId) {
			case OPTION_1:
				sendItemDialogue(1714, 1, "The altar eases its grip on your mid. The curses slip from",
						"your memory and you recall the prayers you used to know.");
				player.getPrayer().setPrayerBook(false);
				stage = 3;
				break;
			case OPTION_2:
				end();
				break;
			}
			break;
		case 3:
			end();
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
