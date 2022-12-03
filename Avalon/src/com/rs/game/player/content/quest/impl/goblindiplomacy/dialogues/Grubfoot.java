package com.rs.game.player.content.quest.impl.goblindiplomacy.dialogues;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;

/**
 * @Author arrow
 * @Contact<arrowrsps@gmail.com;skype:arrowrsps>
 */
public class Grubfoot extends Dialogue {

    public static final int BENTNOZE = 4493;

    @Override
    public int getNPCID() {
        return 4495;
    }

    @Override
    public void start() {
        stageInt = (Integer) parameters[0];
        switch(stageInt) {
            case 0:
                sendNPCChat(Mood.ANGRY, "Grubfoot wear red armour! Grubfoot wear green " +
                        "armour!");
                stageInt = -1;
                break;
        }
    }

    @Override
    public void run(int interfaceId, int componentId) {
        switch(stageInt) {
            case -1:
                sendNPCChat(Mood.ASKING, "Why they not make up their minds?");
                stageInt = 1;
                break;

            case 1:
                sendNPCDialogue(BENTNOZE, VERY_ANGRY_FACE, "Shut up Grubfoot!");
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
