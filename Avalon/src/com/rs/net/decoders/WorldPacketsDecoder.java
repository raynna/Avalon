package com.rs.net.decoders;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import com.rs.Settings;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.item.ground.AutomaticGroundItem;
import com.rs.game.minigames.clanwars.ClanWars;
import com.rs.game.minigames.duel.DuelArena;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.familiar.Familiar.SpecialAttack;
import com.rs.game.player.AccountCreation;
import com.rs.game.player.Inventory;
import com.rs.game.player.LogicPacket;
import com.rs.game.player.Player;
import com.rs.game.player.PublicChatMessage;
import com.rs.game.player.QuickChatMessage;
import com.rs.game.player.RouteEvent;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.PlayerFollow;
import com.rs.game.player.actions.combat.AncientMagicks;
import com.rs.game.player.actions.combat.AncientMagicks.RSAncientSpellStore;
import com.rs.game.player.actions.combat.LunarMagicks;
import com.rs.game.player.actions.combat.LunarMagicks.RSLunarSpellStore;
import com.rs.game.player.actions.combat.ModernMagicks;
import com.rs.game.player.actions.combat.ModernMagicks.RSSpellStore;
import com.rs.game.player.actions.combat.PlayerCombat;
import com.rs.game.player.actions.skills.construction.House;
import com.rs.game.player.actions.skills.construction.Sawmill;
import com.rs.game.player.actions.skills.construction.Sawmill.Plank;
import com.rs.game.player.actions.skills.smithing.DungeoneeringSmithing;
import com.rs.game.player.actions.skills.summoning.Summoning;
import com.rs.game.player.content.Commands;
import com.rs.game.player.content.GambleTest;
import com.rs.game.player.content.ReferSystem;
import com.rs.game.player.content.ReportAbuse;
import com.rs.game.player.content.SkillCapeCustomizer;
import com.rs.game.player.content.TicketSystem;
import com.rs.game.player.content.clans.ClansManager;
import com.rs.game.player.content.customtab.GearTab;
import com.rs.game.player.content.customtab.JournalTab;
import com.rs.game.player.content.customtab.SettingsTab;
import com.rs.game.player.content.friendschat.FriendChatsManager;
import com.rs.game.player.content.grandexchange.GrandExchange;
import com.rs.game.player.content.pet.Pets;
import com.rs.game.player.content.randomevent.AntiBot;
import com.rs.game.player.content.unlockables.UnlockableManager;
import com.rs.game.player.controlers.construction.SawmillController;
import com.rs.game.player.dialogues.Report;
import com.rs.game.route.RouteFinder;
import com.rs.game.route.strategy.FixedTileStrategy;
import com.rs.io.InputStream;
import com.rs.io.OutputStream;
import com.rs.net.Session;
import com.rs.net.decoders.handlers.ButtonHandler;
import com.rs.net.decoders.handlers.InventoryOptionsHandler;
import com.rs.net.decoders.handlers.NPCHandler;
import com.rs.net.decoders.handlers.ObjectHandler;
import com.rs.utils.DisplayNames;
import com.rs.utils.Encrypt;
import com.rs.utils.Logger;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;
import com.rs.utils.huffman.Huffman;

public final class WorldPacketsDecoder extends Decoder {

	public static final int EQUIPMENT_REMOVE_PACKET = 216;
	private static final byte[] PACKET_SIZES = new byte[104];

	private final static int WALKING_PACKET = 8;
	private final static int MINI_WALKING_PACKET = 58;
	private final static int AFK_PACKET = 16;
	public final static int ACTION_BUTTON1_PACKET = 14;
	public final static int ACTION_BUTTON2_PACKET = 67;
	public final static int ACTION_BUTTON3_PACKET = 5;
	public final static int ACTION_BUTTON4_PACKET = 55;
	public final static int ACTION_BUTTON5_PACKET = 68;
	public final static int ACTION_BUTTON6_PACKET = 90;
	public final static int ACTION_BUTTON7_PACKET = 6;
	public final static int ACTION_BUTTON8_PACKET = 32;
	public final static int ACTION_BUTTON9_PACKET = 27;
	public final static int WORLD_MAP_CLICK = 38;
	public final static int ACTION_BUTTON10_PACKET = 96;
	public final static int RECEIVE_PACKET_COUNT_PACKET = 33;
	public final static int EQUIPMENT_EXAMINE_PACKET = 3;
	private final static int MOVE_CAMERA_PACKET = 103;
	private final static int INTERFACE_ON_OBJECT = 37;
	private final static int CLICK_PACKET = -1;
	private final static int MOVE_MOUSE_PACKET = -1;
	private final static int KEY_TYPED_PACKET = 1;
	private final static int CLOSE_INTERFACE_PACKET = 54;
	private final static int COMMANDS_PACKET = 60;
	private final static int ITEM_ON_ITEM_PACKET = 3;
	private final static int IN_OUT_SCREEN_PACKET = -1;
	private final static int DONE_LOADING_REGION_PACKET = 30;
	private final static int PING_PACKET = 21;
	private final static int SCREEN_PACKET = 98;
	private final static int CHAT_TYPE_PACKET = 83;
	private final static int CHAT_PACKET = 53;
	private final static int PUBLIC_QUICK_CHAT_PACKET = 86;
	private final static int ADD_FRIEND_PACKET = 89;
	private final static int ADD_IGNORE_PACKET = 4;
	private final static int REMOVE_IGNORE_PACKET = 73;
	private final static int JOIN_FRIEND_CHAT_PACKET = 36;
	private final static int CHANGE_FRIEND_CHAT_PACKET = 22;
	private final static int KICK_FRIEND_CHAT_PACKET = 74;
	private final static int KICK_CLAN_CHAT_PACKET = 92;
	private final static int REMOVE_FRIEND_PACKET = 24;
	private final static int SEND_FRIEND_MESSAGE_PACKET = 82;
	private final static int SEND_FRIEND_QUICK_CHAT_PACKET = 0;
	private final static int OBJECT_CLICK1_PACKET = 26;
	private final static int OBJECT_CLICK2_PACKET = 59;
	private final static int OBJECT_CLICK3_PACKET = 40;
	private final static int OBJECT_CLICK4_PACKET = 23;
	private final static int OBJECT_CLICK5_PACKET = 80;
	private final static int OBJECT_EXAMINE_PACKET = 25;
	private final static int NPC_CLICK1_PACKET = 31;
	private final static int NPC_CLICK2_PACKET = 101;
	private final static int NPC_CLICK3_PACKET = 34;
	private final static int NPC_CLICK4_PACKET = 65;
	private static final int NPC_EXAMINE_PACKET = 9;
	private final static int ATTACK_NPC = 20;
	public static final int PLAYER_OPTION_1_PACKET = 42;
	public static final int PLAYER_OPTION_2_PACKET = 46;
	public static final int PLAYER_OPTION_3_PACKET = 88;
	public static final int PLAYER_OPTION_4_PACKET = 17;
	public static final int PLAYER_OPTION_5_PACKET = 77;
	public static final int PLAYER_OPTION_6_PACKET = 49;
	public static final int PLAYER_OPTION_7_PACKET = 51;
	public static final int PLAYER_OPTION_8_PACKET = 85;
	public static final int PLAYER_OPTION_9_PACKET = 56;
	public static final int PLAYER_OPTION_10_PACKET = 7;
	private final static int INCOMMING_ASSIST = 51;
	private final static int ITEM_TAKE_PACKET = 57;
	private final static int EXAMINE_FLOORITEM_PACKET = 102;
	private final static int DIALOGUE_CONTINUE_PACKET = 72;
	private final static int ENTER_INTEGER_PACKET = 81;
	private final static int ENTER_NAME_PACKET = 29;
	private final static int ENTER_LONG_TEXT_PACKET = 48;
	private final static int ENTER_STRING_PACKET = -1;
	private final static int SWITCH_INTERFACE_ITEM_PACKET = 76;
	private final static int INTERFACE_ON_PLAYER = 50;
	private final static int INTERFACE_ON_NPC = 66;
	private final static int COLOR_ID_PACKET = 97;

	private final static int REPORT_ABUSE_PACKET = 11;
	private final static int GRAND_EXCHANGE_ITEM_SELECT_PACKET = 71;
	private final static int TELEKINETIC_GRAB_SPELL_PACKET = 69;
	private final static int WORLD_LIST_UPDATE = 87;
	private final static int DEVELOPER_PACKET = 162;

	static {
		loadPacketSizes();
	}

