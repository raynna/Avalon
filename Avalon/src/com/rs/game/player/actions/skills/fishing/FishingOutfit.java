package com.rs.game.player.actions.skills.fishing;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.HexColours;
import com.rs.utils.HexColours.Colour;
import com.rs.utils.Utils;

public class FishingOutfit {

	/**
	 * @author Andreas 2020-01-10
	 */

	public enum Pieces {

		FISHING_HAT(24427),

		FISHING_BODY(24428),

		FISHING_LEGS(24429),

		FISHING_BOOTS(24430),

		FISH_HAT(24431, 0.1);

		private int itemId;
		private double chance;

		private Pieces(int itemId) {
			this(itemId, 100);
		}

		private Pieces(int itemId, double chance) {
			this.setItemId(itemId);
			this.setChance(chance);
		}

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		public double getChance() {
			return chance;
		}

		public void setChance(double chance) {
			this.chance = chance;
		}
	}

	public static Pieces[] data = Pieces.values();

	/**
	 * @param hasAllPieces
	 * @return checks if you have all pieces, checks bank/inventory/equipement
	 */

	public static boolean hasAllPieces(Player player) {
		boolean hasPieces = true;
		for (Pieces pieces : data) {
			if (!player.getInventory().containsOneItem(pieces.getItemId())
					&& !player.getBank().containsOneItem(pieces.getItemId())
					&& !player.getEquipment().containsOneItem(pieces.getItemId()))
				hasPieces = false;
		}
		return hasPieces;
	}

	/**
	 * @param getRandomPiece(Player player)
	 * @return grabs random piece, if you have given piece it repicks
	 */

	public static Pieces getRandomPiece(Player player) {
		Pieces piece = data[Utils.getRandom(data.length - 1)];
		double random = Utils.getRandomDouble(100);
		if (piece == Pieces.FISH_HAT) {
			if (random <= piece.getChance())
				return piece;
			else
				piece = data[Utils.getRandom(data.length - 1)];
		}
		while (player.getInventory().containsOneItem(piece.getItemId())
				|| player.getBank().containsOneItem(piece.getItemId())
				|| player.getEquipment().containsOneItem(piece.getItemId())) {
			if (hasAllPieces(player))// for safety
				return null;
			piece = data[Utils.getRandom(data.length - 1)];
			continue;
		}
		return piece;
	}

	/**
	 * @param addPiece
	 * @return gives the piece 1/100 unless you already have all pieces.
	 */

	public static boolean addPiece(Player player) {
		if (hasAllPieces(player))
			return false;
		int randomLength = 100;
		int random = Utils.getRandom(randomLength);
		if (random >= 1)
			return false;
		Pieces piece = getRandomPiece(player);
		ItemDefinitions defs = ItemDefinitions.getItemDefinitions(piece.getItemId());
		player.sm("You have recieved a "
				+ (piece == Pieces.FISH_HAT ? (HexColours.getShortMessage(Colour.RED, "rare") + " item:") : "") + " "
				+ (HexColours.getShortMessage(Colour.RED, defs.getName())) + "!");
		if (player.getInventory().hasFreeSlots())
			player.getInventory().addItem(piece.getItemId(), 1);
		else
			World.updateGroundItem(new Item(piece.getItemId()), player, player, 60, 0);
		return true;
	}
}
