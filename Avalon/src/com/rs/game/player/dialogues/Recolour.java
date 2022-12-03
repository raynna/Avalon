package com.rs.game.player.dialogues;

/*Andreas*/

public class Recolour extends Dialogue {

	public Recolour() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("What item do you wanna recolour?", "Ancient Staff", "Staff of Light", "Abyssal Whip",
				"Dark bow", "Next Page");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				stage = 20;// Ancient staff
				sendOptionsDialogue("What Colour (Ancient Staff)", "Red", "Blue", "Green", "Yellow");
			} else if (componentId == OPTION_2) {
				stage = 21;// Staff of Light
				sendOptionsDialogue("What Colour (Staff of Light)", "Red", "Yellow", "Blue", "Green");
			} else if (componentId == OPTION_3) {
				stage = 22;// Abyssal whip
				sendOptionsDialogue("What Colour (Abyssal Whip)", "Yellow", "Blue", "White", "Green");
			} else if (componentId == OPTION_4) {
				stage = 23;// Dark bow
				sendOptionsDialogue("What Colour (Dark Bow)", "Yellow", "Blue", "White", "Green");
			} else if (componentId == OPTION_5) {
				stage = 2;// next page
				sendOptionsDialogue("What item do you wanna recolour?", "Top Hat", "Robin hood hat", "Mages' book",
						"Infinity Robes", "No Thanks.");

			} else if (stage == 2) {
				if (componentId == OPTION_1) {
					stage = 24;// Top hat
					sendOptionsDialogue("What Colour (Top Hat)", "Red", "Green", "Blue", "White");
				} else if (componentId == OPTION_2) {
					stage = 25;// Robin hood
					sendOptionsDialogue("What Colour (Robin Hood)", "Red", "Yellow", "Blue", "White");
				} else if (componentId == OPTION_3) {
					stage = 26;// Mages' book
					sendOptionsDialogue("What Colour (Mages' book)", "Blue", "Green", "Gold", "White");
				} else if (componentId == OPTION_4) {
					stage = 27;// Infinity robes
					sendOptionsDialogue("What Colour (Infinity)", "Hat", "Top", "Legs");
				} else if (componentId == OPTION_5) {// exit
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (stage == 20) {// Ancient Staff
					if (componentId == OPTION_1) {
						if (player.getInventory().containsItem(4675, 1)) {
							player.getInventory().deleteItem(4675, 1);
							player.getInventory().deleteItem(24092, 1);
							player.getInterfaceManager().closeChatBoxInterface();
						} else if (!player.getInventory().containsItem(4675, 1)) {
							player.getPackets().sendGameMessage("You don't have an ancient staff to recolour");
							player.getInterfaceManager().closeChatBoxInterface();
						}
					} else if (componentId == OPTION_2) {
						if (player.getInventory().containsItem(4675, 1)) {
							player.getInventory().deleteItem(4675, 1);
							player.getInventory().deleteItem(24094, 1);
							player.getInterfaceManager().closeChatBoxInterface();
						} else if (!player.getInventory().containsItem(4675, 1)) {
							player.getPackets().sendGameMessage("You don't have an ancient staff to recolour");
							player.getInterfaceManager().closeChatBoxInterface();
						}
					} else if (componentId == OPTION_3) {
						if (player.getInventory().containsItem(4675, 1)) {
							player.getInventory().deleteItem(4675, 1);
							player.getInventory().deleteItem(24096, 1);
							player.getInterfaceManager().closeChatBoxInterface();
						} else if (!player.getInventory().containsItem(4675, 1)) {
							player.getPackets().sendGameMessage("You don't have an ancient staff to recolour");
							player.getInterfaceManager().closeChatBoxInterface();
						}
					} else if (componentId == OPTION_4) {
						if (player.getInventory().containsItem(4675, 1)) {
							player.getInventory().deleteItem(4675, 1);
							player.getInventory().deleteItem(24098, 1);
							player.getInterfaceManager().closeChatBoxInterface();
						} else if (!player.getInventory().containsItem(4675, 1)) {
							player.getPackets().sendGameMessage("You don't have an ancient staff to recolour");
							player.getInterfaceManager().closeChatBoxInterface();
						}
					}
				}
			}
		}
	}

	@Override
	public void finish() {
	}

}