package com.rs.game.player.content.quest.impl.princealirescue;

import java.io.Serializable;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.content.quest.Entry;
import com.rs.game.player.content.quest.Quest;
import com.rs.game.player.content.quest.QuestJournal;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.QuestReward;
import com.rs.game.player.content.quest.RewardType;
import com.rs.game.player.content.quest.State.QuestState;

/**
 * @Author Frostbite
 * @Contact<frostbitersps@gmail.com;skype:frostbitersps>
 */
@SuppressWarnings("serial")
public class PrinceAliRescue extends Quest implements Serializable {

	/**
	 * Represents Item Ids
	 */
	public static final int ROPE = 954,
			PINK_SKIRT = 1013,
			WIG = 2421, 
			DYED_WIG = 2419,
			BALL_OF_WOOL = 1759,
			SKIN_PASTE = 2424,
			SOFT_CLAY = 1761,
			KEY_PRINT = 2423,
			BRONZE_KEY = 2418,
			BRONZE_BAR = 2349,
			BEER = 1917;

	/**
	 * Represents the array of disguised items.
	 */
	public static final Item[] DISGUISE = new Item[] {new Item(2424), new Item(2419), new Item(2418), new Item(1013)};

	
	/**
	 * Represents Lady Keli Spawned
	 *
	 */

	public static boolean ladyKeliSpawned = true;

	public PrinceAliRescue(Player player) {
		super(player);
	}

	@Override
	public String getQuestName() {
		return "Prince Ali Rescue";
	}

	@Override
	public void declareDialogues() {

	}

	@Override
	public int getQuestPoints() {
		return 3;
	}

	@Override
	public QuestReward[] getRewards() {
		return new QuestReward[] {
				new QuestReward(RewardType.ITEM, new Item(995, 700)),
				new QuestReward(RewardType.STRING, "Now go pass through the gate that connects Lumbridge and Al-Kharid for free")
		};
	}

	private QuestJournal journal = new QuestJournal() {
		@Override
		public QuestJournal display(int stage) {
			final int progress = player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).getStage();
			final QuestState state = player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).getState();
			switch(progress) {
			/**
			 * Not Started Quest
			 */
			case 0:
				addLog(new Entry(""), new Entry("I can start this quest by speaking to " + keyWord("Hassan") + " at the palace") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				},
				new Entry("in " + keyWord("Al-Kharid.")) {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				});
				break;

				/**
				 * Started Quest
				 */

