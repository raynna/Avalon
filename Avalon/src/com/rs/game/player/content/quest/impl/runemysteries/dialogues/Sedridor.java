package com.rs.game.player.content.quest.impl.runemysteries.dialogues;

import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.actions.combat.Magic;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.game.player.content.quest.impl.runemysteries.RuneMysteries;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;

public class Sedridor extends Dialogue {

	private NPC npc;

	@Override
	public int getNPCID() {
		return 300;
	}

	/**
	 * Teleport option. You need to have completed the Rune Mysteries Quest to use this feature.
	 */

	@Override
	public void start() {
		stageInt = (Integer) parameters[0];
		int progress = player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage();
		if(player.getQuestManager().get(Quests.RUNE_MYSTERIES).getState() == QuestState.COMPLETED) {
			stageInt = -3;
		}
		if(player.getQuestManager().get(Quests.RUNE_MYSTERIES).getState() != QuestState.COMPLETED) {
			if(progress == 0) {
				stageInt = 0;
			}
			if(progress == 2) {
				stageInt = 1;
			}
			if(progress > 2) {
				stageInt = -2;
			}
		}
		switch(stageInt) {
		case -3:
			sendNPCChat(Mood.ASKING, "Welcome adventurer, to the world renowned" +
					" Wizards' Tower. How may I help you?");
			stageInt = 66;
			break;
		case -2:
			sendNPCChat(Mood.ASKING, "Welcome adventurer, to the world renowned" +
					" Wizards' Tower. How may I help you?");
			stageInt = 37;
			break;
		case 0:
			sendNPCChat(Mood.ASKING, "Welcome adventurer, to the world renowned" +
					" Wizards' Tower. How may I help you?");
			if(progress == 0)
				stageInt = 0;
			else
				stageInt = -1;
			break;
		case 1:
			sendNPCChat(Mood.ASKING, "Welcome adventurer, to the world renowned" +
					" Wizards' Tower. How may I help you?");
			stageInt = 23;
			break;
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		int progress = player.getQuestManager().get(Quests.RUNE_MYSTERIES).getStage();
		switch(stageInt) {
		case -1:
			sendOptionsDialogue(DEFAULT, "Nothing thanks, I'm just looking around.", "What are you doing down here?",
					"I'm looking for the head wizard.");
			stageInt = 1;
			break;

		case 0:
			sendOptionsDialogue(DEFAULT, "Nothing thanks, I'm just looking around.", "What are you doing down here?");
			stageInt = 1;
			break;

		case 1:
			if(progress == 0) {
				switch(componentId) {
				case OPTION_1:
					sendNPCChat(Mood.PLAIN_TALKING, "Well, take care adventurer. You stand on the " +
							"ruins of the old destroyed Wizards' Tower." +
							" Strange and powerful magicks lurk here.");
					stageInt = -2;
					break;
				case OPTION_2:
					sendNPCChat(Mood.NORMAL, "That is indeed a good question. Here in the cellar" +
							" of the Wizards' Tower you find the remains of " +
							"the old Wizards' Tower, destroyed by fire");
					stageInt = 2;
					break;
				}
			} else {
				switch (componentId) {
				case OPTION_1:
					sendNPCChat(Mood.PLAIN_TALKING, "Well, take care adventurer. You stand on the " +
							"ruins of the old destroyed Wizards' Tower." +
							" Strange and powerful magicks lurk here.");
					stageInt = -2;
					break;
				case OPTION_2:
					sendNPCChat(Mood.NORMAL, "That is indeed a good question. Here in the cellar" +
							" of the Wizards' Tower you find the remains of " +
							"the old Wizards' Tower, destroyed by fire");
					stageInt = 2;
					break;
				case OPTION_3:
					sendNPCChat(Mood.QUESTIONING, "Oh,  you are, are you?"
							+ " And just why would you be doing that?");
					stageInt = 19;
					break;
				}
			}
			break;

		case 2:
			sendNPCChat(Mood.NORMAL, "many years past by the treachery of the Zamorakians." +
					" Many mysteries were lost, which we try to find once" +
					" more. By building this Tower on the remains of the old,");
			stageInt = 3;
			break;

		case 3:
			sendNPCChat(Mood.NORMAL, "we sought to show the world of our dedication to" +
					" learning the mysteries of Magic. I am here searching" + 
					" through these fragments for knowledge from " + 
					"the artefacts from our past.");
			stageInt = 4;
			break;

		case 4:
			sendPlayerChat(Mood.ASKING, "And have you found anything useful?");
			stageInt = 5;
			break;

		case 5:
			sendNPCChat(Mood.SAD, "Aaaah... that would be telling adventurer. Anything I" +
					" have found I cannot speak freely of, for fear of the" + 
					" treachery of the past might be repeated.");
			stageInt = 6;
			break;

		case 6:
			sendOptionsDialogue(DEFAULT, "Ok, well I'll leave you to it.", "What do you mean treachery?");
			stageInt = 7;
			break;

		case 7:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.HAPPY, "Perhaps I will see you later " + player.getDisplayName() + ".");
				stageInt = 17;
				break;
			case OPTION_2:
				sendNPCChat(Mood.PLAIN_TALKING, "Well, it is a long story from the past... Many years"+
						" ago, this Wizards' Tower was a focus of great " 
						+ "learning, we as mages studied together to try" +
						" and learn the secrets behind");
				stageInt = 8;
				break;
			}
			break;

		case 8:
			sendNPCChat(Mood.PLAIN_TALKING, "the Rune Stones that allow us to use Magic. Who" + 
					" makes them? Where do they come from? How many " + 
					"types are there? What spells can they produce? All" + 
					" these questions and more are still unknown to us,");
			stageInt = 9;
			break;

		case 9:
			sendNPCChat(Mood.PLAIN_TALKING, "but were once known to our ancestors. Legends" + 
					"tell us that in the past the mages who lived" + 
					" here could fashion Rune Stones almost at " + 
					"will, and as many as they desired.");
			stageInt = 10;
			break;

		case 10:
			sendPlayerChat(Mood.QUESTIONING, "But they cannot anymore?");
			stageInt = 11;
			break;

		case 11:
			sendNPCChat(Mood.SAD, "No, unfortunately not. Many years past, the " + 
					"Wizards who follow Zamorak, the god of chaos, burned " + 
					"this Tower to the ground, and all who were inside.");
			stageInt = 12;
			break;

		case 12:
			sendNPCChat(Mood.SAD, "To this day we do not fully know why they did this " + 
					"terrible act, but all our research, all of our greatest " + 
					"magical minds were destroyed in one fell swoop.");
			stageInt = 13;
			break;

		case 13:
			sendNPCChat(Mood.NORMAL, "This is why I spend my time searching through " + 
					"these few remains we have left from the glorious old " + 
					"Tower. I hope someday to find something that will " + 
					"tell us once more of the mysteries of");
			stageInt = 14;
			break;

		case 14:
			sendNPCChat(Mood.NORMAL, "the runes that we use daily, which dwindle in supply " + 
					"with each use. Someday I hope we may once more " + 
					"create our own runes, and the Wizards' Tower will" + 
					"once more be a place of glory!");
			stageInt = 15;
			break;

		case 15:
			sendPlayerChat(Mood.CONFUSED, "Ok, well I'll leave you to it.");
			stageInt = 16;
			break;

		case 16:
			sendNPCChat(Mood.HAPPY, "Perhaps I will see you later " + player.getDisplayName() + ".");
			stageInt = 17;
			break;

		case 17:
			sendPlayerChat(Mood.ASKING, "How did you know my name???");
			stageInt = 18;
			break;

		case 18:
			sendNPCChat(Mood.LAUGHING, "Well, I AM the head wizard here...");
			stageInt = -2;
			break;

		case 19:
			sendPlayerChat(Mood.CONFUSED, "The Duke of Lumbridge sent me to find him. I have"
					+ " this weird talisman he found. He said the head wizard"
					+ " would be very interested in it.");
			stageInt = 20;
			break;

		case 20:
			sendNPCChat(Mood.LAUGHING, "Did he now? HmmmMMMMMmmmmm."
					+ " Well that IS interesting. Hand it over than adventurer,"
					+ " let me see what all the hubbub about it is."
					+ " Just some amulet I'll wager.");
			stageInt = 21;
			break;

		case 21:
			sendOptionsDialogue(DEFAULT, "Ok, here you are.", "No, I'll only give it to the head wizard.");
			stageInt = 22;
			break;

		case 22:
			switch(componentId) {
			case OPTION_1:
				if(player.getInventory().containsOneItem(RuneMysteries.AIR_TALISMAN)) {
					sendHandedItem(15361, "You hand the Talisman to the wizard.");
					player.getInventory().deleteItem(RuneMysteries.AIR_TALISMAN, 1);
					player.getQuestManager().get(Quests.RUNE_MYSTERIES).setStage(2);
					stageInt = 23;
				} else {
					sendPlayerChat(Mood.CONFUSED, "...except I don't have it with me...");
					stageInt = 74;
				}
				break;
			case OPTION_2:
				sendNPCChat(Mood.LAUGHING_HYSTERICALLY, "HA HA HA HA HA! I can tell you are new to this"
						+ " land, for I AM the head wizard! Hand it over and"
						+ " let me have a proper look at it, hmmm?");
				stageInt = 21;
				break;
			}
			break;

		case 23:
			sendNPCChat(Mood.HAPPY, "Wow! This is... incredible!");
			stageInt = 24;
			break;

		case 24:
			sendNPCChat(Mood.HAPPY, "Th-this talisman you brought me...! It is the last piece"
					+ " of the puzzle, I think! Finally! The legacy of our "
					+ "ancestors... it will return to us once more!");
			stageInt = 25;
			break;

		case 25:
			sendNPCChat(Mood.NORMAL, "I need time to study this, " + player.getDisplayName() + ". Can you please"
					+ " do me this task while I study this talisman you have"
					+ " brought me? In the mighty town of Varrock, which");
			stageInt = 26;
			break;

		case 26:
			sendNPCChat(Mood.NORMAL, "is located North East of here, there is a certain shop "
					+ "that sells magical runes. I have in this package all of the"
					+ " research I have done relating to the Rune Stone, and");
			stageInt = 27;
			break;

		case 27:
			sendNPCChat(Mood.NORMAL, "require somebody to take them to the shopkeeper so that"
					+ " he may share my research and offer me his insights. "
					+ "Do this thing for me, and bring back what he gives you,");
			stageInt = 28;
			break;

		case 28:
			sendNPCChat(Mood.NORMAL, "and if my suspicions are correct, I will let you into the"
					+ " knowledge of one of the greatest secrets this world has"
					+ " ever known! A secret so powerful that it destoyed the");
			stageInt = 29;
			break;

		case 29:
			sendNPCChat(Mood.NORMAL, "original Wizards' Tower all of those centuries"
					+ " ago! My research, combined with this mysterious"
					+ " talisman... I cannot believe the answer to"
					+ " the mysteries is so close now!");
			stageInt = 30;
			break;

		case 30:
			sendNPCChat(Mood.NORMAL, "Do this thing for me " + player.getDisplayName() + ". Be rewarded in a "
					+ "way you can never imagine.");
			stageInt = 31;
			break;

		case 31:
			sendOptionsDialogue(DEFAULT, "Yes, certainly.", "No, I'm busy.");
			stageInt = 32;
			break;

		case 32:
			switch(componentId){
			case OPTION_1:
				sendNPCChat(Mood.HAPPY, "Take this package, and head directly North " +
						"from here, through Draynor village, until you reach" +
						" the Barbarian Village. Then head East from there until you reach Varrock.");
				stageInt = 33;
				break;
			case OPTION_2:
				sendNPCChat(Mood.SAD, "As you wish adventurer. I will continue to study this"
						+ " talisman you have brought me. Return here if you find"
						+ " yourself with some spare time to help me.");
				stageInt = -2;
				break;
			}
			break;

		case 33:
			sendNPCChat(Mood.NORMAL, "Once in Varrock, take this package to the owner of the " +
					"rune shop. His name is Aubury. You may find it " +
					"helpful to ask one of the Varrock's citizens for directions,");
			stageInt = 34;
			break;

		case 34:
			sendNPCChat(Mood.NORMAL, "as Varrock can be confusing place for the first time " +
					"visitor. He will give you a special item - bring it back to" +
					" me, and I shall show you the mystery of the runes...");
			stageInt = 35;
			break;

		case 35:
			if(player.getInventory().getFreeSlots() > 1) {
				sendHandedItem(RuneMysteries.RESEARCH_PACKAGE, "The head wizard hands you the package.");
				if(player.getInventory().getFreeSlots() > 0) {
					player.getInventory().addItem(RuneMysteries.RESEARCH_PACKAGE, 1);
				} else {
					World.addGroundItem(new Item(RuneMysteries.RESEARCH_PACKAGE), new WorldTile(player.getX(), player.getY(), player.getPlane()));
				}
				player.getQuestManager().get(Quests.RUNE_MYSTERIES).setStage(3);
				stageInt = 36;
			} else {
				end();
				player.sm("You do not have enough inventory space.");
			}
			break;

		case 36:
			sendNPCChat(Mood.HAPPY, "Best of luck with your quest, " + player.getDisplayName());
			stageInt = -2;
			break;

		case 37:
			sendNPCChat(Mood.ASKING, "Ah, " + player.getDisplayName() + ". How goes your quest? Have you " +
					"delivered that research package to my friend Aubury yet?");
			stageInt = 38;
			break;

		case 38:
			if(progress == 5) {
				sendPlayerChat(Mood.NORMAL, "Yes, I have. He gave me some research notes " +
						"to pass on to you.");
				stageInt = 39;
			} else {
				sendPlayerChat(Mood.SAD, "Not yet...");
				stageInt = 69;
			}
			break;

		case 39:
			sendNPCChat(Mood.ASKING, "May I have his notes then?");
			stageInt = 40;
			break;

		case 40:
			if(!player.getInventory().containsItem(RuneMysteries.RESEARCH_NOTES, 1)) {
				sendPlayerChat(Mood.CONFUSED, "Uh... I kind of... lost them...");
				stageInt = 41;
			} else {
				sendPlayerChat(Mood.NORMAL, "Sure. I have them here.");
				stageInt = 42;
			}
			break;

		case 41:
			sendNPCChat(Mood.ANGRY, "You did? You are extremely careless aren't you? I" +
					" suggest you go and speak to Aubury once more, with" +
					" luck he will have made copies of his research.");
			stageInt = -2;
			break;

		case 42:
			sendNPCChat(Mood.NORMAL, "Well, before you hand them over to me, as you " +
					"have been nothing but truthful with me to this point, " +
					"and I admire that in an adventurer, I will let you " +
					"into the secret of our research.");
			stageInt = 43;
			break;

		case 43:
			sendNPCChat(Mood.PLAIN_TALKING, "Now as you may or may not know, many " +
					"centuries ago, the wizards at this Tower" +
					" learnt the secret of creating Rune Stones, which allowed us to cast Magic very easily.");
			stageInt = 44;
			break;

		case 44:
			sendNPCChat(Mood.PLAIN_TALKING, "When this Tower was burnt down the secret of " +
					"creating runes was lost to us for all time... except it " +
					"wasn't. Some months ago, while searching these ruins " +
					"for information from the old days,");
			stageInt = 45;
			break;

		case 45:
			sendNPCChat(Mood.NORMAL, "I came upon a scroll, almost destroyed, that detailed a" +
					" magical rock deep in the icefields of North, closed " +
					"off from access by anything other than magical means.");
			stageInt = 46;
			break;

		case 46:
			sendNPCChat(Mood.NORMAL, "This rock was called 'Rune Essence' by the " +
					"magicians who studied its powers. Apparently, by simply " +
					"breaking a chunk from it, a Rune Stone could be " +
					"fashioned very quickly and easily at certain");
			stageInt = 47;
			break;

		case 47:
			sendNPCChat(Mood.NORMAL, "element altars that were scattered across the land " +
					"back then. Now, this is an interesting little piece of " +
					"history, but not much use to us as modern wizards " +
					"without access to the Rune Essence,");
			stageInt = 48;
			break;

		case 48:
			sendNPCChat(Mood.NORMAL, "or these elemental altars. This is where you and" +
					" Aubury come into this story. A few weeks back, " +
					"Aubury discovered in a standard delivery of runes " +
					"to his store, a parchment detailing a");
			stageInt = 49;
			break;

		case 49:
			sendNPCChat(Mood.NORMAL, "teleportation spell that he had never come across " +
					"before. To his shock, when cast it took him to a" +
					" strange rock he had never encountered before..." +
					" yet that felt strangely familiar...");
			stageInt = 50;
			break;

		case 50:
			sendNPCChat(Mood.NORMAL, "As I'm sure you have now guessed, he had discovered a " +
					"portal leading to the mythical Rune Essence. As soon as" +
					" he told me of this spell, I saw the importance of his find,");
			stageInt = 51;
			break;

		case 51:
			sendNPCChat(Mood.NORMAL, "for if we could but find the elemental altars spoken" +
					" of in the ancient texts, we would once more be able " +
					"to create runes as our ancestors had done! It would" +
					" be the saviour of the wizards' art!");
			stageInt = 52;
			break;

		case 52:
			sendPlayerChat(Mood.CONFUSED, "I'm still not sure how I fit into" +
					" this little story of yours...");
			stageInt = 53;
			break;

		case 53:
			sendNPCChat(Mood.ASKING, "You haven't guessed? This talisman you brought me..." +
					" it is the key to the elemental altar of air! When" +
					" you hold it next, it will direct you towards");
			stageInt = 54;
			break;

		case 54:
			sendNPCChat(Mood.NORMAL, "the entrance to the long forgotten Air Altar! By" +
					" bringing pieces of the Rune Essence to the air Temple, " +
					"you will be able to fashion your own Air Runes!");
			stageInt = 55;
			break;

		case 55:
			sendNPCChat(Mood.CHEERFULLY_TALK, "And this is not all! by finding other talismans similar " +
					"to this one, you will eventually be able to craft every " +
					"rune that is available on this world! Just");
			stageInt = 56;
			break;

		case 56:
			sendNPCChat(Mood.CHEERFULLY_TALK, "as our ancestors did! I cannot stress enough what a " +
					"find this is! Now, due to the risks involved of letting " +
					"this mighty power fall into the wrong hands");
			stageInt = 57;
			break;

		case 57:
			sendNPCChat(Mood.NORMAL, "I will keep the teleport skill to the Rune Essence " +
					"a closely guarded secret, shared only by myself " +
					"and those Magic users around the world " +
					"whom I trust enough to keep it.");
			stageInt = 58;
			break;

		case 58:
			sendNPCChat(Mood.NORMAL, "This means that if any evil power should discover " +
					"the talismans required to enter the elemental " +
					"temples, we will be able to prevent their access " +
					"to the Rune Essence and prevent");
			stageInt = 59;
			break;

		case 59:
			sendNPCChat(Mood.NORMAL, "tragedy befalling this world. I know not where the " +
					"temples are located, nor do I know where the talismans " +
					"have been scattered to in this land, but I now");
			stageInt = 60;
			break;

		case 60:
			sendNPCChat(Mood.NORMAL, "return your air Talisman to you. Find the Air" +
					" Temple, and you will be able to charge your Rune " +
					"Essences to become Air Runes at will. Any time");
			stageInt = 61;
			break;

		case 61:
			sendNPCChat(Mood.NORMAL, "you wish to visit the Rune Essence, speak to me" +
					" or Aubury and we will open a portal to that " +
					"mystical place for you to visit.");
			stageInt = 62;
			break;

		case 62:
			sendPlayerChat(Mood.ASKING, "So only you and Aubury know the teleport" +
					" spell to the Rune Essence?");
			stageInt = 63;
			break;

		case 63:
			sendNPCChat(Mood.NORMAL, "No... there are others... whom I will tell of your " +
					"authorisation to visit that place. When you speak " +
					"access to that place when asked.");
			stageInt = 64;
			break;

		case 64:
			sendNPCChat(Mood.NORMAL, "Use the Air Talisman to locate the air temple, " +
					"and use any further talismans you find to locate " +
					"the other missing elemental temples. " +
					"Now... my research notes please?");
			stageInt = 65;
			break;

		case 65:
			sendHandedItem(RuneMysteries.RESEARCH_NOTES, "You hand the head wizard the research notes. " +
					"He hands you back the Air Talisman.");
			player.getInventory().deleteItem(RuneMysteries.RESEARCH_NOTES, 1);
			player.getQuestManager().get(Quests.RUNE_MYSTERIES).setStage(6);
			stageInt = -4;
			break;

		case 66:
			sendPlayerChat(Mood.HAPPY, "Hello there.");
			stageInt = 67;
			break;

		case 67:
			sendNPCChat(Mood.HAPPY, "Hello again " + player.getDisplayName() + ". What can I do for you?");
			stageInt = 68;
			break;

		case 68:
			switch(componentId) {
			case OPTION_1:
				sendNPCChat(Mood.PLAIN_TALKING, "Well, take care adventurer. You stand on the " +
						"ruins of the old destroyed Wizards' Tower." +
						" Strange and powerful magicks lurk here.");
				stageInt = -2;
				break;
			case OPTION_2:
				npc.setNextForceTalk(new ForceTalk("Senventior Disthine Molenko!"));
				player.lock(2);
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(
						2909, 4834, 0));
				end();
				break;
			}
			break;

