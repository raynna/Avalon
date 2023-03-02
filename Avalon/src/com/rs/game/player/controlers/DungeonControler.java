package com.rs.game.player.controlers;

import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.dungeonnering.Blink;
import com.rs.game.npc.dungeonnering.DivineSkinweaver;
import com.rs.game.npc.dungeonnering.DungeonBoss;
import com.rs.game.npc.dungeonnering.DungeonFishSpot;
import com.rs.game.npc.dungeonnering.DungeonSlayerNPC;
import com.rs.game.npc.dungeonnering.FrozenAdventurer;
import com.rs.game.npc.dungeonnering.Gravecreeper;
import com.rs.game.npc.dungeonnering.MastyxTrap;
import com.rs.game.npc.dungeonnering.NightGazerKhighorahk;
import com.rs.game.npc.dungeonnering.RuneboundBehemoth;
import com.rs.game.npc.dungeonnering.Stomp;
import com.rs.game.npc.dungeonnering.YkLagorThunderous;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.player.Inventory;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.HomeTeleport;
import com.rs.game.player.actions.WaterFilling;
import com.rs.game.player.actions.combat.Magic;
import com.rs.game.player.actions.skills.mining.DungeoneeringMining;
import com.rs.game.player.actions.skills.mining.DungeoneeringMining.DungeoneeringRocks;
import com.rs.game.player.actions.skills.mining.Mining;
import com.rs.game.player.actions.skills.mining.MiningBase.PickAxeDefinitions;
import com.rs.game.player.actions.skills.prayer.BoneOffering;
import com.rs.game.player.actions.skills.prayer.Burying.Bone;
import com.rs.game.player.actions.skills.smithing.DungeoneeringSmelting.SmeltingBar;
import com.rs.game.player.actions.skills.smithing.DungeoneeringSmithing;
import com.rs.game.player.actions.skills.summoning.Summoning;
import com.rs.game.player.actions.skills.woodcutting.Woodcutting;
import com.rs.game.player.actions.skills.woodcutting.Woodcutting.HatchetDefinitions;
import com.rs.game.player.actions.skills.woodcutting.Woodcutting.TreeDefinitions;
import com.rs.game.player.content.Foods.Food;
import com.rs.game.player.content.dungeoneering.Door;
import com.rs.game.player.content.dungeoneering.DungeonConstants;
import com.rs.game.player.content.dungeoneering.DungeonConstants.KeyDoors;
import com.rs.game.player.content.dungeoneering.DungeonConstants.SkillDoors;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.DungeonResourceShop;
import com.rs.game.player.content.dungeoneering.DungeonUtils;
import com.rs.game.player.content.dungeoneering.Room;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.game.player.content.dungeoneering.VisibleRoom;
import com.rs.game.player.content.dungeoneering.rooms.PuzzleRoom;
import com.rs.game.player.content.dungeoneering.rooms.puzzles.PoltergeistRoom;
import com.rs.game.player.content.dungeoneering.skills.DungeoneeringFarming;
import com.rs.game.player.content.dungeoneering.skills.DungeoneeringFarming.Harvest;
import com.rs.game.player.content.dungeoneering.skills.DungeoneeringFishing;
import com.rs.game.player.content.dungeoneering.skills.DungeoneeringTraps;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.dungeoneering.DestroyCurrentStone;
import com.rs.game.player.dialogues.skilling.LeatherCraftingD;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.net.decoders.WorldPacketsDecoder;
import com.rs.utils.Utils;

public class DungeonControler extends Controler {

	private DungeonManager dungeon;
	private WorldTile gatestone;
	private int voteStage;
	private boolean killedBossWithLessThan10HP;
	private int damageReceived;
	private int meleeDamage, rangeDamage, mageDamage;
	private int healedDamage;
	private int nextBloodHit;
	private boolean showBar;

	@Override
	public void start() {
		dungeon = (DungeonManager) getArguments()[0];
		setArguments(null); // because arguments save on char and we dont want
		// to save dungmanager
		showDeaths();
		refreshDeaths();
	}

	public void showDeaths() {
		player.getInterfaceManager().sendOverlay(945, true);
	}

	public void showBar() {
		player.getPackets().sendHideIComponent(945, 2, !showBar);
	}

	private void hideBar() {
		showBar(false, null);
	}

	public void showBar(boolean show, String name) {
		if (showBar == show)
			return;
		showBar = show;
		showBar();
		if (show)
			player.getPackets().sendGlobalString(315, name);
	}

	public void sendBarPercentage(int percentage) {
		player.getPackets().sendCSVarInteger(1233, percentage * 2);
	}

	@Override
	public boolean login() {
		if (dungeon != null)
			dungeon.getParty().add(player);
		return true;
	}

	public void reset() {
		voteStage = 0;
		gatestone = null;
		killedBossWithLessThan10HP = false;
		damageReceived = 0;
		meleeDamage = 0;
		rangeDamage = 0;
		mageDamage = 0;
		healedDamage = 0;
		refreshDeaths();
		showDeaths();
		hideBar();
		player.getAppearence().setRenderEmote(-1);
	}

	@Override
	public boolean canMove(int dir) {

		VisibleRoom vr = dungeon.getVisibleRoom(dungeon.getCurrentRoomReference(player));
		WorldTile to = new WorldTile(player.getX() + Utils.DIRECTION_DELTA_X[dir],
				player.getY() + Utils.DIRECTION_DELTA_Y[dir], 0);
		if (vr != null && !vr.canMove(player, to)) {
			return false;
		}

		Room room = dungeon.getRoom(dungeon.getCurrentRoomReference(player));
		if (room != null) {
			if (room.getRoom() == DungeonUtils.getBossRoomWithChunk(DungeonConstants.FROZEN_FLOORS, 26, 624)) {
				if (!player.isLocked()
						&& World.getObjectWithType(new WorldTile(player.getX() + Utils.DIRECTION_DELTA_X[dir],
								player.getY() + Utils.DIRECTION_DELTA_Y[dir], 0), 22) == null) {
					player.getAppearence().setRenderEmote(1429);
					player.setRun(true);
					// TODO player.setCantWalk(true);
				}
				if (player.isLocked()) {
					WorldTile nextStep = new WorldTile(player.getX() + Utils.DIRECTION_DELTA_X[dir] * 2,
							player.getY() + Utils.DIRECTION_DELTA_Y[dir] * 2, 0);
					NPC boss = getNPC(player, 9929);
					boolean collides = boss != null && Utils.colides(nextStep.getX(), nextStep.getY(), player.getSize(),
							boss.getX(), boss.getY(), boss.getSize());
					player.resetWalkSteps();
					WorldObject object = World.getObjectWithType(new WorldTile(nextStep.getX(), nextStep.getY(), 0),
							22);
					if (collides || ((object != null && (object.getId() == 49331 || object.getId() == 49333))
							|| !player.addWalkSteps(nextStep.getX(), nextStep.getY(), 1))) {
						// TODO player.setCantWalk(false);
						player.getAppearence().setRenderEmote(-1);
					}
				}
			}
		}
		return dungeon != null && !dungeon.isAtRewardsScreen();
	}

