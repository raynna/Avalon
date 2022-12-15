package com.rs;

import java.math.BigInteger;

import com.rs.game.WorldTile;

public final class Settings {

	/**
	 * Launching settings
	 */
	public static boolean DEBUG;
	public static boolean HOSTED;
	public static int PORT_ID;
	public static boolean VPS_HOSTED;
	
	public static boolean ECONOMY;
	public static boolean FREE_TO_PLAY = false;

	/**
	 * Should we send SQL queries such as recent trades, etc.. to database?
	 */
	public static final boolean sqlQueries = true;

	/**
	 * Should DiscordBot be launched
	 */
	public static final boolean discordEnabled = false;
	
	/**
	 * 0 full eco, 1 eco spawn, 2 spawn
	 * */
	public static int ECONOMY_MODE = 0;
	
	/**
	 * price and <- is free if eco mode 1
	 **/
	public static int LOWPRICE_LIMIT = 100000;

	/**
	 * General client and server settings.
	 */
	public static final String SERVER_NAME = "Avalon";
	public static final String FORMAL_SERVER_NAME = "Avalon";
	public static final String HELP_CC_NAME = "help";
	public static final String LASTEST_UPDATE = "";
	public static final String CACHE_PATH = "C:/Users/Andreas/Documents/GitHub/Avalon/Avalon/data/cache";
	public static final int RECEIVE_DATA_LIMIT = 7500;
	public static final int PACKET_SIZE_LIMIT = 7500;
	public static final int CLIENT_BUILD = 718;
	public static final int CUSTOM_CLIENT_BUILD = 1;

	/**
	 * PkP Settings
	 */

	public static final int NORMAL_PKP = 1, MEMBER_PKP = 2;

	/**
	 * General Settings
	 */

	public static final int MAX_IP_ONLINE_AT_A_TIME = 3;

	/**
	 * Link settings
	 */
	public static final String WEBSITE_LINK = "https://avalon-rsps.com";
	public static final String FORUMS_LINK = "https://avalon-rsps.com/forum";
	public static final String GUIDES_LINK = "https://avalon-rsps.com/forum/Forum-Guides";
	public static final String RULES_LINK = "";
	public static final String COMMANDS_LINK = "";
	public static final String ITEMLIST_LINK = "";
	public static final String ITEMDB_LINK = "http://itemdb.biz";
	public static final String VOTE_LINK = "https://avalon-rsps.com/vote";
	public static final String UPDATE_LINK = "https://avalon-rsps.com/forum/Forum-Updates-BTS";
	public static final String DONATE_LINK = "https://avalon-rsps.com/store";
	public static final String NEW_CLIENT_LINK = "";

	/**
	 * Forum integration setting
	 */
	public static boolean FORUM_INTEGRATION = false;

	/**
	 * If the use of the management server is enabled.
	 */
	public static boolean MANAGMENT_SERVER_ENABLED = false;

	/**
	 * Player settings
	 */
	public static final int START_PLAYER_HITPOINTS = 100;
	public static final WorldTile START_PLAYER_LOCATION = new WorldTile(3087, 3496, 0);// (2387,
																						// 4458,
																						// 0);
	public static final WorldTile HOME_PLAYER_LOCATION = new WorldTile(3087, 3496, 0);// (2391,
																						// 4450,
																						// 0);
	public static final WorldTile RESPAWN_LOSE_LOCATION = new WorldTile(4458, 4124, 0);
	public static final WorldTile RESPAWN_WIN_LOCATION = new WorldTile(4451, 4131, 0);
	public static final WorldTile RESPAWN_WIN2_LOCATION = new WorldTile(4451, 4142, 0);
	public static final WorldTile RESPAWN_LOBBY_LOCATION = new WorldTile(4459, 4148, 0);
	public static final WorldTile RESPAWN_PLAYER_LOCATION = new WorldTile(3101, 3494, 0);// (2386,
																							// 4465,
																							// 0);
	public static final long MAX_PACKETS_DECODER_PING_DELAY = 30000;
	public static final double DUNGEONEERING_EXP_RATE = 25;
	public static final int COMBAT_XP_RATE = 200;
	public static int SKILLING_XP_RATE = 50;
	public static final int SUMMONING_XP_RATE = 10;
	public static final int DROP_RATE = 1;
	public static double BONUS_EXP_WEEK_MULTIPLIER = 1.0;
	public static double BONUS_POINTS_WEEK_MULTIPLIER = 1.0;
	public static boolean DOUBLE_DROP = false;

