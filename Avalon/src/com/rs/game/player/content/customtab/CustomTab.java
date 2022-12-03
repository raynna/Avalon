package com.rs.game.player.content.customtab;

import com.rs.game.player.Player;

public class CustomTab {
	
	protected static final int BLUE_STAR_COMP = 62;
	protected static final int GREEN_STAR_COMP = 61;
	protected static final int RED_STAR_COMP = 60;
	protected static final int PURPLE_STAR_COMP = 59;
	protected static final int YELLOW_STAR_COMP = 26;
	
	protected static final int BACK_BUTTON = 58;
	protected static final int FORWARD_BUTTON = 27;
	
	
	protected static final int BLUE_HIGHLIGHTED = 12184;
	protected static final int GREEN_HIGHLIGHTED = 12182;
	protected static final int RED_HIGHLIGHTED = 12186;
	protected static final int PURPLE_HIGHLIGHTED = 12185;
	protected static final int YELLOW_HIGHLIGHTED = 12187;
	
	protected static final int BLUE_UNSELECECTED = 12188;
	protected static final int GREEN_UNSELECECTED = 12189;
	protected static final int RED_UNSELECECTED = 12190;
	protected static final int PURPLE_UNSELECECTED = 12191;
	protected static final int YELLOW_UNSELECECTED = 675;//12192
	
	
	public static void sendComponents(Player player) {
		player.getPackets().sendHideIComponent(3002, BLUE_STAR_COMP, false);
		player.getPackets().sendHideIComponent(3002, GREEN_STAR_COMP, false);
		player.getPackets().sendHideIComponent(3002, RED_STAR_COMP, false);
		player.getPackets().sendHideIComponent(3002, PURPLE_STAR_COMP, false);
		player.getPackets().sendHideIComponent(3002, YELLOW_STAR_COMP, false);
		player.getPackets().sendSpriteOnIComponent(3002, BLUE_STAR_COMP, BLUE_UNSELECECTED);
		player.getPackets().sendSpriteOnIComponent(3002, GREEN_STAR_COMP, GREEN_UNSELECECTED);
		player.getPackets().sendSpriteOnIComponent(3002, RED_STAR_COMP, RED_UNSELECECTED);
		player.getPackets().sendSpriteOnIComponent(3002, PURPLE_STAR_COMP, PURPLE_UNSELECECTED);
		player.getPackets().sendSpriteOnIComponent(3002, YELLOW_STAR_COMP, YELLOW_UNSELECECTED);
		player.getPackets().sendHideIComponent(3002, 24, true);
	}
	
	public static void sendHideComponents(Player player) {
		player.getPackets().sendHideIComponent(3002, BLUE_STAR_COMP, true);
		player.getPackets().sendHideIComponent(3002, GREEN_STAR_COMP, true);
		player.getPackets().sendHideIComponent(3002, RED_STAR_COMP, true);
		player.getPackets().sendHideIComponent(3002, PURPLE_STAR_COMP, true);
		player.getPackets().sendHideIComponent(3002, YELLOW_STAR_COMP, true);
		player.getPackets().sendHideIComponent(3002, 24, true);
		player.getPackets().sendHideIComponent(3002, BACK_BUTTON, true);
		player.getPackets().sendHideIComponent(3002, FORWARD_BUTTON, true);
		for (int i = 3; i <= 22; i++)
			player.getPackets().sendHideIComponent(3002, i, true);
		for (int i = 28; i <= 56; i++)
			player.getPackets().sendHideIComponent(3002, i, true);
	}

}
