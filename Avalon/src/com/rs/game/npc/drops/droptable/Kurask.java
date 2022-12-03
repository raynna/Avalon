package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public final class Kurask extends MobRewardNodeBuilder {

	public Kurask() {
		super(new Object[] { "Kurask", 1608 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			int random = Utils.random(2, 8);
			createNodeBatch(1, node(BROAD_TIPPED_BOLTS, 1, 10), 
					node(UNFINISHED_BROAD_BOLTS, 21, 60), 
					node(BROAD_ARROWHEADS, 21, 60), 
					node(WHITE_BERRIES, 1), 
					node(LIMPWURT_ROOT, 2), 
					node(GRIMY_GUAM, 1), 
					node(GRIMY_MARRENTILL, 1), 
					node(GRIMY_TARROMIN, 1), 
					node(GRIMY_HARRALANDER, 1), 
					nodeS(BANANA, Utils.random(1, 2), (random <= 2 ? PINEAPPLE : PINEAPPLE_NOTED), random), 
					node(COINS, 22, 748),
					node(FLAX_NOTED, 20, 30),
					node(SUPER_STRENGTH1, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(NATURE_RUNE, 5, 30), 
					node(DEATH_RUNE, 1, 6), 
					node(MIND_RUNE, 4, 16), 
					node(ADAMANT_BOLTS, 5, 19), 
					node(MITHRIL_AXE, 1), 
					node(BROAD_ARROWS, 1, 10), 
					node(ADAMANT_PLATEBODY, 1), 
					node(GRIMY_IRIT, 1), 
					node(GRIMY_AVANTOE, 1), 
					node(GRIMY_KWUARM, 1), 
					node(GRIMY_CADANTINE, 1), 
					node(GRIMY_DWARF_WEED, 1), 
					node(IRIT_SEED, 1), 
					node(CACTUS_SEED, 1), 
					node(TOADFLAX_SEED, 1), 
					node(POISON_IVY_SEED, 1), 
					node(AVANTOE_SEED, 1), 
					node(KWUARM_SEED, 1), 
					node(CADANTINE_SEED, 1), 
					node(BELLADONNA_SEED, 1), 
					node(LANTADYME_SEED, 1), 
					node(MORCHELLA_MUSHROOM_SPORE, 4), 
					node(FELLSTALK_SEED, 1), 
					node(RANGING_POTION1, 1), 
					nodeB(BONES, 1, 2), 
					nodeB(BIG_BONES, 1, 2));
			break;
		case RARE:
			createNodeBatch(1, node(RUNE_LONGSWORD, 1), 
					node(MITHRIL_KITESHIELD, 1), 
					node(GRIMY_RANARR, 1), 
					node(GRIMY_LANTADYME, 1), 
					node(SNAPDRAGON_SEED, 1), 
					node(DWARF_WEED_SEED, 1), 
					node(PLAIN_PIZZA, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(LEAF_BLADED_SWORD, 1), 
					node(MYSTIC_ROBE_TOP_LIGHT, 1), 
					node(KURASK_HEAD, 1));
			break;
		}
		addObj(BONES, 1);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeSummoningCharm(1, 8.5, 27, 6.5, 1);
	}
}
