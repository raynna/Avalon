package com.rs.game.objects.plugins;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Magic;

public class KBDZone extends ObjectPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] { 1817, 1816, 1765 };
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		int id = object.getId();
		if (id == 1817 && object.getX() == 2273 && object.getY() == 4680) // kbd
			// lever
			Magic.pushLeverTeleport(player, new WorldTile(3067, 10254, 0));
		else if (id == 1816 && object.getX() == 3067 && object.getY() == 10252) // kbd
			// out
			// lever
			Magic.pushLeverTeleport(player, new WorldTile(2273, 4681, 0));
		else if (id == 1765 && object.getX() == 3017 && object.getY() == 3849) { // kbd
			// out
			// stairs
			player.stopAll();
			player.setNextWorldTile(new WorldTile(3069, 10255, 0));
			player.getControlerManager().startControler("WildernessControler");
		}
		if (id == 1816) {
			if (!player.KBDEntrance) {
				player.getDialogueManager().startDialogue("KBDEntrance");
				return false;
			}
		}
		return true;
	}
}
