package com.rs.game.player.dialogues.skilling;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.WorldObject;
import com.rs.game.item.Item;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.crafting.SpinningWheel;
import com.rs.game.player.actions.skills.crafting.SpinningWheel.Products;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.content.SkillsDialogue.ItemNameFilter;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class SpinningWheelD extends Dialogue {

	private WorldObject object;

	@Override
	public void start() {
		object = (WorldObject) parameters[0];
		int[] ids = new int[Products.values().length];
		for (int i = 0; i < ids.length; i++)
			ids[i] = Products.values()[i].getProducedItem().getId();
		SkillsDialogue.sendSkillsDialogue(player, SkillsDialogue.MAKE,
				"Choose how many you wish to make,<br>then click on the item to begin.", 28, ids, new ItemNameFilter() {
					int count = 0;

					@Override
					public String rename(String name) {
						Products prod = Products.values()[count++];
						if (player.getSkills().getLevel(Skills.CRAFTING) < prod.getLevelRequired())
							name = "<col=ff0000>" + name + "<br><col=ff0000>Level " + prod.getLevelRequired();
						else
							name = itemMessage(prod, new Item(prod.getProducedItem()), name);
						return name;

					}
				});
	}

	public String itemMessage(Products products, Item item, String itemName) {
		String itemReq = ItemDefinitions.getItemDefinitions(products.getItemsRequired().getId()).getName()
				.toLowerCase();
		return itemName + "<br>("
				+ (products.getItemsRequired().getAmount() > 1 ? products.getItemsRequired().getAmount() + " " + itemReq : "")
				+ Utils.fixChatMessage(itemReq) + ")";
	}

	@Override
	public void run(int interfaceId, int componentId) {
		player.getActionManager().setAction(
				new SpinningWheel(SkillsDialogue.getItemSlot(componentId), object, SkillsDialogue.getQuantity(player)));
		end();
	}

	@Override
	public void finish() {
	}
}
