package com.rs.game.player.content;

import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.item.Item;
import com.rs.game.player.Player;

public class RottenPotato {

	public static final int EAT = 14, HEAL = 67, CM_TOOL = 5, USE = -1, COMMANDS = 90, DROP = 6, EXAMINE = 32;

	public static void handlePotato(Player player, Item item, int packetId) {
		switch (packetId) {
		case EAT:
			player.getDialogueManager().startDialogue("Potato_Op1", item);
			break;
		case HEAL:
			heal(player);
			break;
		case CM_TOOL:
			player.getDialogueManager().startDialogue("Potato_CMTool", item);
			break;
		case USE:

			break;
		case COMMANDS:
			player.getDialogueManager().startDialogue("Potato_Commands", item);
			break;
		case DROP:
			player.getInventory().deleteItem(item);
			player.getPackets().sendGameMessage("Too late! It's already gone.", true);
			break;
		case EXAMINE:
			player.getPackets().sendGameMessage("Yuk!", true);
			break;
		}
	}

	public static void heal(Player player) {
		player.getPrayer().calcRestorePrayer();
		if (player.getPoison().isPoisoned()) {
			player.getPoison().reset();
			player.getPackets().sendGameMessage("Your poison has been cured.", true);
		}
		player.setRunEnergy(100);
		player.getCombatDefinitions().resetSpecialAttack();
		player.getAppearence().generateAppearenceData();
		player.applyHit(new Hit(player, player.getMaxHitpoints(), HitLook.HEALED_DAMAGE));
		player.getPackets().sendGameMessage("You have been healed.", true);
	}
}
