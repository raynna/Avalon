package com.rs.game.player.content.customtab;

import java.util.Map.Entry;

import com.rs.game.World;
import com.rs.game.player.AccountCreation;
import com.rs.game.player.Player;
import com.rs.game.player.content.presets.Preset;
import com.rs.utils.Utils;

public class GearTab extends CustomTab {

	public static void refresh(Player player) {
		int i = 3;
		player.getTemporaryAttributes().put("CUSTOMTAB", 3);
		player.getTemporaryAttributes().remove("ACHIEVEMENTTAB");
		player.getTemporaryAttributes().remove("SELECTEDGEAR");
		player.getPackets().sendIComponentText(3002, 25, "Gear Setups");
		player.getPackets().sendHideIComponent(3002, PURPLE_STAR_COMP, false);
		player.getPackets().sendHideIComponent(3002, YELLOW_STAR_COMP, false);
		player.getPackets().sendIComponentSprite(3002, PURPLE_STAR_COMP, 1842);
		player.getPackets().sendIComponentSprite(3002, YELLOW_STAR_COMP, 1845);
		for (Entry<String, Preset> gear : player.getPresetManager().PRESET_SETUPS.entrySet()) {
			if (gear != null) {
				player.getPackets().sendHideIComponent(3002, i, false);
				player.getPackets().sendIComponentText(3002, i, (gear.getKey()) + "");
			}
			i++;
		}
	}

	public static void open(Player player, String name) {
		String otherName = Utils.formatPlayerNameForDisplay(name);
		Player p2 = World.getPlayerByDisplayName(otherName);
		if (p2 == null && name != null)
			p2 = AccountCreation.loadPlayer(otherName);
		for (int i = 3; i <= 22; i++) {
			player.getPackets().sendHideIComponent(3002, i, true);
		}
		for (int i = 28; i <= 56; i++) {
			player.getPackets().sendHideIComponent(3002, i, true);
		}
		if (p2 == null) {
			player.getTemporaryAttributes().remove("OTHERPRESET_NAME");
		}
		player.getPackets().sendHideIComponent(3002, 24, true);
		player.getTemporaryAttributes().put("CUSTOMTAB", 3);
		player.getTemporaryAttributes().remove("ACHIEVEMENTTAB");
		player.getTemporaryAttributes().remove("SELECTEDGEAR");
		player.getPackets().sendHideIComponent(3002, BACK_BUTTON, false);
		player.getPackets().sendHideIComponent(3002, FORWARD_BUTTON, true);
		player.getPackets().sendHideIComponent(3002, BLUE_STAR_COMP, false);
		player.getPackets().sendIComponentSprite(3002, BLUE_STAR_COMP, 8553);// 9747
		player.getPackets().sendHideIComponent(3002, GREEN_STAR_COMP, false);
		player.getPackets().sendIComponentSprite(3002, RED_STAR_COMP, 675);
		if (p2 == null) {
			player.getPackets().sendHideIComponent(3002, PURPLE_STAR_COMP, false);
			player.getPackets().sendHideIComponent(3002, YELLOW_STAR_COMP, false);
			player.getPackets().sendIComponentSprite(3002, PURPLE_STAR_COMP, 1842);
			player.getPackets().sendIComponentSprite(3002, YELLOW_STAR_COMP, 1845);
		} else {
			player.getPackets().sendHideIComponent(3002, PURPLE_STAR_COMP, true);
			player.getPackets().sendHideIComponent(3002, YELLOW_STAR_COMP, true);
		}
		player.getPackets().sendIComponentSprite(3002, GREEN_STAR_COMP, 8486);
		if (p2 != null)
			player.getPackets().sendIComponentText(3002, 25, otherName + "<br> Presets");
		else
			player.getPackets().sendIComponentText(3002, 25, "Gear Setups");
		int i = 3;
		for (Entry<String, Preset> gear : p2 != null ? p2.getPresetManager().PRESET_SETUPS.entrySet()
				: player.getPresetManager().PRESET_SETUPS.entrySet()) {
			if (gear != null) {
				player.getPackets().sendHideIComponent(3002, i, false);
				player.getPackets().sendIComponentText(3002, i, (gear.getKey()) + "");
			}
			i++;
		}
	}

