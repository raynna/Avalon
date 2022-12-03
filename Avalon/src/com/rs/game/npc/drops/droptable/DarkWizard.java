package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class DarkWizard extends MobRewardNodeBuilder {

	public DarkWizard() {
		super(new Object[] { "Dark wizard", 8874 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(995, 1,30), 
					node(1017, 1), 
					node(581, 1), 
					nodeB(556, 10, 18), 
					nodeB(559, 10, 18), 
					nodeB(562, 4, 5), 
					nodeB(557, 10, 18, 36), 
					nodeB(554, 10, 18), 
					nodeB(558, 10, 18), 
					nodeB(555, 10, 18), 
					node(1379, 1), 
					node(1381, 1));
			break;
		case RARE:
			createNodeBatch(1, node(1015, 1), 
					nodeB(565, 2, 4), 
					node(563, 3), 
					node(1442, 1), 
					node(1444, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(1440, 1), 
					node(1448, 1), 
					node(1383, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, nodeB(564, 2, 4), 
					node(561, 2, 5));
			break;
		}
		addObj(526, 1);// bones
	}
}