			case 1:
				addLog(new Entry("I started this quest by speaking to Hassan in Al-Kharid") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				},
				new Entry(" Palace. He told me I should peak to Osman the spymaster.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				});
				addLog(new Entry("I should go and speak to " + keyWord("Osman") + " for details on this quest.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				});
				break;


				/**
				 * Talked with osman
				 */
			case 2:
				addLog(new Entry("I started this quest by speaking to Hassan in Al-Kharid") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				},
				new Entry(" Palace. He told me I should peak to Osman the spymaster.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				});
				addLog(new Entry("I should go and speak to osman for details on this quest.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				});
				addLog(new Entry(keyWord("Prince Ali") + " has been " + keyWord("kidnapped") + " but luckily the spy " + keyWord("spy") + " has ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				},
				new Entry("found he is being held near " + keyWord("draynor village.") + " I will need to ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				},
				new Entry(keyWord("disguise") + " the " + keyWord("Prince") + " and " + keyWord("tie ") + "up his " + keyWord("captor ") + "to " + keyWord("free") + " from ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				},
				new Entry("their " + keyWord("glutches.")) {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				});

				addLog(new Entry("To do this I should:-") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				},

				/**
				 * Tasks
				 * Talk to Leela
				 */
				new Entry("Talk to " + keyWord("Leela ") + "near " + keyWord("Draynor Village") + " for advice.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				});

				/**
				 * Tasks
				 */
				addLog(new Entry("Get a " + keyWord("duplicate ") + "of the " + keyWord("key ") + "that is " + keyWord("imprisoning ") + "the " + "prince.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 3;
					}
				});

				/**
				 * Rope - Completed
				 */
				if(!player.getInventory().containsItem(ROPE, 1)) {
					addLog(new Entry("Get some " + keyWord("rope ") + "to tie up the Princes' " + keyWord("kidnapper.")) {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getInventory().containsItem(ROPE, 1);
						}
					});
				} else {
					if(player.getInventory().containsItem(ROPE, 1)) {
						addLog(new Entry("I have some rope with me") {
							@Override
							public boolean meetsCondition(Player player) {
								return player.getInventory().containsItem(ROPE, 1);
							}
						});
					}
				}

				/**
				 * Skin Paste
				 */
				if(!player.getInventory().containsItem(SKIN_PASTE, 1)) {
					addLog(new Entry("Get something to " + keyWord("colour ") + "the " + keyWord("Princes' skin ") + "as a " + keyWord("disguise.")) {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getInventory().containsItem(SKIN_PASTE, 1);
						}
					});
				} else {
					if(player.getInventory().containsItem(SKIN_PASTE, 1)) {
						addLog(new Entry("I have some skin paste suitable for disguise with me.") {
							@Override
							public boolean meetsCondition(Player player) {
								return player.getInventory().containsItem(SKIN_PASTE, 1);
							}
						});
					}
				}

				/**
				 * Pink Skirt - Completed
				 */
				if(!player.getInventory().containsItem(PINK_SKIRT, 1)) {
					addLog(new Entry("Get a " + keyWord("skirt ") + "similar " + "to his " + keyWord("kidnapper") + " as " + "disguise.") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getInventory().containsItem(PINK_SKIRT, 1);
						}
					});
				} else {
					if (player.getInventory().containsItem(PINK_SKIRT, 1)) {
						addLog(new Entry("I have a skirt suitable for a disguise with me.") {
							@Override
							public boolean meetsCondition(Player player) {
								return player.getInventory().containsItem(PINK_SKIRT, 1);
							}
						});
					}
				}

				/**
				 * Wig - Completed
				 */
				if(!player.getInventory().containsItem(DYED_WIG, 1)) {
					addLog(new Entry("Get a " + keyWord("Wig") + " to " + keyWord("help disguise ") + "the " + keyWord("prince.")) {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getInventory().containsItem(DYED_WIG, 1);
						}
					});
				} else {
					if(player.getInventory().containsItem(DYED_WIG, 1)) {
						addLog(new Entry("I have a wig suitable for disguise with me") {
							@Override
							public boolean meetsCondition(Player player) {
								return player.getInventory().containsItem(DYED_WIG, 1);
							}
						});
					}
				}
				break;

				/**
				 * Got duplicated key
				 */
			case 3:
				addLog(new Entry("I started this quest by speaking to Hassan in Al-Kharid") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				},
				new Entry(" Palace. He told me I should peak to Osman the spymaster.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				});
				addLog(new Entry("I should go and speak to Osman for details on this quest.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				});
				addLog(new Entry(keyWord("Prince Ali") + " has been " + keyWord("kidnapped") + " but luckily the spy " + keyWord("spy") + " has ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 3;
					}
				},
				new Entry("found he is being held near " + keyWord("draynor village.") + " I will need to ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 3;
					}
				},
				new Entry(keyWord("disguise") + " the " + keyWord("Prince") + " and " + keyWord("tie ") + "up his " + keyWord("captor ") + "to " + keyWord("free") + " from ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 3;
					}
				},
				new Entry("their " + keyWord("clutches.")) {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 3;
					}
				});

				addLog(new Entry("To do this I should:-") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 3;
					}
				},

				/**
				 * Tasks
				 * Talk to Leela
				 */
				new Entry("Talk to " + keyWord("Leela ") + "near " + keyWord("Draynor Village") + " for advice.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 3;
					}
				});

