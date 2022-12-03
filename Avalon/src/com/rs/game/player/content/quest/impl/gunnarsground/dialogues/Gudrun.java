package com.rs.game.player.content.quest.impl.gunnarsground.dialogues;

import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.game.player.content.quest.impl.gunnarsground.GunnarsGround;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;

/**
 * @Author Frostbite
 * @Contact<frostbitersps@gmail.com;skype:frostbitersps>
 */
public class Gudrun extends Dialogue {

    /**
     * TODO edit npc ids.
     */

    int KJELL = 2343;
    int DORODON = 2344;

    @Override
    public void start() {
        stageInt = (Integer) parameters[0];
        int progress = player.getQuestManager().get(Quests.GUNNARS_GROUND).getStage();
            if(progress < 5) {
                sendNPCDialogue(KJELL, ANGRY, "Gudrun! You caught enough fish?");
                stageInt = -1;
                switch (progress ) {
                    case 5:
                        sendOptionsDialogue(DEFAULT, "So, you want me to talk to your father?", "So, you want me to kill your father?");
                        stageInt = 38;
                        break;
                    case 6:
                        sendNPCChat(Mood.ASKING, "What did he say?");
                        stageInt = 42;
                        break;
                    case 7:
                        sendNPCChat(Mood.NORMAL, "If there's anything you can do to make papa see sense," +
                                " please do it.");
                        stageInt = -2;
                        break;

                    case 11:
                        if(!player.getInventory().containsOneItem(GunnarsGround.GUNNARS_GROUND)) {
                                sendNPCChat(Mood.ASKING, "What is it?");
                            stageInt = 47;
                        } else {
                            sendNPCChat(Mood.ASKING, "What have you got there?");
                            stageInt = 48;
                        }
                        break;

                    case 12:
                    case 13:
                    case 14:
                        sendNPCChat(Mood.NORMAL, "Are you ready for the recital?");
                        stageInt = 55;
                        break;

                    case 15:
                        sendNPCChat(Mood.HAPPY, "Papa was so impressed by Dororan's poem, he's made him the " +
                                "village poet!");
                        stageInt = 57;
                        break;
                }

        }
    }

