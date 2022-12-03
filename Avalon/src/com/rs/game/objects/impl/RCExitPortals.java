package com.rs.game.objects.impl;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;
import com.rs.game.player.controlers.EdgevillePvPControler;

public class RCExitPortals extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 2465, 2466, 2467, 2468, 2469, 2470, 2471, 2472, 2743, 2474, 2475, 2477,
				7133, 7132, 7141, 7129, 7130, 7131, 7140, 7139, 7137, 7136, 7135, 7134
		};
	}
	
	@Override
	public boolean processObject(Player player, WorldObject object) {
		int id = object.getId();
		 if (id == 2468 && object.getX() == 3089 && object.getY() == 3493) {
			EdgevillePvPControler.enterPVP(player);
			return false;
		} else if (id == 2465 && object.getX() == 83 && object.getY() == 80) {
			EdgevillePvPControler.leavePVP(player);
			return false;
		}
		if (id == 2465) {// air
			player.movePlayer(new WorldTile(3127, 3408, 0), 1, 1);
			return false;
		} else if (id == 2466 && object.getX() == 2793 && object.getY() == 4827) { // mind
			player.movePlayer(new WorldTile(2980, 3512, 0), 1, 1);
			return false;
		} else if (id == 2467) {// water
			player.movePlayer(new WorldTile(3185, 3163, 0), 1, 1);
			return false;
		} else if (id == 2468) {// earth
			player.movePlayer(new WorldTile(3304, 3474, 0), 1, 1);
			return false;
		} else if (id == 2469) {// fire
			player.movePlayer(new WorldTile(3312, 3253, 0), 1, 1);
			return false;
		} else if (id == 2470) { // body
			player.movePlayer(new WorldTile(3055, 3444, 0), 1, 1);
			return false;
		} else if (id == 2471) {// cosmic
			player.movePlayer(new WorldTile(2408, 4379, 0), 1, 1);
			return false;
		} else if (id == 2472) {// chaos
			player.movePlayer(new WorldTile(2857, 3379, 0), 1, 1);
			return false;
		} else if (id == 2473) {// nature
			player.movePlayer(new WorldTile(2869, 3021, 0), 1, 1);
			return false;
		} else if (id == 2474) {// law
			player.movePlayer(new WorldTile(3060, 3588, 0), 1, 1);
			return false;
		} else if (id == 2475) {// death
			player.movePlayer(new WorldTile(1863, 4639, 0), 1, 1);
			return false;
		} else if (id == 2477) {// blood
			player.movePlayer(new WorldTile(3561, 9779, 0), 1, 1);
			return false;
		} else if (id >= 2465 && id <= 2477) { // portal
			player.setNextWorldTile(new WorldTile(3087, 3491, 0));
		} else if (id == 7133) { // nature rift
			player.setNextWorldTile(new WorldTile(2398, 4841, 0));
		} else if (id == 7132) { // cosmic rift
			player.setNextWorldTile(new WorldTile(2162, 4833, 0));
		} else if (id == 7141) { // blood rift
			player.setNextWorldTile(new WorldTile(2462, 4891, 1));
		} else if (id == 7129) { // fire rift
			player.setNextWorldTile(new WorldTile(2584, 4836, 0));
		} else if (id == 7130) { // earth rift
			player.setNextWorldTile(new WorldTile(2660, 4839, 0));
		} else if (id == 7131) { // body rift
			player.setNextWorldTile(new WorldTile(2527, 4833, 0));
		} else if (id == 7140) { // mind rift
			player.setNextWorldTile(new WorldTile(2794, 4830, 0));
		} else if (id == 7139) { // air rift
			player.setNextWorldTile(new WorldTile(2845, 4832, 0));
		} else if (id == 7137) { // water rift
			player.setNextWorldTile(new WorldTile(3482, 4836, 0));
		} else if (id == 7136) { // death rift
			player.setNextWorldTile(new WorldTile(2207, 4836, 0));
		} else if (id == 7135) { // law rift
			player.setNextWorldTile(new WorldTile(2464, 4834, 0));
		} else if (id == 7134) { // chaotic rift
			player.setNextWorldTile(new WorldTile(2269, 4843, 0));
		}
		return true;
	}
}
