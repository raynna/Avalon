package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class InfernalMage extends MobRewardNodeBuilder {

	public InfernalMage() {
		super(new Object[] { "Infernal Mage", 1643 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(STAFF, 1), 
					node(STAFF_OF_FIRE, 1), 
					nodeB(EARTH_RUNE, 5, 10, 18, 36), 
					nodeB(FIRE_RUNE, 10, 18), 
					nodeB(COINS, 1, 2, 4, 29));
			break;
		case UNCOMMON:
			createNodeBatch(1, nodeB(BLOOD_RUNE, 4, 7), 
					nodeB(AIR_RUNE, 10, 18), 
					node(MIND_RUNE, 18), 
					nodeB(WATER_RUNE, 10, 18), 
					node(BODY_RUNE, 18));
			break;
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(LAVA_BATTLESTAFF, 1), 
					node(MYSTIC_FIRE_STAFF, 1), 
					node(MYSTIC_HAT_DARK, 1), 
					node(MYSTIC_BOOTS_DARK, 1));
			break;
		}
		addObj(BONES, 1);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeSummoningCharm(1, 33, 3, 2.5, 0.65);

	}
}
