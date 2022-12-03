package com.rs.game.player.content.quest;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.customtab.QuestTab;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.game.player.dialogues.Dialogue;

public abstract class Quest implements Serializable  {
	
	private static final long serialVersionUID = 8650378406511612117L;

private Map<String, String> data = new HashMap<String, String>();
	
	private Map<Integer, Dialogue> dialogues = new HashMap<Integer, Dialogue>();
	
	protected transient Player player;
	protected int stage = 0;
	protected boolean completed = false;
	
	private QuestState state = QuestState.NOT_STARTED;
	
	public abstract String getQuestName();
	
	public abstract void declareDialogues();

	public abstract int getQuestPoints();
	
	public abstract QuestReward[] getRewards();
	
	public abstract QuestJournal getJournal();
	
	public abstract int[] getVarp();
	
	public abstract int getSlotId();
	
	public abstract int getRewardItemId();
	

	public Quest(Player player) {
		this.player = player;
		declareDialogues();
	}
	
	public void showRequirements() {
        player.getPackets().sendHideIComponent(1243, 26, true);
		player.getPackets().sendIComponentText(1243, 21,
				"<col=f8ff49>Requirements:</col> <br>" + getRequirements());
	}
	
	public abstract void accept();
	
	public void acceptQuest() {
		player.closeInterfaces();
		setState(QuestState.STARTED);
		setStage(1);
		accept();
	}
	
	public void finish() {
		player.getPackets().sendConfig(getVarp()[0], getVarp()[2]);
		state = QuestState.COMPLETED;
		player.getInterfaceManager().openGameTab(3);
		QuestTab.open(player);
		sendReward();
	}
	
	public void sendReward() {
		StringBuilder rewards = new StringBuilder();
		for (QuestReward reward : getRewards()) {
			switch(reward.getType()) {
			case EXPERIENCE:
				rewards.append(((Integer) reward.getData()[1]) + " " + (Skills.SKILL_NAME[(Integer) reward.getData()[0]] + " experience"));
				player.getSkills().addXp((Integer) reward.getData()[0], (Integer) reward.getData()[1]);
				break;
			case ITEM:
				Item item = (Item) reward.getData()[0];
					rewards.append(item.getAmount() + " " + (item.getAmount() > 1 ? item.getName() + (item.getName().endsWith("s") ? "'s" : "s") : item.getName()));
					if (player.getInventory().getFreeSlots() >= 1) {
						player.getInventory().addItem(item);
					} else {
						 World.addGroundItem(item, new WorldTile(player));
					}
				break;
			case STRING:
				rewards.append((String) reward.getData()[0]); 
				break;
			default:
				break;
			}
			rewards.append("<br>");
		}
		rewards.setLength(rewards.length() - 4);
		player.getQuestManager().setQuestPoints(player.getQuestManager().getQuestPoints() + getQuestPoints());
		//player.getQuestManager().update();
		player.getInterfaceManager().sendInterface(1244);
		player.getPackets().sendIComponentText(1244, 25,
				"You have completed " + getQuestName() + ".");
		player.getPackets().sendIComponentText(1244, 27,
				"Quest Points: " + player.getQuestManager().getQuestPoints());
		player.getPackets()
				.sendGlobalString(
						359,
						getQuestPoints() + " Quest Point"
								+ (getQuestPoints() == 1 ? "" : "s") + "<br>"
								+ rewards);
		player.getPackets().sendItemOnIComponent(1244, 24, getRewardItemId(), 1);
		player.getPackets()
				.sendGameMessage(
						"Congratulations! You have completed the " + getQuestName() + " quest!");
	}
	
	public abstract void decline();
	
	public void showRewards() {
		StringBuilder rewards = new StringBuilder();
		for (QuestReward reward : getRewards()) {
			switch(reward.getType()) {
			case EXPERIENCE:
				rewards.append(((Integer) reward.getData()[1]) + " " + (Skills.SKILL_NAME[(Integer) reward.getData()[0]] + " experience"));
				break;
			case ITEM:
				Item item = (Item) reward.getData()[0];
					rewards.append(item.getAmount() + " " + (item.getAmount() > 1 ? item.getName() + (item.getName().endsWith("s") ? "'s" : "s") : item.getName()));
				break;
			case STRING:
				rewards.append((String) reward.getData()[0]); 
				break;
			default:
				break;
			}
			rewards.append(", ");
		}
		rewards.setLength(rewards.length() - 2);
        player.getPackets().sendHideIComponent(1243, 38, true);
		player.getPackets().sendIComponentText(1243, 33, "<col=f8ff49>Rewards:</col><br>" + rewards.toString());
	}
	
	public void sendStartOption() {
		player.getDialogueManager().finishDialogue();
		player.getQuestManager().setCurrent(this);
		assembleJournal(0);
		player.getInterfaceManager().sendInterface(1243);
		player.getPackets().sendIComponentText(1243, 6, getQuestName());
		player.getPackets().sendHideIComponent(1243, 45, false);
		player.getPackets().sendHideIComponent(1243, 56, true);
		player.getPackets().sendHideIComponent(1243, 57, true);
		player.getPackets().sendIComponentText(1243, 18, "<col=f8ff49>Start Point:</col><br>"
						+ getStart());
		player.getPackets().sendIComponentText(1243, 21, "<col=f8ff49>Requirements:</col>");
		player.getPackets().sendIComponentText(1243, 33, "<col=f8ff49>Rewards:</col>");
	}
	
	public abstract String getStart();
	
	public String getCombatWarning() {
		return "None.";
	}
	
	public String getRequirements() {
		return "None.";
	}
	
	public void assembleJournal(int stage) {
		getJournal().getEntries().clear();
		getJournal().getEntries().clear();
		for (int i = 0; i <= stage; i++) {
			getJournal().display(i);
		}
	}
	
	public Quest openJournal() {
		player.getInterfaceManager().closeScreenInterface();
		for (int i = 0; i < 309; i++) {
			player.getPackets().sendIComponentText(275, i, " ");
		}
		assembleJournal(stage);
		player.getPackets().sendIComponentText(275, 1, getQuestName());
		player.getInterfaceManager().sendInterface(275);
		String text = "";
		for (int i = 0; i < getJournal().getEntries().size(); i++) {
			text = getJournal().getEntries().get(i).meetsCondition(player) ? "<str>"
					 + getJournal().getEntries().get(i).getFinished(player)  :
						getJournal().getEntries().get(i).getText();
			player.getPackets().sendIComponentText(275, 10 + i, text);
		}
		return this;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
		//System.out.println("Stage now: " + stage);
	}
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public boolean isCompleted() {
		return completed;
	}

	public Map<Integer, Dialogue> getDialogues() {
		return dialogues;
	}

	public void setDialogues(Map<Integer, Dialogue> dialogues) {
		this.dialogues = dialogues;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public QuestState getState() {
		return state;
	}

	public void setState(QuestState state) {
		this.state = state;
		//System.out.println("State now: " + state);
	}

}
