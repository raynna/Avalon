package com.rs.net.encoders;

import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.DynamicRegion;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Region;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.npc.NPC;
import com.rs.game.player.HintIcon;
import com.rs.game.player.Player;
import com.rs.game.player.PublicChatMessage;
import com.rs.game.player.QuickChatMessage;
import com.rs.game.player.content.TicketSystem;
import com.rs.game.player.content.clans.ClansManager;
import com.rs.game.player.content.friendschat.FriendChatsManager;
import com.rs.game.player.content.grandexchange.Offer;
import com.rs.game.worldlist.WorldList;
import com.rs.io.OutputStream;
import com.rs.net.Session;
import com.rs.utils.Logger;
import com.rs.utils.MapArchiveKeys;
import com.rs.utils.Utils;
import com.rs.utils.huffman.Huffman;

public class WorldPacketsEncoder extends Encoder {

	private transient Player player;

	public WorldPacketsEncoder(Session session, Player player) {
		super(session);
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public void sendCSVarInteger(int id, int value) {
		if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE)
			sendCSVarInteger2(id, value);
		else
			sendCSVarInteger1(id, value);
	}

	public void sendClientState(int id) {
		OutputStream stream = new OutputStream(3);
		stream.writePacket(player, 150);
		stream.writeShort(id);
		session.write(stream);
	}

	public void sendCSVarInteger1(int id, int value) {
		OutputStream stream = new OutputStream(4);
		stream.writePacket(player, 154);
		stream.writeByteC(value);
		stream.writeShort128(id);
		session.write(stream);
	}

	public void sendCSVarInteger2(int id, int value) {
		OutputStream stream = new OutputStream(7);
		stream.writePacket(player, 63);
		stream.writeShort128(id);
		stream.writeInt(value);
		session.write(stream);
	}

	public void sendCustomPacket161() {
		OutputStream stream = new OutputStream(2);
		stream.writePacket(player, 161);
		int flag = 0x0;
		if (player.isShiftDrop())
			flag |= 0x1;
		if (player.isSlowDrag())
			flag |= 0x2;
		if (player.isZoom())
			flag |= 0x4;
		stream.writeByte(flag);
		session.write(stream);
	}

	public void sendDeveloperPacket() {
		OutputStream stream = new OutputStream(2);
		stream.writePacket(player, 162);
		int flag = 0x0;
		if (player.isDeveloperMode())
			flag |= 0x1;
		//if (player.getGameType() != null)
		//	flag |= 0x2;
		stream.writeByte(flag);
		session.write(stream);
	}

	public void sendMinimapFlag(int x, int y) {
		OutputStream stream = new OutputStream(3);
		stream.writePacket(player, 13);
		stream.writeByte128(y);
		stream.writeByte128(x);
		session.write(stream);
	}

	public void sendRootInterface(int id, int type) {
		int[] xteas = new int[4];
		player.getInterfaceManager().setWindowsPane(id);
		OutputStream stream = new OutputStream(4);
		stream.writePacket(player, 39);
		stream.write128Byte(type);
		stream.writeShort128(id);
		stream.writeIntLE(xteas[1]);
		stream.writeIntV2(xteas[0]);
		stream.writeInt(xteas[3]);
		stream.writeInt(xteas[2]);
		session.write(stream);
	}

	public void sendPouchInfusionOptionsScript(int interfaceId, int componentId, int slotLength, int width, int height,
			String... options) {
		Object[] parameters = new Object[5 + options.length];
		int index = 0;
		parameters[index++] = slotLength;
		parameters[index++] = 1; // dunno
		for (int count = options.length - 1; count >= 0; count--)
			parameters[index++] = options[count];
		parameters[index++] = height;
		parameters[index++] = width;
		parameters[index++] = interfaceId << 16 | componentId;
		sendRunScript(757, parameters);
	}

	public void sendScrollInfusionOptionsScript(int interfaceId, int componentId, int slotLength, int width, int height,
			String... options) {
		Object[] parameters = new Object[5 + options.length];
		int index = 0;
		parameters[index++] = slotLength;
		parameters[index++] = 1;
		for (int count = options.length - 1; count >= 0; count--)
			parameters[index++] = options[count];
		parameters[index++] = height;
		parameters[index++] = width;
		parameters[index++] = interfaceId << 16 | componentId;
		sendRunScript(763, parameters);
	}

	public void sendResetMinimapFlag() {
		OutputStream stream = new OutputStream(3);
		stream.writePacket(player, 13);
		stream.writeByte128(255);
		stream.writeByte128(255);
		session.write(stream);
	}

	public void sendPlayerUnderNPCPriority(boolean priority) {
		OutputStream stream = new OutputStream(2);
		stream.writePacket(player, 6);
		stream.writeByteC(priority ? 1 : 0);
		session.write(stream);
	}

	public void sendGrandExchangeOffer(Offer offer) {
		OutputStream stream = new OutputStream(21);
		stream.writePacket(player, 53);
		stream.writeByte(offer.getSlot());
		stream.writeByte(offer.getStage());
		if (offer.forceRemove())
			stream.skip(18);
		else {
			stream.writeShort(offer.getId());
			stream.writeInt(offer.getPrice());
			stream.writeInt(offer.getAmount());
			stream.writeInt(offer.getTotalAmmountSoFar());
			stream.writeInt(offer.getTotalPriceSoFar());
		}
		session.write(stream);
	}

