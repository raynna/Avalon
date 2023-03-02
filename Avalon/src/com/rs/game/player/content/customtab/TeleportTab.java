package com.rs.game.player.content.customtab;

import java.util.function.Consumer;

import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.ModernMagicks;
import com.rs.game.player.controlers.WildernessControler;
import com.rs.game.player.teleportation.TeleportsData.TeleportStore;
import com.rs.utils.Utils;

public class TeleportTab extends CustomTab {

	private static int CITY_TELEPORTS = 0, SKILLING = 1, MONSTERS = 2, DUNGEONS = 3, BOSSES = 4, MINIGAMES = 5,
			WILDERNESS = 6, SKILLING_MINING = 13, SKILLING_SMITHING = 14, SKILLING_FISHING = 15, SKILLING_COOKING = 16,
			SKILLING_WOODCUTTING = 17, SKILLING_FARMING = 18, SKILLING_AGILITY = 19, SKILLING_THIEVING = 20,
			SKILLING_RUNECRAFTING = 21, SKILLING_HUNTER = 22;

	public enum TeleportsTab {

		TITLE(25, "Teleports"),

		CTIIES(3, "City Teleports"),

		SKILLING(4, "Skilling Teleports"),

		MONSTERS(5, "Monster Teleports"),

		DUNGEONS(6, "Dungeon/Slayer Teleports"),

		BOSSES(7, "Boss Teleports"),

		MINIGAMES(8, "Minigame Teleports"),

		WILDERNESS(9, "Wilderness Teleports"),

		PREVIOUS(11, "<u>Previous Teleport");
		;

		private int compId;
		private String text;

		private TeleportsTab(int compId, String text) {
			this.compId = compId;
			this.text = text;
		}

	}

	public enum TeleportTabData {

		// CITY TELEPORTS
		ALKHARID("A", 3, CITY_TELEPORTS, "Al Kharid"),

		ARDOUGNE("A", 4, CITY_TELEPORTS, "Ardougne"),

		BURTHORPE("B", 5, CITY_TELEPORTS, "Burthorpe"),

		CAMELOT("C", 6, CITY_TELEPORTS, "Camelot"),

		CATHERBY("C", 7, CITY_TELEPORTS, "Catherby"),

		CANIFIS("C", 8, CITY_TELEPORTS, "Canifis"),

		DRAYNOR("D", 9, CITY_TELEPORTS, "Draynor Village"),

		EDGEVILLE("E", 10, CITY_TELEPORTS, "Edgeville"),

		FALADOR("F", 11, CITY_TELEPORTS, "Falador"),

		KARAMJA("K", 12, CITY_TELEPORTS, "Karamja"),

		LUMBRIDGE("L", 13, CITY_TELEPORTS, "Lumbridge"),

		LUNAR("L", 14, CITY_TELEPORTS, "Lunar Isle"),

		NEITIZNOT("N", 15, CITY_TELEPORTS, "Neitiznot"),

		PISCATORIS("P", 16, CITY_TELEPORTS, "Piscatoris Colony"),

		RIMMINGTON("R", 17, CITY_TELEPORTS, "Rimmington"),

		SHILOVILLAGE("S", 18, CITY_TELEPORTS, "Shilo Village"),

		TREEGNOME("T", 19, CITY_TELEPORTS, "Tree Gnome Stronghold"),

		VARROCK("V", 20, CITY_TELEPORTS, "Varrock"),

		YANILLE("Y", 21, CITY_TELEPORTS, "Yanille"),

		// SKILLING TELEPORTS
		MINING(3, SKILLING, "Mining", true),

		SMITHING(4, SKILLING, "Smithing", true),

		FISHING(5, SKILLING, "Fishing", true),

		COOKING(6, SKILLING, "Cooking", true),

		WOODCUTTING(7, SKILLING, "Woodcutting", true),

		FARMING(8, SKILLING, "Farming", true),

		AGILITY(9, SKILLING, "Agility", true),

		THIEVING(10, SKILLING, "Thieving", true),

