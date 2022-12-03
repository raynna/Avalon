package com.rs.game.player.content.customshops;

import java.util.ArrayList;
import java.util.List;

import com.rs.Settings;
import com.rs.game.item.Item;
import com.rs.game.player.Player;

public class CustomStoreData {
	
	/**
	 * @Author -Andreas
	 * 2019-11
	 */

	protected int INTERFACE_ID = 3010;

	protected final int COINS = 0, SKILLCAPE = 3;

	public final int[] COMPONENTS = { 25, 31, 37, 43, 49, 55, 61, 67, 73, 79, 85, 91, 97, 103, 109, 115, 121, 127, 133,
			139, 145, 151, 157, 163, 169, 175, 181, 187, 193, 199, 205, 211, 217, 223, 229, 235, 241, 247, 253, 259,
			265, 271, 277, 283, 289, 295, 301, 307, 313, 319, 325, 331, 337, 343, 349, 355, 361, 367, 373, 379, 385,
			391, 397, 403, 409, 415, 421, 427, 433, 439, 445, 451, 457, 463, 469, 475, 481, 487 };

	public static final int GENERAL_STORE = 100;
	public static final int SUPPLY_SHOP = 1;
	public static final int MELEE_SHOP = 2;
	public static final int RANGE_SHOP = 3;
	public static final int MAGIC_SHOP = 4;
	public static final int BARROWS_SHOP = 6;
	public static final int ACCESSORIES = 5;
	public static final int SKILLING_SHOP = 7;
	public static final int SKILLING_SHOP_2 = 8;
	public static final int CONSTRUCTION = 9;
	public static final int SUMMONING = 10;
	public static final int AVALON_POINTS = 101;
	public static final int PK_POINTS = 102;

	public static List<Item> magic = new ArrayList<>();
	public static List<Item> melee = new ArrayList<>();
	public static List<Item> range = new ArrayList<>();
	public static List<Item> supplies = new ArrayList<>();
	protected static List<Item> barrows = new ArrayList<>();
	protected static List<Item> skilling = new ArrayList<>();
	public static List<Item> skilling2 = new ArrayList<>();
	public static List<Item> construction = new ArrayList<>();
	public static List<Item> summoning = new ArrayList<>();
	protected static List<Item> slayer = new ArrayList<>();
	public static List<Item> accessories = new ArrayList<>();
	protected static List<Item> avalonpts = new ArrayList<>();
	protected static List<Item> pkpts = new ArrayList<>();

