package com.rs.game.npc.dungeonnering;

import java.util.List;

import com.rs.game.Entity;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.Drop;
import com.rs.game.npc.drops.MobRewardGenerator;
import com.rs.game.player.Player;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class DungeonBoss extends DungeonNPC {

	private RoomReference reference;

	public DungeonBoss(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
		this(id, tile, manager, reference, 1);
	}

	public DungeonBoss(int id, WorldTile tile, DungeonManager manager, RoomReference reference, double multiplier) {
		super(id, tile, manager, multiplier);
		this.setReference(reference);
		setForceAgressive(true);
		setIntelligentRouteFinder(true);
		setLureDelay(0);
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		getManager().openStairs(getReference());
	}

	@Override
	public void drop() {
		List<Player> players = getManager().getParty().getTeam();
		if (players.size() == 0)
			return;
		Player luckyPlayer = players.get(Utils.random(players.size()));
		Item[] droppedItems = MobRewardGenerator.getGenerator().generateReward(this, luckyPlayer);
		if (droppedItems == null) {
			luckyPlayer.getPackets().sendGameMessage("Null drops");

		} else {
			for (Item item : droppedItems) {
				Drop drop = new Drop(item.getId(), 100, item.getAmount(),  item.getAmount());
				sendDrop(luckyPlayer, drop);
			}
			return;
		}
	}
	
	public void sendDrop(Player player, Drop drop) {
		Item item = new Item(drop.getItemId());
		player.getInventory().addItemDrop(item.getId(), item.getAmount());
		player.getPackets().sendGameMessage("You received: " + item.getAmount() + " " + item.getName() + ".");
		List<Player> players = getManager().getParty().getTeam();
		if (players.size() == 0)
			return;
		for (Player p2 : players) {
			if (p2 == player)
				continue;
			p2.getPackets().sendGameMessage("" + player.getDisplayName() + " received: " + item.getAmount() + " " + item.getName() + ".");
		}
	}

	public RoomReference getReference() {
		return reference;
	}

	public void setReference(RoomReference reference) {
		this.reference = reference;
	}
}
