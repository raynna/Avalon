package com.rs.game.player.content.friendschat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import com.rs.Settings;
import com.rs.cores.CoresManager;
import com.rs.game.World;
import com.rs.game.minigames.clanwars.ClanWars;
import com.rs.game.player.AccountCreation;
import com.rs.game.player.FriendsIgnores;
import com.rs.game.player.Player;
import com.rs.game.player.QuickChatMessage;
import com.rs.game.player.Ranks.Rank;
import com.rs.io.OutputStream;
import com.rs.utils.Utils;

public class FriendChatsManager {

	private String owner;
	private String ownerDisplayName;
	private FriendsIgnores settings;
	private CopyOnWriteArrayList<String> players;
	private ConcurrentHashMap<String, Long> bannedPlayers;
	private byte[] dataBlock;

	/**
	 * The clan wars instance (if the clan is in a war).
	 */
	private transient ClanWars clanWars;

	private static HashMap<String, FriendChatsManager> cachedFriendChats;

	public static void init() {
		cachedFriendChats = new HashMap<String, FriendChatsManager>();
	}

	public int getRank(int rights, String username) {
		if (rights == 2)
			return 127;
		if (username.equals(owner))//ye owner i had to change to save as username, instead of owner = player, but farming there is no way
			return 7;
		return settings.getRank(username);
	}

	public CopyOnWriteArrayList<String> getPlayers() {
		return players;
	}

	public int getWhoCanKickOnChat() {
		return settings.getWhoCanKickOnChat();
	}

	public String getOwnerDisplayName() {
		return ownerDisplayName;
	}

	public String getOwnerName() {
		return owner;
	}

	public String getChannelName() {
		return settings.getChatName().replaceAll("<img=", "");
	}

	private void joinChat(Player player, boolean silent) {
		synchronized (this) {
			if (!player.getUsername().equals(owner) && !settings.hasRankToJoin(player.getUsername())
					&& !player.isStaff()) {
				player.getPackets()
						.sendGameMessage("You do not have a high enough rank to join this friends chat channel.");
				return;
			}
			if (players.size() >= 100) {
				player.getPackets().sendGameMessage("This chat is full, try again later.");
				return;
			}
			Long bannedSince = bannedPlayers.get(player.getUsername());
			if (bannedSince != null) {
				if (bannedSince + 3600000 > Utils.currentTimeMillis()) {
					player.getPackets().sendGameMessage("You have been banned from this channel.");
					return;
				}
				bannedPlayers.remove(player.getUsername());
			}
			joinChatNoCheck(player, silent ? true : false);
		}
	}

	public void leaveChat(Player player, boolean logout) {
		synchronized (this) {
			player.setCurrentFriendChat(null);
			if (players != null) {
				players.remove(player.getDisplayName());
				if (players.size() == 0) { // no1 at chat so uncache it
					synchronized (cachedFriendChats) {
						cachedFriendChats.remove(owner);
					}
				} else
					refreshChannel();
			}
			if (!logout) {
				player.setCurrentFriendChatOwner(null);
				player.disableLootShare();
				player.closeInterfaces();
				player.getPackets().sendGameMessage("You have left the channel.");
				player.getPackets().sendFriendsChatChannel();
			}
			if (clanWars != null) {
				clanWars.leave(player, false);
			}
		}
	}

	public Player getPlayerByDisplayName(String username) {
		String formatedUsername = Utils.formatPlayerNameForProtocol(username);
		for (Player player : World.getPlayers()) {
			if (player == null)
				continue;
			if (player.getUsername().equals(formatedUsername) || player.getDisplayName().equals(username))
				return World.getPlayerByDisplayName(player.getDisplayName());
		}
		return null;
	}