	public void sendVar(int id, int value) {
		if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE)
			sendVar2(id, value);
		else
			sendVar1(id, value);
	}

	public void sendVarBit(int id, int value) {
		if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE)
			sendVarBit2(id, value);
		else
			sendVarBit1(id, value);
	}

	public void sendVar1(int id, int value) {
		OutputStream stream = new OutputStream(4);
		stream.writePacket(player, 110);
		stream.writeShortLE128(id);
		stream.writeByte128(value);
		session.write(stream);
	}

	public void sendVar2(int id, int value) {
		OutputStream stream = new OutputStream(7);
		stream.writePacket(player, 56);
		stream.writeShort128(id);
		stream.writeIntLE(value);
		session.write(stream);
	}

	public void sendVarBit1(int id, int value) {
		OutputStream stream = new OutputStream(4);
		stream.writePacket(player, 111);
		stream.writeShort128(id);
		stream.writeByteC(value);
		session.write(stream);
	}

	public void sendVarBit2(int id, int value) {
		OutputStream stream = new OutputStream(7);
		stream.writePacket(player, 81);
		stream.writeIntV1(value);
		stream.writeShort128(id);
		session.write(stream);
	}

	public void sendHintIcon(HintIcon icon) {
		OutputStream stream = new OutputStream(15);
		stream.writePacket(player, 79);
		stream.writeByte((icon.getTargetType() & 0x1f) | (icon.getIndex() << 5));
		if (icon.getTargetType() == 0)
			stream.skip(13);
		else {
			stream.writeByte(icon.getArrowType());
			if (icon.getTargetType() == 1 || icon.getTargetType() == 10) {
				stream.writeShort(icon.getTargetIndex());
				stream.writeShort(2500); // how often the arrow flashes, 2500
				// ideal, 0 never
				stream.skip(4);
			} else if ((icon.getTargetType() >= 2 && icon.getTargetType() <= 6)) { // directions
				stream.writeByte(icon.getPlane()); // unknown
				stream.writeShort(icon.getCoordX());
				stream.writeShort(icon.getCoordY());
				stream.writeByte(icon.getDistanceFromFloor() * 4 >> 2);
				stream.writeShort(-1); // distance to start showing on minimap,
				// 0 doesnt show, -1 infinite
			}
			stream.writeInt(icon.getModelId());
		}
		session.write(stream);

	}

	public void sendWeight(double weight) {
		final OutputStream stream = new OutputStream();
		stream.writePacket(player, 98);
		stream.writeShort((short) weight);
		session.write(stream);
	}

	public void sendCameraShake(int slotId, int b, int c, int d, int e) {
		OutputStream stream = new OutputStream(7);
		stream.writePacket(player, 44);
		stream.writeByte128(b);
		stream.writeByte128(slotId);
		stream.writeByte128(d);
		stream.writeByte128(c);
		stream.writeShortLE(e);
		session.write(stream);
	}

	public void sendStopCameraShake() {
		OutputStream stream = new OutputStream(1);
		stream.writePacket(player, 131);
		session.write(stream);
	}

	public void sendIComponentModel(int interfaceId, int componentId, int modelId) {
		OutputStream stream = new OutputStream(9);
		stream.writePacket(player, 102);
		stream.writeIntV1(modelId);
		stream.writeIntV1(interfaceId << 16 | componentId);
		session.write(stream);
	}

	public void sendIComponentSprite(int interfaceId, int componentId, int spriteId) {
		OutputStream stream = new OutputStream(11);
		stream.writePacket(player, 121);
		stream.writeInt(spriteId);
		stream.writeIntV2(interfaceId << 16 | componentId);
		session.write(stream);
	}

	public void sendHideIComponent(int interfaceId, int componentId, boolean hidden) {
		OutputStream stream = new OutputStream(6);
		stream.writePacket(player, 112);
		stream.writeIntV2(interfaceId << 16 | componentId);
		stream.writeByte(hidden ? 1 : 0);
		session.write(stream);
	}

	public void sendRemoveGroundItem(FloorItem item) {
		OutputStream stream = createWorldTileStream(item.getTile());
		int localX = item.getTile().getLocalX(player.getLastLoadedMapRegionTile(), player.getMapSize());
		int localY = item.getTile().getLocalY(player.getLastLoadedMapRegionTile(), player.getMapSize());
		int offsetX = localX - ((localX >> 3) << 3);
		int offsetY = localY - ((localY >> 3) << 3);
		stream.writePacket(player, 108);
		stream.writeShortLE(item.getId());
		stream.write128Byte((offsetX << 4) | offsetY);
		session.write(stream);

	}

	public void sendGroundItem(FloorItem item) {
		OutputStream stream = createWorldTileStream(item.getTile());
		int localX = item.getTile().getLocalX(player.getLastLoadedMapRegionTile(), player.getMapSize());
		int localY = item.getTile().getLocalY(player.getLastLoadedMapRegionTile(), player.getMapSize());
		int offsetX = localX - ((localX >> 3) << 3);
		int offsetY = localY - ((localY >> 3) << 3);
		stream.writePacket(player, 125);
		stream.writeByte128((offsetX << 4) | offsetY);
		stream.writeShortLE128(item.getAmount());
		stream.writeShortLE(item.getId());
		session.write(stream);
	}

	public void sendProjectile(Entity receiver, WorldTile startTile, WorldTile endTile, int gfxId, int startHeight,
			int endHeight, int speed, int delay, int curve, int startDistanceOffset, int creatorSize) {
		OutputStream stream = createWorldTileStream(startTile);
		stream.writePacket(player, 20);
		int localX = startTile.getLocalX(player.getLastLoadedMapRegionTile(), player.getMapSize());
		int localY = startTile.getLocalY(player.getLastLoadedMapRegionTile(), player.getMapSize());
		int offsetX = localX - ((localX >> 3) << 3);
		int offsetY = localY - ((localY >> 3) << 3);
		stream.writeByte((offsetX << 3) | offsetY);
		stream.writeByte(endTile.getX() - startTile.getX());
		stream.writeByte(endTile.getY() - startTile.getY());
		stream.writeShort(receiver == null ? 0
				: (receiver instanceof Player ? -(receiver.getIndex() + 1) : receiver.getIndex() + 1));
		stream.writeShort(gfxId);
		stream.writeByte(startHeight);
		stream.writeByte(endHeight);
		stream.writeShort(delay);
		int duration = (Utils.getDistance(startTile.getX(), startTile.getY(), endTile.getX(), endTile.getY()) * 30
				/ ((speed / 10) < 1 ? 1 : (speed / 10))) + delay;
		stream.writeShort(duration);
		stream.writeByte(curve);
		stream.writeShort(creatorSize * 64 + startDistanceOffset * 64);
		session.write(stream);

	}

	public void sendUnlockIComponentOptionSlots(int interfaceId, int componentId, int fromSlot, int toSlot,
			int... optionsSlots) {
		int settingsHash = 0;
		for (int slot : optionsSlots)
			settingsHash |= 2 << slot;
		sendIComponentSettings(interfaceId, componentId, fromSlot, toSlot, settingsHash);
	}

	public void sendIComponentSettings(int interfaceId, int componentId, int fromSlot, int toSlot, int settingsHash) {
		OutputStream stream = new OutputStream(13);
		stream.writePacket(player, 40);
		stream.writeIntV2(settingsHash);
		stream.writeInt(interfaceId << 16 | componentId);
		stream.writeShort128(fromSlot);
		stream.writeShortLE(toSlot);
		session.write(stream);
	}

	public void sendInterFlashScript(int interfaceId, int componentId, int width, int height, int slot) {
		Object[] parameters = new Object[4];
		int index = 0;
		parameters[index++] = slot;
		parameters[index++] = height;
		parameters[index++] = width;
		parameters[index++] = interfaceId << 16 | componentId;
		sendRunScript(143, parameters);
	}

	public void sendInterSetItemsOptionsScript(int interfaceId, int componentId, int key, int width, int height,
			String... options) {
		sendInterSetItemsOptionsScript(interfaceId, componentId, key, false, width, height, options);
	}

	public void sendInterSetItemsOptionsScript(int interfaceId, int componentId, int key, boolean negativeKey,
			int width, int height, String... options) {
		Object[] parameters = new Object[6 + options.length];
		int index = 0;
		for (int count = options.length - 1; count >= 0; count--)
			parameters[index++] = options[count];
		parameters[index++] = -1; // dunno but always this
		parameters[index++] = 0;// dunno but always this, maybe startslot?
		parameters[index++] = height;
		parameters[index++] = width;
		parameters[index++] = key;
		parameters[index++] = interfaceId << 16 | componentId;
		sendRunScript(negativeKey ? 695 : 150, parameters); // scriptid 150 does
															// that the method
		// name says*/
	}

	public void sendInterSetItemsOptionsScript2(int interfaceId, int componentId, int key, int width, int height,
			String... options) {
		sendInterSetItemsOptionsScript(interfaceId, componentId, key, false, width, height, options);
	}

	public void sendInterSetItemsOptionsScript2(int interfaceId, int componentId, int key, boolean negativeKey,
			int width, int height, String... options) {
		Object[] parameters = new Object[6 + options.length];
		int index = 0;
		for (int count = options.length - 1; count >= 0; count--)
			parameters[index++] = options[count];
		parameters[index++] = -1; // dunno but always this
		parameters[index++] = 0;// dunno but always this, maybe startslot?
		parameters[index++] = height;
		parameters[index++] = width;
		parameters[index++] = key;
		parameters[index++] = interfaceId << 16 | componentId;
		sendRunScript(negativeKey ? 695 : 156, parameters);
	}

	public void sendInputNameScript(String message) {
		sendRunScript(109, new Object[] { message });
	}

	public void sendInputIntegerScript(boolean integerEntryOnly, String message) {
		sendRunScript(108, new Object[] { message });
	}

	public void sendInputLongTextScript(String message) {
		sendRunScript(110, new Object[] { message });
	}

	public void sendRunScript(int scriptId, Object... params) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(player, 119);
		String parameterTypes = "";
		if (params != null) {
			for (int count = params.length - 1; count >= 0; count--) {
				if (params[count] instanceof String)
					parameterTypes += "s"; // string
				else
					parameterTypes += "i"; // integer
			}
		}
		stream.writeString(parameterTypes);
		if (params != null) {
			int index = 0;
			for (int count = parameterTypes.length() - 1; count >= 0; count--) {
				if (parameterTypes.charAt(count) == 's')
					stream.writeString((String) params[index++]);
				else
					stream.writeInt((Integer) params[index++]);
			}
		}
		stream.writeInt(scriptId);
		stream.endPacketVarShort();
		session.write(stream);
	}

	public void sendGlobalConfig(int id, int value) {
		if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE)
			sendGlobalConfig2(id, value);
		else
			sendGlobalConfig1(id, value);
	}

	public void sendGlobalConfig1(int id, int value) {
		OutputStream stream = new OutputStream(4);
		stream.writePacket(player, 154);
		stream.writeByteC(value);
		stream.writeShort128(id);
		session.write(stream);
	}

	public void sendGlobalConfig2(int id, int value) {
		OutputStream stream = new OutputStream(7);
		stream.writePacket(player, 63);
		stream.writeShort128(id);
		stream.writeInt(value);
		session.write(stream);
	}

	public void sendConfig(int id, int value) {
		if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE)
			sendConfig2(id, value);
		else
			sendConfig1(id, value);
	}

	public void sendConfigByFile(int fileId, int value) {
		if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE)
			sendConfigByFile2(fileId, value);
		else
			sendConfigByFile1(fileId, value);
	}

	public void sendConfig1(int id, int value) {
		OutputStream stream = new OutputStream(4);
		stream.writePacket(player, 110);
		stream.writeShortLE128(id);
		stream.writeByte128(value);
		session.write(stream);
	}

	public void sendConfig2(int id, int value) {
		OutputStream stream = new OutputStream(7);
		stream.writePacket(player, 56);
		stream.writeShort128(id);
		stream.writeIntLE(value);
		session.write(stream);
	}

	public void sendConfigByFile1(int fileId, int value) {
		OutputStream stream = new OutputStream(4);
		stream.writePacket(player, 111);
		stream.writeShort128(fileId);
		stream.writeByteC(value);
		session.write(stream);
	}

	public void sendConfigByFile2(int fileId, int value) {
		OutputStream stream = new OutputStream(7);
		stream.writePacket(player, 81);
		stream.writeIntV1(value);
		stream.writeShort128(fileId);
		session.write(stream);
	}

	public void sendRunEnergy() {
		OutputStream stream = new OutputStream(2);
		stream.writePacket(player, 25);
		stream.writeByte(player.getRunEnergy());
		session.write(stream);
	}

	public void sendIComponentText(int interfaceId, int componentId, String text) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(player, 135);
		stream.writeString(text);
		stream.writeInt(interfaceId << 16 | componentId);
		stream.endPacketVarShort();
		session.write(stream);

	}

	public void sendIComponentAnimation(int emoteId, int interfaceId, int componentId) {
		OutputStream stream = new OutputStream(9);
		stream.writePacket(player, 103);
		stream.writeIntV2(emoteId);
		stream.writeInt(interfaceId << 16 | componentId);
		session.write(stream);

	}

	public void sendItemOnIComponent(int interfaceid, int componentId, int id, int amount) {
		OutputStream stream = new OutputStream(12);
		stream.writePacket(player, 152);
		stream.writeShort128(id);
		stream.writeIntV1(amount);
		stream.writeByte(-1);
		stream.writeIntV2(interfaceid << 16 | componentId);
		session.write(stream);
	}

	public void sendEntityOnIComponent(boolean isPlayer, int entityId, int interfaceId, int componentId) {
		if (isPlayer)
			sendPlayerOnIComponent(interfaceId, componentId);
		else
			sendNPCOnIComponent(interfaceId, componentId, entityId);
	}

	public void sendWorldTile(WorldTile tile) {
		session.write(createWorldTileStream(tile));
	}

	public OutputStream createWorldTileStream(WorldTile tile) {
		OutputStream stream = new OutputStream(4);
		stream.writePacket(player, 158);
		stream.writeByte128(tile.getLocalY(player.getLastLoadedMapRegionTile(), player.getMapSize()) >> 3);
		stream.writeByteC(tile.getPlane());
		stream.write128Byte(tile.getLocalX(player.getLastLoadedMapRegionTile(), player.getMapSize()) >> 3);
		return stream;
	}

	public void sendObjectAnimation(WorldObject object, Animation animation) {
		OutputStream stream = new OutputStream(10);
		stream.writePacket(player, 76);
		stream.writeInt(animation.getIds()[0]);
		stream.writeByteC((object.getType() << 2) + (object.getRotation() & 0x3));
		stream.writeIntLE(object.getTileHash());
		session.write(stream);
	}

	public void sendTileMessage(String message, WorldTile tile, int color) {
		sendTileMessage(message, tile, 5000, 255, color);
	}

	public void sendTileMessage(String message, WorldTile tile, int delay, int height, int color) {
		OutputStream stream = createWorldTileStream(tile);
		stream.writePacketVarByte(player, 107);
		stream.skip(1);
		int localX = tile.getLocalX(player.getLastLoadedMapRegionTile(), player.getMapSize());
		int localY = tile.getLocalY(player.getLastLoadedMapRegionTile(), player.getMapSize());
		int offsetX = localX - ((localX >> 3) << 3);
		int offsetY = localY - ((localY >> 3) << 3);
		stream.writeByte((offsetX << 4) | offsetY);
		stream.writeShort(delay / 30);
		stream.writeByte(height);
		stream.write24BitInteger(color);
		stream.writeString(message);
		stream.endPacketVarByte();
		session.write(stream);
	}


	public void sendDestroyObject(WorldObject object) {
		OutputStream stream = createWorldTileStream(object);
		int localX = object.getLocalX(player.getLastLoadedMapRegionTile(), player.getMapSize());
		int localY = object.getLocalY(player.getLastLoadedMapRegionTile(), player.getMapSize());
		int offsetX = localX - ((localX >> 3) << 3);
		int offsetY = localY - ((localY >> 3) << 3);
		stream.writePacket(player, 45);
		stream.writeByteC((object.getType() << 2) + (object.getRotation() & 0x3));
		stream.writeByte128((offsetX << 4) | offsetY);
		session.write(stream);

	}

	public void sendPlayerOnIComponent(int interfaceId, int componentId) {
		OutputStream stream = new OutputStream(5);
		stream.writePacket(player, 23);
		stream.writeIntV2(interfaceId << 16 | componentId);
		session.write(stream);

	}

	public void sendNPCOnIComponent(int interfaceId, int componentId, int npcId) {
		OutputStream stream = new OutputStream(9);
		stream.writePacket(player, 31);
		stream.writeInt(npcId);
		stream.writeInt(interfaceId << 16 | componentId);
		session.write(stream);

	}

	public void sendRandomOnIComponent(int interfaceId, int componentId, int id) {
		/*
		 * OutputStream stream = new OutputStream(); stream.writePacket(player, 235);
		 * stream.writeShort(id); stream.writeIntV1(interfaceId << 16 | componentId);
		 * stream.writeShort(interPacketsCount++); session.write(stream);
		 */
	}

	public void sendFaceOnIComponent(int interfaceId, int componentId, int look1, int look2, int look3) {
		/*
		 * OutputStream stream = new OutputStream(); stream.writePacket(player, 192);
		 * stream.writeIntV2(interfaceId << 16 | componentId);
		 * stream.writeShortLE128(interPacketsCount++); stream.writeShortLE128(look1);
		 * stream.writeShortLE128(look2); stream.writeShort128(look2);
		 * session.write(stream);
		 */
	}

	public void sendFriendsChatChannel() {
		FriendChatsManager manager = player.getCurrentFriendChat();
		OutputStream stream = new OutputStream(manager == null ? 3 : manager.getDataBlock().length + 3);
		stream.writePacketVarShort(player, 117);
		if (manager != null)
			stream.writeBytes(manager.getDataBlock());
		stream.endPacketVarShort();
		session.write(stream);
	}

	public void sendClanChannel(ClansManager manager, boolean myClan) {
		OutputStream stream = new OutputStream(manager == null ? 4 : manager.getClanChannelDataBlock().length + 4);
		stream.writePacketVarShort(player, 85);
		stream.writeByte(myClan ? 1 : 0);
		if (manager != null)
			stream.writeBytes(manager.getClanChannelDataBlock());
		stream.endPacketVarShort();
		session.write(stream);
	}

	public void sendClanSettings(ClansManager manager, boolean myClan) {
		OutputStream stream = new OutputStream(manager == null ? 4 : manager.getClanSettingsDataBlock().length + 4);
		stream.writePacketVarShort(player, 133);
		stream.writeByte(myClan ? 1 : 0);
		if (manager != null)
			stream.writeBytes(manager.getClanSettingsDataBlock());
		stream.endPacketVarShort();
		session.write(stream);
	}

	public void sendFriends() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(player, 2);
		for (String username : player.getFriendsIgnores().getFriends()) {
			String displayName;
			Player p2 = World.getPlayerByDisplayName(username);
			if (p2 != null)
				displayName = p2.getDisplayName();
			else
				displayName = Utils.formatPlayerNameForDisplay(username);
			player.getPackets().sendFriend(Utils.formatPlayerNameForDisplay(username), displayName, 1,
					p2 != null && player.getFriendsIgnores().isOnline(p2), false, stream);
		}
		stream.endPacketVarShort();
		session.write(stream);
	}

	public void sendFriend(String username, String displayName, int world, boolean putOnline, boolean warnMessage) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(player, 2);
		sendFriend(username, displayName, world, putOnline, warnMessage, stream);
		stream.endPacketVarShort();
		session.write(stream);
	}

	public void sendFriend(String username, String displayName, int world, boolean putOnline, boolean warnMessage,
			OutputStream stream) {
		stream.writeByte(warnMessage ? 0 : 1);
		stream.writeString(displayName);
		stream.writeString(displayName.equals(username) ? "" : username);
		stream.writeShort(putOnline ? world : 0);
		stream.writeByte(player.getFriendsIgnores().getRank(Utils.formatPlayerNameForProtocol(username)));
		stream.writeByte(0);
		if (putOnline) {
			stream.writeString(Settings.SERVER_NAME);
			stream.writeByte(0);
		}
	}

	public void sendIgnores() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(player, 55);
		stream.writeByte(player.getFriendsIgnores().getIgnores().size());
		for (String username : player.getFriendsIgnores().getIgnores()) {
			String display;
			Player p2 = World.getPlayerByDisplayName(username);
			if (p2 != null)
				display = p2.getDisplayName();
			else
				display = Utils.formatPlayerNameForDisplay(username);
			String name = Utils.formatPlayerNameForDisplay(username);
			stream.writeString(display.equals(name) ? name : display);
			stream.writeString("");
			stream.writeString(display.equals(name) ? "" : name);
			stream.writeString("");
		}
		stream.endPacketVarShort();
		session.write(stream);
	}

	public void sendIgnore(String name, String display, boolean updateName) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarByte(player, 128);
		stream.writeByte(0x2);
		stream.writeString(display.equals(name) ? name : display);
		stream.writeString("");
		stream.writeString(display.equals(name) ? "" : name);
		stream.writeString("");
		stream.endPacketVarByte();
		session.write(stream);
	}

	public void receiveClanChatMessage(boolean myClan, String display, int rights, String message) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarByte(player, 3);
		stream.writeByte(myClan ? 1 : 0);
		stream.writeString(display);
		for (int i = 0; i < 5; i++)
			stream.writeByte(Utils.getRandom(255));
		stream.writeByte(0);
		Huffman.sendEncryptMessage(stream, message);
		stream.endPacketVarByte();
		session.write(stream);
	}

	public void receiveClanChatQuickMessage(boolean myClan, String display, int rights, QuickChatMessage message) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarByte(player, 1);
		stream.writeByte(myClan ? 1 : 0);
		stream.writeString(display);
		for (int i = 0; i < 5; i++)
			stream.writeByte(Utils.getRandom(255));
		stream.writeByte(rights);
		stream.writeShort(message.getFileId());
		if (message.getMessage() != null)
			stream.writeBytes(message.getMessage().getBytes());
		stream.endPacketVarByte();
		session.write(stream);
	}

	public void sendPrivateMessage(String username, String message) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(player, 130);
		stream.writeString(username);
		Huffman.sendEncryptMessage(stream, message);
		stream.endPacketVarShort();
		session.write(stream);
	}

	public void sendGameBarStages() {
		player.getVarsManager().sendVar(1054, player.getClanStatus());
		player.getVarsManager().sendVar(1055, player.getAssistStatus());
		player.getVarsManager().sendVar(1056, player.isFilterGame() ? 1 : 0);
		player.getVarsManager().sendVar(2159, player.getFriendsIgnores().getFriendsChatStatus());
		sendOtherGameBarStages();
		sendPrivateGameBarStage();
	}

	public void sendOtherGameBarStages() {
		OutputStream stream = new OutputStream(3);
		stream.writePacket(player, 89);
		stream.write128Byte(player.getTradeStatus());
		stream.writeByte(player.getPublicStatus());
		session.write(stream);
	}

	public void sendPrivateGameBarStage() {
		OutputStream stream = new OutputStream(2);
		stream.writePacket(player, 75);
		stream.writeByte(player.getFriendsIgnores().getPrivateStatus());
		session.write(stream);
	}

	public void receivePrivateMessage(String name, String display, int rights, String message) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(player, 105);
		stream.writeByte(name.equals(display) ? 0 : 1);
		if (!name.equals(display))
			stream.writeString(name);
		else
			stream.writeString(display);
		for (int i = 0; i < 5; i++)
			stream.writeByte(Utils.getRandom(255));
		stream.writeByte(rights);
		Huffman.sendEncryptMessage(stream, message);
		stream.endPacketVarShort();
		session.write(stream);
	}

	// 131 clan chat quick message

	public void receivePrivateChatQuickMessage(String name, String display, int rights, QuickChatMessage message) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarByte(player, 104);
		stream.writeByte(name.equals(display) ? 0 : 1);
		stream.writeString(display);
		if (!name.equals(display))
			stream.writeString(name);
		for (int i = 0; i < 5; i++)
			stream.writeByte(Utils.getRandom(255));
		stream.writeByte(rights);
		stream.writeShort(message.getFileId());
		if (message.getMessage() != null)
			stream.writeBytes(message.getMessage().getBytes());
		stream.endPacketVarByte();
		session.write(stream);
	}

	public void sendPrivateQuickMessageMessage(String username, QuickChatMessage message) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarByte(player, 30);
		stream.writeString(username);
		stream.writeShort(message.getFileId());
		if (message.getMessage() != null)
			stream.writeBytes(message.getMessage().getBytes());
		stream.endPacketVarByte();
		session.write(stream);
	}

	public void receiveFriendChatMessage(String name, String display, int rights, String chatName, String message) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarByte(player, 139);
		stream.writeByte(name.equals(display) ? 0 : 1);
		stream.writeString(display);
		if (!name.equals(display))
			stream.writeString(name);
		stream.writeLong(Utils.stringToLong(chatName));
		for (int i = 0; i < 5; i++)
			stream.writeByte(Utils.getRandom(255));
		stream.writeByte(rights);
		Huffman.sendEncryptMessage(stream, message);
		stream.endPacketVarByte();
		session.write(stream);
	}

	public void receiveFriendChatQuickMessage(String name, String display, int rights, String chatName,
			QuickChatMessage message) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarByte(player, 32);
		stream.writeByte(name.equals(display) ? 0 : 1);
		stream.writeString(display);
		if (!name.equals(display))
			stream.writeString(name);
		stream.writeLong(Utils.stringToLong(chatName));
		for (int i = 0; i < 5; i++)
			stream.writeByte(Utils.getRandom(255));
		stream.writeByte(rights);
		stream.writeShort(message.getFileId());
		if (message.getMessage() != null)
			stream.writeBytes(message.getMessage().getBytes());
		stream.endPacketVarByte();
		session.write(stream);
	}

	/*
	 * useless, sending friends unlocks it
	 */
	public void sendUnlockIgnoreList() {
		OutputStream stream = new OutputStream(1);
		stream.writePacket(player, 18);
		session.write(stream);
	}

	/*
	 * dynamic map region
	 */
	public void sendDynamicMapRegion(boolean sendLswp) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(player, 144);
		if (sendLswp)
			player.getLocalPlayerUpdate().init(stream);
		int regionX = player.getChunkX();
		int regionY = player.getChunkY();
		stream.write128Byte(2);
		stream.writeShortLE(regionY);
		stream.writeShortLE128(regionX);
		stream.write128Byte(player.isForceNextMapLoadRefresh() ? 1 : 0);
		stream.writeByteC(player.getMapSize());
		stream.initBitAccess();
		int mapHash = Settings.MAP_SIZES[player.getMapSize()] >> 4;
		int[] realRegionIds = new int[4 * mapHash * mapHash];
		int realRegionIdsCount = 0;
		for (int plane = 0; plane < 4; plane++) {
			for (int thisRegionX = (regionX - mapHash); thisRegionX <= ((regionX + mapHash)); thisRegionX++) { // real
																												// x
																												// calcs
				for (int thisRegionY = (regionY - mapHash); thisRegionY <= ((regionY + mapHash)); thisRegionY++) { // real
																													// y
																													// calcs
					int regionId = (((thisRegionX / 8) << 8) + (thisRegionY / 8));
					Region region = World.getRegions().get(regionId);
					int realRegionX;
					int realRegionY;
					int realPlane;
					int rotation;
					if (region instanceof DynamicRegion) { // generated map
						DynamicRegion dynamicRegion = (DynamicRegion) region;
						int[] regionCoords = dynamicRegion.getRegionCoords()[plane][thisRegionX
								- ((thisRegionX / 8) * 8)][thisRegionY - ((thisRegionY / 8) * 8)];
						realRegionX = regionCoords[0];
						realRegionY = regionCoords[1];
						realPlane = regionCoords[2];
						rotation = regionCoords[3];
					} else { // real map
						// base region + difference * 8 so gets real region
						// coords
						realRegionX = thisRegionX;
						realRegionY = thisRegionY;
						realPlane = plane;
						rotation = 0;// no rotation
					}
					// invalid region, not built region
					if (realRegionX == 0 || realRegionY == 0)
						stream.writeBits(1, 0);
					else {
						stream.writeBits(1, 1);
						stream.writeBits(26,
								(rotation << 1) | (realPlane << 24) | (realRegionX << 14) | (realRegionY << 3));
						int realRegionId = (((realRegionX / 8) << 8) + (realRegionY / 8));
						boolean found = false;
						for (int index = 0; index < realRegionIdsCount; index++)
							if (realRegionIds[index] == realRegionId) {
								found = true;
								break;
							}
						if (!found)
							realRegionIds[realRegionIdsCount++] = realRegionId;
					}

				}
			}
		}
		stream.finishBitAccess();
		for (int index = 0; index < realRegionIdsCount; index++) {
			int[] xteas = MapArchiveKeys.getMapKeys(realRegionIds[index]);
			if (xteas == null)
				xteas = new int[4];
			for (int keyIndex = 0; keyIndex < 4; keyIndex++)
				stream.writeInt(xteas[keyIndex]);
		}
		stream.endPacketVarShort();
		session.write(stream);
	}

	/*
	 * normal map region
	 */
	public void sendMapRegion(boolean sendLswp) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(player, 42);
		if (sendLswp)
			player.getLocalPlayerUpdate().init(stream);
		stream.writeByteC(player.getMapSize());
		stream.writeByte(player.isForceNextMapLoadRefresh() ? 1 : 0);
		stream.writeShort(player.getChunkX());
		stream.writeShort(player.getChunkY());
		for (int regionId : player.getMapRegionsIds()) {
			int[] xteas = MapArchiveKeys.getMapKeys(regionId);
			if (xteas == null)
				xteas = new int[4];
			for (int index = 0; index < 4; index++)
				stream.writeInt(xteas[index]);
		}
		stream.endPacketVarShort();
		session.write(stream);
	}

	public void sendCutscene(int id) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(player, 142);
		stream.writeShort(id);
		stream.writeShort(20); // xteas count
		for (int count = 0; count < 20; count++)
			// xteas
			for (int i = 0; i < 4; i++)
				stream.writeInt(0);
		byte[] appearence = player.getAppearence().getAppeareanceData();
		stream.writeByte(appearence.length);
		stream.writeBytes(appearence);
		stream.endPacketVarShort();
		session.write(stream);
	}

	/*
	 * sets the pane interface
	 */
	public void sendWindowsPane(int id, int type) {
		int[] xteas = new int[4];
		player.getInterfaceManager().setWindowsPane(id);
		OutputStream stream = new OutputStream(4);
		stream.writePacket(player, 39);
		stream.write128Byte(type);
		stream.writeShort128(id);
		stream.writeIntLE(xteas[1]);
		stream.writeIntV2(xteas[0]);
		stream.writeInt(xteas[3]);
		stream.writeInt(xteas[2]);
		session.write(stream);
	}

	public void sendPlayerOption(String option, int slot, boolean top) {
		sendPlayerOption(option, slot, top, -1);
	}

	public void sendPublicMessage(Player p, PublicChatMessage message) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarByte(player, 106);
		stream.writeShort(p.getIndex());
		stream.writeShort(message.getEffects());
		int rights = p.getMessageIcon();
		stream.writeByte(rights);
		if (message instanceof QuickChatMessage) {
			QuickChatMessage qcMessage = (QuickChatMessage) message;
			stream.writeShort(qcMessage.getFileId());
			if (qcMessage.getMessage() != null)
				stream.writeBytes(message.getMessage().getBytes());
		} else {
			byte[] chatStr = new byte[250];
			chatStr[0] = (byte) message.getMessage().length();
			int offset = 1 + Huffman.encryptMessage(1, message.getMessage().length(), chatStr, 0,
					message.getMessage().getBytes());
			stream.writeBytes(chatStr, 0, offset);
		}
		stream.endPacketVarByte();
		session.write(stream);
	}

	public void sendPlayerOption(String option, int slot, boolean top, int cursor) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarByte(player, 118);
		stream.writeByte128(slot);
		stream.writeString(option);
		stream.writeShortLE128(cursor);
		stream.writeByteC(top ? 1 : 0);
		stream.endPacketVarByte();
		session.write(stream);
	}

	/*
	 * sends local players update
	 */
	public void sendLocalPlayersUpdate() {
		session.write(player.getLocalPlayerUpdate().createPacketAndProcess());
	}

	/*
	 * sends local npcs update
	 */
	public void sendLocalNPCsUpdate() {
		session.write(player.getLocalNPCUpdate().createPacketAndProcess());
	}

	public void sendGraphics(Graphics graphics, Object target) {
		OutputStream stream = new OutputStream(13);
		int hash = 0;
		if (target instanceof Player) {
			Player p = (Player) target;
			hash = p.getIndex() & 0xffff | 1 << 28;
		} else if (target instanceof NPC) {
			NPC n = (NPC) target;
			hash = n.getIndex() & 0xffff | 1 << 29;
		} else {
			WorldTile tile = (WorldTile) target;
			hash = tile.getPlane() << 28 | tile.getX() << 14 | tile.getY() & 0x3fff | 1 << 30;
		}
		stream.writePacket(player, 90);
		stream.writeShort(graphics.getId());
		stream.writeByte128(0); // slot id used for entitys
		stream.writeShort(graphics.getSpeed());
		stream.writeByte128(graphics.getSettings2Hash());
		stream.writeShort(graphics.getHeight());
		stream.writeIntLE(hash);
		session.write(stream);
	}

	public void sendDelayedGraphics(Graphics graphics, int delay, WorldTile tile) {

	}

	public void closeInterface(int windowComponentId) {
		closeInterface(player.getInterfaceManager().getTabWindow(windowComponentId), windowComponentId);
		player.getInterfaceManager().removeTab(windowComponentId);
	}

	public void closeInterface(int windowId, int windowComponentId) {
		OutputStream stream = new OutputStream(5);
		stream.writePacket(player, 5);
		stream.writeIntLE(windowId << 16 | windowComponentId);
		session.write(stream);
	}

	public void sendInterface(boolean nocliped, int windowId, int windowComponentId, int interfaceId) {
		// currently fixes the inter engine.. not ready for same component
		// ids(tabs), different inters
		if (!(windowId == 752 && (windowComponentId == 9 || windowComponentId == 12))) { // if
																							// chatbox
			if (player.getInterfaceManager().containsInterface(windowComponentId, interfaceId))
				closeInterface(windowComponentId);
			if (!player.getInterfaceManager().addInterface(windowId, windowComponentId, interfaceId)) {
				Logger.log(this,
						"Error adding interface: " + windowId + " , " + windowComponentId + " , " + interfaceId);
				return;
			}
		}
		int[] xteas = new int[4];
		OutputStream stream = new OutputStream(24);
		stream.writePacket(player, 14);
		stream.writeShort(interfaceId);
		stream.writeInt(xteas[0]);
		stream.writeIntV2(xteas[1]);
		stream.writeIntV1(windowId << 16 | windowComponentId);
		stream.writeByte(nocliped ? 1 : 0);
		stream.writeIntV1(xteas[3]);
		stream.writeIntV2(xteas[2]);
		session.write(stream);
	}

	public void sendSystemUpdate(int delay) {
		OutputStream stream = new OutputStream(3);
		stream.writePacket(player, 141);
		stream.writeShort((int) (delay * 1.6));
		session.write(stream);
	}

	public void sendUpdateItems(int key, ItemsContainer<Item> items, int... slots) {
		sendUpdateItems(key, items.getContainerItems(), slots);
	}

	public void sendUpdateItems(int key, Item[] items, int... slots) {
		sendUpdateItems(key, key < 0, items, slots);
	}

	public void sendUpdateItems(int key, boolean negativeKey, Item[] items, int... slots) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(player, 138);
		stream.writeShort(key);
		stream.writeByte(negativeKey ? 1 : 0);
		for (int slotId : slots) {
			if (slotId >= items.length)
				continue;
			stream.writeSmart(slotId);
			int id = -1;
			int amount = 0;
			Item item = items[slotId];
			if (item != null) {
				id = item.getId();
				amount = item.getAmount();
			}
			stream.writeShort(id + 1);
			if (id != -1) {
				stream.writeByte(amount >= 255 ? 255 : amount);
				if (amount >= 255)
					stream.writeInt(amount);
			}
		}
		stream.endPacketVarShort();
		session.write(stream);
	}

	public void sendGlobalString(int id, String string) {
		OutputStream stream = new OutputStream();
		if (string.length() > 253) {
			stream.writePacketVarShort(player, 34);
			stream.writeString(string);
			stream.writeShort(id);
			stream.endPacketVarShort();
		} else {
			stream.writePacketVarByte(player, 134);
			stream.writeShort(id);
			stream.writeString(string);
			stream.endPacketVarByte();
		}
		session.write(stream);
	}

	public void sendItems(int key, ItemsContainer<Item> items) {
		sendItems(key, key < 0, items);
	}

	public void sendItems(int key, boolean negativeKey, ItemsContainer<Item> items) {
		sendItems(key, negativeKey, items.getContainerItems());
	}

	public void sendItems(int key, Item[] items) {
		sendItems(key, key < 0, items);
	}

	public void resetItems(int key, boolean negativeKey, int size) {
		sendItems(key, negativeKey, new Item[size]);
	}

	public void sendItems(int key, boolean negativeKey, Item[] items) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(player, 77);
		stream.writeShort(key); // negativeKey ? -key : key
		stream.writeByte(negativeKey ? 1 : 0);
		stream.writeShort(items.length);
		for (int index = 0; index < items.length; index++) {
			Item item = items[index];
			int id = -1;
			int amount = 0;
			if (item != null) {
				id = item.getId();
				amount = item.getAmount();
			}
			stream.writeShortLE128(id + 1);
			stream.writeByte128(amount >= 255 ? 255 : amount);
			if (amount >= 255)
				stream.writeIntV1(amount);
		}
		stream.endPacketVarShort();
		session.write(stream);
	}

	public void sendLogout(boolean lobby) {
		// Highscores.highscores(player, null);
		OutputStream stream = new OutputStream();
		stream.writePacket(player, lobby ? 59 : 60);
		ChannelFuture future = session.write(stream);
		if (future != null)
			future.addListener(ChannelFutureListener.CLOSE);
		else
			session.getChannel().close();
		TicketSystem.destroyChatOnLogOut(player);
	}

	public void sendInventoryMessage(int border, int slotId, String message) {
		sendGameMessage(message);
		sendRunScript(948, border, slotId, message);
	}

	public void sendPlayerMessage(int border, Player player, String message) {
		sendGameMessage(message);
		sendGlobalString(306, message);
		sendGlobalConfig(1699, 15263739);
		sendGlobalConfig(1700, border);
		sendGlobalConfig(1695, 1);
		sendPlayerInterface(player, true, 746, 1, 1177);
	}

	public void sendNPCMessage(int border, NPC npc, String message) {
		sendGameMessage(message);
		sendGlobalString(306, message);
		sendGlobalConfig(1699, 15263739);
		sendGlobalConfig(1700, border);
		sendGlobalConfig(1695, 1);
		sendNPCInterface(npc, true, 746, 1, 1177);
	}

	public void sendObjectMessage(int border, WorldObject object, String message) {
		sendGameMessage(message);
		sendGlobalString(306, message);
		sendGlobalConfig(1699, 15263739);
		sendGlobalConfig(1700, border);
		sendGlobalConfig(1695, 1);
		sendObjectInterface(object, true, 746, 1, 1177);
	}

	public void sendObjectInterface(WorldObject object, boolean nocliped, int windowId, int windowComponentId,
			int interfaceId) {
		int[] xteas = new int[4];
		OutputStream stream = new OutputStream(33);
		stream.writePacket(player, 143);
		stream.writeIntV2(xteas[1]);
		stream.writeByte(nocliped ? 1 : 0);
		stream.writeIntLE(xteas[2]);
		stream.writeIntV1(object.getId());
		stream.writeByte128((object.getType() << 2) | (object.getRotation() & 0x3));
		stream.writeInt((object.getPlane() << 28) | (object.getX() << 14) | object.getY());
		stream.writeIntV2((windowId << 16) | windowComponentId);
		stream.writeShort(interfaceId);
		stream.writeInt(xteas[3]);
		stream.writeInt(xteas[0]);
		session.write(stream);
	}

	public void sendPlayerInterface(Player player, boolean nocliped, int windowId, int windowComponentId,
			int interfaceId) {
		int[] xteas = new int[4];
		OutputStream stream = new OutputStream(26);
		stream.writePacket(player, 57);
		stream.writeIntV2(xteas[0]);
		stream.writeShortLE128(player.getIndex());
		stream.writeByte128(nocliped ? 1 : 0);
		stream.writeInt(xteas[3]);
		stream.writeShortLE128(interfaceId);
		stream.writeIntLE(xteas[2]);
		stream.writeIntV2(xteas[1]);
		stream.writeIntV1(windowId << 16 | windowComponentId);
		session.write(stream);
	}

	public void sendNPCInterface(NPC npc, boolean nocliped, int windowId, int windowComponentId, int interfaceId) {
		int[] xteas = new int[4];
		OutputStream stream = new OutputStream(26);
		stream.writePacket(player, 57);
		stream.writeIntV2(xteas[0]);
		stream.writeShortLE128(npc.getIndex());
		stream.writeByte128(nocliped ? 1 : 0);
		stream.writeInt(xteas[3]);
		stream.writeShortLE128(interfaceId);
		stream.writeIntLE(xteas[2]);
		stream.writeIntV2(xteas[1]);
		stream.writeIntV1(windowId << 16 | windowComponentId);
		session.write(stream);
	}

	public void sendClientTrayMessage(int type, String message) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(player, 160);
		stream.writeString(message);
		stream.writeByte(type);
		stream.endPacketVarShort();
		session.write(stream);
	}

	public void sendGameMessage(String text) {
		sendGameMessage(text, false);
	}

	public void sendGameMessage(String text, Object... args) {
		sendGameMessage(String.format(text, args));
	}

	public void sendFilteredGameMessage(boolean filter, String text, Object... args) {
		sendMessage(filter ? 109 : 0, String.format(text, args), null);
	}

	public void sendGameMessage(String text, boolean filter) {
		sendMessage(filter ? 109 : 0, text, null);
	}

	public void sendSpriteOnIComponent(int interfaceId, int componentId, int spriteId) {
		OutputStream stream = new OutputStream(11);
		stream.writePacket(player, 121);
		stream.writeInt(spriteId);
		stream.writeIntV2(interfaceId << 16 | componentId);
		session.write(stream);
	}

	public void sendColoredMessage(String text, boolean filter) {
		if (player.getInterfaceManager().isResizableScreen()) {
			text = "<col=ffff00>" + text;
		} else {
			text = "<col=0000ff>" + text;
		}
		sendMessage(filter ? 109 : 0, text, null);
	}

	public void sendPanelBoxMessage(String text) {
		sendMessage(player.isDeveloper() ? 99 : 0, text, null);
	}

	public void sendAssistMessage(Player p) {
		sendMessage(102, "is requesting your assistance.", p);
	}

	public void sendTradeRequestMessage(Player p) {
		sendMessage(100, "wishes to trade with you.", p);
	}

	public void sendClanWarsRequestMessage(Player p) {
		sendMessage(101, "wishes to challenge your clan to a clan war.", p);
	}

	public void sendClanInviteMessage(Player p) {
		sendMessage(117, p.getDisplayName() + " is inviting you to join their clan.", p);
	}

	public void sendDuelChallengeRequestMessage(Player p, boolean friendly) {
		sendMessage(101, "wishes to duel with you(" + (friendly ? "friendly" : "stake") + ").", p);
	}

	public void sendMessage(int type, String text, Player p) {
		int maskData = 0;
		if (type == 1337) {
			return;
		}
		if (p != null) {
			maskData |= 0x1;
			if (p.hasDisplayName())
				maskData |= 0x2;
		}
		OutputStream stream = new OutputStream();
		stream.writePacketVarByte(player, 136);
		stream.writeSmart(type);
		stream.writeInt(player.getTileHash()); // junk, not used by client
		stream.writeByte(maskData);
		if ((maskData & 0x1) != 0) {
			stream.writeString(p.getDisplayName());
			if (p.hasDisplayName())
				stream.writeString(Utils.formatPlayerNameForDisplay(p.getUsername()));
		}
		stream.writeString(text);
		stream.endPacketVarByte();
		session.write(stream);
	}

	// effect type 1 or 2(index4 or index14 format, index15 format unusused by
	// jagex for now)
	public void sendSound(int id, int delay, int effectType) {
		if (effectType == 1)
			sendIndex14Sound(id, delay);
		else if (effectType == 2)
			sendIndex15Sound(id, delay);
	}

	public void sendVoice(int id) {
		resetSounds();
		sendSound(id, 0, 2);
	}

	public void resetSounds() {
		OutputStream stream = new OutputStream(1);
		stream.writePacket(player, 145);
		session.write(stream);
	}

	public void sendIndex14Sound(int id, int delay) {
		OutputStream stream = new OutputStream(9);
		stream.writePacket(player, 26);
		stream.writeShort(id);
		stream.writeByte(1);// repeated amount
		stream.writeShort(delay);
		stream.writeByte(255);
		stream.writeShort(256);
		session.write(stream);
	}

	public void sendIndex15Sound(int id, int delay) {
		OutputStream stream = new OutputStream(7);
		stream.writePacket(player, 70);
		stream.writeShort(id);
		stream.writeByte(1); // amt of times it repeats
		stream.writeShort(delay);
		stream.writeByte(255); // volume
		session.write(stream);
	}

	public void sendMusicEffect(int id) {
		OutputStream stream = new OutputStream(7);
		stream.writePacket(player, 9);
		stream.write128Byte(255); // volume
		stream.write24BitIntegerV2(0);
		stream.writeShort(id);
		session.write(stream);
	}

	public void sendMusic(int id) {
		sendMusic(id, 100, 255);
	}

	public void sendMusic(int id, int delay, int volume) {
		OutputStream stream = new OutputStream(5);
		stream.writePacket(player, 129);
		stream.writeByte(delay);
		stream.writeShortLE128(id);
		stream.writeByte128(volume);
		session.write(stream);
	}

	public void sendSkillLevel(int skill) {
		OutputStream stream = new OutputStream(7);
		stream.writePacket(player, 146);
		stream.write128Byte(skill); // was 128byte
		stream.writeInt((int) player.getSkills().getXp(skill));
		stream.writeByte128(player.getSkills().getLevel(skill));
		session.write(stream);
	}

	// CUTSCENE PACKETS START

	/**
	 * This will blackout specified area.
	 * 
	 * @param area area = area which will be blackout (0 = unblackout; 1 = blackout
	 *             orb; 2 = blackout map; 5 = blackout orb and map)
	 */
	public void sendBlackOut(int area) {
		OutputStream out = new OutputStream(2);
		out.writePacket(player, 69);
		out.writeByte(area);
		session.write(out);
	}

	// instant
	public void sendCameraLook(int viewLocalX, int viewLocalY, int viewZ) {
		sendCameraLook(viewLocalX, viewLocalY, viewZ, -1, -1);
	}

	public void sendCameraLook(int viewLocalX, int viewLocalY, int viewZ, int speed1, int speed2) {
		OutputStream stream = new OutputStream(7);
		stream.writePacket(player, 116);
		stream.writeByte128(viewLocalY);
		stream.writeByte(speed1);
		stream.writeByteC(viewLocalX);
		stream.writeByte(speed2);
		stream.writeShort128(viewZ >> 2);
		session.write(stream);
	}

	public void sendResetCamera() {
		OutputStream stream = new OutputStream(1);
		stream.writePacket(player, 95);
		session.write(stream);
	}

	public void sendCameraRotation(int unknown1, int unknown2) {
		OutputStream stream = new OutputStream(5);
		stream.writePacket(player, 123);
		stream.writeShort(unknown1);
		stream.writeShortLE(unknown1);
		session.write(stream);
	}

	public void sendCameraPos(int moveLocalX, int moveLocalY, int moveZ) {
		sendCameraPos(moveLocalX, moveLocalY, moveZ, -1, -1);
	}

	public void sendClientConsoleCommand(String command) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarByte(player, 61);
		stream.writeString(command);
		stream.endPacketVarByte();
	}

	public void sendOpenURL(String url) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(player, 17);
		stream.writeByte(0);
		stream.writeString(url);
		stream.endPacketVarShort();
		session.write(stream);
	}

	public void sendSetMouse(String walkHereReplace, int cursor) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarByte(player, 10);
		stream.writeString(walkHereReplace);
		stream.writeShort(cursor);
		stream.endPacketVarByte();
		session.write(stream);
	}

	public void sendItemsLook() {
		OutputStream stream = new OutputStream(2);
		stream.writePacket(player, 159);
		stream.writeByte(player.isOldItemsLook() ? 1 : 0);
		session.write(stream);
	}

	public void sendCameraPos(int moveLocalX, int moveLocalY, int moveZ, int speed1, int speed2) {
		OutputStream stream = new OutputStream(7);
		stream.writePacket(player, 74);
		stream.writeByte128(speed2);
		stream.writeByte128(speed1);
		stream.writeByte(moveLocalY);
		stream.writeShort(moveZ >> 2);
		stream.writeByte(moveLocalX);
		session.write(stream);
	}

	public void sendGE(int slot, int progress, int item, int price, int amount, int currentAmount) {
		OutputStream stream = new OutputStream();
		stream.writePacket(player, 68);// packet id
		stream.writeByte(slot);
		stream.writeByte(progress);
		stream.writeShort(item);
		stream.writeInt(price);
		stream.writeInt(amount);
		stream.writeInt(currentAmount);
		stream.writeInt(price * currentAmount);
		session.write(stream);
	}

	public void resetGE(int i) {
		OutputStream stream = new OutputStream();
		stream.writePacket(player, 68);// packet id
		stream.writeByte(i);
		stream.writeByte(0);
		stream.writeShort(0);
		stream.writeInt(0);
		stream.writeInt(0);
		stream.writeInt(0);
		stream.writeInt(0);
		session.write(stream);
	}

	public void sendInterface(boolean clickThrought, int parentUID, int interfaceId) {
		int[] xteas = new int[4];
		OutputStream stream = new OutputStream(24);
		stream.writePacket(player, 14);
		stream.writeShort(interfaceId);
		stream.writeInt(xteas[0]);
		stream.writeIntV2(xteas[1]);
		stream.writeIntV1(parentUID);
		stream.writeByte(clickThrought ? 1 : 0);
		stream.writeIntV1(xteas[3]);
		stream.writeIntV2(xteas[2]);
		session.write(stream);
	}

	public void sendUnlockIComponentOptionSlots(int interfaceId, int componentId, int fromSlot, int toSlot,
			boolean unlockEvent, int... optionsSlots) {
		int settingsHash = unlockEvent ? 1 : 0;
		for (int slot : optionsSlots)
			settingsHash |= 2 << slot;
		sendIComponentSettings(interfaceId, componentId, fromSlot, toSlot, settingsHash);
	}

	public void sendWorldList(boolean full) {
		OutputStream packet = new OutputStream();
		packet.writePacketVarShort(player, 11);
		packet.writeByte(1);// This was 0
		packet.writeByte(2);
		packet.writeByte(full ? 1 : 0);
		int size = WorldList.WORLDS.size();
		if (full) {
			packet.writeSmart(size);
			for (int world = 1; world <= WorldList.WORLDS.size(); world++) {
				packet.writeSmart(WorldList.getWorld(world).getCountryId()); // Flag icon :3
				packet.writeGJString(WorldList.getWorld(world).getCountryName());
			}
			packet.writeSmart(0);
			packet.writeSmart(size + 1);
			packet.writeSmart(size);
			for (int world = 1; world <= WorldList.WORLDS.size(); world++) {
				packet.writeSmart(world); // wid
				packet.writeByte(0); // loc (idx in list) ^ KEEP THIS 0
				int flags = 0;
				if (WorldList.WORLDS.get(world).isMembers())
					flags |= 0x1;
				flags |= 0x8;// loot share
				packet.writeInt(flags); // flag 1-2-4-8 are flags 1=MEMBERS
				// 8=LOOTSHARE BOTH would be (0x1 |
				// 0x8)
				packet.writeGJString(""); // Leave empty for country name, or else
				// location but that removes flag image
				// OR REPLACE BY -
				packet.writeGJString(WorldList.WORLDS.get(world).getIP()); // Endpoint to connect to
			}
			packet.writeInt(-1723296702);
		}
		for (int world = 1; world <= WorldList.WORLDS.size(); world++) {
			packet.writeSmart(world); // wid
			packet.writeShort(5); // PlayerSize
		}

		packet.endPacketVarShort();
		session.write(packet);
	}

	private OutputStream createChunkInSceneStream(WorldTile tile) {
		OutputStream stream = new OutputStream(4);
		stream.writePacket(player, 158);
		stream.writeByte128(tile.getChunkYInScene(player));
		stream.writeByteC(tile.getPlane());
		stream.write128Byte(tile.getChunkXInScene(player));
		return stream;
	}

	public void sendAddObject(WorldObject object) {
		OutputStream stream = createChunkInSceneStream(object);
		stream.writePacket(player, 120);
		stream.writeByte((object.getXInChunk() << 4) | object.getYInChunk());
		stream.writeByte((object.getType() << 2) + (object.getRotation() & 0x3));
		stream.writeIntLE(object.getId());
		session.write(stream);

	}
	
	public void sendSpawnedObject(WorldObject object) {
		OutputStream stream = createWorldTileStream(object);
		int localX = object.getLocalX(player.getLastLoadedMapRegionTile(), player.getMapSize());
		int localY = object.getLocalY(player.getLastLoadedMapRegionTile(), player.getMapSize());
		int offsetX = localX - ((localX >> 3) << 3);
		int offsetY = localY - ((localY >> 3) << 3);
		stream.writePacket(player, 120);
		stream.writeByte((offsetX << 4) | offsetY);
		stream.writeByte((object.getType() << 2) + (object.getRotation() & 0x3));
		stream.writeIntLE(object.getId());
		session.write(stream);

	}

	public void sendRemoveObject(WorldObject object) {
		OutputStream stream = createChunkInSceneStream(object);
		stream.writePacket(player, 45);
		stream.writeByteC((object.getType() << 2) + (object.getRotation() & 0x3));
		stream.writeByte128((object.getXInChunk() << 4) | object.getYInChunk());
		session.write(stream);

	}

	public void sendDungeonneringRequestMessage(Player p) {
		sendMessage(111, "has invited you to a dungeon party.", p);
	}

	public void sendItemMessage(int border, int colour, int id, int x, int y, String message) {
		sendGameMessage(message);
		sendGlobalString(306, message);
		sendGlobalConfig(1699, colour); // "color" - Default; 1 - Black
		sendGlobalConfig(1700, border); // "border" - Default; 0 - White; 1 - Red; 2 - No Border
		sendGlobalConfig(1695, 1);
		sendItemInterface(new Item(id), new WorldTile(x, y, player.getPlane()), true, 746, 0, 1177);
	}

	public void sendItemInterface(Item item, WorldTile tile, boolean noclipped, int windowId, int windowComponentId,
			int interfaceId) {
		int[] xteas = new int[4];
		OutputStream stream = new OutputStream(30);
		stream.writePacket(player, 36);
		stream.writeByte128(noclipped ? 1 : 0);
		stream.writeIntV2(xteas[0]);
		stream.writeIntLE((windowId << 16) | windowComponentId);
		stream.writeIntV1(xteas[1]);
		stream.writeShort(item.getId());
		stream.writeIntV1(xteas[2]);
		stream.writeInt(xteas[3]);
		stream.writeShortLE(interfaceId);
		stream.writeInt((tile.getPlane() << 28) | (tile.getX() << 14) | tile.getY());
		session.write(stream);
	}

}
