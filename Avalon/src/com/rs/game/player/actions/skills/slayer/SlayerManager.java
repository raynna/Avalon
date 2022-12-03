package com.rs.game.player.actions.skills.slayer;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.slayer.Slayer.SlayerMaster;
import com.rs.game.player.actions.skills.slayer.Slayer.SlayerTask;
import com.rs.game.player.content.tasksystem.TaskManager.Tasks;
import com.rs.utils.Utils;

public class SlayerManager implements Serializable {

	private static final long serialVersionUID = -3935672307271551069L;

	private transient final static Object[][] ABILITY = {
			{ 84, 91, 50, "to receive aquantities as a task from the slayer master Kuradal" },
			{ 85, 97, 400, "to use certain items on npcs in order to speed their death rates" },
			{ 86, 98, 2000, "to be assigned Ice Strykewyrms without owning a Fire cape" },
			{ 83, 90, 300, "to attach broad-tips to bolts and arrows by using them together" },
			{ 87, 99, 300,
					"to make a ring of slaying by joining an enchanted gem, a gold bar, and a ring mold together" },
			{ 88, 100, 400,
					"to make a full slayer helment by combining a spiny helmet, a pair of earmuffs, a nose peg, a face mask, and a black mask" } };
	public transient final static int BUY_INTERFACE = 164, ABILITIES_INTERFACE = 378, ASSIGNMENT_INTERFACE = 161,
			AQUANTITIES = 0, QUICK_BLOWS = 1, ICE_STYKE = 2, BROAD_TIPS = 3, RING_OF_SLAYING = 4, SLAYER_HELMET = 5;

	private transient Player player;
	private transient Player socialPlayer;
	public int canceledTaskCount;
	private SlayerTask[] canceledTasks;
	private SlayerTask currentTask;
	private SlayerMaster currentMaster;
	public int completedTasks;

	public int slayerPoints;

	protected int maximumTaskCount;

	protected int currentTaskCount;

	protected int tempcompletedTasks;
	private boolean[] learnedAbilities;

