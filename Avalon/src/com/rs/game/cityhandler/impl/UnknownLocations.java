package com.rs.game.cityhandler.impl;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.others.FireSpirit;
import com.rs.game.npc.others.Strykewyrm;
import com.rs.game.player.Player;
import com.rs.game.player.RouteEvent;
import com.rs.game.player.dialogues.npcs.FremennikShipmaster;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

/**
 * 
 * @author Frostbite
 *<email@frostbitersps@gmail.com><skype:frostbiterps>
 */

public class UnknownLocations implements CityEvent {

	/**
	 * Each NPC Eventually.. has to be handled into the correct WorldTile.
	 */

	int[] strykeWyrms = {
			9462, 9464, 9466
	};

	@Override
	public boolean init() {
		registerNPC(14, this);
		registerNPC(359, this);
		registerNPC(528, this);
		registerNPC(529, this);
		registerNPC(657, this);
		registerNPC(1843, this);
		registerNPC(2259, this);
		registerNPC(2292, this);
		registerNPC(2417, this);
		registerNPC(2437, this);
		registerNPC(2438, this);
		registerNPC(2728, this);
		registerNPC(2729, this);
		registerNPC(4248, this);
		registerNPC(4250, this);
		registerNPC(5110, this);
		registerNPC(5532, this);
		registerNPC(5563, this);
		registerNPC(5559, this);
		registerNPC(6070, this);
		registerNPC(8091, this);
		registerNPC(9707, this);
		registerNPC(9711, this);
		registerNPC(15517, this);
		registerNPC(15518, this);
		for(int registerStrykeWyrms = 0; registerStrykeWyrms < strykeWyrms.length;registerStrykeWyrms++) {
			registerNPC(strykeWyrms[registerStrykeWyrms], this);
		}
		return registerNPC(15451, this);
	}

