package com.rs.game.player.actions.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.Region;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.duel.DuelArena;
import com.rs.game.minigames.godwars.zaros.NexMinion;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.dungeonnering.DungeonBoss;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.fightkiln.HarAken;
import com.rs.game.npc.fightkiln.HarAkenTentacle;
import com.rs.game.npc.glacior.Glacyte;
import com.rs.game.npc.pest.PestPortal;
import com.rs.game.npc.qbd.QueenBlackDragon;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.game.player.actions.combat.ancientspells.RSAncientCombatSpells;
import com.rs.game.player.actions.combat.ancientspells.RSAncientCombatSpells.AncientCombatSpellsStore;
import com.rs.game.player.actions.combat.modernspells.RSModernCombatSpells;
import com.rs.game.player.actions.combat.modernspells.RSModernCombatSpells.ModernCombatSpellsStore;
import com.rs.game.player.controlers.WildernessControler;
import com.rs.game.player.controlers.pestcontrol.PestControlGame;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Logger;
import com.rs.utils.MapAreas;
import com.rs.utils.Utils;

/**
 * @Improved Andreas - AvalonPK
 */

public class PlayerCombat extends Action {

    private Entity target;
    public int max_hit; // temporary constant
    private double base_mage_xp; // temporary constant
    private int mage_hit_gfx; // temporary constant
    private String spell_name;
    private int magic_sound; // temporary constant
    private int max_poison_hit; // temporary constant
    @SuppressWarnings("unused")
    private boolean reduceAttack; // temporary constant
    private boolean blood_spell; // temporary constant
    private boolean block_tele;
    public static int spellcasterGloves;
    private int spell_type = -1;

    @SuppressWarnings("unused")
    private static final int AIR_SPELL = 0;
    public static final int FIRE_SPELL = 3;
    @SuppressWarnings("unused")
    private static final int SMOKE_SPELL = 4;
    @SuppressWarnings("unused")
    private static final int SHADOW_SPELL = 5;
    private static final int BLOOD_SPELL = 6;
    private static final int ICE_SPELL = 7;

    public PlayerCombat(Entity target) {
        this.target = target;
    }

    public static int getHealthOverlayId(Player player) {
        return player.getInterfaceManager().isResizableScreen() ? 1 : 30;
    }

    @Override
    public boolean start(Player player) {
        if (player == null)
            return false;
        if (target.isDead())
            return false;
        player.setNextFaceEntity(target);
        player.setTemporaryTarget(target);
        player.getTemporaryAttributtes().put("temporaryActionDelay", 4 * 1000 + Utils.currentTimeMillis());
        if (player.toggles("HEALTHBAR", false)
                && (!player.getInterfaceManager().containsTab(getHealthOverlayId(player)))) {
            player.getInterfaceManager().sendTab(getHealthOverlayId(player), 3037);
            updateHealthOverlay(player, target);
        }
        if (checkAll(player)) {
            return true;
        }
        player.setNextFaceEntity(null);
        return false;
    }

    public void updateHealthOverlay(Entity player, Entity target) {
        if (target instanceof Player) {
            Player p2 = (Player) target;
            Player p1 = (Player) player;
            p1.getPackets().sendIComponentText(3037, 6, p2.getDisplayName());
            int combat = p1.isAtWild() ? p1.getSkills().getCombatLevel() : p1.getSkills().getCombatLevelWithSummoning();
            int targetCombat = p2.isAtWild() ? p2.getSkills().getCombatLevel()
                    : p2.getSkills().getCombatLevelWithSummoning();
            String level = (targetCombat > combat ? "<col=ff5331>"
                    : targetCombat == combat ? "<col=FFC428>" : "<col=00b427>") + targetCombat;
            p1.getPackets().sendIComponentText(3037, 9, "Level: " + level);
            p1.getPackets().sendIComponentText(3037, 7,
                    (p1.toggles("ONEXHITS", false) ? p2.getHitpoints() / 10 + "/" + p2.getMaxHitpoints() / 10
                            : p2.getHitpoints() + "/" + p2.getMaxHitpoints()));
            final int pixels = (int) ((double) p2.getHitpoints() / p2.getMaxHitpoints() * 126D);
            p1.getPackets().sendRunScript(6252, pixels);
        } else {
            NPC npc = (NPC) target;
            Player p1 = (Player) player;
            p1.getPackets().sendIComponentText(3037, 6, npc.getName());
            int combat = p1.isAtWild() ? p1.getSkills().getCombatLevel() : p1.getSkills().getCombatLevelWithSummoning();
            int targetCombat = npc.getCombatLevel();
            String level = (targetCombat > combat ? "<col=ff5331>"
                    : targetCombat == combat ? "<col=FFC428>" : "<col=00b427>") + targetCombat;
            p1.getPackets().sendIComponentText(3037, 9, "Level: " + level);
            p1.getPackets().sendIComponentText(3037, 7,
                    (p1.toggles("ONEXHITS", false) ? npc.getHitpoints() / 10 + "/" + npc.getMaxHitpoints() / 10
                            : npc.getHitpoints() + "/" + npc.getMaxHitpoints()));
            final int pixels = (int) ((double) npc.getHitpoints() / npc.getMaxHitpoints() * 126D);
            p1.getPackets().sendRunScript(6252, pixels);
        }
    }

    @Override
    public boolean process(Player player) {
        if (target.isDead())
            return false;
        return checkAll(player);
    }

    private boolean forceCheckClipAsRange(Entity target) {
        return target instanceof NexMinion || target instanceof HarAken || target instanceof HarAkenTentacle
                || target instanceof QueenBlackDragon || target instanceof PestPortal;
    }

    public boolean hasSalamander(Player player) {
        if (player.getEquipment().getWeaponId() >= 10146 && player.getEquipment().getWeaponId() <= 10149)
            return true;
        return false;
    }

    @Override
    public int processWithDelay(Player player) {
        if (target.isDead())
            return 0;
        int isRanging = isRanging(player);
        int spellId = player.getCombatDefinitions().getSpellId();
        int maxDistance = isRanging != 0 ? getAttackDistance(player)
                : (spellId > 0 || hasPolyporeStaff(player) || player.getEquipment().getWeaponId() == 24203) ? 8 : 0;
        String name = ItemDefinitions.getItemDefinitions(player.getEquipment().getWeaponId()).getName().toLowerCase();
        if (name.contains("halberd"))
            maxDistance++;
        if (spellId < 1 && hasSalamander(player)) {
            spellId = 65536;
        }
        if (spellId < 1 && hasPolyporeStaff(player)) {
            spellId = 65535;
        }
        if (spellId < 1 && player.getEquipment().getWeaponId() == 24203) {
            spellId = player.getRunicStaff().getSpellId();
        }
        int distanceX = player.getX() - target.getX();
        int distanceY = player.getY() - target.getY();
        double multiplier = 1.0;
        double magicMultiplier = 1.0;
        if (player.hasWalkSteps() && target.hasWalkSteps())
            maxDistance += player.getRun() ? 2 : 1;
        if (player.temporaryAttribute().get("miasmic_effect") == Boolean.TRUE)
            multiplier = 1.5;
        if (player.temporaryAttribute().get("bakriminel_effect") == Boolean.TRUE)
            magicMultiplier = 1.5;
        int size = target.getSize();
        if (!player.clipedProjectile(target, maxDistance == 0 && !forceCheckClipAsRange(target))) {
            return 0;
        }
        if (distanceX > size + maxDistance || distanceX < -size - maxDistance || distanceY > size + maxDistance
                || distanceY < -size - maxDistance) {
            return 0;
        }
        if (!player.getControlerManager().keepCombating(target)) {
            return -1;
        }
        if (spellId > 0) {
            boolean manualCast = spellId != 65535 && spellId != 65536 && spellId >= 256;
            Item gloves = player.getEquipment().getItem(Equipment.SLOT_HANDS);
            spellcasterGloves = gloves != null && gloves.getDefinitions().getName().contains("Spellcaster glove")
                    && player.getEquipment().getWeaponId() == -1 && new Random().nextInt(30) == 0 ? spellId : -1;
            int delay = mageAttack(player, manualCast ? spellId - 256 : spellId, !manualCast);
            if (player.getNextAnimation() != null && spellcasterGloves > 0) {
                player.animate(new Animation(14339));
                spellcasterGloves = -1;
            }
            return (int) (delay * magicMultiplier);
        } else {
            if (isRanging == 0) {
                return (int) (meleeAttack(player) * multiplier);
            } else if (isRanging == 1) {
                player.getPackets().sendGameMessage("This ammo is not very effective with this weapon.");
                return -1;
            } else if (isRanging == 3) {
                player.getPackets().sendGameMessage("You dont have any ammo in your backpack.");
                return -1;
            } else {
                return (int) (rangeAttack(player) * multiplier);
            }
        }
    }

    private int getRangeCombatDelay(Player player, int weaponId, int attackStyle) {
        int delay = 6;
        if (weaponId != -1) {
            String weaponName = ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase();
            if (weaponName.contains("hexhunter bow") || weaponName.contains("training bow")
                    || weaponName.contains("shortbow") || weaponId == 4734 || weaponId == 4934 || weaponId == 4935
                    || weaponId == 4936 || weaponId == 4937 || weaponId == 10156)
                delay = 3;
            else if (weaponName.contains("crystal") || weaponName.contains("crossbow") || weaponName.contains("thrown"))
                delay = 5;
            else if (weaponName.contains("chinchompa") || weaponId == 6522)
                delay = 3;
            else if (weaponName.contains("sling"))
                delay = 3;
            else if (weaponName.contains("knife"))
                delay = 2;
            else if (weaponName.contains("dart")) {
                delay = 2;
            } else {
                switch (weaponId) {
                    case 15241:
                        delay = 8;
                        break;
                    case 11235: // dark bows
                    case 15701:
                    case 15702:
                    case 15703:
                    case 15704:
                        delay = 9;
                        break;
                    default:
                        delay = 6;
                        break;
                }
            }
        }
        final String weaponName = ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase();
        if (attackStyle == 1) {
            delay--;
        } else if (attackStyle == 2) {
            if (player.getEquipment().getAmmoId() == 24116 && weaponName.contains("crossbow"))
                delay--;
            else
                delay++;
        }
        if (player.getEquipment().getAmmoId() == 24116 && weaponName.contains("crossbow"))
            return delay--;
        else
            return delay;
    }

    public Entity[] getMultiAttackTargets(Player player) {
        return getMultiAttackTargets(player, 1, 9);
    }

    public Entity[] getMultiAttackTargets(Player player, int max) {
        return getMultiAttackTargets(player, 1, max);
    }

    public Entity[] getMultiAttackTargets(Player player, int maxDistance, int maxAmtTargets) {
        List<Entity> possibleTargets = new ArrayList<Entity>();
        possibleTargets.add(target);
        if (target.isAtMultiArea()) {
            for (int regionId : target.getMapRegionsIds()) {
                Region region = World.getRegion(regionId);
                if (target instanceof Player) {
                    List<Integer> playerIndexes = region.getPlayerIndexes();
                    if (playerIndexes == null)
                        continue;
                    for (int playerIndex : playerIndexes) {
                        Player p2 = World.getPlayers().get(playerIndex);
                        if (p2 == null || p2 == player || p2 == target || p2.isDead() || !p2.hasStarted()
                                || p2.hasFinished() || !p2.isCanPvp() || !p2.isAtMultiArea()
                                || !p2.withinDistance(target, maxDistance) || !player.getControlerManager().canHit(p2)
                                || possibleTargets.size() >= maxAmtTargets)
                            continue;
                        possibleTargets.add(p2);
                    }
                } else {
                    List<Integer> npcIndexes = region.getNPCsIndexes();
                    if (npcIndexes == null)
                        continue;
                    for (int npcIndex : npcIndexes) {
                        NPC n = World.getNPCs().get(npcIndex);
                        if (n == null || n == target || n == player.getFamiliar() || n.isDead() || n.hasFinished()
                                || !n.isAtMultiArea() || !n.withinDistance(target, maxDistance)
                                || !n.getDefinitions().hasAttackOption() || !player.getControlerManager().canHit(n)
                                || possibleTargets.size() >= maxAmtTargets)
                            continue;
                        possibleTargets.add(n);
                    }
                }
            }
        }
        return possibleTargets.toArray(new Entity[possibleTargets.size()]);
    }

    public static boolean wearingRunicStaff(Player player) {
        return player.getEquipment().getWeaponId() == 24203;
    }

    public static boolean wearingArmadylStaff(Player player) {
        return player.getEquipment().getWeaponId() == 21777;
    }

    private static boolean hasRunicStaffCharges(Player player) {
        return player.getRunicStaff().getSpellId() > 0 && !player.getStaffCharges().isEmpty();

    }

    public static long teleBlockTime;

