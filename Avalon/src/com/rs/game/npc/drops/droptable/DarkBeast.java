package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class DarkBeast extends MobRewardNodeBuilder {

	public DarkBeast() {
		super(new Object[] { "Dark beast", 2783 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(BLACK_2H_SWORD, 1), 
					node(BLACK_BATTLEAXE, 1), 
					node(ADAMANT_SQ_SHIELD, 1), 
					node(CHAOS_RUNE, 10), 
					node(BLOOD_RUNE, 7), 
					node(GRIMY_GUAM, 1, 2), 
					node(GRIMY_MARRENTILL, 1, 2), 
					node(GRIMY_TARROMIN, 1, 2), 
					nodeB(COINS, 64, 95, 152, 220, 299, 3000));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(BLACK_AXE, 1), 
					node(RUNE_CHAINBODY, 1), 
					node(AIR_RUNE, 47), 
					node(DEATH_RUNE, 3, 5), 
					node(LAW_RUNE, 3, 45), 
					node(GRIMY_HARRALANDER, 1, 2), 
					node(GRIMY_RANARR, 1, 2), 
					node(GRIMY_IRIT, 1, 2), 
					node(GRIMY_AVANTOE, 1, 2), 
					node(GRIMY_CADANTINE, 1, 2), 
					node(GRIMY_LANTADYME, 1, 2), 
					node(GRIMY_KWUARM, 1, 2), 
					node(GRIMY_DWARF_WEED, 1, 2), 
					node(ATTACK_POTION3, 1), 
					node(SHARK, 1, 2), 
					node(DEATH_TALISMAN, 1), 
					node(ADAMANT_BAR, 1));
			break;
		case RARE:
			createNodeBatch(1, node(RUNE_2H_SWORD, 1), 
					node(RUNE_BATTLEAXE, 1), 
					node(DARK_BOW, 1), 
					node(RUNE_FULL_HELM, 1), 
					node(RUNITE_BAR, 1), 
					node(CURVED_BONE, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(RUNE_SQ_SHIELD, 1));
			break;
		}
		addObj(BIG_BONES, 1);
		shakeSummoningCharm(1, 9, 4, 8, 14);

	}
}
