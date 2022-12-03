package com.rs.game.player.dialogues.skilling;

import com.rs.game.item.Item;
import com.rs.game.player.actions.skills.cooking.DoughCooking;
import com.rs.game.player.actions.skills.cooking.DoughCooking.Cook;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.content.SkillsDialogue.ItemNameFilter;
import com.rs.game.player.dialogues.Dialogue;

public class DoughCookingD extends Dialogue {

	private Cook items;

	@Override
	public void start() {
		items = (Cook) parameters[0];
		SkillsDialogue.sendSkillsDialogue(player, SkillsDialogue.MAKE,
				message(items), 28,
				items.getProduct(), new ItemNameFilter() {
					@Override
					public String rename(String name) {
						return itemMessage(items, new Item(items.getId()), name);
					}
				});
	}
	
	public String message(Cook item) {
		return "Choose how many you wish to make<br>then click on the item to begin.";
	}


	public String itemMessage(Cook fletch, Item item, String itemName) {
		return itemName;

	}

	@Override
	public void run(int interfaceId, int componentId) {
		int option = SkillsDialogue.getItemSlot(componentId);
		if (option > items.getProduct().length) {
			end();
			return;
		}
		int quantity = SkillsDialogue.getQuantity(player);
		int invQuantity = player.getInventory().getItems().getNumberOf(items.getId());
			if (quantity > invQuantity)
				quantity = invQuantity;
		player.getActionManager().setAction(new DoughCooking(items, option, quantity));
		end();
	}

	@Override
	public void finish() {
	}

}