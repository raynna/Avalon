package com.rs.game.cityhandler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.rs.Settings;
import com.rs.game.WorldObject;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;

public final class CityEventHandler {

    private static final Map<Integer, CityEvent> cityEvents = new HashMap<Integer, CityEvent>();

    private static final Logger logger = Logger.getLogger(CityEventHandler.class
            .getCanonicalName());

    public static final boolean registerCitys() {
        for (File file : (Settings.VPS_HOSTED ? new File("C:/Users/Administrator/Dropbox/1. Avalon/src/com/game/cityhandlers/impl/") : new File("./src/com/rs/game/cityhandler/impl/")).listFiles()) {
            try {
				if (!((CityEvent) Class.forName("com.rs.game.cityhandler.impl."+ file.getName().replace(".java", "")).newInstance()).init()) {
				    return false;
				}
			} catch (InstantiationException e) {
				//e.printStackTrace();
			} catch (IllegalAccessException e) {
				//e.printStackTrace();
			} catch (ClassNotFoundException e) {
				//e.printStackTrace();
			}
        }
       // logger.info("Loaded " + cityEvents.size() + " city events.");
        return true;
    }

    public static final boolean reload() throws Throwable {
        cityEvents.clear();
        return registerCitys();
    }

    public static final boolean handleNPCClick(Player player, NPC npc, int npcId) {
        CityEvent cityEvent = cityEvents.get(npcId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleNPCClick(player, npc);
    }

    public static boolean handleNPCClick2(Player player, NPC npc, int npcId) {
        CityEvent cityEvent = cityEvents.get(npcId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleNPCClick2(player, npc);
    }

    public static boolean handleNPCClick3(Player player, NPC npc, int npcId) {
        CityEvent cityEvent = cityEvents.get(npcId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleNPCClick3(player, npc);
    }

    public static boolean handleNPCClick4(Player player, NPC npc, int npcId) {
        CityEvent cityEvent = cityEvents.get(npcId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleNPCClick4(player, npc);
    }

    public static final boolean handleObjectClick(Player player, WorldObject object, int objectId) {
        CityEvent cityEvent = cityEvents.get(objectId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleObjectClick(player, object);
    }

    public static final boolean handleObjectClick2(Player player,
    		WorldObject object, int objectId) {
        CityEvent cityEvent = cityEvents.get(objectId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleObjectClick2(player, object);
    }

    public static final boolean handleObjectClick3(Player player,
    		WorldObject object, int objectId) {
        CityEvent cityEvent = cityEvents.get(objectId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleObjectClick3(player, object);
    }

    public static final boolean handleObjectClick4(Player player,
    		WorldObject object, int objectId) {
        CityEvent cityEvent = cityEvents.get(objectId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleObjectClick4(player, object);
    }

    public static final boolean handleObjectClick5(Player player,
    		WorldObject object, int objectId) {
        CityEvent cityEvent = cityEvents.get(objectId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleObjectClick5(player, object);
    }

    public static boolean registerNPCs(int npcId, CityEvent cityEvent) {
        if (cityEvents.containsKey(npcId)) {
            logger.info("City: " + cityEvent.getClass().getCanonicalName()
                    + " Id => " + npcId+ ", already registered with "
                    + cityEvents.get(npcId).getClass().getCanonicalName() + ".");
            return false;
        }
        cityEvents.put(npcId, cityEvent);
        return true;
    }

    public static boolean registerObjects(int objectId, CityEvent cityEvent) {
        if (cityEvents.containsKey(objectId)) {
            logger.info("City: " + cityEvent.getClass().getCanonicalName()
                    + " Id => " + objectId + " already registered with "
                    + cityEvents.get(objectId).getClass().getCanonicalName()
                    + ".");
            return false;
        }
        cityEvents.put(objectId, cityEvent);
        return true;
    }

	public static boolean handleItemOnObject(Player player, WorldObject object, Item item) {
		 CityEvent cityEvent = cityEvents.get(object.getId());
	        if (cityEvent == null)
	            return false;
	        return cityEvent.handleItemOnObject(player, object, item);
	}
}