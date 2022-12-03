package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public final class Mugger extends MobRewardNodeBuilder {

	public Mugger() {
		super(new Object[] { "Mugger" });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(COINS, Utils.random(12, 500)));
			break;
		case UNCOMMON:
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(SLING, 1), node(STAFF_OF_AIR, 1));
			break;
		}
		addObj(BONES, 1);// bones
		shakeSummoningCharm(1, 0.8, 4, 0.25, 0.05);
	}
}
