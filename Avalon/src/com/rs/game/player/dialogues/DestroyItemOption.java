package com.rs.game.player.dialogues;

import java.util.Map.Entry;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.item.ItemScriptHandler;
import com.rs.game.item.ItemScripts;

public class DestroyItemOption extends Dialogue {

	int slotId;
	Item item;

	@Override
	public void start() {
		slotId = (Integer) parameters[0];
		item = (Item) parameters[1];
		player.getInterfaceManager().sendChatBoxInterface(1183);
		player.getPackets().sendIComponentText(1183, 22,
				"Are you sure you want to destroy your " + item.getName() + "?");
		player.getPackets().sendIComponentText(1183, 7, item.getName());
		player.getPackets().sendItemOnIComponent(1183, 13, item.getId(), 1);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (interfaceId == 1183 && componentId == 9) {
			ItemDefinitions itemDef = ItemDefinitions.getItemDefinitions(item.getId());
			ItemScripts script = ItemScriptHandler.cachedItemScripts.getOrDefault(item.getId(), ItemScriptHandler.cachedItemScripts.get(itemDef.name));
			if (script != null) {
				if (script.processDestroy(player, item, slotId)) {
					end();
					return;
				}
			}
			player.getInventory().deleteItem(slotId, item);
			player.getCharges().degrade(item);
			player.getPackets().sendSound(4500, 0, 1);
		}
		end();
	}

	@Override
	public void finish() {

	}

}
