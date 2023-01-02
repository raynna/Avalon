package com.rs.game.item.scripts.misc;

import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemScripts;
import com.rs.game.player.Player;
import com.rs.game.player.content.DwarfMultiCannon;

public class DwarfCannon extends ItemScripts {

    @Override
    public Object[] getKeys() {
        return new Object[]{ItemId.DWARF_CANNON_BASE, ItemId.ROYAL_DWARF_CANNON_BASE, ItemId.GOLDEN_DWARF_CANNON_BASE};
    }

    @Override
    public boolean processItem(Player player, Item item, int slotId) {
        DwarfMultiCannon.setUp(player, item.getId() == ItemId.DWARF_CANNON_BASE ? 0 : item.getId() == ItemId.ROYAL_DWARF_CANNON_BASE ? 1 : 2);
        return true;
    }
}