package com.rs.game.player.dialogues;

import com.rs.game.player.Player;
import com.rs.utils.ShopsHandler;

public class LeatherTanning extends Dialogue {

	int npcId;

	public void resetHideInts() {
		greenHides = 0;
		blueHides = 0;
		redHides = 0;
		blackHides = 0;
		royalHides = 0;
	}

	int greenHides;
	int blueHides;
	int redHides;
	int blackHides;
	int royalHides;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Hello " + player.getDisplayName() + ", What can i do for you?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 1;
			sendOptionsDialogue("Choose an option", "I'd to tan some dragon hides, please.",
					"I'd like to see what you have to offer!", "Nothing, just walking by.");
		} else if (stage == 1) {
			if (componentId == OPTION_1) {
				stage = 2;
				sendPlayerDialogue(9827, "I'd to tan some dragon hides, please.");
			} else if (componentId == OPTION_2) {
				stage = 25;
				if (player.getPlayerRank().isIronman()) {
					sendNPCDialogue(2824, Sad, "Sorry, but I can't give you access to my shop.");
					stage = 5;
					return;
				}
				sendPlayerDialogue(9827, "I'd like to see what you have to offer!");
			} else if (componentId == OPTION_3) {
				end();
				sendPlayerDialogue(9827, "Nothing, just walking by.");
			}
		} else if (stage == 2) {
			stage = 3;
			sendNPCDialogue(570, 9827, "Sure thing.. one moment.");
		} else if (stage == 3) {
			stage = 4;
			sendOptionsDialogue("Choose an option", "Green D'hide (1000)", "Blue D'hide (2000)", "Red D'hide (4000)",
					"Black D'hide (6000)", "Royal D'hide (10000)");
		} else if (stage == 4) {
			if (componentId == OPTION_1) {
				if (hasGreenHides(player)) {
					tanHide("green");
					end();
				} else {
					stage = 5;
					sendNPCDialogue(npcId, 9827, "You don't have any green dragonhides.");
				}
			} else if (componentId == OPTION_2) {
				if (hasBlueHides(player)) {
					tanHide("blue");
					end();
				} else {
					stage = 5;
					sendNPCDialogue(npcId, 9827, "You don't have any blue dragonhides.");
				}
			} else if (componentId == OPTION_3) {
				if (hasRedHides(player)) {
					tanHide("red");
					end();
				} else {
					stage = 5;
					sendNPCDialogue(npcId, 9827, "You don't have any red dragonhides.");
				}
			} else if (componentId == OPTION_4) {
				if (hasBlackHides(player)) {
					tanHide("black");
					end();
				} else {
					stage = 5;
					sendNPCDialogue(npcId, 9827, "You don't have any black dragonhides.");
				}
			} else if (componentId == OPTION_5) {
				if (hasRoyalHides(player)) {
					tanHide("royal");
					end();
				} else {
					stage = 5;
					sendNPCDialogue(npcId, 9827, "You don't have any royal dragonhides.");
				}
			}
		} else if (stage == 5) {
			end();
		} else if (stage == 25) {
			end();
			ShopsHandler.openShop(player, 12);
		}
	}

	private void tanHide(String hide) {
		greenHides = player.getInventory().getNumberOf(1753);
		if (hide == "green") {
			if (player.getInventory().getNumberOf(1753) * 1000 < player.getInventory().getNumberOf(995)) {
				player.getInventory().deleteItem(1753, greenHides);
				player.getInventory().deleteItem(995, greenHides * 1000);
				player.getInventory().addItem(1745, greenHides);
				player.getPackets()
						.sendGameMessage("You tanned your " + greenHides + " green dragonhides to green d-leather");
			} else {
				player.getPackets().sendGameMessage("You do not have enough coins for that.");
				player.getPackets().sendGameMessage("Coins needed: " + greenHides * 1000);
			}
		} else if (hide == "blue") {
			blueHides = player.getInventory().getNumberOf(1751);
			if (player.getInventory().getNumberOf(1751) * 2000 < player.getInventory().getNumberOf(995)) {
				player.getInventory().deleteItem(1751, blueHides);
				player.getInventory().deleteItem(995, blueHides * 2000);
				player.getInventory().addItem(2505, blueHides);
				player.getPackets()
						.sendGameMessage("You tanned your " + blueHides + " blue dragonhides to blue d-leather");
			} else {
				player.getPackets().sendGameMessage("You do not have enough coins for that.");
				player.getPackets().sendGameMessage("Coins needed: " + blueHides * 2000);
			}
		} else if (hide == "red") {
			redHides = player.getInventory().getNumberOf(1749);
			if (player.getInventory().getNumberOf(1749) * 4000 < player.getInventory().getNumberOf(995)) {
				player.getInventory().deleteItem(1749, redHides);
				player.getInventory().deleteItem(995, redHides * 4000);
				player.getInventory().addItem(2507, redHides);
				player.getPackets()
						.sendGameMessage("You tanned your " + redHides + " red dragonhides to red d-leather");
			} else {
				player.getPackets().sendGameMessage("You do not have enough coins for that.");
				player.getPackets().sendGameMessage("Coins needed: " + redHides * 4000);
			}
		} else if (hide == "black") {
			blackHides = player.getInventory().getNumberOf(1747);
			if (player.getInventory().getNumberOf(1747) * 6000 < player.getInventory().getNumberOf(995)) {
				player.getInventory().deleteItem(1747, blackHides);
				player.getInventory().deleteItem(995, blackHides * 6000);
				player.getInventory().addItem(2509, blackHides);
				player.getPackets()
						.sendGameMessage("You tanned your " + blackHides + " black dragonhides to black d-leather");
			} else {
				player.getPackets().sendGameMessage("You do not have enough coins for that.");
				player.getPackets().sendGameMessage("Coins needed: " + blackHides * 6000);
			}
		} else if (hide == "royal") {
			royalHides = player.getInventory().getNumberOf(24372);
			if (player.getInventory().getNumberOf(24372) * 10000 < player.getInventory().getNumberOf(995)) {
				player.getInventory().deleteItem(24372, royalHides);
				player.getInventory().deleteItem(995, royalHides * 10000);
				player.getInventory().addItem(24374, royalHides);
				player.getPackets()
						.sendGameMessage("You tanned your " + royalHides + " royal dragonhides to royal d-leather");
			} else {
				player.getPackets().sendGameMessage("You do not have enough coins for that.");
				player.getPackets().sendGameMessage("Coins needed: " + royalHides * 10000);
			}
		}
	}

	private boolean hasGreenHides(Player player) {
		if (player.getInventory().containsItem(1753, 1))
			return true;
		return false;
	}

	private boolean hasBlueHides(Player player) {
		if (player.getInventory().containsItem(1751, 1))
			return true;
		return false;
	}

	private boolean hasRedHides(Player player) {
		if (player.getInventory().containsItem(1749, 1))
			return true;
		return false;
	}

	private boolean hasBlackHides(Player player) {
		if (player.getInventory().containsItem(1747, 1))
			return true;
		return false;
	}

	private boolean hasRoyalHides(Player player) {
		if (player.getInventory().containsItem(24372, 1))
			return true;
		return false;
	}

	@Override
	public void finish() {

	}

}
