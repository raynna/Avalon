package com.rs.game.item;

import com.rs.game.player.Player;
import com.rs.utils.Logger;

public abstract class ItemPlugin {

    public abstract Object[] getKeys();

    public boolean processItem(Player player, Item item, int slotId) {
        return false;
    }

    public boolean processItem2(Player player, Item item, int slotId) {
        return false;
    }

    public boolean processItem3(Player player, Item item, int slotId) {
        return false;
    }

    public boolean processItem4(Player player, Item item, int slotId) {
        return false;
    }

    public boolean processItem5(Player player, Item item, int slotId) {
        return false;
    }

    public boolean processItem6(Player player, Item item, int slotId) {
        return false;
    }

    public boolean processDrop(Player player, Item item, int slotId) {
        return false;
    }

    public boolean processDestroy(Player player, Item item, int slotId) {
        return false;
    }

    public boolean processItemOnItem(Player player, Item item, Item item2, int fromSlot, int toSlot) {
        return false;
    }

    public boolean usingItems(Item item, Item item2, String string) {
        return item.getName().contains(string) || item2.getName().contains(string);
    }

    public boolean processItem(Player player, Item item, int slotId, String option) {
        return false;
    }

    public boolean itemContains(Item item, Item item2, String string, String exclude) {
        System.out.println(item.getName() + " -  " + item2.getName() + " " + exclude + "");
        return (item.getName().contains(string.toLowerCase()) && !item.getName().toLowerCase().contains(exclude.toLowerCase())) || item2.getName().toLowerCase().contains(string.toLowerCase()) && !item2.getName().toLowerCase().contains(exclude.toLowerCase());
    }


    public void sendPluginLog(int option, Item item, String optionName, boolean executed) {
        StringBuilder builder = new StringBuilder();
        builder.append("Option " + option + " - Class: " + this.getClass().getSimpleName() + ".java, ");
        if (executed) {
            builder.append("Executed: '" + optionName + "' on " + item.getName() + "(" + item.getId() + ")");
        } else {
            builder.append("Failed: '" + optionName + "' option is unhandled in plugin " + item.getName() + "(" + item.getId() + ")");
        }
        Logger.log("ItemPlugin", builder);
    }

    public boolean usingItems(int id1, int id2, Item... itemsNeeded) {
        boolean containsId1 = false;
        boolean containsId2 = false;
        for (Item item : itemsNeeded) {
            if (item.getId() == id1)
                containsId1 = true;
            else if (item.getId() == id2)
                containsId2 = true;
        }
        return containsId1 && containsId2;
    }
}
