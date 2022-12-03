package com.rs.game.player.content.quest.impl.doricsquest;


import java.io.Serializable;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.quest.Entry;
import com.rs.game.player.content.quest.Quest;
import com.rs.game.player.content.quest.QuestJournal;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.QuestReward;
import com.rs.game.player.content.quest.RewardType;
import com.rs.game.player.content.quest.State.QuestState;

@SuppressWarnings("serial")
public class DoricsQuest extends Quest implements Serializable {
	
	private static final int CLAY = 1474;

	private static final int COPPER_ORE = 1470;

	private static final int IRON_ORE = 1476;

	public static final String NAME = "Doric's Quest";

	public DoricsQuest(Player player) {
		super(player);
	}

	@Override
	public String getQuestName() {
		return NAME;
	}

	@Override
	public void declareDialogues() {
	}

	@Override
	public int getQuestPoints() {
		
		return 1;
	}

	@Override
	public QuestReward[] getRewards() {
		
		return new QuestReward[] { new QuestReward(RewardType.EXPERIENCE, Skills.MINING, 1300), new QuestReward(RewardType.ITEM, new Item(995, 180)), new QuestReward(RewardType.STRING, "Use of Doric's Anvils") };
	}
	
	
	private final QuestJournal journal = new QuestJournal() {

		@Override
		public QuestJournal display(int stage) {
			switch(stage) {
			case 0:
				addLog(new Entry(""), new Entry("I can start this quest by speaking to "+ keyWord("Doric" ) + " who is "+ keyWord("North of")) {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DORICS_QUEST).getStage() > 0;
						
					}
					
				}, 
				
				new Entry(keyWord("Falador.")) {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DORICS_QUEST).getStage() > 0;
						
					}
					
				}, 
				new Entry("There aren't any requirements but "+ keyWord("Level 15 Mining") + " will help") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DORICS_QUEST).getStage() > 0;
						
					}
					
				});
				break;
				
			case 1:
				
				addLog(new Entry("I've spoken to with "+ keyWord("Doric") + " and accepted his request. He wants me to") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DORICS_QUEST).getStage() > 1;
						
					}
					
				},
				
				new Entry("gather the following materials:") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DORICS_QUEST).getStage() > 1;
						
					}
					
				},
				
				new Entry("6 Clay (Noted)") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getInventory().containsItem(CLAY, 6) || player.getQuestManager().get(Quests.DORICS_QUEST).getStage() >= 100;
						
					}
					
				}, 
				
				new Entry("4 Copper Ore (Noted)") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getInventory().containsItem(COPPER_ORE, 4) || player.getQuestManager().get(Quests.DORICS_QUEST).getStage() >= 100;
						
					}
					
				}, 
				
				new Entry("2 Iron Ore (Noted)") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getInventory().containsItem(IRON_ORE, 2) || player.getQuestManager().get(Quests.DORICS_QUEST).getStage() >= 100;
						
					}
					
				});
				
				break;
				
			case 100:
				
				addLog(new Entry("I have collected some Clay, Copper Ore, and Iron Ore.") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DORICS_QUEST).getStage() >= 100;
						
					}
				},
				new Entry("Doric rewarded me for all my hard work.") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DORICS_QUEST).getStage() >= 100;
						
					}
				},
				
				new Entry("I can now use Doric's Anvils whenever I want.") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DORICS_QUEST).getStage() >= 100;
						
					}
				},
					
				
				new Entry("<col=FF0000>QUEST COMPLETE!") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DORICS_QUEST).getState() == QuestState.COMPLETED;
						
						}
					});


				break;
				
				
			}
			return this;
		}
		
	};
	

	@Override
	public QuestJournal getJournal() {
		return journal;
	}

	@Override
	public int[] getVarp() {
		
		return new int[] { 31, 10, 100 };
	}

	@Override
	public int getSlotId() {
		
		return 3;
	}

	@Override
	public int getRewardItemId() {
		
		return 1269;
	}

	@Override
	public void accept() {
		player.getDialogueManager().startDialogue("Doric", -3);
		
	}

	@Override
	public void decline() {
		player.getDialogueManager().startDialogue("Doric", -4);
		
	}

	@Override
	public String getStart() {
		
		return "Speak to Doric North of Falador.";
	}
	
	@Override
	public String getRequirements() {
		return "There aren't any requirements but Level 15 Mining will help.";
	}
}