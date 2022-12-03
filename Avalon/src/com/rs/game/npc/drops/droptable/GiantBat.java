package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class GiantBat extends MobRewardNodeBuilder {

	public GiantBat() {
		super(new Object[] { "Giant bat", 78 });
	}

	@Override
	public void populate(Player player) {
		addObj(BAT_BONES, 1);
	}
}
