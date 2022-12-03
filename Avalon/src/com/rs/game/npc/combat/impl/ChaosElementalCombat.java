package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.net.decoders.handlers.ButtonHandler;
import com.rs.utils.Utils;

public class ChaosElementalCombat extends CombatScript {

	/**
	 * @author Phillip
	 */

	@Override
	public Object[] getKeys() {
		return new Object[] { 3200 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();

		/**
		 * Random selection between either an offensive attack or either of the
		 * special attacks (Offensive = 0,3) (Others = 4 and 5 respectively)
		 */
		int attackOption = Utils.getRandom(10);// 5

		/**
		 * Main Offensive Attack: Will fire a projectile at the player with a
		 * random attack. - Mage: Main Attack will hit up to 220 - Range: Other
		 * Attack will hit up to 280 - Melee: Rare Attack will hit up to 240
		 */

		if (attackOption <= 8) {
			switch (Utils.random(8)) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:// normal attack
				npc.animate(new Animation(5443));
				delayHit(npc, 2, target,
						getMagicHit(npc, getRandomMaxHit(npc, 220, NPCCombatDefinitions.MAGE, target)));
				// Entity shooter, Entity receiver, int gfxId, int startHeight,
				// int endHeight, int speed, int delay, int curve, int
				// startDistanceOffset
				World.sendProjectile(npc, target, 1279, 24, 24, 43, 32, 0, -2);
				break;

			case 5:
			case 6:
			case 7:
			case 8:
				npc.animate(new Animation(5443));
				delayHit(npc, 2, target,
						getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
				World.sendProjectile(npc, target, 1279, 24, 24, 43, 32, 0, 0);
				break;

			default:
				npc.animate(new Animation(5443));
				delayHit(npc, 2, target,
						getMeleeHit(npc, getRandomMaxHit(npc, 240, NPCCombatDefinitions.MELEE, target)));
				World.sendProjectile(npc, target, 1279, 24, 24, 43, 32, 0, 0);
				break;
			}

		} else if (attackOption == 9) {

			/**
			 * Secondary Attack: Green - Disarm the player of a randomly
			 * equipped item. Will only take off an item if the player has space
			 * in their inventory. - If attack cannot be made will switch to
			 * offensive attack.
			 */

			if (target instanceof Player) {
				Player player = (Player) target;
				boolean hasSpace = player.getInventory().hasFreeSlots();

				int slot = Utils.random(10);

				if (player.getEquipment().getItem(slot) != null && hasSpace) {
					ButtonHandler.sendRemoveEquipment(player, slot);
					player.sm("Npc " + npc.getId() + " has removed slot " + slot);
				}
			}
			npc.gfx(new Graphics(999));
			npc.animate(new Animation(5443));
			World.sendProjectile(npc, target, 552, 34, 16, 40, 35, 16, 0);

		} else if (attackOption == 10) {

			/**
			 * Secondary Attack: Red - Randomly teleport the player to a space
			 * either 9 to 20 squares away from the NPC location.
			 */

			int tile = (npc.getX() + Utils.random(20));
			int tile2 = (npc.getY() + Utils.random(10));
			int dir = Utils.random(Utils.DIRECTION_DELTA_X.length);
			for (int i = 0; i < 10; i++) {
				if (World.checkWalkStep(target.getPlane(), tile, tile2, dir, 1)) {
					target.setNextWorldTile(new WorldTile(tile, tile2, target.getPlane()));
					npc.animate(new Animation(5442));
					target.setNextWorldTile(new WorldTile(tile, tile2, target.getPlane()));
					break;
				}
			}
		}

		return defs.getAttackDelay();
	}
}