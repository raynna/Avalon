package com.rs.game.player.content.presets;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.rs.Settings;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.controlers.EdgevillePvPControler;
import com.rs.utils.HexColours;
import com.rs.utils.HexColours.Colour;

public final class PresetManager implements Serializable {

	private static final long serialVersionUID = -2928476953478619103L;
	/** Instantiated variables below **/
	private transient Player player;
	public HashMap<String, Preset> PRESET_SETUPS;

	public void setPlayer(Player player) {
		this.player = player;
	}

	public PresetManager() {
		PRESET_SETUPS = new HashMap<String, Preset>();
	}

	private final int getMaxSize() {
		return 28;
	}

	public void reset() {
		PRESET_SETUPS.clear();
		player.getPackets().sendGameMessage(
				"All of your sets have been cleared. You now have " + getMaxSize() + " available slots.");
	}

	public void removePreset(String name) {
		if (name == "")
			return;
		name = name.toLowerCase();
		player.getPackets()
				.sendGameMessage((PRESET_SETUPS.remove(name) == null ? "No set was found for the query: " + name
						: "Successfully removed the set: " + name) + ".");
	}

	public void savePreset(String name) {
		final int size = PRESET_SETUPS.size(), max = getMaxSize();
		if (size >= max) {
			player.getPackets().sendGameMessage("You were unable to store the set " + name
					+ " as your maximum capacity (" + max + ") has been reached.", true);
			return;
		}
		if (name == "")
			return;
		name = name.toLowerCase();
		final Preset set = PRESET_SETUPS.get(name);
		if (set != null) {
			player.getPackets().sendGameMessage("You were unable to store the set " + name + " as it already exists.",
					true);
			return;
		}
		final Item[] inventory = player.getInventory().getItems().getItemsCopy(),
				equipment = player.getEquipment().getItems().getItemsCopy(), runes = player.getRunePouch().getContainerItems();
		PRESET_SETUPS.put(name,
				new Preset(name, inventory, equipment, player.getPrayer().getPrayerBook(),
						player.getCombatDefinitions().spellBook, (Arrays.copyOf(player.getSkills().getXp(), 7)), runes));

		player.getPackets().sendGameMessage("You've successfully stored the set " + name + ".", true);
	}

	public void printPresets() {
		final int size = PRESET_SETUPS.size();
		player.getPackets().sendGameMessage("You have used " + size + "/" + getMaxSize() + " available setups.", true);
		if (size > 0) {
			player.getPackets().sendGameMessage("<col=ff0000>Your available setups are:", true);
			for (final String key : PRESET_SETUPS.keySet()) {
				player.getPackets().sendGameMessage(key, true);
			}
		}
	}

	public void loadPreset(String name, Player p2) {
		if (name == "")
			return;
		if (player.isAtWild()) {
			player.getPackets().sendGameMessage(
					HexColours.getMessage(Colour.RED, "You can't load gear presets in the wilderness."));
			return;
		}
		if (player.getControlerManager().getControler() != null
				&& !(player.getControlerManager().getControler() instanceof EdgevillePvPControler)) {
			player.getPackets()
					.sendGameMessage(HexColours.getMessage(Colour.RED, "You can't load gear presets in here."));
			return;
		}
		if (EdgevillePvPControler.isAtPvP(player) && !EdgevillePvPControler.isAtBank(player)) {
			player.getPackets()
					.sendGameMessage(HexColours.getMessage(Colour.RED, "You can't load gear presets in pvp area."));
			return;
		}
		name = name.toLowerCase();
		final Preset set = (p2 != null ? p2.getPresetManager().PRESET_SETUPS.get(name) : PRESET_SETUPS.get(name));
		if (set == null) {
			player.getPackets().sendGameMessage("You were unable to load the set " + name + " as it does not exist.",
					true);
			return;
		}

		Item[] inventory = player.getInventory().getItems().getItemsCopy();
		Item[] equipment = player.getEquipment().getItems().getItemsCopy();
		Item[] runes = player.getRunePouch().getContainerItems();
		for (Item item : inventory) {
			if (item != null)
				player.getBank().addItem(item, true);
		}
		for (Item item : equipment) {
			if (item != null)
				player.getBank().addItem(item, true);
		}
		for (Item item : runes) {
			if (item != null)
				player.getBank().addItem(item, true);
		}

		Iterator<Map.Entry<Integer, Item[]>> iterator = player.getStaffCharges().entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, Item[]> pair = iterator.next();
			if (pair.getValue() != null) {
				for (Item item : pair.getValue()) {
					if (item == null)
						continue;
					player.message("Added " + item.getName() + " x " + item.getAmount() + " to your bank.");
					player.getBank().addItem(item, true);
				}
			} // to focus more whats not setting
		}

