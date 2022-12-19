package com.rs.game.player.actions;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.impl.DoorsAndGates;
import com.rs.game.player.Player;
import com.rs.game.player.RouteEvent;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.PlayerCombat;
import com.rs.game.player.content.Canoes;

public class ObjectOption1 {

	public static final int ATTACK = 0, DEFENCE = 1, STRENGTH = 2, HITPOINTS = 3, RANGE = 4, PRAYER = 5, MAGIC = 6,
			COOKING = 7, WOODCUTTING = 8, FLETCHING = 9, FISHING = 10, FIREMAKING = 11, CRAFTING = 12, SMITHING = 13,
			MINING = 14, HERBLORE = 15, AGILITY = 16, THIEVING = 17, SLAYER = 18, FARMING = 19, RUNECRAFTING = 20,
			CONSTRUCTION = 22, HUNTER = 21, SUMMONING = 23, DUNGEONEERING = 24;

	public enum Objects {

		DUMMY(false, 45305),

		ENTER_DUNGEONEERING(true, 48496),

		CANOE_1(true, 12163),

		CANOE_2(true, 12163),

		CANOE_3(true, 12164),

		CANOE_4(true, 12165),

		UNKNOWN_DOOR1(true, 11993),

		UNKNOWN_DOOR2(true, 11994),

		CRAFTING_GUILD_DOOR(2647, CRAFTING, 40),

		COOKING_GUILD_DOOR(2712, COOKING, 32);

		private int id;
		private int[] skillReqs;
		private boolean route;

		private Objects(int id) {
			this.setId(id);
		}

		private Objects(boolean route, int id) {
			this.setId(id);
			this.setRoute(route);
		}

		private Objects(int id, int... skillReqs) {
			this.setId(id);
			this.setSkillReqs(skillReqs);
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public boolean isRoute() {
			return route;
		}

		public void setRoute(boolean route) {
			this.route = route;
		}

		public int[] getSkillReqs() {
			return skillReqs;
		}

		public void setSkillReqs(int[] skillReqs) {
			this.skillReqs = skillReqs;
		}
	}

	public static final Objects objectInfo[] = Objects.values();

	public static boolean validObject(Player player, WorldObject object) {
		for (Objects objects : objectInfo) {
			if (objects == null)
				continue;
			if (object.getId() == objects.getId()) {
				player.stopAll();
				player.faceObject(object);
				if (!objects.isRoute()) {
					handleObject(player, object, objects);
					return true;
				}
				player.setRouteEvent(new RouteEvent(object, new Runnable() {
					@Override
					public void run() {
						handleObject(player, object, objects);
					}
				}, true));
				return true;
			}
		}
		return false;
	}

	public static void handleObject(Player player, WorldObject object, Objects objects) {
		if (objects.getSkillReqs() != null) {
			for (int i = 0; i < objects.getSkillReqs().length; i += 2) {
				if (player.getSkills().getLevelForXp(objects.getSkillReqs()[i]) < objects.getSkillReqs()[i + 1]) {
					player.getPackets().sendGameMessage("You need a level of " + objects.getSkillReqs()[i + 1] + " "
							+ player.getSkills().getSkillName(objects.getSkillReqs()[i]) + " to enter this guild.");
					return;
				}
			}
		}
		switch (objects) {
		case COOKING_GUILD_DOOR:
			if (player.getY() == 3443 && player.getEquipment().getHatId() != 1949) {
				player.getPackets().sendGameMessage("You need a chef's hat to enter this guild.");
				return;
			}
			DoorsAndGates.handleDoorTemporary(player, object, 1200);
			break;
		case CRAFTING_GUILD_DOOR:
			if (player.getEquipment().getChestId() != 1005) {
				player.getPackets().sendGameMessage("You need to wear an apron to enter this guild.");
				return;
			}
			player.getPackets().sendGameMessage(
					"You " + (player.getY() == object.getY() ? "enter" : "leave") + " the crafting guild.");
			DoorsAndGates.handleDoorTemporary(player, object, 1200);
			break;
		case UNKNOWN_DOOR1:
			DoorsAndGates.handleDoor(player, object);
			break;
		case UNKNOWN_DOOR2:
			DoorsAndGates.handleCloseDoor(player, object);
			break;
		case CANOE_1:
		case CANOE_2:
		case CANOE_3:
		case CANOE_4:
			if (player.getTemporaryAttributes().get("canoe_shaped") != null
					&& (boolean) player.getTemporaryAttributes().get("canoe_shaped"))
				Canoes.openTravelInterface(player, object.getId() - 12163);
			else if (player.getTemporaryAttributes().get("canoe_chopped") != null
					&& (boolean) player.getTemporaryAttributes().get("canoe_chopped"))
				Canoes.openSelectionInterface(player);
			else
				Canoes.chopCanoeTree(player, object.getId() - 12163);
			break;
		case DUMMY:
			WorldObject loc = new WorldObject(object);
			loc.setX(loc.getX() + 5);
			loc.setY(3256);
			player.faceObject(object);
			if (player.getX() != 3207 && (player.getY() != 3255 || player.getY() != 3256)) {
				player.getPackets().sendGameMessage("You can't range this from here.");
				return;
			}
			if (player.getCombatDefinitions().getSpellId() > 0) {
				player.getPackets().sendGameMessage("You can't use magic on the archery target.");
				return;
			}
			if (player.getEquipment().getWeaponId() != 9705) {
				player.getPackets().sendGameMessage("You can only use training bow on the archery target.");
				return;
			}
			player.lock(3);
			player.gfx(new Graphics(PlayerCombat.getStartArrowProjectileId(
					player.getEquipment().getWeaponId(), player.getEquipment().getAmmoId()), 0, 100));
			World.sendObjectProjectile(player, new WorldTile(object.getX(), object.getY(), player.getPlane()),
					PlayerCombat.getStartArrowProjectileId(player.getEquipment().getWeaponId(),
							player.getEquipment().getAmmoId()));
			player.animate(new Animation(PlayerCombat.getWeaponAttackEmote(player.getEquipment().getWeaponId(),
					player.getCombatDefinitions().getAttackStyle())));
			player.getSkills().addXp(Skills.RANGE, 3);
			break;
		default:
			break;
		}
		switch (object.getId()) {
		}
	}

}
