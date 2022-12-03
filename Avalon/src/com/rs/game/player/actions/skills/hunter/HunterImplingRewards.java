package com.rs.game.player.actions.skills.hunter;

import com.rs.game.item.Item;

public class HunterImplingRewards {

    private static int EXTREME = 0, RARE = 10, UNCOMMON = 40, COMMON = 70;

    public enum BabyReward {

        //TODO

        REWARDHERE(new Item(1, 1), COMMON);

        private Item item;
        private int rarity;

        private BabyReward(Item item, int rarity) {
            this.item = item;
            this.rarity = rarity;
        }
    }
}
