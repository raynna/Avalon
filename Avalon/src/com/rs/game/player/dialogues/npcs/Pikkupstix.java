package com.rs.game.player.dialogues.npcs;

import com.rs.game.player.content.customshops.CustomStoreData;
import com.rs.game.player.dialogues.Dialogue;

public class Pikkupstix extends Dialogue {
	
	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, LISTENS_THEN_LAUGHS, "Hello there! Welcome to my humble abode. How can I help", "you?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			sendOptionsDialogue(DEFAULT, "So, what's Summoning all about, then?", "Can I buy some Summoning supplies?", "Please tell me about skillcapes.");
			//sendOptionsDialogue(DEFAULT, "I have a question about Summoning...", "Do you have a quest for me?");
			stage = 1;
			break;
		case 0:
			switch(componentId) {
			case OPTION_1:
				sendOptionsDialogue(DEFAULT, "So, what's Summoning all about, then?", "Can I buy some Summoning supplies?", "Please tell me about skillcapes.");
				stage = 1;
				break;
			case OPTION_2:
				end();
				break;
			}
			break;
		case 1:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, PLAIN_TALKING, "In general? Or did you have a specific topic in mind?");
				stage = 2;
				break;
			case OPTION_2:
				sendPlayerDialogue(NORMAL, "Can I buy some summoning supplies, please?");
				stage = 107;
				break;
			case OPTION_3:
				sendPlayerDialogue(NORMAL, "Please tell me about skillcapes.");
				stage = 109;
				break;
			}
			break;
		case 2:
			sendOptionsDialogue(DEFAULT, "In general.", "Tell me about summoning familiars.", "Tell me about special moves.", "Tell me about charged items.", "Tell me about pets.");
			stage = 3;
			break;
		case 3:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Well, you already know about Summoning in general;", "otherwise, we would not be having this conversation!");
				stage = 4;
				break;
			case OPTION_2:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Summoned familiars are at the very core of Summoning.", "Each familiar is different, and the more powerful the", "summoner, the more powerful the familiar they can", "summon.");
				stage = 28;
				break;
			case OPTION_3:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Well, if a Summoning pouch is split apart at an obelisk,", "then the energy it contained will reconstitute itself -", "transform - into a scroll. This scroll can then be used to", "make your familiar perform its special move.");
				stage = 51;
				break;
			case OPTION_4:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Charged items are very simple to create, if you have the", "correct Crafting level.");
				stage = 70;
				break;
			case OPTION_5:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Well, these are not really an element of the skill, as such,", "but more like a side-effect of training.");
				stage = 90;
				break;
			}
			break;
		case 4:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Effectively, the skill can be broken into three main parts:", "summoned familiars, charged items and pets.");
			stage = 5;
			break;
		case 5:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Summoned familiars are spiritual animals that can be", "called to you from the spirit plane, to serve you for a", "period of time.");
			stage = 6;
			break;
		case 6:
			sendNPCDialogue(npcId, PLAIN_TALKING, "These animals can also perform a special move, which is", "specific to the species. For example, a spirit wolf can", "perform the Howl special move if you are holding the", "correct Howl scroll.");
			stage = 7;
			break;
		case 7:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Charged items contain summoning power, but in a stable", "form.");
			stage = 8;
			break;
		case 8:
			sendNPCDialogue(npcId, PLAIN_TALKING, "They can be used to physically store any scrolls you have.");
			stage = 9;
			break;
		case 9:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Those are the main effects of Summoning.");
			stage = 10;
			break;
		case 10:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Are you still following me?");
			stage = 11;
			break;
		case 11:
			sendPlayerDialogue(NORMAL, "Yes, I think so.");
			stage = 12;
			break;
		case 12:
			sendNPCDialogue(npcId, PLAIN_TALKING, "I can supply you with pouches and spirit shards, but you", "will have to bring your own charms and secondary", "ingredients.");
			stage = 13;
			break;
		case 13:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Many creatures drop charms when you kill them in", "combat.");
			stage = 14;
			break;
		case 14:
			sendNPCDialogue(npcId, PLAIN_TALKING, "There are many different charms in the world: gold, blue,", "green, crimson and even some specialist ones like obsidian.");
			stage = 15;
			break;
		case 15:
			sendPlayerDialogue(NORMAL, "I'll be sure to keep my eyes open for them.");
			stage = 16;
			break;
		case 16:
			sendNPCDialogue(npcId, PLAIN_TALKING, "The best way to raise your Summoning level is to infuse", "pouches.");
			stage = 17;
			break;
		case 17:
			sendNPCDialogue(npcId, PLAIN_TALKING, "You will still learn a little from summoning a familiar, as", "well as using a scroll and transforming the summoning", "pouches into scrolls.");
			stage = 18;
			break;
		case 18:
			sendNPCDialogue(npcId, PLAIN_TALKING, "But nothing is more potent than infusing a spirit into a", "Summoning pouch in the first place.");
			stage = 19;
			break;
		case 19:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Do you understand?");
			stage = 20;
			break;
		case 20:
			sendPlayerDialogue(HAPPY, "Sure!");
			stage = 21;
			break;
		case 21:
			sendNPCDialogue(npcId, HAPPY, "Good!");
			stage = 22;
			break;
		case 22:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Where was I?");
			stage = 23;
			break;
		case 23:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Ah, yes, the last part of Summoning: the pets. The more", "you practice the skill, the more you will comprehend the", "natural world around you.");
			stage = 24;
			break;
		case 24:
			sendNPCDialogue(npcId, PLAIN_TALKING, "This is reflected in your increased ability to raise animals", "as pets. It takes a skilled summoner to be able to raise", "some of FrostBlade's more exotic animals, such as the lizards", "of Karamja, or even dragons!");
			stage = 25;
			break;
		case 25:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Now that I've given you this overview, do you want to", "know about anything specific?");
			stage = 26;
			break;
		case 26:
			sendOptionsDialogue(DEFAULT, "Tell me about summoning familiars.", "Tell me about special moves.", "Tell me about charged items.", "Tell me about pets.");
			stage = 27;
			break;
		case 27:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Summoned familiars are at the very core of Summoning.", "Each familiar is different, and the more powerful the", "summoner, the more powerful the familiar they can", "summon.");
				stage = 28;
				break;
			case OPTION_2:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Well, if a Summoning pouch is split apart at an obelisk,", "then the energy it contained will reconstitute itself -", "transform - into a scroll. This scroll can then be used to", "make your familiar perform its special move.");
				stage = 51;
				break;
			case OPTION_3:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Charged items are very simple to create, if you have the", "correct Crafting level.");
				stage = 70;
				break;
			case OPTION_4:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Well, these are not really an element of the skill, as such,", "but more like a side-effect of training.");
				stage = 90;
				break;
			}
			break;
		case 28:
			sendNPCDialogue(npcId, PLAIN_TALKING, "The animals themselves are not real, in the sense that", "you or I are real; they are spirits drawn from the spirit", "plane.");
			stage = 29;
			break;
		case 29:
			sendNPCDialogue(npcId, PLAIN_TALKING, "As a result, they have powers that the animals they", "resemble do not.");
			stage = 30;
			break;
		case 30:
			sendNPCDialogue(npcId, PLAIN_TALKING, "They cannot remain in this world indefinitely; they require", "a constant supply of energy to maintain their link to the", "spirit plane.");
			stage = 31;
			break;
		case 31:
			sendNPCDialogue(npcId, PLAIN_TALKING, "This energy is drained from your Summoning skill points.");
			stage = 32;
			break;
		case 32:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Your level of the Summoning skill is not drained, and you", "can refresh your points back up to your maximum level at", "an obelisk whenever you wish.");
			stage = 33;
			break;
		case 33:
			sendPlayerDialogue(NORMAL, "So, my Summoning skill points are like food to them?");
			stage = 34;
			break;
		case 34:
			sendNPCDialogue(npcId, HAPPY, "Yes, that is an appropriate analogy.");
			stage = 35;
			break;
		case 35:
			sendNPCDialogue(npcId, PLAIN_TALKING, "The more powerful the familiar, the more it must 'feed'", "and the more 'food' it will need to be satisfied.");
			stage = 36;
			break;
		case 36:
			sendNPCDialogue(npcId, PLAIN_TALKING, "As a result, only the most powerful summoners are able", "to maintain a link from a familiar to the spirit plane, since", "they are able to provide more 'food' with each Summoning", "level they gain.");
			stage = 37;
			break;
		case 37:
			sendPlayerDialogue(NORMAL, "I'm starting to get a little hungry now.");
			stage = 38;
			break;
		case 38:
			sendNPCDialogue(npcId, PLAIN_TALKING, "As you gain mastery of the skill you will be able to have", "familiars out for the full time they exist. And still have", "some points over to re-summon them afterwards, if you", "wish.");
			stage = 39;
			break;
		case 39:
			sendNPCDialogue(npcId, PLAIN_TALKING, "This is because you are able to feed them the energy they", "need and have leftovers to spare!");
			stage = 40;
			break;
		case 40:
			sendPlayerDialogue(HAPPY, "Great!");
			stage = 41;
			break;
		case 41:
			sendPlayerDialogue(NORMAL, "So, what can these familiars do?");
			stage = 42;
			break;
		case 42:
			sendNPCDialogue(npcId, HAPPY, "Why not ask me to count every blade of grass on a lawn?");
			stage = 43;
			break;
		case 43:
			sendNPCDialogue(npcId, PLAIN_TALKING, "The familiars each have unique abilities and even the most", "experienced summoner will not know them all.");
			stage = 44;
			break;
		case 44:
			sendPlayerDialogue(NORMAL, "Well, can you give me some hints?");
			stage = 45;
			break;
		case 45:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Well, some familiars are able to fight with you in combat.", "They will keep an eye out to see if you are fighting and", "will intervene, if they can.");
			stage = 46;
			break;
		case 46:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Other familiars will not fight, for various reasons, but", "they may provide bonuses in other tasks. Some will even", "carry your items for you, if you need them to.");
			stage = 47;
			break;
		case 47:
			sendPlayerDialogue(HAPPY, "Amazing!");
			stage = 48;
			break;
		case 48:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Would you like to know anything else about Summoning?");
			stage = 49;
			break;
		case 49:
			sendOptionsDialogue(DEFAULT, "Tell me about special moves.", "Tell me about charged items.", "Tell me about pets.");
			stage = 50;
			break;
		case 50:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Well, if a Summoning pouch is split apart at an obelisk,", "then the energy it contained will reconstitute itself -", "transform - into a scroll. This scroll can then be used to", "make your familiar perform its special move.");
				stage = 51;
				break;
			case OPTION_2:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Charged items are very simple to create, if you have the", "correct Crafting level.");
				stage = 70;
				break;
			case OPTION_3:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Well, these are not really an element of the skill, as such,", "but more like a side-effect of training.");
				stage = 90;
				break;
			}
			break;
		case 51:
			sendNPCDialogue(npcId, PLAIN_TALKING, "For example, spirit wolves are able to Howl, scaring away", "an opponent for a short period of time.");
			stage = 52;
			break;
		case 52:
			sendPlayerDialogue(NORMAL, "Or longer, in the case of that giant wolpertinger.");
			stage = 53;
			break;
		case 53:
			sendNPCDialogue(npcId, LISTENS_THEN_LAUGHS, "Indeed!");
			stage = 54;
			break;
		case 54:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Well, each familiar has its own special move, and you can", "only use scrolls on the familiar that it applies to.");
			stage = 55;
			break;
		case 55:
			sendNPCDialogue(npcId, PLAIN_TALKING, "For example, a spirit wolf will only look at you oddly if you", "wish it to perform a dreadfowl special move.");
			stage = 56;
			break;
		case 56:
			sendPlayerDialogue(NORMAL, "So, what sort of special moves are there?");
			stage = 57;
			break;
		case 57:
			sendNPCDialogue(npcId, PLAIN_TALKING, "The special moves are as varied as the familiars", "themselves.");
			stage = 58;
			break;
		case 58:
			sendNPCDialogue(npcId, PLAIN_TALKING, "A good rule of thumb is that if a familiar helps you in", "combat, then its special move is likely to damage", "attackers when you use a scroll.");
			stage = 59;
			break;
		case 59:
			sendNPCDialogue(npcId, PLAIN_TALKING, "On the other hand, if a familiar is more peaceful by", "nature, then its special move might heal or provide means", "to train your other skills - that sort of thing.");
			stage = 60;
			break;
		case 60:
			sendPlayerDialogue(NORMAL, "Are the familiar's special moves similar to its normal", "abilities?");
			stage = 61;
			break;
		case 61:
			sendNPCDialogue(npcId, PLAIN_TALKING, "In general, no. But some familiars' special moves can be a", "more powerful version of their normal ability.");
			stage = 62;
			break;
		case 62:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Take the spirit wolf, for example. Its special move and its", "normal ability are essentially the same.");
			stage = 63;
			break;
		case 63:
			sendNPCDialogue(npcId, PLAIN_TALKING, "However, the special move can be used on any nearby", "opponent, while the normal ability only works on those", "opponents you are currently fighting.");
			stage = 64;
			break;
		case 64:
			sendNPCDialogue(npcId, PLAIN_TALKING, "If your familiar is fighting with you, it will use its normal", "ability whenever it can.");
			stage = 65;
			break;
		case 65:
			sendNPCDialogue(npcId, PLAIN_TALKING, "It won't however, use its special move unless you have", "expressly asked it to, by activating a scroll.");
			stage = 66;
			break;
		case 66:
			sendPlayerDialogue(NORMAL, "I see. Thanks for the information.");
			stage = 67;
			break;
		case 67:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Would you like to know anything else about Summoning?");
			stage = 68;
			break;
		case 68:
			sendOptionsDialogue(DEFAULT, "Tell me about Summoning familiars.", "Tell me about charged items.", "Tell me about pets.");
			stage = 69;
			break;
		case 69:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Summoned familiars are at the very core of Summoning.", "Each familiar is different, and the more powerful the", "summoner, the more powerful the familiar they can", "summon.");
				stage = 28;
				break;
			case OPTION_2:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Charged items are very simple to create, if you have the", "correct Crafting level.");
				stage = 70;
				break;
			case OPTION_3:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Well, these are not really an element of the skill, as such,", "but more like a side-effect of training.");
				stage = 90;
				break;
			}
			break;
		case 70:
			sendNPCDialogue(npcId, PLAIN_TALKING, "If not, then you can simply buy a set of antlers or a lizard", "skull from me. You can take an item that can be charged,", "usually a headdress, and attach scrolls to them.");
			stage = 71;
			break;
		case 71:
			sendNPCDialogue(npcId, PLAIN_TALKING, "The scrolls are then stored in the headdress. Aggressive", "combat scrolls, however, are the only ones that can be", "used in this way.");
			stage = 72;
			break;
		case 72:
			sendPlayerDialogue(NORMAL, "Why is that?");
			stage = 73;
			break;
		case 73:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Well, a scroll in a charged item will only be activated when", "it is hit with enough power.");
			stage = 74;
			break;
		case 74:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Since this can only happen from the impact of combat, it", "makes sense that only combat-based scrolls can be stored", "here. The activated scroll will then be sensed by your", "familiar, and it will defend you.");
			stage = 75;
			break;
		case 75:
			sendPlayerDialogue(NORMAL, "What sort of impact are we talking about here?");
			stage = 76;
			break;
		case 76:
			sendNPCDialogue(npcId, LISTENS_THEN_LAUGHS, "Oh, nothing too serious: stabbings, explosions, arrows to", "the torso - that sort of thing.");
			stage = 77;
			break;
		case 77:
			sendPlayerDialogue(WORRIED, "What?");
			stage = 78;
			break;
		case 78:
			sendNPCDialogue(npcId, LISTENS_THEN_LAUGHS, "Oh, come now; a strapping lad like you will barely feel it.", "Your opponent, however, will certainly feel the power of", "your retaliation!");
			stage = 79;
			break;
		case 79:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Anyway, one impact is not enough; it will take a series of", "strikes to activate the power in the charged item.");
			stage = 80;
			break;
		case 80:
			sendPlayerDialogue(NORMAL, "So, will this allow familiars to perform the special moves", "of other familiars? Or will it work if I have no familiar", "out?");
			stage = 81;
			break;
		case 81:
			sendNPCDialogue(npcId, PLAIN_TALKING, "No. As with the normal method of instigatin a special", "move, the correct scroll is required for the correct", "familiar.");
			stage = 82;
			break;
		case 82:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Even if some dreadfowl scroll power was hanging in the", "air, a spirit wofl would not be able to do anything with it.");
			stage = 83;
			break;
		case 83:
			sendPlayerDialogue(NORMAL, "What happens when the item runs out of charge? Do they", "disintegrate or something?");
			stage = 84;
			break;
		case 84:
			sendNPCDialogue(npcId, LISTENS_THEN_LAUGHS, "Only if you've made them shoddily!");
			stage = 85;
			break;
		case 85:
			sendNPCDialogue(npcId, PLAIN_TALKING, "The items should be useful for a long time. When you run", "out of charges, you simply need to attach more scrolls.");
			stage = 86;
			break;
		case 86:
			sendPlayerDialogue(HAPPY, "That's a relief.");
			stage = 87;
			break;
		case 87:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Would you like to know anything else about Summoning?");
			stage = 88;
			break;
		case 88:
			sendOptionsDialogue(DEFAULT, "Tell me about Summoning familiars.", "Tell me about special moves.", "Tell me about pets.");
			stage = 89;
			break;
		case 89:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Summoned familiars are at the very core of Summoning.", "Each familiar is different, and the more powerful the", "summoner, the more powerful the familiar they can", "summon.");
				stage = 28;
				break;
			case OPTION_2:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Well, if a Summoning pouch is split apart at an obelisk,", "then the energy it contained will reconstitute itself -", "transform - into a scroll. This scroll can then be used to", "make your familiar perform its special move.");
				stage = 51;
				break;
			case OPTION_3:
				sendNPCDialogue(npcId, PLAIN_TALKING, "Well, these are not really an element of the skill, as such,", "but more like a side-effect of training.");
				stage = 90;
				break;
			}
			break;
		case 90:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Essentially, the higher the Summoning level an adventurer", "has, the more they become in tune with nature.");
			stage = 91;
			break;
		case 91:
			sendNPCDialogue(npcId, PLAIN_TALKING, "As a result, they can approach animals that would", "otherwise run away from them.");
			stage = 92;
			break;
		case 92:
			sendNPCDialogue(npcId, PLAIN_TALKING, "This means that you will be able to befriend and raise various animals from around the world.");
			stage = 93;
			break;
		case 93:
			sendPlayerDialogue(NORMAL, "So, what will I need to do to raise these pets?");
			stage = 94;
			break;
		case 94:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Oh, most of them will be quite content to follow you", "around. You just have to feed them and make sure they", "are generally healthy.");
			stage = 95;
			break;
		case 95:
			sendNPCDialogue(npcId, PLAIN_TALKING, "It is the goal of every summoner to be able to raise a", "dragon, yet few have been able to perform this feat.");
			stage = 96;
			break;
		case 96:
			sendPlayerDialogue(HAPPY, "Wow! Imagine riding around on a dragon - even fighting", "with it!");
			stage = 97;
			break;
		case 97:
			sendNPCDialogue(npcId, MILDLY_ANGRY, "That's not the right attitude at all!");
			stage = 98;
			break;
		case 98:
			sendPlayerDialogue(WORRIED, "What?");
			stage = 99;
			break;
		case 99:
			sendNPCDialogue(npcId, MILDLY_ANGRY, "When you raise a pet it becomes your responsibility - your", "friend.");
			stage = 100;
			break;
		case 100:
			sendNPCDialogue(npcId, PLAIN_TALKING, "You can't ride into battle on your friend, or make them", "fight for you. These are not spiritual familiars: if they die,", "they die!");
			stage = 101;
			break;
		case 101:
			sendPlayerDialogue(SAD, "I didn't realise.");
			stage = 102;
			break;
		case 102:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Well, now you know.");
			stage = 103;
			break;
		case 103:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Pets do not fight for you, cast spells or have strange", "abilities, and you may certainly not ride around on them.", "They are animals you raise from birth to follow you", "wherever you go.");
			stage = 104;
			break;
		case 104:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Remember that and take good care of them.");
			stage = 105;
			break;
		case 105:
			sendPlayerDialogue(HAPPY, "I will!");
			stage = 106;
			break;
		case 106:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Would you like to know anything else about Summoning?");
			stage = 88;
			break;
		case 107:
			sendNPCDialogue(npcId, HAPPY, "If you like! It's good to see you training.");
			stage = 108;
			break;
		case 108:
			player.getCustomStore().sendInterface(player, 0, CustomStoreData.SUMMONING);
			end();
			break;
		case 109:
			sendNPCDialogue(npcId, PLAIN_TALKING, "Of course. Skillcapes are a symbol of achievement. Only", "people who have mastered a skill and reached level 99 can", "get their hands on them and gain the benefits they carry.", "Is there something else I can help you with, perhaps?");
			stage = 110;
			break;
		case 110:
			sendOptionsDialogue(DEFAULT, "So what's it all about then?", "Can I buy some summoning supplies?");
			stage = 111;
			break;
		case 111:
			switch(componentId) {
			case OPTION_1:
				sendNPCDialogue(npcId, PLAIN_TALKING, "In general? Or did you have a specific topic in mind?");
				stage = 2;
				break;
			case OPTION_2:
				sendPlayerDialogue(NORMAL, "Can I buy some summoning supplies, please?");
				stage = 107;
				break;
			}
			break;
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}

}
