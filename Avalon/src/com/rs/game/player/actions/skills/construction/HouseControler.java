package com.rs.game.player.actions.skills.construction;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.others.ServantNPC;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.ModernMagicks;
import com.rs.game.player.actions.combat.PlayerCombat;
import com.rs.game.player.actions.construction.SitChair;
import com.rs.game.player.actions.skills.construction.HouseConstants.Builds;
import com.rs.game.player.actions.skills.cooking.Cooking;
import com.rs.game.player.actions.skills.cooking.Cooking.Cookables;
import com.rs.game.player.actions.skills.prayer.BoneOffering;
import com.rs.game.player.actions.skills.prayer.Burying.Bone;
import com.rs.game.player.content.PlayerLook;
import com.rs.game.player.controlers.Controler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.net.decoders.WorldPacketsDecoder;
import com.rs.utils.Utils;

public class HouseControler extends Controler {

	private transient byte ringType;
	private House house;

	@Override
	public void start() {
		this.house = (House) getArguments()[0];
		getArguments()[0] = null; //its was gonna be saved unless somehow in a server restart but lets be safe
	}

	/**
	 * return process normaly
	 */
	@Override
	public boolean processObjectClick5(WorldObject object) {
		//System.out.println(object.getXInChunk() + ", " + object.getYInChunk() + ", 5");
		if (object.getDefinitions().containsOption(4, "Build")) {
			if (!house.isOwner(player)) {
				player.getPackets().sendGameMessage("You can only do that in your own house.");
				return false;
			}
			if (house.isDoor(object)) {
				house.openRoomCreationMenu(object);
			} else {
				for (Builds build : Builds.values()) {
					if (build.containsId(object.getId())) {
						house.openBuildInterface(object, build);
						return false;
					}

				}
			}
		} else if (object.getDefinitions().containsOption(4, "Remove")) {
			if (!house.isOwner(player)) {
				player.getPackets().sendGameMessage("You can only do that in your own house.");
				return false;
			}
			house.openRemoveBuild(object);
		}
		return false;
	}

