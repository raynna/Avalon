package com.rs.game.npc.dungeonnering;

import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;
import com.rs.utils.WeaponTypesLoader.WeaponType;

public class Blink extends DungeonBoss {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1772599559290197609L;
	private static final int[][] RUSH_COORDINATES =
	{
	{ 2, 3, 13, 3 },
	{ 2, 6, 13, 6 },
	{ 2, 9, 13, 9 },
	{ 2, 12, 13, 12 },
	{ 3, 2, 3, 13 },
	{ 6, 2, 6, 13 },
	{ 9, 2, 9, 13 },
	{ 12, 2, 12, 13 }, };
	private static final int[] FAILURE_SOUNDS = new int[]
	{ 3005, 3006, 3010, 3014, 3048, 2978 };
	private static final int[] RUSH_SOUNDS =
	{ 2982, 2987, 2988, 2989, 2990, 2992, 2998, 3002, 3004, 3009, 3015, 3017, 3018, 3021, 3026, 3027, 3031, 3042, 3043, 3047, 3049 };
	private static final String[] RUSH_MESSAGES =
	{
		"Grrrr...",
		"More t...tea Alice?",
		"Where...who?",
		"H..here it comes!",
		"See you all next year!",
		"",
		"",
		"",
		"Coo-coo-ca-choo!",
		"Ah! Grrrr...",
		"Aha! Huh? Ahaha!",
		"",
		"",
		"A face! A huuuge face!",
		"Aaahaahaha!",
		"C...can't catch me!",
		"A whole new world!",
		"Over here!",
		"There's no place like home.",
		"The...spire...doors...everywhere..." };

	private int rushCount, rushStage;
	private int[] selectedPath;
	private boolean inversedPath, specialRequired;
	private WorldTile toPath, activePillar;

	public Blink(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
		super(id, tile, manager, reference);
		setForceFollowClose(true);
		setRun(true);
		rushCount = 0;
		rushStage = 4;
	}

	@Override
	public void processHit(Hit hit) {
		super.processHit(hit);
		if (getHitpoints() <= getMaxHitpoints() * (rushStage * .2125)) {
			rushStage--;
			rushCount = 0;
		}
	}
	
	private static final WeaponType[][] WEAKNESS =
		{{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK) },};

	public WeaponType[][] getWeaknessStyle() {
		return WEAKNESS;
	}

	@Override
	public double getMagePrayerMultiplier() {
		return 1.0;
	}

	@Override
	public double getRangePrayerMultiplier() {
		return 0.6;
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 0.8;
	}

	@SuppressWarnings("unused")
	private void stopRushAttack() {
		rushCount = -1;//stops the rush
		playSound(FAILURE_SOUNDS[Utils.random(FAILURE_SOUNDS.length)], 2);
		setNextForceTalk(new ForceTalk("Oof!"));
		animate(new Animation(14946));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				setSpecialRequired(true);
				setCantInteract(false);
			}
		});
	}

	@Override
	public void processNPC() {
		super.processNPC();

		if (rushCount > -1) {
			if (getManager().isDestroyed() || isDead())
				return;
			rushCount++;
			if (rushCount == 1) {
				resetWalkSteps();
				setNextFaceEntity(null);
				resetCombat();
				setCantInteract(true);
			} else if (rushCount == 3) {
				setNextForceTalk(new ForceTalk("He saw me!"));
				playSound(3017, 2);
			} else if (rushCount == 4) {
				animate(new Animation(14994));
				gfx(new Graphics(2868));
			} else if (rushCount == 15 || rushCount == 5) {
				if (rushCount == 15)
					rushCount = 5;
				setNextNPCTransformation(1957);
			} else if (rushCount == 8) {
				setNextWorldTile(getNextPath());
			} else if (rushCount == 9) {
				setNextNPCTransformation(12865);
				toPath = getManager().getTile(getReference(), selectedPath[inversedPath ? 2 : 0], selectedPath[inversedPath ? 3 : 1]);
				addWalkSteps(toPath.getX(), toPath.getY(), 1, false);
			} else if (rushCount == 10) {
				addWalkSteps(toPath.getX(), toPath.getY(), -1, false);
				int index = Utils.random(RUSH_MESSAGES.length);
				setNextForceTalk(new ForceTalk(RUSH_MESSAGES[index]));
				playSound(RUSH_SOUNDS[index], 2);
			} else if (rushCount == 11) {
				gfx(new Graphics(2869));
				for (Player player : getManager().getParty().getTeam()) {
					if(!getManager().getCurrentRoomReference(this).equals(getManager().getCurrentRoomReference(player))) {
						continue;
					}
					if (!Utils.isOnRange(player.getX(), player.getY(), 1, getX(), getY(), 1, 4))
						continue;
					int damage = Utils.random(200, 600);
					if (player.getMagePrayerMultiplier() == 0.6 || player.getRangePrayerMultiplier() == 0.6)
						damage *= .5D;
					player.gfx(new Graphics(2854));
					player.applyHit(new Hit(this, damage, HitLook.REGULAR_DAMAGE, 35));

				}
			}
		}
	}

	public WorldTile getNextPath() {
		selectedPath = RUSH_COORDINATES[Utils.random(RUSH_COORDINATES.length)];
		inversedPath = Utils.random(2) == 0;
		return getManager().getTile(getReference(), selectedPath[inversedPath ? 0 : 2], selectedPath[inversedPath ? 1 : 3]);
	}

	public void raisePillar(WorldObject selectedPillar) {
		final WorldObject newPillar = new WorldObject(selectedPillar);
		newPillar.setId(32196);//Our little secret :D
		activePillar = new WorldTile(selectedPillar);
		World.spawnObjectTemporary(newPillar, 2500);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				activePillar = null;
			}
		}, 4);
	}

	public boolean hasActivePillar() {
		return activePillar != null;
	}

	public boolean isSpecialRequired() {
		return specialRequired;
	}

	public void setSpecialRequired(boolean specialRequired) {
		this.specialRequired = specialRequired;
	}
}
