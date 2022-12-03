package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Yak extends MobRewardNodeBuilder {

	public Yak() {
		super(new Object[] { "Yak", 5529 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
		case UNCOMMON:
		case RARE:
		case VERY_RARE:
			break;
		}
		addObj(YAK_HIDE_10818, 1);
		addObj(HAIR_10814, 1);
		addObj(RAW_YAK_MEAT_10816, 1);
		addObj(BONES, 1);
	}
}
