package com.rs.game.player.dialogues;

import java.util.HashMap;

import com.rs.utils.Logger;
import com.rs.utils.Utils;

public final class DialogueHandler {

	private static final HashMap<Object, Class<? extends Dialogue>> handledDialogues = new HashMap<Object, Class<? extends Dialogue>>();

	@SuppressWarnings(
	{ "unchecked" })
	public static final void init() {
		try {
			Class<Dialogue>[] regular = Utils.getClasses("com.rs.game.player.dialogues");
			for (Class<Dialogue> c : regular) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			Class<Dialogue>[] item = Utils.getClasses("com.rs.game.player.dialogues.item");
			for (Class<Dialogue> c : item) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			Class<Dialogue>[] npc = Utils.getClasses("com.rs.game.player.dialogues.npcs");
			for (Class<Dialogue> c : npc) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			Class<Dialogue>[] player = Utils.getClasses("com.rs.game.player.dialogues.player");
			for (Class<Dialogue> c : player) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			Class<Dialogue>[] skilling = Utils.getClasses("com.rs.game.player.dialogues.skilling");
			for (Class<Dialogue> c : skilling) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			Class<Dialogue>[] dung = Utils.getClasses("com.rs.game.player.dialogues.dungeoneering");
			for (Class<Dialogue> c : dung) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			@SuppressWarnings("unused")
			Class<Dialogue>[] workers = Utils.
					getClasses("com.rs.game.player.actions.priffidinas.workers");
			
			Class<Dialogue>[] cooksassistant = Utils
					.getClasses("com.rs.game.player.content.quest.impl.cooksassistant.dialogues");
	
			Class<Dialogue>[] miscellania = Utils
					.getClasses("com.rs.game.player.content.jobs.impl.miscellania.dialogues");

			Class<Dialogue>[] druidicritual = Utils
					.getClasses("com.rs.game.player.content.quest.impl.druidicritual.dialogues");

			Class<Dialogue>[] doricsquest = Utils
					.getClasses("com.rs.game.player.content.quest.impl.doricsquest.dialogues");
			
			Class<Dialogue>[] restlessghost = Utils
					.getClasses("com.rs.game.player.content.quest.impl.restlessghost.dialogues");

			Class<Dialogue>[] impcatcher = Utils
					.getClasses("com.rs.game.player.content.quest.impl.impcatcher.dialogues");

			Class<Dialogue>[] runemysteries = Utils
					.getClasses("com.rs.game.player.content.quest.impl.runemysteries.dialogues");
			Class<Dialogue>[] demonslayer = Utils
					.getClasses("com.rs.game.player.content.quest.impl.demonslayer.dialogues");
			for (Class<Dialogue> c : demonslayer) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			for (Class<Dialogue> c : cooksassistant) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			for (Class<Dialogue> c : miscellania) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			for (Class<Dialogue> c : druidicritual) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			for (Class<Dialogue> c : doricsquest) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			for (Class<Dialogue> c : restlessghost) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			for (Class<Dialogue> c : impcatcher) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			for (Class<Dialogue> c : runemysteries) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			Class<Dialogue>[] goblindiplomacy = Utils.getClasses("com.rs.game.player.content.quest.impl.goblindiplomacy.dialogues");
			for (Class<Dialogue> c : goblindiplomacy) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			Class<Dialogue>[] piratestreasure = Utils.getClasses("com.rs.game.player.content.quest.impl.piratestreasure.dialogues");
			for (Class<Dialogue> c : piratestreasure) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			Class<Dialogue>[] princealiresuce = Utils.getClasses("com.rs.game.player.content.quest.impl.princealirescue.dialogues");
			for (Class<Dialogue> c : princealiresuce) {
				if (c.isAnonymousClass()) // next
					continue;
				handledDialogues.put(c.getSimpleName(), c);
			}
			Class<Dialogue>[] vampireslayer = Utils.getClasses("com.rs.game.player.content.quest.impl.vampireslayer.dialogues");
			for (Class<Dialogue> c : vampireslayer) {
				if (c.isAnonymousClass()) // next
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

	private DialogueHandler() {

	}
}
