package com.rs.game.item;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ItemScriptHandler {

    public static final HashMap<Object, ItemScripts> cachedItemScripts = new HashMap<Object, ItemScripts>();

    public static ItemScripts getScript(Item item) {
        ItemScripts script = ItemScriptHandler.cachedItemScripts.getOrDefault(item.getId(), ItemScriptHandler.cachedItemScripts.get(item.getName()));
        if (script != null) {
            System.out.println("getScript("+item.getName()+", id: "+item.getId()+"): script was found by Id.");
            return script;
        }
        if (script == null) {
            System.out.println("getScript("+item.getName()+", id: "+item.getId()+"): First script check is null, try finding by containing name");
            for (Map.Entry<Object, ItemScripts> entry : ItemScriptHandler.cachedItemScripts.entrySet()) {
                Object[] keys = entry.getValue().getKeys();
                for (Object key : keys) {
                    if (key instanceof String && item.getName().toLowerCase().contains(((String) key).toLowerCase())) {
                        script = entry.getValue();
                        System.out.println("getScript("+item.getName()+", id: "+item.getId()+"): Found script by containing name " + entry.getValue().toString());
                        return script;
                    }
                }
            }
        }
        System.out.println("getScript("+item.getName()+", id: "+item.getId()+"): Found no script for this item.");
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
            for (String scriptFolder : scriptFolders) {
                Class[] classes = Utils.getClasses(scriptFolder);
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
            }
        } catch (Throwable e) {
            Logger.handle(e);
        }
    }
}
