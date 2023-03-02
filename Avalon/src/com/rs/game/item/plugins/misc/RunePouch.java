package com.rs.game.item.plugins.misc;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Magic;

public class RunePouch extends ItemPlugin {

    @Override
    public Object[] getKeys() {
        return new Object[]{ItemId.RUNE_POUCH};
    }

    @Override
    public boolean processItem(Player player, Item item, int slotId, String option) {
        switch (option) {
            case "open":
                openRunePouch(player);
                return true;
            case "withdraw-all":
                withdrawAll(player);
                return true;
        }
        return false;
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
            player.message("You can't store more than 3 type of runes in the rune pouch.");
            return true;
        }
        int amount = item.getAmount();
        if (player.getRunePouch().getNumberOf(item) + item.getAmount() > 16000)
            amount = 16000 - player.getRunePouch().getNumberOf(item);
        player.getRunePouch().add(new Item(item.getId(), amount));
        player.getInventory().deleteItem(item.getId(), amount);
        player.getInventory().refresh();
        player.message("You stored " + amount + " x " + ItemDefinitions.getItemDefinitions(item.getId()).getName() + " in the rune pouch.");
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

    public static void withdrawAll(Player player) {
        if (player.getRunePouch().getFreeSlots() == 3) {
            player.message("Your rune pouch is empty.");
            return;
        }
        for (Item runes : player.getRunePouch().getContainerItems()) {
            if (runes == null)
                continue;
            if (!player.getInventory().hasFreeSlots() && !player.getInventory().containsOneItem(runes.getId())) {
                player.message("You don't have enough inventory spaces.");
                continue;
            }
            player.getRunePouch().remove(runes);
            player.getRunePouch().shift();
            player.getInventory().addItem(runes);
            player.getInventory().refresh();
        }
    }


    public static void storeRunePouch(Player player, Item item, int amount) {
        Item newItem = item;
        if (newItem.getAmount() < amount) {
            amount = newItem.getAmount();
        }
        if (!Magic.isRune(newItem.getId())) {
            player.getPackets().sendGameMessage("You can't store " + newItem.getName() + " in the rune pouch.");
            return;
        }
        if (player.getRunePouch().getNumberOf(newItem) == 16000) {
            player.getPackets().sendGameMessage("You can't have more than 16,000 of each rune in the rune pouch.");
            return;
        }
        if (player.getRunePouch().getFreeSlots() == 0 && !player.getRunePouch().containsOne(newItem)) {
            player.getPackets().sendGameMessage("You can't store more than 3 types of runes in the rune pouch.");
            return;
        }
        if (amount + player.getRunePouch().getNumberOf(newItem) > 16000)
            amount = 16000 - player.getRunePouch().getNumberOf(newItem);
        player.getInventory().deleteItem(newItem.getId(), amount);
        player.getRunePouch().add(new Item(newItem.getId(), amount));
        player.getRunePouch().shift();
        player.getInventory().refresh();
        player.getPackets().sendGameMessage("You store " + amount + " x " + item.getName() + "s in the rune pouch.");
        openRunePouch(player);
    }

    public static void withdrawRunePouch(Player player, int slotId, Item item, int amount) {
        if (player.getInventory().getFreeSlots() == 0 && !player.getInventory().containsItem(player.getRunePouch().get(slotId).getId(), 1)) {
            player.getPackets().sendGameMessage("You don't have enough inventory spaces.");
            return;
        }
        if (amount > player.getRunePouch().get(slotId).getAmount()) {
            amount = player.getRunePouch().get(slotId).getAmount();
        }
        player.getRunePouch().get(slotId).setAmount(player.getRunePouch().get(slotId).getAmount() - amount);
        player.getInventory().addItem(item.getId(), amount);
        player.getInventory().refresh();
        if (player.getRunePouch().get(slotId).getAmount() == 0) {
            player.getRunePouch().remove(item);
            player.getRunePouch().shift();
        }
        openRunePouch(player);
        player.getPackets().sendGameMessage("You withdraw " + amount + " x " + item.getName() + "s from the rune pouch.");

    }

    public static void openRunePouch(Player player) {
        Item[] items = player.getRunePouch().getContainerItems();
        if (!player.getInterfaceManager().containsInterface(1284))
            player.getInterfaceManager().sendInterface(1284);
        player.getInterfaceManager().sendInventoryInterface(670);
        player.getPackets().sendInterSetItemsOptionsScript(670, 0, 93, 4, 7, "Store 1", "Store 10", "Store 100", "Store-All");
        player.getPackets().sendUnlockIComponentOptionSlots(670, 0, 0, 27, 0, 1, 2, 3);
        player.getPackets().sendIComponentText(1284, 28, "Rune Pouch");
        player.getPackets().sendHideIComponent(1284, 8, true);
        player.getPackets().sendHideIComponent(1284, 9, true);
        player.getPackets().sendIComponentText(1284, 46, "Take-All");
        player.getPackets().sendInterSetItemsOptionsScript(1284, 7, 100, 8, 4, "Withdraw 1", "Withdraw 10", "Withdraw 100", "Withdraw-All");
        player.getPackets().sendUnlockIComponentOptionSlots(1284, 7, 0, 3, 0, 1, 2, 3);
        player.getPackets().sendItems(100, items);
        player.temporaryAttribute().put("runepouch", Boolean.TRUE);
        player.setCloseInterfacesEvent(new Runnable() {
            @Override
            public void run() {
                player.temporaryAttribute().remove("runepouch");
            }
        });
    }
}
