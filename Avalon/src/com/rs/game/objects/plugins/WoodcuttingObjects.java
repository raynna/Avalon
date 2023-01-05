package com.rs.game.objects.plugins;

import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.game.WorldObject;
import com.rs.game.objects.ObjectPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.woodcutting.Woodcutting;
import com.rs.game.player.actions.skills.woodcutting.Woodcutting.TreeDefinitions;

//TODO: Not done
public class WoodcuttingObjects extends ObjectPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] { "vines", "tree", "evergreen", "dead tree", "swamp tree", "oak", "willow", "maple tree", "raple tree", "yew", "magic tree", "cursed magic tree", "bloodwood tree", "achey tree" };
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		final ObjectDefinitions objectDef = object.getDefinitions();
		switch (objectDef.name.toLowerCase()) {
		// Woodcutting start
		case "tree":
			if (objectDef.containsOption(0, "Chop down"))
				player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.NORMAL));
			break;
		case "evergreen":
			if (objectDef.containsOption(0, "Chop down"))
				player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.EVERGREEN));
			break;
		case "dead tree":
			if (objectDef.containsOption(0, "Chop down"))
				player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.DEAD));
			break;
		case "swamp tree":
			if (objectDef.containsOption(0, "Chop down"))
				player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.DEAD));
			break;
		case "oak":
			if (objectDef.containsOption(0, "Chop down"))
				player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.OAK));
			break;
		case "willow":
			if (objectDef.containsOption(0, "Chop down"))
				player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.WILLOW));
			break;
		case "raple tree":
		case "maple tree":
			if (objectDef.containsOption(0, "Chop down"))
				player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.MAPLE));
			break;
		case "ivy":
			if (objectDef.containsOption(0, "Chop"))
				player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.IVY));
			break;
		case "yew":
			if (objectDef.containsOption(0, "Chop down"))
				player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.YEW));
			break;
		case "magic tree":
			if (objectDef.containsOption(0, "Chop down"))
				player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.MAGIC));
			break;
		case "cursed magic tree":
			if (objectDef.containsOption(0, "Chop down"))
				player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.CURSED_MAGIC));
			break;
		case "bloodwood tree":// TODO
			// if (objectDef.containsOption(0, "Chop down"))
			// player.getActionManager().setAction(new Woodcutting(object,
			// TreeDefinitions.BLOODWOOD));
			break;
		case "achey tree":// TODO
			// if (objectDef.containsOption(0, "Chop"))
			// player.getActionManager().setAction(new Woodcutting(object,
			// TreeDefinitions.));
			break;
		
		}
		return true;
	}
}
