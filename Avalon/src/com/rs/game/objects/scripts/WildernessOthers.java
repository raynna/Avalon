package com.rs.game.objects.scripts;

import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.WildernessObelisk;
import com.rs.game.player.controlers.WildernessControler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.net.decoders.handlers.InventoryOptionsHandler;

public class WildernessOthers extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 42611, 65621, 65620, 65619, 65618, 65617, 65616, 27254, 12202, 12230, 15522, 38811, 37929, 37928, 38815 };
	}
	
	@Override
	public boolean processObject(Player player, WorldObject object) {
		int id = object.getId();
		if (WildernessControler.isDitch(id)) {// wild ditch
			if (((object.getRotation() == 0 || object.getRotation() == 2) && player.getY() < object.getY())
					|| (object.getRotation() == 1 || object.getRotation() == 3)
							&& player.getX() > object.getX()) {
				player.getDialogueManager().startDialogue("WildernessDitch", object);
				return false;
			} else {
				player.lock();
				player.animate(new Animation(6132));
				final WorldTile toTile = new WorldTile(
						object.getRotation() == 1 || object.getRotation() == 3 ? object.getX() + 1
								: player.getX(),
						object.getRotation() == 0 || object.getRotation() == 2 ? object.getY() - 1
								: player.getY(),
						object.getPlane());
				player.setNextForceMovement(new ForceMovement(new WorldTile(player), 1, toTile, 2,
						object.getRotation() == 0 || object.getRotation() == 2 ? ForceMovement.SOUTH
								: ForceMovement.EAST));
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						player.setNextWorldTile(toTile);
						player.faceObject(object);
						player.unlock();
					}
				}, 2);
				return false;
			}
		} else if (id == 42611) {// Magic Portal
			player.getDialogueManager().startDialogue("MagicPortal");
			// } else if
			// (object.getDefinitions().name.equalsIgnoreCase("Obelisk")
			// && object.getY() > 3525) {
			// WildernessObelisk.handleObject(object, player);
		} else if (id >= 65616 && id <= 65622) {
			WildernessObelisk.activateObelisk(id, player);
		} else if (id == 27254) {// Edgeville portal
			player.getPackets().sendGameMessage("You enter the portal...");
			player.useStairs(10584, new WorldTile(3087, 3488, 0), 2, 3, "..and are transported to Edgeville.");
			player.addWalkSteps(1598, 4506, -1, false);
		} else if (id == 12202) {// mole entrance
			if (!player.getInventory().containsItem(952, 1)) {
				player.getPackets().sendGameMessage("You need a spade to dig this.");
				return false;
			}
			if (player.getX() != object.getX() || player.getY() != object.getY()) {
				player.lock();
				player.addWalkSteps(object.getX(), object.getY());
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						InventoryOptionsHandler.dig(player);
					}

				}, 1);
			} else
				InventoryOptionsHandler.dig(player);
		} else if (id == 12230 && object.getX() == 1752 && object.getY() == 5136) {// mole
																					// exit
			player.setNextWorldTile(new WorldTile(2986, 3316, 0));
		} else if (id == 15522) {// portal sign
			if (player.withinDistance(new WorldTile(1598, 4504, 0), 1)) {// PORTAL
				// 1
				player.getInterfaceManager().sendInterface(327);
				player.getPackets().sendIComponentText(327, 13, "Edgeville");
				player.getPackets().sendIComponentText(327, 14, "This portal will take you to edgeville. There "
						+ "you can multi pk once past the wilderness ditch.");
			}
			if (player.withinDistance(new WorldTile(1598, 4508, 0), 1)) {// PORTAL
				// 2
				player.getInterfaceManager().sendInterface(327);
				player.getPackets().sendIComponentText(327, 13, "Mage Bank");
				player.getPackets().sendIComponentText(327, 14, "This portal will take you to the mage bank. "
						+ "The mage bank is a 1v1 deep wilderness area.");
			}
			if (player.withinDistance(new WorldTile(1598, 4513, 0), 1)) {// PORTAL
				// 3
				player.getInterfaceManager().sendInterface(327);
				player.getPackets().sendIComponentText(327, 13, "Magic's Portal");
				player.getPackets().sendIComponentText(327, 14,
						"This portal will allow you to teleport to areas that "
								+ "will allow you to change your magic spell book.");
			}
		} else if (id == 38811 || id == 37929) {// corp beast
			if (object.getX() == 2971 && object.getY() == 4382)
				player.getInterfaceManager().sendInterface(650);
			else if (object.getX() == 2918 && object.getY() == 4382) {
				player.stopAll();
				player.setNextWorldTile(
						new WorldTile(player.getX() == 2921 ? 2917 : 2921, player.getY(), player.getPlane()));
			}
		} else if (id == 37928 && object.getX() == 2883 && object.getY() == 4370) {
			player.stopAll();
			player.setNextWorldTile(new WorldTile(3214, 3782, 0));
			player.getControlerManager().startControler("WildernessControler");
		} else if (id == 38815 && object.getX() == 3209 && object.getY() == 3780 && object.getPlane() == 0) {
			if (player.getSkills().getLevelForXp(Skills.WOODCUTTING) < 37
					|| player.getSkills().getLevelForXp(Skills.MINING) < 45
					|| player.getSkills().getLevelForXp(Skills.SUMMONING) < 23
					|| player.getSkills().getLevelForXp(Skills.FIREMAKING) < 47
					|| player.getSkills().getLevelForXp(Skills.PRAYER) < 55) {
				player.getPackets().sendGameMessage(
						"You need 23 Summoning, 37 Woodcutting, 45 Mining, 47 Firemaking and 55 Prayer to enter this dungeon.");
				return false;
			}
			player.stopAll();
			player.setNextWorldTile(new WorldTile(2885, 4372, 2));
			player.getControlerManager().forceStop();
			// TODO all reqs, skills not added
		} 
		return true;
	}
	
	@Override
	public boolean processObject2(Player player, WorldObject object) {
		int id = object.getId();
		if (id == 38811) {// corp beast 
			player.getCutscenesManager().play("CorporealBeastScene");
			return false;
		}
		
		return true;
	}
}