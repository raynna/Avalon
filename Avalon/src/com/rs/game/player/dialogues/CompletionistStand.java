package com.rs.game.player.dialogues;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.NPC.AchievementKills;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class CompletionistStand extends Dialogue {

	static final int[] randomNPC = { 3373, 3373, 3373, 3373 };
	int capeId;
	NPC guard;

	/**
	 * This dialogue is sent upon the player clicking on object id: 2562.
	 */

	@Override
	public void start() {
		capeId = (int) parameters[0];
		guard = World.getNPCs().get(3373);
		sendNPCDialogue(3373, 9827, "Hey! Get your hands off that!");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			player.addWalkSteps(3089, 3485, 1);
			player.lock();
			WorldTasksManager.schedule(new WorldTask() {
				int phase = 0;

				@Override
				public void run() {
					switch (phase) {
					case 0:
						player.addWalkSteps(3089, 3484, 1);
						player.unlock();
						break;
					}
					phase++;
				}
			}, 2, 2);
			sendPlayerDialogue(9827, "Sorry, I was just wondering what it was.");
			stage = 1;
			break;
		case 1:
			sendNPCDialogue(3373, 9827, "A good question. It's perhaps one of more mysterious exibits you know of.");
			stage = 2;
			break;
		case 2:
			sendNPCDialogue(3373, 9827,
					"It was only discovered recently, but I have"
							+ " dated it back almost as far as I can track. It turns out"
							+ " this cape might be the product, or at least involved with a"
							+ " Second Age mage by the name of Dahmaroc.");
			stage = 3;
			break;
		case 3:
			sendPlayerDialogue(9827, "Dahmaroc?");
			stage = 4;
			break;
		case 4:
			sendNPCDialogue(3373, 9827, "Indeed! " + "He was a powerful mage back in the Second Age. Very skill-"
					+ " focused too, so this cape was a particular find.");
			stage = 5;
			break;
		case 5:
			sendPlayerDialogue(9827, "What do you mean by that?");
			stage = 6;
			break;
		case 6:
			sendNPCDialogue(3373, 9827, "Well, generally, his magical abilities were focused away"
					+ " from combat - it seems this cape is under the mose" + " powerful enchantment I've ever seen.");
			stage = 7;
			break;
		case 7:
			sendPlayerDialogue(9827, "This cape is enchanted?");
			stage = 8;
			break;
		case 8:
			sendNPCDialogue(3373, 9827, "Yes, and more than we can grasp. It physiclly repels anyone who tries to touch"
					+ " it. We had quite a hassle getting it up here.");
			stage = 9;
			break;
		case 9:
			sendPlayerDialogue(9827, "So no one has worn this cape?");
			stage = 10;
			break;
		case 10:
			sendNPCDialogue(3373, 9827,
					"No one can! It's like it has a mind of it's own juding those who try as unworthy.");
			stage = 11;
			break;
		case 11:
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Can I try?", "How interesting.");
			stage = 12;
			break;
		case 12:
			switch (componentId) {
			case OPTION_1:
				stage = 13;
				sendPlayerDialogue(9827, "Can I try?");
				break;
			case OPTION_2:
			default:
				stage = 30;
				sendPlayerDialogue(9827, "How interesting.");
				break;
			}
			break;

		case 13:
			sendNPCDialogue(3373, 9827,
					"I said no touching... but, if you get close enough, I'm sure you'll the enchantment affects"
							+ " ... Good luck!");
			stage = 14;
			break;
		case 14:
			if (!player.hasCompletionistRequirements()) {
				player.getInterfaceManager().closeChatBoxInterface();
				player.lock();
				player.addWalkSteps(3088, 3485, 1);
				WorldTasksManager.schedule(new WorldTask() {
					int phase = 0;

					@Override
					public void run() {
						switch (phase) {
						case 0:
							player.addWalkSteps(3088, 3484, 1);
							break;
						case 1:
							player.animate(new Animation(857));
							break;
						case 2:
							player.animate(new Animation(915));
							break;
						case 3:
							player.animate(new Animation(857));
							break;
						case 4:
							player.gfx(new Graphics(86));
							player.getAppearence().transformIntoNPC(randomNPC[Utils.random(randomNPC.length - 1)]);
							break;
						case 5:
							player.setNextForceTalk(new ForceTalk("..What."));
							break;
						case 6:
							player.gfx(new Graphics(86));
							player.getAppearence().transformIntoNPC(-1);
							player.animate(new Animation(10070));
							player.setNextForceMovement(new ForceMovement(new WorldTile(3088, 3487, 0), 1, 0));
							break;
						case 7:
							player.setNextWorldTile(new WorldTile(3088, 3487, 0));
							sendNPCDialogue(3373, 9827, "Looks like Dahmaroc had a sense of humour!");
							player.unlock();
							sendRequirementMessages();
							break;
						}
						phase++;
					}
				}, 2, 2);
				stage = 15;
			} else {
				player.getInterfaceManager().closeChatBoxInterface();
				player.lock();
				player.addWalkSteps(3088, 3485, 1);
				WorldTasksManager.schedule(new WorldTask() {
					int phase = 0;

					@Override
					public void run() {
						switch (phase) {
						case 0:
							player.addWalkSteps(3088, 3485, 1);
							break;
						case 1:
							player.animate(new Animation(857));
							break;
						case 2:
							player.animate(new Animation(915));
							break;
						case 3:
							player.animate(new Animation(857));
							break;
						case 4:
							player.unlock();
							sendNPCDialogue(3373, 9827, "I've not seen a reaction like that! I think this cape is"
									+ " identifying it's true owner.");
							stage = 16;
							break;
						}
						phase++;
					}
				}, 2, 2);
			}
			break;
		case 15:
			player.unlock();
			sendNPCDialogue(3373, 9827,
					"Sorry, it doesn't look like you are worthy of this cape. At" + " least not yet...");
			stage = -2;
			break;
		case 16:
			sendPlayerDialogue(9827, "You mean I can have it?");
			stage = 17;
			break;
		case 17:
			sendNPCDialogue(3373, 9827, "Well, yes, but... I can't just let you take the exhibit. You"
					+ " may be the true owner, but it is one of the most" + " treasured items we have here.");
			stage = 18;
			break;
		case 18:
			sendNPCDialogue(3373, 9827, "I suppose if the museum were compensated, perhaps I"
					+ " could let you take it... How does 5,000,000 coins" + " sound?");
			stage = 19;
			break;
		case 19:
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "That sounds fair.", "That sounds like a joke!");
			stage = 20;
			break;
		case 20:
			switch (componentId) {
			case OPTION_1:
				sendPlayerDialogue(9827, "That sounds fair.");
				stage = 21;
				break;
			case OPTION_2:
				sendPlayerDialogue(9827, "That sounds like a joke!");
				stage = -2;
				break;
			}
			break;
		case 21:
			if (!player.hasMoney(5000000)) {
				sendNPCDialogue(3373, 9827,
						"Alright, well come talk to me again when you have enough gold and I'll let you have it.");
				stage = -2;
			} else {
				if (player.getInventory().getFreeSlots() < 2) {
					sendNPCDialogue(3373, 9827,
							"Sorry, but your inventory seems to be full, please come back " + "with more space.");
					stage = -2;
				} else {
					player.takeMoney(5000000);
					sendEntityDialogue(SEND_ITEM_DIALOGUE,
							new String[] { ItemDefinitions.getItemDefinitions(20769).name,
									"The guard hands over the Completionist cape" },
							IS_ITEM, 20769, -1);
					player.getInventory().addItem(20769, 1);
					player.getInventory().addItem(20770, 1);
					if (!player.boughtCompCape) {
						player.boughtCompCape = true;
						World.sendWorldMessage("<img=6><col=ff0000>News: " + player.getDisplayName()
								+ " has just achieved Completionist cape.", false);
					}
					stage = -2;
				}
			}
			break;
		case 30:
			sendNPCDialogue(3373, 9827, "Indeed!");
			stage = -2;
			break;
		case -2:
			end();
			break;
		}
	}

	public void getKillcount(String name) {
		int totalKills = 0;
		for (AchievementKills achievement : AchievementKills.values()) {
			if (achievement.name().replace("_", " ").replace("'", "").equalsIgnoreCase(name)) {
				totalKills = (player.getBossKillcount().get(name) != null
						? player.getBossKillcount().get(name).intValue() : 0);
				if (totalKills < achievement.getKills()) {
					player.getPackets().sendGameMessage("You must have killed at least " + achievement.getKills() + " "
							+ name + ", " + (achievement.getKills() - totalKills) + " left.");
				}
			}
		}
	}

	public void sendRequirementMessages() {
		if (!player.hasCompletionistStatRequirements()) {
			player.getPackets().sendGameMessage("You need level 99 in the following: ");
			for (int skill = 0; skill < 19; skill++) {
				if (player.getSkills().getLevelForXp(skill) >= 99)
					continue;
				player.getPackets().sendGameMessage(player.getSkills().getSkillName(skill));
			}
			if (player.getSkills().getLevelForXp(20) < 99)
				player.getPackets().sendGameMessage(player.getSkills().getSkillName(20));
			if (player.getSkills().getLevelForXp(23) < 99)
				player.getPackets().sendGameMessage(player.getSkills().getSkillName(23));
			if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) != 120) {
				player.getPackets().sendGameMessage("You need level 120 in the following: ");
				player.getPackets().sendGameMessage(player.getSkills().getSkillName(24));
			}
		}
		if (!player.getTaskManager().hasCompletedAllTasks())
			player.sm("You must have completed all Tasks.");
		if (!player.isCompletedFightKiln())
			player.getPackets().sendGameMessage("You must have completed the Fight kiln.");
		if (!player.isCompletedFightCaves())
			player.getPackets().sendGameMessage("You must have completed the Fight caves.");
		getKillcount("Corporeal Beast");
		getKillcount("Kree'arra");
		getKillcount("K'ril_Tsutsaroth");
		getKillcount("General Graardor");
		getKillcount("Commander Zilyana");
		getKillcount("King Black Dragon");
		getKillcount("Dagannoth Rex");
		getKillcount("Dagannoth Prime");
		getKillcount("Dagannoth Supreme");
	}

	@Override
	public void finish() {
		// player.setNextFaceWorldTile(new WorldTile(-1));

	}
}