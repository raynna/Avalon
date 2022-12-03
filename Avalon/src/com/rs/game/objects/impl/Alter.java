//package com.rs.game.objects.impl;
//
//import com.rs.cache.loaders.ObjectDefinitions;
//import com.rs.game.Animation;
//import com.rs.game.WorldObject;
//import com.rs.game.objects.ObjectScript;
//import com.rs.game.player.Player;
//import com.rs.game.player.Skills;
//
////TODO: not done - This bugs general alters with other packet uses like itemonObject. leave alone for now
//public class Alter extends ObjectScript {
//
//	@Override
//	public Object[] getKeys() {
//		return new Object[] { 409, 6552, 411, 4859};
//	}
//
//	@Override
//	public int getDistance() {
//		return 0;
//	}
//
//	@Override
//	public boolean processObject(Player player, WorldObject object) {
//		final ObjectDefinitions objectDef = object.getDefinitions();
//		int id = object.getId();
//		switch (objectDef.name.toLowerCase()) {
//		case "altar":
//		case "gorilla statue":
//		case "chaos altar":
//			if (objectDef.containsOption(0, "Pray") || objectDef.containsOption(0, "Pray-at")) {
//				final int maxPrayer = player.getSkills().getLevelForXp(Skills.PRAYER) * 10;
//				if (player.getPrayer().getPrayerpoints() < maxPrayer) {
//					player.lock(1);
//					player.getPackets().sendGameMessage("You pray to the gods...", true);
//					player.getPrayer().restorePrayer(maxPrayer);
//					player.getPackets().sendGameMessage("...and recharged your prayer.", true);
//					player.animate(new Animation(645));
//				} else
//					player.getPackets().sendGameMessage("You already have full prayer.");
//				if (id == 6552)
//					player.getDialogueManager().startDialogue("AncientAltar");
//			}
//			break;
//		}
//		return true;
//	}
//}
