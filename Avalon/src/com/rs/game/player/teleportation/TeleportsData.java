package com.rs.game.player.teleportation;

import com.rs.game.WorldTile;

//TODO: Add Guild teleports page & options
public class TeleportsData {

	public enum TeleportStore {
		
		ALKHARID(new WorldTile(3293, 3184, 0)),

		ARDOUGNE(new WorldTile(2663, 3305, 0)),

		BURTHORPE(new WorldTile(2895, 3546, 0)),

		CAMELOT(new WorldTile(2725, 3485, 0)),

		CATHERBY(new WorldTile(2809, 3435, 0)),

		CANIFIS(new WorldTile(3496, 3488, 0)),

		DRAYNOR(new WorldTile(3103, 3249, 0)),

		EDGEVILLE(new WorldTile(3086, 3495, 0)),

		FALADOR(new WorldTile(2964, 3379, 0)),

		KARAMJA(new WorldTile(2945, 3146, 0)),

		LUMBRIDGE(new WorldTile(3222, 3218, 0)),

		LUNAR(new WorldTile(2100, 3914, 0)),

		NEITIZNOT(new WorldTile(2310, 3781, 0)),

		PISCATORIS(new WorldTile(2335, 3689, 0)),

		RIMMINGTON(new WorldTile(2955, 3224, 0)),

		SHILOVILLAGE(new WorldTile(2852, 2960, 0)),

		TREEGNOME(new WorldTile(2462, 3435, 0)),

		VARROCK(new WorldTile(3212, 3424, 0)),

		YANILLE(new WorldTile(2605, 3093, 0)),
		
		
		// MINING LOCATIONS
		ALKHARID_MINING(new WorldTile(3299, 3277, 0)),
		FALADOR_MINING(new WorldTile(3058, 9777, 0)),
		TZHAAR_MINING(new WorldTile(4784, 5154, 0)),
		YANILLE_MINING(new WorldTile(2626, 3128, 0)),
		RUNE_WILDY_MINING(new WorldTile(3057, 3881, 0)),
		HERO_GUILD_MINING(new WorldTile(2895, 9910, 0)),
		CRANDOR_MINING(new WorldTile(2847, 3295, 0)),
		GEM_ROCK_MINING(new WorldTile(2825, 2994, 0)),

		// SMITHING LOCATIONS
		ALKHARID_FURNACE(new WorldTile(3278, 3171, 0)),
		WILDY_FURNACE(new WorldTile(3212, 3724, 0)),
		NEIT_FURNACE(new WorldTile(2336, 3805, 0)),

		// FISHING LOCATIONS
		CATHERBY_FISHING(new WorldTile(2809, 3434, 0)),
		DRAYNOR_FISHING(new WorldTile(3091, 3228, 0)),
		FISHING_GUILD_FISHING(new WorldTile(2596, 3411, 0)),

		// COOKING LOCATIONS
		CATHERBY_RANGE(new WorldTile(2816, 3437, 0)),
		CCOOKS_GUILD_RANGE(new WorldTile(3143, 3441, 0)),

		// WOODCUTTING LOCATIONS
		CAMELOT_WOODCUTTING(new WorldTile(2727, 3477, 0)),
		GE_WOODCUTTING(new WorldTile(3216, 3502, 0)),
		ETCETERIA_WOODCUTTING(new WorldTile(2605, 3893, 0)),
		PORT_SARIM_WOODCUTTING(new WorldTile(2942, 3227, 0)),

		// FARMING LOCATIONS
		CATHERBY_PATCHES(new WorldTile(2806, 3463, 0)),
		PORT_FATASS_PATCHES(new WorldTile(3609, 3534, 0)),
		ARDY_PATCHES(new WorldTile(2676, 3375, 0)),
		YANILLE_PATCHES(new WorldTile(2577, 3099, 0)),
		LLETYA_PATCHES(new WorldTile(2346, 3167, 0)),
		FALADOR_SOUTH_PATCHES(new WorldTile(3050, 3304, 0)),

		// AGILITY LOCATIONS
		GNOME_COURSE(new WorldTile(2469, 3438, 0)),
		// BARB AGILITY
		BARB_COURSE(new WorldTile(2542, 3569, 0)),
		// WILDY AGILITY
		WILDY_COURSE(new WorldTile(2998, 3915, 0)),
		// PYRMID AGILITY
		PYARMID_COURSE(new WorldTile(3356, 2827, 0)),
		// APE ATOLL AGILITY
		APE_COURSE(new WorldTile(2756, 2744, 0)),
		
		// THIEVING LOCATIONS
		ARDOUGNE_STALLS(new WorldTile(2663, 3305, 0)),
		DRAYNOR_STALLS(new WorldTile(3081, 3250, 0)),

		// RUNECRAFTING LOCATIONS
		AIR_ALTAR(new WorldTile(3128, 3408, 0)),

		MIND_ALTAR(new WorldTile(2980, 3512, 0)),

		WATER_ALTAR(new WorldTile(3185, 3163, 0)),

		EARTH_ALTAR(new WorldTile(3304, 3474, 0)),

		FIRE_ALTAR(new WorldTile(3312, 3253, 0)),

		BODY_ALTAR(new WorldTile(3055, 3444, 0)),

		COSMIC_ALTAR(new WorldTile(2408, 4379, 0)),

		CHAOS_ALTAR(new WorldTile(3060, 3588, 0)),

		ASTRAL_ALTAR(new WorldTile(2151, 3864, 0)),

		NATURE_ALTAR(new WorldTile(2869, 3021, 0)),

		LAW_ALTAR(new WorldTile(2857, 3379, 0)),

		DEATH_ALTAR(new WorldTile(1863, 4639, 0)),

