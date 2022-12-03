package com.rs.game.player.dialogues.skilling;

import com.rs.game.player.actions.skills.crafting.GanodermicCrafting;
import com.rs.game.player.actions.skills.crafting.GanodermicCrafting.ArmourData;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.dialogues.Dialogue;

public class GanodermicCraftingD extends Dialogue {

	@Override
	public void start() {
		int[] items = new int[parameters.length];
		for (int i = 0; i < items.length; i++)
			items[i] = ((ArmourData) parameters[i]).getFinalProduct();

		SkillsDialogue.sendSkillsDialogue(player, SkillsDialogue.MAKE,
				"Choose how many you wish to make,<br>then click on the item to begin.", 28, items, null);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		int option = SkillsDialogue.getItemSlot(componentId);
		if (option > parameters.length) {
			end();
			return;
		}
		ArmourData data = (ArmourData) parameters[option];
		int quantity = SkillsDialogue.getQuantity(player);
		int invQuantity = player.getInventory().getItems().getNumberOf(data.getItemId());
		if (quantity > invQuantity)
			quantity = invQuantity;
		player.getActionManager().setAction(new GanodermicCrafting(data, quantity));
		end();
	}

	@Override
	public void finish() {

	}

}
