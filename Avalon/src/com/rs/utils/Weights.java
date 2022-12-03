package com.rs.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.rs.game.item.Item;
import com.rs.game.player.Player;

public class Weights {

	private static Map<Integer, Double> itemWeight = new HashMap<>();

	private static final int[] WEIGHT_REDUCERS = { 88, 10069, 10073, 10663, 10071, 10074, 10664, 10553, 10554, 24210,
			24211, 14938, 14939, 24208, 24209, 14936, 14937 };

	public static void init() {
		try (BufferedReader reader = new BufferedReader(new FileReader("data/items/unpackedWeights.txt"))) {
			while (true) {
				String file = reader.readLine();
				if (file == null) {
					break;
				}
				if (file.startsWith("//")) {
					continue;
				}
				String[] values = file.split(" - ");
				itemWeight.put(Integer.valueOf(values[0]), Double.parseDouble(values[1]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static double calculateWeight(Player player) {
		for (int i = 0; i < 14; i++)
			player.getEquipment().refresh(i);
		player.setWeight(0);
		for (int REDUCERS : WEIGHT_REDUCERS) {
			if (player.getEquipment().getItems().contains(new Item(REDUCERS))) {
				player.setWeight(player.getWeight() - getWeight(REDUCERS));
			}
		}
		for (int i = 0; i <= Utils.getItemDefinitionsSize(); i++) {
			if (player.getInventory().containsItem(i, 1)) {
				player.setWeight(player.getWeight() + getWeight(i) * player.getInventory().getNumberOf(i));
			} else if (player.getEquipment().getItems().contains(new Item(i))) {
				player.setWeight(player.getWeight() + getWeight(i));
			}
		}
		player.getPackets().sendWeight(player.getWeight());
		return player.getWeight();

	}

	private static double getWeight(int itemId) {
		if (itemWeight.get(itemId) == null)
			return 0;
		return itemWeight.get(itemId);
	}

	public static void updateWeight(Player player) {
		calculateWeight(player);
	}

}
