package com.rs.game.npc.dungeonnering;

import com.rs.game.WorldTile;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public class PlaneFreezerLakhrahnaz extends DungeonBoss {

	public PlaneFreezerLakhrahnaz(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
		super(id, tile, manager, reference);
		//TODO setCantSetTargetAutoRelatio(true);
	}
	
	private static final WeaponType[][] WEAKNESS =
		{{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK) }};

	public WeaponType[][] getWeaknessStyle() {
		return WEAKNESS;
	}
}
