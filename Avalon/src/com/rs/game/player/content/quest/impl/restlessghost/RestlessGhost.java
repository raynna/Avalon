package com.rs.game.player.content.quest.impl.restlessghost;

import java.io.Serializable;

import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.quest.Entry;
import com.rs.game.player.content.quest.Quest;
import com.rs.game.player.content.quest.QuestJournal;
import com.rs.game.player.content.quest.QuestReward;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.RewardType;


@SuppressWarnings("serial")
public class RestlessGhost extends Quest implements Serializable {

	/**
     * Quest Items
     */
    public static final int GHOSTSPEAK_AMULET = 552;

    /**
     * Represents the skull item.
     */
    private static final int SKULL = 964;

    /**
     * Represents the close animation of a content.
     */
    @SuppressWarnings("unused")
	private static final Animation OPEN_ANIM = new Animation(536);

    /**
     * Represents the close animation of a content.
     */
    @SuppressWarnings("unused")
	private static final Animation CLOSE_ANIM = new Animation(535);

    /**
     * Quest Information
     * Open coffin - spawns npc(after animation) (npc opens for every player)
     * When you get done talking to him (stage 3) he disapears.
     * Coffin closes after x amount of seconds.
     * Taking skull (spawns skeleton) - "The skeleton in the corner suddenly comes to life!";
     * Drop sendItemDialogue(Dropping this skull here will destroy it!)
     * - Opens Dialogue ("Are you sure you want to drop the skull?", "Yes", "No")
     * sendItemDialogue("The skull disintegrates before your eyes!");
     * Ghost at coffin disapears after x minutes. (all players nearby)
     */

    public RestlessGhost(Player player) {
        super(player);
    }

    @Override
    public String getQuestName() {
        return "The Restless Ghost";
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
        return new QuestReward[] {new QuestReward(RewardType.EXPERIENCE, Skills.PRAYER, 1125)};
}