		BLOOD_ALTAR(new WorldTile(3561, 9779, 0)),
		OURANIA_ALTAR(new WorldTile(3271, 4859, 0)),
		ABYSS(new WorldTile(3062, 4819, 0)),
		
		HUNTER_ISLAND(new WorldTile(2596, 2906, 0)),
		FALCONRY(new WorldTile(2371, 3622, 0)),
		GRENWALL(new WorldTile(2203,3228,0)),
		

		// MONSTER TELEPORTS
		ROCK_CRABS(new WorldTile(2673, 3709, 0)),
		GOBLINS(new WorldTile(1861, 5224, 0)),
		MINOTAURS(new WorldTile(1875, 5216, 0)),
		YAKS(new WorldTile(2323, 3804, 0)),
		OGRES(new WorldTile(2492, 3097, 0)),
		OGRECAGE(new WorldTile(2528, 3370, 0)),
		COCKROACHES(new WorldTile(3077, 3461, 0)),
		JUNG_WYRM(new WorldTile(2447, 2891, 0)),
		DESERT_WYRM(new WorldTile(3366, 3156, 0)),
		ICE_WYRM(new WorldTile(3435, 5647, 0)),
		MONKEY_GUARD(new WorldTile(2796, 2798, 1)),

		// DUNGEON/SLAYER TELEPORTS
		SLAYER_TOWER(new WorldTile(3424, 3537, 0)),

		TAVERLY(new WorldTile(2884, 9799, 0)),

		BRIMHAVEN(new WorldTile(2708, 9564, 0)),

		WATERBIRTH(new WorldTile(2443, 10147, 0)),

		SECURITYDUNGEON(new WorldTile(3080, 3422, 0)),

		STRONGHOLD(new WorldTile(3074, 3458, 0)),

		ANCIENTCAVERN(new WorldTile(2512, 3515, 0)),

		FREMENNIK_SLAYER(new WorldTile(2806, 10002, 0)),

		ASGARNIA_ICE(new WorldTile(3003, 9548, 0)),

		KALPHITE(new WorldTile(3486, 9510, 2)),

		TZHAAR(new WorldTile(4672, 5156, 0)),

		JADINKO(new WorldTile(3012, 9273, 0)),

		LIVINGROCK(new WorldTile(3013, 9832, 0)),
		FORINTHRY(new WorldTile(3081, 3648, 0)),
		DUNGEONEERING(new WorldTile(3450, 3712, 0)),
		IKOV_TEMPLE(new WorldTile(2677, 9804, 0)),

		// BOSS TELEPORTS
		GODWARS(new WorldTile(2915, 3746, 0)),
		CORP(new WorldTile(2966, 4383, 2)),
		KQ(new WorldTile(3507, 9493, 0)),
		TD(new WorldTile(2572, 5735, 0)),
		DAG_KINGS(new WorldTile(2544, 10143, 0)),
		LEEUNI(new WorldTile(3358, 9354, 0)),
		PEST_QUEEN(new WorldTile(3512, 9696, 0)),
		NOMAD(new WorldTile(1892, 3178, 0)),
		BORK(new WorldTile(3143, 5545, 0)),
		KBD(new WorldTile(3004, 3849, 0)),
		CHAOS_ELEMENTAL(new WorldTile(3296, 3911, 0)),
		
		KALPHITE_QUEEN(new WorldTile(3506, 9493, 0)),
		
		CORPOREAL_BEAST(new WorldTile(2966, 4383, 2)),
		
		QUEEN_BLACK_DRAGON(new WorldTile(1197, 6499, 0)),

		// MINIGAME TELEPORTS
		FIGHTCAVES(new WorldTile(4612, 5129, 0)),

		FIGHTKILN(new WorldTile(4744, 5168, 0)),

		PESTCONTROL(new WorldTile(2662, 2649, 0)),

		WARRIORGUILD(new WorldTile(2879, 3542, 0)),

		DUELARENA(new WorldTile(3369, 3266, 0)),

		CLANWARS(new WorldTile(2993, 9679, 0)),

		DOMINION(new WorldTile(3744, 6425, 0)),
		
		CASTLE_WARS(new WorldTile(2444, 3090, 0)),

		BARROWS(new WorldTile(3565, 3317, 0)),
		
		// WILDERNESS TELEPORTS
		WEST_DRAGONS(new WorldTile(2982, 3597, 0)),

		EAST_DRAGONS(new WorldTile(3346, 3650, 0)),

		CHAOS_WILDY_ALTAR(new WorldTile(3238, 3622, 0)),

		PORTS1(new WorldTile(3155, 3615, 0)),

		PORTS2(new WorldTile(3219, 3649, 0)),

		PORTS3(new WorldTile(3035, 3727, 0)),

		PORTS4(new WorldTile(3106, 3788, 0)),

		PORTS5(new WorldTile(2974, 3871, 0)),

		PORTS6(new WorldTile(3307, 3916, 0)),

		MAGEBANK(new WorldTile(2539, 4716, 0)),

		WILDYAGILITY(new WorldTile(2998, 3908, 0)),
		
		EDGEVILLE_PVP_INSTANCE(new WorldTile(85, 79, 0)),
		
		;
		

		private WorldTile tile;

		private TeleportStore(WorldTile tile) {
			this.setTile(tile);
		}

		public WorldTile getTile() {
			return tile;
		}

		public void setTile(WorldTile tile) {
			this.tile = tile;
		}

		public static TeleportStore getTele(String name) {
			for (TeleportStore tele : TeleportStore.values()) {
				if (tele.name().toLowerCase().replace("_", " ").contains(name.toLowerCase().replace("_", " ").replace("$", "")))
					return tele;
			}
			return null;
		}
	}

}
