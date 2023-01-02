package com.rs.game.item;

import com.rs.utils.Logger;
import com.rs.utils.Utils;

import java.util.HashMap;

public class ItemScriptHandler {

    public static final HashMap<Object, ItemScripts> cachedItemScripts = new HashMap<Object, ItemScripts>();

    @SuppressWarnings("rawtypes")
    public static final void init() {
        try {
            Class[] classes = Utils.getClasses("com.rs.game.item.scripts.*");
            for (Class c : classes) {
                if (c.isAnonymousClass())
                    continue;
                Object o = c.newInstance();
                if (!(o instanceof ItemScripts))
                    continue;
                ItemScripts script = (ItemScripts) o;
                for (Object key : script.getKeys())
                    cachedItemScripts.put(key, script);
            }
        } catch (Throwable e) {
            Logger.handle(e);
        }
    }
}