package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public final class MutatedJadinkoGuard extends MobRewardNodeBuilder {

	public MutatedJadinkoGuard() {
		super(new Object[] { "Mutated jadinko guard", 13821 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 256)) {
			createNodeBatch(1, node(21369, 1));
		} else
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(GRIMY_AVANTOE_NOTED, 1), 
					node(GRIMY_CADANTINE_NOTED, 1), 
					node(GRIMY_IRIT_NOTED, 1), 
					node(GRIMY_KWUARM_NOTED, 1), 
					node(GRIMY_RANARR_NOTED, 1), 
					node(12175, 000), //GRIMY SPIRIT WEED NOTED
					node(GRIMY_TOADFLAX_NOTED, 1),
					node(14836, 1), //GRIMY WERGALI NOTED
					node(21376, 1),
					node(19922, 1),
					node(19927, 1),
					node(11932, 1),
					node(FELLSTALK_SEED, 2),
					node(HARRALANDER_SEED, 1),
					node(IRIT_SEED, 1),
					node(MARRENTILL_SEED, 1),
					node(RANARR_SEED, 1),
					node(STRAWBERRY_SEED, 1),
					node(TARROMIN_SEED, 1),
					node(TOADFLAX_SEED, 1),
					node(WATERMELON_SEED, 1),
					node(MAGIC_LOGS_NOTED, 2, 10),
					node(MAHOGANY_LOGS_NOTED, 13, 17),
					node(YEW_LOGS_NOTED, 13, 17),
					node(19967, 1)//JUJU TELE
					);
			break;
		case UNCOMMON:
			createNodeBatch(1, Utils.random(21377, 21388),//FRUITS
					node(AVANTOE_SEED, 1),
					node(BITTERCAP_MUSHROOM_SEED, 1),
					node(JANGERBERRY_SEED, 1),
					node(LIMPWURT_SEED, 1),
					node(POISON_IVY_SEED, 1),
					node(SPIRIT_WEED_SEED, 1),
					node(WHITEBERRY_SEED, 1),
					node(WILDBLOOD_SEED, 1)
					);
			break;
		case RARE:
			createNodeBatch(1,
					node(GRIMY_DWARF_WEED_NOTED, 1),
					node(GRIMY_LANTADYME_NOTED, 1),
					node(GRIMY_SNAPDRAGON_NOTED, 1),
					node(GRIMY_TORSTOL_NOTED, 1),
					node(DWARF_WEED_SEED, 1),
					node(LANTADYME_SEED, 1),
					node(SNAPDRAGON_SEED, 1),
					node(TORSTOL_SEED, 1),
					node(CURVED_BONE, 1),
					node(LONG_BONE, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, 
					node(CACTUS_SEED, 1));
			break;
		}
		addObj(21358, 2, 6);
		addObj(21359, 2, 5);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeTreasureTrail(player, ELITE_CLUE);
		shakeSummoningCharm(1, 5.5, 2.5, 2, 7.5);

	}
}
