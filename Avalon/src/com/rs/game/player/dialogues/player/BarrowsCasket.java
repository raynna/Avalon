package com.rs.game.player.dialogues.player;

import com.rs.game.item.Item;
import com.rs.game.player.dialogues.Dialogue;

public class BarrowsCasket extends Dialogue {

	private int slotId;
	private Item clickedItem;
	
	@Override
	public void start() {
		this.slotId = (int) parameters[0];
		this.clickedItem = (Item) parameters[1];
		Item item = player.getBarrowsRewards().get(0);
		Item item2 = player.getBarrowsRewards().get(1);
		Item item3 = player.getBarrowsRewards().get(2);
		sendOptionsDialogue("Barrows rewards", item.getName(), item2.getName(), item3.getName());
	}

	@Override
	public void run(int interfaceId, int componentId) {
		Item item = player.getBarrowsRewards().get(0);
		Item item2 = player.getBarrowsRewards().get(1);
		Item item3 = player.getBarrowsRewards().get(2);
		if (stage == -1) {
			switch (componentId) {
			case OPTION_1:
				player.getInventory().deleteItem(slotId, clickedItem);
				player.getInventory().addItem(item.getId(), item.getAmount());
				player.getBarrowsRewards().clear();
				end();
				break;
			case OPTION_2:
				player.getInventory().deleteItem(slotId, clickedItem);
				player.getInventory().addItem(item2.getId(), item2.getAmount());
				player.getBarrowsRewards().clear();
				end();
				break;
			case OPTION_3:
				player.getInventory().deleteItem(slotId, clickedItem);
				player.getInventory().addItem(item3.getId(), item3.getAmount());
				player.getBarrowsRewards().clear();
				end();
				break;
			}
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

	void action(String action) {
		switch (action.toLowerCase().replace("option-", "")) {
		case "agility":
			// break;
		case "cooking":
			// break;
		case "crafting":
			// break;
		case "fishing":
			// break;
		case "farming":
			// break;
		case "hunter":
			// break;
		case "mining":
			// break;
		case "runecrafting":
			// break;
		case "summoning":
			// break;
		case "thieving":
			// break;
		case "woodcutting":
			player.sm("Skilling Teleports not avalible at this time, check back later.");
			break;
		default:
			break;
		}
		end();
	}

	// RuneCrafting - ("Tip: You can talk to the Mage of Zamorak located level 5
	// wilderness to teleport you to the Abyss")
	// Agility - Teleports { }
	// Thieving - Teleports { }
	// Crafting - ("Tip: You can make skilling urns with the Pottery Wheel near
	// the Varrock East Bank by Aubury.")
	// Mining - Teleports { }
	// Fishing - Teleports { }
	// Cooking ("Tip: A good place to cook food is Catherby.")
	// WoodCutting - Teleports { }
	// Farming ("Farming is not yet introduced into the game, coming soon.")
	// Summoning - Teleport to Neitzinot
	// Hunter ("Hunter is not yet introduced into the game, coming soon.")

}