		RUNECRAFTING(11, SKILLING, "Runecrafting", true),
		HUNTER(12, SKILLING, "Hunter", true),

		// MINING LOCATIONS
		ALKHARID_MINING(3, SKILLING_MINING, "Al-kharid Mining"), FALADOR_MINING(4, SKILLING_MINING, "Falador Mining"),
		TZHAAR_MINING(5, SKILLING_MINING, "Tzhaar Mining"), YANILLE_MINING(6, SKILLING_MINING, "Yanille Mining"),
		RUNE_WILDY_MINING(7, SKILLING_MINING, true, "Runite Mining"),
		HERO_GUILD_MINING(8, SKILLING_MINING, "Hero's Guild Mining"),
		CRANDOR_MINING(9, SKILLING_MINING, "Crandor Mining"),
		GEM_ROCK_MINING(10, SKILLING_MINING, "Gem Rock Mining"),

		// SMITHING LOCATIONS
		ALKHARID_FURNACE(3, SKILLING_SMITHING, "Al-kharid Furnace"),
		WILDY_FURNACE(4, SKILLING_SMITHING, true, "Wilderness Furnace"),
		NEIT_FURNACE(5, SKILLING_SMITHING, "Neitiznot Furnace"),

		// FISHING LOCATIONS
		CATHERBY_FISHING(3, SKILLING_FISHING, "Catherby Fishing"),
		FISHING_GUILD_FISHING(4, SKILLING_FISHING, "Fishing Guild Fishing"),

		// COOKING LOCATIONS
		CATHERBY_RANGE(3, SKILLING_COOKING, "Catherby Range"),
		COOKS_GUILD_RANGE(4, SKILLING_COOKING, "Cook's Guild Range"),

		// WOODCUTTING LOCATIONS
		CAMELOT_WOODCUTTING(3, SKILLING_WOODCUTTING, "Camelot Woodcutting"),
		GE_WOODCUTTING(4, SKILLING_WOODCUTTING, "Grand Exchange Woodcutting"),
		ETCETERIA_WOODCUTTING(5, SKILLING_WOODCUTTING, "Etceteria Woodcutting"),
		PORT_SARIM_WOODCUTTING(6, SKILLING_WOODCUTTING, "Port Sarim Woodcutting"),

		// FARMING LOCATIONS
		CATHERBY_PATCHES(3, SKILLING_FARMING, "Catherby Patches"),
		PORT_PHATASS_PATCHES(4, SKILLING_FARMING, "Port Phasmatys Patches"),
		ARDY_PATCHES(5, SKILLING_FARMING, "Ardougne Patches"), YANILLE_PATCHES(6, SKILLING_FARMING, "Yanille Patches"),
		LLETYA_PATCHES(7, SKILLING_FARMING, "Letya Patches"),
		FALADOR_SOUTH_PATCHES(8, SKILLING_FARMING, "Falador South Patches"),

		// AGILITY LOCATIONS
		GNOME_COURSE(3, SKILLING_AGILITY, "Gnome Agility Course"),
		BARB_COURSE(4, SKILLING_AGILITY, "Barbarian Agility Course"),
		WILDY_COURSE(5, SKILLING_AGILITY, "Wilderness Agility Course"),
		PYARMID_COURSE(6, SKILLING_AGILITY, "Pyramid Agility Course"),
		// APE ATOLL AGILITY
		APE_COURSE(7, SKILLING_AGILITY, "Ape Atoll Agility Course"),

		// THIEVING LOCATIONS
		ARDOUGNE_STALLS(3, SKILLING_THIEVING, "Ardougne Stalls"),
		DRAYNOR_STALLS(4, SKILLING_THIEVING, "Draynor Stalls"),

		// RUNECRAFTING LOCATIONS
		AIR_ALTAR(3, SKILLING_RUNECRAFTING, "Air Altar", 1),

		MIND_ALTAR(4, SKILLING_RUNECRAFTING, "Mind Altar", 2),

		WATER_ALTAR(5, SKILLING_RUNECRAFTING, "Water Altar", 5),

