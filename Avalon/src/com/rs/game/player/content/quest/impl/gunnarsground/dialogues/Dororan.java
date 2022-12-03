package com.rs.game.player.content.quest.impl.gunnarsground.dialogues;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.game.player.content.quest.impl.gunnarsground.GunnarsGround;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;
import com.rs.utils.Utils;

/**
 * @Author Frostbite
 * @Contact<frostbitersps@gmail.com;skype:frostbitersps>
 */
public class Dororan extends Dialogue {

    /**
     * *Engrave Ring*
     * sendItemDialogue(engravedRing, "You engrave 'Gudrun the fair, Gudrun the Fiery' onto the ring.");
     * stageInt = -2;
     */

    public static final String[] firstWord = {
            "That doesn't really fit. It needs to rhyme with the word " +
                    "'day.", "That dosen't really fit. It needs to mean something like " +
            "'wandering aimlessly.'", "That doesn't really fit. It needs to be one syllable long."
    },
    secondWord = {
            "That doesn't really fit. It needs to be one syllable long.", "That doesn't really fit. It needs to mean something like " +
            "'danger.'", "That doesn't really fit it. It needs to rhyme with the word " +
            "'yet'."
    },
    thirdWord = { "That doesn't really fit. It needs to rhyme with the word " +
            "'lore'.", "That doesn't really fit. It needs to imply some agressive " +
            "action, like 'started a fight'."

    };

    /**
     * NPC Id's
     */

    /**
     * Edit NPCS
     */

    public final int GUNTHOR = 2322;

    public final int KJELL = 2321;

    public final int HAAKON = 2323;

    public final int GUDRUN = 2324;

    /**
     * TODO add npcIds
     */

    @Override
    public void start() {
        stageInt = (Integer) parameters[0];
        int progress = player.getQuestManager().get(Quests.GUNNARS_GROUND).getStage();
        QuestState state = player.getQuestManager().get(Quests.GUNNARS_GROUND).getState();
        switch(progress) {
            case 3:
                sendNPCChat(Mood.ASKING, "Is it done? have you created a work of magnificent " +
                        "beauty?");
                stageInt = 52;
                break;
            case 7:
                sendNPCChat(Mood.ASKING, "Did you give Gudrun the ring? What did she think? Did it " +
                        "capture her heart?");
                stageInt = 62;
                break;

            case 8:
                sendNPCChat(Mood.NORMAL, "'Even the bloodiest rose must settle.' Mixed metaphor. " +
                        "What settles? Detritus. That's hardly flattering.");
                stageInt = 78;
                break;

            case 9:
                sendNPCChat(Mood.HAPPY, "The poem still isn't finished, though. I have another " +
                        "missing world. GIve me another one; anything, to get me started.");
                stageInt = 100;
                break;

            case 10:
                sendNPCChat(Mood.HAPPY, "It's coming together. We're nearly done! One more to go!");
                stageInt = 112;
                break;

            case 11:
                if(!player.getInventory().containsOneItem(GunnarsGround.GUNNARS_GROUND)) {
                    sendPlayerChat(Mood.NORMAL, "Er, I lost the poem.");
                    stageInt = 135;
                }
                sendNPCChat(Mood.SAD, "My poem is terrible, isn't it? The chieftain will probably " +
                        "have killed me.");
                stageInt = 133;
                break;

            /**
             * First cutscene
             */
            case 12:
                sendNPCChat(Mood.ASKING, "How long have they been in there?");
                stageInt = 138;
                break;

            /**
             * Second cuscene
             */
            case 13:
            sendNPCChat(Mood.SAD, "I hope they at least give me a decent burial.");
                stageInt = 151;
                break;

            /**
             * Third cutscene
             */
            case 14:
                sendNPCDialogue(GUDRUN, HAPPY, "That was brilliant! I must know who wrote that poem.");
                stageInt = 161;
                break;

        }

        switch (stageInt) {
            /**
             * Not Started Quest
             */
            case 0:
                sendNPCChat(Mood.SAD, "'My heart with burdens heave does it lie.'");
                stageInt = -1;
                break;

            /**
             * Declined Quest
             */
            case -3:
                break;

            /**
             * Accepted Quest
             */
            case -4:
                sendNPCChat(Mood.NORMAL, "I need a ring of purest gold. Then we can engrave it with " +
                        "the words of my heart.");
                stageInt = 22;
                break;

            /**
             * Started Quest
             */
            case -5:
                sendNPCChat(Mood.NORMAL, "'I await in eagerness for a loop of lustrous grandeur.' No, " +
                        "that just sounds ridiculous. Have you brought me a ring " +
                        "from Jeffery?");
                stageInt = 32;
                break;
        }

        switch (state) {
            case NOT_STARTED:
                stageInt = 0;
                break;
            case STARTED:
                stageInt = -5;
                break;
            case COMPLETED:
                break;
        }
    }

