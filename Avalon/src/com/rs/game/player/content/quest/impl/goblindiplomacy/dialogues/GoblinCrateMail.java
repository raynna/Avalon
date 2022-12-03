package com.rs.game.player.content.quest.impl.goblindiplomacy.dialogues;

import com.rs.game.player.dialogues.Dialogue;

/**
 * @Author arrow
 * @Contact<arrowrsps@gmail.com;skype:arrowrsps>
 */
public class GoblinCrateMail extends Dialogue {
    @Override
    public void start() {
        sendHandedItem(288, "You take the goblin mail from the crate.");
    }

    @Override
    public void run(int interfaceId, int componentId) {
    end();
    }

    @Override
    public void finish() {

    }
}
