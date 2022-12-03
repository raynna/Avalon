package com.rs.game.player.content;

import com.rs.game.World;
import com.rs.game.player.Player;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Rain
 * @Date - 6 Feb 2016
 *
 **/
public class TicketSystem {

	public static void openTicket(Player player) {
		player.isRequestingChat = true;
		player.getDialogueManager().startDialogue("SimpleMessage", "Your ticket has successfully been created.<br>"
				+ "A staff member will be with you as soon as one is available.");
		for (Player staff : World.getPlayers()) {
			if (staff.isDeveloper() || staff.isModerator()) {
				staff.getPackets().receivePrivateMessage("Ticket System", "Ticket System", 2, player.getDisplayName()
						+ " is requesting for help. Answer with this syntax: ;;openticket " + player.getDisplayName());
			}
		}
	}

	public static void closeTicket(Player player) {
		if (!player.isInLiveChat) {
			player.sm("You are currently not in a chat room.");
			return;
		}
		player.isInLiveChat = false;
		if (player.isDeveloper() || player.isModerator()) {
			Player requester = World.getPlayerByDisplayName(player.ticketsender);
			removeTicket(player, requester);
			player.sm("<col=ff0000> " + player.getDisplayName() + " has closed their ticket.</col>");
			requester.sm("<col=ff0000> " + player.getDisplayName() + " has closed your ticket.</col>");
		} else {
			Player staff = World.getPlayerByDisplayName(player.ticketstaff);
			removeTicket(player, staff);
			player.sm("<col=0000cc>>><col=ff0000> " + player.getDisplayName() + " has closed your ticket.</col>");
			staff.sm("<col=0000cc>>><col=ff0000> " + player.getDisplayName() + " has closed their ticket.</col>");
			staff.isInLiveChat = false;
		}
	}

	public static void handleChat(Player player, String message) {
			Player staff = World.getPlayerByDisplayName(player.ticketstaff);
			player.sm("[You] ->: " + message);
			staff.sm("[Requester] ->: " + message);
	}

	public static void answerTicket(Player requester, Player staff) {
		requester.isRequestingChat = false;
		requester.isInLiveChat = true;
		staff.isInLiveChat = true;
		staff.ticketsender = staff.getDisplayName();
		requester.ticketstaff = staff.getDisplayName();
		staff.sm("<col=0000cc>>> <col=ff0000>" + getIcon(staff) + staff.getDisplayName()
				+ " -></col><col=0000cc> Hello, how may I help you? Start your message with >> to respond.");
		requester.sm("<col=0000cc>>> <col=ff0000>" + getIcon(staff) + staff.getDisplayName()
				+ " -></col><col=0000cc> Hello, how may I help you? Start your message with >> to respond.");
	}

	public static void removeTicket(Player p1, Player p2) {
			for (Player requester : World.getPlayers()) {
				if (requester.isRequestingChat) {
					p1.sm("Open {" + p1.getDisplayName() + " }");
				}
		}
		p1.isInLiveChat = false;
		p2.isInLiveChat = false;
	}

	public static String getIcon(Player player) {
		if (player.isDeveloper())
			return "<img=1>";
		if (player.isModerator())
			return "<img=0>";
		if (player.isMember())
			return "<img=10>";
		return "";
	}

	public static void handleTicketOnLogin(Player player) {
		if (player.isInLiveChat) {
			player.isInLiveChat = false;
		}
		if (player.isRequestingChat) {
			for (Player p2 : World.getPlayers()) {
				if (p2.isDeveloper() || p2.isModerator()) {
					p2.getPackets().receivePrivateMessage("Ticket", "Ticket", 2,
							player.getDisplayName() + " has just logged in and still has an unanswered ticket.");
				}
			}
		}
	}

	public static void destroyChatOnLogOut(Player player) {
		if (!player.isInLiveChat)
			return;
		if (player.isDeveloper() || player.isModerator()) {
			Player requester = World.getPlayerByDisplayName(player.ticketsender);
			Player staff = World.getPlayerByDisplayName(player.ticketstaff);
			removeTicket(player, requester);
			requester.isInLiveChat = false;
			requester.sm(player.getDisplayName()
					+ " has closed your ticket. This was due to them logging out, if you still require help, you can request a new one.");
			staff.isInLiveChat = false;
			removeTicket(player, staff);
		}
	}

}
