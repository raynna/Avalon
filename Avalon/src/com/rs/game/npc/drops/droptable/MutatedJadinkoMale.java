package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class MutatedJadinkoMale extends MobRewardNodeBuilder {

	public MutatedJadinkoMale() {
		super(new Object[] { "Mutated jadinko male", 13822 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 128)) {
			createNodeBatch(1, node(21369, 1));
		} else
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(GRIMY_AVANTOE, 1), 
					node(GRIMY_IRIT, 1), 
					node(GRIMY_KWUARM, 1), 
					node(12174, 1), //GRIMY SPIRIT WEED
					node(GRIMY_TOADFLAX, 1), 
					node(14836, 1), //GRIMY WERGALI
					node(HARRALANDER_SEED, 1), 
					node(MARRENTILL_SEED, 1), 
					node(TARROMIN_SEED, 1), 
					node(21376, 1), //COMMON FRUIT
					node(MAHOGANY_LOGS_NOTED, 14, 16), 
					node(YEW_LOGS_NOTED, 13, 17),  
					node(19967, 1) //JUJU TELEPORT SPIRITBAG
					);
			break;
		case UNCOMMON:
			createNodeBatch(1, node(GRIMY_CADANTINE, 1), 
					node(GRIMY_RANARR, 1), 
					node(19927, 1), //BLUE BLOSSOM SEED
					node(FELLSTALK_SEED, 3), 
					node(19932, 1), //GREEN BLOSSOM SEED
					node(IRIT_SEED, 1), 
					node(JANGERBERRY_SEED, 1), 
					node(KWUARM_SEED, 1), 
					node(LIMPWURT_SEED, 1), 
					node(RANARR_SEED, 1), 
					node(19922, 1), //RED BLOSSOM_SEED
					node(WHITEBERRY_SEED, 1), 
					node(WILDBLOOD_SEED, 1), 
					node(21384, 1), //CAMOUFLAGED FRUIT
					node(21379, 1), //CANNIBAL FRUIT
					node(21383, 1), //DISEASED FRUIT
					node(LONG_BONE, 1), 
					node(MAGIC_LOGS_NOTED, 2, 7));
			break;
		case RARE:
			createNodeBatch(1, node(GRIMY_DWARF_WEED, 1), 
					node(GRIMY_LANTADYME, 1), 
					node(GRIMY_SNAPDRAGON, 1), 
					node(BITTERCAP_MUSHROOM_SEED, 1), 
					node(DWARF_WEED_SEED, 1), 
					node(POISON_IVY_SEED, 1), 
					node(SNAPDRAGON_SEED, 1), 
					node(SPIRIT_WEED_SEED, 1), 
					node(TORSTOL_SEED, 1), 
					node(21381, 1), 
					node(21380, 1), 
					node(21382, 1), 
					node(21385, 1), 
					node(21387, 1), 
					node(21386, 1), 
					node(21388, 1), 
					node(21378, 1), 
					node(21377, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(GRIMY_TORSTOL, 1), 
					node(CURVED_BONE, 1));
			break;
		}
		addObj(21358, 6, 10);
		addObj(21359, 4, 6);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeTreasureTrail(player, ELITE_CLUE);
		shakeSummoningCharm(1, 8, 4.5, 3.5, 13.5);

	}
}