	public void kickPlayerFromChat(Player player, String username) {
		String name = "";
		for (char character : username.toCharArray()) {
			name += Utils.containsInvalidCharacter(character) ? " " : character;
		}
		synchronized (this) {
			int rank = getRank(player.getRights(), player.getUsername());
			if (rank < getWhoCanKickOnChat())
				return;
			Player kicked = getPlayerByDisplayName(name);
			String formatedName = Utils.formatPlayerNameForDisplay(player.getUsername());
			String displayName = player.getDisplayName();
			int rights = player.getRights();
			for (Player p2 : World.getPlayers()) {
				if (p2 == null)
					continue;
				if (players.contains(p2.getDisplayName()))
					p2.getPackets().receiveFriendChatMessage(formatedName, displayName, rights, settings.getChatName(),
							"[Attempting to kick/ban " + name + " from this Friends Chat.]");
			}
			if (kicked == null) {
				player.getPackets().sendGameMessage("This player is not this channel.");
				return;
			}
			if (kicked.isDeveloper()) {
				player.sm("You cannot kick developers!");
				return;
			}
			if (kicked.getUsername() == owner && !player.isDeveloper()) {
				player.sm("You cannot kick the owner!");
				return;
			}
			if (rank <= getRank(kicked.getRights(), kicked.getUsername()))
				return;
			kicked.setCurrentFriendChat(null);
			kicked.setCurrentFriendChatOwner(null);
			player.disableLootShare();
			players.remove(kicked.getDisplayName());
			bannedPlayers.put(kicked.getUsername(), Utils.currentTimeMillis());
			kicked.getPackets().sendFriendsChatChannel();
			kicked.getPackets().sendGameMessage("You have been kicked from the friends chat channel.");
			// player.getPackets().sendGameMessage("You have kicked " +
			// kicked.getUsername() + " from friends chat channel.");
			refreshChannel();

		}
	}

	private void joinChatNoCheck(Player player, boolean silent) {
		synchronized (this) {
			players.add(player.getDisplayName());
			player.setCurrentFriendChat(this);
			player.setCurrentFriendChatOwner(owner);
			if (!silent)
				player.getPackets()
						.sendGameMessage("You are now talking in the friends chat channel " + settings.getChatName());
			refreshChannel();
		}
	}

	public void destroyChat() {
		synchronized (this) {
			for (Player player : World.getPlayers()) {
				if (player == null)
					continue;
				if (players.contains(player.getDisplayName())) {
					player.setCurrentFriendChat(null);
					player.setCurrentFriendChatOwner(null);
					player.disableLootShare();
					player.getPackets().sendFriendsChatChannel();
					player.getPackets().sendGameMessage("You have been removed from this channel!");
				}

			}
		}
		synchronized (cachedFriendChats) {
			cachedFriendChats.remove(owner);
		}

	}

	public void sendQuickMessage(Player player, QuickChatMessage message) {
		synchronized (this) {
			if (!player.getUsername().equals(owner) && !settings.canTalk(player) && !player.isDeveloper()) {
				player.getPackets()
						.sendGameMessage("You do not have a enough rank to talk on this friends chat channel.");
				return;
			}
			String formatedName = Utils.formatPlayerNameForDisplay(player.getUsername());
			String displayName = player.getDisplayName();
			Rank[] rank = player.getPlayerRank().getRank();
			int rights = (rank[0] == Rank.DEVELOPER || rank[0] == Rank.DEVELOPER) ? player.getMessageIcon() + 1
					: player.getMessageIcon();
			for (Player p2 : World.getPlayers()) {
				if (p2 == null)
					continue;
				if (players.contains(p2.getDisplayName()))
					p2.getPackets().receiveFriendChatQuickMessage(formatedName, displayName, rights,
							settings.getChatName(), message);
			}
		}
	}

	public void sendMessage(Player player, String message) {
		synchronized (this) {
			if (!player.getUsername().equals(owner) && !settings.canTalk(player) && !player.isDeveloper()) {
				player.getPackets()
						.sendGameMessage("You do not have a enough rank to talk on this friends chat channel.");
				return;
			}
			String fixed = Utils.fixChatMessage(message);
			String formatedName = Utils.formatPlayerNameForDisplay(player.getUsername());
			String displayName = player.getDisplayName();
			Rank[] rank = player.getPlayerRank().getRank();
			int rights = (rank[0] == Rank.DEVELOPER || rank[0] == Rank.DEVELOPER) ? player.getMessageIcon() + 1
					: player.getMessageIcon();
			for (Player p2 : World.getPlayers()) {
				if (p2 == null)
					continue;
				if (players.contains(p2.getDisplayName()))
					p2.getPackets().receiveFriendChatMessage(formatedName, displayName, rights, settings.getChatName(),
							fixed);
			}
		}
	}

