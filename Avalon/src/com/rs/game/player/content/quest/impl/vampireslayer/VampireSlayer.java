package com.rs.game.player.content.quest.impl.vampireslayer;

import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.quest.Entry;
import com.rs.game.player.content.quest.Quest;
import com.rs.game.player.content.quest.QuestJournal;
import com.rs.game.player.content.quest.QuestReward;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.RewardType;
import com.rs.game.player.content.quest.State.QuestState;

/**
 * @Author Frostbite
 * @Contact<frostbitersps@gmail.com;skype:frostbitersps>
 */
@SuppressWarnings("serial")
public class VampireSlayer extends Quest {

    /**
     * Opens coffin - sit slides open and spawns npc.
     * and spawns a new coffin (shuts after a minute)
     */

    /**
     * Represents Quest Items
     */

    /**
	 * 
	 */
	private static final long serialVersionUID = 64055744574709736L;

	/**
     * Beer
     */

    public static final int BEER = 1917;

    /**
     * Stakes
     */

    public static final int STAKE = 1549;

    /**
     * Garlic
     */

    public static final int GARLIC = 1550;

    /**
     * Hammer
     */

    public static final int HAMMER = 2347;


    public VampireSlayer(Player player) {
        super(player);
    }

    @Override
    public String getQuestName() {
        return "Vampire Slayer";
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
                new QuestReward(RewardType.EXPERIENCE, Skills.ATTACK, 4825)
        };
    }

	QuestJournal journal = new QuestJournal() {
        @Override
        public QuestJournal display(int stage) {
        	final int progress = player.getQuestManager().get(Quests.VAMPIRE_SLAYER).getStage();
            switch(progress) {
                /**
                 * Not Started
                 */
                case 0:
                addLog(new Entry("I can start this quest by speaking to " + keyWord("Morgan") + " who is in ") {
                    @Override
                     public boolean meetsCondition(Player player) {
                        return progress > 0;
                    }
                },
                new Entry(keyWord("Draynor Village") + ".") {
                    @Override
                    public boolean meetsCondition(Player player) {
                        return progress > 0;
                    }
                },
                new Entry("") {

                });
                    addLog(new Entry("Requirements:") {

                    },
                    new Entry("Must be able to kill a level 34 " + keyWord("Vampire") + ".") {

                    });
                    break;

                /**
                 * Started Quest
                 */
                case 1:
                    addLog(new Entry("I spoke to Morgan in Draynor Village. He told me that the ") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 0;
                        }
                    },
                    new Entry("locals are being attacked by a terrifying Vampire!") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 0;
                        }
                    },
                    new Entry("") {

                    });
                    addLog(new Entry("I need to speak to " + keyWord("Dr Harlow") + " who can normally be found in ") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 1;
                        }
                    },
                    new Entry("the " + keyWord("Blue Moon Inn") + " in " + keyWord("Varrock") + ".") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 1;
                        }
                    });
                    break;

                case 2:
                    addLog(new Entry("I spoke to Morgan in Draynor Village. He told me that the ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry("locals are being attacked by a terrifying Vampire!") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 0;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("I need to speak to Dr Harlow who can normally be found in ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 1;
                               }
                           },
                            new Entry("the Blue Moon Inn in Varrock.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 1;
                                }
                            },
                    new Entry("") {

                    });
                    addLog(new Entry("I should see what advice " + keyWord("Dr Harlow") + " can give me about killing") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 2;
                        }
                    },
                    new Entry(keyWord(" Vampires") + ",") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 2;
                        }
                    });
                    addLog(new Entry("When I'm ready, I should go to " + keyWord("Draynor Manor") + ", north of ") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 3;
                        }
                    },
                    new Entry("Draynor to kill the " + keyWord("Vampire") + " that's living in the basement.") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 3;
                        }
                    });
                    break;

                /**
                 * Received information about killing a vampire.
                 */
                case 3:
                    if(player.getInventory().containsOneItem(VampireSlayer.STAKE)) {
                        addLog(new Entry("I spoke to Morgan in Draynor Village. He told me that the ") {
                                   @Override
                                   public boolean meetsCondition(Player player) {
                                       return progress > 0;
                                   }
                               },
                                new Entry("locals are being attacked by a terrifying Vampire!") {
                                    @Override
                                    public boolean meetsCondition(Player player) {
                                        return progress > 0;
                                    }
                                },
                                new Entry("") {

                                });
                        addLog(new Entry("I have spoken to Dr Harlow. He seemed terribly drunk, and ") {
                                   @Override
                                   public boolean meetsCondition(Player player) {
                                       return progress > 2;
                                   }
                               },
                                new Entry("he kept asking me to buy him drinks.") {
                                    @Override
                                    public boolean meetsCondition(Player player) {
                                        return progress > 2;
                                    }
                                },
                                new Entry("") {

                                });
                        addLog(new Entry("Dr Harlow gave me as take to finish off the Vampire when ") {
                                   @Override
                                   public boolean meetsCondition(Player player) {
                                       return progress > 2;
                                   }
                               },
                                new Entry("I'm fighting it.") {
                                    @Override
                                    public boolean meetsCondition(Player player) {
                                        return progress > 2;
                                    }
                                },
                                new Entry("") {

                                });
                        /**
                         * Player has garlic ? or naw.
                         */
                        if(!player.getInventory().containsItem(VampireSlayer.GARLIC, 1)) {
                            addLog(new Entry("Dr Harlow said I should get some " + keyWord("garlic") + " to weaken the ") {
                                    @Override
                                    public boolean meetsCondition(Player player) {
                                        return player.getInventory().containsItem(VampireSlayer.GARLIC, 1);
                                    }
                            },
                            new Entry(keyWord("Vampire") + " He thought I might find " + keyWord("garlic") + " somewhere in ") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return player.getInventory().containsItem(VampireSlayer.GARLIC, 1);
                                }
                            },
                            new Entry(keyWord("Morgan's") + " house.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return player.getInventory().containsItem(VampireSlayer.GARLIC, 1);
                                }
                            });
                        } else {
                            addLog(new Entry("I have some garlic which should weaken the Vampire.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return player.getInventory().containsItem(VampireSlayer.GARLIC, 1);
                                }
                            });
                        }
                        /**
                         * Player has Hammer ? or naw.
                         */
                        if(!player.getInventory().containsItem(VampireSlayer.HAMMER, 1)) {
                            addLog(new Entry("I'll need a " + keyWord("hammer") + " to drive the stake deeply into the ") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return player.getInventory().containsItem(VampireSlayer.HAMMER, 1);
                                }
                            },
                            new Entry(keyWord("Vampire's ") + "chest.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return player.getInventory().containsItem(VampireSlayer.HAMMER, 1);
                                }
                            });
                        } else {
                            addLog(new Entry("I've got a hammer to drive the stake deep into the ") {

                            },
                            new Entry("the Vampire's chest.") {

                            });
                        }
                    } else {
                        //Player does not have Stake.
                        addLog(new Entry("I should see what advice" + keyWord(" Dr Harlow ") + "can give me about killing") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return player.getInventory().containsOneItem(VampireSlayer.STAKE);
                            }
                        },
                        new Entry(keyWord("Vampires") + ".") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return player.getInventory().containsOneItem(VampireSlayer.STAKE);
                            }
                        });
                    }
                    //Ending of stage (if has stake ? and has stake);
                    addLog(new Entry("When I'm ready, I should go to " + keyWord("Draynor Manor") + ", north of ") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 3;
                        }
                    },
                    new Entry("Draynor to kill the " + keyWord("Vampire") + " that's living in the basement.") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 3;
                        }
                    });
                    break;

                /**
                 * Completed Quest
                 */

                case 4:
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
        return new int[] {178, 1, 3};
    }

    @Override
    public int getSlotId() {
        return 16;
    }

    @Override
    public int getRewardItemId() {
        return STAKE;
    }

    @Override
    public void accept() {
        player.getDialogueManager().startDialogue("Morgan", -2);
        player.getQuestManager().get(Quests.VAMPIRE_SLAYER).setStage(1);
        player.getQuestManager().get(Quests.VAMPIRE_SLAYER).setState(QuestState.STARTED);
    }

    @Override
    public void decline() {
        player.getDialogueManager().startDialogue("Morgan", -3);
    }

    @Override
    public String getRequirements() {
        return "Requirements: Must be able to kill a level 34 Vampire.";
    }

    @Override
    public String getStart() {
        return "I can start this quest by speaking to Morgan who is in" +
                "Draynor Village.";
    }
}