	@Override
	public boolean processNPCClick1(NPC npc) {
		if (npc instanceof ServantNPC) {
			npc.faceEntity(player);
			if (!house.isOwner(player)) {
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npc.getId(), "Sorry, I only serve my master.");
				return false;
			}
			player.getDialogueManager().startDialogue("NewServantD", npc, false);
			return false;
		}
		return true;
	}

	@Override
	public boolean processNPCClick2(NPC npc) {
		if (npc instanceof ServantNPC) {
			npc.faceEntity(player);
			if (!house.isOwner(player)) {
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npc.getId(), "The servant ignores your request.");
				return false;
			}
			player.getDialogueManager().startDialogue("NewServantD", npc, true);
			return false;
		}
		return true;
	}

	@Override
	public boolean processItemOnNPC(NPC npc, Item item) {
		player.sm("called");
		if (npc instanceof ServantNPC) {
			npc.faceEntity(player);
			if (!house.isOwner(player)) {
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npc.getId(), "The servant ignores your request.");
				return false;
			}
			player.getDialogueManager().startDialogue("ItemOnServantD", npc, item.getId(), house.getServant().isSawmill());
			return true;
		}
		return false;
	}

	@Override
	public boolean processObjectClick1(WorldObject object) {
		if (object.getId() == HouseConstants.HObject.EXIT_PORTAL.getId())
			house.leaveHouse(player, House.KICKED);
		else if (object.getId() == HouseConstants.HObject.CLAY_FIREPLACE.getId() || object.getId() == HouseConstants.HObject.STONE_FIREPLACE.getId() || object.getId() == HouseConstants.HObject.MARBLE_FIREPLACE.getId()) {
			player.getPackets().sendGameMessage("Use some logs on the fireplace in order to light it.");
			return false;
		}
		else if (object.getId() == HouseConstants.HObject.OAK_LARDER.getId() || object.getId() == HouseConstants.HObject.TEAK_LARDER.getId() || object.getId() == HouseConstants.HObject.WOODEN_LARDER.getId()) {
			player.getDialogueManager().startDialogue("LarderDialouge", object.getId());
		} else if (Builds.SHELVES.containsObject(object) || Builds.SHELVES_2.containsObject(object)) {
			player.getDialogueManager().startDialogue("POHShelvesDialouge", object.getId());
		} else if (object.getId() == HouseConstants.HObject.GLORY.getId()) {
			player.getDialogueManager().startDialogue("Transportation", "Edgeville", new WorldTile(3087, 3496, 0),
					"Forinthry Dungeon", new WorldTile(3081, 3648, 0), "Draynor Village", new WorldTile(3105, 3251, 0),
					"Al Kharid", new WorldTile(3293, 3163, 0));
		} else if (Builds.DRESSERS.containsObject(object)) {
			PlayerLook.openHairdresserSalon(player);
		} else if (Builds.WARDROBE.containsObject(object)) {
			PlayerLook.openThessaliasMakeOver(player);
		} else if (object.getId() >= HouseConstants.HObject.CRUDE_WOODEN_CHAIR.getId() && object.getId() <= HouseConstants.HObject.MAHOGANY_ARMCHAIR.getId()) {//TODO 
			int chair = object.getId() - HouseConstants.HObject.CRUDE_WOODEN_CHAIR.getId();
			player.getActionManager().setAction(new SitChair(player, chair, object));
		} else if (object.getId() >= HouseConstants.HObject.WOOD_BENCH.getId() && object.getId() <= HouseConstants.HObject.GILDED_BENCH.getId()) {//TODO
			int bench = object.getId() - HouseConstants.HObject.WOOD_BENCH.getId();
			player.getActionManager().setAction(new SitChair(player, bench + 6, object));
		} else if (Builds.WEAPONS_RACK.containsObject(object)) {
			int length = object.getId() == 13383 ? 5 : object.getId() == 13382 ? 4 : 2;
			player.getDialogueManager().startDialogue("WeaponsRackD", length);
		} else if (object.getId() >= HouseConstants.HObject.OAK_THRONE.getId() && object.getId() <= HouseConstants.HObject.DEMONIC_THRONE.getId()) {//TODO
			int throne = object.getId() - HouseConstants.HObject.OAK_THRONE.getId();
			player.getActionManager().setAction(new SitChair(player, throne + 12, object));
			return false;
		} else if (Builds.DUNGEON_DOOR1.containsObject(object) || Builds.DUNGEON_DOOR2.containsObject(object)) {
			if (!house.isOwner(player) && house.isChallengeMode()) {
				player.getPackets().sendGameMessage("The door is locked!");
				return false;
			}
			passDoor(object);
			return false;
		} else if (Builds.ALTAR.containsObject(object)) {
			return true; //do what it does to altars outside world
		} else if (Builds.LEVER.containsObject(object)) {
			if (!house.isOwner(player)) {
				player.getPackets().sendGameMessage("You can only do that in your own house.");
				return false;
			}
			if (house.isBuildMode()) {
				player.getPackets().sendGameMessage("You cannot do this while in building mode.");
				return false;
			}
			house.leverEffect(object);
		} else if (Builds.ROPE_BELL_PULL.containsObject(object)) {
			if (!house.isOwner(player)) {
				player.getPackets().sendGameMessage("I'd better not do this...");
				return false;
			}
			house.callServant(true);
			return false;
		} else if (object.getId() == 13523) {
			return true;
		//else if (HouseConstants.Builds.TELESCOPE.containsObject(object)) {//TODO 
		//	ShootingStars.openTelescope(player);
		} else if (Builds.TRAPDOOR.containsObject(object)) {
			house.climbTrapdoor(player, object, false);
		} else if (Builds.LADDER.containsObject(object)) {
			house.climbTrapdoor(player, object, true);
		} else if (Builds.MUSIC_SPOT.containsObject(object)) {
			if (object.getId() == 13214) {
				player.getPackets().sendMusicEffect(50);
				player.animate(new Animation(3674));
			} else if (object.getId() == 13216) {
				player.getPackets().sendMusicEffect(72);
				player.animate(new Animation(3675));
			} else if (object.getId() == 13215) {
				player.getPackets().sendMusicEffect(90);
				player.animate(new Animation(3674));
			}
		} else if (Builds.ALTAR_LAMP.containsObject(object)) {
			if (house.isBuildMode()) {
				player.getPackets().sendGameMessage("You cannot do this while in building mode.");
				return false;
			} else if (!player.getInventory().containsItemToolBelt(590) || object.getId() >= HouseConstants.HObject.INSCENCE_BURNER.getId() && object.getId() <= HouseConstants.HObject.MARBLE_BURNER.getId() && !player.getInventory().containsItem(251, 1)) {
				player.getPackets().sendGameMessage("You do not have the required items to light this.");
				return false;
			}
			player.faceObject(object);
			player.getPackets().sendGameMessage("You use your tinderbox to light the torches.");
			player.lock(2);
			player.animate(new Animation(3687));
			player.getInventory().deleteItem(new Item(251, 1));
			WorldObject objectR = new WorldObject(object);
			objectR.setId(object.getId() + 1);
			int time = 1210000;
			World.spawnObjectTemporary(objectR, time); //lights for 2 min 10 seconds
			World.sendObjectAnimation(objectR, new Animation(13209));
			for (int burner : HouseConstants.INCENSE_BURNERS) {
				if (objectR.getId() == burner) {
					house.setBurnerCount(house.getBurnerCount() + 1);
					WorldTasksManager.schedule(new WorldTask() {

						@Override
						public void run() {
							house.setBurnerCount(house.getBurnerCount() - 1);
						}
					}, time / 600);
				}
			}
		} else if (Builds.LECTURN.containsObject(object)) {
			if (house.isBuildMode()) {
				player.getPackets().sendGameMessage("You cannot do this while in building mode.");
				return false;
			}
			TabletMaking.openTabInterface(player, object.getId() - 13642);
		} else if (Builds.BOOKCASE.containsObject(object))
			player.getPackets().sendGameMessage("You search the bookcase but find nothing.");
		else if (Builds.HEAD_TROPHY.containsObject(object)) {
			player.getDialogueManager().startDialogue("SimplePlayerMessage", "Hello, having a nice day " + object.getDefinitions().name + "?");
		} else if (object.getId() == HouseConstants.HObject.DUNGEON_ENTRACE.getId() || Builds.STAIRCASE.containsObject(object) || Builds.STAIRCASE_DOWN.containsObject(object)) {
			if (object.getDefinitions().getOption(1).equals("Climb"))
				player.getDialogueManager().startDialogue("ClimbHouseStairD", object, house);
			else
				house.climbStaircase(player, object, object.getDefinitions().getOption(1).equals("Climb-up"), object.getId() == HouseConstants.HObject.DUNGEON_ENTRACE.getId());
		} else if (object.getId() == 13137 || object.getId() == 13133 || object.getId() == 13129) { //combat ring
			if (!house.isBuildMode()) {
				byte ringType = (byte) (this.ringType > 0 ? -1 : (object.getId() == 13137 ? 3 : object.getId() == 13133 ? 2 : 1));
				if (!canEnterRing(ringType))
					return false;
				this.ringType = ringType;
			}
			if (object.getRotation() == 0)
				player.useStairs(4853, new WorldTile(player.getX() == object.getX() ? object.getX() - 1 : object.getX(), object.getY(), object.getPlane()), 0, 2);
			else if (object.getRotation() == 2)
				player.useStairs(4853, new WorldTile(player.getX() == object.getX() ? object.getX() + 1 : object.getX(), object.getY(), object.getPlane()), 0, 2);
			else if (object.getRotation() == 1)
				player.useStairs(4853, new WorldTile(object.getX(), player.getY() == object.getY() ? object.getY() + 1 : object.getY(), object.getPlane()), 0, 2);
			else
				player.useStairs(4853, new WorldTile(object.getX(), player.getY() == object.getY() ? object.getY() - 1 : object.getY(), object.getPlane()), 0, 2);
			if (!house.isBuildMode() && !house.isPVPMode())
				player.setCanPvp(!player.isCanPvp());
		} else if (Builds.PORTAL_FOCUS.containsObject(object)) {
			if (!house.isOwner(player)) {
				player.getPackets().sendGameMessage("You can only do that in your own house.");
				return false;
			} else if (!house.isBuildMode()) {
				player.getPackets().sendGameMessage("Your house must be in build mode in order to change your portal's focus.");
				return false;
			}
			player.getDialogueManager().startDialogue("PortalChamberD", house.getRoom(player));
			return false;
		} else if (object.getId() >= 13615 && object.getId() <= 13635) {
			ModernMagicks.sendNormalTeleportSpell(player, -1, HouseConstants.PORTAL_COORDINATES[object.getId() - (13615 + 7 * (((object.getId() - 13615) / 7)))]);
			//Magic.sendTeleportSpell(player, -1, -1, -1, -1, 0, 0, HouseConstants.PORTAL_COORDINATES[object.getId() - (13615 + 7 * (((object.getId() - 13615) / 7)))], 0, true, Magic.OBJECT_TELEPORT, false, null);
			return false;
		}
		return false;
	}

	private void passDoor(WorldObject object) {
		int x = 0;
		int y = 0;
		if (object.getRotation() == 2)
			x = player.getX() == object.getX() ? 1 : -1;
		else if (object.getRotation() == 0)
			x = player.getX() == object.getX() ? -1 : 1;
		else if (object.getRotation() == 3)
			y = player.getY() == object.getY() ? -1 : 1;
		else if (object.getRotation() == 1)
			y = player.getY() == object.getY() ? 1 : -1;
		player.addWalkSteps(player.getX() + x, player.getY() + y, 1, false);
		player.lock(1);
	}

	private final static int[] RESTRICTED_SLOTS =
	{ 0, 1, 2, 4, 5, 7, 9, 10, 12, 13, 14 };

	private boolean canEnterRing(byte ringType) {
		if (ringType == 1 || ringType == 2) {
			for (int slot : RESTRICTED_SLOTS) {
				Item item = player.getEquipment().getItem(slot);
				if (item != null) {
					player.getPackets().sendGameMessage("You need to remove your " + item.getName() + " before entering the ring.");
					return false;
				}
			}
			if (ringType == 1) {
				int weapon = player.getEquipment().getWeaponId();
				if (weapon == 7671 || weapon == 7673)
					return true;
				player.getPackets().sendGameMessage("You need to have boxing gloves equipped in order to box.");
				return false;
			}
		}
		return true;
	}

	/**
	 * RingTypes : 1= boxing, 2= fencing, 3=combat
	 * 
	 * @return
	 */
	private boolean canAttack() {
		if (ringType == 1 || ringType == 2) {
			if (player.getEquipment().getWeaponId() == 22496 || player.getCombatDefinitions().getSpellId() > 0 || PlayerCombat.isRanging(player) > 0) {
				player.getPackets().sendGameMessage("You may only use melee in this ring!");
				return false;
			}
			int weaponId = player.getEquipment().getWeaponId();
			if (ringType == 1) {
				if (weaponId == 7671 || weaponId == 7673)
					return true;
				return false;
			}
		}
		return !house.isPVPMode() || player.getPlane() == 0;
	}

	@Override
	public boolean canEquip(int slot, int item) {
		if (ringType == 1) {
			player.getPackets().sendGameMessage("You may only use boxing gloves in a boxing ring.");
			return false;
		} else if (ringType == 2) {
			if (slot != 3) {
				player.getPackets().sendGameMessage("You may only equip weapons in a fencing ring.");
				return false;
			}
		}
		return true;
	}

	/*@Override
	public boolean canRemoveEquip(int slot, int itemId) {
		if (ringType == 1) {
			if (slot == Equipment.SLOT_WEAPON) {
				player.getPackets().sendGameMessage("You can't remove your boxing gloves.");
				return false;
			}
		}
		return true;
	}
	*/

	@Override
	public boolean processButtonClick(int interfaceId, int componentId, int slotId, int packetId) {
		if (interfaceId == 400) {
			if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
				TabletMaking.handleTabletCreation(player, componentId, 1);
			else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
				TabletMaking.handleTabletCreation(player, componentId, 5);
			else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
				TabletMaking.handleTabletCreation(player, componentId, 10);
			else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
				player.getTemporaryAttributes().put("create_tab_X_component", componentId);
				player.getPackets().sendRunScript(108, new Object[]
				{ "Enter Amount:" });
			} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET)
				TabletMaking.handleTabletCreation(player, componentId, player.getInventory().getAmountOf(1761));
		}
		return true;
	}

	@Override
	public boolean keepCombating(Entity victim) {
		return canAttack();
	}

	@Override
	public boolean handleItemOnObject(WorldObject object, Item item) {
		if (object.getId() == HouseConstants.HObject.CLAY_FIREPLACE.getId() || object.getId() == HouseConstants.HObject.STONE_FIREPLACE.getId() || object.getId() == HouseConstants.HObject.MARBLE_FIREPLACE.getId()) {
			if (house.isBuildMode()) {
				player.getPackets().sendGameMessage("You cannot do this while in building mode.");
				return false;
			} else if (item.getId() != 1511) {
				player.getPackets().sendGameMessage("Only ordinary logs can be used to light the fireplace.");
				return false;
			} else if (!player.getInventory().containsItemToolBelt(590)) {
				player.getPackets().sendGameMessage("You do not have the required items to light this.");
				return false;
			}
			player.lock(2);
			player.animate(new Animation(3658));
			player.getInventory().deleteItem(new Item(1511));
			player.getSkills().addXp(Skills.FIREMAKING, 40);
			WorldObject objectR = new WorldObject(object);
			objectR.setId(object.getId() + 1);
			World.spawnObjectTemporary(objectR, Integer.MAX_VALUE); //lights for 1min
			return true;
		} else if (HouseConstants.HObject.FIREPIT.getId() >= object.getId() && HouseConstants.HObject.FANCY_RANGE.getId() <= object.getId()) {
			Cookables cook = Cooking.isCookingSkill(item);
			if (cook != null) {
				player.getDialogueManager().startDialogue("CookingD", cook, object);
				return true;
			}
			player.getDialogueManager().startDialogue("SimpleMessage", "You can't cook that on a " + (object.getDefinitions().name.equals("Fire") ? "fire" : "range") + ".");
			return true;
		} else if (Builds.DINING_TABLE.containsObject(object) || Builds.KITCHEN_TABLE.containsObject(object)) {
			if (house.isBuildMode()) {
				player.getPackets().sendGameMessage("You cannot place items on the table while in building mode.");
				return true;
			}
			player.faceObject(object);
			player.getPackets().sendGameMessage("You place the " + item.getName() + " on the table.");
			player.animate(new Animation(833));
			player.lock(1);
			player.getInventory().deleteItem(item);
			World.addGroundItem(item, object, player, true, 180);
			return true;
		} else if (Builds.BARRELS.containsObject(object) && item.getId() == 1919) {
			int beer = object.getId() - 13568;
			player.getInventory().deleteItem(item);
			player.getInventory().addItem(new Item(HouseConstants.BEERS[beer]));
			player.animate(new Animation(3661 + beer));
			player.getPackets().sendGameMessage("You fill the glass with " + object.getDefinitions().name + ".");
			return true;
		} else if (Builds.ALTAR.containsObject(object)) {
			Bone bone = Bone.forId(item.getId());
			if (bone != null ) {
				if (house.isBuildMode()) {
					player.getPackets().sendGameMessage("You cannot do this while in building mode.");
					return false;
				}
				if (bone != null)
					player.getActionManager().setAction(new BoneOffering(object, bone, house.getBurnerCount()));
			}
		}
		return true;
	}

	public boolean canDropItem(Item item) {
		if (house.isBuildMode()) {
			player.getPackets().sendGameMessage("You cannot drop items while in building mode.");
			return false;
		}
		return true;
	}

	@Override
	public boolean processObjectClick2(final WorldObject object) {
		if (object.getId() == HouseConstants.HObject.EXIT_PORTAL.getId())
			house.switchLock(player);
		else if (object.getId() == HouseConstants.HObject.DUNGEON_ENTRACE.getId() || Builds.STAIRCASE.containsObject(object) || Builds.STAIRCASE_DOWN.containsObject(object))
			house.climbStaircase(player, object, true, false);
		else if (Builds.DUNGEON_DOOR1.containsObject(object) || Builds.DUNGEON_DOOR2.containsObject(object)) {
			if (!house.isChallengeMode()) {
				player.getPackets().sendGameMessage("The doors are only locked during challenge mode.");
				return false;
			}
			player.animate(new Animation(3692));
			player.getPackets().sendGameMessage("You attempt to pick the lock...");
			final int slot = Builds.DUNGEON_DOOR1.getHObjectSlot(object.getId());
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					if (Utils.random(player.getSkills().getLevel(Skills.THIEVING)) < Utils.random(HouseConstants.DUNGEON_DOOR_LEVELS[slot])) {
						player.getPackets().sendGameMessage("and fail to pick the lock.");
						this.stop();
						return;
					}
					player.getPackets().sendGameMessage("and successfully pick the lock.");
					passDoor(object);
				}
			}, 1);
			return false;
		} else if (Builds.LEVER.containsObject(object)) {
			if (!house.isOwner(player)) {
				player.getPackets().sendGameMessage("You can only do that in your own house.");
				return false;
			} else if (house.isBuildMode()) {
				player.getPackets().sendGameMessage("You cannot do this while in building mode.");
				return false;
			}
			house.pullLeverChallengeMode(object);
		}
		return false;
	}

	@Override
	public boolean processObjectClick3(final WorldObject object) {
		if (Builds.STAIRCASE.containsObject(object) || Builds.STAIRCASE_DOWN.containsObject(object))
			house.climbStaircase(player, object, false, false);
		else if (Builds.DUNGEON_DOOR1.containsObject(object) || Builds.DUNGEON_DOOR2.containsObject(object)) {
			if (!house.isChallengeMode()) {
				player.getPackets().sendGameMessage("The doors are only locked during challenge mode.");
				return false;
			}
			player.animate(new Animation(3693));
			player.getPackets().sendGameMessage("You attempt to force the lock...");
			final int slot = Builds.DUNGEON_DOOR1.getHObjectSlot(object.getId());
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					if (Utils.random(player.getSkills().getLevel(Skills.STRENGTH)) < Utils.random(HouseConstants.DUNGEON_DOOR_LEVELS[slot])) {
						player.getPackets().sendGameMessage("You fail to force the lock.");
						this.stop();
						return;
					}
					passDoor(object);
				}
			}, 1);

			return false;
		}
		return false;
	}

	@Override
	public boolean processObjectClick4(WorldObject object) {
		if (Builds.LADDER.containsObject(object) || Builds.TRAPDOOR.containsObject(object) || object.getId() == HouseConstants.HObject.DUNGEON_ENTRACE.getId() || Builds.STAIRCASE.containsObject(object) || Builds.STAIRCASE_DOWN.containsObject(object)) {
			if (!house.isOwner(player)) {
				player.getPackets().sendGameMessage("You can only do that in your own house.");
				return false;
			}
			house.removeRoom();
		}
		return false;
	}

	@Override
	public boolean checkWalkStep(int lastX, int lastY, int nextX, int nextY) {
		return !house.isSky(nextX, nextY, player.getPlane());
	}

	@Override
	public boolean canMove(final int dir) {
		if (!house.isChallengeMode() || player.isLocked())
			return true;

		/*final WorldObject trap = house.getDungeonTrap(new WorldTile(player.getX() + Utils.DIRECTION_DELTA_X[dir], player.getY() + Utils.DIRECTION_DELTA_Y[dir], player.getPlane()));
		final boolean containsTrap = trap != null;

		if (containsTrap) {

			final int slot = HouseConstants.Builds.TRAP_SPACE_1.getHObjectSlot(trap.getId());
			final int agilityLevel = player.getSkills().getLevel(Skills.AGILITY);
			final boolean isFailure = Utils.getRandomDouble((agilityLevel / 100.0)) < Math.random();
			final boolean isTeleportTrap = slot == 4;

			if (agilityLevel >= 50) {
				if (Utils.random(3) == 0) {// 33% chance
					return true;// Completely skip the trap.
				}
			}

			final WorldTile previousTile = player.getLastWorldTile();
			int transformX = previousTile.getX() - trap.getX();
			int transformY = previousTile.getY() - trap.getY();
			if (transformX > 1)
				transformX = 1;
			if (transformY > 1)
				transformY = 1;
			final WorldTile successTile = trap.transform(-transformX, -transformY, 0);

			player.lock();
			player.faceObject(trap);

			WorldTasksManager.schedule(new WorldTask() {

				int count = 0;

				@Override
				public void run() {
					count++;
					if (count == 1)
						player.addWalkSteps(trap.getX(), trap.getY(), -1, false);
					else if (count == 2) {
						if (!isFailure) {
							if (isTeleportTrap)
								return;
						}
						player.setNextAnimation(new Animation(HouseConstants.TRAP_ANIMATIONS[slot]));
						player.getPackets().sendGraphics(new Graphics(HouseConstants.TRAP_GRAPHICS[slot]), trap);
					} else if (count == (isTeleportTrap ? 10 : 4)) {
						player.lock(slot == 2 ? 2 : 0);
						stop();
						if (isFailure) {
							if (isTeleportTrap)
								return;
							player.addWalkSteps(previousTile.getX(), previousTile.getY());
						} else
							player.setNextWorldTile(successTile);
					}

					if (isFailure) {
						if (count == (isTeleportTrap ? 8 : 3)) {
							if (slot == 0 || slot == 1) {
								player.applyHit(new Hit(player, 100, HitLook.REGULAR_DAMAGE));
								player.setNextForceTalk(new ForceTalk("*Ouch*!"));
							} else if (slot == 3) {
								int newLevel = agilityLevel - 4;
								player.getSkills().set(Skills.AGILITY, newLevel < 0 ? 0 : newLevel);
							} else if (slot == 4) {
								RoomReference obliette = house.getRoom(HouseConstants.Room.OUTBLIETTE);
								if (obliette == null)
									obliette = house.getRoom(HouseConstants.Room.GARDEN);
								if (obliette != null) {
									boolean isPool = obliette.containsHObject(HouseConstants.HObject.TENTACLE_POOL);
									if (isPool) {
										player.setNextGraphics(new Graphics(622, 6, 0));
										player.getAppearence().setRenderEmote(152);
									}
									player.useStairs(isPool ? 3641 : 3640, Utils.getFreeTile(house.getCenterTile(obliette), 1), 0, 2, null, true);
								}
							}
						}
					} else {
						if (count == 3) {
							player.setNextForceMovement(new NewForceMovement(player, 0, successTile, 1, Utils.getAngle(trap.getX() - successTile.getX(), trap.getY() - successTile.getY())));
							player.setNextAnimation(new Animation(3627));
						}
					}
				}
			}, 0, 0);
		}
		return !containsTrap;*/
		return true;
	}

	@Override
	public boolean logout() {
		house.leaveHouse(player, House.LOGGED_OUT);
		return false; //leave house method removes controller already
	}

	//shouldnt happen but lets imagine somehow in a server restart
	@Override
	public boolean login() {
		player.setNextWorldTile(Settings.START_PLAYER_LOCATION);
		removeControler();
		return false; //remove controller manualy since i dont want to call forceclose
	}

	@Override
	public boolean sendDeath() {
		player.lock(8);
		player.stopAll();
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					player.animate(new Animation(836));
				} else if (loop == 1) {
					player.getPackets().sendGameMessage("Oh dear, you have died.");
				} else if (loop == 3) {
					if (ringType > 0)
						ringType = 0;
					player.reset();
					if (player.isCanPvp() && !house.isPVPMode())
						player.setCanPvp(false);
					if (house.isLoaded())
						player.setNextWorldTile(house.getPortal());
					player.animate(new Animation(-1));
				} else if (loop == 4) {
					player.getPackets().sendMusicEffect(90);
					stop();
				}
				loop++;
			}
		}, 0, 1);
		return false;
	}

	@Override
	public void magicTeleported(int type) {
		house.leaveHouse(player, House.TELEPORTED);
	}

	//shouldnt happen
	@Override
	public void forceClose() {
		house.leaveHouse(player, House.TELEPORTED);
	}

	public House getHouse() {
		return house;
	}
}