package com.rs.game.player.dialogues.npcs;

import com.rs.game.player.dialogues.Dialogue;

public class Monk extends Dialogue {

	/**
	 * @author Phillip - It's about 80% done still need to process money
	 *         handling but idfk how you did moneypouch -.-
	 */

	public static final int NPC = 2253;
	static final String NAME = "Brother Jared";
	private static int shield = -1;
	// 1 = blessed, 2 = spectral, 3 = arcane, 4 = elysian, 5 = divine

	private int stage;

	@Override
	public void start() {
		stage = 0;
		sendNPCDialogue(NPC, 9827, "Hello friend, what can I do for you?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case 0:
			sendOptionsDialogue("What would you like?", "I have this spirit shield...", "I forgot what I wanted...");
			break;
		case 1:
			if (componentId == OPTION_1) {
				sendPlayerDialogue(9827,
						"Well I have this spirit shield, and I don't really understand how it works exactally.");
				break;
			} else {
				end();
			}
			break;
		case 2:
			sendNPCDialogue(NPC, 9827, "That is interesting indeed, show me this shield...");
			break;
		case 3:
			sendOptionsDialogue("Which shield do you want to make?", "Blessed Spirit Shield", "Spectral Spirit Shield",
					"Arcane Spirit Shield", "Elysian Spirit Shield", "Divine Spirit Shield");
			break;
		case 4:
			if (componentId == OPTION_1) {
				if (!player.getInventory().containsItem(13734, 1) || !player.getInventory().containsItem(13754, 1)) {
					sendNPCDialogue(NPC, 9827,
							"It appears you don't have the right material for me to complete this task.");
					player.sm("You need a normal spirit shield and a holy exilir for " + NAME
							+ " to complete this task.");
				} else {
					sendNPCDialogue(NPC, 9827, "I will require a fee of 1,000,000 gold coins to do this task.");
					shield = 1;
				}
			} else if (componentId == OPTION_2) {
				if (!player.getInventory().containsItem(13736, 1) || !player.getInventory().containsItem(13752, 1)) {
					sendNPCDialogue(NPC, 9827,
							"It appears you don't have the right material for me to complete this task.");
					player.sm("You need a blessed spirit shield and a spectral sigil for " + NAME
							+ " to complete this task.");
				} else {
					sendNPCDialogue(NPC, 9827, "I will require a fee of 1,500,000 gold coins to do this task.");
					shield = 2;
				}
			} else if (componentId == OPTION_3) {// not done
				if (!player.getInventory().containsItem(13736, 1) || !player.getInventory().containsItem(13746, 1)) {
					sendNPCDialogue(NPC, 9827,
							"It appears you don't have the right material for me to complete this task.");
					player.sm("You need a blessed spirit shield and an arcane sigil for " + NAME
							+ " to complete this task.");
				} else {
					sendNPCDialogue(NPC, 9827, "I will require a fee of 1,500,000 gold coins to do this task.");
					shield = 3;
				}
			} else if (componentId == OPTION_4) {
				if (!player.getInventory().containsItem(13736, 1) || !player.getInventory().containsItem(13750, 1)) {
					sendNPCDialogue(NPC, 9827,
							"It appears you don't have the right material for me to complete this task.");
					player.sm("You need a blessed spirit shield and an elysian sigil for " + NAME
							+ " to complete this task.");
				} else {
					sendNPCDialogue(NPC, 9827, "I will require a fee of 1,500,000 gold coins to do this task.");
					shield = 4;
				}
			} else if (componentId == OPTION_5) {
				if (!player.getInventory().containsItem(13736, 1) || !player.getInventory().containsItem(13752, 1)) {
					sendNPCDialogue(NPC, 9827,
							"It appears you don't have the right material for me to complete this task.");
					player.sm("You need a blessed spirit shield and a divine sigil for " + NAME
							+ " to complete this task.");
				} else {
					sendNPCDialogue(NPC, 9827, "I will require a fee of 1,500,000 gold coins to do this task.");
					shield = 5;
				}
			}
			break;

		case 5:
			if (shield == -1)
				end();

			if (shield == 1)
				sendOptionsDialogue("Blessed Spirit Sheild", "Pay 1,000,000GP", "Nevermind");

			if (shield == 2)
				sendOptionsDialogue("Spectral Spirit Sheild", "Pay 1,500,000GP", "Nevermind");

			if (shield == 3)
				sendOptionsDialogue("Arcane Spirit Sheild", "Pay 1,500,000GP", "Nevermind");

			if (shield == 4)
				sendOptionsDialogue("Elysian Spirit Sheild", "Pay 1,500,000GP", "Nevermind");

			if (shield == 5)
				sendOptionsDialogue("Divine Spirit Sheild", "Pay 1,500,000GP", "Nevermind");
			break;

		case 6:

			if (componentId == OPTION_1) {
				if (shield == 1) {
					if (player.getMoneyPouchValue() < 1000000 && player.getInventory().getAmountOf(995) < 1000000) {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"You do not have enough money to cover this purchase!");
						shield = -1;
						end();
					}
				}

				if (shield == 2) {

				}

				if (shield == 3) {

				}

				if (shield == 4) {

				}

				if (shield == 5) {

				}

			} else if (componentId == OPTION_2) {
				end();
			}

		}

		stage++;
	}

	@Override
	public void finish() {

	}
}