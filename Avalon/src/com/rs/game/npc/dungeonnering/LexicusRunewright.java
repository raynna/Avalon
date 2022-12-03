package com.rs.game.npc.dungeonnering;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.WorldTile;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public class LexicusRunewright extends DungeonBoss {

	private static final int[] TELEPORT_LOCS =
		{ 8, 7, 3, 3, 3, 12, 12, 12, 12, 3 };

	private boolean completedFirstAttack;
	private int attackStage;
	private List<TombOfLexicus> books = new CopyOnWriteArrayList<TombOfLexicus>();

	public LexicusRunewright(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
		super(id, tile, manager, reference);
	}

	@Override
	public void processHit(Hit hit) {
		int damage = hit.getDamage();
		if (damage > 0) {
			if (hit.getLook() == HitLook.MELEE_DAMAGE)
				hit.getSource().applyHit(new Hit(this, (int) (damage * .33), HitLook.REFLECTED_DAMAGE));
		}
		super.processHit(hit);
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		for (TombOfLexicus book : books)
			book.sendDeath(book);
	}

	private static final WeaponType[][] WEAKNESS =
		{
		{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK), new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK), new WeaponType(Combat.RANGE_TYPE, -1), new WeaponType(Combat.MAGIC_TYPE, 1) },};

	public WeaponType[][] getWeaknessStyle() {
		return WEAKNESS;
	}

	public void sendTeleport() {
		setCantInteract(true);
		animate(new Animation(8939));
		gfx(new Graphics(1576));
		WorldTasksManager.schedule(new WorldTask() {

			int cycles = 0;

			@Override
			public void run() {
				cycles++;
				if (cycles == 2) {
					int random = Utils.random(TELEPORT_LOCS.length);
					if (random % 1 == 0 && random != 0)
						random -= 1;
					setNextWorldTile(Utils.getFreeTile(getManager().getTile(getReference(), TELEPORT_LOCS[random], TELEPORT_LOCS[random + 1]), 2));
					animate(new Animation(8941));
					gfx(new Graphics(1577));
				} else if (cycles == 4) {
					setCantInteract(false);
					getCombat().removeTarget();
				}
			}
		}, 0, 0);
	}

	public boolean sendAlmanacArmyAttack(final Entity target) {
		final LexicusRunewright boss = this;
		boss.setNextForceTalk(new ForceTalk("Almanac Army, attack!"));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				for (int id = 0; id < 2; id++) {
					if (reachedMaxBookSize())
						break;
					WorldTile tile = getManager().getTile(getReference(), 6 + Utils.random(4), 6 + Utils.random(4));
					TombOfLexicus book = new TombOfLexicus(boss, 9856 + Utils.random(3), tile, getManager(), 0.5D);
					book.setTarget(target);
					books.add(book);
				}
			}
		}, 2);
		return true;
	}

	public void removeBook(TombOfLexicus book) {
		books.remove(book);
	}

	private boolean reachedMaxBookSize() {
		int size = getManager().getParty().getTeam().size();
		return books.size() >= (size > 3 ? 4 : size) * 3;
	}

	public boolean hasCompletedFirstAttack() {
		return completedFirstAttack;
	}

	public void setCompletedFirstAttack(boolean firstAttack) {
		this.completedFirstAttack = firstAttack;
	}

	public int getAttackStage() {
		return attackStage;
	}

	public void resetAttackStage() {
		attackStage = reachedMaxBookSize() ? 1 : 0;
	}

	public void incrementAttackStage() {
		attackStage++;
	}
}
