package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.minigames.warriorguild.WarriorsGuild;
import com.rs.game.player.Player;
import com.rs.game.player.content.WildernessArtefacts.Artefacts;
import com.rs.game.player.content.pet.Pets;

public class ItemConstants {

	public static int getDegradeItemWhenWear(int id) {
		return -1;
	}

	static int[] nonDegItems = { 13858, 13861, 13864, 13867, 13870, 13873, 13876, 13884, 13887, 13890, 13893, 13896,
			13899, 13902, 13905, 13908, 13911, 13914, 13917, 13920, 13923, 13926, 13929, 13932, 13935, 13938, 13941,
			13944, 13947, 13950 };

	static int[] degItems = { 13859, 13860, 13865, 13866, 13868, 13869, 13871, 13872, 13874, 13875, 13877, 13878, 13885,
			13886, 13888, 13889, 13891, 13892, 13894, 13895, 13897, 13898, 13900, 13901, 13903, 13904, 13906, 13907,
			13909, 13910, 13912, 13913, 13915, 13916, 13918, 13919, 13921, 13922, 13924, 13925, 13927, 13928, 13930,
			13931, 13933, 13934, 13936, 13937, 13939, 13940, 13942, 13943, 13945, 13946, 13948, 13949 };

	static int[] nonDegItemsNormal = { 13858, 13861, 13864, 13867, 13870, 13873, 13876, 13884, 13887, 13890, 13893,
			13896, 13899, 13902, 13905 };

	static int[] nonDegItemsCorrupt = { 13908, 13911, 13914, 13917, 13920, 13923, 13926, 13929, 13932, 13935, 13938,
			13941, 13944, 13947, 13950 };

	static int[] DegItemsNormal = { 13859, 13860, 13865, 13866, 13868, 13869, 13871, 13872, 13874, 13875, 13877, 13878,
			13885, 13886, 13888, 13889, 13891, 13892, 13894, 13895, 13897, 13898, 13900, 13901, 13903, 13904, 13906,
			13907 };

	static int[] DegItemsCorrupt = { 13909, 13910, 13912, 13913, 13915, 13916, 13918, 13919, 13921, 13922, 13924, 13925,
			13927, 13928, 13930, 13931, 13933, 13934, 13936, 13937, 13939, 13940, 13942, 13943, 13945, 13946, 13948,
			13949 };

	static int[] nexDegraded = { 20137, 20141, 20145, 20149, 20153, 20157, 20161, 20165, 20169 };

	static int[] lastCrystal = { 4234, 4223 };
	static int[] degradedCrystalBow = { 4214, 4215, 4216, 4217, 4218, 4219, 4220, 4221, 4222 };
	static int[] degradedCrystalShield = { 4225, 4226, 4227, 4228, 4229, 4230, 4231, 4232, 4233 };
	static int[] FullCrystalBow = { 4212 };
	static int[] fullCrystalShield = { 4224 };

	static int[] barrows = { 4708, 4710, 4712, 4714, 4716, 4718, 4720, 4722, 4724, 4726, 4728, 4730, 4732, 4734, 4736,
			4738, 4745, 4747, 4749, 4751, 4753, 4755, 4757, 4759 };
	static int[] barrows100 = { 4856, 4862, 4868, 4874, 4880, 4886, 4892, 4898, 4904, 4910, 4916, 4922, 4928, 4934,
			4940, 4946, 4952, 4958, 4964, 4970, 4976, 4982, 4988, 4994 };
	static int[] barrows75 = { 4857, 4863, 4869, 4875, 4881, 4887, 4893, 4899, 4905, 4911, 4917, 4923, 4929, 4935, 4941,
			4947, 4953, 4959, 4965, 4971, 4977, 4983, 4989, 4995 };
	static int[] barrows50 = { 4858, 4864, 4870, 4876, 4882, 4888, 4894, 4900, 4906, 4912, 4918, 4924, 4930, 4936, 4942,
			4948, 4954, 4960, 4966, 4972, 4978, 4984, 4990, 4996 };
	static int[] barrows25 = { 4859, 4865, 4871, 4877, 4883, 4889, 4895, 4901, 4907, 4913, 4919, 4925, 4931, 4937, 4943,
			4949, 4955, 4961, 4967, 4973, 4979, 4985, 4991, 4997 };

