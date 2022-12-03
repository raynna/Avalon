package com.rs.game.player.content.quest.impl.druidicritual;


import java.io.Serializable;

import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.quest.Entry;
import com.rs.game.player.content.quest.Quest;
import com.rs.game.player.content.quest.QuestJournal;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.QuestReward;
import com.rs.game.player.content.quest.RewardType;
import com.rs.game.player.content.quest.State.QuestState;

/**
 * Represents the class for the Quest Druidic Ritual.
 * @author Ethan Kyle Millard
 * @version 1.0 3/23/15
 */
@SuppressWarnings("serial")
public class DruidicRitual extends Quest implements Serializable {
	
	/**
	 * Represents the Quest Name.
	 */
	public static final String NAME = "Druidic Ritual";
	
	
	public DruidicRitual(Player player) {
		super(player);
	}

	@Override
	public String getQuestName() {
		return NAME;
	}

	@Override
	public int getQuestPoints() {
		return 1;
	}

	@Override
	public QuestReward[] getRewards() {
		return new QuestReward[] { new QuestReward(RewardType.EXPERIENCE, Skills.HERBLORE, 300) };
	}
	
	private final QuestJournal journal = new QuestJournal() {

		@Override
		public QuestJournal display(int stage) {
			switch(stage) {
			case 0:
				addLog(new Entry(""), new Entry("I can start this quest by speaking to the "+ keyWord("Kaqemeex")+" is at") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DRUIDIC_RITUAL).getStage() > 0;
						
					}
					
				}, 
				
				new Entry("the "+ keyWord("Drudic Circle") +" just "+ keyWord("North") +" of "+ keyWord("Taverley.")) {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DRUIDIC_RITUAL).getStage() > 0;
						
					}
					
				});
				break;
				
			case 1:
				
				addLog(new Entry("I told Kaqemeex I would help them prepare their ceremony.") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DRUIDIC_RITUAL).getStage() > 0;
						
					}
					
				}, 
				
				new Entry("I should speak to "+ keyWord("Sanfew") +" in the village to the "+ keyWord("South")) {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DRUIDIC_RITUAL).getStage() > 10;
						
					}
					
				});
				
				break;
				
				
			case 20:
				
				
				addLog(new Entry(keyWord("Sanfew") +" told me for the ritual they would need me to place") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DRUIDIC_RITUAL).getStage() > 20;
						
					}
					
				},
				
				new Entry(keyWord("raw bear meat, raw chicken, raw rat meat,") + " and "+ keyWord("raw beef") +" in") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DRUIDIC_RITUAL).getStage() > 20;
						
					}
					
				},
				
				new Entry("the "+ keyWord("Cauldron of Thunder in the") +" dungeon South of "+ keyWord("Taverley")) {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DRUIDIC_RITUAL).getStage() > 20;
						
					}
					
				});
				
				break;
				
			case 99:
				
				addLog(new Entry("The ceremony required various meats being placed in the") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DRUIDIC_RITUAL).getStage() > 20;
						
					}
					
				},
				
				new Entry("Cauldron of Thunder. I did this and gave them to Sanfew.") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DRUIDIC_RITUAL).getStage() > 20;
						
					}
					
				},
				
				new Entry("I should speak to "+ keyWord("Kaqemeex") +"again and claim my "+ keyWord("reward.")) {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DRUIDIC_RITUAL).getStage() > 99;
						
					}
					
				});
				
				break;
				
			case 100:
				
				addLog(new Entry("Kaqemeex then taught me the basics of the skill Herblore") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DRUIDIC_RITUAL).getStage() > 99;
						
					}
				},
					
				
				new Entry("<col=FF0000>QUEST COMPLETE!") {
					
					@Override
					public boolean meetsCondition(Player player) {
						
						return player.getQuestManager().get(Quests.DRUIDIC_RITUAL).getState() == QuestState.COMPLETED;
						
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
		return new int[] { 80, 3, 4 };
	}

	@Override
	public int getSlotId() {
		return 33;
	}

	@Override
	public void declareDialogues() {
	}

	@Override
	public void accept() {
		player.getDialogueManager().startDialogue("Kaqemeex", -3);
		player.getPackets().sendConfig(1593, 33);
		player.getInterfaceManager().sendInterfaces();
		
	}

	@Override
	public void decline() {
		player.getDialogueManager().startDialogue("Kaqemeex", -4);
	}

	@Override
	public String getStart() {
		return "Speak to Kaqemeex in Taverley.";
	}
	
	@Override
	public String getRequirements() {
		return "None in specific but beware of the monsters in Taverley Dungeon!";
	}

	@Override
	public int getRewardItemId() {
		return 2446;
	}
	

}
