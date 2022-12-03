package com.rs.game.npc.dungeonnering;

import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.WorldTile;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public class WorldGorgerShukarhazh extends DungeonBoss {

	private static final int[][] EYE_COORDINATES =
	{
	{ 0, 7 },
	{ 7, 15 },
	{ 15, 8 } };

	private FamishedEye[] eyes;

	public WorldGorgerShukarhazh(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
		super(id, tile, manager, reference);
		eyes = new FamishedEye[3];
		for (int idx = 0; idx < eyes.length; idx++)
			eyes[idx] = new FamishedEye(this, 12436 + (idx * 15), manager.getTile(reference, EYE_COORDINATES[idx][0], EYE_COORDINATES[idx][1]), manager, getMultiplier() * .25);
		refreshCapDamage();
	}

	@Override
	public void processHit(Hit hit) {
		for (FamishedEye eye : eyes) {
			if (eye.getType() == hit.getLook().getMark()) {
				if (eye.isInactive())
					hit.setDamage((int) (hit.getDamage() * 0.1));
				break;
			}
		}
		super.processHit(hit);
	}

	public void refreshCapDamage() {
		int inactiveCounter = 0;
		for (int index = 0; index < eyes.length; index++) {
			FamishedEye eye = eyes[index];
			boolean inactive = eye.isInactive();
			if (inactive)
				inactiveCounter++;
		}
		setCapDamage(inactiveCounter == 0 ? 50 : inactiveCounter == 1 ? 150 : -1);
	}
	
	private static final WeaponType[][] WEAKNESS =
		{
		{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK) },};

	public WeaponType[][] getWeaknessStyle() {
		return WEAKNESS;
	}

	@Override
	public double getMagePrayerMultiplier() {
		return 0.6;//reg
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 1.0;//prayer dun work m8t
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		for (FamishedEye eye : eyes)
			eye.finish();
	}

	public FamishedEye[] getFamishedEyes() {
		return eyes;
	}
}
