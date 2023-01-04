package com.rs.game.item;

import com.rs.utils.Logger;
import com.rs.utils.Utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ItemScriptHandler {

    public static final HashMap<Object, ItemScript> cachedItemScripts = new HashMap<Object, ItemScript>();

    public static ItemScript getScript(Item item) {
        ItemScript script = cachedItemScripts.getOrDefault(item.getId(), cachedItemScripts.get(item.getName()));
        if (script != null) {
            System.out.println("[ItemScriptHandler] "+item.getName()+"("+item.getId()+"): script was found by Id.");
            return script;
        }
        if (script == null) {
            for (Map.Entry<Object, ItemScript> entry : cachedItemScripts.entrySet()) {
                Object[] keys = entry.getValue().getKeys();
                for (Object key : keys) {
                    if (key instanceof String && item.getName().toLowerCase().contains(((String) key).toLowerCase())) {
                        script = entry.getValue();
                        System.out.println("[ItemScriptHandler] "+item.getName()+"("+item.getId()+"): Found script name");
                        return script;
                    }
                }
            }
        }
        System.out.println("[ItemScriptHandler] "+item.getName()+"("+item.getId()+"): Found no script for this item.");
        return null;
    }

    @SuppressWarnings("rawtypes")
    public static final void init() {
        try {
            String[] scriptFolders = {"com.rs.game.item.scripts",
                    "com.rs.game.item.scripts.weapons",
                    "com.rs.game.item.scripts.misc",
                    "com.rs.game.item.scripts.tools",
                    "com.rs.game.item.scripts.summoning",
                    "com.rs.game.item.scripts.skilling",
                    "com.rs.game.item.scripts.minigames"};
            Set<Class> processedClasses = new HashSet<>();
            for (String scriptFolder : scriptFolders) {
                Class[] classes = Utils.getClasses(scriptFolder);
                for (Class c : classes) {
                    if (c.isAnonymousClass() || processedClasses.contains(c)) {
                        continue;
                    }
                    Object o = c.newInstance();
                    if (!(o instanceof ItemScript)) {
                        continue;
                    }
                    ItemScript script = (ItemScript) o;
                    for (Object key : script.getKeys()) {
                        cachedItemScripts.put(key, script);
                    }
                    processedClasses.add(c);
                }
            }
            System.out.println("[ItemScriptHandler]: " + processedClasses.size() + " scripts were loaded.");
        } catch (Throwable e) {
            Logger.handle(e);
        }
    }

}
