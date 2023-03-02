package com.rs.game.player.dialogues;

import com.rs.game.player.content.TicketSystem;

public class ReferAFriend extends Dialogue {

	@Override
	public void start() {
		sendOptions(TITLE, "Create a ticket", "Refer a friend", "Titles");
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
			if (player.isRequestingChat) {
						sendDialogue("You already have a ticket that is pending. Please wait for it to be answered.");
						stage = END;
					} else {
						sendDialogue("Disclaimer! <col=ff0000>Abusing this sytem can lead to punishments.");
						stage = 3;
				}
				break;
			case OPTION_2:
				player.getPackets().sendInputLongTextScript("Enter username:");
				player.temporaryAttribute().put("refer", Boolean.TRUE);
				break;
			case OPTION_3:
				sendOptions(TITLE, "Bronze title", "Silver title", "Gold title");
				stage = 2;
			}
			break;
		case 2:
			switch (componentId) {
			case OPTION_1:
				if (player.getTotalRefs() >= 5) {
					player.getAppearence().setTitle(9001);
					end();
				} else {
					player.message("Not enough refs yet!");
					end();
				}
				break;
			case OPTION_2:
				if (player.getTotalRefs() >= 10) {
					player.getAppearence().setTitle(9002);
					end();
				} else {
					player.message("Not enough refs yet!");
					end();
				}
				break;
			case OPTION_3:
				if (player.getTotalRefs() >= 20) {
					player.getAppearence().setTitle(9003);
					end();
				} else {
					player.message("Not enough refs yet!");
					end();
				}
			}
			break;
		case 3:
			sendOptionsDialogue("Would you like to open a ticket?", "Yes please.", "No thanks");
			stage = 4;
			break;
		case 4:
			switch (componentId) {
			case OPTION_1:
				TicketSystem.openTicket(player);
				player.message("Your ticket has been sent successfully!.");
				end();
			}
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
	}

}
