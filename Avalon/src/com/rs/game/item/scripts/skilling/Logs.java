package com.rs.game.item.scripts.skilling;

import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemScripts;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.firemaking.Firemaking;
import com.rs.game.player.actions.skills.fletching.Fletching;
import com.rs.game.player.actions.skills.fletching.Fletching.*;

public class Logs extends ItemScripts {

    @Override
    public Object[] getKeys() {
        return new Object[]{"Logs"};
    }

    @Override
    public boolean processItem(Player player, Item item, int slotId) {
       boolean hasOption = item.getDefinitions().containsOption("craft");
        if (!hasOption)
            return false;
        if (!player.getInventory().containsOneItem(ItemId.KNIFE) && !player.getToolbelt().contains(ItemId.KNIFE)) {
            player.sm("You need a knife to fletch this log.");
            return true;
        }
        Fletch fletch = Fletch.forId(item.getId());
        player.getDialogueManager().startDialogue("FletchingD", fletch);
        return true;
    }

    @Override
    public boolean processItem2(Player player, Item item, int slotId) {
        boolean hasOption = item.getDefinitions().containsOption("light");
        if (!hasOption)
            return false;
        if (!player.getInventory().containsOneItem(ItemId.TINDERBOX) && !player.getToolbelt().contains(ItemId.TINDERBOX)) {
            player.sm("You need a tinderbox to light this log.");
            return true;
        }
        Firemaking.isFiremaking(player, item.getId());
        return true;
    }

    @Override
    public boolean processItemOnItem(Player player, Item item, Item item2, int itemUsed, int usedWith) {
        boolean isTinderbox = item.getName().toLowerCase().contains("tinderbox") || item2.getName().toLowerCase().contains("tinderbox");
        if (isTinderbox) {
            if (Firemaking.isFiremaking(player, item, item2))
                return true;
        }
        Fletch fletch = Fletching.isFletching(item, item2);
        if (fletch != null) {
            player.getDialogueManager().startDialogue("FletchingD", fletch);
            return true;
        }
        return true;
    }
}