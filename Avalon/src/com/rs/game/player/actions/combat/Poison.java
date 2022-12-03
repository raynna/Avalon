package com.rs.game.player.actions.combat;

import java.io.Serializable;

import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public final class Poison implements Serializable {

	private static final long serialVersionUID = -6324477860776313690L;

	private transient Entity entity;
	private int poisonDamage;
	public int poisonCount;

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Entity getEntity() {
		return entity;
	}

	public void makePoisoned(int startDamage) {
		if (poisonDamage > startDamage)
			return;
		if (entity instanceof Player) {
			Player player = ((Player) entity);
			if (player.getPoisonImmune() > Utils.currentTimeMillis())
				return;
			player.getPackets().sendGameMessage("You are now poisoned.");
		}
		poisonCount = 30;
		poisonDamage = startDamage;
		refresh();
	}

	public void processPoison() {
		if (!entity.isDead() && isPoisoned()) {
			if (poisonCount > 0) {
				poisonCount--;
				return;
			}
			boolean heal = false;
			if (entity instanceof Player) {
				Player player = ((Player) entity);
				if (player.getInterfaceManager().containsScreenInter())
					return;
				if (player.getAuraManager().hasPoisonPurge())
					heal = true;
				if (player.healMode)
					heal = true;

			}
			entity.applyHit(new Hit(entity, poisonDamage, heal ? HitLook.HEALED_DAMAGE : HitLook.POISON_DAMAGE));
			poisonDamage -= 5;
			if (isPoisoned()) {
				poisonCount = 30;
				return;
			}
			reset();
		}
	}

	public void reset() {
		poisonDamage = 0;
		poisonCount = 0;
		refresh();
	}

	public enum AntiDotes {
		ANTIDOTE_PLUSPLUS_FLASK_6(23591), ANTIDOTE_PLUSPLUS_FLASK_5(23593), ANTIDOTE_PLUSPLUS_FLASK_4(23595), 
		ANTIDOTE_PLUSPLUS_FLASK_3(23597), ANTIDOTE_PLUSPLUS_FLASK_2(23599), ANTIDOTE_PLUSPLUS_FLASK_1(23601),
		ANTIDOTE_PLUSPLUS_4(5952), ANTIDOTE_PLUSPLUS_3(5954), ANTIDOTE_PLUSPLUS_2(5956), ANTIDOTE_PLUSPLUS_1(5958),
		ANTIDOTE_PLUS__FLASK_6(23579), ANTIDOTE_PLUS__FLASK_5(23581), ANTIDOTE_PLUS__FLASK_4(23583),
		ANTIDOTE_PLUS__FLASK_3(23585), ANTIDOTE_PLUS__FLASK_2(23587), ANTIDOTE_PLUS__FLASK_1(23589),
		ANTIDOTE_PLUS_4(5943), ANTIDOTE_PLUS_3(5945), ANTIDOTE_PLUS_2(5947), ANTIDOTE_PLUS_1(5949), 
		SANFEW_SERUM_FLASK_6(23567), SANFEW_SERUM_FLASK_5(23569), SANFEW_SERUM_FLASK_4(23571), 
		SANFEW_SERUM_FLASK_3(23573), SANFEW_SERUM_FLASK_2(23575), SANFEW_SERUM_FLASK_1(23577), 
		SANFEW_SERUM_4(10925), SANFEW_SERUM_3(10927), SANFEW_SERUM_2(10929), SANFEW_SERUM_1(10931), 
		SUPER_ANTIPOISON_FLASK_6(23327), SUPER_ANTIPOISON_FLASK_5(23329), SUPER_ANTIPOISON_FLASK_4(23331), 
		SUPER_ANTIPOISON_FLASK_3(23333), SUPER_ANTIPOISON_FLASK_2(23335), SUPER_ANTIPOISON_FLASK_1(23337), 
		SUPER_ANTIPOISON_4(2448), SUPER_ANTIPOISON_3(181), SUPER_ANTIPOISON_2(183), SUPER_ANTIPOISON_1(185), 
		ANTI_POISON_FLASK_6(23315), ANTI_POISON_FLASK_5(23317), ANTI_POISON_FLASK_4(23319), 
		ANTI_POISON_FLASK_3(23321), ANTI_POISON_FLASK_2(23323), ANTI_POISON_FLASK_1(23325), 
		ANTI_POISON_4(2446), ANTI_POISON_3(175), ANTI_POISON_2(177), ANTI_POISON_1(179);

		private int id;

		private AntiDotes(int id) {
			this.id = id;
		}

		public int getItemId() {
			return id;
		}
	}

	public void refresh() {
		if (entity instanceof Player) {
			Player player = ((Player) entity);
			player.getPackets().sendConfig(102, isPoisoned() ? 1 : 0);
		}
	}

	public boolean isPoisoned() {
		return poisonDamage >= 1;
	}
}
