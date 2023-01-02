package com.rs.game.player.actions.combat;

import java.util.ArrayList;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.clanwars.FfaZone;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.controlers.CrucibleControler;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class AncientMagicks {

	/**
	 * @author -Andreas
	 *
	 * @2018-06-21
	 */

	public static final int SPELLBOOK_ID = 193;

	public static final int AIR = 556, WATER = 555, EARTH = 557, FIRE = 554, MIND = 558, NATURE = 561, CHAOS = 562,
			DEATH = 560, BLOOD = 565, SOUL = 566, LAW = 563, BODY = 559;

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

	public static final int RUNE_POUCH = 24497;

	public enum RSAncientSpellStore {

		// NORMAL SPELLBOOK
		HOME_TELEPORT(48, 0, 0, false, new WorldTile(3087, 3496, 0), null),

		SMOKE_RUSH(28, 50, true,
				new Item[] { new Item(CHAOS, 2), new Item(DEATH, 2), new Item(FIRE, 1), new Item(AIR, 1) }),

		SHADOW_RUSH(32, 52, true,
				new Item[] { new Item(CHAOS, 2), new Item(DEATH, 2), new Item(AIR, 1), new Item(SOUL, 1) }),

		PADDEWWA_TELEPORT(40, 54, 64, false, new WorldTile(3099, 9882, 0),
				new Item[] { new Item(LAW, 2), new Item(FIRE, 1), new Item(AIR, 1) }),

		BLOOD_RUSH(24, 56, true, new Item[] { new Item(CHAOS, 2), new Item(DEATH, 2), new Item(BLOOD, 1) }),

		ICE_RUSH(20, 58, true, new Item[] { new Item(CHAOS, 2), new Item(DEATH, 2), new Item(WATER, 2) }),

		SENNTISTEN_TELEPORT(41, 60, 70, false, new WorldTile(3222, 3336, 0),
				new Item[] { new Item(LAW, 2), new Item(SOUL, 1) }),

		MIASMIC_RUSH(36, 61, true, new Item[] { new Item(CHAOS, 2), new Item(EARTH, 1), new Item(SOUL, 1) },
				new Item[] { new Item(13867), new Item(13869) }),

		SMOKE_BURST(30, 62, true,
				new Item[] { new Item(CHAOS, 4), new Item(DEATH, 2), new Item(FIRE, 2), new Item(AIR, 2) }),

		SHADOW_BURST(34, 64, true,
				new Item[] { new Item(CHAOS, 4), new Item(DEATH, 2), new Item(AIR, 1), new Item(SOUL, 1) }),

		KHARYRLL_TELEPORT(42, 66, 76, false, new WorldTile(3492, 3471, 0),
				new Item[] { new Item(LAW, 2), new Item(BLOOD, 1) }),

		BLOOD_BURST(26, 68, true, new Item[] { new Item(CHAOS, 4), new Item(DEATH, 2), new Item(BLOOD, 2) }),

		ICE_BURST(22, 70, true, new Item[] { new Item(CHAOS, 4), new Item(DEATH, 2), new Item(WATER, 4) }),

		LASSAR_TELEPORT(43, 72, 82, false, new WorldTile(3006, 3471, 0),
				new Item[] { new Item(LAW, 2), new Item(WATER, 4) }),

		MIASMIC_BURST(38, 73, true, new Item[] { new Item(CHAOS, 4), new Item(EARTH, 2), new Item(SOUL, 2) },
				new Item[] { new Item(13867), new Item(13869) }),

		SMOKE_BLITZ(29, 74, true,
				new Item[] { new Item(DEATH, 2), new Item(BLOOD, 2), new Item(FIRE, 2), new Item(AIR, 2) }),

		SHADOW_BLITZ(33, 76, true,
				new Item[] { new Item(DEATH, 2), new Item(BLOOD, 2), new Item(AIR, 2), new Item(SOUL, 2) }),

		DAREEYAK_TELEPORT(44, 78, 88, false, new WorldTile(2990, 3696, 0),
				new Item[] { new Item(LAW, 2), new Item(FIRE, 3), new Item(AIR, 2) }),

		BLOOD_BLITZ(25, 80, true, new Item[] { new Item(DEATH, 2), new Item(BLOOD, 4) }),

		ICE_BLITZ(21, 82, true, new Item[] { new Item(DEATH, 2), new Item(BLOOD, 2), new Item(WATER, 3) }),

		CARRALLANGAR_TELEPORT(45, 84, 94, false, new WorldTile(3217, 3677, 0),
				new Item[] { new Item(LAW, 2), new Item(SOUL, 2) }),

		MIASMIC_BLITZ(37, 85, true, new Item[] { new Item(BLOOD, 2), new Item(EARTH, 3), new Item(SOUL, 3) },
				new Item[] { new Item(13867), new Item(13869) }),

		SMOKE_BARRAGE(31, 86, true,
				new Item[] { new Item(DEATH, 4), new Item(BLOOD, 2), new Item(FIRE, 4), new Item(AIR, 4) }),

		SHADOW_BARRAGE(35, 88, true,
				new Item[] { new Item(DEATH, 4), new Item(BLOOD, 2), new Item(AIR, 4), new Item(SOUL, 3) }),

		ANNAKARL_TELEPORT(46, 90, 100, false, new WorldTile(3288, 3886, 0),
				new Item[] { new Item(LAW, 2), new Item(BLOOD, 2) }),

		BLOOD_BARRAGE(27, 92, true, new Item[] { new Item(DEATH, 4), new Item(BLOOD, 4), new Item(SOUL, 1) }),

		ICE_BARRAGE(23, 94, true, new Item[] { new Item(DEATH, 4), new Item(BLOOD, 2), new Item(WATER, 6) }),

		GHORROCK_TELEPORT(47, 96, 106, false, new WorldTile(2977, 3873, 0),
				new Item[] { new Item(LAW, 2), new Item(BLOOD, 2) }),

		MIASMIC_BARRAGE(39, 85, true, new Item[] { new Item(BLOOD, 4), new Item(EARTH, 4), new Item(SOUL, 4) },
				new Item[] { new Item(13867), new Item(13869) }),

		;

		private int spellId;
		private int level;
		private int xp;
		private boolean combat;
		private WorldTile tile;
		private Item[] rune;
		private Item[] staff;

		public static RSAncientSpellStore getSpell(int i) {
			for (RSAncientSpellStore s : RSAncientSpellStore.values()) {
				if (s.getSpellId() == i)
					return s;
			}
			return null;
		}

		RSAncientSpellStore(int spellId, int level, boolean combat, Item[] rune) {
			this.setSpellId(spellId);
			this.setLevel(level);
			this.setCombat(combat);
			this.setRune(rune);
		}

		RSAncientSpellStore(int spellId, int level, int xp, boolean combat, WorldTile tile, Item[] rune) {
			this.setSpellId(spellId);
			this.setLevel(level);
			this.setXp(xp);
			this.setCombat(combat);
			this.setTile(tile);
			this.setRune(rune);
		}

		RSAncientSpellStore(int spellId, int level, boolean combat, Item[] rune, Item[] staff) {
			this.setSpellId(spellId);
			this.setLevel(level);
			this.setCombat(combat);
			this.setRune(rune);
			this.setStaff(staff);
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

		public boolean isCombat() {
			return combat;
		}

		public void setCombat(boolean combat) {
			this.combat = combat;
		}

		public WorldTile getTile() {
			return tile;
		}

		public void setTile(WorldTile tile) {
			this.tile = tile;
		}

		public int getXp() {
			return xp;
		}

		public void setXp(int xp) {
			this.xp = xp;
		}
	}

	private boolean autocast;

	public void setAutocast(boolean auto) {
		autocast = auto;
	}

	public boolean isAutocasting() {
		return autocast;
	}

	public static boolean hasRunePouch(Player player) {
		return player.getInventory().containsOneItem(RUNE_POUCH);
	}

	public static boolean hasChargedRunicStaff(Player player, int spellId) {
		if (player.getRunicStaff().getSpellId() == spellId && player.getRunicStaff().getCharges() > 0)
			return true;
		return false;
	}

	public static boolean wearingRunicStaff(Player player) {
		return player.getEquipment().getWeaponId() == 24203;
	}

	public static boolean hasRequirement(Player player, int spellId, boolean skipRunes, boolean autocast) {
		if (player.getCombatDefinitions().getSpellBook() == 193) {
			RSAncientSpellStore s = RSAncientSpellStore.getSpell(spellId);
			if (s == null)
				return false;
			if (s != null) {
				if (!s.isCombat() && player.getLockDelay() > Utils.currentTimeMillis()) {
					return false;
				}
				if (s.isCombat()) {
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

	private static Item[] runesCopy;

	private static boolean hasRune(Player player, int itemId, int amount) {
		return player.getRunePouch().contains(new Item(itemId, amount))
				|| player.getInventory().containsItem(itemId, amount);
	}

	private static ArrayList<Item> runesToRemove = new ArrayList<>();

	public static boolean checkRunes(Player player, int spellId, boolean autocast) {
		if (player.getCombatDefinitions().getSpellBook() == 193) {
			RSAncientSpellStore s = RSAncientSpellStore.getSpell(spellId);
			if (s == null)
				return false;
			if (s != null) {
				if (s.getStaff() != null) {
					int staffAmount = s.getStaff().length;
					for (Item staff : s.getStaff()) {
						if (staff == null)
							continue;
						if (staffAmount == 1) {
							if (player.getEquipment().getWeaponId() != staff.getId()) {
								player.getPackets().sendGameMessage("You need to have "
										+ staff.getName().replace("(deg)", "") + "equipped to cast this spell.");
								return false;
							}
						} else {
							if (player.getEquipment().getWeaponId() != staff.getId()) {
								staffAmount--;
								continue;
							}
						}
					}
				}
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
			}
			castSpell(player, spellId, autocast);
		}
		return true;
	}


	public static void checkCombatSpell(Player player, int spellId) {
		RSAncientSpellStore s = RSAncientSpellStore.getSpell(spellId);
		if (s == null)
			return;
		if (s != null) {
			if (s.isCombat()) {
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

	}

	public static void castSpell(Player player, int spellId, boolean autocast) {
		int itemId = -1;
		int slotId = -1;
		if (player.getTemporaryAttributtes().get("non_combat_spell_itemid") != null)
			itemId = (int) player.getTemporaryAttributtes().get("non_combat_spell_itemid");
		if (player.getTemporaryAttributtes().get("non_combat_spell_slotid") != null)
			slotId = (int) player.getTemporaryAttributtes().get("non_combat_spell_slotid");
		RSAncientSpellStore s = RSAncientSpellStore.getSpell(spellId);
		if (s == null)
			return;
		if (s != null) {
			if (s.isCombat()) {
				if (!autocast)
					player.temporaryAttribute().put("tempCastSpell", spellId);
			} else {
				handleNonCombatSpell(player, spellId, itemId, slotId);
			}
		}
	}

	private static boolean staffOfLightEffect(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		return (weaponId == 15486 || weaponId == 22207 || weaponId == 22209 || weaponId == 22211 || weaponId == 22213)
				&& Utils.getRandom(3) == 0;
	}

	public static void removeRunes(Player player) {
		if (runesToRemove == null)
			return;
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

	public static void handleNonCombatSpell(Player player, int spellId, int itemId, int slotId) {
		RSAncientSpellStore s = RSAncientSpellStore.getSpell(spellId);
		if (s == null)
			return;
		/*if (s.name().toLowerCase().contains("home_teleport")) {
			player.getInterfaceManager().sendInterface(1092);
			return;
		}*/
		if (s.name().toLowerCase().contains("teleport")) {
			sendAncientTeleportSpell(player, s.getXp(), s.getTile());
			return;
		}
		removeRunes(player);
	}

	public final static void sendAncientTeleportSpell(Player player, double xp, WorldTile tile) {
		sendTeleportSpell(player, 9599, -1, 1681, -1, xp, tile, 6, true);
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