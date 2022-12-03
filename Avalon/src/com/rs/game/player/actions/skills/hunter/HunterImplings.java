package com.rs.game.player.actions.skills.hunter;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.Drop;
import com.rs.game.player.Player;
import com.rs.utils.NPCDrops;
import com.rs.utils.Utils;

public class HunterImplings {

	public static void openImpBank(Player player, int npcId) {
		try {
			Drop[] drops = NPCDrops.getDrops(npcId);
			if (drops == null)
				return;
			Drop[] possibleDrops = new Drop[drops.length];
			int possibleDropsCount = 0;
			for (Drop drop : drops) {
				if (drop.getRate() == 100)
					sendImpRewardBank(player, drop);
				else {
					if ((Utils.getRandomDouble(99) + 1) <= drop.getRate()) {
						possibleDrops[possibleDropsCount++] = drop;
					}
				}
			}
			if (possibleDropsCount > 0)
				sendImpRewardBank(player, possibleDrops[Utils.getRandom(possibleDropsCount - 1)]);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Error e) {
			e.printStackTrace();
		}
	}

	public static void sendImpRewardBank(Player player, Drop drop) {
		Item item = ItemDefinitions.getItemDefinitions(drop.getItemId()).isStackable()
				? new Item(drop.getItemId(),
						(drop.getMinAmount() * Settings.DROP_RATE)
								+ Utils.getRandom(drop.getExtraAmount() * Settings.DROP_RATE))
				: new Item(drop.getItemId(), (drop.getMinAmount() + Utils.getRandom(drop.getExtraAmount())));
		player.getBank().addItem(item, true);
	}

	public static void openImp(Player player, int npcId) {
		try {
			Drop[] drops = NPCDrops.getDrops(npcId);
			if (drops == null)
				return;
			Drop[] possibleDrops = new Drop[drops.length];
			int possibleDropsCount = 0;
			for (Drop drop : drops) {
				if (drop.getRate() == 100)
					sendImpReward(player, drop);
				else {
					if ((Utils.getRandomDouble(99) + 1) <= drop.getRate()) {
						possibleDrops[possibleDropsCount++] = drop;
					}
				}
			}
			if (possibleDropsCount > 0)
				sendImpReward(player, possibleDrops[Utils.getRandom(possibleDropsCount - 1)]);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Error e) {
			e.printStackTrace();
		}
	}

	public static void sendImpReward(Player player, Drop drop) {
		Item item = ItemDefinitions.getItemDefinitions(drop.getItemId()).isStackable()
				? new Item(drop.getItemId(),
						(drop.getMinAmount() * Settings.DROP_RATE)
								+ Utils.getRandom(drop.getExtraAmount() * Settings.DROP_RATE))
				: new Item(drop.getItemId(), (drop.getMinAmount() + Utils.getRandom(drop.getExtraAmount())));
		player.getInventory().addItem(item);
	}

	public static void drop(Player player, int npcId) {
		try {
			Drop[] drops = NPCDrops.getDrops(npcId);
			if (drops == null)
				return;
			Drop[] possibleDrops = new Drop[drops.length];
			int possibleDropsCount = 0;
			for (Drop drop : drops) {
				if (drop.getRate() == 100)
					sendDrop(player, drop);
				else {
					if ((Utils.getRandomDouble(99) + 1) <= drop.getRate()) {
						possibleDrops[possibleDropsCount++] = drop;
					}
				}
			}
			if (possibleDropsCount > 0)
				sendDrop(player, possibleDrops[Utils.getRandom(possibleDropsCount - 1)]);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Error e) {
			e.printStackTrace();
		}
	}

	public static void sendDrop(Player player, Drop drop) {
		Item item = ItemDefinitions.getItemDefinitions(drop.getItemId()).isStackable()
				? new Item(drop.getItemId(),
						(drop.getMinAmount() * Settings.DROP_RATE)
								+ Utils.getRandom(drop.getExtraAmount() * Settings.DROP_RATE))
				: new Item(drop.getItemId(), (drop.getMinAmount() + Utils.getRandom(drop.getExtraAmount())));
		World.updateGroundItem(new Item(item.getId(), item.getAmount()),
				new WorldTile(player.getX() + 1, player.getY(), player.getPlane()), player, 1, 0);
	}
}