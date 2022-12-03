package com.rs.game.player.dialogues;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Rain
 * @Date - 29 Feb 2016
 *
 **/
public class Potato_Commands extends Dialogue {

	@Override
	public void start() {
		if (!player.isDeveloper()) {
			sendDialogue("Hi " + player.getDisplayName()
					+ ". Please contact Tristam, letting him know how you have gotten this item. <br>Thankyou!");
			player.getFriendsIgnores().addFriend("tristam");
			stage = END;
		} else {
			sendOptions("Potato_Commands", "Player look up", "Teleport to player", "Teleport player to me",
					"Send home player", player.getAppearence().isHidden() ? "Vis: 1" : "Vis: 0");
			stage = 1;
		}

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
				player.getPackets().sendInputLongTextScript("Enter the players' username, that you wish to look up:");
				player.temporaryAttribute().put("modtool", Boolean.TRUE);
				end();
				break;
			case OPTION_2:
				player.getPackets()
						.sendInputLongTextScript("Enter the players' username, that you wish to teleport to:");
				player.temporaryAttribute().put("tp_player", Boolean.TRUE);
				end();
				break;
			case OPTION_3:
				player.getPackets()
						.sendInputLongTextScript("Enter the players' username, that you wish to teleport to you:");
				player.temporaryAttribute().put("tp_to_me", Boolean.TRUE);
				end();
				break;
			case OPTION_4:
				player.getPackets().sendInputLongTextScript("Enter the player's username, that you wish to send home.");
				player.temporaryAttribute().put("sendhome", Boolean.TRUE);
				end();
				break;
			case OPTION_5:
				player.getAppearence().switchHidden();
				player.sm(player.getAppearence().isHidden() ? "Vis: 1" : "Vis: 0");
				end();
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
