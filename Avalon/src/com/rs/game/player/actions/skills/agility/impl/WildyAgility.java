package com.rs.game.player.actions.skills.agility.impl;

import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.agility.Agility;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class WildyAgility {

	/**
	 * 
	 * @Improved Andreas - AvalonPK
	 * 
	 */

	public static void DoorStart2(final Player player, final WorldObject object) {
		player.lock();
		player.stopAll();
		player.animate(new Animation(9908));
		WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
				object.getX() + 1, object.getY() - 1, object.getPlane());
		if (World.removeObjectTemporary(object, 1200, false))
			World.spawnObjectTemporary(openedDoor, 1200);
		player.getPackets().sendGameMessage("You go trough the gate and try to edge over the ridge...", true);
		final WorldTile toTile = new WorldTile(object.getX() + 1, object.getY() - 15, object.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 16, ForceMovement.SOUTH));
		// player.addWalkSteps(object.getX() + 1, object.getY() - 17, 16,
		// false);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.unlock();
				player.setNextWorldTile(toTile);
				player.getPackets().sendGameMessage("You skillfully balance across the ridge.", true);
			}
		}, 14);
	}

	public static void DoorStart(final Player player, final WorldObject object) {
		player.lock();
		player.stopAll();
		player.animate(new Animation(9908));
		WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() - 1,
				object.getX(), object.getY() + 1, object.getPlane());
		if (World.removeObjectTemporary(object, 1200, false))
			World.spawnObjectTemporary(openedDoor, 1200);
		player.getPackets().sendGameMessage("You go trough the gate and try to edge over the ridge...", true);
		final WorldTile toTile = new WorldTile(object.getX(), object.getY() + 15, object.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 16, ForceMovement.NORTH));
		player.addWalkSteps(object.getX(), object.getY() + 17, 16, false);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.unlock();
				player.setNextWorldTile(toTile);
				player.getPackets().sendGameMessage("You skillfully balance across the ridge.", true);
			}
		}, 14);
	}

	public static void PipeStart(final Player player, final WorldObject object) {
		if (!Agility.hasLevel(player, 52))
			return;
		if (player.getY() != object.getY()) {
			player.addWalkSteps(3004, 3938, -1, false);
			player.lock(2);
			player.getPackets().sendGameMessage("You go trough the pipe...", true);
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					PipeEnd(player, object);
				}
			}, 1);
		} else
			PipeEnd(player, object);
	}

	private static void PipeEnd(final Player player, WorldObject object) {
		player.lock(13);
		final WorldTile toTile = new WorldTile(3004, 3949, object.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 12, ForceMovement.NORTH));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				setWildyStage(player, 0);
				player.addWalkSteps(3004, 3950, -1, false);
				player.lock(2);
				setWildyStage(player, 0);
				player.getSkills().addXp(Skills.AGILITY, 12.5);
				player.getPackets().sendGameMessage("... and you end up on the other side.", true);
				player.setNextWorldTile(toTile);
			}

		}, 8);
	}

	public static void swingOnRopeSwing(final Player player, WorldObject object) {
		if (!Agility.hasLevel(player, 52))
			return;
		if (player.getY() != 3953) {
			player.getPackets().sendGameMessage("You'll need to get closer to make this jump.");
			return;
		}
		player.lock(4);
		player.animate(new Animation(751));
		World.sendObjectAnimation(player, object, new Animation(497));
		final WorldTile toTile = new WorldTile(3005, 3958, object.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 1, toTile, 2, ForceMovement.NORTH));
		player.getPackets().sendGameMessage("You skilfully swing across.", true);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 20);
			}

		}, 1);
		if (getWildyStage(player) == 0)
			setWildyStage(player, 1);
	}

	public static void crossSteppingPalletes(final Player player, final WorldObject object) {
		if (player.getY() != object.getY())
			return;
		player.lock(6);
		WorldTasksManager.schedule(new WorldTask() {

			int x;

			@Override
			public void run() {
				if (x++ == 6) {
					player.getSkills().addXp(Skills.AGILITY, 20);
					stop();
					return;
				}
				final WorldTile toTile = new WorldTile(3002 - x, player.getY(), player.getPlane());
				player.setNextForceMovement(new ForceMovement(toTile, 1, ForceMovement.WEST));
				player.animate(new Animation(741));
				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {
						player.setNextWorldTile(toTile);
					}
				}, 0);
			}
		}, 2, 1);
		if (getWildyStage(player) == 1)
			setWildyStage(player, 2);
	}

	public static void walkLog(final Player player) {
		if (player.getX() != 3002 || player.getY() != 3945)
			player.addWalkSteps(3002, 3945, -1, false);
		final boolean running = player.getRun();
		player.setRunHidden(false);
		player.lock(8);
		player.addWalkSteps(2994, 3945, -1, false);
		player.getPackets().sendGameMessage("You walk carefully across the log...", true);
		WorldTasksManager.schedule(new WorldTask() {
			boolean secondloop;

			@Override
			public void run() {
				if (!secondloop) {
					secondloop = true;
					player.getAppearence().setRenderEmote(155);
				} else {
					player.getAppearence().setRenderEmote(-1);
					player.setRunHidden(running);
					player.getSkills().addXp(Skills.AGILITY, 20);
					player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
					stop();
					if (getWildyStage(player) == 2)
						setWildyStage(player, 3);
				}
			}
		}, 0, 6);
	}

	public static void climbCliff(final Player player, WorldObject object) {
		if (player.getY() != 3939) {
			return;
		}
		player.animate(new Animation(3378));
		final WorldTile toTile = new WorldTile(player.getX(), 3935, 0);

		player.getPackets().sendGameMessage("You climb up the rock.", true);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.animate(new Animation(-1));
				player.getAppearence().setRenderEmote(-1);
				stop();
				if (getWildyStage(player) == 3) {
					removeWildyStage(player);
					player.getSkills().addXp(Skills.AGILITY, 499.5);
				}
			}
		}, 6);
	}

	public static void removeWildyStage(Player player) {
		player.temporaryAttribute().remove("WildyAgility");
	}

	public static void setWildyStage(Player player, int stage) {
		player.temporaryAttribute().put("WildyAgility", stage);
	}

	public static int getWildyStage(Player player) {
		Integer stage = (Integer) player.temporaryAttribute().get("WildyAgility");
		if (stage == null)
			return -1;
		return stage;
	}
}
