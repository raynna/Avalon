package com.rs.game.player.actions.combat;

import java.util.ArrayList;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.minigames.clanwars.FfaZone;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.modernspells.ChargeOrb;
import com.rs.game.player.actions.combat.modernspells.Alchemy;
import com.rs.game.player.actions.combat.modernspells.BonesTo;
import com.rs.game.player.actions.combat.modernspells.Charge;
import com.rs.game.player.actions.combat.modernspells.SuperHeat;
import com.rs.game.player.actions.skills.crafting.Enchanting;
import com.rs.game.player.controlers.CrucibleControler;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class ModernMagicks {

	/**
	 * author -Andreas
	 * 2018-06-21
	 */

	public static final int SPELLBOOK_ID = 192;

	public static final int AIR = 556, WATER = 555, EARTH = 557, FIRE = 554, MIND = 558, NATURE = 561, CHAOS = 562,
			DEATH = 560, BLOOD = 565, SOUL = 566, LAW = 563, ARMADYL = 21773, BODY = 559, BANANA = 1963,
			COSMIC = 564;

	public static final int INVENTORY = 0, NPC = 1, ON_PLAYER = 2, OBJECT = 3, ONE_CLICK = 4, COMBAT = 5, FLOOR = 6;

	public static final int RUNE_POUCH = 24497;

	public static final int MUD = 4698, MIST = 4695, DUST = 4696, LAVA = 4699, STEAM = 4694, SMOKE = 4697;

    public static boolean infiniteFire(Player player) {
		String weapon = ItemDefinitions.getItemDefinitions(player.getEquipment().getWeaponId()).getName().toLowerCase();
		String shield = ItemDefinitions.getItemDefinitions(player.getEquipment().getShieldId()).getName().toLowerCase();
		return weapon.contains("fire") || weapon.contains("lava") || weapon.contains("steam")
				|| weapon.contains("smoke") || shield.contains("tome of fire");
	}

	public static boolean infiniteEarth(Player player) {
		String weapon = ItemDefinitions.getItemDefinitions(player.getEquipment().getWeaponId()).getName().toLowerCase();
		return weapon.contains("earth") || weapon.contains("lava") || weapon.contains("mud") || weapon.contains("dust");
	}

	public static boolean infiniteWater(Player player) {
		String weapon = ItemDefinitions.getItemDefinitions(player.getEquipment().getWeaponId()).getName().toLowerCase();
		String shield = ItemDefinitions.getItemDefinitions(player.getEquipment().getShieldId()).getName().toLowerCase();
		return weapon.contains("water") || weapon.contains("mud") || weapon.contains("steam") || weapon.contains("mist")
				|| shield.contains("tome of frost");
	}

	public static boolean infiniteAir(Player player) {
		String weapon = ItemDefinitions.getItemDefinitions(player.getEquipment().getWeaponId()).getName().toLowerCase();
		return weapon.contains("air") || weapon.contains("mist") || weapon.contains("smoke") || weapon.contains("dust")
				|| player.getEquipment().getWeaponId() == 21777;
	}

	public enum RSSpellStore {

		// NORMAL SPELLBOOK
		HOME_TELEPORT(24, 0, 0, new WorldTile(3087, 3496, 0), null, ONE_CLICK),

		WIND_RUSH(98, 1, new Item[] { new Item(AIR, 2) }, COMBAT),

		WIND_STRIKE(25, 1, new Item[] { new Item(AIR, 1), new Item(MIND, 1) }, COMBAT),

		CONFUSE(26, 3, new Item[] { new Item(WATER, 3), new Item(EARTH, 2), new Item(BODY, 1) }, COMBAT),

		ENCHANT_BOLT(27, 4, new Item[] {}, ONE_CLICK),

		WATER_STRIKE(28, 5, new Item[] { new Item(WATER, 1), new Item(AIR, 1), new Item(BODY, 1) }, COMBAT),

		ENCHANT1(29, 7, new Item[] { new Item(WATER, 1), new Item(COSMIC, 1) }, INVENTORY),

		EARTH_STRIKE(30, 9, new Item[] { new Item(EARTH, 2), new Item(AIR, 1), new Item(MIND, 1) }, COMBAT),

		MOBILISING_TELEPORT(37, 10, 19, new WorldTile(2413, 2848, 0), new Item[] { new Item(LAW, 1), new Item(WATER, 1), new Item(AIR, 1) }, ONE_CLICK),

		WEAKEN(31, 11, new Item[] { new Item(WATER, 3), new Item(EARTH, 2), new Item(BODY, 1) }, COMBAT),

		FIRE_STRIKE(32, 13, new Item[] { new Item(FIRE, 3), new Item(AIR, 2), new Item(MIND, 1) }, COMBAT),

		BONES_TO_BANANA(33, 15, new Item[] { new Item(EARTH, 2), new Item(WATER, 2), new Item(NATURE, 1) }, ONE_CLICK),

		WIND_BOLT(34, 17, new Item[] { new Item(AIR, 2), new Item(CHAOS, 1) }, COMBAT),

		CURSE(35, 19, new Item[] { new Item(WATER, 2), new Item(EARTH, 3), new Item(BODY, 1) }, COMBAT),

		BIND(36, 20, new Item[] { new Item(EARTH, 3), new Item(WATER, 3), new Item(NATURE, 2) }, COMBAT),

		LOW_ALCHEMY(38, 20, new Item[] { new Item(FIRE, 3), new Item(NATURE, 1) }, INVENTORY),

		WATER_BOLT(39, 23, new Item[] { new Item(WATER, 2), new Item(AIR, 2), new Item(CHAOS, 1) }, COMBAT),

		VARROCK_TELEPORT(40, 25, 35, new WorldTile(3212, 3424, 0), new Item[] { new Item(FIRE, 1), new Item(AIR, 3), new Item(LAW, 1) }, ONE_CLICK),

		ENCHANT2(41, 27, new Item[] { new Item(AIR, 3), new Item(COSMIC, 1) }, INVENTORY),

		EARTH_BOLT(42, 29, new Item[] { new Item(EARTH, 3), new Item(AIR, 2), new Item(CHAOS, 1) }, COMBAT),

		LUMBRIDGE_TELEPORT(43, 31, 41, new WorldTile(3222, 3218, 0), new Item[] { new Item(EARTH, 1), new Item(AIR, 3), new Item(LAW, 1) }, ONE_CLICK),

		TELEGRAB(44, 33, new Item[] { new Item(AIR, 1), new Item(LAW, 1) }, FLOOR),

		FIRE_BOLT(45, 35, new Item[] { new Item(FIRE, 4), new Item(AIR, 3), new Item(CHAOS, 1) }, COMBAT),

		FALADOR_TELEPORT(46, 37, 48, new WorldTile(2964, 3379, 0), new Item[] { new Item(WATER, 1), new Item(AIR, 3), new Item(LAW, 1) }, ONE_CLICK),

		CRUMBLE_UNDEAD(47, 39, new Item[] { new Item(EARTH, 2), new Item(AIR, 2), new Item(CHAOS, 1) }, COMBAT),

		HOUSE_TELEPORT(48, 40, 25, new WorldTile(3222, 3222, 0), new Item[] { new Item(LAW, 1), new Item(AIR, 1), new Item(EARTH, 1) }, ONE_CLICK),

		WIND_BLAST(49, 41, new Item[] { new Item(AIR, 3), new Item(DEATH, 1) }, COMBAT),

		SUPERHEAT(50, 43, 53, new Item[] { new Item(FIRE, 4), new Item(NATURE, 1) }, INVENTORY),

		CAMELOT_TELEPORT(51, 45, 55.5, new WorldTile(2757, 3478, 0), new Item[] { new Item(AIR, 5), new Item(LAW, 1) },ONE_CLICK),

		WATER_BLAST(52, 47, new Item[] { new Item(WATER, 3), new Item(AIR, 3), new Item(DEATH, 1) }, COMBAT),

		ENCHANT3(53, 49, new Item[] { new Item(FIRE, 5), new Item(COSMIC, 1) }, INVENTORY),

		IBAN_BLAST(54, 50, new Item[] { new Item(FIRE, 5), new Item(DEATH, 1) }, new Item[] { new Item(1409) }, COMBAT),

		SNARE(55, 50, new Item[] { new Item(EARTH, 4), new Item(WATER, 4), new Item(NATURE, 3) }, COMBAT),

		SLAYER_DART(56, 50, new Item[] { new Item(DEATH, 1), new Item(MIND, 4) }, new Item[] { new Item(4170), new Item(24203) }, COMBAT),

		ARDOUGNE_TELEPORT(57, 51, 61, new WorldTile(2664, 3305, 0), new Item[] { new Item(WATER, 2), new Item(LAW, 2) }, ONE_CLICK),

		EARTH_BLAST(58, 53, new Item[] { new Item(EARTH, 4), new Item(AIR, 3), new Item(DEATH, 1) }, COMBAT),

		HIGH_ALCHEMY(59, 55, new Item[] { new Item(FIRE, 5), new Item(NATURE, 1) }, INVENTORY),

		WATER_ORB(60, 56, 66, new Item[] { new Item(WATER, 30), new Item(COSMIC, 3), new Item(567, 1) }, OBJECT),

		ENCHANT4(61, 57, new Item[] { new Item(EARTH, 10), new Item(COSMIC, 1) }, INVENTORY),

		WATCHTOWER_TELEPORT(62, 58, 68, new WorldTile(2547, 3113, 2), new Item[] { new Item(EARTH, 2), new Item(LAW, 2) }, ONE_CLICK),

		FIRE_BLAST(63, 59, new Item[] { new Item(FIRE, 5), new Item(AIR, 4), new Item(DEATH, 1) }, COMBAT),

		EARTH_ORB(64, 60, 70, new Item[] { new Item(EARTH, 30), new Item(COSMIC, 3), new Item(567, 1) }, OBJECT),

		BONES_TO_PEACHES(65, 60, new Item[] { new Item(NATURE, 2), new Item(WATER, 4), new Item(EARTH, 4) }, ONE_CLICK),

		SARADOMIN_STRIKE(66, 60, new Item[] { new Item(FIRE, 2), new Item(BLOOD, 2), new Item(AIR, 4) }, new Item[] { new Item(2415) }, COMBAT),

		CLAWS_OF_GUTHIX(67, 60, new Item[] { new Item(FIRE, 1), new Item(BLOOD, 2), new Item(AIR, 4) }, new Item[] { new Item(2416), new Item(8841) }, COMBAT),

		FLAMES_OF_ZAMORAK(68, 60, new Item[] { new Item(FIRE, 4), new Item(BLOOD, 2), new Item(AIR, 1) }, new Item[] { new Item(2417) }, COMBAT),

		TROLLHEIM_TELEPORT(69, 61, 68, new WorldTile(2888, 3674, 0), new Item[] { new Item(FIRE, 2), new Item(LAW, 2) }, ONE_CLICK),

		WIND_WAVE(70, 62, new Item[] { new Item(AIR, 5), new Item(BLOOD, 1) }, COMBAT),

		FIRE_ORB(71, 63, 73, new Item[] { new Item(FIRE, 30), new Item(COSMIC, 3), new Item(567, 1) }, OBJECT),

		APE_ATOLL_TELEPORT(72, 64, 76, new WorldTile(2762, 9094, 0), new Item[] { new Item(FIRE, 2), new Item(WATER, 2), new Item(LAW, 2), new Item(BANANA, 1) }, ONE_CLICK),

		WATER_WAVE(73, 65, new Item[] { new Item(WATER, 7), new Item(AIR, 5), new Item(BLOOD, 1) }, COMBAT),

		AIR_ORB(74, 66, 76, new Item[] { new Item(AIR, 30), new Item(COSMIC, 3), new Item(567, 1) }, OBJECT),

		VULNERABILITY(75, 66, new Item[] { new Item(EARTH, 5), new Item(WATER, 5), new Item(SOUL, 1) }, COMBAT),

		ENCHANT5(76, 68, new Item[] { new Item(EARTH, 15), new Item(WATER, 15), new Item(COSMIC, 1) }, INVENTORY),

		EARTH_WAVE(77, 70, new Item[] { new Item(EARTH, 7), new Item(AIR, 5), new Item(BLOOD, 1) }, COMBAT),

		ENFEEBLE(78, 73, new Item[] { new Item(EARTH, 8), new Item(WATER, 8), new Item(SOUL, 1) }, COMBAT),

		TELEPORT_OTHER_LUMBRIDGE(79, 74, new Item[] { new Item(SOUL, 1), new Item(LAW, 1), new Item(EARTH, 1) }, ON_PLAYER),

		FIRE_WAVE(80, 75, new Item[] { new Item(FIRE, 7), new Item(AIR, 5), new Item(BLOOD, 1) }, COMBAT),

		STORM_OF_ARMADYL(99, 77, new Item[] { new Item(ARMADYL, 1) }, COMBAT),

		ENTANGLE(81, 79, new Item[] { new Item(EARTH, 5), new Item(WATER, 5), new Item(NATURE, 4) }, COMBAT),

		STUN(82, 80, new Item[] { new Item(EARTH, 12), new Item(WATER, 12), new Item(SOUL, 1) }, COMBAT),

		CHARGE(83, 80, new Item[] { new Item(FIRE, 3), new Item(BLOOD, 3), new Item(AIR, 3) }, ONE_CLICK),

		WIND_SURGE(84, 81, new Item[] { new Item(AIR, 7), new Item(BLOOD, 1), new Item(DEATH, 1) }, COMBAT),

		TELEPORT_OTHER_FALADOR(85, 82, new Item[] { new Item(SOUL, 1), new Item(WATER, 1), new Item(LAW, 1) }, ON_PLAYER),

		TELEPORT_BLOCK(86, 85, new Item[] { new Item(CHAOS, 1), new Item(LAW, 1), new Item(DEATH, 1) }, COMBAT),

		WATER_SURGE(87, 85, new Item[] { new Item(WATER, 10), new Item(AIR, 7), new Item(BLOOD, 1), new Item(DEATH, 1) }, COMBAT),

		ENCHANT6(88, 87, new Item[] { new Item(EARTH, 20), new Item(FIRE, 20), new Item(COSMIC, 1) }, INVENTORY),

		EARTH_SURGE(89, 90, new Item[] { new Item(EARTH, 10), new Item(AIR, 7), new Item(BLOOD, 1), new Item(DEATH, 1) }, COMBAT),

		TELEPORT_OTHER_CAMELOT(90, 90, new Item[] { new Item(SOUL, 2), new Item(LAW, 1) }, ON_PLAYER),

		FIRE_SURGE(91, 95, new Item[] { new Item(FIRE, 10), new Item(AIR, 10), new Item(BLOOD, 1), new Item(DEATH, 1) }, COMBAT),

		;

		private int spellId;
		private int level;
		private double xp;
		private WorldTile tile;
		private Item[] rune;
		private Item[] staff;
		private int spellType;

		public static RSSpellStore getSpell(int i) {
			for (RSSpellStore s : RSSpellStore.values()) {
				if (s.getSpellId() == i)
					return s;
			}
			return null;
		}

		RSSpellStore(int spellId, int level, double xp, Item[] rune, int spellType) {
			this.setSpellId(spellId);
			this.setLevel(level);
			this.setXp(xp);
			this.setRune(rune);
			this.setSpellType(spellType);
		}

		RSSpellStore(int spellId, int level, Item[] rune, int spellType) {
			this.setSpellId(spellId);
			this.setLevel(level);
			this.setRune(rune);
			this.setSpellType(spellType);
		}

		RSSpellStore(int spellId, int level, double xp, WorldTile tile, Item[] rune, int spellType) {
			this.setSpellId(spellId);
			this.setLevel(level);
			this.setXp(xp);
			this.setTile(tile);
			this.setRune(rune);
			this.setSpellType(spellType);
		}

		RSSpellStore(int spellId, int level, Item[] rune, Item[] staff, int spellType) {
			this.setSpellId(spellId);
			this.setLevel(level);
			this.setRune(rune);
			this.setStaff(staff);
			this.setSpellType(spellType);
		}

		public Item[] getStaff() {
			return staff;
		}

		public void setStaff(Item[] staff) {
			this.staff = staff;
		}

		public int getSpellId() {
			return spellId;
		}

		public void setSpellId(int spellId) {
			this.spellId = spellId;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public Item[] getRune() {
			return rune;
		}

		public void setRune(Item[] rune) {
			this.rune = rune;
		}

		public WorldTile getTile() {
			return tile;
		}

		public void setTile(WorldTile tile) {
			this.tile = tile;
		}

		public double getXp() {
			return xp;
		}

		public void setXp(double xp) {
			this.xp = xp;
		}

		public int getSpellType() {
			return spellType;
		}

		public void setSpellType(int spellType) {
			this.spellType = spellType;
		}
	}

	private static boolean autocast;

	public static void setAutocast(boolean auto) {
		autocast = auto;
	}

	public static boolean isAutocasting() {
		return autocast;
	}

	public static boolean wearingRunicStaff(Player player) {
		return player.getEquipment().getWeaponId() == 24203;
	}

	public static boolean hasRunePouch(Player player) {
		return player.getInventory().containsOneItem(RUNE_POUCH);
	}

	public static boolean hasChargedRunicStaff(Player player, int spellId) {
		return player.getRunicStaff().getSpellId() == spellId && player.getRunicStaff().getCharges() > 0;
	}

	public static boolean hasRequirement(Player player, int spellId, boolean skipRunes, boolean autocast) {
		if (player.getCombatDefinitions().getSpellBook() == 192) {
			RSSpellStore s = RSSpellStore.getSpell(spellId);
			if (s == null)
				return false;
				if (s.getSpellType() != COMBAT && player.getLockDelay() > Utils.currentTimeMillis()) {
					return false;
				}
				if (s.getSpellType() == COMBAT) {
					if (player.getSkills().getLevelForXp(Skills.MAGIC) < s.getLevel()) {
						player.getPackets().sendGameMessage(
								"You need at least a level of " + s.getLevel() + " magic to cast this spell.");
						return false;
					}
				} else {
					if (player.getSkills().getLevel(Skills.MAGIC) < s.getLevel()) {
						player.getPackets().sendGameMessage(
								"You need at least a level of " + s.getLevel() + " magic to cast this spell.");
						return false;
					}
				}
			if (!skipRunes) {
				if (hasChargedRunicStaff(player, spellId) && wearingRunicStaff(player))
					castSpell(player, spellId, autocast);
				else
					checkRunes(player, spellId, autocast);
			}
			return true;
		}
		return true;
	}

	private static boolean hasRune(Player player, int itemId, int amount) {
		return player.getRunePouch().contains(new Item(itemId, amount))
				|| player.getInventory().containsItem(itemId, amount);
	}

	private static final ArrayList<Item> runesToRemove = new ArrayList<>();

	public static boolean checkRunes(Player player, int spellId, boolean autocast) {
		if (player.getCombatDefinitions().getSpellBook() == 192) {
			RSSpellStore s = RSSpellStore.getSpell(spellId);
			if (s == null)
				return false;
				if (s.getStaff() != null) {
					boolean hasStaff = false;
					for (Item staff : s.getStaff()) {
						if (staff == null)
							continue;
						if (player.getEquipment().getWeaponId() == staff.getId()) {
							hasStaff = true;
						}
					}
					if (!hasStaff) {
						player.getPackets()
								.sendGameMessage("You don't have the required staff equipped to cast this spell.");
						return false;
					}
				}
				boolean canCast = true;
				if (s.getRune() != null) {
					Item[] runesCopy = s.getRune();
					int index = 0;
					runesToRemove.clear();
					for (Item originalRune : runesCopy) {
						if (originalRune == null)
							continue;
						Item rune = runesCopy[index].clone();
						index++;
						boolean hasLava = hasRune(player, LAVA, originalRune.getAmount());
						boolean hasSmoke = hasRune(player, SMOKE, originalRune.getAmount());
						boolean hasMist = hasRune(player, MIST, originalRune.getAmount());
						boolean hasMud = hasRune(player, MUD, originalRune.getAmount());
						boolean hasDust = hasRune(player, DUST, originalRune.getAmount());
						boolean hasSteam = hasRune(player, STEAM, originalRune.getAmount());
						if (originalRune.getId() == WATER) {
							if (infiniteWater(player))
								continue;
							if (hasMud) {
								rune.setId(MUD);
							}
							if (hasMist) {
								rune.setId(MIST);
							}
							if (hasSteam) {
								rune.setId(STEAM);
							}
						}
						if (originalRune.getId() == AIR) {
							if (infiniteAir(player))
								continue;
							if (hasMist) {
								rune.setId(MIST);
							}
							if (hasDust) {
								rune.setId(DUST);
							}
							if (hasSmoke) {
								rune.setId(SMOKE);
							}
						}
						if (originalRune.getId() == EARTH) {
							if (infiniteEarth(player))
								continue;
							if (hasDust) {
								rune.setId(DUST);
							}
							if (hasMud) {
								rune.setId(MUD);
							}
							if (hasLava) {
								rune.setId(LAVA);
							}
						}
						if (originalRune.getId() == FIRE) {
							if (infiniteFire(player))
								continue;
							if (hasLava) {
								rune.setId(LAVA);
							}
							if (hasSteam) {
								rune.setId(STEAM);
							}
							if (hasSmoke) {
								rune.setId(SMOKE);
							}
						}
						if (player.getInventory().containsOneItem(RUNE_POUCH)) {
							if (!player.getRunePouch().contains(new Item(rune.getId(), rune.getAmount()))
									&& !player.getInventory().containsItem(rune.getId(), rune.getAmount())) {
								canCast = false;
							}
						} else {
							if (!player.getInventory().containsItem(rune.getId(), rune.getAmount())) {
								canCast = false;
							}
						}
						if (!canCast) {
							player.getPackets().sendGameMessage(
									"You don't have enough " + rune.getName() + " to cast this spell.");
							return false;
						}
						runesToRemove.add(rune);
					}
				}
			castSpell(player, spellId, autocast);
		}
		return true;
	}

	public static void checkCombatSpell(Player player, int spellId) {
		RSSpellStore s = RSSpellStore.getSpell(spellId);
		if (s == null)
			return;
			if (s.getSpellType() == COMBAT) {
				if (hasRequirement(player, spellId, true, false)) {
					if (checkRunes(player, spellId, false)) {
						if (player.getCombatDefinitions().getAutoCastSpell() == s.getSpellId())
							player.getCombatDefinitions().resetSpells(true);
						else {
							if (player.getCombatDefinitions().getAutoCastSpell() > 0)
								player.getCombatDefinitions().resetSpells(true);
							player.getCombatDefinitions().setAutoCastSpell(s.getSpellId());
						}
					}
				}
			} else {
				hasRequirement(player, spellId, false, false);
			}
	}

	public static void castSpell(Player player, int spellId, boolean autocast) {
		int itemId = -1;
		int slotId = -1;
		int objectId = -1;
		FloorItem floorItem = null;
		Entity target = null;
		if (player.getTemporaryAttributtes().get("spell_itemid") != null)
			itemId = (int) player.getTemporaryAttributtes().get("spell_itemid");
		if (player.getTemporaryAttributtes().get("spell_slotid") != null)
			slotId = (int) player.getTemporaryAttributtes().get("spell_slotid");
		if (player.getTemporaryAttributtes().get("spell_objectid") != null)
			objectId = (int) player.getTemporaryAttributtes().get("spell_objectid");
		if (player.getTemporaryAttributtes().get("spell_target") != null)
			target = (Entity) player.getTemporaryAttributtes().get("spell_target");
		if (player.getTemporaryAttributtes().get("spell_flooritem") != null)
			floorItem = (FloorItem) player.getTemporaryAttributtes().get("spell_flooritem");

		RSSpellStore s = RSSpellStore.getSpell(spellId);
		if (s == null)
			return;
			if (s.getSpellType() == COMBAT) {
				if (!autocast)
					player.temporaryAttribute().put("tempCastSpell", spellId);
			} else {
				if (s.getSpellType() == ONE_CLICK)
					handleOneClickSpell(player, spellId);
				else if (s.getSpellType() == FLOOR)
					handleFloorSpell(player, spellId, floorItem);
				else if (s.getSpellType() == INVENTORY)
					handleInventorySpell(player, spellId, itemId, slotId);
				else if (s.getSpellType() == OBJECT)
					handleObjectSpell(player, spellId, objectId);
				else if (s.getSpellType() == ON_PLAYER)
					handleTargetSpell(player, spellId, target);
			}
	}

	private static boolean isWearingFireStaff(int weaponId) {
		return weaponId == 1387 || weaponId == 1393 || weaponId == 1401 || weaponId == 3053 || weaponId == 3054;
	}

	private static boolean staffOfLightEffect(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		return (weaponId == 15486 || weaponId == 22207 || weaponId == 22209 || weaponId == 22211 || weaponId == 22213)
				&& Utils.getRandom(3) == 0;
	}

	public static void removeRunes(Player player) {
		for (Item item : runesToRemove) {
			if (item == null)
				continue;
			if (item.getId() == WATER) {
				if (infiniteWater(player))
					continue;
			}
			if (item.getId() == AIR) {
				if (infiniteAir(player))
					continue;
			}
			if (item.getId() == EARTH) {
				if (infiniteEarth(player))
					continue;
			}
			if (item.getId() == FIRE) {
				if (infiniteFire(player))
					continue;
			}
			if (player.getRunePouch().contains(item) && hasRunePouch(player)) {
				player.getRunePouch().remove(item);
				player.getRunePouch().shift();
				player.getInventory().refresh();
			} else
				player.getInventory().deleteItem(item.getId(), item.getAmount());
		}
	}

	public static void removeRunes(Player player, int spellId) {
		if (staffOfLightEffect(player)) {
			player.getPackets().sendGameMessage("Your spell draws its power completely from your staff.");
			return;
		}
		for (Item item : runesToRemove) {
			if (item == null)
				continue;
			if (item.getId() == WATER) {
				if (infiniteWater(player))
					continue;
			}
			if (item.getId() == AIR) {
				if (infiniteAir(player))
					continue;
			}
			if (item.getId() == EARTH) {
				if (infiniteEarth(player))
					continue;
			}
			if (item.getId() == FIRE) {
				if (infiniteFire(player))
					continue;
			}
			if (player.getRunePouch().contains(item) && hasRunePouch(player)) {
				player.getRunePouch().remove(item);
				player.getRunePouch().shift();
				player.getInventory().refresh();
			} else
				player.getInventory().deleteItem(item.getId(), item.getAmount());
		}
	}

	public static void handleOneClickSpell(Player player, int spellId) {
		RSSpellStore s = RSSpellStore.getSpell(spellId);
		if (s == null)// Check is done here already
			return;
		switch (s) {
			case ENCHANT_BOLT:
				player.stopAll();
				player.getInterfaceManager().sendInterface(432);
				player.getPackets().sendItemOnIComponent(432, 17, 9236, 5); //force to show multiple bolts in interface
				player.getPackets().sendItemOnIComponent(432, 25, 9238, 5);
				player.getPackets().sendItemOnIComponent(432, 21, 9237, 5);
				player.getPackets().sendItemOnIComponent(432, 28, 9239, 5);
				player.getPackets().sendItemOnIComponent(432, 31, 9240, 5);
				player.getPackets().sendItemOnIComponent(432, 34, 9241, 5);
				player.getPackets().sendItemOnIComponent(432, 37, 9242, 5);
				player.getPackets().sendItemOnIComponent(432, 40, 9243, 5);
				player.getPackets().sendItemOnIComponent(432, 43, 9244, 5);
				player.getPackets().sendItemOnIComponent(432, 46, 9245, 5);
				break;
			case BONES_TO_BANANA:
			case BONES_TO_PEACHES:
				if (BonesTo.cast(player, s.getXp(), s == RSSpellStore.BONES_TO_PEACHES))
					removeRunes(player, spellId);
				break;
			case CHARGE:
				if (Charge.castSpell(player))
					removeRunes(player, spellId);
				break;
			case HOME_TELEPORT:
			case LUMBRIDGE_TELEPORT:
			case FALADOR_TELEPORT:
			case VARROCK_TELEPORT:
			case CAMELOT_TELEPORT:
			case MOBILISING_TELEPORT:
			case ARDOUGNE_TELEPORT:
			case TROLLHEIM_TELEPORT:
			case APE_ATOLL_TELEPORT:
			case WATCHTOWER_TELEPORT:
			case HOUSE_TELEPORT:
				sendNormalTeleportSpell(player, s.getXp(), s.getTile());
				break;
		}
	}

	public static void handleInventorySpell(Player player, int spellId, int itemId, int slotId) {
		RSSpellStore s = RSSpellStore.getSpell(spellId);
		if (s == null)// Check is done here already
			return;
		switch (s) {
			case LOW_ALCHEMY:
			case HIGH_ALCHEMY:
				boolean fireStaff = isWearingFireStaff(player.getEquipment().getWeaponId());
				if (Alchemy.castSpell(player, itemId, slotId, fireStaff, s.name().toLowerCase().contains("low_")))
					removeRunes(player, spellId);
				break;
			case SUPERHEAT:
				if (SuperHeat.cast(player, s.getXp(), itemId, slotId))
					removeRunes(player, spellId);
				break;
			case ENCHANT1:
			case ENCHANT2:
			case ENCHANT3:
			case ENCHANT4:
			case ENCHANT5:
			case ENCHANT6:
				int enchantLevel = Integer.parseInt(s.name().replace("ENCHANT", ""));
				if (Enchanting.startEnchant(player, itemId, slotId, enchantLevel, s.getXp()))
					removeRunes(player, spellId);
				break;
		}
	}

	public static void handleObjectSpell(Player player, int spellId, int objectId) {
		RSSpellStore s = RSSpellStore.getSpell(spellId);
		if (s == null)// Check is done here already
			return;
		player.getTemporaryAttributtes().put("spell_runes", runesToRemove);
		player.getTemporaryAttributtes().put("spell_xp", s.getXp());
		switch (s) {
			case AIR_ORB:
				if (objectId == 2152)
					ChargeOrb.charge(player, 573);
				break;
			case WATER_ORB:
				if (objectId == 2151)
					ChargeOrb.charge(player, 571);
				break;
			case EARTH_ORB:
				if (objectId == 2154)
					ChargeOrb.charge(player, 575);
				break;
			case FIRE_ORB:
				if (objectId == 2153)
					ChargeOrb.charge(player, 569);
				break;
		}
		player.getPackets().sendGameMessage("Nothing interesting happens.");
	}

	public static void handleFloorSpell(Player player, int spellId, FloorItem floorItem) {
		RSSpellStore s = RSSpellStore.getSpell(spellId);
		if (s == null)// Check is done here already
			return;
		switch (s) {
			case TELEGRAB:// TODO TELEGRAB, GET PACKET
				player.getPackets().sendGameMessage("telegrab handler works");
				break;
		}
	}

	public static void handleTargetSpell(Player player, int spellId, Entity target) {
		RSSpellStore s = RSSpellStore.getSpell(spellId);
		if (s == null)// Check is done here already
			return;
		Player p2 = (Player) target;
		switch (s) {
			case TELEPORT_OTHER_CAMELOT://TODO ALL TELEPORT OTHER SPELLS
			case TELEPORT_OTHER_LUMBRIDGE:
			case TELEPORT_OTHER_FALADOR:
				player.getPackets().sendGameMessage("Teleport " + p2.getDisplayName() + " to " + s.name());
				break;
		}
	}

	public static void sendNormalTeleportSpell(Player player, double xp, WorldTile tile) {
		sendTeleportSpell(player, 8939, 8941, 1576, 1577, xp, tile, 4, true);
	}

	public static boolean sendTeleportSpell(final Player player, int upEmoteId, final int downEmoteId,
											int upGraphicId, final int downGraphicId, final double xp, final WorldTile tile, int delay,
											final boolean randomize) {
		if (player.getControlerManager().getControler() instanceof FfaZone
				|| player.getControlerManager().getControler() instanceof CrucibleControler
				|| player.getControlerManager().getControler() instanceof FightKiln
				|| player.getControlerManager().getControler() instanceof FightCaves) {
			player.getPackets().sendGameMessage("You cannot teleport out of here.");
			return false;
		}
		if (!player.getControlerManager().processMagicTeleport(tile))
			return false;
		player.stopAll();
		player.tele(5 + delay);
		player.resetReceivedHits();
		if (xp != 0)
			player.getSkills().addXp(Skills.MAGIC, xp);
		removeRunes(player);
		player.lock(3 + delay);
		if (upEmoteId != -1)
			player.animate(new Animation(upEmoteId));
		if (upGraphicId != -1)
			player.gfx(new Graphics(upGraphicId));
		player.getPackets().sendSound(5527, 0, 2);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				if (tile.getX() == 3222 && tile.getY() == 3222) {
					player.tele(4);
					player.setFreezeDelay(0);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getControlerManager().forceStop();
					stop();
					player.getHouse().enterMyHouse();
					return;
				}
				WorldTile teleTile = tile;
				if (randomize) {
					for (int trycount = 0; trycount < 10; trycount++) {
						teleTile = new WorldTile(tile, 2);
						if (World.canMoveNPC(tile.getPlane(), teleTile.getX(), teleTile.getY(), player.getSize()))
							break;
						teleTile = tile;
					}
				}
				player.setNextWorldTile(teleTile);
				player.animate(new Animation(downEmoteId));
				if (downGraphicId != -1)
					player.gfx(new Graphics(downGraphicId));
				player.getPackets().sendSound(5524, 0, 2);
				player.setNextFaceWorldTile(new WorldTile(teleTile.getX(), teleTile.getY() - 1, teleTile.getPlane()));
				player.setDirection(6);
				player.setFreezeDelay(0);
				player.getInterfaceManager().closeChatBoxInterface();
				player.getControlerManager().forceStop();
				stop();
			}
		}, delay, 0);
		return true;
	}

}