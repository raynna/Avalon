package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class KalphiteQueen extends MobRewardNodeBuilder {

	public KalphiteQueen() {
		super(new Object[] { "Kalphite Queen", 1160 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(POTATO_CACTUS_NOTED, 10, 15), 
					node(WINE_OF_ZAMORAK_NOTED, 20, 40), 
					node(RUNE_ARROW, 100), 
					node(RUNE_CHAINBODY, 1), 
					node(BLOOD_RUNE, 50, 150), 
					node(DEATH_RUNE, 100, 150), 
					node(LAW_RUNE, 100, 150), 
					node(RUNE_SPEAR, 2));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(COINS, 22000, 77242), 
					node(SHARK, 14), 
					node(FIRE_TALISMAN_NOTED, 8, 12), 
					node(RUNE_WARHAMMER, 2));
			break;
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(OYSTER_PEARLS_NOTED, 33, 48), 
					node(DRAGON_CHAINBODY, 1), 
					node(DRAGON_2H_SWORD, 1), 
					node(LAVA_BATTLESTAFF, 1), 
					node(KQ_HEAD, 1));
			break;
		}
		shakeTreasureTrail(player, HARD_CLUE);
		shakeTreasureTrail(player, ELITE_CLUE);
		shakeSummoningCharm(6, 3, 3, 6, 9);

	}
}
