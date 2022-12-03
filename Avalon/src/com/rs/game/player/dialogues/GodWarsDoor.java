package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;

public class GodWarsDoor extends Dialogue {

	int objectId, objectX, objectY, objectZ, door;
	// door = arma[0], bandos[1], sara[2], zammy[3]

	@Override
	public void start() {
		try {
			objectId = (int) parameters[0];
			objectX = (int) parameters[1];
			objectY = (int) parameters[2];
			objectZ = (int) parameters[3];
			door = (int) parameters[4];
			sendOptionsDialogue(TITLE, (player.isMember() ? "Pay 250,000 coins." : "Pay 500,00 coins."), "Nothing.");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			switch (componentId) {
			case OPTION_1:
				boolean paid = false;
				int amount = player.isMember() ? 250000 : 500000;
				if (player.canBuy(amount))
					paid = true;
				if (paid) {
					paidFare();
				} else {
					player.getPackets().sendGameMessage("You do not have enough coins to do that.");
					stage = 2;
				}
			case OPTION_2:
				stage = 2;
				break;
			}
		}

		if (stage == 2) {
			end();
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

	void paidFare() {
		switch (objectId) {
		case 26425:// bandos door
			if (objectX == 2863 && objectY == 5354 && objectZ == 2) {
				player.setNextWorldTile(
						player.getX() == 2863 ? new WorldTile(2864, 5354, 2) : new WorldTile(2863, 5354, 2));
				return;
			}
			break;
		case 26427:// saradomin door
			if (objectX == 2908 && objectY == 5265 && objectZ == 0) {
				player.setNextWorldTile(
						player.getX() == 2908 ? new WorldTile(2907, 5265, 0) : new WorldTile(2908, 5265, 0));
				return;
			}
			break;
		case 26426:// armadyl door
			if (objectX == 2839 && objectY == 5295 && objectZ == 2) {
				player.setNextWorldTile(
						player.getY() == 5296 ? new WorldTile(2839, 5295, 2) : new WorldTile(2839, 5296, 2));
				return;
			}
			break;
		case 26428:// zammy door
			if (objectX == 2925 && objectY == 5332 && objectZ == 2) {
				player.setNextWorldTile(
						player.getY() == 5332 ? new WorldTile(2925, 5331, 2) : new WorldTile(2925, 5332, 2));
				return;
			}
			break;
		}
	}

}
