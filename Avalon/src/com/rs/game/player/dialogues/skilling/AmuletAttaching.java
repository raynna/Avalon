package com.rs.game.player.dialogues.skilling;

import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.content.SkillsDialogue.ItemNameFilter;
import com.rs.game.player.dialogues.Dialogue;

public class AmuletAttaching extends Dialogue {

	private static final double[] EXPERIENCE = { 30, 65, 70, 85, 100, 150, 165 };
	private static final int[] LEVELS = { 8, 24, 31, 50, 70, 80, 90 };
	private static final int[] BASE = { 1673, 1675, 1677, 1679, 1681, 1683, 6579 };
	private static final int[] PRODUCTS = { 1692, 1694, 1696, 1698, 1700, 1702, 6581 };
	private static final int STRING = 1759;

	@Override
	public void start() {
		SkillsDialogue.sendSkillsDialogue(player, SkillsDialogue.MAKE,
				"Choose how many you wish to make,<br>then click on the item to begin.", 28, PRODUCTS,
				new ItemNameFilter() {

					int count = 0;

					@Override
					public String rename(String name) {
						int levelRequired = LEVELS[count++];
						if (player.getSkills().getLevel(Skills.CRAFTING) < levelRequired)
							name = "<col=ff0000>" + name + "<br><col=ff0000>Level " + levelRequired;
						return name;
					}
				});
	}

	@Override
	public void run(int interfaceId, int componentId) {
		final int index = SkillsDialogue.getItemSlot(componentId);
		if (index > PRODUCTS.length) {
			end();
			return;
		}
		player.getActionManager().setAction(new Action() {

			int ticks;

			@Override
			public boolean start(final Player player) {
				if (!checkAll(player))
					return false;
				int amuletAmount = player.getInventory().getAmountOf(BASE[index]);
				int requestedAmount = SkillsDialogue.getQuantity(player);
				if (requestedAmount > amuletAmount)
					requestedAmount = amuletAmount;
				int stringAmount = player.getInventory().getAmountOf(STRING);
				if (requestedAmount > stringAmount)
					requestedAmount = stringAmount;
				this.ticks = requestedAmount;
				return true;
			}

			public boolean checkAll(Player player) {
				if (player.getSkills().getLevel(Skills.CRAFTING) < LEVELS[index]) {
					player.getPackets().sendGameMessage("You need a Crafting level of " + LEVELS[index] + ".");
					return false;
				} else if (!player.getInventory().containsItem(STRING, 1)) {
					player.getPackets().sendGameMessage("You have run out of balls of whool.");
					return false;
				} else if (!player.getInventory().containsItem(BASE[index], 1)) {
					player.getPackets().sendGameMessage("You have run out amulets.");
					return false;
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
				player.getSkills().addXp(Skills.CRAFTING, EXPERIENCE[index]);
				player.getInventory().deleteItem(STRING, 1);
				player.getInventory().deleteItem(BASE[index], 1);
				player.getInventory().addItem(PRODUCTS[index], 1);
				player.getPackets().sendGameMessage("You put some string on your amulet.");
				return 2;
			}

			@Override
			public void stop(Player player) {
				setActionDelay(player, 3);
			}
		});
		end();
	}

	public static boolean isAttaching(int used, int usedWith) {
		for (int item : BASE) {
			if (item == used && usedWith == STRING || item == usedWith && used == STRING)
				return true;
		}
		return false;
	}

	@Override
	public void finish() {

	}
}
