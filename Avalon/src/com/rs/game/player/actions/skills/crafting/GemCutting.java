package com.rs.game.player.actions.skills.crafting;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.game.player.content.tasksystem.TaskManager.Tasks;
import com.rs.utils.Utils;

public class GemCutting extends Action {

	/**
	 * Enum for gems
	 * 
	 * @author Raghav
	 * @improved Andreas - AvalonPK
	 * 
	 */
	public enum Gem {
		OPAL(1625, 1609, 15.0, 1, 886),

		JADE(1627, 1611, 20, 13, 886),

		RED_TOPAZ(1629, 1613, 25, 16, 887),

		SAPPHIRE(1623, 1607, 50, 20, 888),

		EMERALD(1621, 1605, 67, 27, 889),

		RUBY(1619, 1603, 85, 34, 887),

		DIAMOND(1617, 1601, 107.5, 43, 890),

		DRAGONSTONE(1631, 1615, 137.5, 55, 885),

		ONYX(6571, 6573, 167.5, 67, 2717);

		private double experience;
		private int levelRequired;
		private int uncut, cut;

		private int emote;

		private Gem(int uncut, int cut, double experience, int levelRequired, int emote) {
			this.uncut = uncut;
			this.cut = cut;
			this.experience = experience;
			this.levelRequired = levelRequired;
			this.emote = emote;
		}

		public int getLevelRequired() {
			return levelRequired;
		}

		public double getExperience() {
			return experience;
		}

		public int getUncut() {
			return uncut;
		}

		public int getCut() {
			return cut;
		}

		public int getEmote() {
			return emote;
		}

	}

	private Gem gem;
	private int quantity;

	public GemCutting(Gem gem, int quantity) {
		this.gem = gem;
		this.quantity = quantity;
	}

	public boolean checkAll(Player player) {
		/*
		 * if (player.getSkills().getLevel(Skills.CRAFTING) < gem .getLevelRequired()) {
		 * player.getPackets().sendGameMessage( "You need a crafting level of " +
		 * gem.getLevelRequired() + " to cut that gem."); return false; }
		 */
		if (!player.getInventory().containsOneItem(gem.getUncut())) {
			player.getPackets().sendGameMessage("You don't have any "
					+ ItemDefinitions.getItemDefinitions(gem.getUncut()).getName().toLowerCase() + " to cut.");
			return false;
		}
		return true;
	}

	public static void cut(Player player, Gem gem) {
		int amount = player.getInventory().getItems().getNumberOf(new Item(gem.getUncut(), 1));
		if (amount <= 1)
			player.getActionManager().setAction(new GemCutting(gem, 1));
		else {
			if (new GemCutting(gem, amount).checkAll(player))
				player.getDialogueManager().startDialogue("GemCuttingD", gem);
		}
	}

	private boolean isSuccesfull(Player player) {
		int craftingLevel = player.getSkills().getLevel(Skills.CRAFTING);
		int level = Utils.getRandom(craftingLevel);
		double ratio = level / (Utils.random(gem.getLevelRequired() / 2) + 1);
		if (gem != Gem.SAPPHIRE && gem != Gem.EMERALD && gem != Gem.RUBY && gem != Gem.DIAMOND && gem != Gem.DRAGONSTONE && gem != Gem.ONYX && (Math.round(ratio * craftingLevel) < gem.getLevelRequired()))
			return false;
		return true;
	}

	@Override
	public boolean start(Player player) {
		if (checkAll(player)) {
			setActionDelay(player, 1);
			player.animate(new Animation(gem.getEmote()));
			return true;
		}
		return false;
	}

	@Override
	public boolean process(Player player) {
		return checkAll(player);
	}

	@Override
	public int processWithDelay(Player player) {
		player.getInventory().deleteItem(gem.getUncut(), 1);
		if (!isSuccesfull(player)) {
			player.getInventory().addItem(1633, 1);
			player.getSkills().addXp(Skills.CRAFTING, 5);
			player.getPackets().sendGameMessage("You accidentally crushed the gem upon attempting to cut it.", true);
		} else {
			player.getInventory().addItem(gem.getCut(), 1);
			player.getSkills().addXp(Skills.CRAFTING, gem.getExperience());
			player.getPackets().sendGameMessage(
					"You cut the " + ItemDefinitions.getItemDefinitions(gem.getUncut()).getName().toLowerCase() + ".",
					true);
			if (gem.getUncut() == 1623)
				player.getTaskManager().checkComplete(Tasks.CUT_UNCUT_SAPPHIRE);
			if (gem.getUncut() == 1617)
				player.getTaskManager().checkComplete(Tasks.CUT_UNCUT_DIAMOND);
			if (gem.getUncut() == 1631)
				player.getTaskManager().checkComplete(Tasks.CUT_UNCUT_DRAGONSTONE);
			if (gem.getUncut() == 6571)
				player.getTaskManager().checkComplete(Tasks.CUT_UNCUT_ONYX);
		}
		quantity--;
		if (quantity <= 0)
			return -1;
		player.setNextAnimationNoPriority(new Animation(gem.getEmote()), player); // start
																					// the
		return 1;
	}

	@Override
	public void stop(final Player player) {
		setActionDelay(player, 3);
	}
}
