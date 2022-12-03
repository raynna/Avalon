package com.rs.game.player.dialogues.skilling;

import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.content.SkillsDialogue.ItemNameFilter;
import com.rs.game.player.dialogues.Dialogue;

public class LeatherCraftingD extends Dialogue {

	private static final int DUNG_THREAD = 17447;
	public static final int DUNG_NEEDLE = 17446, NEEDLE = 1733, THREAD = 1734;

	private static final int[] BASE_LEATHER =
	{
		6289,
		1741,
		1743,
		1745,
		2505,
		2507,
		2509,
		24374,
		22451,
		22449,
		22450,
		17424,
		17426,
		17428,
		17430,
		17432,
		17434,
		17436,
		17438,
		17440,
		17442,
		17468,
		17470,
		17472,
		17474,
		17476,
		17478,
		17480,
		17482,
		17484,
		17486,
		10820}; //yak
	private static final double[][] EXPERIENCE =
	{
	{ 45, 35, 35, 50, 53 },
	{ 13.8, 16.25, 18.5, 22, 25, 27, 30, 37 },
	{ 35 },
	{ 62, 126, 124, 186, 248 },
	{ 70, 140, 140, 210, 280 },
	{ 78, 156, 156, 234, 312 },
	{ 86, 172,  172, 258, 334 },
	{ 94, 188, 188, 282 },
	{ 100, 300, 1000 },
	{ 10, 20, 30 },
	{ 50, 150, 500 },
	{ 1, 3, 5, 7, 9 },
	{ 21.6, 23.3, 50, 80.1, 142 },
	{ 30.1, 31.8, 67, 104.7, 184.5 },
	{ 38.6, 40.3, 84, 131.1, 269.5 },
	{ 47.1, 48.8, 101, 156.6, 269.5 },
	{ 55.6, 57.3, 118, 182.1, 312 },
	{ 64.1, 65.8, 135, 207.6, 354.5 },
	{ 72.6, 74.3, 152, 233.1, 397 },
	{ 81.1, 82.8, 169, 258.5, 439.5 },
	{ 89.6, 91.3, 186, 284.1, 482 },
	{ 12.5, 14.2, 31.8, 52.8, 96.5 },
	{ 21, 22.7, 48.8, 78.3, 139 },
	{ 29.5, 31.2, 65.8, 103.8, 181.5 },
	{ 38, 39.7, 82.8, 129.3, 224 },
	{ 46.5, 48.2, 99.8, 154.8, 266.5 },
	{ 55, 56.7, 116.8, 180.3, 309 },
	{ 63.5, 65.2, 133.8, 205.8, 351.5 },
	{ 72, 73.7, 150.8, 231.3, 394 },
	{ 80.5, 82.2, 167.8, 256.8, 439.5 },
	{ 89, 90.7, 184.8, 282.3, 482 },
	{ 32, 32 },}; //yak
	private int[][] LEVELS =
	{
	{ 48, 45, 47, 51, 55 },
	{ 1, 7, 9, 11, 14, 18, 19, 38 },
	{ 28 },
	{ 57, 60, 61, 63 },
	{ 66, 68, 69, 71 },
	{ 73, 75, 76, 77 },
	{ 79, 82, 83, 84 },
	{ 87, 89, 91, 93 },
	{ 86, 92, 98 },
	{ 3, 12, 21 },
	{ 65, 72, 78 },
	{ 1, 3, 5, 7, 9 },
	{ 11, 13, 15, 17, 19 },
	{ 21, 23, 25, 27, 29 },
	{ 31, 33, 35, 37, 39 },
	{ 41, 43, 45, 47, 49 },
	{ 51, 53, 55, 57, 59 },
	{ 61, 63, 65, 67, 69 },
	{ 71, 73, 75, 77, 79 },
	{ 81, 83, 85, 87, 89 },
	{ 91, 93, 95, 97, 99 },
	{ 1, 2, 4, 6, 8 },
	{ 11, 12, 14, 16, 18 },
	{ 21, 22, 24, 26, 28 },
	{ 31, 32, 34, 36, 38 },
	{ 41, 42, 44, 46, 48 },
	{ 51, 52, 54, 56, 58 },
	{ 61, 62, 64, 66, 68 },
	{ 71, 72, 74, 76, 78 },
	{ 81, 82, 84, 86, 88 },
	{ 91, 92, 94, 96, 98 }, 
	{43, 46}
	};//yak
	private static final Item[][] POTENTIAL_PRODUCTS =
	{
	{ new Item(6326, 5), new Item(6328, 6), new Item(6330, 8), new Item(6324, 12), new Item(6322, 15) },
	{ new Item(1059, 1), new Item(1061, 1), new Item(1167, 1), new Item(1063, 1), new Item(1129, 1), new Item(1095, 1), new Item(25806, 1), new Item(1169, 1) },
	{ new Item(1131, 1), },
	{ new Item(1065, 1), new Item(1099, 2), new Item(12936, 2), new Item(1135, 3)},
	{ new Item(2487, 1), new Item(2493, 2), new Item(12943, 2), new Item(2499, 3)},
	{ new Item(2489, 1), new Item(2495, 2), new Item(12950, 2), new Item(2501, 3)},
	{ new Item(2491, 1), new Item(2497, 2), new Item(12957, 2), new Item(2503, 3)},
	{ new Item(24376, 1), new Item(24379, 2), new Item(24388, 2), new Item(24382, 3) },
	{ new Item(22482, 500), new Item(22486, 1500), new Item(22490, 5000) },
	{ new Item(22458, 300), new Item(22462, 1000), new Item(22466, 2500) },
	{ new Item(22470, 400), new Item(22474, 1200), new Item(22478, 3500) },
	{ new Item(17195, 1), new Item(17297, 1), new Item(17041, 2), new Item(17319, 3), new Item(17173, 5) },
	{ new Item(17197, 1), new Item(17299, 1), new Item(17043, 2), new Item(17321, 3), new Item(17175, 5) },
	{ new Item(17199, 1), new Item(17301, 1), new Item(17045, 2), new Item(17323, 3), new Item(17177, 5) },
	{ new Item(17201, 1), new Item(17303, 1), new Item(17047, 2), new Item(17325, 3), new Item(17179, 5) },
	{ new Item(17203, 1), new Item(17305, 1), new Item(17049, 2), new Item(17327, 3), new Item(17181, 5) },
	{ new Item(17205, 1), new Item(17307, 1), new Item(17051, 2), new Item(17329, 3), new Item(17183, 5) },
	{ new Item(17207, 1), new Item(17309, 1), new Item(17053, 2), new Item(17331, 3), new Item(17185, 5) },
	{ new Item(17209, 1), new Item(17311, 1), new Item(17055, 2), new Item(17333, 3), new Item(17187, 5) },
	{ new Item(17211, 1), new Item(17313, 1), new Item(17057, 2), new Item(17335, 3), new Item(17189, 5) },
	{ new Item(17213, 1), new Item(17315, 1), new Item(17059, 2), new Item(17337, 3), new Item(17191, 5) },
	{ new Item(16911, 1), new Item(17151, 1), new Item(16735, 2), new Item(16845, 3), new Item(17217, 5) },
	{ new Item(16913, 1), new Item(17153, 1), new Item(16737, 2), new Item(16847, 3), new Item(17219, 5) },
	{ new Item(16915, 1), new Item(17155, 1), new Item(16739, 2), new Item(16849, 3), new Item(17221, 5) },
	{ new Item(16917, 1), new Item(17157, 1), new Item(16741, 2), new Item(16851, 3), new Item(17223, 5) },
	{ new Item(16919, 1), new Item(17159, 1), new Item(16743, 2), new Item(16853, 3), new Item(17225, 5) },
	{ new Item(16921, 1), new Item(17161, 1), new Item(16745, 2), new Item(16855, 3), new Item(17227, 5) },
	{ new Item(16923, 1), new Item(17163, 1), new Item(16747, 2), new Item(16857, 3), new Item(17229, 5) },
	{ new Item(16925, 1), new Item(17165, 1), new Item(16749, 2), new Item(16859, 3), new Item(17231, 5) },
	{ new Item(16927, 1), new Item(17167, 1), new Item(16751, 2), new Item(16861, 3), new Item(17233, 5) },
	{ new Item(16929, 1), new Item(17169, 1), new Item(16753, 2), new Item(16863, 3), new Item(17235, 5) },
	{ new Item(10824, 1), new Item(10822, 2)},
	};
	private static final Item[][] REQUIRED_BASE_ITEMS =
	{ null, null, null, null, null, null, null, null, new Item[]
	{ new Item(22452, 1), new Item(22454, 1), new Item(22456, 1) }, new Item[]
	{ new Item(22452, 1), new Item(22454, 1), new Item(22456, 1) }, new Item[]
	{ new Item(22452, 1), new Item(22454, 1), new Item(22456, 1) }, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
	null}; //yak

