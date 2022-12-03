package com.rs.game.player.dialogues;

import com.rs.game.player.Skills;

// Referenced classes of package com.rs.game.player.dialogues:
//            Dialogue

public class StarterSkills extends Dialogue {

	public StarterSkills() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("What would you like to be?", "Zerker Pure", "RunePure", "Med Level", "Pure", "Maxed Main");
		player.lock();

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				player.getInterfaceManager().closeChatBoxInterface();
				player.getPrayer().restorePrayer(
						(int) ((int) (Math.floor(player.getSkills().getLevelForXp(Skills.PRAYER) * 2.5) + 990)
								* player.getAuraManager().getPrayerPotsRestoreMultiplier()));
				player.getPrayer().setPrayerBook(true);
				player.getSkills().switchXPPopup(true);
				player.getSkills().switchXPPopup(true);
				player.getCombatDefinitions().setSpellBook(1);
				player.getSkills().set(0, 75);
				player.getSkills().set(1, 45);
				player.getSkills().set(5, 99);
				player.getSkills().setXp(0, Skills.getXPForLevel(75));
				player.getSkills().setXp(1, Skills.getXPForLevel(45));
				player.getSkills().setXp(5, Skills.getXPForLevel(99));
				player.getSkills().set(2, 99);
				player.getSkills().setXp(2, Skills.getXPForLevel(99));
				player.getSkills().set(6, 99);
				player.getSkills().setXp(6, Skills.getXPForLevel(99));
				player.getSkills().set(4, 99);
				player.getSkills().setXp(4, Skills.getXPForLevel(99));
				player.getSkills().set(3, 99);
				player.getSkills().setXp(3, Skills.getXPForLevel(99));
				player.getSkills().set(23, 99);
				player.getSkills().setXp(23, Skills.getXPForLevel(99));
				player.getAppearence().generateAppearenceData();
				player.getInventory().addItem(4091, 1);
				player.getInventory().addItem(4093, 1);
				player.getInventory().addItem(3751, 1);
				player.getInventory().addItem(7462, 1);
				player.getInventory().addItem(1712, 1);
				player.getInventory().addItem(3842, 1);
				player.getInventory().addItem(2413, 1);
				player.getInventory().addItem(4675, 1);
				player.getInventory().addItem(3105, 1);
				player.getInventory().addItem(1127, 1);
				player.getInventory().addItem(1079, 1);
				player.getInventory().addItem(4151, 1);
				player.getInventory().addItem(5698, 1);
				player.getInventory().addItem(6731, 1);
				player.getInventory().addItem(2503, 1);
				player.getInventory().addItem(2497, 1);
				player.getPackets().sendGameMessage("You choose Zerker Build");
				stage = 2;
				sendOptionsDialogue("Change Armour Look?", "No", "Yes");
				// player.unlock();
			} else if (componentId == OPTION_2) {
				player.getInterfaceManager().closeChatBoxInterface();
				player.getPrayer().restorePrayer(
						(int) ((int) (Math.floor(player.getSkills().getLevelForXp(Skills.PRAYER) * 2.5) + 990)
								* player.getAuraManager().getPrayerPotsRestoreMultiplier()));
				player.getPrayer().setPrayerBook(true);
				player.getSkills().switchXPPopup(true);
				player.getSkills().switchXPPopup(true);
				player.getCombatDefinitions().setSpellBook(1);
				player.getSkills().set(0, 60);
				player.getSkills().set(1, 40);
				player.getSkills().set(5, 99);
				player.getSkills().setXp(0, Skills.getXPForLevel(60));
				player.getSkills().setXp(1, Skills.getXPForLevel(40));
				player.getSkills().setXp(5, Skills.getXPForLevel(99));
				player.getSkills().set(2, 99);
				player.getSkills().setXp(2, Skills.getXPForLevel(99));
				player.getSkills().set(6, 99);
				player.getSkills().setXp(6, Skills.getXPForLevel(99));
				player.getSkills().set(4, 99);
				player.getSkills().setXp(4, Skills.getXPForLevel(99));
				player.getSkills().set(3, 99);
				player.getSkills().setXp(3, Skills.getXPForLevel(99));
				player.getSkills().set(23, 99);
				player.getSkills().setXp(23, Skills.getXPForLevel(99));
				player.getAppearence().generateAppearenceData();
				player.getInventory().addItem(4103, 1);
				player.getInventory().addItem(4101, 1);
				player.getInventory().addItem(8494, 1);
				player.getInventory().addItem(7462, 1);
				player.getInventory().addItem(1712, 1);
				player.getInventory().addItem(3842, 1);
				player.getInventory().addItem(2414, 1);
				player.getInventory().addItem(4675, 1);
				player.getInventory().addItem(3105, 1);
				player.getInventory().addItem(1127, 1);
				player.getInventory().addItem(1079, 1);
				player.getInventory().addItem(4587, 1);
				player.getInventory().addItem(5698, 1);
				player.getInventory().addItem(6731, 1);
				player.getInventory().addItem(2503, 1);
				player.getInventory().addItem(2497, 1);
				player.getPackets().sendGameMessage("You choose Rune Pure Build");
				stage = 2;
				sendOptionsDialogue("Change Armour Look?", "No", "Yes");
				// player.unlock();
			} else if (componentId == OPTION_3) {
				player.getInterfaceManager().closeChatBoxInterface();
				player.getPrayer().restorePrayer(
						(int) ((int) (Math.floor(player.getSkills().getLevelForXp(Skills.PRAYER) * 2.5) + 990)
								* player.getAuraManager().getPrayerPotsRestoreMultiplier()));
				player.getPrayer().setPrayerBook(true);
				player.getSkills().switchXPPopup(true);
				player.getSkills().switchXPPopup(true);
				player.getCombatDefinitions().setSpellBook(1);
				player.getSkills().set(0, 75);
				player.getSkills().set(1, 75);
				player.getSkills().set(5, 99);
				player.getSkills().setXp(0, Skills.getXPForLevel(75));
				player.getSkills().setXp(1, Skills.getXPForLevel(75));
				player.getSkills().setXp(5, Skills.getXPForLevel(99));
				player.getSkills().set(2, 99);
				player.getSkills().setXp(2, Skills.getXPForLevel(99));
				player.getSkills().set(6, 99);
				player.getSkills().setXp(6, Skills.getXPForLevel(99));
				player.getSkills().set(4, 99);
				player.getSkills().setXp(4, Skills.getXPForLevel(99));
				player.getSkills().set(3, 99);
				player.getSkills().setXp(3, Skills.getXPForLevel(99));
				player.getSkills().set(23, 99);
				player.getSkills().setXp(23, Skills.getXPForLevel(99));
				player.getAppearence().generateAppearenceData();
				player.getInventory().addItem(4111, 1);
				player.getInventory().addItem(4113, 1);
				player.getInventory().addItem(10828, 1);
				player.getInventory().addItem(7462, 1);
				player.getInventory().addItem(1712, 1);
				player.getInventory().addItem(3842, 1);
				player.getInventory().addItem(2414, 1);
				player.getInventory().addItem(4675, 1);
				player.getInventory().addItem(11732, 1);
				player.getInventory().addItem(1127, 1);
				player.getInventory().addItem(1079, 1);
				player.getInventory().addItem(4151, 1);
				player.getInventory().addItem(5698, 1);
				player.getInventory().addItem(6731, 1);
				player.getInventory().addItem(2503, 1);
				player.getInventory().addItem(2497, 1);
				player.getInventory().addItem(6524, 1);
				player.getPackets().sendGameMessage("You choose Med Level Build");
				stage = 2;
				sendOptionsDialogue("Change Armour Look?", "No", "Yes");
				// player.unlock();
			} else if (componentId == OPTION_4) {
				player.getInterfaceManager().closeChatBoxInterface();
				player.getPrayer().restorePrayer(
						(int) ((int) (Math.floor(player.getSkills().getLevelForXp(Skills.PRAYER) * 2.5) + 990)
								* player.getAuraManager().getPrayerPotsRestoreMultiplier()));
				player.getPrayer().setPrayerBook(false);
				player.getSkills().switchXPPopup(true);
				player.getSkills().switchXPPopup(true);
				player.getCombatDefinitions().setSpellBook(1);
				player.getSkills().set(0, 60);
				player.getSkills().set(1, 1);
				player.getSkills().set(5, 77);
				player.getSkills().setXp(0, Skills.getXPForLevel(60));
				player.getSkills().setXp(1, Skills.getXPForLevel(1));
				player.getSkills().setXp(5, Skills.getXPForLevel(52));
				player.getSkills().set(2, 99);
				player.getSkills().setXp(2, Skills.getXPForLevel(99));
				player.getSkills().set(6, 99);
				player.getSkills().setXp(6, Skills.getXPForLevel(99));
				player.getSkills().set(4, 99);
				player.getSkills().setXp(4, Skills.getXPForLevel(99));
				player.getSkills().set(3, 99);
				player.getSkills().setXp(3, Skills.getXPForLevel(99));
				player.getSkills().set(23, 99);
				player.getSkills().setXp(23, Skills.getXPForLevel(99));
				player.getAppearence().generateAppearenceData();
				player.getInventory().addItem(6107, 1);
				player.getInventory().addItem(6108, 1);
				player.getInventory().addItem(656, 1);
				player.getInventory().addItem(7459, 1);
				player.getInventory().addItem(1712, 1);
				player.getInventory().addItem(3842, 1);
				player.getInventory().addItem(2412, 1);
				player.getInventory().addItem(4675, 1);
				player.getInventory().addItem(3105, 1);
				player.getInventory().addItem(9185, 1);
				player.getInventory().addItem(10499, 1);
				player.getInventory().addItem(9244, 100);
				player.getInventory().addItem(11091, 15);
				player.getInventory().addItem(5698, 1);
				player.getInventory().addItem(6731, 1);
				player.getInventory().addItem(2497, 1);
				player.getPackets().sendGameMessage("You choose Pure Build");
				stage = 2;
				sendOptionsDialogue("Change Armour Look?", "No", "Yes");
				// player.unlock();
			} else if (componentId == OPTION_5) {
				player.getInterfaceManager().closeChatBoxInterface();
				player.getPrayer().restorePrayer(
						(int) ((int) (Math.floor(player.getSkills().getLevelForXp(Skills.PRAYER) * 2.5) + 990)
								* player.getAuraManager().getPrayerPotsRestoreMultiplier()));
				player.getPrayer().setPrayerBook(true);
				player.getSkills().switchXPPopup(true);
				player.getSkills().switchXPPopup(true);
				player.getCombatDefinitions().setSpellBook(1);
				player.getSkills().set(0, 99);
				player.getSkills().set(1, 99);
				player.getSkills().set(5, 99);
				player.getSkills().setXp(0, Skills.getXPForLevel(99));
				player.getSkills().setXp(1, Skills.getXPForLevel(99));
				player.getSkills().setXp(5, Skills.getXPForLevel(99));
				player.getSkills().set(2, 99);
				player.getSkills().setXp(2, Skills.getXPForLevel(99));
				player.getSkills().set(6, 99);
				player.getSkills().setXp(6, Skills.getXPForLevel(99));
				player.getSkills().set(4, 99);
				player.getSkills().setXp(4, Skills.getXPForLevel(99));
				player.getSkills().set(3, 99);
				player.getSkills().setXp(3, Skills.getXPForLevel(99));
				player.getSkills().set(23, 99);
				player.getSkills().setXp(23, Skills.getXPForLevel(99));
				player.getAppearence().generateAppearenceData();
				player.getInventory().addItem(4111, 1);
				player.getInventory().addItem(4113, 1);
				player.getInventory().addItem(10828, 1);
				player.getInventory().addItem(7462, 1);
				player.getInventory().addItem(1712, 1);
				player.getInventory().addItem(3842, 1);
				player.getInventory().addItem(2414, 1);
				player.getInventory().addItem(4675, 1);
				player.getInventory().addItem(11732, 1);
				player.getInventory().addItem(1127, 1);
				player.getInventory().addItem(1079, 1);
				player.getInventory().addItem(4151, 1);
				player.getInventory().addItem(5698, 1);
				player.getInventory().addItem(6731, 1);
				player.getInventory().addItem(2503, 1);
				player.getInventory().addItem(2497, 1);
				player.getInventory().addItem(6524, 1);
				player.getPackets().sendGameMessage("You choose Maxed Main Build");
				stage = 2;
				sendOptionsDialogue("Change Armour Look?", "No", "Yes");

			}
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				player.unlock();
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				player.switchItemsLook();
				player.unlock();
				player.getInterfaceManager().closeChatBoxInterface();
			}
		}
	}

	@Override
	public void finish() {
	}

}
