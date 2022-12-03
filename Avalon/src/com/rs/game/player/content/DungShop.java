package com.rs.game.player.content;

import java.util.HashMap;
import java.util.Map;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;

/**
 * 
 * @author Dennis | AvalonPK
 * 
 */

public class DungShop {

	public enum DungItem {

		BONE_CRUSHER(34000, 0, 18337),

		HERBICIDE(34000, 5, 19675),

		GEM_BAG(2000, 10, 18338),

		SCROLL_OF_LIFE(10000, 15, 18336),

		ARCANE_PULSE_NECKLACE(6500, 20, 18333),

		TWISTED_BIRD_SKULL_NECKLACE(8500, 25, 19886),

		MAGICAL_BLASTBOX(40000, 30, 19671),

		COAL_BAG(4000, 35, 18339),

		SCROLL_OF_CLEANSING(20000, 40, 19890),

		LONGBOW_SIGHT(10000, 45, 18330),

		GRAVITE_RAPIER(40000, 50, 18365),

		GRAVITE_LONGSWORD(40000, 55, 18367),

		GRAVITE_2H_SWORD(40000, 60, 18369),

		GRAVITE_STAFF(40000, 65, 18371),

		GRAVITE_SHORTBOW(40000, 70, 18373),

		LAW_STAFF(10000, 75, 18342),

		TOME_OF_FROST(43000, 80, 18346),

		AMULET_OF_ZEALOTS(40000, 85, 19892),

		ARCANE_BLAST_NECKLACE(15500, 90, 18334),

		SPIRIT_CAPE(45000, 95, 19893),

		NATURE_STAFF(12500, 100, 18341),

		SCROLL_OF_EFFICIENCY(20000, 105, 19670),

		ANTI_POISON_TOTEM(44000, 110, 18340),

		SPLIT_DRAGONTOOTH_NECKLACE(17000, 115, 19887),

		RING_OF_VIGOUR(50000, 120, 19669),

		SCROLL_OF_RENEWAL(107000, 125, 18343),

		ARCANE_STREAM_NECKLACE(30500, 130, 18335),

		CELESTIAL_SURGEBOX(65000, 135, 19868),

		MERCENARYS_GLOVES(48500, 140, 18347),

		SCROLL_OF_RIGOUR(140000, 145, 18839),

		SCROLL_OF_AUGURY(153000, 150, 18344),

		CHAOTIC_RAPIER(200000, 155, 18349),

		CHAOTIC_LONGSWORD(200000, 160, 18351),

		CHAOTIC_MAUL(200000, 165, 18353),

		CHAOTIC_STAFF(200000, 170, 18355),

		CHAOTIC_CROSSBOW(200000, 175, 18357),

		CHAOTIC_KITESHIELD(200000, 180, 18359),

		EAGLE_EYE_KITESHIELD(200000, 185, 18361),

		FARSEER_KITESHIELD(200000, 190, 18363),

		SNEAKERPEEPER_SPAWN(85000, 195, 19894),

		DEMON_HORN_NECKLACE(35000, 200, 19888),

		DUNGEONEERING_EXPERIENCE(1, 205, 1);

		private int tokens;
		private int slotId;
		private int itemId;

		private static final Map<Integer, DungItem> dungItems = new HashMap<Integer, DungItem>();

		static {
			for (DungItem item : DungItem.values()) {
				dungItems.put(item.slotId, item);
			}
		}

		private DungItem(int tokens, int slotId, int itemId) {
			this.setTokens(tokens);
			this.setSlotId(slotId);
			this.itemId = itemId;
		}

		public static DungItem forId(int slotId) {
			DungItem item = dungItems.get(slotId);
			return item;
		}

		public int getTokens() {
			return tokens;
		}

		public void setTokens(int tokens) {
			this.tokens = tokens;
		}

		public int getSlotId() {
			return slotId;
		}

		public void setSlotId(int slotId) {
			this.slotId = slotId;
		}

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

	}

