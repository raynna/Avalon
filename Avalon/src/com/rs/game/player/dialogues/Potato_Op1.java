package com.rs.game.player.dialogues;

import com.rs.Settings;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.WorldTile;

public class Potato_Op1 extends Dialogue {

	/*
	 * Tristam
	 */

	@Override
	public void start() {
		if (!player.isDeveloper()) {
			sendDialogue("Hi " + player.getDisplayName()
					+ ". Please contact Tristam, letting him know how you have gotten this item. <br>Thankyou!");
			player.getFriendsIgnores().addFriend("tristam");
			stage = END;
		} else {
			sendOptionsDialogue("Op_1", "Kick me out", "Kill me", "Teleport home", "Poison", "Runes");
			stage = 1;
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case END:
			end();
			break;
		case 1:
			switch (componentId) {
			case OPTION_1:
				player.forceLogout();
				end();
				break;
			case OPTION_2:
				player.applyHit(new Hit(player, player.getMaxHitpoints(), HitLook.DESEASE_DAMAGE));
				end();
				break;
			case OPTION_3:
				player.setNextWorldTile(new WorldTile(Settings.HOME_PLAYER_LOCATION));
				player.unlock();
				player.getInterfaceManager().sendTabInterfaces(false);
				player.getControlerManager().removeControlerWithoutCheck();
				end();
				break;
			case OPTION_4:
				player.getPoison().makePoisoned(12);
				end();
				player.message("You are now poisoned.");
				break;
			case OPTION_5:
				for (int runes = 554; runes <= 566; runes++) {
					player.getInventory().addItem(runes, Integer.MAX_VALUE);
					end();
				}
				break;
			}
		}
	}

	@Override
	public void finish() {

	}
}
