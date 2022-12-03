package com.rs.game.player.actions.skills.newmining;

import com.rs.game.WorldObject;

public class Pickaxes extends Mining {
	
	
	public Pickaxes(WorldObject rock) {
		super(rock);
	}

	public enum PickaxeStore {

		DRAGON(15259, 61, 12189, 13),
		
		INFERNO_ADZE(13661, 61, 10222, 10),

		RUNE(1275, 41, 624, 10),

		ADAMANT(1271, 31, 628, 7),

		MITHRIL(1273, 21, 629, 5),

		STEEL(1269, 6, 627, 3),

		IRON(1267, 1, 626, 2),

		BRONZE(1265, 1, 625, 1);

		private int pickAxeId, levelRequried, animationId, pickAxeTime;

		private PickaxeStore(int pickAxeId, int levelRequried, int animationId, int pickAxeTime) {
			this.pickAxeId = pickAxeId;
			this.levelRequried = levelRequried;
			this.animationId = animationId;
			this.pickAxeTime = pickAxeTime;
		}

		public int getPickAxeId() {
			return pickAxeId;
		}

		public int getLevelRequried() {
			return levelRequried;
		}

		public int getAnimationId() {
			return animationId;
		}

		public int getPickAxeTime() {
			return pickAxeTime;
		}
	}

}
