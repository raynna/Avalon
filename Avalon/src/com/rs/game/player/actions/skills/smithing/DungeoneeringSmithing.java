package com.rs.game.player.actions.skills.smithing;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;

/**
 * 
 * @author Cjay0091
 * 
 */
public class DungeoneeringSmithing extends Action {

	private static final int FORGING_INTERFACE = 300, DUNG_INTERFACE = 934;
	public static final int HAMMER = 2347, DUNG_HAMMER = 17883;

	public static final int[][] COMPONENTS =
	{
	{ 18, 26, 34, 42, 50, 58, 66, 74, 82, 90, 98, 106, 114, 122, 130, 138, 146, 154, 162, 170, 178, 186, 194, 202, 210, 218, 226, 234, 242, 267 },
	{ 116, 112, 28, 33, 38, 43, 48, 53, 58, 63, 68, 72, 77, 82, 87, 92, 97, 102, 107 } };
	public static final int[][] BARS =
	{
	{ 2349, 2351, 2353, 2359, 2361, 2363 },
	{ 17650, 17652, 17654, 17656, 17658, 17660, 17662, 17664, 17666, 17668 } };
	private static final byte[][] BASE_LEVEL =
	{
	{ 1, 15, 30, 50, 70, 85 },
	{ 1, 10, 20, 30, 40, 50, 60, 70, 80, 90 } };
	private static final int[][] LEVEL_INCREMENT =
	{
	{ 0, 1, 2, 3, 3, 4, 4, 4, 4, 2, 6, 5, 5, 6, 6, 7, 7, 8, 19, 9, 9, 10, 11, 12, 13, 14, 16, 16, 18, 5 },
	{ 1, 1, 1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 7, 7, 8, 8, 9 } };
	private static final int[][] BAR_DEPLETION =
	{
	{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 2, 1, 1, 3, 3, 3, 3, 2, 3, 3, 3, 5, 1 },
	{ 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5 } };
	private static final String[][] NAMES =
	{
		{
			"Dagger",
			"Hatchet",
			"Mace",
			"Med Helm",
			"Crossbow Bolts",
			"Sword",
			"Dart Tip",
			"Nails",
			"Wire",
			"Split",
			"Studs",
			"Arrow Tips",
			"Scimitar",
			"Crossbow Limbs",
			"Longsword",
			"Throwing Knife",
			"Full Helm",
			"Square Shield",
			"Bullseye Lantern",
			"Grapple Tip",
			"Warhammer",
			"Battleaxe",
			"Chainbody",
			"Kiteshield",
			"Claws",
			"2H Sword",
			"Plateskirt",
			"Platelegs",
			"Platebody",
			"Pickaxe" },
		{
			"Arrowtips",
			"Dagger",
			"Boots",
			"Gauntlets",
			"Hatchet",
			"Pickaxe",
			"Warhammer",
			"Rapier",
			"Longsword",
			"Full Helm",
			"Battleaxe",
			"Kiteshield",
			"Chainbody",
			"Platelegs",
			"Plateskirt",
			"Spear",
			"Maul",
			"Two-handed Sword",
			"Platebody" }, };

