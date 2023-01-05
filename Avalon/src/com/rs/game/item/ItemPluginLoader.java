package com.rs.game.item;

import com.rs.utils.Logger;
import com.rs.utils.Utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ItemPluginLoader {

    public static final HashMap<Object, ItemPlugin> cachedItemPlugins = new HashMap<Object, ItemPlugin>();

    public static ItemPlugin getPlugin(Item item) {
        ItemPlugin plugin = cachedItemPlugins.getOrDefault(item.getId(), cachedItemPlugins.get(item.getName()));
        if (plugin != null) {
            System.out.println("[ItemPluginManager] "+item.getName()+"("+item.getId()+"): plugin was found by Id.");
            return plugin;
        }
        if (plugin == null) {
            for (Map.Entry<Object, ItemPlugin> entry : cachedItemPlugins.entrySet()) {
                Object[] keys = entry.getValue().getKeys();
                for (Object key : keys) {
                    if (key instanceof String && item.getName().toLowerCase().contains(((String) key).toLowerCase())) {
                        plugin = entry.getValue();
                        System.out.println("[ItemPluginManager] "+item.getName()+"("+item.getId()+"): Found plugin name");
                        return plugin;
                    }
                }
            }
        }
        System.out.println("[ItemPluginManager] "+item.getName()+"("+item.getId()+"): Found no plugin for this item.");
        return null;
    }

    @SuppressWarnings("rawtypes")
    public static final void init() {
        try {
            String[] pluginFolders = {"com.rs.game.item.plugins",
                    "com.rs.game.item.plugins.weapons",
                    "com.rs.game.item.plugins.misc",
                    "com.rs.game.item.plugins.tools",
                    "com.rs.game.item.plugins.summoning",
                    "com.rs.game.item.plugins.skilling",
                    "com.rs.game.item.plugins.minigames"};
            Set<Class> processedClasses = new HashSet<>();
            for (String pluginFolder : pluginFolders) {
                Class[] classes = Utils.getClasses(pluginFolder);
                for (Class c : classes) {
                    if (c.isAnonymousClass() || processedClasses.contains(c)) {
                        continue;
                    }
                    Object o = c.newInstance();
                    if (!(o instanceof ItemPlugin)) {
                        continue;
                    }
                    ItemPlugin plugin = (ItemPlugin) o;
                    for (Object key : plugin.getKeys()) {
                        cachedItemPlugins.put(key, plugin);
                    }
                    processedClasses.add(c);
                }
            }
            System.out.println("[ItemPluginManager]: " + processedClasses.size() + " plugins were loaded.");
        } catch (Throwable e) {
            Logger.handle(e);
        }
    }

}
