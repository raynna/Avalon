package com.rs.game.player;

public class VariableKeys {

    public enum IntKey {
        KILLCOUNT(1, 0),
        DEATHCOUNT(2, 0),
        KILLSTREAK(3, 0),
        KILLSTREAK_RECORD(4, 0), PK_POINTS(5, 0), EP(6, 0),

        HIGHEST_ATTACK_LEVEL(20, 1),
        HIGHEST_STRENGTH_LEVEL(21, 1),
        HIGHEST_DEFENCE_LEVEL(22, 1),
        HIGHEST_RANGED_LEVEL(23, 1),
        HIGHEST_PRAYER_LEVEL(24, 1),
        HIGHEST_MAGIC_LEVEL(25, 1),

        HIGHEST_HITPOINTS_LEVEL(20, 1), COAL_STORED(7, 0), OVERLOAD_EFFECT(8, 0), PRAYER_RENEWAL_EFFECT(9, 0), VENGEANCE(10, 0), DISRUPTION_SHIELD(11, 0), TELEPORT_BLOCK(12, 0), FREEZE_DELAY(13, 0), FREEZE_IMMUNITY(14, 0), TELEPORT_BLOCK_IMMUNITY(15, 0);
        private final int uid;
        private final int defaultValue;

        IntKey(int uid, int defaultValue) {
            this.uid = uid;
            this.defaultValue = defaultValue;
        }

        public int getUID() {
            return uid;
        }

        public int getDefaultValue() {
            return defaultValue;
        }
    }

    public enum BooleanKey {

        BANK_PIN(1, false),

        TALKED_TO_MR_EX(2, false), TALKED_TO_MARV(3, false), TALKED_TO_GILES(4, false), TALKED_TO_ESTOCADA(5, false), DISRUPTION_ACTIVE(6, false), VENGEANCE_ACTIVE(7, false);
        private final int uid;
        private final boolean defaultValue;

        BooleanKey(int uid, boolean defaultValue) {
            this.uid = uid;
            this.defaultValue = defaultValue;
        }

        public int getUID() {
            return uid;
        }

        public boolean getDefaultValue() {
            return defaultValue;
        }
    }

    public enum LongKey {
        HIGHEST_VALUE_DROP(11, 0);
        private final int uid;
        private final long defaultValue;

        LongKey(int uid, long defaultValue) {
            this.uid = uid;
            this.defaultValue = defaultValue;
        }

        public int getUID() {
            return uid;
        }

        public long getDefaultValue() {
            return defaultValue;
        }
    }

    public enum StringKey {

        EXAMPLE_STRING(1, ""),

        ;
        private final int uid;
        private final String defaultValue;

        StringKey(int uid, String defaultValue) {
            this.uid = uid;
            this.defaultValue = defaultValue;
        }

        public int getUID() {
            return uid;
        }

        public String getDefaultValue() {
            return defaultValue;
        }
    }
}
