package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class SaradominWizard extends MobRewardNodeBuilder {

	public SaradominWizard() {
		super(new Object[] { "Saradomin wizard", 1264 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
			break;
		case COMMON:
		case UNCOMMON:
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(AIR_BATTLESTAFF, 1), 
					node(WATER_BATTLESTAFF, 1), 
					node(EARTH_BATTLESTAFF, 1), 
					node(FIRE_BATTLESTAFF, 1), 
					nodeB(AIR_RUNE, 189, 196), 
					nodeB(WATER_RUNE, 42, 126, 145, 146), 
					nodeB(EARTH_RUNE, 79, 86, 107), 
					nodeB(FIRE_RUNE, 51, 180, 188), 
					node(COSMIC_RUNE, 25), 
					nodeB(NATURE_RUNE, 28, 30), 
					nodeB(LAW_RUNE, 9, 10), 
					node(DEATH_RUNE, 45), 
					nodeB(BLOOD_RUNE, 8, 40), 
					nodeB(COINS, 77, 89));
			break;
		}
		addObj(BONES, 1);

	}
}
