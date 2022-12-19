package com.rs.game.player.actions.combat;

import java.util.ArrayList;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.clanwars.FfaZone;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.lunarspells.BakePie;
import com.rs.game.player.actions.combat.lunarspells.BoostPotionShare;
import com.rs.game.player.actions.combat.lunarspells.CureGroup;
import com.rs.game.player.actions.combat.lunarspells.CureMe;
import com.rs.game.player.actions.combat.lunarspells.CureOther;
import com.rs.game.player.actions.combat.lunarspells.DisruptionShield;
import com.rs.game.player.actions.combat.lunarspells.EnergyTransfer;
import com.rs.game.player.actions.combat.lunarspells.HealGroup;
import com.rs.game.player.actions.combat.lunarspells.HealOther;
import com.rs.game.player.actions.combat.lunarspells.Humidify;
import com.rs.game.player.actions.combat.lunarspells.MagicImbue;
import com.rs.game.player.actions.combat.lunarspells.MonsterExamine;
import com.rs.game.player.actions.combat.lunarspells.NPCContact;
import com.rs.game.player.actions.combat.lunarspells.PlankMake;
import com.rs.game.player.actions.combat.lunarspells.StatRestoreShare;
import com.rs.game.player.actions.combat.lunarspells.StatSpy;
import com.rs.game.player.actions.combat.lunarspells.StringJewellery;
import com.rs.game.player.actions.combat.lunarspells.SuperGlassMake;
import com.rs.game.player.actions.combat.lunarspells.TuneBaneOre;
import com.rs.game.player.actions.combat.lunarspells.Vengeance;
import com.rs.game.player.actions.combat.lunarspells.VengeanceGroup;
import com.rs.game.player.actions.combat.lunarspells.VengeanceOther;
import com.rs.game.player.controlers.CrucibleControler;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class LunarMagicks {

	/**
	 * @author -Andreas
	 * 
	 * @2018-06-21
	 */

	public static final int AIR = 556, WATER = 555, EARTH = 557, FIRE = 554, MIND = 558, NATURE = 561, CHAOS = 562,
			DEATH = 560, BLOOD = 565, SOUL = 566, LAW = 563, BODY = 559, COSMIC = 564, ASTRAL = 9075;

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

	public static final int INVENTORY = 0, NPC = 1, PLAYER = 2, OBJECT = 3, ONE_CLICK = 4;

	public final static int RUNE_POUCH = 24497;

	public enum RSLunarSpellStore {

		HOME_TELEPORT(39, 0, 0, new WorldTile(3087, 3496, 0), null, ONE_CLICK),

		BAKE_PIE(38, 65, 60, new Item[] { new Item(ASTRAL, 1), new Item(FIRE, 5), new Item(WATER, 4) }, ONE_CLICK),

		CURE_PLANT(55, 66, 60, new Item[] { new Item(ASTRAL, 1), new Item(EARTH, 8) }, OBJECT),

		MONSTER_EXAMINE(28, 66, 61, new Item[] { new Item(ASTRAL, 1), new Item(COSMIC, 1), new Item(MIND, 1) }, NPC),

		NPC_CONTACT(26, 67, 63, new Item[] { new Item(ASTRAL, 1), new Item(COSMIC, 1), new Item(AIR, 2) }, ONE_CLICK),

		CURE_OTHER(23, 68, 65, new Item[] { new Item(ASTRAL, 1), new Item(EARTH, 10) }, PLAYER),

		HUMIDIFY(29, 68, 65, new Item[] { new Item(ASTRAL, 1), new Item(WATER, 3), new Item(FIRE, 1) }, ONE_CLICK),

		MOONCLAN_TELEPORT(43, 69, 66, new WorldTile(2114, 3914, 0),
				new Item[] { new Item(ASTRAL, 2), new Item(LAW, 1), new Item(EARTH, 2) }, ONE_CLICK),

		MOONCLAN_TELEGROUP(56, 70, 67, new WorldTile(2114, 3914, 0),
				new Item[] { new Item(ASTRAL, 2), new Item(LAW, 1), new Item(EARTH, 4) }, ONE_CLICK),

		OURANIA_TELEPORT(54, 71, 69, new WorldTile(2467, 3247, 0),
				new Item[] { new Item(ASTRAL, 2), new Item(LAW, 1), new Item(EARTH, 6) }, ONE_CLICK),

		CURE_ME(46, 71, 69, new Item[] { new Item(ASTRAL, 2), new Item(COSMIC, 2) }, ONE_CLICK),

		HUNTER_KIT(30, 71, 70, new Item[] { new Item(ASTRAL, 2), new Item(EARTH, 2) }, ONE_CLICK),

		SOUTH_FALADOR_TELEPORT(67, 72, 70, new WorldTile(3006, 3327, 0),
				new Item[] { new Item(ASTRAL, 2), new Item(LAW, 1), new Item(AIR, 2) }, ONE_CLICK),

		WATERBIRTH_TELEPORT(47, 72, 71, new WorldTile(2546, 3758, 0),
				new Item[] { new Item(ASTRAL, 2), new Item(LAW, 1), new Item(WATER, 1) }, ONE_CLICK),

		WATERBIRTH_TELEGROUP(57, 73, 72, new WorldTile(2546, 3758, 0),
				new Item[] { new Item(ASTRAL, 2), new Item(LAW, 1), new Item(WATER, 5) }, ONE_CLICK),

		CURE_GROUP(25, 74, 74, new Item[] { new Item(ASTRAL, 2), new Item(COSMIC, 2) }, ONE_CLICK),

		REPAIR_RUNEPOUCH(68, 75, 50, new Item[] { new Item(ASTRAL, 2), new Item(COSMIC, 1), new Item(LAW, 1) },
				INVENTORY),

		BARBARIAN_TELEPORT(22, 75, 76, new WorldTile(2524, 3567, 0),
				new Item[] { new Item(ASTRAL, 2), new Item(LAW, 2), new Item(FIRE, 3) }, ONE_CLICK),

		STAT_SPY(31, 75, 76, new Item[] { new Item(ASTRAL, 2), new Item(COSMIC, 2), new Item(BODY, 5) }, PLAYER),

		NORTH_ARDOUGNE_TELEPORT(69, 76, 77, new WorldTile(2613, 3349, 0),
				new Item[] { new Item(ASTRAL, 2), new Item(LAW, 1), new Item(WATER, 5) }, ONE_CLICK),

		BARBARIAN_TELEGROUP(58, 76, 77, new WorldTile(2524, 3567, 0),
				new Item[] { new Item(ASTRAL, 2), new Item(LAW, 2), new Item(FIRE, 6) }, ONE_CLICK),

		SUPERGLASS_MAKE(48, 77, 78, new Item[] { new Item(ASTRAL, 2), new Item(FIRE, 6), new Item(AIR, 10) },
				ONE_CLICK),

		REMOTE_FARM(70, 78, 79, new Item[] { new Item(ASTRAL, 2), new Item(EARTH, 2), new Item(NATURE, 3) }, ONE_CLICK),

		KHAZARD_TELEPORT(41, 78, 80, new WorldTile(2635, 3166, 0),
				new Item[] { new Item(ASTRAL, 2), new Item(LAW, 2), new Item(WATER, 4) }, ONE_CLICK),

		KHAZARD_TELEGROUP(59, 79, 81, new WorldTile(2635, 3166, 0),
				new Item[] { new Item(ASTRAL, 2), new Item(LAW, 2), new Item(WATER, 8) }, ONE_CLICK),

		DREAM(32, 79, 82, new Item[] { new Item(ASTRAL, 2), new Item(COSMIC, 1), new Item(BODY, 5) }, ONE_CLICK),

		SPIRITUALISE_FOOD(71, 80, 81, new Item[] { new Item(ASTRAL, 2), new Item(COSMIC, 3), new Item(BODY, 5) },
				INVENTORY),

		STRING_JEWELLERY(45, 80, 83.0, new Item[] { new Item(ASTRAL, 2), new Item(EARTH, 10), new Item(WATER, 5) },
				ONE_CLICK),

		STAT_RESTORE_POT_SHARE(50, 81, 84, new Item[] { new Item(ASTRAL, 2), new Item(EARTH, 10), new Item(WATER, 10) },
				INVENTORY),

		MAGIC_IMBUE(36, 82, 86, new Item[] { new Item(ASTRAL, 2), new Item(FIRE, 7), new Item(WATER, 7) }, ONE_CLICK),

		MAKE_LEATHER(72, 83, 87, new Item[] { new Item(ASTRAL, 2), new Item(FIRE, 2), new Item(BODY, 2) }, INVENTORY),

		FERTILE_SOUL(24, 83, 87, new Item[] { new Item(ASTRAL, 3), new Item(NATURE, 2), new Item(EARTH, 15) }, OBJECT),

		BOOST_POTION_SHARE(49, 84, 88, new Item[] { new Item(ASTRAL, 3), new Item(EARTH, 12), new Item(WATER, 10) },
				INVENTORY),

		FISHING_GUILD_TELEPORT(40, 85, 89, new WorldTile(2612, 3383, 0),
				new Item[] { new Item(ASTRAL, 3), new Item(LAW, 3), new Item(WATER, 8) }, ONE_CLICK),

		FISHING_GUILD_TELEGROUP(60, 86, 90, new WorldTile(2612, 3383, 0),
				new Item[] { new Item(ASTRAL, 3), new Item(LAW, 3), new Item(WATER, 10) }, ONE_CLICK),

		PLANK_MAKE(33, 86, 90, new Item[] { new Item(ASTRAL, 2), new Item(EARTH, 15), new Item(NATURE, 1) }, INVENTORY),

		CATHERBY_TELEPORT(44, 87, 92, new WorldTile(2800, 3451, 0),
				new Item[] { new Item(ASTRAL, 3), new Item(LAW, 3), new Item(WATER, 10) }, ONE_CLICK),

		TUNE_BANE_ORE(35, 87, 90, new Item[] { new Item(ASTRAL, 2), new Item(EARTH, 4), new Item(21778, 1) },
				INVENTORY),

		CATHERBY_TELEGROUP(61, 88, 93, new WorldTile(2800, 3451, 0),
				new Item[] { new Item(ASTRAL, 3), new Item(LAW, 3), new Item(WATER, 12) }, ONE_CLICK),

		ICE_PLATUE_TELEPORT(51, 89, 96, new WorldTile(2974, 3940, 0),
				new Item[] { new Item(ASTRAL, 3), new Item(LAW, 3), new Item(WATER, 8) }, ONE_CLICK),

		ICE_PLATUE_TELEGROUP(62, 90, 99, new WorldTile(2974, 3940, 0),
				new Item[] { new Item(ASTRAL, 3), new Item(LAW, 3), new Item(WATER, 16) }, ONE_CLICK),

		DISRUPTION_SHIELD(73, 90, 97, new Item[] { new Item(ASTRAL, 3), new Item(BLOOD, 3), new Item(BODY, 10) },
				ONE_CLICK),

		ENERGY_TRANSFER(27, 91, 100, new Item[] { new Item(ASTRAL, 3), new Item(LAW, 2), new Item(NATURE, 1) }, PLAYER),

		HEAL_OTHER(52, 92, 101, new Item[] { new Item(ASTRAL, 3), new Item(LAW, 3), new Item(BLOOD, 1) }, PLAYER),

		TROLLHEIM_TELEPORT(75, 92, 101, new WorldTile(2814, 3680, 0),
				new Item[] { new Item(ASTRAL, 3), new Item(LAW, 3), new Item(WATER, 10) }, ONE_CLICK),

		TROLLHEIM_TELEGROUP(76, 93, 102, new WorldTile(2814, 3680, 0),
				new Item[] { new Item(ASTRAL, 3), new Item(LAW, 3), new Item(WATER, 20) }, ONE_CLICK),

		VENGEANCE_OTHER(42, 93, 108, new Item[] { new Item(ASTRAL, 3), new Item(DEATH, 3), new Item(EARTH, 10) },
				PLAYER),

		VENGEANCE(37, 94, 112, new Item[] { new Item(ASTRAL, 4), new Item(DEATH, 2), new Item(EARTH, 10) }, ONE_CLICK),

		VENGEANCE_GROUP(74, 95, 120, new Item[] { new Item(ASTRAL, 4), new Item(DEATH, 3), new Item(EARTH, 11) },
				ONE_CLICK),

		HEAL_GROUP(53, 95, 124, new Item[] { new Item(ASTRAL, 4), new Item(LAW, 3), new Item(BLOOD, 2) }, ONE_CLICK),

		SPELLBOOK_SWAP(34, 96, 130, new Item[] { new Item(ASTRAL, 3), new Item(COSMIC, 2), new Item(LAW, 1) },
				ONE_CLICK),

		BORROWED_POWER(77, 99, -1, new Item[] { new Item(ASTRAL, 3) }, PLAYER),

		;

		private int spellId;
		private int level;
		private double xp;
		private WorldTile tile;
		private Item[] rune;
		private int spellType;

		public static RSLunarSpellStore getSpell(int i) {
			for (RSLunarSpellStore s : RSLunarSpellStore.values()) {
				if (s.getSpellId() == i)
					return s;
			}
			return null;
		}

		RSLunarSpellStore(int spellId, int level, double xp, Item[] rune, int spellType) {
			this.setSpellId(spellId);
			this.setLevel(level);
			this.setXp(xp);
			this.setRune(rune);
			this.setSpellType(spellType);
		}

		RSLunarSpellStore(int spellId, int level, double xp, WorldTile tile, Item[] rune, int spellType) {
			this.setSpellId(spellId);
			this.setLevel(level);
			this.setXp(xp);
			this.setTile(tile);
			this.setRune(rune);
			this.setSpellType(spellType);
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

	private boolean autocast;

	public void setAutocast(boolean auto) {
		autocast = auto;
	}

	public boolean isAutocasting() {
		return autocast;
	}

	public boolean wearingRunicStaff(Player player) {
		return player.getEquipment().getWeaponId() == 24203;
	}

	public static boolean hasRunePouch(Player player) {
		return player.getInventory().containsOneItem(RUNE_POUCH);
	}

	public boolean hasChargedRunicStaff(Player player, int spellId) {
		if (player.getRunicStaff().getCharges() > 0)
			return true;
		return false;
	}

	public static boolean unhandledSpell(int spellId) {
		switch (spellId) {
		case 68:
		case 70:
		case 71:
		case 77:
			return true;
		}
		return false;

	}

	public static boolean simpleRequirementCheck(Player player, int spellId) {
		if (player.getCombatDefinitions().getSpellBook() == 430) {
			RSLunarSpellStore s = RSLunarSpellStore.getSpell(spellId);
			if (s == null)
				return false;
			if (s != null) {
				if (unhandledSpell(spellId)) {
					player.getPackets().sendGameMessage(
							Utils.formatPlayerNameForDisplay(s.name().replace("_", " ")) + " is an unhandled spell.");
					return false;
				}
				if (player.getLockDelay() > Utils.currentTimeMillis()) {
					return false;
				}
				if (player.getSkills().getLevel(Skills.MAGIC) < s.getLevel()) {
					player.getPackets().sendGameMessage(
							"You need at least a level of " + s.getLevel() + " magic to cast this spell.");
					return false;
				}
			}
			if (!checkRunes(player, spellId, true))
				return false;
			return true;
		}
		return true;
	}

	public static boolean hasRequirement(Player player, int spellId) {
		if (player.getCombatDefinitions().getSpellBook() == 430) {
			RSLunarSpellStore s = RSLunarSpellStore.getSpell(spellId);
			if (s == null)
				return false;
			if (s != null) {
				if (unhandledSpell(spellId)) {
					player.getPackets().sendGameMessage(
							Utils.formatPlayerNameForDisplay(s.name().replace("_", " ")) + " is an unhandled spell.");
					return false;
				}
				if (player.getLockDelay() > Utils.currentTimeMillis()) {
					return false;
				}
				if (player.getSkills().getLevel(Skills.MAGIC) < s.getLevel()) {
					player.getPackets().sendGameMessage(
							"You need at least a level of " + s.getLevel() + " magic to cast this spell.");
					return false;
				}
			}
			if (!checkRunes(player, spellId, false))
				return false;
			return true;
		}
		return true;
	}

	private static Item[] runesCopy;

	private static boolean hasRune(Player player, int itemId, int amount) {
		return player.getRunePouch().contains(new Item(itemId, amount))
				|| player.getInventory().containsItem(itemId, amount);
	}

	private static ArrayList<Item> runesToRemove = new ArrayList<>();

	public static boolean checkRunes(Player player, int spellId, boolean simpleCheck) {
		if (player.getCombatDefinitions().getSpellBook() == 430) {
			RSLunarSpellStore s = RSLunarSpellStore.getSpell(spellId);
			if (s == null)
				return false;
			if (s != null) {
				boolean canCast = true;
				if (s.getRune() != null) {
					runesCopy = s.getRune();
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
							} else if (hasMist) {
								rune.setId(MIST);
							}else if (hasSteam) {
								rune.setId(STEAM);
							}
						}
						if (originalRune.getId() == AIR) {
							if (infiniteAir(player))
								continue;
							if (hasMist) {
								rune.setId(MIST);
							} else if (hasDust) {
								rune.setId(DUST);
							} else if (hasSmoke) {
								rune.setId(SMOKE);
							}
						}
						if (originalRune.getId() == EARTH) {
							if (infiniteEarth(player))
								continue;
							if (hasDust) {
								rune.setId(DUST);
							} else if (hasMud) {
								rune.setId(MUD);
							} else if (hasLava) {
								rune.setId(LAVA);
							}
						}
						if (originalRune.getId() == FIRE) {
							if (infiniteFire(player))
								continue;
							if (hasLava) {
								rune.setId(LAVA);
							}else if (hasSteam) {
								rune.setId(STEAM);
							} else if (hasSmoke) {
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
			}
			if (!simpleCheck)
				castSpell(player, spellId);
		}
		return true;
	}

	public static void castSpell(Player player, int spellId) {
		int itemId = -1;
		int slotId = -1;
		int objectId = -1;
		Entity target = null;
		if (player.getTemporaryAttributes().get("spell_itemid") != null)
			itemId = (int) player.getTemporaryAttributes().get("spell_itemid");
		if (player.getTemporaryAttributes().get("spell_objectid") != null)
			objectId = (int) player.getTemporaryAttributes().get("spell_objectid");
		if (player.getTemporaryAttributes().get("spell_slotid") != null)
			slotId = (int) player.getTemporaryAttributes().get("spell_slotid");
		if (player.getTemporaryAttributes().get("spell_target") != null)
			target = (Entity) player.getTemporaryAttributes().get("spell_target");
		RSLunarSpellStore s = RSLunarSpellStore.getSpell(spellId);
		if (s == null)
			return;
		if (s != null) {
			if (s.getSpellType() == INVENTORY)
				handleInventorySpell(player, spellId, itemId, slotId);
			else if (s.getSpellType() == NPC)
				handleNPCSpell(player, spellId, target);
			else if (s.getSpellType() == PLAYER)
				handlePlayerSpell(player, spellId, target);
			else if (s.getSpellType() == OBJECT)
				handleObjectSpell(player, spellId, objectId);
			else
				handleOneClickSpell(player, spellId);
		}
	}

	public static void removeRunes(Player player) {
		if (runesToRemove == null)
			return;
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

	public static void removeRunes(Player player, Item[] runes) {
		if (runes == null)
			return;
		for (Item item : runes) {
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
		RSLunarSpellStore s = RSLunarSpellStore.getSpell(spellId);
		if (s == null)
			return;
		if (s.name().toLowerCase().contains("magic_imbue")) {
			if (!MagicImbue.cast(player, s.getXp()))
				return;
		}
		if (s.name().toLowerCase().contains("bake_pie")) {
			if (!BakePie.cast(player, s.getXp()))
				return;
		}
		if (s.name().toLowerCase().contains("superglass_make")) {
			if (!SuperGlassMake.cast(player, s.getXp()))
				return;
		}
		if (s.name().toLowerCase().contains("humidify")) {
			if (!Humidify.cast(player, s.getXp()))
				return;
		}
		if (s.name().toLowerCase().contains("npc_contact")) {
			if (!NPCContact.cast(player, s.getXp()))
				return;
		}
		if (s.name().toLowerCase().contains("cure_me")) {
			if (!CureMe.cast(player, s.getXp()))
				return;
		}
		if (s.name().toLowerCase().contains("disruption_shield")) {
			if (!DisruptionShield.cast(player, s.getXp()))
				return;
		}
		if (s.name().toLowerCase().contains("heal_group")) {
			if (!HealGroup.cast(player, s.getXp()))
				return;
		}
		if (s.name().toLowerCase().contains("cure_group")) {
			if (!CureGroup.cast(player, s.getXp()))
				return;
		}

		if (s.name().toLowerCase().contains("vengeance_group")) {
			if (!VengeanceGroup.cast(player, s.getXp()))
				return;
		}
		if (s.name().toLowerCase().contains("vengeance")) {
			if (!Vengeance.cast(player, s.getXp()))
				return;
		}
		if (s.name().toLowerCase().contains("string_jewellery")) {
			if (!StringJewellery.cast(player, s.getXp()))
				return;
		}
		/*
		 * if (s.name().toLowerCase().contains("home_teleport")) {
		 * player.getInterfaceManager().sendInterface(1092); return; }
		 */
		if (s.name().toLowerCase().contains("teleport")) {
			sendLunarTeleportSpell(player, s.getXp(), s.getTile());
			return;
		}
		removeRunes(player);
	}

	public static void handlePlayerSpell(Player player, int spellId, Entity target) {
		RSLunarSpellStore s = RSLunarSpellStore.getSpell(spellId);
		if (s == null)
			return;
		if (s.name().toLowerCase().contains("cure_other")) {
			if (!CureOther.cast(player, target, s.getXp()))
				return;
		}
		if (s.name().toLowerCase().contains("heal_other")) {
			if (!HealOther.cast(player, target, s.getXp()))
				return;
		}
		if (s.name().toLowerCase().contains("stat_spy")) {
			if (!StatSpy.cast(player, target, s.getXp()))
				return;
		}
		if (s.name().toLowerCase().contains("vengeance_other")) {
			if (!VengeanceOther.cast(player, target, s.getXp()))
				return;
		}
		if (s.name().toLowerCase().contains("energy_transfer")) {
			if (!EnergyTransfer.cast(player, target, s.getXp()))
				return;
		}
		removeRunes(player);
	}

	public static void handleNPCSpell(Player player, int spellId, Entity target) {
		RSLunarSpellStore s = RSLunarSpellStore.getSpell(spellId);
		if (s == null)
			return;
		if (s.name().toLowerCase().contains("monster_examine")) {
			if (!MonsterExamine.cast(player, target, s.getXp()))
				return;
		}
		removeRunes(player);
	}

	public static void handleObjectSpell(Player player, int spellId, int objectId) {
		RSLunarSpellStore s = RSLunarSpellStore.getSpell(spellId);
		if (s == null)
			return;
		removeRunes(player);
	}

	public static void handleInventorySpell(Player player, int spellId, int itemId, int slotId) {
		RSLunarSpellStore s = RSLunarSpellStore.getSpell(spellId);
		if (s == null)
			return;
		String spellName = s.name().toLowerCase();
		if (s.name().toLowerCase().contains("plank_make")) {
			if (!PlankMake.cast(player, s.getXp(), itemId, slotId, false)) {
				return;
			}
		}
		if (spellName.contains("stat_restore_pot_share")) {
			if (!StatRestoreShare.cast(player, s.getXp(), itemId, slotId)) {
				return;
			}
		}
		if (spellName.contains("boost_potion_share")) {
			if (!BoostPotionShare.cast(player, s.getXp(), itemId, slotId)) {
				return;
			}
		}
		if (spellName.contains("tune_bane_ore")) {
			if (!TuneBaneOre.cast(player, s.getXp(), itemId, slotId)) {
				return;
			}
		}
		removeRunes(player);
	}

	public final static void sendLunarTeleportSpell(Player player, double xp, WorldTile tile) {
		sendTeleportSpell(player, 9606, -2, 1685, 1684, xp, tile, 5, true);
	}

	public final static boolean sendTeleportSpell(final Player player, int upEmoteId, final int downEmoteId,
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
		if (xp != 0)
			player.getSkills().addXp(Skills.MAGIC, xp);
		removeRunes(player);
		player.tele(5 + delay);
		player.lock(3 + delay);
		if (upEmoteId != -1)
			player.animate(new Animation(upEmoteId));
		if (upGraphicId != -1)
			player.gfx(new Graphics(upGraphicId));
		player.getPackets().sendSound(5527, 0, 2);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
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
				for (Hit hit : player.getNextHits())
					hit.setDamage(-2);
				player.setFreezeDelay(0);
				player.getInterfaceManager().closeChatBoxInterface();
				stop();
			}
		}, delay, 0);
		return true;
	}

}