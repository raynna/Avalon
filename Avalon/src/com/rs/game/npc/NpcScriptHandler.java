package com.rs.game.npc;

import com.rs.utils.Logger;
import com.rs.utils.Utils;

import java.util.HashMap;

public class NpcScriptHandler {

	public static final HashMap<Object, NpcScript> cachedNpcScripts = new HashMap<Object, NpcScript>();

	@SuppressWarnings("rawtypes")
	public static final void init() {
		try {
			Class[] classes = Utils.getClasses("com.rs.game.npc.scripts");
			for (Class c : classes) {
				if (c.isAnonymousClass())
					continue;
				Object o = c.newInstance();
				if (!(o instanceof NpcScript))
					continue;
				NpcScript script = (NpcScript) o;
				for (Object key : script.getKeys())
					cachedNpcScripts.put(key, script);
			}
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}
}
