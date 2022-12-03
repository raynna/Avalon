package com.rs.game.player.dialogues;

/*Andreas*/

public class ColourChanger extends Dialogue {

	public ColourChanger() {
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

			}
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
			}
		} else if (stage == 20) {// Ancient Staff
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(4675, 1)) {
					player.getInventory().deleteItem(4675, 1);
					player.getInventory().addItem(24092, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(4675, 1)) {
					player.getPackets().sendGameMessage("You don't have an ancient staff to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(4675, 1)) {
					player.getInventory().deleteItem(4675, 1);
					player.getInventory().addItem(24094, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(4675, 1)) {
					player.getPackets().sendGameMessage("You don't have an ancient staff to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(4675, 1)) {
					player.getInventory().deleteItem(4675, 1);
					player.getInventory().addItem(24096, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(4675, 1)) {
					player.getPackets().sendGameMessage("You don't have an ancient staff to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_4) {
				if (player.getInventory().containsItem(4675, 1)) {
					player.getInventory().deleteItem(4675, 1);
					player.getInventory().addItem(24098, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(4675, 1)) {
					player.getPackets().sendGameMessage("You don't have an ancient staff to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			}
		} else if (stage == 21) {// Staff of Light
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(15486, 1)) {
					player.getInventory().deleteItem(15486, 1);
					player.getInventory().addItem(22207, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(15486, 1)) {
					player.getPackets().sendGameMessage("You don't have an staff of light to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(15486, 1)) {
					player.getInventory().deleteItem(15486, 1);
					player.getInventory().addItem(22209, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(15486, 1)) {
					player.getPackets().sendGameMessage("You don't have an staff of light to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(15486, 1)) {
					player.getInventory().deleteItem(15486, 1);
					player.getInventory().addItem(22211, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(15486, 1)) {
					player.getPackets().sendGameMessage("You don't have an staff of light to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_4) {
				if (player.getInventory().containsItem(15486, 1)) {
					player.getInventory().deleteItem(15486, 1);
					player.getInventory().addItem(22213, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(15486, 1)) {
					player.getPackets().sendGameMessage("You don't have an staff of light to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			}
		} else if (stage == 22) {// Abyssal Whip
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(4151, 1)) {
					player.getInventory().deleteItem(4151, 1);
					player.getInventory().addItem(15441, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(4151, 1)) {
					player.getPackets().sendGameMessage("You don't have an abyssal whip to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(4151, 1)) {
					player.getInventory().deleteItem(4151, 1);
					player.getInventory().addItem(15442, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(4151, 1)) {
					player.getPackets().sendGameMessage("You don't have an abyssal whip to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(4151, 1)) {
					player.getInventory().deleteItem(4151, 1);
					player.getInventory().addItem(15443, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(4151, 1)) {
					player.getPackets().sendGameMessage("You don't have an abyssal whip to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_4) {
				if (player.getInventory().containsItem(4151, 1)) {
					player.getInventory().deleteItem(4151, 1);
					player.getInventory().addItem(15444, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(4151, 1)) {
					player.getPackets().sendGameMessage("You don't have an abyssal whip to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			}
		} else if (stage == 23) {// Dark bow
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(11235, 1)) {
					player.getInventory().deleteItem(11235, 1);
					player.getInventory().addItem(15701, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(11235, 1)) {
					player.getPackets().sendGameMessage("You don't have a dark bow to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(11235, 1)) {
					player.getInventory().deleteItem(11235, 1);
					player.getInventory().addItem(15702, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(11235, 1)) {
					player.getPackets().sendGameMessage("You don't have a dark bow to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(11235, 1)) {
					player.getInventory().deleteItem(11235, 1);
					player.getInventory().addItem(15703, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(11235, 1)) {
					player.getPackets().sendGameMessage("You don't have a dark bow to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_4) {
				if (player.getInventory().containsItem(11235, 1)) {
					player.getInventory().deleteItem(11235, 1);
					player.getInventory().addItem(15704, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(11235, 1)) {
					player.getPackets().sendGameMessage("You don't have a dark bow to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			}
		} else if (stage == 24) {// Top Hat
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(13101, 1)) {
					player.getInventory().deleteItem(13101, 1);
					player.getInventory().addItem(24108, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(13101, 1)) {
					player.getPackets().sendGameMessage("You don't have a top hat to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(13101, 1)) {
					player.getInventory().deleteItem(13101, 1);
					player.getInventory().addItem(24110, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(13101, 1)) {
					player.getPackets().sendGameMessage("You don't have a top hat to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(13101, 1)) {
					player.getInventory().deleteItem(13101, 1);
					player.getInventory().addItem(24112, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(13101, 1)) {
					player.getPackets().sendGameMessage("You don't have a top hat to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_4) {
				if (player.getInventory().containsItem(13101, 1)) {
					player.getInventory().deleteItem(13101, 1);
					player.getInventory().addItem(24114, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(13101, 1)) {
					player.getPackets().sendGameMessage("You don't have a top hat to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			}
		} else if (stage == 25) {// Robin Hood
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(2581, 1)) {
					player.getInventory().deleteItem(2581, 1);
					player.getInventory().addItem(20949, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(2581, 1)) {
					player.getPackets().sendGameMessage("You don't have a robin hood hat to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(2581, 1)) {
					player.getInventory().deleteItem(2581, 1);
					player.getInventory().addItem(20950, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(2581, 1)) {
					player.getPackets().sendGameMessage("You don't have a robin hood hat to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(2581, 1)) {
					player.getInventory().deleteItem(2581, 1);
					player.getInventory().addItem(20951, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(2581, 1)) {
					player.getPackets().sendGameMessage("You don't have a robin hood hat to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_4) {
				if (player.getInventory().containsItem(2581, 1)) {
					player.getInventory().deleteItem(2581, 1);
					player.getInventory().addItem(20952, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(2581, 1)) {
					player.getPackets().sendGameMessage("You don't have a robin hood hat to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			}
		} else if (stage == 26) {// Mages' book
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(6889, 1)) {
					player.getInventory().deleteItem(6889, 1);
					player.getInventory().addItem(24100, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6889, 1)) {
					player.getPackets().sendGameMessage("You don't have a mages' book to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(6889, 1)) {
					player.getInventory().deleteItem(6889, 1);
					player.getInventory().addItem(24102, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6889, 1)) {
					player.getPackets().sendGameMessage("You don't have a mages' book to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(6889, 1)) {
					player.getInventory().deleteItem(6889, 1);
					player.getInventory().addItem(24104, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6889, 1)) {
					player.getPackets().sendGameMessage("You don't have a mages' book to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_4) {
				if (player.getInventory().containsItem(6889, 1)) {
					player.getInventory().deleteItem(6889, 1);
					player.getInventory().addItem(24106, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6889, 1)) {
					player.getPackets().sendGameMessage("You don't have a mages' book to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			}
		} else if (stage == 27) {// Infinity Robes Choose
			if (componentId == OPTION_1) {
				stage = 28;// Hat
				sendOptionsDialogue("What Colour (Infinity Hat)", "White", "Blue", "Red", "Brown");
			} else if (componentId == OPTION_2) {
				stage = 29;// Body
				sendOptionsDialogue("What Colour (Infinity Top)", "White", "Blue", "Red", "Brown");
			} else if (componentId == OPTION_3) {
				stage = 30;// Bottom
				sendOptionsDialogue("What Colour (Infinity Bottom)", "White", "Blue", "Red", "Brown");
			}
		} else if (stage == 28) {// Hat
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(6918, 1)) {
					player.getInventory().deleteItem(6918, 1);
					player.getInventory().addItem(15602, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6918, 1)) {
					player.getPackets().sendGameMessage("You don't have an infinity hat to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(6918, 1)) {
					player.getInventory().deleteItem(6918, 1);
					player.getInventory().addItem(15608, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6918, 1)) {
					player.getPackets().sendGameMessage("You don't have an infinity hat to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(6918, 1)) {
					player.getInventory().deleteItem(6918, 1);
					player.getInventory().addItem(15614, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6918, 1)) {
					player.getPackets().sendGameMessage("You don't have an infinity hat to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_4) {
				if (player.getInventory().containsItem(6918, 1)) {
					player.getInventory().deleteItem(6918, 1);
					player.getInventory().addItem(15620, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6918, 1)) {
					player.getPackets().sendGameMessage("You don't have an infinity hat to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			}
		} else if (stage == 29) {// Body
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(6916, 1)) {
					player.getInventory().deleteItem(6916, 1);
					player.getInventory().addItem(15600, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6916, 1)) {
					player.getPackets().sendGameMessage("You don't have an infinity robe top to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(6916, 1)) {
					player.getInventory().deleteItem(6916, 1);
					player.getInventory().addItem(15606, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6916, 1)) {
					player.getPackets().sendGameMessage("You don't have an infinity robe top to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(6916, 1)) {
					player.getInventory().deleteItem(6916, 1);
					player.getInventory().addItem(15612, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6916, 1)) {
					player.getPackets().sendGameMessage("You don't have an infinity robe top to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_4) {
				if (player.getInventory().containsItem(6916, 1)) {
					player.getInventory().deleteItem(6916, 1);
					player.getInventory().addItem(15618, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6916, 1)) {
					player.getPackets().sendGameMessage("You don't have an infinity robe top to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			}
		} else if (stage == 30) {// Bottom
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(6924, 1)) {
					player.getInventory().deleteItem(6924, 1);
					player.getInventory().addItem(15604, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6924, 1)) {
					player.getPackets().sendGameMessage("You don't have an infinity robe bottom to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(6924, 1)) {
					player.getInventory().deleteItem(6924, 1);
					player.getInventory().addItem(15610, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6924, 1)) {
					player.getPackets().sendGameMessage("You don't have an infinity robe bottom to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(6924, 1)) {
					player.getInventory().deleteItem(6924, 1);
					player.getInventory().addItem(15616, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6924, 1)) {
					player.getPackets().sendGameMessage("You don't have an infinity robe bottom to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			} else if (componentId == OPTION_4) {
				if (player.getInventory().containsItem(6924, 1)) {
					player.getInventory().deleteItem(6924, 1);
					player.getInventory().addItem(15622, 1);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (!player.getInventory().containsItem(6924, 1)) {
					player.getPackets().sendGameMessage("You don't have an infinity robe bottom to recolour");
					player.getInterfaceManager().closeChatBoxInterface();
				}
			}
		}
	}

	@Override
	public void finish() {
	}

}