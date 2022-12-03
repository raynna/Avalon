package com.rs.game.player.content.quest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public abstract class QuestJournal implements Serializable {

	public abstract QuestJournal display(int stage);
	
	private final List<Entry> ENTRIES = new ArrayList<Entry>();
	
	public void addLog(Entry... entry) {
		for (Entry e : entry)
			ENTRIES.add(e);
	}
	
	public String keyWord(String text) {
		return "<col=8A0808>" + text + "</col><col=08088A>";
	}
	
	public List<Entry> getEntries() {
		return ENTRIES;
	}
	
}
