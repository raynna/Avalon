package com.rs.game.npc.dungeonnering;

import java.util.ArrayList;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.actions.combat.PlayerCombat;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.utils.Utils;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public class DungeonSlayerNPC extends DungeonNPC {

	public DungeonSlayerNPC(int id, WorldTile tile, DungeonManager manager, double multiplier) {
		super(id, tile, manager, multiplier);
	}

	@Override
	public void drop() {
		super.drop();
		int size = getSize();
		ArrayList<Item> drops = new ArrayList<Item>();
		if (getId() == 10694) {
			if (Utils.random(2) == 0)
				drops.add(new Item(17261));
			else if (Utils.random(10) == 0)
				drops.add(new Item(17263));
		} else if (getId() == 10695) {
			if (Utils.random(2) == 0)
				drops.add(new Item(17265));
			else if (Utils.random(10) == 0)
				drops.add(new Item(17267));
		} else if (getId() == 10696) {
			if (Utils.random(2) == 0)
				drops.add(new Item(17269));
			else if (Utils.random(10) == 0)
				drops.add(new Item(17271));
		} else if (getId() == 10697) {
			if (Utils.random(2) == 0)
				drops.add(new Item(17273));
		} else if (getId() == 10698) {
			if (Utils.random(10) == 0)
				drops.add(new Item(17279));
		} else if (getId() == 10699) {
			if (Utils.random(2) == 0)
				drops.add(new Item(17281));
			else if (Utils.random(10) == 0)
				drops.add(new Item(17283));
		} else if (getId() == 10700) {
			if (Utils.random(2) == 0)
				drops.add(new Item(17285));
			else if (Utils.random(10) == 0)
				drops.add(new Item(17287));
		} else if (getId() == 10701) {
			if (Utils.random(10) == 0)
				drops.add(new Item(17289));
		} else if (getId() == 10702) {
			if (Utils.random(10) == 0)
				drops.add(new Item(17293));
		} else if (getId() == 10704) {
			if (Utils.random(10) == 0)
				drops.add(new Item(17291));
		} else if (getId() == 10705) {
			if (Utils.random(10) == 0)
				drops.add(new Item(17295));
		}

		for (Item item : drops)
			World.addGroundItem(item, new WorldTile(getCoordFaceX(size), getCoordFaceY(size), getPlane()));
	}

	private static final WeaponType[][][] WEAKNESSES = {
		{ { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.CRUSH_ATTACK) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK) }, { new WeaponType(Combat.MAGIC_TYPE, PlayerCombat.FIRE_SPELL) } },
		{ { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.CRUSH_ATTACK) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK) }, },
		{ { new WeaponType(Combat.MAGIC_TYPE, -1) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.CRUSH_ATTACK) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK) } },
		{ { new WeaponType(Combat.MAGIC_TYPE, -1) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK) } },
		{ { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.CRUSH_ATTACK) }, { new WeaponType(Combat.MAGIC_TYPE, PlayerCombat.FIRE_SPELL) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK) } },
		{ { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.CRUSH_ATTACK) }, { new WeaponType(Combat.MAGIC_TYPE, -1) } },
		{ { new WeaponType(Combat.MAGIC_TYPE, -1) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.CRUSH_ATTACK) }, },
		{ { new WeaponType(Combat.RANGE_TYPE, -1) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.CRUSH_ATTACK) } },
		{ { new WeaponType(Combat.MAGIC_TYPE, -1) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK) }, { new WeaponType(Combat.RANGE_TYPE, -1) } },
		{ { new WeaponType(Combat.RANGE_TYPE, -1) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK) } },
		{ { new WeaponType(Combat.RANGE_TYPE, -1) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK) }, { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.CRUSH_ATTACK) } },
	};

	@Override
	public WeaponType[][] getWeaknessStyle() {
		if (getId() == 10694)
			return WEAKNESSES[0];
		else if (getId() == 10695)
			return WEAKNESSES[1];
		else if (getId() == 10696)
			return WEAKNESSES[2];
		else if (getId() == 10697)
			return WEAKNESSES[3];
		else if (getId() == 10698)
			return WEAKNESSES[4];
		else if (getId() == 10699)
			return WEAKNESSES[5];
		else if (getId() == 10700)
			return WEAKNESSES[6];
		else if (getId() == 10701)
			return WEAKNESSES[7];
		else if (getId() == 10702)
			return WEAKNESSES[8];
		else if (getId() == 10704)
			return WEAKNESSES[9];
		else if (getId() == 10705)
			return WEAKNESSES[10];
		return null;
	}
}
