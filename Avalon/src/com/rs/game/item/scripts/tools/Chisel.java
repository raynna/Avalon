package com.rs.game.item.scripts.tools;

import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemScript;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.crafting.*;
import com.rs.game.player.actions.skills.crafting.GemCutting.*;

public class Chisel extends ItemScript {

    @Override
    public Object[] getKeys() {
        return new Object[]{ItemId.CHISEL};
    }

    @Override
    public boolean processItemOnItem(Player player, Item item, Item item2, int fromSlot, int toSlot) {
        System.out.println("CHISEL");
        if (contains(item.getId(), Gem.OPAL.getUncut(), item, item2)) {
            GemCutting.cut(player, Gem.OPAL);
            return true;
        }
        if (contains(item.getId(), Gem.JADE.getUncut(), item, item2)) {
            GemCutting.cut(player, Gem.JADE);
        }
        if (contains(item.getId(), Gem.RED_TOPAZ.getUncut(), item, item2)) {
            GemCutting.cut(player, Gem.RED_TOPAZ);
        }
        if (contains(item.getId(), Gem.SAPPHIRE.getUncut(), item, item2)) {
            GemCutting.cut(player, Gem.SAPPHIRE);
        }
        if (contains(item.getId(), Gem.EMERALD.getUncut(), item, item2)) {
            GemCutting.cut(player, Gem.EMERALD);
        }
        if (contains(item.getId(), Gem.RUBY.getUncut(), item, item2)) {
            GemCutting.cut(player, Gem.RUBY);
        }
        if (contains(item.getId(), Gem.DIAMOND.getUncut(), item, item2)) {
            GemCutting.cut(player, Gem.DIAMOND);
        }
        if (contains(item.getId(), Gem.DRAGONSTONE.getUncut(), item, item2)) {
            GemCutting.cut(player, Gem.DRAGONSTONE);
        }
        if (contains(item.getId(), Gem.ONYX.getUncut(), item, item2)) {
            GemCutting.cut(player, Gem.ONYX);
        }
        return false;
    }
}