	public SlayerManager() {
		learnedAbilities = new boolean[7];
		canceledTasks = new SlayerTask[6];
		setCurrentMaster(SlayerMaster.EASY);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	private void addPoints() {
		int pointsIncreased = 0;
		if (completedTasks % 100 == 0) {
			pointsIncreased += currentMaster.getPointsRange()[2];
			player.getAdventureLog().addActivity("I have completed in total of " + completedTasks + " slayer tasks.");
		} else if (completedTasks % 10 == 0) {
			pointsIncreased += currentMaster.getPointsRange()[1];
			player.getAdventureLog().addActivity("I have completed in total of " + completedTasks + " slayer tasks.");
		} else
			pointsIncreased += currentMaster.getPointsRange()[0];
		slayerPoints += pointsIncreased;
		player.getTaskManager().checkComplete(Tasks.COMPLETE_SLAYER_TASK);
		player.getPackets().sendGameMessage("You have completed in total of " + completedTasks
				+ " slayer tasks and recieved " + pointsIncreased + " slayer points.");
	}

	private boolean removePoints(int pointsValue) {
		int newPoints = slayerPoints - pointsValue;
		if (newPoints < 0) {
			player.getPackets().sendGameMessage("You don't have enough points to complete this transaction.");
			return false;
		}
		slayerPoints -= pointsValue;
		return true;
	}

	private void unlockAbility(int slot) {
		if (learnedAbilities[slot])
			return;
		else if (!removePoints((int) ABILITY[slot][2]))
			return;
		learnedAbilities[slot] = true;
		sendSlayerInterface(ABILITIES_INTERFACE);
		player.getPackets().sendGameMessage("You have unlocked the ability " + ABILITY[slot][3] + "!");
		player.getAdventureLog().addActivity("I have unlocked the ability " + ABILITY[slot][3] + ".");
	}

	public void sendSlayerInterface(int interfaceId) {
		player.getInterfaceManager().sendInterface(interfaceId);
		sendPoints(interfaceId);
		if (interfaceId == ASSIGNMENT_INTERFACE) {
			player.getPackets().sendHideIComponent(ASSIGNMENT_INTERFACE, 42, true); // useless
			// component
			for (int index = 0; index < canceledTasks.length; index++) {
				SlayerTask task = canceledTasks[index];
				if (task != null) {
					player.getPackets().sendIComponentText(ASSIGNMENT_INTERFACE, 31 + index, task.getName());
				} else
					player.getPackets().sendHideIComponent(ASSIGNMENT_INTERFACE, 37 + index, true);
			}
		} else if (interfaceId == ABILITIES_INTERFACE) {
			for (int index = 0; index < learnedAbilities.length; index++) {
				boolean hasUnlocked = learnedAbilities[index];
				if (hasUnlocked) {
					Object[] data = ABILITY[index];
					player.getPackets().sendIComponentText(ABILITIES_INTERFACE, (int) data[0],
							"You have already unlocked this ability.");
					player.getPackets().sendIComponentText(ABILITIES_INTERFACE, (int) data[1], "");
					player.getPackets().sendHideIComponent(ABILITIES_INTERFACE, 73 + index, true);
				}
			}
		}
	}

	private void sendPoints(int interfaceId) {
		if (interfaceId == ASSIGNMENT_INTERFACE)
			player.getPackets().sendIComponentText(interfaceId, 19, "" + slayerPoints);
		else if (interfaceId == BUY_INTERFACE)
			player.getPackets().sendIComponentText(interfaceId, 20, "" + slayerPoints);
		else
			player.getPackets().sendIComponentText(interfaceId, 79, "" + slayerPoints);
	}

	public void handleRewardButtons(int interfaceId, int componentId) {
		if (componentId == 15)
			sendSlayerInterface(BUY_INTERFACE);
		else if (componentId == 16)
			sendSlayerInterface(ABILITIES_INTERFACE);
		else if (componentId == 17)
			sendSlayerInterface(ASSIGNMENT_INTERFACE);
		else if (componentId == 14) {
			if (interfaceId == ASSIGNMENT_INTERFACE)
				sendSlayerInterface(ABILITIES_INTERFACE);
			else
				sendSlayerInterface(ASSIGNMENT_INTERFACE); // xp == 2000
		}
		if (interfaceId == BUY_INTERFACE) {
			if (componentId == 24 || componentId == 32) {
				if (removePoints(400)) {
					player.getSkills().addXp(Skills.SLAYER, 10000);// 40k xp is
					player.getInterfaceManager().closeScreenInterface();
					player.getPackets().sendGameMessage("You begin to feel wiser and more experienced than before.");
				}
			} else if (componentId == 26 || componentId == 33) {
				if (removePoints(75)) {
					player.getInventory().addItemDrop(13281, 1);
					player.getPackets()
							.sendGameMessage("The master quickly forges you a fully charged ring of slaying.");
				}
			} else if (componentId == 28 || componentId == 36) {
				if (removePoints(35)) {
					player.getInventory().addItemDrop(560, 250);
					player.getInventory().addItemDrop(558, 1000);
					player.getPackets().sendGameMessage("Here are your runes. Use them wisely.");
				}
			} else if (componentId == 37 || componentId == 34) {
				if (removePoints(35)) {
					player.getInventory().addItemDrop(13280, 250);
					player.getPackets().sendGameMessage("Here are your bolts. Use them wisely.");
				}
			} else if (componentId == 39 || componentId == 35) {
				if (removePoints(35)) {
					player.getInventory().addItemDrop(4160, 250);
					player.getPackets().sendGameMessage("Here are your bolts. Use them wisely.");
				}
			}
		} else if (interfaceId == ABILITIES_INTERFACE) {
			if (componentId >= 73 && componentId <= 78)
				unlockAbility(componentId - 73);
		} else if (interfaceId == ASSIGNMENT_INTERFACE) {
			if (componentId == 23 || componentId == 26)
				cancelCurrentTask();
			else if (componentId == 24 || componentId == 27)
				removeCurrentTask();
			else if (componentId >= 37 && componentId <= 41)
				addRemovedTask(componentId - 37);
		}
		sendPoints(interfaceId);
	}

	private void cancelCurrentTask() {
		if (currentTask == null) {
			player.getPackets().sendGameMessage("You don't have an active task to cancel.");
			return;
		} else {
			if (removePoints(30)) {
				skipCurrentTask();
				player.refreshTask();
				player.getPackets().sendGameMessage("You skipped your current task.");
			}
		}
	}

	private void addRemovedTask(int slot) {
		SlayerTask task = canceledTasks[slot];
		if (task == null)
			return;
		canceledTasks[slot] = null;
		if (getCanceledTaskCount() > 0)
			setCanceledTaskCount(getCanceledTaskCount() - 1);
		sendSlayerInterface(ASSIGNMENT_INTERFACE);
		player.getPackets()
				.sendGameMessage("You have re-added " + task.getName().toLowerCase() + " to the assignment list.");
	}

	private void removeCurrentTask() {
		if (getCanceledTaskCount() < 5) {
			if (currentTask == null) {
				player.getPackets().sendGameMessage("You don't have an active task to remove.");
				return;
			} else {
				if (getSlayerPoints() >= 100) {
					for (int index = 0; index < 5; index++) {
						SlayerTask task = canceledTasks[index];
						if (task == null) {
							canceledTasks[index] = currentTask;
							player.getPackets().sendGameMessage(
									"You have canceled the task " + currentTask.getName() + " permenantly.");
							setCanceledTaskCount(getCanceledTaskCount() + 1);
							removePoints(100);
							skipCurrentTask();
							player.refreshTask();
							sendSlayerInterface(ASSIGNMENT_INTERFACE);
							return;
						}
					}
				}
			}
		} else
			player.getPackets().sendGameMessage(
					"You have reached the maximum limit of cancable tasks, please remove one before continueing.");
	}

	public Object[] calculateTask(int difficulity) {
		if (difficulity == 4)
			setCurrentMaster(SlayerMaster.BOSS);
		else if (difficulity == 3)
			setCurrentMaster(SlayerMaster.HARD);
		else if (difficulity == 2)
			setCurrentMaster(SlayerMaster.MEDIUM);
		else if (difficulity == 1)
			setCurrentMaster(SlayerMaster.EASY);
		List<SlayerTask> tasks = new LinkedList<SlayerTask>(Arrays.asList(currentMaster.getTask()));
		for (int index = 0; index < canceledTasks.length; index++) {
			SlayerTask task = canceledTasks[index];
			if (task == null)
				continue;
			tasks.remove(task);
		}
		if (currentMaster == SlayerMaster.HARD) {
			if (learnedAbilities[ICE_STYKE])
				tasks.add(SlayerTask.ICE_STRYKEWYRM);
			if (learnedAbilities[AQUANTITIES])
				tasks.add(SlayerTask.AQUANITE);
		}
		while (true) {
			SlayerTask task = tasks.get(Utils.random(tasks.size()));
			if (socialPlayer != null) {
				if (player.getSkills().getLevel(Skills.SLAYER) >= task.getLevelRequried()
						&& socialPlayer.getSkills().getLevel(Skills.SLAYER) >= task.getLevelRequried()
						&& task.getName().toLowerCase().equalsIgnoreCase(canceledTasks.toString()))
					return new Object[] { task,
							(task == SlayerTask.CORPOREAL_BEAST ? Utils.random(5,
									10)
									: task == SlayerTask.HAR¤AKEN || task == SlayerTask.TZTOK¤JAD ? 1 : Utils.random(currentMaster.getTasksRange()[0],
									currentMaster.getTasksRange()[1])) };
			} else {
				if (player.getSkills().getLevel(Skills.SLAYER) >= task.getLevelRequried())
					return new Object[] { task,
							(task == SlayerTask.CORPOREAL_BEAST ? Utils.random(1,
									3)
									: task == SlayerTask.HAR¤AKEN || task == SlayerTask.TZTOK¤JAD ? 1 : Utils.random(currentMaster.getTasksRange()[0],
									currentMaster.getTasksRange()[1])) };
			}
		}
	}

	public boolean isValidTask(String name) {
		if (currentTask == null)
			return false;
		List<SlayerTask> tasks = new LinkedList<SlayerTask>(Arrays.asList(currentTask.getAlternatives()));
		tasks.add(currentTask);
		for (SlayerTask currentTask : tasks) {
			if (name.toLowerCase()
					.equalsIgnoreCase(currentTask.toString().replace("_", " ").replace("$", "'").replace("¤", "-").toLowerCase()))
				return true;
			if (name.toLowerCase().contains(currentTask.toString().replace("_", " ").replace("$", "'").replace("¤", "-").toLowerCase()))
				return true;
		}
		return false;
	}

	public void checkCompletedTask(int damageAdmitted, int otherDamageAdmitted) {
		currentTaskCount++;
		player.refreshTask();
		int otherSocialCount = 0;
		if (socialPlayer != null) {
			if (socialPlayer.withinDistance(player, 16))
				socialPlayer.getSkills().addXp(Skills.SLAYER, otherDamageAdmitted / 5);
			otherSocialCount = socialPlayer.getSlayerManager().getCurrentTaskCount();
		}
		player.getMoneyPouch().addMoney(damageAdmitted * 5, false);
		player.getSkills().addXp(Skills.SLAYER, damageAdmitted / 5);
		if (currentTaskCount + otherSocialCount == maximumTaskCount) {
			if (socialPlayer != null)
				socialPlayer.getPackets()
						.sendGameMessage("You have finished your slayer task, talk to a slayer master for a new one.");
			completedTasks++;
			player.getPackets()
					.sendGameMessage("You have finished your slayer task, talk to a slayer master for a new one.");
			resetTask(true, true);
			player.refreshTask();
			return;
		} else if (currentTaskCount % 10 == 0)
			checkKillsLeft(false);
	}

	public void checkKillsLeft(boolean tips) {
		if (currentTask == null) {
			player.getPackets().sendGameMessage("You currently have no slayer task assigned.");
			return;
		}
		player.getPackets().sendGameMessage("Your current assignment is to kill " + getCount() + " "
				+ currentTask.getName().replace("$", "'") + "'s.");
		String[] tipDialouges = player.getSlayerManager().getCurrentTask().getTips();
		if (tipDialouges != null && tipDialouges.length != 0 && tips) {
			String chosenDialouge = tipDialouges[tipDialouges.length - 1];
			player.getPackets().sendGameMessage(
					currentTask.getName().replace("$", "'") + "'s can be found in the " + chosenDialouge);
		}
		if (socialPlayer != null) {
			player.getPackets().sendGameMessage("Your partner's current assignment is: " + currentTask.getName()
					+ "'s, only " + player.getSlayerManager().getCount() + " more to go.");
			int combinedTasksCount = currentTaskCount + socialPlayer.getSlayerManager().getCurrentTaskCount();
			player.getPackets().sendGameMessage("In total you both have killed " + combinedTasksCount + " out of "
					+ maximumTaskCount + " of the task, only " + (maximumTaskCount - combinedTasksCount));
		}
	}

	public int getCount() {
		return maximumTaskCount - currentTaskCount;
	}

	public void invitePlayer(Player otherPlayer) {
		if (currentTask != null) {
			player.getPackets()
					.sendGameMessage("You need to complete your current task before starting a social slayer group.");
			return;
		} else if (otherPlayer == null || !otherPlayer.withinDistance(player, 7) || player.hasFinished()
				|| otherPlayer.hasFinished()) {
			player.getPackets().sendGameMessage("Your target is no-where to be found.");
			return;
		} else if (otherPlayer.getSlayerManager().getCurrentTask() != null) {
			player.getPackets().sendGameMessage(
					"Your target needs to complete their current task before joining a social slayer group.");
			return;
		} else if (otherPlayer.getSlayerManager().getSocialPlayer() != null) {
			player.getPackets().sendGameMessage("Your target is already in a social slayer group.");
			return;
		} else if (socialPlayer != null) {
			player.getPackets()
					.sendGameMessage("You are already in a social slayer group, leave it in order to start a new one.");
			return;
		}
		if (otherPlayer.temporaryAttribute().get("social_request") == player) {
			player.temporaryAttribute().put("social_request", otherPlayer);
			openSocialInvitation(otherPlayer);
			return;
		}
		player.temporaryAttribute().put("social_request", otherPlayer);
		player.getPackets().sendGameMessage("Sending " + otherPlayer.getDisplayName() + " an invitation...");
		otherPlayer.getPackets().sendMessage(117,
				"You have received an invitation to join " + player.getDisplayName() + "'s social slayer group.",
				player);
	}

	private void openSocialInvitation(final Player otherPlayer) {
		player.getInterfaceManager().sendInterface(1310);
		player.getPackets().sendIComponentText(1310, 6, otherPlayer.getDisplayName());
		player.getPackets().sendIComponentText(1310, 8, "" + otherPlayer.getSkills().getLevel(Skills.SLAYER));
		player.getPackets().sendIComponentText(1310, 10, "" + otherPlayer.getSkills().getCombatLevelWithSummoning());
		player.setCloseInterfacesEvent(new Runnable() {

			@Override
			public void run() {
				otherPlayer.getPackets().sendGameMessage("Your invitation has been declined.");
				player.getPackets().sendGameMessage("You have declined the invitation.");
			}
		});
	}

	public void createSocialGroup(boolean initial) {
		Player socialPlayer = (Player) player.temporaryAttribute().remove("social_request");
		if (socialPlayer == null)
			return;
		if (initial) {
			socialPlayer.getSlayerManager().createSocialGroup(false);
			player.getPackets().sendGameMessage("You have created a social group.");
		} else
			player.getPackets()
					.sendGameMessage("You have just joined " + socialPlayer.getDisplayName() + "'s social group.");
		this.socialPlayer = socialPlayer;
	}

	public void resetSocialGroup(boolean initial) {
		if (socialPlayer != null) {
			if (initial) {
				socialPlayer.getSlayerManager().resetSocialGroup(false);
				player.getPackets().sendGameMessage("You have left the social slayer group.", true);
			} else
				player.getPackets().sendGameMessage("Your social slayer member has left your group.", true);
			socialPlayer = null;
		}
	}

	public void skipCurrentTask() {
		resetTask(false, true);
	}

	public void resetTask(boolean completed, boolean initial) {
		if (completed) {
			addPoints();
		}
		if (initial) {
			if (socialPlayer != null) {
				socialPlayer.getSlayerManager().resetTask(completed, false);
				if (!completed)
					resetSocialGroup(true);
			}
		}
		setCurrentTask(null, 0);
		player.refreshTask();
	}

	public boolean hasLearnedBroad() {
		return learnedAbilities[BROAD_TIPS];
	}

	public boolean hasLearnedQuickBlows() {
		return learnedAbilities[QUICK_BLOWS];
	}

	public boolean hasLearnedRing() {
		return learnedAbilities[RING_OF_SLAYING];
	}

	public boolean hasLearnedSlayerHelmet() {
		return learnedAbilities[SLAYER_HELMET];
	}

	public int getCompletedTasks() {
		return completedTasks;
	}

	public void setCurrentTask(boolean initial, int difficulity) {
		Object[] futureTask = calculateTask(difficulity);
		setCurrentTask((SlayerTask) futureTask[0], (int) futureTask[1]);
		checkKillsLeft(true);
		player.refreshTask();
		if (initial) {
			if (socialPlayer != null)
				socialPlayer.getSlayerManager().setCurrentTask((SlayerTask) futureTask[0], (int) futureTask[1]);
		}
	}

	public void setCurrentTask(SlayerTask task, int maximumTaskCount) {
		if (task == null)
			this.currentTaskCount = 0;
		this.currentTask = task;
		this.maximumTaskCount = maximumTaskCount;
	}

	public SlayerTask getCurrentTask() {
		return currentTask;
	}

	private int getCurrentTaskCount() {
		return currentTaskCount;
	}

	public void setPoints(int i) {
		this.slayerPoints = i;
	}

	public int getSlayerPoints() {
		return slayerPoints;
	}

	public void setCanceledTaskCount(int i) {
		this.canceledTaskCount = i;
	}

	public int getCanceledTaskCount() {
		return canceledTaskCount;
	}

	public void setCurrentMaster(SlayerMaster currentMaster) {
		this.currentMaster = currentMaster;
	}

	public SlayerMaster getCurrentMaster() {
		return currentMaster;
	}

	public Player getSocialPlayer() {
		return socialPlayer;
	}
}