package com.rs.game.player.content.dungeoneering.rooms.puzzles;

import java.util.HashSet;
import java.util.Set;

import com.rs.game.Animation;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.player.Player;
import com.rs.game.player.content.dungeoneering.rooms.PuzzleRoom;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class FlipTilesRoom extends PuzzleRoom {

	private static final int BASE_TILE = 49637;
	private static final int YELLOW_TO_GREEN = 13781;
	private static final int GREEN_TO_YELLOW = 13782;
	private static final int GREEN = 49638;
	private static final int YELLOW = 49642;
	private WorldObject[][] tiles;
	private int xOffset;
	private int yOffset;

	@Override
	public void openRoom() {
		manager.spawnRandomNPCS(reference);
		tiles = new WorldObject[5][5];
		outer: for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				WorldObject object = manager.getObjectWithType(reference, 22, x, y);
				if (object != null && object.getId() == BASE_TILE) {
					xOffset = x;
					yOffset = y;
					break outer;
				}
			}
		}
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				//Apparently not every configuration is solveable but eh, thats what the force option is for!
				tiles[x][y] = new WorldObject(Math.random() > 0.5 ? GREEN : YELLOW, 22, 0, manager.getTile(reference, x + xOffset, y + yOffset));
				World.spawnObject(tiles[x][y]);
			}
		}
	}

	@Override
	public boolean processObjectClick1(Player p, WorldObject object) {
		String name = object.getDefinitions().name;
		if (name.equals("Green tile") || name.equals("Yellow tile")) {
			p.lock(2);
			p.animate(new Animation(7660));
			int[] pos = manager.getRoomPos(object);
			Set<WorldObject> objects = getAdjacent(pos[0] - xOffset, pos[1] - yOffset);
			for (WorldObject tile : objects) {
				flipTile(tile);
			}
			checkComplete();
			return false;
		}

		return true;
	}

	@Override
	public boolean processObjectClick2(Player p, WorldObject object) {
		String name = object.getDefinitions().name;
		if (name.equals("Green tile") || name.equals("Yellow tile")) {
			p.getPackets().sendGameMessage("You force the tile without changing adjacent tiles, and the released energy harms you.");
			p.applyHit(new Hit(p, (int) (p.getMaxHitpoints() * .2), HitLook.REGULAR_DAMAGE));
			p.lock(2);
			p.animate(new Animation(13695));
			int[] pos = manager.getRoomPos(object);
			flipTile(tiles[pos[0] - xOffset][pos[1] - yOffset]);
			checkComplete();
			return false;
		}
		return true;
	}

	public void flipTile(final WorldObject tile) {
		final int id = tile.getId();
		tile.setId(id == GREEN ? YELLOW : GREEN); //instantly update so 2 players pressing the same tiles at once will not bug it out, although visual may be weird, rs might lock the whole puzzle up for 1 sec, not sure tho
		for (Player team : manager.getParty().getTeam()) {
			team.getPackets().sendObjectAnimation(tile, new Animation(id == GREEN ? GREEN_TO_YELLOW : YELLOW_TO_GREEN));
		}
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				World.spawnObject(new WorldObject(id == GREEN ? YELLOW : GREEN, 22, 0, tile));
			}
		}, 1);
	}

	@Override
	public String getCompleteMessage() {
		return "You hear a click as the last tile flips. All the doors in the room are now unlocked.";
	}

	private void checkComplete() {
		if (isComplete())
			return; //You can still flip tiles after puzzle is complete, but don't do any checks
		int first = tiles[0][0].getId();
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				if (first != tiles[x][y].getId()) {
					return;
				}
			}
		}
		setComplete();
	}

	private Set<WorldObject> getAdjacent(int x, int y) {
		Set<WorldObject> set = new HashSet<WorldObject>();

		set.add(tiles[x][y]);
		if (x > 0)
			set.add(tiles[x - 1][y]);
		if (x < 4)
			set.add(tiles[x + 1][y]);
		if (y < 4)
			set.add(tiles[x][y + 1]);
		if (y > 0)
			set.add(tiles[x][y - 1]);

		return set;
	}

}
