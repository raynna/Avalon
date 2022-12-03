package com.rs.game.player.dialogues.player;

import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.content.SkillsDialogue.ItemNameFilter;
import com.rs.game.player.content.customshops.CombatStoreDialogue;
import com.rs.game.player.content.customshops.CombatStoreDialogue.CombatStores;
import com.rs.game.player.dialogues.Dialogue;

public class StoreD extends Dialogue {

	@Override
	public void start() {
		int[] ids = new int[CombatStores.values().length];
		for (int i = 0; i < ids.length; i++)
			ids[i] = CombatStores.values()[i].getShowItem().getId();
		SkillsDialogue.sendStoreDialogue(player,
				"Click on the alternative stores down below.", ids, new ItemNameFilter() {
					int count = 0;

					@Override
					public String rename(String name) {
						CombatStores shop = CombatStores.values()[count++];
						return shop.name().replace("_", " ");

					}
				});
	}


	@Override
	public void run(int interfaceId, int componentId) {
		player.getActionManager().setAction(
				new CombatStoreDialogue(SkillsDialogue.getItemSlot(componentId), SkillsDialogue.getQuantity(player)));
		end();
	}

	@Override
	public void finish() {
	}
}
