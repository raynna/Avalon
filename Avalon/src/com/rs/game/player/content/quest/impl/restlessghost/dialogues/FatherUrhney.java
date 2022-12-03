package com.rs.game.player.content.quest.impl.restlessghost.dialogues;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;

/**
 * Created by Austin on 7/3/2015.
 */
public class FatherUrhney extends Dialogue {

    /*
     *  Respresents Amulet
     */
    public static final int GHOSTSPEAK_AMULET = 552;

    @Override
    public int getNPCID() {
        return 458;
    }

    @Override
    public void start() {
        stageInt = (Integer) parameters[0];
        int progress = player.getQuestManager().get(Quests.THE_RESTLESS_GHOST).getStage();
        /*
        Default Quest Dialogue
         */
        if (stageInt == 0) {
            sendNPCChat(Mood.MILDLY_ANGRY, "Go away! I'm meditating!");
            stageInt = -1;
        }
        /*
        Quest Stage Dialogue
         */
        if (progress == 1) {
            sendNPCChat(Mood.MILDLY_ANGRY, "Go away! I'm meditating!");
            stageInt = 13;
        }
        else if (progress == 2) {
            sendNPCChat(Mood.MILDLY_ANGRY, "Go away! I'm meditating!");
        }

    }

    @Override
    public void run(int interfaceId, int componentId) {
        switch(stageInt) {
            /*
            Default Dialogue Options
             */
            case -1:
                sendOptionsDialogue(DEFAULT, "Well, that's friendly.", "I've come to repossess your house.");
                stageInt = 1;
                break;
            case 1:
                switch(componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "Well, that's friendly.");
                        stageInt = 2;
                        break;
                    case OPTION_2:
                        sendPlayerChat(Mood.NORMAL, "I've come to repossess your house.");
                        stageInt = 4;
                        break;
                }
                break;
            case 2:
                sendNPCChat(Mood.MILDLY_ANGRY, "I SAID go AWAY.");
                stageInt = 3;
                break;
            case 3:
                sendPlayerChat(Mood.NORMAL, "Ok, ok... sheesh, what a grouch.");
                stageInt = -2;
                break;
            case 4:
                sendNPCChat(Mood.NORMAL, "Under what grounds???");
                stageInt = 5;
                break;
            case 5:
                sendOptionsDialogue(DEFAULT, "Repeated failure on mortgage repayments.", "I don't know, I just wanted this house.");
                stageInt = 6;
                break;
            case 6:
                switch(componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "Repeated failure on mortgage repayments.");
                        stageInt = 7;
                        break;
                    case OPTION_2:
                        sendPlayerChat(Mood.NORMAL, "I don't know, I just wanted this house...");
                        stageInt = 12;
                        break;
                }
                break;
            case 7:
                sendNPCChat(Mood.MILDLY_ANGRY, "What?");
                stageInt = 8;
                break;
            case 8:
                sendNPCChat(Mood.NORMAL, "But... I don't have a mortgage! I built this house", "myself!");
                stageInt = 9;
                break;
            case 9:
                sendPlayerChat(Mood.NORMAL, "Sorry. I must have got the wrong address. All the", "houses look the same around here.");
                stageInt = 10;
                break;
            case 10:
                sendNPCChat(Mood.MILDLY_ANGRY, "What? What houses? What ARE you talking about???");
                stageInt = 11;
                break;
            case 11:
                sendPlayerChat(Mood.NORMAL, "Never mind.");
                stageInt = -2;
                break;
            case 12:
                sendPlayerChat(Mood.MILDLY_ANGRY, "Oh... go away and stop waisting my time!");
                stageInt = -2;
                break;
            /*
            (The Restless Ghost) Quest Dialogue
             */
            case 13:
                sendOptionsDialogue(DEFAULT, "Well, that's friendly.", "Father Aereck sent me to talk to you.", "I've come to repossess your house.");
                stageInt = 14;
                break;
            case 14:
                switch(componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "Well, that's friendly.");
                        stageInt = 2;
                        break;
                    case OPTION_2:
                        sendPlayerChat(Mood.NORMAL, "Father Aereck sent me to talk to you.");
                        stageInt = 15;
                        break;
                    case OPTION_3:
                        sendPlayerChat(Mood.NORMAL, "I've come to repossess your house.");
                        stageInt = 4;
                        break;
                }
                break;
            case 15:
                sendNPCChat(Mood.NORMAL, "I suppose I'd better talk to you then. What problems", "has he got himself into this time?");
                stageInt = 16;
                break;
            case 16:
                sendOptionsDialogue(DEFAULT, "He's got a ghost haunting his graveyard.", "You mean he gets himself into lots of problems?");
                stageInt = 17;
                break;
            case 17:
                switch(componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "He's got a ghost haunting his graveyard.");
                        stageInt = 21;
                        break;
                    case OPTION_2:
                        sendPlayerChat(Mood.NORMAL, "You mean he gets himself into lots of problems?");
                        stageInt = 18;
                        break;
                }
                break;
            case 18:
                sendNPCChat(Mood.NORMAL, "Yeah. For example, when we were trainee priests he", "kept on getting stuck up bell ropes.");
                stageInt = 19;
                break;
            case 19:
                sendNPCChat(Mood.NORMAL, "Anyway. I don't have time for chitchat. What's his", "problem THIS time?");
                stageInt = 20;
                break;
            case 20:
                sendPlayerChat(Mood.NORMAL, "He's got a ghost haunting his graveyard.");
                stageInt = 21;
                break;
            case 21:
                sendNPCChat(Mood.NORMAL, "Oh, the silly fool.");
                stageInt = 22;
                break;
            case 22:
                sendNPCChat(Mood.NORMAL, "I leave town for just five months, and ALREADY he", "can't manage.");
                stageInt = 23;
                break;
            case 23:
                sendNPCChat(Mood.NORMAL, "(sigh)");
                stageInt = 24;
                break;
            case 24:
                sendNPCChat(Mood.MILDLY_ANGRY, "Well, I can't go back and exorcise it. I vowed not to", "leave this place. Until I had done a full two years of", "prayer and meditation.");
                stageInt = 25;
                break;
            case 25:
                sendNPCChat(Mood.NORMAL, "Tell you what I can do though; take this amulet.");
                stageInt = 26;
                break;
            case 26:
                sendHandedItem(GHOSTSPEAK_AMULET, "Father Urhney hands you an amulet.");
                player.getInventory().addItem(GHOSTSPEAK_AMULET, 1);
                player.getQuestManager().get(Quests.THE_RESTLESS_GHOST).setStage(2);
                player.getQuestManager().update();
                stageInt = 27;
                break;
            case 27:
                sendNPCChat(Mood.NORMAL, "It is an Amulet of Ghostspeak.");
                stageInt = 28;
                break;
            case 28:
                sendNPCChat(Mood.NORMAL, "So called, because when you wear it you can speak to", "ghosts. A lot of ghosts are doomed to be ghosts because", "they have left some important task uncompleted.");
                stageInt = 29;
                break;
            case 29:
                sendNPCChat(Mood.NORMAL, "Maybe if you know what this task is, you can get rid of", "the ghost. I'm not making any guarantees mind you,", "but it is the best I can do right now.");
                stageInt = 30;
                break;
            case 30:
                sendPlayerChat(Mood.NORMAL, "Thank you. I'll give it a try!");
                stageInt = -2;
                break;
            case 31:
                sendOptionsDialogue(DEFAULT, "Well, that's friendly.", "I've come to repossess your house.", "I've lost the Amulet of Ghostspeak.");
                stageInt = 32;
                break;
            case 32:
                switch(componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "Well, that's friendly.");
                        stageInt = 2;
                        break;
                    case OPTION_2:
                        sendPlayerChat(Mood.NORMAL, "Father Aereck sent me to talk to you.");
                        stageInt = 15;
                        break;
                    case OPTION_3:
                        sendPlayerChat(Mood.NORMAL, "I've lost the Amulet of Ghostspeak.");
                        stageInt = 33;
                        break;
                }
                break;
            case 33:
                sendDialogue("Father Urhney sighs.");
                stageInt = 34;
                break;
            case 34:
                if (player.getInventory().containsOneItem(GHOSTSPEAK_AMULET)) {
                    sendNPCChat(Mood.MILDLY_ANGRY, "What are you talking about? I can see you've got it", "with you!");
                    stageInt = -2;
                } else {
                    sendNPCChat(Mood.MILDLY_ANGRY, "How careless can you get? Those things aren't easy to", "come by you know! It's a good job I've got a spare.");
                    stageInt = 35;
                }
                break;
            case 35:
                sendHandedItem(GHOSTSPEAK_AMULET, "Father Urhney hands you an amulet.");
                if(player.getInventory().getFreeSlots() > 0) {
                    player.getInventory().addItem(GHOSTSPEAK_AMULET, 1);
                } else {
                    World.addGroundItem(new Item(GHOSTSPEAK_AMULET), new WorldTile(player.getX(), player.getY(), player.getPlane()));
                }
                stageInt = 36;
                break;
            case 36:
                sendNPCChat(Mood.MILDLY_ANGRY, "Be more careful this time.");
                stageInt = 37;
                break;
            case 37:
                sendPlayerChat(Mood.NORMAL, "Ok, I'll try to be.");
                stageInt = -2;
                break;
            case -2:
                end();
                break;
        }
    }

    @Override
    public void finish() {

    }
}
