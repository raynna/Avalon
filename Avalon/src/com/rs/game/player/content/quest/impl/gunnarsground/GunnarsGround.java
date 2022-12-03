package com.rs.game.player.content.quest.impl.gunnarsground;

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

/**
 * @Author Frostbite
 * @Contact<frostbitersps@gmail.com;skype:frostbitersps>
 */
public class GunnarsGround extends Quest implements Serializable {

    /**
     * Things unique i've seen during quest.
     * Barbarian npcs scream 'FOR GUNNAR!' while in combat.
     */

    /**
     * Quest Items
     */
	private static final long serialVersionUID = -4574056345706880949L;

	/**
     * Represents Jeffery's ring
     */
    public static final int JEFFERYS_RING = 19770;

    /**
     * Represents Dororan's Engraved ring
     */
    public static final int ENGRAVED_RING = 19771;

    /**
     * Represents Gunnars Ground item
     */
    public static final int GUNNARS_GROUND = 19772;

    /**
     * Represents Dororan's love poem
     */
    public static final int LOVE_POEM = 19774;

    /**
     * Represents Reward antiue lamp
     */
    public static final int ANTIQUE_LAMP = 19775;

    /**
     * Represents Reward swanky boots
     */
    public static final int SWANKY_BOOTS = 19776;

    /**
     * Tools
     */

    /**
     * Represents chisel id
     */
    public static final int CHISEL = 1755;


    public GunnarsGround(Player player) {
        super(player);
    }

    @Override
    public String getQuestName() {
        return "Gunnar's Ground";
    }

    @Override
    public void declareDialogues() {

    }

    @Override
    public int getQuestPoints() {
        return 0;
    }

    @Override
    public QuestReward[] getRewards() {
        return new QuestReward[] {
                new QuestReward(RewardType.EXPERIENCE, Skills.CRAFTING, 300),
                new QuestReward(RewardType.ITEM, new Item(4447, 1)),
                new QuestReward(RewardType.ITEM, new Item(19776, 1))
        };
    }

