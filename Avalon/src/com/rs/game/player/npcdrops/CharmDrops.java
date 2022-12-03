package com.rs.game.player.npcdrops;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * 
 * @author Andreas, Dennis - AvalonPK
 * 
 */

public class CharmDrops {

	public static final double NO_CHARMS_CHANCE = 50;

	public static final int GOLD_CHARM_MAX_DROP = 4, GREEN_CHARM_MAX_DROP = 4, CRIMSON_CHARM_MAX_DROP = 3,
			BLUE_CHARM_MAX_DROP = 2;

	public enum Charms {

		GOLD_CHARM(12158, 45.0),

		GREEN_CHARM(12159, 27.0),

		CRIMSON_CHARM(12160, 11.0),

		BLUE_CHARM(12163, 5.0);

		private int itemId;

		private double chance;

		private Charms(int itemId, double chance) {
			this.itemId = itemId;
			this.chance = chance;
		}

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		public double getChance() {
			return chance;
		}

		public void setChance(double chance) {
			this.chance = chance;
		}

	}

	public static void sendDrop(Player player, NPC npc) {
		Charms drop = calculateCharm(player, npc);
		int size = npc.getSize();
		if (drop == null)
			return;
		World.updateGroundItem(new Item(drop.getItemId(), getCharmsAmount(npc, drop)),
				new WorldTile(npc.getCoordFaceX(size), npc.getCoordFaceY(size), npc.getPlane()), player, 60, 0);
	}

	public static Charms calculateCharm(Player player, NPC npc) {
		if (npc.getCombatLevel() < 10)
			return null;
		if ((npc.getId() != 7134) && Utils.getRandomDouble(100) < NO_CHARMS_CHANCE)
			return null;
		while (true) {
			double chance = Utils.getRandomDouble(100);
			Charms charm = Charms.values()[Utils.getRandom(Charms.values().length - 1)];
			if ((charm.getChance()) > chance) {
				return charm;
			} else
				continue;
		}
	}

	public static int getCharmsAmount(NPC npc, Charms charm) {
		if (npc.getId() == 7134)
			return Utils.random(10, 38);
		int charmAmount = (int) (Utils.random(npc.getCombatLevel()) * 0.1);
		switch (charm) {
		case BLUE_CHARM:
			return charmAmount;
		case CRIMSON_CHARM:
			return charmAmount;
		case GOLD_CHARM:
			return charmAmount;
		case GREEN_CHARM:
			return charmAmount;
		}
		return -1;
	}

}
