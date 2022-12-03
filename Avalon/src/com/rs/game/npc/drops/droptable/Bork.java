package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public final class Bork extends MobRewardNodeBuilder {

	public Bork() {
		super(new Object[] { "Bork", 7133 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 256)) {
			createNodeBatch(1, node(DRACONIC_VISAGE, 1));
		} else
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, 
					node(SAPPHIRE_NOTED_1608, 1), 
					node(UNCUT_DIAMOND_NOTED_1618, 1), 
					nodeB(UNCUT_RUBY_NOTED_1620, 1), 
					node(UNCUT_EMERALD_NOTED_1622, 1),
			node(LOOP_HALF_OF_KEY, 1),
			node(TOOTH_HALF_OF_KEY, 1),
			node(DRAGON_LONGSWORD, 1),
			node(LOOP_HALF_OF_KEY, 1),
			node(RUNE_ARROWTIPS_44, 113, 137),
			node(RAW_LOBSTER_NOTED_378, 135, 165),
			node(FLAX_NOTED, 450, 550),
			node(MOLTEN_GLASS_NOTED_1776, 45, 55),
			node(LOOP_HALF_OF_KEY, 1));
			
			break;
		case VERY_RARE:
			createNodeBatch(1,
					node(13845, 1),
					node(13846, 1),					
					node(13847, 1),
					node(13848, 1),
					node(13849, 1),					
					node(13850, 1),
					node(13851, 1),
					node(13852, 1),					
					node(13853, 1),
					node(13854, 1),
					node(13855, 1),					
					node(13856, 1),
					node(13857, 1));
			break;
		case RARE:
			createNodeBatch(1, 
					node(UNCUT_DIAMOND_NOTED_1618, 45, 55),
					node(UNCUT_DRAGONSTONE_NOTED_1632, 45, 55),
					node(DRAGON_SPEAR, 1),
					node(BATTLESTAFF_NOTED, 180, 220),
					node(RUNE_JAVELIN, 5),
					node(SHIELD_LEFT_HALF_2366, 1),
					node(WATER_TALISMAN_NOTED, 68, 82),
					node(EARTH_TALISMAN_NOTED, 68, 82),
					node(DRAGON_BONES_NOTED, 180, 220),
					node(PALM_SEEDLING_5486, 10),
					node(YEW_LOGS_NOTED, 675, 825),
					node(MAHOGANY_PLANK_NOTED_8783, 270, 330));
			
			break;
		case UNCOMMON:
			createNodeBatch(1, 
					node(UNCUT_DRAGONSTONE_1631, 1), 
					node(DRAGON_MED_HELM, 1), 
					node(RAW_SHARK_NOTED_384, 225, 275), 
					node(BIG_BONES_NOTED, 68, 82), 
					node(GRIMY_TORSTOL_NOTED, 90, 110), 
					node(GRIMY_SNAPDRAGON_NOTED, 90, 110), 
					node(SUPER_RESTORE4_NOTED_3025, 45, 55), 
					node(PRAYER_POTION4_NOTED_2435, 45, 55), 
					node(LANTADYME_SEED, 14, 16), 
					node(DWARF_WEED_SEED, 14, 16), 
					node(SOFT_CLAY_NOTED_1762, 450, 550), 
					node(YEW_LOGS_NOTED, 68, 82), 
					node(TEAK_PLANK_NOTED_8781, 45, 55));
			break;
		}
		addObj(BIG_BONES, 1);// dragon bones
		addObj(COINS, Utils.random(2, 10000));// dragonhide
		
		shakeSummoningCharm(4, 4, 3, 59, 0.9);

	}
}
