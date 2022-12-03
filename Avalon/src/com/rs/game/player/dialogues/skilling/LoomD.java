package com.rs.game.player.dialogues.skilling;

import com.rs.game.WorldObject;
import com.rs.game.item.Item;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.crafting.Loom;
import com.rs.game.player.actions.skills.crafting.Loom.Products;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.content.SkillsDialogue.ItemNameFilter;
import com.rs.game.player.dialogues.Dialogue;

public class LoomD extends Dialogue {

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
		String defs = itemName.toLowerCase();
		if (defs.contains("sack"))
			return itemName.replace("Empty s", "S") + "<br>(4 jute fibres)";
		if (defs.contains("basket"))
			return itemName + "<br>(6 willow branches)";
		if (defs.contains("cloth"))
			return itemName.replace("Strip of c", "C") + "<br>(2 balls of wool)";
		if (defs.contains("net"))
			return itemName.replace("Unfinished", "Seaweed") + "<br>(5 flax)";
		if (defs.contains("milestone"))
			return itemName.replace(" (10)", "s") + "<br>(balls of wool)";
		return itemName;

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (componentId == 18) {
			player.getDialogueManager().startDialogue("MilestoneD");
			return;
		}
		player.getActionManager().setAction(
				new Loom(SkillsDialogue.getItemSlot(componentId), object, SkillsDialogue.getQuantity(player)));
		end();
	}

	@Override
	public void finish() {
	}
}
