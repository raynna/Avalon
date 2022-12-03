package com.rs.game.player;

import com.rs.cache.loaders.ClientScriptMap;

public class CharacterDesign {

	public CharacterDesign() {

	}

	static int armStyle;

	public static void openPlayerCustomizing(Player player) {
		player.getPackets().sendWindowsPane(1028, 0);
		player.getPackets().sendIComponentSettings(1028, 65, 0, 11, 2);
		player.getPackets().sendIComponentSettings(1028, 128, 0, 50, 2);
		player.getPackets().sendIComponentSettings(1028, 132, 0, 250, 2);
		player.getPackets().sendHideIComponent(1028, 123, true);
		player.getPackets().sendConfig(2533, 0);
		player.getPackets().sendConfig(1363, player.getAppearence().isMale() ? 0 : 4096);
		player.temporaryAttribute().put("customizing_stage", Integer.valueOf(0));
		player.temporaryAttribute().put("customizing_set", Integer.valueOf(-1));
		player.temporaryAttribute().put("extra_set", Integer.valueOf(-1));
		armStyle = player.getAppearence().getArmsStyle();
	}

	public static void handleButtons(Player player, int componentId, int slotId, int packetId) {
		if (componentId >= 116 && componentId <= 121)
			player.temporaryAttribute().put("customizing_stage", Integer.valueOf(componentId - 115));
		if (componentId == 137 || componentId == 139)
			player.temporaryAttribute().put("customizing_stage", Integer.valueOf(componentId != 139 ? 0 : 1));
		if (componentId == 138) {
			int set = ((Integer) player.temporaryAttribute().get("customizing_set")).intValue();
			if (set == -1)
				set = 0;
			setPlayerSet(player, set);
			player.getAppearence().generateAppearenceData();
			player.getPackets().sendWindowsPane(player.getInterfaceManager().hasRezizableScreen() ? 746 : 548, 0);
			player.hasDesign = true;
		}
		switch (((Integer) player.temporaryAttribute().get("customizing_stage")).intValue()) {
		default:
			break;

		case 0:
			switch (componentId) {
			case 62:
			case 63:
				setGender(player, componentId == 62);
				break;

			case 65:
				player.getAppearence().setSkinColor(ClientScriptMap.getMap(748).getIntValue(slotId));
				break;

			case 68:
			case 69:
			case 70:
			case 71:
			case 72:
			case 73:
			case 74:
				player.temporaryAttribute().put("customizing_set", Integer.valueOf(componentId - 68));
				break;
			}
			break;

		case 1:
			switch (componentId) {
			case 132:
				player.getAppearence().setSkinColor(ClientScriptMap.getMap(748).getIntValue(slotId));
				break;
			}
			break;

		case 2:
			switch (componentId) {
			case 128:
				player.getAppearence().setHairStyle(HAIR_STYLES[player.getAppearence().isMale() ? 0 : 1][slotId]);
				break;

			case 132:
				player.getAppearence().setHairColor(ClientScriptMap.getMap(2345).getIntValue(slotId));
				break;
			}
			break;

		case 3:
			switch (componentId) {
			case 129:
			case 130:
			case 131:
			default:
				break;

			case 128:
				player.getAppearence().setTopStyle(
						ClientScriptMap.getMap(player.getAppearence().isMale() ? 690 : 1591).getIntValue(slotId));
				int arms = TORSOS[player.getAppearence().isMale() ? 0 : 1][0][slotId];
				if (arms != -1)
					player.getAppearence().setArmsStyle(arms);
				player.getAppearence().setWristsStyle(TORSOS[player.getAppearence().isMale() ? 0 : 1][1][slotId]);
				break;

			case 132:
				player.getAppearence().setTopColor(ClientScriptMap.getMap(3283).getIntValue(slotId));
				break;
			}
			break;

		case 4:
			switch (componentId) {
			case 128:
				player.getAppearence().setLegsStyle(
						ClientScriptMap.getMap(player.getAppearence().isMale() ? 1586 : 1607).getIntValue(slotId));
				break;

			case 132:
				player.getAppearence().setLegsColor(ClientScriptMap.getMap(3283).getIntValue(slotId));
				break;
			}
			break;

		case 5:
			switch (componentId) {
			case 128:
				player.getAppearence().setBootsStyle(
						ClientScriptMap.getMap(player.getAppearence().isMale() ? 1136 : 1137).getIntValue(slotId));
				break;

			case 132:
				player.getAppearence().setBootsColor(ClientScriptMap.getMap(3297).getIntValue(slotId));
				break;
			}
			break;

		case 6:
			switch (componentId) {
			case 128:
				player.getAppearence().setBeardStyle(ClientScriptMap.getMap(3307).getIntValue(slotId));
				break;

			case 132:
				player.getAppearence().setHairColor(ClientScriptMap.getMap(2345).getIntValue(slotId));
				break;
			}
			break;
		}
	}

