package com.rs.game.npc.dungeonnering;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.utils.Utils;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public class LakkTheRiftSplitter extends DungeonBoss {

	private static final int[] RAIN_GRAPHICS =
	{ 2581, 2583, 2585 };

	private List<PortalCluster> clusters;

	public LakkTheRiftSplitter(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
		super(id, tile, manager, reference);
		clusters = new CopyOnWriteArrayList<PortalCluster>();
	}

	@Override
	public void processNPC() {
		if (isDead() || clusters == null)
			return;
		super.processNPC();
		for (PortalCluster cluster : clusters) {
			cluster.incrementCycle();
			if (cluster.getCycle() == 35) {
				clusters.remove(cluster);
				continue;
			}
			for (Entity t : getPossibleTargets()) {
				Player player = (Player) t;
				if (cluster.getCycle() < 1)
					continue;
				if (cluster.getCycle() % 2 == 0) {
					for (WorldTile tile : cluster.getBoundary()) {
						if (player.getX() == tile.getX() && player.getY() == tile.getY()) {
							cluster.increaseEffectMultipier();
							int type = cluster.getType();
							double effectMultiplier = cluster.getEffectMultiplier();
							int maxHit = getMaxHit();

							if (type == 0)
								player.applyHit(new Hit(this, (int) (Utils.random(maxHit * .35, maxHit * .55) * effectMultiplier), HitLook.REGULAR_DAMAGE));
							else if (type == 1)
								player.getPoison().makePoisoned((int) (Utils.random(maxHit * .10, maxHit * .30) * effectMultiplier));
							else {
								int skill = Utils.random(6);
								player.getSkills().drainLevel(skill == 3 ? Skills.MAGIC : skill, (int) (Utils.random(2, 3) * effectMultiplier));
							}
						}
					}
				}
			}
			if (cluster.getCycle() % 15 == 0)
				submitGraphics(cluster, this);
		}
	}

	@Override
	public void sendDeath(Entity killer) {
		clusters.clear();
		for (Player player : getManager().getParty().getTeam()) {
			if (player.getPoison().isPoisoned())
				player.getPoison().reset();
			player.getPackets().sendGameMessage("The poison from the room clears.");
		}
		super.sendDeath(killer);
	}

	public void addPortalCluster(int type, WorldTile[] boundary) {
		PortalCluster cluster = new PortalCluster(type, boundary);
		submitGraphics(cluster, this);
		clusters.add(cluster);
	}

	public static void submitGraphics(PortalCluster cluster, NPC creator) {
		for (WorldTile tile : cluster.getBoundary())
			World.sendGraphics(creator, new Graphics((Utils.random(3) == 0 ? 1 : 0) + RAIN_GRAPHICS[cluster.getType()]), tile);
	}
	
	private static final WeaponType[][] WEAKNESS =
		{
		{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK), new WeaponType(Combat.RANGE_TYPE, -1), new WeaponType(Combat.MAGIC_TYPE, -1) },};

	public WeaponType[][] getWeaknessStyle() {
		return WEAKNESS;
	}

	private static class PortalCluster {

		private final int type;
		private final WorldTile[] boundary;
		private int cycle;
		private double effectMultiplier;

		public PortalCluster(int type, WorldTile[] boundary) {
			this.type = type;
			this.boundary = boundary;
			effectMultiplier = 0.5;
		}

		public WorldTile[] getBoundary() {
			return boundary;
		}

		public int getType() {
			return type;
		}

		public void incrementCycle() {
			cycle++;
		}

		public int getCycle() {
			return cycle;
		}

		public double getEffectMultiplier() {
			return effectMultiplier;
		}

		public void increaseEffectMultipier() {
			effectMultiplier += 0.5;
		}
	}

	public boolean doesBoundaryOverlap(List<WorldTile> boundaries) {
		for (PortalCluster cluster : clusters) {
			for (WorldTile tile : cluster.getBoundary()) {
				for (WorldTile boundary : boundaries) {
					if (tile.getX() == boundary.getX() && tile.getY() == boundary.getY())
						return true;
				}
			}
		}
		return false;
	}
}
