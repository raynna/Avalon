package com.rs.game.player.content;

import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class PirateHut {

	/*
	 * @Author Andreas, Pirate Hut west of Mage Bank Aggressive pirates to avoid
	 * you getting pjed. Requirements, 70 Thieving & a lockpick, id 1523 3 Doors
	 * & a chance of failing picklocking doors Used to tank clans if you're
	 * teleblocked
	 */

	public static void LeaveEastDoor(final Player player, final WorldObject object) {
		player.addWalkSteps(object.getX(), object.getY(), -1, false);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.lock(2);
				WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
						object.getX() + 1, object.getY(), object.getPlane());
				if (World.removeObjectTemporary(object, 1200, false))
					World.spawnObjectTemporary(openedDoor, 1200);
				player.addWalkSteps(object.getX() + 1, object.getY(), 1, false);
			}
		}, 1);
	}

	public static void LeaveWestDoor(final Player player, final WorldObject object) {
		player.addWalkSteps(object.getX(), object.getY(), -1, false);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.lock(2);
				WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
						object.getX() - 1, object.getY(), object.getPlane());
				if (World.removeObjectTemporary(object, 1200, false))
					World.spawnObjectTemporary(openedDoor, 1200);
				player.addWalkSteps(object.getX() - 1, object.getY(), 1, false);
			}
		}, 1);
	}

	public static void LeaveNorthDoor(final Player player, final WorldObject object) {
		player.addWalkSteps(object.getX(), object.getY(), -1, false);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.lock(2);
				WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
						object.getX(), object.getY() + 1, object.getPlane());
				if (World.removeObjectTemporary(object, 1200, false))
					World.spawnObjectTemporary(openedDoor, 1200);
				player.addWalkSteps(object.getX(), object.getY() + 1, 1, false);
			}
		}, 1);

	}

	public static void EnterEastDoor(final Player player, final WorldObject object) {
		if (player.getX() == 3045) {
			player.lock(2);
			WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
					object.getX() + 1, object.getY(), object.getPlane());
			if (World.removeObjectTemporary(object, 1200, false))
				World.spawnObjectTemporary(openedDoor, 1200);
			player.addWalkSteps(object.getX() - 1, object.getY(), 1, false);
		}
	}

	public static void EnterWestDoor(final Player player, final WorldObject object) {
		if (player.getX() == 3037) {
			player.lock(2);
			WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
					object.getX() - 1, object.getY(), object.getPlane());// Coordinations
			if (World.removeObjectTemporary(object, 1200, false))
				World.spawnObjectTemporary(openedDoor, 1200);
			player.addWalkSteps(object.getX(), object.getY(), 1, false);
		}
	}

	public static void EnterNorthDoor(final Player player, final WorldObject object) {
		if (player.getY() == 3960) {
			player.lock(2);
			WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
					object.getX(), object.getY() + 1, object.getPlane());
			if (World.removeObjectTemporary(object, 1200, false))
				World.spawnObjectTemporary(openedDoor, 1200);
			player.addWalkSteps(object.getX(), object.getY(), 1, false);

		}
	}
}