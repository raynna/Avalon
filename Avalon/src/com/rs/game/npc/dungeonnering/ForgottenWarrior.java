package com.rs.game.npc.dungeonnering;

import java.util.ArrayList;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.content.dungeoneering.DungeonConstants;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.DungeonUtils;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.utils.Utils;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public class ForgottenWarrior extends Guardian {

	public ForgottenWarrior(int id, WorldTile tile, DungeonManager manager, RoomReference reference, double multiplier) {
		super(id, tile, manager, reference, multiplier);
	}

	@Override
	public void drop() {
		super.drop();
		int size = getSize();
		ArrayList<Item> drops = new ArrayList<Item>();
		//just 1 for now
		for (int type = 0; type < DungeonConstants.FORGOTTEN_WARRIORS.length; type++) {
			for (int id : DungeonConstants.FORGOTTEN_WARRIORS[type])
				if (id == getId()) {
					int tier = getManager().getParty().getAverageCombatLevel() / 10;
					if (tier > 10)
						tier = 10;
					else if (tier < 1)
						tier = 1;
					//melee mage range
					if (type == 0)
						drops.add(new Item(DungeonUtils.getRandomMeleeGear(Utils.random(tier) + 1)));
					else if (type == 1)
						drops.add(new Item(DungeonUtils.getRandomMagicGear(Utils.random(tier) + 1)));
					else
						drops.add(new Item(DungeonUtils.getRandomRangeGear(Utils.random(tier) + 1)));
					break;
				}
		}
		for (Item item : drops)
			World.addGroundItem(item, new WorldTile(getCoordFaceX(size), getCoordFaceY(size), getPlane()));
	}
	
	@Override
	public int getShadowType() {
		for (int type = 0; type < DungeonConstants.FORGOTTEN_WARRIORS.length; type++)
			for (int id : DungeonConstants.FORGOTTEN_WARRIORS[type])
				if (id == getId() && type == 1)
					return DungeonConstants.VISIBLE_AND_REMOVE;
		return DungeonConstants.NOT_VISIBLE;
	}

	private static final WeaponType[][][] WEAKNESSES =
	{
	{
	{ new WeaponType(Combat.MAGIC_TYPE, -1) },
	{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.CRUSH_ATTACK), new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK) } },

	{
	{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK) },
	{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK) } },

	{
	{ new WeaponType(Combat.RANGE_TYPE, -1) },
	{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK) },
	{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK) } },

	};

	@Override
	public WeaponType[][] getWeaknessStyle() {
		for (int type = 0; type < DungeonConstants.FORGOTTEN_WARRIORS.length; type++) {
			for (int id : DungeonConstants.FORGOTTEN_WARRIORS[type]) {
				if (id == getId())
					return WEAKNESSES[type];
			}
		}
		return null;
	}
}
