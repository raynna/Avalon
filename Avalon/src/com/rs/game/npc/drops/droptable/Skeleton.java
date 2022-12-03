package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Skeleton extends MobRewardNodeBuilder {

	public Skeleton() {
		super(new Object[] { "Skeleton", -1 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(199, 1), 
					node(205, 1), 
					node(201, 1), 
					node(203, 1), 
					node(882, 1,11), 
					node(1137, 1), 
					node(1203, 1), 
					node(1420, 1), 
					node(1381, 1), 
					node(556, 12,15), 
					node(557, 1,3), 
					node(554, 2), 
					node(558, 1,25), 
					node(55, 2,9), 
					node(995, 1,65));
			break;
		case VERY_RARE:
		case RARE:
			createNodeBatch(1, node(215, 1), 
					node(217, 1), 
					node(2485, 1), 
					node(886, 1), 
					node(1323, 1), 
					node(560, 1,50), 
					node(4440, 1), 
					node(1442, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(211, 1), 
					node(209, 1), 
					node(213, 1), 
					node(207, 1), 
					node(884, 1), 
					node(884, 12,15), 
					node(1349, 1), 
					node(1279, 1), 
					node(562, 3,8), 
					node(564, 2), 
					node(563, 2), 
					node(4698, 3), 
					node(561, 1,3), 
					node(1925, 1), 
					node(2, 3), 
					node(1947, 1), 
					node(2349, 1), 
					node(440, 1), 
					node(438, 1));
			break;
		}
		addObj(526, 1);// bones
		shakeTreasureTrail(player, MEDIUM_CLUE);
		shakeSummoningCharm(1, 5, 0.6, 2, 0.7);

	}
}
