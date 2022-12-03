package com.rs.game.player.controlers;

import java.util.concurrent.TimeUnit;

import com.rs.cores.CoresManager;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.map.MapBuilder;
import com.rs.game.npc.NPC;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Rain
 * @Date - 1 Mar 2016
 * 
 *       This instance was only for test purposes. It is poorly made, proceed
 *       with caution.
 *
 **/

public class Instance extends Controler {

	@Override
	public void start() {
		startInstance();
	}

	private int[] boundChunks;

	private NPC minion;
	private NPC minion2;
	private NPC minion3;
	private NPC bandos;

	public void startInstance() {
		player.lock();
		CoresManager.slowExecutor.execute(new Runnable() {
			@Override
			public void run() {
				boundChunks = MapBuilder.findEmptyChunkBound(8, 8);
				MapBuilder.copyAllPlanesMap(356, 666, boundChunks[0], boundChunks[1], 8, 8);
				player.setNextWorldTile(getWorldTile(17, 26, 2));
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						player.unlock();
						bandos = new NPC(6260, getWorldTile(20, 33, 2), -1, true);
						minion = new NPC(6261, getWorldTile(18, 33, 2), -1, true);
						minion2 = new NPC(6263, getWorldTile(25, 33, 2), -1, true);
						minion3 = new NPC(6265, getWorldTile(16, 33, 2), -1, true);
						World.addNPC(bandos);
						World.addNPC(minion);
						World.addNPC(minion2);
						World.addNPC(minion3);
						player.setForceMultiArea(true);
						bandos.setForceMultiArea(true);
						minion.setForceMultiArea(true);
						minion2.setForceMultiArea(true);
						minion3.setForceMultiArea(true);
					}
				}, 1);
			}
		});

	}

	public WorldTile getWorldTile(int mapX, int mapY, int plane) {
		return new WorldTile(boundChunks[0] * 8 + mapX, boundChunks[1] * 8 + mapY, plane);
	}

	@Override
	public boolean processJewerlyTeleport(WorldTile toTile) {
		destroyInstance();
		return true;
	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		destroyInstance();
		return true;
	}

	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		destroyInstance();
		return true;
	}

	public void destroyInstance() {
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				player.IsInInstance = false;
				player.setForceMultiArea(false);
				bandos.setForceMultiArea(false);
				minion.setForceMultiArea(false);
				minion2.setForceMultiArea(false);
				minion3.setForceMultiArea(false);
				World.removeNPC(bandos);
				World.removeNPC(minion);
				World.removeNPC(minion2);
				World.removeNPC(minion3);
				removeControler();
				MapBuilder.destroyMap(boundChunks[0], boundChunks[1], 8, 8);
			}
		}, 3500, TimeUnit.MILLISECONDS);
	}

}
