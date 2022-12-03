package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class CrawlingHand extends MobRewardNodeBuilder {

	public CrawlingHand() {
		super(new Object[] { "Crawling Hand", 1648 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(1059, 1), 
					node(2922, 1), 
					nodeB(995, 5, 8, 312, 442));
			break;
		case UNCOMMON:
		case RARE:
			createNodeBatch(1, node(2902, 1), 
					node(2912, 1), 
					node(2932, 1), 
					node(2942, 1), 
					node(1635, 1), 
					node(1637, 1), 
					node(1639, 1), 
					node(1641, 1), 
					node(592, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(7975, 1), 
					node(4115, 1));
			break;
		}
		addObj(BONES, 1);
		shakeSummoningCharm(1, 9.5, 0.65, 0.65, 0.15);

	}
}
