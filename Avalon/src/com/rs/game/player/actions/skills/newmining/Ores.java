package com.rs.game.player.actions.skills.newmining;

import com.rs.game.WorldObject;

public class Ores extends Mining {

	public Ores(WorldObject rock) {
		super(rock);
	}

	public enum OreStore {

		CLAY_ORE(15503, 1, 5, 434, 10, 1, 11555, 5, 0),

		CLAY_ORE2(15504, 1, 5, 434, 10, 1, 11556, 5, 0),

		CLAY_ORE3(15505, 1, 5, 434, 10, 1, 11557, 5, 0),
		

		COPPER_ORE(11936, 1, 17.5, 436, 10, 1, 11552, 5, 0),

		COPPER_ORE2(11937, 1, 17.5, 436, 10, 1, 11553, 5, 0),

		COPPER_ORE3(11938, 1, 17.5, 436, 10, 1, 11554, 5, 0),
		
		COPPER_ORE4(11960, 1, 17.5, 436, 10, 1, 11555, 5, 0),
		
		COPPER_ORE5(11961, 1, 17.5, 436, 10, 1, 11556, 5, 0),
		
		COPPER_ORE6(11962, 1, 17.5, 436, 10, 1, 11557, 5, 0),
		

		TIN_ORE(11933, 1, 17.5, 438, 15, 1, 11552, 5, 0),

		TIN_ORE2(11934, 1, 17.5, 438, 15, 1, 11553, 5, 0),

		TIN_ORE3(11935, 1, 17.5, 438, 15, 1, 11554, 5, 0),

		TIN_ORE4(11957, 1, 17.5, 438, 15, 1, 11555, 5, 0),

		TIN_ORE5(11958, 1, 17.5, 438, 15, 1, 11556, 5, 0),

		TIN_ORE6(11959, 1, 17.5, 438, 15, 1, 11557, 5, 0),
		
		
		LIMESTONE(4027, 10, 26.5, 3211, 20, 1, 4028, 30, 0),
		
		LIMESTONE2(4028, 10, 26.5, 3211, 20, 1, 4029, 30, 0),
		
		LIMESTONE3(4029, 10, 26.5, 3211, 20, 1, 4030, 30, 0),
		

		IRON_ORE(37307, 15, 35, 440, 15, 1, 11552, 10, 0),

		IRON_ORE2(37308, 15, 35, 440, 15, 1, 11553, 10, 0),

		IRON_ORE3(37309, 15, 35, 440, 15, 1, 11554, 10, 0),

		IRON_ORE4(11954, 15, 35, 440, 15, 1, 11555, 10, 0),

		IRON_ORE5(11955, 15, 35, 440, 15, 1, 11556, 10, 0),

		IRON_ORE6(11956, 15, 35, 440, 15, 1, 11557, 10, 0),
		

		SANDSTONE_ORE(10946, 35, 30, 6971, 30, 1, 11554, 10, 0),
		

		SILVER_ORE(37304, 20, 40, 442, 25, 1, 11552, 20, 0),

		SILVER_ORE2(37305, 20, 40, 442, 25, 1, 11553, 20, 0),

		SILVER_ORE3(37306, 20, 40, 442, 25, 1, 11554, 20, 0),
		
		SILVER_ORE4(32444, 20, 40, 442, 25, 1, 11552, 20, 0),
		

		COAL_ORE(11930, 30, 50, 453, 50, 10, 11552, 30, 0),

		COAL_ORE2(11931, 30, 50, 453, 50, 10, 11553, 30, 0),

		COAL_ORE3(11932, 30, 50, 453, 50, 10, 11554, 30, 0),
		
		COAL_ORE4(11963, 30, 50, 453, 50, 10, 11555, 30, 0),
		
		COAL_ORE6(11964, 30, 50, 453, 50, 10, 11556, 30, 0),
		

		GRANITE_ORE(10947, 45, 50, 6979, 50, 10, 11554, 20, 0),
		

		GOLD_ORE(37310, 40, 60, 444, 80, 20, 11552, 40, 0),

		GOLD_ORE2(37312, 40, 60, 444, 80, 20, 11554, 40, 0),
		

		MITHRIL_ORE(11942, 55, 80, 447, 100, 20, 11552, 60, 0),

		MITHRIL_ORE2(11943, 55, 80, 447, 100, 20, 11553, 60, 0),

		MITHRIL_ORE3(11944, 55, 80, 447, 100, 20, 11554, 60, 0),
		

		ADAMANT_ORE(11939, 70, 95, 449, 130, 25, 11552, 180, 0),

		ADAMANT_ORE2(11941, 70, 95, 449, 130, 25, 11554, 180, 0),
		

		RUNITE_ORE(14859, 85, 125, 451, 150, 30, 11552, 360, 0),

		RUNITE_ORE2(14860, 85, 125, 451, 150, 30, 11553, 360, 0),
		
		RUNITE_ORE3(45069, 85, 125, 451, 150, 30, 11552, 360, 0),

		RUNITE_ORE4(45071, 85, 125, 451, 150, 30, 11553, 360, 0),
		

		LRC_COAL_ORE(5999, 77, 50, 453, 50, 10, -1, 180, 0),

		LRC_GOLD_ORE(45076, 80, 60, 444, 40, 10, -1, 180, 0),
		
		
		GEM_ROCK(11194, 40, 65, 1625, 25, 10, 11193, 30, 0),
		
		GEM_ROCK2(11195, 40, 65, 1625, 25, 10, 11192, 30, 0),
		
		GEM_ROCK3(11364, 40, 65, 1625, 25, 10, 11192, 30, 0),
		
		//tzhaar
		TZHAAR_COAL_ORE(31168, 30, 50, 453, 50, 10, 11552, 30, 0),
		TZHAAR_MITHRIL_ORE(31170, 55, 80, 447, 100, 20, 11552, 60, 0),
		TZHAAR_ADAMANT_ORE(31173, 70, 95, 449, 130, 25, 11552, 180, 0),
		
		;

		int objectId;
		int level;
		double xp;
		int oreId;
		int oreBaseTime;
		int oreRandomTime;
		int emptySpot;
		int respawnDelay;
		int randomLife;

		private OreStore(int objectId, int level, double xp, int oreId, int oreBaseTime, int oreRandomTime,
				int emptySpot, int respawnDelay, int randomLife) {
			this.objectId = objectId;
			this.level = level;
			this.xp = xp;
			this.oreId = oreId;
			this.oreBaseTime = oreBaseTime;
			this.oreRandomTime = oreRandomTime;
			this.emptySpot = emptySpot;
			this.respawnDelay = respawnDelay;
			this.randomLife = randomLife;

		}
	}

}
