package com.rs.game.player.actions.combat.ancientspells;

import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class RSAncientCombatSpells {// this gets the data from spells

	/*
	 * @author -Andreas
	 * 
	 * @2018-06-21
	 */

	public static final int SMOKE_SPELL = 4, SHADOW_SPELL = 5, BLOOD_SPELL = 6, ICE_SPELL = 7, MIASMIC_SPELL = 8;

	public static enum AncientCombatSpellsStore {

		SMOKE_RUSH(28, 30.0, 150, 1978, null, 386, 385, SMOKE_SPELL),

		SHADOW_RUSH(32, 31.0, 160, 1978, null, 380, 379, SHADOW_SPELL),

		BLOOD_RUSH(24, 33.0, 170, 1978, null, 374, 373, BLOOD_SPELL),

		ICE_RUSH(20, 34.0, 180, 1978, null, 362, 361, ICE_SPELL),

		MIASMIC_RUSH(36, 35.0, 200, 10513, new Graphics(1845), 1846, 1847, MIASMIC_SPELL),

		SMOKE_BURST(30, 36.0, 190, 1979, null, -1, 389, true, SMOKE_SPELL),

		SHADOW_BURST(34, 37.0, 200, 1979, null, -1, 383, true, SHADOW_SPELL),

		BLOOD_BURST(26, 39.0, 210, 1979, null, -1, 376, true, BLOOD_SPELL),

		ICE_BURST(22, 46.0, 220, 1979, null, 366, 363, true, ICE_SPELL),

		MIASMIC_BURST(38, 42.0, 240, 10516, new Graphics(1848), -1, 1849, true, MIASMIC_SPELL),

		SMOKE_BLITZ(29, 42.0, 230, 1978, null, 386, 387, SMOKE_SPELL),

		SHADOW_BLITZ(33, 43.0, 240, 1978, null, 380, 381, SHADOW_SPELL),

		BLOOD_BLITZ(25, 45.0, 250, 1978, null, 374, 375, BLOOD_SPELL),

		ICE_BLITZ(21, 46.0, 260, 1978, new Graphics(366), 368, 367, ICE_SPELL),

		MIASMIC_BLITZ(37, 48.0, 280, 10524, new Graphics(1850), 1852, 1851, MIASMIC_SPELL),

		SMOKE_BARRAGE(31, 48.0, 270, 1979, null, 390, 391, true, SMOKE_SPELL),

		SHADOW_BARRAGE(35, 49.0, 280, 1979, null, -1, 383, true, SHADOW_SPELL),

		BLOOD_BARRAGE(27, 51.0, 290, 1979, null, -1, 377, true, BLOOD_SPELL),

		ICE_BARRAGE(23, 52.0, 300, 1979, null, 368, 369, true, ICE_SPELL),

		MIASMIC_BARRAGE(39, 54.0, 320, 10518, new Graphics(1853), -1, 1854, true, MIASMIC_SPELL),;

		private int id;
		private double xp;
		private int baseDamage;
		private int animation;
		private Graphics startGfx;
		private int projectileId;
		private int[] projectiles;
		private int endGfx;
		private int spell_type;
		private boolean multi_spell;

		public static AncientCombatSpellsStore getSpell(int i) {
			for (AncientCombatSpellsStore s : AncientCombatSpellsStore.values()) {
				if (s.getId() == i)
					return s;
			}
			return null;
		}

		private AncientCombatSpellsStore(int id, double xp, int baseDamage, int animation, Graphics startGfx,
				int projectileId, int endGfx, boolean multi_spell, int spell_type) {
			this.setId(id);
			this.setXp(xp);
			this.setBaseDamage(baseDamage);
			this.setAnimation(animation);
			this.setStartGfx(startGfx);
			this.setProjectileId(projectileId);
			this.setEndGfx(endGfx);
			this.setMulti_spell(multi_spell);
			this.setSpellType(spell_type);
		}

		private AncientCombatSpellsStore(int id, double xp, int baseDamage, int animation, Graphics startGfx,
				int projectileId, int endGfx, int spell_type) {
			this.setId(id);
			this.setXp(xp);
			this.setBaseDamage(baseDamage);
			this.setAnimation(animation);
			this.setStartGfx(startGfx);
			this.setProjectileId(projectileId);
			this.setEndGfx(endGfx);
			this.setSpellType(spell_type);
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public double getXp() {
			return xp;
		}

		public void setXp(double xp) {
			this.xp = xp;
		}

		public int getBaseDamage() {
			return baseDamage;
		}

		public void setBaseDamage(int baseDamage) {
			this.baseDamage = baseDamage;
		}

		public Graphics getStartGfx() {
			return startGfx;
		}

		public void setStartGfx(Graphics startGfx) {
			this.startGfx = startGfx;
		}

		public int getProjectileId() {
			return projectileId;
		}

		public void setProjectileId(int projectileId) {
			this.projectileId = projectileId;
		}

		public int getEndGfx() {
			return endGfx;
		}

		public void setEndGfx(int endGfx) {
			this.endGfx = endGfx;
		}

		public int getAnimation() {
			return animation;
		}

		public void setAnimation(int animation) {
			this.animation = animation;
		}

		public int[] getProjectiles() {
			return projectiles;
		}

		public void setProjectiles(int[] projectiles) {
			this.projectiles = projectiles;
		}

		public int getSpellType() {
			return spell_type;
		}

		public void setSpellType(int spell_type) {
			this.spell_type = spell_type;
		}

		public boolean isMulti_spell() {
			return multi_spell;
		}

		public void setMulti_spell(boolean multi_spell) {
			this.multi_spell = multi_spell;
		}
	}

	public static void instantSpellEffect(Player player, Entity target, int damage, AncientCombatSpellsStore spell) {
		if (spell.getSpellType() == SMOKE_SPELL) {
			target.getPoison().makePoisoned(spell.getId() == 28 || spell.getId() == 30 ? 20 : 40);
		}
		if (spell.getSpellType() == SHADOW_SPELL) {
			if (target instanceof Player) {
				Player p2 = (Player) target;
				p2.getSkills().drainLevel(Skills.ATTACK, (int) ((int) p2.getSkills().getLevel(Skills.ATTACK)
						* (spell.getId() == 32 || spell.getId() == 34 ? 0.10 : 0.15)));
			}

		}
		if (spell.getSpellType() == BLOOD_SPELL) {
			player.heal(damage / 4);
		}
		if (spell.getSpellType() == ICE_SPELL) {
			if (target.getSize() < 2 && target.getFreezeDelay() < Utils.currentTimeMillis()
					&& target.getFreezeImmuneDelay() < Utils.currentTimeMillis()) {
				int freeze_time = spell.getId() == 20 ? 8
						: spell.getId() == 21 ? 16 : spell.getId() == 22 ? 24 : 32;
				if (damage > 0) {
					if (target instanceof Player) {
						Player p2 = (Player) target;
						p2.setFrozenBy(player);
					}
					target.addFreezeDelay(freeze_time, false);
					target.setFreezeImmune(freeze_time + 6);
				}
			}
		}
		if (spell.getSpellType() == MIASMIC_SPELL) {
			if (target.temporaryAttribute().get("miasmic_immunity") == Boolean.FALSE) {
				if (target instanceof Player) {
					Player p2 = (Player) target;
					p2.getPackets().sendGameMessage("You feel slowed down.");
				}
				target.temporaryAttribute().put("miasmic_immunity", Boolean.TRUE);
				target.temporaryAttribute().put("miasmic_effect", Boolean.TRUE);
				final Entity t = target;
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						t.temporaryAttribute().remove("miasmic_effect");
						WorldTasksManager.schedule(new WorldTask() {
							@Override
							public void run() {
								t.temporaryAttribute().remove("miasmic_immunity");
								stop();
							}
						}, 15);
						stop();
					}
				}, 20);
			}
		}
	}

}
