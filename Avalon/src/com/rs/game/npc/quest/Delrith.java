package com.rs.game.npc.quest;

import com.rs.game.Entity;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.impl.demonslayer.DelrithControler;

@SuppressWarnings("serial")
public class Delrith extends NPC {

	private DelrithControler delrith;

	public Delrith(int id, WorldTile tile, DelrithControler delrith) {
		super(id, tile, -1, true, true);
		this.delrith = delrith;
	}

	@Override
	public void sendDeath(Entity source) {
		if (delrith != null) {
			delrith = null;
		}
		if (source instanceof Player) {
			Player player = (Player) source;
			if (player.getQuestManager().get(Quests.DEMON_SLAYER).getStage() == 1) {
				player.getQuestManager().get(Quests.DEMON_SLAYER).setStage(2);
				player.setNextWorldTile(new WorldTile(3221, 3362, 0));
				player.sm("I should go tell Gypsy Aris i've killed the demon.");
			}
		}
		super.sendDeath(source);
	}

	public void disapear() {
		delrith = null;
		finish();
	}


}
