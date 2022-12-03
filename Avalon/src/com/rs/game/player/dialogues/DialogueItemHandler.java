package com.rs.game.player.dialogues;

import java.util.HashMap;

import com.rs.utils.Logger;
import com.rs.utils.Utils;

public final class DialogueItemHandler {

	private static final HashMap<Object, Class<? extends Dialogue>> handledDialogues = new HashMap<Object, Class<? extends Dialogue>>();

	@SuppressWarnings(
	{ "unchecked" })
	public static final void init() {
		try {
			Class<Dialogue>[] classes = Utils.getClasses("com.rs.game.player.dialogues.item");
			for (Class<Dialogue> c : classes) {
				if (c.isAnonymousClass())
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}

	public static final void reload() {
		handledDialogues.clear();
		init();
	}

	public static final Dialogue getDialogue(Object key) {
		if (key instanceof Dialogue)
			return (Dialogue) key;
		Class<? extends Dialogue> classD = handledDialogues.get(key);
		if (classD == null)
			return null;
		try {
			return classD.newInstance();
		} catch (Throwable e) {
			Logger.handle(e);
		}
		return null;
	}

	private DialogueItemHandler() {

	}
}
