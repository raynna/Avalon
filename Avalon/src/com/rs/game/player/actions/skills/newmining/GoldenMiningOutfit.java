package com.rs.game.player.actions.skills.newmining;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.HexColours;
import com.rs.utils.Utils;
import com.rs.utils.HexColours.Colours;

public class GoldenMiningOutfit {

	/**
	 * @author Andreas 2020-01-10
	 */

	public enum Pieces {

		GOLDEN_MINING_HAT(20789),

		GOLDEN_MINING_BODY(20791),

		GOLDEN_MINING_LEGS(20790),

		GOLDEN_MINING_GLOVES(20787),

		GOLDEN_MINING_BOOTS(20788);

		private int itemId;

		private Pieces(int itemId) {
			this.setItemId(itemId);
		}

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}
	}

	public static Pieces[] data = Pieces.values();

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

	public static Pieces getRandomPiece(Player player) {
		Pieces piece = data[Utils.getRandom(data.length - 1)];
		while (player.getInventory().containsOneItem(piece.getItemId())
				|| player.getBank().containsOneItem(piece.getItemId())
				|| player.getEquipment().containsOneItem(piece.getItemId())) {
			piece = data[Utils.getRandom(data.length - 1)];
			continue;
		}
		return piece;
	}

	public static boolean addPiece(Player player) {
		if (hasAllPieces(player))
			return false;
		int randomLength = 100;
		int random = Utils.getRandom(randomLength);
		if (random >= 1)
			return false;
		Pieces piece = getRandomPiece(player);
		ItemDefinitions defs = ItemDefinitions.getItemDefinitions(piece.getItemId());
		player.sm("You have recieved " + (HexColours.getShortMessage(Colours.RED, defs.getName())) + "!");
		if (player.getInventory().hasFreeSlots())
			player.getInventory().addItem(piece.getItemId(), 1);
		else
			World.updateGroundItem(new Item(piece.getItemId()), player, player, 60, 0);
		return true;
	}
}
