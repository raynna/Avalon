package com.rs.game.player.actions.skills.runecrafting;

import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.mining.MiningBase;
import com.rs.game.player.actions.skills.mining.MiningBase.PickAxeDefinitions;
import com.rs.game.player.actions.skills.woodcutting.Woodcutting;
import com.rs.game.player.actions.skills.woodcutting.Woodcutting.HatchetDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class AbbysObsticals {

	private static final int[][] ABYSS_TELEPORT_OUTER = { { 3059, 4817 },
			{ 3062, 4812 }, { 3052, 4810 }, { 3041, 4807 }, { 3035, 4811 },
			{ 3030, 4808 }, { 3026, 4810 }, { 3021, 4811 }, { 3015, 4810 },
			{ 3020, 4818 }, { 3018, 4819 }, { 3016, 4824 }, { 3013, 4827 },
			{ 3017, 4828 }, { 3015, 4837 }, { 3017, 4843 }, { 3014, 4849 },
			{ 3021, 4847 }, { 3022, 4852 }, { 3027, 4849 }, { 3031, 4856 },
			{ 3035, 4854 }, { 3043, 4855 }, { 3045, 4852 }, { 3050, 4857 },
			{ 3054, 4855 }, { 3055, 4848 }, { 3060, 4848 }, { 3059, 4844 },
			{ 3065, 4841 }, { 3061, 4836 }, { 3063, 4832 }, { 3064, 4828 },
			{ 3060, 4824 }, { 3063, 4821 }, { 3041, 4808 }, { 3030, 4810 },
			{ 3018, 4816 }, { 3015, 4829 }, { 3017, 4840 }, { 3020, 4849 },
			{ 3031, 4855 }, { 3020, 4854 }, { 3035, 4855 }, { 3047, 4854 },
			{ 3060, 4846 }, { 3062, 4836 }, { 3060, 4828 }, { 3063, 4820 },
			{ 3028, 4806 }, };

	public static void clearRocks(final Player player, final WorldObject object) {
		final PickAxeDefinitions defintions = MiningBase.getPickAxeDefinitions(
				player);
		if (defintions == null) {
			player.getPackets()
					.sendGameMessage(
							"You need a usable pickaxe in order to clear this obstical.");
			return;
		}
		player.lock();
		WorldTasksManager.schedule(new WorldTask() {
			int ticks = 0;

			@Override
			public void run() {
				ticks++;
				if (ticks == 1)
					player.faceObject(object);
				else if (ticks == 2) {
					player.animate(new Animation(defintions
							.getAnimationId()));
				} else if (ticks == 4) {
					if (!isSuccessFul(player, Skills.MINING)) {
						player.unlock();
						player.animate(new Animation(-1));
						stop();
					}
				} else if (ticks >= 5 && ticks <= 7) {
					demolishObstacle(7158 + (ticks - 5), object);
				} else if (ticks == 9) {
					player.setNextWorldTile(new WorldTile(object.getX(), object
							.getY() + 13, 0));
					player.unlock();
					stop();
				}
			}
		}, 1, 1);
		return;
	}

	public static void clearTendrills(final Player player,
			final WorldObject object, final WorldTile tile) {
		final HatchetDefinitions defintions = Woodcutting.getHatchet(player);
		if (defintions == null) {
			player.getPackets()
					.sendGameMessage(
							"You need a usable hatchet in order to clear this obstical.");
			return;
		}
		player.lock();
		WorldTasksManager.schedule(new WorldTask() {
			int ticks = 0;

			@Override
			public void run() {
				ticks++;
				if (ticks == 1)
					player.faceObject(object);
				else if (ticks == 2) {
					player.animate(new Animation(defintions
							.getEmoteId()));
				} else if (ticks == 3) {
					if (!isSuccessFul(player, Skills.WOODCUTTING)) {
						player.unlock();
						player.animate(new Animation(-1));
						stop();
					}
				} else if (ticks >= 4 && ticks <= 6) {
					demolishObstacle(7161 + (ticks - 4), object);
				} else if (ticks == 7) {
					player.setNextWorldTile(tile);
					player.unlock();
					stop();
				}
			}
		}, 1, 1);
		return;
	}

	public static void clearEyes(final Player player, final WorldObject object,
			final WorldTile tile) {
		player.lock();
		WorldTasksManager.schedule(new WorldTask() {
			int ticks = 0;

			@Override
			public void run() {
				ticks++;
				if (ticks == 1)
					player.faceObject(object);
				else if (ticks == 2) {
					player.animate(new Animation(866));
				} else if (ticks == 3) {
					if (!isSuccessFul(player, Skills.THIEVING)) {
						player.unlock();
						player.animate(new Animation(-1));
						stop();
					}
				} else if (ticks >= 4 && ticks <= 6) {
					demolishObstacle(7168 + (ticks - 4), object);
				} else if (ticks == 7) {
					player.setNextWorldTile(tile);
					player.unlock();
					stop();
				}
			}
		}, 1, 1);
		return;
	}

	public static void clearGap(final Player player, final WorldObject object,
			final WorldTile tile, final boolean quick) {
		player.lock();
		WorldTasksManager.schedule(new WorldTask() {
			int ticks = 0;

			@Override
			public void run() {
				ticks++;
				if (ticks == 1)
					player.faceObject(object);
				else if (ticks == 3) {
					player.animate(new Animation(844));
					if (!quick) {
						if (!isSuccessFul(player, Skills.AGILITY)) {
							player.getPackets().sendGameMessage(
									"You cannot seem to slip through the gap.");
							player.unlock();
							player.animate(new Animation(-1));
							stop();
						}
					}
				} else if (ticks == 4) {
					player.setNextWorldTile(tile);
					player.unlock();
					stop();
				}
			}
		}, 1, 1);
		return;
	}

	public static void burnGout(final Player player, final WorldObject object,
			final WorldTile tile) {
		if (!player.getInventory().containsItem(590, 1)
				&& player.getInventory().containsItemToolBelt(590)) {
			player.getPackets().sendGameMessage(
					"You need a tinderbox in order to burn the boil.");
			return;
		}
		player.lock();
		WorldTasksManager.schedule(new WorldTask() {
			int ticks = 0;

			@Override
			public void run() {
				ticks++;
				if (ticks == 1)
					player.faceObject(object);
				else if (ticks == 2) {
					player.animate(new Animation(733));
				} else if (ticks == 3) {
					if (!isSuccessFul(player, Skills.THIEVING)) {
						player.unlock();
						player.animate(new Animation(-1));
						stop();
					}
				} else if (ticks >= 4 && ticks <= 6) {
					demolishObstacle(7165 + (ticks - 4), object);
				} else if (ticks == 7) {
					player.setNextWorldTile(tile);
					player.unlock();
					stop();
				}
			}
		}, 1, 1);
		return;
	}

	private static void demolishObstacle(int objectId, WorldObject object) {
		object.setId(objectId);
		World.spawnObjectTemporary(object, 10000);
		return;
	}

	private static boolean isSuccessFul(Player player, int requestedSkill) {
		return (player.getSkills().getLevel(requestedSkill) / 99) > Math
				.random();
	}

	public static void teleport(final Player player, NPC npc) {
		player.lock(2);
		npc.setNextForceTalk(new ForceTalk("Veniens! Sallkar! Rinnesset!"));
		npc.gfx(new Graphics(108));
		player.gfx(new Graphics(110));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				int index = Utils.random(ABYSS_TELEPORT_OUTER.length);
				player.useStairs(-1, new WorldTile(
						ABYSS_TELEPORT_OUTER[index][0],
						ABYSS_TELEPORT_OUTER[index][1], 0), 0, 1);
				player.setWildernessSkull();
			}
		}, 2);
	}
}