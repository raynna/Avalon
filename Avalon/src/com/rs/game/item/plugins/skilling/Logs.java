package com.rs.game.item.plugins.skilling;

import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.firemaking.Firemaking;
import com.rs.game.player.actions.skills.fletching.Fletching;
import com.rs.game.player.actions.skills.fletching.Fletching.*;

public class Logs extends ItemPlugin {

    @Override
    public Object[] getKeys() {
        return new Object[]{"Logs"};
    }

    @Override
    public boolean processItem(Player player, Item item, int slotId, String option) {
        switch (option) {
            case "craft":
                if (!player.getInventory().containsOneItem(ItemId.KNIFE) && !player.getToolbelt().contains(ItemId.KNIFE)) {
                    player.message("You need a knife to fletch this log.");
                    return true;
                }
                FletchingData fletchingData = FletchingData.forId(item.getId());
                player.getDialogueManager().startDialogue("FletchingD", fletchingData);
                return true;
            case "light":
                if (!player.getInventory().containsOneItem(ItemId.TINDERBOX) && !player.getToolbelt().contains(ItemId.TINDERBOX)) {
                    player.message("You need a tinderbox to light this log.");
                    return true;
                }
                Firemaking.isFiremaking(player, item.getId());
                return true;
        }
        return false;
    }

    @Override
    public boolean processItemOnItem(Player player, Item item, Item item2, int itemUsed, int usedWith) {
        boolean isTinderbox = usingItems(item, item2, "Tinderbox");
        if (isTinderbox) {
            if (Firemaking.isFiremaking(player, item, item2))
                return true;
        }
        boolean isKnife = usingItems(item, item2, "Knife");
        if (isKnife) {
            FletchingData fletchingData = Fletching.findFletchingData(item, item2);
            if (fletchingData != null) {
                player.getDialogueManager().startDialogue("FletchingD", fletchingData);
                return true;
            }
        }
        return false;
    }
}