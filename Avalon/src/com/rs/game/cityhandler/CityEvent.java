package com.rs.game.cityhandler;

import com.rs.game.WorldObject;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;

/**
 *
 * @author Austin
 *
 */

public interface CityEvent {

    public boolean init();

    public boolean handleNPCClick(Player player, NPC npc);

    public boolean handleNPCClick2(Player player, NPC npc);

    public boolean handleNPCClick3(Player player, NPC npc);

    public boolean handleNPCClick4(Player player, NPC npc);

    public boolean handleObjectClick(Player player, WorldObject object);

    public boolean handleObjectClick2(Player player, WorldObject object);

    public boolean handleObjectClick3(Player player, WorldObject object);

    public boolean handleObjectClick4(Player player, WorldObject object);

    public boolean handleObjectClick5(Player player, WorldObject object);

	public boolean handleItemOnObject(Player player, WorldObject object, Item item);


}