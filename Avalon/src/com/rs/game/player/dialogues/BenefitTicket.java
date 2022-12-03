package com.rs.game.player.dialogues;

import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Rain
 * @Date - 4 Mar 2016
 *
 **/
public class BenefitTicket extends Dialogue {

	@Override
	public void start() {
		sendOptions("THANKS FOR REFERRING!", "5 days membership", "5,000,000 GP", "More coming soon!");
		stage = 1;

	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case END:
			end();
			break;
		case 1:
			switch (componentId) {
			case OPTION_1:
				player.getInventory().deleteItem(24853, 1);
				sendItemDialogueNoContinue(player, 24853, 1, "Processing...");
				player.lock();
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						sendDialogue("5 days of membership has successfully been added to your account!");
						player.member = true;
						player.unlock();
						player.makeMember(5);
						stage = END;
					}
				}, 5);
				break;
			case OPTION_2:
				player.getInventory().deleteItem(24853, 1);
				sendItemDialogueNoContinue(player, 24853, 1, "Processing...");
				player.lock();
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						sendDialogue("5,000,000 coins has successfully been added to your account.");
						player.getMoneyPouch().addMoney(5000000, false);
						player.unlock();
						stage = END;
					}
				}, 5);
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
