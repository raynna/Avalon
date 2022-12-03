package com.rs.game.npc.combat;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.PlayerCombat;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public abstract class CombatScript {

    /*
     * Returns ids and names
     */
    public abstract Object[] getKeys();

    /*
     * Returns Move Delay
     */
    public abstract int attack(NPC npc, Entity target);

    public static void delayHit(NPC npc, int delay, final Entity target, final Hit... hits) {
        for (Hit hit : hits) {
            target.handleIncommingHit(hit);
            int attackSpeed = npc.getAttackSpeed() * 600;
            npc.setAttackDelay((attackSpeed / 2) + 4800);
            handleProtectPrayers(npc, target, hit);
            handleAbsorb(target, hit);
            handleSOL(target, hit);
            handleDivine(target, hit);
            handleElysian(target, hit);
            if (npc.getId() == 13448)
                sendSoulSplit(hit, npc, target);
            if (npc.getId() == 2027) {
                if (hit.getDamage() != 0 && Utils.random(3) == 0) {
                    target.gfx(new Graphics(398));
                    npc.heal(hit.getDamage());
                }
            }
            if (npc.getId() == 6367) {
                if (hit.getLook() == HitLook.MAGIC_DAMAGE && hit.getDamage() > 0)
                    target.addFreezeDelay(20000, false);
            }
        }
        WorldTasksManager.schedule(new WorldTask() {

            @Override
            public void run() {
                for (Hit hit : hits) {
                    NPC npc = (NPC) hit.getSource();
                    if (npc.isDead() || npc.hasFinished() || target.isDead() || target.hasFinished())
                        return;
                    target.applyHit(hit);
                    handleRingOfRecoil(npc, target, hit);
                    handleVengHit(target, hit);
                    if (npc.getId() >= 912 && npc.getId() <= 914) {
                        if (hit.getDamage() == 0)
                            target.gfx(new Graphics(85, 0, 96));
                        else
                            target.gfx(new Graphics(npc.getCombatDefinitions().getAttackProjectile(), 0, 0));
                    }
                    if (npc.getId() == 6367) {
                        if (hit.getDamage() == 0)
                            target.gfx(new Graphics(85, 0, 96));
                        if (hit.getDamage() > 0 && target.getFreezeDelay() > Utils.currentTimeMillis())
                            target.gfx(new Graphics(1677));
                        if (hit.getDamage() > 0 && target.getFreezeDelay() < Utils.currentTimeMillis())
                            target.gfx(new Graphics(369));
                    }
                    if (npc.getId() == 1007 && hit.getLook() == HitLook.MAGIC_DAMAGE) {
                        if (hit.getDamage() == 0)
                            target.gfx(new Graphics(85, 0, 96));
                        if (hit.getDamage() > 0)
                            target.gfx(new Graphics(78, 0, 0));
                    }
                    if (npc.getId() == 1264 && hit.getLook() == HitLook.MAGIC_DAMAGE) {
                        if (hit.getDamage() == 0)
                            target.gfx(new Graphics(85, 0, 96));
                        if (hit.getDamage() > 0)
                            target.gfx(new Graphics(76, 0, 0));
                    }
                    if (hit.getDamage() == 0) {
                        if (npc.getId() == 9172)// aquanite splash
                            target.gfx(new Graphics(2122));
                    }
                    npc.getCombat().doDefenceEmote(target);
                    if (target instanceof Player) {
                        Player p2 = (Player) target;
                        p2.getCharges().processIncommingHit();
                        p2.closeInterfaces();
                        if (p2.getCombatDefinitions().isAutoRelatie() && !p2.getActionManager().hasSkillWorking()
                                && !p2.hasWalkSteps())
                            p2.getActionManager().setAction(new PlayerCombat(npc));
                    } else {
                        NPC n = (NPC) target;
                        if (!n.isUnderCombat() || n.canBeAttackedByAutoRelatie())
                            n.setTarget(npc);
                    }

                }
            }

        }, delay);
    }

    public static void sendSoulSplit(final Hit hit, final NPC npc, final Entity target) {
        Player p2 = (Player) target;
        if (target instanceof Player) {
            World.sendSoulsplitProjectile(npc, target, 2263);
            if (npc.getHitpoints() > 0 && npc.getHitpoints() <= npc.getMaxHitpoints()) {
                npc.heal(hit.getDamage() / 5);
                p2.getPrayer().drainPrayer(hit.getDamage() / 5);
            }
            WorldTasksManager.schedule(new WorldTask() {
                @Override
                public void run() {
                    target.gfx(new Graphics(2264));
                    World.sendSoulsplitProjectile(target, npc, 2263);
                }
            }, 1);
        }
    }

    public static void handleRingOfRecoil(NPC npc, Entity target, Hit hit) {
        if (target instanceof NPC)
            return;
        int damage = (int) (hit.getDamage() * 0.1);
        if (target instanceof Player) {
            Player p2 = (Player) target;
            if (p2.getEquipment().getRingId() == 2550) {
                if (hit.getLook() == HitLook.MELEE_DAMAGE || hit.getLook() == HitLook.RANGE_DAMAGE
                        || hit.getLook() == HitLook.MAGIC_DAMAGE) {
                    if (hit.getDamage() > 9) {
                        if (p2.getRecoilHits() == 0) {
                            npc.applyHit(new Hit(p2, damage > 60 ? 60 : damage, HitLook.REFLECTED_DAMAGE));
                            p2.setRecoilHits(400);
                            p2.setRecoilHits(p2.getRecoilHits() - damage);
                            p2.getPackets().sendGameMessage("Your ring of recoil started degrading.");
                        } else if (p2.getRecoilHits() >= damage) {
                            npc.applyHit(new Hit(p2, damage > 60 ? 60 : damage, HitLook.REFLECTED_DAMAGE));
                            p2.setRecoilHits(p2.getRecoilHits() - damage);
                        } else if (p2.getRecoilHits() - damage <= 0) {
                            p2.getEquipment().deleteItem(2550, 1);
                            p2.getAppearence().generateAppearenceData();
                            hit.getSource()
                                    .applyHit(new Hit(target, (int) (hit.getDamage() * 0.75), HitLook.REGULAR_DAMAGE));
                            npc.applyHit(new Hit(p2, damage > 60 ? 60 : damage, HitLook.REFLECTED_DAMAGE));
                            p2.setRecoilHits(400);
                            p2.setRecoilHits(p2.getRecoilHits() - damage);
                            p2.getPackets().sendGameMessage("Your ring of recoil has shattered.");
                        }
                    }
                }
            }
        }
    }

    private static void handleVengHit(Entity target, Hit hit) {
        if (target instanceof NPC) {
            return;
        }
        Player p2 = (Player) target;
        if (p2.castedVeng && hit.getDamage() >= 4) {
            p2.castedVeng = false;
            p2.setNextForceTalk(new ForceTalk("Taste vengeance!"));
            hit.getSource().applyHit(new Hit(target, (int) (hit.getDamage() * 0.75), HitLook.REGULAR_DAMAGE));
        }
    }

    public static void handleAbsorb(Entity target, Hit hit) {
        if (target instanceof NPC) {
            return;
        }
        Player p2 = (Player) target;
        if (hit.getLook() == HitLook.MELEE_DAMAGE) {
            int reducedDamage = (hit.getDamage() - 200)
                    * p2.getCombatDefinitions().getBonuses()[CombatDefinitions.ABSORVE_MELEE_BONUS] / 100;
            if (hit.getDamage() - reducedDamage > 200 && p2.getHitpoints() > 200) {
                if (reducedDamage > 0) {
                    hit.setDamage(hit.getDamage() - reducedDamage);
                    hit.setSoaking(new Hit(target, reducedDamage, HitLook.ABSORB_DAMAGE));
                }
            }
        } else if (hit.getLook() == HitLook.RANGE_DAMAGE) {
            int reducedDamage = (hit.getDamage() - 200)
                    * p2.getCombatDefinitions().getBonuses()[CombatDefinitions.ABSORVE_RANGE_BONUS] / 100;
            if (hit.getDamage() - reducedDamage > 200 && p2.getHitpoints() > 200) {
                if (reducedDamage > 0) {
                    hit.setDamage(hit.getDamage() - reducedDamage);
                    hit.setSoaking(new Hit(target, reducedDamage, HitLook.ABSORB_DAMAGE));
                }
            }
        } else if (hit.getLook() == HitLook.MAGIC_DAMAGE) {
            int reducedDamage = (hit.getDamage() - 200)
                    * p2.getCombatDefinitions().getBonuses()[CombatDefinitions.ABSORVE_MAGE_BONUS] / 100;
            if (hit.getDamage() - reducedDamage > 200 && p2.getHitpoints() > 200) {
                if (reducedDamage > 0) {
                    hit.setDamage(hit.getDamage() - reducedDamage);
                    hit.setSoaking(new Hit(target, reducedDamage, HitLook.ABSORB_DAMAGE));
                }
            }
        }
    }

    public static void handleDivine(Entity target, Hit hit) {
        if (target instanceof NPC)
            return;
        if (target instanceof Player) {
            Player p2 = (Player) target;
            int shieldId = p2.getEquipment().getShieldId();
            if (shieldId == 13740) {
                int drain = (int) (Math.ceil(hit.getDamage() * 0.3) / 2);
                if (p2.getPrayer().getPrayerpoints() >= drain) {
                    hit.setDamage((int) (hit.getDamage() * 0.70));
                    p2.getPrayer().drainPrayer(drain);
                }
            }
        }
    }

    public static void handleElysian(Entity target, Hit hit) {
        if (target instanceof NPC)
            return;
        if (target instanceof Player) {
            Player p2 = (Player) target;
            int shieldId = p2.getEquipment().getShieldId();
            if (shieldId == 13742) {
                if (Utils.getRandom(10) <= 7)
                    hit.setDamage((int) (hit.getDamage() * 0.75));
            }
        }
    }

    public static void handleSOL(Entity target, Hit hit) {
        if (target instanceof NPC)
            return;
        if (target instanceof Player) {
            Player p2 = (Player) target;
            if (hit.getLook() == HitLook.MELEE_DAMAGE) {
                int weaponId = p2.getEquipment().getWeaponId();
                if (p2.polDelay > Utils.currentTimeMillis()) {
                    if (weaponId != 15486) {
                        p2.setPolDelay(0);
                    } else {
                        p2.gfx(new Graphics(2320, 0, 100));
                        hit.setDamage((int) (hit.getDamage() * 0.5));
                    }
                }
            }
        }
    }

    public static void handleProtectPrayers(NPC npc, Entity target, Hit hit) {
        if (target instanceof NPC)
            return;
        Player p2 = (Player) target;
        if (p2.getPrayer().hasPrayersOn() && hit.getDamage() != 0) {
            int deflectedDamage = (int) (hit.getDamage() * 0.1);
            if (hit.getLook() == HitLook.MAGIC_DAMAGE) {
                if (p2.getPrayer().usingPrayer(0, 17))
                    hit.setDamage((int) (hit.getDamage() * npc.getMagePrayerMultiplier()));
                else if (p2.getPrayer().usingPrayer(1, 7)) {
                    if (Utils.getRandom(2) <= 1 && hit.getDamage() > 10) {
                        npc.applyHit(new Hit(target, deflectedDamage, HitLook.REFLECTED_DAMAGE));
                        p2.gfx(new Graphics(2228));
                        p2.setNextAnimationNoPriority(new Animation(12573), p2);
                    }
                    hit.setDamage((int) (hit.getDamage() * npc.getMagePrayerMultiplier()));
                }
            }
            if (hit.getLook() == HitLook.RANGE_DAMAGE) {
                if (p2.getPrayer().usingPrayer(0, 18))
                    hit.setDamage((int) (hit.getDamage() * npc.getRangePrayerMultiplier()));
                else if (p2.getPrayer().usingPrayer(1, 8)) {
                    if (Utils.getRandom(2) <= 1 && hit.getDamage() > 10) {
                        npc.applyHit(new Hit(target, deflectedDamage, HitLook.REFLECTED_DAMAGE));
                        p2.gfx(new Graphics(2229));
                        p2.setNextAnimationNoPriority(new Animation(12573), p2);
                    }
                    hit.setDamage((int) (hit.getDamage() * npc.getRangePrayerMultiplier()));
                }
            }
            if (hit.getLook() == HitLook.MELEE_DAMAGE) {
                if (p2.getPrayer().usingPrayer(0, 19))
                    hit.setDamage((int) (hit.getDamage() * npc.getMeleePrayerMultiplier()));
                else if (p2.getPrayer().usingPrayer(1, 9)) {
                    if (Utils.getRandom(2) <= 1 && hit.getDamage() > 10) {
                        npc.applyHit(new Hit(target, deflectedDamage, HitLook.REFLECTED_DAMAGE));
                        p2.gfx(new Graphics(2230));
                        p2.setNextAnimationNoPriority(new Animation(12573), p2);
                    }
                    hit.setDamage((int) (hit.getDamage() * npc.getMeleePrayerMultiplier()));
                }
            }
        }
    }

    public static Hit getRangeHit(NPC npc, int damage) {
        return new Hit(npc, damage, HitLook.RANGE_DAMAGE);
    }

    public static Hit getMagicHit(NPC npc, int damage) {
        return new Hit(npc, damage, HitLook.MAGIC_DAMAGE);
    }

    public static Hit getRegularHit(NPC npc, int damage) {
        return new Hit(npc, damage, HitLook.REGULAR_DAMAGE);
    }

    public static Hit getMeleeHit(NPC npc, int damage) {
        return new Hit(npc, damage, HitLook.MELEE_DAMAGE);
    }

    public static int getRandomMaxHit(NPC npc, int maxHit, int attackStyle, Entity target) {
        int[] bonuses = npc.getBonuses();
        double mage = 0;
        double M = 0;
        double mageBonus = 0;
        double attack = 0;
        double A = 0;
        double attackBonus = 0;
        double range = 0;
        double R = 0;
        double rangeBonus = 0;
        npc.setBonuses();
        if (attackStyle == NPCCombatDefinitions.MAGE) {
            mageBonus = bonuses != null ? bonuses[CombatDefinitions.NPC_MAGIC_BONUS] : 0;
            mage = bonuses != null ? bonuses[CombatDefinitions.NPC_MAGIC_LEVEL] * 1.5 : npc.getCombatLevel();
            mage = Math.round(mage);
            mage += 8;
            M = Math.round(mage);
            mage = mage * (1 + mageBonus / 64);
            M = Math.round(mage);
        } else if (attackStyle == NPCCombatDefinitions.RANGE) {
            rangeBonus = (bonuses != null ? bonuses[CombatDefinitions.NPC_RANGE_BONUS] : 0);
            range = bonuses != null ? bonuses[CombatDefinitions.NPC_RANGE_LEVEL] * 1.5 : npc.getCombatLevel();
            range = Math.round(range);
            range += 8;
            R = Math.round(range);
            range = range * (1 + rangeBonus / 64);
            R = Math.round(range);
        } else {
            attackBonus = npc.getCombatDefinitions().getAttackType() == NPCCombatDefinitions.STAB
                    ? bonuses != null ? bonuses[CombatDefinitions.NPC_STAB_BONUS] : 0
                    : npc.getCombatDefinitions().getAttackType() == NPCCombatDefinitions.SLASH
                    ? bonuses != null ? bonuses[CombatDefinitions.NPC_SLASH_BONUS] : 0
                    : bonuses != null ? bonuses[CombatDefinitions.NPC_CRUSH_BONUS] : 0;
            attack += bonuses != null ? bonuses[CombatDefinitions.NPC_ATTACK_LEVEL] * 1.5 : npc.getCombatLevel();
            attack = Math.round(attack);
            attack += 8;
            attack = attack * (1 + attackBonus / 64);
            A = Math.round(attack);
        }
        double defence = 0;
        double D = 0;
        double rangedefence = 0;
        double RD = 0;
        double magedefence = 0;
        double MD = 0;
        if (target instanceof Player) {
            Player p2 = (Player) target;
            double defenceBonus = (p2.getCombatDefinitions().getBonuses()[attackStyle == NPCCombatDefinitions.RANGE
                    ? CombatDefinitions.RANGE_DEF
                    : CombatDefinitions.MAGIC_DEF]);
            double meleeBonus = (p2.getCombatDefinitions()
                    .getBonuses()[npc.getCombatDefinitions().getAttackType() == NPCCombatDefinitions.STAB
                    ? CombatDefinitions.STAB_DEF
                    : npc.getCombatDefinitions().getAttackType() == NPCCombatDefinitions.SLASH
                    ? CombatDefinitions.SLASH_DEF
                    : CombatDefinitions.CRUSH_DEF]);
            if (attackStyle == NPCCombatDefinitions.MAGE) {
                magedefence = (p2.getSkills().getLevel(Skills.DEFENCE) * 0.3)
                        + (p2.getSkills().getLevel(Skills.MAGIC) * 0.7);
                magedefence = Math.round(magedefence);
                magedefence += 8;
                magedefence = magedefence * (1 + defenceBonus / 64);
                MD = Math.round(magedefence);
            } else if (attackStyle == NPCCombatDefinitions.RANGE) {
                rangedefence = p2.getSkills().getLevel(Skills.DEFENCE);
                rangedefence *= p2.getPrayer().getDefenceMultiplier();
                rangedefence = Math.round(rangedefence);
                rangedefence += 8;
                rangedefence = rangedefence * (1 + defenceBonus / 64);
                RD = Math.round(rangedefence);
            } else {
                defence = p2.getSkills().getLevel(Skills.DEFENCE);
                defence *= p2.getPrayer().getDefenceMultiplier();
                defence = Math.round(defence);
                defence += 8;
                defence = defence * (1 + meleeBonus / 64);
                D = Math.round(defence);
            }
        } else if (target instanceof NPC || target instanceof Familiar) {
            NPC n = (NPC) target;
            defence = n.getBonuses()[attackStyle == NPCCombatDefinitions.RANGE ? CombatDefinitions.RANGE_DEF
                    : attackStyle == NPCCombatDefinitions.MAGE ? CombatDefinitions.MAGIC_DEF
                    : CombatDefinitions.STAB_DEF];
            double defenceBonus = (attackStyle == NPCCombatDefinitions.RANGE
                    ? n.getBonuses()[CombatDefinitions.NPC_RANGE_BONUS]
                    : attackStyle == NPCCombatDefinitions.MAGE ? n.getBonuses()[CombatDefinitions.NPC_MAGIC_BONUS]
                    : n.getBonuses()[CombatDefinitions.NPC_STAB_BONUS]);
            if (attackStyle == NPCCombatDefinitions.MAGE) {
                magedefence = n.getBonuses()[CombatDefinitions.NPC_DEFENCE_LEVEL];
                magedefence = Math.round(magedefence);
                magedefence += 8;
                magedefence = magedefence * (1 + defenceBonus);
                MD = Math.round(magedefence);
            } else if (attackStyle == NPCCombatDefinitions.RANGE) {
                rangedefence = n.getBonuses()[CombatDefinitions.NPC_DEFENCE_LEVEL];
                rangedefence = Math.round(rangedefence);
                rangedefence += 8;
                rangedefence = rangedefence * (1 + defenceBonus);
                RD = Math.round(rangedefence);
            } else {
                defence = n.getBonuses()[CombatDefinitions.NPC_DEFENCE_LEVEL];
                defence = Math.round(defence);
                defence += 8;
                defence = defence * (1 + defenceBonus);
                D = Math.round(defence);
            }
        }
        if (attackStyle == NPCCombatDefinitions.MAGE) {
            double prob = M / MD;
            double random = Utils.getRandomDouble(100);
            if (M <= MD)
                prob = (M - 1) / (MD * 2);
            else if (M > MD)
                prob = 1 - (MD + 1) / (M * 2);
            if (npc.getId() == 1158 || npc.getId() == 1160)
                prob = 100;
            if (prob < random / 100)
                return 0;
        } else if (attackStyle == NPCCombatDefinitions.RANGE) {
            double prob = R / RD;
            double random = Utils.getRandomDouble(100);
            if (R <= RD)
                prob = (R - 1) / (RD * 2);
            else if (R > RD)
                prob = 1 - (RD + 1) / (R * 2);
            ;
            if (npc.getId() == 1158 || npc.getId() == 1160)
                prob = 100;
            if (prob < random / 100)
                return 0;
        } else {
            double prob = A / D;
            double random = Utils.getRandomDouble(100);
            if (A <= D)
                prob = (A - 1) / (D * 2);
            else if (A > D)
                prob = 1 - (D + 1) / (A * 2);
            if (prob < random / 100)
                return 0;
        }
        return Utils.getRandom(maxHit);
    }

}
