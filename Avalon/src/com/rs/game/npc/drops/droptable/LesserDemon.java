package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class LesserDemon extends MobRewardNodeBuilder {

	public LesserDemon() {
		super(new Object[] { "Lesser demon", -1 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(995, 10,2543), 
					node(1195, 1), 
					node(1071, 1), 
					node(199, 1), 
					node(201, 1), 
					node(203, 1), 
					node(1313, 1), 
					node(1361, 1), 
					node(1297, 1));
			break;
		case VERY_RARE:
		case RARE:
			createNodeBatch(1, node(1147, 1), 
					node(211, 1), 
					node(215, 1), 
					node(217, 1), 
					node(211, 1), 
					node(215, 1), 
					node(217, 1), 
					node(205, 1), 
					node(209, 1), 
					node(213, 1), 
					node(2485, 1), 
					node(207, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(1988, 1, 3), 
					node(379, 1), 
					node(1109, 1), 
					node(1181, 1), 
					node(445, 1,3), 
					node(562, 6,24), 
					node(560, 10,20), 
					node(554, 4,120), 
					node(1315, 1));
			break;
		}
		addObj(20266, 1);// ashes
		shakeTreasureTrail(player, MEDIUM_CLUE);
		shakeSummoningCharm(1, 2, 0.7, 3, 0.6);

	}
}