    private final QuestJournal journal = new QuestJournal() {
        @Override
        public QuestJournal display(int stage) {
        	final int progress = player.getQuestManager().get(Quests.THE_RESTLESS_GHOST).getStage();
            switch(progress) {
                /**
                 * Not Started
                 */
                case 0:
                    addLog(new Entry("I can start this quest by speaking to " + keyWord("Father Aereck") + " in the ") {
                        @Override
                    public boolean meetsCondition(Player player) {
                            return progress > 0;
                        }
                    },
                    new Entry(keyWord("church") + " next to " + keyWord("Lumbridge Castle.")) {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 0;
                        }
                    });
                    break;

                    /**
                     * Started
                     */
                case 1:
                    addLog(new Entry("Father Aereck asked me to help him deal with the Ghost in ") {
                        @Override
                    public boolean meetsCondition(Player player) {
                            return progress > 0;
                        }
                    },
                    new Entry("the graveyard next to the church.") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 0;
                        }
                    });
                    addLog(new Entry("I should find " + keyWord("Father Urhney") + " who is an expert on " + keyWord("ghosts. ")) {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 1;
                        }
                    },
                    new Entry("He lives in a " + keyWord("shack") + " in " + keyWord("Lumbridge Swamp.")) {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 1;
                        }
                    });
                    break;

                /**
                 * Talked with Father Urhney
                 */

                case 2:
                    addLog(new Entry("Father Aereck asked me to help him deal with the Ghost in ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry("the graveyard next to the church.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 0;
                                }
                            });
                    addLog(new Entry("I found Father urhney in the swamp south of Lumbridge.He ") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 1;
                        }
                    },
                    new Entry("gave me an Amulet of Ghostspeak to talk to the ghost.") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 1;
                        }
                    });
                    addLog(new Entry("I should talk to the " + keyWord("Ghost") + " to find out why it is haunting the ") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 2;
                        }
                    },
                    new Entry(keyWord("graveyard crypt")) {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 1;
                        }

                    });
                    if(!player.getInventory().containsOneItem(GHOSTSPEAK_AMULET)) {
                        addLog(new Entry("I seem to have lost my " + keyWord("Amulet of Ghost speak.") + " I should") {

                        },
                        new Entry(" talk to " + keyWord("Father Urheny") + " and see if he has a replacement.") {

                        });
                    }
                    break;

                /**
                 * Talked with Ghost
                 */
                case 3:
                    addLog(new Entry("Father Aereck asked me to help him deal with the Ghost in ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry("the graveyard next to the church.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 0;
                                }
                            });
                    addLog(new Entry("I found Father Urhney in the swamp south of Lumbridge. He ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 1;
                               }
                           },
                            new Entry("gave me an Amulet of Ghostspeak to talk to the ghost.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 1;
                                }
                            });
                    addLog(new Entry("I spoke to the Ghost and he told me he could not rest in") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return progress > 2;
                            }
                    },
                    new Entry(" peace because an evil wizard had stolen his skull.") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 2;
                        }
                    });
                    addLog(new Entry("I should go and search the " + keyWord("Wizard's Tower South West of")) {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return player.getInventory().containsOneItem(SKULL);
                        }
                    },
                    new Entry(keyWord(" Lumbridge") + " for the " + keyWord("Ghost's Skull.")) {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return player.getInventory().containsOneItem(SKULL);
                        }
                    });
                    if(!player.getInventory().containsOneItem(GHOSTSPEAK_AMULET)) {
                        addLog(new Entry("I seem to have lost my " + keyWord("Amulet of Ghost speak.") + " I should") {

                     },
                     new Entry(" talk to " + keyWord("Father Urheny") + " and see if he has a replacement.") {

                    });
                    }
                    break;

                /**
                 * Found Skull
                 */
                case 4:
                    addLog(new Entry("Father Aereck asked me to help him deal with the Ghost in ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry("the graveyard next to the church.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 0;
                                }
                            });
                    addLog(new Entry("I found Father Urhney in the swamp south of Lumbridge. He ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 1;
                               }
                           },
                            new Entry("gave me an Amulet of Ghostspeak to talk to the ghost.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 1;
                                }
                            });
                    addLog(new Entry("I spoke to the Ghost and he told me he could not rest in") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 2;
                               }
                           },
                            new Entry(" peace because an evil wizard had stolen his skull.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 2;
                                }
                            });
                    if(player.getInventory().containsOneItem(SKULL)) {
                        addLog(new Entry("I found the Ghost's Skull in the basement of the Wizards'") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return player.getInventory().containsOneItem(SKULL);
                            }
                        },
                        new Entry("Tower. It was guarded by a skeleton, but I took it anyways.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return player.getInventory().containsOneItem(SKULL);
                            }
                        });
                        addLog(new Entry("I should take the " + keyWord("Skull") + " back to the " + keyWord("Ghost") + " so it can rest.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return progress > 3;
                            }
                        });
                    } else {
                        addLog(new Entry("I should go and search the " + keyWord("Wizard's Tower South West of")) {
                                   @Override
                                   public boolean meetsCondition(Player player) {
                                       return player.getInventory().containsOneItem(SKULL);
                                   }
                               },
                                new Entry(keyWord(" Lumbridge") + " for the " + keyWord("Ghost's Skull.")) {
                                    @Override
                                    public boolean meetsCondition(Player player) {
                                        return player.getInventory().containsOneItem(SKULL);
                                    }
                                });
                    }
                    if(!player.getInventory().containsOneItem(GHOSTSPEAK_AMULET)) {
                        addLog(new Entry("I seem to have lost my " + keyWord("Amulet of Ghost speak.") + " I should") {

                    },
                        new Entry(" talk to " + keyWord("Father Urheny") + " and see if he has a replacement.") {

                     });
                    }
                    break;

                /**
                 * Returned Skull - Use item on object
                 * Completed Quest //TODO - Cutscene
                 */
                case 5:
                    addLog(new Entry("Father Aereck asked me to help him deal with the Ghost in ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry("the graveyard next to the church.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 0;
                                }
                            });
                    addLog(new Entry("I found Father Urhney in the swamp south of Lumbridge. He ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 1;
                               }
                           },
                            new Entry("gave me an Amulet of Ghostspeak to talk to the ghost.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 1;
                                }
                            });
                    addLog(new Entry("I spoke to the Ghost and he told me he could not rest in") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 2;
                               }
                           },
                            new Entry(" peace because an evil wizard had stolen his skull.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 2;
                                }
                            });
                    addLog(new Entry("I found the Ghost's Skull in the basement of the Wizards'") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return player.getInventory().containsOneItem(SKULL);
                               }
                           },
                            new Entry("Tower. It was guarded by a skeleton, but I took it anyways.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return player.getInventory().containsOneItem(SKULL);
                                }
                            });
                    addLog(new Entry("I placed the Skull in the Ghost's coffin, and allowed it to ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 4;
                               }
                    },
                    new Entry("rest in peace once more, with gratitude for my help.") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 4;
                        }
                    },
                    new Entry("") {

                    });
                    addLog(new Entry("<col=ff0000> QUEST COMPLETE!") {

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
        return new int[] {107, 1, 5};
    }

    @Override
    public int getSlotId() {
        return SKULL;
    }

    @Override
    public int getRewardItemId() {
        return 0;
    }

    @Override
    public void accept() {
        player.getDialogueManager().startDialogue("FatherAereck", -3);
        player.getInterfaceManager().sendInterfaces();
    }

    @Override
    public void decline() {
        player.getDialogueManager().startDialogue("FatherAereck", -4);
    }

    @Override
    public String getStart() {
        return "Speak to Father Aereck in the Lumbridge Church.";
    }

    @Override
    public String getRequirements() {
        return "I must be able to defeat a Level 13 Skeleton!";
    }

    /**
     * Method used to search altar.
     * @param player
     */
    public static void searchAltar(final Player player) {
    int stage = player.getQuestManager().get(Quests.THE_RESTLESS_GHOST).getStage();
        if(stage < 3) {
            player.getPackets().sendGameMessage("You search the altar and find nothing.");
            return;
        }
        if(!player.getInventory().containsOneItem(SKULL)) {
            if(player.getInventory().getFreeSlots() > 0) {
                player.getInventory().addItem(SKULL, 1);
            } else {
                World.addGroundItem(new Item(SKULL), new WorldTile(player.getX(), player.getY(), player.getPlane()));
            }
            //TODO send object config
            player.getQuestManager().get(Quests.THE_RESTLESS_GHOST).setStage(4);
            player.getPackets().sendGameMessage("The skeleton in the corner suddenly comes to life!");
            sendSkeleton(player);
        }
    }

        public static void sendSkeleton(Player player) {
            World.spawnNPC(459, new WorldTile(3120, 9568, 0), -1, true);
         }

}
