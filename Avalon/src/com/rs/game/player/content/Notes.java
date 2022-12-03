package com.rs.game.player.content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.rs.game.player.Player;

public final class Notes implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5564620907978487391L;

	/** The notes. */
	private List<Note> notes;

	/** The player. */
	private transient Player player;

	/**
	 * Instantiates a new notes.
	 */
	public Notes() {
		notes = new ArrayList<Note>(30);
	}

	/**
	 * Sets the player.
	 *
	 * @param player
	 *            the new player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Inits the.
	 */
	public void init() {
		player.getPackets().sendIComponentSettings(34, 9, 0, 30, 2621470);
		player.getPackets().sendHideIComponent(34, 3, false);
		player.getPackets().sendHideIComponent(34, 44, false);
		player.getVarsManager().sendVar(1437, 1); // unlocks add notes
		player.getVarsManager().sendVar(1439, -1);
		refresh();
	}

	/**
	 * Refresh.
	 */
	private void refresh() {
		for (int i = 0; i < 30; i++)
			player.getPackets().sendGlobalString(149 + i, notes.size() <= i ? "" : notes.get(i).text);
		player.getVarsManager().sendVar(1440, getPrimaryColour(this));
		player.getVarsManager().sendVar(1441, getSecondaryColour(this));
	}

	/**
	 * Gets the current note.
	 *
	 * @return the current note
	 */
	public int getCurrentNote() {
		Integer note = (Integer) player.temporaryAttribute().get("CURRENT_NOTE");
		if (note == null)
			return -1;
		return note;
	}

	/**
	 * Sets the current note.
	 *
	 * @param id
	 *            the new current note
	 */
	public void setCurrentNote(int id) {
		if (id >= 30)
			return;
		player.temporaryAttribute().put("CURRENT_NOTE", id);
		player.getVarsManager().sendVar(1439, id);
	}

	/**
	 * Removes the current note.
	 */
	public void removeCurrentNote() {
		player.temporaryAttribute().remove("CURRENT_NOTE");
		player.getVarsManager().sendVar(1439, -1);
	}

	/**
	 * Adds the.
	 *
	 * @param text
	 *            the text
	 * @return true, if successful
	 */
	public boolean add(String text) {
		if (notes.size() >= 30) {
			player.getPackets().sendGameMessage("You may only have 30 notes!");
			return false;
		}
		if (text.length() > 50) {
			player.getPackets().sendGameMessage("You can only enter notes up to 50 characters!");
			return false;
		}
		player.getPackets().sendGlobalString(149 + notes.size(), text);
		setCurrentNote(notes.size());
		return notes.add(new Note(text));
	}

	/**
	 * Edits the.
	 *
	 * @param text
	 *            the text
	 * @return true, if successful
	 */
	public boolean edit(String text) {
		if (text.length() > 50) {
			player.getPackets().sendGameMessage("You can only enter notes up to 50 characters!");
			return false;
		}
		int id = getCurrentNote();
		if (id == -1 || notes.size() <= id)
			return false;
		notes.get(id).setText(text);
		player.getPackets().sendGlobalString(149 + id, text);
		return true;
	}

	/**
	 * Colour.
	 *
	 * @param colour
	 *            the colour
	 * @return true, if successful
	 */
	public boolean colour(int colour) {
		int id = getCurrentNote();
		if (id == -1 || notes.size() <= id)
			return false;
		notes.get(id).setColour(colour);
		if (id < 16)
			player.getVarsManager().sendVar(1440, getPrimaryColour(this));
		else
			player.getVarsManager().sendVar(1441, getSecondaryColour(this));
		return true;
	}

	/**
	 * Switch notes.
	 *
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 */
	public void switchNotes(int from, int to) {
		if (notes.size() <= from || notes.size() <= to) {
			notes.set(from, notes.get(from));
			refresh();
			return;
		}
		notes.set(to, notes.set(from, notes.get(to)));
		refresh();
	}

	/**
	 * Delete.
	 */
	public void delete() {
		delete(getCurrentNote());
	}

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	public void delete(int id) {
		if (id == -1 || notes.size() <= id)
			return;
		notes.remove(id);
		removeCurrentNote();
		refresh();
	}

	/**
	 * Delete all.
	 */
	public void deleteAll() {
		notes.clear();
		removeCurrentNote();
		refresh();
	}

	/**
	 * Gets the primary colour.
	 *
	 * @param notes
	 *            the notes
	 * @return the primary colour
	 */
	public static int getPrimaryColour(Notes notes) {
		int color = 0;
		for (int i = 0; i < 16; i++) {
			if (notes.notes.size() <= i)
				break;
			color += colourize(notes.notes.get(i).colour, i);
		}
		return color;
	}

	/**
	 * Gets the secondary colour.
	 *
	 * @param notes
	 *            the notes
	 * @return the secondary colour
	 */
	public static int getSecondaryColour(Notes notes) {
		int color = 0;
		for (int i = 0; i < 14; i++) {
			if (notes.notes.size() - 16 <= i)
				break;
			color += colourize(notes.notes.get(i + 16).colour, i);
		}
		return color;
	}

	/**
	 * Colourize.
	 *
	 * @param colour
	 *            the colour
	 * @param noteId
	 *            the note id
	 * @return the int
	 */
	public static int colourize(int colour, int noteId) {
		return (int) (Math.pow(4, noteId) * colour);
	}

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public List<Note> getNotes() {
		return notes;
	}

	/**
	 * The Class Note.
	 */
	public static final class Note implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -3867862135920019512L;

		/** The text. */
		private String text;

		/** The colour. */
		private int colour;

		/**
		 * Instantiates a new note.
		 *
		 * @param text
		 *            the text
		 */
		public Note(String text) {
			this.text = text;
		}

		/**
		 * Gets the text.
		 *
		 * @return the text
		 */
		public String getText() {
			return text;
		}

		/**
		 * Gets the colour.
		 *
		 * @return the colour
		 */
		public int getColour() {
			return colour;
		}

		/**
		 * Sets the text.
		 *
		 * @param text
		 *            the new text
		 */
		public void setText(String text) {
			this.text = text;
		}

		/**
		 * Sets the colour.
		 *
		 * @param colour
		 *            the new colour
		 */
		public void setColour(int colour) {
			this.colour = colour;
		}
	}
}
