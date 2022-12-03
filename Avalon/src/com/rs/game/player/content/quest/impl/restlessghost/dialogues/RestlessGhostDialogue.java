package com.rs.game.player.content.quest.impl.restlessghost.dialogues;

import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;

/**
 * Created by James on 7/3/2015.
 */
public class RestlessGhostDialogue extends Dialogue {

    /*
    Quest Items
     */
    public static final int GHOSTSPEAK_AMULET = 552;
    public static final int SKULL = 964;

    @Override
    public int getNPCID() {
        return 457;
    }

    @Override
    public void start() {
        stageInt = (Integer) parameters[0];
        @SuppressWarnings("unused")
		int progress = player.getQuestManager().get(Quests.THE_RESTLESS_GHOST).getStage();
        switch(stageInt) {
            /**
             * Not Started
             */
            case 0:
                sendPlayerChat(Mood.NORMAL, "Hello ghost, how are you?");
                stageInt = -1;
                break;
            /**
             * After Talking with Ghost.
             */
            case 3:
            case 4:
                sendPlayerChat(Mood.NORMAL, "Hello ghost, how are you?");
                stageInt = 60;
                break;
        }
    }

    @Override
    public void run(int interfaceId, int componentId) {
        switch(stageInt) {
            case -1:
                if (player.getEquipment().getAmuletId() == GHOSTSPEAK_AMULET) {
                    sendNPCChat(Mood.NORMAL, "Not very good actually.");
                    stageInt = 1;
                } else {
                    sendNPCChat(Mood.NORMAL, "Woo woo wooooo!");
                    stageInt = 30;
                }
                break;
            case 1:
                sendPlayerChat(Mood.NORMAL, "What's the problem then?");
                stageInt = 2;
                break;
            case 2:
                sendNPCChat(Mood.NORMAL, "Did you just understand what I said???");
                stageInt = 3;
                break;
            case 3:
                sendOptionsDialogue(DEFAULT, "Yep, now tell me what the problem is.", "No, you sound like you're speaking nonsense to me.", "Wow, this amulet works!");
                stageInt = 4;
                break;
            case 4:
                switch(componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "Yep, now tell me what the problem is.");
                        stageInt = 6;
                        break;
                    case OPTION_2:
                        sendPlayerChat(Mood.NORMAL, "No, you sound like you're speaking nonsense to me.");
                        stageInt = 10;
                        break;
                    case OPTION_3:
                        sendPlayerChat(Mood.NORMAL, "Wow, this amulet works!");
                        stageInt = 29;
                        break;
                }
                break;
            /*First Option*/
            case 6:
                sendNPCChat(Mood.NORMAL, "WOW! This is INCREDIBLE! I didn't expect anyone", "to ever understand me again!");
                stageInt = 7;
                break;
            case 7:
                sendPlayerChat(Mood.NORMAL, "Ok, Ok, I can understand you!");
                stageInt = 8;
                break;
            case 8:
                sendPlayerChat(Mood.NORMAL, "But have you any idea WHY you're doomed to be a", "ghost?");
                stageInt = 9;
                break;
            case 9:
                sendNPCChat(Mood.NORMAL, "Well, to be honest... I'm not sure.");
                stageInt = 22;
                break;

            /*Second Option*/
            case 10:
                sendNPCChat(Mood.NORMAL, "Oh that's a pity. You got my hopes up there.");
                stageInt = 11;
                break;
            case 11:
                sendPlayerChat(Mood.NORMAL, "Yeah, it is a pity. Sorry about that.");
                stageInt = 12;
                break;
            case 12:
                sendPlayerChat(Mood.NORMAL, "Hang on a second... you CAN understand me!");
                stageInt = 13;
                break;
            case 13:
                sendOptionsDialogue(DEFAULT, "No I can't.", "Yep, clever aren't I?");
                stageInt = 14;
                break;
            case 14:
                switch(componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "No I can't.");
                        stageInt = 15;
                        break;
                    case OPTION_2:
                        sendPlayerChat(Mood.NORMAL, "Yep, clever aren't I?");
                        stageInt = 18;
                        break;
                }
                break;
            case 15:
                sendNPCChat(Mood.NORMAL, "Great.");
                stageInt = 16;
                break;
            case 16:
                sendNPCChat(Mood.NORMAL, "The first person I can speak to in ages...");
                stageInt = 17;
                break;
            case 17:
                sendNPCChat(Mood.NORMAL, "..and they're a moron.");
                stageInt = -2;
                break;