    public int mageAttack(final Player player, int spellId, boolean autocast) {
        if (spellId == 65535) {
            player.setNextFaceEntity(target);
            player.gfx(new Graphics(2034));
            player.animate(new Animation(15448));
            mage_hit_gfx = 2036;
            delayMagicHit(Utils.getDistance(player, target) > 3 ? 2 : 1, getMagicHit(player,
                    getRandomMagicMaxHit(player, (5 * player.getSkills().getLevel(Skills.MAGIC)) - 180)));
            World.sendElementalProjectile(player, target, 2035);
            return 4;
        }
        if (spellId == 65536) {// temporary custom salamanders
            int weaponId = player.getEquipment().getWeaponId();
            int attackStyle = player.getCombatDefinitions().getAttackStyle();
            int damage = getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true);
            int magicMaxHit = (int) Math.floor(0.5 + (player.getSkills().getLevel(Skills.MAGIC) * 132) / 640) * 10;
            int mageDamage = getRandomMagicMaxHit(player, magicMaxHit);
            player.gfx(new Graphics(953, 0, 100));
            if (attackStyle == 0) {
                delayHit(1, weaponId, attackStyle, getMeleeHit(player, damage));
            } else if (attackStyle == 1) {
                delayHit(1, weaponId, attackStyle, getRangeHit(player, damage));
            } else {
                delayHit(1, weaponId, attackStyle, getMagicHit(player, mageDamage));
            }
            return 5;
        }
        if (!autocast) {
            player.getCombatDefinitions().resetSpells(false);
            player.getActionManager().forceStop();
        }
        boolean usingRunic = false;
        if (spellId == player.getRunicStaff().getSpellId() && hasRunicStaffCharges(player)
                && wearingRunicStaff(player)) {
            usingRunic = true;
        }
        if (!usingRunic) {
            if (player.getCombatDefinitions().getSpellBook() == 192) {
                if (!ModernMagicks.hasRequirement(player, spellId, true, true))
                    return -1;
                if (!ModernMagicks.checkRunes(player, spellId, true)) {
                    if (autocast)
                        player.getCombatDefinitions().resetSpells(true);
                    return -1;
                }
            } else {
                if (!AncientMagicks.hasRequirement(player, spellId, true, true))
                    return -1;
                if (!AncientMagicks.checkRunes(player, spellId, true)) {
                    if (autocast)
                        player.getCombatDefinitions().resetSpells(true);
                    return -1;
                }
            }
        }
        if (player.getCombatDefinitions().getSpellBook() == ModernMagicks.SPELLBOOK_ID) {
            ModernCombatSpellsStore spell = ModernCombatSpellsStore.getSpell(spellId);
            if (spell == null)
                return -1;
            String spellName = spell.name().replace("_", " ").toLowerCase();
            if (spell == ModernCombatSpellsStore.CRUMBLE_UNDEAD) {
                player.setNextFaceEntity(target);
                if (target instanceof NPC) {
                    NPC n = (NPC) target;
                    boolean undead = false;
                    for (String string : Settings.UNDEAD_NPCS) {
                        if (n.getDefinitions().getName().toLowerCase().contains(string) || n.getId() == 4474) {
                            undead = true;
                        }
                    }
                    if (!undead || (target instanceof Player)) {
                        player.sm("You can only cast crumble undead on undead monsters.");
                        return -1;
                    }
                }
            }
            if (spell == ModernCombatSpellsStore.BIND || spell == ModernCombatSpellsStore.SNARE
                    || spell == ModernCombatSpellsStore.ENTANGLE) {
                if (target.getFreezeDelay() > Utils.currentTimeMillis()) {
                    player.sm("This " + (target instanceof Player ? "player" : "monster") + " is already effected by this spell.");
                    return -1;
                }
            }
            if (spell == ModernCombatSpellsStore.TELEPORT_BLOCK) {
                if (target instanceof NPC) {
                    player.getPackets().sendGameMessage("You can't cast teleport block on a monster.");
                    return -1;
                }
                if (target instanceof Player) {
                    Player p2 = (Player) target;
                    if (p2.getTeleBlockDelay() > Utils.currentTimeMillis()) {
                        player.getPackets().sendGameMessage("This player is already teleport blocked.");
                        return -1;
                    }
                }
            }
            if (spell.getId() == player.getRunicStaff().getSpellId() && hasRunicStaffCharges(player)
                    && wearingRunicStaff(player))
                player.getRunicStaff().removeCharge(1, spellId);
            else
                ModernMagicks.removeRunes(player, spellId);
            if (spell.getStartGfx() != null)
                player.gfx(spell.getStartGfx());
            if (spell.getAnimation() == -1)
                player.animate(new Animation(spell.getAnimation() != -1 ? spell.getAnimation() : 14221));
            if (spell == ModernCombatSpellsStore.STORM_OF_ARMADYL)
                World.sendSOAProjectile(player, target, spell.getProjectileId());
            else {
                if (spell.getProjectiles() != null) {
                    for (int projectiles : spell.getProjectiles())
                        World.sendElementalProjectile(player, target, projectiles);
                } else
                    World.sendElementalProjectile(player, target, spell.getProjectileId());
            }
            spell_type = spell.getSpellType();
            mage_hit_gfx = spell.getEndGfx();
            base_mage_xp = spell.getXp();
            spell_name = spell.name();
            if (spell.getSpellType() >= 0) {
                player.playSound(155, 1);
                magic_sound = 156;
            }
            int baseDamage = RSModernCombatSpells.getDamageBoost(player, spell.getId(), spell.getBaseDamage()) != 0
                    ? RSModernCombatSpells.getDamageBoost(player, spell.getId(), spell.getBaseDamage())
                    : spell.getBaseDamage();
            Hit hit = getMagicHit(player, getRandomMagicMaxHit(player, baseDamage));
            if (target instanceof Player) {
                Player p2 = (Player) target;
                if (wearingRunicStaff(p2) && p2.getRunicStaff().getSpellId() == spell.getId()) {
                    if (Utils.getRandom(4) == 0) {
                        hit.setDamage(-2);
                        player.sm("Enemies greater runic staff resists your " + spellName + " spell.");
                    } else if (Utils.getRandom(15) == 0) {
                        p2.getRunicStaff().chargeCombat(1);
                        player.sm(p2.getDisplayName() + " absorbs your " + spellName + " spell.");
                        p2.sm("You absorb the " + spellName + " spell into your greater runic staff.");
                    }
                }
            }
            delayMagicHit(getMageDelay(player, target), hit);
            RSModernCombatSpells.instantSpellEffect(player, target, spell.getId(), hit.getDamage() > 0);
            return spell == ModernCombatSpellsStore.STORM_OF_ARMADYL && wearingArmadylStaff(player) ? 3 : 4;
        } else if (player.getCombatDefinitions().getSpellBook() == AncientMagicks.SPELLBOOK_ID) {
            AncientCombatSpellsStore spell = AncientCombatSpellsStore.getSpell(spellId);
            if (spell == null)
                return -1;
            if (spell.getId() == player.getRunicStaff().getSpellId() && hasRunicStaffCharges(player)
                    && wearingRunicStaff(player))
                player.getRunicStaff().removeCharge(1, spellId);
            else
                AncientMagicks.removeRunes(player);
            if (spell.getStartGfx() != null)
                player.gfx(spell.getStartGfx());
            if (spell.getAnimation() == -1)
                player.animate(new Animation(14221));
            else
                player.animate(new Animation(spell.getAnimation()));
            if (spell.getProjectileId() != -1)
                World.sendElementalProjectile(player, target, spell.getProjectileId());
            spell_type = spell.getSpellType();
            if (spell.getSpellType() == RSAncientCombatSpells.ICE_SPELL)
                player.playSound(171, 1);
            if (spell == AncientCombatSpellsStore.ICE_BLITZ || spell == AncientCombatSpellsStore.ICE_RUSH)
                magic_sound = 169;
            if (spell == AncientCombatSpellsStore.ICE_BURST)
                magic_sound = 170;
            if (spell == AncientCombatSpellsStore.ICE_BARRAGE) {
                magic_sound = 168;
                if (target.getSize() >= 2 || target.getFreezeDelay() >= Utils.currentTimeMillis()
                        || target.getFreezeImmuneDelay() >= Utils.currentTimeMillis()) {
                    mage_hit_gfx = 1677;
                } else
                    mage_hit_gfx = 369;
            } else
                mage_hit_gfx = spell.getEndGfx();
            base_mage_xp = spell.getXp();
            spell_name = spell.name();
            int baseDamage = spell.getBaseDamage();
            if (spell.isMulti_spell() && player.isAtMultiArea()) {
                attackTarget(getMultiAttackTargets(player), new MultiAttack() {

                    private boolean nextTarget;

                    @Override
                    public boolean attack() {
                        Hit hit = getMagicHit(player, getRandomMagicMaxHit(player, baseDamage));
                        delayMagicHit(getMageDelay(player, target), hit);
                        RSAncientCombatSpells.instantSpellEffect(player, target, hit.getDamage(), spell);
                        if (!nextTarget) {
                            nextTarget = true;
                        }
                        return nextTarget;

                    }
                });
            } else {
                Hit hit = getMagicHit(player, getRandomMagicMaxHit(player, baseDamage));
                if (target instanceof Player) {
                    Player p2 = (Player) target;
                    if (p2.getEquipment().getWeaponId() == 24203 && p2.getRunicStaff().getSpellId() == spell.getId()) {
                        if (Utils.getRandom(4) == 0) {
                            hit.setDamage(-2);
                            player.getPackets().sendGameMessage("Enemies greater runic staff resists your "
                                    + spell.name().toLowerCase().replace("_", " ") + " spell.");
                        } else if (Utils.getRandom(15) == 0) {
                            p2.getRunicStaff().chargeCombat(1);
                            player.getPackets().sendGameMessage(p2.getDisplayName() + " absorbs your "
                                    + spell.name().toLowerCase().replace("_", " ") + " spell.");
                            p2.getPackets()
                                    .sendGameMessage("You absorb the " + spell.name().toLowerCase().replace("_", " ")
                                            + " spell into your greater runic staff.");
                        }
                    }
                }
                delayMagicHit(getMageDelay(player, target), hit);
                RSAncientCombatSpells.instantSpellEffect(player, target, hit.getDamage(), spell);
            }
            return 4;
        }
        return -1;
    }

    public interface MultiAttack {

        public boolean attack();

    }

    public int getMageDelay(Player player, Entity target) {
        int spellId = player.getCombatDefinitions().getSpellId();
        int spellBook = player.getCombatDefinitions().getSpellBook();
        int getDistance = Utils.getDistance(player, target);
        int mageDelay;
        if (spellBook != 192) {
            if (spellId >= 36 && spellId <= 39) {
                if (getDistance > 3)
                    mageDelay = 2;
                else
                    mageDelay = 1;
            } else {
                if (getDistance >= 4)
                    mageDelay = 4;
                else
                    mageDelay = getDistance;
            }
        } else {
            if (getDistance > 3)
                mageDelay = 3;
            else
                mageDelay = getDistance;
        }
        return mageDelay;
    }

    public void attackTarget(Entity[] targets, MultiAttack perform) {
        Entity realTarget = target;
        for (Entity t : targets) {
            target = t;
            if (!perform.attack())
                break;
        }
        target = realTarget;
    }

    public int getRandomMagicMaxHit(Player player, int baseDamage) {
        int current = getMagicMaxHit(player, baseDamage);
        if (current <= 0)
            return -1;
        int newRandom = Utils.random(9);
        int hit = Utils.random(current + 1);
        if (newRandom >= 7)
            hit = current / 2 + Utils.random(current / 2);
        if (player.getInventory().containsItem(773, 1))
            hit = current;
        if (hit > 0) {
            if (target instanceof NPC) {
                NPC n = (NPC) target;
                if (n.getId() == 14301 && (spell_type == FIRE_SPELL || spell_type == BLOOD_SPELL)) {
                    int half = (int) (hit / 2);
                    hit += half;
                } else if (n.getId() == 14301 && hasFireCape(player) && (spell_type != ICE_SPELL)) {
                    hit += 20;
                } else if (n.getId() == 14301 && (spell_type == ICE_SPELL)) {
                    hit = 0;
                    player.sm("Your spell seems to have no effect on the elementals icy armor.");
                }
                if (n.getId() == 9463 && (spell_type == FIRE_SPELL)) {
                    if (hasFireCape(player))
                        hit *= 2;
                    else
                        hit *= 1.5;
                }
            }
        }
        if (target instanceof NPC) {
            NPC n = (NPC) target;
            if (n.getId() == 9462 && hasFireCape(player))
                hit += 40;
            if (n.getId() == 4474) {
                int MaxHit = (int) (max_hit);
                hit -= MaxHit;
                max_hit -= MaxHit;
                if (hit < 0)
                    hit = MaxHit;
                if (hit < MaxHit)
                    hit += MaxHit;
            }
        }
        return hit;
    }

    public int getMagicMaxHit(Player player, int baseDamage) {
        double A = 0, D = 0;
        double attackBonus = player.getCombatDefinitions().getBonuses()[CombatDefinitions.MAGIC_ATTACK];
        double attack = Math.round(player.getSkills().getLevel(Skills.MAGIC) * player.getPrayer().getMageMultiplier()) + 8;
        if (fullVoidEquipped(player, 11663, 11674))
            attack *= hasEliteVoid(player) ? 1.475 : 1.45;
        if (target instanceof NPC) {
            NPC n = (NPC) target;
            attack *= NpcDamageBoost.getMultiplier(player, n, NpcDamageBoost.Style.MAGIC);
        }
        attack = attack * (1 + attackBonus / 64);
        A = Math.round(attack);
        if (target instanceof Player) {
            Player p2 = (Player) target;
            double defenceBonus = p2.getCombatDefinitions().getBonuses()[CombatDefinitions.MAGIC_DEF];
            double defence = Math.round((p2.getSkills().getLevel(Skills.MAGIC) * 0.7 + p2.getSkills().getLevel(Skills.DEFENCE) * 0.3) * p2.getMagePrayerMultiplier()) + 8;
            defence = defence * (1 + defenceBonus / 64);
            D = Math.round(defence);
        } else {
            NPC n = (NPC) target;
            n.setBonuses();
            double defenceBonus = n.getBonuses()[CombatDefinitions.NPC_MAGIC_DEFENCE];
            double defence = Math.round((n.getBonuses()[CombatDefinitions.NPC_MAGIC_LEVEL] * 0.7)
                    + (n.getBonuses()[CombatDefinitions.NPC_MAGIC_DEFENCE] * 0.3)) + 8;
            defence = defence + (1 + defenceBonus);
            D = Math.round(defence);
        }
        double prob = A / D;
        double random = Utils.getRandomDouble(100);
        if (A <= D) {
            prob = (A - 1) / (D * 2);
        } else if (A > D) {
            prob = 1 - (D + 1) / (A * 2);
        }
        if (prob < random / 100) {
            return 0;
        }
        max_hit = baseDamage;
        double boost = 1
                + ((player.getSkills().getLevel(Skills.MAGIC) - player.getSkills().getLevelForXp(Skills.MAGIC)) * 0.03);
        if (boost > 1)
            max_hit *= boost;
        double magicPerc = player.getCombatDefinitions().getBonuses()[CombatDefinitions.MAGIC_DAMAGE];
        if (player.getCombatDefinitions().getSpellId() >= 66 && player.getCombatDefinitions().getSpellId() <= 68) {
            if ((player.getEquipment().getWeaponId() == 2415 && player.getEquipment().getCapeId() == 2412)
                    || (player.getEquipment().getWeaponId() == 2416 && player.getEquipment().getCapeId() == 2413)
                    || (player.getEquipment().getWeaponId() == 2417 && player.getEquipment().getCapeId() == 2414)) {
                if (player.getChargeDelay() > Utils.currentTimeMillis())
                    max_hit += 100;
            }
        }
        if (spellcasterGloves > 0) {
            if (baseDamage > 60 || spellcasterGloves == 28 || spellcasterGloves == 25) {
                magicPerc += 17;
                if (target instanceof Player) {
                    Player p2 = (Player) target;
                    p2.drainLevel(Skills.ATTACK, (int) (p2.getSkills().getLevel(Skills.ATTACK) * 0.9));
                    p2.drainLevel(Skills.DEFENCE, (int) (p2.getSkills().getLevel(Skills.DEFENCE) * 0.9));
                    p2.drainLevel(Skills.STRENGTH, (int) (p2.getSkills().getLevel(Skills.STRENGTH) * 0.9));
                    p2.sm("Your melee skills have been drained.");
                    player.sm("Your spell weakened your enemy.");
                }
                player.getPackets().sendGameMessage("Your magic surged with extra power.");
            }
        }
        boost = (magicPerc / 100) + 1;
        max_hit *= boost;
        if (Utils.random(3) == 0 && fullAhrimsEquipped(player)) {
            max_hit *= 1.05;
        }
        if (target instanceof NPC) {
            NPC n = (NPC) target;
            max_hit *= NpcDamageBoost.getMultiplier(player, n, NpcDamageBoost.Style.MAGIC);
            if (player.getAuraManager().isActivated())
                max_hit *= player.getAuraManager().getMagicAccurayMultiplier();
        }
        return max_hit;
    }

    private int rangeAttack(final Player player) {
        final int weaponId = player.getEquipment().getWeaponId();
        final int attackStyle = player.getCombatDefinitions().getAttackStyle();
        int combatDelay = getRangeCombatDelay(player, weaponId, attackStyle);
        int soundId = getSoundId(weaponId, attackStyle);
        if (player.getCombatDefinitions().isUsingSpecialAttack()) {
            int specAmt = getSpecialAmmount(weaponId);
            if (specAmt == 0) {
                player.getPackets().sendGameMessage("This weapon has no special Attack.");
                player.getCombatDefinitions().desecreaseSpecialAttack(0);
                return combatDelay;
            }
            if (player.getCombatDefinitions().hasRingOfVigour())
                specAmt *= 0.9;
            if (player.getInventory().containsItem(773, 1))
                specAmt *= 0.0;
            if (player.getCombatDefinitions().getSpecialAttackPercentage() < specAmt) {
                player.getPackets().sendGameMessage("You don't have enough power left.");
                player.getCombatDefinitions().desecreaseSpecialAttack(0);
                return combatDelay;
            }
            player.getCombatDefinitions().desecreaseSpecialAttack(specAmt);
            switch (weaponId) {
                case 19149:// zamorak bow
                case 19151:
                    player.animate(new Animation(426));
                    player.gfx(new Graphics(97));
                    World.sendFastBowProjectile(player, target, 100);
                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
                    dropAmmo(player, 1);
                    break;
                case 19146:
                case 19148:// guthix bow
                    player.animate(new Animation(426));
                    player.gfx(new Graphics(95));
                    World.sendFastBowProjectile(player, target, 98);
                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
                    dropAmmo(player, 1);
                    break;
                case 19143:// saradomin bow
                case 19145:
                    player.animate(new Animation(426));
                    player.gfx(new Graphics(96));
                    // World.sendProjectile(player, target, 99, 41, 16, 25, 35, 16);
                    World.sendFastBowProjectile(player, target, 99);
                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
                    dropAmmo(player, 1);
                    break;
                case 10034:
                case 10033:
                    attackTarget(getMultiAttackTargets(player), new MultiAttack() {

                        private boolean nextTarget; // real target is first player
                        // on array

                        @Override
                        public boolean attack() {
                            int damage = getRandomMaxHit(player, weaponId, attackStyle, true, true,
                                    weaponId == 10034 ? 1.2 : 1.0, true);
                            player.animate(new Animation(2779));
                            World.sendProjectile(player, target, weaponId == 10034 ? 909 : 908, 41, 16, 31, 35, 16);
                            delayHit(1, weaponId, attackStyle, getRangeHit(player, damage));
                            WorldTasksManager.schedule(new WorldTask() {
                                @Override
                                public void run() {
                                    if (player == null || target == null || player.isDead() || player.hasFinished()
                                            || target.isDead() || target.hasFinished())
                                        return;
                                    target.gfx(new Graphics(2739, 0, 96 << 16));
                                    dropAmmo(player, 1);
                                }
                            }, 2);
                            if (!nextTarget) {
                                if (damage == -1)
                                    return false;
                                nextTarget = true;
                            }
                            return nextTarget;

                        }
                    });
                    break;
                case 859: // magic longbow
                case 10284: // Magic composite bow
                case 18332: // Magic longbow (sighted)
                    player.animate(new Animation(getWeaponAttackEmote(weaponId, attackStyle)));
                    World.sendSlowBowProjectile(player, target, 249);
                    delayHit(Utils.getDistance(player, target) > 3 ? 3 : 1, weaponId, attackStyle,
                            getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
                    dropAmmo(player, 2);
                    break;
                case 861: // magic shortbow
                    player.animate(new Animation(1074));
                    player.playSound(2545, 1);
                    World.sendMSBProjectile(player, target, 249);
                    World.sendMSBProjectile2(player, target, 249);
                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
                    delayHit(Utils.getDistance(player, target) > 3 ? 3 : 1, weaponId, attackStyle,
                            getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
                    dropAmmo(player, 2);
                    break;
                case 15241: // Hand cannon
                    player.animate(new Animation(12175));
                    WorldTasksManager.schedule(new WorldTask() {
                        int loop = 0;

                        @Override
                        public void run() {
                            if ((target.isDead() || player.isDead() || loop > 6)) {
                                stop();
                                return;
                            }
                            if (loop == 2) {
                                if (player.getEquipment().getWeaponId() == 15241) {
                                    player.gfx(new Graphics(2138));
                                    player.animate(new Animation(12153));
                                    World.sendCannonProjectile(player, target, 2143);
                                    dropAmmo(player, 1);
                                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                                            getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true)));
                                    player.getActionManager().setActionDelay(1);
                                } else {
                                    player.getCombatDefinitions().restoreSpecialAttack(50);
                                }
                            } else if (loop == 3) {
                                stop();
                            }
                            loop++;
                        }
                    }, 0, (int) 0.25);
                    combatDelay = 9;
                    break;
                case 11235: // dark bows
                case 15701:
                case 15702:
                case 15703:
                case 15704:
                    int ammoId = player.getEquipment().getAmmoId();
                    player.animate(new Animation(getWeaponAttackEmote(weaponId, attackStyle)));
                    player.gfx(new Graphics(getStartArrowProjectileId(weaponId, ammoId), 0, 100));
                    boolean dragonArrows = ItemDefinitions.getItemDefinitions(player.getEquipment().getAmmoId()).getName()
                            .toLowerCase().contains("dragon arrow");
                    int damage = getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.270, true);
                    int damage2 = getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.270, true);
                    int minimum = dragonArrows ? 80 : 50;
                    if (damage < minimum) {
                        damage = minimum;
                    } else
                        damage += minimum;
                    if (damage2 < minimum) {
                        damage2 = minimum;
                    } else
                        damage2 += minimum;
                    World.sendSlowBowProjectile(player, target, dragonArrows ? 1099 : 1103);
                    World.sendSlowBow2Projectile(player, target, dragonArrows ? 1099 : 1103);
                    int distance = Utils.getDistance(player, target);
                    delayHit(distance >= 5 ? 2 : distance >= 3 ? 1 : 1, weaponId, attackStyle, getRangeHit(player, damage));
                    delayHit(distance >= 5 ? 4 : distance >= 3 ? 1 : 2, weaponId, attackStyle,
                            getRangeHit(player, damage2));
                    checkSwiftGlovesEffect(player, attackStyle, weaponId, damage2, getArrowProjectileId(weaponId, ammoId),
                            2);
                    WorldTasksManager.schedule(new WorldTask() {
                        @Override
                        public void run() {
                            if (player == null || target == null || player.isDead() || player.hasFinished()
                                    || target.isDead() || target.hasFinished())
                                return;
                            target.gfx(new Graphics(dragonArrows ? 1100 : 1103, 0, 100));
                            target.gfx(new Graphics(dragonArrows ? 1100 : 1103, 0, 100));
                        }
                    }, distance == 3 ? 1 : 2);
                    break;
                case 14684: // zanik cbow
                    player.animate(new Animation(11359));
                    player.gfx(new Graphics(1714));
                    WorldTasksManager.schedule(new WorldTask() {
                        int loop = 0;

                        @Override
                        public void run() {
                            if ((target.isDead() || player.isDead() || loop > 3)) {
                                stop();
                                return;
                            }
                            if (loop == 1) {
                                ItemDefinitions defs = ItemDefinitions
                                        .getItemDefinitions(player.getEquipment().getWeaponId());
                                World.sendCBOWProjectile(player, target, 2001);
                                delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                                        getRangeHit(player,
                                                getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true) + 30
                                                        + Utils.getRandom(120)));
                                dropAmmo(player, 1);
                                player.getActionManager().setActionDelay(defs.getAttackSpeed());
                            } else if (loop == 3)
                                stop();
                            loop++;
                        }
                    }, 0, (int) 0.25);
                    break;
                case 13954:// morrigan javelin
                case 12955:
                case 13956:
                case 13879:
                case 13880:
                case 13881:
                case 13882:
                    player.gfx(new Graphics(1836));
                    player.animate(new Animation(10501));
                    World.sendThrowProjectile(player, target, 1837);
                    final int hit = getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true);
                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, hit));
                    processMorriganJavelins(player, hit);
                    dropAmmo(player, -1);
                    break;

                case 13883:
                case 13957:// morigan thrown axe
                    player.gfx(new Graphics(1838));
                    player.animate(new Animation(10504));
                    World.sendThrowProjectile(player, target, 1839);
                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.2, true)));
                    dropAmmo(player, -1);
                    break;
                default:
                    Logger.log(this, "Unhandled Special Attack from " + weaponId);
                    player.getPackets().sendGameMessage("This weapon has no special Attack.");
                    return combatDelay;
            }
        } else {
            if (weaponId != -1) {
                String weaponName = ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase();
                if (weaponName.toLowerCase().contains("throwing axe") || weaponName.toLowerCase().contains("knife")
                        || weaponName.toLowerCase().contains("dart") || weaponName.toLowerCase().contains("saga")
                        || weaponName.toLowerCase().contains("javelin") || weaponId == 6522
                        || weaponName.toLowerCase().contains("thrownaxe")) {
                    int hit = getRandomMaxHit(player, weaponId, attackStyle, true);
                    player.gfx(new Graphics(getStartThrowProjectileId(weaponId), 0, 100));
                    if (weaponName.toLowerCase().contains("dart"))
                        World.sendDartProjectile(player, target, getThrowProjectileId(weaponId));
                    else
                        World.sendThrowProjectile(player, target, getThrowProjectileId(weaponId));
                    checkSwiftGlovesEffect(player, attackStyle, weaponId, hit, getThrowProjectileId(weaponId), 4);
                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, hit));
                    dropAmmo(player, -1);
                    player.getAppearence().generateAppearenceData();
                } else if (weaponName.contains("chinchompa")) {
                    player.setNextAnimationNoPriority(new Animation(2779), player);
                    World.sendThrowProjectile(player, target, weaponId == 10034 ? 909 : 908);
                    dropAmmo(player, -1);
                    attackTarget(getMultiAttackTargets(player), new MultiAttack() {

                        private boolean nextTarget; // real target is first
                        // player
                        // on array

                        @Override
                        public boolean attack() {
                            int damage = getRandomMaxHit(player, weaponId, attackStyle, true, true,
                                    weaponId == 10034 ? 1.2 : 1.0, true);
                            final Entity[] targets = getMultiAttackTargets(player);
                            delayHit(1, weaponId, attackStyle, getRangeHit(player, damage));
                            WorldTasksManager.schedule(new WorldTask() {
                                @Override
                                public void run() {
                                    for (Entity e : targets) {
                                        e.gfx(new Graphics(2739, 0, 100));
                                    }
                                    target.gfx(new Graphics(2739, 0, 100));
                                }
                            }, 1);
                            if (!nextTarget) {
                                if (damage == -1) {
                                    return false;
                                }
                                nextTarget = true;
                            }
                            return nextTarget;

                        }
                    });
                } else if ((weaponName.toLowerCase().contains(" crossbow")
                        || weaponName.toLowerCase().equals("crossbow"))
                        && !weaponName.toLowerCase().contains("karil's")) {

                    int damage = 0;
                    int ammoId = player.getEquipment().getAmmoId();
                    int random = Utils.random(weaponId == 18357 ? 7 : 8);
                    if (ammoId != -1 && random == 1) {
                        switch (ammoId) {
                            case 9241:
                                damage = getRandomMaxHit(player, weaponId, attackStyle, true);
                                target.gfx(new Graphics(752));
                                target.getPoison().makePoisoned(48);
                                soundId = 2914;
                                break;
                            case 9237:
                                damage = getRandomMaxHit(player, weaponId, attackStyle, true);
                                target.gfx(new Graphics(755));
                                if (target instanceof Player) {
                                    Player p2 = (Player) target;
                                    p2.stopAll();
                                } else {
                                    NPC n = (NPC) target;
                                    n.setTarget(null);
                                }
                                soundId = 2914;
                                break;
                            case 9242:
                                max_hit = Short.MAX_VALUE;
                                damage = (int) (target.getHitpoints() * 0.2);
                                target.gfx(new Graphics(754));
                                player.applyHit(new Hit(target,
                                        player.getHitpoints() > 20 ? (int) (player.getHitpoints() * 0.1) : 1,
                                        HitLook.REFLECTED_DAMAGE));
                                soundId = 2912;
                                break;
                            case 9243:
                                damage = getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.15, true);
                                target.gfx(new Graphics(758));
                                soundId = 2913;
                                if (target instanceof Player) {
                                    Player targetPlayer = ((Player) target);
                                    int amountLeft;
                                    if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.DEFENCE,
                                            damage / 20)) > 0) {
                                        if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.STRENGTH,
                                                amountLeft)) > 0) {
                                            if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.PRAYER,
                                                    amountLeft)) > 0) {
                                                if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.ATTACK,
                                                        amountLeft)) > 0) {
                                                    if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.MAGIC,
                                                            amountLeft)) > 0) {
                                                        if (targetPlayer.getSkills().drainLevel(Skills.RANGE,
                                                                amountLeft) > 0) {
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    NPC n = (NPC) target;
                                    int npcDef = n.getBonuses()[CombatDefinitions.NPC_RANGE_DEFENCE];
                                    int temporaryDef = 0;
                                    if (n.getTemporaryAttributtes().get("NPC_RANGE_DEFENCE") == null) {
                                        temporaryDef = npcDef;
                                    } else
                                        temporaryDef = (int) n.getTemporaryAttributtes().get("NPC_RANGE_DEFENCE");
                                    int drain = damage / 20;
                                    if ((temporaryDef - drain) < 0)
                                        temporaryDef = 0;
                                    else
                                        temporaryDef -= drain;
                                    player.getPackets()
                                            .sendGameMessage("You drain " + n.getName() + " range defence by <col=a80d05>"
                                                    + drain + "</col> down to <col=a80d05>" + temporaryDef + "</col>.");
                                    n.getTemporaryAttributtes().put("NPC_RANGE_DEFENCE", temporaryDef);
                                }
                                break;
                            case 9244:
                                if (Combat.hasAntiDragProtection(target))
                                    break;
                                damage = getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.45, true);
                                if (damage > 0) {
                                    target.gfx(new Graphics(756));
                                    soundId = 2915;
                                }
                                break;
                            case 9245:
                                damage = getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.20, true);
                                target.gfx(new Graphics(753));
                                player.heal((int) (damage * 0.25));
                                soundId = 2917;
                                break;
                            case 24116:
                                damage = getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true);
                                if (attackStyle == 0) {
                                    target.gfx(new Graphics(3025));
                                    if (target.temporaryAttribute().get("bakriminel_immunity") != Boolean.TRUE) {
                                        if (target instanceof Player) {
                                            ((Player) target).getPackets().sendGameMessage("You feel slowed down.");
                                        }
                                        target.temporaryAttribute().put("bakriminel_immunity", Boolean.TRUE);
                                        target.temporaryAttribute().put("bakriminel_effect", Boolean.TRUE);
                                        final Entity t = target;
                                        WorldTasksManager.schedule(new WorldTask() {
                                            @Override
                                            public void run() {
                                                t.temporaryAttribute().remove("bakriminel_effect");
                                                WorldTasksManager.schedule(new WorldTask() {
                                                    @Override
                                                    public void run() {
                                                        t.temporaryAttribute().remove("bakriminel_immunity");
                                                        stop();
                                                    }
                                                }, 15);
                                                stop();
                                            }
                                        }, 30);
                                    }
                                }

                                if (attackStyle == 1) {
                                    target.gfx(new Graphics(3024));
                                    final Entity finalTarget = target;
                                    WorldTasksManager.schedule(new WorldTask() {
                                        int damage1 = 8;

                                        @Override
                                        public void run() {
                                            if (finalTarget.isDead() || finalTarget.hasFinished()) {
                                                stop();
                                                return;
                                            }
                                            if (damage1 > 0) {
                                                damage1 -= 1;
                                                finalTarget.applyHit(
                                                        new Hit(player, Utils.random(15), HitLook.REGULAR_DAMAGE));
                                            } else {
                                                finalTarget.applyHit(new Hit(player, damage1, HitLook.REGULAR_DAMAGE));
                                                stop();
                                            }
                                        }
                                    }, 4, 2);
                                }
                                if (attackStyle == 2) {
                                    target.gfx(new Graphics(3025));
                                    int random1 = new Random().nextInt(100);
                                    if (random1 > 50) {
                                        if (target instanceof Player) {
                                            Player p2 = (Player) target;
                                            p2.setRunEnergy(p2.getRunEnergy() > 15 ? p2.getRunEnergy() - 15 : 0);
                                        }
                                    } else if (random1 < 50) {
                                        target.addFreezeDelay(5000, true);
                                    }
                                }
                                break;
                            default:
                                damage = getRandomMaxHit(player, weaponId, attackStyle, true);
                        }
                    } else {
                        damage = getRandomMaxHit(player, weaponId, attackStyle, true);
                        checkSwiftGlovesEffect(player, attackStyle, weaponId, damage, getBoltGfxId(weaponId, ammoId),
                                3);
                    }
                    World.sendCBOWProjectile(player, target,

                            getBoltGfxId(weaponId, ammoId));
                    delayHit(Utils.getDistance(player, target) > 4 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, damage));
                    if (weaponId != 4740)
                        dropAmmo(player);
                    else
                        player.getEquipment().removeAmmo(ammoId, 1);
                } else if (weaponId == 15241) {// handcannon
                    if (Utils.getRandom(player.getSkills().getLevel(Skills.FIREMAKING) << 1) == 0) {
                        // explode
                        player.gfx(new Graphics(2140));
                        player.getEquipment().getItems().set(3, null);
                        player.getEquipment().refresh(3);
                        player.getAppearence().generateAppearenceData();
                        player.applyHit(new Hit(player, Utils.getRandom(150) + 10, HitLook.REGULAR_DAMAGE));
                        player.animate(new Animation(12175));
                        return combatDelay;
                    } else {
                        player.gfx(new Graphics(2138));
                        World.sendCannonProjectile(player, target, 2143);
                        delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                                getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true)));
                        dropAmmo(player, -2);
                    }
                } else if (weaponName.toLowerCase().contains("crystal")) {
                    player.animate(new Animation(getWeaponAttackEmote(weaponId, attackStyle)));
                    player.gfx(new Graphics(250, 0, 100));
                    int hit = getRandomMaxHit(player, weaponId, attackStyle, true);
                    World.sendFastBowProjectile(player, target, 249);
                    checkSwiftGlovesEffect(player, attackStyle, weaponId, hit, 249, 1);
                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, hit));
                } else if (weaponId == 19830) {
                    player.animate(new Animation(789));
                    int hit = getRandomMaxHit(player, weaponId, attackStyle, true);
                    World.sendFastBowProjectile(player, target, 1);
                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, hit));
                } else if (weaponName.toLowerCase().contains("zaryte")) {
                    final int ammoId = player.getEquipment().getAmmoId();
                    player.gfx(new Graphics(getStartArrowProjectileId(weaponId, ammoId), 0, 100));
                    player.animate(new Animation(getWeaponAttackEmote(weaponId, attackStyle)));
                    int hit = getRandomMaxHit(player, weaponId, attackStyle, true);
                    World.sendFastBowProjectile(player, target,
                            ammoId == 11212 ? 1066 : getArrowProjectileId(weaponId, ammoId));
                    checkSwiftGlovesEffect(player, attackStyle, weaponId, hit,
                            ammoId == 11212 ? 1066 : getArrowProjectileId(weaponId, ammoId), 1);
                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, hit));
                    dropAmmo(player);
                } else if (weaponId == 21364) {// sagaie
                    player.getEquipment().removeAmmo(weaponId, -1);
                    player.getAppearence().generateAppearenceData();
                    player.animate(new Animation(3236));
                    World.sendFastBowProjectile(player, target, getThrowProjectileId(weaponId));
                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true)));
                } else if (weaponId == 11235 || weaponId == 15701 || weaponId == 15702 || weaponId == 15703
                        || weaponId == 15704) { // dbows
                    final int ammoId = player.getEquipment().getAmmoId();
                    player.gfx(new Graphics(getStartArrowProjectileId(weaponId, ammoId), 0, 100));
                    int hit = getRandomMaxHit(player, weaponId, attackStyle, true);
                    World.sendSlowBowProjectile(player, target, getArrowProjectileId(weaponId, ammoId));
                    World.sendSlowBow2Projectile(player, target, getArrowProjectileId(weaponId, ammoId));
                    checkSwiftGlovesEffect(player, attackStyle, weaponId, hit, getArrowProjectileId(weaponId, ammoId),
                            2);
                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true)));
                    delayHit(Utils.getDistance(player, target) > 3 ? 3 : 2, weaponId, attackStyle,
                            getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true)));
                    dropAmmo(player, 2);
                } else if (weaponName.toLowerCase().contains("karil's cross")) {
                    player.animate(new Animation(getWeaponAttackEmote(weaponId, attackStyle)));
                    World.sendCBOWProjectile(player, target, 27);
                    int karilDamage = getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true);
                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, karilDamage));
                    if (fullKarilsEquipped(player)) {
                        int random = Utils.random(3);
                        if (random == 0) {
                            delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                                    getRangeHit(player, karilDamage / 2));
                            World.sendCBOWSwiftProjectile(player, target, 27);
                        }
                    }
                    dropAmmo(player, 2);
                } else if (weaponId == 21365) { // Bolas
                    dropAmmo(player, -3);
                    player.animate(new Animation(3128));
                    World.sendFastBowProjectile(player, target, getThrowProjectileId(weaponId));
                    int delay = 15000;
                    if (target instanceof Player) {
                        Player p = (Player) target;
                        Item weapon = p.getEquipment().getItem(3);
                        boolean slashBased = weapon != null;
                        if (weapon != null) {
                            int slash = p.getCombatDefinitions().getBonuses()[CombatDefinitions.SLASH_ATTACK];
                            for (int i = 0; i < 5; i++) {
                                if (p.getCombatDefinitions().getBonuses()[i] > slash) {
                                    slashBased = false;
                                    break;
                                }
                            }
                        }
                        if (p.getInventory().containsItem(946, 1) || slashBased) {
                            delay /= 2;
                        }
                        if (p.getPrayer().usingPrayer(0, 18) || p.getPrayer().usingPrayer(1, 8)) {
                            delay /= 2;
                        }
                        if (delay < 5000) {
                            delay = 5000;
                        }
                    }
                    long currentTime = Utils.currentTimeMillis();
                    if (getRandomMaxHit(player, weaponId, attackStyle, true) > 0
                            && target.getFreezeImmuneDelay() < currentTime) {
                        target.addFreezeDelay(delay, true);
                        WorldTasksManager.schedule(new WorldTask() {
                            @Override
                            public void run() {
                                if (player == null || target == null || player.isDead() || player.hasFinished()
                                        || target.isDead() || target.hasFinished())
                                    return;
                                target.gfx(new Graphics(469, 0, 96));
                            }
                        }, 2);
                    }
                    playSound(soundId, player, target);
                    return combatDelay;
                } else { // bow/default
                    final int ammoId = player.getEquipment().getAmmoId();
                    player.gfx(new Graphics(getStartArrowProjectileId(weaponId, ammoId), 0, 100));
                    int hit = getRandomMaxHit(player, weaponId, attackStyle, true);
                    World.sendFastBowProjectile(player, target, getArrowProjectileId(weaponId, ammoId));
                    checkSwiftGlovesEffect(player, attackStyle, weaponId, hit, getArrowProjectileId(weaponId, ammoId),
                            1);
                    delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                            getRangeHit(player, hit));
                    if (weaponId != -1) {
                        if (!weaponName.toLowerCase().contains("crystal") && !weaponName.toLowerCase().contains("sling")
                                && !weaponName.toLowerCase().contains("karil")) {
                            dropAmmo(player);
                        }
                    }
                }

                player.setNextAnimationNoPriority(new Animation(getWeaponAttackEmote(weaponId, attackStyle)), target);
            }
        }
        playSound(soundId, player, target);
        return combatDelay;
    }

    /**
     * Handles the swift gloves effect.
     *
     * @param player      The player.
     * @param attackStyle The attack style used.
     * @param weaponId    The weapon id.
     * @param hit         The hit done.
     * @param gfxId       The gfx id.
     * @param// hitDelay            The delay before hitting the target.
     * @param// startHeight         The start height of the original projectile.
     * @param// endHeight           The end height of the original projectile.
     * @param// speed               The speed of the original projectile.
     * @param// delay               The delay of the original projectile.
     * @param// curve               The curve of the original projectile.
     * @param// startDistanceOffset The start distance offset of the original
     * projectile.
     */
    private void checkSwiftGlovesEffect(Player player, int attackStyle, int weaponId, int hit, int gfxId, int bow) {
        // int bow;
        // 1 = shortbow/fast projectile
        // 2 = dbow/slow projectile
        // 3 = crossbow/bolt projectile
        // 4 = dart/knife projectile
        Item gloves = player.getEquipment().getItem(Equipment.SLOT_HANDS);
        if (gloves == null || !gloves.getDefinitions().getName().contains("Swift glove")) {
            return;
        }
        if (hit != 0 && hit < ((max_hit / 3) * 2) || new Random().nextInt(3) != 0) {
            return;
        }
        player.getPackets().sendGameMessage("You fired an extra shot.");
        if (bow == 1)
            World.sendFastBowSwiftProjectile(player, target, gfxId);
        if (bow == 2)
            World.sendSlowBowSwiftProjectile(player, target, gfxId);
        if (bow == 3)
            World.sendCBOWSwiftProjectile(player, target, gfxId);
        if (bow == 4)
            World.sendThrowSwiftProjectile(player, target, gfxId);
        delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
                getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true)));
        if (hit > (max_hit - 10)) {
            target.addFreezeDelay(10000, false);
            target.gfx(new Graphics(181, 0, 96));
        }

    }

    public void dropAmmo(Player player, int quantity) {
        if (quantity == -2) {
            final int ammoId = player.getEquipment().getAmmoId();
            player.getEquipment().removeAmmo(ammoId, 1);
        } else if (quantity == -1 || quantity == -3) {
            final int weaponId = player.getEquipment().getWeaponId();
            final int capeId = player.getEquipment().getCapeId();
            ItemDefinitions defs = ItemDefinitions.getItemDefinitions(player.getEquipment().getWeaponId());
            if (weaponId != -1) {
                if ((capeId == 10498 || capeId == 10499 || capeId == 20068 || capeId == 20769 || capeId == 20771)
                        && Utils.getRandom(2) <= 1) {
                    /*
                     * player.getPackets().sendGameMessage( "Your " +
                     * ItemDefinitions.getItemDefinitions( player.getEquipment().getCapeId())
                     * .getName() + " saved you some ammo.");
                     */
                    return;
                }
                player.getEquipment().removeAmmo(weaponId, quantity);
                if (!defs.getName().toLowerCase().contains("chinchompa")) {
                    if (Utils.getRandom(3) != 0) {
                        World.updateGroundItem(new Item(weaponId, quantity),
                                new WorldTile(target.getCoordFaceX(target.getSize()),
                                        target.getCoordFaceY(target.getSize()), target.getPlane()),
                                player, 60, 0);
                    }
                }
            }
        } else {
            final int ammoId = player.getEquipment().getAmmoId();
            final int capeId = player.getEquipment().getCapeId();
            if (ammoId != -1) {
                if ((capeId == 10498 || capeId == 10499 || capeId == 20068 || capeId == 20769 || capeId == 20771)
                        && Utils.getRandom(2) <= 1) {
                    if (ammoId != 4740 && ammoId != 19152 && ammoId != 19157 && ammoId != 19162 && ammoId != 15243) {
                        /*
                         * player.getPackets().sendGameMessage( "Your " +
                         * ItemDefinitions.getItemDefinitions( player.getEquipment()
                         * .getCapeId()).getName() + " saved you some ammo.");
                         */
                        return;
                    }
                }
                player.getEquipment().removeAmmo(ammoId, quantity);
                if (ammoId != 4740 && ammoId != 19152 && ammoId != 19157 && ammoId != 19162 && ammoId != 15243) {
                    if (Utils.getRandom(3) != 0) {
                        World.updateGroundItem(new Item(ammoId, quantity),
                                new WorldTile(target.getCoordFaceX(target.getSize()),
                                        target.getCoordFaceY(target.getSize()), target.getPlane()),
                                player, 60, 0);
                    }
                }
            }
        }
    }

    public void dropAmmo(Player player) {
        dropAmmo(player, 1);
    }

    public int getBoltGfxId(int weaponId, int ammoId) {
        if (ammoId == 24116) {
            return 3023;
        }
        if (ammoId == 8882) {
            return 740;
        }
        return 27;
    }

    private int getAttackDistance(Player player) {
        int weaponId = player.getEquipment().getWeaponId();
        int attackStyle = player.getCombatDefinitions().getAttackStyle();
        String name = ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase();
        if (weaponId == 24203 || name.contains("polypore") || name.contains("seercull")
                || name.contains("armadyl crossbow") || name.contains("armadyl crossbow"))
            return attackStyle != 1 ? 8 : 10;
        if (name.contains("dart"))
            return attackStyle != 2 ? 3 : 5;
        if (name.contains("knife") || name.contains("throwaxe") || name.contains("sling"))
            return attackStyle != 2 ? 4 : 6;
        if (name.contains("javelin") || name.contains("comp ogre bow") || name.contains("blowpipe"))
            return attackStyle != 2 ? 5 : 7;
        if (name.contains("dorgeshuun"))
            return attackStyle != 2 ? 6 : 8;
        if (name.contains("longbow") || name.contains("chinchompa") || name.contains("ballista")
                || name.contains("3rd age bow"))
            return attackStyle != 2 ? 9 : 10;
        if (name.contains("zaryte") || name.contains("crystal") || name.contains("dark bow")
                || name.contains("ogre bow") || name.contains("twisted bow") || name.contains("composite"))
            return 10;
        return attackStyle != 2 ? 7 : 9;
    }

    public static int getStartArrowProjectileId(int weaponId, int arrowId) {
        String name = ItemDefinitions.getItemDefinitions(arrowId).getName().toLowerCase();
        if (arrowId == 9706)
            return 806;
        if (name.contains("bronze arrow")) {
            return 19;
        }
        if (name.contains("iron arrow")) {
            return 18;
        }
        if (name.contains("steel arrow")) {
            return 20;
        }
        if (name.contains("mithril arrow")) {
            return 21;
        }
        if (name.contains("adamant arrow")) {
            return 22;
        }
        if (name.contains("rune arrow")) {
            return 24;
        }
        if (arrowId == 19152) {
            return 96;
        }
        if (arrowId == 19157) {
            return 95;
        }
        if (arrowId == 19162) {
            return 97;
        }
        if (name.contains("dragon arrow")) {
            return 1116;
        }
        return -1; // bronze default
    }

    public int getArrowProjectileId(int weaponId, int arrowId) {
        String name = ItemDefinitions.getItemDefinitions(arrowId).getName().toLowerCase();
        if (arrowId == 9706)
            return 805;
        if (name.contains("bronze arrow")) {
            return 10;
        }
        if (name.contains("iron arrow")) {
            return 9;
        }
        if (name.contains("steel arrow")) {
            return 11;
        }
        if (name.contains("mithril arrow")) {
            return 12;
        }
        if (name.contains("adamant arrow")) {
            return 13;
        }
        if (name.contains("rune arrow")) {
            return 15;
        }
        if (arrowId == 19152) {
            return 99;
        }
        if (arrowId == 19157) {
            return 98;
        }
        if (arrowId == 19162) {
            return 100;
        }
        if (name.contains("dragon arrow")) {
            return 1120;
        }
        return -1;// bronze default
    }

    public static int getStartThrowProjectileId(int weaponId) {
        String name = ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase();
        if (name.contains("bronze dart")) {
            return 232;
        } else if (name.contains("iron dart")) {
            return 233;
        } else if (name.contains("steel dart")) {
            return 234;
        } else if (name.contains("black dart")) {
            return 235;
        } else if (name.contains("mithril dart")) {
            return 235;
        } else if (name.contains("adamant dart")) {
            return 236;
        } else if (name.contains("rune dart")) {
            return 237;
        } else if (name.contains("dragon dart")) {
            return 1123;
        }
        return -1;
    }

    public static int getThrowProjectileId(int weaponId) {
        String name = ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase();
        if (name.contains("bronze knife")) {
            return 212;
        } else if (name.contains("iron knife")) {
            return 213;
        } else if (name.contains("steel knife")) {
            return 214;
        } else if (name.contains("black knife")) {
            return 215;
        } else if (name.contains("mithril knife")) {
            return 216;
        } else if (name.contains("adamant knife")) {
            return 217;
        } else if (name.contains("rune knife")) {
            return 218;
            // darts
        } else if (name.contains("bronze dart")) {
            return 226;
        } else if (name.contains("iron dart")) {
            return 227;
        } else if (name.contains("steel dart")) {
            return 228;
        } else if (name.contains("black dart")) {
            return 229;
        } else if (name.contains("mithril dart")) {
            return 229;
        } else if (name.contains("adamant dart")) {
            return 230;
        } else if (name.contains("rune dart")) {
            return 231;
        } else if (name.contains("dragon dart")) {
            return 1122;
        } else if (name.contains("sagaie")) {
            return 466;
        } else if (name.contains("bolas")) {
            return 468;
        } else if (name.contains("morrigan's javelin")) {
            return 1837;
        } else if (name.contains("morrigan's throwing")) {
            return 1839;
        } else if (name.contains("toktz-xil-ul")) {
            return 442;
        }
        return -1;
    }

    @SuppressWarnings("unused")
    private int getRangeHitDelay(Player player) {
        return Utils.getDistance(player.getX(), player.getY(), target.getX(), target.getY()) >= 1 ? 1 : 1;
    }

    private int meleeAttack(final Player player) {
        int weaponId = player.getEquipment().getWeaponId();
        final int attackStyle = player.getCombatDefinitions().getAttackStyle();
        int combatDelay = getMeleeCombatDelay(player, weaponId);
        int soundId = getSoundId(weaponId, attackStyle);
        if (weaponId == -1) {
            Item gloves = player.getEquipment().getItem(Equipment.SLOT_HANDS);
            if (gloves != null && gloves.getDefinitions().getName().contains("Goliath gloves")) {
                weaponId = -2;
            }
        }
        if (player.getCombatDefinitions().isUsingSpecialAttack()) {
            if (!specialExecute(player))
                return combatDelay;
            switch (weaponId) {
                case 11061:
                    player.animate(new Animation(6147));
                    player.gfx(new Graphics(1052, 0, -50));
                    int damage = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.1, true);
                    if (target instanceof Player) {
                        Player p2 = (Player) target;
                        p2.getPrayer().drainPrayer(damage);
                    }
                    player.getPrayer().restorePrayer(damage);
                    delayHit(1, weaponId, attackStyle, getMeleeHit(player, damage));
                    break;

                case 15442:// whip start
                case 15443:
                case 15444:
                case 15441:
                case 4151:
                case 23691:
                    player.animate(new Animation(11971));
                    target.gfx(new Graphics(2108, 0, 100));
                    if (target instanceof Player) {
                        Player p2 = (Player) target;
                        p2.setRunEnergy(p2.getRunEnergy() > 25 ? p2.getRunEnergy() - 25 : 0);
                    }
                    delayNormalHit(weaponId, attackStyle,
                            getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true)));
                    break;
                case 21371: // Abby Vine Whip
                case 21372:
                case 21373:
                case 21374:
                case 21375:
                    processVineCall(player);
                    break;

                case 11730: // sara sword
                case 23690:
                    player.animate(new Animation(11993));
                    target.gfx(new Graphics(1194));
                    delayNormalHit(weaponId, attackStyle,
                            getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.1, true)),
                            getMagicHit(player, 50 + Utils.getRandom(100)));
                    soundId = 3853;
                    break;
                case 1249:// d spear
                case 1263:
                case 3176:
                case 5716:
                case 5730:
                case 13770:
                case 13772:
                case 13774:
                case 13776:
                case 11716:
                    player.animate(new Animation(12017));
                    player.stopAll();
                    target.gfx(new Graphics(80, 5, 60));
                    if (target instanceof Player) {
                        final Player other = (Player) target;
                        other.lock(5);
                        other.stopAll();
                        other.addFoodDelay(3000);
                        if (!target.addWalkSteps(target.getX() - player.getX() + target.getX(),
                                target.getY() - player.getY() + target.getY(), 1))
                            player.setNextFaceEntity(target);
                        target.setDirection(player.getDirection());
                    } else {
                        NPC n = (NPC) target;
                        n.setFreezeDelay(3000);
                        n.resetCombat();
                    }
                    break;
                case 11698: // sgs
                case 24514:
                case 23681:
                    player.animate(new Animation(12019));
                    player.gfx(new Graphics(2109));
                    int sgsdamage = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.1, true);
                    player.heal(sgsdamage / 2);
                    player.getPrayer().restorePrayer((sgsdamage / 4) * 10);
                    delayNormalHit(weaponId, attackStyle, getMeleeHit(player, sgsdamage));
                    break;
                case 11696: // bgs
                case 24512:
                case 23680:
                    player.animate(new Animation(11991));
                    player.gfx(new Graphics(2114));
                    int damage1 = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.15, true);
                    delayNormalHit(weaponId, attackStyle, getMeleeHit(player, damage1));
                    if (target instanceof Player) {
                        Player targetPlayer = ((Player) target);
                        int amountLeft;
                        if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.DEFENCE, damage1 / 10)) > 0) {
                            if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.STRENGTH, amountLeft)) > 0) {
                                if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.PRAYER, amountLeft)) > 0) {
                                    if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.ATTACK, amountLeft)) > 0) {
                                        if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.MAGIC,
                                                amountLeft)) > 0) {
                                            if (targetPlayer.getSkills().drainLevel(Skills.RANGE, amountLeft) > 0) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (damage1 > 0) {
                            NPC n = (NPC) target;
                            int npcDef = n.getBonuses()[CombatDefinitions.NPC_DEFENCE_LEVEL];
                            int temporaryDef = 0;
                            if (n.getTemporaryAttributtes().get("NPC_DEFENCE_LEVEL") == null)
                                temporaryDef = npcDef;
                            else
                                temporaryDef = (int) n.getTemporaryAttributtes().get("NPC_DEFENCE_LEVEL");
                            int drain = damage1 / 20;
                            if ((temporaryDef - drain) < 0)
                                temporaryDef = 0;
                            else
                                temporaryDef -= drain;
                            player.getPackets()
                                    .sendGameMessage("You drain " + n.getName() + " defence level by <col=a80d05>" + drain
                                            + "</col> down to <col=a80d05>" + temporaryDef + "</col>.");
                            n.getTemporaryAttributtes().put("NPC_DEFENCE_LEVEL", temporaryDef);
                        }
                    }
                    break;
                case 13902: // statius hammer
                case 13904:
                    player.animate(new Animation(10505));
                    player.gfx(new Graphics(1840));
                    int warhammerDamage = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.21, true);
                    delayNormalHit(weaponId, attackStyle, getMeleeHit(player, warhammerDamage));
                    if (target instanceof Player) {
                        Player targetPlayer = ((Player) target);
                        int amountLeft;
                        if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.DEFENCE, warhammerDamage / 10)) > 0) {
                            if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.STRENGTH, amountLeft)) > 0) {
                                if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.PRAYER, amountLeft)) > 0) {
                                    if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.ATTACK, amountLeft)) > 0) {
                                        if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.MAGIC,
                                                amountLeft)) > 0) {
                                            if (targetPlayer.getSkills().drainLevel(Skills.RANGE, amountLeft) > 0) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (warhammerDamage > 0) {
                            NPC n = (NPC) target;
                            int npcDef = n.getBonuses()[CombatDefinitions.NPC_DEFENCE_LEVEL];
                            int temporaryDef = 0;
                            if (n.getTemporaryAttributtes().get("NPC_DEFENCE_LEVEL") == null)
                                temporaryDef = npcDef;
                            else
                                temporaryDef = (int) n.getTemporaryAttributtes().get("NPC_DEFENCE_LEVEL");
                            int drain = warhammerDamage / 20;
                            if ((temporaryDef - drain) < 0)
                                temporaryDef = 0;
                            else
                                temporaryDef -= drain;
                            player.getPackets()
                                    .sendGameMessage("You drain " + n.getName() + " defence level by <col=a80d05>" + drain
                                            + "</col> down to <col=a80d05>" + temporaryDef + "</col>.");
                            n.getTemporaryAttributtes().put("NPC_DEFENCE_LEVEL", temporaryDef);
                        }
                    }
                    break;
                case 11694: // ags
                case 24510:
                case 23679:
                    player.setNextAnimationNoPriority(new Animation(11989), player);
                    player.gfx(new Graphics(2113));
                    delayNormalHit(weaponId, attackStyle,
                            getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.375, true)));
                    break;

                case 13899: // vls
                case 13901:
                    player.animate(new Animation(10502));
                    delayNormalHit(weaponId, attackStyle,
                            getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.25, true)));
                    break;
                case 13905: // vesta spear
                case 13907:
                    player.animate(new Animation(10499));
                    player.gfx(new Graphics(1835));
                    delayNormalHit(weaponId, attackStyle,
                            getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.25, true)));
                    break;

                case 19780:
                case 19784: // korasi sword
                    player.animate(new Animation(14788));
                    player.gfx(new Graphics(1729));
                    final double multiplier = 0.5 + Math.random();
                    attackTarget(getMultiAttackTargets(player), new MultiAttack() {

                        private boolean nextTarget; // real target is firsts
                        final int weaponId = player.getEquipment().getWeaponId();

                        // player on array

                        @Override
                        public boolean attack() {
                            final Entity[] targets = getMultiAttackTargets(player);
                            int korasiDamage = getMaxHit(player, weaponId, attackStyle, false, true, 1.0);
                            korasiDamage *= multiplier;
                            max_hit = (int) (korasiDamage);
                            Hit h = getMagicHit(player, korasiDamage / targets.length + 1);
                            h.setCriticalMark();
                            delayHit(0, weaponId, attackStyle, h);
                            player.getPackets().sendSound(3865, 0, 1);
                            player.getPackets().sendSound(3853, 0, 1);

                            WorldTasksManager.schedule(new WorldTask() {
                                @Override
                                public void run() {
                                    for (Entity e : targets)
                                        e.gfx(new Graphics(2795, 0, 100));
                                    target.gfx(new Graphics(2795, 0, 100));
                                }
                            });
                            if (!nextTarget) {
                                if (korasiDamage == -1)
                                    return false;
                                nextTarget = true;
                            }
                            return nextTarget;

                        }
                    });
                    return combatDelay;

                case 19787: // korasi sword
                    player.animate(new Animation(14788));
                    player.gfx(new Graphics(1729));
                    int korasiDamage = getMaxHit(player, weaponId, attackStyle, false, true, 1.0);
                    double multiplier1 = 0.5 + Math.random();
                    max_hit = (int) (korasiDamage * 1.5);
                    korasiDamage *= multiplier1;
                    Hit h = getMagicHit(player, korasiDamage);
                    h.setCriticalMark();
                    delayNormalHit(weaponId, attackStyle, h);
                    break;

                case 11700:
                case 24516:
                    int zgsdamage = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.1, true);
                    player.animate(new Animation(7070));
                    player.gfx(new Graphics(1221));
                    if (zgsdamage != 0 && target.getSize() == 1) {
                        target.gfx(new Graphics(2104));
                        target.setFreezeDelay(18000);
                    }
                    delayNormalHit(weaponId, attackStyle, getMeleeHit(player, zgsdamage));
                    break;
                case 14484:
                case 23695:
                    player.setNextAnimationNoPriority(new Animation(10961), player);
                    player.gfx(new Graphics(1950));
                    int[] hits = new int[]{0, 1};
                    int hit = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true);
                    for (int i = 20; i <= 80; i += 20)// all 4 d claw specs in right timing
                        player.getPackets().sendSound(7464, i, 1);
                    if (hit > 100) {
                        hits = new int[]{hit, hit / 2, (hit / 2) / 2, (hit / 2) - ((hit / 2) / 2)};
                    } else {
                        hit = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true);
                        if (hit > 100) {
                            hits = new int[]{0, hit, hit / 2, hit - (hit / 2)};
                        } else {
                            hit = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true);
                            if (hit > 100) {
                                hits = new int[]{0, 0, hit / 2, (hit / 2) + 10};
                            } else {
                                hit = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true);
                                if (hit > 100) {
                                    hits = new int[]{0, 0, 0, (int) (hit * 1.0)};
                                } else {
                                    int[] miss = {Utils.random(10), Utils.random(10)};
                                    for (int i = 0; i < miss.length; i++) {
                                        delayHit(0, weaponId, attackStyle, getMeleeHit(player, miss[i]));
                                    }
                                }
                            }
                        }
                    }
                    player.getPackets().sendSound(7464, 0, 1);
                    player.getPackets().sendSound(7465, 20, 1);
                    player.getPackets().sendSound(7466, 40, 1);
                    player.getPackets().sendSound(7467, 60, 1);
                    for (int i = 0; i < hits.length; i++) {
                        if (i > 1) {
                            delayHit(1, weaponId, attackStyle, getMeleeHit(player, hits[i]));
                        } else {
                            delayNormalHit(weaponId, attackStyle, getMeleeHit(player, hits[i]));
                        }
                    }
                    break;
                case 10887: // anchor
                    player.animate(new Animation(5870));
                    player.gfx(new Graphics(1027));
                    delayNormalHit(weaponId, attackStyle,
                            getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true)));
                    break;
                case 1305: // dragon long
                    player.animate(new Animation(12033));
                    player.gfx(new Graphics(2117));
                    delayNormalHit(weaponId, attackStyle,
                            getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.25, true)));
                    break;
                case 3204: // d hally
                    player.animate(new Animation(1203));
                    player.gfx(new Graphics(282, 0, 96));
                    delayNormalHit(weaponId, attackStyle,
                            getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.1, true)));
                    if (target.getSize() > 1)
                        delayHit(0, weaponId, attackStyle, getMeleeHit(player,
                                getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.1, true)));
                    break;
                case 4587: // dragon sci
                    player.animate(new Animation(12031));
                    player.gfx(new Graphics(2118));
                    Hit hit1 = getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true));
                    if (target instanceof Player) {
                        Player p2 = (Player) target;
                        if (hit1.getDamage() > 0)
                            p2.setPrayerDelay(5000);// 5 seconds
                    }
                    delayNormalHit(weaponId, attackStyle, hit1);
                    soundId = 2540;
                    break;
                case 1215: // dragon dagger
                case 5698: // dds
                    player.setNextAnimationNoPriority(new Animation(1062), player);
                    player.gfx(new Graphics(252, 0, 100));
                    if (target instanceof Player) {
                        delayNormalHit(weaponId, attackStyle,
                                getMeleeHit(player,
                                        getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.15, true)),
                                getMeleeHit(player,
                                        getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.15, true)));
                    } else {
                        delayNormalHit(weaponId, attackStyle, getMeleeHit(player,
                                getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.15, true)));
                        delayHit(1, weaponId, attackStyle, getMeleeHit(player,
                                getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.15, true)));
                    }
                    soundId = 2537;
                    break;
                case 1434: // dragon mace
                    player.animate(new Animation(1060));
                    player.gfx(new Graphics(251, 0, 100));
                    delayNormalHit(weaponId, attackStyle,
                            getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.45, true)));
                    soundId = 2541;
                    break;
                case 4153:
                    delayNormalHit(weaponId, attackStyle,
                            getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true)));
                    player.animate(new Animation(1667));
                    player.gfx(new Graphics(340, 0, 96 << 16));
                    break;
                case 14679:
                    delayNormalHit(weaponId, attackStyle,
                            getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true)));
                    player.animate(new Animation(10505));
                    player.gfx(new Graphics(1840, 0, 96 << 16));
                    break;
                default:
                    Logger.log(this, "Unhandled Special Attack from " + weaponId);
                    player.getPackets().sendGameMessage(
                            "This weapon has no special Attack, if you still see special bar please relogin.");
                    return combatDelay;
            }
        } else {
            if (weaponId == -2 && new Random().nextInt(15) == 0) {
                player.animate(new Animation(14417));
                final int attack = attackStyle;
                attackTarget(getMultiAttackTargets(player, 5, Integer.MAX_VALUE), new MultiAttack() {

                    private boolean nextTarget;

                    @Override
                    public boolean attack() {
                        target.addFreezeDelay(10000, true);
                        target.gfx(new Graphics(181, 0, 96));
                        final Entity t = target;
                        WorldTasksManager.schedule(new WorldTask() {
                            @Override
                            public void run() {
                                final int damage = getRandomMaxHit(player, -2, attack, false, true, 1.0, false);
                                t.gfx(new Graphics(181, 0, 96));
                                t.applyHit(new Hit(player, damage, HitLook.REGULAR_DAMAGE));

                                stop();
                            }
                        }, 1);
                        if (target instanceof Player) {
                            Player p = (Player) target;
                            for (int i = 0; i < 7; i++) {
                                if (i != 3 && i != 5) {
                                    p.getSkills().drainLevel(i, 7);
                                }
                            }
                            p.getPackets().sendGameMessage("Your stats have been drained!");
                        }
                        if (!nextTarget) {
                            nextTarget = true;
                        }
                        return nextTarget;

                    }
                });
                return combatDelay;
            }
            MeleeHitDelay(player);
            player.setNextAnimationNoPriority(new Animation(getWeaponAttackEmote(weaponId, attackStyle)), player);
            CoresManager.slowExecutor.schedule(new Runnable() {

                @Override
                public void run() {
                    if (target.getNextAnimation() != null)
                        return;
                    doDefenceEmote(target);
                }

            }, 0, TimeUnit.MILLISECONDS);
        }
        playSound(soundId, player, target);
        return combatDelay;
    }

    public void MeleeHitDelay(final Player player) {
        int weaponId = player.getEquipment().getWeaponId();
        int attackStyle = player.getCombatDefinitions().getAttackStyle();
        delayNormalHit(weaponId, attackStyle,
                getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false)));
    }

    public void playSound(int soundId, Player player, Entity target) {
        if (soundId == -1)
            return;
        player.getPackets().sendSound(soundId, 0, 1);
        if (target instanceof Player) {
            Player p2 = (Player) target;
            p2.getPackets().sendSound(soundId, 0, 1);
        }
    }

    public static int getSpecialAmmount(int weaponId) {
        switch (weaponId) {
            case 4587: // dragon sci
            case 859: // magic longbow
            case 861: // magic shortbow
            case 10284: // Magic composite bow
            case 18332: // Magic longbow (sighted)
            case 19149:// zamorak bow
            case 19151:
            case 19143:// saradomin bow
            case 19145:
            case 19146:
            case 19148:// guthix bow
                return 55;
            case 11235: // dark bows
            case 15701:
            case 15702:
            case 15703:
            case 15704:
                return 65;
            case 13899: // vls
            case 13901:
            case 1305: // dragon long
            case 1215: // dragon dagger
            case 5698: // dds
            case 1434: // dragon mace
            case 1249:// d spear
            case 1263:
            case 11716:
            case 3176:
            case 5716:
            case 5730:
            case 13770:
            case 13772:
            case 13774:
            case 13776:
                return 25;
            case 3204: // d hally
                return 30;
            case 13902:
            case 13904:
                return 35;
            case 15442:// whip start
            case 15443:
            case 15444:
            case 15441:
            case 21371:
            case 4151:
            case 23691:
            case 11698: // sgs
            case 24514:
            case 23681:
            case 11694: // ags
            case 24510:
            case 23679:
            case 13905: // vesta spear
            case 13907:
            case 14484: // d claws
            case 23695:
            case 10887: // anchor
            case 4153: // granite maul
            case 14679:
            case 14684: // zanik cbow
            case 15241: // hand cannon
            case 13908:
            case 13954:// morrigan javelin
            case 13955:
            case 13956:
            case 13879:
            case 13880:
            case 13881:
            case 13882:
            case 13883:// morigan thrown axe
            case 13957:
            case 11696:
                return 50;
            case 11061:
                return 100;
            case 11730: // ss
            case 23690:
            case 24512:
            case 23680:
            case 23682:
            case 35:// Excalibur
            case 8280:
            case 14632:
            case 1377:// dragon battle axe
            case 13472:
            case 22207:
            case 22209:
            case 22211:
            case 22213:
            case 15486:// staff of lights
            case 11736:
                return 100;
            case 19784: // korasi sword
            case 11700:
            case 24516:
                return 60;
            default:
                return 0;
        }
    }

    public int getRandomMaxHit(Player player, int weaponId, int attackStyle, boolean ranging) {
        return getRandomMaxHit(player, weaponId, attackStyle, ranging, true, 1.0D, false);
    }

    public int getRandomMaxHit(Player player, int weaponId, int attackStyle, boolean ranging, boolean defenceAffects,
                               double specMultiplier, boolean usingSpec) {
        max_hit = getMaxHit(player, weaponId, attackStyle, ranging, usingSpec, specMultiplier);
        double A = 0, D = 0, R = 0, RD = 0;
        if (defenceAffects) {
            double attackBonus = player.getCombatDefinitions().getBonuses()[CombatDefinitions
                    .getMeleeBonusStyle(weaponId, attackStyle)];
            double rangeBonus = player.getCombatDefinitions().getBonuses()[4];
            if (!ranging) {
                if (fullVeracsEquipped(player) && Utils.getRandom(3) == 0)
                    player.getTemporaryAttributtes().put("VERAC_EFFECT", Boolean.TRUE);
                double attack = Math.round(player.getSkills().getLevel(Skills.ATTACK) * player.getPrayer().getAttackMultiplier()) + 8;
                if (fullVoidEquipped(player, (new int[]{11665, 11676})))
                    attack *= (hasEliteVoid(player) ? 1.135 : 1.1);
                if (fullDharokEquipped(player)) {
                    double hp = player.getHitpoints();
                    double maxhp = player.getMaxHitpoints();
                    double d = (maxhp - hp) / 10;
                    attack += d;
                }
                if (target instanceof NPC) {
                    if (player.getAuraManager().isActivated())
                        attack *= player.getAuraManager().getAttackAccurayMultiplier();
                }
                if (usingSpec) {
                    double multiplier = getSpecialAccuracyModifier(player);
                    attack *= multiplier;
                }
                if (target instanceof NPC) {
                    NPC n = (NPC) target;
                   // attack *= NpcDamageBoost.getMultiplier(player, n, NpcDamageBoost.Style.MELEE);
                }
                attack *= PlayerDamageBoost.getMultiplier(player);
                if (player.getCombatDefinitions().getStyle(weaponId, attackStyle) == CombatDefinitions.ACCURATE)
                    attack += 3;
                else if (player.getCombatDefinitions().getStyle(weaponId, attackStyle) == CombatDefinitions.CONTROLLED)
                    attack += 1;
                attack = attack * (1 + attackBonus / 64);
                A = Math.round(attack);
            } else {
                double range = Math.round(player.getSkills().getLevel(Skills.RANGE) * player.getPrayer().getRangeMultiplier()) + 8;
                if (fullVoidEquipped(player, (new int[]{11664, 11675})))
                    range *= (hasEliteVoid(player) ? 1.135 : 1.1);
                if (usingSpec) {
                    double multiplier = getSpecialAccuracyModifier(player);
                    range *= multiplier;
                }
                if (target instanceof NPC) {
                    NPC n = (NPC) target;
                    range *= NpcDamageBoost.getMultiplier(player, n, NpcDamageBoost.Style.RANGE);
                }
                if ((player.getEquipment().getWeaponId() == 20173 || player.getEquipment().getWeaponId() == 20171)
                        && !(player.getControlerManager().getControler() instanceof WildernessControler)) {
                    range += getZaryteBowModifier(1, target);
                }
                if (player.getCombatDefinitions().getStyle(weaponId, attackStyle) == CombatDefinitions.ACCURATE)
                    range += 3;
                else if (player.getCombatDefinitions().getStyle(weaponId, attackStyle) == CombatDefinitions.RAPID)
                    range += 1;
                range = range * (1 + rangeBonus / 64);
                R = Math.round(range);
            }
            if (target instanceof Player) {
                Player p2 = (Player) target;
                if (!ranging) {
                    double defenceBonus = p2.getCombatDefinitions().getBonuses()[CombatDefinitions
                            .getMeleeDefenceBonus(CombatDefinitions.getMeleeBonusStyle(weaponId, attackStyle))];
                    double defence = Math.round(p2.getSkills().getLevel(Skills.DEFENCE) * p2.getPrayer().getDefenceMultiplier()) + 8;
                    int style = p2.getCombatDefinitions().getStyle(weaponId, attackStyle);
                    if (style == CombatDefinitions.DEFENSIVE)
                        defence += 3;
                    else if (style == CombatDefinitions.CONTROLLED)
                        defence += 1;
                    if (fullToragsEquipped(p2) && p2.getHitpoints() < p2.getMaxHitpoints()) {
                        double fullHealth = p2.getMaxHitpoints() / 10;
                        double currentHealth = p2.getHitpoints() / 10;
                        double boost = (fullHealth - currentHealth) / 100;
                        defence *= 1 + boost;
                    }
                    defence = defence * (1 + defenceBonus / 64);
                    D = Math.round(defence);
                } else {
                    double rangeDefenceBonus = p2.getCombatDefinitions().getBonuses()[9];
                    double rangedefence = Math.round(p2.getSkills().getLevel(Skills.DEFENCE) * p2.getPrayer().getDefenceMultiplier()) + 8;
                    int style = p2.getCombatDefinitions().getStyle(weaponId, attackStyle);
                    if (style == CombatDefinitions.LONGRANGE || style == CombatDefinitions.DEFENSIVE)
                        rangedefence += 3;
                    else if (style == CombatDefinitions.CONTROLLED)
                        rangedefence += 1;
                    rangedefence = rangedefence * (1 + rangeDefenceBonus / 64);
                    RD = Math.round(rangedefence);
                }
            } else {
                if (ranging) {
                    NPC n = (NPC) target;
                    if (n.getBonuses() == null)
                        n.setBonuses();
                    int temporaryBonus = 0;
                    if (n.getTemporaryAttributtes().get("NPC_RANGE_DEFENCE") != null)
                        temporaryBonus = (int) n.getTemporaryAttributtes().get("NPC_RANGE_DEFENCE");
                    double rangeDefenceBonus = temporaryBonus > 0 ? temporaryBonus
                            : n.getBonuses() != null ? n.getBonuses()[CombatDefinitions.NPC_RANGE_DEFENCE] : 0;
                    int temporaryDef = 0;
                    if (n.getTemporaryAttributtes().get("NPC_DEFENCE_LEVEL") != null)
                        temporaryDef = (int) n.getTemporaryAttributtes().get("NPC_DEFENCE_LEVEL");
                    double rangedefence = temporaryDef > 0 ? temporaryDef
                            : n.getBonuses() != null ? n.getBonuses()[CombatDefinitions.NPC_DEFENCE_LEVEL]
                            : n.getCombatLevel();
                    rangedefence = Math.round(rangedefence);
                    rangedefence += 8;
                    rangedefence = rangedefence + (1 + rangeDefenceBonus);
                    RD = Math.round(rangedefence);
                } else {
                    NPC n = (NPC) target;
                    if (n.getBonuses() == null)
                        n.setBonuses();
                    int attackMethod = CombatDefinitions.getMeleeBonusStyle(weaponId, attackStyle);
                    double defenceBonus = attackMethod == CombatDefinitions.STAB_ATTACK
                            ? n.getBonuses() == null ? 0 : n.getBonuses()[CombatDefinitions.NPC_STAB_DEFENCE]
                            : attackMethod == CombatDefinitions.SLASH_ATTACK
                            ? n.getBonuses() == null ? 0 : n.getBonuses()[CombatDefinitions.NPC_SLASH_DEFENCE]
                            : n.getBonuses() == null ? n.getCombatLevel()
                            : n.getBonuses()[CombatDefinitions.NPC_CRUSH_DEFENCE];
                    int temporaryDef = 0;
                    if (n.getTemporaryAttributtes().get("NPC_DEFENCE_LEVEL") != null)
                        temporaryDef = (int) n.getTemporaryAttributtes().get("NPC_DEFENCE_LEVEL");
                   double defence = temporaryDef > 0 ? temporaryDef
                            : n.getBonuses() != null ? n.getBonuses()[CombatDefinitions.NPC_DEFENCE_LEVEL]
                            : n.getCombatLevel() / 3;
                    defence = Math.round(defence);
                    defence += 8;
                    defence = defence + (1 + defenceBonus);
                    D = Math.round(defence);
                }
            }
            if (ranging) {
                double prob = R / RD;
                double random = Utils.getRandomDouble(100);
                if (R < RD) {
                    prob = (R - 1) / (RD * 2);
                } else if (R >= RD) {
                    prob = 1 - (RD + 1) / (R * 2);
                }
                if (prob < random / 100) {
                    return 0;
                }
            } else {
                double prob = A / D;
                double random = Utils.getRandomDouble(100);
                if (A < D) {
                    prob = (A - 1) / (D * 2);
                } else if (A >= D) {
                    prob = 1 - (D + 1) / (A * 2);
                }
                if (player.getTemporaryAttributtes().remove("VERAC_EFFECT") == Boolean.TRUE)
                    prob = 100;
                if (player.getEquipment().getWeaponId() == 4566)
                    prob = 100;
                if (prob < random / 100) {
                    return 0;
                }
            }

        }
        int newRandom = Utils.random(100);
        int hit = Utils.random(max_hit + 1);
        if (newRandom >= (usingSpec ? 70 : 90))
            hit = max_hit / 2 + Utils.random(max_hit / 2);
        if (target instanceof NPC) {
            NPC n = (NPC) target;
            if (n.getId() == 4474) {
                int MaxHit = (int) (max_hit);
                hit -= MaxHit;
                max_hit -= MaxHit;
                if (hit < 0)
                    hit = MaxHit;
                if (hit < MaxHit)
                    hit += MaxHit;
            }
        }
        if (player.getInventory().containsItem(773, 1) && player.isDeveloper()) {
            int MaxHit = (int) (max_hit);
            hit -= MaxHit;
            max_hit -= MaxHit;
            if (hit < 0)
                hit = MaxHit;
            if (hit < MaxHit)
                hit += MaxHit;
        }

        if (player.getAuraManager().usingEquilibrium()) {
            int perc25MaxHit = (int) (max_hit * 0.25);
            hit -= perc25MaxHit;
            max_hit -= perc25MaxHit;
            if (hit < 0)
                hit = perc25MaxHit;
            if (hit < perc25MaxHit)
                hit += perc25MaxHit;

        }
        return hit;
    }

    public static boolean hasEliteVoid(Player player) {
        int legsId = player.getEquipment().getLegsId();
        int torsoId = player.getEquipment().getChestId();
        return (legsId == 19786 || legsId == 19788 || legsId == 19790)
                || (torsoId == 19785 || torsoId == 19787 || torsoId == 19789);

    }

    private final int getMaxHit(Player player, int weaponId, int attackStyle, boolean ranging, boolean usingSpec,
                                double specMultiplier) {
        if (!ranging) {
            double strengthLvl = player.getSkills().getLevel(Skills.STRENGTH);
            double otherBonus = 1;
            if (fullDharokEquipped(player)) {
                double hp = player.getHitpoints();
                double maxhp = player.getMaxHitpoints();
                double d = (hp / maxhp);
                otherBonus = 2 - (d);
            }
            double effectiveStrength = 8 + Math.floor((strengthLvl * player.getPrayer().getStrengthMultiplier()));
            double strengthBonus = player.getCombatDefinitions().getBonuses()[CombatDefinitions.STRENGTH_BONUS];
            if (usingGoliathGloves(player))
                strengthBonus += 82;
            if (fullVoidEquipped(player, 11665, 11676))
                effectiveStrength = Math.floor(effectiveStrength * (hasEliteVoid(player) ? 1.135 : 1.1));
            double baseDamage = 5 + effectiveStrength * (1 + (strengthBonus / 64));
            int maxHit = (int) Math.floor(baseDamage * specMultiplier * otherBonus);
            if (target instanceof NPC) {
                NPC n = (NPC) target;
                int oldMaxHit = maxHit;
                maxHit *= NpcDamageBoost.getMultiplier(player, n, NpcDamageBoost.Style.MELEE);
                int newMaxHit = maxHit;
                System.out.println("Old MaxHit: " + oldMaxHit + " - new MaxHit: " + newMaxHit);
            }
            maxHit *= PlayerDamageBoost.getMultiplier(player);
            int style = player.getCombatDefinitions().getStyle(weaponId, attackStyle);
            if (style == CombatDefinitions.AGGRESSIVE)
                maxHit += 3;
            else if (style == CombatDefinitions.CONTROLLED)
                maxHit += 1;
            return maxHit;
        } else {
            if (weaponId == 24338 && target instanceof Player) {
                player.getPackets()
                        .sendGameMessage("The royal crossbow feels weak and unresponsive against other players.");
                return 60;
            }
            double rangedLvl = player.getSkills().getLevel(Skills.RANGE);
            double styleBonus = attackStyle == 0 ? 3 : attackStyle == 1 ? 0 : 1;
            double effectiveStrength = Math.round(rangedLvl * player.getPrayer().getRangeMultiplier()) + styleBonus;
            double otherBonus = 1;
            if (fullVoidEquipped(player, 11664, 11675))
                effectiveStrength = Math.floor(effectiveStrength * (hasEliteVoid(player) ? 1.135 : 1.1));
            if ((player.getEquipment().getWeaponId() == 20173 || player.getEquipment().getWeaponId() == 20171)
                    && target instanceof NPC) {
                if (getZaryteBowModifier(0, target) > 150)
                    effectiveStrength += 150;
                else
                    effectiveStrength += Math.floor(getZaryteBowModifier(0, target));
            }
            double strengthBonus = player.getCombatDefinitions().getBonuses()[CombatDefinitions.RANGED_STR_BONUS];
            double baseDamage = 5 + (((effectiveStrength + 8) * (strengthBonus + 64)) / 64);
            int maxHit = (int) Math.floor(baseDamage * specMultiplier * otherBonus);
            if (target instanceof NPC) {
                NPC n = (NPC) target;
                int oldMaxHit = maxHit;
                maxHit *= NpcDamageBoost.getMultiplier(player, n, NpcDamageBoost.Style.RANGE);
                int newMaxHit = maxHit;
                System.out.println("Old MaxHit: " + oldMaxHit + " - new MaxHit: " + newMaxHit);
            }
            if (player.getCombatDefinitions().getStyle(weaponId, attackStyle) == CombatDefinitions.ACCURATE)
                maxHit += 3;
            if (performHexbow(weaponId, target)) {
                int combatModifier = ((NPC) target).getCombatLevel();
                if (combatModifier > 199)
                    combatModifier = 199;
                if (target instanceof DungeonBoss)
                    maxHit *= 2.5 + (combatModifier / 100);// 150% increase
                else
                    maxHit *= 2 + (combatModifier / 100);
                if (maxHit > 750)
                    maxHit = 750;
            } else if ((weaponId == 15913 || weaponId == 16337) && target instanceof NPC)
                maxHit *= 2;
            return maxHit;
        }
    }

    public static boolean performHexbow(int weaponId, Entity target) {
        if ((weaponId == 17295 || weaponId == 15836) && target instanceof NPC) {
            int type = ((NPC) target).getCombatDefinitions().getAttackStyle();
            if (type == NPCCombatDefinitions.MAGE || type == NPCCombatDefinitions.SPECIAL2
                    || ((NPC) target).getId() == 10024 || ((NPC) target).getId() == 10128
                    || ((NPC) target).getId() == 11925 || ((NPC) target).getId() == 10744
                    || ((NPC) target) instanceof DungeonBoss) {
                return true;
            }
        }
        return false;
    }

    private int getZaryteBowModifier(int type, Entity target) {
        if (target instanceof NPC) {
            NPC n = (NPC) target;
            int magic = n.getBonuses()[CombatDefinitions.NPC_MAGIC_LEVEL];
            /*
             * if (type == 0) { if (magic > 250) magic = 250; } else { if (magic > 140)
             * magic = 140; }
             */
            int accuracy = 140 + (10 * 3 * magic / 10 - 10) / 100 - ((3 * magic / 10 - 100) ^ 2) / 100;
            int damage = 250 + (10 * 3 * magic / 10 - 14) / 100 - ((3 * magic / 10 - 140) ^ 2) / 100;
            // int accuracy = (magic / 2);
            // int damage = (int) ((magic / 2) * 2.5);
            return type == 0 ? damage : accuracy;
        }
        return 0;
    }

    private double getSpecialAccuracyModifier(Player player) {
        Item weapon = player.getEquipment().getItem(Equipment.SLOT_WEAPON);
        if (weapon == null)
            return 1;
        String name = weapon.getDefinitions().getName().toLowerCase();
        if (name.contains("magic shortbow") || name.contains("dragon dagger") || name.contains("dragon scimitar")
                || name.contains("dragon longsword") || name.contains("dragon halberd") || name.contains("dragon 2h"))
            return 1.15;
        if (name.contains("dragon mace") || name.contains("whip"))
            return 1.10;
        if (name.contains("whip"))
            return 1.25;
        if (name.contains("saradomin sword"))
            return 1.5;
        if (name.contains("hand cannon") || name.contains("morrigan") || name.contains("vesta's spear")
                || name.contains("statius's warhammer") || name.contains("dark bow") || name.contains("godsword")
                || name.contains("dragon claws") || name.contains("anchor"))
            return 2;
        if (name.contains("vesta's longsword"))
            return 2.5;
        if (name.contains("korasi"))
            return 5;
        if (name.contains("magic longbow") || name.contains("magic comp"))
            return 100;
        return 1;
    }

    public boolean hasFireCape(Player player) {
        int capeId = player.getEquipment().getCapeId();
        return capeId == 6570 || capeId == 23659 || capeId == 23660 || capeId == 20769 || capeId == 20771;
    }

    public static final boolean fullVanguardEquipped(Player player) {
        int helmId = player.getEquipment().getHatId();
        int chestId = player.getEquipment().getChestId();
        int legsId = player.getEquipment().getLegsId();
        int weaponId = player.getEquipment().getWeaponId();
        int bootsId = player.getEquipment().getBootsId();
        int glovesId = player.getEquipment().getGlovesId();
        if (helmId == -1 || chestId == -1 || legsId == -1 || weaponId == -1 || bootsId == -1 || glovesId == -1)
            return false;
        return ItemDefinitions.getItemDefinitions(helmId).getName().contains("Vanguard")
                && ItemDefinitions.getItemDefinitions(chestId).getName().contains("Vanguard")
                && ItemDefinitions.getItemDefinitions(legsId).getName().contains("Vanguard")
                && ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Vanguard")
                && ItemDefinitions.getItemDefinitions(bootsId).getName().contains("Vanguard")
                && ItemDefinitions.getItemDefinitions(glovesId).getName().contains("Vanguard");
    }

    public static final boolean usingGoliathGloves(Player player) {
        String name = player.getEquipment().getItem(Equipment.SLOT_SHIELD) != null
                ? player.getEquipment().getItem(Equipment.SLOT_SHIELD).getDefinitions().getName().toLowerCase()
                : "";
        if (player.getEquipment().getItem((Equipment.SLOT_HANDS)) != null) {
            if (player.getEquipment().getItem(Equipment.SLOT_HANDS).getDefinitions().getName().toLowerCase()
                    .contains("goliath") && player.getEquipment().getWeaponId() == -1) {
                if (name.contains("defender") && name.contains("dragonfire shield"))
                    return true;
                return true;
            }
        }
        return false;
    }

    public static final boolean fullVeracsEquipped(Player player) {
        int helmId = player.getEquipment().getHatId();
        int chestId = player.getEquipment().getChestId();
        int legsId = player.getEquipment().getLegsId();
        int weaponId = player.getEquipment().getWeaponId();
        if (helmId == -1 || chestId == -1 || legsId == -1 || weaponId == -1)
            return false;
        return ItemDefinitions.getItemDefinitions(helmId).getName().contains("Verac's")
                && ItemDefinitions.getItemDefinitions(chestId).getName().contains("Verac's")
                && ItemDefinitions.getItemDefinitions(legsId).getName().contains("Verac's")
                && ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Verac's");
    }

    public static final boolean fullGuthansEquipped(Player player) {
        int helmId = player.getEquipment().getHatId();
        int chestId = player.getEquipment().getChestId();
        int legsId = player.getEquipment().getLegsId();
        int weaponId = player.getEquipment().getWeaponId();
        if (helmId == -1 || chestId == -1 || legsId == -1 || weaponId == -1)
            return false;
        return ItemDefinitions.getItemDefinitions(helmId).getName().contains("Guthan's")
                && ItemDefinitions.getItemDefinitions(chestId).getName().contains("Guthan's")
                && ItemDefinitions.getItemDefinitions(legsId).getName().contains("Guthan's")
                && ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Guthan's");
    }

    public static final boolean fullAkrisaeEquipped(Player player) {
        int helmId = player.getEquipment().getHatId();
        int chestId = player.getEquipment().getChestId();
        int legsId = player.getEquipment().getLegsId();
        int weaponId = player.getEquipment().getWeaponId();
        if (helmId == -1 || chestId == -1 || legsId == -1 || weaponId == -1)
            return false;
        return ItemDefinitions.getItemDefinitions(helmId).getName().contains("Akrisae's")
                && ItemDefinitions.getItemDefinitions(chestId).getName().contains("Akrisae's")
                && ItemDefinitions.getItemDefinitions(legsId).getName().contains("Akrisae's")
                && ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Akrisae's");
    }

    public static final boolean fullDharokEquipped(Player player) {
        int helmId = player.getEquipment().getHatId();
        int chestId = player.getEquipment().getChestId();
        int legsId = player.getEquipment().getLegsId();
        int weaponId = player.getEquipment().getWeaponId();
        if (helmId == -1 || chestId == -1 || legsId == -1 || weaponId == -1)
            return false;
        return ItemDefinitions.getItemDefinitions(helmId).getName().contains("Dharok's")
                && ItemDefinitions.getItemDefinitions(chestId).getName().contains("Dharok's")
                && ItemDefinitions.getItemDefinitions(legsId).getName().contains("Dharok's")
                && ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Dharok's");
    }

    public static final boolean fullKarilsEquipped(Player player) {
        int helmId = player.getEquipment().getHatId();
        int chestId = player.getEquipment().getChestId();
        int legsId = player.getEquipment().getLegsId();
        int weaponId = player.getEquipment().getWeaponId();
        if (helmId == -1 || chestId == -1 || legsId == -1 || weaponId == -1)
            return false;
        return ItemDefinitions.getItemDefinitions(helmId).getName().contains("Karil's")
                && ItemDefinitions.getItemDefinitions(chestId).getName().contains("Karil's")
                && ItemDefinitions.getItemDefinitions(legsId).getName().contains("Karil's")
                && ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Karil's");
    }

    public static final boolean fullAhrimsEquipped(Player player) {
        int helmId = player.getEquipment().getHatId();
        int chestId = player.getEquipment().getChestId();
        int legsId = player.getEquipment().getLegsId();
        int weaponId = player.getEquipment().getWeaponId();
        if (helmId == -1 || chestId == -1 || legsId == -1 || weaponId == -1)
            return false;
        return ItemDefinitions.getItemDefinitions(helmId).getName().contains("Ahrim's")
                && ItemDefinitions.getItemDefinitions(chestId).getName().contains("Ahrim's")
                && ItemDefinitions.getItemDefinitions(legsId).getName().contains("Ahrim's")
                && ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Ahrim's");
    }

    public static final boolean fullToragsEquipped(Player player) {
        int helmId = player.getEquipment().getHatId();
        int chestId = player.getEquipment().getChestId();
        int legsId = player.getEquipment().getLegsId();
        int weaponId = player.getEquipment().getWeaponId();
        if (helmId == -1 || chestId == -1 || legsId == -1 || weaponId == -1)
            return false;
        return ItemDefinitions.getItemDefinitions(helmId).getName().contains("Torag's")
                && ItemDefinitions.getItemDefinitions(chestId).getName().contains("Torag's")
                && ItemDefinitions.getItemDefinitions(legsId).getName().contains("Torag's")
                && ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Torag's");
    }

    public static final boolean hasBerskerNecklace(Player player) {
        int amuletId = player.getEquipment().getAmuletId();
        int weaponId = player.getEquipment().getWeaponId();
        if (amuletId == -1 || weaponId == -1)
            return false;
        return ItemDefinitions.getItemDefinitions(amuletId).getName().contains("Berserker neck")
                && (ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Tzhaar-ket-om")
                || ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Tzhaar-ket-em")
                || ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Toktz-xil-ak")
                || ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Toktz-xil-ek"));
    }

    public static final boolean fullVoidEquipped(Player player, int... helmid) {
        boolean hasDeflector = player.getEquipment().getShieldId() == 19712;
        if (player.getEquipment().getGlovesId() != 8842) {
            if (hasDeflector)
                hasDeflector = false;
            else
                return false;
        }
        int legsId = player.getEquipment().getLegsId();
        boolean hasLegs = legsId != -1 && (legsId == 8840 || legsId == 19786 || legsId == 19788 || legsId == 19790);
        if (!hasLegs) {
            if (hasDeflector)
                hasDeflector = false;
            else
                return false;
        }
        int torsoId = player.getEquipment().getChestId();
        boolean hasTorso = torsoId != -1
                && (torsoId == 8839 || torsoId == 10611 || torsoId == 19785 || torsoId == 19787 || torsoId == 19789);
        if (!hasTorso) {
            if (hasDeflector)
                hasDeflector = false;
            else
                return false;
        }
        if (hasDeflector)
            return true;
        int helmId = player.getEquipment().getHatId();
        if (helmId == -1)
            return false;
        boolean hasHelm = false;
        for (int id : helmid) {
            if (helmId == id) {
                hasHelm = true;
                break;
            }
        }
        if (!hasHelm)
            return false;
        return true;
    }

    public void delayNormalHit(final int weaponId, final int attackStyle, final Hit... hits) {
        if (weaponId != 4153 && (ItemDefinitions.getItemDefinitions(weaponId).getName().contains("maul")
                || ItemDefinitions.getItemDefinitions(weaponId).getName().contains("ket-om") || weaponId == 10887/**barrelchest anchor*/))
            delayHit(1, weaponId, attackStyle, hits);
        else
            delayHit(0, weaponId, attackStyle, hits);
    }

    public static Hit getMeleeHit(Player player, int damage) {
        return new Hit(player, damage, HitLook.MELEE_DAMAGE);
    }

    public static Hit getRangeHit(Player player, int damage) {
        return new Hit(player, damage, HitLook.RANGE_DAMAGE);
    }

    public static Hit getMagicHit(Player player, int damage) {
        return new Hit(player, damage, HitLook.MAGIC_DAMAGE);
    }

    public static Hit getRegularHit(Player player, int damage) {
        return new Hit(player, damage, HitLook.REGULAR_DAMAGE);
    }

    public static Hit getReflectedHit(Player player, int damage) {
        return new Hit(player, damage, HitLook.REFLECTED_DAMAGE);
    }

    private void delayMagicHit(int delay, final Hit... hits) {
        delayHit(delay, -1, -1, hits);
    }

    public void resetVariables() {
        base_mage_xp = 0;
        mage_hit_gfx = 0;
        magic_sound = 0;
        max_poison_hit = 0;
        reduceAttack = false;
        blood_spell = false;
        block_tele = false;
    }

    public void sendSoulSplit(final Hit hit, final Player player, final Entity target) {
        int damage = hit.getDamage() > target.getHitpoints() ? target.getHitpoints() : hit.getDamage();
        if (damage == 0)
            return;
        World.sendSoulsplitProjectile(player, target, 2263);
        if (player.getHitpoints() > 0 && player.getHitpoints() <= player.getMaxHitpoints()) {
            player.heal(damage / 5);
            if (target instanceof Player) {
                Player p2 = (Player) target;
                p2.getPrayer().drainPrayer(damage / 5);
            }
        }
        WorldTasksManager.schedule(new WorldTask() {
            @Override
            public void run() {
                if (player == null || target == null || player.isDead() || player.hasFinished() || target.isDead()
                        || target.hasFinished())
                    return;
                target.gfx(new Graphics(2264));
                World.sendSoulsplitProjectile(target, player, 2263);
            }
        }, 1);
    }

    public void sendSap(final Player player, final Entity target) {
        if (target instanceof Player) {
            if (player == null || target == null)
                return;
            final Player p2 = (Player) target;
            boolean usingMelee = player.getPrayer().usingPrayer(1, 1);
            boolean usingRange = player.getPrayer().usingPrayer(1, 2);
            boolean usingMagic = player.getPrayer().usingPrayer(1, 3);
            boolean usingSpecial = player.getPrayer().usingPrayer(1, 4);
            boolean reachedMax;
            int projectileId = usingMelee ? 2215 : usingRange ? 2218 : usingMagic ? 2221 : usingSpecial ? 2224 : -1;
            int gfx1 = usingMelee ? 2214 : usingRange ? 2217 : usingMagic ? 2220 : 2223;
            int gfx2 = usingMelee ? 2216 : usingRange ? 2219 : usingMagic ? 2222 : 2225;
            reachedMax = false;
            if (Utils.getRandom(4) == 0 && projectileId != -1) {
                player.setNextAnimationNoPriority(new Animation(12569), player);
                player.getPrayer().setBoostedLeech(true);
                p2.getPrayer().setBoostedLeech(true);
                player.gfx(new Graphics(gfx1));
                World.sendLeechProjectile(player, target, projectileId);
                target.gfx(new Graphics(gfx2));
                if (usingMelee) {
                    for (int i = 0; i < 3; i++) {
                        if (player.getPrayer().reachedMax(i) || player.getPrayer().reachedMin(i))
                            reachedMax = true;
                    }
                    if (reachedMax)
                        player.getPackets()
                                .sendGameMessage("You are boosted so much that your sap curse has no effect.", true);
                    else {
                        for (int i = 0; i < 3; i++) {
                            player.getPrayer().increase(i);
                            p2.getPrayer().decrease(i);
                        }
                        player.getPackets()
                                .sendGameMessage("Your curse drains Melee from the enemy, boosting your Melee.", true);
                        p2.getPackets().sendGameMessage("Your Melee has been drained by an enemy curse.", true);
                    }
                }
                if (usingRange) {
                    if (player.getPrayer().reachedMax(3) || player.getPrayer().reachedMin(3))
                        player.getPackets()
                                .sendGameMessage("You are boosted so much that your sap curse has no effect.", true);
                    else {
                        player.getPrayer().increase(3);
                        p2.getPrayer().decrease(3);
                        player.getPackets().sendGameMessage(
                                "Your curse drains Ranging from the enemy, boosting your Ranging.", true);
                        p2.getPackets().sendGameMessage("Your Ranging has been drained by an enemy curse.", true);
                    }
                }
                if (usingMagic) {
                    if (player.getPrayer().reachedMax(4) || player.getPrayer().reachedMin(4))
                        player.getPackets()
                                .sendGameMessage("You are boosted so much that your sap curse has no effect.", true);
                    else {
                        player.getPrayer().increase(4);
                        p2.getPrayer().decrease(4);
                        player.getPackets()
                                .sendGameMessage("Your curse drains Magic from the enemy, boosting your Magic.", true);
                        p2.getPackets().sendGameMessage("Your Magic has been drained by an enemy curse.", true);
                    }
                }
                if (usingSpecial) {
                    if (p2.getCombatDefinitions().getSpecialAttackPercentage() <= 0)
                        player.getPackets()
                                .sendGameMessage("You are boosted so much that your sap curse has no effect.", true);
                    else {
                        p2.getCombatDefinitions().desecreaseSpecialAttack(10);
                        player.getPackets().sendGameMessage("Your curse drains Special Attack from the enemy.", true);
                        p2.getPackets().sendGameMessage("Your Special Attack has been drained by an enemy curse.",
                                true);
                    }
                }
            }
        }

    }

    public void sendLeech(final Player player, final Entity target) {
        if (target instanceof Player) {
            if (player == null || target == null)
                return;
            final Player p2 = (Player) target;
            boolean usingAttack = player.getPrayer().usingPrayer(1, 10);
            boolean usingStrength = player.getPrayer().usingPrayer(1, 14);
            boolean usingDefence = player.getPrayer().usingPrayer(1, 13);
            boolean usingRange = player.getPrayer().usingPrayer(1, 11);
            boolean usingMagic = player.getPrayer().usingPrayer(1, 12);
            boolean usingRun = player.getPrayer().usingPrayer(1, 15);
            boolean usingSpecial = player.getPrayer().usingPrayer(1, 16);
            int getCurseId = usingAttack ? 0
                    : usingStrength ? 1 : usingDefence ? 2 : usingRange ? 3 : usingMagic ? 4 : -1;
            int projectileId = usingAttack ? 2231
                    : usingRange ? 2236
                    : usingMagic ? 2240 : usingDefence ? 2244 : usingStrength ? 2248 : usingRun ? 2252 : 2256;

            int gfx1 = usingAttack ? 2232
                    : usingRange ? 2238
                    : usingMagic ? 2242 : usingDefence ? 2246 : usingStrength ? 2250 : usingRun ? 2254 : 2258;
            String message = usingSpecial ? "Special attack"
                    : usingRun ? "Run energy"
                    : usingAttack ? "Attack"
                    : usingStrength ? "Strength"
                    : usingDefence ? "Defence" : usingRange ? "Ranging" : "Magic1";
            if (Utils.getRandom(4) == 0 && getCurseId != -1) {
                player.setNextAnimationNoPriority(new Animation(12575), target);
                p2.gfx(new Graphics(gfx1));
                World.sendLeechProjectile(player, target, projectileId);
                player.getPrayer().setBoostedLeech(true);
                p2.getPrayer().setBoostedLeech(true);
                player.getPackets().sendGameMessage(
                        "Your curse drains " + message + " from the enemy, boosting your " + message + ".", true);
                p2.getPackets().sendGameMessage("Your " + message + " has been drained by an enemy curse.", true);
                if (usingAttack) {
                    if (player.getPrayer().reachedMax(0) || player.getPrayer().reachedMin(0)) {
                        player.getPackets()
                                .sendGameMessage("You are boosted so much that your leech curse has no effect.", true);
                    } else {
                        player.getPrayer().increase(0);
                        p2.getPrayer().decrease(0);
                    }
                }
                if (usingStrength) {
                    if (player.getPrayer().reachedMax(1) || player.getPrayer().reachedMin(1)) {
                        player.getPackets()
                                .sendGameMessage("You are boosted so much that your leech curse has no effect.", true);
                    } else {
                        player.getPrayer().increase(1);
                        p2.getPrayer().decrease(1);
                    }
                }
                if (usingDefence) {
                    if (player.getPrayer().reachedMax(2) || player.getPrayer().reachedMin(2)) {
                        player.getPackets()
                                .sendGameMessage("You are boosted so much that your leech curse has no effect.", true);
                    } else {
                        player.getPrayer().increase(2);
                        p2.getPrayer().decrease(2);
                    }
                }
                if (usingRange) {
                    if (player.getPrayer().reachedMax(3) || player.getPrayer().reachedMin(3)) {
                        player.getPackets()
                                .sendGameMessage("You are boosted so much that your leech curse has no effect.", true);
                    } else {
                        player.getPrayer().increase(3);
                        p2.getPrayer().decrease(3);
                    }
                }
                if (usingMagic) {
                    if (player.getPrayer().reachedMax(4) || player.getPrayer().reachedMin(4)) {
                        player.getPackets()
                                .sendGameMessage("You are boosted so much that your leech curse has no effect.", true);
                    } else {
                        player.getPrayer().increase(4);
                        p2.getPrayer().decrease(4);
                    }
                }
                if (usingSpecial) {
                    if (p2.getCombatDefinitions().getSpecialAttackPercentage() <= 0) {
                        player.getPackets()
                                .sendGameMessage("You are boosted so much that your leech curse has no effect.", true);
                    } else {
                        player.getCombatDefinitions().increaseSpecialAttack(10);
                        p2.getCombatDefinitions().desecreaseSpecialAttack(
                                p2.getCombatDefinitions().getSpecialAttackPercentage() < 10 ? 0 : 10);
                    }
                }
                if (usingRun) {
                    if (p2.getRunEnergy() <= 9) {
                        player.getPackets().sendGameMessage(
                                "Your opponent has been weakened so much that your leech curse has no effect.", true);
                    } else {
                        player.setNextAnimationNoPriority(new Animation(12575), player);
                        player.setRunEnergy(player.getRunEnergy() > 90 ? 100 : player.getRunEnergy() + 10);
                        p2.setRunEnergy(p2.getRunEnergy() < 10 ? 0 : p2.getRunEnergy() - 10);
                    }
                }

            }
        }
    }

    public void boostTurmoil(final Player player) {
        Player p2 = (Player) target;
        if (target instanceof Player) {
            if (!player.getPrayer().isBoostedLeech()) {
                if (player.getPrayer().usingPrayer(1, 19)) {
                    player.getPrayer().increaseTurmoilBonus(player, p2);
                    // player.getPrayer().setBoostedLeech(true);
                }
            }
        }
    }

    public void boostTurmoilNPC(final Player player) {
        if (target instanceof NPC) {
            if (!player.getPrayer().isBoostedLeech()) {
                if (player.getPrayer().usingPrayer(1, 19)) {
                    player.getPrayer().increaseTurmoilBonusNPC(player);

                }
            }
        }
    }

    public void smitePrayer(final Player player, final Hit hit) {
        if (target instanceof Player) {
            Player p2 = (Player) target;
            int damage = hit.getDamage() > target.getHitpoints() ? target.getHitpoints() : hit.getDamage();
            int prayerDrain = damage / 4;
            if (player.getPrayer().usingPrayer(0, 24)) {
                if (prayerDrain > 0) {
                    p2.getPrayer().drainPrayer(prayerDrain);
                }
            }
        }
    }

    public void sendGuthanEffect(final Hit hit, final Player player, final Entity target) {
        int damage = hit.getDamage();
        if (target instanceof Player) {
            Player p2 = (Player) target;
            player.heal(damage);
            p2.gfx(new Graphics(398));
        } else {
            player.heal(damage);
            target.gfx(new Graphics(398));
        }
    }

    public void sendAkrisaeEffect(final Hit hit, final Player player, final Entity target) {
        int damage = hit.getDamage();
        if (target instanceof Player) {
            Player p2 = (Player) target;
            player.getPrayer().restorePrayer(damage / 3);
            p2.getPrayer().drainPrayer(damage / 5);
        } else {
            player.getPrayer().restorePrayer(damage / 3);
        }
    }

    public void handleDisruptionShield(Player player, Hit hit) {
        if (target instanceof Player) {
            Player p2 = (Player) target;
            if (p2.isDisruptionActivated() && hit.getDamage() >= 1 && (hit.getLook() == HitLook.MELEE_DAMAGE
                    || hit.getLook() == HitLook.RANGE_DAMAGE || hit.getLook() == HitLook.MAGIC_DAMAGE)) {
                hit.setMissedHit();
                p2.getPackets().sendGameMessage("Your Disruption Shield blocked your victim's damage.");
                player.getPackets().sendGameMessage("Your victim's Disruption Shield blocked your damage.");
                p2.getTemporaryAttributtes().put("isDisruptionActivated", Boolean.FALSE);
            }
        }
    }

    public void handleDivine(Player player, Hit hit) {
        int damage = hit.getDamage();
        if (target instanceof Player) {
            Player p2 = (Player) target;
            int shieldId = p2.getEquipment().getShieldId();
            if (damage > 0 && p2.getPrayer().getPrayerpoints() > 0) {
                if (shieldId == 13740 || shieldId == 23698) {
                    int drain = (int) (Math.ceil(damage * 0.3) / 2);
                    if (p2.getPrayer().getPrayerpoints() >= drain) {
                        hit.setDamage((int) (damage * 0.70));
                        p2.getPrayer().drainPrayer(drain);
                    }
                }
            }
        }
    }

    public void handleElysian(Player player, Hit hit) {
        int damage = hit.getDamage();
        if (target instanceof Player) {
            Player p2 = (Player) target;
            int shieldId = p2.getEquipment().getShieldId();
            if (shieldId == 13742) {
                if (Utils.getRandom(10) <= 7 && damage > 0)
                    hit.setDamage((int) (damage * 0.75));
            }
        }
    }

    public void handleSOL(Player player, Hit hit) {
        int damage = hit.getDamage();
        if (target instanceof Player) {
            Player p2 = (Player) target;
            int weaponId = p2.getEquipment().getWeaponId();
            if (hit.getLook() == HitLook.MELEE_DAMAGE) {
                if (p2.polDelay > Utils.currentTimeMillis()) {
                    if (weaponId != 15486 && weaponId != 11736) {
                        p2.setPolDelay(0);
                        p2.getPackets().sendGameMessage("The power of the " + (weaponId == 15486 ? "light" : "dead")
                                + "fades. Your resistance to melee attacks return to normal.");
                    } else {
                        if (damage > 0) {
                            if (weaponId == 15486)
                                p2.gfx(new Graphics(2320, 0, 0));
                            if (weaponId == 11736)
                                p2.gfx(new Graphics(3059, 0, 0));
                            hit.setDamage((int) (damage * 0.5));
                        }
                    }
                }
            }
        }
    }

    public void handlePenance(Player player, Hit hit) {
        int damage = hit.getDamage();
        if (target instanceof Player) {
            Player p2 = (Player) target;
            if (p2.getAuraManager().usingPenance()) {
                int amount = (int) (damage * 0.2);
                if (amount > 0)
                    p2.getPrayer().restorePrayer(amount);
            }
        }
    }

    public void handleDeathTouchBracelet(Player player, Hit hit) {
        int reflection = (int) (hit.getDamage() / 3);
        if (target instanceof Player) {
            Player p2 = (Player) target;
            if (player.getEquipment().getGlovesId() == 20694) {
                if (Utils.random(2) == 1 && hit.getDamage() > 100) {
                    p2.applyHit(new Hit(p2, reflection, HitLook.REFLECTED_DAMAGE));
                    p2.getPackets().sendFilteredGameMessage(true,
                            "Your opponents' deathtouch bracelet activiates and hits <col=ff0000>" + reflection
                                    + "</col> damage.");
                    p2.gfx(new Graphics(1664));
                }
            }
        }
    }

    public void handleRingOfRecoil(Player player, Hit hit) {
        if (hit.getLook() != HitLook.MELEE_DAMAGE && hit.getLook() != HitLook.RANGE_DAMAGE
                && hit.getLook() != HitLook.MAGIC_DAMAGE) {
            return;
        }
        int damage = (int) (hit.getDamage() * 0.1);
        if (target instanceof Player) {
            Player p2 = (Player) target;
            if (p2.getEquipment().getRingId() != 2550 || hit.getDamage() < 10)
                return;
            Hit finalHit = new Hit(p2, damage > 60 ? 60 : damage, HitLook.REGULAR_DAMAGE);
            player.applyHit(finalHit);
            player.getCharges().processHit(finalHit);
        }
    }

    private void handleVengHit(Player player, Hit hit) {
        int damage = hit.getDamage() > target.getHitpoints() ? target.getHitpoints() : hit.getDamage();
        if (target instanceof Player) {
            Player p2 = (Player) target;
            if (p2.isVengeanceActivated() && damage >= 4) {
                p2.setVengeance(false);
                p2.setNextForceTalk(new ForceTalk("Taste vengeance!"));
                player.applyHit(new Hit(p2, (int) (damage * 0.75), HitLook.REGULAR_DAMAGE));
            }
        }
    }

    public void handleAbsorb(Player player, Hit hit) {
        Player p2 = (Player) target;
        int damage = hit.getDamage() > p2.getHitpoints() ? p2.getHitpoints() : hit.getDamage();
        if (hit.getLook() == HitLook.MELEE_DAMAGE) {
            int reducedDamage = (damage - 200)
                    * p2.getCombatDefinitions().getBonuses()[CombatDefinitions.ABSORVE_MELEE_BONUS] / 100;
            if (damage - reducedDamage > 200 && p2.getHitpoints() > 200) {
                if (reducedDamage > 0) {
                    hit.setDamage(damage - reducedDamage);
                    hit.setSoaking(new Hit(player, reducedDamage, HitLook.ABSORB_DAMAGE));
                }
            }
        }
        if (hit.getLook() == HitLook.RANGE_DAMAGE) {
            int reducedDamage = (damage - 200)
                    * p2.getCombatDefinitions().getBonuses()[CombatDefinitions.ABSORVE_RANGE_BONUS] / 100;
            if (damage - reducedDamage > 200 && p2.getHitpoints() > 200) {
                if (reducedDamage > 0) {
                    hit.setDamage(damage - reducedDamage);
                    hit.setSoaking(new Hit(player, reducedDamage, HitLook.ABSORB_DAMAGE));
                }
            }
        }
        if (hit.getLook() == HitLook.MAGIC_DAMAGE) {
            int reducedDamage = (damage - 200)
                    * p2.getCombatDefinitions().getBonuses()[CombatDefinitions.ABSORVE_MAGE_BONUS] / 100;
            if (damage - reducedDamage > 200 && p2.getHitpoints() > 200) {
                if (reducedDamage > 0) {
                    hit.setDamage(damage - reducedDamage);
                    hit.setSoaking(new Hit(player, reducedDamage, HitLook.ABSORB_DAMAGE));
                }
            }
        }
    }

    public void handleAutoRetaliate(Player player, Entity target) {
        if (target instanceof Player) {
            Player p2 = (Player) target;
            if (p2.getCombatDefinitions().isAutoRelatie() && !p2.getActionManager().hasSkillWorking()
                    && !p2.hasWalkSteps()) {
                p2.closeInterfaces();
                p2.getActionManager().setAction(new PlayerCombat(player));
            }
        }
    }

    public void checkPID(Hit hit, Player player, Entity target) {
        if (target instanceof Player) {
            Player p2 = (Player) target;
            if (player.getControlerManager().getControler() instanceof DuelArena) {
                if (player.getDamage() >= p2.getHitpoints() && p2.getDamage() >= player.getHitpoints()) {
                    if (player.getIndex() > p2.getIndex()) {
                        hit.setDamage(-2);
                    }
                }
            }
        }
    }

    public void handleKerisEffect(Hit hit, Player player) {
        if (Utils.getRandom(9) == 0) {
            hit.setDamage(hit.getDamage() * 3);
            player.getPackets().sendGameMessage(
                    "You slip your dagger through a chink in the creature's chitin, landing a vicious blow.");
        } else {
            hit.setDamage(hit.getDamage() * 2);
        }
    }

    public void handleSagaie(Hit hit, Player player) {
        if (isRanging(player) > 0) {
            if (player.getEquipment().getWeaponId() == 21364) {
                int distance = Utils.getDistance(player, target);
                if (distance > 4)
                    distance = 4;
                if (hit.getDamage() > 20)
                    hit.setDamage(hit.getDamage() + (20 * distance));
            }
        }
    }

    public boolean isVeracEffect(Player player) {
        Boolean verac = (Boolean) player.getTemporaryAttributtes().get("VERAC_EFFECT");
        if (verac == null)
            return false;
        return verac;
    }

    public void delayHit(int delay, final int weaponId, final int attackStyle, final Hit... hits) {
        final Entity target = this.target;
        final double base_mage_xp = this.base_mage_xp;
        final int max_hit = this.max_hit;
        final int mage_hit_gfx = this.mage_hit_gfx;
        @SuppressWarnings("unused") final String spell_Name = this.spell_name;
        final int magic_sound = this.magic_sound;
        final int max_poison_hit = this.max_poison_hit;
        final boolean blood_spell = this.blood_spell;
        final boolean block_tele = this.block_tele;
        resetVariables();
        for (Hit hit : hits) {
            boolean rangeAttack = hit.getLook() == HitLook.RANGE_DAMAGE;
            boolean meleeAttack = hit.getLook() == HitLook.MELEE_DAMAGE;
            boolean magicAttack = hit.getLook() == HitLook.MAGIC_DAMAGE;
            final Player player = (Player) hit.getSource();
            handleDivine(player, hit);
            handleElysian(player, hit);
            handleSOL(player, hit);
            if (target instanceof Player) {
                Player p2 = (Player) target;
                if (meleeAttack) {
                    CoresManager.slowExecutor.schedule(new Runnable() {

                        @Override
                        public void run() {
                            if (target.getNextAnimation() != null)
                                return;
                            doDefenceEmote(target);
                        }

                    }, 0, TimeUnit.MILLISECONDS);
                }
                if (!p2.attackedBy.containsKey(player))
                    p2.attackedBy.put(player, 1440);// 15minutes add to list
                if (player.getEquipment().getWeaponId() == 4566)
                    hit.setDamage(p2.getHitpoints());
                if (!isVeracEffect(player))
                    p2.handleProtectPrayers(hit);
                sendLeech(player, p2);
                sendSap(player, p2);
                p2.getCharges().processIncommingHit();
            }
            player.getCharges().processOutgoingHit();
            target.handleIncommingHit(hit);
            handlePenance(player, hit);
            smitePrayer(player, hit);
            handleSagaie(hit, player);
            if (target instanceof Player)
                handleAbsorb(player, hit);
            if (target instanceof NPC) {
                NPC n = (NPC) target;
                if (n.getId() == 1160) {
                    if (meleeAttack) {
                        hit.setDamage(0);
                        player.getPackets()
                                .sendGameMessage(n.getName() + "s protection prayer blocks your melee attack!");
                    }
                }
                if (n.getId() == 1158) {
                    if (rangeAttack || magicAttack) {
                        hit.setDamage(0);
                        player.getPackets().sendGameMessage(n.getName() + "s protection prayer blocks your "
                                + (rangeAttack ? "range" : "magic") + " attack!");
                    }
                }
                if (player.getEquipment().getWeaponId() == 10581
                        && n.getDefinitions().getName().toLowerCase().contains("kalphite"))
                    handleKerisEffect(hit, player);
                if (n.getCapDamage() != -1 && hit.getDamage() > n.getCapDamage()) {
                    if (player.getEquipment().getRingId() == 773)
                        hit.setDamage(n.getMaxHitpoints());
                    else
                        hit.setDamage(n.getCapDamage());
                }
                if (n.getId() == 1158) {
                    if (rangeAttack || magicAttack)
                        hit.setDamage(hit.getDamage() / 2);
                }
                if (n.getId() == 1160) {
                    if (meleeAttack) {
                        if (player.getTemporaryAttributtes().get("VERAC_EFFECT") == Boolean.FALSE)
                            hit.setDamage(hit.getDamage() / 2);
                    }
                }
                if (n.getName().toLowerCase().contains("turoth") || n.getName().toLowerCase().contains("kurask")) {
                    if (meleeAttack) {
                        if (player.getEquipment().getWeaponId() != 13290
                                && player.getEquipment().getWeaponId() != 4158) {
                            player.getPackets()
                                    .sendGameMessage("You can't harm " + n.getName() + "s with this weapon.");
                            hit.setDamage(0);
                        }
                    } else if (rangeAttack) {
                        if (player.getEquipment().getAmmoId() != 4160 && player.getEquipment().getAmmoId() != 13280) {
                            player.getPackets().sendGameMessage("You can't harm " + n.getName() + "s with this ammo.");
                            hit.setDamage(0);
                        }
                    } else if (magicAttack) {
                        if (player.getCombatDefinitions().getSpellId() != 56) {
                            player.getPackets()
                                    .sendGameMessage("You can't harm " + n.getName() + "s with this magic spell.");
                            hit.setDamage(0);
                        }
                    }
                }
                if (player.getTemporaryAttributtes().get("GODMODE") != null)
                    hit.setDamage(n.getHitpoints());
            }
            if (player.isAtWild() && player.getEp() != 100 && player.getAttackedByDelay() > Utils.currentTimeMillis()) {
                if (Utils.getRandom(2) == 0) {
                    int random = Utils.random(5) + 1;
                    if (player.getEp() + random > 100)
                        player.setEp(100);
                    else
                        player.setEp(player.getEp() + (random));
                }
            }
            int damage = hit.getDamage() > target.getHitpoints() ? target.getHitpoints() : hit.getDamage();
            player.getAuraManager().checkSuccefulHits(damage);

            if (blood_spell)
                player.heal(damage / 4);
            if (player.getPrayer().usingPrayer(1, 18) && damage > 0)
                sendSoulSplit(hit, player, target);
            if (fullGuthansEquipped(player) && Utils.random(3) == 0)
                sendGuthanEffect(hit, player, target);
            if (fullAkrisaeEquipped(player) && Utils.random(3) == 0)
                sendAkrisaeEffect(hit, player, target);
            if (player.getControlerManager().getControler() instanceof PestControlGame)
                player.pestControlDamage += damage;
            boolean isDummy = false;
            if (target instanceof NPC) {
                NPC n = (NPC) target;
                isDummy = n.getId() == 4474;
            }
            if ((target instanceof Player || target instanceof NPC) && !isDummy) {
                if (rangeAttack) {
                    double rangeXP = (damage * 0.4);
                    if (player.toggles("ONEXPPERHIT", false)) {
                        if (rangeXP > 0)
                            player.getSkills().addXpNoBonus(Skills.RANGE,
                                    (player.toggles("ONEXHITS", false) ? Math.round(damage) / 10 : damage));
                    } else {
                        if (attackStyle == 2 && player.getEquipment().getAmmoId() != 24116) {
                            player.getSkills().addXp(Skills.RANGE, (damage * 0.2));
                            player.getSkills().addXp(Skills.DEFENCE, (damage * 0.2));
                        } else {
                            player.getSkills().addXp(Skills.RANGE, rangeXP);
                        }
                        double hpXP = (damage * 0.133);
                        if (hpXP > 0)
                            player.getSkills().addXp(Skills.HITPOINTS, hpXP);
                    }
                } else if (meleeAttack) {
                    double meleeXP = (damage * 0.4);
                    if (player.toggles("ONEXPPERHIT", false)) {
                        int xpStyle = player.getCombatDefinitions().getXpStyle(weaponId, attackStyle);
                        if (xpStyle != CombatDefinitions.SHARED) {
                            if (meleeXP > 0)
                                player.getSkills().addXpNoBonus(xpStyle,
                                        (player.toggles("ONEXHITS", false) ? Math.round(damage) / 10 : damage));
                        } else {
                            if (meleeXP > 0)
                                player.getSkills().addXpNoBonus(Skills.STRENGTH,
                                        ((player.toggles("ONEXHITS", false) ? Math.round(damage) / 10 : damage)));
                        }
                    } else {
                        if (meleeXP > 0) {
                            int xpStyle = player.getCombatDefinitions().getXpStyle(weaponId, attackStyle);
                            if (xpStyle != CombatDefinitions.SHARED) {
                                player.getSkills().addXp(xpStyle, meleeXP);
                            } else {
                                player.getSkills().addXp(Skills.ATTACK, (damage * 0.133));
                                player.getSkills().addXp(Skills.STRENGTH, (damage * 0.133));
                                player.getSkills().addXp(Skills.DEFENCE, (damage * 0.133));
                            }
                        }
                        double hpXP = (0.133 * damage);
                        if (hpXP > 0)
                            player.getSkills().addXp(Skills.HITPOINTS, hpXP);
                    }
                } else if (magicAttack) {
                    double magicXP = base_mage_xp + (damage > 0 ? damage * 0.3 : 0);
                    if (player.toggles("ONEXPPERHIT", false)) {
                        if (magicXP > 0)
                            player.getSkills().addXpNoBonus(Skills.MAGIC,
                                    (player.toggles("ONEXHITS", false) ? Math.round(damage) / 10 : damage));
                    } else {
                        if (magicXP > 0) {
                            if (player.getCombatDefinitions().isDefensiveCasting() || (hasPolyporeStaff(player)
                                    && player.getCombatDefinitions().getAttackStyle() == 1)) {
                                player.getSkills().addXp(Skills.DEFENCE, (damage * 0.1));
                                player.getSkills().addXp(Skills.MAGIC, (damage * 0.133));
                            } else {
                                player.getSkills().addXp(Skills.MAGIC, magicXP);
                            }
                            double hpXP = (damage * 0.133);
                            if (hpXP > 0)
                                player.getSkills().addXp(Skills.HITPOINTS, hpXP);
                        }
                    }
                }
            }
            player.setDamage(hit.getDamage());
            checkPID(hit, player, target);
        }

        WorldTasksManager.schedule(new WorldTask() {
            @Override
            public void run() {
                for (Hit hit : hits) {
                    boolean splash = false;
                    Player player = (Player) hit.getSource();
                    if (player == null || player.isDead() || player.hasFinished() || target.isDead()
                            || target.hasFinished())
                        return;
                    boolean rangeAttack = hit.getLook() == HitLook.RANGE_DAMAGE;
                    boolean meleeAttack = hit.getLook() == HitLook.MELEE_DAMAGE;
                    boolean magicAttack = hit.getLook() == HitLook.MAGIC_DAMAGE;
                    boolean regularAttack = rangeAttack || meleeAttack || magicAttack;
                    if (target instanceof Player) {
                        if (magicAttack || rangeAttack)
                            doDefenceEmote(target);
                        player.setDamage(hit.getDamage());
                        handleVengHit(player, hit);
                        boostTurmoil(player);
                        handleRingOfRecoil(player, hit);
                    }
                    if (target instanceof NPC) {
                        NPC n = (NPC) target;
                        doDefenceEmote(n);
                        boostTurmoilNPC(player);
                    }
                    player.setDamage(0);
                    handleDeathTouchBracelet(player, hit);
                    handleDisruptionShield(player, hit);
                    if (rangeAttack) {
                        int arrowId = player.getEquipment().getAmmoId();
                        int bowId = player.getEquipment().getWeaponId();
                        if (arrowId >= 19152 && arrowId <= 19162) {
                            if (bowId >= 19143 && bowId <= 19149 && Utils.getRandom(5) == 0) {
                                target.applyHit(
                                        new Hit(player, (int) (20 + Utils.getRandom(38)), HitLook.MAGIC_DAMAGE));
                            }
                            if (!(bowId >= 19143 && bowId <= 19149) && Utils.getRandom(10) == 0) {
                                target.applyHit(
                                        new Hit(player, (int) (20 + Utils.getRandom(38)), HitLook.MAGIC_DAMAGE));
                            }
                        }
                    }
                    if (magicAttack && hit.getDamage() < 1) {
                        splash = true;
                        hit.setDamage(0);
                    } else if (regularAttack && hit.getDamage() > -1) {
                        target.applyHit(hit);
                    }
                    int damage = hit.getDamage() > target.getHitpoints() ? target.getHitpoints() : hit.getDamage();
                    if ((damage >= max_hit * 0.90) && regularAttack)
                        hit.setCriticalMark();
                    if (rangeAttack || meleeAttack) {
                        double combatXp = damage / 2.5;
                        if (combatXp > 0) {
                            if (rangeAttack) {
                                if (weaponId != -1) {
                                    int ammoId = player.getEquipment().getAmmoId();
                                    String name = ItemDefinitions.getItemDefinitions(weaponId).getName();
                                    String ammoName = ItemDefinitions.getItemDefinitions(ammoId).getName();
                                    if (name.contains("(p++)") || ammoName.contains("(p++)")) {
                                        if (Utils.getRandom(8) == 0)
                                            target.getPoison().makePoisoned(68);
                                    } else if (name.contains("(p+)") || ammoName.contains("(p+)")) {
                                        if (Utils.getRandom(8) == 0)
                                            target.getPoison().makePoisoned(48);
                                    } else if (name.contains("(p)") || ammoName.contains("(p+)")) {
                                        if (Utils.getRandom(8) == 0)
                                            target.getPoison().makePoisoned(28);
                                    }
                                }
                            } else {
                                if (weaponId != -1) {
                                    String name = ItemDefinitions.getItemDefinitions(weaponId).getName();
                                    if (name.contains("(p++)")) {
                                        if (Utils.getRandom(8) == 0)
                                            target.getPoison().makePoisoned(68);
                                    } else if (name.contains("(p+)")) {
                                        if (Utils.getRandom(8) == 0)
                                            target.getPoison().makePoisoned(48);
                                    } else if (name.contains("(p)")) {
                                        if (Utils.getRandom(8) == 0)
                                            target.getPoison().makePoisoned(28);
                                    }
                                    if (weaponId >= 21371 && weaponId <= 21375) {
                                        if (Utils.getRandom(8) == 0) {
                                            target.getPoison().makePoisoned(68);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (magicAttack) {
                        if (splash || hit.getDamage() == 0) {
                            target.gfx(new Graphics(85, 0, 96));
                            playSound(227, player, target);
                        } else {
                            if (player.getCombatDefinitions().getSpellBook() == 192 || mage_hit_gfx == 1677)
                                target.gfx(new Graphics(mage_hit_gfx, 0, 100));
                            else
                                target.gfx(new Graphics(mage_hit_gfx));
                            if (block_tele) {
                                if (target instanceof Player) {
                                    Player targetPlayer = (Player) target;
                                    targetPlayer.setTeleBlockDelay(teleBlockTime);
                                    targetPlayer.setTeleBlockImmune(teleBlockTime + 30000);
                                    targetPlayer.getPackets().sendGameMessage(
                                            "A teleportblock spell have been cast on you, you can't teleport for another "
                                                    + targetPlayer.getTeleBlockTimeleft() + ".",
                                            true);
                                }
                            }
                            if (magic_sound > 0)
                                playSound(magic_sound, player, target);
                        }
                    }
                    if (max_poison_hit > 0 && Utils.getRandom(10) == 0) {
                        if (!target.getPoison().isPoisoned())
                            target.getPoison().makePoisoned(max_poison_hit);
                    }
                    if (target instanceof Player) {
                        Player p2 = (Player) target;
                        handleAutoRetaliate(player, p2);
                    } else {
                        NPC n = (NPC) target;
                        if (!n.isUnderCombat() || n.canBeAttackedByAutoRelatie())
                            n.setTarget(player);
                    }
                }
            }
        }, delay);
    }

    private int getSoundId(int weaponId, int attackStyle) {
        if (weaponId != -1) {
            String weaponName = ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase();
            if (weaponName.contains("bow") || weaponName.contains("dart") || weaponName.contains("knife"))
                return 2702;
            if (weaponName.contains("claws"))
                return 7464;
        }
        return -1;
    }

    public static int getWeaponAttackEmote(int weaponId, int attackStyle) {
        if (weaponId != -1) {
            if (weaponId == -2) {
                // punch/block:14393 kick:14307 spec:14417
                switch (attackStyle) {
                    case 1:
                        return 14307;
                    default:
                        return 14393;
                }
            }
            if (weaponId == 4726) {
                switch (attackStyle) {
                    case 1:// str
                        return 2081;
                    case 2:// controlled
                        return 2083;
                    default:// attack & def
                        return 2080;
                }
            }
            if (weaponId == 11736) {
                switch (attackStyle) {
                    case 1:// str
                        return 413;
                    case 2:// controlled
                        return 414;
                    default:// attack & def
                        return 413;
                }
            }
            String weaponName = ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase();
            if (weaponName != null && !weaponName.equals("null")) {
                if (weaponName.contains("crossbow"))
                    return weaponName.contains("karil") ? 2075 : 4230;
                if (weaponName.contains("bow"))
                    return 426;
                if (weaponName.contains("chinchompa"))
                    return 2779;
                if (weaponName.contains("sling"))
                    return 789;
                if (weaponName.contains("staff of light")) {
                    switch (attackStyle) {
                        case 0:
                            return 15072;
                        case 1:
                            return 15071;
                        case 2:
                            return 414;
                    }
                }
                if (weaponName.contains("staff") || weaponName.contains("wand")) {
                    switch (attackStyle) {
                        case 2:
                            return 419;
                        default:
                            return 393;
                    }
                }
                if (weaponName.contains("chaotic staff"))
                    return 401;
                if (weaponName.contains("dart"))
                    return 582;
                if (weaponName.contains("knife"))
                    return 9055;
                if (weaponName.contains("scimitar") || weaponName.contains("korasi's sword")) {
                    switch (attackStyle) {
                        case 2:
                            return 15072;
                        default:
                            return 15071;
                    }
                }
                if (weaponName.contains("granite mace"))
                    return 400;
                if (weaponName.contains("mace")) {
                    switch (attackStyle) {
                        case 2:
                            return 400;
                        default:
                            return 401;
                    }
                }
                if (weaponName.contains("hatchet")) {
                    switch (attackStyle) {
                        case 2:
                            return 401;
                        default:
                            return 395;
                    }
                }
                if (weaponName.contains("warhammer")) {
                    switch (attackStyle) {
                        default:
                            return 401;
                    }
                }
                if (weaponName.contains("claws")) {
                    switch (attackStyle) {
                        case 2:
                            return 1067;
                        default:
                            return 393;
                    }
                }
                if (weaponName.contains("whip")) {
                    switch (attackStyle) {
                        case 1:
                            return 11969;
                        case 2:
                            return 11970;
                        default:
                            return 11968;
                    }
                }
                if (weaponName.contains("anchor")) {
                    switch (attackStyle) {
                        case 2:
                            return 5865;
                        default:
                            return 5865;
                    }
                }
                if (weaponName.contains("tzhaar-ket-em")) {
                    switch (attackStyle) {
                        default:
                            return 401;
                    }
                }
                if (weaponName.contains("toktz-xil-ek")) {
                    switch (attackStyle) {
                        case 2:
                            return 400;
                        default:
                            return 12311;
                    }
                }
                if (weaponName.contains("toktz-xil-ak")) {
                    switch (attackStyle) {
                        case 2:
                            return 12311;
                        default:
                            return 400;
                    }
                }
                if (weaponName.contains("tzhaar-ket-om")) {
                    switch (attackStyle) {
                        default:
                            return 2661;
                    }
                }
                if (weaponName.contains("halberd")) {
                    switch (attackStyle) {
                        case 1:
                            return 440;
                        default:
                            return 428;
                    }
                }
                if (weaponName.contains("zamorakian spear")) {
                    switch (attackStyle) {
                        case 1:// str
                            return 12005;
                        case 2:// controlled
                            return 12009;
                        default:// attack & def
                            return 12006;
                    }
                }
                if (weaponName.contains("spear")) {
                    switch (attackStyle) {
                        case 1:
                            return 440;
                        case 2:
                            return 429;
                        default:
                            return 428;
                    }
                }
                if (weaponName.contains("flail")) {
                    return 2062;
                }
                if (weaponName.contains("javelin")) {
                    return 10501;
                }
                if (weaponName.contains("morrigan's throwing axe"))
                    return 10504;
                if (weaponName.contains("agai"))
                    return 3236;
                if (weaponName.contains("pickaxe") || weaponName.contains("adze")) {
                    switch (attackStyle) {
                        case 2:
                            return 400;
                        default:
                            return 401;
                    }
                }
                if (weaponName.contains("dragon dagger")) {
                    switch (attackStyle) {
                        case 2:
                            return 377;
                        default:
                            return 376;
                    }
                }
                if (weaponName.contains("dagger")) {
                    switch (attackStyle) {
                        case 2:
                            return 390;
                        default:
                            return 386;
                    }
                }
                if (weaponName.contains("Toktz-xil-ak")) {
                    switch (attackStyle) {
                        case 2:
                            return 12311;
                        default:
                            return 400;
                    }
                }
                if (weaponName.contains("godsword")) {
                    switch (attackStyle) {
                        case 2:
                            return 7048;
                        case 3:
                            return 7049;
                        default:
                            return 7041;
                    }
                }
                if (weaponId == 10858 || weaponName.contains("2h sword") || weaponName.equals("dominion sword")
                        || weaponName.equals("thok's sword") || weaponName.equals("saradomin sword")
                        || weaponName.equals(" godsword")) {
                    switch (attackStyle) {
                        case 2:
                            return 7048;
                        case 3:
                            return 7049;
                        default:
                            return 7041;
                    }
                }
                if (weaponName.contains("laded sword")) {
                    switch (attackStyle) {
                        case 2:
                            return 13048;
                        default:
                            return 13049;
                    }
                }
                if (weaponName.contains(" sword") || weaponName.contains("saber") || weaponName.contains("longsword")
                        || weaponName.contains("light") || weaponName.contains("excalibur")) {
                    switch (attackStyle) {
                        case 2:
                            return 12310;
                        default:
                            return 12311;
                    }
                }
                if (weaponName.contains("rapier") || weaponName.contains("brackish")) {
                    switch (attackStyle) {
                        case 2:
                            return 13048;
                        default:
                            return 13049;
                    }
                }
                if (weaponName.contains("katana")) {
                    switch (attackStyle) {
                        case 2:
                            return 1882;
                        default:
                            return 1884;
                    }
                }
                if (weaponName.contains("greataxe") || weaponName.contains("balmung")) {
                    switch (attackStyle) {
                        case 2:
                            return 12003;
                        default:
                            return 12002;
                    }
                }
                if (weaponName.contains("rubber chicken")) {
                    return 395;
                }
                if (weaponName.contains("torag's hammer")) {
                    switch (attackStyle) {
                        default:
                            return 2068;
                    }
                }
                if (weaponName.contains("granite maul")) {
                    switch (attackStyle) {
                        default:
                            return 1665;
                    }
                }
                if (weaponName.contains("battleaxe")) {
                    switch (attackStyle) {
                        default:
                            return 395;
                    }
                }

            }
        }
        switch (weaponId) {
            case 6522:
                return 2614;
            case 16405:// novite maul
            case 16407:// Bathus maul
            case 16409:// Maramaros maul
            case 16411:// Kratonite maul
            case 16413:// Fractite maul
            case 16415:// Zephyrium maul
            case 16417:// Argonite maul
            case 16419:// Katagon maul
            case 16421:// Gorgonite maul
            case 16423:// Promethium maul
            case 16425:
                return 2661; // mauls
            case 18353:
                return 13055; // chaotic maul
            case 1419:
                return 440;
            case 13883: // morrigan thrown axe
                return 10504;
            case 15241:
                return 12174;
            case 18355:
                return 401;
            case 4675:
            case 4710:
            case 4862:
            case 21777:
            case 4170:
                return 393;
            default:
                switch (attackStyle) {
                    case 1:
                        return 423;
                    default:
                        return 422; // todo default emote
                }
        }
    }

    public void doDefenceEmote(Entity target) {
        if (target instanceof Player) {
            Player p2 = (Player) target;
            if (p2.getLockDelay() > Utils.currentTimeMillis())
                return;
            if (p2.getLockDelay() > Utils.currentTimeMillis())
                return;
            p2.animate(new Animation(Combat.getDefenceEmote(p2)));
        }
    }

    private void doDefenceEmote(NPC npc) {
        npc.animate(new Animation(Combat.getDefenceEmote(target)));
    }

    public static int getMeleeCombatDelay(Player player, int weaponId) {
        final ItemDefinitions defs = ItemDefinitions.getItemDefinitions(weaponId);
        if (defs == null || weaponId == -1) {
            return weaponId == -1 ? 3 : 4;
        }
        if (player.getTemporaryTarget() instanceof NPC && defs.getName().toLowerCase().contains("decorative"))
            return 1;
        return defs.getAttackSpeed() - 1;
    }

    @Override
    public void stop(final Player player) {
        player.setNextFaceEntity(null);
        // player.getInterfaceManager().closeTab(player.getInterfaceManager().isResizableScreen(),
        // getHealthOverlayId(player));
    }

    private boolean checkAll(Player player) {
        if (player.isDead() || player.hasFinished() || target.isDead() || target.hasFinished()
                || player.getLockDelay() > Utils.currentTimeMillis()) {
            return false;
        }
        int distanceX = player.getX() - target.getX();
        int distanceY = player.getY() - target.getY();
        int size = player.getSize();
        size = 1;
        int maxDistance = 16;
        if (player.hasWalkSteps())
            player.resetWalkSteps();

        if (player.getPlane() != target.getPlane() || distanceX > size + maxDistance || distanceX < -1 - maxDistance
                || distanceY > size + maxDistance || distanceY < -1 - maxDistance) {
            player.resetFreezeDelay();
            return false;
        }
        if (player.getCombatDefinitions().getSpellId() <= 0
                && Utils.inCircle(new WorldTile(3105, 3933, 0), target, 23)) {
            player.getPackets().sendGameMessage("You can only use magic in the arena.");
            return false;
        }
        if (target instanceof Player) {
            Player p2 = (Player) target;
            if (!player.isCanPvp() || !p2.isCanPvp())
                return false;
        } else {
            NPC n = (NPC) target;
            if (n.isCantInteract()) {
                return false;
            }
            if (n instanceof Familiar) {
                Familiar familiar = (Familiar) n;
                if (!familiar.canAttack(target))
                    return false;
            } else {
                if (!n.canBeAttackFromOutOfArea() && !MapAreas.isAtArea(n.getMapAreaNameHash(), player)) {
                    return false;
                }
                if (n.getId() == 879) {
                    if (player.getEquipment().getWeaponId() != 2402
                            && player.getCombatDefinitions().getAutoCastSpell() <= 0 && !hasPolyporeStaff(player)
                            && player.getEquipment().getWeaponId() != 24203) {
                        player.getPackets().sendGameMessage("I'd better wield Silverlight first.");
                        return false;
                    }
                } else if (n.getId() >= 14084 && n.getId() <= 14139) {
                    int weaponId = player.getEquipment().getWeaponId();
                    if (!((weaponId >= 13117 && weaponId <= 13146) || (weaponId >= 21580 && weaponId <= 21582))
                            && player.getCombatDefinitions().getAutoCastSpell() <= 0 && !hasPolyporeStaff(player)) {
                        player.getPackets().sendGameMessage("I'd better wield a silver weapon first.");
                        return false;
                    }
                } else if (n.getId() == 6222 || n.getId() == 6223 || n.getId() == 6225 || n.getId() == 6227
                        || (n.getId() >= 6232 && n.getId() <= 6246)) {
                    if (isRanging(player) == 0) {
                        player.getPackets()
                                .sendGameMessage("The Aviansie is flying too high for you to attack using melee.");
                        return false;
                    }
                } else if (n.getId() == 14301 || n.getId() == 14302 || n.getId() == 14303 || n.getId() == 14304) {
                    Glacyte glacyte = (Glacyte) n;
                    if (glacyte.getGlacor().getTargetIndex() != -1
                            && player.getIndex() != glacyte.getGlacor().getTargetIndex()) {
                        player.getPackets().sendGameMessage("This isn't your target.");
                        return false;
                    }
                }
            }
        }
        if (!(target instanceof NPC && ((NPC) target).isForceMultiAttacked())) {
            if (player.isAtMultiArea() && !target.isAtMultiArea()) {
                if (target.getAttackedBy() != player && target.getAttackedByDelay() > Utils.currentTimeMillis()) {
                    player.getPackets().sendGameMessage("That "
                            + (player.getAttackedBy() instanceof Player ? "player" : "npc") + " is already in combat.");
                    return false;
                }
            }
            if (!target.isAtMultiArea() && !player.isAtMultiArea()) {
                if (player.getAttackedBy() != target && player.getAttackedByDelay() > Utils.currentTimeMillis()) {
                    player.getPackets().sendGameMessage("You are already in combat.");
                    return false;
                }
                if (target.getAttackedBy() != player && target.getAttackedByDelay() > Utils.currentTimeMillis()) {
                    player.getPackets().sendGameMessage("That "
                            + (player.getAttackedBy() instanceof Player ? "player" : "npc") + " is already in combat.");
                    return false;
                }
            }
        }
        int isRanging = isRanging(player);
        if (Utils.colides(player.getX(), player.getY(), size, target.getX(), target.getY(), target.getSize())
                && !target.hasWalkSteps()) {
            if (player.getFreezeDelay() >= Utils.currentTimeMillis()) {
                player.getPackets().sendGameMessage("A magical force prevents you from moving.");
                return false;
            }
            player.resetWalkSteps();
            if (!player.addWalkSteps(target.getX() + target.getSize(), player.getY())) {
                player.resetWalkSteps();
                if (!player.addWalkSteps(target.getX() - size, player.getY())) {
                    player.resetWalkSteps();
                    if (!player.addWalkSteps(player.getX(), target.getY() + target.getSize())) {
                        player.resetWalkSteps();
                        if (!player.addWalkSteps(player.getX(), target.getY() - size)) {
                            player.resetWalkSteps();
                            return false;
                        }
                    }
                }
            }
            return true;
        } else if (isRanging == 0 && player.getCombatDefinitions().getSpellId() <= 0 && !hasPolyporeStaff(player)
                && player.getEquipment().getWeaponId() != 24203 && target.getSize() == 1
                && Math.abs(player.getX() - target.getX()) == 1 && Math.abs(player.getY() - target.getY()) == 1
                && !target.hasWalkSteps()) {
            if (player.getFreezeDelay() >= Utils.currentTimeMillis()) {
                player.getPackets().sendGameMessage("A magical force prevents you from moving.");
                return false;
            }
            player.calcFollow(target, player.getRun() ? 2 : 1, true, true);
            return true;
        }
        int spellId = player.getCombatDefinitions().getSpellId();
        maxDistance = isRanging != 0 ? getAttackDistance(player) + (target.hasWalkSteps() ? 1 : 0)
                : (spellId > 0 || hasPolyporeStaff(player) || player.getEquipment().getWeaponId() == 24203) ? 7 : 0;
        String name = ItemDefinitions.getItemDefinitions(player.getEquipment().getWeaponId()).getName().toLowerCase();
        if (name.contains("halberd"))
            maxDistance++;
        boolean needCalc = !player.hasWalkSteps() || target.hasWalkSteps();
        if ((!player.clipedProjectile(target, maxDistance == 0 && !forceCheckClipAsRange(target)))
                || !Utils.isOnRange(player.getX(), player.getY(), size, target.getX(), target.getY(), target.getSize(),
                maxDistance)) {
            player.resetWalkSteps();
            if (!player.hasWalkSteps()) {
                if (needCalc) {
                    if (player.getX() == target.getX() && player.getY() == target.getY() && target.hasWalkSteps()) {
                        if (player.getIndex() < target.getIndex())
                            player.resetWalkSteps();
                        else
                            target.resetWalkSteps();
                    } else
                        player.calcFollow(target, player.getRun() ? 2 : 1, true, true);
                }
            }
            return true;
        } else {
            player.resetWalkSteps();
        }
        if (player.getPolDelay() >= Utils.currentTimeMillis() && !(player.getEquipment().getWeaponId() == 15486
                || player.getEquipment().getWeaponId() == 22207 || player.getEquipment().getWeaponId() == 22209
                || player.getEquipment().getWeaponId() == 11736 || player.getEquipment().getWeaponId() == 22211
                || player.getEquipment().getWeaponId() == 22213))
            player.setPolDelay(0);
        player.temporaryAttribute().put("last_target", target);
        if (target instanceof Player) {
            Player p2 = (Player) target;
            player.setTargetName(p2);
            p2.setTargetName(player);
        }
        target.temporaryAttribute().put("last_attacker", player);
        player.getTemporaryAttributtes().put("temporaryActionDelay", 4 * 1000 + Utils.currentTimeMillis());
        if (player.getCombatDefinitions().isInstantAttack()) {
            player.getCombatDefinitions().setInstantAttack(false);
            if (player.getCombatDefinitions().getAutoCastSpell() > 0)
                return true;
            if (player.getCombatDefinitions().isUsingSpecialAttack()) {
                if (!specialExecute(player))
                    return true;
                int weaponId = player.getEquipment().getWeaponId();
                int attackStyle = player.getCombatDefinitions().getAttackStyle();
                switch (weaponId) {
                    case 4153:
                        player.faceEntity(target);
                        player.setNextAnimationNoPriority(new Animation(1667), player);
                        player.gfx(new Graphics(340, 0, 96 << 16));
                        delayNormalHit(weaponId, attackStyle, getMeleeHit(player,
                                getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true)));
                        player.getActionManager().setActionDelay(player.getActionManager().getActionDelay());
                        break;
                    case 14679:
                        player.setNextAnimationNoPriority(new Animation(10505), player);
                        player.gfx(new Graphics(1840));
                        player.faceEntity(target);
                        delayNormalHit(weaponId, attackStyle, getMeleeHit(player,
                                getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true)));
                        player.getActionManager().setActionDelay(2);
                        break;
                }
            }
            return true;
        }
        return true;
    }

    public static boolean specialExecute(Player player) {
        int weaponId = player.getEquipment().getWeaponId();
        player.getCombatDefinitions().switchUsingSpecialAttack();
        int specAmt = getSpecialAmmount(weaponId);
        if (specAmt == 0) {
            player.getPackets()
                    .sendGameMessage("This weapon has no special Attack, if you still see special bar please relogin.");
            return false;
        }
        if (player.getCombatDefinitions().hasRingOfVigour())
            specAmt *= 0.9;
        if (player.getInventory().containsItem(773, 1))
            specAmt *= 0.0;
        if (player.getCombatDefinitions().getSpecialAttackPercentage() < specAmt) {
            player.getPackets().sendGameMessage("You don't have enough power left.");
            player.getCombatDefinitions().desecreaseSpecialAttack(0);
            return false;
        }
        player.getCombatDefinitions().desecreaseSpecialAttack(specAmt);
        return true;
    }

    /*
     * 0 not ranging, 1 invalid ammo so stops att, 2 can range, 3 no ammo
     */

    public static final int isRanging(Player player) {
        int weaponId = player.getEquipment().getWeaponId();
        if (weaponId == -1)
            return 0;
        if (weaponId >= 13719 && weaponId <= 13721)
            return 0;
        String name = ItemDefinitions.getItemDefinitions(weaponId).getName();
        if (name != null) {
            if (name.toLowerCase().contains("bronze knife") || name.toLowerCase().contains("iron knife")
                    || name.toLowerCase().contains("steel knife") || name.toLowerCase().contains("black knife")
                    || name.toLowerCase().contains("mthiril knife") || name.toLowerCase().contains("adamant knife")
                    || name.toLowerCase().contains("rune knife") || name.toLowerCase().contains("bronze dart")
                    || name.toLowerCase().contains("iron dart") || name.toLowerCase().contains("steel dart")
                    || name.toLowerCase().contains("black dart") || name.toLowerCase().contains("mithril dart")
                    || name.toLowerCase().contains("adamant dart") || name.toLowerCase().contains("dragon dart")
                    || name.toLowerCase().contains("rune dart") || name.toLowerCase().contains("javelin")
                    || name.contains("thrownaxe") || name.toLowerCase().contains("throwing axe")
                    || name.toLowerCase().contains("crystal bow") || name.toLowerCase().contains("sling")
                    || name.toLowerCase().contains("chinchompa") || name.toLowerCase().contains("bolas")
                    || weaponId == 21364 || weaponId == 6522)
                return 2;
        }
        int ammoId = player.getEquipment().getAmmoId();
        String ammo = ItemDefinitions.getItemDefinitions(ammoId).getName().toLowerCase();
        switch (weaponId) {
            case 9705:
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 9706:
                        return 2;
                    default:
                        return 1;
                }
            case 15241: // Hand cannon
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 15243: // Hand cannon shot
                        return 2;
                    default:
                        return 1;
                }
            case 839: // longbow
            case 841: // shortbow
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 883: // bronze p
                    case 5616: // bronze p+
                    case 5622: // bronze p++
                    case 885: // iron p
                    case 5617: // iron p+
                    case 5623: // iron p++
                    case 882: // bronze arrow
                    case 884: // iron arrow
                        return 2;
                    default:
                        return 1;
                }
            case 843: // oak longbow
            case 845: // oak shortbow
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 883: // bronze p
                    case 5616: // bronze p+
                    case 5622: // bronze p++
                    case 885: // iron p
                    case 5617: // iron p+
                    case 5623: // iron p++
                    case 887: // steel p
                    case 5618: // steel p
                    case 5624: // steel p++
                    case 882: // bronze arrow
                    case 884: // iron arrow
                    case 886: // steel arrow
                        return 2;
                    default:
                        return 1;
                }
            case 847: // willow longbow
            case 849: // willow shortbow
            case 13541: // Willow composite bow
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 883: // bronze p
                    case 5616: // bronze p+
                    case 5622: // bronze p++
                    case 885: // iron p
                    case 5617: // iron p+
                    case 5623: // iron p++
                    case 887: // steel p
                    case 5618: // steel p
                    case 5624: // steel p++
                    case 889: // mithril p
                    case 5619: // mithril p+
                    case 5625: // mithril p++
                    case 882: // bronze arrow
                    case 884: // iron arrow
                    case 886: // steel arrow
                    case 888: // mithril arrow
                        return 2;
                    default:
                        return 1;
                }
            case 851: // maple longbow
            case 853: // maple shortbow
            case 18331: // Maple longbow (sighted)
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 883: // bronze p
                    case 5616: // bronze p+
                    case 5622: // bronze p++
                    case 885: // iron p
                    case 5617: // iron p+
                    case 5623: // iron p++
                    case 887: // steel p
                    case 5618: // steel p
                    case 5624: // steel p++
                    case 889: // mithril p
                    case 5619: // mithril p+
                    case 5625: // mithril p++
                    case 891: // adamant p
                    case 5620: // adamant p+
                    case 5626: // adamant p++
                    case 882: // bronze arrow
                    case 884: // iron arrow
                    case 886: // steel arrow
                    case 888: // mithril arrow
                    case 890: // adamant arrow
                        return 2;
                    default:
                        return 1;
                }
            case 2883:// ogre bow
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 2866: // ogre arrow
                        return 2;
                    default:
                        return 1;
                }
            case 4827:// Comp ogre bow
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 2866: // ogre arrow
                    case 4773: // bronze brutal
                    case 4778: // iron brutal
                    case 4783: // steel brutal
                    case 4788: // black brutal
                    case 4793: // mithril brutal
                    case 4798: // adamant brutal
                    case 4803: // rune brutal
                        return 2;
                    default:
                        return 1;
                }
            case 855: // yew longbow
            case 857: // yew shortbow
            case 10281: // Yew composite bow
            case 14121: // Sacred clay bow
            case 859: // magic longbow
            case 861: // magic shortbow
            case 10284: // Magic composite bow
            case 18332: // Magic longbow (sighted)
            case 6724: // seercull
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 883: // bronze p
                    case 5616: // bronze p+
                    case 5622: // bronze p++
                    case 885: // iron p
                    case 5617: // iron p+
                    case 5623: // ron p++
                    case 887: // steel p
                    case 5618: // steel p
                    case 5624: // steel p++
                    case 889: // mithril p
                    case 5619: // mithril p+
                    case 5625: // mithril p++
                    case 891: // adamant p
                    case 5620: // adamant p+
                    case 5626: // adamant p++
                    case 893: // rune arrow p
                    case 5621: // rune arrow p+
                    case 5627: // rune arrow p++
                    case 882: // bronze arrow
                    case 884: // iron arrow
                    case 886: // steel arrow
                    case 888: // mithril arrow
                    case 890: // adamant arrow
                    case 892: // rune arrow
                        return 2;
                    case 19157: // guthix arrows
                    case 19152: // saradomin arrows
                    case 19162: // zamorak arrows
                        return 2;
                    default:
                        return 1;
                }
            case 11235: // dark bows
            case 15701:
            case 15702:
            case 15703:
            case 15704:
            case 20171:
            case 20172:
            case 20173:
            case 20174:
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 883: // bronze p
                    case 5616: // bronze p+
                    case 5622: // bronze p++
                    case 885: // iron p
                    case 5617: // iron p+
                    case 5623: // ron p++
                    case 887: // steel p
                    case 5618: // steel p
                    case 5624: // steel p++
                    case 889: // mithril p
                    case 5619: // mithril p+
                    case 5625: // mithril p++
                    case 891: // adamant p
                    case 5620: // adamant p+
                    case 5626: // adamant p++
                    case 893: // rune arrow p
                    case 5621: // rune arrow p+
                    case 5627: // rune arrow p++
                    case 11227: // dragon arrow p
                    case 11228: // dragon arrow p+
                    case 11229: // dragon arrow p++
                    case 882: // bronze arrow
                    case 884: // iron arrow
                    case 886: // steel arrow
                    case 888: // mithril arrow
                    case 890: // adamant arrow
                    case 892: // rune arrow
                    case 11212: // dragon arrow
                        return 2;
                    default:
                        return 1;
                }
            case 19143: // saradomin bow
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 19152: // saradomin arrow
                        return 2;
                    default:
                        return 1;
                }
            case 19146: // guthix bow
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 19157: // guthix arrow
                        return 2;
                    default:
                        return 1;
                }
            case 19149: // zamorak bow
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 19162: // zamorak arrow
                        return 2;
                    default:
                        return 1;
                }
            case 24338: // Royal crossbow
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 24336: // Coral bolts
                        return 2;
                    default:
                        return 1;
                }
            case 24303: // Coral crossbow
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 24304: // Coral bolts
                        return 2;
                    default:
                        return 1;
                }
            case 4734: // karil crossbow
            case 4934:
            case 4935:
            case 4936:
            case 4937:
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 4740: // bolt rack
                        return 2;
                    default:
                        return 1;
                }
            case 10156: // hunters crossbow
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 10158: // Kebbit bolts
                    case 10159: // Long kebbit bolts
                        return 2;
                    default:
                        return 1;
                }
            case 8880: // Dorgeshuun c'bow
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 8882: // bone bolts
                        return 2;
                    default:
                        return 1;
                }
            case 14684: // zanik crossbow
                if (ammo.contains("bronze bolt") || ammo.contains("iron bolt") || ammo.contains("steel bolt")
                        || ammo.contains("black bolt") || ammo.contains("mithril bolt") || ammo.contains("adamant bolt")
                        || ammo.contains("silver bolt") || ammo.contains("opal bolt") || ammo.contains("pearl bolt")
                        || ammo.contains("topaz bolt") || ammo.contains("sapphire bolt") || ammo.contains("emerald bolt")
                        || ammo.contains("ruby bolt") || ammo.contains("diamond bolt") || ammo.contains("broad-tipped bolt")
                        || ammo.contains("bone bolt"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 767: // phoenix crossbow
            case 837: // crossbow
                switch (ammoId) {
                    case -1:
                        return 3;
                    case 877: // bronze bolts
                        return 2;
                    default:
                        return 1;
                }
            case 9174: // bronze crossbow
                if (ammo.contains("bronze bolt") || ammo.contains("opal bolt"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 9176: // blurite crossbow
                if (ammo.contains("bronze bolt") || ammo.contains("iron bolt") || ammo.contains("opal bolt")
                        || ammo.contains("black bolt") || ammo.contains("topaz bolt") || ammo.contains("pearl bolt")
                        || ammo.contains("blurite bolt") || ammo.contains("jade bolt"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 9177: // iron crossbow
                if (ammo.contains("bronze bolt") || ammo.contains("iron bolt") || ammo.contains("opal bolt")
                        || ammo.contains("pearl bolt"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 9179: // steel crossbow
                if (ammo.contains("bronze bolt") || ammo.contains("iron bolt") || ammo.contains("steel bolt")
                        || ammo.contains("silver bolt") || ammo.contains("opal bolt") || ammo.contains("pearl bolt")
                        || ammo.contains("topaz bolt"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 13081: // black crossbow
                if (ammo.contains("bronze bolt") || ammo.contains("iron bolt") || ammo.contains("steel bolt")
                        || ammo.contains("black bolt") || ammo.contains("silver bolt") || ammo.contains("opal bolt")
                        || ammo.contains("pearl bolt") || ammo.contains("topaz bolt"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 9181: // Mith crossbow
                if (ammo.contains("bronze bolt") || ammo.contains("iron bolt") || ammo.contains("steel bolt")
                        || ammo.contains("black bolt") || ammo.contains("mithril bolt") || ammo.contains("silver bolt")
                        || ammo.contains("opal bolt") || ammo.contains("pearl bolt") || ammo.contains("topaz bolt")
                        || ammo.contains("sapphire bolt") || ammo.contains("emerald bolt"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 9183: // adam c bow
                if (ammo.contains("bronze bolt") || ammo.contains("iron bolt") || ammo.contains("steel bolt")
                        || ammo.contains("black bolt") || ammo.contains("mithril bolt") || ammo.contains("adamant bolt")
                        || ammo.contains("silver bolt") || ammo.contains("opal bolt") || ammo.contains("pearl bolt")
                        || ammo.contains("topaz bolt") || ammo.contains("sapphire bolt") || ammo.contains("emerald bolt")
                        || ammo.contains("ruby bolt") || ammo.contains("diamond bolt")
                        || ammo.contains("broad-tipped bolt"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 9185: // rune c bow
            case 18357: // chaotic crossbow
            case 18358:
                if (ammo.contains("bronze bolt") || ammo.contains("iron bolt") || ammo.contains("steel bolt")
                        || ammo.contains("black bolt") || ammo.contains("mithril bolt") || ammo.contains("adamant bolt")
                        || ammo.contains("runite bolt") || ammo.contains("silver bolt") || ammo.contains("opal bolt")
                        || ammo.contains("pearl bolt") || ammo.contains("topaz bolt") || ammo.contains("sapphire bolt")
                        || ammo.contains("emerald bolt") || ammo.contains("ruby bolt") || ammo.contains("diamond bolt")
                        || ammo.contains("dragon bolt") || ammo.contains("onyx bolt") || ammo.contains("bakriminel bolt")
                        || ammo.contains("broad-tipped bolt"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }

            case 17295: // hexhunter bow
            case 15836:
            case 15785: // sagittarian
            case 15913:
            case 16887:
            case 16337:
                if (ammo.contains("novite arrow") || ammo.contains("bathus arrow") || ammo.contains("marmaros arrow")
                        || ammo.contains("kratonite arrow") || ammo.contains("fractite arrow")
                        || ammo.contains("zephyrium arrow") || ammo.contains("argonite arrow")
                        || ammo.contains("katagon arrow") || ammo.contains("gorgonite arrow")
                        || ammo.contains("promenthium arrow") || ammo.contains("sagittarian arrow"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 15784: // grave creeper
            case 15912:
            case 16335:
            case 16885:
                if (ammo.contains("novite arrow") || ammo.contains("bathus arrow") || ammo.contains("marmaros arrow")
                        || ammo.contains("kratonite arrow") || ammo.contains("fractite arrow")
                        || ammo.contains("zephyrium arrow") || ammo.contains("argonite arrow")
                        || ammo.contains("katagon arrow") || ammo.contains("gorgonite arrow")
                        || ammo.contains("promenthium arrow"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 15783: // entgallow
            case 15911:
            case 16333:
            case 16334:
                if (ammo.contains("novite arrow") || ammo.contains("bathus arrow") || ammo.contains("marmaros arrow")
                        || ammo.contains("kratonite arrow") || ammo.contains("fractite arrow")
                        || ammo.contains("zephyrium arrow") || ammo.contains("argonite arrow")
                        || ammo.contains("katagon arrow") || ammo.contains("gorgonite arrow"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 15782: // corpsethorn
            case 15910:
            case 16331:
            case 16332:
                if (ammo.contains("novite arrow") || ammo.contains("bathus arrow") || ammo.contains("marmaros arrow")
                        || ammo.contains("kratonite arrow") || ammo.contains("fractite arrow")
                        || ammo.contains("zephyrium arrow") || ammo.contains("argonite arrow")
                        || ammo.contains("katagon arrow"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 15781: // thigat
            case 15909:
            case 16329:
            case 16879:
                if (ammo.contains("novite arrow") || ammo.contains("bathus arrow") || ammo.contains("marmaros arrow")
                        || ammo.contains("kratonite arrow") || ammo.contains("fractite arrow")
                        || ammo.contains("zephyrium arrow") || ammo.contains("argonite arrow"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 15780: // bovistrangler
            case 15908:
            case 16327:
            case 16877:
                if (ammo.contains("novite arrow") || ammo.contains("bathus arrow") || ammo.contains("marmaros arrow")
                        || ammo.contains("kratonite arrow") || ammo.contains("fractite arrow")
                        || ammo.contains("zephyrium arrow"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 15779: // spinebeam
            case 15907:
            case 16325:
            case 16875:
                if (ammo.contains("novite arrow") || ammo.contains("bathus arrow") || ammo.contains("marmaros arrow")
                        || ammo.contains("kratonite arrow") || ammo.contains("fractite arrow"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 15778: // utuku
            case 15906:
            case 16323:
            case 16873:
                if (ammo.contains("novite arrow") || ammo.contains("bathus arrow") || ammo.contains("marmaros arrow")
                        || ammo.contains("kratonite arrow"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 15777: // blood spindle
            case 15905:
            case 16321:
            case 16871:
                if (ammo.contains("novite arrow") || ammo.contains("bathus arrow") || ammo.contains("marmaros arrow"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 15776: // seeping elm
            case 15904:
            case 16319:
            case 16869:
                if (ammo.contains("novite arrow") || ammo.contains("bathus arrow"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            case 15775: // tangle gum
            case 15903:
            case 16317:
            case 16867:
                if (ammo.contains("novite arrow"))
                    return 2;
                switch (ammoId) {
                    case -1:
                        return 3;
                    default:
                        return 1;
                }
            default:
                return 0;
        }
    }

    /**
     * Checks if the player is wielding polypore staff.
     *
     * @param player The player.
     * @return {@code True} if so.
     */
    private static boolean hasPolyporeStaff(Player player) {
        int weaponId = player.getEquipment().getWeaponId();
        return weaponId == 22494 || weaponId == 22496;
    }

    public void processMorriganJavelins(final Player player, int hit) {
        final Entity finalTarget = target;
        if (finalTarget.getTemporaryAttributtes().get("MORRIGANEFFECT") == Boolean.TRUE) {
            finalTarget.morriganHits += hit;
            return;
        }
        finalTarget.getTemporaryAttributtes().put("MORRIGANEFFECT", Boolean.TRUE);
        finalTarget.morriganHits += hit;
        WorldTasksManager.schedule(new WorldTask() {

            @Override
            public void run() {
                if (finalTarget.isDead() || finalTarget.hasFinished() || player.isDead() || player.hasFinished()) {
                    stop();
                    finalTarget.morriganHits = 0;
                    finalTarget.getTemporaryAttributtes().remove("MORRIGANEFFECT");
                    return;
                }
                if (finalTarget.morriganHits >= 50) {
                    finalTarget.morriganHits -= 50;
                    finalTarget.applyHit(new Hit(player, 50, HitLook.REGULAR_DAMAGE));
                } else {
                    finalTarget.applyHit(new Hit(player, finalTarget.morriganHits, HitLook.REGULAR_DAMAGE));
                    finalTarget.morriganHits = 0;
                    finalTarget.getTemporaryAttributtes().remove("MORRIGANEFFECT");
                    stop();
                    return;
                }
            }
        }, 2, 2);
    }

    public void processVineCall(final Player player) {
        int weaponId = player.getEquipment().getWeaponId();
        final int attackStyle = player.getCombatDefinitions().getAttackStyle();
        final Entity finalTarget = target;
        player.animate(new Animation(11971));
        if (Utils.random(1) == 0 && !target.getPoison().isPoisoned()) {
            target.getPoison().makePoisoned(48);
        }
        delayNormalHit(weaponId, attackStyle,
                getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true)));
        if (finalTarget.getTemporaryAttributtes().get("VINEEFFECT") == Boolean.TRUE) {
            finalTarget.vineHits = 10;
        } else
            finalTarget.vineHits += 10;
        finalTarget.getTemporaryAttributtes().put("VINEEFFECT", Boolean.TRUE);
        final WorldTile vineTile = new WorldTile(target.getX(), target.getY(), target.getPlane());
        WorldTasksManager.schedule(new WorldTask() {

            @Override
            public void run() {
                if (player.getEquipment().getWeaponId() != 21371) {
                    player.sm("Since you no longer wield a vine whip, the vine ignores your call");
                    finalTarget.getTemporaryAttributtes().remove("VINEEFFECT");
                    finalTarget.vineHits = 0; stop();
                 return;
                }

                if (finalTarget.isDead() || finalTarget.hasFinished() || player.isDead() || player.hasFinished()) {
                    finalTarget.getTemporaryAttributtes().remove("VINEEFFECT");
                    finalTarget.vineHits = 0;
                    stop();
                    return;
                }
                if (finalTarget.vineHits > 0) {
                    finalTarget.vineHits--;
                    World.sendGraphics(null, new Graphics(478), vineTile);
                }
                if (finalTarget.vineHits < 1) {
                    finalTarget.getTemporaryAttributtes().remove("VINEEFFECT");
                    stop();
                }
                int damage = 0;
                if (player.isAtMultiArea() && target.isAtMultiArea()) {
                    if (target instanceof Player) {
                        int i = 0;
                        damage = Utils.random(80, 130);
                        if (Utils.inCircle(finalTarget, vineTile, 1))
                            finalTarget.applyHit(new Hit(player, damage, HitLook.MELEE_DAMAGE));
                        for (Player players : World.getPlayers()) {
                            if (players != null) {
                                if (Math.abs(player.getSkills().getCombatLevel()
                                        - players.getSkills().getCombatLevel()) > WildernessControler
                                        .getWildLevel(player)
                                        && Utils.inCircle(players, vineTile, 1)) {
                                    if (i > 2)
                                        continue;
                                    damage = Utils.random(80, 130);
                                    i++;
                                    if (i > 0) {
                                        addMeleeCombatXp(player, damage, weaponId, attackStyle);
                                        players.applyHit(new Hit(player, damage, HitLook.MELEE_DAMAGE));
                                    }
                                }
                            }
                        }
                    } else {
                        NPC t = (NPC) target;
                        int i = 0;
                        damage = Utils.random(80, 130);
                        if (Utils.inCircle(finalTarget, vineTile, t.getSize()))
                            finalTarget.applyHit(new Hit(player, damage, HitLook.MELEE_DAMAGE));
                        for (NPC npcs : World.getNPCs()) {
                            if (npcs != null && npcs != finalTarget && Utils.inCircle(npcs, vineTile, npcs.getSize())
                                    && t.getDefinitions().hasAttackOption()) {
                                if (i > 2)
                                    continue;
                                damage = Utils.random(80, 130);
                                i++;
                                if (i > 0) {
                                    addMeleeCombatXp(player, damage, weaponId, attackStyle);
                                    npcs.applyHit(new Hit(player, damage, HitLook.MELEE_DAMAGE));
                                }
                            }
                        }
                    }
                } else {
                    damage = Utils.random(80, 130);
                    if (Utils.inCircle(target, vineTile, 1)) {
                        addMeleeCombatXp(player, damage, weaponId, attackStyle);
                        finalTarget.applyHit(new Hit(player, damage, HitLook.MELEE_DAMAGE));
                    }
                }

            }
        }, 4, 2);
    }

    public Entity getTarget() {
        return target;
    }

    private final void addMeleeCombatXp(Player player, int damage, int weaponId, int attackStyle) {
        double meleeXP = (damage * 0.4);
        if (player.toggles("ONEXPPERHIT", false)) {
            int xpStyle = player.getCombatDefinitions().getXpStyle(weaponId, attackStyle);
            if (xpStyle != CombatDefinitions.SHARED) {
                player.getSkills().addXpNoBonus(xpStyle,
                        (player.toggles("ONEXHITS", false) ? Math.round(damage) / 10 : damage));
            } else {
                player.getSkills().addXpNoBonus(Skills.STRENGTH,
                        ((player.toggles("ONEXHITS", false) ? Math.round(damage) / 10 : damage)));
            }
        } else {
            if (meleeXP > 0) {
                int xpStyle = player.getCombatDefinitions().getXpStyle(weaponId, attackStyle);
                if (xpStyle != CombatDefinitions.SHARED) {
                    player.getSkills().addXp(xpStyle, meleeXP);
                } else {
                    player.getSkills().addXp(Skills.ATTACK, (damage * 0.133));
                    player.getSkills().addXp(Skills.STRENGTH, (damage * 0.133));
                    player.getSkills().addXp(Skills.DEFENCE, (damage * 0.133));
                }
            }
            double hpXP = (0.133 * damage);
            if (hpXP > 0)
                player.getSkills().addXp(Skills.HITPOINTS, hpXP);
        }
    }
}