	/* 57600 ticks = 10 hours */
	/* 24480 ticks = 3.75 hour */
	/* 5760 ticks = 1 hour */
	/* 2880 ticks = 30 minutes */
	/* 2400 ticks = 25 minutes */
	/* 960 ticks = 10 minutes */
	/* 480 ticks = 5 minute */
	/* 96 ticks = 1 minute */
	/* 16 ticks = 10 seconds */
	public static int getItemDefaultCharges(int id) {
		for (int Barrows100 : barrows100) {
			if (id == Barrows100) {
				return 24480 / 2;// 3.75 / 2 hours
			}
		}
		for (int Barrows75 : barrows75) {
			if (id == Barrows75) {
				return 24480 / 2;// 3.75 / 2 hours
			}
		}
		for (int Barrows50 : barrows50) {
			if (id == Barrows50) {
				return 24480 / 2;// 3.75 / 2 hours
			}
		}
		for (int Barrows25 : barrows25) {
			if (id == Barrows25) {
				return 24480 / 2;// 3.75 / 2 hours
			}
		}
		for (int crystalBow : degradedCrystalBow) {
			if (id == crystalBow) {
				return 1600;// 1600 ticks
			}
		}
		for (int lastcrystal : lastCrystal) {
			if (id == lastcrystal) {
				return 1600;// 1600 ticks
			}
		}
		for (int crystalShield : degradedCrystalShield) {
			if (id == crystalShield) {
				return 1600;// 1600 ticks
			}
		}
		for (int deg : DegItemsNormal) {
			if (id == deg) {
				return 2880 * 2;// 30 minutes * 2
			}
		}
		for (int corruptDeg : DegItemsCorrupt) {
			if (id == corruptDeg) {
				return 1440;// 15 minutes
			}
		}
		for (int nonDegraded : nonDegItems) {
			if (id == nonDegraded) {
				return 48;// 30 seconds
			}
		}
		for (int nexArmour : nexDegraded) {
			if (id == nexArmour) {
				return 57600; // 10 hours
			}
		}
		return -1;
	}

	public static int getTradeableId(String name) {
		return -1;
	}

	public static int getItemDegrade(int id) {
		if (id == 20137 || id == 20141 || id == 20145 || id == 20149 || id == 20153 || id == 20157 || id == 20161
				|| id == 20165 || id == 20169 || id == 20173)
			return id + 1;
		for (int lastcrystal : lastCrystal) {
			if (id == lastcrystal)
				return 4207; // 1/10 To Crystal seed
		}
		for (int crystalBow : degradedCrystalBow) {
			if (id == crystalBow)
				return id + 1; // Degrade 1 charge
		}
		for (int crystalShield : degradedCrystalShield) {
			if (id == crystalShield)
				return id + 1; // Degrade 1 charge
		}
		for (int nonDegraded : nonDegItems) {
			if (id == nonDegraded)
				return id + 2; // Normal to (deg)
		}
		for (int Barrows100 : barrows100) {
			if (id == Barrows100)
				return id + 1;// 100% to 75%
		}
		for (int Barrows75 : barrows75) {
			if (id == Barrows75)
				return id + 1;// 75% to 50%
		}
		for (int Barrows50 : barrows50) {
			if (id == Barrows50)
				return id + 1;// 50% to 25%
		}
		for (int Barrows25 : barrows25) {
			if (id == Barrows25)
				return id + 1;// 25% to 0%
		}
		return -1;
	}

