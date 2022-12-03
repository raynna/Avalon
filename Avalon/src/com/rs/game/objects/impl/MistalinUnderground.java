package com.rs.game.objects.impl;

import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class MistalinUnderground extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 29375 };
	}
	
	@Override
	public boolean processObject(Player player, WorldObject object) {
		int id = object.getId();
		if (id == 29375) {
			boolean north = player.getY() == 9963 ? true : false;
			player.lock();
			player.animate(new Animation(742));
			final WorldTile toTile = new WorldTile(3120, north ? 9964 : 9969, player.getPlane());
			player.setNextForceMovement(
					new ForceMovement(toTile, 1, north ? ForceMovement.NORTH : ForceMovement.SOUTH));
			player.setNextWorldTile(toTile);
			WorldTasksManager.schedule(new WorldTask() {

				int x;

				@Override
				public void run() {
					if (x++ == 7) {
						stop();
						return;
					}
					if (x == 1) {
						player.setNextAnimationNoPriority(new Animation(744), player);
						final WorldTile toTile = new WorldTile(3120, north ? 9964 + x : 9969 - x,
								player.getPlane());
						player.setNextForceMovement(new ForceMovement(toTile, 1,
								north ? ForceMovement.NORTH : ForceMovement.SOUTH));
						WorldTasksManager.schedule(new WorldTask() {

							@Override
							public void run() {
								player.setNextAnimationNoPriority(new Animation(745), player);
								player.setNextWorldTile(toTile);
							}
						}, 0);
					} else if (x == 2) {
						player.setNextAnimationNoPriority(new Animation(744), player);
						final WorldTile toTile = new WorldTile(3120, north ? 9964 + x : 9969 - x,
								player.getPlane());
						player.setNextForceMovement(new ForceMovement(toTile, 1,
								north ? ForceMovement.NORTH : ForceMovement.SOUTH));
						WorldTasksManager.schedule(new WorldTask() {

							@Override
							public void run() {
								player.setNextAnimationNoPriority(new Animation(745), player);
								player.setNextWorldTile(toTile);
							}
						}, 0);
					} else if (x == 3) {
						player.setNextAnimationNoPriority(new Animation(744), player);
						final WorldTile toTile = new WorldTile(3120, north ? 9964 + x : 9969 - x,
								player.getPlane());
						player.setNextForceMovement(new ForceMovement(toTile, 1,
								north ? ForceMovement.NORTH : ForceMovement.SOUTH));
						WorldTasksManager.schedule(new WorldTask() {

							@Override
							public void run() {
								player.setNextAnimationNoPriority(new Animation(745), player);
								player.setNextWorldTile(toTile);
							}
						}, 0);
					} else if (x == 4) {
						player.setNextAnimationNoPriority(new Animation(744), player);
						final WorldTile toTile = new WorldTile(3120, north ? 9964 + x : 9969 - x,
								player.getPlane());
						player.setNextForceMovement(new ForceMovement(toTile, 1,
								north ? ForceMovement.NORTH : ForceMovement.SOUTH));
						WorldTasksManager.schedule(new WorldTask() {

							@Override
							public void run() {
								player.setNextAnimationNoPriority(new Animation(745), player);
								player.setNextWorldTile(toTile);
							}
						}, 0);
					} else if (x == 5) {
						player.setNextAnimationNoPriority(new Animation(744), player);
						final WorldTile toTile = new WorldTile(3120, north ? 9964 + x : 9969 - x,
								player.getPlane());
						player.setNextForceMovement(new ForceMovement(toTile, 1,
								north ? ForceMovement.NORTH : ForceMovement.SOUTH));
						WorldTasksManager.schedule(new WorldTask() {

							@Override
							public void run() {
								player.setNextAnimationNoPriority(new Animation(745), player);
								player.setNextWorldTile(toTile);
							}
						}, 0);
					} else if (x == 6) {
						player.setNextAnimationNoPriority(new Animation(743), player);
						final WorldTile toTile = new WorldTile(3120, north ? 9964 + x : 9969 - x,
								player.getPlane());
						player.setNextForceMovement(new ForceMovement(toTile, 1, ForceMovement.NORTH));
						WorldTasksManager.schedule(new WorldTask() {

							@Override
							public void run() {
								player.setNextWorldTile(toTile);
								player.unlock();
							}
						}, 0);
					}
				}
			}, 1, 1);
		}

		return true;
	}
}
