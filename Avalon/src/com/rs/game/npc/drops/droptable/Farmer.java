package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Farmer extends MobRewardNodeBuilder {

	public Farmer() {
		super(new Object[] { "Farmer", 1757 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(995, 3,15), 
					node(5329, 1), 
					node(1965, 1), 
					node(313, 1), 
					node(5341, 1), 
					node(882, 7), 
					node(877, 3,12), 
					node(1139, 1), 
					node(436, 1), 
					node(556, 4), 
					node(554, 6), 
					node(558, 9), 
					node(5318, 1,4), 
					node(5323, 1,4), 
					node(5324, 4), 
					node(12148, 4), 
					node(5319, 1), 
					node(5320, 1), 
					node(5322, 4), 
					node(5321, 4));
			break;
		case VERY_RARE:
		case RARE:
			createNodeBatch(1, node(5331, 1), 
					node(5345, 1), 
					node(211, 1), 
					node(213, 1), 
					node(207, 1), 
					node(217, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(1440, 1), 
					node(1203, 1));
			break;
		}
		addObj(526, 1);// bones
		shakeTreasureTrail(player, EASY_CLUE);

	}
}
