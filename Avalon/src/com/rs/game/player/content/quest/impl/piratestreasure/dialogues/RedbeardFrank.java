package com.rs.game.player.content.quest.impl.piratestreasure.dialogues;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.game.player.content.quest.impl.piratestreasure.PiratesTreasure;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;

/**
 * @Author arrow
 * @Contact<arrowrsps@gmail.com;skype:arrowrsps>
 */
public class RedbeardFrank extends Dialogue {

    public int getNPCID() {
        return 375;
    }

    @Override
    public void start() {
        stageInt = (Integer) parameters[0];
        int progress = player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage();
        if(progress > 0 && progress < 7 && stageInt != 1) {
            sendNPCChat(Mood.ASKING, "Have ye brought some rum for yer ol' mate Frank?");
            stageInt = 11;
        }
        switch(stageInt) {
            case 0:
                sendNPCChat(Mood.NORMAL, "Arr, Matey!");
                stageInt = -1;
                break;

            case 1:
                sendPlayerChat(Mood.CONFUSED, "Well I'll give it " + (progress > 1 ? "another" : "a" +" shot."));
                stageInt = 10;
                break;
        }
    }

    @Override
    public void run(int interfaceId, int componentId) {
        int progress = player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage();
            switch(stageInt) {
                case -3:
                    sendNPCChat(Mood.HAPPY, "Yer a saint, although it'll take a miracle to get it off " +
                            "Karamja.");
                    stageInt = 7;
                    break;
                case -2:
                    sendNPCChat(Mood.SAD, "Fair enough. I'll be here and thirsty whenever you " +
                            "feel like helpin' out.");
                    stageInt = -4;
                    break;
                case -1:
                    if(player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() < 8 && player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 6) {
                        if (!player.getInventory().containsOneItem(PiratesTreasure.CHEST_KEY)) {
                            sendPlayerChat(Mood.SAD, "I've seem to have lost my chest key...");
                            stageInt = 21;
                        }
                    }
                    if (progress > 0 || player.getQuestManager().get(Quests.PIRATES_TREASURE).getState() == QuestState.COMPLETED) {
                        sendOptionsDialogue(DEFAULT, "Arr!", "Do you have anything for trade?");
                        stageInt = 1;
                    }
                    if (progress < 1) {
                        sendOptionsDialogue(DEFAULT, "I'm in search of treasure.", "Arr!",
                                "Do you have anything for trade?");
                        stageInt = 1;
                    }
                    break;

                case 1:
                    if(progress < 1) {
                        switch (componentId) {
                            case OPTION_1:
                                sendNPCChat(Mood.NORMAL, "Arr, treasure you be after eh? Well I might be able to " +
                                        "tell you where to find some... For a price...");
                                stageInt = 2;
                                break;
                            case OPTION_2:
                                sendPlayerChat(Mood.CONFUSED, "Arr!");
                                stageInt = 6;
                                break;
                            case OPTION_3:
                                sendNPCChat(Mood.NORMAL, "Nothin' at the moment, but then again the Customs " +
                                        "Agents are on the warpath right now.");
                                stageInt = -2;
                                break;
                        }
                        } else {
                            switch (componentId) {
                                case OPTION_1:
                                    sendPlayerChat(Mood.CONFUSED, "Arr!");
                                    stageInt = 6;
                                    break;
                                case OPTION_2:
                                    sendNPCChat(Mood.NORMAL, "Nothin' at the moment, but then again the Customs " +
                                            "Agents are on the warpath right now.");
                                    stageInt = -2;
                                    break;
                        }
                    }
                    break;

                case 2:
                    sendPlayerChat(Mood.ASKING, "What sort of price?");
                    stageInt = 3;
                    break;

                case 3:
                    sendNPCChat(Mood.QUESTIONING, "Well for example if you can get me a bottle of rum..." +
                            " Not just any rum mind...");
                    stageInt = 4;
                    break;

                case 4:
                    sendNPCChat(Mood.NORMAL, "I'd like some rum made on Karamja Island. There's no " +
                            "rum like Karamja Rum!");
                    stageInt = 5;
                    break;

                case 5:
                    player.getQuestManager().get(Quests.PIRATES_TREASURE).sendStartOption();
                    break;

                case 6:
                    sendNPCChat(Mood.NORMAL, "Arr!");
                    stageInt = -1;
                    break;

                case 7:
                    sendPlayerChat(Mood.ASKING, "What do you mean?");
                    stageInt = 8;
                    break;

                case 8:
                    sendNPCChat(Mood.NORMAL, "The Customs office has been clampin' down on the " +
                            "export of spirits. You seem like a resourceful young lad, " +
                            "I'm sure ye'll be able to find a way to slip the stuff past them.");
                    stageInt = 9;
                    break;

                case 9:
                    sendPlayerChat(Mood.CONFUSED, "Well I'll give it " + (progress > 1 ? "another" : "a") + " shot.");
                    stageInt = 10;
                    break;

                case 10:
                    sendNPCChat(Mood.HAPPY, "Arr, that's the spirit!");
                    if(player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() < 2) {
                        player.getQuestManager().get(Quests.PIRATES_TREASURE).setStage(1);
                    }
                    stageInt = -4;
                    break;

                /*
                 * Check for rum (To Frank)
                 */
                case 11:
                    if(!player.getInventory().containsItem(PiratesTreasure.KARAMJA_RUM, 1) ||
                            player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() < 5) {
                        sendPlayerChat(Mood.SAD, "No, not yet.");
                        stageInt = 12;
                    } else {
                        sendPlayerChat(Mood.HAPPY, "Yes, I've got some.");
                        stageInt = 13;
                    }
                    break;


                case 12:
                    sendNPCChat(Mood.NORMAL, "Not surprising, tis no easy task to get it off Karamja.");
                    stageInt = 7;
                    break;

                case 13:
                    sendNPCChat(Mood.HAPPY, "Now a deal's a deal, I'll tell ye about the treasure. I " +
                            "used to serve under a pirate captain called One-Eyed " +
                            "Hector.");
                    stageInt = 14;
                    break;

                case 14:
                    sendNPCChat(Mood.PLAIN_TALKING, "Hector were very successful and became very rich. " +
                            "But about a year ago we were boarded by the Customs " +
                            "and Excise Agents.");
                    stageInt = 15;
                    break;

                case 15:
                    sendNPCChat(Mood.PLAIN_TALKING, "Hector were killed along with many of the crew, I were " +
                            "one of the few to escape and I escaped with this.");
                    stageInt = 16;
                    break;


                case 16:
                    sendHandedItem(PiratesTreasure.CHEST_KEY, "Frank happily takes the rum... ... and hands you a key.");
                    player.getInventory().deleteItem(PiratesTreasure.KARAMJA_RUM, 1);
                    if(player.getInventory().getFreeSlots() > 1) {
                        player.getQuestManager().get(Quests.PIRATES_TREASURE).setStage(7);
                        player.getInventory().addItem(PiratesTreasure.CHEST_KEY, 1);
                    } else {
                        World.addGroundItem(new Item(PiratesTreasure.CHEST_KEY), new WorldTile(player));
                    }
                    stageInt = 17;
                    break;

                case 17:
                    sendNPCChat(Mood.PLAIN_TALKING, "This be Hector's key. I believe it opens his chest in his " +
                            "old room in the Blue Moon Inn in Varrock.");
                    stageInt = 18;
                    break;

                case 18:
                    sendNPCChat(Mood.PLAIN_TALKING, "With any luck his treasure will be in there.");
                    stageInt = 19;
                    break;

                case 19:
                    sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Ok thanks, I'll go and get it.", "So why didn't you ever get it?");
                   stageInt = 20;
                    break;

                case 20:
                    switch (componentId) {
                        case OPTION_1:
                            player.getQuestManager().get(Quests.PIRATES_TREASURE).setStage(7);
                            end();
                            break;
                        case OPTION_2:
                            sendNPCChat(Mood.PLAIN_TALKING, "I'm not allowed in the Blue Moon Inn. Apparently I'm " +
                                    "a drunken trouble maker.");
                            player.getQuestManager().get(Quests.PIRATES_TREASURE).setStage(7);
                            stageInt = -2;
                            break;
                    }
                    break;
                    
                case 21:
                	sendNPCChat(Mood.NORMAL, "Arr, silly you. Forunately I took the precaution to have "
                			+ "have one made.");
                	stageInt = 22;
                	break;
                	
                case 22:
                    sendHandedItem(PiratesTreasure.CHEST_KEY, "Frank hands you a chest key.");
                    if(player.getInventory().getFreeSlots() < 1) {
                		World.addGroundItem(new Item(PiratesTreasure.CHEST_KEY), new WorldTile(player));
                		stageInt = 23;
                	} else {
                		player.getInventory().addItem(PiratesTreasure.CHEST_KEY, 1);
                		stageInt = 23;
                	}
                	break;
                	
                case 23:
                	sendOptionsDialogue(DEFAULT, "Arr!", "Do you have anything for trade?");
                	stageInt = 24;
                	break;
                	
                case 24:
                	switch(componentId) {
                	case OPTION_1:
                		 sendPlayerChat(Mood.CONFUSED, "Arr!");
                         stageInt = 6;
                		break;
                	case OPTION_2:
                		 sendNPCChat(Mood.NORMAL, "Nothin' at the moment, but then again the Customs " +
                                 "Agents are on the warpath right now.");
                         stageInt = -2;
                		break;
                	}
                	break;

                case -4:
                    end();
                    break;


                default:
                    end();
                    break;
            }
    }

    @Override
    public void finish() {

    }
}
