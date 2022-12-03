package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Nechryael extends MobRewardNodeBuilder {

	public Nechryael() {
		super(new Object[] { "Nechryael", 1613 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(STEEL_2H_SWORD, 1), 
					node(STEEL_BATTLEAXE, 1), 
					node(STEEL_AXE, 1), 
					nodeB(DEATH_RUNE, 5, 10), 
					node(BELLADONNA_SEED, 1), 
					node(IRIT_SEED, 1), 
					node(CACTUS_SEED, 1), 
					node(MORCHELLA_MUSHROOM_SPORE, 4), 
					nodeB(COINS, 11, 44, 132, 220, 460, 3000, 8992), 
					node(TUNA, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(RUNE_FULL_HELM, 1), 
					node(ADAMANT_PLATELEGS, 1), 
					node(MITHRIL_KITESHIELD, 1), 
					node(RUNE_BOOTS, 1), 
					nodeB(CHAOS_RUNE, 3, 37), 
					node(AVANTOE_SEED, 1), 
					node(KWUARM_SEED, 1), 
					node(POISON_IVY_SEED, 1), 
					node(TOADFLAX_SEED, 1), 
					node(CADANTINE_SEED, 1), 
					node(FELLSTALK_SEED, 1), 
					node(SNAPDRAGON_SEED, 1), 
					node(THREAD, 10), 
					node(GOLD_BAR, 1));
			break;
		case RARE:
			createNodeBatch(1, node(DWARF_WEED_SEED, 1), 
					node(LANTADYME_SEED, 1), 
					node(RUNITE_BAR, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(RUNE_SQ_SHIELD, 1), 
					node(RUNE_LONGSWORD, 1), 
					node(RUNE_BATTLEAXE, 1), 
					node(RUNE_2H_SWORD, 1));
			break;
		}
		addObj(INFERNAL_ASHES, 1);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeSummoningCharm(1, 9, 4, 30.5, 1.05);

	}
}
