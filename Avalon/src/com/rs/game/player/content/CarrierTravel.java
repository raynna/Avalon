package com.rs.game.player.content;

import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;

/*
 * Credits goes to Matrix 3.
 */

public class CarrierTravel {

	private static final int TRAVEL_INTERFACE = 299, CHARTER_INTERFACE = 95, GLIDER_INTEFACE = 138;
	private static final int[] REGIONS = new int[] { 8496, 14646, 11061, 11823, 11825, 11058, 10545, 12081, 14637, -1,
			10284 };

	public enum Carrier {

		/**
		 * Carter Ships
		 */
		Port_Tyras(new int[] { -1, 3200, 3200, 3200, 3200, 3200, 3200, 1600, 3200, -1, 3200 },
				new WorldTile(2142, 3122, 0)),

		Port_Phasmatys(new int[] { 3200, -1, 3500, -1, 2900, 2900, 4100, 1300, -1, -1, 2800 },
				new WorldTile(3702, 3503, 0)),

		Catherby(new int[] { 3200, 2500, -1, 1600, 480, 480, 1600, 1000, 1750, -1, 3400 },
				new WorldTile(2792, 3414, 0)),

		Ship_Yard(new int[] { 3200, -1, 1600, -1, 200, 400, 720, 400, 225, -1, 900 }, new WorldTile(3001, 3032, 0)),

		Karamja(new int[] { 3200, 2900, 480, 200, -1, 200, 400, -1, 225, -1, 2000 }, new WorldTile(2954, 3158, 0)),

		Brimhaven(new int[] { 3200, 2900, 480, 400, 200, -1, 400, 1600, 975, -1, 3800 }, new WorldTile(2760, 3238, 0)),

		Port_Khazard(new int[] { 3200, 4100, 1600, 1600, 1600, 1600, -1, 1280, 1025, -1, 5000 },
				new WorldTile(2674, 3144, 0)),

		Port_Sarim(new int[] { 3200, 1300, 1000, 400, -1, 1600, 1280, -1, 325, -1, 1400 },
				new WorldTile(3038, 3192, 0)),

		Mos_Le_Harmless(new int[] { 1600, -1, 625, 275, 1025, 725, 1025, 325, -1, -1, 500 },
				new WorldTile(3671, 2931, 0)),

		Crandor(new int[] { 3200, 2800, 3400, 900, 2000, 3800, 5000, 1400, 500, -1, -1 }, new WorldTile(2623, 2857, 0)), // disable
																															// for
																															// now

		Oo_glog(new int[] { 3200, 2800, 3400, 900, 2000, 3800, 5000, 1400, 500, -1, -1 }, new WorldTile(2623, 2857, 0)),

		/**
		 * Fares
		 */
		Etrana_Fare(new int[] { -1 }, 3500, "Port Sarim", new WorldTile(2834, 3335, 0), new WorldTile(3048, 3234, 0)),

		Cranador_Fare(new int[] { -1 }, 5000, "Port Sarim", new WorldTile(2853, 3238, 0), new WorldTile(3047, 3204, 0)),

		Karamja_Fare(new int[] { 30 }, 3000, "Port Sarim", new WorldTile(2956, 3146, 0), new WorldTile(3029, 3217, 0)),

		Brimhaven_Fare(new int[] { 30 }, 4000, "Ardougne", new WorldTile(2772, 3234, 0), new WorldTile(2683, 3271, 0)),

		UNUSED(null, -1, null, null, null),

		Port_Khazard_Fare(new int[] { -1 }, 7000, "Ship yard", new WorldTile(2981, 3052, 0),
				new WorldTile(2676, 3170, 0)), // 15

		Carin_Island_Fare(new int[] { -1 }, 6000, "Ship yard", new WorldTile(2995, 3052, 0),
				new WorldTile(2763, 2956, 1)),

		Void_Outpost_Fare(new int[] { -1 }, 6000, "Port Sarim", new WorldTile(2659, 2676, 0),
				new WorldTile(3041, 3202, 0)),

		/**
		 * Relleka boats
		 */
		Jatizo(new int[] { -1 }, 1000, "Relleke", new WorldTile(2422, 3781, 0), new WorldTile(2643, 3710, 0)),

		Neitznot(new int[] { -1 }, 1000, "Relleke", new WorldTile(2311, 3781, 0), new WorldTile(2643, 3710, 0)),

		Waterbirth(new int[] { -1 }, 1000, "Relleke", new WorldTile(2551, 3756, 0), new WorldTile(2620, 3686, 0)),

		Miscellenia(new int[] { -1 }, 1000, "Relleke", new WorldTile(2581, 3847, 0), new WorldTile(2629, 3693, 0)),

		Pirates_Cove(new int[] { -1 }, 1000, "Relleke", new WorldTile(2213, 3794, 0), new WorldTile(2620, 3686, 0)),

		LumbridgeCanoe(new int[] { -1 }, 1000, null, new WorldTile(3233, 3249, 0), null),

		LegendsCanoe(new int[] { -1 }, 1000, null, new WorldTile(3199, 3343, 0), null),

		BarbCanoe(new int[] { -1 }, 1000, null, new WorldTile(3113, 3406, 0), null),

		EdgeCanoe(new int[] { -1 }, 1000, null, new WorldTile(3130, 3505, 0), null),

		WildernessCanoe(new int[] { -1 }, 1000, null, new WorldTile(3147, 3799, 0), null),

