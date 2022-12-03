package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Wizard extends MobRewardNodeBuilder {

	public Wizard() {
		super(new Object[] { "Wizard", -1 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(000, 1), 
					nodeB(556, 5, 12), 
					nodeB(559, 5, 12), 
					nodeB(557, 5, 12), 
					nodeB(554, 5, 12), 
					nodeB(558, 5, 12), 
					nodeB(555, 5, 12), 
					node(1448, 1), 
					node(1379, 1));
			break;
		case VERY_RARE:
		case RARE:
			createNodeBatch(1, node(565, 2), 
					nodeB(562, 2, 10), 
					node(563, 2), 
					node(561, 2));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(557, 1), 
					node(1444, 1));
			break;
		}
		addObj(526, 1);// bones
		shakeSummoningCharm(1, 11, 5, 3, 0.1);

	}
}
