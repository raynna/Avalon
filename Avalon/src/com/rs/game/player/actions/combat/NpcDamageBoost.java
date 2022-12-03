package com.rs.game.player.actions.combat;

import com.rs.Settings;
import com.rs.game.Entity;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;


/**
 * @Author -Andreas
 * Date 2022-11-21
 */

public class NpcDamageBoost extends PlayerCombat{

    public NpcDamageBoost(Entity target) {
        super(target);
    }

    public enum Style {
        MELEE,
        RANGE,
        MAGIC,
        HYBRID
    }

    public enum Type {
        SLAYER, UNDEAD, REGULAR
    }

    public enum BoostEquipment {
        FULL_SLAYER_HELMET(15492, Style.HYBRID, Type.SLAYER, .20),

        SLAYER_HELMET(13263, Style.MELEE, Type.SLAYER, .20),

        SALVE_AMULET_E(10588, Style.HYBRID, Type.UNDEAD, .20),

        BLACK_MASK(8921, Style.MELEE, Type.SLAYER, .15),

        HEXCREST(15488, Style.MAGIC, Type.SLAYER, .15),

        FOCUSSIGHT(15490, Style.RANGE, Type.SLAYER, .15),

        SALVE_AMULET(4081, Style.MELEE, Type.UNDEAD, .15);

        private final int itemId;
        private final Style style;
        private final Type type;
        private final double boost;


        BoostEquipment(int itemId, Style style, Type type, double boost) {
            this.itemId = itemId;
            this.style = style;
            this.type = type;
            this.boost = boost;
        }

        public int getItemId() {
            return itemId;
        }

        public Style getStyle() {
            return style;
        }

        public double getBoost() {
            return boost;
        }

        public Type getType() {
            return type;
        }

        public static BoostEquipment getBoost(int i) {
            for (BoostEquipment s : BoostEquipment.values()) {
                if (s.itemId == i)
                    return s;
            }
            return null;
        }
    }

    public static double getMultiplier(Player player, NPC target, Style style) {
        boolean undead = false;
        boolean maxHitDummy = ((NPC) target).getId() == 4474;
        double multiplier = 1;
        BoostEquipment equippedItem = null;
        for (BoostEquipment items : BoostEquipment.values()) {
            if (items == null)
                continue;
        for (Item equipment : player.getEquipment().getItems().getItemsCopy()) {
            if (equipment == null)
                continue;
                if (equipment.getId() == items.getItemId()) {
                        equippedItem = items;
                        System.out.println("Equipped item is valid: " + items.name() + ", id: " + items.getItemId());
                        continue;
                }
            }
        }
        if (equippedItem == null)
            System.out.println("No valid item equipped.");
          BoostEquipment equipment = BoostEquipment.getBoost(equippedItem == null ? -1 : equippedItem.getItemId());
        if (equipment != null) {
                System.out.println("Has " + equipment.getStyle() + " equipment boost of " + equipment.getBoost() * 100 + "% against " + equipment.getType());
            }
        for (String string : Settings.UNDEAD_NPCS) {
            if (target.getDefinitions().getName().toLowerCase().contains(string)) {
                undead = true;
            }
        }
        if (equipment != null) {
            System.out.println("Currently using " + style + " style for item " + equipment.name() + " who has " + equipment.getStyle() + " type.");
            if (style == equipment.getStyle() || (equipment.getStyle() == Style.HYBRID)) {
                if (maxHitDummy) {
                    multiplier += equipment.getBoost();
                }
                if (equipment.getType() == Type.UNDEAD && undead) {
                    multiplier += equipment.getBoost();
                }
                if (equipment.getType() == Type.SLAYER && player.getSlayerTask() != null && player.getSlayerManager().isValidTask(target.getName())) {
                    multiplier += equipment.getBoost();
                }
            }
        }
        return multiplier;
    }
}