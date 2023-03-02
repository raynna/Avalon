package com.rs.game.player.actions.skills.agility.impl;

import com.rs.game.Animation;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class AgilityPyramid {

	private static final int PYRAMID_STAIRCE_OBJECT = 10857, PYRAMID_STAIRCE_OBJECT2 = 10858,
			PYRAMID_WALL_OBJECT = 10865, PYRAMID_PLANK_OBJECT = 10868, PYRAMID_GAP_OBJECT = 10863,
			PYRAMID_LEDGE_OBJECT = 10860, PYRAMID_LEDGE_OBJECT3 = 10886, PYRAMID_GAP_OBJECT2 = 10884,
			PYRAMID_GAP_OBJECT3 = 10859, PYRAMID_GAP_OBJECT4 = 10861, PYRAMID_LEDGE_OBJECT2 = 10888,
			PYRAMID_REWARD_OBJECT = 10851, PYRAMID_EXIT_OBJECT = 10855, PYRAMID_JUMP_OBJECT = 10882; // pyramid course objects

	private static boolean hotSpot(Player player, int hotX, int hotY) {
		if (player.getX() == hotX && player.getY() == hotY) {
			return true;
		}
		return false;
	}

	private static boolean checkLevel(Player player, int objectId) {
		if (getLevelRequired(objectId) > player.getSkills().getLevel(Skills.AGILITY)) {
			player.message("You need atleast " + getLevelRequired(objectId) + " agility to do this.");
			return true;
		}
		return false;
	}

	private static int getLevelRequired(int objectId) {
		switch (objectId) {
		case PYRAMID_WALL_OBJECT:
		case PYRAMID_STAIRCE_OBJECT:
		case PYRAMID_PLANK_OBJECT:
		case PYRAMID_GAP_OBJECT:
			return 30;
		}
		return -1;
	}

	private static int getXp(int objectId) {
		switch (objectId) {
		case PYRAMID_WALL_OBJECT:
			return 8;
		case PYRAMID_GAP_OBJECT:
		case PYRAMID_GAP_OBJECT2:
		case PYRAMID_GAP_OBJECT3:
		case PYRAMID_GAP_OBJECT4:
			return 52;
		case PYRAMID_PLANK_OBJECT:
		case PYRAMID_LEDGE_OBJECT:
		case PYRAMID_LEDGE_OBJECT2:
		case PYRAMID_LEDGE_OBJECT3:
			return 56;
		}
		return -1;
	}

	public static boolean pyramidCourse(final Player player, final WorldObject object) {
		int objectId = object.getId();
		switch (objectId) {
		case PYRAMID_STAIRCE_OBJECT:
		case PYRAMID_STAIRCE_OBJECT2:
			if (checkLevel(player, objectId))
				return false;
				if (object.getX() == 3360 && object.getY() == 2837) {
					player.useStairs(828, new WorldTile(3041, 4695, 2), 1, 2);
				} else if (object.getX() == 3042 && object.getY() == 4695) {
					player.useStairs(828, new WorldTile(3043, 4697, 3), 1, 2);
				} else if (player.getPlane() == 0) {
					player.getTemporaryAttributtes().put("PENDING_PYRAMID_REWARD", Boolean.FALSE);
					player.useStairs(828, new WorldTile(player.getX() -2, player.getY() + 3, 1), 1, 2);
				} else if (player.getPlane() == 1) {
					player.useStairs(828, new WorldTile(player.getX(), player.getY() + 3, 2), 1, 2);
				} else if (player.getPlane() == 2) {
					player.useStairs(828, new WorldTile(player.getX(), player.getY() + 3, 3), 1, 2);
				} else if (player.getPlane() == 3) {
					player.useStairs(828, new WorldTile(player.getX(), player.getY() + 3, 4), 1, 2);
				}
			return true;
		case PYRAMID_EXIT_OBJECT:
			player.message("You journey back to the bottom of the pyramid.");
			player.getSkills().addXp(Skills.AGILITY, 150);
			player.setNextWorldTile(new WorldTile(3364, 2830, 0));
			return true;
		case PYRAMID_REWARD_OBJECT:
			if (player.getTemporaryAttributtes().get("PENDING_PYRAMID_REWARD") == Boolean.FALSE) {
				player.getMoneyPouch().addMoney(10000, false);
				player.message("You recieve a reward for reaching the top of the pyramid.");
				player.getTemporaryAttributtes().put("PENDING_PYRAMID_REWARD", Boolean.TRUE);
			} else {
				player.message("You have already claimed your reward, complete the pyramid again.");
			}
			return true;
		case PYRAMID_LEDGE_OBJECT:
		case PYRAMID_LEDGE_OBJECT2:
		case PYRAMID_LEDGE_OBJECT3:
			if (checkLevel(player, objectId))
				return false;
			if (hotSpot(player, 3363, 2851)) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 3) {
							player.getSkills().addXp(Skills.AGILITY, getXp(object.getId()));
							player.getAppearence().setRenderEmote(-1);
							player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
							stop();
						} else if (loop == 0) {
							player.lock(3);
							player.setRun(false);
							player.addWalkSteps(player.getX() + 5, player.getY(), 5, false);
							player.getAppearence().setRenderEmote(1427);
							player.message("You attempt to cross the ledge...");
							loop++;
						} else {
							loop++;
						}
					}
				}, 0, 1);
			} else if (hotSpot(player, 3372, 2841)) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 3) {
							player.getSkills().addXp(Skills.AGILITY, getXp(object.getId()));
							player.getAppearence().setRenderEmote(-1);
							player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
							stop();
						} else if (loop == 0) {
							player.lock(3);
							player.setRun(false);
							player.addWalkSteps(player.getX(), player.getY() - 5, 5, false);
							player.getAppearence().setRenderEmote(1427);
							player.message("You attempt to cross the ledge...");
							loop++;
						} else {
							loop++;
						}
					}
				}, 0, 1);
			} else if (hotSpot(player, 3359, 2842)) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 3) {
							player.getSkills().addXp(Skills.AGILITY, getXp(object.getId()));
							player.getAppearence().setRenderEmote(-1);
							player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
							stop();
						} else if (loop == 0) {
							player.lock(3);
							player.setRun(false);
							player.addWalkSteps(player.getX(), player.getY() + 5, 5, false);
							player.getAppearence().setRenderEmote(1427);
							player.message("You attempt to cross the ledge...");
							loop++;
						} else {
							loop++;
						}
					}
				}, 0, 1);
			} else if (hotSpot(player, 3364, 2832)) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 3) {
							player.getSkills().addXp(Skills.AGILITY, getXp(object.getId()));
							player.getAppearence().setRenderEmote(-1);
							player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
							stop();
						} else if (loop == 0) {
							player.lock(3);
							player.setRun(false);
							player.addWalkSteps(player.getX() - 5, player.getY(), 5, false);
							player.getAppearence().setRenderEmote(1427);
							player.message("You attempt to cross the ledge...");
							loop++;
						} else {
							loop++;
						}
					}
				}, 0, 1);
			}
			return true;

		case PYRAMID_WALL_OBJECT:
			if (checkLevel(player, objectId))
				return false;
			if (hotSpot(player, 3354, 2848) || hotSpot(player, 3355, 2848)) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 1) {
							player.getSkills().addXp(Skills.AGILITY, getXp(object.getId()));
							player.getAppearence().setRenderEmote(-1);
							player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
							stop();
						} else {
							player.setNextWorldTile(new WorldTile(player.getX(), player.getY() + 2, player.getPlane()));
							player.message("you attempt to climb the wall...");
							loop++;
						}
					}
				}, 0, 1);
			} else if (hotSpot(player, 3371, 2834) || hotSpot(player, 3371, 2833)) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 1) {
							player.getSkills().addXp(Skills.AGILITY, getXp(object.getId()));
							player.getAppearence().setRenderEmote(-1);
							player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
							stop();
						} else {
							player.setNextWorldTile(new WorldTile(player.getX() - 2, player.getY(), player.getPlane()));
							player.message("you attempt to climb the wall...");
							loop++;
						}
					}
				}, 0, 1);
			} else if (hotSpot(player, 3041, 4701) || hotSpot(player, 3041, 4702)) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 1) {
							player.getSkills().addXp(Skills.AGILITY, getXp(object.getId()));
							player.getAppearence().setRenderEmote(-1);
							player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
							stop();
						} else {
							player.setNextWorldTile(new WorldTile(player.getX() + 2, player.getY(), player.getPlane()));
							player.message("you attempt to climb the wall...");
							loop++;
						}
					}
				}, 0, 1);
			} else if (hotSpot(player, 3048, 4694) || hotSpot(player, 3048, 4693)) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 1) {
							player.getSkills().addXp(Skills.AGILITY, getXp(object.getId()));
							player.getAppearence().setRenderEmote(-1);
							player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
							stop();
						} else {
							player.setNextWorldTile(new WorldTile(player.getX() - 2, player.getY(), player.getPlane()));
							player.message("you attempt to climb the wall...");
							loop++;
						}
					}
				}, 0, 1);
			} else if (hotSpot(player, 3359, 2838) || hotSpot(player, 3358, 2838)) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 1) {
							player.getSkills().addXp(Skills.AGILITY, getXp(object.getId()));
							player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
							stop();
						} else {
							player.setNextWorldTile(new WorldTile(player.getX(), player.getY() + 2, player.getPlane()));
							player.message("you attempt to climb the wall...");
							loop++;
						}
					}
				}, 0, 1);
			}
			player.animate(new Animation(839));
			return true;
		case PYRAMID_GAP_OBJECT:
		case PYRAMID_GAP_OBJECT2:
		case PYRAMID_GAP_OBJECT3:
		case PYRAMID_GAP_OBJECT4:
			if (checkLevel(player, objectId))
				return false;
			if (hotSpot(player, 3363, 2851)) {
				player.lock(2);
//				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(3368, 2851, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.animate(new Animation(3067));
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3357, 2836) || hotSpot(player, 3356, 2836)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(player.getX(), player.getY() + 5, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3046, 4699) || hotSpot(player, 3047, 4699)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(player.getX(), player.getY() - 3, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3048, 4697) || hotSpot(player, 3049, 4697)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(player.getX(), player.getY() - 3, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3041, 4696) || hotSpot(player, 3040, 4696)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(player.getX(), player.getY() + 3, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3357, 2846) || hotSpot(player, 3356, 2846)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(player.getX(), player.getY() + 3, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3359, 2849) || hotSpot(player, 3359, 2850)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(player.getX() + 5, player.getY(), player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3372, 2832)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(3367, 2832, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3364, 2832)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(3359, 2832, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3357, 2836)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(3357, 2841, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3357, 2846)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(3357, 2849, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3359, 2849)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(3366, 2849, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3366, 2834) || hotSpot(player, 3366, 2833)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(3363, 2834, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3359, 2842)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(3359, 2847, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3370, 2843) || hotSpot(player, 3371, 2843)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(3370, 2838, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							player.getSkills().addXp(Skills.AGILITY, getXp(object.getId()));
							stop();
						}
						stage++;
					}

				}, 0, 1);
			} else if (hotSpot(player, 3372, 2841)) {
				player.lock(2);
				player.animate(new Animation(3067));
				final WorldTile toTile = new WorldTile(3372, 2836, player.getPlane());
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 1) {
							player.setNextWorldTile(toTile);
							player.message("You successfully made it to the other side!");
							player.getSkills().addXp(Skills.AGILITY, getXp(object.getId()));
							stop();
						}
						stage++;
					}

				}, 0, 1);
			}
			return true;

		case PYRAMID_PLANK_OBJECT:
			if (checkLevel(player, objectId))
				return false;
			if (hotSpot(player, 3375, 2846)) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 3) {
							player.lock(3);
							player.getSkills().addXp(Skills.AGILITY, getXp(object.getId()));
							player.getAppearence().setRenderEmote(-1);
							player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
							stop();
						} else if (loop == 0) {
							player.setRunHidden(false);
							player.addWalkSteps(player.getX(), player.getY() - 6, 6, false);
							player.getAppearence().setRenderEmote(1637);
							player.message("You attempt to cross the plank...");
							loop++;
						} else {
							loop++;
						}
					}
				}, 0, 1);
			}
			if (hotSpot(player, 3371, 2835)) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 3) {
							player.lock(3);
							player.getSkills().addXp(Skills.AGILITY, getXp(object.getId()));
							player.getAppearence().setRenderEmote(-1);
							player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
							stop();
						} else if (loop == 0) {
							player.setRunHidden(false);
							player.addWalkSteps(player.getX() - 6, player.getY(), 6, false);
							player.getAppearence().setRenderEmote(1637);
							player.message("You attempt to cross the plank...");
							loop++;
						} else {
							loop++;
						}
					}
				}, 0, 1);
			}
			return false;
		case PYRAMID_JUMP_OBJECT:
			if (checkLevel(player, objectId))
				return false;
			if (hotSpot(player, 3372, 2832) || hotSpot(player, 3372, 2831)) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 1) {
							player.lock(3);
							player.getSkills().addXp(Skills.AGILITY, getXp(object.getId()));
							player.getAppearence().setRenderEmote(-1);
							player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
							final WorldTile toTile = new WorldTile(player.getX() - 6, player.getY(), player.getPlane());
							player.setNextWorldTile(toTile);
							stop();
						} else if (loop == 0) {
							player.setRunHidden(false);
							player.animate(new Animation(3067));
							player.message("You jump accross the gap...");
							loop++;
						} else {
							loop++;
						}
					}
				}, 0, 1);
			}
		}
		return false;
	}

}
