package com.rs.net;

import java.text.DecimalFormat;
import java.util.HashMap;

import com.google.common.util.concurrent.FutureCallback;
import com.rs.Launcher;
import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.player.AccountCreation;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.HexColours;
import com.rs.utils.HexColours.Colours;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class DiscordBot {

	private DiscordAPI api;
	private Server server;
	private Channel channel;
	private HashMap<User, Integer> warnings = new HashMap<>();
	final String token = "Njc5MDUyMzczMDk5OTM3ODEz.XkwI5g.DI1fePTlzGEjZKTdBR-myF6T_5g";
	final String serverId = "568087421837770752";
	public static final String COMMANDCHATID = "679362895020032001";
	public static final String YELLCHATID = "679432984423825408";
	
	public Channel getChannelByName(String name) {
		for (Channel channel : getServer().getChannels()) {
			if (channel.getName().toLowerCase().contains(name.toLowerCase()))
				return channel;
		}
		return null;
	}

	public DiscordBot() {
		api = Javacord.getApi(token, true);
		api.connect(new FutureCallback<DiscordAPI>() {
			@Override
			public void onSuccess(DiscordAPI api) throws StringIndexOutOfBoundsException {
				server = api.getServerById(serverId);
				channel = api.getChannelById(serverId);
				Logger.log("DiscordBot", "Connected to Discord: " + server.getName());
				Logger.log("DiscordBot", "Discord members: " + server.getMemberCount());
				api.registerListener((MessageCreateListener) (api1, message) -> {
					if (!message.isPrivateMessage())
						handleWarnings(message);
					if (message.isDeleted() || message.getContent().isEmpty())
						return;
					if (message.getContent().charAt(0) != '!') {
						return;
					}
					String[] cmd = message.getContent().substring(1).split(" ");
					Player player;
					if (message.getChannelReceiver() == Launcher.getDiscordBot().getChannel(COMMANDCHATID)) {
						switch (cmd[0]) {
						case "doublexp":
						case "bonusxp":
							Launcher.getDiscordBot().getChannel(COMMANDCHATID).sendMessage(
									"```Server Bonus Experience is " + (Settings.BONUS_EXP_WEEK_MULTIPLIER > 1.0
											? "active with x" + Settings.BONUS_EXP_WEEK_MULTIPLIER
											: "inactive") + "```");
							break;
						case "doublepoints":
						case "bonusppoints":
						case "bonuspts":
							Launcher.getDiscordBot().getChannel(COMMANDCHATID).sendMessage(
									"```Server Bonus Points is " + (Settings.BONUS_POINTS_WEEK_MULTIPLIER > 1.0
											? "active with x" + Settings.BONUS_POINTS_WEEK_MULTIPLIER
											: "inactive") + "```");
							break;
						case "players":
							StringBuilder players = new StringBuilder();
							players.append("```There " + (World.getPlayers().size() == 1 ? "is" : "are") + " currently "
									+ World.getPlayers().size() + " "
									+ (World.getPlayers().size() == 1 ? "person" : "people") + " playing Avalon!"
									+ "\n");
							for (Player p : World.getPlayers()) {
								if (p == null)
									continue;
								players.append(p.getDisplayName() + ", ");
							}
							Launcher.getDiscordBot().getChannel(COMMANDCHATID)
									.sendMessage(players.replace(players.length() - 2, players.length(), "!") + "```");
							break;
						case "wildy":
						case "wilderness":
						case "wildyplayers":
							players = new StringBuilder();
							players.append("```There " + (World.getPlayersInWilderness() == 1 ? "is" : "are")
									+ " currently " + World.getPlayersInWilderness() + " "
									+ (World.getPlayersInWilderness() == 1 ? "person" : "people")
									+ " in the wilderness!" + "\n");
							Launcher.getDiscordBot().getChannel(COMMANDCHATID).sendMessage(players.toString() + "```");
							break;
						case "pvp":
						case "pvpplayers":
							players = new StringBuilder();
							players.append("```There " + (World.getPlayersInPVP() == 1 ? "is" : "are") + " currently "
									+ World.getPlayersInPVP() + " "
									+ (World.getPlayersInPVP() == 1 ? "person" : "people") + " inside edgeville pvp!"
									+ "\n");
							Launcher.getDiscordBot().getChannel(COMMANDCHATID).sendMessage(players.toString() + "```");
							break;
						case "ffa":
						case "clanwars":
							players = new StringBuilder();
							players.append("```There " + (World.getPlayersInFFA() == 1 ? "is" : "are") + " currently "
									+ World.getPlayersInFFA() + " "
									+ (World.getPlayersInFFA() == 1 ? "person" : "people")
									+ " inside Clan Wars (FFA) portal!" + "\n");
							Launcher.getDiscordBot().getChannel(COMMANDCHATID).sendMessage(players.toString() + "```");
							break;
						case "stats":
							if (cmd.length < 2) {
								Launcher.getDiscordBot().getChannel(COMMANDCHATID)
										.sendMessage("```Use proper formatting: .stats <player_name>```");
								break;
							}
							if (!AccountCreation.exists(cmd[1].toLowerCase())) {
								Launcher.getDiscordBot().getChannel(COMMANDCHATID)
										.sendMessage("```Player " + cmd[1] + " does not exist.```");
								break;
							}
							player = World.getPlayer(cmd[1].toLowerCase());
							if (player == null)
								player = AccountCreation.loadPlayer(cmd[1].toLowerCase());
							String statsMessage = ">>> Current stats for "
									+ (player.getUsername().substring(0, 1).toUpperCase()
											+ player.getUsername().substring(1))
									+ " (" + player.getSkills().getCombatLevel() + ")\n";
							for (int i = Skills.ATTACK; i <= Skills.DUNGEONEERING; i++) {
								statsMessage += (Skills.SKILL_NAME[i] + " - Level: " + player.getSkills().getLevel(i)
										+ ", Exp: " + Utils.getFormattedNumber((int) player.getSkills().getXp(i), ',')
										+ " \n");
							}
							statsMessage += "Total Level: "
									+ Utils.getFormattedNumber(player.getSkills().getTotalLevel(player), ',')
									+ ", Total Exp: "
									+ Utils.getFormattedNumber(player.getSkills().getTotalXP(player), ',');
							Launcher.getDiscordBot().getChannel(COMMANDCHATID).sendMessage(statsMessage);
							break;
						case "kdr":
						case "killdeathratio":
						case "ratio":
							if (cmd.length < 2) {
								Launcher.getDiscordBot().getChannel(COMMANDCHATID)
										.sendMessage("```Use proper formatting: .online <player_name>```");
								break;
							}
							if (!AccountCreation.exists(cmd[1].toLowerCase())) {
								Launcher.getDiscordBot().getChannel(COMMANDCHATID)
										.sendMessage("```Player " + cmd[1] + " does not exist.```");
								break;
							}
							player = World.getPlayer(cmd[1].toLowerCase());
							if (player == null)
								player = AccountCreation.loadPlayer(cmd[1].toLowerCase());
							double kill = player.getKillCount();
							double death = player.getDeathCount();
							double dr = kill / death;
							Launcher.getDiscordBot().getChannel(COMMANDCHATID).sendMessage("```K: " + (int) kill
									+ "\nD: " + (int) death + "\nR: " + new DecimalFormat("##.#").format(dr) + "```");
							break;
						case "online":
							if (cmd.length < 2) {
								Launcher.getDiscordBot().getChannel(COMMANDCHATID)
										.sendMessage("```Use proper formatting: .online <player_name>```");
								break;
							}
							if (!AccountCreation.exists(cmd[1].toLowerCase())) {
								Launcher.getDiscordBot().getChannel(COMMANDCHATID)
										.sendMessage("```Player " + cmd[1] + " does not exist.```");
								break;
							}
							player = World.getPlayer(cmd[1].toLowerCase());
							Launcher.getDiscordBot().getChannel(COMMANDCHATID).sendMessage("```Player " + cmd[1]
									+ " is currently " + (player == null ? "offline." : "online.") + "```");
							break;
						case "cmd":
							StringBuilder commands = new StringBuilder();
							commands.append("\n::players - Shows how many players currently there is playing Avalon.");
							commands.append(
									"\n::pvp - Shows how many players there is currently inside Edgeville PvP.");
							commands.append(
									"\n::wilderness - Shows how many players there is currently inside the Wilderness.");
							commands.append(
									"\n::clanwars - Shows how many players there is currently inside the Free-for-All Clanwars portal.");
							commands.append("\n::kdr <name> - Shows the kill/death ratio for the player chosen.");
							commands.append("\n::stats <name> - Shows the skill levels for the player chosen.");
							commands.append("\n::doublepoints - Shows if server currently have a points boost active.");
							commands.append("\n::doublexp - Shows if server currently have a experience boost active.");
							Launcher.getDiscordBot().getChannel(COMMANDCHATID)
									.sendMessage("This channels commands are: ```" + commands.toString() + "```");
							break;
						default:
							Launcher.getDiscordBot().getChannel(COMMANDCHATID)
									.sendMessage("```Invalid command, use !cmd for a list of commands```");
							break;
						}
					}
					if (message.getChannelReceiver() == Launcher.getDiscordBot().getChannel(YELLCHATID)) {
						switch (cmd[0]) {
						case "yell":
						case "say":
							String data = "";
							for (int i = 1; i < cmd.length; i++) {
								data += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
							}
							World.sendWorldMessage(Colours.PURPLE.getHex() + "[Discord] "
									+ Utils.formatPlayerNameForDisplay(message.getAuthor().getName()) + "#"
									+ message.getAuthor().getDiscriminator() + ": " + HexColours.end()
									+ Utils.fixChatMessage(data), true);
							Launcher.getDiscordBot().getChannel(YELLCHATID)
									.sendMessage("```[Discord -> Ingame] \n" + message.getAuthor().getName() + "#"
											+ message.getAuthor().getDiscriminator() + " " + Utils.fixChatMessage(data)
											+ "```");
							break;
						default:
							Launcher.getDiscordBot().getChannel(YELLCHATID)
									.sendMessage("Invalid command, use !cmd for a list of commands");
							break;
						}
					}
				});
			}

			@Override
			public void onFailure(Throwable t) {
				System.out.println("ERROR 500: FAILURE TO CONNECT TO DISCORD CHANNEL!");
				t.printStackTrace();
			}
		});
	}

	public void handleWarnings(Message message) {
		if (message.getContent().contains("nigg")
				|| (message.getContent().contains("porch") && message.getContent().contains("monkey"))
				|| (message.getContent().contains("gang") && message.getContent().contains("scape"))) {
			warnings.putIfAbsent(message.getAuthor(), 0);
			warnings.put(message.getAuthor(), warnings.get(message.getAuthor()) + 1);
			String text = "You have been issued a warning for the following message:\n" + message + "\n";
			switch (warnings.get(message.getAuthor())) {
			case 1:
				text += "This is a warning, the next offence is a kick, after that you will be banned.";
				break;
			case 2:
				channel.sendMessage(message.getAuthor().getName() + " has been kicked from the server.");
				server.kickUser(message.getAuthor());
				text += "You have been kicked from the channel, one more warning and you will be banned.";
				break;
			case 3:
				channel.sendMessage(message.getAuthor().getName() + " has been banned from the server.");
				server.banUser(message.getAuthor(), 1);
				text += "You have been banned from the channel for the next 24 hours.";
				break;
			}
			message.getAuthor().sendMessage(text);
			for (User user : server.getMembers())
				if (user.getRoles(server).contains(server.getRoleById("250050192227696641")))
					user.sendMessage(
							message.getAuthor().getName() + " has been issued a warning for the following message:\n"
									+ message + "\n" + "They now have " + warnings.get(message.getAuthor()) + " warning"
									+ (warnings.get(message.getAuthor()) == 1 ? "" : "s") + ".");
		}
	}

	public Server getServer() {
		return server;
	}

	public Channel getChannel() {
		return channel;
	}

	public Channel getChannel(String channelId) {
		return api.getChannelById(channelId);
	}

}
