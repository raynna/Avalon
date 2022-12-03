package com.rs.game.player.actions;

import com.rs.game.Entity;
import com.rs.game.player.Player;
import com.rs.game.route.RouteFinder;
import com.rs.game.route.strategy.EntityStrategy;
import com.rs.utils.Utils;

public class PlayerFollow extends Action {

	private transient Player target;
	private transient Entity npcTarget;

	public PlayerFollow(Player target) {
		this.target = target;
	}

	public PlayerFollow(Entity npcTarget) {
		this.npcTarget = npcTarget;
	}

	@Override
	public boolean start(Player player) {
		player.setNextFaceEntity(target);
		if (checkAll(player))
			return true;
		player.setNextFaceEntity(null);
		return false;
	}

	private boolean checkAll(Player player) {
		if (npcTarget != null) {
			if (player.isDead() || player.hasFinished() || npcTarget.isDead() || npcTarget.hasFinished())
				return false;
			if (player.getPlane() != npcTarget.getPlane())
				return false;
			int distanceX = player.getX() - npcTarget.getX();
			int distanceY = player.getY() - npcTarget.getY();
			int size = player.getSize();
			int maxDistance = 16;
			if (player.getPlane() != npcTarget.getPlane() || distanceX > size + maxDistance
					|| distanceX < -1 - maxDistance || distanceY > size + maxDistance || distanceY < -1 - maxDistance)
				return false;
			player.setNextFaceEntity(npcTarget);
			if (!player.clipedProjectile(npcTarget, true) || !Utils.isOnRange(player.getX(), player.getY(), size,
					npcTarget.getX(), npcTarget.getY(), npcTarget.getSize(), 0)) {
				int steps = RouteFinder.findRoute(RouteFinder.WALK_ROUTEFINDER, player.getX(), player.getY(),
						player.getPlane(), player.getSize(), new EntityStrategy(npcTarget), true);
				if (steps == -1)
					return false;

				if (steps > 0) {
					player.resetWalkSteps();
					int[] bufferX = RouteFinder.getLastPathBufferX();
					int[] bufferY = RouteFinder.getLastPathBufferY();
					for (int step = steps - 1; step >= 0; step--) {
						if (!player.addWalkSteps(bufferX[step], bufferY[step], 25, true))
							break;
					}
				}
				return true;
			}
			return true;
		} else {
			if (player.isDead() || player.hasFinished() || target.isDead() || target.hasFinished())
				return false;
			if (player.getPlane() != target.getPlane())
				return false;

			int distanceX = player.getX() - target.getX();
			int distanceY = player.getY() - target.getY();
			int size = player.getSize();
			int maxDistance = 16;
			if (player.getPlane() != target.getPlane() || distanceX > size + maxDistance || distanceX < -1 - maxDistance
					|| distanceY > size + maxDistance || distanceY < -1 - maxDistance)
				return false;
			if (!player.clipedProjectile(target, true) || !Utils.isOnRange(player.getX(), player.getY(), size,
					target.getX(), target.getY(), target.getSize(), 0)) {
				int steps = RouteFinder.findRoute(RouteFinder.WALK_ROUTEFINDER, player.getX(), player.getY(),
						player.getPlane(), player.getSize(), new EntityStrategy(target), true);
				if (steps == -1)
					return false;

				if (steps > 0) {
					player.resetWalkSteps();

					int[] bufferX = RouteFinder.getLastPathBufferX();
					int[] bufferY = RouteFinder.getLastPathBufferY();
					for (int step = steps - 1; step >= 0; step--) {
						if (!player.addWalkSteps(bufferX[step], bufferY[step], 25, true))
							break;
					}
				}
				return true;
			}
			return true;
		}
	}

	@Override
	public boolean process(Player player) {
		return checkAll(player);
	}

	@Override
	public int processWithDelay(Player player) {
		return 0;
	}

	@Override
	public void stop(final Player player) {
		player.setNextFaceEntity(null);
	}
}