	public static void init() {
		resetShops();

		pkpts.add(new Item("dragon claws", 60000));
		pkpts.add(new Item("armadyl godsword", 60000));
		pkpts.add(new Item(19784, 70000));
		pkpts.add(new Item("vesta's chainbody", 15000));
		pkpts.add(new Item("vesta's plateskirt", 15000));
		pkpts.add(new Item("statius's full helm", 10000));
		pkpts.add(new Item("statius's platebody", 15000));
		pkpts.add(new Item("statius's platelegs", 15000));
		pkpts.add(new Item("morrigan's coif", 7500));
		pkpts.add(new Item("morrigan's leather body", 10000));
		pkpts.add(new Item("morrigan's leather chaps", 10000));
		pkpts.add(new Item("zuriel's hood", 7500));
		pkpts.add(new Item("zuriel's robe top", 10000));
		pkpts.add(new Item("zuriel's robe bottom", 10000));
		pkpts.add(new Item("vesta's longsword", 22000));
		pkpts.add(new Item("vesta's spear", 12000));
		pkpts.add(new Item("statius's warhammer", 16000));

		accessories.add(new Item("ring of recoil"));
		accessories.add(new Item("ring of duelling (8)"));
		accessories.add(new Item("ring of life"));
		accessories.add(new Item("amulet of power"));
		accessories.add(new Item("amulet of strength"));
		accessories.add(new Item("amulet of glory"));
		accessories.add(new Item("amulet of fury"));
		accessories.add(new Item("berserker necklace"));
		accessories.add(new Item("combat bracelet"));
		accessories.add(new Item("regen bracelet"));
		accessories.add(new Item("berserker ring"));
		accessories.add(new Item("warrior ring"));
		accessories.add(new Item("archers' ring"));
		accessories.add(new Item("seers' ring"));
		accessories.add(new Item(7458));
		accessories.add(new Item(7459));
		accessories.add(new Item(7460));
		accessories.add(new Item(7461));
		accessories.add(new Item(7462));
		accessories.add(new Item("fighter torso"));
		accessories.add(new Item("fighter hat"));
		accessories.add(new Item("mithril defender"));
		accessories.add(new Item("adamant defender"));
		accessories.add(new Item("rune defender"));
		accessories.add(new Item("dragon defender"));
		accessories.add(new Item(6570));
		accessories.add(new Item(23659));

		magic.add(new Item("air rune"));
		magic.add(new Item("earth rune"));
		magic.add(new Item("water rune"));
		magic.add(new Item("fire rune"));
		magic.add(new Item("chaos rune"));
		magic.add(new Item("death rune"));
		magic.add(new Item("blood rune"));

		magic.add(new Item("nature rune"));
		magic.add(new Item("law rune"));
		magic.add(new Item("cosmic rune"));
		magic.add(new Item("astral rune"));
		magic.add(new Item("soul rune"));
		magic.add(new Item("lava rune"));
		magic.add(new Item("mud rune"));

		magic.add(new Item("mist rune"));
		magic.add(new Item("dust rune"));
		magic.add(new Item("smoke rune"));
		magic.add(new Item("armadyl rune"));
		magic.add(new Item("water battlestaff"));
		magic.add(new Item("ancient staff"));
		magic.add(new Item("staff of light"));

		magic.add(new Item("ragefire boots"));
		magic.add(new Item("ahrim's staff"));
		magic.add(new Item("mystic mud staff"));
		magic.add(new Item("master wand"));
		magic.add(new Item("saradomin staff"));
		magic.add(new Item("zamorak staff"));
		magic.add(new Item("guthix staff"));

		magic.add(new Item("mages' book"));
		magic.add(new Item("infinity hat"));
		magic.add(new Item("infinity top"));
		magic.add(new Item("infinity bottoms"));
		magic.add(new Item("saradomin cape"));
		magic.add(new Item("zamorak cape"));
		magic.add(new Item("guthix cape"));

		magic.add(new Item("infinity boots"));
		magic.add(new Item("dagon'hai hat"));
		magic.add(new Item("ghostly hood"));
		magic.add(new Item("wizard hat"));
		magic.add(new Item("enchanted hat"));
		magic.add(new Item("mystic hat"));
		magic.add(new Item("ahrim's hood"));

		magic.add(new Item("mystic boots"));
		magic.add(new Item("dagon'hai robe top"));
		magic.add(new Item("ghostly robe"));
		magic.add(new Item("wizard robe top"));
		magic.add(new Item("enchanted top"));
		magic.add(new Item("mystic robe top"));
		magic.add(new Item("ahrim's robe top"));

		magic.add(new Item("wizard boots"));
		magic.add(new Item("dagon'hai robe bottom"));
		magic.add(new Item("ghostly robe¤1"));
		magic.add(new Item("wizard robe skirt"));
		magic.add(new Item("enchanted robe"));
		magic.add(new Item("mystic robe bottom"));
		magic.add(new Item("ahrim's robe skirt"));

		magic.add(new Item("zamorak robe"));
		magic.add(new Item("zamorak robe¤2"));

		magic.add(new Item("spirit shield"));
		magic.add(new Item("blessed spirit shield"));

		magic.add(new Item("fungal visor"));
		magic.add(new Item("fungal poncho"));
		magic.add(new Item("fungal leggings"));
		magic.add(new Item("grifolic visor"));
		magic.add(new Item("grifolic poncho"));
		magic.add(new Item("grifolic leggings"));
		/*magic.add(new Item("ganodermic visor"));
		magic.add(new Item("ganodermic poncho"));
		magic.add(new Item("ganodermic leggings"));*/
		magic.add(new Item("polypore staff"));
		magic.add(new Item("greater runic staff"));

		magic.add(new Item("zuriel's staff"));
		/*magic.add(new Item("zuriel's hood"));
		magic.add(new Item("zuriel's robe top"));
		magic.add(new Item("zuriel's robe bottom"));

		magic.add(new Item("corrupt zuriel's robe top"));
		magic.add(new Item("corrupt zuriel's robe bottom"));*/

		melee.add(new Item("proselyte sallet"));
		melee.add(new Item("proselyte hauberk"));
		melee.add(new Item("proselyte cuisse"));
		melee.add(new Item("proselyte tasset"));
		melee.add(new Item("berserker helm"));
		melee.add(new Item("warrior helm"));
		melee.add(new Item("helm of neitiznot"));

		melee.add(new Item("rune full helm"));
		melee.add(new Item("rune platebody"));
		melee.add(new Item("rune platelegs"));
		melee.add(new Item("rune plateskirt"));
		melee.add(new Item("rune kiteshield"));
		melee.add(new Item("rune boots"));
		melee.add(new Item("toktz-ket-xil"));

		melee.add(new Item("dragon helm"));
		melee.add(new Item("dragon chainbody"));
		melee.add(new Item("dragon platelegs"));
		melee.add(new Item("dragon plateskirt"));
		melee.add(new Item("dragon sq shield"));
		melee.add(new Item("dragon boots"));
		melee.add(new Item("obsidian cape"));

		melee.add(new Item("rock-shell helm"));
		melee.add(new Item("rock-shell plate"));
		melee.add(new Item("rock-shell legs"));
		melee.add(new Item("granite helm"));
		melee.add(new Item("granite body"));
		melee.add(new Item("granite legs"));
		melee.add(new Item("granite shield"));

		melee.add(new Item("bandos chestplate"));
		melee.add(new Item("bandos tassets"));
		melee.add(new Item("bandos boots"));
		melee.add(new Item("steadfast boots"));
		melee.add(new Item("iron scimitar"));
		melee.add(new Item("rune scimitar"));
		melee.add(new Item("brine sabre"));
		melee.add(new Item("brackish blade"));

		melee.add(new Item("abyssal whip"));
		melee.add(new Item("granite maul"));
		melee.add(new Item("dragon scimitar"));
		melee.add(new Item("dragon dagger (p++)"));
		melee.add(new Item("dragon mace"));
		melee.add(new Item("dragon battleaxe"));
		melee.add(new Item("dragon 2h sword"));
		melee.add(new Item("dragon halberd"));

		melee.add(new Item("dragon spear"));
		melee.add(new Item("dragon longsword"));
		melee.add(new Item("toktz-xil-ak"));
		melee.add(new Item("tzhaar-ket-em"));
		melee.add(new Item("toktz-xil-ek"));
		melee.add(new Item("tzhaar-ket-om"));
		melee.add(new Item("granite mace"));

		/*melee.add(new Item("vesta's chainbody"));
		melee.add(new Item("vesta's plateskirt"));
		melee.add(new Item("statius's full helm"));
		melee.add(new Item("statius's platebody"));
		melee.add(new Item("statius's platelegs"));
		melee.add(new Item("vesta's spear"));
		melee.add(new Item("vesta's longsword"));
		melee.add(new Item("statius's warhammer"));

		melee.add(new Item("corrupt vesta's chainbody"));
		melee.add(new Item("corrupt vesta's plateskirt"));
		melee.add(new Item("corrupt statius's full helm"));
		melee.add(new Item("corrupt statius's platebody"));
		melee.add(new Item("corrupt statius's platelegs"));
		melee.add(new Item("corrupt vesta's spear"));
		melee.add(new Item("corrupt vesta's longsword"));
		melee.add(new Item("corrupt statius's warhammer"));*/

		range.add(new Item("coif"));
		range.add(new Item("leather body"));
		range.add(new Item("hardleather body"));
		range.add(new Item("studded body"));
		range.add(new Item("green d'hide body"));
		range.add(new Item("green d'hide chaps"));
		range.add(new Item("green d'hide vambraces"));

		range.add(new Item("archer helm"));
		range.add(new Item("black d'hide body"));
		range.add(new Item("black d'hide chaps"));
		range.add(new Item("black d'hide vambraces"));
		range.add(new Item("armadyl helmet"));
		range.add(new Item("armadyl chestplate"));
		range.add(new Item("armadyl chainskirt"));

		range.add(new Item("snakeskin bandana"));
		range.add(new Item("snakeskin body"));
		range.add(new Item("snakeskin chaps"));
		range.add(new Item("snakeskin boots"));
		range.add(new Item("karil's coif"));
		range.add(new Item("karil's top"));
		range.add(new Item("karil's skirt"));

		range.add(new Item("royal d'hide body"));
		range.add(new Item("royal d'hide chaps"));

		range.add(new Item("ranger boots"));
		range.add(new Item("robin hood hat"));
		range.add(new Item("glaiven boots"));
		range.add(new Item("ava's accumulator"));
		range.add(new Item("iron knife", 10));
		range.add(new Item("rune knife", 10));
		range.add(new Item("dragon dart", 10));
		range.add(new Item("toktz-xil-ul", 10));

		range.add(new Item("shortbow"));
		range.add(new Item("yew shortbow"));
		range.add(new Item("magic shortbow"));
		range.add(new Item("rune crossbow"));
		range.add(new Item("iron arrow", 10));
		range.add(new Item("adamant arrow", 10));
		range.add(new Item("rune arrow", 10));

		range.add(new Item("dragon arrow", 10));
		range.add(new Item("ruby bolts (e)", 10));
		range.add(new Item("diamond bolts (e)", 10));
		range.add(new Item("dragon bolts (e)", 10));
		range.add(new Item("dark bow"));
		range.add(new Item("hunters' crossbow"));
		range.add(new Item("long kebbit bolts", 10));

		range.add(new Item("zanik's crossbow"));
		range.add(new Item("karil's crossbow"));
		range.add(new Item("bolt rack", 10));

		/*range.add(new Item("morrigan's coif"));
		range.add(new Item("morrigan's leather body"));
		range.add(new Item("morrigan's leather chaps"));*/
		range.add(new Item("morrigan's throwing axe", 10));
		range.add(new Item("morrigan's javelin", 10));

		/*range.add(new Item("corrupt morrigan's coif"));
		range.add(new Item("corrupt morrigan's leather body"));
		range.add(new Item("corrupt morrigan's leather chaps"));*/

		supplies.add(new Item("super strength (4)"));
		supplies.add(new Item("super attack (4)"));
		supplies.add(new Item("super defence (4)"));
		supplies.add(new Item("ranging potion (4)"));
		supplies.add(new Item("magic potion (4)"));
		supplies.add(new Item("super combat potion (4)"));
		supplies.add(new Item("strength potion (4)"));

		supplies.add(new Item("saradomin brew (4)"));
		supplies.add(new Item("super restore (4)"));
		supplies.add(new Item("sanfew serum (4)"));
		supplies.add(new Item("prayer renewal (4)"));
		supplies.add(new Item("super antifire (4)"));
		supplies.add(new Item("overload (4)"));
		supplies.add(new Item("super prayer (4)"));

		supplies.add(new Item("trout"));
		supplies.add(new Item("lobster"));
		supplies.add(new Item("swordfish"));
		supplies.add(new Item("monkfish"));
		supplies.add(new Item("shark"));
		supplies.add(new Item("cavefish"));
		supplies.add(new Item("rocktail"));

		supplies.add(new Item("tuna potato"));
		supplies.add(new Item("cooked karambwan"));
		supplies.add(new Item("pineapple pizza"));
		supplies.add(new Item(10476));// purple sweet
		supplies.add(new Item("teleport to house"));
		supplies.add(new Item("lumber yard teleport"));
		supplies.add(new Item(8015));// bones to peaches

		supplies.add(new Item("potion flask"));

		for (int i = 4708; i < 4740; i += 2)
			barrows.add(new Item(i, 1));
		for (int i = 4745; i < 4760; i += 2)
			barrows.add(new Item(i, 1));
		for (int i = 21736; i < 21761; i += 8)
			barrows.add(new Item(i, 1));

		skilling.add(new Item("hammer"));
		skilling.add(new Item(590));
		skilling.add(new Item("chisel"));
		skilling.add(new Item("knife"));
		skilling.add(new Item("saw"));
		skilling.add(new Item("pestle and mortar"));
		skilling.add(new Item("shears"));
		skilling.add(new Item("small fishing net"));
		skilling.add(new Item("big fishing net"));
		skilling.add(new Item("fly fishing rod"));
		skilling.add(new Item("feather"));
		skilling.add(new Item("fishing rod"));
		skilling.add(new Item("fishing bait"));
		skilling.add(new Item("harpoon"));
		skilling.add(new Item("lobster pot"));
		skilling.add(new Item("needle"));
		skilling.add(new Item("thread"));
		skilling.add(new Item("amulet mould"));
		skilling.add(new Item("bracelet mould"));
		skilling.add(new Item("necklace mould"));
		skilling.add(new Item("ring mould"));
		skilling.add(new Item("tiara mould"));
		skilling.add(new Item("ammo mould"));
		skilling.add(new Item("rake"));
		skilling.add(new Item("seed dibber"));
		skilling.add(new Item("spade"));
		skilling.add(new Item("gardening trowel"));
		skilling.add(new Item("secateurs"));

		skilling.add(new Item("iron pickaxe"));
		skilling.add(new Item("iron hatchet"));
		skilling.add(new Item("rune pickaxe"));
		skilling.add(new Item("rune hatchet"));
		skilling.add(new Item("dragon pickaxe"));
		skilling.add(new Item("dragon hatchet"));

		skilling2.add(new Item("eye of newt"));
		skilling2.add(new Item("unicorn horn"));
		skilling2.add(new Item("limpwurt root"));
		skilling2.add(new Item("red spiders' eggs"));
		skilling2.add(new Item("white berries"));
		skilling2.add(new Item("desert goat horn"));
		skilling2.add(new Item("snape grass"));
		skilling2.add(new Item("wine of zamorak"));
		skilling2.add(new Item("potato cactus"));
		skilling2.add(new Item("crushed nest"));
		skilling2.add(new Item("papaya fruit"));
		skilling2.add(new Item("phoenix feather"));
		skilling2.add(new Item("grenwall spikes"));
		skilling2.add(new Item("ground mud runes"));
		skilling2.add(new Item("morchella mushroom"));
		skilling2.add(new Item(6810));
		skilling2.add(new Item("air talisman"));
		skilling2.add(new Item("mind talisman"));
		skilling2.add(new Item("water talisman"));
		skilling2.add(new Item("earth talisman"));
		skilling2.add(new Item("fire talisman"));
		skilling2.add(new Item("body talisman"));
		skilling2.add(new Item("cosmic talisman"));
		skilling2.add(new Item("chaos talisman"));
		skilling2.add(new Item("astral tiara"));
		skilling2.add(new Item("nature talisman"));
		skilling2.add(new Item("law talisman"));
		skilling2.add(new Item("death talisman"));
		skilling2.add(new Item("blood talisman"));

		summoning.add(new Item("spirit shard pack"));
		summoning.add(new Item("spirit shards"));
		summoning.add(new Item("pouch"));
		summoning.add(new Item("wolf bones"));
		summoning.add(new Item("raw chicken"));
		summoning.add(new Item("spider carcass"));
		summoning.add(new Item("thin snail"));
		summoning.add(new Item("iron ore"));
		summoning.add(new Item("bronze claws"));
		summoning.add(new Item("potato cactus"));
		summoning.add(new Item("vampyre dust"));
		summoning.add(new Item("willow logs"));
		summoning.add(new Item("carved evil turnip"));
		summoning.add(new Item("iron bar"));
		summoning.add(new Item("raw beef"));
		summoning.add(new Item("raw bird meat"));
		summoning.add(new Item("snake hide"));
		summoning.add(new Item("tortoise shell"));
		summoning.add(new Item("banana"));
		summoning.add(new Item("pot of flour"));
		summoning.add(new Item("water orb"));
		summoning.add(new Item("dagannoth hide"));
		summoning.add(new Item("swamp lizard"));
		summoning.add(new Item("unicorn horn"));
		summoning.add(new Item("raw rabbit"));
		summoning.add(new Item("iron platebody"));
		summoning.add(new Item("yak-hide"));
		summoning.add(new Item("steel platebody"));

		construction.add(new Item("steel nails"));
		construction.add(new Item("bolt of cloth"));
		construction.add(new Item("limestone brick"));
		construction.add(new Item("marble block"));
		construction.add(new Item(8784));
	}

