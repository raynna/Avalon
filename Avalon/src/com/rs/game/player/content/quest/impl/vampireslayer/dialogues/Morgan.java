package com.rs.game.player.content.quest.impl.vampireslayer.dialogues;

import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;

/**
 * @Author Frostbite
 * @Contact<frostbitersps@gmail.com;skype:frostbitersps>
 */
public class Morgan extends Dialogue {

    /**
     * TODO add npcIds
     */

	@Override
	public int getNPCID() {
		return 755;
	}
	
	
    @Override
    public void start() {
        stageInt = (Integer) parameters[0];
        @SuppressWarnings("unused")
		int progress = player.getQuestManager().get(Quests.VAMPIRE_SLAYER).getStage();
        QuestState state = player.getQuestManager().get(Quests.VAMPIRE_SLAYER).getState();
        switch (state) {
        case NOT_STARTED:
            sendNPCChat(Mood.UPSET_FACE, "Please please help us, bold adventurer!");
            stageInt = -1;
            break;

        case STARTED:
        	if(stageInt != -2) {
            sendNPCChat(Mood.ASKING, "How are you doing with the quest?");
            stageInt = 6;
        	}
            break;
	case COMPLETED:
		break;
	default:
		break;

    }
        switch (stageInt) {
                /**
                 * Accepted Quest.
                 */
            case -2:
                sendPlayerChat(Mood.HAPPY, "Ok, I'm up for an adventure.");
                stageInt = 3;
                break;

            /**
             * Declined Quest
             */
            case -3:
                sendNPCChat(Mood.SAD, "I don't blame you.");
                stageInt = -2;
                break;
        }
    }

    @Override
    public void run(int interfaceId, int componentId) {
            switch (stageInt) {
                case -1:
                    sendPlayerChat(Mood.ASKING, "What's the problem?");
                    stageInt = 1;
                    break;

                case 1:
                    sendNPCChat(Mood.SAD, "Our little village has been dreadfully ravaged by an evil " +
                            "vampire! He lives in the basement of the manor to the " +
                            "north, we need someone to get rid of him once and for " +
                            "all!");
                    stageInt = 2;
                    break;

                case 2:
                    end();
                    player.getQuestManager().get(Quests.VAMPIRE_SLAYER).sendStartOption();
                    break;

                case 3:
                    sendNPCChat(Mood.HAPPY, "I think first you should seek help. I have a friend who " +
                            "is a retired vampire hunter, his name is Dr. Harlow. He " +
                            "may be bale to give you some tips. He can normally be " +
                            "found in the Blue Moon Inn in Varrock, he's a bit of ");
                    stageInt = 4;
                    break;

                case 4:
                    sendNPCChat(Mood.NORMAL, "an old soak these days. mention his old friend Morgan, " +
                            "I'm sure he wouldn't want me killed by a vampire.");
                    stageInt = 5;
                    break;

                case 5:
                    sendPlayerChat(Mood.NORMAL, "I'll look him up then.");
                    stageInt = -2;
                    break;

                case 6:
                    sendPlayerChat(Mood.NORMAL, "I'm still working on it.");
                    stageInt = 7;
                    break;

                case 7:
                    sendNPCChat(Mood.SAD, "Please hurry! Every day we live in fear that we will be " +
                            "the vampire's next victim!");
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
