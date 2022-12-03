package com.rs.game.player.content;

import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class AxeHut {

	/*
	 * @author Andreas, Axe Hut east of Mage Bank Requirements: 60 thieving & a
	 * lockpick Aggressive magical axes to avoid you getting pjed 2 Gates with a
	 * chance of failing while picklocking the gates Used to tank clans or used
	 * for a afk training
	 */

	public static void GateNorthOut(final Player player, final WorldObject object) {
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.lock(2);
				WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
						object.getX(), object.getY() - 1, object.getPlane());
				if (World.removeObjectTemporary(object, 1200, false))
					World.spawnObjectTemporary(openedDoor, 1200);
				player.addWalkSteps(object.getX(), object.getY(), -1, false);
			}
		}, 1);

	}

	public static void GateSouthOut(final Player player, final WorldObject object) {
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.lock(2);
				WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
						object.getX(), object.getY() + 1, object.getPlane());
				if (World.removeObjectTemporary(object, 1200, false))
					World.spawnObjectTemporary(openedDoor, 1200);
				player.addWalkSteps(object.getX(), object.getY(), -1, false);
			}
		}, 1);
	}

	public static void GateNorth(final Player player, final WorldObject object) {
		player.addWalkSteps(object.getX(), object.getY(), -1, false);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.lock(2);
				WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
						object.getX(), object.getY() - 1, object.getPlane());
				if (World.removeObjectTemporary(object, 1200, false))
					World.spawnObjectTemporary(openedDoor, 1200);
				player.addWalkSteps(object.getX(), object.getY() - 1, -1, false);
			}
		}, 1);
	}

	public static void GateSouth(final Player player, final WorldObject object) {
		player.addWalkSteps(object.getX(), object.getY(), -1, false);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.lock(2);
				WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
						object.getX(), object.getY() + 1, object.getPlane());
				if (World.removeObjectTemporary(object, 1200, false))
					World.spawnObjectTemporary(openedDoor, 1200);
				player.addWalkSteps(object.getX(), object.getY() + 1, -1, false);
			}
		}, 1);
	}
}