package com.rs.game.player.content.quest;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.rs.game.player.Player;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.game.player.content.quest.impl.cooksassistant.CooksAssistant;
import com.rs.game.player.content.quest.impl.demonslayer.DemonSlayer;
import com.rs.game.player.content.quest.impl.doricsquest.DoricsQuest;
import com.rs.game.player.content.quest.impl.druidicritual.DruidicRitual;
import com.rs.game.player.content.quest.impl.goblindiplomacy.GoblinDiplomacy;
import com.rs.game.player.content.quest.impl.gunnarsground.GunnarsGround;
import com.rs.game.player.content.quest.impl.impcatcher.ImpCatcher;
import com.rs.game.player.content.quest.impl.piratestreasure.PiratesTreasure;
import com.rs.game.player.content.quest.impl.piratestreasure.interfaces.Scroll;
import com.rs.game.player.content.quest.impl.princealirescue.PrinceAliRescue;
import com.rs.game.player.content.quest.impl.restlessghost.RestlessGhost;
import com.rs.game.player.content.quest.impl.runemysteries.RuneMysteries;
import com.rs.game.player.content.quest.impl.vampireslayer.VampireSlayer;

public class QuestManager implements Serializable {

	private static final long serialVersionUID = -7686361750792708554L;
	private transient Player player;
	private transient Quest current;

	private int questPoints;
	private List<Quest> quests = new ArrayList<Quest>(Quests.values().length);

	public QuestManager(Player player) {
		this.setPlayer(player);
		init();
	}

	public void handleButton(int interfaceId, int componentId, int slotId) {
		switch(interfaceId) {
			case 1243:
				switch(componentId) {
					case 46:
						if (getCurrent() != null)
							getCurrent().acceptQuest();
						break;
					case 51:
						if (getCurrent() != null) {
							player.closeInterfaces();
							getCurrent().decline();
						}
						break;
			/*
			 * requirements
			 */
					case 26:
						if (getCurrent() != null)
							getCurrent().showRequirements();
						break;
			/*
			 * rewards
			 */
					case 38:
						if (getCurrent() != null)
							getCurrent().showRewards();
						break;
				}
				break;
			case 190:
				switch (componentId) {
					case 15:
						for (Quest q : quests) {
							if (q.getSlotId() == slotId) {
								q.openJournal();
							}
						}
				}
				break;
		}
	}

	public void startDialogue(int id, int i) {
		for (Quest q : quests) {
			if (q.getDialogues().get(id) != null) {
				player.getDialogueManager().startDialogue(q.getDialogues().get(id), i);
			}
		}
	}

	public void add(Quests quests, Quest quest) {
		getQuests().add(quests.index(), quest);
	}

	public Quest get(Quests key) {
		return getQuests().get(key.index());
	}

	public QuestManager init() {
		add(Quests.COOKS_ASSISTANT, new CooksAssistant(player));
		add(Quests.DEMON_SLAYER, new DemonSlayer(player));
		add(Quests.DRUIDIC_RITUAL, new DruidicRitual(player));
		add(Quests.IMP_CATCHER, new ImpCatcher(player));
		add(Quests.DORICS_QUEST, new DoricsQuest(player));
		add(Quests.RUNE_MYSTERIES, new RuneMysteries(player));
		add(Quests.THE_RESTLESS_GHOST, new RestlessGhost(player));
		add(Quests.GOBLIN_DIPLOMACY, new GoblinDiplomacy(player));
		add(Quests.PIRATES_TREASURE, new PiratesTreasure(player));
		add(Quests.PRINCE_ALI_RESCUE, new PrinceAliRescue(player));
        add(Quests.GUNNARS_GROUND, new GunnarsGround(player));
		add(Quests.VAMPIRE_SLAYER, new VampireSlayer(player));
		return this;
	}

	public QuestManager process() {
		return this;
	}

	public List<Quest> getQuests() {
		return quests;
	}

	public int getQuestPoints() {
		return questPoints;
	}

	public void setQuestPoints(int questPoints) {
		this.questPoints = questPoints;
	}

	public void update() {
		int totalQuestPoints = 333;
		player.getPackets().sendConfig(904, totalQuestPoints);
		player.getPackets().sendConfig(101, player.getQuestManager().getQuestPoints());
		/**
		 * Started
		 */
		if(player.getQuestManager().get(Quests.RUNE_MYSTERIES).getState() == QuestState.STARTED) {
			player.getPackets().sendConfig(player.getQuestManager().get(Quests.RUNE_MYSTERIES).getVarp()[0], player.getQuestManager().get(Quests.RUNE_MYSTERIES).getVarp()[1]);
		}
		if(player.getQuestManager().get(Quests.PIRATES_TREASURE).getState() == QuestState.STARTED) {
			player.getPackets().sendConfig(player.getQuestManager().get(Quests.PIRATES_TREASURE).getVarp()[0], player.getQuestManager().get(Quests.PIRATES_TREASURE).getVarp()[1]);
		}
		if(player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getState() == QuestState.STARTED) {
			player.getPackets().sendConfig(player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getVarp()[0], player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getVarp()[1]);
		}

		/**
		 * Finished
		 */
		if(player.getQuestManager().get(Quests.RUNE_MYSTERIES).getState() == QuestState.COMPLETED) {
			player.getPackets().sendConfig(player.getQuestManager().get(Quests.RUNE_MYSTERIES).getVarp()[0], player.getQuestManager().get(Quests.RUNE_MYSTERIES).getVarp()[2]);
		}
		if(player.getQuestManager().get(Quests.PIRATES_TREASURE).getState() == QuestState.COMPLETED) {
			player.getPackets().sendConfig(player.getQuestManager().get(Quests.PIRATES_TREASURE).getVarp()[0], player.getQuestManager().get(Quests.PIRATES_TREASURE).getVarp()[2]);
		}
		if(player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getState() == QuestState.COMPLETED) {
			player.getPackets().sendConfig(player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getVarp()[0], player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getVarp()[2]);
		}
	}


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Quest getCurrent() {
		return current;
	}

	public void setCurrent(Quest current) {
		this.current = current;
	}

	public boolean processButtonClick(int interfaceId, int componentId, int slotId, int itemId, int packetId) {
		if (interfaceId == 190 || interfaceId == 1243) {
			player.getQuestManager().handleButton(interfaceId, componentId, slotId);
		}
		if(itemId == PiratesTreasure.PIRATE_MESSAGE) {
			if(player.getQuestManager().get(Quests.PIRATES_TREASURE).getStage() > 7) {
				player.getQuestManager().get(Quests.PIRATES_TREASURE).setStage(9);
				Scroll.initalizeScroll(player);
			} else {
				player.message("Nothing interesting happens.");
			}
		}
		return false;
	}

}
