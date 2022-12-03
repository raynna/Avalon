package com.rs.game.npc.dungeonnering;

import java.util.ArrayList;
import java.util.List;

import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.content.dungeoneering.DungeonConstants;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.DungeonUtils;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.utils.Utils;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public class DungeonNPC extends NPC {

	private DungeonManager manager;
	private int[] bonuses;
	private boolean marked;
	private double multiplier;

	public DungeonNPC(int id, WorldTile tile, DungeonManager manager, double multiplier) {
		super(id, tile, -1, true, true);
		setManager(manager);
		setMultiplier(multiplier);
		if (getDefinitions().hasAttackOption()) {
			setForceAgressive(true);
			int level = manager.getTargetLevel(id, this instanceof DungeonBoss || this instanceof DungeonSkeletonBoss, multiplier);
			setCombatLevel(level);
			setHitpoints(getMaxHitpoints());
			resetBonuses();
		}
		setForceTargetDistance(20); //includes whole room
		setForceMultiArea(true);
	}

	public void resetBonuses() {
		bonuses = manager.getBonuses(this instanceof DungeonBoss, getCombatLevel());
	}

	/*
	 * they dont respawn anyway, and this way stomp will be fine
	 */
	@Override
	public int getRespawnDirection() {
		return getDirection();
	}

	public NPC getNPC(int id) {
		List<Integer> npcsIndexes = World.getRegion(getRegionId()).getNPCsIndexes();
		if (npcsIndexes != null) {
			for (int npcIndex : npcsIndexes) {
				NPC npc = World.getNPCs().get(npcIndex);
				if (npc.getId() == id) {
					return npc;
				}
			}
		}
		return null;
	}

	@Override
	public ArrayList<Entity> getPossibleTargets() {
		ArrayList<Entity> targets = new ArrayList<Entity>();
		for (Entity t : super.getPossibleTargets()) {
			if (DungeonConstants.isVisible(this, t, false))
				targets.add(t);
		}
		return targets;
	}

	public int getShadowType() {
		if ((getId() >= 10629 && getId() <= 10669) || (getId() >= 10469 && getId() <= 10479) || (getId() >= 10706 && getId() <= 10717) || (getId() >= 10629 && getId() <= 10669) || (getId() >= 10670 && getId() <= 10681) || (getId() >= 10364 && getId() <= 10377) || (getId() >= 10364 && getId() <= 10377))
			return DungeonConstants.NOT_VISIBLE;
		else if ((getId() >= 10196 && getId() <= 10206) || (getId() >= 12903 && getId() <= 12913))
			return DungeonConstants.VISIBLE_AND_REMOVE;
		for (int type = 0; type < DungeonConstants.FORGOTTEN_WARRIORS.length; type++) {
			for (int id : DungeonConstants.FORGOTTEN_WARRIORS[type]) {
				if (id == getId()) {
					if (type == 1) {
						return DungeonConstants.VISIBLE_AND_REMOVE;
					} else {
						return DungeonConstants.NOT_VISIBLE;
					}
				}
			}
		}
		return DungeonConstants.VISIBLE;
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		if (marked) {
			getManager().removeMark();
			marked = false;
		}
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (isUnderCombat()) {
			Entity target = getCombat().getTarget();
			RoomReference thisR = getManager().getCurrentRoomReference(this);
			RoomReference targetR = getManager().getCurrentRoomReference(target);
			if (!targetR.equals(thisR))
				getCombat().removeTarget();
		}
	}

	@Override
	public int getMaxHitpoints() {
		return getCombatLevel() * (this instanceof DungeonBoss ? 20 : 10) + 1;
	}

	public int getMaxHit() {
		return getCombatLevel();
	}

	@Override
	public int[] getBonuses() {
		return bonuses == null ? super.getBonuses() : bonuses;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	private int getBones() {
		return getName().toLowerCase().contains("dragon") ? 536 : getSize() > 1 ? 532 : 526;
	}

	@Override
	public void drop() {
		int size = getSize();
		ArrayList<Item> drops = new ArrayList<Item>();
		if (getId() != 10831 && getId() != 10821) //nature & ghost
			drops.add(new Item(getBones()));
		for (int i = 0; i < 1 + Utils.random(10); i++)
			drops.add(new Item(DungeonUtils.getFood(1 + Utils.random(8))));

		if (Utils.random(10) == 0)
			drops.add(new Item(DungeonUtils.getDagger(1 + Utils.random(5))));

		if (Utils.random(5) == 0)
			drops.add(new Item(DungeonConstants.RUNES[Utils.random(DungeonConstants.RUNES.length)], 90 + Utils.random(30)));

		if (getManager().getParty().getComplexity() >= 5 && Utils.random(5) == 0) //torm bag, 1
			drops.add(new Item(DungeonUtils.getTornBag(1 + Utils.random(10))));

		if (getManager().getParty().getComplexity() >= 3 && Utils.random(5) == 0) //ore, up to 10
			drops.add(new Item(DungeonUtils.getOre(1 + Utils.random(5)), 1 + Utils.random(10)));

		if (getManager().getParty().getComplexity() >= 2 && Utils.random(5) == 0) //branche, up to 10
			drops.add(new Item(DungeonUtils.getBranche(1 + Utils.random(5)), 1 + Utils.random(10)));

		if (getManager().getParty().getComplexity() >= 4 && Utils.random(5) == 0) //textile, up to 10
			drops.add(new Item(DungeonUtils.getTextile(1 + Utils.random(10)), 1 + Utils.random(10)));

		if (getManager().getParty().getComplexity() >= 5 && Utils.random(5) == 0) //herb, up to 10
			drops.add(new Item(DungeonUtils.getHerb(1 + Utils.random(9)), 1 + Utils.random(10)));

		if (getManager().getParty().getComplexity() >= 5 && Utils.random(5) == 0) //seed, up to 10
			drops.add(new Item(DungeonUtils.getSeed(1 + Utils.random(12)), 1 + Utils.random(10)));

		if (getManager().getParty().getComplexity() >= 5 && Utils.random(3) == 0) //charms, depending in mob size
			drops.add(new Item(DungeonConstants.CHARMS[Utils.random(DungeonConstants.CHARMS.length)], size));

		if (getManager().getParty().getComplexity() >= 2) //coins, 1000 up to 11000
			drops.add(new Item(DungeonConstants.RUSTY_COINS, 1000 + Utils.random(10001)));

		if (getManager().getParty().getComplexity() >= 3 && Utils.random(5) == 0) //essence, 10 up to 300
			drops.add(new Item(DungeonConstants.RUNE_ESSENCE, 10 + Utils.random(300)));
		if (getManager().getParty().getComplexity() >= 2 && Utils.random(5) == 0) //feather, 10 up to 300
			drops.add(new Item(DungeonConstants.FEATHER, 10 + Utils.random(300)));
		if ((getManager().getParty().getComplexity() >= 5 && Utils.random(10) == 0)) //vial, 1
			drops.add(new Item(17490));
		if ((Utils.random(10) == 0)) //anti dragon shield
			drops.add(new Item(16933));
		if ((getManager().getParty().getComplexity() >= 4 && Utils.random(10) == 0)) //bowstring, 1
			drops.add(new Item(17752));
		if ((getManager().getParty().getComplexity() >= 2 && Utils.random(10) == 0)) //fly fishing rod, 1
			drops.add(new Item(17794));
		if ((getManager().getParty().getComplexity() >= 4 && Utils.random(5) == 0)) //thread, 10 up to 300
			drops.add(new Item(17447, 10 + Utils.random(300)));

		for (Item item : drops)
			World.addGroundItem(item, new WorldTile(getCoordFaceX(size), getCoordFaceY(size), getPlane()));
	}

	public WeaponType[][] getWeaknessStyle() {
		return null;
	}

	public DungeonManager getManager() {
		return manager;
	}

	public void setManager(DungeonManager manager) {
		this.manager = manager;
	}

	public double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
}