    @Override
    public void run(int interfaceId, int componentId) {
        String firstRandomWord = firstWord[Utils.random(firstWord.length)];
        String secondRandomWord = secondWord[Utils.random(secondWord.length)];
        String thirdRandomWord = thirdWord[Utils.random(thirdWord.length)];
    switch(stageInt) {
        case -1:
            sendNPCChat(Mood.SAD, "'For never did I...'");
            stageInt = 1;
            break;

        case 1:
            sendNPCChat(Mood.SAD, "Um...");
            stageInt = 2;
            break;

        case 2:
            sendOptionsDialogue(DEFAULT, "'...ever learn to fly?'", "'...eat redberry pie?'", "'...get the evil eye?'");
            stageInt = 3;
            break;

        case 3:
            switch(componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.HAPPY, "You're a poet too?");
                    stageInt = 4;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.HAPPY, "You're a poet too?");
                    stageInt = 4;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.HAPPY, "You're a poet too?");
                    stageInt = 4;
                    break;
            }
            break;

        case 4:
            sendOptionsDialogue(DEFAULT, "Yes.", "Maybe a bit.", "No.");
            stageInt = 5;
            break;

        case 5:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.SAD, "Ah! Then i'm sure you can identify with the arduous state " +
                            "of my life.");
                    stageInt = 6;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.SAD, "Ah! Then i'm sure you can identify with the arduous state " +
                            "of my life.");
                    stageInt = 6;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.SAD, "Oh. How can I expect you to identify with the arduous " +
                            "state of my life?");
                    stageInt = 6;
                    break;
            }
            break;


        case 6:
            sendNPCChat(Mood.UPSET_FACE, "My heart is stricken with that most audacious of maladies!");
            stageInt = 7;
            break;

        case 7:
            sendOptionsDialogue(DEFAULT, "Angina?", "Hypertension?", "Coclearabsidosis?");
            stageInt = 8;
            break;

        case 8:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, "Love!");
                    stageInt = 9;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, "Love!");
                    stageInt = 9;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, "Love!");
                    stageInt = 9;
                    break;
            }
            break;

        case 9:
            sendNPCChat(Mood.SAD, "The walls of my heart are besieged by love's armies, and " +
                    "those walls begin to tumble!");
            stageInt = 10;
            break;

        case 10:
            sendNPCChat(Mood.SAD, "In the barbarian village lives the fairest maiden I have " +
                    "witnessed in all my life.");
            stageInt = 11;
            break;

        case 11:
            sendOptionsDialogue(DEFAULT, "What's so special about her?", "Get to the point.");
            stageInt = 12;
            break;

        case 12:
            switch (componentId) {
                case OPTION_1:
            sendNPCChat(Mood.NORMAL, "I wouldn't know where to start! Her fiery spirit? Her proud " +
                    "bearing? her winsome form?");
                    stageInt = 13;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.SAD, "The people of this village value strength, stature and " +
                            "riches. I have none of these things.");
                    stageInt = 15;
                    break;
            }
            break;

        case 13:
            sendOptionsDialogue(DEFAULT, "But why is this making you sad?", "What do you actually need?");
            stageInt = 14;
            break;

        case 14:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.SAD, "The people of this village value strength, stature and " +
                            "riches. I have none of these things.");
                    stageInt = 15;
                    break;
                case OPTION_2:
                    break;
            }
            break;

        case 15:
            sendNPCChat(Mood.SAD, "My people are indomitable warriors, dripping with gold and " +
                    "precious gems, but not I.");
            stageInt = 16;
            break;

        case 16:
            sendNPCChat(Mood.SAD, "I am not built for combat, and poetry has proven a little of " +
                    "poverty!");
            stageInt = 17;
            break;

        case 17:
            sendOptionsDialogue(DEFAULT, "There must be something you can do.", "Not to mention low stature.");
            stageInt = 18;
            break;

        case 18:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.CONFUSED, "If Gudrun could ever love a dwarf, surely she would need " +
                            "to see my artisanry.");
                    stageInt = 19;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.SAD, "You see!");
                    stageInt = 21;
                    break;
            }
            break;

        case 19:
            sendNPCChat(Mood.ASKING, "Will you help me? I am no crafter of metal.");
            stageInt = 20;
            break;

        case 20:
            end();
            player.getQuestManager().get(Quests.GUNNARS_GROUND).sendStartOption();
            break;

        case 21:
            sendNPCChat(Mood.CONFUSED, "If Gudrun could ever love a dwarf, surely she would need " +
                    "to see my artisanry.");
            stageInt = 19;
            break;

        case 22:
            sendNPCChat(Mood.HAPPY, "Oh! I know the perfect place to get a gold ring.");
            stageInt = 23;
            break;

        case 23:
            sendNPCChat(Mood.NORMAL, "Edgeville's metalsmith, Jeffery, labours like myself under " +
                    "the weight of unrequited love.");
            stageInt = 24;
            break;

        case 24:
            sendNPCChat(Mood.NORMAL, "Perhaps, if you took one of my love poems to Jeffery, he " +
                    "would trade it for a gold ring.");
            stageInt = 25;
            break;

        case 25:
            sendItemDialogue(GunnarsGround.LOVE_POEM, "Dororan gives you a poem.");
            stageInt = 26;
            break;

        case 26:
            sendOptionsDialogue(DEFAULT, "I have some questions.", "I'll return with a ring from Jeffery.");
            stageInt = 27;
            break;

        case 27:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, "By all means.");
                    stageInt = 28;
                    break;
                case OPTION_2:
                    sendPlayerChat(Mood.NORMAL, "I'll return the ring from Jeffery.");
                    stageInt = -2;
                    break;
            }
            break;

        case 28:
            sendOptionsDialogue(DEFAULT, "Does it have to be a ring from Jeffery?", "Where is Edgeville?", "Why can't you go yourself?",
                    "Why can't you give a poem directly to Gudrun?", "You want me to trick her into thinking you made the ring?");
            stageInt = 29;
            break;

        case 29:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, "Yes! jeffery's rings are timeless works of incomparable " +
                            "romantic splendour.");
                    stageInt = 26;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, "North of here, beyond a ruined fortress. It used to be a " +
                            "bustling den of cutthroats but it's quite quiet these days.");
                    stageInt = 26;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.SAD, "Some time ago, Jeffery asked me for advice in acting on " +
                            "his affections. I gave him the best advice that I would.");
                    stageInt = 31;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, "These love poems are written in the Misthalinian style. A " +
                            "noble barbarian maiden would be insulted, not flattered.");
                    stageInt = 26;
                    break;
                case OPTION_5:
                    sendNPCChat(Mood.NORMAL, "Oh no, nothing like that! I have the words, I just need " +
                            "your help with the tools.");
                    stageInt = 26;
                    break;
            }
            break;

        case 31:
            sendNPCChat(Mood.NORMAL, "Things didn't work out very well for him. one thing led to " +
                    "another and now he no longer wishes to speak to me.");
            stageInt = 26;
            break;

        case 32:
            if(player.getQuestManager().get(Quests.GUNNARS_GROUND).getStage() > 1) {
                if(!player.getInventory().containsOneItem(GunnarsGround.JEFFERYS_RING)) {
                    sendPlayerChat(Mood.NORMAL, "I did get a ring from Jeffery, but I seem to have lost it.");
                    stageInt = 34;
                }
            }
            if(!player.getInventory().containsOneItem(GunnarsGround.JEFFERYS_RING)) {
                sendOptionsDialogue(DEFAULT, "Where could I find one?", "I'll return with a ring from Jeffery.");
                stageInt = 33;
            }
            break;

        case 33:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, "Go north to Jeffery in Edgeville and trade the poem I " +
                            "gave you for a gold ring.");
                    stageInt = 26;
                    break;
                case OPTION_2:
                    sendPlayerChat(Mood.NORMAL, "I'll return the ring from Jeffery.");
                    stageInt = -2;
                    break;
            }
            break;

        case 34:
            sendNPCChat(Mood.ANGRY_YELLING, "How careless!");
            stageInt = 36;
            break;

        case 36:
            sendNPCChat(Mood.ASKING, "Is it this one? I found it on the ground.");
            stageInt = 37;
            break;

        case 37:
            sendItemDialogue(GunnarsGround.JEFFERYS_RING, "Dororan gives you back the ring.");
            if(player.getInventory().getFreeSlots() > 0) {
                player.getInventory().addItem(GunnarsGround.JEFFERYS_RING, 1);
            } else {
                World.addGroundItem(new Item(GunnarsGround.JEFFERYS_RING), new WorldTile(player.getX(), player.getY(), player.getPlane()));
            }
            stageInt = 38;
            break;

        case 38:
            sendNPCChat(Mood.NORMAL, "Now, would you engrave something on it for me?");
            stageInt = 39;
            break;

        case 39:
            sendNPCChat(Mood.ASKING, "Now, would you engrave something on it for me?");
            stageInt = 40;
            break;

        case 40:
            sendOptionsDialogue(DEFAULT, "What do you want me to engrave on it?", "It had better be something impressive.");
            stageInt = 41;
            break;

        case 41:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, "I've given this some thought.");
                    stageInt = 42;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, "I've given this some thought.");
                    stageInt = 42;
                    break;
            }
            break;

        case 42:
            sendNPCChat(Mood.HAPPY, "'Gudrun the Fair, Gudrun the Fiery.'");
            player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(3);
            stageInt = 43;
            break;

        case 43:
            sendOptionsDialogue(DEFAULT, "How do I engrave that?", "That sounds simple enough.");
            stageInt = 44;
            break;

        case 44:
            sendNPCChat(Mood.NORMAL, "Just use a chisel on the gold ring.");
            stageInt = 45;
            break;

        case 45:
            sendOptionsDialogue(DEFAULT, "Do you have a chisel I can use?", "Isn't a chisel a bit clumsy for that?", "Okay.");
            stageInt = 46;
            break;

        case 46:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.HAPPY, "Yes, here you go!");
                    stageInt = 47;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.HAPPY, "I've seen jewelcrafters use them for all sorts of precise " +
                            "work.");
                    stageInt = 50;
                    break;
                case OPTION_3:
                    sendPlayerChat(Mood.NORMAL, "Okay.");
                    stageInt = -2;
                    break;
            }
            break;

        case 47:
            sendItemDialogue(GunnarsGround.CHISEL, "Dororan gives you a chisel.");
            stageInt  = 48;
            break;

        case 48:
            sendOptionsDialogue(DEFAULT, "Isn't a chisel a bit clumsy for that?", "Okay.");
            stageInt = 49;
            break;

        case 49:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.HAPPY, "I've seen jewelcrafters use them for all sorts of precise " +
                            "work.");
                    stageInt = 50;
                    break;
                case OPTION_2:
                    sendPlayerChat(Mood.NORMAL, "Okay.");
                    stageInt = -2;
                    break;
            }
            break;

        case 50:
            sendOptionsDialogue(DEFAULT, "Do you have a chisel I can use?", "Okay.");
            stageInt = 51;
            break;

        case 51:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.HAPPY, "Yes, here you go!");
                    stageInt = 47;
                    break;
                case OPTION_2:
                    sendPlayerChat(Mood.NORMAL, "Okay.");
                    stageInt = -2;
                    break;
            }
            break;

        case 52:
            if(player.getInventory().containsItem(GunnarsGround.ENGRAVED_RING, 1)) {
                sendOptionsDialogue(DEFAULT, "It's come out perfectly.", "How does this look?", "It's a complete disaster.");
                stageInt = 53;
            }
            if(!player.getInventory().containsOneItem(GunnarsGround.JEFFERYS_RING)) {
                sendPlayerChat(Mood.SAD, "I lost the ring I got from Jeffery.");//TODO animation for recieving ring.
                stageInt = 34;
            } else {
                sendOptionsDialogue(DEFAULT, "Do you have a chisel I can use?", "Isn't a chisel a bit clumsy for that?", "Okay.");
                stageInt = 46;
                }
            break;

        case 53:
            switch (componentId) {
                case OPTION_1:
                    sendItemDialogue(GunnarsGround.ENGRAVED_RING, "You show Dororan the engraved ring.");
                    stageInt = 54;
                    break;
                case OPTION_2:
                    sendItemDialogue(GunnarsGround.ENGRAVED_RING, "You show Dororan the engraved ring.");
                    stageInt = 54;
                    break;
                case OPTION_3:
                    sendItemDialogue(GunnarsGround.ENGRAVED_RING, "You show Dororan the engraved ring.");
                    stageInt = 54;
                    break;
            }
            break;

        case 54:
            sendNPCChat(Mood.HAPPY, "Brilliant! That's perfect!");
            stageInt = 55;
            break;

        case 55:
            sendNPCChat(Mood.ASKING, "Will you do one more thing for me?");
            stageInt = 56;
            break;

        case 56:
            sendOptionsDialogue(DEFAULT, "Of course.", "What now?");
            stageInt = 57;
            break;

        case 57:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.SAD, "I fear she will only judge this poor book by its cover. " +
                            "Would you take the ring to Gudrun for me?");
                    player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(4);
                    stageInt = 58;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.SAD, "I fear she will only judge this poor book by its cover. " +
                            "Would you take the ring to Gudrun for me?");
                    player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(4);
                    stageInt = 58;
                    break;
            }
            break;

        case 58:
            sendOptionsDialogue(DEFAULT, "Very well.", "I hope this is going somewhere.");
            stageInt = 59;
            break;

        case 59:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, "Please don't tell her I'm a dwarf just yet.");
                    stageInt = 60;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, "Please don't tell her I'm a dwarf just yet.");
                    stageInt = 60;
                    break;
            }
            break;

        case 60:
            sendOptionsDialogue(DEFAULT, "Where is she?", "I'm on it.");
            stageInt = 61;
            break;

        case 61:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, "Inside the barbarian village.");
                    stageInt = -2;
                    break;
                case OPTION_2:
                    sendPlayerChat(Mood.NORMAL, "I'm on it.");
                    stageInt = -2;
                    break;
            }
            break;

        case 62:
            sendPlayerChat(Mood.NORMAL, "There's a problem.");
            stageInt = 62;
            break;

        case 63:
            sendNPCChat(Mood.SAD, "It's because I'm a dwarf, isn't it? Or because I'm a poet? I " +
                    "knew it! I'm completely worthless!");
            stageInt = 64;
            break;

        case 64:
            sendOptionsDialogue(DEFAULT, "No, she liked the ring.", "Would you be quiet for a moment?");
            stageInt = 65;
            break;

        case 65:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.HAPPY, "Oh! Then what's the problem?");
                    stageInt = 66;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.SAD, "Sorry!");
                    stageInt = 66;
                    break;
            }
            break;

        case 66:
            sendNPCChat(Mood.NORMAL, "Most cruel is fate! most cruel! Why not?");
            stageInt = 67;
            break;

        case 67:
            sendPlayerChat(Mood.NORMAL, "He's obsessed with the stories of his ancestors. he says " +
                    "his people are still at war.");
            stageInt = 68;
            break;

        case 68:
            sendNPCChat(Mood.WTF_FACE, "This village has stood for a hundred years!");
            stageInt = 69;
            break;

        case 69:
            sendPlayerChat(Mood.NORMAL, "I heard him arguing with one of the others. He says he " +
                    "honours his ancestors this way.");
            stageInt = 70;
            break;

        case 70:
            sendNPCChat(Mood.ASKING, "Really? interesting.");
            stageInt = 71;
            break;

        case 71:
            sendOptionsDialogue(DEFAULT, "Do you know a lot about the village's history?", "What are we going to do?");
            stageInt = 72;
            break;

        case 72:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, "Not really. I talked with Hunding, who guards this tower " +
                            "here.");
                    stageInt = 73;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, "An idea occurs to me, but it is hubris of the greatest " +
                            "magnitude.");
                    stageInt = 74;
                    break;
            }
            break;

        case 73:
            sendNPCChat(Mood.NORMAL, "An idea occurs to me, but it is hubris of the greatest " +
                    "magnitude.");
            stageInt = 74;
            break;

        case 74:
            sendPlayerChat(Mood.ASKING, "What is it?");
            stageInt = 75;
            break;

        case 75:
            sendNPCChat(Mood.ASKING, "What if I wrote a poem? Forged a sweeping, historical " +
                    "epic? Crafted a tale to touch the chieftain's soul?");
            stageInt = 76;
            break;

        case 76:
            sendPlayerChat(Mood.ASKING, "Will that work?");
            stageInt = 77;
            break;

        case 77:
            sendNPCChat(Mood.HAPPY, "To win the heart of my beloved from her father's iron " +
                    "grasp? It is worth it just to try!");
            player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(8);
            //Fade screen and start new dialogue //TODO
            stageInt = -2;
            break;

        case 78:
            sendNPCChat(Mood.NORMAL, "'Even the rolliest boulder...'");
            stageInt = 79;
            break;

        case 79:
            sendPlayerChat(Mood.ASKING, "how is the poem going?");
            stageInt = 80;
            break;

        case 80:
            sendNPCChat(Mood.SAD, "I'm stuck! I'm a worthless wordsmith! My work is pointless! " +
                    "My life is pointless!");
            stageInt = 81;
            break;

        case 81:
            sendOptionsDialogue(DEFAULT, "I'm sure that's not true.", "What's the problem?");
            stageInt = 82;
            break;

        case 82:
            switch (componentId) {
                case OPTION_1:
                case OPTION_2:
                    sendNPCChat(Mood.SAD, "I'm stuck on a word. By the colossus of King Alvis! I can't " +
                            "find the words!");
                    stageInt = 83;
                    break;
            }
            break;

        case 83:
            sendNPCChat(Mood.ASKING, "Maybe I can help. What sort of word?");
            stageInt = 84;
            break;

        case 84:
            sendNPCChat(Mood.SAD, "I don't know! i'm not some kind of word scientist! I just " +
                    "feel it out as I go.");
            stageInt = 85;
            break;

        case 85:
            sendNPCChat(Mood.ASKING, "Maybe you could suggest some words to get me started. " +
                    "Then I can tell you more.");
            stageInt = 86;
            break;

        case 86:
            sendPlayerChat(Mood.CONFUSED, "Alright, how about, uh...");
            stageInt = 87;
            break;

        case 87:
            sendOptionsDialogue(DEFAULT, "Cucumber.", "Monkey.", "Saradomin.", "Barbarian.");
            stageInt = 88;
            break;

        case 88:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 89;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 91;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 93;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 89;
                    break;
            }
            break;

        case 89:
            sendOptionsDialogue(DEFAULT, "Ham.", "Fey.", "Jaunt.", "Grass", "More words");
            stageInt = 90;
            break;

        case 90:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 93;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 91;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 93;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 93;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Roam.", "Fish.", "Stray.", "Hay.");
                    stageInt = 96;
                    break;
            }
            break;

        case 91:
            sendOptionsDialogue(DEFAULT, "Deviate.", "Roam.", "Veer.", "Traipse.", "More words");
            stageInt = 92;
            break;

        case 92:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 89;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 93;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 93;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 93;
                    break;
                case OPTION_5:
                    sendNPCChat(Mood.NORMAL, "Meander.", "Astray.", "Jaunt.", "Stray.", "More words");
                    stageInt = 97;
                    break;
            }
            break;

        case 93:
            sendOptionsDialogue(DEFAULT, "Lay.", "Beret.", "May.", "Hay.", "More words");
            stageInt = 94;
            break;

        case 94:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 91;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 89;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 91;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 91;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Stray.", "Dismay.", "Fey.", "More words");
                    stageInt = 95;
                    break;
            }
            break;

        case 95:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, "'And from his righteous purpose never <col=0000ff>stray.</col>'");
                    stageInt = 98;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 89;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 91;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 91;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Lay.", "Beret.", "May.", "Hay.", "More words");
                    stageInt = 94;
                    break;
            }
            break;

        case 96:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 93;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 93;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, "'And from his righteous purpose never <col=0000ff>stray.</col>'");
                    stageInt = 98;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 91;
                    break;
                case OPTION_5:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 90;
                    break;
            }
            break;

        case 97:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 89;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 89;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 93;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, "'And from his righteous purpose never <col=0000ff>stray.</col>'");
                    stageInt = 98;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Deviate.", "Roam.", "Veer.", "Traipse.", "More words");
                    stageInt = 92;
                    break;
            }
            break;

        case 98:
            sendNPCChat(Mood.HAPPY, "That fits! It fits perfectly. Right meaning, right length, " +
                    "right rhyme. Well done!");
            player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(9);
            stageInt = 99;
            break;

        case 99:
            sendNPCChat(Mood.HAPPY, "The poem still isn't finished, though. I have another " +
                    "missing world. GIve me another one; anything, to get me started.");
            stageInt = 100;
            break;

        case 100:
            sendOptionsDialogue(DEFAULT, "Stockade.", "Longsword.", "Dungeoneering.", "Grass.");
            stageInt = 101;
            break;

        case 101:
            switch (componentId) {
                case OPTION_1:
                sendNPCChat(Mood.NORMAL, secondRandomWord);
                stageInt = 102;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, secondRandomWord);
                    stageInt = 106;
                    break;
                case OPTION_3:
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, secondRandomWord);
                    stageInt = 104;
                    break;
            }
            break;

        case 102:
            sendOptionsDialogue(DEFAULT, "Storm.", "Wet.", "Hat.", "Length.", "More words");
            stageInt = 103;
            break;

        case 103:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 104;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 106;
                    break;
                case OPTION_3:
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 104;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Debt.", "Threat.", "Axe.", "Risk.", "More words");
                    stageInt = 109;
                    break;
            }
            break;

        case 104:
            sendOptionsDialogue(DEFAULT, "Debt.", "Sweat.", "Upset.", "Brunette.", "More words");
            stageInt = 105;
            break;

        case 105:
            switch (componentId) {
                case OPTION_1:
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 106;
                    break;
                case OPTION_3:
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 102;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Threat.", "Regret.", "Set.", "Wet.", "More words");
                    stageInt = 108;
                    break;
            }
            break;

        case 106:
            sendOptionsDialogue(DEFAULT, "Risk.", "Crisis.", "Peril.", "Menance.", "More words");
            stageInt = 107;
            break;

        case 107:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 104;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, "'But long is gone the author of that <col=0000ff>threat.</col>'");
                    stageInt = 111;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 102;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 104;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Upset.", "Storm.", "Hazard.", "Threat.", "More words");
                    stageInt = 110;
                    break;
            }
            break;

        case 108:
            switch (componentId) {
                case OPTION_1://TODO correct word. wlep forgot one.
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, secondRandomWord);
                    stageInt = 102;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, secondRandomWord);
                    stageInt = 106;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, secondRandomWord);
                    stageInt = 104;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Debt.", "Sweat.", "Upset.", "Brunette.", "More words");
                    stageInt = 105;
                    break;
            }
            break;

        case 109:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, secondRandomWord);
                    stageInt = 106;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, "'But long is gone the author of that <col=0000ff>threat.</col>'");
                    stageInt = 111;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 104;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, firstRandomWord);
                    stageInt = 106;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Storm.", "Wet.", "Hat.", "Length.", "More words");
                    stageInt = 103;
                    break;
            }
            break;

        case 110:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, secondRandomWord);
                    stageInt = 102;
                    break;
                case OPTION_2:
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, secondRandomWord);
                    stageInt = 104;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, "'But long is gone the author of that <col=0000ff>threat.</col>'");
                    stageInt = 111;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Risk.", "Crisis.", "Peril.", "Menance.", "More words");
                    stageInt = 107;
                    break;
            }
            break;

        case 111:
            sendNPCChat(Mood.HAPPY, "It's coming together. We're nearly done! One more to go!");
            player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(10);
            stageInt = 112;
            break;

        case 112:
            sendNPCChat(Mood.CONFUSED, "This one is tricky, though. It's a phrase I need. Someone " +
                    "did something.");
            stageInt = 113;
            break;

        case 113:
            sendOptionsDialogue(DEFAULT, "Threw the ball.", "Ate a tasty pie.", "Schemed intently.", "Went for a walk.");
            stageInt = 114;
            break;

        case 114:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 116;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 115;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 115;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 122;
                    break;
            }
            break;

        case 115:
            sendOptionsDialogue(DEFAULT, "Picked a flight.", "Started a war.", "Marched to battle.", "Settled the score.", "More words");
            stageInt = 119;
            break;

        case 116:
        sendOptionsDialogue(DEFAULT, "Started a war.", "Cleaned the floor.", "Loosed a mighty roar.", "Shut the door.", "More words");
            stageInt = 117;
        break;

        case 117:
        switch (componentId) {
            case OPTION_1:
                sendNPCChat(Mood.NORMAL, thirdRandomWord);
                stageInt = 122;
                break;
            case OPTION_2:
                sendNPCChat(Mood.NORMAL, thirdRandomWord);
                stageInt = 115;
                break;
            case OPTION_3:
                sendNPCChat(Mood.NORMAL, thirdRandomWord);
                stageInt = 116;
                break;
            case OPTION_4:
                sendNPCChat(Mood.NORMAL, thirdRandomWord);
                stageInt = 115;
                break;
            case OPTION_5:
                sendOptionsDialogue(DEFAULT, "Learned to soar.", "Settled the score.", "Swept to war.", "Counted to flour.", "More words");
                stageInt = 118;
                break;
        }
            break;

        case 118:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 116;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 122;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, "'Who then, in the face of madness, <col=0000f>swet tp wear.</col>'");
                    stageInt = 125;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 122;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Started a war.", "Cleaned the floor.", "Loosed a mighty roar.", "Shut the door.", "More words");
                    stageInt = 117;
                    break;
            }
            break;

        case 119:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 121;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 121;
                    break;
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 121;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 122;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Swept to war.", "Loosed a mighty roar.", "Initiated a battle.", "Commenced fisticuffs.", "More words");
                    stageInt = 120;
                    break;
            }
            break;

        case 120:
            switch (componentId) {
                case OPTION_1:
                    //TODO correct answer. end enother.
                    break;
                case OPTION_2:
                case OPTION_3:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 122;
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 122;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Picked a flight.", "Started a war.", "Marched to battle.", "Settled the score.", "More words");
                    stageInt = 118;
                    break;
            }
            break;

        case 121:
            sendOptionsDialogue(DEFAULT, "Started a war.", "Cleaned the floor.", "Loosed a mighty roar.", "Shut the door.", "More words");
            stageInt = 116;
            break;

        case 122:
            sendOptionsDialogue(DEFAULT, "Picked a rose.", "Made a raft.", "Learned to soar.", "Cleaned the floor.", "More words");
            stageInt = 123;
            break;

        case 123:
            switch (componentId) {
                case OPTION_1:
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 121;
                    break;
                case OPTION_3:
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 115;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Heard a song.", "Picked a flight.", "Swept to war.", "Tarned a shrew.", "More words");
                    stageInt = 124;
                    break;
            }
            break;

        case 124:
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 115;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 121;
                    break;
                case OPTION_3:
                    //TODO right answer end naother..
                    break;
                case OPTION_4:
                    sendNPCChat(Mood.NORMAL, thirdRandomWord);
                    stageInt = 121;
                    break;
                case OPTION_5:
                    sendOptionsDialogue(DEFAULT, "Picked a rose.", "Made a raft.", "Learned to soar.", "Cleaned the floor.", "More words");
                    stageInt = 123;
                    break;
            }
            break;

        case 125:
            sendNPCChat(Mood.NORMAL, "That's it! That's brilliant!");
            player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(11);
            stageInt = 126;
            break;

        case 126:
        sendNPCChat(Mood.NORMAL, "At last! It's done! It's finished! My finest work! Thank you " +
                "so much for your help!");
            stageInt = 127;
            break;

        case 127:
            sendPlayerChat(Mood.ASKING, "Are you ready to present it to the chieftain?");
            stageInt = 128;
            break;

        case 128:
            sendNPCChat(Mood.WTF_FACE, "What? No! I'm a writer, not a performer.");
            stageInt = 129;
            break;

        case 129:
            sendNPCChat(Mood.NORMAL, "I think the chieftain would respond best to one of his " +
                    "people. Perhaps you could ask Gudrun to recite it to her father?");
            stageInt = 130;
            break;

        case 130:
            sendItemDialogue(GunnarsGround.LOVE_POEM, "Dororan gives you the poem.");
            stageInt = 131;
            break;

        case 131:
            sendOptionsDialogue(DEFAULT, "I'll get right on it.", "This had better be the last time.");
            stageInt = 132;
            break;

        case 132:
            switch (componentId) {
                case OPTION_1:
                    sendPlayerChat(Mood.NORMAL, "I'll get right on it.");
                    stageInt = -2;
                    break;
                case OPTION_2:
                    sendPlayerChat(Mood.NORMAL, "This had better be the last time.");
                    stageInt = -2;
                    break;
            }
            break;

        case 133:
            sendOptionsDialogue(DEFAULT, "Everything will work out.", "I expect so.");
            stageInt = 134;
            break;

        case 134:
            switch (componentId) {
                case OPTION_1:
                    sendPlayerChat(Mood.NORMAL, "Everything will work out.");
                    stageInt = -2;
                    break;
                case OPTION_2:
                    sendPlayerChat(Mood.NORMAL, "I expect so.");
                    stageInt = -2;
                    break;
            }
            break;

        /**
         * Lost the Gunnars Ground
         */
        case 135:
            sendNPCChat(Mood.NORMAL, "Luckily for you, I wrote a second draft.");
            stageInt = 136;
            break;

        case 136:
            sendItemDialogue(GunnarsGround.GUNNARS_GROUND, "Dororan gives you another draft of the poem.");
            //TODO add emote for grabbing item.
            if(player.getInventory().getFreeSlots() > 0) {
                player.getInventory().addItem(GunnarsGround.GUNNARS_GROUND, 1);
            } else {
                World.addGroundItem(new Item(GunnarsGround.GUNNARS_GROUND), new WorldTile(player.getX(), player.getY(), player.getPlane()));
            }
            stageInt = 137;
            break;

        case 137:
            sendNPCChat(Mood.ANGRY, "Try not to lose this one.");
            stageInt = -2;
            break;

        case 138:
            sendOptionsDialogue(DEFAULT, "They're just starting.", "You're late.");
            stageInt = 139;
            break;

        case 139:
            switch (componentId) {
                case OPTION_1:
                case OPTION_2:
                    sendNPCChat(Mood.SAD, "This isn't going to work.");
                    stageInt = 140;
                    break;
            }
            break;

        case 140:
            sendOptionsDialogue(DEFAULT, "Why's that?", "You're so pessimistic.");
            stageInt = 141;
            break;

        case 141:
            switch (componentId) {
                case OPTION_1:
                case OPTION_2:
                    sendNPCChat(Mood.SAD, "What was I thinking? You should go in there and stop them " +
                            "before Gudrun makes a fool of herself.");
                    player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(12);
                    stageInt = 142;
                    break;
            }
            break;

        case 142:
            sendOptionsDialogue(DEFAULT, "Okay, I will.", "Don't be silly.");
            switch (componentId) {
                case OPTION_1:
                    sendNPCChat(Mood.SAD, "No! Wait, stay here, it's too late now, We'll just have to " +
                            "see how it turns out.");
                    stageInt = 143;
                    break;
                case OPTION_2:
                    sendNPCChat(Mood.SAD, "You're right, it's too late now. We'll just have to see how " +
                            "it turns out.");
                    stageInt = 143;
                    break;
            }
            break;

        case 143:
            sendNPCChat(Mood.ASKING, "I can't hear whats happening. Can you hear what's " +
                    "happening?");
            stageInt = 144;
            break;

        case 144:
            sendNPCChat(Mood.NORMAL, "Gunthor is laughing at something.");
            stageInt = 145;
            break;

        case 145:
            sendNPCChat(Mood.SAD, "He's probably considering the various tortures he has " +
                    "planned for me.");
            stageInt = 146;
            break;

        case 146:
            sendOptionsDialogue(DEFAULT, "Why would he do that?", "Now you're just being ridiculous.");
            stageInt = 147;
            break;

        case 147:
            switch (componentId) {
                case OPTION_1:
                case OPTION_2:
                    sendNPCChat(Mood.NORMAL, "The poem says you can honour your ancestors by setting " +
                            "peacefully on the land they conquered.");
                    stageInt = 148;
                    break;
            }
            break;

        case 148:
            sendNPCChat(Mood.SAD, "He'll probably just find it insulting");
            stageInt = 149;
            break;

        case 149:
            sendOptionsDialogue(DEFAULT, "Now's your chance to find out.", "You're doomed.");
            stageInt = 150;
            break;

        case 150:
            //TODO second cutscene..
            end();
            player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(13);
            break;

        case 151:
            sendNPCDialogue(GUNTHOR, ANGRY, "Freemen! Freemen! I have an announcement!");
            //TODO -> Bell ringing Emote. (TODO -> Chieftain)
            stageInt = 152;
            break;

        case 152:
            sendNPCDialogue(KJELL, ANGRY, "Hear the chieftan speak! Hear him!");
            stageInt = 153;
            break;

        case 153:
            sendNPCDialogue(GUNTHOR, ANGRY, "We have always borne the legacy of our ancestors, and we " +
                    "have borne it with honour!");
            stageInt = 154;
            break;

        case 154:
            sendNPCDialogue(KJELL, VERY_ANGRY_FACE, "FOR GUNNAR!");
            stageInt = 155;
            break;

        case 155:
            sendNPCDialogue(GUNTHOR, ANGRY, "And though we honoured them still, the time of our " +
                    "ancestors is past. This is the time of Gunthor!");
            stageInt = 156;
            break;

        case 156:
            sendNPCDialogue(HAAKON, ANGRY, "FOR GUNTHOR!");
            stageInt = 157;
            break;

        case 157:
            sendNPCDialogue(GUNTHOR, ANGRY, "Gunthor says: This is Gunnar's ground, bought with blood! " +
                    "Let it remain Gunnar's ground forever! here we settle!");
            stageInt = 158;
            break;

        case 158:
            sendNPCDialogue(GUNTHOR, ANGRY, "GUNNAR'S GROUND!");
            player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(14);
            stageInt = -2;
            //TODO ends 2nd cutscene, starts 3rd.
            break;



        case 161:
            sendNPCChat(Mood.SAD, "Um, that would be me. Hello.");
            stageInt = 162;
            break;

        case 162:
            sendNPCDialogue(GUDRUN, HAPPY, "That line about beauty was for me, wasn't it?");
            stageInt = 163;
            break;

        case 163:
            sendNPCChat(Mood.SAD, "Uh, yes.");
            stageInt = 164;
            break;

        case 164:
            sendNPCDialogue(GUDRUN, HAPPY, "You're the mystery poet who sent me the gold ring!");
            stageInt = 165;
            break;

        case 165:
            sendNPCChat(Mood.SAD, "Sorry.");
            stageInt = 166;
            break;

        case 166:
            sendNPCDialogue(GUDRUN, HAPPY, "I had no idea dwarves could be so romantic! Come here!");
            player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(15);
            //TODO - They hug and cutscene ends.
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
