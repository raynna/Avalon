package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class BrutalGreenDragon extends MobRewardNodeBuilder {

	public BrutalGreenDragon() {
		super(new Object[] { "Brutal green dragon", 5362 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 512)) {
			createNodeBatch(1, node("draconic visage", 1));
		} else
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node("adamant dart (p)", 16), 
					node("adamant javelin", 30), 
					node("coins", 129, 3000));
			break;
		case VERY_RARE:
			createNodeBatch(1, node("rune battleaxe", 1), 
					node("rune 2h sword", 1), 
					node("steel bar", 1, 2));
			break;
		case RARE:
			createNodeBatch(1, node("rune longsword", 1), 
					node("rune sq shield", 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node("mithril hatchet", 1), 
					node("adamant javelin", 30), 
					node("black hatchet", 1), 
					node("mithril battleaxe", 1), 
					node("mithril kiteshield", 1), 
					node("mithril 2h sword", 1), 
					node("adamant platebody", 1), 
					node("rune knife", 2), 
					node("rune dart", 10), 
					node("air rune", 75), 
					node("blood rune", 15), 
					node("death rune", 5, 50), 
					node("fire rune", 50), 
					node("law rune", 10, 12), 
					node("law rune", 45), 
					node("nature rune", 67, 79), 
					node("chaos rune", 90), 
					node("adamant bar", 1), 
					node("runite limbs", 1), 
					node("chocolate cake", 1));
			break;
		}
		addObj("dragon bones", 1);// bones
		addObj("black dragonhide", 1);// hide
		shakeTreasureTrail(player, HARD_CLUE);
		shakeSummoningCharm(3, 8.5, 26.5, 7, 1);

	}
}
