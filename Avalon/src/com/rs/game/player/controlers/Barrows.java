package com.rs.game.player.controlers;

import java.util.ArrayList;
import java.util.List;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.npc.others.BarrowsBrother;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public final class Barrows extends Controler {

    private BarrowsBrother target;

    private static enum Hills {
        AHRIM_HILL(new WorldTile(3564, 3287, 0), new WorldTile(3557, 9703, 3)),
        DHAROK_HILL(new WorldTile(3573, 3296, 0), new WorldTile(3556, 9718, 3)),
        GUTHAN_HILL(new WorldTile(3574, 3279, 0), new WorldTile(3534, 9704, 3)),
        KARIL_HILL(new WorldTile(3563, 3276, 0), new WorldTile(3546, 9684, 3)),
        TORAG_HILL(new WorldTile(3553, 3281, 0), new WorldTile(3568, 9683, 3)),
        VERAC_HILL(new WorldTile(3556, 3296, 0), new WorldTile(3578, 9706, 3));

        private WorldTile outBound;
        private WorldTile inside;

        private Hills(WorldTile outBound, WorldTile in) {
            this.outBound = outBound;
            inside = in;
        }
    }

    public static boolean digIntoGrave(final Player player) {
        for (Hills hill : Hills.values()) {
            if (player.getPlane() == hill.outBound.getPlane() && player.getX() >= hill.outBound.getX()
                    && player.getY() >= hill.outBound.getY() && player.getX() <= hill.outBound.getX() + 3
                    && player.getY() <= hill.outBound.getY() + 3) {
                player.useStairs(-1, hill.inside, 1, 2, "You've broken into a crypt.");
                player.getPackets().sendBlackOut(2);
                WorldTasksManager.schedule(new WorldTask() {
                    @Override
                    public void run() {
                        player.getControlerManager().startControler("Barrows");
                    }
                });
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canAttack(Entity target) {
        if (target instanceof BarrowsBrother && target != this.target) {
            player.getPackets().sendGameMessage("This isn't your target.");
            return false;
        }
        return true;
    }

    private void exit(WorldTile outside) {
        player.setNextWorldTile(outside);
        leave(false);
    }

    private void leave(boolean logout) {
        if (target != null) {
            target.finish();
            player.getPackets().sendGameMessage(
                    "We'll finish this later.......");
        }
        if (!logout) {
            for (int varBit : TUNNEL_CONFIG[getTunnelIndex()])
                player.getVarsManager().sendVarBit(varBit, 0);
            player.getVarsManager().sendVar(1270, 0);
            player.getPackets().sendBlackOut(0);
            if (player.getHiddenBrother() == -1)
                player.getPackets().sendStopCameraShake();
            else
                player.getInterfaceManager().closeOverlay(player.getInterfaceManager().hasRezizableScreen());
            removeControler();
        }
        if (player.getControlerManager().getControler() != null) {
            System.out.println(player.getControlerManager().getControler() != null ? "had to call this" : "didn't need to really..");
            player.getPackets().sendStopCameraShake();
            player.getInterfaceManager().
                    player.getInterfaceManager().closeOverlay(player.getInterfaceManager().hasRezizableScreen());
            player.getPackets().sendBlackOut(0);
        }
    }

    @Override
    public boolean sendDeath() {
        leave(false);
        return true;
    }

    @Override
    public void magicTeleported(int type) {
        player.getPackets().sendStopCameraShake();
        removeControler();
        leave(false);
    }

    public int getRandomBrother() {
        List<Integer> bros = new ArrayList<Integer>();
        for (int i = 0; i < Hills.values().length; i++) {
            if (player.getKilledBarrowBrothers()[i] || player.getHiddenBrother() == i)
                continue;
            bros.add(i);
        }
        if (bros.isEmpty())
            return -1;
        return bros.get(Utils.random(bros.size()));

    }


    private static final Item[] COMMON_REWARDS = {
            new Item(MobRewardNodeBuilder.MIND_RUNE, Utils.random(850)),
            new Item(MobRewardNodeBuilder.CHAOS_RUNE, Utils.random(850)),
            new Item(MobRewardNodeBuilder.DEATH_RUNE, Utils.random(600)),
            new Item(MobRewardNodeBuilder.BLOOD_RUNE, Utils.random(850)),
            new Item(MobRewardNodeBuilder.BOLT_RACK_4740, Utils.random(850))};

    private static final short[][] TUNNEL_CONFIG = {
            {470, 479, 482, 476, 474}, {479, 477, 478, 480, 472},
            {477, 471, 472, 476, 475, 478, 480, 477}};

    public void sendReward() {
        double percentage = 0;
        for (boolean died : player.getKilledBarrowBrothers()) {
            if (died)
                percentage += 2.5;
        }
        percentage += (player.getBarrowsKillCount() / 40d);
        if (percentage > 90)
            percentage = 90;
        if (percentage >= Math.random() * 95) {
            player.getInventory().addItem(405, 1);
            player.getPackets().sendGameMessage("You recieved a barrows casket.");
        }
        for (int i = 0; i < 10; i++)
            if (percentage >= Math.random() * 100)
                drop(COMMON_REWARDS[Utils.random(COMMON_REWARDS.length)]);
        drop(new Item(995, Utils.random(50307)));
        player.setBarrowsKillCount(0);
    }

    private void drop(Item item) {
        player.getInventory().addItemDrop(item.getId(), item.getAmount());

    }

    @Override
    public boolean processObjectClick1(WorldObject object) {
        if (object.getId() >= 6702 && object.getId() <= 6707) {
            WorldTile out = Hills.values()[object.getId() - 6702].outBound;
            exit(new WorldTile(out.getX() + 1, out.getY() + 1, out.getPlane()));
            return false;
        } else if (object.getId() == 10284) {
            if (player.getHiddenBrother() == -1) {
                player.getPackets().sendGameMessage("You found nothing.");
                return false;
            }
            if (!player.getKilledBarrowBrothers()[player.getHiddenBrother()])
                sendTarget(2025 + player.getHiddenBrother(), player);
            if (target != null) {
                player.getPackets().sendGameMessage("You found nothing.");
                return false;
            }
            if (!player.getInventory().hasFreeSlots()) {
                player.getPackets().sendGameMessage("You don't have any inventory space.");
                return false;
            }
            sendReward();
            player.getPackets().sendCameraShake(3, 12, 25, 12, 25);
            player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0);
            player.getPackets().sendSpawnedObject(new WorldObject(6775, 10, 0, 3551, 9695, 0));
            player.resetBarrows();
            return false;
        } else if (object.getId() >= 6716 && object.getId() <= 6749) {
            WorldTile walkTo;
            if (object.getRotation() == 0)
                walkTo = new WorldTile(object.getX() + 5, object.getY(), 0);
            else if (object.getRotation() == 1)
                walkTo = new WorldTile(object.getX(), object.getY() - 5, 0);
            else if (object.getRotation() == 2)
                walkTo = new WorldTile(object.getX() - 5, object.getY(), 0);
            else
                walkTo = new WorldTile(object.getX(), object.getY() + 5, 0);
            if (!World.isNotCliped(walkTo.getPlane(), walkTo.getX(), walkTo.getY(), 1))
                return false;
            player.addWalkSteps(walkTo.getX(), walkTo.getY(), -1, false);
            player.lock(6);
            if (player.getHiddenBrother() != -1) {
                int brother = getRandomBrother();
                if (brother != -1)
                    sendTarget(2025 + brother, walkTo);
            }
            return false;
        } else {
            int sarcoId = getSarcophagusId(object.getId());
            if (sarcoId != -1) {
                if (sarcoId == player.getHiddenBrother())
                    player.getDialogueManager().startDialogue("BarrowsD");
                else if (target != null || player.getKilledBarrowBrothers()[sarcoId])
                    player.getPackets().sendGameMessage("You found nothing.");
                else
                    sendTarget(2025 + sarcoId, player);
                return false;
            }
        }
        return true;
    }

    ;

    public int getSarcophagusId(int objectId) {
        switch (objectId) {
            case 66017:
                return 0;
            case 63177:
                return 1;
            case 66020:
                return 2;
            case 66018:
                return 3;
            case 66019:
                return 4;
            case 66016:
                return 5;
            default:
                return -1;
        }
    }

    public void targetDied() {
        player.getHintIconsManager().removeUnsavedHintIcon();
        setBrotherSlained(target.getId() >= 14297 ? 6 : target
                .getId() - 2025);
        target = null;

    }

    public void targetFinishedWithoutDie() {
        player.getHintIconsManager().removeUnsavedHintIcon();
        target = null;
    }

    public void setBrotherSlained(int index) {
        player.getKilledBarrowBrothers()[index] = true;
        sendBrotherSlain(index, true);
    }

    public void sendTarget(int id, WorldTile tile) {
        if (target != null)
            target.disapear();
        target = new BarrowsBrother(id, tile, this);
        target.setTarget(player);
        target.setNextForceTalk(new ForceTalk("You dare disturb my rest!"));
        player.getHintIconsManager().addHintIcon(target, 1, -1, false);
    }

    public Barrows() {

    }

    public int getAndIncreaseHeadIndex() {
        Integer head = (Integer) player.temporaryAttribute().remove("BarrowsHead");
        if (head == null || head == player.getKilledBarrowBrothers().length - 1)
            head = 0;
        player.temporaryAttribute().put("BarrowsHead", head + 1);
        return player.getKilledBarrowBrothers()[head] ? head : -1;
    }

    private static final int[] CRYPT_NPCS = {1243, 1244, 1245, 1246, 1247,
            1618, 2031, 2032, 2033, 2034, 2035, 2036, 2037, 4920, 4921, 5381,
            5422, 7637};

    @Override
    public void processNPCDeath(NPC npc) {
        NPCDefinitions npcDef = new NPCDefinitions(npc.getId());
        for (int crypt_npc : CRYPT_NPCS) {
            if (crypt_npc == npcDef.getId() || npc.getName() == npcDef.getName()) {
                creatureCount++;
                sendCreaturesSlainCount(creatureCount + 1);
            }
        }
    }

    private int headComponentId;
    private int timer;

    @Override
    public void process() {
        if (timer > 0) {
            timer--;
            if (timer == 0) {
                resetHeadTimer();
            }
            return;
        }
        if (headComponentId == 0) {
            if (player.getHiddenBrother() == -1) {
                player.applyHit(new Hit(player, Utils.random(50) + 1, HitLook.REGULAR_DAMAGE));
                resetHeadTimer();
                return;
            }
            int headIndex = getAndIncreaseHeadIndex();
            if (headIndex == -1) {
                resetHeadTimer();
                return;
            }
            player.getPackets().sendGlobalConfig(1043, 0);
            headComponentId = 9 + Utils.random(10);
            player.getPackets().sendGlobalConfig(1043, 4761 + headIndex);
//			player.getPackets().sendItemOnIComponent(24, headComponentId, 4761 + headIndex, 0);
//			player.getPackets().sendIComponentAnimation(9810, 24, headComponentId);
            int activeLevel = player.getPrayer().getPrayerpoints();
            if (activeLevel > 0) {
                int level = player.getSkills().getLevelForXp(Skills.PRAYER) * 10;
                player.getPrayer().drainPrayer(level / 6);
            }
            timer = 3;
        } else {
            player.getPackets().sendGlobalConfig(1043, 0);
            player.getPackets().sendItemOnIComponent(24, headComponentId, -1, 0);
            headComponentId = 0;
            resetHeadTimer();
        }
    }

    public void resetHeadTimer() {
        timer = 25 + Utils.random(6);
    }

    @Override
    public void sendInterfaces() {
        if (player.getHiddenBrother() != -1)
            player.getInterfaceManager().sendOverlay(24, player.getInterfaceManager().hasRezizableScreen());
    }

    private int creatureCount;

    public void loadData() {
        resetHeadTimer();
        if (getArguments().length == 2)
            creatureCount = (int) getArguments()[1];
        for (int i = 0; i < player.getKilledBarrowBrothers().length; i++)
            sendBrotherSlain(i, player.getKilledBarrowBrothers()[i]);
        sendCreaturesSlainCount(player.getBarrowsKillCount());
        player.getPackets().sendBlackOut(2);
        for (int varBit : TUNNEL_CONFIG[getTunnelIndex()])
            player.getVarsManager().sendVarBit(varBit, 1);
        player.getVarsManager().sendVarBit(467, 1);
    }

    public void sendBrotherSlain(int index, boolean slain) {
        player.getPackets().sendConfigByFile(457 + index, slain ? 1 : 0);
    }

    public void sendCreaturesSlainCount(int count) {
        player.getPackets().sendIComponentText(24, 6, Utils.format(count));
        player.getPackets().sendConfigByFile(464, count);
    }

    @Override
    public void start() {
        if (player.getHiddenBrother() == -1 || player.getHiddenBrother() == 6)
            player.setHiddenBrother(Utils.random(6));
        setArguments(new Object[]{Utils.random(TUNNEL_CONFIG.length), 0});
        loadData();
        sendInterfaces();
    }

    @Override
    public boolean login() {
        player.getControlerManager().startControler("Barrows");
        if (player.getHiddenBrother() == -1)
            player.getPackets().sendCameraShake(3, 25, 50, 25, 50);
        loadData();
        sendInterfaces();
        return false;
    }

    private int getTunnelIndex() {
        if (getArguments() == null || getArguments().length == 0)
            return 0;
        return (int) this.getArguments()[0];
    }

    @Override
    public boolean logout() {
        this.setArguments(new Object[]{getTunnelIndex(), creatureCount});
        leave(true);
        return false;
    }

    @Override
    public void forceClose() {
        leave(true);
    }

}
