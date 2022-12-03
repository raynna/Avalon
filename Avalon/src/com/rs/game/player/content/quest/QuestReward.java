package com.rs.game.player.content.quest;


import java.io.Serializable;

import com.rs.game.item.Item;
import com.rs.game.player.Player;

@SuppressWarnings("serial")
public class QuestReward implements Serializable {
	
	private final RewardType type;
	private final Object[] data;
	
	public QuestReward(RewardType type, int... data) {
		Object[] o = new Object[data.length];
		for (int i = 0; i < o.length; i++)
			o[i] = data[i];
		this.type = type;
		this.data = o;
	}
	
	public QuestReward(RewardType type, Item item) {
		Object[] o = { item };
		this.type = type;
		this.data = o;
	}
	
	public QuestReward(RewardType type, String item) {
		Object[] o = { item };
		this.type = type;
		this.data = o;
	}
	
	public QuestReward(RewardType type, Object[]... data) {
		this.type = type;
		this.data = data;
	}

	public void reward(Player player) {
		
	}
	
	public RewardType getType() {
		return type;
	}

	public Object[] getData() {
		return data;
	}

}
