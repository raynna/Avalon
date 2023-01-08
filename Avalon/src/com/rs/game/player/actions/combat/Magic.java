package com.rs.game.player.actions.combat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.clanwars.FfaZone;
import com.rs.game.minigames.clanwars.RequestController;
import com.rs.game.minigames.clanwars.WarControler;
import com.rs.game.minigames.duel.DuelArena;
import com.rs.game.npc.NPC;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.HomeTeleport;
import com.rs.game.player.actions.WaterFilling.Fill;
import com.rs.game.player.content.ItemConstants;
import com.rs.game.player.content.dungeoneering.DungeonConstants;
import com.rs.game.player.controlers.CrucibleControler;
import com.rs.game.player.controlers.EdgevillePvPControler;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.controlers.Instance;
import com.rs.game.player.controlers.WildernessControler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

/*
 * content package used for static stuff
 */
public class Magic {

	public static final int MAGIC_TELEPORT = 0, ITEM_TELEPORT = 1, OBJECT_TELEPORT = 2, JEWERLY_TELEPORT = 3;

	public static final int AIR_RUNE = 556, WATER_RUNE = 555, EARTH_RUNE = 557, FIRE_RUNE = 554, MIND_RUNE = 558,
			NATURE_RUNE = 561, CHAOS_RUNE = 562, DEATH_RUNE = 560, BLOOD_RUNE = 565, SOUL_RUNE = 566,
			ASTRAL_RUNE = 9075, LAW_RUNE = 563, STEAM_RUNE = 4694, MIST_RUNE = 4695, DUST_RUNE = 4696,
			SMOKE_RUNE = 4697, MUD_RUNE = 4698, LAVA_RUNE = 4699, ARMADYL_RUNE = 21773, BODY_RUNE = 559,
			COSMIC_RUNE = 564, ELEMENTAL_RUNE = 12850, CATALYTIC_RUNE = 12851;

	public static final boolean hasInfiniteRunes(int runeId, int weaponId, int shieldId) {
		if (runeId == AIR_RUNE) {
			if (weaponId == 1381 || weaponId == 1397 || weaponId == 1405 || weaponId == 21777)
				return true;
		} else if (runeId == WATER_RUNE) {
			if (weaponId == 1383 || weaponId == 1395 || weaponId == 1403 || shieldId == 18346 || weaponId == 6562
					|| weaponId == 6563 || weaponId == 11736 || weaponId == 11738)
				return true;
		} else if (runeId == EARTH_RUNE) {
			if (weaponId == 1385 || weaponId == 1399 || weaponId == 1407 || weaponId == 6562 || weaponId == 6563
					|| weaponId == 3053 || weaponId == 3055)
				return true;
		} else if (runeId == FIRE_RUNE) {
			if (weaponId == 1387 || weaponId == 1393 || weaponId == 1401 || weaponId == 3053 || weaponId == 3055
					|| weaponId == 11736 || weaponId == 11738)
				return true;
		}
		return false;
	}

	public static final void castLunarSpell(Player player, int spellId, int itemId, int slotId) {

	}

	public static final void castMiscellaneousSpell(Player player, int spellId, int itemId, int slotId) {
	}

	public static final boolean checkCombatSpell(Player player, int spellId, int set, boolean delete) {
		return true;
	}

	public static final void setCombatSpell(Player player, int spellId) {
		if (player.getCombatDefinitions().getAutoCastSpell() == spellId)
			player.getCombatDefinitions().resetSpells(true);
		else
			checkCombatSpell(player, spellId, 0, false);
	}

	public static final void processLunarSpell(final Player player, final int spellId, int packetId) {
		switch (spellId) {
		}
	}

	public static ArrayList<Player> getPossibleTargets(Player player) {
		ArrayList<Player> closetargets = new ArrayList<Player>();
		Iterator<?> iterator = player.getMapRegionsIds().iterator();
		while (iterator.hasNext()) {
			int regionId = ((Integer) iterator.next()).intValue();
			List<?> playerIndexes = World.getRegion(regionId).getPlayerIndexes();
			if (playerIndexes != null) {
				Iterator<?> iterator_0_ = playerIndexes.iterator();
				while (iterator_0_.hasNext()) {
					int npcIndex = ((Integer) iterator_0_.next()).intValue();
					Player e = (Player) World.getPlayers().get(npcIndex);
					if (e != null && !e.isDead() && !e.hasFinished() && e.isActive() && e.withinDistance(player, 64))
						closetargets.add(e);
				}
			}
		}
		return closetargets;
	}

	public static final void processAncientSpell(Player player, int spellId, int packetId) {
		player.stopAll(false);
		switch (spellId) {
		case 28:
		case 32:
		case 24:
		case 20:
		case 30:
		case 34:
		case 26:
		case 22:
		case 29:
		case 33:
		case 25:
		case 21:
		case 31:
		case 35:
		case 27:
		case 23:
		case 36:
		case 37:
		case 38:
		case 39:
			setCombatSpell(player, spellId);
			break;

		case 48:
			if (player.getControlerManager().getControler() instanceof FfaZone
					|| player.getControlerManager().getControler() instanceof CrucibleControler
					|| player.getControlerManager().getControler() instanceof WarControler) {
				player.getPackets().sendGameMessage("You cannot teleport out of here.");
				return;
			}
			if (player.getControlerManager().getControler() instanceof Instance) {
				useHomeTele(player);
				return;
			}
			useHomeTele(player);
			break;
		case 40:
			sendAncientTeleportSpell(player, 54, 64, new WorldTile(3099, 9882, 0), LAW_RUNE, 2, FIRE_RUNE, 1, AIR_RUNE,
					1);
			break;
		case 41:
			sendAncientTeleportSpell(player, 60, 70, new WorldTile(3222, 3336, 0), LAW_RUNE, 2, SOUL_RUNE, 1);
			break;
		case 42:
			sendAncientTeleportSpell(player, 66, 76, new WorldTile(3492, 3471, 0), LAW_RUNE, 2, BLOOD_RUNE, 1);
			break;
		case 43:
			sendAncientTeleportSpell(player, 72, 82, new WorldTile(3006, 3471, 0), LAW_RUNE, 2, WATER_RUNE, 4);
			break;
		case 44:
			sendAncientTeleportSpell(player, 78, 88, new WorldTile(2990, 3696, 0), LAW_RUNE, 2, FIRE_RUNE, 3, AIR_RUNE,
					2);
			break;
		case 45:
			sendAncientTeleportSpell(player, 84, 94, new WorldTile(3217, 3677, 0), LAW_RUNE, 2, SOUL_RUNE, 2);
			break;
		case 46:
			sendAncientTeleportSpell(player, 90, 100, new WorldTile(3288, 3886, 0), LAW_RUNE, 2, BLOOD_RUNE, 2);
			break;
		case 47:
			sendAncientTeleportSpell(player, 96, 106, new WorldTile(2977, 3873, 0), LAW_RUNE, 2, WATER_RUNE, 8);
			break;
		}
	}