	public void sendFCGiveaway(final Player player, boolean reroll, boolean message) {
		if (message)
			sendRollMessage(player, "[Giveaway] " + (reroll ? "Could not find a winner, rerolling.." : "Rolling.."));
		CoresManager.slowExecutor.schedule(new Runnable() {

			@Override
			public void run() {
				if (getPlayers().size() < 3) {
					sendRollMessage(player, "Not enough players in friendchat to host giveaway.");
					return;
				}
				sendRollMessage(player, "[Giveaway] " + "Picking a random winner..");
				CoresManager.slowExecutor.schedule(new Runnable() {

					@Override
					public void run() {
						Player winner = null;
						int winnerIndex = Utils.getRandom(getPlayers().size() - 1);
						int index = 0;
						for (Player p2 : World.getPlayers()) {
							if (p2 == null)
								continue;
							if (players.contains(p2.getDisplayName())) {
								if (winnerIndex == index) {
									winner = p2;
									if (getPlayers().size() < 3)
										return;
									if (winnerIndex == getWhoCanKickOnChat() || winner == player) {
										sendFCGiveaway(player, true, true);
										return;
									}
									break;
								}
							}
							index++;
						}
						sendRollMessage(player, "[Giveaway] Winner is.. " + winner.getDisplayName() + ".");
						winner.getPackets()
								.sendGameMessage("Congratulations! You won the friendschat giveaway hosted by "
										+ player.getDisplayName() + ".");
					}
				}, 2000, TimeUnit.MILLISECONDS);
			}
		}, 2000, TimeUnit.MILLISECONDS);
	}

	public void sendRollMessage(final Player player, String message) {
		String formatedName = Utils.formatPlayerNameForDisplay((player).getUsername());
		String displayName = player.getDisplayName();
		int rights = player.getRights();
		for (Player p2 : World.getPlayers()) {
			if (p2 == null)
				continue;
			if (players.contains(p2.getDisplayName())) {
				p2.getPackets().receiveFriendChatMessage(formatedName, displayName, rights, settings.getChatName(),
						message);
			}
		}
	}

	public void sendDiceMessage(Player player, String message) {
		synchronized (this) {
			if (!player.getUsername().equals(owner) && !settings.canTalk(player) && !player.isDeveloper()) {
				player.getPackets()
						.sendGameMessage("You do not have a enough rank to talk on this friends chat channel.");
				return;
			}
			for (Player p2 : World.getPlayers()) {
				if (p2 == null)
					continue;
				if (players.contains(p2.getDisplayName())) {
					p2.getPackets().sendGameMessage(message);
				}
			}
		}
	}

	public void refreshChannel() {
		synchronized (this) {
			OutputStream stream = new OutputStream();
			stream.writeString(ownerDisplayName + " <col=ffc800>~" + players.size() + "~");
			String ownerName = Utils.formatPlayerNameForDisplay(owner);
			stream.writeByte(getOwnerDisplayName().equals(ownerName) ? 0 : 1);
			String channelName = getChannelName();
			if (!getOwnerDisplayName().equals(ownerName))
				stream.writeString(ownerName);
			stream.writeLong(Utils.stringToLong(channelName));
			int kickOffset = stream.getOffset();
			stream.writeByte(0);
			stream.writeByte(getPlayers().size());
			for (Player player : World.getPlayers()) {
				if (player == null)
					continue;
				if (players.contains(player.getDisplayName())) {
					String displayName = player.getDisplayName();
					String name = Utils.formatPlayerNameForDisplay(player.getUsername());
					stream.writeString(displayName);
					stream.writeByte(displayName.equals(name) ? 0 : 1);
					if (!displayName.equals(name))
						stream.writeString(name);
					stream.writeShort(1);
					int rank = getRank(player.getRights(), player.getUsername());
					stream.writeByte(rank);
					stream.writeString(Settings.SERVER_NAME);
				}
			}
			dataBlock = new byte[stream.getOffset()];
			stream.setOffset(0);
			stream.getBytes(dataBlock, 0, dataBlock.length);
			for (Player player : World.getPlayers()) {
				if (player == null)
					continue;
				if (players.contains(player.getDisplayName())) {
					dataBlock[kickOffset] = (byte) (player.getUsername().equals(owner) ? 0 : getWhoCanKickOnChat());
					player.getPackets().sendFriendsChatChannel();
				}
			}
		}
	}

	public byte[] getDataBlock() {
		return dataBlock;
	}

	private FriendChatsManager(Player player) {
		owner = player.getUsername();
		ownerDisplayName = player.getDisplayName();
		settings = player.getFriendsIgnores();
		players = new CopyOnWriteArrayList<String>();
		bannedPlayers = new ConcurrentHashMap<String, Long>();
	}

	public static void destroyChat(Player player) {
		synchronized (cachedFriendChats) {
			FriendChatsManager chat = cachedFriendChats.get(player.getUsername());
			if (chat == null)
				return;
			chat.destroyChat();
			player.getPackets().sendGameMessage("Your friends chat channel has now been disabled!");
		}
	}