	public static String currentTime(String dateFormat) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	public static void archiveMessage(Player player, String message, int chatType) {
		try {
			String location = "";
			location = "data/logs/" + (chatType == 2 ? "clanchat" : chatType == 1 ? "friendchat" : "chat") + "/"
					+ player.getUsername() + ".txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter(location, true));
			writer.write("[" + currentTime("dd MMMMM yyyy 'at' hh:mm:ss z") + "] - " + player.getUsername() + ": "
					+ message);
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadPacketSizes() {
		PACKET_SIZES[0] = -1;
		PACKET_SIZES[1] = -2;
		PACKET_SIZES[2] = -1;
		PACKET_SIZES[3] = 16;
		PACKET_SIZES[4] = -1;
		PACKET_SIZES[5] = 8;
		PACKET_SIZES[6] = 8;
		PACKET_SIZES[7] = 3;
		PACKET_SIZES[8] = -1;
		PACKET_SIZES[9] = 3;
		PACKET_SIZES[10] = -1;
		PACKET_SIZES[11] = -1;
		PACKET_SIZES[12] = -1;
		PACKET_SIZES[13] = 7;
		PACKET_SIZES[14] = 8;
		PACKET_SIZES[15] = 6;
		PACKET_SIZES[16] = 2;
		PACKET_SIZES[17] = 3;
		PACKET_SIZES[18] = -1;
		PACKET_SIZES[19] = -2;
		PACKET_SIZES[20] = 3;
		PACKET_SIZES[21] = 0;
		PACKET_SIZES[22] = -1;
		PACKET_SIZES[23] = 9;
		PACKET_SIZES[24] = -1;
		PACKET_SIZES[25] = 9;
		PACKET_SIZES[26] = 9;
		PACKET_SIZES[27] = 8;
		PACKET_SIZES[28] = 4;
		PACKET_SIZES[29] = -1;
		PACKET_SIZES[30] = 0;
		PACKET_SIZES[31] = 3;
		PACKET_SIZES[32] = 8;
		PACKET_SIZES[33] = 4;//4
		PACKET_SIZES[34] = 3;
		PACKET_SIZES[35] = -1;
		PACKET_SIZES[36] = -1;
		PACKET_SIZES[37] = 17;
		PACKET_SIZES[38] = 4;
		PACKET_SIZES[39] = 4;
		PACKET_SIZES[40] = 9;
		PACKET_SIZES[41] = -1;
		PACKET_SIZES[42] = 3;
		PACKET_SIZES[43] = 7;
		PACKET_SIZES[44] = -2;
		PACKET_SIZES[45] = 7;
		PACKET_SIZES[46] = 3;
		PACKET_SIZES[47] = 4;
		PACKET_SIZES[48] = -1;
		PACKET_SIZES[49] = 3;
		PACKET_SIZES[50] = 11;
		PACKET_SIZES[51] = 3;
		PACKET_SIZES[52] = -1;
		PACKET_SIZES[53] = -1;
		PACKET_SIZES[54] = 0;
		PACKET_SIZES[55] = 8;
		PACKET_SIZES[56] = 3;
		PACKET_SIZES[57] = 7;
		PACKET_SIZES[58] = -1;
		PACKET_SIZES[59] = 9;
		PACKET_SIZES[60] = -1;
		PACKET_SIZES[61] = 7;
		PACKET_SIZES[62] = 7;
		PACKET_SIZES[63] = 12;
		PACKET_SIZES[64] = 4;
		PACKET_SIZES[65] = 3;
		PACKET_SIZES[66] = 11;
		PACKET_SIZES[67] = 8;
		PACKET_SIZES[68] = 8;//8
		PACKET_SIZES[69] = 15;
		PACKET_SIZES[70] = 1;
		PACKET_SIZES[71] = 2;
		PACKET_SIZES[72] = 6;
		PACKET_SIZES[73] = -1;
		PACKET_SIZES[74] = -1;
		PACKET_SIZES[75] = -2;
		PACKET_SIZES[76] = 16;
		PACKET_SIZES[77] = 3;
		PACKET_SIZES[78] = 1;
		PACKET_SIZES[79] = 3;
		PACKET_SIZES[80] = 9;
		PACKET_SIZES[81] = 4;
		PACKET_SIZES[82] = -2;
		PACKET_SIZES[83] = 1;
		PACKET_SIZES[84] = 1;
		PACKET_SIZES[85] = 3;
		PACKET_SIZES[86] = -1;
		PACKET_SIZES[87] = 4;
		PACKET_SIZES[88] = 3;
		PACKET_SIZES[89] = -1;
		PACKET_SIZES[90] = 8;
		PACKET_SIZES[91] = -2;
		PACKET_SIZES[92] = -1;
		PACKET_SIZES[93] = -1;
		PACKET_SIZES[94] = 9;
		PACKET_SIZES[95] = -2;
		PACKET_SIZES[96] = 8;
		PACKET_SIZES[97] = 2;
		PACKET_SIZES[98] = 6;
		PACKET_SIZES[99] = 2;
		PACKET_SIZES[100] = -2;
		PACKET_SIZES[101] = 3;
		PACKET_SIZES[102] = 7;
		PACKET_SIZES[103] = 4;
	}

	private transient Player player;
	private transient Player other;
	private int chatType;

	public WorldPacketsDecoder(Session session, Player player) {
		super(session);
		this.player = player;
	}

	@Override
	public void decode(InputStream stream) {
		while (stream.getRemaining() > 0 && session.getChannel().isConnected() && !player.hasFinished()) {
			int packetId = stream.readPacket(player);
			if (packetId >= PACKET_SIZES.length || packetId < 0) {
				if (Settings.DEBUG)
					System.out.println("PacketId " + packetId + " has fake packet id.");
				break;
			}
			int length = PACKET_SIZES[packetId];
			if (length == -1)
				length = stream.readUnsignedByte();
			else if (length == -2)
				length = stream.readUnsignedShort();
			else if (length == -3)
				length = stream.readInt();
			else if (length == -4) {
				length = stream.getRemaining();
				if (Settings.DEBUG)
					System.out.println("Invalid size for PacketId " + packetId + ". Size guessed to be " + length);
			}
			if (length > stream.getRemaining()) {
				length = stream.getRemaining();
				if (Settings.DEBUG)
					System.out.println("PacketId " + packetId + " has fake size. - expected size " + length);
				// break;

			}
			/*
			 * System.out.println("PacketId " +packetId+ " has . - expected size " +length);
			 */
			int startOffset = stream.getOffset();
			processPackets(packetId, stream, length);
			stream.setOffset(startOffset + length);
			//System.out.println(packetId + " : " + startOffset + " : " + stream.readByte());
		}
	}

	public static void decodeLogicPacket(final Player player, LogicPacket packet) {
		int packetId = packet.getId();
		InputStream stream = new InputStream(packet.getData());
		if (packetId == WALKING_PACKET || packetId == MINI_WALKING_PACKET) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead())
				return;
			if (player.isLocked())
				return;
			if (player.getFreezeDelay() >= Utils.currentTimeMillis()) {
				player.getPackets().sendGameMessage("A magical force prevents you from moving.");
				player.stopAll(true, false, true);
				return;
			}
			if (!player.getControlerManager().canMove(0)) {
				player.stopAll(true, false, true);
				return;
			}
			int length = stream.getLength();
			int baseX = stream.readUnsignedShort128();
			boolean forceRun = stream.readUnsigned128Byte() == 1;
			int baseY = stream.readUnsignedShort128();
			int steps = (length - 5) / 2;
			if (steps > 25)
				steps = 25;
			player.stopAll();
			player.setNextFaceEntity(null);
			if (forceRun)
				player.setRun(forceRun);
			if (steps > 0) {
				int x = 0, y = 0;
				for (int step = 0; step < steps; step++) {
					x = baseX + stream.readUnsignedByte();
					y = baseY + stream.readUnsignedByte();
				}
				steps = RouteFinder.findRoute(RouteFinder.WALK_ROUTEFINDER, player.getX(), player.getY(),
						player.getPlane(), player.getSize(), new FixedTileStrategy(x, y), true);
				int[] bufferX = RouteFinder.getLastPathBufferX();
				int[] bufferY = RouteFinder.getLastPathBufferY();
				int last = -1;
				for (int i = steps - 1; i >= 0; i--) {
					if (!player.addWalkSteps(bufferX[i], bufferY[i], 25, true))
						break;
					last = i;
				}

				if (last != -1) {
					WorldTile tile = new WorldTile(bufferX[last], bufferY[last], player.getPlane());
					player.getPackets().sendMinimapFlag(
							tile.getLocalX(player.getLastLoadedMapRegionTile(), player.getMapSize()),
							tile.getLocalY(player.getLastLoadedMapRegionTile(), player.getMapSize()));
				} else {
					player.getPackets().sendResetMinimapFlag();
				}
				if (player.temporaryAttribute().get("Dreaming") == Boolean.TRUE) {
					player.stopAll(true);
					player.animate(new Animation(6297));
					player.temporaryAttribute().remove("Dreaming");
				}
			}
		} else if (packetId == INTERFACE_ON_OBJECT) {
			boolean forceRun = stream.readByte128() == 1;
			int itemId = stream.readShortLE128();
			int y = stream.readShortLE128();
			int objectId = stream.readIntV2();
			int interfaceHash = stream.readInt();
			final int interfaceId = interfaceHash >> 16;
			int componentId = interfaceHash - (interfaceId << 16);
			int slot = stream.readShortLE();
			int x = stream.readShort128();
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead())
				return;
			long currentTime = Utils.currentTimeMillis();
			if (player.getLockDelay() >= currentTime || player.getEmotesManager().getNextEmoteEnd() >= currentTime)
				return;
			final WorldTile tile = new WorldTile(x, y, player.getPlane());
			int regionId = tile.getRegionId();
			if (!player.getMapRegionsIds().contains(regionId))
				return;
			WorldObject mapObject = World.getObjectWithId(tile, objectId);
			if (mapObject == null || mapObject.getId() != objectId)
				return;
			final WorldObject object = !player.isAtDynamicRegion() ? mapObject
					: new WorldObject(objectId, mapObject.getType(), mapObject.getRotation(), x, y, player.getPlane());
			final Item item = player.getInventory().getItem(slot);
			if (player.isDead() || Utils.getInterfaceDefinitionsSize() <= interfaceId)
				return;
			if (player.getLockDelay() > Utils.currentTimeMillis())
				return;
			if (!player.getInterfaceManager().containsInterface(interfaceId))
				return;
			player.stopAll(false); // false
			if (forceRun)
				player.setRun(forceRun);
			switch (interfaceId) {
			case 430:
				player.setRouteEvent(new RouteEvent(object, new Runnable() {
					@Override
					public void run() {
						player.faceObject(object);
						RSLunarSpellStore s = RSLunarSpellStore.getSpell(componentId);
						if (s != null) {
							if (s.getSpellId() == 44)
								return;
							player.getTemporaryAttributtes().put("spell_objectid", objectId);
							if (!LunarMagicks.hasRequirement(player, componentId)) {
								return;
							}
						}
					}
				}));
				break;
			case 192:
				player.setRouteEvent(new RouteEvent(object, new Runnable() {
					@Override
					public void run() {
						player.faceObject(object);
						RSSpellStore modern = RSSpellStore.getSpell(componentId);
						if (modern != null) {
							player.getTemporaryAttributtes().put("spell_objectid", objectId);
							if (!ModernMagicks.hasRequirement(player, componentId, false, false)) {
								return;
							}
						}
					}
				}));
				break;
			case Inventory.INVENTORY_INTERFACE: // inventory
				if (item == null || item.getId() != itemId)
					return;
				ObjectHandler.handleItemOnObject(player, object, interfaceId, item);
				break;
			}
		} else if (packetId == PLAYER_OPTION_2_PACKET) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead())
				return;
			@SuppressWarnings("unused")
			boolean unknown = stream.readByte() == 1;
			int playerIndex = stream.readUnsignedShortLE128();
			Player p2 = World.getPlayers().get(playerIndex);
			if (p2 == null || p2.isDead() || p2.hasFinished() || !player.getMapRegionsIds().contains(p2.getRegionId()))
				return;
			if (player.getLockDelay() > Utils.currentTimeMillis())
				return;
			if (player.getEquipment().getWeaponId() == 10501) {
				player.faceEntity(p2);
				player.getEquipment().deleteItem(10501, 1);
				player.stopAll(true);
				player.animate(new Animation(7530));
				World.sendFastBowProjectile(player, p2, 1281);
				p2.gfx(new Graphics(862, 100, 0));
				return;
			}
			player.stopAll(false);
			player.getActionManager().setAction(new PlayerFollow(p2));
			// start
		} else if (packetId == PLAYER_OPTION_5_PACKET) {
			@SuppressWarnings("unused")
			boolean unknown = stream.readByte() == 1;
			int playerIndex = stream.readUnsignedShortLE128();
			final Player p2 = World.getPlayers().get(playerIndex);
			player.setRouteEvent(new RouteEvent(p2, new Runnable() {

				@Override
				public void run() {
					if (p2 == null || p2.isDead() || p2.hasFinished()
							|| !player.getMapRegionsIds().contains(p2.getRegionId()))
						return;
					if (player.getLockDelay() > Utils.currentTimeMillis())
						return;
					player.stopAll(false);
					if (player.getPlayerRank().isIronman()) {
						player.sm("You cannot assist as a " + (player.getPlayerRank().isHardcore()
								? "Hardcore Iron " + (player.getAppearence().isMale() ? "Man" : "Woman") + "."
								: "Iron " + (player.getAppearence().isMale() ? "Man" : "Woman") + "."));
						return;
					}
					if (p2.getPlayerRank().isIronman()) {
						player.sm("You cannot assist a "
								+ (p2.getPlayerRank().isHardcore() ? "HC ironman." : "Ironman."));
						return;
					}
					if (player.isCantTrade()) {
						player.getPackets().sendGameMessage("You are busy.");
						return;
					}
					if (p2.getInterfaceManager().containsScreenInter() || p2.isCantTrade()) {
						player.getPackets().sendGameMessage("The other player is busy.");
						return;
					}
					if (p2.temporaryAttribute().get("assist") == player) {
						p2.temporaryAttribute().remove("assist");
						player.getAssist().Assist(p2);
						return;
					}
					player.temporaryAttribute().put("assist", p2);
					player.sm("Currently Disabled.");
					// p2.getPackets().sendAssistMessage(player);
				}
			}));
		} else if (packetId == PLAYER_OPTION_6_PACKET) {
			@SuppressWarnings("unused")
			boolean unknown = stream.readByte() == 1;
			int playerIndex = stream.readUnsignedShortLE128();
			final Player p2 = World.getPlayers().get(playerIndex);
			player.setRouteEvent(new RouteEvent(p2, new Runnable() {
				@Override
				public void run() {
					if (p2 == null || p2.isDead() || p2.hasFinished()
							|| !player.getMapRegionsIds().contains(p2.getRegionId()))
						return;
					if (player.getLockDelay() > Utils.currentTimeMillis())
						return;
					player.sm("Currently out of order.");

				}
			}));
		} else if (packetId == PLAYER_OPTION_4_PACKET) {
			@SuppressWarnings("unused")
			boolean unknown = stream.readByte() == 1;
			int playerIndex = stream.readUnsignedShortLE128();
			final Player p2 = World.getPlayers().get(playerIndex);
			player.setRouteEvent(new RouteEvent(p2, new Runnable() {
				@Override
				public void run() {
					if (p2 == null || p2.isDead() || p2.hasFinished()
							|| !player.getMapRegionsIds().contains(p2.getRegionId()))
						return;
					if (player.getLockDelay() > Utils.currentTimeMillis())
						return;
					player.stopAll(false);
					if (player.getPlayerRank().isIronman()) {
						player.sm("You cannot trade as a " + (player.getPlayerRank().isHardcore()
								? "Hardcore Iron " + (player.getAppearence().isMale() ? "Man" : "Woman") + "."
								: "Iron " + (player.getAppearence().isMale() ? "Man" : "Woman") + "."));
						return;
					}
					if (p2.getPlayerRank().isIronman()) {
						player.sm(
								"You cannot trade a " + (p2.getPlayerRank().isHardcore() ? "HC ironman." : "Ironman."));
						return;
					}
					if (player.isCantTrade()) {
						player.getPackets().sendGameMessage("You are busy.");
						return;
					}
					if (p2.getInterfaceManager().containsScreenInter() || p2.isCantTrade()) {
						player.getPackets().sendGameMessage("The other player is busy.");
						return;
					}
					if (p2.temporaryAttribute().get("TradeTarget") == player) {
						p2.temporaryAttribute().remove("TradeTarget");
						player.getTrade().openTrade(p2);
						p2.getTrade().openTrade(player);
						return;
					}
					player.temporaryAttribute().put("TradeTarget", p2);
					player.getPackets().sendGameMessage("Sending trade offer...");
					p2.getPackets().sendTradeRequestMessage(player);
				}
			}));
		} else if (packetId == PLAYER_OPTION_1_PACKET) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead())
				return;
			@SuppressWarnings("unused")
			boolean unknown = stream.readByte() == 1;
			int playerIndex = stream.readUnsignedShortLE128();
			final Player p2 = World.getPlayers().get(playerIndex);
			if (p2 == null || p2.isDead() || p2.hasFinished() || !player.getMapRegionsIds().contains(p2.getRegionId()))
				return;
			if (player.getLockDelay() > Utils.currentTimeMillis())
				return;
			if (player.getTemporaryAttributtes().get("DUNGEON_INVITE_RECIEVED") != null) {
				Player inviteBy = (Player) player.getTemporaryAttributtes().get("DUNGEON_INVITE_RECIEVED");
				if (inviteBy != null)
					player.getDungManager().acceptInvite(inviteBy.getDisplayName());
				else
					player.sm("inviteBy is null");
				return;
			}
			if (!player.getControlerManager().canPlayerOption1(p2))
				return;
			if (!player.isCanPvp())
				return;
			if (!player.getControlerManager().canAttack(p2))
				return;
			if (!player.isCanPvp() || !p2.isCanPvp()) {
				player.getPackets().sendGameMessage("You can only attack players in a player-vs-player area.");
				return;
			}
			if (player.isAtMultiArea() && !p2.isAtMultiArea()) {
				if (p2.getAttackedBy() != player && p2.getAttackedByDelay() > Utils.currentTimeMillis()) {
					player.getPackets().sendGameMessage("That player is already in combat.");
					return;
				}
			}
			if (!p2.isAtMultiArea() && !player.isAtMultiArea()) {
				if (player.getAttackedBy() != p2 && player.getAttackedByDelay() > Utils.currentTimeMillis()) {
					player.getPackets().sendGameMessage("You are already in combat.");
					return;
				}
				if (p2.getAttackedBy() != player && p2.getAttackedByDelay() > Utils.currentTimeMillis()) {
					if (p2.getAttackedBy() instanceof NPC) {
						p2.setAttackedBy(player);
					} else {
						player.getPackets().sendGameMessage("That player is already in combat.");
						return;
					}
				}
			}
			player.stopAll(false);
			player.getActionManager().setAction(new PlayerCombat(p2));
		} else if (packetId == PLAYER_OPTION_9_PACKET) {
			boolean forceRun = stream.readUnsignedByte() == 1;
			int playerIndex = stream.readUnsignedShortLE128();
			Player p2 = World.getPlayers().get(playerIndex);
			if (p2 == null || p2 == player || p2.isDead() || p2.hasFinished()
					|| !player.getMapRegionsIds().contains(p2.getRegionId()))
				return;
			if (player.isLocked())
				return;
			if (forceRun)
				player.setRun(forceRun);
			player.stopAll();
			if (player.getTemporaryAttributtes().remove("claninvite") != null) {
				ClansManager.viewInvite(player, p2);
				return;
			}
		} else if (packetId == ATTACK_NPC) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead())
				return;
			if (player.getLockDelay() > Utils.currentTimeMillis())
				return;
			int npcIndex = stream.readUnsignedShort128();
			boolean forceRun = stream.read128Byte() == 1;
			if (forceRun)
				player.setRun(forceRun);
			NPC npc = World.getNPCs().get(npcIndex);
			if (npc == null || npc.isDead() || npc.hasFinished()
					|| !player.getMapRegionsIds().contains(npc.getRegionId())
					|| !npc.getDefinitions().hasAttackOption()) {
				return;
			}
			if (!player.getControlerManager().canAttack(npc)) {
				return;
			}
			if (npc instanceof Familiar) {
				Familiar familiar = (Familiar) npc;
				if (familiar == player.getFamiliar()) {
					player.getPackets().sendGameMessage("You can't attack your own familiar.");
					return;
				}
				if (!familiar.canAttack(player)) {
					player.getPackets().sendGameMessage("You can't attack this npc.");
					return;
				}
			} else if (!npc.isForceMultiAttacked()) {
				if (player.isAtMultiArea() && !npc.isAtMultiArea()) {
					if (npc.getAttackedBy() != player && npc.getAttackedByDelay() > Utils.currentTimeMillis()) {
						player.getPackets().sendGameMessage("This npc is already in combat.");
						return;
					}
				}
				if (!npc.isAtMultiArea() && !player.isAtMultiArea()) {
					if (player.getAttackedBy() != npc && player.getAttackedByDelay() > Utils.currentTimeMillis()) {
						player.getPackets().sendGameMessage("You are already in combat.");
						return;
					}
					if (npc.getAttackedBy() != player && npc.getAttackedByDelay() > Utils.currentTimeMillis()) {
						player.getPackets().sendGameMessage("This npc is already in combat.");
						return;
					}
				}
			}
			player.stopAll(false);
			player.getActionManager().setAction(new PlayerCombat(npc));
		} else if (packetId == TELEKINETIC_GRAB_SPELL_PACKET) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead() || player.isLocked())
				return;
			long currentTime = Utils.currentTimeMillis();
			if (player.getLockDelay() > currentTime)
				// || player.getFreezeDelay() >= currentTime)
				return;
			int xCoord = stream.readShort();
			int yCoord = stream.readShort();
			@SuppressWarnings("unused")
			int unknown128 = stream.readShortLE128();
			@SuppressWarnings("unused")
			int unknownv2 = stream.readIntV2();
			@SuppressWarnings("unused")
			int unknownLE = stream.readShortLE();
			@SuppressWarnings("unused")
			boolean forceRun = stream.readByte() == 1 ? true : false;
			int itemId = stream.readShortLE();
			final WorldTile tile = new WorldTile(xCoord, yCoord, player.getPlane());
			final int regionId = tile.getRegionId();
			if (!player.getMapRegionsIds().contains(regionId))
				return;
			final FloorItem item = World.getRegion(regionId).getGroundItem(itemId, tile, player);
			if (item == null)
				return;
			player.stopAll(false);
			if (player.getSkills().getLevel(Skills.MAGIC) < 33) {
				player.getPackets().sendGameMessage("You do not have the required level to cast this spell.");
				return;
			}
			if (player.getEquipment().getWeaponId() == 1381 || player.getEquipment().getWeaponId() == 1397
					|| player.getEquipment().getWeaponId() == 1405) {
				if (!player.getInventory().containsItem(563, 1)) {
					player.getPackets().sendGameMessage("You do not have the required runes to cast this spell.");
					return;
				}
				player.setNextFaceWorldTile(tile);
				player.animate(new Animation(711));
				player.getSkills().addXp(Skills.MAGIC, 10);
				player.getInventory().deleteItem(563, 1);
				World.sendProjectile(player, new WorldTile(xCoord, yCoord, player.getPlane()), 142, 18, 5, 20, 50, 0,
						0);
				CoresManager.slowExecutor.schedule(new Runnable() {
					@Override
					public void run() {
						World.sendGraphics(player, new Graphics(144), tile);
						if (World.getRegion(regionId).getGroundItem(itemId, tile, player) == null) {
							player.getPackets().sendGameMessage("Oops! - To late!");
							return;
						}
						if (!player.getInventory().hasFreeSlots()) {
							player.getPackets().sendGameMessage("You don't have enough inventory space.");
							return;
						}
						player.getInventory().addItem(item.getId(), item.getAmount());
						World.removeGroundItem(player, item);
					}
				}, 2, TimeUnit.SECONDS);
			} else {
				if (!player.getInventory().containsItem(563, 1) || !player.getInventory().containsItem(556, 1)) {
					player.getPackets().sendGameMessage("You do not have the required runes to cast this spell.");
					return;
				}
				player.setNextFaceWorldTile(tile);
				player.animate(new Animation(711));
				player.getSkills().addXp(Skills.MAGIC, 10);
				player.getInventory().deleteItem(563, 1);
				World.sendProjectile(player, new WorldTile(xCoord, yCoord, player.getPlane()), 142, 18, 5, 20, 50, 0,
						0);
				CoresManager.slowExecutor.schedule(new Runnable() {
					@Override
					public void run() {
						World.sendGraphics(player, new Graphics(144), tile);
						if (World.getRegion(regionId).getGroundItem(itemId, tile, player) == null) {
							player.getPackets().sendGameMessage("Oops! - To late!");
							return;
						}
						if (!player.getInventory().hasFreeSlots()) {
							player.getPackets().sendGameMessage("You don't have enough inventory space.");
							return;
						}
						player.getInventory().addItem(item.getId(), item.getAmount());
						World.removeGroundItem(player, item);
					}
				}, 2, TimeUnit.SECONDS);
			}
		} else if (packetId == INTERFACE_ON_PLAYER) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead())
				return;
			if (player.getLockDelay() > Utils.currentTimeMillis())
				return;
			@SuppressWarnings("unused")
			int junk1 = stream.readUnsignedShort();
			int playerIndex = stream.readUnsignedShort();
			int interfaceHash = stream.readIntV2();
			@SuppressWarnings("unused")
			int junk2 = stream.readUnsignedShortLE128();
			@SuppressWarnings("unused")
			boolean unknown = stream.read128Byte() == 1;
			int interfaceId = interfaceHash >> 16;
			int componentId = interfaceHash - (interfaceId << 16);
			if (Utils.getInterfaceDefinitionsSize() <= interfaceId)
				return;
			if (!player.getInterfaceManager().containsInterface(interfaceId))
				return;
			if (componentId == 65535)
				componentId = -1;
			if (componentId != -1 && Utils.getInterfaceDefinitionsComponentsSize(interfaceId) <= componentId)
				return;
			final Player p2 = World.getPlayers().get(playerIndex);
			if (p2 == null || p2.isDead() || p2.hasFinished() || !player.getMapRegionsIds().contains(p2.getRegionId()))
				return;
			switch (interfaceId) {
			case 1110:
				if (componentId == 87)
					player.setRouteEvent(new RouteEvent(p2, new Runnable() {
						@Override
						public void run() {
							ClansManager.invite(player, p2);
						}
					}));
				break;
			case 662:
			case 747:
				if (player.getFamiliar() == null)
					return;
				if ((interfaceId == 747 && componentId == 15) || (interfaceId == 662 && componentId == 65)
						|| (interfaceId == 662 && componentId == 74) || interfaceId == 747 && componentId == 18) {
					if ((interfaceId == 662 && componentId == 74 || interfaceId == 747 && componentId == 24
							|| interfaceId == 747 && componentId == 18)) {
						// if (player.getFamiliar().getSpecialAttack() !=
						// SpecialAttack.ENTITY)
						// return;
					}
					if (!player.isCanPvp() || !p2.isCanPvp()) {
						player.getPackets().sendGameMessage("You can only attack players in a player-vs-player area.");
						return;
					}
					if (!player.getFamiliar().canAttack(p2)) {
						player.getPackets().sendGameMessage("You can only use your familiar in a multi-zone area.");
						return;
					} else {
						player.getFamiliar().setTarget(p2);
					}
				}
				break;
			case 430:
				RSLunarSpellStore lunar = RSLunarSpellStore.getSpell(componentId);
				if (lunar != null) {
					player.getTemporaryAttributtes().put("spell_target", p2);
					if (lunar.getSpellType() == LunarMagicks.NPC) {
						player.getPackets().sendGameMessage("You can only cast this spell on a npcs.");
						return;
					}
					if (!LunarMagicks.hasRequirement(player, componentId)) {
						player.getPackets().sendGameMessage("Nothing interesting happens.");
						return;
					}
				}
				break;
			case 193:
				RSAncientSpellStore s = RSAncientSpellStore.getSpell(componentId);
				if (s == null)
					return;
				if (s != null) {
					player.setNextFaceWorldTile(new WorldTile(p2.getCoordFaceX(p2.getSize()),
							p2.getCoordFaceY(p2.getSize()), p2.getPlane()));
					if (s.isCombat()) {
						if (!player.getControlerManager().canAttack(p2))
							return;
						if (!player.isCanPvp() || !p2.isCanPvp()) {
							player.getPackets()
									.sendGameMessage("You can only attack players in a player-vs-player area.");
							return;
						}
						if (player.isAtMultiArea() && !p2.isAtMultiArea()) {
							if (p2.getAttackedBy() != player && p2.getAttackedByDelay() > Utils.currentTimeMillis()) {
								player.getPackets().sendGameMessage("That player is already in combat.");
								return;
							}
						}
						if (!p2.isAtMultiArea() && !player.isAtMultiArea()) {
							if (player.getAttackedBy() != p2
									&& player.getAttackedByDelay() > Utils.currentTimeMillis()) {
								player.getPackets().sendGameMessage("You are already in combat.");
								return;
							}
							if (p2.getAttackedBy() != player && p2.getAttackedByDelay() > Utils.currentTimeMillis()) {
								if (p2.getAttackedBy() instanceof NPC)
									p2.setAttackedBy(player);
								else {
									player.getPackets().sendGameMessage("That player is already in combat.");
									return;
								}
							}
						}
						if (AncientMagicks.hasRequirement(player, componentId, true, false)
								&& AncientMagicks.checkRunes(player, componentId, false)) {
							player.getActionManager().setAction(new PlayerCombat(p2));
							return;
						}
					} else {
						AncientMagicks.hasRequirement(player, componentId, false, false);
						return;
					}
				}
				break;
			case 192:
				RSSpellStore s2 = RSSpellStore.getSpell(componentId);
				if (s2 == null)
					return;
				if (s2 != null) {
					player.setNextFaceWorldTile(new WorldTile(p2.getCoordFaceX(p2.getSize()),
							p2.getCoordFaceY(p2.getSize()), p2.getPlane()));
					if (s2.getSpellType() == ModernMagicks.COMBAT) {
						if (!player.getControlerManager().canAttack(p2))
							return;
						if (!player.isCanPvp() || !p2.isCanPvp()) {
							player.getPackets()
									.sendGameMessage("You can only attack players in a player-vs-player area.");
							return;
						}
						if (player.isAtMultiArea() && !p2.isAtMultiArea()) {
							if (p2.getAttackedBy() != player && p2.getAttackedByDelay() > Utils.currentTimeMillis()) {
								player.getPackets().sendGameMessage("That player is already in combat.");
								return;
							}
						}
						if (!p2.isAtMultiArea() && !player.isAtMultiArea()) {
							if (player.getAttackedBy() != p2
									&& player.getAttackedByDelay() > Utils.currentTimeMillis()) {
								player.getPackets().sendGameMessage("You are already in combat.");
								return;
							}
							if (p2.getAttackedBy() != player && p2.getAttackedByDelay() > Utils.currentTimeMillis()) {
								if (p2.getAttackedBy() instanceof NPC)
									p2.setAttackedBy(player);
								else {
									player.getPackets().sendGameMessage("That player is already in combat.");
									return;
								}
							}
						}
						if (ModernMagicks.hasRequirement(player, componentId, true, false)
								&& ModernMagicks.checkRunes(player, componentId, false)) {
							player.getActionManager().setAction(new PlayerCombat(p2));
							return;
						}
					} else {
						player.getTemporaryAttributtes().put("spell_target", p2);
						ModernMagicks.hasRequirement(player, componentId, false, false);
						return;
					}
				}
				break;
			}
			if (!Settings.HOSTED)
				player.getPackets().sendFilteredGameMessage(true, "Spell:" + componentId);
		} else if (packetId == INTERFACE_ON_NPC) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead())
				return;
			if (player.getLockDelay() > Utils.currentTimeMillis())
				return;
			@SuppressWarnings("unused")
			boolean unknown = stream.readByte() == 1;
			int interfaceHash = stream.readInt();
			int npcIndex = stream.readUnsignedShortLE();
			int interfaceSlot = stream.readUnsignedShortLE128();
			@SuppressWarnings("unused")
			int junk2 = stream.readUnsignedShortLE();
			int interfaceId = interfaceHash >> 16;
			int componentId = interfaceHash - (interfaceId << 16);
			if (Utils.getInterfaceDefinitionsSize() <= interfaceId)
				return;
			if (!player.getInterfaceManager().containsInterface(interfaceId))
				return;
			if (componentId == 65535)
				componentId = -1;
			if (componentId != -1 && Utils.getInterfaceDefinitionsComponentsSize(interfaceId) <= componentId)
				return;
			NPC npc = World.getNPCs().get(npcIndex);
			if (npc == null || npc.isDead() || npc.hasFinished()
					|| !player.getMapRegionsIds().contains(npc.getRegionId()))
				return;
			player.stopAll(false);
			switch (interfaceId) {
			case Inventory.INVENTORY_INTERFACE:
				Item item = player.getInventory().getItem(interfaceSlot);
				if (item == null)
					return;
				InventoryOptionsHandler.handleItemOnNPC(player, npc, item, interfaceSlot);
				break;
			case 662:
			case 747:
				if (player.getFamiliar() == null)
					return;
				player.resetWalkSteps();
				if ((interfaceId == 747 && componentId == 15) || (interfaceId == 662 && componentId == 65)
						|| (interfaceId == 662 && componentId == 74) || interfaceId == 747 && componentId == 18
						|| interfaceId == 747 && componentId == 24) {
					if ((interfaceId == 662 && componentId == 74 || interfaceId == 747 && componentId == 18)) {
						if (player.getFamiliar().getSpecialAttack() != SpecialAttack.ENTITY) {
							return;
						}
					}
					if (npc instanceof Familiar) {
						Familiar familiar = (Familiar) npc;
						if (familiar == player.getFamiliar()) {
							player.getPackets().sendGameMessage("You can't attack your own familiar.");
							return;
						}
						if (!player.getFamiliar().canAttack(familiar.getOwner())) {
							player.getPackets()
									.sendGameMessage("You can only attack players in a player-vs-player area.");
							return;
						}
					}
					if (!player.getFamiliar().canAttack(npc)) {
						player.getPackets().sendGameMessage("You can only use your familiar in a multi-zone area.");
						return;
					} else {
						player.getFamiliar().setTarget(npc);
					}
				}
				break;
			case 193:
				player.faceEntity(npc);
				if (!npc.getDefinitions().hasAttackOption() && !(npc instanceof Familiar))
					return;
				RSAncientSpellStore s = RSAncientSpellStore.getSpell(componentId);
				if (s == null)
					return;
				if (s != null) {
					if (s.isCombat()) {
						player.setNextFaceWorldTile(new WorldTile(npc.getCoordFaceX(npc.getSize()),
								npc.getCoordFaceY(npc.getSize()), npc.getPlane()));
						if (npc.getId() == 23921) {
							player.getPackets().sendGameMessage("You can't use magic on a dummy.");
							return;
						}
						if (npc instanceof Familiar) {
							Familiar familiar = (Familiar) npc;
							player.getPackets().sendGameMessage(familiar.getOwner().getDisplayName());
							if (familiar == player.getFamiliar()) {
								player.getPackets().sendGameMessage("You can't attack your own familiar.");
								return;
							}
							if (!familiar.canAttack(player)) {
								player.getPackets().sendGameMessage("You can't attack this npc.");
								return;
							}
							if (AncientMagicks.hasRequirement(player, componentId, true, false)
									&& AncientMagicks.checkRunes(player, componentId, false)) {
								if (!player.getControlerManager().canAttack(npc))
									return;
								player.getActionManager().setAction(new PlayerCombat(familiar.getOwner()));
								return;
							}
						} else if (!npc.isForceMultiAttacked()) {
							if (player.isAtMultiArea() && !npc.isAtMultiArea()) {
								if (npc.getAttackedBy() != player
										&& npc.getAttackedByDelay() > Utils.currentTimeMillis()) {
									player.getPackets().sendGameMessage("This npc is already in combat.");
									return;
								}
							}
							if (!npc.isAtMultiArea() && !player.isAtMultiArea()) {
								if (player.getAttackedBy() != npc
										&& player.getAttackedByDelay() > Utils.currentTimeMillis()) {
									player.getPackets().sendGameMessage("You are already in combat.");
									return;
								}
								if (npc.getAttackedBy() != player
										&& npc.getAttackedByDelay() > Utils.currentTimeMillis()) {
									player.getPackets().sendGameMessage("This npc is already in combat.");
									return;
								}
							}
						}
						if (AncientMagicks.hasRequirement(player, componentId, true, false)
								&& AncientMagicks.checkRunes(player, componentId, false)) {
							player.getActionManager().setAction(new PlayerCombat(npc));
							return;
						}
					} else {
						AncientMagicks.hasRequirement(player, componentId, false, false);
						return;
					}
				}
				break;
			case 430:
				RSLunarSpellStore lunar = RSLunarSpellStore.getSpell(componentId);
				if (lunar != null) {
					player.getTemporaryAttributtes().put("spell_target", npc);
					if (lunar.getSpellType() == LunarMagicks.PLAYER) {
						player.getPackets().sendGameMessage("You can only cast this spell on players.");
						return;
					}
					if (!LunarMagicks.hasRequirement(player, componentId)) {
						player.getPackets().sendGameMessage("Nothing interesting happens.");
						return;
					}
				}
				break;
			case 192:
				if (!npc.getDefinitions().hasAttackOption())
					return;
				RSSpellStore s2 = RSSpellStore.getSpell(componentId);
				if (s2 == null)
					return;
				if (s2 != null) {
					if (s2.getSpellType() == ModernMagicks.COMBAT) {
						player.setNextFaceWorldTile(new WorldTile(npc.getCoordFaceX(npc.getSize()),
								npc.getCoordFaceY(npc.getSize()), npc.getPlane()));
						if (!player.getControlerManager().canAttack(npc))
							return;
						if (npc.getId() == 23921) {
							player.getPackets().sendGameMessage("You can't use magic on a dummy.");
							return;
						}
						if (npc instanceof Familiar) {
							Familiar familiar = (Familiar) npc;
							if (familiar == player.getFamiliar()) {
								player.getPackets().sendGameMessage("You can't attack your own familiar.");
								return;
							}
							if (!familiar.canAttack(player)) {
								player.getPackets().sendGameMessage("You can't attack this npc.");
								return;
							}
						} else if (!npc.isForceMultiAttacked()) {
							if (player.isAtMultiArea() && !npc.isAtMultiArea()) {
								if (npc.getAttackedBy() != player
										&& npc.getAttackedByDelay() > Utils.currentTimeMillis()) {
									player.getPackets().sendGameMessage("This npc is already in combat.");
									return;
								}
							}
							if (!npc.isAtMultiArea() && !player.isAtMultiArea()) {
								if (player.getAttackedBy() != npc
										&& player.getAttackedByDelay() > Utils.currentTimeMillis()) {
									player.getPackets().sendGameMessage("You are already in combat.");
									return;
								}
								if (npc.getAttackedBy() != player
										&& npc.getAttackedByDelay() > Utils.currentTimeMillis()) {
									player.getPackets().sendGameMessage("This npc is already in combat.");
									return;
								}
							}
						}
						if (ModernMagicks.hasRequirement(player, componentId, true, false)
								&& ModernMagicks.checkRunes(player, componentId, false)) {
							player.getActionManager().setAction(new PlayerCombat(npc));
							return;
						}
					}
				}
				break;
			}
			if (!Settings.HOSTED)
				player.getPackets().sendFilteredGameMessage(true, "Spell: " + componentId);
		} else if (packetId == NPC_CLICK1_PACKET)
			NPCHandler.handleOption1(player, stream);
		else if (packetId == NPC_CLICK2_PACKET)
			NPCHandler.handleOption2(player, stream);
		else if (packetId == NPC_CLICK3_PACKET)
			NPCHandler.handleOption3(player, stream);
		else if (packetId == NPC_CLICK4_PACKET)
			NPCHandler.handleOption4(player, stream);
		else if (packetId == OBJECT_CLICK1_PACKET)
			ObjectHandler.handleOption(player, stream, 1);
		else if (packetId == OBJECT_CLICK2_PACKET)
			ObjectHandler.handleOption(player, stream, 2);
		else if (packetId == OBJECT_CLICK3_PACKET)
			ObjectHandler.handleOption(player, stream, 3);
		else if (packetId == OBJECT_CLICK4_PACKET)
			ObjectHandler.handleOption(player, stream, 4);
		else if (packetId == OBJECT_CLICK5_PACKET)
			ObjectHandler.handleOption(player, stream, 5);
		else if (packetId == ITEM_TAKE_PACKET) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead())
				return;
			final long currentTime = Utils.currentTimeMillis();
			if (player.getLockDelay() > currentTime)
				return;
			int y = stream.readUnsignedShort();
			int x = stream.readUnsignedShortLE();
			final int id = stream.readUnsignedShort();
			boolean forceRun = stream.read128Byte() == 1;
			final WorldTile tile = new WorldTile(x, y, player.getPlane());
			final int regionId = tile.getRegionId();
			if (!player.getMapRegionsIds().contains(regionId))
				return;
			final FloorItem item = World.getRegion(regionId).getGroundItem(id, tile, player);
			if (item == null)
				return;
			player.stopAll(false);
			if (forceRun)
				player.setRun(forceRun);
			player.setRouteEvent(new RouteEvent(item, new Runnable() {
				@Override
				public void run() {
					final FloorItem item = World.getRegion(regionId).getGroundItem(id, tile, player);
					if (item == null || !player.getControlerManager().canTakeItem(item))
						return;
					/*
					 * if (item.isSpawned()) { WorldTasksManager.schedule(new WorldTask() {
					 * 
					 * @Override public void run() { World.addGlobalGroundItem(item, item.getTile(),
					 * item.getTick(), item.isSpawned()); this.stop(); } }, item.getTick()); }
					 */
					if (item.getOwn() != player) {
						if (player.getPlayerRank().isIronman()) {
							player.sm("You are not able to pick other players' items.");
							return;
						}
					}
					player.setNextFaceWorldTile(tile);
					if (player.getFreezeDelay() <= currentTime)
						player.addWalkSteps(tile.getX(), tile.getY(), 1);
					AutomaticGroundItem.pickup(tile, item);
					World.removeGroundItem(player, item);
				}
			}));
		} else if (packetId == EXAMINE_FLOORITEM_PACKET) {
			FloorItem.handleExamine(player, stream);
		}
	}

	public void processPackets(final int packetId, InputStream stream, int length) {
		player.setPacketsDecoderPing(Utils.currentTimeMillis());
		if (packetId == PING_PACKET) {
			OutputStream packet = new OutputStream(0);
			packet.writePacket(player, 153);
			player.getSession().write(packet);
		} else if (packetId == MOVE_MOUSE_PACKET) {
			// USELESS PACKET
		} else if (packetId == DEVELOPER_PACKET) {
				System.out.println("Developer Packet: " + packetId);
				System.out.println("Value" + stream.readByte());
		} else if (packetId == RECEIVE_PACKET_COUNT_PACKET) {
			stream.readInt();
		} else if (packetId == WORLD_LIST_UPDATE) {
			int updateType = stream.readInt();
			player.getPackets().sendWorldList(updateType == 0);
		} else if (packetId == ITEM_ON_ITEM_PACKET) {
			InventoryOptionsHandler.handleItemOnItem(player, stream);
		} else if (packetId == EQUIPMENT_EXAMINE_PACKET) {
			InventoryOptionsHandler.handleMagicOnItem(player, stream);
		} else if (packetId == AFK_PACKET) {

		} else if (packetId == CLOSE_INTERFACE_PACKET) {
			if (player.hasStarted() && !player.hasFinished() && !player.isActive()) {
				player.run();
				return;
			}
			player.closeInterfaces();
		} else if (packetId == MOVE_CAMERA_PACKET) {
			// not using it atm
			stream.readUnsignedShort();
			stream.readUnsignedShort();
		} else if (packetId == IN_OUT_SCREEN_PACKET) {
			// not using this check because not 100% efficient
			@SuppressWarnings("unused")
			boolean inScreen = stream.readByte() == 1;
		} else if (packetId == SCREEN_PACKET) {
			int displayMode = stream.readUnsignedByte();
			player.setScreenWidth(stream.readUnsignedShort());
			player.setScreenHeight(stream.readUnsignedShort());
			@SuppressWarnings("unused")
			boolean switchScreenMode = stream.readUnsignedByte() == 1;
			if (!player.hasStarted() || player.hasFinished() || displayMode == player.getDisplayMode()
					|| !player.getInterfaceManager().containsInterface(742))
				return;
			player.setDisplayMode(displayMode);
			player.getInterfaceManager().removeAll();
			player.getInterfaceManager().sendInterfaces();
			player.getInterfaceManager().sendInterface(742);
		} else if (packetId == INCOMMING_ASSIST) {
			@SuppressWarnings("unused")
			boolean unknown = stream.readByte() == 1;
			int playerIndex = stream.readUnsignedShortLE128();
			final Player p2 = World.getPlayers().get(playerIndex);
			player.setRouteEvent(new RouteEvent(p2, new Runnable() {
				@Override
				public void run() {
					if (p2 == null || p2.isDead() || p2.hasFinished()
							|| !player.getMapRegionsIds().contains(p2.getRegionId()))
						return;
					if (player.getLockDelay() > Utils.currentTimeMillis())
						return;
					player.stopAll(false);
					if (player.getPlayerRank().isIronman()) {
						player.sm("You cannot assist as a "
								+ (player.getPlayerRank().isHardcore() ? "HC ironman." : "Ironman."));
						return;
					}
					if (p2.getPlayerRank().isIronman()) {
						player.sm("You cannot assist a "
								+ (p2.getPlayerRank().isHardcore() ? "HC ironman." : "Ironman."));
						return;
					}
					if (p2.getInterfaceManager().containsScreenInter()) {
						player.getPackets().sendGameMessage("The other player is busy.");
						return;
					}
					if (p2.temporaryAttribute().get("assist") == player) {
						p2.temporaryAttribute().remove("assist");
						player.getAssist().Assist(p2);
						return;
					}
				}
			}));
		} else if (packetId == CLICK_PACKET) {
			int mouseHash = stream.readShortLE128();
			int mouseButton = mouseHash >> 15;
			int time = mouseHash - (mouseButton << 15);
			int positionHash = stream.readIntV1();
			int y = positionHash >> 16; // y;
			int x = positionHash - (y << 16); // x
			@SuppressWarnings("unused")
			boolean clicked;
			if (time <= 1 || x < 0 || x > player.getScreenWidth() || y < 0 || y > player.getScreenHeight()) {
				clicked = false;
				return;
			}
			clicked = true;
		} else if (packetId == DIALOGUE_CONTINUE_PACKET) {
			int interfaceHash = stream.readInt();
			int junk = stream.readShort128();
			int interfaceId = interfaceHash >> 16;
			int buttonId = (interfaceHash & 0xFF);
			if (Utils.getInterfaceDefinitionsSize() <= interfaceId)
				return;
			if (!player.isActive() || !player.getInterfaceManager().containsInterface(interfaceId))
				return;
			if (Settings.DEBUG)
				Logger.log(this, "Dialogue: " + interfaceId + ", " + buttonId + ", " + junk);
			int componentId = interfaceHash - (interfaceId << 16);
			player.getDialogueManager().continueDialogue(interfaceId, componentId);
		} else if (packetId == WORLD_MAP_CLICK) {
			int coordinateHash = stream.readInt();
			int x = coordinateHash >> 14;
			int y = coordinateHash & 0x3fff;
			int plane = coordinateHash >> 28;
			Integer hash = (Integer) player.temporaryAttribute().get("worldHash");
			if (hash == null || coordinateHash != hash)
				player.temporaryAttribute().put("worldHash", coordinateHash);
			else {
				player.temporaryAttribute().remove("worldHash");
				player.getHintIconsManager().addHintIcon(x, y, plane, 20, 0, 2, -1, true);
				player.getPackets().sendConfig(1159, coordinateHash);
			}
		} else if (packetId == ACTION_BUTTON1_PACKET || packetId == ACTION_BUTTON2_PACKET
				|| packetId == ACTION_BUTTON4_PACKET || packetId == ACTION_BUTTON5_PACKET
				|| packetId == ACTION_BUTTON6_PACKET || packetId == ACTION_BUTTON7_PACKET
				|| packetId == ACTION_BUTTON8_PACKET || packetId == ACTION_BUTTON3_PACKET
				|| packetId == ACTION_BUTTON9_PACKET || packetId == ACTION_BUTTON10_PACKET
				|| packetId == INCOMMING_ASSIST) {
			ButtonHandler.handleButtons(player, stream, packetId);
		} else if (packetId == ENTER_NAME_PACKET) {
			if (!player.isActive() || player.isDead())
				return;
			String value = stream.readString();
			if (value.equals(""))
				return;
			if (player.temporaryAttribute().get("PUNISH_NAME") == Boolean.TRUE) {
				if (World.getPlayer(value) == null) {
					value = Utils.formatPlayerNameForProtocol(value);
					if (!AccountCreation.exists(value)) {
						player.getPackets()
								.sendGameMessage("No such account named " + value + " was found in the database.");
					} else {
						// Player p =
						// SerializableFilesManager.loadPlayer(value);
						player.getDialogueManager().startDialogue("Punish", value, false);
					}
					player.temporaryAttribute().put("PUNISH_NAME", Boolean.FALSE);
					return;
				}
				Player target = World.getPlayerByDisplayName(value);
				try {
					player.getDialogueManager().startDialogue("Punish", target, true);
				} catch (Exception e) {
					player.getPackets().sendGameMessage(Utils.formatPlayerNameForDisplay(value) + " wasn't found.");
				}

				player.temporaryAttribute().put("PUNISH_NAME", Boolean.FALSE);
				return;
			}
			if (player.getInterfaceManager().containsInterface(1108))
				player.getFriendsIgnores().setChatPrefix(value);
			else if (player.temporaryAttribute().remove("setclan") != null)
				ClansManager.createClan(player, value);
			else if (player.temporaryAttribute().remove("joinguestclan") != null)
				ClansManager.connectToClan(player, value, true);
			else if (player.temporaryAttribute().remove("banclanplayer") != null)
				ClansManager.banPlayer(player, value);
			else if (player.temporaryAttribute().remove("unbanclanplayer") != null)
				ClansManager.unbanPlayer(player, value);
			else if (player.getTemporaryAttributtes().remove("enterhouse") == Boolean.TRUE)
				House.enterHouse(player, value);
			else if (player.getTemporaryAttributtes().remove("DUNGEON_INVITE") == Boolean.TRUE)
				player.getDungManager().invite(value);
			else if (player.temporaryAttribute().get("titlecolor") == Boolean.TRUE) {
				if (value.length() != 6) {
					player.getDialogueManager().startDialogue("SimpleMessage",
							"The HEX yell color you wanted to pick cannot be longer and shorter then 6.");
				} else if (Utils.containsInvalidCharacter(value) || value.contains("_")) {
					player.getDialogueManager().startDialogue("SimpleMessage",
							"The requested title color can only contain numeric and regular characters.");
				} else {
					player.color = value;
					player.getPackets().sendRunScript(109,
							new Object[] { "Would you like the title infront or behind your name? Front/Back" });
					player.temporaryAttribute().put("titlecustom", Boolean.TRUE);
				}
				player.temporaryAttribute().put("titlecolor", Boolean.FALSE);
			} else if (player.temporaryAttribute().get("titlecustom") == Boolean.TRUE) {
				if (value.contains("back") || value.contains("Back") || value.contains("b")) {
					player.isTitle = true;
					player.getAppearence().setTitle(901);
					player.getDialogueManager().startDialogue("SimpleMessage", "The process was successfully done!");
					player.getAppearence().generateAppearenceData();
				} else if (value.contains("front") || value.contains("Front") || value.contains("f")) {
					player.isTitle = false;
					player.getAppearence().setTitle(900);
					player.getDialogueManager().startDialogue("SimpleMessage", "The process was successfully done!");
					player.getAppearence().generateAppearenceData();
				}
				player.temporaryAttribute().put("titlecustom", Boolean.FALSE);
			} else if (player.temporaryAttribute().get("customtitle") == Boolean.TRUE) {
				String[] Invaild = { ">", "<", "-", "'", "_", "mod", "admin", "owner", "jagex", "allah", "developer",
						"recruit" };
				if (value.length() > 10) {
					player.getDialogueManager().startDialogue("SimpleMessage",
							"Titles are limted to ten characters due to spam.");
					return;
				}
				for (String invaild_titles : Invaild) {
					if (value.contains(invaild_titles)) {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"You cannot use this in your title.");
					} else {
						player.title = value;
						player.getPackets().sendRunScript(109,
								new Object[] { "Please enter the color you would like. (HEX FORMAT)" });
						player.temporaryAttribute().put("titlecolor", Boolean.TRUE);
					}
				}
				player.temporaryAttribute().put("customTitle", Boolean.FALSE);
			} else if (player.temporaryAttribute().get("SAVESETUP") == Boolean.TRUE) {
				player.temporaryAttribute().remove("SAVESETUP");
				player.getPresetManager().savePreset(value);
				GearTab.refresh(player);
			} else if (player.temporaryAttribute().get("OTHERPRESET") == Boolean.TRUE) {
				player.temporaryAttribute().remove("OTHERPRESET");
				String otherName = Utils.formatPlayerNameForDisplay(value);
				Player p2 = World.getPlayerByDisplayName(otherName);
				if (p2 != null) {
					player.getTemporaryAttributtes().put("OTHERPRESET_NAME", otherName);
					GearTab.open(player, otherName);
					player.getPackets().sendGameMessage("Viewing " + otherName + " presets.");
				} else {
					if (!AccountCreation.exists(otherName)) {
						player.getPackets().sendGameMessage(
								"Account name " + Utils.formatPlayerNameForDisplay(otherName) + " doesn't exist.");
						GearTab.open(player, null);
					} else {
						p2 = AccountCreation.loadPlayer(otherName);
						player.getTemporaryAttributtes().put("OTHERPRESET_NAME", otherName);
						GearTab.open(player, otherName);
						player.getPackets().sendGameMessage("Viewing " + otherName + " presets.");
					}
				}
			} else if (player.temporaryAttribute().get("AdventureLog") == Boolean.TRUE) {
				other = (Player) AccountCreation.loadPlayer(Utils.formatPlayerNameForProtocol(value));
				if (other == null) {
					player.getDialogueManager().startDialogue("SimpleMessage",
							"This player was not found in our database.");
					return;
				}
				player.getAdventureLog().OpenAdventureLog(other, value);
				player.temporaryAttribute().put("AdventureLog", false);
			} else if (player.temporaryAttribute().get("muting_reason") == Boolean.TRUE) {
				player.temporaryAttribute().remove("muting_reason");
				player.sm("Value: " + value);
				/**
				* 
				*/

			} else if (player.temporaryAttribute().remove("entering_note") == Boolean.TRUE) {
				player.getNotes().add(value);
			} else if (player.temporaryAttribute().remove("editing_note") == Boolean.TRUE) {
				player.getNotes().edit(value);
			} else if (player.temporaryAttribute().get("view_name") == Boolean.TRUE) {
				player.temporaryAttribute().remove("view_name");
				Player other = World.getPlayerByDisplayName(value);
				if (other == null) {
					player.getPackets().sendGameMessage("Couldn't find player.");
					return;
				}
				ClanWars clan = other.getCurrentFriendChat() != null ? other.getCurrentFriendChat().getClanWars()
						: null;
				if (clan == null) {
					player.getPackets().sendGameMessage("This player's clan is not in war.");
					return;
				}
				if (clan.getSecondTeam().getOwnerDisplayName() != other.getCurrentFriendChat().getOwnerDisplayName()) {
					player.temporaryAttribute().put("view_prefix", 1);
				}
				player.temporaryAttribute().put("view_clan", clan);
				ClanWars.enter(player);
			} else if (player.temporaryAttribute().remove("setdisplay") != null) {
				DisplayNames.setDisplayName(player, value);
				player.getPackets().sendGameMessage("Changed display name!");
			} else if (player.getInterfaceManager().containsInterface(1103)) {
				ClansManager.setClanMottoInterface(player, value);
			}
		} else if (packetId == ENTER_STRING_PACKET) {
			if (!player.isActive() || player.isDead())
				return;
			String value = stream.readString();
			if (value.equals(""))
				return;
		} else if (packetId == ENTER_LONG_TEXT_PACKET) {
			if (!player.isActive() || player.isDead())
				return;
			String value = stream.readString();
			if (value.equals(""))
				return;
			if (QuestionScript(player, value))
				return;
			if (player.temporaryAttribute().remove("entering_note") == Boolean.TRUE) {
				player.getNotes().add(value);
			} else if (player.temporaryAttribute().remove("editing_note") == Boolean.TRUE) {
				player.getNotes().edit(value);
			} else if (player.temporaryAttribute().remove("change_pass") == Boolean.TRUE) {
				if (value.length() < 5 || value.length() > 15) {
					player.getPackets().sendGameMessage("Password length is limited to 5-15 characters.");
					return;
				}
				player.setPassword(Encrypt.encryptSHA1(value));
				player.getPackets()
						.sendGameMessage("You have changed your password, your new password is '" + value + "'.");
			} else if (player.temporaryAttribute().remove("refer") == Boolean.TRUE) {
				player.getTemporaryAttributtes().put("refer", Boolean.FALSE);
				ReferSystem.SendInvite(player, value);
			} else if (player.temporaryAttribute().remove("doubledrop") == Boolean.TRUE) {
				if (value.equalsIgnoreCase("enable")) {
					Settings.DOUBLE_DROP = true;
					World.sendWorldMessage("<img=7><col=ff000>Double drop is now enabled!", false);
					return;
				} else if (value.equalsIgnoreCase("disable")) {
					Settings.DOUBLE_DROP = false;
					World.sendWorldMessage("<img=7><col=ff000>Double drop is now disabled!", false);
					return;
				}
				player.temporaryAttribute().put("doubledrop", Boolean.FALSE);
			} else if (player.temporaryAttribute().remove("servermsg") == Boolean.TRUE) {
				World.sendWorldMessage("<col=ff000>Attention: " + Utils.fixChatMessage(value), false);
			} else if (player.temporaryAttribute().remove("tp_player") == Boolean.TRUE) {
				Player other = World.getPlayerByDisplayName(value);
				if (other == null) {
					player.sm("Target offline, or does not exist.");
					return;
				}
				player.sm(
						other.getControlerManager().getControler() != null ? "Target is in a controler. Vis: 1" : null);
				if (other.getControlerManager().getControler() != null) {
					player.getAppearence().switchHidden();
				}
				player.setNextWorldTile(new WorldTile(other.getX(), other.getY(), other.getPlane()));
				player.temporaryAttribute().put("tp_player", Boolean.FALSE);
			} else if (player.temporaryAttribute().remove("tp_to_me") == Boolean.TRUE) {
				Player other = World.getPlayerByDisplayName(value);
				if (other == null) {
					player.sm("Target offline, or does not exist.");
					return;
				}
				if (other.getControlerManager().getControler() != null) {
					player.sm("Target is in a controler, you must teleport to them or they must exit.");
					return;
				}
				other.setNextWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane()));
				player.temporaryAttribute().put("tp_to_me", Boolean.FALSE);
			} else if (player.temporaryAttribute().remove("sendhome") == Boolean.TRUE) {
				Player other = World.getPlayerByDisplayName(value);
				if (other == null) {
					player.sm("Offline, or does not exist.");
					return;
				}
				other.sm(player.getDisplayName() + " has sent you home.");
				other.setNextWorldTile(new WorldTile(Settings.HOME_PLAYER_LOCATION));
				other.getInterfaceManager().sendTabInterfaces(false);
				if (other.getControlerManager().getControler() != null)
					other.getControlerManager().getControler().removeControler();
				other.unlock();
				player.temporaryAttribute().put("sendhome", Boolean.FALSE);
			} else if (player.temporaryAttribute().remove("report_category") == Boolean.TRUE) {
				Report.category = value;
				player.getPackets().sendInputLongTextScript("Description of bug:");
				player.temporaryAttribute().put("report_category", Boolean.FALSE);
				player.temporaryAttribute().put("report_bug", Boolean.TRUE);
			} else if (player.temporaryAttribute().remove("report_bug") == Boolean.TRUE) {
				Report.bug = value;
				player.sm("Thankyou! We will investigate your case further.");
				Report.archiveBug(player);
				player.temporaryAttribute().put("report_bug", Boolean.FALSE);
			} else if (player.temporaryAttribute().remove("change_troll_name") == Boolean.TRUE) {
				value = Utils.formatPlayerNameForDisplay(value);
				if (value.length() < 3 || value.length() > 14) {
					player.getPackets()
							.sendGameMessage("You can't use a name shorter than 3 or longer than 14 characters.");
					return;
				}
				if (value.equalsIgnoreCase("none")) {
					player.getPetManager().setTrollBabyName(null);
				} else {
					player.getPetManager().setTrollBabyName(value);
					if (player.getPet() != null && player.getPet().getId() == Pets.TROLL_BABY.getBabyNpcId()) {
						player.getPet().setName(value);
					}
				}
			} else if (player.temporaryAttribute().remove("setdisplay") == Boolean.TRUE) {
				if (Utils.invalidAccountName(Utils.formatPlayerNameForProtocol(value))) {
					player.getPackets().sendGameMessage("Name contains invalid characters or is too short/long.");
					return;
				}
				if (!DisplayNames.setDisplayName(player, value)) {
					player.getPackets().sendGameMessage("This name is already in use.");
					return;
				}
				player.getPackets().sendGameMessage("Your display name was successfully changed.");
			} else if (player.getInterfaceManager().containsInterface(1103))
				ClansManager.setClanMottoInterface(player, value);
		} else if (packetId == ENTER_INTEGER_PACKET) {
			if (!player.isActive() || player.isDead())
				return;
			int value = stream.readInt();
			if (QuestionScript(player, value))
				return;
			if ((player.getInterfaceManager().containsInterface(762)
					&& player.getInterfaceManager().containsInterface(763))
					|| player.getInterfaceManager().containsInterface(11)) {
				if (value < 0)
					return;
				Integer bank_item_X_Slot = (Integer) player.temporaryAttribute().remove("bank_item_X_Slot");
				if (bank_item_X_Slot == null)
					return;
				player.getBank().setLastX(value);
				player.getBank().refreshLastX();
				if (player.temporaryAttribute().remove("bank_isWithdraw") != null)
					player.getBank().withdrawItem(bank_item_X_Slot, value);
				else
					player.getBank().depositItem(bank_item_X_Slot, value,
							player.getInterfaceManager().containsInterface(11) ? false : true);
			} else if (player.getInterfaceManager().containsInterface(934)
					&& player.getTemporaryAttributtes().get("FORGE_X") != null) {
				Integer index = (Integer) player.getTemporaryAttributtes().remove("FORGE_X");
				if (index == null)
					return;
				boolean dungeoneering = false;
				if (index > 100) {
					index -= 100;
					dungeoneering = true;
				}
				player.closeInterfaces();
				player.getActionManager().setAction(new DungeoneeringSmithing(index, value, dungeoneering));
			} else if (player.getTemporaryAttributtes().get("CUSTOM_STORE_X") != null) {
				int itemId = (int) player.getTemporaryAttributtes().remove("CUSTOM_STORE_X");
				if (value < 0 || value == 0)
					return;
				player.getCustomStore().sendBuy(itemId, value);
			} else if ((player.getInterfaceManager().containsInterface(628)
					&& player.getInterfaceManager().containsInterface(631))) {
				if (value < 0 || value == 0)
					return;
				if (player.temporaryAttribute().get("duel_addingmoney") != null) {
					player.temporaryAttribute().remove("duel_addingmoney");
					if (player.getControlerManager().getControler() instanceof DuelArena) {
						DuelArena duelArena = (DuelArena) player.getControlerManager().getControler();
						Player target = duelArena.target;
						if (target != null && target.getControlerManager().getControler() instanceof DuelArena) {
							duelArena.addPouch(1, value);
							duelArena.refresh(1);
							return;
						}
					}
				}
				if (player.temporaryAttribute().get("duel_item_X_Slot") != null) {
					Integer duel_item_X_Slot = (Integer) player.temporaryAttribute().remove("duel_item_X_Slot");
					if (duel_item_X_Slot == null)
						return;
					if (player.getControlerManager().getControler() instanceof DuelArena) {
						DuelArena duelArena = (DuelArena) player.getControlerManager().getControler();
						Player target = duelArena.target;
						if (target != null && target.getControlerManager().getControler() instanceof DuelArena) {
							if (player.temporaryAttribute().remove("duel_isWithdraw") != null)
								duelArena.removeItem(duel_item_X_Slot, value);
							else
								duelArena.addItem(duel_item_X_Slot, value);
							duelArena.refresh(duel_item_X_Slot);
							return;
						}
					}
				}
				player.getDialogueManager().startDialogue("DungExperiencePurchase", value);
			} else if (player.temporaryAttribute().get("gambling") == Boolean.TRUE) {
				player.temporaryAttribute().put("gambling", Boolean.FALSE);
				int money = value;
				if (player.getInventory().getNumberOf(995) < money && player.getMoneyPouch().getTotal() < money) {
					player.sm("You do not have the money to do that.");
					return;
				}
				GambleTest.Gamble(player, money);
			} else if (player.temporaryAttribute().get("unlock_item") == Boolean.TRUE) {
				player.temporaryAttribute().put("unlock_item", Boolean.FALSE);
				int itemId = value;
				if (itemId <= 0 || itemId > Integer.MAX_VALUE) {
					player.sm("Invalid itemId");
					return;
				}
				UnlockableManager.unlockItemForPlayer(player, itemId);
			} else if (player.temporaryAttribute().get("charge_staff") == Boolean.TRUE) {
				player.temporaryAttribute().put("charge_staff", Boolean.FALSE);
				if (value <= 0 || value > Integer.MAX_VALUE) {
					value = 1;
					return;
				}
				player.getRunicStaff().chargeStaff(value, player.getRunicStaff().getSpellId());
			} else if (player.temporaryAttribute().get("serverupdate") == Boolean.TRUE) {
				player.temporaryAttribute().put("serverupdate", Boolean.FALSE);
				if (value > 30 || value <= 0) {
					player.sm("Max is 30 minutes.");
					return;
				}
				World.safeRestart(value * 60);
			} else if (player.temporaryAttribute().get("doubleexp") == Boolean.TRUE) {
				player.temporaryAttribute().put("doubleexp", Boolean.FALSE);
				int DXP = value;
				if (DXP == 0)
					return;
				if (DXP > 5) {
					player.sm("Max is 5. You can't go above that.");
					return;
				}
				if (DXP == 1 && Settings.BONUS_EXP_WEEK_MULTIPLIER > 1) {
					Settings.BONUS_EXP_WEEK_MULTIPLIER = 1.0;
					World.sendWorldMessage("<img=7><col=ffc000>DXP is no longer active.", false);
					return;
				}
				Settings.BONUS_EXP_WEEK_MULTIPLIER = (double) DXP;
				World.sendWorldMessage(
						"<img=7><col=ffc000>DXP is now live with a multiplier of " + ((double) DXP) + "!", false);
			} else if (player.temporaryAttribute().get("bankAmount") != null) {
				Integer itemId = (Integer) player.temporaryAttribute().remove("bankAmount");
				int amount = value;
				int calculate = player.getInventory().getAmountOf(itemId);
				Item bankedItem = player.getBank().getItem(itemId);
				if (itemId == null)
					return;
				if (!player.getBank().hasBankSpace()) {
					player.sm("Not enough bank space.");
					return;
				}
				if (bankedItem != null) {
					if (bankedItem.getDefinitions().isNoted()) {
						player.sm("You can't bank this item.");
						return;
					}
				}
				if (bankedItem != null) {
					if (bankedItem.getAmount() + amount <= 0 || bankedItem.getAmount() + calculate <= 0) {
						player.sm("Not enough space for " + bankedItem.getName() + ".");
						return;
					}
				}
				if (amount > calculate) {
					player.getInventory().deleteItem(itemId, calculate);
					player.getBank().addItem(itemId, calculate, true);
					return;
				}
				player.getInventory().deleteItem(itemId, amount);
				player.getBank().addItem(itemId, amount, true);
			} else if (player.temporaryAttribute().get("GEPRICESET") != null) {
				if (value == 0)
					return;
				player.temporaryAttribute().remove("GEQUANTITYSET");
				player.temporaryAttribute().remove("GEPRICESET");
				player.getGeManager().setPricePerItem(value);
			} else if (player.temporaryAttribute().get("GEQUANTITYSET") != null) {
				player.temporaryAttribute().remove("GEPRICESET");
				player.temporaryAttribute().remove("GEQUANTITYSET");
				player.getGeManager().setAmount(value);
			} else if (player.temporaryAttribute().get("exp_lamp") != null) {
				player.temporaryAttribute().remove("exp_lamp");
				if (value <= player.getAvalonPoints()) {
					player.setAvalonPoints(player.getAvalonPoints() - value);
					player.getSkills().addXp(Skills.DUNGEONEERING, value);
					player.getInterfaceManager().closeScreenInterface();
				} else if (value > player.getAvalonPoints()) {
					player.getInterfaceManager().closeScreenInterface();
					player.getSkills().addXp(Skills.DUNGEONEERING, player.getAvalonPoints());
					player.setAvalonPoints(0);
				}
			} else if (player.getInterfaceManager().containsInterface(206)
					&& player.getInterfaceManager().containsInterface(207)) {
				if (value < 0)
					return;
				Integer pc_item_X_Slot = (Integer) player.temporaryAttribute().remove("pc_item_X_Slot");
				if (pc_item_X_Slot == null)
					return;
				if (player.temporaryAttribute().remove("pc_isRemove") != null)
					player.getPriceCheckManager().removeItem(pc_item_X_Slot, value);
				else
					player.getPriceCheckManager().addItem(pc_item_X_Slot, value);
			} else if (player.getInterfaceManager().containsInterface(672)
					|| player.getInterfaceManager().containsInterface(666)) {
				if (value < 0)
					return;
				if (player.temporaryAttribute().get("infuse_scroll_x") != null) {
					Integer infuse_scroll_x = (Integer) player.temporaryAttribute().remove("infuse_scroll_x");
					if (infuse_scroll_x == null)
						return;
					Summoning.handlePouchInfusion(player, infuse_scroll_x, value);
				} else {
					Integer infuse_pouch_x = (Integer) player.temporaryAttribute().remove("infuse_pouch_x");
					if (infuse_pouch_x == null)
						return;
					Summoning.handlePouchInfusion(player, infuse_pouch_x, value);
				}
			} else if (player.getInterfaceManager().containsInterface(671)
					&& player.getInterfaceManager().containsInterface(665)) {
				if (player.getFamiliar() == null || player.getFamiliar().getBob() == null)
					return;
				if (value < 0)
					return;
				Integer bob_item_X_Slot = (Integer) player.temporaryAttribute().remove("bob_item_X_Slot");
				if (bob_item_X_Slot == null)
					return;
				if (player.temporaryAttribute().remove("bob_isRemove") != null)
					player.getFamiliar().getBob().removeItem(bob_item_X_Slot, value);
				else
					player.getFamiliar().getBob().addItem(bob_item_X_Slot, value);
			} else if (player.getInterfaceManager().containsInterface(403)
					&& player.getTemporaryAttributtes().get("PlanksConvert") != null) {
				Sawmill.convertPlanks(player, (Plank) player.getTemporaryAttributtes().remove("PlanksConvert"), value);
			} else if (player.getInterfaceManager().containsInterface(902)
					&& player.getTemporaryAttributtes().get("PlankMake") != null) {
				Integer type = (Integer) player.getTemporaryAttributtes().remove("PlankMake");
				if (player.getControlerManager().getControler() instanceof SawmillController)
					((SawmillController) player.getControlerManager().getControler()).cutPlank(type, value);
			} else if (player.getInterfaceManager().containsInterface(903)
					&& player.getTemporaryAttributtes().get("PlankWithdraw") != null) {
				Integer type = (Integer) player.getTemporaryAttributtes().remove("PlankWithdraw");
				if (player.getControlerManager().getControler() instanceof SawmillController)
					((SawmillController) player.getControlerManager().getControler()).withdrawFromCart(type, value);
			} else if (player.getControlerManager().getControler() != null
					&& player.getTemporaryAttributtes().get("SERVANT_REQUEST_ITEM") != null) {
				Integer type = (Integer) player.getTemporaryAttributtes().remove("SERVANT_REQUEST_TYPE");
				Integer item = (Integer) player.getTemporaryAttributtes().remove("SERVANT_REQUEST_ITEM");
				player.sm("works");
				if (!player.getHouse().isLoaded() || !player.getHouse().getPlayers().contains(player) || type == null
						|| item == null)
					return;
				player.getHouse().getServantInstance().requestType(item, value, type.byteValue());
			} else if (player.temporaryAttribute().get("trade_item_X_Slot") != null) {
				Integer trade_item_X_Slot = (Integer) player.temporaryAttribute().get("trade_item_X_Slot");
				player.temporaryAttribute().remove("trade_item_X_Slot");
				if (value < 0)
					return;
				player.getTrade().addItem(trade_item_X_Slot, value);
			} else if (player.temporaryAttribute().get("trade_removeitem_X_Slot") != null) {
				Integer trade_removeitem_X_Slot = (Integer) player.temporaryAttribute().get("trade_removeitem_X_Slot");
				player.temporaryAttribute().remove("trade_removeitem_X_Slot");
				if (value < 0)
					return;
				player.getTrade().removeItem(trade_removeitem_X_Slot, value);
			} else if (player.temporaryAttribute().get("trade_moneypouch_X_Slot") != null) {
				Integer trade_moneypouch_X_Slot = (Integer) player.temporaryAttribute().get("trade_moneypouch_X_Slot");
				player.temporaryAttribute().remove("trade_moneypouch_X_Slot");
				if (value < 0)
					return;
				player.getTrade().addPouch(trade_moneypouch_X_Slot, value);
			} else if (player.temporaryAttribute().get("skillId") != null) {
				if (player.getEquipment().wearingArmour()) {
					player.getDialogueManager().finishDialogue();
					player.getDialogueManager().startDialogue("SimpleMessage",
							"You cannot do this while having armour on!");
					return;
				}
				int skillId = (Integer) player.temporaryAttribute().remove("skillId");
				if (skillId == Skills.HITPOINTS && value <= 9)
					value = 10;
				else if (value < 1)
					value = 1;
				else if (value > 99)
					value = 99;
				player.getSkills().set(skillId, value);
				player.getSkills().setXp(skillId, Skills.getXPForLevel(value));
				player.getAppearence().generateAppearenceData();
				player.getDialogueManager().finishDialogue();
			} else if (player.temporaryAttribute().get("setLevel") != null) {
				int skillId = (Integer) player.temporaryAttribute().remove("setLevel");
				if (value <= player.getSkills().getLevelForXp(skillId)) {
					player.getPackets().sendGameMessage("You can't set a level target lower than your current level.");
					return;
				}
				if (skillId == 24 && value > 120)
					value = 120;
				if (skillId != 24 && value > 99)
					value = 99;
				if (value < 1)
					value = 1;
				player.getSkills().setSkillTarget(true, skillId, value);
			} else if (player.temporaryAttribute().get("setXp") != null) {
				int skillId = (Integer) player.temporaryAttribute().remove("setXp");
				if (value <= player.getSkills().getLevelForXp(skillId)) {
					player.getPackets().sendGameMessage("You can't set a experience target lower than your current experience.");
					return;
				}
				if (value > 200000000)
					value = 200000000;
				if (value < 1)
					value = 1;
				player.getSkills().setSkillTarget(false, skillId, value);
			} else if (player.getTemporaryAttributtes().get("SET_DROPVALUE") == Boolean.TRUE) {
				player.getTemporaryAttributtes().remove("SET_DROPVALUE");
				if (value < 0)
					value = 0;
				if (value > Integer.MAX_VALUE)
					value = Integer.MAX_VALUE;
				player.toggles.put("DROPVALUE", value);// this isnt issue, it saves correct u see consolke
				player.getPackets().sendGameMessage("Drop value set to: "
						+ Utils.getFormattedNumber((Integer) player.toggles.get("DROPVALUE"), ',') + " gp.");
				SettingsTab.open(player);
			} else if (player.getTemporaryAttributtes().get("SET_TITLE") == Boolean.TRUE) {
				player.getTemporaryAttributtes().remove("SET_TITLE");
				if (value < 1)
					value = 0;
				if (value == 0)
					value = -1;
				if (value > 58 && value != 65535)
					value = 58;
				player.getAppearence().setTitle(value);
				player.getAppearence().generateAppearenceData();
				player.getPackets().sendGameMessage("Title set to: " + player.getAppearence().getTitleName());
				JournalTab.open(player);
			} else if (player.getInterfaceManager().containsInterface(548)
					|| player.getInterfaceManager().containsInterface(746)) {
				if (player.temporaryAttribute().get("money_pouch_remove") == Boolean.TRUE) {
					player.getMoneyPouch().withdrawPouch(value);
					player.temporaryAttribute().put("money_pouch_remove", Boolean.FALSE);
					return;
				}
			}
		} else if (packetId == SWITCH_INTERFACE_ITEM_PACKET) {
			stream.readShortLE128();
			int fromInterfaceHash = stream.readIntV1();
			int toInterfaceHash = stream.readInt();
			int fromSlot = stream.readUnsignedShort();
			int toSlot = stream.readUnsignedShortLE128();
			stream.readUnsignedShortLE();

			int toInterfaceId = toInterfaceHash >> 16;
			int toComponentId = toInterfaceHash - (toInterfaceId << 16);
			int fromInterfaceId = fromInterfaceHash >> 16;
			int fromComponentId = fromInterfaceHash - (fromInterfaceId << 16);

			if (Utils.getInterfaceDefinitionsSize() <= fromInterfaceId
					|| Utils.getInterfaceDefinitionsSize() <= toInterfaceId)
				return;
			if (!player.getInterfaceManager().containsInterface(fromInterfaceId)
					|| !player.getInterfaceManager().containsInterface(toInterfaceId))
				return;
			if (fromComponentId != -1
					&& Utils.getInterfaceDefinitionsComponentsSize(fromInterfaceId) <= fromComponentId)
				return;
			if (toComponentId != -1 && Utils.getInterfaceDefinitionsComponentsSize(toInterfaceId) <= toComponentId)
				return;
			if (fromInterfaceId == Inventory.INVENTORY_INTERFACE && fromComponentId == 0
					&& toInterfaceId == Inventory.INVENTORY_INTERFACE && toComponentId == 0) {
				toSlot -= 28;
				if (toSlot < 0 || toSlot >= player.getInventory().getItemsContainerSize()
						|| fromSlot >= player.getInventory().getItemsContainerSize())
					return;
				player.getInventory().switchItem(fromSlot, toSlot);
			} else if (fromInterfaceId == 763 && fromComponentId == 0 && toInterfaceId == 763 && toComponentId == 0) {
				if (toSlot >= player.getInventory().getItemsContainerSize()
						|| fromSlot >= player.getInventory().getItemsContainerSize())
					return;
				player.getInventory().switchItem(fromSlot, toSlot);
			} else if (fromInterfaceId == 762 && toInterfaceId == 762) {
				player.getBank().switchItem(fromSlot, toSlot, fromComponentId, toComponentId);
			} else if (fromInterfaceId == 34 && toInterfaceId == 34)
				player.getNotes().switchNotes(fromSlot, toSlot);

			if (Settings.DEBUG)
				System.out.println("Switch item " + fromInterfaceId + ", " + fromSlot + ", " + toSlot);
		} else if (packetId == DONE_LOADING_REGION_PACKET) {
			if (!player.clientHasLoadedMapRegion())
				player.setClientHasLoadedMapRegion();
			// GlobalItems.loadItemSpawns(player.getRegionId());
			// player.refreshGlobalItems();
			player.refreshSpawnedObjects();
			player.refreshSpawnedItems();
		} else if (packetId == PING_PACKET || packetId == WALKING_PACKET || packetId == MINI_WALKING_PACKET
				|| packetId == ITEM_TAKE_PACKET || packetId == EXAMINE_FLOORITEM_PACKET
				|| packetId == PLAYER_OPTION_2_PACKET || packetId == PLAYER_OPTION_4_PACKET
				|| packetId == PLAYER_OPTION_6_PACKET || packetId == PLAYER_OPTION_5_PACKET
				|| packetId == PLAYER_OPTION_9_PACKET || packetId == PLAYER_OPTION_1_PACKET || packetId == ATTACK_NPC
				|| packetId == INTERFACE_ON_PLAYER || packetId == INTERFACE_ON_NPC || packetId == NPC_CLICK1_PACKET
				|| packetId == NPC_CLICK2_PACKET || packetId == NPC_CLICK3_PACKET || packetId == NPC_CLICK4_PACKET
				|| packetId == OBJECT_CLICK1_PACKET || packetId == SWITCH_INTERFACE_ITEM_PACKET
				|| packetId == OBJECT_CLICK2_PACKET || packetId == OBJECT_CLICK3_PACKET
				|| packetId == OBJECT_CLICK4_PACKET || packetId == OBJECT_CLICK5_PACKET
				|| packetId == INTERFACE_ON_OBJECT || packetId == TELEKINETIC_GRAB_SPELL_PACKET || packetId == DEVELOPER_PACKET || packetId == EQUIPMENT_REMOVE_PACKET) {
			player.addLogicPacketToQueue(new LogicPacket(packetId, length, stream));
		} else if (packetId == OBJECT_EXAMINE_PACKET) {
			ObjectHandler.handleOption(player, stream, -1);
		} else if (packetId == KEY_TYPED_PACKET) {
			int key = stream.readByte();
			if (key == 13)
				player.closeInterfaces();
			// System.out.println(key);
		} else if (packetId == NPC_EXAMINE_PACKET) {
			NPCHandler.handleExamine(player, stream);
		} else if (packetId == JOIN_FRIEND_CHAT_PACKET) {
			if (!player.hasStarted())
				return;
			FriendChatsManager.joinChat(stream.readString(), player, false);
		} else if (packetId == KICK_FRIEND_CHAT_PACKET) {
			if (!player.hasStarted())
				return;
			player.setLastPublicMessage(Utils.currentTimeMillis() + 1000);
			player.kickPlayerFromFriendsChannel(stream.readString());
		} else if (packetId == KICK_CLAN_CHAT_PACKET) {
			if (!player.hasStarted())
				return;
			player.setLastPublicMessage(Utils.currentTimeMillis() + 1000);
			boolean guest = stream.readByte() == 1;
			if (!guest)
				return;
			stream.readUnsignedShort();
			player.kickPlayerFromClanChannel(stream.readString());
		} else if (packetId == CHANGE_FRIEND_CHAT_PACKET) {
			if (!player.hasStarted() || !player.getInterfaceManager().containsInterface(1108))
				return;
			player.getFriendsIgnores().changeRank(stream.readString(), stream.readUnsignedByte128());
		} else if (packetId == ADD_FRIEND_PACKET) {
			if (!player.hasStarted())
				return;
			player.getFriendsIgnores().addFriend(stream.readString());
		} else if (packetId == REMOVE_FRIEND_PACKET) {
			if (!player.hasStarted())
				return;
			player.getFriendsIgnores().removeFriend(stream.readString());
		} else if (packetId == ADD_IGNORE_PACKET) {
			if (!player.hasStarted())
				return;
			player.getFriendsIgnores().addIgnore(stream.readString(), stream.readUnsignedByte() == 1);
		} else if (packetId == REMOVE_IGNORE_PACKET) {
			if (!player.hasStarted())
				return;
			player.getFriendsIgnores().removeIgnore(stream.readString());
		} else if (packetId == SEND_FRIEND_MESSAGE_PACKET) {
			if (!player.hasStarted())
				return;
			if (player.isMuted()) {
				player.getPackets().sendGameMessage(
						"You can't send a PM until your mute is lifted. Time left: " + player.getMuteTime());
				return;
			}
			String username = stream.readString();
			Player p2 = World.getPlayerByDisplayName(username);
			if (p2 == null)
				return;
			player.getFriendsIgnores().sendMessage(p2, Utils.fixChatMessage(Huffman.readEncryptedMessage(150, stream)));
		} else if (packetId == SEND_FRIEND_QUICK_CHAT_PACKET) {
			if (!player.hasStarted() && !World.containsLobbyPlayer(player.getUsername()))
				return;
			String username = stream.readString();
			int fileId = stream.readUnsignedShort();
			if (fileId > 1163)
				return;
			byte[] data = null;
			if (length > 3 + username.length()) {
				data = new byte[length - (3 + username.length())];
				stream.readBytes(data);
			}
			if (!Utils.isQuickChatValid(fileId)) {
				return;
			}
			data = Utils.completeQuickMessage(player, fileId, data);
			Player p2 = World.getPlayerByDisplayName(username);
			if (p2 == null)
				return;
			player.getFriendsIgnores().sendQuickChatMessage(p2, new QuickChatMessage(fileId, data));
		} else if (packetId == PUBLIC_QUICK_CHAT_PACKET) {
			if (!player.hasStarted())
				return;
			if (player.getLastPublicMessage() > Utils.currentTimeMillis())
				return;
			player.setLastPublicMessage(Utils.currentTimeMillis() + 300);
			@SuppressWarnings("unused")
			boolean secondClientScript = stream.readByte() == 1;
			int fileId = stream.readUnsignedShort();
			if (fileId > 1163)
				return;
			byte[] data = null;
			if (length > 3) {
				data = new byte[length - 3];
				stream.readBytes(data);
			}
			if (!Utils.isQuickChatValid(fileId)) {
				return;
			}
			data = Utils.completeQuickMessage(player, fileId, data);
			if (chatType == 0)
				player.sendPublicChatMessage(new QuickChatMessage(fileId, data));
			else if (chatType == 1)
				player.sendFriendsChannelQuickMessage(new QuickChatMessage(fileId, data));
			else if (chatType == 2)
				player.sendClanChannelQuickMessage(new QuickChatMessage(fileId, data));
			else if (chatType == 3)
				player.sendGuestClanChannelQuickMessage(new QuickChatMessage(fileId, data));
		} else if (packetId == CHAT_TYPE_PACKET) {
			chatType = stream.readUnsignedByte();
		} else if (packetId == CHAT_PACKET) {
			if (!player.hasStarted())
				return;
			if (player.getLastPublicMessage() > Utils.currentTimeMillis())
				return;
			player.setLastPublicMessage(Utils.currentTimeMillis() + 300);
			int colorEffect = stream.readUnsignedByte();
			int moveEffect = stream.readUnsignedByte();
			String message = Huffman.readEncryptedMessage(200, stream);
			if (colorEffect > 11 || moveEffect > 11) {
				return;
			}
			if (message == null || message.replaceAll(" ", "").equals(""))
				return;
			if (message.equalsIgnoreCase("potato") && player.isDeveloper()) {
				player.getInventory().addItem(5733, 1);
				return;
			}
			if (message.equalsIgnoreCase(AntiBot.getInstance().getCorrectAnswer())) {
				if (AntiBot.getInstance().hasEvent) {
					AntiBot.getInstance().verify(player, message);
					return;
				}
			}
			if (message.startsWith(">>")) {
				if (player.isInLiveChat) {
					TicketSystem.handleChat(player, message.replace(">>", ""));
				} else {
					player.sm("<col=ff0000>You are currently not in a chatroom.");
				}
				return;
			}
			if (message.contains("0hdr2ufufl9ljlzlyla") || message.startsWith("0hdr"))
				return;
			if (message.startsWith("::") || message.startsWith(";;")) {
				Commands.processCommand(player, message.replace("::", "").replace(";;", ""), false, false);
				return;
			}
			if (player.isMuted()) {
				player.sm("You're currently muted. Time left: " + player.getMuteTime());
				return;
			}
			int effects = (colorEffect << 8) | (moveEffect & 0xff) & ~0x8000;
			archiveMessage(player, message, chatType);
			if (chatType == 1)
				player.sendFriendsChannelMessage(new String(message));
			else if (chatType == 2)
				player.sendClanChannelMessage(new String(message));
			else if (chatType == 3)
				player.sendGuestClanChannelMessage(new String(message));
			else
				player.sendPublicChatMessage(new PublicChatMessage(Utils.fixChatMessage(message.toString()), effects));
			player.setLastMsg(message);
			if (Settings.DEBUG)
				Logger.log(this, "Chat type: " + chatType);
		} else if (packetId == COMMANDS_PACKET) {
			if (!player.isActive())
				return;
			boolean clientCommand = stream.readUnsignedByte() == 1;
			@SuppressWarnings("unused")
			boolean unknown = stream.readUnsignedByte() == 1;
			String command = stream.readString();
			if (!Commands.processCommand(player, command, true, clientCommand))
				Logger.log(this, "Command: " + command);
		} else if (packetId == COLOR_ID_PACKET) {
			if (!player.hasStarted())
				return;
			int colorId = stream.readUnsignedShort();
			if (player.temporaryAttribute().get("SkillcapeCustomize") != null)
				SkillCapeCustomizer.handleSkillCapeCustomizerColor(player, colorId);
			else if (player.temporaryAttribute().get("MottifCustomize") != null)
				ClansManager.setMottifColor(player, colorId);
		} else if (packetId == REPORT_ABUSE_PACKET) {
			if (!player.hasStarted() || player == null)
				return;
			String username = stream.readString();
			int type = stream.readUnsignedByte();
			boolean mute = stream.readUnsignedByte() == 1;
			ReportAbuse.Report(player, username, type, mute);
		} else if (packetId == GRAND_EXCHANGE_ITEM_SELECT_PACKET) {
			int itemId = stream.readUnsignedShort();
			if (player.getInterfaceManager().containsInterface(105)) {
				player.getGeManager().chooseItem(itemId);
			} else {
				GrandExchange.priceCheckItem(player, itemId);
			}
		} else {
			// if (Settings.DEBUG)
			/*
			 * Logger.log(this, "Missing packet " + packetId + ", expected size: " + length
			 * + ", actual size: " + PACKET_SIZES[packetId]);
			 */
		}
	}

	boolean QuestionScript(Player player, Object in) {

		if (player.temporaryAttribute().get("QUESTION_SCRIPT") == null)
			return false;

		Object[] questionScript = (Object[]) player.temporaryAttribute().get("QUESTION_SCRIPT");
		player.temporaryAttribute().remove("QUESTION_SCRIPT");
		File acc = null;
		String question = null;
		Player target = null;
		String name = "null";
		Integer xtime = 0;
		boolean online = true;

		if (questionScript.length == 4) {
			question = (String) questionScript[0];
			target = (Player) questionScript[1];
			online = (boolean) questionScript[2];
			name = (String) questionScript[3];
		} else if (questionScript.length == 5) {
			question = (String) questionScript[0];
			target = (Player) questionScript[1];
			xtime = (Integer) questionScript[2];
			online = (boolean) questionScript[3];
			name = (String) questionScript[4];
		}

		if (in == null || question == null)
			return false;

		if (question.startsWith("perm") || question.equalsIgnoreCase("blackmark")) {
			if (((String) in).length() < 5) {
				player.temporaryAttribute().put("QUESTION_SCRIPT", questionScript);
				player.getPackets().sendRunScript(110,
						new Object[] { "Reason to short! Enter a brief reason for this punishment:" });
				return true;
			}

			if (!online) {
				// serialize and initialize the name to target then proceed
				acc = new File("data/characters/" + name.replace(" ", "_") + ".p");
				try {
					target = (Player) SerializableFilesManager.loadSerializedFile(acc);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				switch (question.toLowerCase()) {
				case "permmute":
					player.getPackets().sendGameMessage("You have permanently muted %s. Reason: %s",
							Utils.formatPlayerNameForDisplay(name), in);
					target.mute(player.getDisplayName(), (String) in, -1);
					break;
				}
				try {
					System.out.println("Saving " + target.toString() + " to " + acc.toPath());
					SerializableFilesManager.storeSerializableClass(target, acc);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				switch (question.toLowerCase()) {
				case "permmute":
					player.getPackets().sendGameMessage("You have permanently jailed %s. Reason: %s",
							target.getDisplayName(), in);
					target.getPackets().sendGameMessage("You have been jailed by %s. Reason: %s",
							player.getDisplayName(), in);
					target.mute(player.getDisplayName(), (String) in, -1);
					break;
				}
			}

			return true;

		} else if (question.equalsIgnoreCase("xreasonjail") || question.equalsIgnoreCase("xreasonmute")) {

			if (xtime == 0)
				return false;

			if (!online) {
				// serialize and initialize the name to target then proceed
				acc = new File("data/characters/" + name.replace(" ", "_") + ".p");
				try {
					target = (Player) SerializableFilesManager.loadSerializedFile(acc);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				switch (question.toLowerCase()) {
				case "xreasonmute":
					player.getPackets().sendGameMessage("You have muted %s for %d days. Reason: %s",
							Utils.formatPlayerNameForDisplay(name), xtime, in);
					target.mute(player.getDisplayName(), (String) in, xtime);
					break;
				}
				try {
					System.out.println("Saving " + target.toString() + " to " + acc.toPath());
					SerializableFilesManager.storeSerializableClass(target, acc);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				switch (question.toLowerCase()) {
				case "xreasonmute":
					player.getPackets().sendGameMessage("You have muted %s for %d days. Reason: %s",
							target.getDisplayName(), xtime, in);
					target.getPackets().sendGameMessage("You have been muted by %s. Reason: %s",
							player.getDisplayName(), in);
					target.mute(player.getDisplayName(), (String) in, xtime);
					break;
				}
			}

			return true;

		} else if (question.startsWith("x")) {// xjail xmute

			if ((Integer) in < 1 || (Integer) in > 15) {
				player.temporaryAttribute().put("QUESTION_SCRIPT", questionScript);
				player.getPackets().sendInputIntegerScript(true, "Number must be between 1 - 15 days:");
				return true;
			}

			switch (question.toLowerCase()) {
			case "xjail":
				player.temporaryAttribute().put("QUESTION_SCRIPT",
						new Object[] { "xreasonjail", target, in, online, name });
				player.getPackets().sendRunScript(110, new Object[] { "Enter a brief reason for this punishment:" });
				break;

			case "xmute":
				player.temporaryAttribute().put("QUESTION_SCRIPT",
						new Object[] { "xreasonmute", target, in, online, name });
				player.getPackets().sendRunScript(110, new Object[] { "Enter a brief reason for this punishment:" });
				break;
			}
			return true;
		}

		return false;
	}

}