	public static final void processNormalSpell(Player player, int spellId, int packetId) {
		player.stopAll(false);
		switch (spellId) {
		case 98: // wind rush
		case 25: // air strike
		case 28: // water strike
		case 30: // earth strike
		case 32: // fire strike
		case 34: // air bolt
		case 39: // water bolt
		case 42: // earth bolt
		case 45: // fire bolt
		case 49: // air blast
		case 52: // water blast
		case 58: // earth blast
		case 63: // fire blast
		case 70: // air wave
		case 73: // water wave
		case 77: // earth wave
		case 80: // fire wave
		case 99:
		case 84:
		case 87:
		case 89:
		case 91:
		case 36:
		case 55:
		case 81:
		case 66:
		case 67:
		case 68:
			if (player.getEquipment().getWeaponId() == 24203 && player.getRunicStaff().getSpellId() == spellId
					&& !player.getStaffCharges().isEmpty()) {
				player.temporaryAttribute().put("tempCastSpell", spellId);
				return;
			}
			setCombatSpell(player, spellId);
			break;
		case 27: // crossbow bolt enchant
			if (Settings.FREE_TO_PLAY) {
				player.getPackets().sendGameMessage("You can't use this spell in free to play.");
				return;
			}
			if (player.getSkills().getLevel(Skills.MAGIC) < 4) {
				player.getPackets().sendGameMessage("Your Magic level is not high enough for this spell.");
				return;
			}
			player.stopAll();
			player.getInterfaceManager().sendInterface(432);
			break;
		case 33:

			int amountOfBones = player.getInventory().getNumberOf(526);
			if (player.getSkills().getLevel(Skills.MAGIC) < 15) {
				player.getPackets().sendGameMessage("Your Magic level is not high enough for this spell.");
				return;
			}
			if (amountOfBones == 0) {
				player.getPackets().sendGameMessage("You don't have any bones.");
				return;
			}
			if (!Magic.checkRunes1(player, true, Magic.EARTH_RUNE, 2, Magic.WATER_RUNE, 2, Magic.NATURE_RUNE, 1)) {
				player.getPackets().sendGameMessage("You do not have enough runes for this spell.");
				return;
			}
			player.animate(new Animation(722));
			player.gfx(new Graphics(141, 0, 100));
			player.getInventory().deleteItem(526, amountOfBones);
			player.getInventory().addItem(1963, amountOfBones);
			player.getSkills().addXp(Skills.MAGIC, 25);
			break;
		case 65:
			if (Settings.FREE_TO_PLAY) {
				player.getPackets().sendGameMessage("You can't use this spell in free to play.");
				return;
			}
			amountOfBones = player.getInventory().getNumberOf(526);
			if (player.getSkills().getLevel(Skills.MAGIC) < 60) {
				player.getPackets().sendGameMessage("Your Magic level is not high enough for this spell.");
				return;
			}
			if (amountOfBones == 0) {
				player.getPackets().sendGameMessage("You don't have any bones.");
				return;
			}
			if (!Magic.checkRunes1(player, true, Magic.EARTH_RUNE, 4, Magic.WATER_RUNE, 4, Magic.NATURE_RUNE, 2)) {
				player.getPackets().sendGameMessage("You do not have enough runes for this spell.");
				return;
			}
			player.animate(new Animation(722));
			player.gfx(new Graphics(311, 0, -250));
			player.getInventory().deleteItem(526, amountOfBones);
			player.getInventory().addItem(6883, amountOfBones);
			player.getSkills().addXp(Skills.MAGIC, 35.5);
			break;
		case 24:
			player.getInterfaceManager().sendInterface(1092);
			break;
		case 83:
			if (Settings.FREE_TO_PLAY) {
				player.getPackets().sendGameMessage("You can't use this spell in free to play.");
				return;
			}
			if (player.getSkills().getLevel(Skills.MAGIC) < 80) {
				player.getPackets().sendGameMessage("You need at least a level of 80 magic to cast this spell.");
				return;
			}
			if (!player.getInventory().containsItem(565, 3) || !player.getInventory().containsItem(554, 3)
					|| !player.getInventory().containsItem(556, 3)) {
				player.getPackets().sendGameMessage("You do not have the required runes to cast this spell.");
				return;
			}
			if ((player.getEquipment().getWeaponId() != 2415 && player.getEquipment().getWeaponId() != 2416
					&& player.getEquipment().getWeaponId() != 2417)) {
				player.getPackets().sendGameMessage("You do not have the required equipment to cast this spell.");
				return;
			} else {
				if ((player.getEquipment().getWeaponId() == 2415 && player.getEquipment().getCapeId() != 2412)
						|| (player.getEquipment().getWeaponId() == 2416 && player.getEquipment().getCapeId() != 2413)
						|| (player.getEquipment().getWeaponId() == 2417 && player.getEquipment().getCapeId() != 2414)) {
					player.getPackets().sendGameMessage("You do not have the required equipment to cast this spell.");
					return;
				}
			}
			if (player.getChargeImmune() > Utils.currentTimeMillis()) {
				player.getPackets().sendGameMessage("You can't use this spell yet.");
				return;
			} else {
				player.animate(new Animation(811));
				player.getSkills().addXp(Skills.MAGIC, 180);
				player.setChargeImmune(60000);
				player.setChargeDelay(420000);
			}
			break;
		case 37: // mobi
			if (Settings.FREE_TO_PLAY) {
				player.getPackets().sendGameMessage("You can't use this spell in free to play.");
				return;
			}
			sendNormalTeleportSpell(player, 10, 19, new WorldTile(2413, 2848, 0), LAW_RUNE, 1, WATER_RUNE, 1, AIR_RUNE,
					1);
			break;
		case 40: // varrock
			sendNormalTeleportSpell(player, 25, 19, new WorldTile(3212, 3424, 0), FIRE_RUNE, 1, AIR_RUNE, 3, LAW_RUNE,
					1);
			break;
		case 43: // lumby
			sendNormalTeleportSpell(player, 31, 41, new WorldTile(3222, 3218, 0), EARTH_RUNE, 1, AIR_RUNE, 3, LAW_RUNE,
					1);
			break;
		case 46: // fally
			sendNormalTeleportSpell(player, 37, 48, new WorldTile(2964, 3379, 0), WATER_RUNE, 1, AIR_RUNE, 3, LAW_RUNE,
					1);
			break;
		case 51: // camelot
			if (Settings.FREE_TO_PLAY) {
				player.getPackets().sendGameMessage("You can't use this spell in free to play.");
				return;
			}
			sendNormalTeleportSpell(player, 45, 55.5, new WorldTile(2757, 3478, 0), AIR_RUNE, 5, LAW_RUNE, 1);
			break;
		case 57: // ardy
			if (Settings.FREE_TO_PLAY) {
				player.getPackets().sendGameMessage("You can't use this spell in free to play.");
				return;
			}
			sendNormalTeleportSpell(player, 51, 61, new WorldTile(2664, 3305, 0), WATER_RUNE, 2, LAW_RUNE, 2);
			break;
		case 62: // watch
			if (Settings.FREE_TO_PLAY) {
				player.getPackets().sendGameMessage("You can't use this spell in free to play.");
				return;
			}
			sendNormalTeleportSpell(player, 58, 68, new WorldTile(2547, 3113, 2), EARTH_RUNE, 2, LAW_RUNE, 2);
			break;
		case 69: // troll
			if (Settings.FREE_TO_PLAY) {
				player.getPackets().sendGameMessage("You can't use this spell in free to play.");
				return;
			}
			sendNormalTeleportSpell(player, 61, 68, new WorldTile(2888, 3674, 0), FIRE_RUNE, 2, LAW_RUNE, 2);
			break;
		case 72: // ape
			if (Settings.FREE_TO_PLAY) {
				player.getPackets().sendGameMessage("You can't use this spell in free to play.");
				return;
			}
			sendNormalTeleportSpell(player, 64, 76, new WorldTile(2762, 9094, 0), FIRE_RUNE, 2, WATER_RUNE, 2, LAW_RUNE,
					2, 1963, 1);
			break;
		}
	}

