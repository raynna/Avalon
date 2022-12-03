package com.rs.game.item.itemdegrading;

import java.util.HashMap;
import java.util.Map.Entry;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

/**
 * @author -Andreas 2 feb. 2020 07:25:41
 * @project 1. Avalon
 * 
 */

public class ArmourRepair {

	private static RepairData[] data = RepairData.values();

	public enum RepairData {

		/*
		 * broken pieces id, repairtype, repairedId
		 */

		AHRIMS_HOOD(new int[] { 4856, 4857, 4858, 4859, 4860 }, RepairType.HELMET, 4708),

		AHRIMS_STAFF(new int[] { 4862, 4863, 4864, 4865, 4866 }, RepairType.WEAPON, 4710),

		AHRIMS_TOP(new int[] { 4868, 4869, 4870, 4871, 4872 }, RepairType.BODY, 4712),

		AHRIMS_BOTTOMS(new int[] { 4874, 4875, 4876, 4877, 4878 }, RepairType.LEGS, 4714),

		DHAROKS_HELM(new int[] { 4880, 4881, 4882, 4883, 4884 }, RepairType.HELMET, 4716),

		DHAROKS_AXE(new int[] { 4886, 4887, 4888, 4889, 4890 }, RepairType.WEAPON, 4718),

		DHAROKS_PLATEBODY(new int[] { 4892, 4893, 4894, 4895, 4896 }, RepairType.BODY, 4720),

		DHAROKS_PLATELEGS(new int[] { 4898, 4899, 4900, 4901, 4902 }, RepairType.LEGS, 4722),

		GUTHANS_HELM(new int[] { 4904, 4905, 4906, 4907, 4908 }, RepairType.HELMET, 4724),

		GUTHANS_SPEAR(new int[] { 4910, 4911, 4912, 4913, 4914 }, RepairType.WEAPON, 4726),

		GUTHANS_BODY(new int[] { 4916, 4917, 4918, 4919, 4920 }, RepairType.BODY, 4728),

		GUTHANS_LEGS(new int[] { 4922, 4923, 4924, 4925, 4926 }, RepairType.LEGS, 4730),

		KARILS_COIF(new int[] { 4928, 4929, 4930, 4931, 4932 }, RepairType.HELMET, 4732),

		KARILS_CROSSBOW(new int[] { 4934, 4935, 4936, 4937, 4938 }, RepairType.WEAPON, 4734),

		KARILS_TOP(new int[] { 4940, 4941, 4942, 4943, 4944 }, RepairType.BODY, 4736),

		KARILS_SKIRT(new int[] { 4946, 4947, 4948, 4949, 4950 }, RepairType.LEGS, 4738),

		TORAGS_HELM(new int[] { 4952, 4953, 4954, 4955, 4956 }, RepairType.HELMET, 4745),

		TORAGS_HAMMER(new int[] { 4958, 4959, 4960, 4961, 4962 }, RepairType.WEAPON, 4747),

		TORAGS_PLATEBODY(new int[] { 4964, 4965, 4966, 4967, 4968 }, RepairType.BODY, 4749),

		TORAGS_PLATELEGS(new int[] { 4970, 4971, 4972, 4973, 4974 }, RepairType.LEGS, 4751),

		VERACS_HELM(new int[] { 4976, 4977, 4978, 4979, 4980 }, RepairType.HELMET, 4753),

		VERACS_FLAIL(new int[] { 4982, 4983, 4984, 4985, 4086 }, RepairType.WEAPON, 4755),

		VERACS_BRASSARD(new int[] { 4088, 4089, 4090, 4091, 4092 }, RepairType.BODY, 4757),

		VERACS_SKIRT(new int[] { 4094, 4095, 4096, 4097, 4098 }, RepairType.LEGS, 4759),

		;

		private int[] itemIds;
		private int repairedId;
		private RepairType type;

