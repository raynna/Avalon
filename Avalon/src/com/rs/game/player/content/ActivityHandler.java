package com.rs.game.player.content;

import com.rs.game.player.Player;

public class ActivityHandler {
     
	//the interface id
	public static int INTERFACE = 1158;
	/**
	  * To get the current wildy event
	  **/
	public static String CurrentWildyEvent = "Wait for the chest to Spawn.";
	
	public static String getCurrentWildyEvent() {
		return  CurrentWildyEvent;
	}
	public static void setCurrentWildyEvent(String  CurrentWildyEvent) {
		ActivityHandler.CurrentWildyEvent =  CurrentWildyEvent;
	}
	//skilling event
	public static String WildySkillingEvent = "";
	
	public static String getWildySkillingEvent() {
		return  WildySkillingEvent;
	}
	public static void setWildySkillingEvent(String  WildySkillingEvent) {
		ActivityHandler.WildySkillingEvent =  WildySkillingEvent;
	}
	//evil tree
	public static String EvilTree = "";
	
	public static String getEvilTree() {
		return  EvilTree;
	}
	public static void setEvilTree(String  EvilTree) {
		ActivityHandler.EvilTree =  EvilTree;
	}
	//World event
	public static String worldEvent = "";
	
	public static String getworldEvent() {
		return  worldEvent;
	}
	public static void setworldEvent(String  worldEvent) {
		ActivityHandler.worldEvent =  worldEvent;
	}
	//shooting star
	public static String SHOOTINGSTAR = "";
	
	public static String getShootingStar() {
		return SHOOTINGSTAR;
	}
	public static void setShootingStar(String  ShootingStar) {
		ActivityHandler.SHOOTINGSTAR =  ShootingStar;
	}
	//home raids
	public static String HOMERAID = "";
	
	public static String getHomeRaid() {
		return HOMERAID;
	}
	public static void setHomeRaid(String  homeRaids) {
		ActivityHandler.HOMERAID =  homeRaids;
	}
	/**
	  * call this method to send the interface 
	  */
	public static void sendInterface(Player player) {
	player.getInterfaceManager().sendInterface(INTERFACE);
	player.getPackets().sendIComponentText(INTERFACE, 74, "Event Checker");
	player.getPackets().sendIComponentText(INTERFACE, 9, "<img=7>Name.");
	player.getPackets().sendIComponentText(INTERFACE, 10, "<img=7>Description");
	player.getPackets().sendIComponentText(INTERFACE, 11, "<img=7>Type.");
	player.getPackets().sendIComponentText(INTERFACE, 14, "Shooting star");
	player.getPackets().sendIComponentText(INTERFACE, 15, ""+ getShootingStar()+"");
	player.getPackets().sendIComponentText(INTERFACE, 16, "Skilling");
	}
	

}