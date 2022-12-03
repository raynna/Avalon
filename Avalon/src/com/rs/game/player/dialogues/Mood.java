package com.rs.game.player.dialogues;

public enum Mood {
		
		QUESTIONING(9827),
		SAD(Dialogue.SAD_FACE),
		ASKING(Dialogue.ASKING_FACE),
		HAPPY(Dialogue.HAPPY_FACE),
		SLIGHTLY_ANGRY(Dialogue.MILDLY_ANGRY_FACE),
		SARCASTIC(9831),
		SUSPICIOUS(9836),
		LAUGHING(9840),
		LAUGHING_HYSTERICALLY(9841),
		EVIL_LAUGH(9742),
		NORMAL(9843),
		PLAIN_TALKING(9808),
		CHEERFULLY_TALK(9848),
		HYSTERICAL_LAUGH_TALK(9854),
		GOOFY_FACE(9878),
		UPSET_FACE(9771),
		SCARED_FACE(9773),
		VERY_SCARED(9778),
		MAD(9781),
		ANGRY(9788),
		ANGRY_YELLING(9789),
		CONFUSED(9830),
		MILDLY_ANGRY(9784),
		DRUNK(9835),
		WTF_FACE(9820),
		;
		
		private int index;
		
		Mood(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

	}