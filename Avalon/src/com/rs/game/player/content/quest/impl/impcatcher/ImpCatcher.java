package com.rs.game.player.content.quest.impl.impcatcher;


import java.io.Serializable;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.quest.Entry;
import com.rs.game.player.content.quest.Quest;
import com.rs.game.player.content.quest.QuestJournal;
import com.rs.game.player.content.quest.QuestReward;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.RewardType;
import com.rs.game.player.content.quest.State.QuestState;

@SuppressWarnings("serial")
public class ImpCatcher extends Quest implements Serializable {
	

	/**
	 * Represents the black bead item.
	 */
	private static final int BLACK_BEAD = 1474;

	/**
	 * Represents the red bead item.
	 */
	private static final int RED_BEAD = 1470;

	/**
	 * Represents the white bead item.
	 */
	private static final int WHITE_BEAD = 1476;

	/**
	 * Represents the yellow bead item.
	 */
	private static final int YELLOW_BEAD = 1472;

	/**
	 * Represents the amulet item.
	 */
	private static final int AMULET = 1478;

	public ImpCatcher(Player player) {
		super(player);
	}

	@Override
	public String getQuestName() {
		return "Imp Catcher";
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
		
		return new QuestReward[] { new QuestReward(RewardType.EXPERIENCE, Skills.MAGIC, 300), new QuestReward(RewardType.ITEM, new Item(1478, 1)) };
	}
	
	private final QuestJournal journal = new QuestJournal() {

		@Override
		public QuestJournal display(int stage) {
			switch(stage) {
			case 0:
				addLog(new Entry(""), new Entry("To start this quest, I can speak to "+ keyWord("Wizard Mizgog")) {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.IMP_CATCHER).getStage() > 0;
						
					}
					
				}, 
				
				new Entry("inside the "+ keyWord("Wizards Tower.")) {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.IMP_CATCHER).getStage() > 0;
						
					}
					
				});
				break;
				
			case 1:
				
				addLog(new Entry("I need to collect some items by killing "+ keyWord(" Imps.")) {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.IMP_CATCHER).getStage() > 0;
						
					}
					
				}, 
				
				new Entry("1 Black Bead") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getInventory().containsItem(BLACK_BEAD, 1) || player.getQuestManager().get(Quests.IMP_CATCHER).getStage() >= 100;
						
					}
					
				}, 
				
				new Entry("1 Red Bead") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getInventory().containsItem(RED_BEAD, 1) || player.getQuestManager().get(Quests.IMP_CATCHER).getStage() >= 100;
						
					}
					
				}, 
				
				new Entry("1 Yellow Bead") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getInventory().containsItem(YELLOW_BEAD, 1) || player.getQuestManager().get(Quests.IMP_CATCHER).getStage() >= 100;
						
					}
					
				}, 
				
				new Entry("1 White Bead") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getInventory().containsItem(WHITE_BEAD, 1) || player.getQuestManager().get(Quests.IMP_CATCHER).getStage() >= 100;
						
					}
					
				});
				
				break;
				
			case 100:
				
				addLog(new Entry("I have returned all the beads to Wizard Mizgog.") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.IMP_CATCHER).getStage() >= 100;
						
					}
				},
					
				
				new Entry("<col=FF0000>QUEST COMPLETE!") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.IMP_CATCHER).getState() == QuestState.COMPLETED;
						
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
		
		return new int[] { 160, 1, 2 };
	}

	@Override
	public int getSlotId() {
		
		return 7;
	}

	@Override
	public int getRewardItemId() {
		
		return AMULET;
	}

	@Override
	public void accept() {
		player.getDialogueManager().startDialogue("WizardMizgog", -3);
		
	}

	@Override
	public void decline() {
		player.getDialogueManager().startDialogue("WizardMizgog", -4);
		
	}

	@Override
	public String getStart() {
		
		return "Speak to Wizard Mizgog in the Wizard's Tower.";
	}
	
	@Override
	public String getRequirements() {
		return "I'd be better off with Armor and a Weapon.";
	}

}
