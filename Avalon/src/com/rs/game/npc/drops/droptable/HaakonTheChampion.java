package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class HaakonTheChampion extends MobRewardNodeBuilder {

	public HaakonTheChampion() {
		super(new Object[] { "Haakon the champion", -1 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(995, 5, 32), 
					node(2142, 1), 
					node(882, 10), 
					nodeB(884, 3,8,10), 
					node(438, 1), 
					node(1375, 1), 
					node(1349, 1), 
					node(1420, 1));
			break;
		case VERY_RARE:
		case RARE:
		case UNCOMMON:
			createNodeBatch(1, node(1595, 1), 
					node(948, 1), 
					node(1917, 1), 
					node(956, 1), 
					node(888, 5), 
					node(562, 3), 
					node(557, 5), 
					node(554, 8), 
					node(563, 2), 
					node(558, 10));
			break;
		}
		addObj(526, 1);// bones
		shakeTreasureTrail(player, EASY_CLUE);
		shakeSummoningCharm(1, 4, 1.4, 0.3, 0.3);

	}
}
