package com.rs.game.player.content.quest.impl.naturespirit;

import java.io.Serializable;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.content.quest.Entry;
import com.rs.game.player.content.quest.Quest;
import com.rs.game.player.content.quest.QuestJournal;
import com.rs.game.player.content.quest.QuestReward;
import com.rs.game.player.content.quest.RewardType;

/**
 * @Author Austin
 * @Contact<deviouscoding@gmail.com;skype:modjames>
 */
@SuppressWarnings("serial")
public class NatureSpirit extends Quest implements Serializable {

	public NatureSpirit(Player player) {
        super(player);
    }

    @Override
    public String getQuestName() {
        return "Nature Spirit";
    }

    @Override
    public void declareDialogues() {

    }

    @Override
    public int getQuestPoints() {
        return 1;
    }

    @Override
    public QuestReward[] getRewards() {
        return new QuestReward[] { new QuestReward(RewardType.STRING, "Introduction to the Runecrafting skill and rune essence mine"), new QuestReward(RewardType.ITEM, new Item(2, 1)) };
    }

    private final QuestJournal journal = new QuestJournal() {
        @Override
        public QuestJournal display(int stage) {
            switch(stage) {
                case 0:
                    addLog(new Entry(
                            "I can start this quest by speaking to "+ keyWord("Drezel") + " in the temple"
                    ));
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
        return new int[] {307, 5, 110};
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
    public String getStart() {
        return "Speak to Duke Horacio, upstairs in Lumbridge Castle.";
    }
}
