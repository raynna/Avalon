package com.rs.game.npc.combat;

import com.rs.game.*;
import com.rs.game.minigames.godwars.zaros.Nex;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.fightcaves.FightCavesNPC;
import com.rs.game.npc.fightkiln.HarAkenTentacle;
import com.rs.game.npc.pest.PestPortal;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Combat;
import com.rs.utils.Logger;
import com.rs.utils.MapAreas;
import com.rs.utils.Utils;

/**
 * @Improved Andreas - AvalonPK
 */

public final class NPCCombat {

    private NPC npc;
    private int combatDelay;
    private Entity target;

    public NPCCombat(NPC npc) {
        this.npc = npc;
    }

    public int getCombatDelay() {
        return combatDelay;
    }

    /*
     * returns if under combat
     */
    public boolean process() {
        if (combatDelay > 0)
            combatDelay--;
        if (target != null) {
            if (!checkAll()) {
                removeTarget();
                return false;
            }
            if (combatDelay <= 0)
                combatDelay = combatAttack();
            return true;
        }
        return false;
    }

    private boolean walking;

    public boolean isUnderTarget(Entity target, NPC npc) {
        if (Utils.colides(npc.getX(), npc.getY(), npc.getSize(), target.getX(), target.getY(), target.getSize())) {
            int x = target.getX() + target.getSize();
            int y = npc.getY();
            if (!npc.addWalkSteps(x, y)) {
                npc.resetWalkSteps();
                x = target.getX() - npc.getSize();
                y = npc.getY();
                if (!npc.addWalkSteps(x, y)) {
                    npc.resetWalkSteps();
                    x = npc.getX();
                    y = target.getY() - target.getSize();
                    if (!npc.addWalkSteps(x, y)) {
                        npc.resetWalkSteps();
                        x = npc.getX();
                        y = target.getY() - npc.getSize();
                        if (!npc.addWalkSteps(x, y)) {
                            npc.resetWalkSteps();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /*
     * return combatDelay
     */
    private int combatAttack() {
        Entity target = this.target;
        if (target == null) {
            return 0;
        }
        if (npc.getId() == 4474 || npc.getId() == 7891 || npc.isDead() || npc.hasFinished() || target.isDead()
                || target.hasFinished() || npc.getPlane() != target.getPlane())
            return 0;
        NPCCombatDefinitions defs = npc.getCombatDefinitions();
        int attackStyle = defs.getAttackStyle();
        if (target instanceof Familiar) {
            Familiar familiar = (Familiar) target;
            Player player = familiar.getOwner();
            if (player != null) {
                target = player;
                npc.setTarget(target);
            }
            if (target == familiar.getOwner()) {
                npc.setTarget(target);
            }
        }
        int maxDistance = attackStyle == NPCCombatDefinitions.MELEE || attackStyle == NPCCombatDefinitions.SPECIAL2 ? 0
                : npc instanceof HarAkenTentacle ? 12
                : npc instanceof FightCavesNPC && attackStyle == NPCCombatDefinitions.SPECIAL ? 14 : 7;
        if (target.hasWalkSteps())
            maxDistance += 1;
        if ((!(npc instanceof Nex))
                && !npc.clipedProjectile(target, maxDistance == 0 && !forceCheckClipAsRange(target))) {
            return 0;
        }
        int size = npc.getSize();
        int distanceX = target.getX() - npc.getX();
        int distanceY = target.getY() - npc.getY();
        if (distanceX > size + maxDistance || distanceX < -1 - maxDistance || distanceY > size + maxDistance
                || distanceY < -1 - maxDistance) {
            return 0;
        }
        return CombatScriptsHandler.specialAttack(npc, target);
    }

    public boolean checkAll() {
        Entity target = this.target; // prevents multithread issues
        if (target == null)
            return false;
        if (npc.isDead() || npc.hasFinished() || (npc.isForceWalking() && npc.isCantFollowUnderCombat()) || target.isDead() || target.hasFinished()
                || npc.getPlane() != target.getPlane())
            return false;
        if (npc instanceof Familiar && target instanceof NPC && ((NPC) target).isCantInteract())
            return false;
        int distanceX = npc.getX() - npc.getRespawnTile().getX();
        int distanceY = npc.getY() - npc.getRespawnTile().getY();
        int size = npc.getSize();
        int maxDistance;
        int agroRatio = npc.getForceTargetDistance() > 0 ? npc.getForceTargetDistance() : 8;
        if (npc.hasForceWalk() && npc.getAttackedByDelay() > Utils.currentTimeMillis())
            npc.setForceWalk(null);
        if (npc.hasForceWalk() && npc.getAttackedByDelay() < Utils.currentTimeMillis())
            return false;
        if (!npc.isNoDistanceCheck() && !npc.isCantFollowUnderCombat()) {
            maxDistance = agroRatio > 8 ? agroRatio : 8; // before 32, but its too much
            if (!(npc instanceof Familiar)) {
                if (npc.getMapAreaNameHash() != -1) {
                    // if out his area
                    if (!MapAreas.isAtArea(npc.getMapAreaNameHash(), npc) || (!npc.canBeAttackFromOutOfArea()
                            && !MapAreas.isAtArea(npc.getMapAreaNameHash(), target))) {
                        combatDelay = 1;
                        npc.forceWalkRespawnTile();
                        return true;
                    }
                } else if (distanceX > size + maxDistance || distanceX < -1 - maxDistance
                        || distanceY > size + maxDistance || distanceY < -1 - maxDistance) {
                    // if more than 32 distance from respawn place
                    npc.forceWalkRespawnTile();
                    combatDelay = 1;
                    return true;
                }
            }
            maxDistance = agroRatio > 16 ? agroRatio : 16;
            distanceX = target.getX() - npc.getX();
            distanceY = target.getY() - npc.getY();
            if (distanceX > size + maxDistance || distanceX < -1 - maxDistance || distanceY > size + maxDistance
                    || distanceY < -1 - maxDistance) {
                return false; // if target distance higher 16
            }
        } else {
            distanceX = target.getX() - npc.getX();
            distanceY = target.getY() - npc.getY();
        }
        // checks for no multi area :)
        if (npc instanceof Familiar) {
            Familiar familiar = (Familiar) npc;
            if (!familiar.canAttack(target)) {
                return false;
            }
        } else {
            if (!npc.isForceMultiAttacked()) {
                if (!target.isAtMultiArea() || !npc.isAtMultiArea()) {
                    if (npc.getAttackedBy() != target && npc.getAttackedByDelay() > Utils.currentTimeMillis()) {
                        return false;
                    }
                    if (target.getAttackedBy() != npc && target.getAttackedByDelay() > Utils.currentTimeMillis()) {
                        return false;
                    }
                }
            }
        }
        if (!npc.isCantFollowUnderCombat()) {
            // if is under
            int targetSize = target.getSize();
            /*
             * if (distanceX < size && distanceX > -targetSize && distanceY < size &&
             * distanceY > -targetSize && !target.hasWalkSteps()) {
             */
            if (Utils.colides(npc.getX(), npc.getY(), size, target.getX(), target.getY(), target.getSize())
                    && !target.hasWalkSteps()) {
                if (npc.getFreezeDelay() >= Utils.currentTimeMillis()) {
                    combatDelay = 1;
                    return true;
                }
                npc.resetWalkSteps();
                if (!npc.addWalkStepsInteract(target.getX() + target.getSize(), npc.getY(), 1, npc.getSize(), true)) {
                    npc.resetWalkSteps();
                    if (!npc.addWalkStepsInteract(target.getX() - size, npc.getY(), 1, npc.getSize(), true)) {
                        npc.resetWalkSteps();
                        if (!npc.addWalkStepsInteract(npc.getX(), target.getY() + target.getSize(), 1, npc.getSize(), true)) {
                            npc.resetWalkSteps();
                            if (!npc.addWalkStepsInteract(npc.getX(), target.getY() - size, 1, npc.getSize(), true)) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            } else if (npc.getCombatDefinitions().getAttackStyle() == NPCCombatDefinitions.MELEE && targetSize == 1
                    && Math.abs(npc.getX() - target.getX()) == 1 && Math.abs(npc.getY() - target.getY()) == 1
                    && !target.hasWalkSteps() && npc.getSize() == 1) {
                if (!npc.addWalkSteps(target.getX(), npc.getY(), 1))
                    npc.addWalkSteps(npc.getX(), target.getY(), 1);
                return true;
            }

            int attackStyle = npc.getCombatDefinitions().getAttackStyle();
            if (npc instanceof Nex) {
                Nex nex = (Nex) npc;
                maxDistance = nex.isForceFollowClose() ? 0 : 7;
                if ((!npc.clipedProjectile(target, maxDistance == 0 && !forceCheckClipAsRange(target)))
                        || !Utils.isOnRange(npc.getX(), npc.getY(), size, target.getX(), target.getY(), targetSize,
                        maxDistance)) {
                    npc.resetWalkSteps();
                    if (!Utils.isOnRange(npc.getX(), npc.getY(), size, target.getX(), target.getY(), targetSize, 10)) {
                        int[][] dirs = Utils.getCoordOffsetsNear(size);
                        for (int dir = 0; dir < dirs[0].length; dir++) {
                            final WorldTile tile = new WorldTile(new WorldTile(target.getX() + dirs[0][dir],
                                    target.getY() + dirs[1][dir], target.getPlane()));
                            if (World.isTileFree(tile.getPlane(), tile.getX(), tile.getY(), size)) { // if
                                // found
                                // done
                                npc.setNextForceMovement(new ForceMovement(new WorldTile(npc), 0, tile, 1,
                                        Utils.getMoveDirection(tile.getX() - npc.getX(), tile.getY() - npc.getY())));
                                npc.animate(new Animation(17408));
                                npc.setNextWorldTile(tile);
                                return true;
                            }
                        }
                    } else
                        npc.calcFollow(target, 2, true, npc.isIntelligentRouteFinder());
                    return true;
                } else
                    // if doesnt need to move more stop moving
                    npc.resetWalkSteps();
            } else {
                // MAGE_FOLLOW and RANGE_FOLLOW follow close but can attack far
                // unlike melee
                maxDistance = npc.isForceFollowClose() ? 0
                        : (attackStyle == NPCCombatDefinitions.MELEE || attackStyle == NPCCombatDefinitions.SPECIAL2)
                        ? 0
                        : 7;
                npc.resetWalkSteps();
                // is far from target, moves to it till can attack
                if ((!npc.clipedProjectile(target, maxDistance == 0 && !forceCheckClipAsRange(target)))
                        || !Utils.isOnRange(npc.getX(), npc.getY(), size, target.getX(), target.getY(),
                        target.getSize(), maxDistance)) {
                    if (npc.isIntelligentRouteFinder()) {
                        if (!npc.calcFollow(target, npc.getRun() ? 2 : 1, true, npc.isIntelligentRouteFinder())) {
                            return true;
                        }
                    } else {
                        if (!npc.addWalkStepsInteract(target.getX(), target.getY(), npc.getRun() ? 2 : 1, size, true)) {
                            return true;
                        }
                    }
                } else {
                    if (npc.getAttackDelay() < Utils.currentTimeMillis() || npc.getAttackDelay() == 0)// set flinch if haven't attacked in a while
                        flinch();
                    if (npc.getFlinchDelay() > Utils.currentTimeMillis())// dont attack if flinch active
                        combatDelay = 1;
                }
            }
        }
        return true;
    }

    protected void doDefenceEmote(Entity target) {
        target.setNextAnimationNoPriority(new Animation(Combat.getDefenceEmote(target)), target);
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
        npc.setNextFaceEntity(target);
        if (!checkAll()) {
            removeTarget();
            return;
        }
    }

    public void flinch() {
        if (npc.getFlinchDelay() > Utils.currentTimeMillis())
            return;
        int attackSpeed = npc.getAttackSpeed() * 600;
        npc.setFlinch((attackSpeed / 2) - 1000);
        npc.setAttackDelay((attackSpeed / 2) + 4800);
    }

    private boolean forceCheckClipAsRange(Entity target) {
        return target instanceof PestPortal;
    }

    public void addCombatDelay(int delay) {
        combatDelay += delay;
    }

    public void setCombatDelay(int delay) {
        combatDelay = delay;
    }

    public boolean underCombat() {
        return target != null;
    }

    public void removeTarget() {
        this.target = null;
        npc.setNextFaceEntity(null);
    }

    public void reset() {
        combatDelay = 0;
        target = null;
    }

}
