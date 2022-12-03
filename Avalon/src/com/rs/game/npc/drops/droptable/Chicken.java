package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Chicken extends MobRewardNodeBuilder {

	public Chicken() {
		super(new Object[] { "Chicken", 41 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
		case UNCOMMON:
		case RARE:
			dissectNodeBatch(1, nodeB(FEATHER, 5, 10, 15));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(CORNUCOPIA, 1));
			break;
		}
		addObj(BONES, 1);// bones
		addObj(RAW_CHICKEN, 1);// raw chicken
	}
}
