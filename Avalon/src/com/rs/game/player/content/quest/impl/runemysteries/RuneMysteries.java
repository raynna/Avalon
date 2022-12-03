package com.rs.game.player.content.quest.impl.runemysteries;


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
public class RuneMysteries extends Quest {

	public static final int AIR_TALISMAN = 15361,
     RESEARCH_PACKAGE = 290,
     RESEARCH_NOTES = 291;


    public RuneMysteries(Player player) {
        super(player);
    }

    @Override
    public String getQuestName() {
        return "Rune Mysteries";
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
        return new QuestReward[] { new QuestReward(RewardType.STRING, "Introduction to the Runecrafting skill and rune essence mine"), new QuestReward(RewardType.ITEM, new Item(AIR_TALISMAN, 1)) };
    }

    private final QuestJournal journal = new QuestJournal() {
        @Override
        public QuestJournal display(int stage) {
        	stage = player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage();
        	
             if (stage == 0) {
                addLog(new Entry(""), new Entry("To start this quest, I can speak to " + keyWord(" Duke Horacio")) {
                           @Override
                           public boolean meetsCondition(Player player) {
                               return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 0;

                           }
                       },

                        new Entry("upstairs in the " + keyWord("Lumbridge Castle.")) {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 0;
                            }
                        });
            } else {
                addLog(new Entry("I spoke to Duke Horacio and he showed me a strange") {
                           @Override
                           public boolean meetsCondition(Player player) {
                               return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 0;
                           }
                       },
                        new Entry("talisman that had been found by one of his subjects.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 0;
                            }
                        },

                        new Entry("I agreed to take it to the Wizards' Tower, South West of") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 0;
                            }
                        },

                        new Entry("Lumbridge for further examination by the wizards.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 0;
                            }
                        });
                     }
            if (stage == 1) {
                addLog(new Entry("I need to find the " + keyWord("Head Wizard ") + "and give him the " + keyWord("Talisman")) {
                    @Override
                    public boolean meetsCondition(Player player) {
                        return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 1;
                    }

                });
            }

            if (stage == 2) {
                addLog(new Entry("I gave the Talisman to the Wizard but I didn't want to help") {
                           @Override
                           public boolean meetsCondition(Player player) {
                               return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 0;
                           }
                       },

                        new Entry("him in his research right now.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 0;

                            }
                        },

                        new Entry("I should talk to " + keyWord("Sedridor") + " again to continue this quest.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 2;

                            }
                        });
            } else {
                if(stage > 2) {
                    addLog(new Entry("I gave the Talisman to the Head Wizard of the Tower and") {
                        @Override
                         public boolean meetsCondition(Player player) {
                            return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 2;
                        }
                    },
                    new Entry("agreed to help him with his research into rune stones.") {
                        @Override
                    public boolean meetsCondition(Player player) {
                            return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 2;
                        }
                    });
                }
            }

            if(stage == 3) {
                addLog(new Entry("I should take this " + keyWord("Research Package") + " to " + keyWord("Aubury ")  + "in " + keyWord("Varrock")) {
                    @Override
                public boolean meetsCondition(Player player) {
                        return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 3;
                    }
                });
            } else {
                if(stage > 3) {
                    addLog(new Entry("I took the research package to Varrock and delivered it.") {
                        @Override
                    public boolean meetsCondition(Player player) {
                            return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 3;
                        }
                    });
                }
            }

            if(stage == 4) {
                addLog(new Entry("I should speak to " + keyWord("Aubury") + " again when he has finished") {
                    @Override
                public boolean meetsCondition(Player player) {
                        return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 4;
                    }

                },
                new Entry("examining the " + keyWord("research package ") + "I have delivered him.") {
                    @Override
                public boolean meetsCondition(Player player) {
                        return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 4;
                    }
                });
            } else {
                if(stage > 4) {
                    addLog(new Entry("Aubury was intersted in the research package and gave") {
                        @Override
                    public boolean meetsCondition(Player player) {
                            return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 4;
                        }
                    },
                     new Entry(" me his own research notes to deliver to Sedridor.") {
                         @Override
                     public boolean meetsCondition(Player player) {
                             return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 4;
                         }
                     });
                }
            }

            if(stage == 5) {
                addLog(new Entry("I should take these " + keyWord("notes") + " to " + keyWord("Sedridor ") + "and see what he says") {
                    @Override
                public boolean meetsCondition(Player player) {
                        return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() > 5;
                    }
                });
            }

            if(stage == 6) {
                addLog(new Entry("I brought Sedridor the research notes that Aubury had ") {
                    @Override
                public boolean meetsCondition(Player player) {
                        return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() == 6;
                    }
                },
                new Entry("compiled so that he could compare their research. They ") {
                    @Override
                    public boolean meetsCondition(Player player) {
                        return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() == 6;
                    }
                },
                        new Entry("discovered that it was now possible to create new rune ") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() == 6;
                            }
                        },
                        new Entry("stones, a skill that had been thought lost forever.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() == 6;
                            }
                        },
                        new Entry(" In return for all of my help they taught me how to do this, ") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() == 6;
                            }
                        },
                        new Entry("and will teleport me to mine blank runes anytime.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage() == 6;
                            }
                        });
            }
                if(player.getQuestManager().get(Quests.RUNE_MYSTERIES).getState() == QuestState.COMPLETED) {
                    addLog(new Entry(""), new Entry("<col=FF0000> QUEST COMPLETE!</col>") {

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
        return new int[] {63, 1, 6};
    }

    @Override
    public int getSlotId() {
        return 13;
    }

    @Override
    public int getRewardItemId() {
        return AIR_TALISMAN;
    }

    @Override
    public void accept() {
        player.getDialogueManager().startDialogue("DukeHoracio", -3);
    }

    @Override
    public void decline() {
        player.getDialogueManager().startDialogue("DukeHoracio", -4);
    }

    @Override
    public String getStart() {
        return "Speak to Duke Horacio, upstairs in Lumbridge Castle.";
    }
}
