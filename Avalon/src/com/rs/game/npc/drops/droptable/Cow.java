package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Cow extends MobRewardNodeBuilder {

	public Cow() {
		super(new Object[] { "Cow", -1 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			break;
		case UNCOMMON:
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(SLING, 1), node(STAFF_OF_AIR, 1));
			break;
		}
		addObj(BONES, 1);// bones
		addObj(COWHIDE, 1);// cowhide
		addObj(RAW_BEEF, 1);// raw beef
		shakeSummoningCharm(1, 0.8, 4, 0.25, 0.05);
	}
}
