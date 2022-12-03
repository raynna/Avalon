/**
 * Copyright 2015 - 2015 (C) Project-Arrow
 * 
 * File name : DemonSlayer.java
 * Created on : Aug 9, 2015
 * Author     : Ethan
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 * Tristan Swasey / Abexlry
 * Brandon / Frostbite
 * Ethan Millard / Fate
 * Yassin / Displee / Maffia-rpg
 * Austin Owens / Whitelight
 */
package com.rs.game.player.content.quest.impl.demonslayer;

import com.rs.game.player.Player;
import com.rs.game.player.content.quest.Entry;
import com.rs.game.player.content.quest.Quest;
import com.rs.game.player.content.quest.QuestJournal;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.QuestReward;
import com.rs.game.player.content.quest.RewardType;
import com.rs.game.player.content.quest.State.QuestState;

/**
 * The Class DemonSlayer.
 */
@SuppressWarnings("serial")
public class DemonSlayer extends Quest {

	
	/**
	 * Instantiates a new demon slayer.
	 *
	 * @param player
	 *            the player
	 */
	public DemonSlayer(Player player) {
		super(player);
	}
	

	/* (non-Javadoc)
	 * @see com.arrow.game.content.quest.Quest#getQuestName()
	 */
	@Override
	public String getQuestName() {
		return "Demon Slayer";
	}

	/* (non-Javadoc)
	 * @see com.arrow.game.content.quest.Quest#declareDialogues()
	 */
	@Override
	public void declareDialogues() {

	}

	/* (non-Javadoc)
	 * @see com.arrow.game.content.quest.Quest#getQuestPoints()
	 */
	@Override
	public int getQuestPoints() {
		return 1;
	}

	/* (non-Javadoc)
	 * @see com.arrow.game.content.quest.Quest#getRewards()
	 */
	@Override
	public QuestReward[] getRewards() {
		return new QuestReward[] {
				new QuestReward(RewardType.STRING, "You can keep Silverlight, which is useful for weakening any kind of demon.")
		};
	}

	/** The journal. */
	private QuestJournal journal = new QuestJournal() {
		@Override
		public QuestJournal display(int stage) {
			int progress = player.getQuestManager().get(Quests.DEMON_SLAYER).getStage();
			switch(progress) {
			/**
			 * Not Started Quest
			 */
			case 0:
				addLog(new Entry(""), new Entry("I can start this quest by speaking to the " + keyWord("Gypsy") + " in the " + keyWord("tent")) {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				},
				new Entry("in " + keyWord("Varrock's main square.")) {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				},
				new Entry(""),
				new Entry(""),
				new Entry("I must be able to defeat a level 27 " + keyWord("apocalyptic demon") + "!") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				});
				break;
			case 1:
				addLog(new Entry(""), new Entry("I need to find this demon to kill somewhere south of Varrock.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				});
				break;
			case 2:
				addLog(new Entry(""), new Entry("I've killed the demon, I should go back to Gypsy Aris and tell") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				},
				new Entry("the news.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				});
				break;

			}
			return this;
		}
	};

	/* (non-Javadoc)
	 * @see com.arrow.game.content.quest.Quest#getJournal()
	 */
	@Override
	public QuestJournal getJournal() {
		return journal;
	}

	/* (non-Javadoc)
	 * @see com.arrow.game.content.quest.Quest#getVarp()
	 */
	@Override
	public int[] getVarp() {
		return new int[] {273, 10, 110};
	}

	/* (non-Javadoc)
	 * @see com.arrow.game.content.quest.Quest#getSlotId()
	 */
	@Override
	public int getSlotId() {
		return 10;
	}

	/* (non-Javadoc)
	 * @see com.arrow.game.content.quest.Quest#getRewardItemId()
	 */
	@Override
	public int getRewardItemId() {
		return -1;
	}

	/* (non-Javadoc)
	 * @see com.arrow.game.content.quest.Quest#accept()
	 */
	@Override
	public void accept() {
		player.getQuestManager().get(Quests.DEMON_SLAYER).setStage(1);
		player.getQuestManager().get(Quests.DEMON_SLAYER).setState(QuestState.STARTED);
	}

	/* (non-Javadoc)
	 * @see com.arrow.game.content.quest.Quest#decline()
	 */
	@Override
	public void decline() {
		player.getQuestManager().get(Quests.DEMON_SLAYER).setStage(0);
		player.getQuestManager().get(Quests.DEMON_SLAYER).setState(QuestState.NOT_STARTED);
	}

	/* (non-Javadoc)
	 * @see com.arrow.game.content.quest.Quest#getRequirements()
	 */
	@Override
	public String getRequirements() {
		return "I must be able to defeat a level 27 apocalyptic demon!";
	}


	/* (non-Javadoc)
	 * @see com.arrow.game.content.quest.Quest#getStart()
	 */
	@Override
	public String getStart() {
		return "I can start this quest by speaking to the Gypsy in Varrock square.";
	}
	
}