            case 18:
                sendNPCChat(Mood.NORMAL, "I'm impressed. You must be very powerful. I don't", "suppose you can stop me being a ghost?");
                stageInt = 19;
                break;
            case 19:
                sendOptionsDialogue(DEFAULT, "Yes, ok. Do you know WHY you're a ghost?", "No, you're scary!");
                stageInt = 20;
                break;
            case 20:
                switch(componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "Yes, ok. Do you know WHY you're a ghost?");
                        stageInt = 21;
                        break;
                    case OPTION_2:
                        sendPlayerChat(Mood.NORMAL, "No, you're scary!");
                        stageInt = 15;
                        break;
                }
                break;
            case 21:
                sendNPCChat(Mood.NORMAL, "Nope. I just know I can't do much of anything like", "this!");
                stageInt = 22;
                break;
            case 22:
                sendPlayerChat(Mood.NORMAL, "I've been told a certain task may need to be completed", "so you can rest in peace.");
                stageInt = 23;
                break;
            case 23:
                sendNPCChat(Mood.NORMAL, "I should think it is probably because a warlock has come", "along and stolen my skull. If you look inside my coffin", "there, you'll find my corpse without a head on it.");
                stageInt = 24;
                break;
            case 24:
                sendPlayerChat(Mood.NORMAL, "Do you know where this warlock might be now?");
                stageInt = 25;
                break;
            case 25:
                sendNPCChat(Mood.NORMAL, "I think it was one of the warlocks who lives in the big", "tower by the sea south-west from here.");
                stageInt = 26;
                break;
            case 26:
                sendPlayerChat(Mood.NORMAL, "Ok. I will try and get the skull back for you, then you", "can rest in peace.");
                player.getQuestManager().get(Quests.THE_RESTLESS_GHOST).setStage(3);
                player.getQuestManager().update();
                stageInt = 27;
                break;
            case 27:
                sendNPCChat(Mood.NORMAL, "Ooh, thank you. That would be such a great relief!");
                stageInt = 28;
                break;
            case 28:
                sendNPCChat(Mood.NORMAL, "It is so dull being a ghost...");
                stageInt = -2;
                break;
            /*Third Option*/
            case 29:
                sendNPCChat(Mood.NORMAL, "Oh! It's your amulet that's doing it! I did wonder. I", "don't suppose you can help me?");
                stageInt = 19;
                break;
            /*
            Dialogue When Not Wearing Amulet
             */
            case 30:
                sendOptionsDialogue(DEFAULT, "Sorry, I don't speak ghost.", "Ooh... THAT'S interesting.", "Any hints where I can find some treasure?");
                stageInt = 31;
                break;
            case 31:
                switch(componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "Sorry, I don't speak ghost.");
                        stageInt = 32;
                        break;
                    case OPTION_2:
                        sendPlayerChat(Mood.NORMAL, "Ooh... THAT'S interesting.");
                        stageInt = 36;
                        break;
                    case OPTION_3:
                        sendPlayerChat(Mood.NORMAL, "Any hints where I can find some treasure?");
                        stageInt = 56;
                        break;
                }
                break;
            case 32:
                sendNPCChat(Mood.NORMAL, "Woo woo?");
                stageInt = 33;
                break;
            case 33:
                sendPlayerChat(Mood.NORMAL, "Nope, still don't understand you.");
                stageInt = 34;
                break;
            case 34:
                sendNPCChat(Mood.NORMAL, "W000000000!");
                stageInt = 35;
                break;
            case 35:
                sendPlayerChat(Mood.NORMAL, "Never mind.");
                stageInt = -2;
                break;
            case 36:
                sendNPCChat(Mood.NORMAL, "Woo wooo. Woooooooooooooooooo!");
                stageInt = 37;
                break;
            case 37:
                sendOptionsDialogue(DEFAULT, "Did he really?", "Yeah, that's what I thought.");
                stageInt = 38;
                break;
            case 38:
                switch(componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "Did he really?");
                        stageInt = 39;
                        break;
                    case OPTION_2:
                        sendPlayerChat(Mood.NORMAL, "Yeah, that's what I thought.");
                        stageInt = 47;
                        break;
                }
                break;
            case 39:
                sendNPCChat(Mood.NORMAL, "Woo.");
                stageInt = 40;
                break;
            case 40:
                sendOptionsDialogue(DEFAULT, "My brother had EXACTLY the same problem.", "Goodbye. Thanks for the chat.");
                stageInt = 41;
                break;
            case 41:
                switch(componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "My brother had EXACTLY the same problem.");
                        stageInt = 42;
                        break;
                    case OPTION_2:
                        sendPlayerChat(Mood.NORMAL, "Goodbye. Thanks for the chat.");
                        stageInt = 46;
                        break;
                }
                break;
            case 42:
                sendNPCChat(Mood.NORMAL, "Woo Wooooo!");
                stageInt = 43;
                break;
            case 43:
                sendNPCChat(Mood.NORMAL, "Wooooo Woo woo woo!");
                stageInt = 44;
                break;
            case 44:
                sendOptionsDialogue(DEFAULT, "Goodbye. Thanks for the chat.", "You'll have to give me the recipe some time...");
                stageInt = 45;
                break;
            case 45:
                switch(componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "Goodbye. Thanks for the chat.");
                        stageInt = 46;
                        break;
                    case OPTION_2:
                        sendNPCChat(Mood.NORMAL, "You'll have to give me the recipe some time...");
                        stageInt = 55;
                        break;
                }
                break;
            case 46:
                sendNPCChat(Mood.NORMAL, "Wooo wooo?");
                stageInt = -2;
                break;
            case 47:
                sendNPCChat(Mood.NORMAL, "Woo wooooooooooooooo...");
                stageInt = 48;
                break;
            case 48:
                sendOptionsDialogue(DEFAULT, "Goodbye. Thanks for the chat.", "Hmm I'm not sure about that.");
                stageInt = 49;
                break;
            case 49:
                switch(componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "Goodbye. Thanks for the chat.");
                        stageInt = 46;
                        break;
                    case OPTION_2:
                        sendPlayerChat(Mood.NORMAL, "Hmm... I'm not sure about that.");
                        stageInt = 50;
                        break;
                }
                break;
            case 50:
                sendPlayerChat(Mood.NORMAL, "Well, if you INSIST.");
                stageInt = 51;
                break;
            case 51:
                sendNPCChat(Mood.NORMAL, "Wooooooooo!");
                stageInt = 52;
                break;
            case 52:
                sendPlayerChat(Mood.NORMAL, "Ah well, better be off now...");
                stageInt = 53;
                break;
            case 53:
                sendNPCChat(Mood.NORMAL, "Woo.");
                stageInt = 54;
                break;
            case 54:
                sendPlayerChat(Mood.NORMAL, "Bye.");
                stageInt = -2;
                break;
            case 55:
                sendNPCChat(Mood.NORMAL, "Wooooooo woo woooooooo.");
                stageInt = 48;
                break;
            case 56:
                sendNPCChat(Mood.NORMAL, "Wooooooo woo! Wooooo woo wooooo woowoowoo woo", "Woo wooo. Wooooo woo woo? Woooooooooooooooooo!");
                stageInt = 57;
                break;
            case 57:
                sendOptionsDialogue(DEFAULT, "Sorry, I don't speak ghost.", "Thank you. You've been very helpful.");
                stageInt = 58;
                break;
            case 58:
                switch(componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "Sorry, I don't speak ghost.");
                        stageInt = 32;
                        break;
                    case OPTION_2:
                        sendPlayerChat(Mood.NORMAL, "Thank you. You've been very helpful.");
                        stageInt = 59;
                        break;
                }
                break;
            case 59:
                sendNPCChat(Mood.NORMAL, "Woooooooo.");
                stageInt = -2;
                break;
            case 60:
                sendNPCChat(Mood.NORMAL, "How are you doing finding my skull?");
                stageInt = 61;
                break;
            case 61:
                if (player.getInventory().containsItem(SKULL, 1)) {
                    sendPlayerChat(Mood.NORMAL, "I have found it!");
                    stageInt = 62;
                } else {
                    sendPlayerChat(Mood.NORMAL, "Sorry, I can't find it at the moment.");
                    stageInt = 63;
                }
                break;
            case 62:
                sendNPCChat(Mood.NORMAL, "Hurrah! Now I can stop being a ghost! You just need", "to put it in my coffin there, and I will be free!");
                stageInt = -2;
                break;
            case 63:
                sendNPCChat(Mood.NORMAL, "Ah well. Keep on looking.");
                stageInt = 64;
                break;
            case 64:
                sendNPCChat(Mood.NORMAL, "I'm pretty sure it's somewhere in the tower south-west", "from here. There's a lot of levels to the tower, though. I", "suppose it might take a little while to find.");
                stageInt = -2;
                break;
            /*
                USE SKULL ON COFFIN TO COMPLETE QUEST
             */
            case -2:
                end();
                break;
        }
    }

    @Override
    public void finish() {

    }
}
