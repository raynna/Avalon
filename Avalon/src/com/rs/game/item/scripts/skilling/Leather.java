package com.rs.game.item.scripts.skilling;

import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemScripts;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.crafting.LeatherCrafting;
import com.rs.game.player.actions.skills.crafting.LeatherCrafting.*;

public class Leather extends ItemScripts {

    @Override
    public Object[] getKeys() {
        return new Object[]{"leather"};
    }

    @Override
    public boolean processItem(Player player, Item item, int slotId) {
        boolean hasOption = item.getDefinitions().containsOption("craft");
        if (!hasOption)
            return true;
        if (!player.getInventory().containsOneItem(ItemId.NEEDLE) && !player.getToolbelt().contains(ItemId.NEEDLE)) {
            player.sm("You need a needle to craft this item.");
            return true;
        }
        Craft craft = Craft.forId(item.getId());
        if (craft != null)
            player.getDialogueManager().startDialogue("CraftingD", craft, false);
        return true;
    }

    @Override
    public boolean processItemOnItem(Player player, Item item, Item item2, int itemUsed, int usedWith) {
        Craft craft = LeatherCrafting.isCrafting(item, item2);
        if (craft == null)
            craft = LeatherCrafting.isCrafting(item2, item);
        if (craft != null) {
            player.getDialogueManager().startDialogue("CraftingD", craft);
            return true;
        }
        return true;
    }

    @Override
    public boolean processDrop(Player player, Item item, int slotId) {
        return true;
    }

    @Override
    public boolean processDestroy(Player player, Item item, int slotId) {
        return true;
    }
}