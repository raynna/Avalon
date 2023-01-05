package com.rs.game.objects.plugins;

import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Magic;

public class DoorsAndGates extends ObjectPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] { 1817, 1816, 32015, 1765, 29624};
	}

	
	@Override
	public boolean processObject(Player player, WorldObject object) {
		int id = object.getId();
		if (id == 1817 && object.getX() == 2273 && object.getY() == 4680) // kbd
			// lever
			Magic.pushLeverTeleport(player, new WorldTile(3067, 10254, 0));
		else if (id == 1816 && object.getX() == 3067 && object.getY() == 10252) // kbd
			// out
			// lever
			Magic.pushLeverTeleport(player, new WorldTile(2273, 4681, 0));
		else if (id == 32015 && object.getX() == 3069 && object.getY() == 10256) { // kbd
			// stairs
			player.useStairs(828, new WorldTile(3017, 3848, 0), 1, 2);
			player.getControlerManager().startControler("WildernessControler");
		} else if (id == 1765 && object.getX() == 3017 && object.getY() == 3849) { // kbd
			// out
			// stairs
			player.stopAll();
			player.setNextWorldTile(new WorldTile(3069, 10255, 0));
			player.getControlerManager().startControler("WildernessControler");
		}
		if (id == 1816) {
			if (!player.KBDEntrance) {
				player.getDialogueManager().startDialogue("KBDEntrance");
				return false;
			}
		}
		if (object.getId() == 29624) {
			if (!player.safetyLever) {
				player.getPackets().sendGameMessage("This gate is locked.");
				return false;
			}
			if (object.getX() == 3178 && object.getY() == 4269)
				player.useStairs(-1, new WorldTile(3177, 4266, 0), 1, 2);
			if (object.getX() == 3178 && object.getY() == 4266)
				player.useStairs(-1, new WorldTile(3177, 4269, 2), 1, 2);
			if (object.getX() == 3142 && object.getY() == 4270)
				player.useStairs(-1, new WorldTile(3142, 4272, 1), 1, 2);
			if (object.getX() == 3141 && object.getY() == 4272)
				player.useStairs(-1, new WorldTile(3143, 4270, 0), 1, 2);
			return false;
		}
		return true;
	}
	
	public static boolean handleGateTemporary(Player player, WorldObject object, int timer) {
		if (object.getRotation() == 0) {
			boolean south = true;
			WorldObject otherDoor = World.getObjectWithType(
					new WorldTile(object.getX(), object.getY() + 1, object.getPlane()), object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
					|| otherDoor.getType() != object.getType()
					|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObjectWithType(new WorldTile(object.getX(), object.getY() - 1, object.getPlane()),
						object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
						|| otherDoor.getType() != object.getType()
						|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name))
					return false;
				south = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation(),
					object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation(),
					otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (south) {
				openedDoor1.moveLocation(-1, 0, 0);
				openedDoor2.moveLocation(-1, 0, 0);
				openedDoor1.setRotation(3);
				openedDoor2.setRotation(1);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			} else {
				openedDoor1.moveLocation(-1, 0, 0);
				openedDoor2.moveLocation(-1, 0, 0);
				openedDoor1.setRotation(1);
				openedDoor2.setRotation(3);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			}
			if (World.removeObjectTemporary(object, timer, false)
					&& World.removeObjectTemporary(otherDoor, timer, false)) {
				player.faceObject(openedDoor1);
				World.spawnObjectTemporary(openedDoor1, timer);
				World.spawnObjectTemporary(openedDoor2, timer);
				return true;
			}
		} else if (object.getRotation() == 2) {

			boolean south = true;
			WorldObject otherDoor = World.getObjectWithType(
					new WorldTile(object.getX(), object.getY() + 1, object.getPlane()), object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
					|| otherDoor.getType() != object.getType()
					|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObjectWithType(new WorldTile(object.getX(), object.getY() - 1, object.getPlane()),
						object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
						|| otherDoor.getType() != object.getType()
						|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name))
					return false;
				south = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation(),
					object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation(),
					otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (south) {
				openedDoor1.moveLocation(1, 0, 0);
				openedDoor2.moveLocation(1, 0, 0);
				openedDoor1.setRotation(3);
				openedDoor2.setRotation(1);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			} else {
				openedDoor1.moveLocation(1, 0, 0);
				openedDoor2.moveLocation(1, 0, 0);
				openedDoor1.setRotation(1);
				openedDoor2.setRotation(3);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			}
			if (World.removeObjectTemporary(object, timer, false)
					&& World.removeObjectTemporary(otherDoor, timer, false)) {
				player.faceObject(openedDoor1);
				World.spawnObjectTemporary(openedDoor1, timer);
				World.spawnObjectTemporary(openedDoor2, timer);
				return true;
			}
		} else if (object.getRotation() == 3) {

			boolean right = true;
			WorldObject otherDoor = World.getObjectWithType(
					new WorldTile(object.getX() - 1, object.getY(), object.getPlane()), object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
					|| otherDoor.getType() != object.getType()
					|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObjectWithType(new WorldTile(object.getX() + 1, object.getY(), object.getPlane()),
						object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
						|| otherDoor.getType() != object.getType()
						|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name))
					return false;
				right = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation(),
					object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation(),
					otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (right) {
				openedDoor1.moveLocation(0, -1, 0);
				openedDoor2.moveLocation(0, -1, 0);
				openedDoor1.setRotation(2);
				openedDoor2.setRotation(0);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			} else {
				openedDoor1.moveLocation(0, -1, 0);
				openedDoor2.moveLocation(0, -1, 0);
				openedDoor1.setRotation(0);
				openedDoor2.setRotation(2);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			}
			if (World.removeObjectTemporary(object, timer, false)
					&& World.removeObjectTemporary(otherDoor, timer, false)) {
				player.faceObject(openedDoor1);
				World.spawnObjectTemporary(openedDoor1, timer);
				World.spawnObjectTemporary(openedDoor2, timer);
				return true;
			}
		} else if (object.getRotation() == 1) {

			boolean right = true;
			WorldObject otherDoor = World.getObjectWithType(
					new WorldTile(object.getX() - 1, object.getY(), object.getPlane()), object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
					|| otherDoor.getType() != object.getType()
					|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObjectWithType(new WorldTile(object.getX() + 1, object.getY(), object.getPlane()),
						object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
						|| otherDoor.getType() != object.getType()
						|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name))
					return false;
				right = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation(),
					object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation(),
					otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (right) {
				openedDoor1.moveLocation(0, 1, 0);
				openedDoor2.moveLocation(0, 1, 0);
				openedDoor1.setRotation(2);
				openedDoor2.setRotation(0);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			} else {
				openedDoor1.moveLocation(0, 1, 0);
				openedDoor2.moveLocation(0, 1, 0);
				openedDoor1.setRotation(0);
				openedDoor2.setRotation(2);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			}
			if (World.removeObjectTemporary(object, timer, false)
					&& World.removeObjectTemporary(otherDoor, timer, false)) {
				player.faceObject(openedDoor1);
				World.spawnObjectTemporary(openedDoor1, timer);
				World.spawnObjectTemporary(openedDoor2, timer);
				if (player.getY() == object.getY())
					player.addWalkSteps(player.getX(), player.getY() + 1, 1, false);
				if (player.getY() > object.getY())
					player.addWalkSteps(player.getX(), player.getY() - 1, 1, false);
				return true;
			}
		}
		return false;
	}

	public static int getClosedGateId(int id) {
		switch (id) {
		case 11718:
			return 11717;

		case 11716:
		case 11717:
			return 11722;
		case 11719:
		case 11721:
			return 11723;
		case 24369:
			return 24373;
		case 24370:
			return 24374;
		}
		return id;
	}

	public static int getOpenedGateId(int id) {
		switch (id) {
		case 11722:
			return 11716;
		case 11723:
			return 11721;
		case 24369:
			return 24373;
		case 24370:
			return 24374;
		}
		return id;
	}

	public static boolean handleGate(Player player, WorldObject object) {
		if (World.isSpawnedObject(object))
			return false;
		int doorDelay = 60000;
		if (object.getId() == 65386) {
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation(),
					object.getX(), object.getY(), object.getPlane());
			openedDoor1.moveLocation(-1, 0, 0);
			openedDoor1.setRotation(3);
			openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
			if (World.removeObjectTemporary(object, doorDelay, true)) {
				player.faceObject(openedDoor1);
				World.spawnObjectTemporary(openedDoor1, doorDelay);
				return true;
			}
			return true;
		}
		if (object.getRotation() == 0) {
			boolean south = true;
			WorldObject otherDoor = World.getObjectWithType(
					new WorldTile(object.getX(), object.getY() + 1, object.getPlane()), object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
					|| otherDoor.getType() != object.getType()
					|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObjectWithType(new WorldTile(object.getX(), object.getY() - 1, object.getPlane()),
						object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
						|| otherDoor.getType() != object.getType()
						|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name))
					return false;
				south = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation(),
					object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation(),
					otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (south) {
				openedDoor1.moveLocation(-1, 0, 0);
				openedDoor2.moveLocation(-1, 0, 0);
				openedDoor1.setRotation(3);
				openedDoor2.setRotation(1);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			} else {
				openedDoor1.moveLocation(-1, 0, 0);
				openedDoor2.moveLocation(-1, 0, 0);
				openedDoor1.setRotation(1);
				openedDoor2.setRotation(3);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			}
			if (World.removeObjectTemporary(object, doorDelay, true)
					&& World.removeObjectTemporary(otherDoor, doorDelay, true)) {
				player.faceObject(openedDoor1);
				World.spawnObjectTemporary(openedDoor1, doorDelay);
				World.spawnObjectTemporary(openedDoor2, doorDelay);
				return true;
			}
		} else if (object.getRotation() == 2) {
			boolean south = true;
			WorldObject otherDoor = World.getObjectWithType(
					new WorldTile(object.getX(), object.getY() + 1, object.getPlane()), object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
					|| otherDoor.getType() != object.getType()
					|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObjectWithType(new WorldTile(object.getX(), object.getY() - 1, object.getPlane()),
						object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
						|| otherDoor.getType() != object.getType()
						|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name))
					return false;
				south = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation(),
					object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation(),
					otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (south) {
				openedDoor1.moveLocation(1, 0, 0);
				openedDoor2.moveLocation(1, 0, 0);
				openedDoor1.setRotation(3);
				openedDoor2.setRotation(1);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			} else {
				openedDoor1.moveLocation(1, 0, 0);
				openedDoor2.moveLocation(1, 0, 0);
				openedDoor1.setRotation(1);
				openedDoor2.setRotation(3);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			}
			if (World.removeObjectTemporary(object, doorDelay, true)
					&& World.removeObjectTemporary(otherDoor, doorDelay, true)) {
				player.faceObject(openedDoor1);
				World.spawnObjectTemporary(openedDoor1, doorDelay);
				World.spawnObjectTemporary(openedDoor2, doorDelay);
				return true;
			}
		} else if (object.getRotation() == 3) {

			boolean right = true;
			WorldObject otherDoor = World.getObjectWithType(
					new WorldTile(object.getX() - 1, object.getY(), object.getPlane()), object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
					|| otherDoor.getType() != object.getType()
					|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObjectWithType(new WorldTile(object.getX() + 1, object.getY(), object.getPlane()),
						object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
						|| otherDoor.getType() != object.getType()
						|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name))
					return false;
				right = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation(),
					object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation(),
					otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (right) {
				openedDoor1.moveLocation(0, -1, 0);
				openedDoor2.moveLocation(0, -1, 0);
				openedDoor1.setRotation(2);
				openedDoor2.setRotation(0);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			} else {
				openedDoor1.moveLocation(0, -1, 0);
				openedDoor2.moveLocation(0, -1, 0);
				openedDoor1.setRotation(0);
				openedDoor2.setRotation(2);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			}
			if (World.removeObjectTemporary(object, doorDelay, true)
					&& World.removeObjectTemporary(otherDoor, doorDelay, true)) {
				player.faceObject(openedDoor1);
				World.spawnObjectTemporary(openedDoor1, doorDelay);
				World.spawnObjectTemporary(openedDoor2, doorDelay);
				return true;
			}
		} else if (object.getRotation() == 1) {

			boolean right = true;
			WorldObject otherDoor = World.getObjectWithType(
					new WorldTile(object.getX() - 1, object.getY(), object.getPlane()), object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
					|| otherDoor.getType() != object.getType()
					|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObjectWithType(new WorldTile(object.getX() + 1, object.getY(), object.getPlane()),
						object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
						|| otherDoor.getType() != object.getType()
						|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name))
					return false;
				right = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation(),
					object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation(),
					otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (right) {
				openedDoor1.moveLocation(0, 1, 0);
				openedDoor2.moveLocation(0, 1, 0);
				openedDoor1.setRotation(2);
				openedDoor2.setRotation(0);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			} else {
				openedDoor1.moveLocation(0, 1, 0);
				openedDoor2.moveLocation(0, 1, 0);
				openedDoor1.setRotation(0);
				openedDoor2.setRotation(2);
				openedDoor1.setId(getClosedGateId(openedDoor1.getId()));
				openedDoor2.setId(getClosedGateId(openedDoor2.getId()));
			}
			if (World.removeObjectTemporary(object, doorDelay, true)
					&& World.removeObjectTemporary(otherDoor, doorDelay, true)) {
				player.faceObject(openedDoor1);
				World.spawnObjectTemporary(openedDoor1, doorDelay);
				World.spawnObjectTemporary(openedDoor2, doorDelay);
				return true;
			}
		}
		return false;
	}

	public static boolean handleCloseGate(Player player, WorldObject object) {
		if (object.getRotation() == 1) {
			WorldObject otherDoor = World.getObjectWithType(
					new WorldTile(object.getX(), object.getY() - 1, object.getPlane()), object.getType());
			WorldObject closedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation(),
					object.getX(), object.getY(), object.getPlane());
			WorldObject closedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation(),
					otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			closedDoor1.moveLocation(-1, 0, 0);
			closedDoor1.setRotation(0);
			closedDoor1.setId(getOpenedGateId(closedDoor1.getId()));
			player.faceObject(object);
			World.removeObject(object);
			World.removeObject(otherDoor);
			World.spawnObject(closedDoor1);
			World.spawnObject(closedDoor2);
			return true;
		}
		if (object.getRotation() == 3) {
			WorldObject otherDoor = World.getObjectWithType(
					new WorldTile(object.getX(), object.getY() + 1, object.getPlane()), object.getType());
			WorldObject closedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation(),
					object.getX(), object.getY(), object.getPlane());
			WorldObject closedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation(),
					otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (player.getX() < closedDoor1.getX()) {
				closedDoor1.moveLocation(-1, 0, 0);
				closedDoor2.moveLocation(-1, 0, 0);
				closedDoor1.setRotation(0);
				closedDoor2.setRotation(0);
				closedDoor1.setId(getOpenedGateId(closedDoor1.getId()));
				closedDoor2.setId(getOpenedGateId(closedDoor2.getId()));
			} else if (player.getX() > closedDoor1.getX()) {
				closedDoor1.moveLocation(1, 0, 0);
				closedDoor2.moveLocation(1, 0, 0);
				closedDoor1.setRotation(0);
				closedDoor2.setRotation(0);
				closedDoor1.setId(getOpenedGateId(closedDoor1.getId()));
				closedDoor2.setId(getOpenedGateId(closedDoor2.getId()));
			}
			player.faceObject(object);
			World.removeObject(object);
			World.removeObject(otherDoor);
			World.spawnObject(closedDoor1);
			World.spawnObject(closedDoor2);
			return true;
		}
		return false;
	}

	public static boolean handleCloseDoor(Player player, WorldObject object) {
		WorldObject closedDoor = new WorldObject(object.getId() - 1, object.getType(), object.getRotation() - 1,
				object.getX(), object.getY(), object.getPlane());
		if (object.getId() == 11715 && (object.getX() == 2959 && object.getY() == 3334 && object.getPlane() == 0)
				|| (object.getX() == 2960 && object.getY() == 3343 && object.getPlane() == 0)) {
			player.getPackets().sendGameMessage("It won't budge!");
			return false;
		}
		if (object.getId() == 24375 || object.getId() == 15535 || (object.getX() == 2966 && object.getY() == 3328)
				|| (object.getX() == 2978 && object.getY() == 3330) || (object.getX() == 2988 && object.getY() == 3334)
				|| (object.getX() == 2980 && object.getY() == 3316) || (object.getX() == 3027 && object.getY() == 3379)
				|| (object.getX() == 2977 && object.getY() == 3373) || (object.getX() == 2971 && object.getY() == 3376)
				|| (object.getX() == 2961 && object.getY() == 3372) || (object.getX() == 2956 && object.getY() == 3378)
				|| (object.getX() == 2958 && object.getY() == 3385) || (object.getX() == 2950 && object.getY() == 3385)
				|| (object.getX() == 2945 && object.getY() == 3337) || (object.getX() == 2972 && object.getY() == 3314)
				|| (object.getX() == 3034 && object.getY() == 3290)
				|| (object.getX() == 3092 && object.getY() == 3287)) {
			if (World.removeObjectTemporary(object, 60000, true)) {
				if (object.getRotation() == 0) {
					closedDoor.setRotation(3);
					closedDoor.moveLocation(0, 1, 0);
				} else if (object.getRotation() == 1) {
					closedDoor.moveLocation(1, 0, 0);
				} else if (object.getRotation() == 2) {
					closedDoor.moveLocation(0, -1, 0);
				} else if (object.getRotation() == 3) {
					closedDoor.moveLocation(-1, 0, 0);
				} else if (object.getRotation() == 4) {
					closedDoor.moveLocation(0, 1, 0);
				}
				if (object.getId() == 15535)
					closedDoor.setId(15536);
				else if (object.getId() == 24375)
					closedDoor.setId(24376);
				player.faceObject(closedDoor);
				World.spawnObjectTemporary(closedDoor, 60000);
				return false;
			}
		}
		if (object.getRotation() == 0) {
			closedDoor.moveLocation(0, 1, 0);
		} else if (object.getRotation() == 1) {
			closedDoor.moveLocation(1, 0, 0);
		} else if (object.getRotation() == 2) {
			closedDoor.moveLocation(0, -1, 0);
		} else if (object.getRotation() == 3) {
			closedDoor.moveLocation(-1, 0, 0);
		} else if (object.getRotation() == 4) {
			closedDoor.moveLocation(0, 1, 0);
		}
		World.removeObject(object);
		player.faceObject(closedDoor);
		World.spawnObject(closedDoor);
		return true;
	}

	public static boolean handle2DoorTemporary(Player player, WorldObject object, long timer) {
		if (World.isSpawnedObject(object))
			return false;
		WorldObject otherDoor = World.getObjectWithType(
				new WorldTile(object.getX(), object.getY() - 1, object.getPlane()), object.getType());
		WorldObject otherDoor2 = World.getObjectWithType(
				new WorldTile(object.getX(), object.getY() + 1, object.getPlane()), object.getType());
		WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
				object.getX(), object.getY(), object.getPlane());
		WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation() + 1,
				otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
		WorldObject openedDoor3 = new WorldObject(otherDoor2.getId(), otherDoor2.getType(),
				otherDoor2.getRotation() + 1, otherDoor2.getX(), otherDoor2.getY(), otherDoor2.getPlane());
		if (object.getRotation() == 0) {
			if (object.getId() == 35549)
				openedDoor.setRotation(3);
			openedDoor.moveLocation(-1, 0, 0);
			if (player.getX() == object.getX())
				player.addWalkSteps(player.getX() - 1, player.getY(), 1, false);
			if (player.getX() < object.getX())
				player.addWalkSteps(player.getX() + 1, player.getY(), 1, false);
		} else if (object.getRotation() == 1) {
			if (player.getY() == object.getY())
				player.addWalkSteps(player.getX(), player.getY() + 1, 1, false);
			if (player.getY() > object.getY())
				player.addWalkSteps(player.getX(), player.getY() - 1, 1, false);
			openedDoor.moveLocation(0, 1, 0);
		} else if (object.getRotation() == 2) {
			openedDoor.moveLocation(1, 0, 0);
		} else if (object.getRotation() == 3) {
			openedDoor.moveLocation(0, -1, 0);
		}
		if (object.getId() == 35551 && player.getY() < object.getY()) {
			openedDoor2.moveLocation(-1, 0, 0);
			openedDoor2.setRotation(3);
			if (World.removeObjectTemporary(otherDoor, timer, false)) {
				player.faceObject(openedDoor2);
				World.spawnObjectTemporary(openedDoor2, timer);
				return true;
			}
		}
		if (object.getId() == 35549 && player.getY() > object.getY()) {
			openedDoor3.moveLocation(-1, 0, 0);
			openedDoor3.setRotation(1);
			if (World.removeObjectTemporary(otherDoor2, timer, false)) {
				player.faceObject(openedDoor3);
				World.spawnObjectTemporary(openedDoor3, timer);
				return true;
			}
		}
		if (World.removeObjectTemporary(object, timer, false)) {
			player.faceObject(openedDoor);
			World.spawnObjectTemporary(openedDoor, timer);
			return true;
		}
		return false;
	}

	public static boolean handleDoorTemporary(Player player, WorldObject object, long timer) {
		if (World.isSpawnedObject(object))
			return false;
		WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
				object.getX(), object.getY(), object.getPlane());
		if (object.getRotation() == 0) {
			openedDoor.moveLocation(-1, 0, 0);
			if (player.getX() == object.getX())
				player.addWalkSteps(object.getX() - 1, object.getY(), 1, false);
			if (player.getX() < object.getX())
				player.addWalkSteps(object.getX() + 1, object.getY(), 1, false);
		} else if (object.getRotation() == 1) {
			if (player.getY() == object.getY())
				player.addWalkSteps(object.getX(), object.getY() + 1, 1, false);
			if (player.getY() > object.getY())
				player.addWalkSteps(object.getX(), object.getY() - 1, 1, false);
			openedDoor.moveLocation(0, 1, 0);
		} else if (object.getRotation() == 2) {
			if (player.getX() == object.getX())
				player.addWalkSteps(object.getX() + 1, object.getY(), 1, false);
			if (player.getX() > object.getX())
				player.addWalkSteps(object.getX() - 1, object.getY(), 1, false);
			openedDoor.moveLocation(1, 0, 0);
		} else if (object.getRotation() == 3) {
			if (player.getY() == object.getY())
				player.addWalkSteps(object.getX(), object.getY() - 1, 1, false);
			if (player.getY() < object.getY())
				player.addWalkSteps(object.getX(), object.getY() + 1, 1, false);
			openedDoor.moveLocation(0, -1, 0);
		}
		if (World.removeObjectTemporary(object, timer, false)) {
			player.faceObject(openedDoor);
			World.spawnObjectTemporary(openedDoor, timer);
			return true;
		}
		return false;
	}

	public static boolean handleDoorTemporary2(Player player, WorldObject object, long timer) {
		if (World.isSpawnedObject(object))
			return false;
		WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
				object.getX(), object.getY(), object.getPlane());
		if (object.getRotation() == 0) {
			openedDoor.moveLocation(-1, 0, 0);
			if (player.getX() == object.getX())
				player.addWalkSteps(object.getX() - 1, object.getY(), 1, false);
			if (player.getX() < object.getX())
				player.addWalkSteps(object.getX() + 1, object.getY(), 1, false);
		} else if (object.getRotation() == 1) {
			if (player.getY() == object.getY())
				player.addWalkSteps(object.getX(), object.getY() + 1, 1, false);
			if (player.getY() > object.getY())
				player.addWalkSteps(object.getX(), object.getY() - 1, 1, false);
			openedDoor.moveLocation(0, 1, 0);
		} else if (object.getRotation() == 2) {
			if (player.getX() == object.getX())
				player.addWalkSteps(object.getX() + 1, object.getY(), 1, false);
			if (player.getX() > object.getX())
				player.addWalkSteps(object.getX() - 1, object.getY(), 1, false);
			openedDoor.moveLocation(1, 0, 0);
		} else if (object.getRotation() == 3) {
			if (player.getY() == object.getY())
				player.addWalkSteps(object.getX(), object.getY() - 1, 1, false);
			if (player.getY() < object.getY())
				player.addWalkSteps(object.getX(), object.getY() + 1, 1, false);
			openedDoor.moveLocation(0, -1, 0);
		}
		if (World.removeObjectTemporary(object, timer, false)) {
			player.faceObject(openedDoor);
			World.spawnObjectTemporary(openedDoor, timer);
			return true;
		}
		return false;
	}

	public static int getOpenedDoorId(WorldObject object) {
		switch (object.getId()) {
		case 36846:
			return object.getId() + 2;
		case 37123:
			return 37130;
		case 15536:
			return 15535;
		case 24381:
		case 24384:
			return 24383;
		case 24376:
			return 24375;
		case 24378:
			return 24377;
		}
		return object.getId() + 1;
	}

	public static boolean handleDoor(Player player, WorldObject object, long timer) {
		WorldObject openedDoor = new WorldObject(getOpenedDoorId(object), object.getType(), object.getRotation() + 1,
				object.getX(), object.getY(), object.getPlane());
		if (object.getRotation() == 0) {
			openedDoor.moveLocation(-1, 0, 0);
		} else if (object.getRotation() == 1) {
			openedDoor.moveLocation(0, 1, 0);
		} else if (object.getRotation() == 2) {
			openedDoor.moveLocation(1, 0, 0);
		} else if (object.getRotation() == 3) {
			openedDoor.moveLocation(0, -1, 0);
		}
		if (World.removeObjectTemporary(object, timer, true)) {
			player.faceObject(openedDoor);
			World.spawnObjectTemporary(openedDoor, timer);
			return true;
		}
		return false;
	}

	public static boolean handleDoor(Player player, WorldObject object) {
		return handleDoor(player, object, 60000);
	}
}