	public int getHealedDamage() {
		return healedDamage;
	}

	/*
	 * the dmg you receiving
	 */
	@Override
	public void processIngoingHit(Hit hit) {
		damageReceived += hit.getDamage();
	}

	/*
	 * the dmg you doing
	 */
	@Override
	public void processIncommingHit(Hit hit, Entity target) {
		if (hit.getLook() == HitLook.MELEE_DAMAGE)
			meleeDamage += hit.getDamage();
		else if (hit.getLook() == HitLook.RANGE_DAMAGE)
			rangeDamage += hit.getDamage();
		if (hit.getLook() == HitLook.MAGIC_DAMAGE)
			mageDamage += hit.getDamage();
	}

	public int getDamageReceived() {
		return damageReceived;
	}

	public int getMeleeDamage() {
		return meleeDamage;
	}

	public int getRangeDamage() {
		return rangeDamage;
	}

	public int getMageDamage() {
		return mageDamage;
	}

	public int getDamage() {
		return meleeDamage + rangeDamage + mageDamage;
	}

	@Override
	public void sendInterfaces() {
		if (dungeon != null && dungeon.isAtRewardsScreen())
			return;
		showDeaths();
	}

	@Override
	public void processNPCDeath(NPC npc) {
		if (npc instanceof DungeonBoss) {
			if (player.getHitpoints() <= 10)
				killedBossWithLessThan10HP = true;
		}
	}