		/**
		 * Glider
		 */

		Sindrpos_Glider(new int[] { -1 }, 1000, null, new WorldTile(2845, 3501, 0), new WorldTile(2464, 3503, 3)),

		LemantoAndra_Glider(new int[] { -1 }, 1500, null, new WorldTile(3322, 3431, 0), new WorldTile(2464, 3503, 3)),

		KarHewo_Glider(new int[] { -1 }, 1700, null, new WorldTile(3284, 3213, 0), new WorldTile(2464, 3503, 3)),

		Gandius_Glider(new int[] { -1 }, 1500, null, new WorldTile(2973, 2969, 0), new WorldTile(2464, 3503, 3)),

		UNUSED2(new int[] { -1 }, -1, null, null, null),

		Lemantolly_Undri_Glider(new int[] { -1 }, 1000, null, new WorldTile(2545, 2972, 0),
				new WorldTile(2464, 3503, 3));

		private int[] costs;
		private long destinationTime;
		private WorldTile destination, origon;
		private String secondDest;

		private Carrier(int[] costs, long destinationTime, String secondDest, WorldTile destination, WorldTile origon) {
			this.costs = costs;
			this.destinationTime = destinationTime;
			this.destination = destination;
			this.secondDest = secondDest;
			this.origon = origon;
		}

		private Carrier(int[] cost, WorldTile destination) {
			this(cost, 2500, null, destination, null);
		}

		public int[] getCosts() {
			return costs;
		}

		public long getDestinationTime() {
			return destinationTime;
		}

		public WorldTile getDestination() {
			return destination;
		}

		public WorldTile getOrigon() {
			return origon;
		}

		public String getFixedName(boolean returning) {
			return (returning ? secondDest : toString().replace("_Fare", "").replace("_", " "));
		}
	}

	public static boolean sendCarrier(final Player player, final Carrier ship, boolean returning) {
		return sendCarrier(player, ship, 0, returning);
	}

	public static boolean sendCarrier(final Player player, final Carrier ship, int shipIndex, boolean returning) {
		if (player.getTemporaryAttributes().get("using_carrier") != null)
			return false;
		int cost = ship.getCosts()[shipIndex];
		if (cost != -1) {
			if (player.hasMoney(cost)) {
				player.getPackets().sendGameMessage("You don't have enough money for that.");
				return false;
			}
			player.getMoneyPouch().removeMoneyMisc(cost);
			player.getPackets().sendGameMessage("You pay the fare and sail to " + ship.getFixedName(returning) + ".");
		}
		final boolean isFare = ship.toString().contains("Fare");
		if (isFare) {
			if (ship.ordinal() == 10 && !returning) {
				boolean hasEquip = false;
				for (Item item : player.getInventory().getItems().getContainerItems()) {
					if (item == null)
						continue;
					if (Equipment.getItemSlot(item.getId()) != -1) {
						hasEquip = true;
						break;
					}
				}
				if (player.getEquipment().wearingArmour() || hasEquip) {
					player.getPackets().sendGameMessage(
							"The monk notices that you tried to fool him. Deposit your armor near the deposite box to travel to Entrana.");
					return false;
				}
			}
			player.getInterfaceManager().sendInterface(TRAVEL_INTERFACE);
			int configValue = 1
					+ (((ship.ordinal() - 10) * 2) + (ship.ordinal() >= 17 ? returning ? -1 : 0 : returning ? 1 : 0));
			player.getVarsManager().sendVar(75, configValue);
		}
		final WorldTile tile = returning ? ship.getOrigon() : ship.getDestination();
		player.lock();
		player.getPackets().sendMusicEffect(650);
		player.getTemporaryAttributes().put("using_carrier", true);
		FadingScreen.fade(player, new Runnable() {

			@Override
			public void run() {// 9
				player.setNextWorldTile(tile);
				player.lock(1);
				player.closeInterfaces();
				if (isFare)
					player.getVarsManager().sendVar(75, 0);
				player.getTemporaryAttributes().remove("using_carrier");
			}
		});
		return true;
	}

	public static void openGliderInterface(Player player, Carrier ship) {
		player.getInterfaceManager().sendInterface(GLIDER_INTEFACE);
		if (ship != Carrier.WildernessCanoe)
			CarrierTravel.sendCarrier(player, ship, true);
	}

	public static void openCharterInterface(Player player) {
		player.getInterfaceManager().sendInterface(CHARTER_INTERFACE);
		int shipIndex = getCostIndex(player.getRegionId());
		Carrier ship = Carrier.values()[shipIndex];
		for (int index = 0; index < REGIONS.length; index++) {
			int cost = ship.getCosts()[index];
			if (cost == -1)
				player.getPackets().sendHideIComponent(CHARTER_INTERFACE, 23 + index, true);
		}
		// never
		// unlocked
	}

	public static void handleCharterOptions(Player player, int componentId) {
		int index = componentId - 23;
		if (index < 0 || index > 10)
			return;
		Carrier ship = Carrier.values()[index];
		player.closeInterfaces();
		int costI = getCostIndex(player.getRegionId());
		if (costI == -1)
			return;
		player.getDialogueManager().startDialogue("QuickCharter", ship, ship.getCosts()[costI]);
	}

	private static int getCostIndex(int regionId) {
		for (int index = 0; index < REGIONS.length; index++) {
			if (REGIONS[index] == regionId)
				return index;
		}
		return -1;
	}
}