	private static void useHomeTele(Player player) {
		if (player.isAtWild() || player.isCanPvp()) {
			player.stopAll();
			player.getActionManager().setAction(new HomeTeleport(Settings.HOME_PLAYER_LOCATION));
		} else {
			player.stopAll();
			player.teleporting(player.getControlerManager().getControler() instanceof WildernessControler ? 10 : 8);
			if (player.getCombatDefinitions().spellBook == 2) {// lunar
				sendLunarTeleportSpell(player, 0, 0, new WorldTile(Settings.HOME_PLAYER_LOCATION));
			} else if (player.getCombatDefinitions().spellBook == 0) {// normal
				sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.HOME_PLAYER_LOCATION));
			} else if (player.getCombatDefinitions().spellBook == 1) {// ancient
				sendAncientTeleportSpell(player, 0, 0, new WorldTile(Settings.HOME_PLAYER_LOCATION));
			}
		}
	}

	public static final boolean checkSpellRequirements1(Player player, int level, boolean delete, int... runes) {
		if (player.getSkills().getLevelForXp(Skills.MAGIC) < level) {
			player.getPackets().sendGameMessage("Your Magic level is not high enough for this spell.");
			return false;
		}
		return checkRunes1(player, delete, runes);
	}

	public static final boolean checkMiscSpellRequirements(Player player, int level, boolean delete, int spellId,
			int... runes) {
		if (player.getSkills().getLevelForXp(Skills.MAGIC) < level) {
			player.getPackets().sendGameMessage("Your Magic level is not high enough for this spell.");
			return false;
		}
		return checkMiscRunes(player, delete, spellId, runes);
	}

	public static final boolean checkMiscRunes(Player player, boolean delete, int spellId, int... runes) {
		int weaponId = player.getEquipment().getWeaponId();
		int shieldId = player.getEquipment().getShieldId();
		int runesCount = 0;
		while (runesCount < runes.length) {
			int runeId = runes[runesCount++];
			int ammount = runes[runesCount++];
			if (hasInfiniteRunes(runeId, weaponId, shieldId))
				continue;
			Item rune = new Item(runeId, ammount);
			if (player.getRunePouch().contains(rune) && player.getInventory().containsOneItem(24497))
				continue;
			if (!player.getInventory().containsItem(runeId, ammount)) {
				if ((runeId == AIR_RUNE || runeId == WATER_RUNE || runeId == EARTH_RUNE || runeId == FIRE_RUNE)
						&& player.getInventory().containsItem(ELEMENTAL_RUNE, ammount)) {
					runes[runesCount - 2] = ELEMENTAL_RUNE;
					continue;
				}
				if ((runeId == CHAOS_RUNE || runeId == DEATH_RUNE || runeId == BLOOD_RUNE || runeId == LAW_RUNE
						|| runeId == NATURE_RUNE || runeId == MIND_RUNE || runeId == BODY_RUNE || runeId == COSMIC_RUNE
						|| runeId == SOUL_RUNE || runeId == ASTRAL_RUNE || runeId == ARMADYL_RUNE)
						&& player.getInventory().containsItem(CATALYTIC_RUNE, ammount)) {
					runes[runesCount - 2] = CATALYTIC_RUNE;
					continue;
				}
				if ((runeId == AIR_RUNE || runeId == WATER_RUNE)
						&& player.getInventory().containsItem(MIST_RUNE, ammount)) {
					runes[runesCount - 2] = MIST_RUNE;
					continue;
				}
				if ((runeId == AIR_RUNE || runeId == EARTH_RUNE)
						&& player.getInventory().containsItem(DUST_RUNE, ammount)) {
					runes[runesCount - 2] = DUST_RUNE;
					continue;
				}
				if ((runeId == WATER_RUNE || runeId == EARTH_RUNE)
						&& player.getInventory().containsItem(MUD_RUNE, ammount)) {
					runes[runesCount - 2] = MUD_RUNE;
					continue;
				}
				if ((runeId == AIR_RUNE || runeId == FIRE_RUNE)
						&& player.getInventory().containsItem(SMOKE_RUNE, ammount)) {
					runes[runesCount - 2] = SMOKE_RUNE;
					continue;
				}
				if ((runeId == WATER_RUNE || runeId == FIRE_RUNE)
						&& player.getInventory().containsItem(STEAM_RUNE, ammount)) {
					runes[runesCount - 2] = STEAM_RUNE;
					continue;
				}
				if ((runeId == EARTH_RUNE || runeId == FIRE_RUNE)
						&& player.getInventory().containsItem(LAVA_RUNE, ammount)) {
					runes[runesCount - 2] = LAVA_RUNE;
					continue;
				}
				player.getPackets()
						.sendGameMessage("You do not have enough "
								+ ItemDefinitions.getItemDefinitions(runeId).getName().replace("rune", "Rune")
								+ "s to cast this spell.");
				return false;
			}
		}
		if (hasStaffOfLight(weaponId) && Utils.getRandom(2) == 0) {
			delete = false;
			player.getPackets().sendGameMessage("Your spell draws its power completely from your staff.");
		}
		if (delete) {
			runesCount = 0;
			while (runesCount < runes.length) {
				int runeId = runes[runesCount++];
				int ammount = runes[runesCount++];
				if (hasInfiniteRunes(runeId, weaponId, shieldId))
					continue;
				Item rune = new Item(runeId, ammount);
				if (player.getInventory().containsOneItem(24497)) {
					if (player.getRunePouch().contains(rune)) {
						player.getRunePouch().remove(rune);
						if (player.getRunePouch().getNumberOf(rune) <= 0) {
							player.getRunePouch().shift();
							player.getPackets()
									.sendGameMessage("You are out of " + rune.getDefinitions().getName() + "s.");
							continue;
						}
						player.getPackets().sendGameMessage(
								rune.getAmount() + " x " + rune.getDefinitions().getName() + " were used up.");
						continue;
					}
				}
				player.getInventory().deleteItem(runeId, ammount);
			}
		}
		handleSpell(spellId);
		return true;
	}

	public static final boolean checkRunes1(Player player, boolean delete, int... runes) {
		int weaponId = player.getEquipment().getWeaponId();
		int shieldId = player.getEquipment().getShieldId();
		int runesCount = 0;
		while (runesCount < runes.length) {
			int runeId = runes[runesCount++];
			int ammount = runes[runesCount++];
			if (hasInfiniteRunes(runeId, weaponId, shieldId))
				continue;
			Item rune = new Item(runeId, ammount);
			if (player.getRunePouch().contains(rune) && player.getInventory().containsOneItem(24497))
				continue;
			if (!player.getInventory().containsItem(runeId, ammount)) {
				if ((runeId == AIR_RUNE || runeId == WATER_RUNE || runeId == EARTH_RUNE || runeId == FIRE_RUNE)
						&& player.getInventory().containsItem(ELEMENTAL_RUNE, ammount)) {
					runes[runesCount - 2] = ELEMENTAL_RUNE;
					continue;
				}
				if ((runeId == CHAOS_RUNE || runeId == DEATH_RUNE || runeId == BLOOD_RUNE || runeId == LAW_RUNE
						|| runeId == NATURE_RUNE || runeId == MIND_RUNE || runeId == BODY_RUNE || runeId == COSMIC_RUNE
						|| runeId == SOUL_RUNE || runeId == ASTRAL_RUNE || runeId == ARMADYL_RUNE)
						&& player.getInventory().containsItem(CATALYTIC_RUNE, ammount)) {
					runes[runesCount - 2] = CATALYTIC_RUNE;
					continue;
				}
				if ((runeId == AIR_RUNE || runeId == WATER_RUNE)
						&& player.getInventory().containsItem(MIST_RUNE, ammount)) {
					runes[runesCount - 2] = MIST_RUNE;
					continue;
				}
				if ((runeId == AIR_RUNE || runeId == EARTH_RUNE)
						&& player.getInventory().containsItem(DUST_RUNE, ammount)) {
					runes[runesCount - 2] = DUST_RUNE;
					continue;
				}
				if ((runeId == WATER_RUNE || runeId == EARTH_RUNE)
						&& player.getInventory().containsItem(MUD_RUNE, ammount)) {
					runes[runesCount - 2] = MUD_RUNE;
					continue;
				}
				if ((runeId == AIR_RUNE || runeId == FIRE_RUNE)
						&& player.getInventory().containsItem(SMOKE_RUNE, ammount)) {
					runes[runesCount - 2] = SMOKE_RUNE;
					continue;
				}
				if ((runeId == WATER_RUNE || runeId == FIRE_RUNE)
						&& player.getInventory().containsItem(STEAM_RUNE, ammount)) {
					runes[runesCount - 2] = STEAM_RUNE;
					continue;
				}
				if ((runeId == EARTH_RUNE || runeId == FIRE_RUNE)
						&& player.getInventory().containsItem(LAVA_RUNE, ammount)) {
					runes[runesCount - 2] = LAVA_RUNE;
					continue;
				}
				player.getPackets()
						.sendGameMessage("You do not have enough "
								+ ItemDefinitions.getItemDefinitions(runeId).getName().replace("rune", "Rune")
								+ "s to cast this spell.");
				return false;
			}
		}
		if (hasStaffOfLight(weaponId) && Utils.getRandom(2) == 0) {
			delete = false;
			player.getPackets().sendGameMessage("Your spell draws its power completely from your staff.");
		}
		if (delete) {
			runesCount = 0;
			while (runesCount < runes.length) {
				int runeId = runes[runesCount++];
				int ammount = runes[runesCount++];
				if (hasInfiniteRunes(runeId, weaponId, shieldId))
					continue;
				Item rune = new Item(runeId, ammount);
				if (player.getInventory().containsOneItem(24497)) {
					if (player.getRunePouch().contains(rune)) {
						player.getRunePouch().remove(rune);
						if (player.getRunePouch().getNumberOf(rune) <= 0) {
							player.getRunePouch().shift();
							player.getPackets()
									.sendGameMessage("You are out of " + rune.getDefinitions().getName() + "s.");
							continue;
						}
						player.getPackets().sendGameMessage(
								rune.getAmount() + " x " + rune.getDefinitions().getName() + " were used up.");
						continue;
					}
				}
				player.getInventory().deleteItem(runeId, ammount);
			}
		}
		return true;
	}

	public static void handleSpell(int spellId) {

	}

	public static boolean isRune(int id) {
		if (id == AIR_RUNE || id == WATER_RUNE || id == EARTH_RUNE || id == FIRE_RUNE || id == MIND_RUNE
				|| id == CHAOS_RUNE || id == DEATH_RUNE || id == BLOOD_RUNE || id == SOUL_RUNE || id == LAW_RUNE
				|| id == NATURE_RUNE || id == COSMIC_RUNE || id == ASTRAL_RUNE || id == ARMADYL_RUNE || id == MUD_RUNE
				|| id == DUST_RUNE || id == STEAM_RUNE || id == MIST_RUNE || id == LAVA_RUNE || id == SMOKE_RUNE
				|| id == BODY_RUNE)
			return true;
		return false;
	}

	public static final void sendLunarTeleportSpell(Player player, int level, double xp, WorldTile tile, int... runes) {
		sendTeleportSpell(player, 9606, -2, 1685, 1684, level, xp, tile, 5, true, MAGIC_TELEPORT, false, runes);
	}

	public static final void sendAncientTeleportSpell(Player player, int level, double xp, WorldTile tile,
			int... runes) {
		sendTeleportSpell(player, 9599, -1, 1681, -1, level, xp, tile, 5, true, MAGIC_TELEPORT, false, runes);
	}

	public static final void sendNormalTeleportSpell(Player player, int level, double xp, WorldTile tile,
			int... runes) {
		sendTeleportSpell(player, 8939, 8941, 1576, 1577, level, xp, tile, 3, true, MAGIC_TELEPORT, false, runes);
	}

	public static final boolean sendItemTeleportSpell(Player player, boolean randomize, int upEmoteId, int upGraphicId,
			int delay, WorldTile tile) {
		return sendTeleportSpell(player, upEmoteId, -2, upGraphicId, -1, 0, 0, tile, delay, randomize, ITEM_TELEPORT,
				false);
	}

	public static final boolean sendJewerlyTeleportSpell(Player player, boolean randomize, int upEmoteId,
			int upGraphicId, int delay, WorldTile tile) {
		return sendTeleportSpell(player, upEmoteId, -2, upGraphicId, -1, 0, 0, tile, delay, randomize, JEWERLY_TELEPORT,
				false);
	}

	public static final boolean sendSkullSceptreTeleport(Player player, boolean randomize, int upEmoteId,
			int upGraphicId, int delay, WorldTile tile) {
		return sendTeleportSpell(player, upEmoteId, -2, upGraphicId, -1, 0, 0, tile, delay, randomize, ITEM_TELEPORT,
				false);
	}

	public static void pushLeverTeleport(final Player player, final WorldTile tile) {
		if (!player.getControlerManager().processObjectTeleport(tile))
			return;
		player.animate(new Animation(2140));
		player.lock(1);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				Magic.sendObjectTeleportSpell(player, false, tile);
			}
		}, 1);
	}

	public static final void sendObjectTeleportSpell(Player player, boolean randomize, WorldTile tile) {
		sendTeleportSpell(player, 8939, 8941, 1576, 1577, 0, 0, tile, 3, randomize, OBJECT_TELEPORT, false);
	}

	public static final void sendDelayedObjectTeleportSpell(Player player, int delay, boolean randomize,
			WorldTile tile) {
		sendTeleportSpell(player, 8939, 8941, 1576, 1577, 0, 0, tile, delay, randomize, OBJECT_TELEPORT, false);
	}

	public static final boolean sendTeleportSpell(final Player player, int upEmoteId, final int downEmoteId,
			int upGraphicId, final int downGraphicId, int level, final double xp, final WorldTile tile, int delay,
			final boolean randomize, final int teleType, boolean checkRunes, int... runes) {
		long currentTime = Utils.currentTimeMillis();
		if (player.getLockDelay() > currentTime)
			return false;
		if (player.getSkills().getLevel(Skills.MAGIC) < level) {
			player.getPackets().sendGameMessage("Your Magic level is not high enough for this spell.");
			return false;
		}
		if (player.getControlerManager().getControler() instanceof FfaZone
				|| player.getControlerManager().getControler() instanceof CrucibleControler
				|| player.getControlerManager().getControler() instanceof FightKiln
				|| player.getControlerManager().getControler() instanceof FightCaves) {
			player.getPackets().sendGameMessage("You cannot teleport out of here.");
			return false;
		}
		if (!checkRunes1(player, false, runes))
			return false;
		if (teleType == MAGIC_TELEPORT) {
			if (!player.getControlerManager().processMagicTeleport(tile))
				return false;
		} else if (teleType == ITEM_TELEPORT) {
			if (!player.getControlerManager().processItemTeleport(tile))
				return false;
		} else if (teleType == JEWERLY_TELEPORT) {
			if (!player.getControlerManager().processItemTeleport(tile))
				return false;
		} else if (teleType == OBJECT_TELEPORT) {
			if (!player.getControlerManager().processObjectTeleport(tile))
				return false;
		}

		checkRunes1(player, true, runes);
		player.tele(7);
		player.stopAll();
		if (xp != 0)
			player.getSkills().addXp(Skills.MAGIC, xp);
		player.lock(3 + delay);
		if (upEmoteId != -1)
			player.animate(new Animation(upEmoteId));
		if (upGraphicId != -1)
			player.gfx(new Graphics(upGraphicId));
		if (teleType == MAGIC_TELEPORT)
			player.getPackets().sendSound(5527, 0, 2);
		WorldTasksManager.schedule(new WorldTask() {

			boolean removeDamage;

			@Override
			public void run() {
				if (!removeDamage) {
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
					player.getControlerManager().magicTeleported(teleType);
					if (player.getControlerManager().getControler() == null)
						teleControlersCheck(player, teleTile);
					player.animate(new Animation(downEmoteId));
					if (downGraphicId != -1)
						player.gfx(new Graphics(downGraphicId));
					if (teleType == MAGIC_TELEPORT) {
						player.getPackets().sendSound(5524, 0, 2);
						player.setNextFaceWorldTile(
								new WorldTile(teleTile.getX(), teleTile.getY() - 1, teleTile.getPlane()));
						player.setDirection(6);
					}
					removeDamage = true;
				} else {
					player.setFreezeDelay(-1);
					player.getInterfaceManager().closeChatBoxInterface();
					stop();
				}
			}
		}, delay, 0);
		return true;
	}

	private final static WorldTile[] TABS = { new WorldTile(3217, 3426, 0), new WorldTile(3222, 3218, 0),
			new WorldTile(2965, 3379, 0), new WorldTile(2758, 3478, 0), new WorldTile(2660, 3306, 0),
			new WorldTile(2553, 3114, 0), new WorldTile(Settings.START_PLAYER_LOCATION), new WorldTile(2908, 3715, 0),
			new WorldTile(1907, 4366, 0), new WorldTile(2908, 3735, 0) };

	public static boolean useTabTeleport(final Player player, final int itemId) {
		if (itemId != 8007 && itemId != 8008 && itemId != 8009 && itemId != 8010 && itemId != 8011 && itemId != 8012
				&& itemId != 8013 && itemId != 18809 && itemId != 18810 && itemId != 20175)
			return false;
		if (itemId >= 8007 && itemId <= 8013)
			useTeleTab(itemId, player, TABS[itemId - 8007]);
		else
			useTeleTab(itemId, player,
					TABS[itemId == 20175 ? 7 : itemId == 18809 ? 8 : 9]);
		return true;
	}

	private final static WorldTile[] LUMBERYARD = { /* nardah */
			new WorldTile(3434, 2891, 0), /* bandit camp */new WorldTile(3173, 2982, 0), /* miscellania */
			new WorldTile(2515, 3860, 0), /* phoenix lair */new WorldTile(2296, 3615, 0), /* tai bwo wannai */
			new WorldTile(2795, 3087, 0), /* lumber yard */new WorldTile(3308, 3491, 0) };

	public static boolean useLumberYardTeleport(final Player player, final int itemId) {
		if (itemId < 19475 || itemId > 19475 + LUMBERYARD.length - 1)
			return false;
		if (useLumberTele(player, LUMBERYARD[itemId - 19475]))
			player.getInventory().deleteItem(itemId, 1);
		return true;
	}

	private static WorldTile pvp = new WorldTile(85, 78, 0);

	public static boolean useLumberTele(final Player player, final WorldTile tile) {
		if (!player.getControlerManager().processItemTeleport(tile))
			return false;
		player.stopAll();
		player.lock(5);
		player.tele(7);
		player.animate(new Animation(14293));
		player.gfx(new Graphics(94));
		WorldTasksManager.schedule(new WorldTask() {
			int stage;

			@Override
			public void run() {
				if (stage == 0) {
					player.animate(new Animation(4731));
					stage = 1;
				} else if (stage == 1) {
					WorldTile teleTile = tile;
					for (int trycount = 0; trycount < 10; trycount++) {
						teleTile = new WorldTile(tile, 2);
						if (World.canMoveNPC(tile.getPlane(), teleTile.getX(), teleTile.getY(), player.getSize()))
							break;
						teleTile = tile;
					}
					player.setNextWorldTile(teleTile);
					player.getControlerManager().magicTeleported(ITEM_TELEPORT);
					if (player.getControlerManager().getControler() == null)
						teleControlersCheck(player, teleTile);
					player.setNextFaceWorldTile(
							new WorldTile(teleTile.getX(), teleTile.getY() - 1, teleTile.getPlane()));
					player.setDirection(6);
					player.animate(new Animation(-1));
					stage = 2;
				} else if (stage == 2) {
					player.resetReceivedDamage();
					player.setFreezeDelay(-1);
					player.getControlerManager().forceStop();
					stop();
				}

			}
		}, 2, 1);
		return true;
	}

	public static void dungeonTeleport(final Player player, final int x, final int y, final int h) {
		player.lock(3);
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					player.animate(new Animation(13288));
					player.gfx(new Graphics(2516));
				} else if (loop == 1) {
					player.setNextWorldTile(new WorldTile(x, y, h));
					player.animate(new Animation(13285));
					player.gfx(new Graphics(2517));
				}
				loop++;
			}
		}, 0, 1);
	}

	public static boolean useTeleTab(int itemId, final Player player, final WorldTile tile) {
		if (!player.getControlerManager().processItemTeleport(tile))
			return false;
		if (player.getControlerManager().getControler() instanceof FfaZone
				|| player.getControlerManager().getControler() instanceof CrucibleControler
				|| player.getControlerManager().getControler() instanceof FightKiln) {
			player.getPackets().sendGameMessage("You cannot teleport out of here.");
			return false;
		}
		player.getInventory().deleteItem(itemId, 1);
		player.resetAllDamage();
		player.stopAll();
		player.tele(7);
		player.lock(5);
		player.animate(new Animation(9597));
		player.gfx(new Graphics(1680));
		WorldTasksManager.schedule(new WorldTask() {
			int stage;

			@Override
			public void run() {
				if (stage == 0) {
					player.animate(new Animation(4731));
					stage = 1;
				} else if (stage == 1) {
					WorldTile teleTile = player.getControlerManager().getControler() instanceof EdgevillePvPControler
							&& itemId == 8013 ? pvp : tile;
					for (int trycount = 0; trycount < 10; trycount++) {
						teleTile = new WorldTile(tile, 2);
						if (World.canMoveNPC(tile.getPlane(), teleTile.getX(), teleTile.getY(), player.getSize()))
							break;
						teleTile = tile;
					}
					player.setNextWorldTile(
							player.getControlerManager().getControler() instanceof EdgevillePvPControler ? pvp
									: teleTile);
					player.getControlerManager().magicTeleported(ITEM_TELEPORT);
					if (player.getControlerManager().getControler() == null)
						teleControlersCheck(player, teleTile);
					player.setNextFaceWorldTile(
							new WorldTile(teleTile.getX(), teleTile.getY() - 1, teleTile.getPlane()));
					player.setDirection(6);
					player.animate(new Animation(-1));
					if (itemId != 8013)
						player.animate(new Animation(767));
					stage = 2;
				} else if (stage == 2) {
					player.setFreezeDelay(-1);
					if (!(player.getControlerManager().getControler() instanceof EdgevillePvPControler))
						player.getControlerManager().forceStop();
					stop();
				}
			}
		}, 2, 1);
		return true;
	}

	public static void teleControlersCheck(Player player, WorldTile teleTile) {
		if (WildernessControler.isAtWild(teleTile))
			player.getControlerManager().startControler("WildernessControler");
		else if (RequestController.inWarRequest(player))
			player.getControlerManager().startControler("clan_wars_request");
		else if (FfaZone.inArea(player))
			player.getControlerManager().startControler("clan_wars_ffa");
	}

	public static void sendGroupLunarTeleport(Player player, WorldTile teleTile) {

	}

	public static void useEctoPhial(final Player player, Item item) {
		player.lock(6);
		player.getPackets().sendGameMessage("You empty the ectoplasm onto the floor...");
		player.gfx(new Graphics(1688));
		player.animate(new Animation(9609));
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				sendTeleportSpell(player, 8939, 8941, 1678, 1679, 0, 0, new WorldTile(3662, 3518, 0), 4, true,
						ITEM_TELEPORT, false);
			}
		}, 6);
	}

	private Magic() {

	}

	/**
	 * Spy player by Tristam.
	 *
	 * @param player the player
	 * @param other  the other
	 */
	public static void spyPlayer(final Player player, final Player other) {
		int start = 1;
		int end = 2;
		if (player.getSkills().getLevel(Skills.MAGIC) < 75) {
			player.getPackets().sendGameMessage("Your Magic level is not high enough for this spell.");
			return;
		}
		if (!player.withinDistance(other, 9)) {
			player.sm(other.getDisplayName() + " is too far away!");
			return;
		}
		if ((Long) player.temporaryAttribute().get("LAST_SPELL") != null
				&& (Long) player.temporaryAttribute().get("LAST_SPELL") + 8250 > Utils.currentTimeMillis()) {
			return;
		}
		if (!Magic.checkRunes1(player, true, Magic.ASTRAL_RUNE, 2, Magic.COSMIC_RUNE, 2, Magic.BODY_RUNE, 5)) {
			return;
		}
		player.stopAll();
		player.faceEntity(other);
		player.animate(new Animation(6293));
		player.gfx(new Graphics(1059));
		other.gfx(new Graphics(736, 0, 100));
		player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 114 : 174, 523);
		player.getInterfaceManager().openGameTab(3);
		player.getPackets().sendIComponentText(523, 103,
				"Viewing stats for <br><img=" + other.getRights() + ">" + other.getDisplayName());
		for (int i = 0; i < 25; i++) {
			player.getPackets().sendIComponentText(523, start, "" + other.getSkills().getLevel(i));
			player.getPackets().sendIComponentText(523, end, "99");
			start += 4;
			end += 4;
		}
		player.getPackets().sendIComponentText(523, 98, "120");
		player.getPackets().sendIComponentText(523, 106, "Hitpoints: " + other.getHitpoints());
		player.getSkills().addXp(Skills.MAGIC, 76);
		player.temporaryAttribute().put("LAST_SPELL", Utils.currentTimeMillis());
		other.sm("Your stats are being spied on by " + player.getDisplayName() + ".");
	}

	/**
	 * Spy npc by Tristam.
	 *
	 * @param player the player
	 * @param npc    the npc
	 */
	public static void SpyNPC(final Player player, final NPC npc) {
		if (player.getSkills().getLevel(Skills.MAGIC) < 66) {
			player.getPackets().sendGameMessage("Your Magic level is not high enough for this spell.");
			return;
		}
		if (!player.withinDistance(npc, 9)) {
			player.sm(npc.getName() + " is too far away!");
			return;
		}
		if ((Long) player.temporaryAttribute().get("LAST_SPELL") != null
				&& (Long) player.temporaryAttribute().get("LAST_SPELL") + 8250 > Utils.currentTimeMillis()) {
			return;
		}
		if (!Magic.checkRunes1(player, true, Magic.COSMIC_RUNE, 1, Magic.ASTRAL_RUNE, 1, Magic.MIND_RUNE, 1)) {
			return;
		}
		player.stopAll();
		player.faceEntity(npc);
		player.animate(new Animation(6293));
		player.gfx(new Graphics(1059));
		npc.gfx(new Graphics(736, 0, 100));
		player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 114 : 174, 522);
		player.getPackets().sendIComponentText(522, 0, npc.getName());
		player.getPackets().sendIComponentText(522, 1, "Combat level: " + npc.getCombatLevel());
		player.getPackets().sendIComponentText(522, 2, "Life Points: " + npc.getHitpoints());
		player.getPackets().sendIComponentText(522, 3, "Max hit: " + npc.getMaxHit());
		player.getPackets().sendIComponentText(522, 4, "Agreesive: " + (npc.checkAgressivity() ? "Yes." : "No."));
		player.getInterfaceManager().openGameTab(3);
		player.getSkills().addXp(Skills.MAGIC, 61);
		player.temporaryAttribute().put("LAST_SPELL", Utils.currentTimeMillis());
	}

	public static final void processDungSpell(Player player, int spellId, int packetId) {
		processDungSpell(player, spellId, -1, packetId);
	}

	public static final void processDungSpell(Player player, int spellId, int slot, int packetId) {
		final Item target = player.getInventory().getItem(slot);
		if (target == null && slot != -1)
			return;
		switch (spellId) {
		case 25:
		case 27:
		case 28:
		case 30:
		case 32: // air bolt
		case 36: // water bolt
		case 37: // earth bolt
		case 41: // fire bolt
		case 42: // air blast
		case 43: // water blast
		case 45: // earth blast
		case 47: // fire blast
		case 48: // air wave
		case 49: // water wave
		case 54: // earth wave
		case 58: // fire wave
		case 61:// air surge
		case 62:// water surge
		case 63:// earth surge
		case 67:// fire surge
		case 34:// bind
		case 44:// snare
		case 59:// entangle
			setCombatSpell(player, spellId);
			break;
		case 65:
			if (player.getSkills().getLevel(Skills.MAGIC) < 94) {
				player.getPackets().sendGameMessage("Your Magic level is not high enough for this spell.");
				return;
			} else if (player.getSkills().getLevel(Skills.DEFENCE) < 40) {
				player.getPackets().sendGameMessage("You need a Defence level of 40 for this spell");
				return;
			}
			Long lastVeng = (Long) player.getTemporaryAttributtes().get("LAST_VENG");
			if (lastVeng != null && lastVeng + 30000 > Utils.currentTimeMillis()) {
				player.getPackets().sendGameMessage("Players may only cast vengeance once every 30 seconds.");
				return;
			}
			if (!checkRunes(player, true, true, 17790, 4, 17786, 2, 17782, 10))
				return;
			player.getSkills().addXp(Skills.MAGIC, 112);
			player.gfx(new Graphics(726, 0, 100));
			player.animate(new Animation(4410));
			player.setVengeance(true);
			player.setVengeance(30000);
			player.getPackets().sendGameMessage("You cast a vengeance.");
			break;
		case 66: // vegeance group
			if (player.getSkills().getLevel(Skills.MAGIC) < 95) {
				player.getPackets().sendGameMessage("Your Magic level is not high enough for this spell.");
				return;
			}
			lastVeng = (Long) player.getTemporaryAttributtes().get("LAST_VENG");
			if (lastVeng != null && lastVeng + 30000 > Utils.currentTimeMillis()) {
				player.getPackets().sendGameMessage("Players may only cast vengeance once every 30 seconds.");
				return;
			}
			if (!checkRunes(player, true, true, 17790, 4, 17786, 3, 17782, 11))
				return;
			int affectedPeopleCount = 0;
			for (int regionId : player.getMapRegionsIds()) {
				List<Integer> playerIndexes = World.getRegion(regionId).getPlayerIndexes();
				if (playerIndexes == null)
					continue;
				for (int playerIndex : playerIndexes) {
					Player p2 = World.getPlayers().get(playerIndex);
					if (p2 == null || p2 == player || p2.isDead() || !p2.hasStarted() || p2.hasFinished()
							|| !p2.withinDistance(player, 4) || !player.getControlerManager().canHit(p2))
						continue;
					if (!p2.isAcceptAid()) {
						player.getPackets().sendGameMessage(p2.getDisplayName() + " is not accepting aid");
						continue;
					} else if (p2.getControlerManager().getControler() != null
							&& p2.getControlerManager().getControler() instanceof DuelArena) {
						continue;
					}
					p2.gfx(new Graphics(725, 0, 100));
					p2.setVengeance(true);
					p2.setVengeance(30000);
					p2.getPackets().sendGameMessage("You have the power of vengeance!");
					affectedPeopleCount++;
				}
			}
			player.getSkills().addXp(Skills.MAGIC, 120);
			player.animate(new Animation(4411));
			player.setVengeance(30000);
			player.getPackets().sendGameMessage("The spell affected " + affectedPeopleCount + " nearby people.");
			break;
		case 53:
			if (player.getSkills().getLevel(Skills.MAGIC) < 68) {
				player.getPackets().sendGameMessage("Your Magic level is not high enough for this spell.");
				return;
			}
			if (!checkRunes(player, true, true, 17790, 1, 17783, 1, 17781, 3))
				return;
			player.lock(2);
			Item[] itemsBefore = player.getInventory().getItems().getItemsCopy();
			for (Item item : player.getInventory().getItems().getContainerItems()) {
				if (item == null)
					continue;
				for (Fill fill : Fill.values()) {
					if (fill.getEmpty() == item.getId())
						item.setId(fill.getFull());
				}
			}
			player.getInventory().refreshItems(itemsBefore);
			player.getSkills().addXp(Skills.MAGIC, 65);
			player.getInterfaceManager().openGameTab(4);
			player.animate(new Animation(4413));
			player.gfx(new Graphics(1061, 0, 150));
			break;
		case 35: // low alch
		case 46: // high alch
			boolean highAlch = spellId == 46;
			if (!Magic.checkSpellLevel(player, (highAlch ? 55 : 21)))
				return;
			if (target.getId() == DungeonConstants.RUSTY_COINS) {
				player.getPackets()
						.sendGameMessage("You can't cast " + (highAlch ? "high" : "low") + " alchemy on gold.");
				return;
			}
			if (target.getDefinitions().isDestroyItem() || ItemConstants.getItemDefaultCharges(target.getId()) != -1
					|| !ItemConstants.isTradeable(target)) {
				player.getPackets().sendGameMessage("You can't convert this item..");
				return;
			}
			if (target.getAmount() != 1 && !player.getInventory().hasFreeSlots()) {
				player.getPackets().sendGameMessage("Not enough space in your inventory.");
				return;
			}
			if (!checkRunes(player, true, true, 17783, highAlch ? 5 : 3, 17791, 1))
				return;
			player.lock(4);
			player.getInterfaceManager().openGameTab(7);
			player.getInventory().deleteItem(target.getId(), 1);
			player.getSkills().addXp(Skills.MAGIC, highAlch ? 25 : 15);
			player.getInventory().addItem(new Item(DungeonConstants.RUSTY_COINS,
					(int) (target.getDefinitions().getValue() * (highAlch ? 0.6D : 0.3D))));
			Item weapon = player.getEquipment().getItem(Equipment.SLOT_WEAPON);
			if (weapon != null && weapon.getName().toLowerCase().contains("staff")) {
				player.animate(new Animation(highAlch ? 9633 : 9625));
				player.gfx(new Graphics(highAlch ? 1693 : 1692));
			} else {
				player.animate(new Animation(713));
				player.gfx(new Graphics(highAlch ? 113 : 112));
			}
			break;
		case 31:// bones to bananas
			if (!Magic.checkSpellLevel(player, 15))
				return;
			else if (!checkRunes(player, true, true, 17791, 1, 17781, 2, 17782, 2))
				return;
			int bones = 0;
			for (int i = 0; i < 28; i++) {
				Item item = player.getInventory().getItem(i);
				if (item == null)
					continue;
				item.setId(18199);
				bones++;
			}
			if (bones != 0) {
				player.getSkills().addXp(Skills.MAGIC, 25);
				player.getInventory().refresh();
			}
			break;
		case 55:
			if (player.getSkills().getLevel(Skills.MAGIC) < 71) {
				player.getPackets().sendGameMessage("Your Magic level is not high enough for this spell.");
				return;
			}
			if (!checkRunes(player, true, true, 17790, 2, 17789, 2))
				return;
			player.animate(new Animation(4411));
			player.gfx(new Graphics(736, 0, 150));
			player.getPoison().reset();
			break;
		case 57:
			if (!Magic.checkSpellLevel(player, 74))
				return;
			else if (!checkRunes(player, true, true, 17790, 2, 17789, 2))
				return;
			affectedPeopleCount = 0;
			for (int regionId : player.getMapRegionsIds()) {
				List<Integer> playerIndexes = World.getRegion(regionId).getPlayerIndexes();
				if (playerIndexes == null)
					continue;
				for (int playerIndex : playerIndexes) {
					Player p2 = World.getPlayers().get(playerIndex);
					if (p2 == null || p2 == player || p2.isDead() || !p2.hasStarted() || p2.hasFinished()
							|| !p2.withinDistance(player, 4))
						continue;
					if (!p2.isAcceptAid())
						continue;
					player.gfx(new Graphics(736, 0, 150));
					p2.getPackets().sendGameMessage("You have been cured of all illnesses!");
					affectedPeopleCount++;
				}
			}
			player.animate(new Animation(4411));
			player.getPackets().sendGameMessage("The spell affected " + affectedPeopleCount + " nearby people.");
			break;
		default:
			if (Settings.DEBUG)
				Logger.log(Magic.class, "Component " + spellId);
			break;
		}
	}

	public static final boolean checkSpellRequirements(Player player, int level, boolean delete, int... runes) {
		return checkSpellRequirements(player, level, delete, false, runes);
	}

	public static final boolean checkSpellRequirements(Player player, int level, boolean delete, boolean dungeoneering,
			int... runes) {
		if (!checkSpellLevel(player, level))
			return false;
		return checkRunes(player, delete, dungeoneering, runes);
	}

	public static boolean checkSpellLevel(Player player, int level) {
		if (player.getSkills().getLevel(Skills.MAGIC) < level
				&& player.getSkills().getLevelForXp(Skills.MAGIC) < level) {
			player.getPackets().sendGameMessage("Your Magic level is not high enough for this spell.");
			return false;
		}
		return true;
	}

	public static boolean hasStaffOfLight(int weaponId) {
		if (weaponId == 15486 || weaponId == 22207 || weaponId == 22209 || weaponId == 22211 || weaponId == 22213)
			return true;
		return false;
	}

	public static final boolean checkRunes(Player player, boolean delete, int... runes) {
		return checkRunes(player, delete, false, runes);
	}

	public static final boolean checkRunes(Player player, boolean delete, boolean dungeoneering, int... runes) {
		int weaponId = player.getEquipment().getWeaponId();
		int shieldId = player.getEquipment().getShieldId();
		int runesCount = 0;
		while (runesCount < runes.length) {
			int runeId = runes[runesCount++];
			int amount = runes[runesCount++];
			if (hasInfiniteRunes(runeId, weaponId, shieldId))
				continue;
			else if (hasStaffOfLight(weaponId) && !containsRune(LAW_RUNE, runes) && !containsRune(NATURE_RUNE, runes)
					&& Utils.random(8) == 0 && runeId != 21773) {
				player.getPackets()
						.sendGameMessage("The power of your staff of light saves some runes from being drained.");
				continue;
			} else if (dungeoneering) {
				if (player.getInventory().containsItem(runeId - 1689, amount))
					continue;
			}
			if (!player.getInventory().containsItem(runeId, amount)) {
				player.getPackets()
						.sendGameMessage("You do not have enough "
								+ ItemDefinitions.getItemDefinitions(runeId).getName().replace("rune", "Rune")
								+ "s to cast this spell.");
				return false;
			}

		}
		if (delete) {
			runesCount = 0;
			while (runesCount < runes.length) {
				int runeId = runes[runesCount++];
				int amount = runes[runesCount++];
				if (hasInfiniteRunes(runeId, weaponId, shieldId))
					continue;
				else if (dungeoneering) {
					int bindedRune = runeId - 1689;
					if (player.getInventory().containsItem(bindedRune, amount)) {
						player.getInventory().deleteItem(bindedRune, amount);
						continue; // won't delete the extra rune anyways.
					}
				}
				player.getInventory().deleteItem(runeId, amount);
			}
		}
		return true;
	}

	private static boolean containsRune(int rune, int[] runes) {
		for (int id : runes) {
			if (rune == id)
				return true;
		}
		return false;
	}

	public static final boolean sendTeleportSpell(final Player player, int upEmoteId, final int downEmoteId,
			int upGraphicId, final int downGraphicId, int level, final double xp, final WorldTile tile, int delay,
			final boolean randomize, final int teleType, int... runes) {
		return sendTeleportSpell(player, upEmoteId, downEmoteId, upGraphicId, downGraphicId, level, xp, tile, delay,
				randomize, teleType, false, runes);
	}

	public static final boolean sendTeleportSpell1(final Player player, int upEmoteId, final int downEmoteId,
			int upGraphicId, final int downGraphicId, int level, final double xp, final WorldTile tile, int delay,
			final boolean randomize, final int teleType, final boolean dung, int... runes) {
		if (player.isLocked())
			return false;
		if (player.getSkills().getLevel(Skills.MAGIC) < level) {
			player.getPackets().sendGameMessage("Your Magic level is not high enough for this spell.");
			return false;
		}
		if (!checkRunes(player, false, dung, runes))
			return false;
		final WorldTile checkTile = tile == null ? player.getHouse().getLocation().getTile() : tile;
		if (teleType == MAGIC_TELEPORT) {
			if (!player.getControlerManager().processMagicTeleport(checkTile))
				return false;
		} else if (teleType == ITEM_TELEPORT) {
			if (!player.getControlerManager().processItemTeleport(checkTile))
				return false;
		} else if (teleType == OBJECT_TELEPORT) {
			if (!player.getControlerManager().processObjectTeleport(checkTile))
				return false;
		} else if (teleType == JEWERLY_TELEPORT) {
			if (!player.getControlerManager().processJewerlyTeleport(checkTile))
				return false;
		}
		checkRunes(player, true, dung, runes);
		player.stopAll();
		if (upEmoteId != -1)
			player.animate(new Animation(upEmoteId));
		if (upGraphicId != -1)
			player.gfx(new Graphics(upGraphicId));
		if (teleType == MAGIC_TELEPORT)
			player.getPackets().sendSound(5527, 0, 2);
		player.lock(3 + delay);
		WorldTasksManager.schedule(new WorldTask() {

			boolean removeDamage;

			@Override
			public void run() {
				if (!removeDamage) {
					WorldTile teleTile = checkTile;
					if (randomize) {
						// attemps to randomize tile by 4x4 area
						for (int trycount = 0; trycount < 10; trycount++) {
							teleTile = new WorldTile(checkTile, 2);
							if (World.isTileFree(checkTile.getPlane(), teleTile.getX(), teleTile.getY(),
									player.getSize()))
								break;
							teleTile = checkTile;
						}
					}
					player.setNextWorldTile(teleTile);
					player.getControlerManager().magicTeleported(teleType);
					if (xp != 0)
						player.getSkills().addXp(Skills.MAGIC, xp);
					if (downEmoteId != -1)
						player.animate(new Animation(downEmoteId == -2 ? -1 : downEmoteId));
					if (downGraphicId != -1)
						player.gfx(new Graphics(downGraphicId));
					if (teleType == MAGIC_TELEPORT) {
						player.getPackets().sendSound(5524, 0, 2);
						player.setNextFaceWorldTile(
								new WorldTile(teleTile.getX(), teleTile.getY() - 1, teleTile.getPlane()));
						player.setDirection(6);
					}
					if (tile == null && !player.getHouse().isArriveInPortal())
						player.getHouse().enterMyHouse();
					else if (player.getControlerManager().getControler() == null)
						teleControlersCheck(player, teleTile);
					removeDamage = true;
				} else {
					player.resetReceivedDamage();
					stop();
				}
			}
		}, delay, 0);
		return true;
	}

}
