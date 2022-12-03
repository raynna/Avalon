package com.rs.game.player.content.quest.impl.piratestreasure;

import java.io.Serializable;

import com.rs.game.World;
import com.rs.game.WorldTile;
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
 * @Author arrow
 * @Contact<arrowrsps@gmail.com;skype:arrowrsps>
 */
@SuppressWarnings("serial")
public class PiratesTreasure extends Quest implements Serializable  {


	public static final int KARAMJA_RUM = 431;
	public static final int CHEST_KEY = 432;
	public static final int PIRATE_MESSAGE = 433;
	public static final int CHEST = 7956;
	public static final Item[] CASKET_REWARDS = new Item[] {new Item(995, 450), new Item(1635), new Item(1605)};

	public PiratesTreasure(Player player) {
		super(player);
	}

	@Override
	public String getQuestName() {
		return "Pirate's Treasure";
	}

	@Override
	public void declareDialogues() {

	}

	@Override
	public int getQuestPoints() {
		return 2;
	}

	@Override
	public QuestReward[] getRewards() {
		return new QuestReward[] {new QuestReward(RewardType.ITEM, new Item(995, 450)), new QuestReward(RewardType.STRING, "Access to One-Eyed Hector's Treasure Chest."), new QuestReward(RewardType.STRING, "Ability to use the pay-fare option to and from Karamja.")};
	}