	protected List<Item> general = new ArrayList<>();
	protected static List<Item> extraStock = new ArrayList<>();

	protected static void resetShops() {
		magic.clear();
		melee.clear();
		range.clear();
		supplies.clear();
		barrows.clear();
		skilling.clear();
		skilling2.clear();
		construction.clear();
		summoning.clear();
		accessories.clear();
	}

	protected List<Item> getItems(int type, int shop) {
		switch (shop) {
		case MAGIC_SHOP:
			return magic;
		case MELEE_SHOP:
			return melee;
		case RANGE_SHOP:
			return range;
		case SUPPLY_SHOP:
			return supplies;
		case GENERAL_STORE:
			return general;
		case BARROWS_SHOP:
			return barrows;
		case SKILLING_SHOP:
			return skilling;
		case SKILLING_SHOP_2:
			return skilling2;
		case CONSTRUCTION:
			return construction;
		case SUMMONING:
			return summoning;
		case ACCESSORIES:
			return accessories;
		case AVALON_POINTS:
			return avalonpts;
		case PK_POINTS:
			return pkpts;
		}
		return null;
	}

	protected int[][] getShopType(int type) {
		return type == 1 ? PKPointsData.ITEMS : AvalonPointsData.ITEMS;
	}

	protected String getShopTitle(int type) {
		return type == 0 ? CoinStoreData.TITLE
				: type == 1 ? PKPointsData.TITLE : type == 2 ? AvalonPointsData.TITLE : SkillcapeStore.TITLE;
	}

