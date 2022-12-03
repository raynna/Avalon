package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class SkeletalWyvern extends MobRewardNodeBuilder {

	public SkeletalWyvern() {
		super(new Object[] { "Skeletal Wyvern", -1 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 512)) {
			createNodeBatch(1, node(11286, 1));
		} else
			switch (rarityNode(player)) {
			case -1:
			case COMMON:
				createNodeBatch(1, node(1399, 1), node(556, 225), node(555, 150), node(199, 1, 3), node(201, 1, 3),
						node(203, 1, 3), node(205, 1, 3), node(995, 300));
				break;
			case VERY_RARE:
			case RARE:
				createNodeBatch(1, node(4585, 1), node(4087, 1), node(6809, 1), node(566, 20));
				break;
			case UNCOMMON:
				createNodeBatch(1, node(1359, 1), node(1373, 1), node(1347, 1), node(1201, 1), node(1163, 1),
						node(7937, 250), nodeB(562, 20, 80), node(563, 45), node(560, 40), node(565, 25),
						node(9143, 7, 99), node(9144, 5, 44), node(892, 36), node(5295, 3), node(5300, 1),
						node(207, 1, 3), node(209, 1, 3), node(211, 1, 3), node(213, 1, 3), node(215, 1, 3),
						node(2485, 1, 3), node(217, 1, 3), node(1514, 35), node(2362, 10), node(568, 75),
						node(441, 200), node(1620, 10), node(1618, 5), node(1392, 10), node(2434, 2), node(379, 1),
						node(9465, 1));
				break;
			}
		addObj(6812, 1);// wyvern bones
		shakeTreasureTrail(player, HARD_CLUE);
		shakeTreasureTrail(player, ELITE_CLUE);
		shakeSummoningCharm(2, 6, 3, 54, 1);

	}
}