		// Lol have no clue why after this line it seems to reset it? ye the preset one
		// this
		// player.getStaffCharges().clear();
		// So u gonna comment it out? i guess, i gotta check if values are correct
		// ingame

		player.getRunePouch().reset();
		player.getInventory().reset();
		player.getInventory().refresh();
		player.getEquipment().reset();
		player.getEquipment().refresh();
		player.getAppearence().generateAppearenceData();
		player.refreshHitPoints();
		player.getPrayer().reset();
		Item[] data = set.getEquipment();
		if (data != null && data.length > 0) {
			skip: for (int i = 0; i < data.length; i++) {
				final Item item = data[i];
				if (item == null)
					continue;
				final HashMap<Integer, Integer> requirements = item.getDefinitions().getWearingSkillRequiriments();
				if (requirements != null) {
					for (final int skillId : requirements.keySet()) {
						if (skillId > 24 || skillId < 0)
							continue;
						final int level = requirements.get(skillId);
						if (level < 0 || level > 120)
							continue;
						if (player.getSkills().getLevelForXp(skillId) < level) {
							player.getPackets()
									.sendGameMessage("You were unable to equip your " + item.getName().toLowerCase()
											+ ", as you don't meet the requirements to wear them.", true);
							continue skip;
						}
					}
				}

				if ((Settings.ECONOMY_MODE == 1
						&& set.getEquipment()[i].getDefinitions().getValue() >= Settings.LOWPRICE_LIMIT)
						|| Settings.ECONOMY_MODE == 0
						|| (!item.getDefinitions().isTradeable() && Settings.ECONOMY_MODE < 2)) {
					if (player.getBank().getItem(set.getEquipment()[i].getId()) != null && player.getBank()
							.getItem(set.getEquipment()[i].getId()).getAmount() >= item.getAmount()) {
						int[] slot = player.getBank().getItemSlot(set.getEquipment()[i].getId());
						player.getBank().removeItem2(slot, item.getAmount(), true, false);
					} else {
						player.getPackets().sendGameMessage(
								"Couldn't find item " + item.getAmount() + " x " + item.getName() + " in bank.");
						continue;
					}
				}
				player.getEquipment().getItems().set(i, new Item(set.getEquipment()[i].getId(), item.getAmount()));
				player.getEquipment().refresh(i);
			}
		}

		data = set.getInventory();
		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				final Item item = data[i];
				if (item == null)
					continue;
				if ((Settings.ECONOMY_MODE == 1 && set.getInventory()[i] != null
						&& set.getInventory()[i].getDefinitions().getValue() >= Settings.LOWPRICE_LIMIT)
						|| Settings.ECONOMY_MODE == 0
						|| (!item.getDefinitions().isTradeable() && Settings.ECONOMY_MODE < 2)) {
					if (player.getBank().getItem(set.getInventory()[i].getId()) != null && player.getBank()
							.getItem(set.getInventory()[i].getId()).getAmount() >= item.getAmount()) {
						int[] slot = player.getBank().getItemSlot(set.getInventory()[i].getId());
						player.getBank().removeItem2(slot, item.getAmount(), true, false);
					} else {
						player.getInventory().addItem(0, 1);
						player.getPackets().sendGameMessage(
								"Couldn't find item " + item.getAmount() + " x " + item.getName() + " in bank.");
						continue;
					}
				}
				player.getInventory().addItem(item);
			}
		}

		data = set.getRunes();
		if (data != null && data.length > 0) {
			for (Item item : data) {
				if (item != null) {
					player.getRunePouch().add(item);
					player.getRunePouch().shift();
				}
			}
		}
		player.getInventory().deleteItem(0, 28);
		player.getCombatDefinitions().setSpellBook(set.getSpellBook(), false);
		player.getPrayer().setPrayerBook(set.getPrayerBook() == 1 ? true : false);
		player.getAppearence().generateAppearenceData();
		player.getSkills().switchXPPopup(true);
		player.getSkills().switchXPPopup(true);
		player.getPackets().sendGameMessage("Loaded setup: " + name + ".");

	}

}