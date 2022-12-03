package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public final class BronzeDragon extends MobRewardNodeBuilder {

	public BronzeDragon() {
		super(new Object[] { "Bronze dragon", 1590 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 224)) {
			createNodeBatch(1, node(Utils.random(1) == 0 ? DRAGON_PLATELEGS : DRAGON_PLATESKIRT, 1));
		} else
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, nodeB(ADAMANT_JAVELIN, 30, 60), 
					nodeB(LAW_RUNE, 10, 45), 
					node(COINS, 168, 3000));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(ADAMANT_DART, 16), 
					node(MITHRIL_AXE, 1), 
					node(MITHRIL_2H_SWORD, 1), 
					node(MITHRIL_BATTLEAXE, 1), 
					node(MITHRIL_BOLTS, 2, 6), 
					nodeB(MITHRIL_BOLTS, 8, 10, 11, 13, 16),
					nodeB(RUNE_KNIFE, 2, 5), 
					node(MITHRIL_KITESHIELD, 1), 
					node(FIRE_RUNE, 50, 150), 
					nodeB(BLOOD_RUNE, 15, 30), 
					node(NATURE_RUNE, 67), 
					nodeB(DEATH_RUNE, 25, 45), 
					node(SWORDFISH, 1, 2));
			break;
		case RARE:
			createNodeBatch(1, node(RUNE_JAVELIN, 1), 
					node(RUNE_LONGSWORD, 1), 
					node(RUNE_BATTLEAXE, 1), 
					node(ADAMANT_PLATEBODY, 1), 
					node(RUNE_SQ_SHIELD, 1), 
					node(ADAMANT_BAR, 1), 
					node(RUNITE_BAR, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(RUNE_2H_SWORD, 1), 
					node(DRAGON_PLATESKIRT, 1), 
					node(DRAGON_PLATELEGS, 1));
			break;
		}
		addObj(DRAGON_BONES, 1);
		addObj(BRONZE_BAR, 5);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeTreasureTrail(player, ELITE_CLUE);
		shakeSummoningCharm(1, 13.5, 31, 12.5, 3.5);

	}
}
