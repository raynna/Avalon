package com.rs.game.item.plugins.weapons;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class AbyssalVineWhip extends ItemPlugin {

    private final int ATTACK_LEVEL_REQUIREMENT = 75, SLAYER_LEVEL_REQUIREMENT = 80;

    @Override
    public Object[] getKeys() {
        return new Object[]{ItemId.ABYSSAL_VINE_WHIP, ItemId.VINE_WHIP};
    }

    @Override
    public boolean processItem(Player player, Item item, int slotId, String option) {
        switch (option) {
            case "split":
                if (!player.getInventory().hasFreeSlots()) {
                    player.getPackets().sendGameMessage("You don't have enough inventory space to split the abyssal vine whip.");
                    return true;
                }
                player.getInventory().replaceItem(ItemId.ABYSSAL_WHIP, 1, slotId);
                player.getInventory().addItem(ItemId.VINE_WHIP, 1);
                player.message("You split your abyssal vine whip.");
                return true;
            case "drop":
                if (item.getId() == ItemId.VINE_WHIP)
                    return false;//script false, continues to regular drop method
                player.getInventory().dropItem(slotId, item, false);
                World.updateGroundItem(new Item(ItemId.VINE_WHIP, 1), new WorldTile(player), player, player.isAtWild() ? 0 : 60, 0);
                World.updateGroundItem(new Item(ItemId.ABYSSAL_WHIP, 1), new WorldTile(player), player, player.isAtWild() ? 0 : 60, 0);
                return true;
        }
        return false;
    }

    @Override
    public boolean processItemOnItem(Player player, Item itemUsed, Item itemUsedWith, int fromSlot, int toSlot) {
        if (!usingItems(ItemId.ABYSSAL_WHIP, ItemId.VINE_WHIP, itemUsed, itemUsedWith)) {
            return false;
        }
        if (!player.getSkills().hasRequirements(
                Skills.ATTACK, ATTACK_LEVEL_REQUIREMENT,
                Skills.SLAYER, SLAYER_LEVEL_REQUIREMENT)) {
            player.message("You need an attack level of " + ATTACK_LEVEL_REQUIREMENT +
                    " and a slayer level of " + SLAYER_LEVEL_REQUIREMENT + " in order to create abyssal vine whip.");
            return false;
        }
        player.getInventory().replaceItem(ItemId.ABYSSAL_VINE_WHIP, 1, toSlot);
        player.getInventory().deleteItem(fromSlot, itemUsed);
        player.message("You attach vine whip with your abyssal whip to create an abyssal vine whip.");
        return true;
    }
}