	public static final int[][] BUILDS =
	{
	{ 1205, 1351, 1422, 1139, 877, 1277, 819, 4819, 1794, -1, -1, 39, 1321, 9420, 1291, 864, 1155, 1173, -1, -1, 1337, 1375, 1103, 1189, 3095, 1307, 1087, 1075, 1117, 1265 },
	{ 1203, 1349, 1420, 1137, 9377, 1279, 820, 4820, -1, 7225, -1, 40, 1323, 9423, 1293, 863, 1153, 1175, 4540, -1, 1335, 1363, 1101, 1191, 3096, 1309, 1081, 1067, 1115, 1267 },
	{ 1207, 1353, 1424, 1141, 9378, 1281, 821, 1539, -1, -1, 2370, 41, 1325, 9425, 1295, 865, 1157, 1177, 4544, -1, 1339, 1365, 1105, 1193, 3097, 1311, 1083, 1069, 1119, 1269 },
	{ 1209, 1355, 1428, 1143, 9379, 1285, 822, 4822, -1, -1, -1, 42, 1329, 9427, 1299, 866, 1159, 1181, -1, 9416, 1343, 1369, 1109, 1197, 3099, 1315, 1085, 1071, 1121, 1273 },
	{ 1211, 1357, 1430, 1145, 9380, 1287, 823, 4823, -1, -1, -1, 43, 1331, 9429, 1301, 867, 1161, 1183, -1, -1, 1345, 1371, 1111, 1199, 3100, 1317, 1091, 1073, 1123, 1271 },
	{ 1213, 1359, 1432, 1147, 9381, 1289, 824, 4824, -1, -1, -1, 44, 1333, 9431, 1303, 868, 1163, 1185, -1, -1, 1347, 1373, 1113, 1201, 3101, 1319, 1093, 1079, 1127, 1275 },
	{ 17885, 16757, 16339, 16273, 16361, 16295, 17019, 16935, 16383, 16691, 15753, 17341, 16713, 16669, 16647, 17063, 16405, 16889, 17239 },
	{ 17890, 16765, 16341, 16275, 16363, 16297, 17021, 16937, 16385, 16693, 15755, 17343, 16715, 16671, 16649, 17071, 16407, 16891, 17241 },
	{ 17895, 16773, 16343, 16277, 16365, 16299, 17023, 16939, 16387, 16695, 15757, 17345, 16717, 16673, 16651, 17079, 16409, 16893, 17243 },
	{ 17900, 16781, 16345, 16279, 16367, 16301, 17025, 16941, 16389, 16697, 15759, 17347, 16719, 16675, 16653, 17087, 16411, 16895, 17245 },
	{ 17905, 16789, 16347, 16281, 16369, 16303, 17027, 16943, 16391, 16699, 15761, 17349, 16721, 16677, 16655, 17095, 16413, 16897, 17247 },
	{ 17910, 16797, 16349, 16283, 16371, 16305, 17029, 16945, 16393, 16701, 15763, 17351, 16723, 16679, 16657, 17103, 16415, 16899, 17249 },
	{ 17915, 16805, 16351, 16285, 16373, 16307, 17031, 16947, 16395, 16703, 15765, 17353, 16725, 16681, 16659, 17111, 16417, 16901, 17251 },
	{ 17920, 16813, 16353, 16287, 16375, 16309, 17033, 16949, 16397, 16705, 15767, 17355, 16727, 16683, 16661, 17119, 16419, 16903, 17253 },
	{ 17925, 16821, 16355, 16289, 16377, 16311, 17035, 16951, 16399, 16707, 15769, 17357, 16729, 16685, 16663, 17127, 16421, 16905, 17255 },
	{ 17930, 16829, 16357, 16291, 16379, 16313, 17037, 16953, 16401, 16709, 15771, 17359, 16731, 16687, 16665, 17135, 16423, 16907, 17257 } };

	private final int index;
	private int cycles, type;
	private boolean dungeoneering;

	public DungeoneeringSmithing(int index, int cycles, boolean dungeoneering) {
		this.index = index;
		this.cycles = cycles;
		this.dungeoneering = dungeoneering;
	}

	@Override
	public boolean start(Player player) {
		type = (int) player.getTemporaryAttributtes().get("FORGE_TYPE");
		int levelRequirement = BASE_LEVEL[dungeoneering ? 1 : 0][type] + LEVEL_INCREMENT[dungeoneering ? 1 : 0][index];
		if (levelRequirement > 99)
			levelRequirement = 99;
		if (player.getSkills().getLevel(Skills.SMITHING) < levelRequirement) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You need a Smithing level of " + levelRequirement + " to forge this bar.");
			return false;
		}
		int calculatedAmount = player.getInventory().getAmountOf(BARS[dungeoneering ? 1 : 0][type]) / BAR_DEPLETION[dungeoneering ? 1 : 0][index];
		if (calculatedAmount == 0)
			return false;

		if (cycles > calculatedAmount)
			cycles = calculatedAmount;