	public static void handleButtons(Player player, String name, int compId) {
		String otherName = Utils.formatPlayerNameForDisplay(name);
		Player p2 = World.getPlayerByDisplayName(otherName);
		if (p2 == null && name != null)
			p2 = AccountCreation.loadPlayer(otherName);
		if (compId == 62) {
			if (p2 != null)
				open(player, null);
			else
				SettingsTab.open(player);
			return;
		}
		if (compId == 61) {
			player.temporaryAttribute().remove("SAVESETUP");
			player.temporaryAttribute().put("OTHERPRESET", true);
			player.getPackets().sendRunScript(109, "Search for other players presets: ");
			return;
		}
		if (compId == 60) {
			Integer selectedGear = (Integer) player.getTemporaryAttributes().get("SELECTEDGEAR");
			if (selectedGear != null) {
				for (Entry<String, Preset> gear : p2 != null ? p2.getPresetManager().PRESET_SETUPS.entrySet()
						: player.getPresetManager().PRESET_SETUPS.entrySet()) {
					if (gear != null) {
						if (gear.getValue().getId(p2 != null ? p2 : player) == selectedGear) {
							player.getPresetManager().loadPreset(gear.getKey(), p2 != null ? p2 : null);
							if (p2 != null)
								open(player, null);
							else
								refresh(player);
							return;
						}
					}
				}
			} else {
				player.getPackets().sendGameMessage("You don't have any gear setup selected.");
				return;
			}
		}
		if (compId == 59) {
			Integer selectedGear = (Integer) player.getTemporaryAttributes().get("SELECTEDGEAR");
			if (selectedGear != null) {
				player.getPackets().sendGameMessage("Overwrite setup?");
				return;
			} else {
				player.temporaryAttribute().remove("OTHERPRESET");
				player.temporaryAttribute().put("SAVESETUP", true);
				player.getPackets().sendRunScript(109, "Enter setup name: ");
			}
		}
		if (compId == 26) {
			Integer selectedGear = (Integer) player.getTemporaryAttributes().get("SELECTEDGEAR");
			if (selectedGear != null) {
				for (Entry<String, Preset> gear : player.getPresetManager().PRESET_SETUPS.entrySet()) {
					if (gear != null) {
						if (gear.getValue().getId(player) == selectedGear) {
							player.getPresetManager().removePreset(gear.getKey());
							open(player, null);
							return;
						}
					}
				}
			} else {
				player.getPackets().sendGameMessage("You don't have any gear setup selected.");
				return;
			}
		}
		int i = 3;
		for (Entry<String, Preset> gear : p2 != null ? p2.getPresetManager().PRESET_SETUPS.entrySet()
				: player.getPresetManager().PRESET_SETUPS.entrySet()) {
			if (gear != null) {
				player.getPackets().sendIComponentText(3002, i, gear.getKey());
				if (compId == i) {
					Integer selectedGear = (Integer) player.getTemporaryAttributes().get("SELECTEDGEAR");
					if (selectedGear != null) {
						if (gear.getValue().getId(p2 != null ? p2 : player) == selectedGear) {
							player.getTemporaryAttributes().remove("SELECTEDGEAR");
							player.getPackets().sendIComponentText(3002, i, gear.getKey());
						} else {
							player.getTemporaryAttributes().put("SELECTEDGEAR",
									gear.getValue().getId(p2 != null ? p2 : player));
							player.getPackets().sendIComponentText(3002, i, gear.getKey() + "<img=12>");
						}
					} else {
						player.getTemporaryAttributes().put("SELECTEDGEAR",
								gear.getValue().getId(p2 != null ? p2 : player));
						player.getPackets().sendIComponentText(3002, i, gear.getKey() + "<img=12>");
					}
				}
				i++;
			}
		}
		switch (compId) {
		case BACK_BUTTON:
			SettingsTab.open(player);
			break;
		case FORWARD_BUTTON:
			QuestTab.open(player);
			break;
		default:
			break;
		}
	}

}
