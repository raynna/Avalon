package com.rs.game.minigames.godwars.zamorak;

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
public class GodwarsZammorakFaction extends NPC {

	public GodwarsZammorakFaction(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(6000);// Lurable boss
	}

	@Override
	public ArrayList<Entity> getPossibleTargets() {
		if (!withinDistance(new WorldTile(2881, 5306, 0), 200))
			return super.getPossibleTargets();
		else {
			ArrayList<Entity> targets = getPossibleTargets(true, true);
			ArrayList<Entity> targetsCleaned = new ArrayList<Entity>();
			for (Entity t : targets) {
				if (t instanceof GodwarsZammorakFaction || (t instanceof Player && hasGodItem((Player) t)) || t instanceof Familiar)
					continue;
				targetsCleaned.add(t);
			}
			return targetsCleaned;
		}
	}


	public static boolean hasGodItem(Player player) {
		for (Item item : player.getEquipment().getItems().getContainerItems()) {
			if (item == null || item.getId() == -1)
				continue; // shouldn't happen
			String name = item.getDefinitions().getName().toLowerCase();
			if (name.contains("zamorak coif") || name.contains("zamorak mitre") || name.contains("zamorak full helm")
					|| name.contains("zamorak halo") || name.contains("torva full helm") || name.contains("pernix cowl")
					|| name.contains("virtus mask"))
				return true;
			else if (name.contains("zamorak cape") || name.contains("zamorak cloak"))
				return true;
			else if (name.contains("unholy symbol") || name.contains("zamorak stole"))
				return true;
			else if (name.contains("illuminated unholy book") || name.contains("unholy book")
					|| name.contains("zamorak kiteshield"))
				return true;
			else if (name.contains("zamorak arrows"))
				return true;
			else if (name.contains("zamorak godsword") || name.contains("zamorakian spear")
					|| name.contains("zamorak staff") || name.contains("zamorak crozier")
					|| name.contains("zaryte Bow"))
				return true;
			else if (name.contains("zamorak d'hide") || name.contains("zamorak platebody")
					|| name.contains("torva platebody") || name.contains("pernix body")
					|| name.contains("virtus robe top"))
				return true;
			else if (name.contains("zamorak robe") || name.contains("zamorak robe bottom ")
					|| name.contains("zamorak chaps") || name.contains("zamorak platelegs")
					|| name.contains("zamorak plateskirt") || name.contains("torva platelegs")
					|| name.contains("pernix chaps") || name.contains("virtus robe legs"))
				return true;
			else if (name.contains("zamorak vambraces"))
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
							godControler.incrementKillCount(3);
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