	private QuestJournal journal = new QuestJournal() {
		@Override
		public QuestJournal display(int stage) {
			int progress = player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage();
			/**
			 * Starting Addlogs
			 */
			if(progress == 0) {
				addLog(new Entry(""), new Entry("I can start this quest by speaking to " + keyWord("Redbeard Frank") + " who") {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 0;//Meets Condition
					}
				},
				new Entry(" is at " + keyWord("Port Sarim.")) {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 0;//Meets Condition
					}
				});
			} else {
				addLog(new Entry(""), new Entry("I have spoken to " + keyWord("Redbeard Frank.") + "He has agreed to tell me ") {
					@Override
					public boolean meetsCondition(Player player){
						return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 6;//Meets Condition
					}
				},
				new Entry("the location of some " + keyWord("treasure") + " for some " + keyWord("Karamja Rum.")) {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 6;//Meets Condition
					}
				});
			}

			/**
			 * Completed From here...
			 */

			/**
			 * Starting I need to get rum
			 */

			/**
			 * I need to go to karamja and buy some rum I hope its not too expensive..
			 * Progress 1(After talking to frank) - Without having rum
			 */
			if(progress < 6) {
				if(progress > 0 && progress < 3 && !player.getInventory().containsItem(PiratesTreasure.KARAMJA_RUM, 1)) {
					addLog(new Entry(""), new Entry("I need to go to " + keyWord("Karamja") + " and buy some " + keyWord("rum.") + " I hope it is not") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getInventory().containsItem(PiratesTreasure.KARAMJA_RUM, 1);
						}
					},
					new Entry(" too expensive.") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getInventory().containsItem(PiratesTreasure.KARAMJA_RUM, 1);
						}
					});
				}

				/**
				 * I have taken employment on the banana plantation as the Custom Officers might not notice the rum if it is covered up by bananas.
				 * After talking to Lucas.. (stage 2)
				 */
				if (progress > 1) {
					addLog(new Entry(""), new Entry("I have taken employment on the " + keyWord("banana plantation") + ", as the ") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 3;
						}
					},
					new Entry(keyWord("Customs Officers ") + "might not notice the " + keyWord("rum ") + "if it is covered ") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 3;
						}
					},
					new Entry("in" + keyWord(" bananas.")) {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 3;
						}
					});
				}

				/**
				 * I have the rum and now i need to find a way to get the rum off karamja, this might be tricky as the customs officers are searching for it.
				 * Progress 1 (after talking to frank) - with having rum
				 */
				if (progress > 0 && progress < 2 && player.getInventory().containsItem(PiratesTreasure.KARAMJA_RUM, 1)) {
					addLog(new Entry(""), new Entry("I have the " + keyWord("rum") + ", and now I need to find a new way to get the rum") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 3;
						}
					},
					new Entry(" off " + keyWord("Karamja") + ". This might be tricky, as the " + keyWord("Customs Officers")) {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 3;
						}
					},
					new Entry(" are searching people for it.") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 3;
						}
					});
				}
				/**
				 * I'm sure i will be able to hide my rum in the next crate destine for wydin's store.
				 * After talking to Lucas.. (stage 2) - Having rum
				 */
				if(progress > 1 && player.getInventory().containsItem(PiratesTreasure.KARAMJA_RUM, 1)) {
					addLog(new Entry(""), new Entry("I'm sure I will be able to hide my " + keyWord("rum") + " in the next crate") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 3;
						}
					},
					new Entry(" destined for " + keyWord("Wydin's store.")) {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 3;
						}
					});
				}

				/**
				 * Hidden rum and after talking with lucas (Stage 3)
				 */
				if(progress > 2) {
					addLog(new Entry(""), new Entry("I have hidden my " + keyWord("rum") + " in the crate it with") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 3;
						}
					},
					new Entry(keyWord(" bananas") + " I should speak to " + keyWord("Luthas ") + "and have it shipped") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 3;
						}
					},
					new Entry(" over.") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 3;
						}
					});
				}

				if (progress > 3) {
					addLog(new Entry(""), new Entry("I have spoken to " + keyWord("Luthas") + ", and the crate has been shipped") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 5;
						}
					},
					new Entry(" to " + keyWord("Wydin's store ") + "in " + keyWord("Port Sarim.") + " Now all I have to do is get to ") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 5;
						}
					},
					new Entry("it...") {
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 5;
						}
					});
				}

				if (progress > 4) {
					addLog(new Entry("I have taken a job at " + keyWord("Wydin's store.") + " I now have access to ") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 5;
						}
					},
					new Entry("the back room of his shop where the " + keyWord("rum ") + "is hidden...") {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 5;
						}
					});
				}
			}

			if(progress > 5) {
				addLog(new Entry(""), new Entry("I have the " + keyWord("Karamja Rum.") + " I should take it to " + keyWord("Redbeard Frank.")) {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 6;
					}
				});
			}

			if(progress > 6) {
				addLog(new Entry("I have smuggled some rum off Karamja, and retrieved it") {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 6;
					}
				},
				new Entry(" from the back room of Wydin's shop.") {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 6;
					}
				});
			}

			if(progress > 6) {
				addLog(new Entry(""), new Entry("I have given the rum to " + keyWord("Redbeard Frank.") + "He told me ") {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 7;
					}
				},
				new Entry("that the " + keyWord("treasure") + " is hidden in the chest in the upstairs ") {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 7;
					}
				},
				new Entry("room of the " + "Blue Moon Inn " + "in " + keyWord("Varrock.")) {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 7;
					}
				});
			}

			if (progress > 6 && progress < 8 && player.getInventory().containsOneItem(PiratesTreasure.CHEST_KEY)) {
				addLog(new Entry(""), new Entry("I have a " + keyWord("key ") + "that can be used to unlock that chest that ") {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 7;
					}
				},
				new Entry("holds the treasure.") {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 7;
					}
				});
			} else if(progress > 6 && progress < 8 && !player.getInventory().containsOneItem(PiratesTreasure.CHEST_KEY)) {
			addLog(new Entry(""), new Entry("I have lost the " + keyWord("key ") + "that " + keyWord("Redbeard Frank ") + "gave me. I should") {
							@Override
							public boolean meetsCondition(Player player) {
								return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 7;
							}
						},
						new Entry(" see if he has another.") {
							@Override
							public boolean meetsCondition(Player player) {
								return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 7;
							}
						});
			}

			if(progress > 7) {
				addLog(new Entry(""), new Entry("I have opened the chest in the Blue Moon, and found a ") {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 8;
					}
				},
				new Entry("note inside. I think it will tell me where to dig.") {
					@Override
					public boolean meetsCondition(Player player) {
						return player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 8;
					}
				});
			}

			if(progress > 8) {
				addLog(new Entry(""), new Entry("The notes reads " + keyWord("'Visit the city of the White Knights. In the")) {
							@Override
							public boolean meetsCondition(Player player) {
								return player.getQuestManager().get(Quests.PIRATES_TREASURE).getState() == QuestState.COMPLETED;
							}
						},
						new Entry(keyWord(" park, Saradomin points to the X which marks the spot.'")) {
							@Override
							public boolean meetsCondition(Player player) {
								return player.getQuestManager().get(Quests.PIRATES_TREASURE).getState() == QuestState.COMPLETED;
							}
						});
			}
				if(progress > 8 && !player.getInventory().containsOneItem(PiratesTreasure.PIRATE_MESSAGE) && player.getQuestManager().get(Quests.PIRATES_TREASURE).getState() != QuestState.COMPLETED) {
					addLog(new Entry(""), new Entry("It's a good job I remembered that, as I have lost the " + keyWord("note.")) {
						@Override
						public boolean meetsCondition(Player player) {
							return player.getQuestManager().get(Quests.PIRATES_TREASURE).getState() == QuestState.COMPLETED;
						}
					});
				}

			if(player.getQuestManager().get(Quests.PIRATES_TREASURE).getState() == QuestState.COMPLETED) {
				addLog(new Entry(""), new Entry("<col=FF0000>QUEST COMPLETE!"), new Entry(""),
						new Entry("I've found the treasure, gained 2 Quest Points and gained "),
						new Entry("access to the Pay-fare option to travel to and from "),
						new Entry("Karamja!"){

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
	        return new int[] {71, 1, 4};
	    }

	@Override
	public int getSlotId() {
		return 9;
	}

	@Override
	public int getRewardItemId() {
		return CHEST;
	}

	@Override
	public void accept() {
		player.getQuestManager().get(Quests.PIRATES_TREASURE).setStage(1);
		player.getQuestManager().get(Quests.PIRATES_TREASURE).setState(QuestState.STARTED);
		player.getDialogueManager().startDialogue("RedbeardFrank", 1);

	}

	@Override
	public void decline() {

	}

	@Override
	public String getStart() {
		return "I can start this quest by speaking to Redbeard Frank who " +
				"is at Port Sarim.";
	}

	public static void handleCasket(Player player) {
		if(player.getQuestManager().get(Quests.PIRATES_TREASURE).getState() == QuestState.COMPLETED) {
			for (Item i : CASKET_REWARDS) {
				if (!player.getInventory().addItem(i)) {
					World.addGroundItem(i, new WorldTile(player));
				}
			}
			player.getInventory().deleteItem(CHEST, 1);
			player.getPackets().sendGameMessage("You open the casket, and find One-Eyed Hector's treasure.");
		}
	}


	public static boolean digSpot(Player player) {
		if (player.getQuestManager().get(Quests.PIRATES_TREASURE).getState() == QuestState.STARTED) {
			if (player.getX() == 3000 && player.getY() == 3383) {
				if (player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 8) {
					player.getQuestManager().get(Quests.PIRATES_TREASURE).setState(QuestState.COMPLETED);
					player.getQuestManager().get(Quests.PIRATES_TREASURE).sendReward();
					if (player.getInventory().getFreeSlots() > 1) {
						player.getInventory().addItem(CHEST, 1);
					} else {
						World.addGroundItem(new Item(CHEST, 1), new WorldTile(player));
					}
				}
			}
		}
			return false;
		}
}
