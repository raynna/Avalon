package com.rs.game.item.plugins.misc;

import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.content.DwarfMultiCannon;

public class DwarfCannon extends ItemPlugin {

    @Override
    public Object[] getKeys() {
        return new Object[]{ItemId.DWARF_CANNON_BASE, ItemId.ROYAL_DWARF_CANNON_BASE, ItemId.GOLDEN_DWARF_CANNON_BASE};
    }

    @Override
    public boolean processItem(Player player, Item item, int slotId, String option) {
        switch (option) {
            case "set-up":
                DwarfMultiCannon.setUp(player, item.getId() == ItemId.DWARF_CANNON_BASE ? 0 : item.getId() == ItemId.GOLDEN_DWARF_CANNON_BASE ? 1 : 2);
                return true;
        }
        return false;
    }
}