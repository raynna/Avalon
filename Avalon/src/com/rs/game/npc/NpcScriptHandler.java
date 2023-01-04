package com.rs.game.npc;

import com.rs.game.WorldObject;
import com.rs.game.npc.NpcScript;
import com.rs.utils.Logger;
import com.rs.utils.Utils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NpcScriptHandler {

	public static final HashMap<Object, NpcScript> cachedNpcScripts = new HashMap<Object, NpcScript>();

	public static NpcScript getScript(NPC npc) {
		NpcScript script = cachedNpcScripts.getOrDefault(npc.getId(), cachedNpcScripts.get(npc.getName()));
		if (script != null) {
			System.out.println("[NpcScriptHandler] "+npc.getName()+"("+npc.getId()+"): script was found by Id.");
			return script;
		}
		if (script == null) {
			for (Map.Entry<Object, NpcScript> entry : cachedNpcScripts.entrySet()) {
				Object[] keys = entry.getValue().getKeys();
				for (Object key : keys) {
					if (key instanceof String && npc.getName().toLowerCase().contains(((String) key).toLowerCase())) {
						script = entry.getValue();
						System.out.println("[NpcScriptHandler] "+npc.getName()+"("+npc.getId()+"): Found script by name");
						return script;
					}
				}
			}
		}
		System.out.println("[NpcScriptHandler] "+npc.getName()+"("+npc.getId()+"): Found no script for this npc.");
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static final void init() {
		try {
			String[] scriptFolders = {"com.rs.game.npc.scripts"};
			Set<Class> processedClasses = new HashSet<>();
			for (String scriptFolder : scriptFolders) {
				Class[] classes = Utils.getClasses(scriptFolder);
				for (Class c : classes) {
					if (c.isAnonymousClass() || processedClasses.contains(c))
						continue;
					Object o = c.newInstance();
					if (!(o instanceof NpcScript))
						continue;
					NpcScript script = (NpcScript) o;
					for (Object key : script.getKeys())
						cachedNpcScripts.put(key, script);
					processedClasses.add(c);
				}
			}
			System.out.println("[NpcScriptHandler]: " + processedClasses.size() + " scripts were loaded.");
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}
}
