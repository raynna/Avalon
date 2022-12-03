package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;
import com.rs.game.player.content.pet.Pets;

public final class GreenDragon extends MobRewardNodeBuilder {

    public GreenDragon() {
        super(new Object[]{"Green dragon", -1});
    }

    @Override
    public void populate(Player player) {
        petRoll(player, Pets.BABY_GREEN_DRAGON.getBabyItemId(), 500);
        switch (rarityNode(player)) {
            case -1:
            case COMMON:
                createNodeBatch(1, node(995, 11, 440),
                        node(1161, 1),
                        node(1069, 1),
                        node(205, 1),
                        node(209, 1),
                        node(554, 37),
                        node(563, 3),
                        nodeB(561, 15, 30, 75),
                        node(555, 75, 150),
                        node(1365, 1));
                break;
            case VERY_RARE:
            case RARE:
                createNodeBatch(1, node(365, 1),
                        node(1355, 1));
                break;
            case UNCOMMON:
                createNodeBatch(1, node(1197, 1),
                        node(211, 1),
                        node(215, 1),
                        node(217, 1),
                        node(199, 1),
                        node(213, 1),
                        node(2485, 1),
                        node(201, 1),
                        node(207, 1),
                        node(203, 1),
                        node(449, 1),
                        node(1243, 1),
                        node(1213, 1));
                break;
        }
        addObj(DRAGON_BONES, 1);// bones
        addObj(1753, 1);// hide
        shakeTreasureTrail(player, HARD_CLUE);
        shakeSummoningCharm(1, 8, 16, 7, 1);

    }
}