		EARTH_ALTAR(6, SKILLING_RUNECRAFTING, "Earth Altar", 9),

		FIRE_ALTAR(7, SKILLING_RUNECRAFTING, "Fire Altar", 14),

		BODY_ALTAR(8, SKILLING_RUNECRAFTING, "Body Altar", 20),

		COSMIC_ALTAR(9, SKILLING_RUNECRAFTING, "Cosmic Altar", 27),

		CHAOS_ALTAR(10, SKILLING_RUNECRAFTING, "Chaos Altar", 35),

		ASTRAL_ALTAR(11, SKILLING_RUNECRAFTING, "Astral Altar", 40),

		NATURE_ALTAR(12, SKILLING_RUNECRAFTING, "Nature Altar", 44),

		LAW_ALTAR(13, SKILLING_RUNECRAFTING, "Law Altar", 54),

		DEATH_ALTAR(14, SKILLING_RUNECRAFTING, "Death Altar", 65),

		BLOOD_ALTAR(15, SKILLING_RUNECRAFTING, "Blood Altar", 77),

		OURANIA_ALTAR(16, SKILLING_RUNECRAFTING, "Ourania Altar", 1),
		ABYSS(17, SKILLING_RUNECRAFTING, "Abyss Alters", 1),
		
		HUNTER_ISLAND(3, SKILLING_HUNTER, "Hunter Island", 1),
		FALCONRY(4, SKILLING_HUNTER, "Falconry Training", 43),
		GRENWALL(5, SKILLING_HUNTER, "Grenwall Hunter", 77),

		// MONSTER TELEPORTS
		ROCK_CRABS(3, MONSTERS, "Rock Crabs"),

		GOBLINS(4, MONSTERS, "Goblins"),

		MINOTAURS(5, MONSTERS, "Minotaurs"),

		YAKS(6, MONSTERS, "Yaks"),

		OGRES(7, MONSTERS, "Ogres"),

		OGRECAGE(8, MONSTERS, "Ogre Cage"),

		COCKROACHES(9, MONSTERS, "Cockroaches"),

		MONKEY_GUARD(10, MONSTERS, "Monkey Guards"),

		JUNG_WYRM(11, MONSTERS, "Jungle Strykewyrm"),

		DESERT_WYRM(12, MONSTERS, "Desert Strykewyrm"),

		ICE_WYRM(13, MONSTERS, "Ice Strykewyrm"),

		// DUNGEON/SLAYER TELEPORTS
		SLAYER_TOWER(3, DUNGEONS, "Slayer Tower"),

		TAVERLY(4, DUNGEONS, "Taverly Dungeon"),

		BRIMHAVEN(5, DUNGEONS, "Brimhaven Dungeon"),

		WATERBIRTH(6, DUNGEONS, "Waterbirth Dungeon"),

		SECURITYDUNGEON(7, DUNGEONS, "Stronghold of Security"),

		STRONGHOLD(8, DUNGEONS, "Stronghold of Safety"),

		ANCIENTCAVERN(9, DUNGEONS, "Ancient Cavern"),

		FREMENNIK_SLAYER(10, DUNGEONS, "Fremennik Slayer Dungeon"),

		ASGARNIA_ICE(11, DUNGEONS, "Asgarnia Ice Dungeon"),

		KALPHITE(12, DUNGEONS, "Kalphite Hive"),

		TZHAAR(13, DUNGEONS, "Tzhaar Area"),

		JADINKO(14, DUNGEONS, "Jadinko Lair"),

		LIVINGROCK(15, DUNGEONS, "Living Rock Cavern"),
		FORINTHRY(16, DUNGEONS, true, "Forinthry Dungeon"),

		DUNGEONEERING(17, DUNGEONS, "Dungeoneering"),

		IKOV_TEMPLE(18, DUNGEONS, "Temple of Ikov Dungeon"),
		