    public final QuestJournal journal = new QuestJournal() {
        /**
		 * 
		 */
		private static final long serialVersionUID = -5522254915045072286L;

		@SuppressWarnings("serial")
		@Override
        public QuestJournal display(int stage) {
        	final int progress = player.getQuestManager().get(Quests.GUNNARS_GROUND).getStage();
            switch (progress) {
                /**
                 * Not Started Quest
                 */
                case 0:
                    addLog(new Entry("I can start this quest by talking to " + keyWord("Dororan") + ", the dwarf just outside the") {
                    @Override
                    public boolean meetsCondition(Player player) {
                        return progress > 0;
                    }
                    },
                    new Entry(keyWord(" barbarian village") + ".") {
                        public boolean meetsCondition(Player player) {
                            return progress > 0;
                        }
                    });
                    addLog(new Entry("I must have the following skill levels:") {

                    });
                   addLog(new Entry("Level 5 Crafting") {
                       @Override
                   public boolean meetsCondition(Player player) {
                           return player.getSkills().getLevel(Skills.CRAFTING) > 4;
                       }
                    });
                    break;

                /**
                 * Recieved Ring
                 */
                case 1:
                case 2:
                    addLog(new Entry("I met an unhappy dwarf named Dororan just outside the barbarian village.") {
                        @Override
                    public boolean meetsCondition(Player player) {
                            return progress > 0;
                        }
                    },
                    new Entry("") {

                    });
                    addLog(new Entry(keyWord("Dororan") + " outside the " + keyWord("barbarian village") + ", wants me to bring him a " + keyWord("gold ring") + ".He") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 1;
                        }
                    },
                    new Entry("specifically wants a ring from Jeffery in Edgeville.") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 1;
                        }
                    });
                    addLog(new Entry("Items I need:") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 1;
                        }
                    },
                    new Entry(keyWord("Ring from Jeffery")) {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return player.getInventory().containsItem(GunnarsGround.JEFFERYS_RING, 1);
                        }
                    });
                    break;

                /**
                 * Tells you what to engrave.
                 */
                case 3:
                    addLog(new Entry("I met an unhappy dwarf named Dororan just outside the barbarian village.") {
                        @Override
                    public boolean meetsCondition(Player player) {
                            return progress > 0;
                        }
                    },
                    new Entry("") {

                    });
                    addLog(new Entry("Dororan, outside the barbarian village, wants me to bring him a gold ring. He") {
                        @Override
                    public boolean meetsCondition(Player player) {
                        return progress > 0;
                        }
                    },
                    new Entry(" specifically wants a ring from Jeffery in Edgeville.") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 0;
                        }
                    });
                    addLog(new Entry("Dororan wants me to engrave the words 'Gudrun the Fair, Gudrun the Fiery'") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                        }
                    },
                    new Entry(" onto the gold ring with a chisel.") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                        }
                    });
                    if(player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING)) {
                        addLog(new Entry("I need to show the " + keyWord("engraved ring") + " to " + keyWord("Dororan") + ", outside the " +
                                keyWord("barbarian village") + ".") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return progress > 3;
                            }
                        });
                    }
                    break;

                /**
                 * Tells you to bring her the ring.
                 */
                case 4:
                    addLog(new Entry("I met an unhappy dwarf named Dororan just outside the barbarian village.") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan, outside the barbarian village, wants me to bring him a gold ring. He") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry(" specifically wants a ring from Jeffery in Edgeville.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 0;
                                }
                            },
                            new Entry("") {

                             });
                    addLog(new Entry("Dororan wants me to engrave the words 'Gudrun the Fair, Gudrun the Fiery'") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                               }
                           },
                            new Entry(" onto the gold ring with a chisel.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                                }
                            },
                            new Entry("") {

                            });

                    if(player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING)) {
                        addLog(new Entry("I need to show the engraved ring to Dororan, outside the " +
                               "barbarian village.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return progress > 3;
                            }
                        });
                    }
                    addLog(new Entry("Dororan wants me to take the " + keyWord("engraved ring") + "to " + keyWord("Gudrun") + " in the " + keyWord("barbarian village") + ".") {
                        @Override
                    public boolean meetsCondition(Player player) {
                            return progress > 4;
                        }
                    },
                    new Entry("He asked that I not reveal that he is a dwarf just yet.") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 4;
                        }
                    });
                    break;

                /**
                 * Gunrun's father task
                 */
                case 5:
                    addLog(new Entry("I met an unhappy dwarf named Dororan just outside the barbarian village.") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan, outside the barbarian village, wants me to bring him a gold ring. He") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry(" specifically wants a ring from Jeffery in Edgeville.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 0;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan wants me to engrave the words 'Gudrun the Fair, Gudrun the Fiery'") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                               }
                           },
                            new Entry(" onto the gold ring with a chisel.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                                }
                            },
                            new Entry("") {

                            });

                    if(player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING)) {
                        addLog(new Entry("I need to show the engraved ring to Dororan, outside the " +
                                keyWord("barbarian village") + ".") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return progress > 3;
                            }
                        });
                    }
                    addLog(new Entry("Dororan wants me to take the engraved ring to Gudrun in the barbarian village.") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 4;
                               }
                           },
                            new Entry("He asked that I not reveal that he is a dwarf just yet.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 4;
                                }
                            },
                    new Entry("") {

                    });
                    addLog(new Entry("Gudrun says that her father, " + keyWord("Gunthor") + ", would never allow her to have a ") {
                        @Override
                    public boolean meetsCondition(Player player) {
                            return progress > 5;
                        }
                    },
                    new Entry("relationship with an outside. She wondered if I could convince him otherwise. he ") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 5;
                        }
                    },
                    new Entry("an be found at the " + keyWord("longhouse") + " in the " + keyWord("barbarian village") + ".") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 5;
                        }
                    });
                    break;

                /**
                 * hieftain Gunthor's message
                 */
                case 6:
                    addLog(new Entry("I met an unhappy dwarf named Dororan just outside the barbarian village.") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan, outside the barbarian village, wants me to bring him a gold ring. He") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry(" specifically wants a ring from Jeffery in Edgeville.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 0;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan wants me to engrave the words 'Gudrun the Fair, Gudrun the Fiery'") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                               }
                           },
                            new Entry(" onto the gold ring with a chisel.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                                }
                            },
                            new Entry("") {

                            });

                    if(player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING)) {
                        addLog(new Entry("I need to show the engraved ring to Dororan, outside the " +
                                "barbarian village.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return progress > 3;
                            }
                        });
                    }
                    addLog(new Entry("Dororan wants me to take the engraved ring to Gudrun in the barbarian village.") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 4;
                               }
                           },
                            new Entry("He asked that I not reveal that he is a dwarf just yet.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 4;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gudrun says that her father, Gunthor, would never allow her to have a ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 5;
                               }
                           },
                            new Entry("relationship with an outside. She wondered if I could convince him otherwise. he ") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("an be found at the longhouse in the barbarian village.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                    new Entry("") {

                    });
                    addLog(new Entry("Gunthor was not accommondating, He told me to ask " + keyWord("Gundrun") + "in the " + keyWord("barbarian")) {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 6;
                        }
                    },
                    new Entry(keyWord(" village") + "what someone called Gunnar would think.") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 6;
                        }
                    });
                    break;

                /**
                 * Dororans idea
                 */
                case 7:
                    addLog(new Entry("I met an unhappy dwarf named Dororan just outside the barbarian village.") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan, outside the barbarian village, wants me to bring him a gold ring. He") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry(" specifically wants a ring from Jeffery in Edgeville.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 0;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan wants me to engrave the words 'Gudrun the Fair, Gudrun the Fiery'") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                               }
                           },
                            new Entry(" onto the gold ring with a chisel.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                                }
                            },
                            new Entry("") {

                            });

                    if(player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING)) {
                        addLog(new Entry("I need to show the " + keyWord("engraved ring") + " to " + keyWord("Dororan") + ", outside the " +
                               "barbarian village.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return progress > 3;
                            }
                        });
                    }
                    addLog(new Entry("Dororan wants me to take the engraved ring to Gudrun in the " + keyWord("barbarian village") + ".") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 4;
                               }
                           },
                            new Entry("He asked that I not reveal that he is a dwarf just yet.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 4;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gudrun says that her father,Gunthor, would never allow her to have a ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 5;
                               }
                           },
                            new Entry("relationship with an outside. She wondered if I could convince him otherwise. he ") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("an be found at the longhouse in the barbarian village.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gunthor was not accommondating, He told me to ask Gundrun in the barbarian") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 6;
                               }
                           },
                            new Entry(keyWord(" village") + "what someone called Gunnar would think.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                    new Entry("") {

                    });
                    addLog(new Entry("Gudrun sugested I see if " + keyWord("Dororan") + ", outside the " + keyWord("barbarian vilalge") + ", can think of ") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 7;
                        }
                    },
                    new Entry("anything to help convince Gunthor.") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 7;
                        }
                    });
                    break;

                case 8:
                case 9:
                case 10:
                    addLog(new Entry("I met an unhappy dwarf named Dororan just outside the barbarian village.") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan, outside the barbarian village, wants me to bring him a gold ring. He") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry(" specifically wants a ring from Jeffery in Edgeville.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 0;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan wants me to engrave the words 'Gudrun the Fair, Gudrun the Fiery'") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                               }
                           },
                            new Entry(" onto the gold ring with a chisel.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                                }
                            },
                            new Entry("") {

                            });

                    if(player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING)) {
                        addLog(new Entry("I need to show the " + keyWord("engraved ring") + " to " + keyWord("Dororan") + ", outside the " +
                                "barbarian village.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return progress > 3;
                            }
                        });
                    }
                    addLog(new Entry("Dororan wants me to take the engraved ring to Gudrun in the " + keyWord("barbarian village") + ".") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 4;
                               }
                           },
                            new Entry("He asked that I not reveal that he is a dwarf just yet.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 4;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gudrun says that her father,Gunthor, would never allow her to have a ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 5;
                               }
                           },
                            new Entry("relationship with an outside. She wondered if I could convince him otherwise. he ") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("an be found at the longhouse in the barbarian village.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gunthor was not accommondating, He told me to ask Gundrun in the barbarian") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 6;
                               }
                           },
                            new Entry(keyWord(" village") + "what someone called Gunnar would think.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gudrun sugested I see if Dororan, outside the barbarian vilalge, can think of ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 7;
                               }
                           },
                            new Entry("anything to help convince Gunthor.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 7;
                                }
                            },
                    new Entry("") {

                    });
                    addLog(new Entry(keyWord("Dororan") + ", outside the " + keyWord("barbarian village") + ", is going to try to write a poem to ") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 9;
                        }
                    },
                    new Entry("convince Gunthor. I should check how he's getting on.") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 9;
                        }
                    });
                    break;

                case 11:
                    addLog(new Entry("I met an unhappy dwarf named Dororan just outside the barbarian village.") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan, outside the barbarian village, wants me to bring him a gold ring. He") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry(" specifically wants a ring from Jeffery in Edgeville.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 0;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan wants me to engrave the words 'Gudrun the Fair, Gudrun the Fiery'") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                               }
                           },
                            new Entry(" onto the gold ring with a chisel.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                                }
                            },
                            new Entry("") {

                            });

                    if(player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING)) {
                        addLog(new Entry("I need to show the " + keyWord("engraved ring") + " to " + keyWord("Dororan") + ", outside the " +
                                "barbarian village.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return progress > 3;
                            }
                        });
                    }
                    addLog(new Entry("Dororan wants me to take the engraved ring to Gudrun in the " + keyWord("barbarian village") + ".") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 4;
                               }
                           },
                            new Entry("He asked that I not reveal that he is a dwarf just yet.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 4;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gudrun says that her father,Gunthor, would never allow her to have a ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 5;
                               }
                           },
                            new Entry("relationship with an outside. She wondered if I could convince him otherwise. he ") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("an be found at the longhouse in the barbarian village.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gunthor was not accommondating, He told me to ask Gundrun in the barbarian") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 6;
                               }
                           },
                            new Entry(keyWord(" village") + "what someone called Gunnar would think.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gudrun sugested I see if Dororan, outside the barbarian vilalge, can think of ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 7;
                               }
                           },
                            new Entry("anything to help convince Gunthor.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 7;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan, outside the barbarian village, is going to try to write a poem to ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 9;
                               }
                           },
                            new Entry("convince Gunthor. I should check how he's getting on.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 9;
                                }
                            },
                    new Entry("") {

                    });
                    addLog(new Entry("I need to take the poem to " + keyWord("Gudrun") + " in the " + keyWord("barbarian village") + " and ask her to read it ") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 10;
                        }
                    },
                    new Entry("to her father.") {

                    });
                    break;

                case 12:
                case 13:
                    addLog(new Entry("I met an unhappy dwarf named Dororan just outside the barbarian village.") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan, outside the barbarian village, wants me to bring him a gold ring. He") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry(" specifically wants a ring from Jeffery in Edgeville.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 0;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan wants me to engrave the words 'Gudrun the Fair, Gudrun the Fiery'") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                               }
                           },
                            new Entry(" onto the gold ring with a chisel.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                                }
                            },
                            new Entry("") {

                            });

                    if(player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING)) {
                        addLog(new Entry("I need to show the " + keyWord("engraved ring") + " to " + keyWord("Dororan") + ", outside the " +
                                "barbarian village.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return progress > 3;
                            }
                        });
                    }
                    addLog(new Entry("Dororan wants me to take the engraved ring to Gudrun in the " + keyWord("barbarian village") + ".") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 4;
                               }
                           },
                            new Entry("He asked that I not reveal that he is a dwarf just yet.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 4;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gudrun says that her father,Gunthor, would never allow her to have a ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 5;
                               }
                           },
                            new Entry("relationship with an outside. She wondered if I could convince him otherwise. he ") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("an be found at the longhouse in the barbarian village.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gunthor was not accommondating, He told me to ask Gundrun in the barbarian") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 6;
                               }
                           },
                            new Entry(keyWord(" village") + "what someone called Gunnar would think.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gudrun sugested I see if Dororan, outside the barbarian vilalge, can think of ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 7;
                               }
                           },
                            new Entry("anything to help convince Gunthor.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 7;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan, outside the barbarian village, is going to try to write a poem to ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 9;
                               }
                           },
                            new Entry("convince Gunthor. I should check how he's getting on.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 9;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("I need to take the poem to Gudrun in the barbarian village and ask her to read it ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 10;
                               }
                           },
                            new Entry("to her father.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 10;
                                }
                            },
                    new Entry("") {

                    });
                    addLog(new Entry("I should talk to " + keyWord("Gudrun") + " in the " + keyWord("barbarian village") + " once I'm ready for her to read ") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 13;
                        }
                    },
                    new Entry("the poem to her father.") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 13;
                        }
                    });

                case 15:
                    addLog(new Entry("I met an unhappy dwarf named Dororan just outside the barbarian village.") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan, outside the barbarian village, wants me to bring him a gold ring. He") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry(" specifically wants a ring from Jeffery in Edgeville.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 0;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan wants me to engrave the words 'Gudrun the Fair, Gudrun the Fiery'") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                               }
                           },
                            new Entry(" onto the gold ring with a chisel.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                                }
                            },
                            new Entry("") {

                            });

                    if(player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING)) {
                        addLog(new Entry("I need to show the " + keyWord("engraved ring") + " to " + keyWord("Dororan") + ", outside the " +
                                "barbarian village.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return progress > 3;
                            }
                        });
                    }
                    addLog(new Entry("Dororan wants me to take the engraved ring to Gudrun in the " + keyWord("barbarian village") + ".") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 4;
                               }
                           },
                            new Entry("He asked that I not reveal that he is a dwarf just yet.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 4;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gudrun says that her father,Gunthor, would never allow her to have a ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 5;
                               }
                           },
                            new Entry("relationship with an outside. She wondered if I could convince him otherwise. he ") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("an be found at the longhouse in the barbarian village.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gunthor was not accommondating, He told me to ask Gundrun in the barbarian") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 6;
                               }
                           },
                            new Entry(keyWord(" village") + "what someone called Gunnar would think.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gudrun sugested I see if Dororan, outside the barbarian vilalge, can think of ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 7;
                               }
                           },
                            new Entry("anything to help convince Gunthor.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 7;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan, outside the barbarian village, is going to try to write a poem to ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 9;
                               }
                           },
                            new Entry("convince Gunthor. I should check how he's getting on.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 9;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("I need to take the poem to Gudrun in the barbarian village and ask her to read it ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 10;
                               }
                           },
                            new Entry("to her father.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 10;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("I should talk to Gudrun in the barbarian village once I'm ready for her to read ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 13;
                               }
                           },
                            new Entry("the poem to her father.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 13;
                                }
                            },
                    new Entry("I should talk to " + keyWord("Gudrun") + " or " + keyWord("Dororan") + " in the " + keyWord("barbarian village") + " about hwo the recital") {
                        @Override
                        public boolean meetsCondition(Player player) {
                            return progress > 15;
                        }
                    });
                    addLog(new Entry("went.") {
                        public boolean meetsCondition(Player player) {
                            return progress > 15;
                        }
                    });
                    break;

                case 16:
                    addLog(new Entry("I met an unhappy dwarf named Dororan just outside the barbarian village.") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan, outside the barbarian village, wants me to bring him a gold ring. He") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 0;
                               }
                           },
                            new Entry(" specifically wants a ring from Jeffery in Edgeville.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 0;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan wants me to engrave the words 'Gudrun the Fair, Gudrun the Fiery'") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                               }
                           },
                            new Entry(" onto the gold ring with a chisel.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return !player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING);
                                }
                            },
                            new Entry("") {

                            });

                    if(player.getInventory().containsOneItem(GunnarsGround.ENGRAVED_RING)) {
                        addLog(new Entry("I need to show the " + keyWord("engraved ring") + " to " + keyWord("Dororan") + ", outside the " +
                                "barbarian village.") {
                            @Override
                            public boolean meetsCondition(Player player) {
                                return progress > 3;
                            }
                        });
                    }
                    addLog(new Entry("Dororan wants me to take the engraved ring to Gudrun in the " + keyWord("barbarian village") + ".") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 4;
                               }
                           },
                            new Entry("He asked that I not reveal that he is a dwarf just yet.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 4;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gudrun says that her father,Gunthor, would never allow her to have a ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 5;
                               }
                           },
                            new Entry("relationship with an outside. She wondered if I could convince him otherwise. he ") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("an be found at the longhouse in the barbarian village.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gunthor was not accommondating, He told me to ask Gundrun in the barbarian") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 6;
                               }
                           },
                            new Entry(keyWord(" village") + "what someone called Gunnar would think.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 5;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Gudrun sugested I see if Dororan, outside the barbarian vilalge, can think of ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 7;
                               }
                           },
                            new Entry("anything to help convince Gunthor.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 7;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("Dororan, outside the barbarian village, is going to try to write a poem to ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 9;
                               }
                           },
                            new Entry("convince Gunthor. I should check how he's getting on.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 9;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("I need to take the poem to Gudrun in the barbarian village and ask her to read it ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 10;
                               }
                           },
                            new Entry("to her father.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 10;
                                }
                            },
                            new Entry("") {

                            });
                    addLog(new Entry("I should talk to Gudrun in the barbarian village once I'm ready for her to read ") {
                               @Override
                               public boolean meetsCondition(Player player) {
                                   return progress > 13;
                               }
                           },
                            new Entry("the poem to her father.") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 13;
                                }
                            },
                            new Entry("I should talk to Gudrun or Dororan in the barbarian village about hwo the recital") {
                                @Override
                                public boolean meetsCondition(Player player) {
                                    return progress > 15;
                                }
                            });
                    addLog(new Entry("went.") {
                        public boolean meetsCondition(Player player) {
                            return progress > 15;
                        }
                    },
                    new Entry("") {

                    });
                    addLog(new Entry("Dororan has moved into a large house, east of the barbarian village and across ") {

                    },
                    new Entry("the river. The house is north of the road and has roses outside.") {

                    },
                    new Entry("<col=FF0000> QUEST COMPELTE!") {

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
        return new int[0];
    }

    @Override
    public int getSlotId() {
        return 0;
    }

    @Override
    public int getRewardItemId() {
        return 0;
    }

    @Override
    public void accept() {

    }

    @Override
    public void decline() {

    }

    @Override
    public String getRequirements() {
        return "level 5 Crafting";
    }

    @Override
    public String getStart() {
        return "Speak to Dororan outside the barbarian village.";
    }
}
