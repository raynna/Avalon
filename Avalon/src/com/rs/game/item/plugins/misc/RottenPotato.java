package com.rs.game.item.plugins.misc;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Hit;
import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Magic;
import com.rs.net.decoders.handlers.ButtonHandler;

public class RottenPotato extends ItemPlugin {

    @Override
    public Object[] getKeys() {
        return new Object[]{ItemId.ROTTEN_POTATO};
    }

    @Override
    public boolean processItem(Player player, Item item, int slotId, String option) {
        switch (option) {
            case "eat":
                player.getDialogueManager().startDialogue("Potato_Op1", item);
                return true;
            case "heal":
                player.heal(player.getMaxHitpoints(), true, true);
                return true;
            case "cm-tool":
                player.getDialogueManager().startDialogue("Potato_CMTool", item);
                return true;
            case "commands":
                player.getDialogueManager().startDialogue("Potato_Commands", item);
                return true;
            case "drop":
                player.getInventory().deleteItem(item);
                player.getPackets().sendGameMessage("Too late! It's already gone.", true);
                return true;
            case "examine":
                player.getPackets().sendGameMessage("Yuk!", true);
                return true;
        }
        return false;
    }
}
