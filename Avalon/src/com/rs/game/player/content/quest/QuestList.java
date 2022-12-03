package com.rs.game.player.content.quest;

import java.io.Serializable;

public class QuestList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5294311181034701877L;

	public enum Quests {
	
	COOKS_ASSISTANT(0),
	DEMON_SLAYER(1),
	DRUIDIC_RITUAL(2),
	IMP_CATCHER(3),
	DORICS_QUEST(4),
	RUNE_MYSTERIES(5),
	THE_RESTLESS_GHOST(6),
	GOBLIN_DIPLOMACY(7),
	PIRATES_TREASURE(8),
	PRINCE_ALI_RESCUE(9),
	GUNNARS_GROUND(10),
	VAMPIRE_SLAYER(11);

	;

	private int index;
	

	/**
	 * Quests parameter index
	 * @param index
	 */
	private Quests(int index) {
		this.index = index;
	}

	/**
	 * Gets the index of the quest.
	 * @return index
	 */
	public int index() {
		return index;
	}


	/**
	 * Returns the quest value
	 * @param id
	 * @return quest
	 */
	public static Quests get(int id) {

		for (Quests quest : Quests.values()) {
			if (quest.index() == id)
				return quest;
		}

		return null;
	}
	}

}
