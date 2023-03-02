package com.rs.game.player.dialogues.npcs;

import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.player.content.FadingScreen;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 26 Apr 2016
 *
 **/

public class GrimReaper extends Dialogue {

	int grim;

	@Override
	public void start() {
		grim = (Integer) parameters[0];
		if (player.getPlayerRank().isHardcore()) {
			sendNPCDialogue(grim, Plain, "Greetings. What do you want? Make it quick.");
			stage = 1;
		} else {
			player.message("Death ignores you.");
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case END:
			end();
			break;
		case 1:
			sendOptions(
					"<col=990000>Price for next life: "
							+ Utils.formatDoubledAmountShort(player.getIronman().getLifePrices(1.5)),
					"I want to purchase a life.", "Nevermind.");
			stage = 2;
			break;
		case 2:
			switch (componentId) {
			case OPTION_1:
				if (player.hasMoney(player.getIronman().getLifePrices(1.5))) {
					purchase();
				} else {
					int damage = (int) (player.getHitpoints() * 0.25);
					player.applyHit(new Hit(player, damage, HitLook.DESEASE_DAMAGE));
					player.gfx(new Graphics(2005, 0, 75));
					sendNPCDialogue(grim, Angry, "Be gone! You do not have the money for one.");
					player.message("Death swings in fury and hits you a total of " + damage + ".");
					stage = END;
				}
				break;
			case OPTION_2:
				end();
				break;
			}
			break;
		}
	}

	private void purchase() {
		player.lock(5);
		player.getInterfaceManager().sendTabInterfaces(true);
		sendNPCDialogueNoContinue(player, grim, Thinking, "Very well....");
		player.getMoneyPouch().removeMoneyMisc(player.getIronman().getLifePrices(1.5));
		FadingScreen.fade(player, 5, new Runnable() {

			@Override
			public void run() {
				sendDialogue(
						"You suddenly feel weird, as if some force controls you.... Death puts an extra life into your soul in exchange for <col=ff0000>"
								+ Utils.formatDoubledAmount(player.getIronman().getLifePrices(1.5)) + " GP</col>");
				player.getIronman().addLife(1);
				player.getInterfaceManager().closeFadingInterface();
				player.getInterfaceManager().sendTabInterfaces(false);
				stage = END;
			}

		});
	}

	@Override
	public void finish() {

	}

}