	public static int getDegradeItemWhenCombating(int id) {
		if (id == 20135 || id == 20139 || id == 20143 || id == 20147 || id == 20151 || id == 20155 || id == 20159
				|| id == 20163 || id == 20167 || id == 20171)
			return id + 2;
		for (int crystalBow : FullCrystalBow) {
			if (id == crystalBow)
				return id + 2; // Normal to (Full)
		}
		for (int crystalShield : fullCrystalShield) {
			if (id == crystalShield)
				return id + 1; // Normal to (Full)
		}
		if (id == 4708)
			return id + 148;
		if (id == 4710)
			return id + 152;
		if (id == 4712)
			return id + 156;
		if (id == 4714)
			return id + 160;
		if (id == 4716)
			return id + 164;
		if (id == 4718)
			return id + 168;
		if (id == 4720)
			return id + 172;
		if (id == 4722)
			return id + 176;
		if (id == 4724)
			return id + 180;
		if (id == 4726)
			return id + 184;
		if (id == 4728)
			return id + 188;
		if (id == 4730)
			return id + 192;
		if (id == 4732)
			return id + 196;
		if (id == 4734)
			return id + 200;
		if (id == 4736)
			return id + 204;
		if (id == 4738)
			return id + 208;
		if (id == 4745)
			return id + 207;
		if (id == 4747)
			return id + 211;
		if (id == 4749)
			return id + 215;
		if (id == 4751)
			return id + 219;
		if (id == 4753)
			return id + 223;
		if (id == 4755)
			return id + 227;
		if (id == 4757)
			return id + 231;
		if (id == 4759)
			return id + 235;
		return -1;
	}

	public static boolean itemDegradesWhileHit(int id) {
		if (id == 2550)
			return true;
		return false;
	}

	public static boolean itemDegradesWhileWearing(int id) {
		@SuppressWarnings("unused")
		String name = ItemDefinitions.getItemDefinitions(id).getName().toLowerCase();
		for (int degItems : DegItemsCorrupt) {
			if (id == degItems)
				return true;
		}
		for (int nonDegItems : nonDegItemsCorrupt) {
			if (id == nonDegItems)
				return true;
		}
		return false;
	}

	public static boolean itemDegradesWhileCombating(int id) {
		String name = ItemDefinitions.getItemDefinitions(id).getName().toLowerCase();
		if (name.contains("torva") || name.contains("pernix") || name.contains("virtux") || name.contains("zaryte"))
			return true;
		for (int Barrows100 : barrows100) {
			if (id == Barrows100) {
				return true;
			}
		}
		for (int Barrows75 : barrows75) {
			if (id == Barrows75)
				return true;
		}
		for (int Barrows50 : barrows50) {
			if (id == Barrows50)
				return true;
		}
		for (int Barrows25 : barrows25) {
			if (id == Barrows25)
				return true;
		}
		for (int degItems : DegItemsNormal) {
			if (id == degItems)
				return true;
		}
		for (int nondegItems : nonDegItemsNormal) {
			if (id == nondegItems)
				return true;
		}
		for (int crystalBow : degradedCrystalBow) {
			if (id == crystalBow)
				return true;
		}
		for (int crystalShield : degradedCrystalShield) {
			if (id == crystalShield)
				return true;
		}
		for (int lastcrystal : lastCrystal) {
			if (id == lastcrystal)
				return true;
		}
		return false;
	}

	public static boolean canWear(Item item, Player player) {
		if (player.isDeveloper())
			return true;
		if (item.getId() >= 22358 && item.getId() <= 22369) {
			if (player.getSkills().getCombatLevel() < 110) {
				player.getPackets().sendGameMessage("You need atleast 110 combat to wear these gloves.");
				return false;
			}
		} else if (item.getId() == 8856) {
			if (!WarriorsGuild.inCatapultArea(player)) {
				player.getPackets().sendGameMessage(
						"You may not equip this shield outside of the catapult room in the Warrior's Guild.");
				return false;
			}
		}
		if (item.getId() == 20769) {
			if (!player.hasCompletionistRequirements()) {
				player.message("You must have finished all the requirements.");
				return false;
			}
		}
		if (item.getId() == 20771) {
			if (!player.hasTrimCompReqs()) {
				player.message("You must have finished all the requirements.");
				return false;
			}
		}
		if (item.getDefinitions().getName().toLowerCase().contains(" 0")
				|| item.getDefinitions().getName().toLowerCase().contains("broken"))
			return false;
		return true;
	}

	public static boolean isWearable(Item item) {
		return true;
	}