	public static void linkSettings(Player player) {
		synchronized (cachedFriendChats) {
			FriendChatsManager chat = cachedFriendChats.get(player.getUsername());
			if (chat == null)
				return;
			chat.settings = player.getFriendsIgnores();
		}
	}

	public static void refreshChat(Player player) {
		synchronized (cachedFriendChats) {
			FriendChatsManager chat = cachedFriendChats.get(player.getUsername());
			if (chat == null)
				return;
			chat.refreshChannel();
		}
	}

	public static List<Player> getLootSharingPeople(Player player) {
		if (!player.isToogleLootShare())
			return null;
		FriendChatsManager chat = player.getCurrentFriendChat();
		if (chat == null)
			return null;
		List<Player> players = new ArrayList<Player>();
		for (Player p2 : World.getPlayers()) {
			if (p2 == null)
				continue;
			if (p2.getCurrentFriendChat().getPlayers().contains(player.getDisplayName())) {
				if (p2.isToogleLootShare() && p2.withinDistance(player))
					players.add(p2);
			}
		}
		return players;
	}

	public List<Player> getPlayerList(FriendChatsManager currentChat) {
		List<Player> pl = new ArrayList<Player>();
		for (String fc : currentChat.getPlayers()) {
			if (fc == null)
				continue;
			pl.add(World.getPlayerByDisplayName(fc));
		}
		return pl;
	}

	public static void toogleLootShare(Player player) {
		if (player.getCurrentFriendChat() == null) {
			player.getPackets().sendGameMessage("You need to be in a Friends Chat channel to activate LootShare.");
			player.refreshToogleLootShare();
			return;
		}
		if (!player.getUsername().equals(player.getCurrentFriendChat().getOwnerName())
				&& !player.getCurrentFriendChat().settings.hasRankToLootShare(player.getUsername())) {
			player.getPackets().sendGameMessage("You are not a high enough rank to share loot in this Friends Chat.");
			player.refreshToogleLootShare();
			return;
		}

		long currentTime = Utils.currentTimeMillis();//
		if (player.getAttackedByDelay() + 10000 > currentTime) {
			player.getPackets().sendGameMessage("You cannot toggle this setting until you are out of combat.");
			return;
		}

		player.toogleLootShare();
		if (player.isToogleLootShare())
			player.getPackets().sendGameMessage("<col=005500>LootShare is now active.</col>");
		else
			player.getPackets().sendGameMessage("Lootshare has been deactivated, you are no longer sharing loot.");
	}

	public static void joinChat(String ownerName, Player player, boolean silent) {
		synchronized (cachedFriendChats) {
			if (player.getCurrentFriendChat() != null)
				return;
			if (!silent)
				player.getPackets().sendGameMessage("Attempting to join channel...");

			String formatedName = Utils.formatPlayerNameForProtocol(ownerName);
			FriendChatsManager chat = cachedFriendChats.get(formatedName);

			if (chat == null) {
				Player owner = World.getPlayerByDisplayName(ownerName);
				if (owner == null) {
					if (!AccountCreation.exists(formatedName)) {
						player.getPackets().sendGameMessage("The channel you tried to join does not exist.");
						return;
					}
					owner = AccountCreation.loadPlayer(formatedName);
					if (owner == null) {
						player.getPackets().sendGameMessage("The channel you tried to join does not exist.");
						return;
					}
					owner.setUsername(formatedName);
				}
				FriendsIgnores settings = owner.getFriendsIgnores();
				if (!settings.hasFriendChat()) {
					player.getPackets().sendGameMessage("The channel you tried to join does not exist.");
					return;
				}
				if (!player.getUsername().equals(ownerName) && !settings.hasRankToJoin(player.getUsername())
						&& !player.isDeveloper()) {
					player.getPackets()
							.sendGameMessage("You do not have a enough rank to join this friends chat channel.");
					return;
				}
				if (owner.getFriendsIgnores().getIgnores().contains(player.getUsername())) {
					player.sm("You are not allowed to join this user's channel.");
					return;
				}
				chat = new FriendChatsManager(owner);
				cachedFriendChats.put(chat.owner, chat);
				chat.joinChatNoCheck(player, silent ? true : false);
			} else
				chat.joinChat(player, silent ? true : false);
		}

	}

	/**
	 * Gets the clanWars.
	 * @return The clanWars.
	 */
	public ClanWars getClanWars() {
		return clanWars;
	}

	/**
	 * Sets the clanWars.
	 * @param clanWars The clanWars to set.
	 */
	public void setClanWars(ClanWars clanWars) {
		this.clanWars = clanWars;
	}
}