		private RepairData(int[] itemIds, RepairType type, int repairedId) {
			this.itemIds = itemIds;
			this.type = type;
			this.repairedId = repairedId;
		}

		public int[] getItemIds() {
			return itemIds;
		}

		public RepairType getType() {
			return type;
		}

		public int getRepairedId() {
			return repairedId;
		}
	}

	private enum RepairType {

		HELMET(new int[] { 12000, 24000, 36000, 48000, 60000 }),

		WEAPON(new int[] { 20000, 40000, 60000, 80000, 100000 }),

		BODY(new int[] { 18000, 36000, 54000, 72000, 90000 }),

		LEGS(new int[] { 16000, 32000, 48000, 64000, 80000 });

		private int[] prices;

		private RepairType(int[] prices) {
			this.prices = prices;
		}

		private int[] getPrices() {
			return prices;
		}
	}

	public static double getPriceReduction(int smithingLevel) {
		return (1.0 - ((double) (smithingLevel / 2) / 100));
	}

	public static int getTotalPrice(Player player) {
		int totalPrice = 0;
		int smithingLevel = player.getSkills().getLevel(Skills.SMITHING);
		for (Item inventory : player.getInventory().getItems().toArray()) {
			if (inventory == null)
				continue;
			for (RepairData data : data) {
				if (data == null)
					continue;
				int index = 0;
				for (int id : data.getItemIds()) {
					if (inventory.getId() == id) {
						totalPrice += (data.getType().getPrices()[index] * getPriceReduction(smithingLevel));
					}
					index++;
				}

			}
		}
		return totalPrice;
	}

	public static int getPrice(Player player, Item item) {
		int totalPrice = 0;
		int smithingLevel = player.getSkills().getLevel(Skills.SMITHING);
		for (RepairData data : data) {
			if (data == null)
				continue;
			int index = 0;
			for (int id : data.getItemIds()) {
				if (item.getId() == id) {
					totalPrice += (data.getType().getPrices()[index] * getPriceReduction(smithingLevel));
				}
				index++;
			}
		}
		return totalPrice;
	}

	public static void repairAllItems(Player player) {
		HashMap<Item, Integer> itemRepairs = new HashMap<>();
		int totalPrice = getTotalPrice(player);
		for (Item inventory : player.getInventory().getItems().toArray()) {
			if (inventory == null)
				continue;
			for (RepairData data : data) {
				if (data == null)
					continue;
				for (int id : data.getItemIds()) {
					if (inventory.getId() == id) {
						itemRepairs.put(inventory, data.getRepairedId());
					}
				}
			}
		}
		if (itemRepairs.isEmpty()) {
			player.sm("You don't have any items to repair.");
			return;
		}
		if (player.canBuy(totalPrice)) {
			for (Entry<Item, Integer> repairItems : itemRepairs.entrySet()) {
				player.getInventory().deleteItem(repairItems.getKey());
				player.getInventory().addItem(new Item(repairItems.getValue()));
			}
			player.sm("All your items has been repaired.");
		} else {
			player.sm("You don't have enough coins to repair all items.");
			player.sm("You need at least " + Utils.getFormattedNumber(totalPrice, ',') + " coins.");
		}
	}

	public static void repairItem(Player player, Item item, boolean stand) {
		int price = getPrice(player, item);
		for (RepairData data : data) {
			if (data == null)
				continue;
			for (int id : data.getItemIds()) {
				if (item.getId() == id) {
					if (player.canBuy(price)) {
						player.getInventory().deleteItem(item);
						player.getInventory().addItem(data.getRepairedId(), 1);
						player.sm(stand ? "You repair your " + item.getName() + " on the armour stand." : "Bob has repaired your " + item.getName() + ".");
					} else {
						player.sm("You don't have enough coins to repair all items.");
						player.sm("You need at least " + Utils.getFormattedNumber(price, ',') + " coins.");
					}
				}
			}
		}
	}
}
