package com.rs.game.player.dialogues;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

// Referenced classes of package com.rs.game.player.dialogues:
//            Dialogue

public class Skillingz extends Dialogue {

	public Skillingz() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("What skill do you wanna level?", "Mining", "Smithing", "Fishing", "Cooking", "Next Page");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				sendOptionsDialogue("What skill do you wanna level?", "Al-kharid Mining", "Falador Mining",
						"Varrock Mining", "Desert Mining", "Previous");
				stage = 20;
			} else if (componentId == OPTION_2) {
				sendOptionsDialogue("What skill do you wanna level?", "Al-kharid furnace", "Edgeville furnace",
						"Camelot anvil", "Varrock anvil", "Previous");
				stage = 21;
			} else if (componentId == OPTION_3) {
				sendOptionsDialogue("What fishing spot do you wanna visit?", "Al-kharid fishing", "Catherby fishing",
						"Living Rock Cavern", "Draynor fishing", "Previous");
				stage = 22;
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("What fishing spot do you wanna visit?", "Catherby range", "Falador range",
						"Cooking guild", "Previous");
				stage = 23;
			} else if (componentId == OPTION_5) {
				stage = 2;
				sendOptionsDialogue("What skill do you wanna level?", "Woodcutting", "Runecrafting", "Thieving",
						"Previous", "Next Page");

			}
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				sendOptionsDialogue("What woodcutting spot do you wanna visit?", "Draynor willows", "Camelot maples",
						"Camelot yews", "Camelot magics", "Previous");
				stage = 24;
			} else if (componentId == OPTION_2) {
				sendOptionsDialogue("What runecrafting altar do you wanna visit?", "Air altar", "Fire altar",
						"Astral altar", "Death altar", "Blood altar");
				stage = 25;
				// RunespanControler.enterRunespan(player, false);
			} else if (componentId == OPTION_3) {
				stage = 26;
				sendOptionsDialogue("What thieving spot do you wanna visit?", "Edgeville man", "Draynor farmer",
						"Ardougne square", "Previous");
			} else if (componentId == OPTION_4) {
				stage = 1;
				sendOptionsDialogue("What skill do you wanna level?", "Mining", "Smithing", "Fishing", "Cooking",
						"Next Page");
			} else if (componentId == OPTION_5) {
				stage = 3;
				sendOptionsDialogue("What skill do you wanna level?", "Hunter", "Agility", "Previous", "No thanks");

			}
		} else if (stage == 3) {
			if (componentId == OPTION_1) {
				sendTeleport(new WorldTile(2373, 3608, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				stage = 27;
				sendOptionsDialogue("What agility spot do you wanna visit?", "Gnome course", "Barbarian course",
						"Previous");
			} else if (componentId == OPTION_3) {
				stage = 2;
				sendOptionsDialogue("What skill do you wanna level?", "Woodcutting", "Runecrafting", "Previous",
						"Next Page");
			} else if (componentId == OPTION_4) {
				player.getInterfaceManager().closeChatBoxInterface();

			}
		} else if (stage == 20) { // mining
			if (componentId == OPTION_1) {
				sendTeleport(new WorldTile(3299, 3296, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				sendTeleport(new WorldTile(3039, 9774, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				sendTeleport(new WorldTile(3285, 3367, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				sendTeleport(new WorldTile(3176, 2904, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_5) {
				stage = 1;
				sendOptionsDialogue("What skill do you wanna level?", "Mining", "Smithing", "Fishing", "Cooking",
						"Next Page");

			}
		} else if (stage == 21) { // smithing
			if (componentId == OPTION_1) {
				sendTeleport(new WorldTile(3277, 3186, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				sendTeleport(new WorldTile(3103, 3500, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				sendTeleport(new WorldTile(2710, 3492, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				sendTeleport(new WorldTile(3187, 3426, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_5) {
				stage = 1;
				sendOptionsDialogue("What skill do you wanna level?", "Mining", "Smithing", "Fishing", "Cooking",
						"Next Page");

			}
		} else if (stage == 22) { // fishing
			if (componentId == OPTION_1) {
				sendTeleport(new WorldTile(3270, 3151, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				sendTeleport(new WorldTile(2849, 3432, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				sendTeleport(new WorldTile(3639, 5086, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				sendTeleport(new WorldTile(3087, 3230, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_5) {
				stage = 1;
				sendOptionsDialogue("What skill do you wanna level?", "Mining", "Smithing", "Fishing", "Cooking",
						"Next Page");

			}
		} else if (stage == 23) { // cooking
			if (componentId == OPTION_1) {
				sendTeleport(new WorldTile(2817, 3438, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				sendTeleport(new WorldTile(2989, 3369, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				sendTeleport(new WorldTile(3147, 3450, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				stage = 1;
				sendOptionsDialogue("What skill do you wanna level?", "Mining", "Smithing", "Fishing", "Cooking",
						"Next Page");

			}
		} else if (stage == 24) { // woodcutting
			if (componentId == OPTION_1) {
				sendTeleport(new WorldTile(3087, 3234, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				sendTeleport(new WorldTile(2726, 3499, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				sendTeleport(new WorldTile(2710, 3463, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				sendTeleport(new WorldTile(2700, 3422, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_5) {
				stage = 2;
				sendOptionsDialogue("What skill do you wanna level?", "Woodcutting", "Runecrafting", "Previous",
						"Next Page");

			}
		} else if (stage == 25) { // runecrafting
			if (componentId == OPTION_1) {
				sendTeleport(new WorldTile(2845, 4832, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				sendTeleport(new WorldTile(2584, 4836, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				sendTeleport(new WorldTile(2155, 3864, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				sendTeleport(new WorldTile(2207, 4836, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_5) {
				sendTeleport(new WorldTile(2462, 4901, 1));
				player.getInterfaceManager().closeChatBoxInterface();

			}
		} else if (stage == 26) { // thieving
			if (componentId == OPTION_1) {
				sendTeleport(new WorldTile(3097, 3509, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				sendTeleport(new WorldTile(3079, 3251, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				sendTeleport(new WorldTile(2662, 3306, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				stage = 3;
				sendOptionsDialogue("What skill do you wanna level?", "Thieving", "Agility", "Previous", "No thanks");

			}
		} else if (stage == 27) { // agility
			if (componentId == OPTION_1) {
				sendTeleport(new WorldTile(2473, 3438, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				sendTeleport(new WorldTile(2552, 3557, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				stage = 3;
				sendOptionsDialogue("What skill do you wanna level?", "Thieving", "Agility", "Previous", "No thanks");

			}
		}
	}

	public void sendTeleport(final WorldTile coord) {
		player.lock(4);
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					player.animate(new Animation(7082));
					player.gfx(new Graphics(1229));
				} else if (loop == 2) {
					player.setNextWorldTile(coord);
					player.animate(new Animation(7084));
					player.gfx(new Graphics(1229));
				} else if (loop == 4) {
					player.animate(new Animation(-1));
				}
				loop++;
			}
		}, 0, 1);
	}

	@Override
	public void finish() {
	}

}
