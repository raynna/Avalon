package com.rs.game.player.controlers;

import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.RouteEvent;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.hunter.Hunter;
import com.rs.game.player.actions.skills.hunter.Hunter.DynamicFormula;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class Falconry extends Controler {

    public int[] xp = {103, 132, 156};
    public int[] furRewards = {10125, 10115, 10127};
    public int[] levels = {43, 57, 69};

    public static void beginFalconry(Player player) {
        if ((player.getEquipment().getItem(3) != null && player.getEquipment().getItem(3).getId() == -1)
                || (player.getEquipment().getItem(5) != null && player.getEquipment().getItem(5).getId() == -1)) {
            player.getDialogueManager().startDialogue("SimpleMessage", "You need both hands free to use a falcon.");
            return;
        } else if (player.getSkills().getLevel(Skills.HUNTER) < 43) {
            player.getDialogueManager().startDialogue("SimpleMessage",
                    "You need a Hunter level of at least 43 to use a falcon, come back later.");
            return;
        }
        player.getControlerManager().startControler("Falconry");
    }

    @Override
    public void start() {
        player.animate(new Animation(1560));
        WorldTasksManager.schedule(new WorldTask() {
            @Override
            public void run() {
                player.setNextWorldTile(new WorldTile(2371, 3620, 0));
            }
        });
        player.getEquipment().getItems().set(3, new Item(10024, 1));
        player.getEquipment().refresh(3);
        player.getAppearence().generateAppearenceData();
        player.getDialogueManager().startDialogue("SimpleMessage", "Simply click on the target and try your luck.");
    }

    @Override
    public boolean canEquip(int slotId, int itemId) {
        return true;
    }

    @Override
    public void magicTeleported(int type) {
        forceClose();
    }

    @Override
    public void forceClose() {
        if (player.getInventory().containsOneItem(10024)) {
            player.getInventory().deleteItem(10024, 1);
        }
        if (player.getEquipment().getWeaponId() == 10024) {
            player.getEquipment().getItems().set(3, new Item(-1, 1));
            player.getEquipment().refresh(3);
            player.getAppearence().generateAppearenceData();
        }
    }

    @Override
    public boolean processNPCClick1(final NPC npc) {
        player.setNextFaceEntity(npc);
        if (npc.getDefinitions().name.toLowerCase().contains("kebbit")) {
            if (player.temporaryAttribute().get("falconReleased") != null) {
                player.getDialogueManager().startDialogue("SimpleMessage",
                        "You cannot catch a kebbit without your falcon.");
                return false;
            }
            int level = levels[(npc.getId() - 5098)];
            if (proccessFalconAttack(npc)) {
                if (player.getSkills().getLevel(Skills.HUNTER) < level) {
                    player.getDialogueManager().startDialogue("SimpleMessage",
                            "You need a Hunter level of " + level + " to capture this kebbit.");
                    return true;
                } else if (Hunter.isSuccessful(player, level, new DynamicFormula() {
                    @Override
                    public int getExtraProbablity(Player player) {
                        if (player.getEquipment().getGlovesId() == 10075)
                            return 3;
                        return 1;
                    }
                })) {
                    player.getEquipment().getItems().set(3, new Item(10023, 1));
                    player.getEquipment().refresh(3);
                    player.getAppearence().generateAppearenceData();
                    player.temporaryAttribute().put("falconReleased", true);
                    WorldTasksManager.schedule(new WorldTask() {
                        @Override
                        public void run() {
                            World.sendProjectile(player, npc, 918, 41, 16, 31, 35, 16, 0);
                            WorldTasksManager.schedule(new WorldTask() {
                                @Override
                                public void run() {
                                    npc.transformIntoNPC(npc.getId() - 4);
                                    player.temporaryAttribute().put("ownedFalcon", npc);
                                    player.getPackets().sendGameMessage(
                                            "The falcon successfully swoops down and captures the kebbit.");
                                    npc.setRandomWalk(0);
                                    player.getHintIconsManager().addHintIcon(npc, 1, -1, false);
                                }
                            }, Utils.getDistance(player, npc) > 4 ? 3 : Utils.getDistance(player, npc) > 2 ? 2 : 1);
                        }
                    });
                } else {
                    player.getEquipment().getItems().set(3, new Item(10023, 1));
                    player.getEquipment().refresh(3);
                    player.getAppearence().generateAppearenceData();
                    player.temporaryAttribute().put("falconReleased", true);
                    WorldTasksManager.schedule(new WorldTask() {
                        @Override
                        public void run() {
                            World.sendProjectile(player, npc, 918, 41, 16, 31, 35, 16, 0);
                            WorldTasksManager.schedule(new WorldTask() {
                                @Override
                                public void run() {
                                    World.sendProjectile(npc, player, 918, 41, 16, 31, 35, 16, 0);
                                    WorldTasksManager.schedule(new WorldTask() {
                                        @Override
                                        public void run() {
                                            player.getEquipment().getItems().set(3, new Item(10024, 1));
                                            player.getEquipment().refresh(3);
                                            player.getAppearence().generateAppearenceData();
                                            player.temporaryAttribute().remove("falconReleased");
                                            player.getPackets().sendGameMessage(
                                                    "The falcon swoops down on the kebbit, but just barely misses catching it.");
                                        }
                                    });
                                }
                            }, Utils.getDistance(player, npc) > 4 ? 3 : Utils.getDistance(player, npc) > 2 ? 2 : 1);
                        }
                    });
                }
            }
        } else if (npc.getDefinitions().name.toLowerCase().contains("gyr falcon")) {
            player.setRouteEvent(new RouteEvent(npc, new Runnable() {
                @Override
                public void run() {
                    NPC kill = (NPC) player.temporaryAttribute().get("ownedFalcon");
                    if (kill == null)
                        return;
                    if (kill != npc) {
                        player.getDialogueManager().startDialogue("SimpleMessage", "This isn't your kill!");
                        return;
                    }
                    npc.setRespawnTask();
                    player.getInventory().addItem(new Item(furRewards[(npc.getId() - 5094)], 1));
                    player.getInventory().addItem(new Item(526, 1));
                    player.getSkills().addXp(Skills.HUNTER, xp[(npc.getId() - 5094)]);
                    player.getPackets().sendGameMessage("You retreive the falcon as well as the fur of the dead kebbit.");
                    player.getHintIconsManager().removeUnsavedHintIcon();
                    player.getEquipment().getItems().set(3, new Item(10024, 1));
                    player.getEquipment().refresh(3);
                    player.getAppearence().generateAppearenceData();
                    player.temporaryAttribute().remove("ownedFalcon");
                    player.temporaryAttribute().remove("falconReleased");
                    npc.transformIntoNPC(npc.getId() + 4);
                }
            }, true));
            return true;
        }
        return true;
    }

    private boolean proccessFalconAttack(NPC target) {
        int distanceX = player.getX() - target.getX();
        int distanceY = player.getY() - target.getY();
        int size = player.getSize();
        int maxDistance = 16;
        player.resetWalkSteps();
        if ((!player.clipedProjectile(target, maxDistance == 0)) || distanceX > size + maxDistance
                || distanceX < -1 - maxDistance || distanceY > size + maxDistance || distanceY < -1 - maxDistance) {
            if (!player.addWalkStep(target.getX(), target.getY(), 2, size, true))
                return true;
        }
        return true;
    }

    @Override
    public boolean login() {
        start();
        return true;
    }

    @Override
    public boolean logout() {
        return true;
    }
}
