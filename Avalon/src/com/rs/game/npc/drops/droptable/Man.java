package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Man extends MobRewardNodeBuilder {

	public Man() {
		super(new Object[] { "Man", 1 });
	} // every npc with the name cpor beast OR npcId of 1337 will drop these...

	@Override
	public void populate(Player player) {
		int rarity = rarityNode(player);
		switch (rarity) {
		case -1:
		case COMMON:
			createNodeBatch(1,
					// grimy guam
					node(199, 1),
					// sling
					node(19830, 1),
					// bronze bolts
					node(877, 2, 12),
					// coins
					node(995, 1, 40));
			break;
		case RARE:
		case VERY_RARE:
			createNodeBatch(1,
					// body rune
					node(559, 7),
					// chaos rune
					node(562, 2),
					// grimy cadantine
					node(215, 1),
					// grimy dwarf weed
					node(217, 1),
					// grimy lantadyme
					node(2485, 1),
					// staff of air
					node(1381, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1,
					// earth rune
					node(557, 4),
					// fire rune
					node(554, 6),
					// mind rune
					node(558, 9),
					// water rune
					node(555, 7),
					// grimy avantoe
					node(211, 1),
					// grimy harralander
					node(205, 1),
					// grimy irit
					node(209, 1),
					// grimy kwuarm
					node(213, 1),
					// grimy marrentill
					node(201, 1),
					// grimy ranarr
					node(207, 1),
					// grimy tarromin
					node(203, 1),
					// bronze arrow
					node(882, 7),
					// bronze helm
					node(1139, 1),
					// iron dagger
					node(1203, 1),
					// cabbage
					node(1965, 1),
					// fishing bait
					node(313, 1),
					// copper ore
					node(436, 1),
					// tin ore
					node(438, 1),
					// air talisman
					node(1438, 1),
					// earth talisman
					node(1440, 1));
			break;
		}
		addObj(526, 1);// bones
		shakeTreasureTrail(player, EASY_CLUE);

	}

}
