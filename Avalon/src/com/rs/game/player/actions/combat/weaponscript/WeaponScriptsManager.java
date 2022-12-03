package com.rs.game.player.actions.combat.weaponscript;

import java.util.HashMap;

import com.rs.utils.Logger;
import com.rs.utils.Utils;

/**
 * @author -Andreas 11 feb. 2020 12:23:00
 * @project 1. Avalon
 * 
 */

public class WeaponScriptsManager {

	public static final HashMap<Object, WeaponScript> weaponScripts = new HashMap<Object, WeaponScript>();

	@SuppressWarnings("rawtypes")
	public static final void init() {
		try {
			Class[] classes = Utils.getClasses("com.rs.game.player.actions.combat.weaponscript.impl");
			for (Class c : classes) {
				if (c.isAnonymousClass())
					continue;
				Object o = c.newInstance();
				if (!(o instanceof WeaponScript))
					continue;
				WeaponScript script = (WeaponScript) o;
				for (Object key : script.getKeys())
					weaponScripts.put(key, script);
			}
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}
}
