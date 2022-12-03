package com.rs.game.npc.dungeonnering;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.actions.combat.PlayerCombat;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public class Gravecreeper extends DungeonBoss {

	public static final int BURN_DELAY = 17;

	private List<BurnTile> burnedTiles;
	private long specialDelay;

	private WorldObject[][] plinths;
	private boolean[][] triggeredPlinths;

	public Gravecreeper(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
		super(id, tile, manager, reference);
		burnedTiles = new CopyOnWriteArrayList<>();
		plinths = new WorldObject[4][4];
		triggeredPlinths = new boolean[4][4];
	}

	@Override
	public double getRangePrayerMultiplier() {
		return 0.6; //rs makes it always 0.6 99% time when partialy blocked duh
	}

	@Override
	public double getMagePrayerMultiplier() {
		return 0.6;
	}
	
	private static final WeaponType[][] WEAKNESS =
		{{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK), new WeaponType(Combat.MAGIC_TYPE, PlayerCombat.FIRE_SPELL) },};

	public WeaponType[][] getWeaknessStyle() {
		return WEAKNESS;
	}

	@Override
	public void processNPC() {
		if (burnedTiles != null && !burnedTiles.isEmpty())
			processBurnAttack();
		super.processNPC();
	}

	public boolean removeBurnedTile(WorldTile center) {
		for (BurnTile bTile : burnedTiles) {
			if (bTile.center.getX() == center.getX() && bTile.center.getY() == center.getY()) {
				burnedTiles.remove(bTile);
				return true;
			}
		}
		return false;
	}

	private void processBurnAttack() {
		for (BurnTile bTile : burnedTiles) {
			bTile.cycles++;
			if (!bTile.permenant && bTile.cycles == BURN_DELAY) {
				burnedTiles.remove(bTile);
				continue;
			}
			if (bTile.cycles % 2 != 0)
				continue;
			bTile.sendGraphics();
			for (Entity t : getPossibleTargets()) {
				Player p2 = (Player) t;
				tileLoop: for (WorldTile tile : bTile.tiles) {
					if (p2.getX() != tile.getX() || p2.getY() != tile.getY())
						continue tileLoop;
					p2.applyHit(new Hit(this, (int) Utils.random(getMaxHit() * .1, getMaxHit() * .25), HitLook.REGULAR_DAMAGE));
					p2.getPrayer().drainPrayer(20);
					if (p2.getPrayer().hasPrayersOn()) {
						p2.getPrayer().closeAllPrayers();
					}
				}
			}
		}
	}

	@Override
	public void sendDeath(final Entity source) {
		if (specialDelay != -2) {
			setHitpoints(1);
			specialDelay = -1;
			return;
		}
		burnedTiles.clear();
		super.sendDeath(source);
	}

	private static final String[] SPECIAL_SHOUTS =
	{ "Burrrrrry", "Digggggg", "Brrainnns" };

	public void useSpecial() {
		WorldTile walkTo = getNearestPlinch();
		if (walkTo == null)
			return;
		getManager().setTemporaryBoss(this);
		setCantInteract(true);
		resetReceivedDamage();
		setNextFaceEntity(null);
		setForceWalk(walkTo);
		setNextForceTalk(new ForceTalk(SPECIAL_SHOUTS[specialDelay == -1 ? 2 : Utils.random(SPECIAL_SHOUTS.length)]));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				animate(new Animation(14507));
				activatePlinths();
				activateTombs();
				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {
						//finish(); no cuz it stops process burned
						setNextNPCTransformation(1957);
						if (specialDelay == -1)
							specialDelay = -2;
						WorldTasksManager.schedule(new WorldTask() {

							@Override
							public void run() {
								if (getManager().isDestroyed())
									return;
								setNextWorldTile(getManager().getTile(getReference(), 3 + Utils.random(4) * 3, 3 + Utils.random(4) * 3));
								//setLocation(getManager().getTile(getReference(), 3 + Utils.random(4) * 3, 3 + Utils.random(4) * 3));
								setNextNPCTransformation(11708);
								//	spawn();
								animate(new Animation(14506));
								WorldTasksManager.schedule(new WorldTask() {

									@Override
									public void run() {
										if (getManager().isDestroyed())
											return;
										setCantInteract(false);
										triggerPlinths();
										getManager().setTemporaryBoss(null);
									}
								});

							}

						}, 8);
					}
				}, 1);
			}

		}, Utils.getDistance(this, walkTo));
	}

	public WorldTile getNearestPlinch() {
		int distance = Integer.MAX_VALUE;
		WorldTile p = null;
		for (int x = 0; x < plinths.length; x++) {
			for (int y = 0; y < plinths[x].length; y++) {
				WorldObject plinth = getManager().getObjectWithType(getReference(), 22, 3 + x * 3, 3 + y * 3);
				if (plinth == null)
					continue;
				int d = Utils.getDistance(this, plinth);
				if (d >= distance)
					continue;
				distance = d;
				p = plinth;
			}
		}
		return p;
	}

	public void triggerPlinths() {
		List<Entity> possibleTargets = getPossibleTargets();
		for (int x = 0; x < plinths.length; x++) {
			for (int y = 0; y < plinths[x].length; y++) {
				if (plinths[x][y] == null)
					continue;
				WorldTile altarLoc = getManager().getTile(getReference(), TOMB_LOC_POS_2[y][x][0], TOMB_LOC_POS_2[y][x][1]);
				World.sendGraphics(this, new Graphics(2751), altarLoc);
				if (!triggeredPlinths[x][y]) {
					triggeredPlinths[x][y] = true;
					createBurnTiles(plinths[x][y], true);
					for (Entity t : possibleTargets) {
						if (Utils.isOnRange(t.getX(), t.getY(), t.getSize(), altarLoc.getX(), altarLoc.getY(), 1, 2))
							t.applyHit(new Hit(this, Utils.random((int) (t.getMaxHitpoints() * 0.1)) + 1, HitLook.MAGIC_DAMAGE));
					}
				}
			}
		}
	}

	public boolean cleanseTomb(Player player, WorldObject tomb) {
		int[] pos = getManager().getRoomPos(tomb);
		for (int x = 0; x < plinths.length; x++) {
			for (int y = 0; y < plinths[x].length; y++) {
				if (TOMB_LOC_POS_2[y][x][0] == pos[0] && TOMB_LOC_POS_2[y][x][1] == pos[1]) {
					player.lock(1);
					player.animate(new Animation(645));
					player.getPackets().sendGameMessage("Blessing the grave costs prayer points, but evil retreats.");
					player.getPrayer().drainPrayer(10);
					cleanseTomb(x, y);
					return false;
				}
			}
		}
		return true;
	}

	public void cleanseTomb(int x, int y) {
		if (plinths[x][y] != null) {
			World.removeObject(plinths[x][y]);
			World.sendGraphics(this, new Graphics(2320), plinths[x][y]);
			if (triggeredPlinths[x][y]) {
				removeBurnedTile(plinths[x][y]);
				triggeredPlinths[x][y] = false;
			}
			plinths[x][y] = null;
		}
	}

	private static final int[][][] TOMB_LOC_POS_2 =
	{
	{
	{ 2, 2 },
	{ 6, 2 },
	{ 9, 2 },
	{ 13, 2 } },
	{
	{ 2, 6 },
	{ 7, 7 },
	{ 8, 7 },
	{ 13, 6 } },
	{
	{ 2, 9 },
	{ 7, 8 },
	{ 8, 8 },
	{ 13, 9 } },
	{
	{ 2, 13 },
	{ 6, 13 },
	{ 9, 13 },
	{ 13, 13 } } };

	public void activateTombs() {
		for (int x = 0; x < plinths.length; x++) {
			for (int y = 0; y < plinths[x].length; y++) {
				if (plinths[x][y] == null)
					continue;
				WorldObject altar = getManager().getObjectWithType(getReference(), 10, TOMB_LOC_POS_2[y][x][0], TOMB_LOC_POS_2[y][x][1]);
				if (altar == null)
					continue;
				WorldObject activeAltar = new WorldObject(altar);
				activeAltar.setId(altar.getId() + 1);
				World.spawnObjectTemporary(activeAltar, 7000);
				World.sendGraphics(this, new Graphics(2752), activeAltar);
			}
		}
	}

	public void activatePlinths() {
		for (int x = 0; x < plinths.length; x++) {
			for (int y = 0; y < plinths[x].length; y++) {
				if (plinths[x][y] != null)
					continue;
				WorldObject plinth = getManager().getObjectWithType(getReference(), 22, 3 + x * 3, 3 + y * 3);
				if (plinth == null)
					continue;
				if (plinths[x][y] == null && Utils.random(15) < getManager().getParty().getTeam().size() * 3) {
					WorldObject activePlinth = new WorldObject(plinth);
					activePlinth.setId(plinth.getId() + 1);
					World.spawnObject(activePlinth);
					plinths[x][y] = activePlinth;
				}

			}
		}
	}

	public void createBurnTiles(WorldTile tile, boolean permenant) {
		burnedTiles.add(new BurnTile(tile, permenant));
	}

	public void createBurnTiles(WorldTile tile) {
		createBurnTiles(tile, false);
	}

	public long getSpecialDelay() {
		return specialDelay;
	}

	public void setSpecialDelay(long specialDelay) {
		this.specialDelay = specialDelay;
	}

	public class BurnTile {
		private int cycles;
		private boolean permenant;
		private final WorldTile center;
		private final WorldTile[] tiles;

		public BurnTile(WorldTile center, boolean permenant) {
			this.center = center;
			this.permenant = permenant;
			tiles = new WorldTile[9];
			int index = 0;
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {
					tiles[index++] = center.transform(x, y, 0);
				}
			}
		}

		public void sendGraphics() {
			World.sendGraphics(null, new Graphics(133), center);
		}
	}
}
