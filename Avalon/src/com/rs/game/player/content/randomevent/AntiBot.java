package com.rs.game.player.content.randomevent;

import com.rs.Settings;
import com.rs.game.player.Player;
import com.rs.game.player.content.customtab.JournalTab;
import com.rs.utils.Utils;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Rain
 * @Date - 23 Jan 2016
 *
 **/

public class AntiBot {

	public int appearTime;
	public boolean hasEvent;
	public String correctAnswer, hint;

	public static String[][] Questions = {
			{ "What is the name of this server?", Settings.SERVER_NAME, "" },
			{ "Spell Avalon backwards", "nolava", "" },
			{ "Who's the owner of " + Settings.SERVER_NAME +"?", "Andreas", "Hint: Andr.." },
			{ "What is the famous last name of RuneScape creators?.", "Gower", "Hint: G_W_ER" },
			{ "What is the name of the CAPE with FIRE?", "Fire cape", "" } };

	public static AntiBot instance;

	public static AntiBot getInstance() {
		if (instance == null)
			instance = new AntiBot();
		return instance;
	}

	public void start(Player player) {
		if (hasEvent || player.getControlerManager().getControler() != null
				|| player.getAttackedByDelay() + 10000 > Utils.currentTimeMillis() || player.isLocked()
				|| player.getInterfaceManager().containsScreenInter()) {
			setTimer(4800);
			player.sm("You are busy.");
			return;
		}
		hasEvent(true);
		player.getInterfaceManager().sendTabInterfaces(true);
		player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 114 : 174, 865);
		player.getPackets().sendIComponentText(928, 10, "<col=ffc800>A wild event has appeared!");
		player.getPackets().sendIComponentText(928, 16, "<col=ffc800>" + generateQuestion() + ""
				+ "<br><br><col=ffc800>" + getHint() + "<br><br><col=ffc800>Type the answer in the chatbox.");
		JournalTab.open(player);
		player.stopAll(true);
		player.lock();
	}

	public boolean verify(Player player, String answer) {
		if (answer == null) {
			player.sm("There is nothing to answer at the moment.");
			return false;
		}
		if (answer.equalsIgnoreCase(getCorrectAnswer())) {
			player.unlock();
			player.getMoneyPouch().addMoney(50000, false);
			player.sm("Way to go! You can now continue playing." + (player.isDeveloper()
					? " Time left to next round: " + Utils.getFormatedTime(appearTime) + "" : ""));
			player.getInterfaceManager().sendTabInterfaces(false);
			hasEvent(false);
			setTimer(Utils.random(4200, 5800));
			return true;
		}
		return false;
	}

	public void setTimer(int time) {
		this.appearTime = time;
	}

	public void hasEvent(boolean hasEvent) {
		this.hasEvent = hasEvent;
	}

	public void destroy(Player player) {
		hasEvent(false);
	}

	public void setHint(String hint) {
		if (hint == null)
			return;
		getInstance().hint = hint;
	}

	public String getHint() {
		return hint;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setAnswer(String answer) {
		if (answer == null)
			return;
		getInstance().setCorrectAnswer(answer);
	}

	public String generateQuestion() {
		int questions = Utils.random(0, Questions.length - 1);
		setAnswer(Questions[questions][1]);
		setHint(Questions[questions][2]);
		return Questions[questions][0];
	}

}