		// BOSS TELEPORTS
		GODWARS(3, BOSSES, "Godwars"), CORP(4, BOSSES, "Corpreal Beast"), KQ(5, BOSSES, "Kalphite Queen"),
		TD(6, BOSSES, "Tormented Demons"), DAG_KINGS(7, BOSSES, "Dagannoth kings"), LEEUNI(8, BOSSES, "Leeuni"),
		NOMAD(9, BOSSES, "Nomad"), BORK(10, BOSSES, "Bork"), KBD(11, BOSSES, "King Black Dragon"), CHAOS_ELEMENTAL(12, BOSSES, true, "Chaos Elemental"),

		// MINIGAME TELEPORTS
		FIGHTCAVES(3, MINIGAMES, "Fight Caves"),

		FIGHTKILN(4, MINIGAMES, "Fight Kiln"),

		PESTCONTROL(5, MINIGAMES, "Pest Control"),
		
		Barrows(6, MINIGAMES, "Barrows"),

		WARRIORGUILD(7, MINIGAMES, "Warrior Guild"),

		DUELARENA(8, MINIGAMES, "Duel Arena"),

		CLANWARS(9, MINIGAMES, "Clan Wars"),

		DOMINION(10, MINIGAMES, "Dominion Tower"),
		
		CASTLE_WARS(11, MINIGAMES, "Castle Wars"),

		OURANIA_ALTAR$(12, MINIGAMES, "Ourania Altar"),

		// WILDERNESS TELEPORTS
		WEST_DRAGONS(3, WILDERNESS, true, "West Dragons"),

		EAST_DRAGONS(4, WILDERNESS, true, "East Dragons"),

		CHAOS_WILDY_ALTAR(5, WILDERNESS, true, "Chaos Altar"),

		PORTS1(6, WILDERNESS, true, "13 Ports - Multi"),

		PORTS2(7, WILDERNESS, true, "17 Ports - Multi"),

		PORTS3(8, WILDERNESS, true, "26 Ports"),

		PORTS4(9, WILDERNESS, true, "35 Ports"),

		PORTS5(10, WILDERNESS, true, "44 Ports"),

		PORTS6(11, WILDERNESS, true, "50 Ports - Multi"),

		MAGEBANK(12, WILDERNESS, "Mage Bank"),

		WILDYAGILITY(13, WILDERNESS, true, "Wilderness Agility")

		;

		private int compId;
		private int category;
		private String text;
		private boolean dangerous;
		public transient Consumer<Player> c;
		private String order;
		private boolean skilling;
		private int levelReq;

		private TeleportTabData(String order, int compId, int category, String text) {
			this.order = order;
			this.compId = compId;
			this.category = category;
			this.text = text;
		}

		private TeleportTabData(int compId, int category, String text, boolean skilling) {
			this.compId = compId;
			this.category = category;
			this.text = text;
			this.skilling = skilling;
		}

		private TeleportTabData(int compId, int category, String text) {
			this.compId = compId;
			this.category = category;
			this.text = text;
		}

		private TeleportTabData(int compId, int category, String text, int levelReq) {
			this.compId = compId;
			this.category = category;
			this.text = text;
			this.levelReq = levelReq;
		}

		private TeleportTabData(int compId, int category, boolean dangerous, String text) {
			this.compId = compId;
			this.category = category;
			this.text = text;
			this.dangerous = dangerous;
		}

		private TeleportTabData(int compId, int category, String text, Consumer<Player> c) {
			this.compId = compId;
			this.category = category;
			this.text = text;
			this.c = c;
		}

		private TeleportTabData(int compId, String text) {
			this.compId = compId;
			this.text = text;
		}

		private TeleportTabData(int compId, String text, Consumer<Player> c) {
			this.compId = compId;
			this.text = text;
			this.c = c;
		}

	}