	private static void setGender(Player player, boolean male) {
		if (male) {
			player.getAppearence().male();
			player.getAppearence().setLook(0, 5);
			player.getAppearence().setLook(1, 14);
		} else {
			player.getAppearence().female();
			player.getAppearence().setLook(0, 141);
			player.getAppearence().setLook(1, 57);
		}
		player.getAppearence().setLookStyles(SETS[player.getAppearence().isMale() ? 0 : 1][0][0]);
		player.getAppearence().setColours(SETS[player.getAppearence().isMale() ? 0 : 1][0][1]);
		player.getPackets().sendConfig(1363, player.getAppearence().isMale() ? 0 : 4096);
		player.getAppearence().generateAppearenceData();
	}

	private static void setPlayerSet(Player player, int set) {
		int doubleSet = -1;
		if (set < 4)
			doubleSet = ((Integer) player.temporaryAttribute().get("extra_set")).intValue();
		player.getAppearence()
				.setLookStyles(SETS[player.getAppearence().isMale() ? 0 : 1][set][doubleSet != -1 ? doubleSet * 2 : 0]);
		player.getAppearence().setColours(
				SETS[player.getAppearence().isMale() ? 0 : 1][set][(doubleSet != -1 ? doubleSet * 2 : 0) + 1]);
		player.getAppearence().generateAppearenceData();
	}

	private static final short HAIR_STYLES[][] = {
			{ 5, 6, 93, 96, 92, 268, 265, 264, 267, 315, 94, 263, 312, 313, 311, 314, 261, 310, 1, 0, 97, 95, 262, 316,
					309, 3, 91, 4 },
			{ 141, 361, 272, 273, 359, 274, 353, 277, 280, 360, 356, 269, 358, 270, 275, 357, 145, 271, 354, 355, 45,
					52, 49, 47, 48, 46, 143, 362, 144, 279, 142, 146, 278, 135 } };
	public static final short TORSOS[][][] = {
			{ { 558, 0, 591, 592, 593, 594, 0, 596, 597, 0, 599, 600, 601, 0, 603, 0, 0, 0, 607, 608, 0, 589, 0, 0, 0,
					613, 0, 615, 616, 0, 618, 0 },
			{ 364, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385,
					365, 386, 387, 388, 389, 390, 391, 392, 393, 394, 9 } },
			{ { 395, 397, 398, 399, 400, 401, -1, 403, 404, 405, 406, 407, 408, -1, 410, -1, -1, -1, 414, -1, 416, 396,
					417, 418, -1, 420, 421, 422, 423, -1, 425, -1 },
					{ 507, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526,
							527, 528, 508, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538 } } };
	public static final int SETS[][][][] = { {
			{ { 473, 616, 392, 648, 441 }, { 37, 213, 4 }, { 443, armStyle, 390, 646, 440 }, { 37, 213, 4 },
					{ 474, 618, 394, 650, 441 }, { 37, 213, 4 } },
			{ { 453, armStyle, 380, 636, 429 }, { 197, 202, 4 }, { 454, armStyle, 381, 637, 429 }, { 197, 202, 4 },
					{ 455, armStyle, 382, 638, 429 }, { 197, 202, 4 } },
			{ { 447, armStyle, 386, 642, 429 }, { 125, 125, 4 }, { 448, armStyle, 387, 643, 429 }, { 125, 125, 4 },
					{ 449, armStyle, 388, 644, 429 }, { 125, 125, 4 } },
			{ { 469, 607, 383, 639, 431 }, { 149, 150, 4 }, { 470, 608, 384, 640, 429 }, { 149, 150, 4 },
					{ 450, armStyle, 385, 641, 429 }, { 149, 150, 4 } },
			{ { 452, armStyle, 371, 627, 434 }, { 189, 189, 39 } }, { { 461, 593, 369, 625, 432 }, { 77, 78, 4 } },
			{ { 446, armStyle, 374, 630, 429 }, { 109, 109, 4 } } }, {
					{ // female set 1
							{ 586, 423, 535, 503, 553 }, { 56, 56, 155 }, { 585, 422, 534, 502, 554 }, { 48, 224, 4 },
							{ 587, 425, 537, 505, 551 }, { 56, 56, 4 } },
					{ // female set 2
							{ 562, 423, 523, 491, 551 }, { 200, 197, 4 }, { 563, 423, 524, 492, 551 }, { 200, 197, 4 },
							{ 564, 423, 525, 493, 551 }, { 200, 197, 4 } },
					{ // female set 3
							{ 581, 417, 529, 497, 551 }, { 128, 128, 4 }, { 582, 418, 530, 498, 551 }, { 128, 128, 4 },
							{ 557, 418, 531, 499, 551 }, { 128, 128, 4 } },
					{ // female set 4
							{ 579, 414, 526, 494, 551 }, { 152, 152, 4 }, { 559, 414, 527, 495, 551 }, { 152, 156, 4 },
							{ 580, 416, 528, 496, 552 }, { 152, 156, 4 } },
					{ // female set 5
							{ 561, 414, 514, 482, 545 }, { 192, 192, 39 } },
					{ // female set 6
							{ 570, 400, 512, 480, 543 }, { 80, 81, 4 } },
					{ // female set 7
							{ 574, 405, 517, 485, 548 }, { 112, 112, 4 } }

			}

	};

}