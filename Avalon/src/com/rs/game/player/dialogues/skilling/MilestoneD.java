package com.rs.game.player.dialogues.skilling;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.actions.skills.crafting.MilestoneCapes;
import com.rs.game.player.actions.skills.crafting.MilestoneCapes.Products;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.content.SkillsDialogue.ItemNameFilter;
import com.rs.game.player.dialogues.Dialogue;

public class MilestoneD extends Dialogue {

	@Override
	public void start() {
		int[] ids = new int[Products.values().length];
		for (int i = 0; i < ids.length; i++)
			ids[i] = Products.values()[i].getProducedItem().getId();
		SkillsDialogue.sendSkillsDialogue(player, SkillsDialogue.MAKE,
				"Choose how many you wish to make,<br>then click on the item to begin.", 28, ids, new ItemNameFilter() {
					int count = 0;

					@Override
					public String rename(String name) {
						Products prod = Products.values()[count++];
						return itemMessage(prod, new Item(prod.getProducedItem()), name);

					}
				});
	}

	public String itemMessage(Products products, Item item, String itemName) {
		String required = ItemDefinitions.getItemDefinitions(products.getItemsRequired().getId()).getName()
				.toLowerCase();
		return itemName + "<br>(" + products.getItemsRequired().getAmount() + " " + required + ")";

	}

	@Override
	public void run(int interfaceId, int componentId) {
		player.getActionManager().setAction(
				new MilestoneCapes(SkillsDialogue.getItemSlot(componentId), SkillsDialogue.getQuantity(player)));
		end();
	}

	@Override
	public void finish() {
	}
}
