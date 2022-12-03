package com.rs.game.item.itemdegrading;

import java.io.Serializable;
import java.util.HashMap;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.item.Item;
import com.rs.game.item.itemdegrading.ItemDegrade.DegradeType;
import com.rs.game.item.itemdegrading.ItemDegrade.ItemStore;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author -Andreas 1 feb. 2020 13:58:09
 * @project 1. Avalon
 * 
 */

public class ChargesManager implements Serializable {

	private static final long serialVersionUID = -5978513415281726450L;

	private transient Player player;

	private HashMap<Integer, Integer> charges;

	public ChargesManager() {
		charges = new HashMap<Integer, Integer>();
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	private static ItemStore[] data = ItemStore.values();

	public void process() {
		Item[] items = player.getEquipment().getItems().getContainerItems();
		for (int slot = 0; slot < items.length; slot++) {
			Item item = items[slot];
			if (item == null)
				continue;
			int defaultCharges = -1;
			for (ItemStore data : data) {
				if (data == null || data.getCurrentItem().getId() != item.getId())
					continue;
				defaultCharges = data.getTime() != null ? data.getTime().getTicks() : -1;
				if (defaultCharges == -1)
					continue;
				if (data.getType() == DegradeType.WEAR)
					degrade(item.getId(), defaultCharges, slot);
				else if (data.getType() == DegradeType.IN_COMBAT
						&& player.getAttackedByDelay() > Utils.currentTimeMillis())
					degrade(item.getId(), defaultCharges, slot);
			}
		}
	}

	public void processOutgoingHit() {
		Item[] items = player.getEquipment().getItems().getContainerItems();
		for (int slot = 0; slot < items.length; slot++) {
			Item item = items[slot];
			if (item == null)
				continue;
			int defaultCharges = -1;
			for (ItemStore data : data) {
				if (data == null)
					continue;
				if (data.getCurrentItem().getId() != item.getId()) {
					continue;
				}

				defaultCharges = data.getHits();
				if (defaultCharges == -1)
					continue;
				if (data.getType() == DegradeType.AT_OUTGOING_HIT)
					degrade(item.getId(), defaultCharges, slot);
			}
		}
	}

	public void processIncommingHit() {
		Item[] items = player.getEquipment().getItems().getContainerItems();
		for (int slot = 0; slot < items.length; slot++) {
			Item item = items[slot];
			if (item == null)
				continue;
			int defaultCharges = -1;
			for (ItemStore data : data) {
				if (data == null || data.getCurrentItem().getId() != item.getId())
					continue;
				defaultCharges = data.getHits();
				if (defaultCharges == -1)
					continue;
				if (data.getType() == DegradeType.AT_INCOMMING_HIT)
					degrade(item.getId(), defaultCharges, slot);
			}
		}
	}

	public void processHit(Hit hit) {
		Item[] items = player.getEquipment().getItems().getContainerItems();
		for (int slot = 0; slot < items.length; slot++) {
			Item item = items[slot];
			if (item == null)
				continue;
			int defaultCharges = -1;
			for (ItemStore data : data) {
				if (data == null || data.getCurrentItem().getId() != item.getId())
					continue;
				defaultCharges = data.getHits();
				if (defaultCharges == -1)
					continue;
				if (data.getType() == DegradeType.HITS)
					degrade(item.getId(), defaultCharges, slot, hit);
			}
		}
	}

	public int getCharges(int id) {
		Integer c = charges.get(id);
		return c == null ? 0 : c;
	}

	public void resetCharges(int id) {
		charges.remove(id);
	}

	public void setCharges(int id, int amount) {
		charges.put(id, amount);
	}

	public boolean degrade(Item item) {
		int defaultCharges = -1;
		for (ItemStore data : data) {
			if (data == null)
				continue;
			if (data.getCurrentItem().getId() == item.getId()) {
				defaultCharges = (data.getType() == DegradeType.AT_INCOMMING_HIT
						|| data.getType() == DegradeType.AT_OUTGOING_HIT || data.getType() == DegradeType.HITS) ? data.getHits() : data.getTime().getTicks();
				if (defaultCharges == -1)
					return false;
				charges.put(item.getId(), 1);
				int newId = data.getBrokenItem() != null ? data.getBrokenItem().getId()
						: data.getDegradedItem() != null ? data.getDegradedItem().getId() : -1;
				if (newId != -1) {
					item.setId(newId);
					charges.put(newId, 1);
				} else
					return true;
			}
		}
		return false;
	}

	public static final String REPLACE = "##";

	public void checkPercentage(String message, int id, boolean reverse) {
		int charges = getCharges(id);
		int maxCharges = 0;
		for (ItemStore data : data) {
			if (data == null)
				continue;
			if (data.getCurrentItem().getId() == id) {
				maxCharges = (data.getType() == DegradeType.AT_INCOMMING_HIT
						|| data.getType() == DegradeType.AT_OUTGOING_HIT) ? data.getHits() : data.getTime().getTicks();
			}
		}
		int percentage = reverse ? (charges == 0 ? 0 : (100 - (charges * 100 / maxCharges)))
				: charges == 0 ? 100 : (charges * 100 / maxCharges);
		player.getPackets().sendGameMessage(message.replace(REPLACE, String.valueOf(percentage)));
	}

	public int getPercentage(int id, boolean reverse) {
		int charges = getCharges(id);
		int maxCharges = 0;
		for (ItemStore data : data) {
			if (data == null)
				continue;
			if (data.getCurrentItem().getId() == id
					|| (data.getDegradedItem() != null && data.getDegradedItem().getId() == id)) {
				maxCharges = (data.getType() == DegradeType.AT_INCOMMING_HIT
						|| data.getType() == DegradeType.AT_OUTGOING_HIT) ? data.getHits() : data.getTime().getTicks();
			}
		}
		int percentage = reverse ? (charges == 0 ? 0 : (100 - (charges * 100 / maxCharges)))
				: charges == 0 ? 100 : (charges * 100 / maxCharges);
		return percentage;
	}

	public void checkCharges(String message, int id) {
		player.getPackets().sendGameMessage(message.replace(REPLACE, String.valueOf(getCharges(id))));
	}

	private void degrade(int itemId, int defaultCharges, int slot, Hit hit) {
		ItemDefinitions itemDef = ItemDefinitions.getItemDefinitions(itemId);
		Integer c = charges.remove(itemId);
		Item newItem = null;
		for (ItemStore data : data) {
			if (data == null || data.getCurrentItem().getId() != itemId)
				continue;
			if (c == null || c == 0) {
				c = data.getHits();
				player.getPackets().sendGameMessage("Your " + itemDef.getName() + " started degrading.");
			}	
			int removeHits = hit != null ? hit.getDamage() : 0;
			if (removeHits > c) {
				c = 0;
			} else {
				c -= removeHits;
			}
			if (c > 0) {
				charges.put(itemId, c);
			} else {
				if (data.getDegradedItem() == null) {
					if (data.getBrokenItem() != null) {
						newItem = data.getBrokenItem();
					}
				} else {
					if (itemId != data.getDegradedItem().getId()) {
						newItem = data.getDegradedItem();
					}
				}
				if (newItem == null) {
					player.getPackets().sendGameMessage("Your " + itemDef.getName() + " turned into dust.");
				}
				player.getEquipment().getItems().set(slot, newItem);
				player.getEquipment().refresh(slot);
				player.getAppearence().generateAppearenceData();
			}
		}
	}

	private void degrade(int itemId, int defaultCharges, int slot) {
		ItemDefinitions itemDef = ItemDefinitions.getItemDefinitions(itemId);
		Integer c = charges.remove(itemId);
		Item newItem = null;
		for (ItemStore data : data) {
			if (data == null || data.getCurrentItem().getId() != itemId)
				continue;
			if (c == null || c == 0)
				c = (data.getType() == DegradeType.AT_INCOMMING_HIT || data.getType() == DegradeType.AT_OUTGOING_HIT)
						? data.getHits()
						: data.getTime().getTicks();
			c--;
			if (c > 0) {
				charges.put(itemId, c);
				if (itemDef.getName().contains("(deg")) {// pvp equipment
					if (c % (Math.floor(data.getTime().getTicks() * 0.05)) == 0) {// every 5%
						player.getCharges().checkPercentage(
								"Your " + itemDef.getName() + " has degraded " + ChargesManager.REPLACE + "%.", itemId,
								true);
					}
				}
				if (itemId >= 18349 && itemId <= 18363) {// chaotic equipment
					if (c % (Math.floor(data.getHits() * 0.05)) == 0) {// every 5%
						player.getCharges().checkPercentage(
								"Your " + itemDef.getName() + " has " + ChargesManager.REPLACE + "% charges left.",
								itemId, false);
					}
				}
				if (itemDef.getName().toLowerCase().contains("crystal")) {// crystal equipment
					if (c % (Math.floor(data.getHits() * 0.05)) == 0) {// every 5%
						player.getCharges().checkPercentage(
								"Your " + itemDef.getName() + " has degraded " + ChargesManager.REPLACE + "%.", itemId,
								true);
					}
				}
			} else {
				if (data.getDegradedItem() == null) {
					if (data.getBrokenItem() != null) {
						newItem = data.getBrokenItem();
					}
				} else {
					if (itemId != data.getDegradedItem().getId()) {
						newItem = data.getDegradedItem();
					}
				}
				if (newItem == null) {
					if (itemDef.getName().contains("(deg)")) {
						if (slot == 0)
							player.gfx(new Graphics(1859));
						if (slot == 4)
							player.gfx(new Graphics(1861));
						if (slot == 7)
							player.gfx(new Graphics(1860));
					}
					player.getPackets().sendGameMessage("Your " + itemDef.getName() + " turned into dust.");
				} else {
					player.getPackets().sendGameMessage("Your " + itemDef.getName() + " degraded.");
				}
				player.getEquipment().getItems().set(slot, newItem);
				player.getEquipment().refresh(slot);
				player.getAppearence().generateAppearenceData();
			}
		}
	}

}
