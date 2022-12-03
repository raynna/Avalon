package com.rs.game.player.content.quest.impl.gunnarsground.dialogues;

import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;

/**
 * @Author Frostbite
 * @Contact<frostbitersps@gmail.com;skype:frostbitersps>
 */
public class ChieftainGunthor extends Dialogue {
    /**
     * TODO add npcIds
     */

    int HAAKON = 23343;

    @Override
    public void start() {
        int progress = player.getQuestManager().get(Quests.GUNNARS_GROUND).getStage();
        if(progress > 5) {
            sendNPCChat(Mood.ANGRY, "Run back to Gudrun and tell her to remember her " +
                    "forefathers!");
            stage = 25;
        } else if(progress < 5) {
            sendNPCChat(Mood.ANGRY, "Begone, outlander! Your kind are not welcome here!");
            stage = -1;
        } else if(progress > 4) {
            sendNPCChat(Mood.ANGRY, "Begone, outlander! Your kind are not welcome here!");
            stage = 1;
        }
    }

    @Override
    public void run(int interfaceId, int componentId) {
        switch(stage) {
            case -1:
                end();
                break;

            case 1:
                sendOptionsDialogue(DEFAULT, "I need to speak with you, chieftain.", "Be quite and listen.");
                stage = 2;
                break;

            case 2:
                switch (componentId) {
                    case OPTION_1:
                    case OPTION_2:
                        sendNPCChat(Mood.ANGRY, "Make it short.");
                        stage = 3;
                        break;
                      }
                    break;

                    case 3:
                        sendNPCChat(Mood.NORMAL, "Your daughter seeks permission to court an outlander.");
                        stage = 4;
                        break;

                    case 4:
                        sendNPCChat(Mood.ANGRY_YELLING, "WHAT?");
                        stage = 5;
                        break;

                    case 5:
                        sendOptionsDialogue(DEFAULT, "Your daughter seeks permission to court an outlander.", "Are you deaf?");
                        stage = 6;
                        break;

                    case 6:
                        switch (componentId) {
                            case OPTION_1:
                            case OPTION_2:
                                sendNPCChat(Mood.ANGRY, "Do you have ANY idea who we are?");
                                stage = 7;
                                break;
                        }
                        break;

                    case 7:
                        sendOptionsDialogue(DEFAULT, "You're barbarians.", "You're a tribe of primitives.");
                        stage = 8;
                        break;

                    case 8:
                        switch (componentId) {
                            case OPTION_1:
                            case OPTION_2:
                                sendNPCChat(Mood.ANGRY, "We are the storm that sweeps from the mountains! We " +
                                        "are the scourage of these soft lands!");
                                stage = 9;
                                break;
                        }
                        break;

                    case 9:
                        sendOptionsDialogue(DEFAULT, "Please wait a moment.", "Are you finished?");
                        stage = 10;
                        break;

                    case 10:
                        switch (componentId){
                            case OPTION_1:
                            case OPTION_2:
                                sendNPCChat(Mood.ANGRY, "We are the freemen of the ice. YOu think this is a " +
                                        "settlement, but it is a camp of war!");
                                stage = 11;
                                break;
                        }
                        break;

                    case 11:
                        sendNPCDialogue(HAAKON, ASKING, "Chieftain! May I interrupt?");
                        stage = 12;
                        break;

            case 12:
                sendNPCChat(Mood.ANGRY, "What is it, Haakon?");
                stage = 13;
                break;

            case 13:
                sendNPCDialogue(HAAKON, NORMAL, "We have lived here since before the time of my father. " +
                        "Perhaps we are no longer a camp.");
                stage = 14;
                break;

            case 14:
                sendNPCChat(Mood.ASKING, "Your father? Do you honour him, Haakon?");
                stage = 15;
                break;

            case 15:
                sendNPCDialogue(HAAKON, ANGRY, "Of course!");
                stage = 16;
                break;

            case 16:
                sendNPCChat(Mood.ASKING, "And do you honour Warlord Gunnar?");
                stage = 17;
                break;

            case 17:
                sendNPCDialogue(HAAKON, ANGRY, "Of course, Chieftain!");
                stage = 18;
                break;

            case 18:
                sendNPCChat(Mood.ANGRY, "Then why do you dishonour his name by abandoning what " +
                        "we fought for?");
                stage = 19;
                break;

            case 19:
                sendNPCChat(Mood.ANGRY, "We will honour our fathers, and we will honour Gunnar!");
                stage = 20;
                break;

            case 20:
                sendNPCDialogue(HAAKON, NORMAL, "Yes, Chieftain. You are wise. I am sorry.");
                stage = 21;
                break;

            case 21:
                sendNPCChat(Mood.ANGRY_YELLING, "You! Outlander!");
                stage = 22;
                break;

            case 22:
                sendPlayerChat(Mood.ASKING, "What?");
                stage = 23;
                break;

            case 23:
                sendNPCChat(Mood.ANGRY_YELLING, "We are not friends, you and I! We are not allies!");
                stage = 24;
                break;

            case 24:
                sendNPCChat(Mood.ANGRY, "Run back to Gudrun and tell her to remember her " +
                        "forefathers!");
                stage = 25;
                break;

            case 25:
                sendNPCChat(Mood.ANGRY, "Tell her to think of Gunnar and what he would think of " +
                        "this insult! Now go, before I have Haakon dismember you.");
                player.getQuestManager().get(Quests.GUNNARS_GROUND).setStage(6);
                stage = 26;
                break;

            case 26:
                sendOptionsDialogue(DEFAULT, "I'm going!", "I'd like to see him try.", "I'm going to challenge him right now!");
                stage = 27;
                break;

            case 27:
                switch (componentId) {
                    case OPTION_1:
                        sendPlayerChat(Mood.NORMAL, "I'm going!");
                        stage = -2;
                        break;
                    case OPTION_2:
                        sendNPCDialogue(HAAKON, ANGRY, "Come here and say that to my face, outlander!");
                        stage = -2;
                        break;
                    case OPTION_3:
                        sendNPCDialogue(HAAKON, ANGRY, "Come here and say that to my face, outlander!");
                        stage = -2;
                        break;
                }
                break;

        }
    }

    @Override
    public void finish() {

    }
}
