package com.rs.game.player.content.customtab;

import java.text.DecimalFormat;

import com.rs.game.player.Player;
import com.rs.game.player.content.tasksystem.TaskManager.Tasks;
import com.rs.utils.Utils;

public class AchievementsTab extends CustomTab {

	public enum AchievementsStore {

		TITLE(25, "Achievements"),

		EASY_TASKS(3, "Easy Achievements"),

		MEDIUM_TASKS(4, "Medium Achievements"),

		HARD_TASKS(5, "Hard Achievements"),

		ELITE_TASKS(6, "Elite Achievements");
		;

		private int compId;
		private String text;

		private AchievementsStore(int compId, String text) {
			this.compId = compId;
			this.text = text;
		}

	}

	private static int MARKED_1 = 3893, UNMARKED_1 = 3894, MARKED_2 = 3895, UNMARKED_2 = 3896, MARKED_3 = 3897,
			UNMARKED_3 = 3898, MARKED_4 = 3899, UNMARKED_4 = 3900;

	private static void sendComponentButtons(Player player) {
		player.getPackets().sendHideIComponent(3002, BACK_BUTTON, true);
		player.getPackets().sendHideIComponent(3002, FORWARD_BUTTON, true);
		;
		player.getPackets().sendHideIComponent(3002, 24, false);
		player.getPackets().sendHideIComponent(3002, BLUE_STAR_COMP, false);
		player.getPackets().sendSpriteOnIComponent(3002, BLUE_STAR_COMP, 1820);
		player.getPackets().sendHideIComponent(3002, GREEN_STAR_COMP, false);
		player.getPackets().sendSpriteOnIComponent(3002, GREEN_STAR_COMP, UNMARKED_1);
		player.getPackets().sendHideIComponent(3002, RED_STAR_COMP, false);
		player.getPackets().sendSpriteOnIComponent(3002, RED_STAR_COMP, UNMARKED_2);
		player.getPackets().sendHideIComponent(3002, PURPLE_STAR_COMP, false);
		player.getPackets().sendSpriteOnIComponent(3002, PURPLE_STAR_COMP, UNMARKED_3);
		player.getPackets().sendHideIComponent(3002, YELLOW_STAR_COMP, false);
		player.getPackets().sendSpriteOnIComponent(3002, YELLOW_STAR_COMP, UNMARKED_4);
	}

	public static void open(Player player) {
		sendComponentButtons(player);
		for (int i = 3; i <= 22; i++)
			player.getPackets().sendHideIComponent(3002, i, true);
		for (int i = 28; i <= 56; i++)
			player.getPackets().sendHideIComponent(3002, i, true);
		player.getTemporaryAttributtes().put("ACHIEVEMENTTAB", 0);
		player.getTemporaryAttributtes().remove("ACHIEVEMENTCATEGORY");
		completedTasks = 0;
		totalTasks = 0;
		totalActions = 0;
		currentActions = 0;
		for (Tasks tasks : Tasks.values()) {
			if (tasks != null) {
				currentActions += tasks.getAmount() > 0 ? player.getTaskManager().getTaskStage(tasks)
						: player.getTaskManager().completedTask(tasks) ? 1 : 0;
				totalActions += tasks.getAmount() > 1 ? tasks.getAmount() : 1;
				if (player.getTaskManager().completedTask(tasks))
					completedTasks++;
				totalTasks++;
			}
		}
		for (AchievementsStore store : AchievementsStore.values()) {
			if (store != null) {
				player.getPackets().sendHideIComponent(3002, store.compId, false);
				if (store.text != null) {
					player.getPackets().sendIComponentText(3002, store.compId, store.text);
				}
			}
		}
		double percentage = getPercentage(currentActions, totalActions);
		player.getPackets().sendIComponentText(3002, 24,
				completedTasks + "/" + totalTasks + (percentage == 100 ? "" : " ")
						+ (percentage == 100 ? "<col=04BB3B>" : percentage == 0 ? "<col=BB0404>" : "<col=FFF300>")
						+ (percentage == 100 ? "100%" : new DecimalFormat("##.##").format(percentage) + "%"));
		if (percentage == 100) {
			player.getPackets().sendHideIComponent(3002, 10, false);
			player.getPackets().sendIComponentText(3002, 10, "<col=04BB3B>You completed all " + totalTasks + " tasks!");
		}
	}

