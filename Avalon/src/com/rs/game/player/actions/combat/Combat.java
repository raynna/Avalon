package com.rs.game.player.actions.combat;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public final class Combat {
	
	public static final int MELEE_TYPE = 0, RANGE_TYPE = 1, MAGIC_TYPE = 2, ALL_TYPE = 3;
	public static final int NONE_STYLE = 0, ARROW_STYLE = 8, BOLT_STYLE = 9, THROWN_STYLE = 10;

	public static boolean rollHit(double att, double def) {
		if (att < 0) // wont happen unless low att lv plus negative bonus
			return false;
		if (def < 0) // wont happen unless low def lv plus negative bonus
			return true;
		
		double prob = att / def;
		double random = Utils.getRandomDouble(100);
		if (att <= def) {
			prob = (att - 1) / (def * 2);
		} else if (att > def) {
			prob = 1 - (def + 1) / (att * 2);
		}
		if (prob < random / 100) {
			return false;
		}
		return true;
	}

	public static boolean hasAntiDragProtection(Entity target) {
		if (target instanceof NPC)
			return false;
		Player p2 = (Player) target;
		if (p2.getAntifire() > Utils.currentTimeMillis())
			return true;
		if (p2.getSuperAntifire() > Utils.currentTimeMillis())
			return true;
		int shieldId = p2.getEquipment().getShieldId();
		return shieldId == 1540 || shieldId == 11283 || shieldId == 11284;
	}

	public static int getSlayerLevelForNPC(int id) {
		switch (id) {
		case 9463:
			return 93;
		default:
			return 0;
		}
	}

	public static int getDefenceEmote(Entity target) {
		if (target instanceof NPC) {
			NPC n = (NPC) target;
			return n.getCombatDefinitions().getDefenceEmote();
		} else {
			Player p = (Player) target;
			int shieldId = p.getEquipment().getShieldId();
			String shieldName = shieldId == -1 ? null
					: ItemDefinitions.getItemDefinitions(shieldId).getName().toLowerCase();
			if (shieldId == -1 || (shieldName.contains("book")
					|| (shieldName.contains("tome of frost") || (shieldName.contains("void knight def"))))) {
				int weaponId = p.getEquipment().getWeaponId();
				if (weaponId == -1)
					return 424;
				if (weaponId == 18353)
					return 13054;
				if (weaponId == 15486)
					return 12806;
				String weaponName = ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase();
				if (weaponName != null && !weaponName.equals("null")) {
					if (weaponName.contains("scimitar") || weaponName.contains("korasi sword"))
						return 15074;
					if (weaponName.contains("whip"))
						return 11974;
					if (weaponName.contains("hand cannon"))
						return 12156;
					if (weaponName.contains("staff of light"))
						return 12806;
					if (weaponId == 11736)
						return 415;
					if (weaponName.contains("longsword") || weaponName.contains("darklight")
							|| weaponName.contains("silverlight") || weaponName.contains("excalibur"))
						return 388;
					if (weaponName.contains("dagger"))
						return 378;
					if (weaponName.contains("rapier"))
						return 13038;
					if (weaponName.contains("pickaxe"))
						return 397;
					if (weaponName.contains("mace"))
						return 403;
					if (weaponName.contains("claws"))
						return 430;
					if (weaponName.contains("hatchet"))
						return 397;
					if (weaponName.contains("greataxe"))
						return 12004;
					if (weaponName.contains("wand"))
						return 415;
					if (weaponName.contains("chaotic staff"))
						return 13046;
					if (weaponName.contains("staff"))
						return 420;
					if (weaponName.contains("anchor"))
						return 5866;
					if (weaponName.contains("granite maul") || weaponName.contains("granite_maul"))
						return 1666;
					if (weaponName.contains("maul"))
						return 13054;
					if (weaponName.contains("warhammer") || weaponName.contains("tzhaar-ket-em"))
						return 403;
					if (weaponName.contains("tzhaar-ket-om"))
						return 1666;
					if (weaponName.contains("zamorakian spear") || weaponName.contains("scythe"))
						return 12008;
					if (weaponName.contains("spear") || weaponName.contains("halberd") || weaponName.contains("hasta"))
						return 430;
					if (weaponId == 13290)
						return 4177;
					if (weaponName.contains("2h sword") || weaponName.contains("godsword")
							|| weaponName.equals("saradomin sword"))
						return 7050;
				}
				return 424;
			}
			if (shieldName != null) {
				if (shieldName.contains("shield"))
					return 1156;
				if (shieldName.contains("toktz-ket-xil"))
					return 1156;
				if (shieldName.contains("defender"))
					return 4177;
			}
			switch (shieldId) {
			case -1:
			default:
				return 424;
			}
		}
	}

	private Combat() {
	}
}
