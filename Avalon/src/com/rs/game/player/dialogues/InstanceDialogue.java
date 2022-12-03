package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Rain
 * @Date - 3 Mar 2016
 *
 **/
public class InstanceDialogue extends Dialogue {

	@Override
	public void start() {
		sendOptions(TITLE, "Enter room", "Buy an instance");
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
				player.setNextWorldTile(
						player.getX() == 2863 ? new WorldTile(2864, 5354, 2) : new WorldTile(2863, 5354, 2));
				end();
				break;
			case OPTION_2:
				if (!player.hasMoney(10000000)) {
					sendDialogue("You don't have enough money, you need 10m to buy an instance.");
					stage = END;
				} else {
					end();
					player.getMoneyPouch().removeMoneyMisc(10000000);
					player.IsInInstance = true;
					player.getControlerManager().startControler("Instance", player);
				}
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
