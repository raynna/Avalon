package com.rs.game.player.dialogues.skilling;

import java.util.LinkedList;
import java.util.List;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.npc.others.ServantNPC;
import com.rs.game.player.actions.skills.construction.HouseConstants;
import com.rs.game.player.dialogues.Dialogue;

public class NewServantD extends Dialogue {

	private ServantNPC servant;
	private byte page = -1;

	@Override
	public void start() {
		this.servant = (ServantNPC) parameters[0];
		servant.setFollowing(true);
		int paymentStage = player.getHouse().getPaymentStage();
		if (paymentStage == 10) {
			stageInt = 13;
			sendNPCDialogue(servant.getId(), NORMAL, "Excuse me, but before I can continue working you must pay my fee.");
		} else {
			if ((boolean) parameters[1])
				sendBeginningOption();
			else
				sendNPCDialogue(servant.getId(), NORMAL, "I am at thy command, my master");
		}
	}

	private void sendBeginningOption() {
		if (servant.getServantData().isSawmill()) {
			sendOptionsDialogue("Select an Option", "Take something to the bank", "Bring something from the bank", "Take something to the sawmill");
		} else {
			sendOptionsDialogue("Select an Option", "Take something to the bank", "Bring something from the bank");
		}
		stageInt = 9;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stageInt == -1) {
			sendOptionsDialogue("Select an Option", "Go to the bank/sawmill...", "Misc...", "Stop following me", "You're fired");
			stageInt = 2;
		} else if (stageInt == 2) {
			if (componentId == OPTION_1) {
				sendBeginningOption();
			} else if (componentId == OPTION_2) {
				sendOptionsDialogue("Select an Option", "Make tea", "Serve dinner", "Serve drinks", "Greet guests");
				stageInt = 4;
			} else if (componentId == OPTION_3) {
				sendBeginningOption();
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("Do you really want to fire your servant?", "Yes.", "No.");
				stageInt = 3;
			}
		} else if (stageInt == 3) {
			if (componentId == OPTION_1) {
				sendPlayerDialogue(NORMAL, "You are dismissed.");
				servant.fire();
			} else {
				end();
			}
			stageInt = 99;
		} else if (stageInt == 4) {
			if (componentId == OPTION_1) {
				sendNPCDialogue(servant.getId(), NORMAL, "Thou shall taste the very tea of the Demon Lords themselves!");
				stageInt = 6;
			} else if (componentId == OPTION_2) {
				sendNPCDialogue(servant.getId(), NORMAL, "I shall prepare thee a banquet fit for the lords of Pandemonium!");
				stageInt = 7;
			} else if (componentId == OPTION_3) {
				sendPlayerDialogue(NORMAL, "Serve drinks please.");
				stageInt = 8;
			} else if (componentId == OPTION_4) {
				sendPlayerDialogue(NORMAL, "Stay at the entrance and greet guests.");
				stageInt = 5;
			}
		} else if (stageInt == 5) {
			servant.setGreetGuests(true);
			servant.setFollowing(false);
			servant.setNextWorldTile(servant.getRespawnTile());
		} else if (stageInt == 6) {
			end();
			servant.makeFood(HouseConstants.TEA_BUILDS);
		} else if (stageInt == 7) {
			end();
			servant.makeFood(HouseConstants.DINNER_BUILDS);
		} else if (stageInt == 8) {
			end();
			servant.makeFood(HouseConstants.DRINKS_BUILDS);
		} else if (stageInt == 9) {
			if (componentId == OPTION_1) {
				sendNPCDialogue(servant.getId(), NORMAL, "Give any item to me and I shall take it swiftly to the bank where it will be safe from thieves and harm.");
				stageInt = 99;
			} else if (componentId == OPTION_2) {
				sendOptionsDialogue("What would you like from the bank?", getPageOptions());
			} else {
				sendNPCDialogue(servant.getId(), NORMAL, "Give me some logs and I will return as fast as possible.");
				stageInt = 99;
			}
		} else if (stageInt == 10 || stageInt == 11 || stageInt == 12) {
			if (componentId == (stageInt == 10 ? OPTION_4 : OPTION_5)) {
				sendOptionsDialogue("What would you like from the bank?", getPageOptions());
			} else {
				player.getTemporaryAttributtes().put("SERVANT_REQUEST_TYPE", 0);
				player.getTemporaryAttributtes().put("SERVANT_REQUEST_ITEM", HouseConstants.BANKABLE_ITEMS[page][componentId == 11 ? 0 : componentId - 12]);
				player.getPackets().sendInputIntegerScript(false, "How many would you like?");
				end();
			}
		} else if (stageInt == 13) {
			sendOptionsDialogue("Would you you like to pay the fee of " + servant.getServantData().getCost() + "?", "Yes", "No", "Fire.");
			stageInt = 14;
		} else if (stageInt == 14) {
			if (componentId == OPTION_1) {
				int cost = servant.getServantData().getCost();
				if (player.getInventory().getCoinsAmount() < cost) {
					sendNPCDialogue(servant.getId(), NORMAL, "You do not have enough coins to cover up my cost.");
					stageInt = 99;
					return;
				}
				player.getInventory().deleteItem(new Item(995, cost));
				player.getHouse().resetPaymentStage();
				sendNPCDialogue(servant.getId(), NORMAL, "Thank you!");
				stageInt = -1;
			} else if (componentId == OPTION_2) {
				end();
			} else if (componentId == OPTION_3) {
				sendOptionsDialogue("Do you really want to fire your servant?", "Yes.", "No.");
				stageInt = 3;
			}
		} else if (stageInt == 99) {
			end();
		}
	}

	private String[] getPageOptions() {
		List<String> options = new LinkedList<String>();
		page = (byte) (stageInt == 12 ? 0 : page + 1);
		int[] items = HouseConstants.BANKABLE_ITEMS[page];
		for (int index = 0; index < items.length; index++) {
			options.add(ItemDefinitions.getItemDefinitions(items[index]).getName());
		}
		options.add("More...");
		stageInt = (int) (page + 10);
		return options.toArray(new String[options.size()]);
	}

	@Override
	public void finish() {

	}
}
