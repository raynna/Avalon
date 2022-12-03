package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.Magic;

/*Nathan*/

public class PvMTeles extends Dialogue {

	public PvMTeles() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Where do you wanna go?", "GodWars", "Kalphite Queen", "Corporeal Beast",
				"Tormented Demons", "Nothing");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				stage = 20;// GodWars
				sendOptionsDialogue("What Boss do you wanna go to?", "Bandos", "Armadyl", "Saradomin", "Zamorak",
						"Previous.");
			} else if (componentId == OPTION_2) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3507, 9493, 0));// Kalphite
																							// Queen
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2966, 4383, 2));// corp
																							// tele
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2562, 5739, 0));// Torm
																							// Demons
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_5) {
				player.getInterfaceManager().closeChatBoxInterface();

			}
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				player.getPackets().sendGameMessage("Will be added soon!");
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				player.getPackets().sendGameMessage("Will be added soon!");
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				// Magic.sendNormalTeleportSpell(player, 0, 0, new
				// WorldTile(3365, 3275, 0));//PolyPore
				player.getPackets().sendGameMessage("Will be added soon!");
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2562, 5739, 0));// Torm
																							// Demons
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_5) {
				player.getInterfaceManager().closeChatBoxInterface();

			}
		} else if (stage == 20) {
			if (componentId == OPTION_1) {
				teleportPlayer(2849, 5333, 2);
				// Magic.sendNormalTeleportSpell(player, 0, 0, new
				// WorldTile(2865, 5354, 2));//bandos
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				teleportPlayer(2872, 5267, 2);
				// Magic.sendNormalTeleportSpell(player, 0, 0, new
				// WorldTile(2838, 5299, 2));//armadyl
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				teleportPlayer(2915, 5300, 1);
				// Magic.sendNormalTeleportSpell(player, 0, 0, new
				// WorldTile(2906, 5265, 0));//saradomin
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				teleportPlayer(2885, 5345, 2);
				// Magic.sendNormalTeleportSpell(player, 0, 0, new
				// WorldTile(2925, 5329, 2));//zamorak
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_5) {
				stage = 1;// previous
				sendOptionsDialogue("Where do you wanna go?", "GodWars", "Kalphite Queen", "Corporeal Beast",
						"Tormented Demons", "Nothing");

			}
		}

		else if (stage == 25) {// QbdYouSure
			if (componentId == OPTION_1) {
				end();
				if (player.getSkills().getLevelForXp(Skills.SUMMONING) < 60) {
					player.getPackets().sendGameMessage("You need a summoning level of 60 to go through this portal.");
					return;
				}
				player.getControlerManager().startControler("QueenBlackDragonControler");
			} else if (componentId == OPTION_2) {
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				stage = 1;
				sendOptionsDialogue("Where do you wanna go?", "GodWars", "Kalphite Queen", "Queen Black Dragon",
						"Corporeal Beast", "Next Page");

			}
		} else if (stage == 26) {// CorpYouSure
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2966, 4383, 2));// corp
																							// tele
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				stage = 1;
				sendOptionsDialogue("Where do you wanna go?", "GodWars", "Kalphite Queen", "Queen Black Dragon",
						"Corporeal Beast", "Next Page");

			}
		} else if (stage == 27) {// NexYouSure
			if (componentId == OPTION_1) {
				teleportPlayer(2905, 5203, 0);
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				stage = 2;
				sendOptionsDialogue("Where do you wanna go?", "--", "--", "--", "Tormented Demons", "No Thanks.");

			}
		}
	}

	private void teleportPlayer(int x, int y, int z) {
		player.setNextWorldTile(new WorldTile(x, y, z));
		player.stopAll();
		player.getControlerManager().startControler("GodWars");
	}

	@Override
	public void finish() {
	}

}
