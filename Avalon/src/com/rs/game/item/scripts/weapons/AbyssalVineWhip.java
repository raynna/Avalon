package com.rs.game.item.scripts.weapons;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemScript;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class AbyssalVineWhip extends ItemScript {
    @Override
    public Object[] getKeys() {
        return new Object[]{ItemId.ABYSSAL_VINE_WHIP, ItemId.VINE_WHIP};
    }

    @Override
    public boolean processItem3(Player player, Item item, int slotId) {
        if (!player.getInventory().hasFreeSlots()) {
            player.getPackets().sendGameMessage("You don't have enough space to split the abyssal vine whip.");
            return true;
        }
        player.getInventory().replaceItem(ItemId.ABYSSAL_WHIP, 1, slotId);
        player.getInventory().addItem(ItemId.VINE_WHIP, 1);
        player.sm("You split the whip vine from the abbysal whip.");
        return true;
    }

    @Override
    public boolean processDrop(Player player, Item item, int slotId) {
        if (item.getId() == ItemId.VINE_WHIP)
            return false;//script false, continues to regular drop method
        player.getInventory().dropItem(slotId, item, false);
        World.updateGroundItem(new Item(ItemId.VINE_WHIP, 1), new WorldTile(player), player, player.isAtWild() ? 0 : 60, 0);
        World.updateGroundItem(new Item(ItemId.ABYSSAL_WHIP, 1), new WorldTile(player), player, player.isAtWild() ? 0 : 60, 0);
        return true;
    }

    @Override
    public boolean processItemOnItem(Player player, Item item, Item item2, int fromSlot, int toSlot) {
        if (contains(ItemId.ABYSSAL_WHIP, ItemId.VINE_WHIP, item, item2)) {
            if (!player.getSkills().hasRequirements(Skills.ATTACK, 75, Skills.SLAYER, 80)) {
                player.sm("You need an attack level of 75 and slayer level of 80 in order to attach the whip vine to the whip.");
                return true;
            }
            player.getInventory().replaceItem(ItemId.ABYSSAL_VINE_WHIP, 1, toSlot);
            player.getInventory().deleteItem(fromSlot, item);
            player.sm("You attach the whip vine to the abbysal whip.");
            return true;
        }
        return false;
    }
}