		return true;
	}

	@Override
	public boolean process(Player player) {
		return cycles > 0;
	}

	@Override
	public int processWithDelay(Player player) {
		cycles--;

		player.animate(new Animation(player.getInventory().containsOneItem(14112, 14104) ? 3923 : 898));
		player.gfx(new Graphics(2123));

		int depletedBars = BAR_DEPLETION[dungeoneering ? 1 : 0][index], forgedAmount = getForgedAmount(index, dungeoneering);

		if (forgedAmount != 1)
			forgedAmount *= 1;

		int barId = BARS[dungeoneering ? 1 : 0][type];
		player.getInventory().deleteItem(new Item(barId, depletedBars));
		if (!player.getInventory().addItem(new Item(BUILDS[type + (dungeoneering ? 6 : 0)][index], forgedAmount))) {
			player.getInventory().addItem(new Item(barId, depletedBars));
			return -1;
		}

		player.getSkills().addXp(Skills.SMITHING, ((dungeoneering ? 10 : 12.5) * (type + 1)) * depletedBars);

		if (cycles > 0)
			return 3;
		return -1;// stops the action
	}

	@Override
	public void stop(Player player) {
		setActionDelay(player, 3);
	}

	public static void sendForgingInterface(Player player, int type, boolean dungeoneering) {
		int baseLevel = BASE_LEVEL[dungeoneering ? 1 : 0][type];
		int currentLevel = player.getSkills().getLevel(Skills.SMITHING);
		if (currentLevel < baseLevel) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You need a Smithing level of " + baseLevel + " to forge this bar.");
			return;
			
			
			/*(fletch.getSelected() == KNIFE && !player.getInventory().containsItem(14111, amount)
					&& !player.getInventory().containsItem(14103, amount))*/
			
		} else if (!player.getInventory().containsItemToolBelt(dungeoneering ? DUNG_HAMMER : HAMMER)
				&& !player.getInventory().containsOneItem(14112, 14104)) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You need a hammer to work the metal with.");
			return;
		}
		player.getTemporaryAttributtes().put("FORGE_TYPE", type);

		for (int index = 0; index < BUILDS[type + (dungeoneering ? 6 : 0)].length; index++) {
			int componentBase = COMPONENTS[dungeoneering ? 1 : 0][index];
			int build = BUILDS[type + (dungeoneering ? 6 : 0)][index];
			if (build == -1) {
				continue;
			}
			if (dungeoneering) {
				int componentId = index == 0 ? 22 : index == 1 ? 23 : (14 + index * 5);

				player.getPackets().sendUnlockIComponentOptionSlots(DUNG_INTERFACE, componentId, 0, 5, 0, 1, 2, 3);
				player.getPackets().sendInterSetItemsOptionsScript(DUNG_INTERFACE, componentId, 143 + index, 4, 5, "Make-1", "Make-5", "Make-X", "Make-All");

				player.getPackets().sendHideIComponent(DUNG_INTERFACE, componentBase - (index >= 11 ? 2 : 0), true);
				player.getPackets().sendItems(143 + index, new Item[]
				{ new Item(build, getForgedAmount(index, true)) });

				player.getPackets().sendIComponentText(DUNG_INTERFACE, componentBase - (index >= 11 ? 1 : 2), (currentLevel >= LEVEL_INCREMENT[1][index] + baseLevel ? "<col=FFFFFF>" : "") + NAMES[1][index]);
				player.getPackets().sendIComponentText(DUNG_INTERFACE, componentBase - (index >= 11 ? 0 : 1), (player.getInventory().getAmountOf(BARS[1][type]) >= BAR_DEPLETION[1][index] ? "<col=00FF00>" : "") + BAR_DEPLETION[1][index] + " Bars");
			} else {
				setComponents(player, componentBase, dungeoneering);
				player.getPackets().sendItemOnIComponent(FORGING_INTERFACE, componentBase, build, getForgedAmount(index, false));
				player.getPackets().sendIComponentText(FORGING_INTERFACE, componentBase + 1, (currentLevel >= LEVEL_INCREMENT[0][index] + baseLevel ? "<col=FFFFFF>" : "") + NAMES[0][index]);
				player.getPackets().sendIComponentText(FORGING_INTERFACE, componentBase + 2, (player.getInventory().getAmountOf(BARS[0][type]) >= BAR_DEPLETION[0][index] ? "<col=00FF00>" : "") + BAR_DEPLETION[dungeoneering ? 1 : 0][index] + " Bars");
			}
		}
		if (!dungeoneering)
			player.getPackets().sendIComponentText(FORGING_INTERFACE, 14, ItemDefinitions.getItemDefinitions(BARS[0][type]).getName().replace(" bar", "") + " Smithing");
		player.getInterfaceManager().sendInterface(dungeoneering ? DUNG_INTERFACE : FORGING_INTERFACE);
	}

	private static void setComponents(Player player, int componentBase, boolean dungeoneering) {
		for (int i = -1; i < 7; i++) {
			player.getPackets().sendHideIComponent(dungeoneering ? DUNG_INTERFACE : FORGING_INTERFACE, componentBase + i, false);
		}
	}

	private static int getForgedAmount(int index, boolean dungeoneering) {
		if (dungeoneering) {
			if (index == 0)
				return 20;
			return 1;
		}

		switch (index) {
		case 4:
		case 6:
		case 10:
		case 15:
			return 10;
		case 7:
		case 11:
			return 15;
		default:
			return 1;
		}
	}
}