	public static boolean isTradeable(Item item) {
		String defs = item.getDefinitions().getName().toLowerCase();
		if (item.getDefinitions().isDestroyItem() && item.getId() != 13663 || item.getDefinitions().isLended())
			return false;
		if (defs.contains("training"))
			return false;
		if (defs.contains("super antifire"))
			return false;
		if (defs.contains("coin bag"))
			return false;
		if (defs.contains("(deg)"))
			return false;
		if (defs.contains("flaming skull"))
			return false;
		if (defs.contains("milestone cape"))
			return false;
		if (defs.contains("dice ")
				|| defs.contains("die ("))
			return false;
		if (item.getId() >= 20135 && item.getId() <= 20174 && item.getId() != 20135 && item.getId() != 20139
				&& item.getId() != 20143 && item.getId() != 20147 && item.getId() != 20151 && item.getId() != 20155 && item.getId() != 20159 && item.getId() != 20163 && item.getId() != 20167 && item.getId() != 20171)
			return false;
		if (item.getId() >= 18330 && item.getId() <= 18374)
			return false;
		if (item.getId() >= 19669 && item.getId() <= 19675)
			return false;
		if (item.getId() == 19888)
			return false;
		if (item.getId() == 12850 || item.getId() == 12851)
			return false;
		if (item.getId() == 18839)
			return false;
		if (item.getId() == 6665 || item.getId() == 6666)
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("rotten potato"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("sneak"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("salve amu"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("overload"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("crystal ("))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("damaged book"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("special re"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("extreme "))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("void "))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains(" charm"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("clue scroll"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("casket"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("lamp"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains(" 100")
				|| item.getDefinitions().getName().toLowerCase().contains(" 75")
				|| item.getDefinitions().getName().toLowerCase().contains(" 50")
				|| item.getDefinitions().getName().toLowerCase().contains(" 25"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("swift"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("spellcast"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("(charged)"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("(i)"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("wildstalker"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("duellist"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("goliath"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("slayer helm"))
			return false;
		if (item.getDefinitions().getName().toLowerCase().contains("ring of slaying"))
			return false;
		for (Pets items : Pets.values()) {
			if (item.getDefinitions().getId() == items.getBabyItemId())
				return false;
		}
		for (Artefacts artefacts : Artefacts.values()) {
			if (item.getDefinitions().getId() == artefacts.getId())
				return false;
		}
		switch (item.getId()) {
		case 10501:
		case 22899:
		case 22901:
		case 22904:
		case 23876:
		case 22905:
		case 22907:
		case 22909:
		case 23874:
		case 23848:
		case 23850:
		case 23852:
		case 23854:
		case 23856:
		case 23862:
		case 22897:
		case 23866:
		case 23864:
		case 22298:
		case 22300:
		case 23860:
		case 23868:
		case 23858:
		case 19335:
		case 19336:
		case 19337:
		case 19338:
		case 19339:
		case 19340:
		case 19341:
		case 19342:
		case 19343:
		case 19344:
		case 19345:
		case 3840:
		case 3842:
		case 3844:
		case 6570: // firecape
		case 6529: // tokkul
		case 7462: // barrow gloves
		case 23659: // tookhaar-kal
		case 24876:
		case 23660:
		case 20771:
		case 20767:
		case 20768:
		case 21371:
		case 20769:
		case 20770:
		case 20772:
		case 8844:// defender start
		case 8845:
		case 8846:
		case 8847:
		case 8848:
		case 8849:
		case 8850:
		case 20072:// defender end
		case 2412:
		case 2414:
		case 2413:
		case 19748:
		case 3600:
		case 10551:
		case 12852: // fogtoken
		case 18333: // arcanepulse
		case 18334: // arcaneblast
		case 18335: // arcanestream
		case 18346: // tomboffrost
		case 19893: // spiritcape
		case 19669: // vigour
		case 18349: // chaoticrapier
		case 18351: // chaoticlong
		case 18353: // chaoticmaul
		case 18355: // chaoticstaff
		case 18357: // chaoticcbow
		case 18359: // chaoticshield
		case 18361: // eagleeyekite
		case 18363: // farseerkite
		case 11283:
		case 24497:
			return false;
		default:
			return true;
		}
	}

	public static boolean keptOnDeath(Item item) {
		if (item.getDefinitions().isLended())
			return true;
		/*
		 * if (item.getId() >= 18330 && item.getId() <= 18374) return true; if
		 * (item.getId() >= 19669 && item.getId() <= 19675) return true;
		 */
		
		if (item.getId() == 19888)
			return true;
		if (item.getId() == 18839)
			return true;
		if (item.getId() == 24497)
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains("sneak"))
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains(" charm"))
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains("sneak"))
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains("overload"))
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains("ava's"))
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains("slayer "))
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains("void"))
			return true;
		switch (item.getId()) {
		case 22899:
		case 22901:
		case 22904:
		case 23876:
		case 22905:
		case 22907:
		case 22909:
		case 23874:
		case 23848:
		case 23850:
		case 23852:
		case 23854:
		case 23856:
		case 23862:
		case 22897:
		case 23866:
		case 23864:
		case 22298:
		case 22300:
		case 23860:
		case 23868:
		case 23858:
		case 2412: // god cape
		case 2413:
		case 2414:
		case 23659:
		case 24876:
		case 6570:
		case 23660:
		case 18346:
		case 18335:
		case 10551:
		case 10548:
		case 20072:
		case 8850:
		case 8849:
		case 8848:
		case 8847:
		case 3839:
		case 3840:
		case 3841:
		case 3842:
		case 3843:
		case 3844:
		case 7460:
		case 7459:
		case 7458:
		case 7457:
		case 7456:
		case 7455:
		case 7454:
		case 7453:
		case 8842:
		case 11663:
		case 11664:
		case 11665:
		case 8839:
		case 8840:
		case 6665:
		case 6666:
			return true;
		default:
			return false;
		}
	}

	public static boolean turnCoins(Item item) {
		if (item.getDefinitions().getName().toLowerCase().contains("(deg)"))
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains("strength cape"))
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains("max cape"))
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains("max hood"))
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains("completionist cape"))
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains("completionist hood"))
			return true;
		switch (item.getId()) {
		case 10887:
		case 7462:
		case 7461:
		case 18349:
		case 18351:
		case 18353:
		case 18355:
		case 18357:
		case 18359:
		case 18361:
		case 18363:
		case 18335:
		case 18334:
		case 18333:
			return true;
		default:
			return false;
		}
	}

	public static boolean degradeOnDrop(Item item) {
		if (item.getDefinitions().getName().toLowerCase().contains(" 100")
				|| item.getDefinitions().getName().toLowerCase().contains(" 75")
				|| item.getDefinitions().getName().toLowerCase().contains(" 50")
				|| item.getDefinitions().getName().toLowerCase().contains(" 25"))
			return true;
		return false;
	}

	public static boolean removeAttached(Item item) {
		if (item.getDefinitions().getName().toLowerCase().contains("vine whip"))
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains("(e)")
				&& !item.getDefinitions().getName().toLowerCase().contains("bolt"))
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains("(i)"))
			return true;
		if (item.getDefinitions().getName().toLowerCase().contains("(charged)"))
			return true;
		return false;
	}

