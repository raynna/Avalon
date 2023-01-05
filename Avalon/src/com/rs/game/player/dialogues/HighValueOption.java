package com.rs.game.player.dialogues;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.ItemPluginLoader;
import com.rs.game.item.ItemPlugin;
import com.rs.game.player.content.ItemConstants;

public class HighValueOption extends Dialogue {

	int slotId;
	Item item;

	@Override
	public void start() {
		slotId = (Integer) parameters[0];
		item = (Item) parameters[1];
		if (player.HighValueOption) {
			player.getCharges().degrade(item);
			player.getInventory().deleteItem(slotId, item);
			if (item.getId() == 21371) {
				item.setId(ItemConstants.removeAttachedId(item));
				if (player.isAtWild() && ItemConstants.isTradeable(item))
					World.updateGroundItem(new Item(4151, 1), new WorldTile(player), player, 1, 0);
				else
					World.updateGroundItem(new Item(4151, 1), new WorldTile(player), player, 60, 0);
			}
			if (player.isAtWild() && ItemConstants.isTradeable(item))
				World.updateGroundItem(item, new WorldTile(player), player, 1, 0);
			else
				World.updateGroundItem(item, new WorldTile(player), player, 60, 0);
			player.getPackets().sendSound(4500, 0, 1);
		} else if (player.isAtWild()) {
			sendItemDialogue(item.getDefinitions().isNoted() ? item.getId() - 1 : item.getId(),
					item.getDefinitions().isStackable() ? 900 : 1,
					"The item you are trying to drop is considered <col=ff0000>valuable.<br>You are standing in a PvP zone. Your item will appear instantly to everyone.");
			stage = 1;
		} else {
			sendItemDialogue(item.getDefinitions().isNoted() ? item.getId() - 1 : item.getId(),
					item.getDefinitions().isStackable() ? 900 : 1,
					"The item you are trying to drop is considered <col=ff0000>valuable.<br>Are you absolutely sure that you want to drop it?");
			stage = 1;
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case 1:
			sendOptions(item.getName() + "; Really want to drop it?", "Yes", "Yes and don't ask me again", "No");
			stage = 2;
			break;
		case 2:
			switch (componentId) {
			case OPTION_1:
				if (!player.getInventory().containsItem(item.getId(), item.getAmount())) {
					end();
					return;		
				}
				ItemPlugin plugin = ItemPluginLoader.cachedItemPlugins.getOrDefault(item.getId(), ItemPluginLoader.cachedItemPlugins.get(ItemDefinitions.getItemDefinitions(item.getId()).name));
				if (plugin != null) {
					if (plugin.processDrop(player, item, slotId))
						return;
				}
				player.getInventory().dropItem(slotId, item, true);
				end();
				break;
			case OPTION_2:
				if (!player.getInventory().containsItem(item.getId(), item.getAmount())) {
					end();
					return;		
				}
				plugin = ItemPluginLoader.cachedItemPlugins.getOrDefault(item.getId(), ItemPluginLoader.cachedItemPlugins.get(ItemDefinitions.getItemDefinitions(item.getId()).name));
				if (plugin != null) {
					if (plugin.processDrop(player, item, slotId))
						return;
				}
				player.getInventory().dropItem(slotId, item, true);
				player.HighValueOption = true;
				end();
				break;
			case OPTION_3:
				end();
			}
			break;
		}
	}

	@Override
	public void finish() {

	}

}
