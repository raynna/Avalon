package com.rs.game.item;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.ItemConstants;
import com.rs.io.InputStream;
import com.rs.utils.EconomyPrices;
import com.rs.utils.ItemExamines;
import com.rs.utils.Utils;

public class FloorItem extends Item {

	private static final long serialVersionUID = -2287633342490535089L;

	private WorldTile tile;
	private String ownerName;
	private transient Player owner;
	// 0 visible, 1 invisible, 2 visible and reappears 30sec after taken
	private int type;
	private int tick;
	private boolean spawned;
	private boolean globalPicked;
	private String cantPickupBy;

	public FloorItem(int id) {
		super(id);
	}

	@Override
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public FloorItem(Item item, WorldTile tile, Player owner, boolean underGrave, boolean invisible, String ironmanName) {
		super(item.getId(), item.getAmount());
		this.tile = tile;
		if (owner != null)
			this.ownerName = owner.getUsername();
		this.owner = owner;
		this.type = invisible ? 1 : 0;
		this.setCantPickupBy(ironmanName);
	}

	public FloorItem(Item item, WorldTile tile, Player owner, boolean underGrave, boolean invisible) {
		super(item.getId(), item.getAmount());
		this.tile = tile;
		if (owner != null)
			this.ownerName = owner.getUsername();
		this.owner = owner;
		this.type = invisible ? 1 : 0;
	}

	
	public FloorItem(Item item, WorldTile tile, Player owner, boolean invisible, int tick, boolean spawned) {
		super(item.getId(), item.getAmount());
		this.tile = tile;
		this.owner = owner;
		this.type = invisible ? 1 : 0;
		this.tick = tick;
		this.spawned = spawned;
	}

	@Deprecated
	public FloorItem(Item item, WorldTile tile, boolean appearforever) {
		super(item.getId(), item.getAmount());
		this.tile = tile;
		this.type = appearforever ? 2 : 0;
	}

	public void setGlobalPicked(boolean picked) {
		this.globalPicked = picked;
	}

	public boolean isGlobalPicked() {
		return globalPicked;
	}

	public WorldTile getTile() {
		return tile;
	}

	public boolean isInvisible() {
		return type == 1;
	}

	public boolean isForever() {
		return type == 2;
	}

	public String getOwner() {
		return ownerName;
	}

	public static void handleExamine(final Player player, InputStream stream) {
		if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead())
			return;
		int y = stream.readUnsignedShort();
		int x = stream.readUnsignedShortLE();
		final int id = stream.readUnsignedShort();
		final WorldTile tile = new WorldTile(x, y, player.getPlane());
		final int regionId = tile.getRegionId();
		if (!player.getMapRegionsIds().contains(regionId))
			return;
		final FloorItem item = World.getRegion(regionId).getGroundItem(id, tile, player);
		if (item == null)
			return;
		long price;
		long amount;
		player.stopAll(false);
		final FloorItem floorItem = World.getRegion(regionId).getGroundItem(id, tile, player);
		if (floorItem == null)
			return;
		// player.getPackets().sendGameMessage(ItemExamines.getExamine(floorItem));
		if (!ItemConstants.isTradeable(floorItem)) {
			player.getPackets().sendGameMessage(
					"[Price Checker] " + (floorItem.getAmount() > 1 ? floorItem.getAmount() + " x " : "")
							+ floorItem.getDefinitions().getName() + " is untradeable.");
			return;
		}
		if (floorItem.getId() == 995) {
			player.getPackets()
					.sendGameMessage("[Price Checker] " + Utils.getFormattedNumber(floorItem.getAmount(), ',') + " x "
							+ floorItem.getDefinitions().getName() + ".");
			return;
		}
		if ((floorItem.getDefinitions().isNoted() || floorItem.getDefinitions().isStackable())
				&& floorItem.getAmount() > 1) {
			amount = floorItem.getAmount();
			price = EconomyPrices.getPrice(floorItem.getId()) * amount;
			player.getPackets()
					.sendGameMessage("[Price Checker] " + Utils.getFormattedNumber(floorItem.getAmount(), ',') + " x "
							+ floorItem.getDefinitions().getName() + ": " + Utils.formatDoubledAmount(price)
							+ " coins.");
		} else
			player.getPackets().sendGameMessage("[Price Checker] " + floorItem.getDefinitions().getName() + ": "
					+ Utils.getFormattedNumber(EconomyPrices.getPrice(floorItem.getId()), ',') + " coins.");
		if (player.isDeveloper())
			player.getPackets()
					.sendGameMessage(floorItem.getDefinitions().getName() + ", ItemId: "
							+ floorItem.getDefinitions().getId() + ", X: " + tile.getX() + ", Y: " + tile.getY()
							+ ", H: " + tile.getPlane() + ", Owner: " + floorItem.getOwner());
		player.getPackets().sendItemMessage(0, 15263739, id, x, y, ItemExamines.getExamine(new Item(id))); // ChatboxMessage
	}

	public boolean hasOwner() {
		return owner != null;
	}

	public void setInvisible(boolean invisible) {
		type = invisible ? 1 : 0;
	}

	public Player getOwn() {
		return owner;
	}

	public boolean isSpawned() {
		return spawned;
	}

	public int getTick() {
		return tick;
	}
	
	public void setCantPickupBy(String player) {
		cantPickupBy = player;
	}

	public boolean cantPickupBy(String player) {
		return cantPickupBy != null && cantPickupBy.equalsIgnoreCase(player);
	}

}