	public static void open(Player player) {
		sendComponents(player);
		for (int i = 3; i <= 22; i++)
			player.getPackets().sendHideIComponent(3002, i, true);
		for (int i = 28; i <= 56; i++)
			player.getPackets().sendHideIComponent(3002, i, true);
		player.getTemporaryAttributtes().remove("ACHIEVEMENTTAB");
		player.getTemporaryAttributtes().remove("DANGEROUSTELEPORT");
		player.getTemporaryAttributtes().remove("TELEPORTTYPE");
		player.getTemporaryAttributtes().put("CUSTOMTAB", 1);
		player.getPackets().sendHideIComponent(3002, BACK_BUTTON, false);
		player.getPackets().sendHideIComponent(3002, FORWARD_BUTTON, false);
		player.getPackets().sendSpriteOnIComponent(3002, GREEN_STAR_COMP, GREEN_HIGHLIGHTED);
		for (TeleportsTab store : TeleportsTab.values()) {
			if (store != null) {
				player.getPackets().sendHideIComponent(3002, store.compId, false);
				if (store.text != null) {
					player.getPackets().sendIComponentText(3002, store.compId,
							(store.compId == 25 ? "" : "<col=f4ee42>") + store.text);
				}
			}
		}
	}

	private static void sendDangerousTeleport(Player player, WorldTile tile) {
		sendHideComponents(player);
		player.getTemporaryAttributtes().put("DANGEROUSTELEPORT", tile);
		player.getPackets().sendHideIComponent(3002, BACK_BUTTON, false);
		player.getPackets().sendHideIComponent(3002, 3, false);
		player.getPackets().sendHideIComponent(3002, 7, false);
		player.getPackets().sendHideIComponent(3002, 9, false);
		player.getPackets().sendHideIComponent(3002, BLUE_STAR_COMP, false);
		player.getPackets().sendIComponentText(3002, 3,
				"<br><br>This is a <col=BB0404>dangerous</col> teleport, <br>Are you sure you want <br>to teleport?");
		player.getPackets().sendIComponentText(3002, 7, "<col=04BB3B>Yes</col>, I want to teleport.");
		player.getPackets().sendIComponentText(3002, 9, "<col=BB0404>No</col>, I don't want to teleport.");
		player.getPackets().sendIComponentText(3002, 25, "Dangerous!");
		player.getPackets().sendSpriteOnIComponent(3002, BLUE_STAR_COMP, 439);

	}

	private static void sendTeleport(Player player, WorldTile tile, int type) {
		if (WildernessControler.getWildLevel(player) >= 20 && player.isAtWild()) {
			player.getPackets().sendGameMessage("You can't use this teleport deeper than 20 wilderness.");
			openTeleports(player, type);
			return;
		}
		if (player.isInCombat(10000)) {
			player.getPackets().sendGameMessage("You can't use this teleport in combat.");
			return;
		}
		player.getTemporaryAttributtes().remove("PREVIOUSTELEPORT");
		ModernMagicks.sendNormalTeleportSpell(player, -1, tile);
		open(player);
		player.getTemporaryAttributtes().put("PREVIOUSTELEPORT", tile);
	}

	public static void handleButtons(Player player, int compId) {
		Integer type = (Integer) player.temporaryAttribute().get("TELEPORTTYPE");
		WorldTile dangerTile = (WorldTile) player.getTemporaryAttributtes().get("DANGEROUSTELEPORT");
		WorldTile previousTile = (WorldTile) player.getTemporaryAttributtes().get("PREVIOUSTELEPORT");
		if (player.getLockDelay() > Utils.currentTimeMillis())
			return;
		if (dangerTile != null) {
			if (compId == BACK_BUTTON || compId == 9) {
				openTeleports(player, type);
				return;
			}
			if (compId == 7) {
				sendTeleport(player, dangerTile, type);
				return;
			}
		}
		if (compId == BACK_BUTTON) {
			if (type != null && type >= 0) {
				if (type >= SKILLING_MINING) {// if chosen a skill, go back to skilling teleports
					openTeleports(player, 1);
					return;
				} else {
					open(player);
					return;
				}
			}
		}
		if (type != null && type >= 0) {
			for (TeleportTabData store : TeleportTabData.values()) {
				if (store != null) {
					TeleportStore tele = TeleportStore.getTele(store.name());
					if (store.category != type)
						continue;
					if (store.compId == compId) {
						if (store.skilling) {
							openSkillingTeleports(player, compId + 10);
							return;
						}
						if (tele == null) {
							player.message("This teleport is not handled yet.");
							open(player);
							return;
						}
						if (store.dangerous) {
							sendDangerousTeleport(player, tele.getTile());
							return;
						}
						sendTeleport(player, tele.getTile(), type);
					}
				}
			}
		} else {
			player.temporaryAttribute().remove("TELEPORTTYPE");
			if (compId >= 3 && compId <= 15) {// all teleport options
				if (compId == 11) {
					if (previousTile != null) {
						sendTeleport(player, previousTile, -1);
						return;
					} else {
						player.getPackets().sendGameMessage("You don't have any previous teleport location.");
						return;
					}
				}
				openTeleports(player, compId - 3);
				return;
			}
			switch (compId) {
			case FORWARD_BUTTON:
				SettingsTab.open(player);
				break;
			case BACK_BUTTON:
				JournalTab.open(player);
				break;
			default:
				break;
			}
		}
	}

