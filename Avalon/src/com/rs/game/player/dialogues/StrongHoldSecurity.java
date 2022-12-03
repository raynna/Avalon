package com.rs.game.player.dialogues;

import com.rs.cache.loaders.ItemDefinitions;

public class StrongHoldSecurity extends Dialogue {

	private int floorId;

	@Override
	public void start() {
		sendDialogue("You have already claimed this reward.");
		floorId = (Integer) parameters[0];
		if (floorId == 1) {
			if (player.strongHoldSecurityFloor1) {
				stageName = "end";
				sendDialogue("You have already claimed this reward.");
			} else {
				stageName = "floor1.0";
				sendDialogue("The box hinges creak and appear to form audible words....");
			}
		} else if (floorId == 2) {
			if (player.strongHoldSecurityFloor2) {
				stageName = "end";
				sendDialogue("You have already claimed this reward.");
				return;
			}
			stageName = "floor2.0";
			sendDialogue("The grain shifts in the sack, sighing audible words....");
		} else if (floorId == 3) {
			if (player.strongHoldSecurityFloor3) {
				stageName = "end";
				sendDialogue("You have already claimed this reward.");
				return;
			}
			stageName = "floor3.0";
			sendDialogue("The box hinges creak and appear to form audible words....");
		} else if (floorId == 4) {
			if (player.strongHoldSecurityFloor4) {
				stageName = "end";
				sendDialogue("You have already claimed this reward.");
				return;
			}
			stageName = "floor4.0";
			sendDialogue("As your hands touches the cradle, you hear the voices of a million dead adventurers...");
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stageName == "floor1.0") {
			stageName = "end";
			player.getMoneyPouch().addMoney(2000, false);
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(995).name,
							"...Congratulations adventurer, you have been deemed worthy of this reward." },
					IS_ITEM, 1003, -1);
			player.strongHoldSecurityFloor1 = true;
		} else if (stageName == "floor2.0") {
			stageName = "end";
			player.getMoneyPouch().addMoney(3000, false);
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(995).name,
							"...Congratulations adventurer, you have been deemed worthy of this reward." },
					IS_ITEM, 1003, -1);
			player.strongHoldSecurityFloor2 = true;
		} else if (stageName == "floor3.0") {
			stageName = "end";
			player.getMoneyPouch().addMoney(5000, false);
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(995).name,
							"...Congratulations adventurer, you have been deemed worthy of this reward." },
					IS_ITEM, 1003, -1);
			player.strongHoldSecurityFloor3 = true;
		} else if (stageName == "floor4.0") {
			stageName = "floor4.1";
			sendDialogue("Welcome, adventurer... you have a choice.");
		} else if (stageName == "floor4.1") {
			stageName = "floor4.2";
			sendEntityDialogue(SEND_ITEM_DIALOGUE, new String[] { ItemDefinitions.getItemDefinitions(9005).name,
					"You can choose between these pairs of boots." }, IS_ITEM, 9005, -1);
		} else if (stageName == "floor4.2") {
			stageName = "floor4.3";
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(9006).name, "or these pairs of boots." }, IS_ITEM,
					9006, -1);
		} else if (stageName == "floor4.3") {
			stageName = "floor4.4";
			sendDialogue("They will both protect your feet in exactly the same manner;",
					"however, they look very different. You can always come back and",
					"get another pair if you lose them, or even swap them for the other", "style!");
		} else if (stageName == "floor4.4") {
			stageName = "floor4.5";
			sendOptionsDialogue("Choose your style of boots.", "I'll take the colourful ones.",
					"I'll take the fighting ones.");
		} else if (stageName == "floor4.5") {
			if (componentId == OPTION_1) {
				stageName = "colourful1.0";
				sendPlayerDialogue(Happy, "I'll take the colourful ones.");
			} else if (componentId == OPTION_2) {
				stageName = "fighting1.0";
				sendPlayerDialogue(Happy, "I'll take the fighting ones.");
			}
		} else if (stageName == "colourful1.0") {
			if (!player.getInventory().hasFreeSlots()) {
				stageName = "end";
				sendDialogue("You don't have any inventory space.");
				return;
			}
			stageName = "end";
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(9005).name,
							"Congratulations! You have successfully navigated the Stronghold of Security and claimed your reward." },
					IS_ITEM, 9005, -1);
			player.getInventory().addItem(9005, 1);
			player.strongHoldSecurityFloor4 = true;
		} else if (stageName == "fighting1.0") {
			if (!player.getInventory().hasFreeSlots()) {
				stageName = "end";
				sendDialogue("You don't have any inventory space.");
				return;
			}
			stageName = "end";
			sendEntityDialogue(SEND_ITEM_DIALOGUE,
					new String[] { ItemDefinitions.getItemDefinitions(9006).name,
							"Congratulations! You have successfully navigated the Stronghold of Security and claimed your reward." },
					IS_ITEM, 9006, -1);
			player.getInventory().addItem(9006, 1);
			player.strongHoldSecurityFloor4 = true;
		} else if (stageName == "end")
			end();

	}

	@Override
	public void finish() {

	}
}