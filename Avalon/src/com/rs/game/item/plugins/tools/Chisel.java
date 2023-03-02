package com.rs.game.item.plugins.tools;

import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.crafting.*;
import com.rs.game.player.actions.skills.fletching.*;
import com.rs.game.player.actions.skills.crafting.GemCutting.*;
import com.rs.game.player.actions.skills.fletching.Fletching.*;

public class Chisel extends ItemPlugin {

    @Override
    public Object[] getKeys() {
        return new Object[]{ItemId.CHISEL};
    }

    @Override
    public boolean processItemOnItem(Player player, Item item, Item item2, int fromSlot, int toSlot) {
        Gem gem = GemCutting.getUncut(item, item2);
        if (gem != null) {
            GemCutting.cut(player, gem);
            return true;
        }
        FletchingData fletchingData = Fletching.findFletchingData(item, item2);
        if (fletchingData != null) {
            player.getDialogueManager().startDialogue("FletchingD", fletchingData);
            return true;
        }
        return false;
    }
}
