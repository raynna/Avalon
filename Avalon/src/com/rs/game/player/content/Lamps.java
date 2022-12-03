package com.rs.game.player.content;

import com.rs.Settings;
import com.rs.cache.loaders.ClientScriptMap;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class Lamps {

	public static final int LAMP_SMALL = 0;
	public static final int LAMP_MEDIUM = 1;
	public static final int LAMP_BIG = 2;
	public static final int LAMP_HUGE = 3;

	public static final int[] SELECTABLE_XP_LAMPS = new int[] { 23713, 23714, 23715, 23716, 18782 };
	public static final int[] SELECTABLE_XP_LAMPS_TYPES = new int[] { LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE,
			LAMP_BIG };
	public static final int[][] SKILL_LAMPS = new int[][] { { 23717, 23718, 23719, 23720 },
			{ 23725, 23726, 23727, 23728 }, { 23721, 23722, 23723, 23724 }, { 23753, 23754, 23755, 23756 },
			{ 23729, 23730, 23731, 23732 }, { 23737, 23738, 23739, 23740 }, { 23733, 23734, 23735, 23736 },
			{ 23798, 23799, 23800, 23801 }, { 23806, 23807, 23808, 23809 }, { 23774, 23775, 23776, 23777 },
			{ 23794, 23795, 23796, 23797 }, { 23802, 23803, 23804, 23805 }, { 23769, 23770, 23771, 23773 },
			{ 23790, 23791, 23792, 23793 }, { 23786, 23787, 23788, 23789 }, { 23761, 23762, 23763, 23764 },
			{ 23757, 23758, 23759, 23760 }, { 23765, 23766, 23767, 23768 }, { 23778, 23779, 23780, 23781 },
			{ 23810, 23811, 23812, 23813 }, { 23741, 23742, 23743, 23744 }, { 23782, 23783, 23784, 23785 },
			{ 23745, 23746, 23747, 3748 }, { 23814, 23815, 23816, 23817 }, { 23749, 23750, 23751, 23752 } };
	public static final int[][] SKILL_LAMPS_TYPES = new int[][] { { LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE },
			{ LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE }, { LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE },
			{ LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE }, { LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE },
			{ LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE }, { LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE },
			{ LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE }, { LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE },
			{ LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE }, { LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE },
			{ LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE }, { LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE },
			{ LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE }, { LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE },
			{ LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE }, { LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE },
			{ LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE }, { LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE },
			{ LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE }, { LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE },
			{ LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE }, { LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE },
			{ LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE }, { LAMP_SMALL, LAMP_MEDIUM, LAMP_BIG, LAMP_HUGE }, };

	public static final int[] OTHER_LAMPS = new int[] {};

	private static final int[] DIALOGUE_INTERFACE_C2S = new int[] { Skills.ATTACK, Skills.MAGIC, Skills.MINING,
			Skills.WOODCUTTING, Skills.AGILITY, Skills.FLETCHING, Skills.THIEVING, Skills.STRENGTH, Skills.RANGE,
			Skills.SMITHING, Skills.FIREMAKING, Skills.HERBLORE, Skills.SLAYER, Skills.CONSTRUCTION, Skills.DEFENCE,
			Skills.PRAYER, Skills.FISHING, Skills.CRAFTING, Skills.FARMING, Skills.HUNTER, Skills.SUMMONING,
			Skills.HITPOINTS, Skills.DUNGEONEERING, Skills.COOKING, Skills.RUNECRAFTING };


	private static final double[] BASE_LAMPS_XP = new double[] { 62.5, 69, 77, 85, 94, 104, 115, 127, 139, 154, 170,
			188, 206, 229, 252, 262, 274, 285, 298, 310, 325, 337, 352, 367.5, 384, 399, 405, 414, 453, 473, 514, 528,
			536, 551, 583, 609, 635, 662, 692, 721, 752, 785, 818, 854, 890, 929, 971, 1013, 1055, 1101, 1149, 1200,
			1250, 1305, 1362, 1422, 1485, 1542, 1617, 1685, 1758, 1836, 1912, 2004.5, 2085, 2172, 2269, 2379, 2471,
			2593, 2693, 2810, 2947, 3082, 3214, 3339, 3496, 3648, 3793, 3980, 4166, 4348, 4522, 4762, 4919, 5150, 5376,
			5593, 5923, 6122, 6452, 6615, 6929, 7236, 7533, 8065, 8348, 8602 };

	public static void processLampClick(Player player, int slot, int id) {

		if (isSelectable(id)) {
			openSelectableDialog(player, slot, id);
		} else if (isSkillLamp(id)) {
			openSkillDialog(player, slot, id);
		} else if (isOtherLamp(id)) {
			
		}
	}

	private static void openSelectableDialog(Player player, final int slot, final int id) {
		player.getDialogueManager().startDialogue(new Dialogue() {
			private int selectedSkill = -1;

			@Override
			public void start() {
				player.getInterfaceManager().sendInterface(1263);
				player.getPackets().sendGlobalString(358, "What sort of XP would you like?");
				sendSelectedSkill();
				player.getVarsManager().sendVarBit(2187, 1);//unlock construction skill
				player.getPackets().sendGlobalConfig(1797, 0); // selectable lamps don't show xp
				player.getPackets().sendGlobalConfig(1798, 1); // minimum level of 1 to show
				player.getPackets().sendGlobalConfig(1799, id);

				for (int i = 13; i < 44; i++)
					player.getPackets().sendUnlockIComponentOptionSlots(1263, i, -1, 0, 0);

			}

			@Override
			public void run(int interfaceId, int componentId) {
				if (componentId >= 13 && componentId <= 37) {
					int skill = DIALOGUE_INTERFACE_C2S[componentId - 13];
						// client auto sends message that this skill can't be given exp without a house
						selectedSkill = skill;
						sendSelectedSkill();
						player.getPackets().sendUnlockIComponentOptionSlots(1263, 43, -1, 0, true, 0); // not the right way but eh
				} else if (componentId == 43 && selectedSkill != -1) {
					if (!player.getInventory().containsItem(id, 1)) {
						end();
						return;
					}
					player.getInventory().deleteItem(slot, new Item(id, 1));
					double exp = getExp(player.getSkills().getLevelForXp(selectedSkill), selectableLampType(id));
					player.getSkills().addXpNoBonus(selectedSkill, exp);
					player.getInterfaceManager().closeScreenInterface();
					player.getDialogueManager().startDialogue("SimpleMessage",
							"<col=0000ff>Your wish has been granted!</col>",
							"You have been awarded " + Utils.getFormattedNumber(exp, ',') + " XP in "
									+ Skills.SKILL_NAME[selectedSkill] + "!");
				} else if (componentId == 44 || componentId == 7) {
					end();
				}
			}

			@Override
			public void finish() {
				if (player.getInterfaceManager().containsScreenInter())
					player.getInterfaceManager().closeScreenInterface();
			}

			private void sendSelectedSkill() {
				ClientScriptMap map = ClientScriptMap.getMap(681);
				if (selectedSkill == map.getDefaultIntValue()) {
					player.getPackets().sendGlobalConfig(1796, map.getDefaultIntValue());
					return;
				}
				long key = map.getKeyForValue(selectedSkill);
				player.getPackets().sendGlobalConfig(1796, (int) key);
			}
		});
	}

	private static void openSkillDialog(Player player, final int slot, final int id) {
		final int type = skillLampType(id);
		final int skillId = skillLampSkillId(id);

		player.getDialogueManager().startDialogue(new Dialogue() {
			@Override
			public void start() {
				sendOptionsDialogue("Rub Lamp?", "Gain <col=ff0000>" + Skills.SKILL_NAME[skillId] + "</col> experience",
						"Cancel");
			}

			@Override
			public void run(int interfaceId, int componentId) {
				if (componentId != Dialogue.OPTION_1 || !player.getInventory().containsItem(id, 1)) {
					end();
					return;
				}

				player.getInventory().deleteItem(slot, new Item(id, 1));
				double exp = getExp(player.getSkills().getLevelForXp(skillId), type) * Settings.SKILLING_XP_RATE;
				player.getSkills().addXpNoBonus(skillId, exp);
				player.getDialogueManager().startDialogue("SimpleMessage",
						"<col=0000ff>Your wish has been granted!</col>", "You have been awarded "
								+ Utils.getFormattedNumber(exp, ',') + " XP in " + Skills.SKILL_NAME[skillId] + "!");
			}

			@Override
			public void finish() {
			}

		});
	}

    private static double getExp(int skillLevel, int lampType) {
    	//developers choice to multiply xp by. 8.6k no bonuses (99 skill)
    	return skillLevel <= BASE_LAMPS_XP.length ? BASE_LAMPS_XP[skillLevel - 1] : BASE_LAMPS_XP[BASE_LAMPS_XP.length - 1] * 3D;
    }

	private static int selectableLampType(int id) {
		for (int i = 0; i < SELECTABLE_XP_LAMPS.length; i++) {
			if (SELECTABLE_XP_LAMPS[i] == id)
				return SELECTABLE_XP_LAMPS_TYPES[i];
		}
		return -1;
	}

	public static boolean isSelectable(int id) {
		for (int i = 0; i < SELECTABLE_XP_LAMPS.length; i++) {
			if (SELECTABLE_XP_LAMPS[i] == id)
				return true;
		}
		return false;
	}

	private static int skillLampType(int id) {
		for (int skillId = 0; skillId < SKILL_LAMPS.length; skillId++) {
			for (int i = 0; i < SKILL_LAMPS[skillId].length; i++) {
				if (SKILL_LAMPS[skillId][i] == id)
					return SKILL_LAMPS_TYPES[skillId][i];
			}
		}
		return -1;
	}

	private static int skillLampSkillId(int id) {
		for (int skillId = 0; skillId < SKILL_LAMPS.length; skillId++) {
			for (int i = 0; i < SKILL_LAMPS[skillId].length; i++) {
				if (SKILL_LAMPS[skillId][i] == id)
					return skillId;
			}
		}
		return -1;
	}

	public static boolean isSkillLamp(int id) {
		for (int skillId = 0; skillId < SKILL_LAMPS.length; skillId++) {
			for (int i = 0; i < SKILL_LAMPS[skillId].length; i++) {
				if (SKILL_LAMPS[skillId][i] == id)
					return true;
			}
		}
		return false;
	}

	public static boolean isOtherLamp(int id) {
		for (int i = 0; i < OTHER_LAMPS.length; i++) {
			if (OTHER_LAMPS[i] == id)
				return true;
		}
		return false;
	}

}
