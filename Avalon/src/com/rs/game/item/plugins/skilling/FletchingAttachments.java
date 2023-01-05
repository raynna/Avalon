package com.rs.game.item.plugins.skilling;

import com.rs.game.item.Item;
import com.rs.game.item.ItemPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.fletching.Fletching;
import com.rs.game.player.actions.skills.fletching.Fletching.Fletch;

public class FletchingAttachments extends ItemPlugin {

    @Override
    public Object[] getKeys() {
        return new Object[]{"arrow", "Headless arrow", "bolts", "Feather", "bow string", " stock", "shaft", "mutated vine", "tip"};
    }

    @Override
    public boolean processItemOnItem(Player player, Item item, Item item2, int fromSlot, int toSlot) {
        Fletch fletch = Fletching.isFletching(item, item2);
        if (fletch == null)
            fletch = Fletching.isFletching(item2, item);
        if (fletch != null) {
            player.getDialogueManager().startDialogue("FletchingD", fletch);
            return true;
        }
        return false;
    }
}