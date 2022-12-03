package com.rs.game.player.content;

import com.rs.game.WorldTile;

/**
 * 
 * @author Andreas - AvalonPK
 * 
 */

public enum Teleports {

	VARROCK(3212, 3424, 0, "varrock"), FALADOR(2964, 3379, 0, "falador"), LUMBRIDGE(3222, 3218, 0, "lumby"), CAMELOT(
			2757, 3478, 0, "camelot"), ARDOUGNE(2662, 3305, 0, "ardougne"), YANILLE(2605, 3093, 0, "yanille"), DRAYNOR(
					3104, 3249, 0, "draynor"), AL_KHARID(3293, 3183, 0, "al_kharid"), RIMMINGTON(2956, 3210, 0,
							"rimmington"), KARAMJA(2904, 3173, 0, "karamja"), SHILO_VILLAGE(2852, 2961, 0,
									"shilo_village"), CASTLE_WARS(2442, 3089, 0, "castle_wars"), DUEL_ARENA(3370, 3268,
											0, "duel_arena"), EDGEVILLE(3087, 3501, 0, "edgeville"), BURTHORPE(2898,
													3539, 0, "burthorpe"), NEITIZNOT(2322, 3803, 0,
															"neitiznot"), LUNAR_ISLE(2096, 3913, 0, "lunar_isle");

	private Teleports(int x, int y, int h, String city) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.city = city;
	}

	private int x;
	private int y;
	private int h;
	private String city;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPlane() {
		return h;
	}

	public String getName() {
		return city;
	}

	public WorldTile getWorldTile() {
		return new WorldTile(getX(), getY(), getPlane());
	}

	public static Teleports forName(String city) {
		for (Teleports teleport : Teleports.values()) {
			if (teleport.getName().equalsIgnoreCase(city))
				return teleport;
		}
		return null;
	}

}
