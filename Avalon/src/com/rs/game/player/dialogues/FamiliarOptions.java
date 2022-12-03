package com.rs.game.player.dialogues;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Rain
 * @Date - 9 Feb 2016
 *
 **/
public class FamiliarOptions extends Dialogue {

	@Override
	public void start() {
		sendOptions("<col=ff000>Current HP: " + player.getFamiliar().getHitpoints(),
				(player.familiarAutoAttack ? "Disable " : "Enable ") + "'Auto Retailiate (BETA)'",
				"View basic information about " + player.getFamiliar().getName(),
				player.storedScrolls >= 1 ? "Take out the stored " + player.getFamiliar().getSpecialName() + " scrolls."
						: "You do not have any stored scrolls. (BETA)",
				"Talk with your " + player.getFamiliar().getName());
		stage = 1;
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
				if (player.familiarAutoAttack) {
					player.familiarAutoAttack = false;
				} else {
					player.familiarAutoAttack = true;
				}
				sendDialogue("Your familiar will now: " + (player.familiarAutoAttack
						? "Auto retailiate<br><col=ff0000>Only works in Multi!" : "No longer auto retailiate."));
				stage = END;
				break;
			case OPTION_2:
				sendDialogue("Special attack: " + player.getFamiliar().getSpecialName() + "<br>" + "Description: "
						+ player.getFamiliar().getSpecialDescription() + "<br>" + "Max HP/Current HP: "
						+ player.getFamiliar().getMaxHitpoints() + " | " + player.getFamiliar().getHitpoints() + "<br>"
						+ "Max hit: " + player.getFamiliar().getMaxHit() + "<br>"
						+ (player.getFamiliar().getHitpoints() < player.getFamiliar().getMaxHitpoints()
								? "<col=ff0000>Your familiar is currently hurt and needs healing. Heal it at a nearby obelisk."
								: (player.getFamiliar().getBOBSize() >= 1
										? "Bob size: " + player.getFamiliar().getBOBSize() : "")));
				stage = END;
				break;
			case OPTION_3:
				if (player.storedScrolls >= 1) {
					end();
					player.getFamiliar().takeStoredScrolls();
				} else {
					sendDialogue(
							"You do not have any stored scrolls! Use the specific scroll for the familiar, and it will use special attack automatically if it has any."
									+ " (Cannot be used on BOB familiars) <col=ff0000>BETA");
					stage = END;
				}
				break;
			case OPTION_4:
				player.getFamiliar().startDialogue();
			}
			break;

		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