	public static void openShop(Player player) {
		player.getInterfaceManager().sendInterface(940);
		player.getPackets().sendIComponentSettings(940, 2, 0, 205, 145346);
		player.getPackets().sendIComponentText(940, 31, player.getAvalonPoints() + "");

	}

	public static void handleButtons(Player player, int componentId, int slotId) {
		switch (componentId) {
		case 2:
			setAttribute(player, slotId);
			break;
		case 64:
			player.getPackets().sendHideIComponent(940, 42, false);
			break;
		case 48:
			handleBuy(player);
			break;
		case 50:
			player.getPackets().sendHideIComponent(940, 42, true);
			break;
		}
	}

	private static void handleBuy(Player player) {
		DungItem item = DungItem.forId(Integer.parseInt(player.temporaryAttribute().get("dungshop").toString()));
		if (player.getAvalonPoints() >= item.tokens) {
			if (item == DungItem.DUNGEONEERING_EXPERIENCE) {
				player.temporaryAttribute().put("exp_lamp", true);
				player.getPackets().sendRunScript(108,
						"Please enter the number of tokens you wish to trade for XP. 1x3 ");
				player.getPackets().sendHideIComponent(940, 42, true);
			} else if ((item == DungItem.SCROLL_OF_RIGOUR && player.hasRigour)
					|| (item == DungItem.SCROLL_OF_RIGOUR && invContains(player, 18839))
					|| (item == DungItem.SCROLL_OF_RIGOUR && bankContains(player, 18839))) {
				player.getPackets().sendGameMessage("You already bought Rigour once.");
				player.getPackets().sendHideIComponent(940, 42, true);
			} else if ((item == DungItem.SCROLL_OF_AUGURY && player.hasAugury)
					|| (item == DungItem.SCROLL_OF_AUGURY && invContains(player, 18344))
					|| (item == DungItem.SCROLL_OF_AUGURY && bankContains(player, 18344))) {
				player.getPackets().sendGameMessage("You already bought Augury once.");
				player.getPackets().sendHideIComponent(940, 42, true);
			} else if ((item == DungItem.SCROLL_OF_RENEWAL && player.hasRenewal)
					|| (item == DungItem.SCROLL_OF_RENEWAL && invContains(player, 18343))
					|| (item == DungItem.SCROLL_OF_RENEWAL && bankContains(player, 18343))) {
				player.getPackets().sendGameMessage("You already bought Rapid renewal once.");
				player.getPackets().sendHideIComponent(940, 42, true);
			} else if (player.getInventory().getFreeSlots() > 0) {
				player.setAvalonPoints(player.getAvalonPoints() - item.tokens);
				player.getInventory().addItem(item.getItemId(), 1);
				player.getPackets().sendGameMessage(
						"You purchased a " + ItemDefinitions.getItemDefinitions(item.getItemId()).getName() + ".");
				if (item != DungItem.DUNGEONEERING_EXPERIENCE)
					player.getAdventureLog().addActivity(
							"I have purchased " + ItemDefinitions.getItemDefinitions(item.getItemId()).getName());
				player.getPackets().sendHideIComponent(940, 42, true);
			} else
				player.getPackets().sendGameMessage("You do not have enough inventory space.");
			player.getPackets().sendHideIComponent(940, 42, true);
		} else {
			player.getPackets().sendGameMessage("You do not have enough " + Settings.SERVER_NAME + " points to purchase that item.");
			player.getPackets().sendHideIComponent(940, 42, true);
		}
		player.getPackets().sendIComponentText(940, 31, player.getAvalonPoints() + "");
	}

	public static boolean bankContains(Player player, int itemId) {
		return player.getBank().getItem(itemId) != null;
	}

	public static boolean invContains(Player player, int itemId) {
		return player.getInventory().containsItem(itemId, 1);
	}

	public static void setAttribute(Player player, int slotId) {
		player.temporaryAttribute().put("dungshop", slotId);
	}

}
