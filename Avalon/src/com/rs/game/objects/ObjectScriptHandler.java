package com.rs.game.objects;

import com.rs.game.WorldObject;
import com.rs.utils.Logger;
import com.rs.utils.Utils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ObjectScriptHandler {

	public static final HashMap<Object, ObjectScript> cachedObjectScripts = new HashMap<Object, ObjectScript>();

	public static ObjectScript getScript(WorldObject object) {
		ObjectScript script = cachedObjectScripts.getOrDefault(object.getId(), cachedObjectScripts.get(object.getName()));
		if (script != null) {
			System.out.println("[ObjectScriptHandler] "+object.getName()+"("+object.getId()+"): script was found by Id.");
			return script;
		}
		if (script == null) {
			for (Map.Entry<Object, ObjectScript> entry : cachedObjectScripts.entrySet()) {
				Object[] keys = entry.getValue().getKeys();
				for (Object key : keys) {
					if (key instanceof String && object.getName().toLowerCase().contains(((String) key).toLowerCase())) {
						script = entry.getValue();
						System.out.println("[ObjectScriptHandler] "+object.getName()+"("+object.getId()+"): Found script by name");
						return script;
					}
				}
			}
		}
		System.out.println("[ObjectScriptHandler] "+object.getName()+"("+object.getId()+"): Found no script for this object.");
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static final void init() {
		try {
			String[] scriptFolders = {"com.rs.game.objects.scripts"};
			Set<Class> processedClasses = new HashSet<>();
			for (String scriptFolder : scriptFolders) {
				Class[] classes = Utils.getClasses(scriptFolder);
				for (Class c : classes) {
					if (c.isAnonymousClass() || processedClasses.contains(c))
						continue;
					Object o = c.newInstance();
					if (!(o instanceof ObjectScript))
						continue;
					ObjectScript script = (ObjectScript) o;
					for (Object key : script.getKeys())
						cachedObjectScripts.put(key, script);
					processedClasses.add(c);
				}
			}
			System.out.println("[ObjectScriptHandler]: " + processedClasses.size() + " scripts were loaded.");
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}
}
