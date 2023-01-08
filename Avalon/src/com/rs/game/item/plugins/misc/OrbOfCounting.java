package com.rs.game.item.plugins.misc;

import com.rs.cores.CoresManager;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemPlugin;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

import java.util.concurrent.TimeUnit;

public class OrbOfCounting extends ItemPlugin {

    @Override
    public Object[] getKeys() {
        return new Object[]{ItemId.ORB_OF_COUNTING};
    }

    @Override
    public boolean processItem(Player player, Item item, int slotId, String option) {
        if (player.getTemporaryAttributtes().get("ORB_OF_COUNTING") == Boolean.TRUE) {
            player.sm("You already have one counting.");
            return true;
        }
        switch (option) {
            case "nearby-count":
			case "world-count":
                sendCount(player, option.equalsIgnoreCase("world-count"));
                return true;
        }
        return false;
    }

    public void sendCount(Player player, boolean world) {
        player.getTemporaryAttributtes().put("ORB_OF_COUNTING", Boolean.TRUE);
        player.sm("Counting...");
        CoresManager.slowExecutor.schedule(new Runnable() {

            @Override
            public void run() {
				if (!player.isActive())
					return;
                int blueHats = 0;
                int redHats = 0;
                if (world) {
                    for (Player players : World.getPlayers()) {
                        if (players.getEquipment().getHatId() == ItemId.VOTING_HAT_RED)
                            redHats++;
                        if (players.getEquipment().getHatId() == ItemId.VOTING_HAT_BLUE)
                            blueHats++;
                    }
                } else {
                    for (Entity players : Utils.getAroundEntities(player, player, 14)) {
                        if (players instanceof NPC)
                            continue;
                        Player p = (Player) players;
                        if (p.getEquipment().getHatId() == ItemId.VOTING_HAT_RED)
                            redHats++;
                        if (p.getEquipment().getHatId() == ItemId.VOTING_HAT_BLUE)
                            blueHats++;
                    }
                }
                player.getTemporaryAttributtes().remove("ORB_OF_COUNTING");
                player.sm("Blue hats: " + blueHats + ", Red hats: " + redHats);
                if (blueHats == redHats) {
                    player.sm("No winner! It's a tie!");
                } else
                    player.sm("Winner is: " + (blueHats < redHats ? "Red!" : "Blue!"));
            }
        }, 2000, TimeUnit.MILLISECONDS);
    }
}