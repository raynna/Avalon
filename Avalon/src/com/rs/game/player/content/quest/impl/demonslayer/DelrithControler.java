package com.rs.game.player.content.quest.impl.demonslayer;

import com.rs.cores.CoresManager;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.map.MapBuilder;
import com.rs.game.npc.quest.Delrith;
import com.rs.game.player.Player;
import com.rs.game.player.controlers.Controler;

public final class DelrithControler extends Controler {

	private Delrith target;
	
	private static int[] boundChuncks;

	public void buildMap() {
		int[] map = new int[] { 402, 420 };
		MapBuilder.copyAllPlanesMap(map[0], map[1], boundChuncks[0], boundChuncks[1], 3);
	}
	
	public static WorldTile getWorldTile(int mapX, int mapY) {
		return new WorldTile(boundChuncks[0] * 8 + mapX, boundChuncks[1] * 8 + mapY, 0);
	}
	
	public void buildInstance(Player player) {
		Runnable event = new Runnable() {
			@Override
			public void run() {
				CoresManager.slowExecutor.execute(new Runnable() {
					@Override
					public void run() {
						if (boundChuncks == null) {
							boundChuncks = MapBuilder.findEmptyChunkBound(3, 3);
							buildMap();
						} else {
							buildMap();
						}
						//spawn demon in demon slayer
						sendTarget(879, new WorldTile(getWorldTile(13, 13).getX(), getWorldTile(7, 7).getY(), 0));
						player.setNextWorldTile(new WorldTile(getWorldTile(10, 10).getX(), getWorldTile(7, 7).getY(), 0));
						//World.spawnNPC(879, new WorldTile(getWorldTile(13, 13).getX(), getWorldTile(7, 7).getY(), 0), -1, true, true, player);
						//System.out.println(getWorldTile(0, 0).getX() + " " + getWorldTile(0, 0).getY());
					}
				});

			}
		};
		event.run();
	}

	@Override
	public boolean canAttack(Entity target) {
		if (target instanceof Delrith && target != this.target) {
			player.getPackets().sendGameMessage("This isn't your target.");
			return false;
		}
		return true;
	}

	private void leave(boolean logout) {
		if (target != null)
			target.finish();
		if (!logout) {
			if (player.getHiddenBrother() != -1)
				player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0);
			player.getPackets().sendBlackOut(0);
			removeControler();
		}
	}

	@Override
	public boolean sendDeath() {
		leave(false);
		return true;
	}

	@Override
	public void magicTeleported(int type) {
		leave(false);
	}

	@Override
	public boolean processObjectClick1(WorldObject object) {
		return true;
	};

	public void targetDied() {
		target = null;

	}

	public void targetFinishedWithoutDie() {
		player.getHintIconsManager().removeUnsavedHintIcon();
		target = null;
	}

	public void sendTarget(int id, WorldTile tile) {
		if (target != null)
			target.disapear();
		target = new Delrith(id, tile, this);
		target.setTarget(player);
		target.setNextForceTalk(new ForceTalk("You dare disturb my rest!"));
		// player.getHintIconsManager().addHintIcon(target, 1, -1, false);
	}

	public DelrithControler() {

	}
	

	@Override
	public void process() {
	}

	public void resetHeadTimer() {
	}

	@Override
	public void sendInterfaces() {
	}

	@Override
	public void start() {
		sendInterfaces();
		buildInstance(player);
	}

	@Override
	public boolean login() {
		sendInterfaces();
		return false;
	}

	@Override
	public boolean logout() {
		leave(true);
		return false;
	}

	@Override
	public void forceClose() {
		leave(true);
	}

}
