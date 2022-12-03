package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;
import com.rs.game.player.content.pet.Pets;

public final class BabyBlueDragon extends MobRewardNodeBuilder {

	public BabyBlueDragon() {
		super(new Object[] { "Baby blue dragon", -1 });
	}

	@Override
	public void populate(Player player) {
		petRoll(player, Pets.BABY_BLUE_DRAGON.getBabyItemId(), 1000);
		addObj(BABYDRAGON_BONES, 1);// baby dragon bones
	}
}
