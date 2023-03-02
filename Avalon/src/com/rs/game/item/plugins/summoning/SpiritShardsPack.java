package com.rs.game.item.plugins.summoning;

import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemPlugin;
import com.rs.game.player.Player;

public class SpiritShardsPack extends ItemPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] { ItemId.SPIRIT_SHARDS_PACK };
	}
	@Override
	public boolean processItem(Player player, Item item, int slotId, String option) {
		switch (option) {
			case "open":
				if (player.getInventory().getNumberOf(item.getId()) > 1 && !player.getInventory().containsOneItem(ItemId.SPIRIT_SHARD) && !player.getInventory().hasFreeSlots()) {
					player.message("You don't have enough inventory space to open this pack.");
					return true;
				}
				if (player.getInventory().getNumberOf(ItemId.SPIRIT_SHARD) + 5000 < 0) {
					player.getPackets().sendGameMessage("You don't have enough inventory space to open this pack.");
					return true;
				}
				player.getInventory().deleteItem(item.getId(), 1);
				player.getInventory().addItem(ItemId.SPIRIT_SHARD, 5000);
				return true;
			case "open-all":
				int packs = player.getInventory().getAmountOf(ItemId.SPIRIT_SHARDS_PACK);
				int amount = packs * 5000;
				int shards = player.getInventory().getAmountOf(ItemId.SPIRIT_SHARD);
				if (shards + 5000 == Integer.MAX_VALUE || (!player.getInventory().containsOneItem(ItemId.SPIRIT_SHARD) && !player.getInventory().hasFreeSlots() && packs > 1)) {
					player.getPackets().sendGameMessage("You don't have enough inventory space to open any packs.");
					return true;
				}
				if (shards + amount < 0) {
					packs = (Integer.MAX_VALUE - shards) / 5000;
					amount = packs * 5000;
					player.getPackets().sendGameMessage("You don't have enough inventory space to open all packs.");
				}
				player.getInventory().deleteItem(ItemId.SPIRIT_SHARDS_PACK, packs);
				player.getInventory().addItem(ItemId.SPIRIT_SHARD, amount);
				return true;
		}
		return false;
	}
}