package com.rs.game.npc.dungeonnering;

import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public final class SkeletalAdventurer extends DungeonBoss {

	private int npcId;

	public SkeletalAdventurer(int id, WorldTile tile, DungeonManager manager, RoomReference reference, double multiplier) {
		super(id, tile, manager, reference, multiplier);
		npcId = id;
	}

	@Override
	public void processNPC() {
		if (isDead())
			return;
		super.processNPC();
		if (Utils.random(15) == 0)
			setNextNPCTransformation(npcId + Utils.random(3));
	}

	@Override
	public void sendDeath(final Entity source) {
		final NPCCombatDefinitions defs = getCombatDefinitions();
		resetWalkSteps();
		getCombat().removeTarget();
		animate(null);
		boolean last = true;
		List<Integer> npcsIndexes = World.getRegion(getRegionId()).getNPCsIndexes();
		if (npcsIndexes != null) {
			for (int npcIndex : npcsIndexes) {
				NPC npc = World.getNPCs().get(npcIndex);
				if (npc == this || npc.isDead() || npc.hasFinished() || !npc.getName().startsWith("Skeletal "))
					continue;
				last = false;
				break;
			}
		}
		final boolean l = last;
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					animate(new Animation(defs.getDeathEmote()));
				} else if (loop >= defs.getDeathDelay()) {
					/*if (source instanceof Player)
						((Player) source).getControlerManager().processDeath(SkeletalAdventurer.this);*///TODO
					if (l)
						drop();
					reset();
					finish();
					stop();
				}
				loop++;
			}
		}, 0, 1);
		if (last)
			getManager().openStairs(getReference());
	}

	@Override
	public int getMaxHit() {
		return super.getMaxHit() * 2;
	}

	public int getPrayer() {
		return this.getId() - npcId;
	}

	@Override
	public void handleHit(Hit hit) {
		if ((hit.getLook() == HitLook.MELEE_DAMAGE && getPrayer() == 0) || (hit.getLook() == HitLook.RANGE_DAMAGE && getPrayer() == 1) || (hit.getLook() == HitLook.MAGIC_DAMAGE && getPrayer() == 2))
			hit.setDamage(0);
		super.handleHit(hit);
	}

	/*@Override
	public void drop() {
		Drops drops = NPCDrops.getDrops(11985);
		if (drops == null)
			return;
		Drop drop = drops.getDrop(Drops.COMMOM, Double.MAX_VALUE); //to make 100% chance
		if (drop == null) //shouldnt
			return;
		List<Player> players = getManager().getParty().getTeam();
		if (players.size() == 0)
			return;
		Player luckyPlayer = players.get(Utils.random(players.size()));
		Item item = sendDrop(luckyPlayer, drop);
		luckyPlayer.getPackets().sendGameMessage("You received: " + item.getAmount() + " " + item.getName() + ".");
		for (Player p2 : players) {
			if (p2 == luckyPlayer)
				continue;
			p2.getPackets().sendGameMessage("" + luckyPlayer.getDisplayName() + " received: " + item.getAmount() + " " + item.getName() + ".");
		}
	}

	@Override
	public Item sendDrop(Player player, Drop drop) {
		Item item = new Item(drop.getItemId());
		player.getInventory().addItemDrop(item.getId(), item.getAmount());
		int tier = (item.getId() - 16867) / 2 + 1;
		player.getInventory().addItemDrop(DungeonUtils.getArrows(tier), 125);
		return item;
	}*/ //TODO

}
