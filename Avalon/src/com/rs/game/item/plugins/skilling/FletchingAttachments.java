package com.rs.game.item.plugins.skilling;

import com.rs.game.item.Item;
import com.rs.game.item.ItemPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.fletching.Fletching;
import com.rs.game.player.actions.skills.fletching.Fletching.FletchingData;

public class FletchingAttachments extends ItemPlugin {

    @Override
    public Object[] getKeys() {
        return new Object[]{"arrow", "Headless arrow", "bolts", "Feather", "bow string", " stock", "shaft", "mutated vine", "tip"};
    }

    @Override
    public boolean processItemOnItem(Player player, Item item, Item item2, int fromSlot, int toSlot) {
        FletchingData fletchingData = Fletching.findFletchingData(item, item2);
        if (fletchingData == null)
            fletchingData = Fletching.findFletchingData(item2, item);
        if (fletchingData != null) {
            player.getDialogueManager().startDialogue("FletchingD", fletchingData);
            return true;
        }
        return false;
    }
}