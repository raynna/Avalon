package com.rs.game.item.scripts.misc;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemScript;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Magic;
import com.rs.net.decoders.handlers.ButtonHandler;

public class RunePouch extends ItemScript {

    @Override
    public Object[] getKeys() {
        return new Object[]{ItemId.RUNE_POUCH};
    }

    @Override
    public boolean processItem(Player player, Item item, int slotId) {
        ButtonHandler.openRunePouch(player);
        return true;
    }


    @Override
    public boolean processItem2(Player player, Item item, int slotId) {
        if (player.getRunePouch().getFreeSlots() == 3) {
            player.sm("Your rune pouch is empty.");
            return true;
        }
        for (Item runes : player.getRunePouch().getContainerItems()) {
            if (runes == null)
                continue;
            if (!player.getInventory().hasFreeSlots() && !player.getInventory().containsOneItem(runes.getId())) {
                player.sm("You don't have enough inventory spaces.");
                continue;
            }
            player.getRunePouch().remove(runes);
            player.getRunePouch().shift();
            player.getInventory().addItem(runes);
            player.getInventory().refresh();
        }
        return true;
    }

    @Override
    public boolean processItemOnItem(Player player, Item item, Item item2, int itemUsed, int usedWith) {
        if (!Magic.isRune(item.getId())) {
            player.getPackets().sendGameMessage("You can't store " + item.getName() + " in the rune pouch.");
            return true;
        }
        if (player.getRunePouch().getNumberOf(item) == 16000) {
            player.getPackets().sendGameMessage("You can't have more than 16,000 of each rune in the rune pouch.");
            return true;
        }
        if (player.getRunePouch().getFreeSlots() == 0 && !player.getRunePouch().contains(item)) {
            player.sm("You can't store more than 3 type of runes in the rune pouch.");
            return true;
        }
        int amount = item.getAmount();
        if (player.getRunePouch().getNumberOf(item) + item.getAmount() > 16000)
            amount = 16000 - player.getRunePouch().getNumberOf(item);
        player.getRunePouch().add(new Item(item.getId(), amount));
        player.getInventory().deleteItem(item.getId(), amount);
        player.getInventory().refresh();
        player.sm("You stored " + amount + " x " + ItemDefinitions.getItemDefinitions(item.getId()).getName() + " in the rune pouch.");
        return true;
    }

    @Override
    public boolean processDestroy(Player player, Item item, int slotId) {
        if (player.getRunePouch() != null) {
			player.getInventory().dropItems(true, player.getRunePouch().getContainerItems());
			player.getRunePouch().reset();
		}
		player.getInventory().dropItem(slotId, item, false);
		player.getPackets().sendGameMessage("All your runes in your rune pouch were dropped on the floor.");
        return true;
    }
}
