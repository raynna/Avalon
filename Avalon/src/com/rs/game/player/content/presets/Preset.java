package com.rs.game.player.content.presets;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import com.rs.game.item.Item;
import com.rs.game.player.Player;

public final class Preset implements Serializable {

	private static final long serialVersionUID = 1385575955598546603L;

	private final Item[] inventory, equipment;
	private final int prayerBook;
	private final byte spellBook;
	private final String name;
	private final double[] xp;
	private final Item[] runes;

	public Preset(String id, final Item[] inventory, final Item[] equipment, int prayerBook, byte spellBook,
			double[] xp, Item[] runes) {
		this.name = id;
		this.inventory = inventory;
		this.equipment = equipment;
		this.prayerBook = prayerBook;
		this.spellBook = spellBook;
		this.xp = xp;
		this.runes = runes;
		/*int i = 0;
		for (Entry<Integer, Item[]> charges : runicStaff.entrySet()) {
			if (charges == null)
				continue;
			if (i != 0)
				continue;
			this.runicStaff.put(charges.getKey(), charges.getValue());
			i++;
		}
		Iterator<Map.Entry<Integer, Item[]>> iterator = this.runicStaff.entrySet().iterator();
		System.out.println("save this.runicStaff = runicStaff");
		if (!iterator.hasNext())
			System.out.println("failed to save this.runicStaff = runicStaff!");
		while (iterator.hasNext()) {
			Map.Entry<Integer, Item[]> pair = iterator.next();
			for (Item item : pair.getValue()) {
				if (item == null)
					continue;
				System.out
						.println("Preset name: " + name + "saved, rune: " + item.getName() + " x " + item.getAmount());
			}
			System.out.println("Preset name: " + name + "saved, spellId: " + pair.getKey());
		}*/
	}

	public final Item[] getInventory() {
		return inventory;
	}

	public final Item[] getEquipment() {
		return equipment;
	}

	public final Item[] getRunes() {
		return runes;
	}

	public final int getPrayerBook() {
		return prayerBook;
	}

	public final byte getSpellBook() {
		return spellBook;
	}

	public final double[] getLevels() {
		return xp;
	}

//    public final int getId() {
//        return name;
//    }

	public final int getId(final Player player) {
		int i = 0;
		for (Entry<String, Preset> gear : player.getPresetManager().PRESET_SETUPS.entrySet()) {
			if (gear.getKey().toLowerCase().equals(name)) {
				return i;
			}
			i++;
		}
		throw new RuntimeException("failed to locate preset");
	}

}