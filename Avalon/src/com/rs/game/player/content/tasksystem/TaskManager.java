package com.rs.game.player.content.tasksystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Graphics;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.content.customtab.AchievementsTab;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class TaskManager implements Serializable {

	private static final long serialVersionUID = 5402478562684453627L;

	public static String EASY = "easy";
	public static String MEDIUM = "medium";
	public static String HARD = "hard";
	public static String ELITE = "elite";

	public static enum Tasks {

		/*
		 * Easy Tasks
		 */
		CHOP_LOGS(EASY, 50), SMITH_BRONZE_PLATEBODY(EASY, 20), STEAL_FROM_BAKERS_STALL(EASY, 25),
		CLAIM_WILDSTALKER_HELMET(EASY), FLETCH_SHORTBOW(EASY, 50), MINE_COPPER_AND_TIN(EASY, 100),
		GNOME_AGILITY(EASY, 5), CUT_UNCUT_SAPPHIRE(EASY, 25), MAKE_ATTACK_POTION(EASY, 10), FISH_SHRIMP(EASY, 75),
		COOK_SARDINE(EASY, 25), CRAFT_AIR_RUNE(EASY, 200),

		/*
		 * Medium Tasks
		 */

		CHOP_MAPLE_LOGS(MEDIUM, 50), SMITH_MITHRIL_SCIMITAR(MEDIUM, 20), MINE_MITHRIL_ORE(MEDIUM, 50),
		FLETCH_MAPLE_LONGBOW(MEDIUM, 75), BARBARIAN_AGILITY(MEDIUM, 10), COMPLETE_SLAYER_TASK(MEDIUM, 10),
		CLEAN_AVANTOE(MEDIUM, 50), CUT_UNCUT_DIAMOND(MEDIUM, 50), LIGHT_MAPLE_LOG(MEDIUM, 100),
		STEAL_FROM_SILK_STALL(MEDIUM, 50),

		/*
		 * Hard Tasks
		 */

		MINE_ADAMANT_ORE(HARD, 100), CHOP_YEW_LOGS(HARD, 100), FISH_SHARK(HARD, 150), CUT_UNCUT_DRAGONSTONE(HARD, 50),
		SMITH_ADAMANT_SWORD(HARD, 100), FLETCH_YEW_SHORTBOW(HARD, 150), MAKE_RANGING_POTION(HARD, 50),
		LIGHT_YEW_LOGS(HARD, 150),

		/*
		 * Elite Tasks
		 */

		FLETCH_MAGIC_LONGBOW(ELITE, 200), CUT_UNCUT_ONYX(ELITE), CHOP_MAGIC_LOGS(ELITE, 150),
		MAKE_EXTREME_STRENGTH(ELITE, 150), SMITH_ADAMANT_PLATEBODY(ELITE, 100), LIGHT_MAGIC_LOG(ELITE, 200),
		GNOME_AGILITY_ADVANCED(ELITE, 25), STEAL_FROM_GEM_STALL(ELITE, 150), KILL_ABYSSAL_DEMON(ELITE),
		CRAFT_BLOOD_RUNE(ELITE, 500), MINE_RUNITE_ORE(ELITE, 150), SUMMON_UNICORN_STALLION(ELITE)

		;

		private String difficulity;
		private int amount;

		private Tasks(String difficulity) {
			this.difficulity = difficulity;
		}

		private Tasks(String difficulity, int amount) {
			this.difficulity = difficulity;
			this.setAmount(amount);
		}

		public String getDifficulity() {
			return difficulity;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}
	}

	public static enum Rewards {

		/*
		 * Easy Rewards
		 */
		ARDOUGNE_CLOAK_1(15345, EASY), VARROCK_ARMOUR_1(11756, EASY), FALADOR_SHIELD_1(14577, EASY),
		SEERS_HEADBAND_1(14631, EASY), FREMENNIK_SEA_BOOTS_1(14571, EASY), EXPLORERS_RING_1(13560, EASY),
		KARAMJA_GLOVES_1(11136, EASY),

		/*
		 * Medium Rewards
		 */

		ARDOUGNE_CLOAK_2(15347, MEDIUM), VARROCK_ARMOUR_2(11757, MEDIUM), FALADOR_SHIELD_2(14578, MEDIUM),
		SEERS_HEADBAND_2(14662, MEDIUM), FREMENNIK_SEA_BOOTS_2(14572, MEDIUM), EXPLORERS_RING_2(13561, MEDIUM),
		KARAMJA_GLOVES_2(11138, MEDIUM),

		/*
		 * Hard Rewards
		 */

		ARDOUGNE_CLOAK_3(15349, HARD), VARROCK_ARMOUR_3(11758, HARD), FALADOR_SHIELD_3(14579, HARD),
		SEERS_HEADBAND_3(14663, HARD), FREMENNIK_SEA_BOOTS_3(14573, HARD), EXPLORERS_RING_3(13562, HARD),
		KARAMJA_GLOVES_3(11140, HARD),

		/*
		 * Elite Rewards
		 */

		ARDOUGNE_CLOAK_4(19748, ELITE), VARROCK_ARMOUR_4(19757, ELITE), FALADOR_SHIELD_4(19749, ELITE),
		SEERS_HEADBAND_4(19763, ELITE), FREMENNIK_SEA_BOOTS_4(19766, ELITE), EXPLORERS_RING_4(19760, ELITE),
		KARAMJA_GLOVES_4(19754, ELITE), AGILE_TOP(14936, ELITE), AGILE_LEGS(14938, ELITE), INFERNO_ADZE2(13661, ELITE)

		;

		private int itemId;
		private String difficulity;

		private Rewards(int itemId, String difficulity) {
			this.itemId = itemId;
			this.difficulity = difficulity;
		}

		public String getDifficulity() {
			return difficulity;
		}

		public int getItemId() {
			return itemId;
		}
	}

	private transient Player player;
	private List<Tasks> completedTasks;
	private HashMap<Tasks, Integer> taskStages;

	/**
	 * Instantiates a new task manager.
	 */
	public TaskManager() {
		completedTasks = new ArrayList<Tasks>();
	}

	public void setPlayer(Player player) {
		this.player = player;
		if (taskStages == null)
			taskStages = new HashMap<Tasks, Integer>();
	}

	private transient final String[] finishTaskMessages = { "Nice job!", "Great job!", "Awesome,", "Well done!", "Cool!",
			"Amazing!" };

	public void resetAllTasks() {
		for (Tasks tasks : Tasks.values()) {
			taskStages.put(tasks, 0);
			completedTasks.remove(tasks);
		}
		player.getPackets().sendGameMessage("All tasks reset.");
	}

	public void completeTask(Tasks task) {
		completedTasks.add(task);
		taskStages.put(task, task.getAmount());
		// taskStages.remove(task);
		player.gfx(new Graphics(3201, 0, 150));
		player.getInterfaceManager().sendOverlay(1073, false);
		player.getPackets().sendIComponentText(1073, 10, "<col=ffc800>Task completed");
		player.getPackets().sendIComponentText(1073, 11, Utils.formatString(task.toString()));
		player.getPackets().sendFilteredGameMessage(true,
				(finishTaskMessage() + " You completed a " + Utils.formatString(task.getDifficulity())
								+ " task: " + Utils.formatString(task.name().replace("$", "'"))
								+ "! To view what tasks you got left, check achievement tab."));
		player.getAdventureLog()
				.addActivity("I have completed a " + Utils.formatString(task.getDifficulity()) + " task: "
								+ Utils.formatString(task.name().replace("$", "'")));
		sendReward(task, 1);
		WorldTasksManager.schedule(new WorldTask() {
			int timer;

			@Override
			public void run() {
				if (timer == 4) {
					stop();
					player.getInterfaceManager().closeOverlay(false);
				}
				timer++;
			}
		}, 0, 1);
	}

	public String finishTaskMessage() {
		return finishTaskMessages[Utils.random(0, finishTaskMessages.length - 1)];
	}

	public boolean completedTask(Tasks task) {
		return completedTasks.contains(task);
	}

	public boolean hasCompletedTasks(String d) {
		boolean completed = true;
		for (Tasks tasks : Tasks.values()) {
			if (tasks.getDifficulity() == d) {
				if (!player.getTaskManager().completedTask(tasks)) {
					completed = false;
				}
			}
		}
		if (completed)
			return true;
		return false;
	}

	public void getRewards(String d) {
		for (Rewards rewards : Rewards.values()) {
			if (rewards.getDifficulity() != d) {
				for (Item equipment : player.getEquipment().getItems().getContainerItems()) {
					if (equipment == null)
						continue;
					if (equipment.getId() == rewards.getItemId()) {
						player.getEquipment().deleteItem(equipment.getId(), Integer.MAX_VALUE);
						player.getAppearence().generateAppearenceData();
					}
				}
				for (Item inventory : player.getInventory().getItems().getContainerItems()) {
					if (inventory == null)
						continue;
					if (inventory.getId() == rewards.getItemId()) {
						player.getInventory().deleteItem(inventory.getId(), Integer.MAX_VALUE);
					}
				}
				for (Item bank : player.getBank().getContainerCopy()) {
					if (bank == null)
						continue;
					if (bank.getId() == rewards.getItemId()) {
						player.getBank().removeItem(player.getBank().getItemSlot(rewards.getItemId()),
								Integer.MAX_VALUE, true, false);
					}
				}
				continue;
			}
			if (player.getInventory().containsItem(rewards.getItemId(), 1)
					|| player.getBank().getItem(rewards.getItemId()) != null
					|| player.getEquipment().getHatId() == rewards.getItemId()
					|| player.getEquipment().getWeaponId() == rewards.getItemId()
					|| player.getEquipment().getBootsId() == rewards.getItemId()
					|| player.getEquipment().getChestId() == rewards.getItemId()
					|| player.getEquipment().getCapeId() == rewards.getItemId()
					|| player.getEquipment().getGlovesId() == rewards.getItemId()
					|| player.getEquipment().getLegsId() == rewards.getItemId()
					|| player.getEquipment().getRingId() == rewards.getItemId()
					|| player.getEquipment().getShieldId() == rewards.getItemId()) {
				player.getPackets().sendGameMessage(
						"You already have " + ItemDefinitions.getItemDefinitions(rewards.getItemId()).getName() + ".");
				continue;
			} else if (player.getInventory().hasFreeSlots()) {
				player.getInventory().addItem(rewards.getItemId(), 1);
				player.getPackets().sendGameMessage(
						ItemDefinitions.getItemDefinitions(rewards.getItemId()).getName() + " added to inventory.");
			} else {
				player.getBank().addItem(rewards.getItemId(), 1, true);
				player.getPackets().sendGameMessage(
						ItemDefinitions.getItemDefinitions(rewards.getItemId()).getName() + " added to bank.");
			}
		}
	}


	public void claimRewards() {
		if (hasCompletedTasks(EASY)) {
			if (!hasCompletedTasks(MEDIUM))
				getRewards(EASY);
		}
		if (hasCompletedTasks(MEDIUM)) {
			if (!hasCompletedTasks(EASY)) {
				player.getPackets()
						.sendGameMessage("You need to complete all easy tasks before claiming medium rewards.");
				return;
			}
			if (!hasCompletedTasks(HARD))
				getRewards(MEDIUM);
		}
		if (hasCompletedTasks(HARD)) {
			if (!hasCompletedTasks(EASY) || !hasCompletedTasks(MEDIUM)) {
				player.getPackets().sendGameMessage("You need to complete all " + (!hasCompletedTasks(EASY) ? "easy, " : "")
						+ (!hasCompletedTasks(MEDIUM) ? "medium, " : "") + " tasks before claiming hard rewards.");
				return;
			}
			if (!hasCompletedTasks(ELITE))
				getRewards(HARD);
		}
		if (hasCompletedTasks(ELITE)) {
			if (!hasCompletedTasks(EASY) || !hasCompletedTasks(MEDIUM) || !hasCompletedTasks(HARD)) {
				player.getPackets()
						.sendGameMessage("You need to complete all " + (!hasCompletedTasks(EASY) ? "easy, " : "")
								+ (!hasCompletedTasks(MEDIUM) ? "medium, " : "") + (!hasCompletedTasks(HARD) ? "hard, " : "")
								+ "tasks before claiming elite rewards.");
				return;
			}
			getRewards(ELITE);
		}
	}

	public void sendReward(Tasks task, int amount) {
		if (task.getDifficulity() == EASY) {
			player.getMoneyPouch().addMoney(50000, false);
		}
		if (task.getDifficulity() == MEDIUM) {
			player.getMoneyPouch().addMoney(100000, false);
		}
		if (task.getDifficulity() == HARD) {
			player.getMoneyPouch().addMoney(150000, false);
		}
		if (task.getDifficulity() == ELITE) {
			player.getMoneyPouch().addMoney(250000, false);
		}
	}

	public boolean hasCompletedAllTasks() {
		List<Tasks> tasks = Arrays.asList(Tasks.values());
		return (completedTasks.containsAll(tasks) ? true : false);
	}

	public void setTaskStage(Tasks task, int stage) {
		if (completedTasks.contains(task))
			return;
		taskStages.put(task, stage);
		// player.sm("You have completed a step in the task: "
		// + Utils.formatString(task.toString().replace("$", "'") + ", Step:
		// <col=ff0000>" + stage));
	}

	public int getTaskStage(Tasks task) {
		Integer stage = taskStages.get(task);
		return stage == null ? 0 : stage;
	}

	public void sendRewardAndRefresh(Tasks task, int stage) {
		setTaskStage(task, stage);
		sendReward(task, 1);
	}

	public void checkComplete(Tasks task) {
		if (player.getTaskManager().completedTask(task)) {
			player.getTaskManager().setTaskStage(task, task.getAmount());
			return;
		}
		if (!player.getTaskManager().completedTask(task)) {
			if (task.getAmount() > 1) {
				if (player.getTaskManager().getTaskStage(task) == task.getAmount() - 1)
					player.getTaskManager().completeTask(task);
				else
					player.getTaskManager().setTaskStage(task, player.getTaskManager().getTaskStage(task) + 1);
			} else {
				player.getTaskManager().completeTask(task);
			}
		}
		if (player.getInterfaceManager().containsInterface(3002)) {
			if (player.getTemporaryAttributes().get("ACHIEVEMENTTAB") != null) {
				if ((int) player.getTemporaryAttributes().get("ACHIEVEMENTTAB") == 0) {
					if ((String) player.getTemporaryAttributes().get("ACHIEVEMENTCATEGORY") != null) {
						AchievementsTab.openTasks(player, (String) player.getTemporaryAttributes().get("ACHIEVEMENTCATEGORY"));
					} else {
						AchievementsTab.open(player);
					}
				}
			}

		}
	}

	public void checkComplete(Tasks task, int amount) {
		if (player.getTaskManager().completedTask(task)) {
			player.getTaskManager().setTaskStage(task, task.getAmount());
			return;
		}
		if (!player.getTaskManager().completedTask(task)) {
			if (task.getAmount() > 1) {
				if (amount + player.getTaskManager().getTaskStage(task) >= task.getAmount() - 1)
					player.getTaskManager().completeTask(task);
				else {
					player.getTaskManager().setTaskStage(task, player.getTaskManager().getTaskStage(task) + amount);
				}
			} else {
				player.getTaskManager().completeTask(task);
			}
		}
	}
}
