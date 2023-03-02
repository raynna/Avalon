package com.rs.game.item.plugins.weapons;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.content.GreaterRunicStaff.*;

import java.util.Map;

public class GreaterRunicStaff extends ItemPlugin {

    @Override
    public Object[] getKeys() {
        return new Object[]{ItemId.GREATER_RUNIC_STAFF_INACTIVE, ItemId.GREATER_RUNIC_STAFF_ACTIVE, ItemId.GREATER_RUNIC_STAFF_CHARGED};
    }

    @Override
    public boolean processItem(Player player, Item item, int slotId, String option) {
        switch (option) {
            case "set spell":
                player.getRunicStaff().openChooseSpell(player);
                return true;
            case "charge":
                player.getDialogueManager().startDialogue("GreaterRunicStaffD");
                return true;
            case "check charges"://TODO for some reason wield and check charge is opposite order
                if (player.getSwitchItemCache().contains(slotId))
                    return true;
                player.getSwitchItemCache().add(slotId);
                return true;
            case "wield":
                if (item.getId() == ItemId.GREATER_RUNIC_STAFF_INACTIVE) {
                    if (player.getSwitchItemCache().contains(slotId))
                        return true;
                    player.getSwitchItemCache().add(slotId);
                    return true;
                }
                RunicStaffSpellStore s = RunicStaffSpellStore.getSpell(player.getRunicStaff().getSpellId());
                if (s == null)
                    return true;
                player.message("You currently have " + player.getRunicStaff().getCharges() + " " + s.name().toLowerCase().replace('_', ' ') + " charges left.");
                return true;
            case "clear spell":
                player.getRunicStaff().clearSpell(false);
                return true;
            case "empty charge":
                player.getRunicStaff().clearCharges(false, false);
                return true;
        }
        return false;
    }

    @Override
    public boolean processDestroy(Player player, Item item, int slotId) {
        if (item.getId() == ItemId.GREATER_RUNIC_STAFF_CHARGED) {
            for (Map.Entry<Integer, Item[]> charges : player.getStaffCharges().entrySet()) {
                if (charges.getValue() == null)
                    continue;
                for (Item staffRunes : charges.getValue()) {
                    if (item == null)
                        continue;
                    World.updateGroundItem(staffRunes, new WorldTile(player), player, player.isAtWild() ? 0 : 60, 0);
                }
            }
            player.message("All your runes in your runic staff were dropped.");
        }
        if (player.getRunicStaff().getSpellId() > 0) {
            player.getRunicStaff().setStaffValues(-1, null);
            player.getPackets().sendGameMessage("You clear your greater runic staff spell.");
            player.getStaffCharges().clear();
        }
        player.getInventory().dropItem(slotId, item, false);
        return true;
    }
}
