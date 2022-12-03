package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public final class AbyssCreature extends MobRewardNodeBuilder {

	public AbyssCreature() {
		super(new Object[] { 2263, 2265, 2264});
	}

	@Override
	public void populate(Player player) {
			switch (rarityNode(player)) {
			case -1:
			case COMMON:
				createNodeBatch(1, 
						node(AIR_TALISMAN, 1), 
						node(WATER_TALISMAN, 1),
						node(EARTH_TALISMAN, 1),
						node(FIRE_TALISMAN, 1),
						node(MIND_TALISMAN, 1),
						node(BODY_TALISMAN, 1),
						node(PURE_ESSENCE_NOTED, Utils.random(5, 8))
				);
				break;
			case UNCOMMON:
				createNodeBatch(1, 
						node(COSMIC_TALISMAN, 1),
						node(CHAOS_TALISMAN, 1),
						node(NATURE_TALISMAN, 1),
						node(12161, 1)
				);
				break;
			case RARE:
				createNodeBatch(1, 
						node(BINDING_NECKLACE_5521, 1), 
						node(GIANT_POUCH_5514, 1),
						node(LARGE_POUCH_5512, 1),
						node(MEDIUM_POUCH_5510, 1),
						node(SMALL_POUCH_5509, 1),
						node(ELEMENTAL_TALISMAN_5516, 1),
						node(PURE_ESSENCE_NOTED, Utils.random(15, 30))
				);
				break;
			}
		addObj(ASHES, 1);
		shakeTreasureTrail(player, MEDIUM_CLUE);
		shakeSummoningCharm(1, 16, 18, 22, 9);
	}
}