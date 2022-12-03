package com.rs.game.minigames.godwars.bandos;

import java.util.ArrayList;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.player.Player;
import com.rs.game.player.controlers.Controler;
import com.rs.game.player.controlers.GodWars;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class GodwarsBandosFaction extends NPC {

	public GodwarsBandosFaction(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
	}

	@Override
	public ArrayList<Entity> getPossibleTargets() {
		if (!withinDistance(new WorldTile(2881, 5306, 0), 200))
			return super.getPossibleTargets();
		else {
			ArrayList<Entity> targets = getPossibleTargets(true, true);
			ArrayList<Entity> targetsCleaned = new ArrayList<Entity>();
			for (Entity t : targets) {
				if (t instanceof GodwarsBandosFaction || (t instanceof Player && hasGodItem((Player) t)) || t instanceof Familiar)
					continue;
				targetsCleaned.add(t);
			}
			return targetsCleaned;
		}
	}


	private boolean hasGodItem(Player player) {
		for (Item item : player.getEquipment().getItems().getContainerItems()) {
			if (item == null)
				continue; // shouldn't happen
			String name = item.getDefinitions().getName().toLowerCase();
			// using else as only one item should count
			if (name.contains("bandos mitre") || name.contains("bandos Full helm") || name.contains("bandos coif")
					|| name.contains("torva full helm") || name.contains("pernix cowl") || name.contains("vitus mask"))
				return true;
			else if (name.contains("bandos cloak"))
				return true;
			else if (name.contains("bandos stole"))
				return true;
			else if (name.contains("ancient mace") || name.contains("granite mace") || name.contains("bandos godsword")
					|| name.contains("bandos crozier") || name.contains("zaryte bow"))
				return true;
			else if (name.contains("bandos body") || name.contains("bandos robe top")
					|| name.contains("bandos chestplate") || name.contains("bandos platebody")
					|| name.contains("torva platebody") || name.contains("pernix body")
					|| name.contains("virtus robe top"))
				return true;
			else if (name.contains("illuminated book of war") || name.contains("book of war")
					|| name.contains("bandos kiteshield"))
				return true;
			else if (name.contains("bandos robe legs") || name.contains("bandos tassets")
					|| name.contains("bandos chaps") || name.contains("bandos platelegs")
					|| name.contains("bandos plateskirt") || name.contains("torva platelegs")
					|| name.contains("pernix chaps") || name.contains("virtus robe legs"))
				return true;
			else if (name.contains("bandos vambraces"))
				return true;
			else if (name.contains("bandos boots"))
				return true;
		}
		return false;
	}

	public void sendDeath(final Entity source) {
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
					if (source instanceof Player) {
						Player player = (Player) source;
						Controler controler = player.getControlerManager().getControler();
						if (controler != null && controler instanceof GodWars) {
							GodWars godControler = (GodWars) controler;
							godControler.incrementKillCount(0);
						}
					}
					drop();
					reset();
					setLocation(getRespawnTile());
					finish();
					if (!isSpawned())
						setRespawnTask();
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}
}
