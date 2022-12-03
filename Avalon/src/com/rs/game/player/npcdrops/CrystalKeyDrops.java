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

public class CrystalKeyDrops {

	public enum KeyPart {

		TOOTH_HALF_KEY(985, 55),

		LOOP_HALF_KEY(987, 45);

		private int itemId;

		private double chance;

		private KeyPart(int itemId, double chance) {
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
		KeyPart drop = calculateDrop(player, npc);
		int size = npc.getSize();
		if (drop == null)
			return;
		player.getPackets().sendGameMessage("You recieve a crystal key piece in loot!");
		World.addGroundItem(new Item(drop.getItemId(), 1),
				new WorldTile(npc.getCoordFaceX(size), npc.getCoordFaceY(size), npc.getPlane()), player, true, 60, 0);
	}

	public static KeyPart calculateDrop(Player player, NPC npc) {
		final double DROP_CHANCE = (0.125 * (npc.getCombatLevel() / 5));
		if (Utils.getRandom(100) > DROP_CHANCE)
			return null;
		if (npc.getCombatLevel() < 10)
			return null;
		while (true) {
			double chance = Utils.getRandomDouble(100);
			KeyPart items = KeyPart.values()[Utils.getRandom(KeyPart.values().length - 1)];
			if ((items.getChance()) > chance) {
				return items;
			} else
				continue;
		}
	}

}
