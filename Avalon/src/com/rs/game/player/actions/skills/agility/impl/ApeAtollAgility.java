package com.rs.game.player.actions.skills.agility.impl;

import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.agility.Agility;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class ApeAtollAgility {

	public static void ClimbDownTropicalTree(final Player player, final WorldObject object) {
		if (!Agility.hasLevel(player, 48))
			return;
		if (player.getEquipment().getWeaponId() != 4024) {
			player.getPackets()
					.sendGameMessage("You need to transform into a ninja monkey to use the ape atoll courses.");
			return;
		}
		player.lock();
		final WorldTile toTile = new WorldTile(2769, 2746, 1);
		player.setNextForceMovement(new ForceMovement(player, 1, toTile, 7, ForceMovement.NORTH));
		player.getAppearence().setRenderEmote(760);
		player.getPackets().sendGameMessage("You climb the vine...");
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.getPackets().sendGameMessage("..And make it carefully to the end of it.");
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 55);
				player.getAppearence().setRenderEmote(-1);
				player.unlock();
				stop();
			}
		}, 2);
	}

	public static void ClimbDownVine(final Player player, WorldObject object) {
		if (!Agility.hasLevel(player, 48))
			return;
		if (player.getEquipment().getWeaponId() != 4024) {
			player.getPackets()
					.sendGameMessage("You need to transform into a ninja monkey to use the ape atoll courses.");
			return;
		}
		player.lock(3);
		final WorldTile toTile = new WorldTile(2770, 2747, 0);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.getPackets().sendGameMessage("You climb down the vine.");
				player.animate(new Animation(1381));
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 36);
				stop();
			}
		}, 1);
	}

	public static void ClimbUpSkullSlope(final Player player, final WorldObject object) {
		if (!Agility.hasLevel(player, 48))
			return;
		if (player.getEquipment().getWeaponId() != 4024) {
			player.getPackets()
					.sendGameMessage("You need to transform into a ninja monkey to use the ape atoll courses.");
			return;
		}
		player.lock(4);
		final WorldTile toTile = new WorldTile(2743, 2741, 0);
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 3, ForceMovement.WEST));
		player.getAppearence().setRenderEmote(739);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.getPackets().sendGameMessage("You climb up the skull slope.");
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 45);
				player.getAppearence().setRenderEmote(-1);
				stop();
			}
		}, 2);
	}

	public static void ClimbUpTropicalTree(final Player player, WorldObject object) {
		if (!Agility.hasLevel(player, 48))
			return;
		if (player.getEquipment().getWeaponId() != 4024) {
			player.getPackets()
					.sendGameMessage("You need to transform into a ninja monkey to use the ape atoll courses.");
			return;
		}
		player.lock(3);
		final WorldTile toTile = new WorldTile(2752, 2742, 2);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.getPackets().sendGameMessage("You climb up the tree...");
				player.animate(new Animation(1382));
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 25);
				stop();
			}
		}, 1);
	}

	public static void crossMonkeyBars(final Player player, final WorldObject object) {
		if (!Agility.hasLevel(player, 48))
			return;
		if (player.getEquipment().getWeaponId() != 4024) {
			player.getPackets()
					.sendGameMessage("You need to transform into a ninja monkey to use the ape atoll courses.");
			return;
		}
		player.lock(4);
		final WorldTile toTile = new WorldTile(2747, 2741, 0);
		final WorldTile toTile2 = new WorldTile(2747, 2741, 2);
		player.setNextForceMovement(new ForceMovement(player, 0, toTile2, 4, ForceMovement.WEST));
		player.getAppearence().setRenderEmote(744);
		player.getPackets().sendGameMessage("You jump to the monkey bars...");
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.getPackets().sendGameMessage("..And made it carefully to the other side.");
				player.getAppearence().setRenderEmote(-1);
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 35);
				stop();
			}
		}, 3);
	}

	public static void JumpToSteppingStone(final Player player, WorldObject object) {
		if (!Agility.hasLevel(player, 48))
			return;
		if (player.getEquipment().getWeaponId() != 4024) {
			player.getPackets()
					.sendGameMessage("You need to transform into a ninja monkey to use the ape atoll courses.");
			return;
		}
		player.lock(3);
		final WorldTile toTile = new WorldTile(object.getX(), object.getY(), object.getPlane());
		final WorldTile toTile2 = new WorldTile(player.getX() == 2755 ? 2753 : 2755, 2742, object.getPlane());
		final WorldTile WaterTile = new WorldTile(2756, 2746, object.getPlane());
		final WorldTile Land = new WorldTile(2757, 2746, object.getPlane());
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.getPackets().sendGameMessage("You jump to the stepping stone...");
				player.animate(new Animation(1381));
				player.setNextWorldTile(toTile);
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						if (Utils.random(5) == 0) {
							player.animate(new Animation(1381));
							player.getPackets().sendGameMessage("..And accidently fall to the water.");
							player.applyHit(new Hit(player, Utils.random(200), HitLook.REGULAR_DAMAGE));
							player.setNextForceMovement(
									new ForceMovement(player, 0, WaterTile, 3, ForceMovement.NORTH));
							player.getAppearence().setRenderEmote(741);
							WorldTasksManager.schedule(new WorldTask() {
								@Override
								public void run() {
									player.getAppearence().setRenderEmote(-1);
									player.setNextWorldTile(Land);
									stop();
								}
							}, 1);
							stop();
						} else {
							player.animate(new Animation(1381));
							player.setNextWorldTile(toTile2);
							player.getSkills().addXp(Skills.AGILITY, 15);
							player.getPackets().sendGameMessage("..And made it carefully to the other side.");
							stop();
						}
					}
				}, 1);
				stop();
			}
		}, 1);
	}

	public static void swingRope(final Player player, WorldObject object) {
		if (!Agility.hasLevel(player, 48))
			return;
		if (player.getX() == 2756)
			return;
		if (player.getEquipment().getWeaponId() != 4024) {
			player.getPackets().sendGameMessage("You need to be a ninja monkey to be able to do this agility.");
			return;
		}
		player.lock(4);
		player.animate(new Animation(1392));
		World.sendObjectAnimation(player, object, new Animation(497));
		final WorldTile toTile = new WorldTile(2756, 2731, object.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.EAST));
		player.getSkills().addXp(Skills.AGILITY, 22);
		player.getPackets().sendGameMessage("You skilfully swing across.", true);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				stop();
			}
		}, 1);
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isObject(WorldObject object) {
		switch (object.getId()) {
		case 12568:
		case 12570:
		case 12573:
		case 12575:
		case 12578:
		case 12618:
		case 12576:
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param object
	 * @param player
	 */
	public static void handleObjects(WorldObject object, Player player) {
		switch (object.getId()) {
		case 12568:
			JumpToSteppingStone(player, object);
			break;
		case 12570:
			ClimbUpTropicalTree(player, object);
			break;
		case 12618:
			ClimbDownVine(player, object);
			break;
		case 12573:
			crossMonkeyBars(player, object);
			break;
		case 12578:
			swingRope(player, object);
			break;
		case 12576:
			ClimbUpSkullSlope(player, object);
			break;
		}
	}
}