	public static int removeAttachedId(Item item) {
		if (item.getDefinitions().getName().toLowerCase().contains("vine whip"))
			return 21369;
		switch (item.getId()) {
		case 12675:
		case 12674:
			return 3751;
		case 12678:
		case 12679:
			return 3755;
		case 12672:
		case 12673:
			return 3749;
		case 12676:
		case 12677:
			return 3753;
		case 15018:
			return 6731;
		case 15019:
			return 6733;
		case 15020:
			return 6735;
		case 15220:
			return 6737;
		case 15017:
			return 6575;
		case 19335:
			return 19333;
		case 19336:
			return 19346;
		case 19337:
			return 19350;
		case 19338:
		case 19339:
			return 19348;
		case 19340:
			return 19352;
		case 19341:
			return 19354;
		case 19342:
			return 19358;
		case 19343:
		case 19344:
			return 19356;
		case 19345:
			return 19360;

		}
		return -1;
	}

	public static int removeAttachedId2(Item item) {
		if (item.getDefinitions().getName().toLowerCase().contains("vine whip"))
			return 4151;
		switch (item.getId()) {
		case 19335:
			return 6585;
		case 19336:
		case 19341:
			return 11335;

		case 19337:
		case 19342:
			return 14479;

		case 19338:
		case 19343:
			return 4087;

		case 19339:
		case 19344:
			return 4585;

		case 19340:
		case 19345:
			return 1187;

		}
		return -1;
	}
}