	private static void openSkillingTeleports(Player player, int type) {
		sendComponents(player);
		for (int i = 3; i <= 15; i++)
			player.getPackets().sendHideIComponent(3002, i, true);
		player.getTemporaryAttributtes().remove("DANGEROUSTELEPORT");
		player.getTemporaryAttributtes().put("TELEPORTTYPE", type);
		player.getPackets().sendHideIComponent(3002, 27, true);
		player.getPackets().sendSpriteOnIComponent(3002, GREEN_STAR_COMP, GREEN_HIGHLIGHTED);
		for (TeleportTabData store : TeleportTabData.values()) {
			if (store != null) {
				if (store.category != type && store.category != -1)
					continue;
				player.getPackets().sendHideIComponent(3002, store.compId, false);
				if (store.text != null) {
					player.getPackets().sendIComponentText(3002, store.compId,
							(store.order != null ? store.order + "-" : "")
									+ (store.dangerous ? "<col=BB0404>" : "<col=f4ee42>") + store.text
									+ (store.dangerous ? " - Dangerous!" : "<col=f4ee42>")
									+ (store.levelReq > 0 ? " - Level: " + store.levelReq : ""));
					player.getPackets().sendIComponentText(3002, 25,
							(type == 13 ? "Mining"
									: type == 14 ? "Smithing"
											: type == 15 ? "Fishing"
													: type == 16 ? "Cooking"
															: type == 17 ? "Woodcutting"
																	: type == 18 ? "Farming"
																			: type == 19 ? "Agility"
																					: type == 20 ? "Thieving"
																							: type == 22 ? "Hunter"
																									: "Runecrafting"));
				}
			}
		}
	}

	public static void openTeleports(Player player, int type) {
		sendComponents(player);
		for (int i = 3; i <= 15; i++)
			player.getPackets().sendHideIComponent(3002, i, true);
		player.getTemporaryAttributtes().remove("DANGEROUSTELEPORT");
		player.getTemporaryAttributtes().put("TELEPORTTYPE", type);
		player.getPackets().sendHideIComponent(3002, 27, true);
		player.getPackets().sendSpriteOnIComponent(3002, GREEN_STAR_COMP, GREEN_HIGHLIGHTED);
		for (TeleportTabData store : TeleportTabData.values()) {
			if (store != null) {
				if (store.category != type && store.category != -1)
					continue;
				player.getPackets().sendHideIComponent(3002, store.compId, false);
				if (store.text != null) {
					player.getPackets().sendIComponentText(3002, store.compId,
							(store.order != null ? store.order + "-" : "")
									+ (store.dangerous ? "<col=BB0404>" : "<col=f4ee42>") + store.text
									+ (store.dangerous ? " - Dangerous!" : "<col=f4ee42>"));
					player.getPackets().sendIComponentText(3002, 25,
							(type == 0 ? "City Teleports"
									: type == 1 ? "Skilling Teleports"
											: type == 2 ? "Monster Teleports"
													: type == 3 ? "Dungeon Teleports"
															: type == 4 ? "Boss Teleports"
																	: type == 5 ? "Minigame Teleports"
																			: "Wilderness Teleports"));

				}
			}
		}
	}

}