	protected String getShopTitle(int type, int shop) {
		if (type == 0) {
			switch (shop) {
			case MAGIC_SHOP:
				return "Magic store";
			case MELEE_SHOP:
				return "Melee store";
			case RANGE_SHOP:
				return "Range store";
			case SUPPLY_SHOP:
				return "Supplies store";
			case GENERAL_STORE:
				return "General store";
			case BARROWS_SHOP:
				return "Barrows store";
			case SKILLING_SHOP:
				return "Skilling store";
			case SKILLING_SHOP_2:
				return "Skilling store 2";
			case CONSTRUCTION:
				return "Construction store";
			case SUMMONING:
				return "Summoning store";
			case ACCESSORIES:
				return "Accessories store";
			case AVALON_POINTS:
				return "Avalon Point store";
			case PK_POINTS:
				return "Pk Point store";
			}
		}
		return type == 0 ? CoinStoreData.TITLE
				: type == 1 ? PKPointsData.TITLE : type == 2 ? AvalonPointsData.TITLE : SkillcapeStore.TITLE;
	}

	protected int getCurrencySprite(int type, int shop) {
		return shop == PK_POINTS ? PKPointsData.CURRENCY_SPRITE
				: shop == AVALON_POINTS ? AvalonPointsData.CURRENCY_SPRITE : CoinStoreData.CURRENCY_SPRITE;
	}

	protected String getPointsName(int type, int shop) {
		return shop == PK_POINTS ? "Pk Points" : shop == AVALON_POINTS ? Settings.SERVER_NAME + " Points" : "Coins";
	}

	protected void removeCurrency(Player player, int shop, int amount) {
		switch (shop) {
		case PK_POINTS:
			player.setPKP(player.getPKP() - amount);
			break;
		case AVALON_POINTS:
			player.setAvalonPoints(player.getAvalonPoints() - amount);
			break;
		default:
			player.canBuy(amount);
			break;
		}
	}

}
