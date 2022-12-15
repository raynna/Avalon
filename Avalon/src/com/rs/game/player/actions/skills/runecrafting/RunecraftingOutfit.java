package com.rs.game.player.actions.skills.runecrafting;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.HexColours;
import com.rs.utils.Utils;
import com.rs.utils.HexColours.Colour;

public class RunecraftingOutfit {

	/**
	 * @author Andreas 2020-01-10
	 */

	public enum Pieces {

		RC_HAT(21485),

		RC_BODY(21484),

		RC_LEGS(21486),

		RC_BOOTS(21487);

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

	public static boolean addPiece(Player player, int baseLevel) {
		if (hasAllPieces(player))
			return false;
		int chancePerRune = 3000;
		int level = (int) Math.ceil(baseLevel * 0.3) * 100;
		int randomLength = chancePerRune - level;
		int random = Utils.getRandom(randomLength);
		if (random >= 1)
			return false;
		Pieces piece = getRandomPiece(player);
		ItemDefinitions defs = ItemDefinitions.getItemDefinitions(piece.getItemId());
		player.sm("<img=5><col=b25200>You have recieved " + (HexColours.getShortMessage(Colour.RED, defs.getName())) + "!");
		if (player.getInventory().hasFreeSlots())
			player.getInventory().addItem(piece.getItemId(), 1);
		else
			World.updateGroundItem(new Item(piece.getItemId()), player, player, 60, 0);
		return true;
	}
}
