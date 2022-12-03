package com.rs.game.player.dialogues.player;

import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.content.SkillsDialogue.ItemNameFilter;
import com.rs.game.player.content.customshops.MiscStoreDialogue;
import com.rs.game.player.content.customshops.MiscStoreDialogue.MiscStores;
import com.rs.game.player.dialogues.Dialogue;

public class MiscStoreD extends Dialogue {

	@Override
	public void start() {
		int[] ids = new int[MiscStores.values().length];
		for (int i = 0; i < ids.length; i++)
			ids[i] = MiscStores.values()[i].getShowItem().getId();
		SkillsDialogue.sendStoreDialogue(player,
				"Click on the alternative stores down below.", ids, new ItemNameFilter() {
					int count = 0;

					@Override
					public String rename(String name) {
						MiscStores shop = MiscStores.values()[count++];
						return shop.name().replace("_", " ");

					}
				});
	}


	@Override
	public void run(int interfaceId, int componentId) {
		player.getActionManager().setAction(
				new MiscStoreDialogue(SkillsDialogue.getItemSlot(componentId), SkillsDialogue.getQuantity(player)));
		end();
	}

	@Override
	public void finish() {
	}
}