    @Override
    public void run(int interfaceId, int componentId) {
        switch (stageInt) {
            case -1:
                sendNPCChat(Mood.ANGRY, "Yes! I have plenty of fish!");
                stageInt = 1;
                break;

            case 1:
                sendNPCDialogue(KJELL, ANGRY, "Your father needs many fish to feed the freemen!");
                stageInt = 2;
                break;

            case 2:
                sendNPCChat(Mood.ANGRY, "I know!");
                stageInt = 3;
                break;

            case 3:
                sendNPCDialogue(KJELL, ANGRY, "Maybe you sneak off to the outlander city again? Buy " +
                        "fish in market, instead of catching them?");
                stageInt = 4;
                break;

            case 4:
                sendNPCChat(Mood.ANGRY, "Shut up! I'm much better at fishing than you.");
                stageInt = 5;
                break;

            case 5:
                sendNPCDialogue(KJELL, ANGRY, "You are not!");
                stageInt = 6;
                break;

            case 6:
                sendNPCChat(Mood.ANGRY, "Just guard the hut like the chieftain told you to!");
                stageInt = 7;
                break;

            case 7:
                sendNPCDialogue(KJELL, ANGRY, "Fine!");
                stageInt = 8;
                break;

            case 8:
                sendNPCChat(Mood.SLIGHTLY_ANGRY, "Stupid barbarian.");
                stageInt = 9;
                break;

            case 9:
                sendNPCChat(Mood.SAD, "Sorry about that, stranger. Did you want something?");
                stageInt = 10;
                break;

            case 10:
                sendPlayerChat(Mood.ASKING, "Are you Gudrun?");
                stageInt = 11;
                break;

            case 11:
                sendNPCChat(Mood.NORMAL, "Yes.");
                stageInt = 12;
                break;

            case 12:
                sendPlayerChat(Mood.HAPPY, "This is for you.");
                stageInt = 13;
                break;

            case 13:
                sendItemDialogue(GunnarsGround.ENGRAVED_RING, "You show Gudrun the ring.");
                stageInt = 14;
                break;

            case 14:
                sendNPCChat(Mood.HAPPY, "It's lovely! There's something written on it:");
                stageInt = 15;
                break;

            case 15:
                sendNPCChat(Mood.HAPPY, "'Gudrun the Fair, Gudrun the Fiery.' Is it about me?");
                stageInt = 16;
                break;

            case 16:
                sendOptionsDialogue(DEFAULT, "Yes.", "Presumable.");
                stageInt = 17;
                break;

            case 17:
                switch (componentId) {
                    case OPTION_1:
                    case OPTION_2:
                        sendNPCChat(Mood.HAPPY, "This is a beautiful gift, stranger. Thank you.");
                        stageInt = 18;
                        break;
                }
                break;

            case 18:
                sendOptionsDialogue(DEFAULT, "The ring isn't from me!", "It should belong to someone just as beautiful.");
                stageInt = 19;
                break;

            case 19:
                switch (componentId) {
                    case OPTION_1:
                        sendNPCChat(Mood.WTF_FACE, "Oh! Who is it from?");
                        stageInt = 24;
                        break;
                    case OPTION_2:
                        sendNPCChat(Mood.HAPPY, "That's very flattering! You look like an adventurer, though?");
                        stageInt = 20;
                        break;
                }
                break;

            case 20:
                sendOptionsDialogue(DEFAULT, "That's right.", "Some call me that.");
                stageInt = 21;
                break;

            case 21:
                switch (componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "That's right.");
                        stageInt = 22;
                        break;
                    case OPTION_2:
                        sendPlayerChat(Mood.NORMAL, "Some call me that.");
                        stageInt = 22;
                        break;
                }
                break;

            case 22:
                sendNPCChat(Mood.NORMAL, "I'm sorry, I could never get involved with an adventurer.");
                stageInt = 23;
                break;

            case 23:
                sendNPCChat(Mood.WTF_FACE, "Oh! Who is it from?");
                stageInt = 24;
                break;

            case 24:
                sendOptionsDialogue(DEFAULT, "A great poet.", "A secret admirer.", "A short suitor.");
                stageInt = 25;
                break;

            case 25:
                switch (componentId) {
                    case OPTION_1:
                        sendNPCChat(Mood.HAPPY, "A tale-teller? A bard? My people have great respect for " +
                                "poets.");
                        stageInt = 26;
                        break;
                    case OPTION_2:
                        sendNPCChat(Mood.HAPPY, "Does that really happen? How exciting!");
                        stageInt = 26;
                        break;
                    case OPTION_3:
                        sendNPCChat(Mood.CONFUSED, "What?");
                        stageInt = 27;
                        break;
                }
                break;

            case 26:
                sendNPCChat(Mood.ASKING, "This man, he is from outside the village?");
                stageInt = 30;
                break;

            case 27:
                sendPlayerChat(Mood.NORMAL, "A petite paramour.");
                stageInt = 28;
                break;

            case 28:
                sendNPCChat(Mood.CONFUSED,"What?");
                stageInt = 29;
                break;

            case 29:
                sendPlayerChat(Mood.NORMAL, "A concise courter!");
                stageInt = 26;
                break;

            case 30:
                sendPlayerChat(Mood.NORMAL, "Yes.");
                stageInt = 31;
                break;

            case 31:
                sendNPCChat(Mood.HAPPY, "I would love to leave the village and be romanced by " +
                        "exotic, handsome, outlander men. There's a problem, " +
                        "though.");
                stageInt = 32;
                break;

            case 32:
                sendPlayerChat(Mood.ASKING, "What's that?");
                stageInt = 34;
                break;

            case 34:
                sendNPCChat(Mood.SAD, "My papa, the chieftain. He would never let an outerlander " +
                        "pursue me.");
                stageInt = 35;
                break;

            case 35:
                sendPlayerChat(Mood.ASKING, "Why not?");
                stageInt = 36;
                break;

            case 36:
                sendNPCChat(Mood.NORMAL, "He thinks all your people are our enemies.");
                stageInt = 37;
                break;

            case 37:
                sendOptionsDialogue(DEFAULT, "So, you want me to talk to your father?", "So, you want me to kill your father?");
                stageInt = 38;
                break;

            case 38:
                switch (componentId) {
                    case OPTION_1:
                        sendNPCChat(Mood.SAD, "I've tried to reason with him, but he's impossible! Maybe " +
                                "he'll listen to you. I know some of the others feel the " +
                                "same, but they're loyal to papa.");
                        player.getInventory().deleteItem(GunnarsGround.ENGRAVED_RING, 1);
                        player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(5);
                        stageInt = 40;
                        break;
                    case OPTION_2:
                        sendNPCChat(Mood.WTF_FACE, "What? No! Maybe... you could just try talking to him.");
                        stageInt = 39;
                        break;

                    case 39:
                        sendNPCChat(Mood.SAD, "I've tried to reason with him, but he's impossible! Maybe " +
                                "he'll listen to you. I know some of the others feel the " +
                                "same, but they're loyal to papa.");
                        player.getInventory().deleteItem(GunnarsGround.ENGRAVED_RING, 1);
                        player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(5);
                        stageInt = 40;
                        break;

                    case 40:
                        sendOptionsDialogue(DEFAULT, "Where is he?", "I'll see what I can do.");
                        stageInt = 41;
                        break;

                    case 41:
                        switch (componentId) {
                            case OPTION_1:
                                sendNPCChat(Mood.NORMAL, "In the longhouse at the north end of the village, drinking " +
                                        "and shouting.");
                                stageInt = -2;
                                break;
                            case OPTION_2:
                                sendPlayerChat(Mood.NORMAL, "I'll see what I can do.");
                                stageInt = -2;
                                break;
                        }
                        break;

                    case 42:
                        sendPlayerChat(Mood.NORMAL, "He mentioned someone called Gunnar, and what you should " +
                                "think about his feelings.");
                        stageInt = 43;
                        break;

                    case 43:
                        sendNPCChat(Mood.ANGRY, "By the eyeballs of Guthix! Always Gunnar!");
                        stageInt = 44;
                        break;

                    case 44:
                        sendOptionsDialogue(DEFAULT, "Who is Gunnar?", "What should we do now?");
                        stageInt = 45;
                        break;

                    case 45:
                        sendNPCChat(Mood.ANGRY, "He was my great-grandpapa! He founded this village a " +
                                "hundred years ago.");
                        stageInt = 46;
                        break;

                    case 46:
                        sendOptionsDialogue(DEFAULT, "I don't know, maybe your mystery man will know.");
                        player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(7);
                        stageInt = -2;
                        break;

                    case 47:
                        sendPlayerChat(Mood.SAD, "I meant to bring you a poem, but I seem to have " +
                                "mislaid it.");
                        stageInt = -2;
                        break;

                    case 48:
                        sendPlayerChat(Mood.HAPPY, "Another gift from your mysterious suitor.");
                        stageInt = 49;
                        break;

                    case 49:
                        sendNPCChat(Mood.ASKING, "A scroll?");
                        stageInt = 50;
                        break;

                    case 50:
                        sendNPCChat(Mood.NORMAL, "It's a poem; a story to convince your father to settle " +
                                "down. You could recite it to him.");
                        stageInt = 51;
                        break;

                    case 51:
                        sendNPCChat(Mood.HAPPY, "Let me see that.");
                        stageInt = 52;
                        break;

                    case 52:
                        sendItemDialogue(GunnarsGround.GUNNARS_GROUND, "You show Gudrun the poem.");
                        stageInt = 53;
                        break;

                    case 53:
                        sendNPCChat(Mood.NORMAL, "'Gunnar's Ground.'");
                        stageInt = 54;
                        break;

                    case 54:
                        sendNPCChat(Mood.HAPPY, "Yes! I think this could work. I'll go to the longhouse right " +
                                "away!");
                        player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(12);
                        player.getInventory().deleteItem(GunnarsGround.GUNNARS_GROUND, 1);
                        stageInt = -3;
                        break;

                    case 55:
                        sendOptionsDialogue(DEFAULT, "Yes.", "Not right now.");
                        stageInt = 56;
                        break;

                    case 56:
                        switch (componentId) {
                            case OPTION_1:
                                //TODO Starts cutscene.
                                end();
                                break;
                            case OPTION_2:
                                sendPlayerChat(Mood.NORMAL, "Not right now.");
                                stageInt = -2;
                                break;
                        }
                        break;

                    case 57:
                        sendNPCDialogue(DORODON, HAPPY, "I'm more than a little surprised! He even gave me a house to live in!");
                        stageInt = 58;
                        break;

                    case 58:
                        sendNPCChat(Mood.HAPPY, "Our people's tradition is that the tribe provides lodging " +
                                "for the poet.");
                        stageInt = 59;
                        break;

                    case 59:
                        sendNPCDialogue(DORODON, HAPPY, "It's huge!");
                        stageInt = 60;
                        break;

                    case 60:
                        sendNPCChat(Mood.HAPPY, "It's not in the village. It's easy of here: across the river " +
                                "and north of the road on the way to Varrock. It's a big " +
                                "house with roses outside.");
                        stageInt = 61;
                        break;

                    case 61:
                        sendNPCDialogue(DORODON, HAPPY, "I think Gunthor wants me to keep close, but not too close. " +
                                "Oh, I found something there for you!");
                        stageInt = 62;
                        break;

                    case 62:
                        sendNPCDialogue(DORODON, HAPPY, "Whoever lived there before left a dozen pairs of boots in " +
                                "the attic.");
                        stageInt = 63;
                        break;

                    case 63:
                        sendNPCDialogue(DORODON, NORMAL, "I picked out a pair for you to thank you for all your help.");
                        stageInt = 64;
                        break;

                    case 64:
                        sendNPCDialogue(DORODON, HAPPY, "Underneath them all was this magic lamp. You should have " +
                                "it aswell!");
                        stageInt = 65;
                        break;

                    case 65:
                        sendNPCChat(Mood.HAPPY, "We're going to the new house. You should come and visit!");
                        stageInt = 66;
                        break;

                    case 66:
                        sendNPCDialogue(DORODON, HAPPY, "Yes, we'll see you there!");
                        stageInt = 67;
                        break;

                    case 67:
                        sendOptionsDialogue(DEFAULT, "I'll see you soon.", "I'll consider dropping in.");
                        stageInt = 68;
                        break;

                    case 68:
                        switch (componentId) {
                            case OPTION_1:
                            case OPTION_2:
                                sendNPCDialogue(DORODON, HAPPY, "Goodbye!");
                                stageInt = 69;
                                break;
                        }
                        break;

                    case 69:
                        sendNPCChat(Mood.HAPPY, "Goodbye!");
                        stageInt = -4;
                        break;


                    case -4:
                        //TODO fadescreen
                        end();
                        player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(16);
                        player.getQuestManager().get(Quests.GUNNARS_GROUND).setState(QuestState.COMPLETED);
                        player.getQuestManager().get(Quests.GUNNARS_GROUND).finish();
                        break;

                    case -3:
                        //Starts Gunnar's Ground cutscene.
                        break;

                    case -2:
                        end();
                        break;
                }
                break;

        }
    }

    @Override
    public void finish() {

    }
}