	private int index;
	private boolean dungeoneering;

	@Override
	public void start() {
		index = (int) parameters[0];
		dungeoneering = (boolean) parameters[1];
		int[] ids = new int[POTENTIAL_PRODUCTS[index].length];
		for (int i = 0; i < ids.length; i++)
			ids[i] = POTENTIAL_PRODUCTS[index][i].getId();
		SkillsDialogue.sendSkillsDialogue(player, SkillsDialogue.MAKE, "Choose how many you wish to make,<br>then click on the item to begin.", 28, ids, new ItemNameFilter() {

			int count = 0;

			@Override
			public String rename(String name) {
				int levelRequired = LEVELS[index][count++];
				if (player.getSkills().getLevel(Skills.CRAFTING) < levelRequired)
					name = "<col=ff0000>" + name + "<br><col=ff0000>Level " + levelRequired;
				return name;
			}
		});
	}

	@Override
	public void run(int interfaceId, int componentId) {
		final int componentIndex = SkillsDialogue.getItemSlot(componentId);
		if (componentIndex >= POTENTIAL_PRODUCTS[index].length) {
			end();
			return;
		}
		player.getActionManager().setAction(new Action() {

			int ticks;

			@Override
			public boolean start(final Player player) {
				if (!checkAll(player))
					return false;
				int leatherAmount = player.getInventory().getAmountOf(BASE_LEATHER[index]);
				int requestedAmount = SkillsDialogue.getQuantity(player);
				if (requestedAmount > leatherAmount)
					requestedAmount = leatherAmount;
				setTicks(requestedAmount);
				return true;
			}

			public void setTicks(int ticks) {
				this.ticks = ticks;
				if (!dungeoneering)
					player.getInventory().deleteItem(THREAD, 1);
			}

			public boolean checkAll(Player player) {
				final int levelReq = LEVELS[index][componentIndex];
				if (player.getSkills().getLevel(Skills.CRAFTING) < levelReq) {
					player.getPackets().sendGameMessage("You need a Crafting level of " + levelReq + " to craft this hide.");
					return false;
				}
				if (player.getInventory().getItems().getNumberOf(BASE_LEATHER[index]) < POTENTIAL_PRODUCTS[index][componentIndex].getAmount()) {
					player.getPackets().sendGameMessage("You don't have enough hides in your inventory.");
					return false;
				}
				if (!player.getInventory().containsOneItem(dungeoneering ? DUNG_THREAD : THREAD)) {
					player.getPackets().sendGameMessage("You need a thread in order to bind the tanned hides together.");
					return false;
				}
				if (!player.getInventory().containsItemToolBelt(dungeoneering ? DUNG_NEEDLE : NEEDLE) && !player.getInventory().containsOneItem(14113, 14105)) {
					player.getPackets().sendGameMessage("You need a needle in order to bind the tanned hides together.");
					return false;
				}
				Item[] extraItems = REQUIRED_BASE_ITEMS[index];
				if (extraItems != null) {
					Item item = extraItems[componentIndex];
					if (!player.getInventory().containsItem(item.getId(), item.getAmount())) {
						player.getPackets().sendGameMessage("You need a " + item.getName().toLowerCase() + ".");
						return false;
					}
				}
				return true;
			}

			@Override
			public boolean process(Player player) {
				return checkAll(player) && ticks > 0;
			}

			@Override
			public int processWithDelay(Player player) {
				ticks--;
				if (dungeoneering)
					player.getInventory().deleteItem(new Item(DUNG_THREAD, 1));
				else {
					if (ticks % 4 == 0)// will use one every time AT LEAST
						player.getInventory().deleteItem(THREAD, 1);
				}
				Item item = POTENTIAL_PRODUCTS[index][componentIndex];
				player.getInventory().deleteItem(new Item(BASE_LEATHER[index], item.getAmount()));
				player.getInventory().addItem(item.getId(), 1);
				player.getSkills().addXp(Skills.CRAFTING, EXPERIENCE[index][componentIndex]);
				Item[] extraItems = REQUIRED_BASE_ITEMS[index];
				if (extraItems != null)
					player.getInventory().deleteItem(extraItems[componentIndex]);
				player.animate(new Animation(player.getInventory().containsOneItem(14113, 14105) ? 11064 : dungeoneering ? 13247 : 1249));
				return 3;
			}

			@Override
			public void stop(Player player) {
				setActionDelay(player, 3);
			}
		});
		end();
	}

	public static int getIndex(int requestedId) {
		for (int index = 0; index < BASE_LEATHER.length; index++) {
			int baseId = BASE_LEATHER[index];
			if (requestedId == baseId)
				return index;
		}
		return -1;
	}

	@Override
	public void finish() {

	}

	public static boolean isExtraItem(int requestedId) {
		for (int index = 0; index < REQUIRED_BASE_ITEMS.length; index++) {
			Item[] items = REQUIRED_BASE_ITEMS[index];
			if (items == null)
				continue;
			for (Item item : items) {
				if (item.getId() == requestedId)
					return true;
			}
		}
		return false;
	}

}