		case 69:
			if(!player.getInventory().containsOneItem(RuneMysteries.RESEARCH_PACKAGE)) {
				sendPlayerChat(Mood.SAD, "...I lost the package you gave me.");
				stageInt = 70;
			} else {
				sendNPCChat(Mood.NORMAL, "Well, please do as soon as possible. Remember: to get " +
						"to Varrock, head due North through Draynor Village, " +
						"around Dranynor Manor, and then head East when");
				stageInt = 73;
			}
			break;

		case 70:
			sendNPCChat(Mood.ANGRY, "You WHAT?");
			stageInt = 71;
			break;

		case 71:
			sendNPCChat(Mood.ANGRY, "Tch, that was really very careless of you. Luckily as" +
					" head wizard I have great powers, and will be able to " +
					"teleport it back here without too much effort.");
			stageInt = 72;
			break;

		case 72:
			if(player.getInventory().getFreeSlots() > 1) {
				sendNPCChat(Mood.NORMAL, "Ok, I have retrieved it. Luckily it doesn't appear to " +
						"have been damaged. Now please take it to Aubury, " +
						"and try not t lose it again.");
				if(player.getInventory().getFreeSlots() > 0) {
					player.getInventory().addItem(RuneMysteries.RESEARCH_PACKAGE, 1);
				} else {
					World.addGroundItem(new Item(RuneMysteries.RESEARCH_PACKAGE), new WorldTile(player.getX(), player.getY(), player.getPlane()));
				}
				stageInt = -2;
			} else {
				end();
				player.sm("You do not have enough inventory space.");
			}
			break;

		case 73:
			sendNPCChat(Mood.NORMAL, "you get to the Barbarian village. The man you seek " +
					"is named Aubury, and he owns the rune shop there. " +
					"It is vital he receives this package.");
			stageInt = -2;
			break;

		case 74:
			sendNPCChat(Mood.CONFUSED, "Hmmm? You are a very odd person. " +
					"Come back when you have found it.");
			stageInt = -2;
			break;

		case -4:
			end();
			player.getQuestManager().get(Quests.RUNE_MYSTERIES).finish();
			break;

		case -2:
			end();
			break;

		default:
			end();
			break;
		}
	}

	@Override
	public void finish() {

	}

}
