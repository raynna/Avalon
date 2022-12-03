package com.rs.game.player.content.quest.impl.cooksassistant;


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

/**
 * The Cook's Assistant Quest.
 * 
 * @author Vip3r
 * Version 1.0 - 15/03/2015; 17:14
 */
@SuppressWarnings("serial")
public class CooksAssistant extends Quest {
	
	/**
	 * Quest Items.
	 */
	public static final Item BUCKET_OF_MILK = new Item(15413, 1), POT_OF_FLOUR = new Item(15414, 1), EGG = new Item(15412, 1);
	
	/**
	 * Constructs a new {@link CooksAssistant}.
	 * 
	 * @param player
	 * 			The player.
	 */
	public CooksAssistant(Player player) {
		super(player);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.fb.game.content.quest.Quest#declareDialogues()
	 */
	@Override
	public void declareDialogues() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.fb.game.content.quest.Quest#getQuestName()
	 */
	@Override
	public String getQuestName() {
		return "Cook's Assistant";
	}

	/*
	 * (non-Javadoc)
	 * @see org.fb.game.content.quest.Quest#getQuestPoints()
	 */
	@Override
	public int getQuestPoints() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * @see org.fb.game.content.quest.Quest#getRewards()
	 */
	@Override
	public QuestReward[] getRewards() {
		return new QuestReward[] { new QuestReward(RewardType.EXPERIENCE, Skills.COOKING, 300), new QuestReward(RewardType.ITEM, new Item(995, 500)), new QuestReward(RewardType.ITEM, new Item(326, 20)), new QuestReward(RewardType.STRING, "Access to the cook's range") };
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.fb.game.content.quest.Quest#getSlotId()
	 */
	@Override
	public int getSlotId() {
		return 1;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.fb.game.content.quest.Quest#getVarp()
	 */
	@Override
	public int[] getVarp() {
		return new int[] { 29, 1, 2 };
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.fb.game.content.quest.Quest#getStart()
	 */
	@Override
	public String getStart() {
		return "Speak to the cook in the kitchen of Lumbridge Castle.";
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.fb.game.content.quest.Quest#getRequirements()
	 */
	@Override
	public String getRequirements() {
		return "None in specific but watch out for the local Goblins!";
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.fb.game.content.quest.Quest#accept()
	 */
	@Override
	public void accept() {
		player.getDialogueManager().startDialogue("LumbridgeCook", -1);
		player.getPackets().sendConfig(1593, 1);
		player.getInterfaceManager().sendInterfaces();
		player.getQuestManager().get(Quests.COOKS_ASSISTANT).setStage(1);
		player.getQuestManager().get(Quests.COOKS_ASSISTANT).setState(QuestState.STARTED);
	}

	@Override
	public void decline() {
		player.getDialogueManager().startDialogue("LumbridgeCook", -2);
	}

	
	/**
	 * The Quest stages.
	 */
	//public static final int NOT_STARTED = 0, ASSIGNED_TASK = 1, FINISHED_TASK = 2, COMPLETED_QUEST = 3;
	
	
	private final QuestJournal journal = new QuestJournal() {

		@Override
		public QuestJournal display(int stage) {
			switch(player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage()) {
			case 0:
				addLog(new Entry(" ") {

						   @Override
						   public boolean meetsCondition(Player player) {
							   return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() > 0;
						   }

					   },
						new Entry("I can start this quest by speaking to the " + keyWord("Cook") + " in the") {

						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() > 0;
						}
					
				}, 
				
				new Entry(keyWord("kitchen") + " on the ground floor of " + keyWord("Lumbridge Castle") + ".") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() > 0;
					}
					
				});
				break;
				
			case 1:
			case 2:
				
				addLog(new Entry("It's the " + keyWord("Duke of Lumbridge's") + " birthday and I have to help") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() > 1;
					}
					
				}, 
				
				new Entry("his " + keyWord("Cook") + " make him a " + keyWord("birthday cake") + ". To do this I need to") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() > 1;
					}
					
				}, new Entry("bring him the following ingredients:") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() > 1;
					}
				}, new Entry(""), new Entry(""),
				
				new Entry("I need to find some " + keyWord("Top-Quality Milk") + ".") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getInventory().containsItem(BUCKET_OF_MILK);
					}
					
					@Override
					public String getFinished(Player player) {
						return "I have found some " + keyWord("Top-Quality Milk") + " to give to the cook.";
					}
				},
				
				new Entry("I should speak to someone in the " + keyWord("Cow Fields") +".") {

					@Override
					public boolean meetsCondition(Player player) {
						return player.getInventory().containsItem(BUCKET_OF_MILK);
					}
					
					@Override
					public String getFinished(Player player) {
						return "";
					}
				}, 
				
				new Entry("I need to find a " + keyWord("Super large Egg") + ".") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getInventory().containsItem(EGG);
					}
					
					@Override
					public String getFinished(Player player) {
						return "I have found a " + keyWord("Super large Egg") + " to give to the cook.";
					}
				},
				
				new Entry("I should speak to someone on the " + keyWord("Chicken Farm") + ".") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getInventory().containsItem(EGG);
					}
					
					@Override
					public String getFinished(Player player) {
						return "";
					}
				},
				
				new Entry("I need to find some " + keyWord("Extra fine flour") + ".") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getInventory().containsItem(POT_OF_FLOUR);
					}
					
					@Override
					public String getFinished(Player player) {
						return "I have found some " + keyWord("Extra fine flour") + " to give to the cook.";
					}
				}, 
				
				new Entry("I should speak to someone at the " + keyWord("Mill") + ".") {

					@Override
					public boolean meetsCondition(Player player) {
						return player.getInventory().containsItem(POT_OF_FLOUR);
					}
					
					@Override
					public String getFinished(Player player) {
						return "";
					}
					
				});
				break;
				
			case 3:
				
				addLog(new Entry("I have given the cook some " + keyWord("Top-quality milk.")) {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getData().get("bucket_of_milk") != null;
					}
					
				},
				new Entry("I have given the cook some" + keyWord("Extra fine flour.")) {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getData().get("flour") != null;
					}
					
				},
				new Entry("I have given the cook a " + keyWord("Super large Egg.")) {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getData().get("egg") != null;
					}
					
				});
				
				break;
				
			case 4:
				
				addLog(new Entry("It was the Duke of Lumbridge's birthday,  but his cook had") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() == 3;
					}
					
				},
				new Entry("forgotten to buy the ingredients he needed to make him a") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() == 3;
					}
					
				},
				new Entry("cake. I brought the cook an egg, some flour and some milk") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() == 3;
					}
					
				},
				new Entry("and then cook made a delicious looking cake with them.") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() == 3;
					}
					
				},
				new Entry("As a reward he now lets me use his high quality range") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() == 3;
					}
					
				},
				new Entry("which lets me burn things less whenever I wish to cook") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() == 3;
					}
					
				},
				new Entry("there.") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() == 3;
					}
					
				},
				new Entry("<col=FF0000>QUEST COMPLETE!") {
					
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() == 3;
					}
					
				});
				
				break;
				
				
			}
			return this;
		}
		
	};

	/*
	 * (non-Javadoc)
	 * @see org.fb.game.content.quest.Quest#getJournal()
	 */
	@Override
	public QuestJournal getJournal() {
		return journal;
	}

	@Override
	public int getRewardItemId() {
		return 1891;
	}

}
