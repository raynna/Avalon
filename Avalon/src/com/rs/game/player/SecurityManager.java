package com.rs.game.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rs.utils.Utils;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 26 Mar 2016
 * 
 */

public class SecurityManager implements Serializable {

	private static final long serialVersionUID = -1394761641357936615L;

	/** The player. */
	private transient Player player;

	/** Secuirty strings */
	public String LoginCode, lastChanged, LastSignedIn, LastSignedInIP, Birthday;

	/** Secuirty timers. */
	public long TrustedIPTime, RequestedRemoveTime, RequestedChangeTime;

	/** Login attempts, locks account at 0. */
	public int loginAttempts = 5;

	/** Extra checks. */
	public boolean TypingEmail, lockedAccount;

	/** The valid email address regex. */
	private final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	/** The Email list. */
	private List<String> Email;

	/** The ips list. */
	private List<String> IPS;

	/** The Trusted ips list. */
	private List<String> TrustedIPS;

	/**
	 * Sets the player.
	 *
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Instantiates a new security manager. - Constructor
	 */
	public SecurityManager() {
		TrustedIPS = new ArrayList<String>();
		Email = new ArrayList<String>();
		IPS = new ArrayList<String>();
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public List<String> getEmail() {
		return Email;
	}

	/**
	 * Gets the registered ips.
	 *
	 * @return the registered ips
	 */
	public List<String> getRegisteredIPS() {
		return IPS;
	}

	/**
	 * Gets the trusted ips.
	 *
	 * @return the trusted ips
	 */
	public List<String> getTrustedIPS() {
		return TrustedIPS;
	}

	/**
	 * Adds the email.
	 *
	 * @param email
	 *            the email
	 * @return true, if successful
	 */
	public boolean AddEmail(String email) {
		email = email.toLowerCase();
		if (email == null) {
			return false;
		}
		if (getEmail().contains(email)) {
			player.sm("This e-mail already exists.");
			return false;
		}
		if (email.equalsIgnoreCase("exit") || email.equalsIgnoreCase("close")) {
			player.getInterfaceManager().sendTabInterfaces(false);
			player.unlock();
			Email(false);
			return false;
		}
		if (!CheckEmailRequirement(email)) {
			player.sm("<u><col=ff0000>Your email is missing something.");
			return false;
		}
		Email(false);
		player.getInterfaceManager().sendTabInterfaces(false);
		player.unlock();
		Email.add(email);
		player.sm("The e-mail " + email + " has successfully been added to your account.");
		return true;
	}

	/**
	 * Trust ip.
	 *
	 * @param IP
	 *            the ip
	 */
	public void TrustIP(String IP) {
		if (getTrustedIPS().contains(IP)) {
			player.getDialogueManager().startDialogue("SimpleMessage",
					"The Trusted IP: " + IP + ", is already stored in your account.");
			return;
		}
		getTrustedIPS().add(IP);
	}

	/**
	 * Adds the registered ip.
	 *
	 * @param IP
	 *            the ip
	 */
	public void addRegisteredIP(String IP) {
		if (getRegisteredIPS().contains(IP)) {
			return;
		}
		getRegisteredIPS().add(IP);
	}

	/**
	 * Security Check.
	 */
	public void check() {
		if (LoginCode != null) {
			if (TrustedIPS.contains(player.getSession().getIP()))
				return;
			player.lock();
			player.getPackets().sendRunScript(109,
					loginAttempts == 1
							? "<col=ff0000>Enter your login code, last attempt or your account will be locked</col>:"
							: "Please enter your Login Code to procceed: (" + loginAttempts + " attempts left)");
			player.temporaryAttribute().put("enterlogincode", true);
			player.getInterfaceManager().sendTabInterfaces(true);
		}
	}

	/**
	 * Displays registered emails.
	 */
	public void displayRegisteredEmails() {
		if (Email.size() < 1) {
			player.sm("There are no emails linked to your account.");
			return;
		}
		player.getInterfaceManager().sendInterface(275);
		for (int i = 0; i < 100; i++)
			player.getPackets().sendIComponentText(275, i, "");
		player.getPackets().sendIComponentText(275, 1, "Registered email(s)");
		for (int i = 0; i < Email.size(); i++) {
			player.getPackets().sendIComponentText(275, 10 + i, Email.get(i));
		}
		UpdateLastChanged();
	}

	/**
	 * Displays registered ips.
	 */
	public void displayRegisteredIPS() {
		int unknownIPS = 0;
		player.getInterfaceManager().sendInterface(275);
		for (int i = 0; i < 100; i++)
			player.getPackets().sendIComponentText(275, i, "");
		player.getPackets().sendIComponentText(275, 1, "Registered IP(s)");
		for (int i = 0; i < IPS.size(); i++) {
			player.getPackets().sendIComponentText(275, 10 + i,
					IPS.get(i) + " - " + Utils.getCountryName(IPS.get(i), true));
			if (!player.getSession().getIP().equalsIgnoreCase(IPS.get(i)))
				unknownIPS++;
			player.getPackets().sendIComponentText(275, IPS.size() + 14, "<col=ff0000> There are " + unknownIPS
					+ (unknownIPS > 1 ? " IPS" : " IP") + " that does not match to yours.");

		}
		UpdateLastChanged();
	}

	/**
	 * Updates last changed settings.
	 */
	public void UpdateLastChanged() {
		lastChanged = currentTime();
	}

	/**
	 * Updates last signed in.
	 */
	public void UpdateLastSignedIn() {
		addRegisteredIP(player.getSession().getIP());
		if (LastSignedInIP != null)
		player.sm("You last logged in " + (LastSignedIn != null ? LastSignedIn : "") + " from <col=ff0000>"
				+ (Utils.getCountryName(LastSignedInIP, false)));
		LastSignedIn = currentTime();
		LastSignedInIP = player.getSession().getIP();
	}

	/**
	 * Current time.
	 *
	 * @param dateFormat
	 *            the date format
	 * @return the string
	 */
	public String currentTime() {
		return Utils.GrabCountryDayTimeMonth(false);
	}

	/**
	 * Gets the timer.
	 *
	 * @param type
	 *            the type
	 * @return the timer
	 */
	public long getTimer(int type) {
		if (type == 1) {
			return TrustedIPTime;
		} else if (type == 2) {
			return RequestedRemoveTime;
		} else if (type == 3) {
			return RequestedChangeTime;
		} else {
			return -1;
		}
	}

	/**
	 * Sets the timer.
	 *
	 * @param type
	 *            the type
	 * @param time
	 *            the time
	 * @return the long
	 */
	public long setTimer(int type, long time) {
		if (type == 1) {
			return TrustedIPTime = time;
		} else if (type == 2) {
			return RequestedRemoveTime = time;
		} else if (type == 3) {
			return RequestedChangeTime = time;
		} else {
			return -1;
		}
	}

	/**
	 * Email.
	 *
	 * @param value
	 *            the value
	 * @return true, if successful
	 */
	public boolean Email(boolean value) {
		return TypingEmail = value;
	}

	/**
	 * Checks if is typing.
	 *
	 * @return true, if is typing
	 */
	public boolean isTyping() {
		return TypingEmail;
	}

	/**
	 * Check email requirement.
	 *
	 * @param email
	 *            the email
	 * @return true, if successful
	 */
	public boolean CheckEmailRequirement(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}

}