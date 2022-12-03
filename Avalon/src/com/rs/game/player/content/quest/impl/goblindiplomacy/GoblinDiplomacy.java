package com.rs.game.player.content.quest.impl.goblindiplomacy;

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

/**
 * @Author arrow
 * @Contact<arrowrsps@gmail.com;skype:arrowrsps>
 */
@SuppressWarnings("serial")
public class GoblinDiplomacy extends Quest implements Serializable {

	/*
	 * Stage in Quest
	 */

	public static final int NOT_STARTED = 0, ASSIGNED_TASK = 1;

	/*
	 * Represents Reward Item id
	 */

	public static final int GOLD_BAR = 2357;

	/*
	 * Goblin Mail Ids
	 */

	public static final int ORANGE_GOBLIN_MAIL = 286;

	public GoblinDiplomacy(Player player) {
		super(player);
	}

	@Override
	public String getQuestName() {
		return "Goblin Diplomacy";
	}

	@Override
	public void declareDialogues() {

	}

	@Override
	public int getQuestPoints() {
		return 5;
	}

	@Override
	public QuestReward[] getRewards() {
		return new QuestReward[] { new QuestReward(RewardType.EXPERIENCE, Skills.CRAFTING, 200),
				new QuestReward(RewardType.ITEM, new Item(GOLD_BAR, 1)) };
	}

	private QuestJournal journal = new QuestJournal() {
		@Override
		public QuestJournal display(int stage) {
			if (player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getState() == QuestState.COMPLETED) {
				addLog(new Entry("I brought Bentnoze and wartface some " + keyWord("Orange Goblin Armour.")) {
					@Override
					public boolean meetsCondition(Player player) {
						return true;
					}
				}, new Entry("COMPLETED") {
					@Override
					public boolean meetsCondition(Player player) {
						return true;
					}
				});
			} else if (player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() == 0) {
				addLog(new Entry(""),
						new Entry("I can start this quest by speaking to " + keyWord("Generals Wartface ")) {
							@Override
							public boolean meetsCondition(Player player) {
								return player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() > 0;
							}
						}, new Entry(keyWord("and Bentnoze ") + "in the " + keyWord("Goblin Village.")) {
							@Override
							public boolean meetsCondition(Player player) {
								return player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() > 0;
							}
						});
			} else if (player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() > 0) {
						if (!player.getInventory().containsItem(ORANGE_GOBLIN_MAIL, 1)) {
							addLog(new Entry("I should bring the goblins some " + keyWord("Orange Goblin Armour.")) {
								@Override
								public boolean meetsCondition(Player player) {
									return player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() > 1;
								}
							}, new Entry("Maybe the generals will know where to get some.") {
								@Override
								public boolean meetsCondition(Player player) {
									return player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() > 1;
								}
							});
						} else {
							addLog(new Entry("I have some " + keyWord("Orange Goblin Armour") + ". I should show it to the") {

								@Override
								public boolean meetsCondition(Player player) {
									return player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() > 1;
								}
							}, new Entry("generals.") {
								@Override
								public boolean meetsCondition(Player player) {
									return player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() > 1;
								}
							});
					}
			} else {
				addLog(new Entry("I spoke to Generals Wartface and Bentnoze in the Goblin") {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() > 0;
					}
				}, new Entry(" Village and found that the goblins were on the brink of civil") {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() > 0;
					}
				}, new Entry(" war over the colour of their armour. I offered to help the ") {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() > 0;
					}
				}, new Entry("generals by finding another colour that they both like.") {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() > 0;
					}
				}, new Entry("") {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() > 0;
					}
				});
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
		return new int[] { 2378, 1, 6 };
	}

	@Override
	public int getSlotId() {
		return 6;
	}

	@Override
	public int getRewardItemId() {
		return GOLD_BAR;
	}

	@Override
	public void accept() {
		player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).setState(QuestState.STARTED);
		player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).setStage(1);
	}

	@Override
	public void decline() {

	}

	@Override
	public String getStart() {
		return "I can start this quest by speaking to Generals Wartface and Bentnoze in the Goblin Village.";
	}
}
