package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public final class SteelDragon extends MobRewardNodeBuilder {

	public SteelDragon() {
		super(new Object[] { "Steel dragon", 1592 });
	}

	@Override
	public void populate(Player player) {
		addObj(DRAGON_BONES, 1);
		addObj(STEEL_BAR, 5);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeTreasureTrail(player, ELITE_CLUE);
		shakeSummoningCharm(2, 12.5, 32.5, 13, 3);
		if (shake(player, 416)) {
			createNodeBatch(1, node(DRACONIC_VISAGE, 1));
		} else if (shake(player, 112)) {
			createNodeBatch(1, node(Utils.random(1) == 0 ? DRAGON_PLATELEGS : DRAGON_PLATESKIRT, 1));
		} else
			switch (rarityNode(player)) {
			case -1:
			case COMMON:
				createNodeBatch(1, node(RUNE_JAVELIN, 4, 8), node(BLOOD_RUNE, 15), node(SUPER_STRENGTH1, 1),
						nodeB(COINS, 270, 550, 3000));
				break;
			case UNCOMMON:
				createNodeBatch(1, node(ADAMANT_2H_SWORD, 1), node(ADAMANT_BATTLEAXE, 1), node(ADAMANT_AXE, 1),
						node(ADAMANT_SQ_SHIELD, 1), node(ADAMANT_BOLTS, 2, 12), node(RUNE_DARTP, 9),
						node(RUNE_KNIFE, 1, 7), node(SOUL_RUNE, 3, 5), node(ADAMANT_BAR, 2), node(CURRY, 1, 2),
						node(RUNITE_LIMBS, 1));
				break;
			case RARE:
				createNodeBatch(1, node(RUNE_AXE, 1), node(RUNE_MACE, 1), node(RUNE_LONGSWORD, 1),
						node(RUNE_BATTLEAXE, 1), node(RUNE_MED_HELM, 1), node(RUNE_FULL_HELM, 1),
						node(RUNE_SQ_SHIELD, 1), node(RUNE_KITESHIELD, 1), node(STEEL_ARROW, 150),
						nodeB(RUNITE_BOLTS, 2, 4, 8), nodeB(DEATH_RUNE, 15, 45), nodeB(CHAOS_RUNE, 25, 50),
						node(NATURE_RUNE, 60, 79), nodeB(LAW_RUNE, 45, 90), node(RUNITE_BAR, 1), node(SUPER_ATTACK3, 1),
						node(SUPER_DEFENCE4, 1));
				break;
			case VERY_RARE:
				createNodeBatch(1, node(RUNE_2H_SWORD, 1));
				break;
			}
	}
}
