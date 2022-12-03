package com.rs.game.player.content.customtab;

import com.rs.game.player.Player;
import com.rs.game.player.content.quest.Quest;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.utils.HexColours;
import com.rs.utils.HexColours.Colours;

public class QuestTab extends CustomTab {
	
	/**
	 * @author Andreas
	 *
	 */

	public enum QuestStore {
		
		TITLE(24, "Quests"),
		POINTS(25, "Points"),
		COOKSASSISTANT(3, Quests.COOKS_ASSISTANT),
		DEMONSLAYER(4, Quests.DEMON_SLAYER),
		DORICSQUEST(5, Quests.DORICS_QUEST),
		DRUIDIC_RITUAL(6, Quests.DRUIDIC_RITUAL),
		GOBLIN_DIPLOMACY(7, Quests.GOBLIN_DIPLOMACY),
		GUNNARS_GROUND(8, Quests.GUNNARS_GROUND),
		IMP_CATCHER(9, Quests.IMP_CATCHER),
		PIRATES_TREASURE(10, Quests.PIRATES_TREASURE),
		PRINCE_ALI_RESCUE(11, Quests.PRINCE_ALI_RESCUE),
		RESTLESS_GHOST(12, Quests.THE_RESTLESS_GHOST),
		RUNE_MYSTERIES(13, Quests.RUNE_MYSTERIES),
		VAMPIRE_SLAYER(14, Quests.VAMPIRE_SLAYER),
		;
		

		private int compId;
		private Quests quest;
		private Colours state;
		private String text;

		private QuestStore(int compId, Quests quest) {
			this.compId = compId;
			this.setQuest(quest);
		}
		
		private QuestStore(int compId, String text) {
			this.compId = compId;
			this.text = text;
		}
		
		public void usage(Player p) {
		    p.getQuestManager().get(getQuest(p)).openJournal();
		}
		
		public String text(Player p) {
			if (text != null) {
				if (text.toLowerCase().contains("points"))
					return "Quest Points: " + p.getQuestManager().getQuestPoints();
				return text;
			}
		    return HexColours.getMessage(getState(p, quest), getQuestInfo(p).getQuestName());
		}

		public Quests getQuest(Player player) {
			this.setState(state);
			return quest;
		}
		
		public Quest getQuestInfo(Player player) {
			return player.getQuestManager().get(quest);
		}

		public void setQuest(Quests quest) {
			this.quest = quest;
		}

		public Colours getState(Player p, Quests quest) {
			return p.getQuestManager().get(quest).getState() == QuestState.COMPLETED ? Colours.GREEN : p.getQuestManager().get(quest).getState() == QuestState.STARTED ? Colours.YELLOW : Colours.RED;
		}

		public void setState(Colours state) {
			this.state = state;
		}

	}

	public static void open(Player player) {
		sendComponents(player);
		for (int i = 3; i <= 22; i++)
			player.getPackets().sendHideIComponent(3002, i, true);
		for (int i = 28; i <= 56; i++)
			player.getPackets().sendHideIComponent(3002, i, true);
		player.getTemporaryAttributtes().put("CUSTOMTAB", 4);
		player.getPackets().sendHideIComponent(3002, BACK_BUTTON, false);
		player.getPackets().sendHideIComponent(3002, FORWARD_BUTTON, false);
		player.getPackets().sendSpriteOnIComponent(3002, PURPLE_STAR_COMP, PURPLE_HIGHLIGHTED);
		for (QuestStore store : QuestStore.values()) {
			if (store != null) {
				player.getPackets().sendHideIComponent(3002, store.compId, false);
				if (store.text(player) != null)
					player.getPackets().sendIComponentText(3002, store.compId, store.text(player));
			}
		}
	}

	public static void handleButtons(Player player, int compId) {
		for (QuestStore store : QuestStore.values()) {
			if (store != null) {
				if (compId != store.compId)
					continue;
				store.usage(player);
				//open(player);
			}
		}
		switch (compId) {
		case FORWARD_BUTTON:
			GearTab.open(player, null);
			break;
		case BACK_BUTTON:
			SettingsTab.open(player);
			break;
		default:
			break;
		}
	}
}
