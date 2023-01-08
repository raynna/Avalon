package com.rs.game.player.dialogues.player;

import java.io.File;
import java.io.IOException;

import com.rs.game.player.Player;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class StaffOptions extends Dialogue {

	private transient Player target = player;
	private String name = "null";
	private final int cp1 = OPTION_1, cp2 = OPTION_2, cp3 = OPTION_3, cp4 = OPTION_4, cp5 = OPTION_5;
	private boolean online;

	@Override
	public void start() {
		try {
			online = (boolean) parameters[1];
			if (!online) {
				name = (String) parameters[0];
				online = false;
			} else {
				target = (Player) parameters[0];
			}
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("Error setting parameters in com.rs.game.player.dialogues.cbook.Punish.java");
			player.getPackets().sendGameMessage("Logic error try again or contact a developer.");
			player.getDialogue().finish();
			return;
		}

		if (!isStaff()) {
			System.out.println("Player accessing punishment dialogue ? Check account: " + player.getUsername());
			player.getDialogue().finish();
			return;
		}

		sendOptionsDialogue(
				"Options for " + (online ? target.getDisplayName() : Utils.formatPlayerNameForDisplay(name)), "Kick",
				"Jail", "Mute", "Blackmark", "More");
	}

	@Override
	public void run(int interfaceId, int componentId) {

		if (stage == -1) {
			switch (componentId) {
			case cp1:
				kickPlayer(target);
				break;
			case cp2:
				stage = 1;
				sendOptionsDialogue(
						"Jail " + (online ? target.getDisplayName() : Utils.formatPlayerNameForDisplay(name)), "X Days",
						"Permanent", "Cancel");
				break;
			case cp3:
				stage = 2;
				sendOptionsDialogue(
						"Mute " + (online ? target.getDisplayName() : Utils.formatPlayerNameForDisplay(name)), "X Days",
						"Permanent", "Cancel");
				break;
			case cp4:
				player.temporaryAttribute().put("QUESTION_SCRIPT", new Object[] { "blackmark", target, online, name });
				player.getPackets().sendRunScript(110, "Enter a brief reason for this punishment:");
				end();
				break;
			case cp5:
				stage = 4;
				sendOptionsDialogue(
						"Options for " + (online ? target.getDisplayName() : Utils.formatPlayerNameForDisplay(name)),
						"Unjail", "Unmute", "Clear Blackmarks", "View Account", "More");
				break;
			}
		} else if (stage == 1) {
			switch (componentId) {
			case cp1:
				sendPunishment("jail", target);
				break;
			case cp2:
				sendPunishment("permjail", target);
				break;
			case cp3:
				end();
				break;
			}

		} else if (stage == 2) {
			switch (componentId) {
			case cp1:
				sendPunishment("mute", target);
				break;
			case cp2:
				sendPunishment("permmute", target);
				break;
			case cp3:
				end();
				break;
			}

		} else if (stage == 4) {
			switch (componentId) {
			case cp1:
				undoPunishment((online ? target : name), online, "jail");
				break;
			case cp2:
				undoPunishment((online ? target : name), online, "mute");
				break;
			case cp3:
				undoPunishment((online ? target : name), online, "blackmark");
				break;
			case cp4:
				stage = 5;
				sendOptionsDialogue(
						"View Account for "
								+ (online ? target.getDisplayName() : Utils.formatPlayerNameForDisplay(name)),
						"Account Information", "Account Bank and Inventory", "Account GE and Equipment",
						"Account Trade/Stake/Drop history", "IP and MAC history");
				break;
			case cp5:
				stage = -1;
				sendOptionsDialogue(
						"Options for " + (online ? target.getDisplayName() : Utils.formatPlayerNameForDisplay(name)),
						"Kick", "Jail", "Mute", "Blackmark", "More");
				break;
			}
		} else if (stage == 5) {
			switch (componentId) {
			case cp1:
				accountInfo(online, target, new String[] { "info", name });
				break;
			case cp2:
				accountInfo(online, target, new String[] { "bank", name });
				break;
			case cp3:
				accountInfo(online, target, new String[] { "equipment", name });
				break;
			case cp4:
				accountInfo(online, target, new String[] { "logs", name });
				break;
			case cp5:
				accountInfo(online, target, new String[] { "iphist", name });
				break;
			}
		}
	}

	@Override
	public void finish() {
	}

	private void accountInfo(boolean active, Player target, String[] args) {
		switch (args[0].toLowerCase()) {
		case "bank":
			if (active) {
				try {
					player.getPackets().sendItems(95, target.getBank().getContainerCopy());
					player.getBank().openPlayerBank(target);
					player.getPackets().sendItems(93, target.getInventory().getItems());
					player.getPackets()
							.sendColoredMessage(target.getDisplayName() + " has "
									+ Utils.getFormattedNumber(target.getMoneyPouchValue(), ',')
									+ " coins in their money pouch", false);

				} catch (Exception e) {
					player.getPackets().sendGameMessage(Utils.formatPlayerNameForDisplay(name) + " wasn't found.");
				}
			} else {
				String accName = args[1];
				File acc = new File("data/characters/" + accName.replace(" ", "_") + ".p");
				try {
					Player targ = (Player) SerializableFilesManager.loadSerializedFile(acc);
					player.getPackets().sendItems(95, targ.getBank().getContainerCopy());
					player.getBank().openPlayerBank(targ);
					player.getPackets().sendItems(93, targ.getInventory().getItems());
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
			break;
		case "equipment":
			break;
		case "logs":
			break;
		case "iphist":
			break;
		}
		end();
	}

	boolean isStaff() {
		return player.isStaff();
	}

	void viewBank(boolean active, Player targ, String n) {
		if (!active) {

		} else {

		}
	}

	void kickPlayer(Player target) {
		if (target == null || !target.isActive()) {
			player.getPackets().sendGameMessage("The target does not appear to be online.");
			end();
			return;
		} else {
			player.getPackets().sendGameMessage("You have kicked %s from the game.", target.getDisplayName());
			target.forceLogout();
			end();
			return;
		}
	}

	void undoPunishment(Object targetInfo, boolean online, String punishment) {
		if (!online) {
			String accName = (String) targetInfo;
			File acc = new File("data/characters/" + accName.replace(" ", "_") + ".p");
			Player targ = null;
			try {
				targ = (Player) SerializableFilesManager.loadSerializedFile(acc);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			switch (punishment.toLowerCase()) {
			case "mute":
				player.getPackets().sendGameMessage("You have lifted the mute for %s.",
						Utils.formatPlayerNameForDisplay(accName));
				targ.liftMute(false, player);
				break;
			}
			try {
				SerializableFilesManager.storeSerializableClass(targ, acc);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			Player target = (Player) targetInfo;
			switch (punishment.toLowerCase()) {
			case "mute":
				player.getPackets().sendGameMessage("You have lifted the mute for %s.", target.getDisplayName());
				target.liftMute(false, player);
				break;
			}
		}
		end();
	}

	void sendPunishment(String key, Object target) {
		switch (key) {
		case "jail":
			player.temporaryAttribute().put("QUESTION_SCRIPT",
					new Object[] { "xjail", (online ? (Player) target : (String) target), online, name });
			player.getPackets().sendInputIntegerScript(true, "Enter the amount of days to jail "
					+ (online ? ((Player) target).getDisplayName() : name) + " for:");
			break;
		case "permjail":
			player.temporaryAttribute().put("QUESTION_SCRIPT",
					new Object[] { "permjail", (online ? (Player) target : (String) target), online, name });
			player.getPackets().sendRunScript(110, new Object[] { "Enter a brief reason for this punishment:" });
			break;
		case "mute":
			player.temporaryAttribute().put("QUESTION_SCRIPT",
					new Object[] { "xmute", (online ? (Player) target : (String) target), online, name });
			player.getPackets().sendInputIntegerScript(true, "Enter the amount of days to mute "
					+ (online ? ((Player) target).getDisplayName() : name) + " for:");
			break;
		case "permmute":
			player.temporaryAttribute().put("QUESTION_SCRIPT",
					new Object[] { "permmute", (online ? (Player) target : (String) target), online, name });
			player.getPackets().sendRunScript(110, new Object[] { "Enter a brief reason for this punishment:" });
			break;
		}
		end();
	}

}
