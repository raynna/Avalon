package com.rs.game.player.content;

import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.rs.cores.CoresManager;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 7 May 2016
 *
 **/

public class XPHR implements Serializable {

	private static final long serialVersionUID = 6431559376950202547L;

	private transient Player player;

	private double SessionXP;
	private double XPHR;

	private HashMap<String, Integer> TempValues;
	private HashMap<String, Integer> Skill;
	private HashMap<String, Boolean> Configuration;

	private boolean StartedSession;
	private boolean SessionActive;
	private boolean CalculateXPHR;
	private boolean StartTimer;

	public boolean Stopped;

	private int time;

	public XPHR(Player player) {
		this.player = player;
		if (Skill == null)
			Skill = new HashMap<String, Integer>();
		if (Configuration == null)
			Configuration = new HashMap<String, Boolean>();
		if (TempValues == null)
			TempValues = new HashMap<String, Integer>();
		TempValues.put("TempSessionXP", 0);
		TempValues.put("TempXPHR", 0);
		Configuration.put("Timer", false);
		Configuration.put("SkillSettings", false);
	}

	public void Initiate() {
		player.getDialogueManager().startDialogue("XPHr");
	}

	public double AddSessionXP(double xp, int skill) {
		if ((skill >= 0 && skill <= 4) || skill == 6) {
			return -1;
		}
		SessionActive(true);
		AddSkill(skill, xp);
		AddTempXP(xp);
		if (!CalculateXPHR) {
			CalculateXPHR(skill);
		}
		if (!StartTimer) {
			StartTimer(skill);
		}
		return SessionXP += xp;
	}

	public void CalculateXPHR(int skill) {
		CalculateXPHR = true;
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				TempValues.put("TempXPHR", TempValues.get("TempSessionXP") * 12);
				XPHR = TempValues.get("TempXPHR");
				TempValues.put("TempSessionXP", 0);
				TempValues.put("TempXPHR", 0);
				player.getPackets()
						.sendIComponentText(3001, 1,
								(!Configuration.get("SkillSettings") ? "XP (" + Skills.SKILL_NAME[skill] + "): "
										+ Skill.get(Skills.SKILL_NAME[skill]) : "Session XP: " + SessionXP)
						+ "<br>XP/H: " + Utils.getFormattedNumber(XPHR, ',') + " (" + Utils.getFormatedTimeShort(time)
						+ ")");
				CalculateXPHR = false;
			}
		}, 5, TimeUnit.MINUTES);
	}

	public void AddSkill(int skill, double xp) {
		if (!Skill.containsKey(Skills.SKILL_NAME[skill]))
			Skill.put(Skills.SKILL_NAME[skill], 0);
		if (!Configuration.get("SkillSettings")) {
			AddSkillXP(skill, xp);
		}
	}

	public void StartTimer(int skill) {
		StartTimer = true;
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				if (Stopped) {
					stop();
					Reset();
					return;
				}
				if (time == (!Configuration.get("Timer") ? 0 : 3600)) {
					stop();
					Reset();
				}
				player.getPackets()
						.sendIComponentText(3001, 1,
								(!Configuration.get("SkillSettings") ? "XP (" + Skills.SKILL_NAME[skill] + "): "
										+ Skill.get(Skills.SKILL_NAME[skill]) : "Session XP: " + SessionXP)
						+ "<br>XP/H: " + Utils.getFormattedNumber(XPHR, ',') + " (" + Utils.getFormatedTimeShort(time)
						+ ")");
				if (!Configuration.get("Timer")) {
					time--;
				} else {
					time++;
				}
			}
		}, 0, 1);
	}

	public void Reset() {
		OpenStatistics();
		Skill.clear();
		player.getInterfaceManager().closeHealth(false);
		player.getPackets().sendIComponentText(3001, 1, "");
		SessionActive(false);
		StartedSession(false);
		SessionXP = 0;
		XPHR = 0;
		time = !getConfig("Timer") ? 3600 : 0;
		TempValues.put("TempXPHR", 0);
		TempValues.put("TempSessionXP", 0);
		CalculateXPHR = false;
		StartTimer = false;
		Stopped = false;
	}

	private void OpenStatistics() {
		player.getInterfaceManager().sendInterface(275);
		for (int i = 0; i < 100; i++)
			player.getPackets().sendIComponentText(275, i, "");
		player.getPackets().sendIComponentText(275, 1, "Statistics");
		player.getPackets().sendIComponentText(275, 13, "Total Gained XP: " + Utils.getFormattedNumber(SessionXP, ',')
				+ " XP <br>Average XP/H " + Utils.getFormattedNumber(XPHR, ','));
		player.getPackets().sendIComponentText(275, 15, "XP in individual skills");
		player.getPackets().sendIComponentText(275, 16,
				Skill.toString().replace("{", "").replace("}", "").replace("=", " = ").replace(",", "<br>") + " XP");
	}

	public void AddSkillXP(int skill, double xp) {
		if (Skill.containsKey(Skills.SKILL_NAME[skill]))
			Skill.put(Skills.SKILL_NAME[skill], (int) (Skill.get(Skills.SKILL_NAME[skill]) + xp));
	}

	public double AddTempXP(double xp) {
		return TempValues.put("TempSessionXP", (int) (TempValues.get("TempSessionXP") + xp));
	}

	public HashMap<String, Boolean> getConfig() {
		return Configuration;
	}

	public HashMap<String, Integer> getSkill() {
		return Skill;
	}

	public boolean getConfig(String setting) {
		return Configuration.get(setting);
	}

	public int setTimer(int timer) {
		return time = timer;
	}

	public boolean SessionActive(boolean toggle) {
		return SessionActive = toggle;
	}

	public boolean isInSession() {
		return SessionActive;
	}

	public boolean StartedSession(boolean toggle) {
		return StartedSession = toggle;
	}

	public boolean hasStartedSession() {
		return StartedSession;
	}

}
