package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Guard extends MobRewardNodeBuilder {

	public Guard() {
		super(new Object[] { "Guard", 5919 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, nodeB(995, 1, 2, 3, 4, 12, 25), 
					node(1987, 3), 
					nodeB(884, 1, 2), 
					node(9140, 2, 18), 
					node(556, 6), 
					node(557, 3, 6), 
					node(554, 2), 
					node(5319, 4), 
					node(5318, 4,6), 
					node(1203, 1));
			break;
		case VERY_RARE:
		case RARE:
			createNodeBatch(1, node(1115, 1), 
					node(2353, 1), 
					node(199, 1), 
					node(440, 1), 
					node(2434, 1), 
					node(173, 1), 
					node(565, 1,4), 
					node(562, 1,2), 
					node(561, 1), 
					node(5323, 1,4), 
					node(5320, 3), 
					node(5321, 2,4), 
					node(1339, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(2347, 1), 
					nodeB(882, 1,2,5), 
					nodeB(886, 1,5,10,11), 
					node(5324, 4), 
					node(5322, 3,4), 
					node(1446, 1), 
					node(1458, 1));
			break;
		}
		addObj(526, 1);// bones
		shakeTreasureTrail(player, MEDIUM_CLUE);
		shakeSummoningCharm(1, 7, 0.7, 0.4, 0.1);

	}
}
