package com.rs.game.objects;

import java.util.HashMap;

import com.rs.utils.Logger;
import com.rs.utils.Utils;

public class ObjectScriptsHandler {

	public static final HashMap<Object, ObjectScript> cachedObjectScripts = new HashMap<Object, ObjectScript>();

	@SuppressWarnings("rawtypes")
	public static final void init() {
		try {
			Class[] classes = Utils.getClasses("com.rs.game.objects.impl");
			for (Class c : classes) {
				if (c.isAnonymousClass())
					continue;
				Object o = c.newInstance();
				if (!(o instanceof ObjectScript))
					continue;
				ObjectScript script = (ObjectScript) o;
				for (Object key : script.getKeys())
					cachedObjectScripts.put(key, script);
			}
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}
}
