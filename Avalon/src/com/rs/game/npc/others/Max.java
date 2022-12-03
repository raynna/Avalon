package com.rs.game.npc.others;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.RouteEvent;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class Max extends NPC {

	/**
	 * @author -Andreas
	 */

	private int COOL_DOWN_TICKS = 10;// 50 original
	private int ACTIVITY_COUNT = 10;// 10 original
	private int ACTIVITY_DELAY = 5;// 5 original

	private final Animation FLETCHING_ANIMATION = new Animation(6702);
	private final Animation STEAL_ANIMATION = new Animation(881);
	private final Animation WOODCUTTING_ANIMATION = new Animation(2846);
	private final Animation ANVIL_ANIMATION = new Animation(898);

	private int stage = -1;
	private int count = 0;
	private int skillCount;

	public Max(int id, WorldTile tile, boolean spawned) {
		super(id, tile, -1, true, true);
	}

	private int[] stages = new int[] { 0, 4, 6, 8, 50 };

	private int getRandomStage() {
		int randomStage = stages[Utils.getRandom(stages.length - 1)];
		while (true) {
			if (randomStage == (stage - 1)) {
				randomStage = stages[Utils.getRandom(stages.length - 1)];
			} else {
				return randomStage;
			}
		}
	}

	private boolean finishedActivity(int count) {
		if (skillCount >= count) {
			skillCount = 0;
			setStage(getRandomStage());
			return true;
		}

		return false;
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (isDead())
			return;
		count++;
		switch (getStage()) {
		case -1: // null
			faceWorldTile(this, "north");
			if (count >= COOL_DOWN_TICKS)
				setStage(getRandomStage());
			break;

		case 1: // activities
		case 5:
		case 7:
		case 9:
			if (count % ACTIVITY_DELAY == 0 && !finishedActivity(ACTIVITY_COUNT)) {
				animate(getActivityAnimation(stage));
				if (getActivityFace(getStage()) != null)
					faceWorldTile(Max.this, getActivityFace(getStage()));
				skillCount++;
			}
			break;
		case 0:// walk
		case 4:
		case 6:
		case 8:
		case 50:
			WorldObject object = getActivityObject(stage);
			setRouteEvent(new RouteEvent(object, new Runnable() {
				@Override
				public void run() {
					if (stage == 50) {
						addWalkSteps(spawn.getX(), spawn.getY());
						reset();
						return;
					}
					setStage(getStage() + 1);
					faceObject(object);
				}
			}, true));
			break;
		}
	}

	public void reset() {
		faceWorldTile(this, "north");
		count = 0;
		setStage(-1);
		skillCount = 0;
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	private WorldTile BANK_TILE = (new WorldTile(3095, 3491, 0));
	WorldObject bank = new WorldObject(42217, 0, 1, new WorldTile(BANK_TILE));

	private WorldTile SPAWN_TILE = (new WorldTile(3089, 3483, 0));
	WorldObject spawn = new WorldObject(-1, 0, 1, new WorldTile(SPAWN_TILE));

	private WorldTile STALL_TILE = (new WorldTile(3099, 3505, 0));
	WorldObject stall = new WorldObject(34385, 0, 1, new WorldTile(STALL_TILE));

	private WorldTile TREE_TILE = (new WorldTile(3104, 3494, 0));
	WorldObject tree = new WorldObject(38786, 0, 1, new WorldTile(TREE_TILE));

	private WorldTile ANVIL_TILE = (new WorldTile(3107, 3497, 0));
	WorldObject anvil = new WorldObject(2783, 10, 0, new WorldTile(ANVIL_TILE));

	private Animation getActivityAnimation(int stage) {
		switch (stage) {
		case 0:
		case 1:
			return FLETCHING_ANIMATION;
		case 4:
		case 5:
			return STEAL_ANIMATION;
		case 6:
		case 7:
			return WOODCUTTING_ANIMATION;
		case 8:
		case 9:
			return ANVIL_ANIMATION;
		}
		return null;
	}

	private WorldObject getActivityObject(int stage) {
		switch (stage) {
		case 0:
		case 1:
			return bank;
		case 4:
		case 5:
			return stall;
		case 6:
		case 7:
			return tree;
		case 8:
		case 9:
			return anvil;
		case 50:
			return spawn;
		}
		return null;
	}

	@SuppressWarnings("unused")
	private String getActivityName(int stage) {
		switch (stage) {
		case 0:
		case 1:
			return "Fletching";
		case 2:
		case 3:
			return "Fight Guard";
		case 4:
		case 5:
			return "Thieving";
		case 6:
		case 7:
			return "Woodcutting";
		case 8:
		case 9:
			return "Smithing";
		}
		return "Walk to Spawn";
	}

	private String getActivityFace(int stage) {
		switch (stage) {
		case 0:
		case 1:
		case 6:
		case 7:
			return "east";
		case 4:
		case 5:
			return "west";
		case 8:
		case 9:
			return "south";
		}
		return "north";
	}
}
