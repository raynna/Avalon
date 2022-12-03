package com.rs.game.player.content.quest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class State implements Serializable {
	
	private static final long serialVersionUID = 3379392276829314476L;

	public enum QuestState {
	
	NOT_STARTED(0), STARTED(1), COMPLETED(2);

    private int state;

    private static Map<Integer, QuestState> map = new HashMap<Integer, QuestState>();

    static {
        for (QuestState stateEnum : QuestState.values()) {
            map.put(stateEnum.state, stateEnum);
        }
    }

    private QuestState(final int value) { 
    	state = value; 
    }

    public static QuestState valueOf(int value) {
        return map.get(value);
    }
	}

}