	/**
	 * World settings
	 */
	public static final int WORLD_CYCLE_TIME = 600;

	/**
	 * Music & Emote settings
	 */
	public static final int AIR_GUITAR_MUSICS_COUNT = 50;

	/**
	 * Memory settings
	 */
	public static final int PLAYERS_LIMIT = 2000;
	public static final int LOCAL_PLAYERS_LIMIT = 250;
	public static final int NPCS_LIMIT = Short.MAX_VALUE;
	public static final int LOCAL_NPCS_LIMIT = 250;
	public static final int MIN_FREE_MEM_ALLOWED = 30000000;

	public static String[] Vowels = { "a", "e", "i", "o", "u" };

	/**
	 * Game constants
	 */
	public static final int[] MAP_SIZES = { 104, 120, 136, 168, 72 };

	public static final String GRAB_SERVER_TOKEN = "hAJWGrsaETglRjuwxMwnlA/d5W6EgYWx";
	public static final int[] GRAB_SERVER_KEYS = { 1441, 78700, 44880, 39771, 363186, 44375, 0, 16140, 7316, 271148,
			810710, 216189, 379672, 454149, 933950, 21006, 25367, 17247, 1244, 1, 14856, 1494, 119, 882901, 1818764,
			3963, 3618 };

	public static final BigInteger GRAB_SERVER_PRIVATE_EXPONENT = new BigInteger(
			"95776340111155337321344029627634178888626101791582245228586750697996713454019354716577077577558156976177994479837760989691356438974879647293064177555518187567327659793331431421153203931914933858526857396428052266926507860603166705084302845740310178306001400777670591958466653637275131498866778592148380588481");
	public static final BigInteger GRAB_SERVER_MODULUS = new BigInteger(
			"119555331260995530494627322191654816613155476612603817103079689925995402263457895890829148093414135342420807287820032417458428763496565605970163936696811485500553506743979521465489801746973392901885588777462023165252483988431877411021816445058706597607453280166045122971960003629860919338852061972113876035333");

	public static final BigInteger PRIVATE_EXPONENT = new BigInteger(
			"90587072701551327129007891668787349509347630408215045082807628285770049664232156776755654198505412956586289981306433146503308411067358680117206732091608088418458220580479081111360656446804397560752455367862620370537461050334224448167071367743407184852057833323917170323302797356352672118595769338616589092625");
	public static final BigInteger MODULUS = new BigInteger(
			"102876637271116124732338500663639643113504464789339249490399312659674772039314875904176809267475033772367707882873773291786014475222178654932442254125731622781524413208523465520758537060408541610254619166907142593731337618490879831401461945679478046811438574041131738117063340726565226753787565780501845348613");

	public static String[] UNDEAD_NPCS = { "ghost", "zombie", "revenant", "skeleton", "abberant spectre", "banshee",
			"ghoul", "vampire", "skeletal" };

	public static final int[] UnlockableItems = { 20135, 20137, 20139, 20141, 20143, 20145, 20147, 20149, 20151, 20153,
			20155, 20157, 20159, 20161, 20163, 20165, 20167, 20169, 20171, 20173, 13858, 13861, 13870, 13873, 13884,
			13887, 13890, 13893, 13896, 13899, 13902, 13905, 14484, 19784, 11694, 13738, 13740, 13742, 13744, 15017,
			15018, 15019, 15020, 15220, 23659, 6570 };
	
	public static final String[] DEVELOPERS = { "starter", "bank", "andreas", "manager", "zed"};
	
	public static final String[] MODERATORS = {};
	
	public static final String[] HEADMODERATORS = {};
	
	public static final double CHARM_RATE = 1;
	
	public static final int[] IGNORE_DROPS = {2894, 2896, 2738, 2734, 2617, 2630, 2631,  2743, 2745, 2739, 2741, 2602, 8646, 8645};
	
	public static final WorldTile[] REMOVED_OBJECT_TILES = {new WorldTile(3095, 3489, 0), new WorldTile(3096, 3489, 0)};

	private Settings() {

	}
}
