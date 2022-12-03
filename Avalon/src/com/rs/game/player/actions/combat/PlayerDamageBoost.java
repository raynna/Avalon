package com.rs.game.player.actions.combat;

import com.rs.game.Entity;
import com.rs.game.player.Player;

public class PlayerDamageBoost extends PlayerCombat {
    public PlayerDamageBoost(Entity target) {
        super(target);
    }

    public static int getMultiplier(Player player) {
        int multiplier = 1;
        if (hasBerskerNecklace(player))
            multiplier *= 1.2;
        if (player.getAuraManager().isActivated() && player.getAuraManager().usingBerserker() && !player.isAtWild())
            multiplier *= 1.1;
        return multiplier;
    }
}
