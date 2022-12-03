package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class PoisonSpider extends MobRewardNodeBuilder {

	public PoisonSpider() {
		super(new Object[] { "Poison spider", 134 });
	}

	@Override
	public void populate(Player player) {
		shakeSummoningCharm(1, 10, 28, 0.1, 0.3);
	}
}
