package com.rs.game.npc.dungeonnering;

import java.util.ArrayList;
import java.util.List;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.Region;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.content.dungeoneering.DungeonConstants;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public final class Stomp extends DungeonBoss {

	private static final int IVULNERABLE_TIMER = 27; //16.5 sec
	private int stage;
	private int count;
	private int lodeStoneType;
	private boolean[] lodestones;

	private List<int[]> shadows;

	public Stomp(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
		super(id, tile, manager, reference);
		setCantFollowUnderCombat(true); //force cant walk
		lodestones = new boolean[2];
		shadows = new ArrayList<int[]>();
	}

	@Override
	public void processNPC() {
		if (getId() == 9781)
			animate(new Animation(13460));
		else {
			if (count > 0) {
				if (count == IVULNERABLE_TIMER - 3) {
					List<Entity> possibleTargets = getPossibleTargets();
					for (int[] s : shadows) {
						WorldObject object = getManager().spawnObjectTemporary(getReference(), 49268, 10, 0, s[0], s[1], 30000);
						for (Entity target : possibleTargets)
							if (target.getX() == object.getX() && target.getY() == object.getY())
								target.applyHit(new Hit(this, 1 + Utils.random((int) (target.getMaxHitpoints() * 0.8)), HitLook.REGULAR_DAMAGE));
					}
				}
				if (count == 1) {
					setCantInteract(false);
					if (lodestones[0] == true && lodestones[1] == true) {
						stage++;
						if (stage == 3) {
							setHitpoints(0);
							sendDeath(this);
							destroyExistingDebris();
						}
						for (Entity target : getPossibleTargets()) {
							if (target instanceof Player) {
								Player player = (Player) target;
								player.getPackets().sendGameMessage("The portal weakens, harming Stomp!");
							}
						}
					} else
						applyHit(new Hit(this, (int) (getMaxHitpoints() * 0.25), HitLook.HEALED_DAMAGE));
					lodestones[0] = lodestones[1] = false;
					refreshLodestones();
					removeCrystals();
				}

				count--;
				return;
			}

			super.processNPC();
		}
	}

	private static final WeaponType[][] WEAKNESS =
		{
		{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK), new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.CRUSH_ATTACK) }};

	public WeaponType[][] getWeaknessStyle() {
		return WEAKNESS;
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return stage == 2 ? 0.6 : 0;
	}

	@Override
	public double getMagePrayerMultiplier() {
		return stage == 2 ? 0.6 : 0;
	}

	@Override
	public double getRangePrayerMultiplier() {
		return stage == 2 ? 0.6 : 0;
	}

	public void refreshLodestones() {
		for (int i = 0; i < lodestones.length; i++)
			refreshLodestone(i);
	}

	private static final int[] CRYSTAL =
		{ 15752, 15751, 15750 };

	public void refreshLodestone(int index) {

		int id = count == IVULNERABLE_TIMER ? (49274 + lodeStoneType * 2 + index) : lodestones[index] ? lodeStoneType == 0 ? 51099 : lodeStoneType == 1 ? 51601 : 51647 : (49270 + index);

		getManager().spawnObject(getReference(), id, 10, 2, index == 1 ? 10 : 5, 10);
	}

	public void chargeLodeStone(Player player, int index) {
		if (lodestones[index] || count <= 1)
			return;
		if (player.getInventory().containsItem(CRYSTAL[lodeStoneType], 1)) {
			player.lock(1);
			player.animate(new Animation(833));
			lodestones[index] = true;
			player.getInventory().deleteItem(CRYSTAL[lodeStoneType], 1);
			player.getPackets().sendGameMessage("You place the crystal into the device and it powers up.");
			refreshLodestone(index);
			if (lodestones[0] == true && lodestones[1] == true) {
				for (Entity target : getPossibleTargets()) {
					if (target instanceof Player) {
						Player p2 = (Player) target;
						p2.getPackets().sendGameMessage("The lodestone has been fully activated.");
					}
				}
			}
		} else
			player.getPackets().sendGameMessage("You need a " + ItemDefinitions.getItemDefinitions(CRYSTAL[lodeStoneType]).getName().toLowerCase() + " to activate this lodestone.");

	}

	public void charge() {
		count = IVULNERABLE_TIMER;
		lodeStoneType = Utils.random(3);
		refreshLodestones();
		animate(new Animation(13451));
		gfx(new Graphics(2407));
		setCantInteract(true);
		for (Entity target : getPossibleTargets()) {
			if (target instanceof Player) {
				Player player = (Player) target;
				player.getPackets().sendGameMessage("Stomp enters a defensive stance. It is currently invulnerable, but no longer protecting the portal's lodestones!");
			}
		}
		destroyExistingDebris();
		for (int count = 0; count < 11; count++) {
			l: for (int i = 0; i < DungeonConstants.SET_RESOURCES_MAX_TRY; i++) {
				int x = 2 + Utils.getRandom(12);
				int y = 2 + Utils.getRandom(9);
				if (containsShadow(x, y) || !getManager().isFloorFree(getReference(), x, y))
					continue;
				shadows.add(new int[]
						{ x, y });
				getManager().spawnObject(getReference(), 49269, 10, 0, x, y);
				break l;
			}
		}

		for (int count = 0; count < 2; count++) {
			l: for (int i = 0; i < DungeonConstants.SET_RESOURCES_MAX_TRY; i++) {
				int x = 2 + Utils.getRandom(12);
				int y = 2 + Utils.getRandom(9);
				if (containsShadow(x, y) || !getManager().isFloorFree(getReference(), x, y))
					continue;
				getManager().spawnItem(getReference(), new Item(CRYSTAL[lodeStoneType]), x, y);
				break l;
			}
		}
	}

	/*
	 * if wasnt destroyed yet
	 */
	public void destroyExistingDebris() {
		for (int[] s : shadows)
			getManager().removeObject(getReference(), 49269, 10, 0, s[0], s[1]);
		shadows.clear();
	}

	public void removeCrystals() {
		Region region = World.getRegion(getRegionId());
		if (region.getGroundItems() != null) {
			for (FloorItem item : region.getGroundItems())
				if (item.getId() == CRYSTAL[lodeStoneType])
					World.removeGroundItem(null, item, false);
		}
	}

	public boolean containsShadow(int x, int y) {
		for (int[] s : shadows)
			if (s[0] == x && s[1] == y)
				return true;
		return false;
	}

	@Override
	public void sendDeath(final Entity source) {
		if (stage != 3) {
			setHitpoints(1);
			return;
		}
		final NPCCombatDefinitions defs = getCombatDefinitions();
		resetWalkSteps();
		getCombat().removeTarget();
		animate(null);
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					animate(new Animation(defs.getDeathEmote()));
				} else if (loop >= defs.getDeathDelay()) {
					/*if (source instanceof Player)
						((Player) source).getControlerManager().processNPCDeath(Stomp.this);*///TODO
					drop();
					reset();
					setCantInteract(true);
					setNextNPCTransformation(9781);
					stop();
				}
				loop++;
			}
		}, 0, 1);
		getManager().openStairs(getReference());
	}

/*    @Override
    public Item sendDrop(Player player, Drop drop) {
	Item item = new Item(drop.getItemId());
	player.getInventory().addItemDrop(item.getId(), item.getAmount());
	return item;
    }*/

	@Override
	public void setNextFaceEntity(Entity entity) {
		//this boss doesnt face
	}

	@Override
	public boolean clipedProjectile(WorldTile tile, boolean checkClose, int size) {
		//because npc is under cliped data
		return getManager().isAtBossRoom(tile);
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

}