	@Override
	public boolean sendDeath() {
		// TODO player.setCantWalk(true);
		player.stopAll();
		player.getPackets().sendMusicEffect(418);
		if (player.getInventory().containsItem(DungeonConstants.GROUP_GATESTONE, 1)) {
			WorldTile tile = new WorldTile(player);
			dungeon.setGroupGatestone(tile);
			World.addGroundItem(new Item(DungeonConstants.GROUP_GATESTONE), tile, player, false, 180, 2, -1);
			player.getInventory().deleteItem(DungeonConstants.GROUP_GATESTONE, 1);
			player.getPackets().sendGameMessage("Your group gatestone drops to the floor as you die.");
		}
		if (player.getInventory().containsItem(DungeonConstants.GATESTONE, 1)) {
			WorldTile tile = new WorldTile(player);
			setGatestone(tile);
			removeCurrentGatestone();
			World.addGroundItem(new Item(DungeonConstants.GATESTONE), tile, player, false, 180, 2, -1);
			player.getInventory().deleteItem(DungeonConstants.GATESTONE, 1);
			player.getPackets().sendGameMessage("Your gatestone drops to the floor as you die.");
		}
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					player.animate(new Animation(836));
				} else if (loop == 1) {
					player.getPackets().sendGameMessage("Oh dear, you are dead!");
					if (dungeon != null) {
						for (Player p2 : dungeon.getParty().getTeam()) {
							if (p2 == player)
								continue;
							p2.getPackets().sendGameMessage(player.getDisplayName() + " has died.");
						}
					}
				} else if (loop == 3) {
					// TODO int prayerRenewal = player.getPrayerRenewalDelay();
					player.reset();
					// TODO player.setPrayerRenewalDelay(prayerRenewal);
					player.resetReceivedDamage();
					if (dungeon != null && dungeon.getParty().getTeam().contains(player)) {
						if (dungeon.isAtBossRoom(player, 26, 672, true)) {
							NPC npc = getNPC(player, 11872);
							if (npc != null) {
								npc.setNextForceTalk(new ForceTalk("Another kill for the Thunderous!"));
								npc.playSound(1928, 2);
							}
						}
						WorldTile startRoom = dungeon.getHomeTile();
						player.setNextWorldTile(startRoom);
						dungeon.playMusic(player, dungeon.getCurrentRoomReference(startRoom));
						increaseDeaths();
					}
					player.animate(new Animation(-1));
					player.getAppearence().setRenderEmote(-1);
					hideBar();
				} else if (loop == 4) {
					stop();
				}
				loop++;
			}
		}, 0, 1);
		return false;
	}

	@Override
	public void process() {
		int neck = player.getEquipment().getAmuletId();
		if (neck == 17291 || neck == 15834) {
			nextBloodHit++;
			if (nextBloodHit % 25 == 0) {
				List<Integer> npcsIndexes = World.getRegion(player.getRegionId()).getNPCsIndexes();
				if (npcsIndexes != null) {
					for (int npcIndex : npcsIndexes) {
						NPC npc = World.getNPCs().get(npcIndex);
						if (npc == null || !npc.isUnderCombat() || !Utils.isOnRange(npc.getX(), npc.getY(),
								npc.getSize(), player.getX(), player.getY(), player.getSize(), 0))
							continue;
						npc.applyHit(new Hit(player, 40, HitLook.REGULAR_DAMAGE));
						player.heal(40);
					}
				}
				nextBloodHit = 0;// Reset it, why not? XD
			}
		}
	}

	private void refreshDeaths() {
		player.getVarsManager().sendVarBit(7554, getDeaths()); // deaths
	}

	private void increaseDeaths() {
		Integer deaths = dungeon.getPartyDeaths().get(player.getUsername());
		if (deaths == null)
			deaths = 0;
		else if (deaths == 15)
			return;
		dungeon.getPartyDeaths().put(player.getUsername(), deaths + 1);
		deaths++;
		refreshDeaths();
	}

	public int getDeaths() {
		if (dungeon == null)
			return 0;
		Integer deaths = dungeon.getPartyDeaths().get(player.getUsername());
		return deaths == null ? 0 : deaths;// deaths;
	}

	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		if (dungeon == null || !player.getCombatDefinitions().isDungeonneringSpellBook() || !dungeon.hasStarted()
				|| dungeon.isAtRewardsScreen())
			return false;
		return true;
	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		return false;
	}

	@Override
	public boolean canTakeItem(FloorItem item) {
		for (KeyDoors key : DungeonConstants.KeyDoors.values()) {
			if (item.getId() == key.getKeyId()) {
				dungeon.setKey(key, true);
				World.removeGroundItem(player, item, false);
				return false;
			}
		}
		if (item.getId() == DungeonConstants.GROUP_GATESTONE) {
			dungeon.setGroupGatestone(null);
			World.removeGroundItem(player, item, true);
			return true;
		} else if (item.getId() == DungeonConstants.GATESTONE) {
			if (!item.hasOwner()) {
				World.removeGroundItem(player, item);
				return false;
			} else if (!item.getOwner().equals(player.getUsername())) {
				player.getPackets().sendGameMessage("This isn't your gatestone!");
				return false;
			}
			setGatestone(null);
			return true;
		} else if (!World.isFloorFree(item.getTile().getPlane(), item.getTile().getX(), item.getTile().getY())) {
			player.animate(new Animation(833));
			player.setNextFaceWorldTile(item.getTile());
		}
		return true;
	}

	private void openSkillDoor(final SkillDoors s, final WorldObject object, final Room room, final int floorType) {
		final int index = room.getDoorIndexByRotation(object.getRotation());
		if (index == -1)
			return;
		final Door door = room.getDoor(index);
		if (door == null || door.getType() != DungeonConstants.SKILL_DOOR)
			return;
		if (door.getLevel() > player.getSkills().getLevel(s.getSkillId())) {
			player.getPackets().sendGameMessage("You need a " + Skills.SKILL_NAME[s.getSkillId()] + " level of "
					+ door.getLevel() + " to remove this " + object.getDefinitions().name.toLowerCase() + ".");
			return;
		}
		int openAnim = -1;
		if (s.getSkillId() == Skills.FIREMAKING) {
			if (!player.getInventory().containsItemToolBelt(DungeonConstants.TINDERBOX)) {
				player.getPackets().sendGameMessage("You need a tinderbox to do this.");
				return;
			}
		} else if (s.getSkillId() == Skills.MINING) {
			PickAxeDefinitions defs = Mining.getPickAxeDefinitions(player);
			if (defs == null) {
				player.getPackets().sendGameMessage(
						"You do not have a pickaxe or do not have the required level to use the pickaxe.");
				return;
			}
			openAnim = defs.getAnimationId();
		} else if (s.getSkillId() == Skills.WOODCUTTING) {
			HatchetDefinitions defs = Woodcutting.getHatchet(player);
			if (defs == null) {
				player.getPackets().sendGameMessage(
						"You do not have a hatchet or do not have the required level to use the hatchet.");
				return;
			}
			openAnim = defs.getEmoteId();
		}
		final boolean fail = Utils.random(100) <= 10;
		// TODO player.setCantWalk(true);
		if (s.getOpenAnim() != -1)
			player.animate(new Animation(
					openAnim != -1 ? openAnim : fail && s.getFailAnim() != -1 ? s.getFailAnim() : s.getOpenAnim()));
		if (s.getOpenGfx() != -1 || s.getFailGfx() != -1)
			player.gfx(new Graphics(fail ? s.getFailGfx() : s.getOpenGfx()));
		if (s.getOpenObjectAnim() != -1 && !fail)
			World.sendObjectAnimation(object, new Animation(s.getOpenObjectAnim()));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				// TODO player.setCantWalk(false);
				if (s.getFailAnim() == -1)
					player.animate(new Animation(-1));
				if (!fail) {
					if (room.getDoor(index) == null) // means someone else opeenda t same time
						return;
					player.getSkills().addXp(s.getSkillId(), door.getLevel() * 5 + 10);
					room.setDoor(index, null);
					int openId = s.getOpenObject(floorType);
					if (openId == -1)
						World.removeObject(object);
					else
						dungeon.setDoor(dungeon.getCurrentRoomReference(object), -1, openId, object.getRotation());
				} else {
					player.getPackets().sendGameMessage(s.getFailMessage());
					player.applyHit(new Hit(player, door.getLevel() * 4, HitLook.REGULAR_DAMAGE));
					if (room.getDoor(index) == null) // means someone else opeenda t same time
						return;
					if (s.getFailObjectAnim() != -1)
						World.sendObjectAnimation(object, new Animation(s.getFailObjectAnim()));
				}

			}

		}, 2);

	}

	@Override
	public boolean processNPCClick1(final NPC npc) {
		if (dungeon == null || !dungeon.hasStarted() || dungeon.isAtRewardsScreen())
			return false;
		VisibleRoom vRoom = dungeon.getVisibleRoom(dungeon.getCurrentRoomReference(player));
		if (vRoom == null || !vRoom.processNPCClick1(player, npc)) {
			return false;
		}
		if (npc.getId() == DungeonConstants.FISH_SPOT_NPC_ID) {
			player.message("start fish");
			player.getActionManager().setAction(new DungeoneeringFishing((DungeonFishSpot) npc));
			return false;
		} else if (npc.getId() == 10023) {
			FrozenAdventurer adventurer = (FrozenAdventurer) npc;
			adventurer.getFrozenPlayer().getAppearence().transformIntoNPC(-1);
			return false;
		} else if (npc.getId() == DungeonConstants.SMUGGLER) {
			npc.faceEntity(player);
			player.getDialogueManager().startDialogue("SmugglerD", dungeon.getParty().getComplexity());
			return false;
		} else if (npc.getId() >= 11076 && npc.getId() <= 11085) {
			DungeoneeringTraps.removeTrap(player, (MastyxTrap) npc, dungeon);
			return false;
		} else if (npc.getId() >= 11096 && npc.getId() <= 11105) {
			DungeoneeringTraps.skinMastyx(player, npc);
			return false;
		} else if (npc instanceof DivineSkinweaver) {
			((DivineSkinweaver) npc).talkTo(player);
			return false;
		}
		return true;
	}

	@Override
	public boolean processNPCClick2(final NPC npc) {
		if (dungeon == null || !dungeon.hasStarted() || dungeon.isAtRewardsScreen())
			return false;
		VisibleRoom room = dungeon.getVisibleRoom(dungeon.getCurrentRoomReference(player));
		if (room == null)
			return false;
		if (!room.processNPCClick2(player, npc)) {
			return false;
		}
		if (npc instanceof Familiar) {
			Familiar familiar = (Familiar) npc;
			if (player.getFamiliar() != familiar) {
				player.getPackets().sendGameMessage("That isn't your familiar.");
				return false;
			} else if (familiar.getDefinitions().hasOption("Take")) {
				familiar.takeBob();
				return false;
			}
			return true;
		} else if (npc.getDefinitions().hasOption("Mark")) {
			if (!dungeon.getParty().isLeader(player)) {
				player.getPackets().sendGameMessage("Only your party's leader can mark a target!");
				return false;
			}
			dungeon.setMark(npc, !player.getHintIconsManager().hasHintIcon(6)); // 6th slot
			return false;
		} else if (npc.getId() == DungeonConstants.SMUGGLER) {
			DungeonResourceShop.openResourceShop(player, dungeon.getParty().getComplexity());
			return false;
		}
		return true;
	}

	@Override
	public boolean processNPCClick3(NPC npc) {
		if (dungeon == null || !dungeon.hasStarted() || dungeon.isAtRewardsScreen())
			return false;
		VisibleRoom room = dungeon.getVisibleRoom(dungeon.getCurrentRoomReference(player));
		if (room == null)
			return false;
		if (!room.processNPCClick3(player, npc)) {
			return false;
		}
		return true;
	}

	public static NPC getNPC(Entity entity, int id) {
		List<Integer> npcsIndexes = World.getRegion(entity.getRegionId()).getNPCsIndexes();
		if (npcsIndexes != null) {
			for (int npcIndex : npcsIndexes) {
				NPC npc = World.getNPCs().get(npcIndex);
				if (npc.getId() == id) {
					return npc;
				}
			}
		}
		return null;
	}

	public static NPC getNPC(Entity entity, String name) {
		List<Integer> npcsIndexes = World.getRegion(entity.getRegionId()).getNPCsIndexes();
		if (npcsIndexes != null) {
			for (int npcIndex : npcsIndexes) {
				NPC npc = World.getNPCs().get(npcIndex);
				if (npc.getName().equals(name)) {
					return npc;
				}
			}
		}
		return null;
	}

	@Override
	public boolean processObjectClick1(final WorldObject object) {
		if (dungeon == null || !dungeon.hasStarted() || dungeon.isAtRewardsScreen())
			return false;
		Room room = dungeon.getRoom(dungeon.getCurrentRoomReference(player));
		VisibleRoom vr = dungeon.getVisibleRoom(dungeon.getCurrentRoomReference(player));
		if (vr == null || !vr.processObjectClick1(player, object))
			return false;
		int floorType = DungeonUtils.getFloorType(dungeon.getParty().getFloor());
		for (SkillDoors s : SkillDoors.values()) {
			if (s.getClosedObject(floorType) == object.getId()) {
				openSkillDoor(s, object, room, floorType);
				return false;
			}
		}
		if (object.getId() >= 54439 && object.getId() <= 54456
				&& object.getDefinitions().containsOption(0, "Cleanse")) {
			@SuppressWarnings("deprecation")
			NPC boss = dungeon.getTemporaryBoss();// getNPC(player, 11708);
			if (boss == null || !(boss instanceof Gravecreeper))
				return false;
			return ((Gravecreeper) boss).cleanseTomb(player, object);
		}
		if (object.getId() == 49265) {
			NPC boss = getNPC(player, 9725);
			if (boss == null) {
				player.getPackets().sendGameMessage("You don't need to light anymore.");
				return false;
			}
			((NightGazerKhighorahk) boss).lightPillar(player, object);
			return false;
		} else if (object.getId() >= 49274 && object.getId() <= 49279) {
			NPC boss = getNPC(player, 9782);
			if (boss != null)
				((Stomp) boss).chargeLodeStone(player, (object.getId() & 0x1));
			return false;
		} else if (object.getId() == 49268) {
			PickAxeDefinitions defs = Mining.getPickAxeDefinitions(player);
			if (defs == null) {
				player.getPackets().sendGameMessage(
						"You do not have a pickaxe or do not have the required level to use the pickaxe.");
				return false;
			}
			player.animate(new Animation(defs.getAnimationId()));
			// TODO player.setCantWalk(true);
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					// TODO player.setCantWalk(false);
					player.animate(new Animation(-1));
					World.removeObject(object);
				}
			}, 3);
			return false;
		} else if (object.getId() >= 49286 && object.getId() <= 49288) {
			NPC boss = getNPC(player, 10058);
			if (boss != null)
				((DivineSkinweaver) boss).blockHole(player, object);
			return false;
		} else if (object.getId() == 49297) {

			Integer value = (Integer) player.getTemporaryAttributtes().get("UNHOLY_CURSEBEARER_ROT");
			if (value != null && value >= 6) {
				NPC boss = getNPC(player, 10111);
				if (boss != null) {
					player.getPackets().sendGameMessage(
							"You restore your combat stats, and the skeletal archmage is healed in the process. The font lessens the effect of the rot within your body.");
					player.getTemporaryAttributtes().put("UNHOLY_CURSEBEARER_ROT", 1);
					player.getSkills().restoreSkills();
					boss.heal(boss.getMaxHitpoints() / 10);
				}
			} else
				player.getPackets().sendGameMessage("You can't restore your stats yet.");
			return false;
		} else if (object.getId() >= KeyDoors.getLowestObjectId() && object.getId() <= KeyDoors.getMaxObjectId()) {
			int index = room.getDoorIndexByRotation(object.getRotation());
			if (index == -1)
				return false;
			Door door = room.getDoor(index);
			if (door == null || door.getType() != DungeonConstants.KEY_DOOR)
				return false;
			KeyDoors key = KeyDoors.values()[door.getId()];
			if (/* !Settings.DEBUG && */ !dungeon.hasKey(key)
					&& !player.getInventory().containsItem(key.getKeyId(), 1)) {
				player.getPackets().sendGameMessage("You don't have the correct key.");
				return false;
			}
			player.getInventory().deleteItem(key.getKeyId(), 1);
			player.getPackets().sendGameMessage("You unlock the door.");
			player.animate(new Animation(13798));// unlock key
			dungeon.setKey(key, false);
			room.setDoor(index, null);
			World.removeObject(object);
			return false;
		} else if (object.getId() == DungeonConstants.DUNGEON_DOORS[floorType]
				|| object.getId() == DungeonConstants.DUNGEON_GUARDIAN_DOORS[floorType]
				|| object.getId() == DungeonConstants.DUNGEON_BOSS_DOORS[floorType]
				|| DungeonUtils.isOpenSkillDoor(object.getId(), floorType)
				|| (object.getId() >= KeyDoors.getLowestDoorId(floorType)
						&& object.getId() <= KeyDoors.getMaxDoorId(floorType))
				|| (object.getDefinitions().name.equals("Door") && object.getDefinitions().containsOption(0, "Enter")) // theres
																														// many
																														// ids
																														// for
																														// challenge
																														// doors
		) {
			if (object.getId() == DungeonConstants.DUNGEON_BOSS_DOORS[floorType] && player.isInCombat(1800)) {
				player.getPackets().sendGameMessage("This door is too complex to unlock while in combat.");
				return false;
			}
			// Rotation.
			Door door = room.getDoorByRotation(object.getRotation());
			if (door == null) {
				openDoor(object);
				return false;
			}
			if (door.getType() == DungeonConstants.GUARDIAN_DOOR)
				player.getPackets().sendGameMessage(
						"The door won't unlock until all of the guardians in the room have been slain.");
			else if (door.getType() == DungeonConstants.KEY_DOOR || door.getType() == DungeonConstants.SKILL_DOOR)
				player.getPackets().sendGameMessage("The door is locked.");
			else if (door.getType() == DungeonConstants.CHALLENGE_DOOR)
				player.getPackets().sendGameMessage(((PuzzleRoom) vr).getLockMessage());
			return false;
		} else if (object.getId() == DungeonConstants.THIEF_CHEST_LOCKED[floorType]) {
			room = dungeon.getRoom(dungeon.getCurrentRoomReference(player));
			int type = room.getThiefChest();
			if (type == -1)
				return false;
			int level = type == 0 ? 1 : (type * 10);
			if (level > player.getSkills().getLevel(Skills.THIEVING)) {
				player.getPackets().sendGameMessage("You need a " + Skills.SKILL_NAME[Skills.THIEVING] + " level of "
						+ level + " to open this chest.");
				return false;
			}
			room.setThiefChest(-1);
			player.animate(new Animation(536));
			// TODO player.setCantWalk(true);
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
//TODO player.setCantWalk(false);
				}
			}, 2);
			WorldObject openedChest = new WorldObject(object);
			openedChest.setId(DungeonConstants.THIEF_CHEST_OPEN[floorType]);
			World.spawnObject(openedChest);
			player.getInventory().addItemDrop(DungeonConstants.RUSTY_COINS, Utils.random((type + 1) * 10000) + 1);
			if (Utils.random(2) == 0)
				player.getInventory().addItemDrop(DungeonConstants.CHARMS[Utils.random(DungeonConstants.CHARMS.length)],
						Utils.random(5) + 1);
			if (Utils.random(3) == 0)
				player.getInventory().addItemDrop(DungeoneeringFarming.getHerbForLevel(level), Utils.random(1) + 1);
			if (Utils.random(4) == 0)
				player.getInventory().addItemDrop(DungeonUtils.getArrows(type + 1), Utils.random(100) + 1);
			if (Utils.random(5) == 0)
				player.getInventory().addItemDrop(DungeonUtils.getRandomWeapon(type + 1), 1);
			player.getSkills().addXp(Skills.THIEVING, DungeonConstants.THIEF_CHEST_XP[type]);
			return false;
		} else if (object.getId() == DungeonConstants.THIEF_CHEST_OPEN[floorType]) {
			player.getPackets().sendGameMessage("You already looted this chest.");
			return false;
		} else if (DungeonUtils.isLadder(object.getId(), floorType)) {
			if (voteStage != 0) {
				player.getPackets().sendGameMessage("You have already voted to move on.");
				return false;
			}
			player.getDialogueManager().startDialogue("DungeonClimbLadder", this);
			return false;
		} else if (object.getId() == 53977 || object.getId() == 53978 || object.getId() == 53979) {
			int type = object.getId() == 53977 ? 0 : object.getId() == 53979 ? 1 : 2;
			NPC boss = getNPC(player, "Runebound behemoth");
			if (boss != null)
				((RuneboundBehemoth) boss).activateArtifact(player, object, type);
			return false;
		}
		String name = object.getDefinitions().name.toLowerCase();
		switch (name) {
		case "dungeon exit":
			player.getDialogueManager().startDialogue("DungeonExit", this);
			return false;
		case "water trough":
			WaterFilling.isFilling(player, 17490, false);
			return false;
		case "salve nettles":
			DungeoneeringFarming.initHarvest(player, Harvest.SALVE_NETTLES, object);
			return false;
		case "wildercress":
			DungeoneeringFarming.initHarvest(player, Harvest.WILDERCRESS, object);
			return false;
		case "blightleaf":
			DungeoneeringFarming.initHarvest(player, Harvest.BLIGHTLEAF, object);
			return false;
		case "roseblood":
			DungeoneeringFarming.initHarvest(player, Harvest.ROSEBLOOD, object);
			return false;
		case "bryll":
			DungeoneeringFarming.initHarvest(player, Harvest.BRYLL, object);
			return false;
		case "duskweed":
			DungeoneeringFarming.initHarvest(player, Harvest.DUSKWEED, object);
			return false;
		case "soulbell":
			DungeoneeringFarming.initHarvest(player, Harvest.SOULBELL, object);
			return false;
		case "ectograss":
			DungeoneeringFarming.initHarvest(player, Harvest.ECTOGRASS, object);
			return false;
		case "runeleaf":
			DungeoneeringFarming.initHarvest(player, Harvest.RUNELEAF, object);
			return false;
		case "spiritbloom":
			DungeoneeringFarming.initHarvest(player, Harvest.SPIRITBLOOM, object);
			return false;
		case "tangle gum tree":
			player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.TANGLE_GUM_VINE));
			return false;
		case "seeping elm tree":
			player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.SEEPING_ELM_TREE));
			return false;
		case "blood spindle tree":
			player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.BLOOD_SPINDLE_TREE));
			return false;
		case "utuku tree":
			player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.UTUKU_TREE));
			return false;
		case "spinebeam tree":
			player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.SPINEBEAM_TREE));
			return false;
		case "bovistrangler tree":
			player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.BOVISTRANGLER_TREE));
			return false;
		case "thigat tree":
			player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.THIGAT_TREE));
			return false;
		case "corpsethorn tree":
			player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.CORPESTHORN_TREE));
			return false;
		case "entgallow tree":
			player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.ENTGALLOW_TREE));
			return false;
		case "grave creeper tree":
			player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.GRAVE_CREEPER_TREE));
			return false;
		case "novite ore":
			player.getActionManager().setAction(new DungeoneeringMining(object, DungeoneeringRocks.NOVITE_ORE));
			return false;
		case "bathus ore":
			player.getActionManager().setAction(new DungeoneeringMining(object, DungeoneeringRocks.BATHUS_ORE));
			return false;
		case "marmaros ore":
			player.getActionManager().setAction(new DungeoneeringMining(object, DungeoneeringRocks.MARMAROS_ORE));
			return false;
		case "kratonite ore":
			player.getActionManager().setAction(new DungeoneeringMining(object, DungeoneeringRocks.KRATONIUM_ORE));
			return false;
		case "fractite ore":
			player.getActionManager().setAction(new DungeoneeringMining(object, DungeoneeringRocks.FRACTITE_ORE));
			return false;
		case "zephyrium ore":
			player.getActionManager().setAction(new DungeoneeringMining(object, DungeoneeringRocks.ZEPHYRIUM_ORE));
			return false;
		case "argonite ore":
			player.getActionManager().setAction(new DungeoneeringMining(object, DungeoneeringRocks.AGRONITE_ORE));
			return false;
		case "katagon ore":
			player.getActionManager().setAction(new DungeoneeringMining(object, DungeoneeringRocks.KATAGON_ORE));
			return false;
		case "gorgonite ore":
			player.getActionManager().setAction(new DungeoneeringMining(object, DungeoneeringRocks.GORGONITE_ORE));
			return false;
		case "promethium ore":
			player.getActionManager().setAction(new DungeoneeringMining(object, DungeoneeringRocks.PROMETHIUM_ORE));
			return false;
		case "furnace":
			player.getDialogueManager().startDialogue("SingleSmithingD", object, DungeonConstants.SMELTING_BARS);
			return false;
		case "runecrafting altar":
			player.getDialogueManager().startDialogue("DungRunecraftingD", 0);
			return false;
		case "spinning wheel":
			player.getDialogueManager().startDialogue("SpinningD", true);
			return false;
		case "summoning obelisk":
			Summoning.openInfusionInterface(player);
			return false;
		case "group gatestone portal":
			portalGroupStoneTeleport();
			return false;
		case "sunken pillar":
			Blink boss = (Blink) getNPC(player, 12865);
			if (boss == null) {
				player.getPackets().sendGameMessage("The mechanism doesn't respond.");
				return false;
			} else if (boss.hasActivePillar()) {
				player.getPackets().sendGameMessage("The mechanism will not respond while the other pillar is raised.");
				return false;
			}
			for (Entity t : boss.getPossibleTargets()) {
				if (t.matches(object) || boss.matches(object)) {
					player.getPackets()
							.sendGameMessage("The mechanism cannot be activated while someone is standing there.");
					return false;
				}
			}
			boss.raisePillar(object);
			return false;
		default:
			return true;
		}
	}

	@Override
	public boolean processObjectClick2(final WorldObject object) {
		if (dungeon == null || !dungeon.hasStarted() || dungeon.isAtRewardsScreen())
			return false;

		VisibleRoom vr = dungeon.getVisibleRoom(dungeon.getCurrentRoomReference(player));
		if (vr == null || !vr.processObjectClick2(player, object))
			return false;
		if (object.getId() >= DungeonConstants.FARMING_PATCH_BEGINING
				&& object.getId() <= DungeonConstants.FARMING_PATCH_END) {
			if (object.getDefinitions().containsOption("Inspect"))
				return false;
			Harvest harvest = Harvest.values()[((object.getId() - 50042) / 3)];
			if (harvest == null)
				return false;
			DungeoneeringFarming.initHarvest(player, harvest, object);
			return false;
		}
		String name = object.getDefinitions().name.toLowerCase();
		switch (name) {
		case "runecrafting altar":
			player.getDialogueManager().startDialogue("DungRunecraftingD", 1);
			return false;
		}
		return true;
	}

	@Override
	public boolean processObjectClick3(WorldObject object) {
		if (dungeon == null || !dungeon.hasStarted() || dungeon.isAtRewardsScreen())
			return false;
		VisibleRoom vr = dungeon.getVisibleRoom(dungeon.getCurrentRoomReference(player));
		if (vr == null || !vr.processObjectClick4(player, object))
			return false;
		if (object.getId() >= DungeonConstants.FARMING_PATCH_BEGINING
				&& object.getId() <= DungeonConstants.FARMING_PATCH_END) {
			DungeoneeringFarming.clearHarvest(player, object);
			return false;
		}
		String name = object.getDefinitions().name.toLowerCase();
		switch (name) {
		case "runecrafting altar":
			player.getDialogueManager().startDialogue("DungRunecraftingD", 2);
			return false;
		}
		return true;
	}

	@Override
	public boolean processObjectClick4(final WorldObject object) {
		if (dungeon == null || !dungeon.hasStarted() || dungeon.isAtRewardsScreen())
			return false;
		if (!dungeon.getVisibleRoom(dungeon.getCurrentRoomReference(player)).processObjectClick4(player, object)) {
			return false;
		}
		String name = object.getDefinitions().name.toLowerCase();
		switch (name) {
		case "runecrafting altar":
			player.getDialogueManager().startDialogue("DungRunecraftingD", 3);
			return false;
		}
		return true;
	}

	@Override
	public boolean processObjectClick5(final WorldObject object) {
		if (dungeon == null || !dungeon.hasStarted() || dungeon.isAtRewardsScreen())
			return false;
		VisibleRoom vr = dungeon.getVisibleRoom(dungeon.getCurrentRoomReference(player));
		if (vr == null || !vr.processObjectClick5(player, object))
			return false;
		String name = object.getDefinitions().name.toLowerCase();
		switch (name) {
		case "runecrafting altar":
			player.getDialogueManager().startDialogue("DungRunecraftingD", 4);
			return false;
		}
		return true;
	}

	public void leaveDungeon() {
		if (dungeon == null || !dungeon.hasStarted())
			return;
		dungeon.getParty().leaveParty(player, false);
	}

	public void voteToMoveOn() {
		if (dungeon == null || !dungeon.hasStarted() || voteStage != 0)
			return;
		voteStage = 1;
		dungeon.voteToMoveOn(player);
	}

