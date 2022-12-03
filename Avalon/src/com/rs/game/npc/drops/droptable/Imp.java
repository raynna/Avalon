package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Imp extends MobRewardNodeBuilder {

	public Imp() {
		super(new Object[] { "Imp", 708 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(2309, 1), 
					node(1965, 1), 
					node(579, 1), 
					node(1011, 1), 
					node(556, 5), 
					node(559, 5), 
					node(557, 5), 
					node(554, 5), 
					node(558, 5), 
					node(555, 5));
			break;
		case VERY_RARE:
		case RARE:
		case UNCOMMON:
			createNodeBatch(1, node(592, 1), 
					node(1474, 1), 
					node(2146, 1), 
					node(1949, 1), 
					node(434, 1), 
					node(1944, 1), 
					node(956, 1), 
					node(1470, 1), 
					node(1947, 1), 
					node(1476, 1), 
					node(1472, 1), 
					node(577, 1), 
					node(199, 1));
			break;
		}
		addObj(20264, 1);// impious ashes

	}
}