	@Override
	public boolean handleNPCClick(final Player player, final NPC npc) {

		/**
		 * Stryke Wyrms
		 */
		for(int wyrms = 0; wyrms < strykeWyrms.length;wyrms++) {
			if(npc.getId() == strykeWyrms[wyrms]) {
				Strykewyrm.handleStomping(player, npc);
			}
		}
		switch(npc.getId()) {

		/**
		 * Liquid Gold Nymph
		 */
		case 14:
			player.getSquealOfFortune().giveEarnedSpins(1);
			player.getPackets()
			.sendGameMessage(
					"The Liquid Gold Nymph gave you a reward to say thank you.");
			npc.finish();
			break;

			/**
			 * Mound NPC
			 */
		case 2417:
			if (npc.isCantInteract())
				return false;
			if (!npc.isAtMultiArea() || !player.isAtMultiArea()) {
				if (player.getAttackedBy() != npc
						&& player.getAttackedByDelay() > Utils
						.currentTimeMillis()) {
					player.getPackets().sendGameMessage(
							"You are already in combat.");
					return false;
				}
				if (npc.getAttackedBy() != player
						&& npc.getAttackedByDelay() > Utils
						.currentTimeMillis()) {
					if (npc.getAttackedBy() instanceof NPC) {
						npc.setAttackedBy(player); // changes enemy to
						// player,
						// player has priority over
						// npc on single areas
					} else {
						player.getPackets().sendGameMessage(
								"That npc is already in combat.");
						return false;
					}
				}
			}
			if (player.getSkills().getLevel(18) < 99) {
				player.getPackets()
				.sendGameMessage(
						"You need at least a slayer level of 99 to start this fight.");
				return false;
			}
			player.animate(new Animation(4278));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					npc.animate(new Animation(12795));
					npc.transformIntoNPC(3334);
					npc.setTarget(player);
					npc.setAttackedBy(player);
					stop();
				}

			}, 1, 2);
			break;


			/**
			 * Sorcerer Garden NPCS
			 */
		case 5532:
			player.getDialogueManager().startDialogue(
					"SorceressGardenNPCs", npc);
			break;

		case 5563:
			player.getDialogueManager().startDialogue(
					"SorceressGardenNPCs", npc);
			break;

			/**
			 * End of Sorcerer Garden NPCS
			 */

		case 5559:
			player.sendDeath(npc);
			break;


			/**
			 * Fire Spirit
			 */
		case 15451:
			if(npc instanceof FireSpirit) {
				FireSpirit spirit = (FireSpirit) npc;
				spirit.giveReward(player);
			}
			break;

		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(final Player player, final NPC npc) {
		switch(npc.getId()) {

		/**
		 * General Store - Unknown WorldTiles
		 */
		case 528:
		case 529:
			ShopsHandler.openShop(player, 1);
			break;

			/**
			 * Unknown
			 */
		case 1843:
			if (player.getY() < 10134)
				player.setNextWorldTile(new WorldTile(2837, 10140, 0));
			else
				player.setNextWorldTile(new WorldTile(2839, 10133, 0));
			break;

			/**
			 * Unknown
			 */
		case 657:
		case 2728:
		case 2729:
			boolean travel = true;
			int[] auras = { 20958, 22268, 20962, 22270, 20967, 22272,
					22280, 22282, 22284, 22286, 22288, 22290, 20965,
					22276, 22292, 22294, 22300, 22296, 22298, 22302,
					22899, 22905, 23848, 22901, 22907, 23850, 23874,
					23876, 23854, 22897 };

			for (Item item : player.getEquipment().getItems()
					.getContainerItems()) {
				if (item == null) {
					continue;
				}
				@SuppressWarnings("unused")
				ItemDefinitions ids = item.getDefinitions();
				if (player.getEquipment().wearingArmour()) {
					travel = false;
					break;
				}
				for (int auraId : auras) {
					if (item.getId() == auraId) {
						travel = false;
						break;
					}
				}
			}
			for (Item item : player.getInventory().getItems()
					.getContainerItems()) {
				if (item == null) {
					continue;
				}
				@SuppressWarnings("unused")
				ItemDefinitions ids = item.getDefinitions();
				if (player.getEquipment().wearingArmour()) {
					travel = false;
					break;
				}
				for (int auraId : auras) {
					if (item.getId() == auraId) {
						travel = false;
						break;
					}
				}
			}
			if (player.getAuraManager().isActivated()) {
				travel = false;
			}
			if (player.getFamiliar() != null) {
				travel = false;
			}

			if (travel) {
				player.lock();
				player.getInterfaceManager().sendInterface(299);
				player.getPackets().sendConfig(75, 1);

				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {
						player.unlock();
						player.closeInterfaces();
						player.setNextWorldTile(new WorldTile(2834,
								3335, 0));
						player.getDialogueManager().startDialogue(
								"SimpleMessage",
								"The ship arrives at Entrana.");
						player.getPackets().sendConfig(75, 0);
						return;
					}
				}, 12);
			}
			break;

			/**
			 * Rug Merchant
			 */

		case 2292:
			if (npc.getRegionId() == 13104)
				player.getDialogueManager().startDialogue(
						"RugMerchant", player, 1);
			else if (npc.getRegionId() == 13872)
				player.getDialogueManager().startDialogue(
						"RugMerchant", player, 2);
			else if (npc.getRegionId() == 12591)
				player.getDialogueManager().startDialogue(
						"RugMerchant", player, 3);
			else if (npc.getRegionId() == 13358 && npc.getY() > 2949)
				player.getDialogueManager().startDialogue(
						"RugMerchant", player, 4);
			else if (npc.getRegionId() == 13358
					|| npc.getRegionId() == 13357 && npc.getY() < 2949)
				player.getDialogueManager().startDialogue(
						"RugMerchant", player, 5);
			else if (npc.getRegionId() == 13099)
				player.getDialogueManager().startDialogue(
						"RugMerchant", player, 6);
			else if (npc.getRegionId() == 12843)
				player.getDialogueManager().startDialogue(
						"RugMerchant", player, 7);
			else if (npc.getRegionId() == 13613)
				player.getDialogueManager().startDialogue(
						"RugMerchant", player, 8);
			break;

			/**
			 * 
			 */
		case 2437:
			player.useStairs(-1, new WorldTile(2551, 3759, 0), 0, 1);
			break;

			/**
			 * Unknown
			 */
		case 2438:
			player.useStairs(-1, new WorldTile(2620, 3687, 0), 0, 1);
			break;

			/**
			 * Unknown
			 */
		case 4248:
			ShopsHandler.openShop(player, 76);
			break;

			/**
			 * Unknown
			 */
		case 4250:
			player.setRouteEvent(new RouteEvent(npc, new Runnable() {
				@Override
				public void run() {
					player.faceEntity(npc);
					if (!player.withinDistance(npc, 1))
						return;
					npc.faceEntity(player);
					player.getInterfaceManager().sendInterface(403);
				}
			}, true));
			break;

			/**
			 * Fremennik Ship Master
			 */

		case 9707:
			FremennikShipmaster.sail(player, true);
			break;


			/**
			 * Unknown
			 */
		case 15517:
		case 15518:
			player.setRouteEvent(new RouteEvent(npc, new Runnable() {
				@Override
				public void run() {
					player.faceEntity(npc);
				}
			}));
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick3(final Player player, final NPC npc) {


		switch(npc.getId()) {

		/**
		 * Unknown
		 */
		case 359:
			ShopsHandler.openShop(player, 10);
			break;


			/**
			 * Unknown
			 */
		case 4250:
			player.setRouteEvent(new RouteEvent(npc, new Runnable() {
				@Override
				public void run() {
					player.faceEntity(npc);
					if (!player.withinDistance(npc, 1))
						return;
					npc.faceEntity(player);
					ShopsHandler.openShop(player, 60);
				}
			}, true));
			break;

		}
		return false;
	}

	@Override
	public boolean handleNPCClick4(Player player, NPC npc) {
		
		switch(npc.getId()) {

		/**
		 * Unknown
		 */

		case 5110:
			ShopsHandler.openShop(player, 77);
			break;

		}
		return false;
	}

	@Override
	public boolean handleObjectClick(Player player, WorldObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleObjectClick2(Player player, WorldObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleObjectClick3(Player player, WorldObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleObjectClick4(Player player, WorldObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleObjectClick5(Player player, WorldObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	boolean registerNPC(int npcId, CityEvent cityevent) {
		return CityEventHandler.registerNPCs(npcId, this);
	}

	boolean registerObject(int objectId, CityEvent cityEvent) {
		return CityEventHandler.registerObjects(objectId, this);
	}

	@Override
	public boolean handleItemOnObject(Player player, WorldObject object, Item item) {
		// TODO Auto-generated method stub
		return false;
	}


}