//Player player, WorldObject object, int interfaceId, Item item) {
	@Override
	public boolean handleItemOnObject(WorldObject object, Item item) {
		if (dungeon == null || !dungeon.hasStarted() || dungeon.isAtRewardsScreen())
			return false;
		VisibleRoom room = dungeon.getVisibleRoom(dungeon.getCurrentRoomReference(player));
		if (room == null)
			return false;
		if (!room.handleItemOnObject(player, object, item)) {
			return false;
		}
		String name = object.getDefinitions().name.toLowerCase();
		switch (name) {
		case "altar":
			Bone bone = Bone.forId(item.getId());
			if (bone != null) {
				player.getActionManager().setAction(new BoneOffering(object, bone, 0)); // 250% xp, if you want 350% use
																						// your own house
				return true;
			}
			return true;
		case "farming patch":
			DungeoneeringFarming.plantHarvest(item, player, object, dungeon);
			return true;
		case "furnace":
			for (SmeltingBar bar : SmeltingBar.values()) {
				if (bar.getItemsRequired()[0].getId() == item.getId()) {
					player.getDialogueManager().startDialogue("SingleSmithingD", object, new SmeltingBar[] { bar });
					break;
				}
			}
			return true;
		case "anvil":
			player.message("anvil");
			for (int index = 0; index < DungeoneeringSmithing.BARS[1].length; index++) {
				player.message("index: " + index);
				if (DungeoneeringSmithing.BARS[1][index] == item.getId()) {
					player.message("open forge");
					DungeoneeringSmithing.sendForgingInterface(player, index, true);
					break;
				}
			}
			return true;

		}
		return true;
	}

	@Override
	public boolean canUseItemOnItem(Item itemUsed, Item usedWith) {
		if (dungeon == null || !dungeon.hasStarted() || dungeon.isAtRewardsScreen())
			return false;
		if (itemUsed.getId() == LeatherCraftingD.DUNG_NEEDLE || usedWith.getId() == LeatherCraftingD.DUNG_NEEDLE) {
			int leatherIndex = LeatherCraftingD.getIndex(itemUsed.getId());
			if (leatherIndex == -1)
				leatherIndex = LeatherCraftingD.getIndex(usedWith.getId());
			if (leatherIndex != -1) {
				player.getDialogueManager().startDialogue("LeatherCraftingD", leatherIndex, true);
				return true;
			}
			// } else if (WeaponPoison.poison(player, itemUsed, usedWith, true))
			// return false;
		}
		return true;
	}

	public void openDoor(WorldObject object) {
		RoomReference roomReference = dungeon.getCurrentRoomReference(player);
		if (dungeon.enterRoom(player, roomReference.getX() + Utils.DOOR_ROTATION_DIR_X[object.getRotation()],
				roomReference.getY() + Utils.DOOR_ROTATION_DIR_Y[object.getRotation()]))
			;
		hideBar();
	}

	/**
	 * called once teleport is performed
	 */
	public void magicTeleported(int type) {
		dungeon.playMusic(player, dungeon.getCurrentRoomReference(player.getNextWorldTile()));
		hideBar();
	}

	@Override
	public boolean keepCombating(Entity target) {
		if (target instanceof DungeonSlayerNPC) {
			DungeonSlayerNPC npc = (DungeonSlayerNPC) target;

			for (int index = 0; index < DungeonConstants.SLAYER_CREATURES.length; index++) {
				if (npc.getId() == DungeonConstants.SLAYER_CREATURES[index]) {
					int level = DungeonConstants.SLAYER_LEVELS[index];

					if (player.getSkills().getLevel(Skills.SLAYER) < level) {
						player.getPackets().sendGameMessage(
								"You need a Slayer level of " + level + " in order to attack this monster.");
						return false;
					}
					continue;
				}
			}
		}
		return true;
	}

	/*
	 * return process normaly
	 */
	@Override
	public boolean processButtonClick(int interfaceId, int componentId, int slotId, int slotId2, int packetId) {
		if (dungeon == null || !dungeon.hasStarted())
			return false;

		if (dungeon.isAtRewardsScreen()) {
			if (interfaceId == 933) {
				if (componentId >= 318 && componentId <= 322) {
					if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
						dungeon.leave(player);
					} else {
						if (voteStage == 2)
							return false;
						voteStage = 2;
						dungeon.ready(player);
					}
				}
			}
			return false;
		} else {
			if (interfaceId == 950 && componentId == 24) {
				player.getActionManager().setAction(new HomeTeleport(dungeon.getHomeTile()));
				return false;
			} else if ((interfaceId == 548 && componentId == 148) || (interfaceId == 746 && componentId == 199)) {
				if (player.getInterfaceManager().containsScreenInter()
						|| player.getInterfaceManager().containsInventoryInter()) {
					// TODO cant open sound
					player.getPackets()
							.sendGameMessage("Please finish what you're doing before opening the dungeon map.");
					return false;
				} /*
					 * else if (player.getAttackedByDelay() >= Utils.currentTimeMillis()) {
					 * player.getPackets().
					 * sendGameMessage("You cannot be in combat while opening the dungeon map.");
					 * return false; }
					 */
				dungeon.openMap(player);
				return false;
			} else if (interfaceId == Inventory.INVENTORY_INTERFACE) {
				Item item = player.getInventory().getItem(slotId);
				if (item == null || item.getId() != slotId2)
					return false;
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					int leatherIndex = LeatherCraftingD.getIndex(item.getId());
					if (leatherIndex != -1) {
						player.getDialogueManager().startDialogue("LeatherCraftingD", leatherIndex, true);
						return false;
					}

					for (int index = 0; index < DungeoneeringTraps.ITEM_TRAPS.length; index++) {
						if (item.getId() == DungeoneeringTraps.ITEM_TRAPS[index]) {
							DungeoneeringTraps.placeTrap(player, dungeon, index);
							return false;
						}
					}
					for (int i = 0; i < PoltergeistRoom.HERBS.length; i++) {
						if (item.getId() == PoltergeistRoom.HERBS[i]) {
							VisibleRoom room = dungeon.getVisibleRoom(dungeon.getCurrentRoomReference(player));
							if (room == null)
								return false;
							if (!(room instanceof PoltergeistRoom)) {
								player.getPackets().sendGameMessage(
										"You need to be closer to the poltergeist to cleanse this herb.");
								return false;
							}
							((PoltergeistRoom) room).consecrateHerbs(player, item.getId());
							return false;
						}
					}
				}
				return true;
			} else if (interfaceId == Summoning.POUCHES_INTERFACE) {
				// TODO Summoning.handleInfusionOptions(player, packetId, componentId, slotId,
				// slotId2, true);
				return false;
			} else if (interfaceId == DungeonResourceShop.RESOURCE_SHOP) {
				if (componentId == 24) {
					int quantity = -1;
					if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
						quantity = 1;
					else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
						quantity = 5;
					else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
						quantity = 10;
					else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET)
						quantity = 50;
					DungeonResourceShop.handlePurchaseOptions(player, slotId, quantity);
				}
				return false;
			} else if (interfaceId == DungeonResourceShop.RESOURCE_SHOP_INV) {
				if (componentId == 0) {
					int quantity = -1;
					if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
						quantity = 1;
					else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
						quantity = 5;
					else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
						quantity = 10;
					else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET)
						quantity = 50;
					DungeonResourceShop.handleSellOptions(player, slotId, slotId2, quantity);
				}
				return false;
			} else if (interfaceId == 950) {
				if (componentId == 2)
					player.getCombatDefinitions().switchDefensiveCasting();
				else if (componentId == 7)
					player.getCombatDefinitions().switchShowCombatSpells();
				else if (componentId == 9)
					player.getCombatDefinitions().switchShowTeleportSkillSpells();
				else if (componentId == 11)
					player.getCombatDefinitions().switchShowMiscallaneousSpells();
				else if (componentId == 13)
					player.getCombatDefinitions().switchShowSkillSpells();
				else if (componentId >= 15 & componentId <= 17)
					player.getCombatDefinitions().setSortSpellBook(componentId - 15);
				else if (componentId == 39 || componentId == 40)
					stoneTeleport(componentId == 40);
				else if (componentId == 38) {
					if (!canCreateGatestone())
						return false;
					Dialogue d = player.getDialogueManager().getLast();
					if ((d != null && d instanceof DestroyCurrentStone)
							|| packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
						if (d != null)
							player.getDialogueManager().finishDialogue();
						removeCurrentGatestone();
						addGatestone();
						return true;
					} else {
						if (gatestone != null)
							player.getDialogueManager().startDialogue("DestroyCurrentStone");
						else
							addGatestone();
					}
				} else
					Magic.processDungSpell(player, componentId, packetId);
				return false;
			}
			return true;
		}
	}

	public void addGatestone() {
		if (!Magic.checkRunes(player, true, true, 17789, 3))
			return;
		player.getInventory().addItem(DungeonConstants.GATESTONE, 1);
		player.getSkills().addXp(Skills.MAGIC, 43.5);
		player.animate(new Animation(713));
		player.gfx(new Graphics(113));
	}

	public void removeCurrentGatestone() {
		if (gatestone != null) {
			FloorItem item = World.getRegion(gatestone.getRegionId()).getGroundItem(DungeonConstants.GATESTONE,
					gatestone, player);
			if (item == null)
				return;
			World.removeGroundItem(player, item, false);
			setGatestone(null);
		}
	}

	public boolean canDropItem(Item item) {
		if (item.getId() == 15707) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You cannot destroy that here.");
			return false;
		}
		if (item.getDefinitions().isDestroyItem())
			return true;
		WorldTile currentTile = new WorldTile(player);
		player.getPackets().sendSound(2739, 0, 1); // global drop
		player.stopAll(false);
		player.getInventory().deleteItem(item);
		if (item.getId() == DungeonConstants.GROUP_GATESTONE)
			dungeon.setGroupGatestone(currentTile);
		else if (item.getId() == DungeonConstants.GATESTONE) {
			removeCurrentGatestone();
			setGatestone(currentTile);
			World.addGroundItem(item, currentTile, player, true, -1, 2, -1);
			player.getPackets().sendGameMessage("You place the gatestone. You can teleport back to it at any time.");
			return false;
		}
		World.addGroundItem(item, currentTile);
		return false;
	}

	private void stoneTeleport(boolean group) {
		WorldTile tile = group ? dungeon.getGroupGatestone() : gatestone;

		// TODO if (player.isCantWalk() || (group &&
		// dungeon.getParty().getLeaderPlayer().getControlerManager().getControler()
		// instanceof Kalaboss))
		// TODO return;

		if (dungeon.isAtBossRoom(player, 26, 626, true)
				|| (dungeon.isAtBossRoom(player, 26, 672, true) && !YkLagorThunderous.isBehindPillar(player, dungeon,
						dungeon.getCurrentRoomReference(new WorldTile(player))))) {
			player.getPackets().sendGameMessage("You can't teleport here.");
			return;
		} else if (tile == null) { // Shouldn't happen for group gatestone
			player.getPackets().sendGameMessage("You currently do not have an active gatestone.");
			return;
		} else if (tile == player) {
			player.getPackets().sendGameMessage("There is no point in teleporting to yourself.");
			return;
		}

		if (!group) {
			FloorItem item = World.getRegion(gatestone.getRegionId()).getGroundItem(DungeonConstants.GATESTONE, tile,
					player);
			if (item == null)
				return;
			World.removeGroundItem(player, item);
			player.getInventory().deleteItem(item);
			setGatestone(null);
		}
		Magic.sendTeleportSpell(player, 13288, 13285, 2516, 2517, group ? 64 : 32, 0, tile, 2, false,
				Magic.MAGIC_TELEPORT, group ? DungeonConstants.GROUP_GATESTONE_RUNES : new int[] {});
		player.resetReceivedDamage();
		player.unlock();
		// TODO player.setCantWalk(true);
		player.getEmotesManager().setNextEmoteEnd(2000); // prevents dropping etc
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.resetReceivedDamage();
				// TODO player.setCantWalk(false);
			}
		}, 4);
	}

	private void portalGroupStoneTeleport() {
		Magic.sendTeleportSpell(player, 14279, 13285, 2518, 2517, 1, 0, dungeon.getGroupGatestone(), 1, false,
				Magic.OBJECT_TELEPORT);
	}

	private boolean canCreateGatestone() {
		if (player.getInventory().containsItem(DungeonConstants.GATESTONE, 1)) {
			player.getPackets()
					.sendGameMessage("You already have a gatestone in your pack. Making another would be pointless.");
			return false;
		} else if (!Magic.checkSpellLevel(player, 32))
			return false;
		else if (!Magic.checkRunes(player, false, true, 17789, 3))
			return false;
		return true;
	}

	public void leaveDungeonPermanently() {
		int index = dungeon.getParty().getIndex(player);
		leaveDungeon();
		for (Player p2 : dungeon.getParty().getTeam())
			p2.getPackets().sendCSVarInteger(1397 + index, 2);
	}

	@Override
	public boolean processItemOnPlayer(Player p2, Item item) {
		Food food = Food.forId(item.getId());
		if (food != null) {
			if (p2.getFoodDelay() > Utils.currentTimeMillis() || p2.getPotDelay() > Utils.currentTimeMillis())
				return true;
			p2.addFoodDelay(1000);
			p2.applyHit(new Hit(p2, food.getHeal() * 10, HitLook.HEALED_DAMAGE));
			healedDamage += food.getHeal() * 10;
			p2.getActionManager().addActionDelay(3);
			player.getInventory().deleteItem(item);
			// TODO if (food.getEffect() != null) {
			// food.getEffect().effect(player);
			// }
			return false;
		}
		return true;
	}

	@Override
	public void forceClose() {
		if (dungeon != null)
			dungeon.getParty().leaveParty(player, false);
		else { // rollback prevent taking items
			player.getInventory().reset();
			player.getEquipment().reset();
			if (player.getFamiliar() != null)
				player.getFamiliar().sendDeath(player);
		}
	}

	@Override
	public boolean logout() {
		if (dungeon != null)
			dungeon.getParty().leaveParty(player, true);
		return true; // else removes katalaboss controller
	}

	private void setGatestone(WorldTile tile) {
		this.gatestone = tile;
	}

	public boolean isKilledBossWithLessThan10HP() {
		return killedBossWithLessThan10HP;
	}
}