	public static void handleButtons(Player player, int compId) {
		String category = (String) player.temporaryAttribute().get("ACHIEVEMENTCATEGORY");
		if (compId == BACK_BUTTON) {
			if (category != null) {
				if (category.contains("elite"))
					openTasks(player, "hard");
				if (category.contains("hard"))
					openTasks(player, "medium");
				if (category.contains("medium"))
					openTasks(player, "easy");
				if (category.contains("easy"))
					open(player);
				return;
			}
		}
		if (compId == FORWARD_BUTTON) {
			if (category != null) {
				if (category.contains("easy"))
					openTasks(player, "medium");
				if (category.contains("medium"))
					openTasks(player, "hard");
				if (category.contains("hard"))
					openTasks(player, "elite");
				return;
			}
		}
		if (category != null) {
			int i = 3;
			for (Tasks task : Tasks.values()) {
				if (task != null) {
					if (!task.getDifficulity().contains(category))
						continue;
					if (compId == i) {
						player.getPackets()
								.sendGameMessage(Utils.formatString(task.getDifficulity()) + " Achievement: "
										+ (player.getTaskManager().completedTask(task) ? "<str>" : "")
										+ Utils.formatString(task.name()).replace("$", "'")
										+ (player.getTaskManager().completedTask(task) ? " - Completed!"
												: (task.getAmount() > 1)
														? " - " + "(" + player.getTaskManager().getTaskStage(task) + "/"
																+ task.getAmount() + ")"
														: "."));
					}
					i++;
				}
			}
		} else {
			player.temporaryAttribute().remove("ACHIEVEMENTCATEGORY");
			for (AchievementsStore store : AchievementsStore.values()) {
				if (store != null) {
					if (compId == store.compId) {
						String c = store.text.toLowerCase().replace(" achievements", "").trim();
						openTasks(player, c);
					}
				}
			}
		}
		switch (compId) {
		case BLUE_STAR_COMP:
			open(player);
			break;
		case GREEN_STAR_COMP:
			openTasks(player, "easy");
			break;
		case RED_STAR_COMP:
			openTasks(player, "medium");
			break;
		case PURPLE_STAR_COMP:
			openTasks(player, "hard");
			break;
		case YELLOW_STAR_COMP:
			openTasks(player, "elite");
			break;
		default:
			break;
		}
	}

	static int completedTasks;
	static int totalTasks;
	static int totalActions;
	static int currentActions;

	public static void openTasks(Player player, String category) {
		for (int i = 3; i <= 15; i++)
			player.getPackets().sendHideIComponent(3002, i, true);
		player.getTemporaryAttributtes().put("ACHIEVEMENTCATEGORY", category);
		sendComponentButtons(player);
		if (!category.contains("elite"))
			player.getPackets().sendHideIComponent(3002, FORWARD_BUTTON, false);
		player.getPackets().sendHideIComponent(3002, BACK_BUTTON, false);
		if (category.contains("easy"))
		player.getPackets().sendSpriteOnIComponent(3002, GREEN_STAR_COMP, MARKED_1);
		else if (category.contains("medium"))
			player.getPackets().sendSpriteOnIComponent(3002, RED_STAR_COMP, MARKED_2);
		else if (category.contains("hard"))
			player.getPackets().sendSpriteOnIComponent(3002, PURPLE_STAR_COMP, MARKED_3);
		else if (category.contains("elite"))
			player.getPackets().sendSpriteOnIComponent(3002, YELLOW_STAR_COMP, MARKED_4);
		int i = 3;
		completedTasks = 0;
		totalTasks = 0;
		totalActions = 0;
		currentActions = 0;
		for (Tasks store : Tasks.values()) {
			if (store != null) {
				if (!store.getDifficulity().equals(category)) {
					continue;
				}
				currentActions += store.getAmount() > 0 ? player.getTaskManager().getTaskStage(store) : player.getTaskManager().completedTask(store) ? 1 : 0;
				totalActions += store.getAmount() > 1 ? store.getAmount() : 1;
				totalTasks++;
				if (player.getTaskManager().completedTask(store))
					completedTasks++;
				player.getPackets().sendHideIComponent(3002, i, false);
				player.getPackets().sendIComponentText(3002, i,
						(player.getTaskManager().completedTask(store) ? "<col=04BB3B>"
								: player.getTaskManager().getTaskStage(store) > 0 ? "<col=FFF300>" : "<col=BB0404>")
								+ Utils.formatString(store.name()).replace("$", "'")
								+ (store.getAmount() > 1 && !player.getTaskManager().completedTask(store) ? " ("
										+ player.getTaskManager().getTaskStage(store) + "/" + store.getAmount() + ")"
										: ""));
				player.getPackets().sendIComponentText(3002, 25, Utils.formatString(category) + "");
				i++;
			}
		}
		double percentage = getPercentage(currentActions, totalActions);
		player.getPackets().sendIComponentText(3002, 24,
				completedTasks + "/" + totalTasks + (percentage == 100 ? "" : " ")
						+ (percentage == 100 ? "<col=04BB3B>" : percentage == 0 ? "<col=BB0404>" : "<col=FFF300>")
						+ (percentage == 100 ? "100%" : new DecimalFormat("##.#").format(percentage) + "%"));
	}

	public static double getPercentage(double completed, double totaltasks) {
		return (completed / totaltasks) * 100;
	}

}