				/**
				 * Tasks
				 */
				addLog(new Entry("I have duplicated a key, I need to get it from " + keyWord("Leela")) {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 3;
					}
				});

				/**
				 * Rope - Completed
				 */
				if(!player.getInventory().containsItem(ROPE, 1)) {
					addLog(new Entry("Get some " + keyWord("rope ") + "to tie up the Princes' " + keyWord("kidnapper.")) {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getInventory().containsItem(ROPE, 1);
						}
					});
				} else {
					if(player.getInventory().containsItem(ROPE, 1)) {
						addLog(new Entry("I have some rope with me") {
							@Override
							public boolean meetsCondition(Player player) {
								return player.getInventory().containsItem(ROPE, 1);
							}
						});
					}
				}

				/**
				 * Skin Paste
				 */
				if(!player.getInventory().containsItem(SKIN_PASTE, 1)) {
					addLog(new Entry("Get something to " + keyWord("colour ") + "the " + keyWord("Princes' skin ") + "as a " + keyWord("disguise.")) {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getInventory().containsItem(SKIN_PASTE, 1);
						}
					});
				} else {
					if(player.getInventory().containsItem(SKIN_PASTE, 1)) {
						addLog(new Entry("I have some skin paste suitable for disguise with me.") {
							@Override
							public boolean meetsCondition(Player player) {
								return player.getInventory().containsItem(SKIN_PASTE, 1);
							}
						});
					}
				}

				/**
				 * Pink Skirt - Completed
				 */
				if(!player.getInventory().containsItem(PINK_SKIRT, 1)) {
					addLog(new Entry("Get a " + keyWord("skirt ") + "similar " + "to his " + keyWord("kidnapper") + " as " + "disguise.") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getInventory().containsItem(PINK_SKIRT, 1);
						}
					});
				} else {
					if (player.getInventory().containsItem(PINK_SKIRT, 1)) {
						addLog(new Entry("I have a skirt suitable for a disguise with me.") {
							@Override
							public boolean meetsCondition(Player player) {
								return player.getInventory().containsItem(PINK_SKIRT, 1);
							}
						});
					}
				}

				/**
				 * Wig - Completed
				 */
				if(!player.getInventory().containsItem(DYED_WIG, 1)) {
					addLog(new Entry("Get a " + keyWord("Wig") + " to " + keyWord("help disguise ") + "the " + keyWord("prince.")) {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getInventory().containsItem(DYED_WIG, 1);
						}
					});
				} else {
					if(player.getInventory().containsItem(DYED_WIG, 1)) {
						addLog(new Entry("I have a wig suitable for disguise with me") {
							@Override
							public boolean meetsCondition(Player player) {
								return player.getInventory().containsItem(DYED_WIG, 1);
							}
						});
					}
				}
				break;

				/**
				 * Guard drunk
				 * Leela
				 */
			case 4:
			case 5:
				addLog(new Entry("I started this quest by speaking to Hassan in Al-Kharid") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				},
				new Entry(" Palace. He told me I should peak to Osman the spymaster.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				});
				addLog(new Entry("I should go and speak to Osman for details on this quest.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				});
				/**
				 * Tasks
				 * Talk to Leela
				 */
				addLog(new Entry("Talk to Leela near Draynor Village for advice.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				});

				addLog(new Entry("Do something to prevent " +keyWord("Joe the Guard") + " seeing the") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 6;
					}
				},
				new Entry(" escape") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 6;
					}
				});
				addLog(new Entry("Use the " + keyWord("Pink Skirt") + ", " + keyWord("Rope") + ", " + 
						keyWord("Blonde Wig") + " and " + keyWord("Cell")) {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 7;
					}
				},
				new Entry(keyWord(" Key") + " to free " + keyWord("Prince Ali") + " from his cell somehow.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 7;
					}
				});
				break;

				/**
				 * Successfully got guard drunk
				 */
			case 6:
				addLog(new Entry("I started this quest by speaking to Hassan in Al-Kharid") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				},
				new Entry(" Palace. He told me I should peak to Osman the spymaster.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				});
				addLog(new Entry("I should go and speak to Osman for details on this quest.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				});
				/**
				 * Talk to Leela
				 */
				addLog(new Entry("Talk to Leela near Draynor Village for advice.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				});

				addLog(new Entry("I also had to prevent the Guard from seeing what I was up") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 4;
					}
				},
				new Entry(" to, by getting him drunk.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 4;
					}
				});
				addLog(new Entry("Use the " + keyWord("Pink Skirt") + ", " + keyWord("Rope") + ", " + 
						keyWord("Blonde Wig") + " and " + keyWord("Cell")) {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 7;
					}
				},
				new Entry(keyWord(" Key") + " to free " + keyWord("Prince Ali") + " from his cell somehow.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 7;
					}
				});
				break;

				/**
				 * Tied up Keli
				 */

			case 7:
				addLog(new Entry("I started this quest by speaking to Hassan in Al-Kharid") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				},
				new Entry(" Palace. He told me I should peak to Osman the spymaster.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				});
				addLog(new Entry("I should go and speak to Osman for details on this quest.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				});
				addLog(new Entry("Prince Ali has been kidnapped but luckily the spy spy has ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				},
				new Entry("found he is being held near draynor village. I will need to ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				},
				new Entry("disguise the Prince and tie up his captor to free from ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				},
				new Entry("their glutches.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				});
				addLog(new Entry("I also had to prevent the Guard from seeing what I was up") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 4;
					}
				},
				new Entry(" to, by getting him drunk.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 4;
					}
				});
				addLog(new Entry("With the guard disposed of, I used my rope to tie up Lady ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 6;
					}
				},
				new Entry("Keli in a cupboard, so I could disguise the Prince.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 6;
					}
				});
				addLog(new Entry("I need to " + keyWord("unlock the cell door ") + "and then give the Prince") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 7;
					}
				});

				addLog(new Entry("the " + keyWord("Skin Potion") + ", " + keyWord("Pink Skirt") + ", " + keyWord("Rope") + ", " + 
						keyWord("Blonde Wig") + " and " + keyWord("Cell")) {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 7;
					}
				},
				new Entry(keyWord(" Key") + " to free " + keyWord("Prince Ali") + " from his cell somehow.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 7;
					}
				});
				break;

			case 8:
				addLog(new Entry("I started this quest by speaking to Hassan in Al-Kharid") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				},
				new Entry(" Palace. He told me I should peak to Osman the spymaster.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				});
				addLog(new Entry("I should go and speak to Osman for details on this quest.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				});
				addLog(new Entry("Prince Ali has been kidnapped but luckily the spy spy has ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				},
				new Entry("found he is being held near draynor village. I will need to ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				},
				new Entry("disguise the Prince and tie up his captor to free from ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				},
				new Entry("their glutches.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				});
				addLog(new Entry("I also had to prevent the Guard from seeing what I was up") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 4;
					}
				},
				new Entry(" to, by getting him drunk.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 4;
					}
				});
				addLog(new Entry("With the guard disposed of, I used my rope to tie up Lady ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 6;
					}
				},
				new Entry("Keli in a cupboard, so I could disguise the Prince.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 6;
					}
				});
				addLog(new Entry("I then used a wig, a skirt, and some skin paste to make the ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 6;
					}
				},
				new Entry("prince look like Lady Keli so he could escape to his ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 6;
					}
				},
				new Entry("freedom with Leela after unlocking his sell door.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 6;
					}
				});
				addLog(new Entry("I should return to " + keyWord("Hassan ") + "to claim my reward.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 8;
					}
				});
				break;

				/**
				 * Completed Quest
				 */
			case 9:
				addLog(new Entry("I started this quest by speaking to Hassan in Al-Kharid") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				},
				new Entry(" Palace. He told me I should peak to Osman the spymaster.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 0;
					}
				});
				addLog(new Entry("I should go and speak to Osman for details on this quest.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 1;
					}
				});
				addLog(new Entry("Prince Ali has been kidnapped but luckily the spy has ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				},
				new Entry("found he is being held near draynor village. I will need to ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				},
				new Entry("disguise" + " the Prince and tie up his captor to free from ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				},
				new Entry("their glutches.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 2;
					}
				});
				addLog(new Entry("I also had to prevent the Guard from seeing what I was up") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 4;
					}
				});
				addLog(new Entry("I also had to prevent the Guard from seeing what I was up") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 4;
					}
				},
				new Entry(" to, by getting him drunk.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 4;
					}
				});
				addLog(new Entry("With the guard disposed of, I used my rope to tie up Lady ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 6;
					}
				},
				new Entry("Keli in a cupboard, so I could disguise the Prince.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 6;
					}
				});
				addLog(new Entry("I then used a wig, a skirt, and some skin paste to make the ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 6;
					}
				},
				new Entry("prince look like Lady Keli so he could escape to his ") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 6;
					}
				},
				new Entry("freedom with Leela after unlocking his sell door.") {
					@Override
					public boolean meetsCondition(Player player) {
						return progress > 6;
					}
				});
				addLog(new Entry("Hassan the chancellor rewarded me for all of my help.") {
					@Override
					public boolean meetsCondition(Player player) {
						return state == QuestState.COMPLETED;
					}
				},
				new Entry("I am now a friend of Al-Kharid and may pass through the ") {
					@Override
					public boolean meetsCondition(Player player) {
						return state == QuestState.COMPLETED;
					}
				},
				new Entry("gate leading between Lumbridge and Al-Kharid for free.") {
					@Override
					public boolean meetsCondition(Player player) {
						return state == QuestState.COMPLETED;
					}
				},
				new Entry("") {

				},
				new Entry("<col=FF0000>QUEST COMPLETE!") {

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
		return new int[] {273, 10, 110};
	}

	@Override
	public int getSlotId() {
		return 10;
	}

	@Override
	public int getRewardItemId() {
		return 999;
	}

	@Override
	public void accept() {
		player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).setStage(1);
		player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).setState(QuestState.STARTED);
	}

	@Override
	public void decline() {
		player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).setStage(0);
		player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).setState(QuestState.NOT_STARTED);
	}

	@Override
	public String getRequirements() {
		return "Able to get past aggressive combat level 26 Jail Guards.";
	}


	@Override
	public String getStart() {
		return "I can start this quest by speaking to Hassan in the palace in Al-Kharid.";
	}
	
	public static boolean setLadyKeliSpawned(boolean spawned) {
		return ladyKeliSpawned = spawned;
	}
	
	public static boolean isLadyKeliSpawned() {
		return ladyKeliSpawned;
	}
}
