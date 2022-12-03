package com.rs.game.npc.dungeonnering;

import com.rs.game.Hit;
import com.rs.game.WorldTile;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public class WarpedGulega extends DungeonBoss {

	public WarpedGulega(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
		super(id, tile, manager, reference);
	}

	//thats default lol
	/* @Override
	 public double getMeleePrayerMultiplier() {
	return 0.0;//Fully block it.
	 }
	 
	 @Override
	 public double getRangePrayerMultiplier() {
	return 0.0;//Fully block it.
	 }
	 
	 @Override
	 public double getMagePrayerMultiplier() {
	return 0.0;//Fully block it.
	 }*/
	
	private static final WeaponType[][] WEAKNESS =
		{{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK)}};

	public WeaponType[][] getWeaknessStyle() {
		return WEAKNESS;
	}

	@Override
	public void processHit(Hit hit) {
		if (!(hit.getSource() instanceof Familiar))
			hit.setDamage((int) (hit.getDamage() * 0.45D));
		super.processHit(hit);
	}
}
