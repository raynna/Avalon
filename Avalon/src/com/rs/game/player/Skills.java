package com.rs.game.player;

import java.io.Serializable;

import com.rs.Launcher;
import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.player.dialogues.LevelUp;
import com.rs.utils.HexColours;
import com.rs.utils.Utils;

public final class Skills implements Serializable {

    private static final long serialVersionUID = -7086829989489745985L;

    public static final double MAXIMUM_EXP = 200000000;

    public enum SkillData {
        ATTACK(0, 1, 1469, "Attack"),

        DEFENCE(1, 5, 1471, "Defence"),

        STRENGTH(2, 2, 1470, "Strength"),

        HITPOINTS(3, 6, 1475, "Constitution"),

        RANGE(4, 3, 1472,"Ranging"),

        PRAYER(5, 7, 1473,"Prayer"),

        MAGIC(6, 4, 1474,"Magic"),

        COOKING(7, 16, 1484, "Cooking"),

        WOODCUTTING(8, 18, 1486, "Woodcutting"),

        FLETCHING(9, 19, 1480, "Fletching"),

        FISHING(10, 15, 1483, "Fishing"),

        FIREMAKING(11, 17, 1485, "Firemaking"),

        CRAFTING(12, 11, 1479,"Crafting"),

        SMITHING(13, 14, 1482,"Smithing"),

        MINING(14, 13, 1481,"Mining"),

        HERBLORE(15, 9, 1477,"Herblore"),

        AGILITY(16, 8, 1476,"Agility"),

        THIEVING(17, 10, 1478,"Thieving"),

        SLAYER(18, 20, 1488,"Slayer"),

        FARMING(19, 21, 1489,"Farming"),

        RUNECRAFTING(20, 12, 1487,"Runecrafting"),

        HUNTER(21, 23, 1491,"Hunter"),

        CONSTRUCTION(22, 22, 1490,"Construction"),

        SUMMONING(23, 24, 1492,"Summoning"),

        DUNGEONEERING(24, 25, 1493,"Dungeoneering");;

        private int id, configValue, levelGainedConfig;
        private String name;

        private SkillData(int id, int configValue, int levelGainedConfig, String name) {
            this.id = id;
            this.configValue = configValue;
            this.levelGainedConfig = levelGainedConfig;
            this.name = name;
        }

        public static SkillData forId(int id) {
            for (SkillData data : SkillData.values()) {
                if (data.getId() == id) {
                    return data;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public int getValue() {
            return configValue;
        }

        public int getLevelGainedConfig() { return levelGainedConfig; }
    }

    //id
    public static final int ATTACK = 0, DEFENCE = 1, STRENGTH = 2, HITPOINTS = 3, RANGE = 4, PRAYER = 5, MAGIC = 6,
            COOKING = 7, WOODCUTTING = 8, FLETCHING = 9, FISHING = 10, FIREMAKING = 11, CRAFTING = 12, SMITHING = 13,
            MINING = 14, HERBLORE = 15, AGILITY = 16, THIEVING = 17, SLAYER = 18, FARMING = 19, RUNECRAFTING = 20,
            CONSTRUCTION = 22, HUNTER = 21, SUMMONING = 23, DUNGEONEERING = 24;

    public static final String[] SKILL_NAME = {"Attack", "Defence", "Strength", "Constitution", "Ranged", "Prayer",
            "Magic", "Cooking", "Woodcutting", "Fletching", "Fishing", "Firemaking", "Crafting", "Smithing", "Mining",
            "Herblore", "Agility", "Thieving", "Slayer", "Farming", "Runecrafting", "Hunter", "Construction",
            "Summoning", "Dungeoneering"};

    public short level[];
    private double xp[];
    private double[] xpTracks;
    private boolean[] trackSkills;
    private byte[] trackSkillsIds;
    private boolean[] enabledSkillsTargets;
    private boolean[] skillsTargetsUsingLevelMode;
    private int[] skillsTargetsValues;
    private boolean xpDisplay, xpPopup;

    private transient int currentCounter;
    private transient Player player;

    public void passLevels(Player p) {
        this.level = p.getSkills().level;
        this.xp = p.getSkills().xp;
    }

    public Skills() {
        level = new short[25];
        xp = new double[25];
        for (int i = 0; i < level.length; i++) {
            level[i] = 1;
            xp[i] = 0;
        }
        level[3] = 10;
        xp[3] = 1184;
        level[HERBLORE] = 3;
        xp[HERBLORE] = 250;
        xpPopup = true;
        xpTracks = new double[3];
        trackSkills = new boolean[3];
        trackSkillsIds = new byte[3];
        trackSkills[0] = true;
        for (int i = 0; i < trackSkillsIds.length; i++)
            trackSkillsIds[i] = 30;
        enabledSkillsTargets = new boolean[25];
        skillsTargetsUsingLevelMode = new boolean[25];
        skillsTargetsValues = new int[25];

    }

    public void sendXPDisplay() {
        for (int i = 0; i < trackSkills.length; i++) {
            player.getVarsManager().sendVarBit(10444 + i, trackSkills[i] ? 1 : 0);
            player.getVarsManager().sendVarBit(10440 + i, trackSkillsIds[i] + 1);
            refreshCounterXp(i);
        }
    }

    public void setupXPCounter() {
        player.getInterfaceManager().sendXPDisplay(1214);
    }

    public void refreshCurrentCounter() {
        player.getVarsManager().sendVar(2478, currentCounter + 1);
    }

    public void setCurrentCounter(int counter) {
        if (counter != currentCounter) {
            currentCounter = counter;
            refreshCurrentCounter();
        }
    }

    public void switchTrackCounter() {
        trackSkills[currentCounter] = !trackSkills[currentCounter];
        player.getVarsManager().sendVarBit(10444 + currentCounter, trackSkills[currentCounter] ? 1 : 0);
    }

    public void resetCounterXP() {
        xpTracks[currentCounter] = 0;
        refreshCounterXp(currentCounter);
    }

    public void setCounterSkill(int skill) {
        xpTracks[currentCounter] = 0;
        trackSkillsIds[currentCounter] = (byte) skill;
        player.getVarsManager().sendVarBit(10440 + currentCounter, trackSkillsIds[currentCounter] + 1);
        refreshCounterXp(currentCounter);
    }

    public void refreshCounterXp(int counter) {
        player.getVarsManager().sendVar(counter == 0 ? 1801 : 2474 + counter, (int) (xpTracks[counter] * 10));
    }

    public void handleSetupXPCounter(int componentId) {
        if (componentId == 18)
            player.getInterfaceManager().sendXPDisplay();
        else if (componentId >= 22 && componentId <= 24)
            setCurrentCounter(componentId - 22);
        else if (componentId == 27)
            switchTrackCounter();
        else if (componentId == 61)
            resetCounterXP();
        else if (componentId >= 31 && componentId <= 57)
            if (componentId == 33)
                setCounterSkill(4);
            else if (componentId == 34)
                setCounterSkill(2);
            else if (componentId == 35)
                setCounterSkill(3);
            else if (componentId == 42)
                setCounterSkill(18);
            else if (componentId == 49)
                setCounterSkill(11);
            else
                setCounterSkill(componentId >= 56 ? componentId - 27 : componentId - 31);

    }

    public void sendInterfaces() {
        if (xpDisplay)
            player.getInterfaceManager().sendXPDisplay();
        if (xpPopup)
            player.getInterfaceManager().sendXPPopup();
    }

    public void resetXPDisplay() {
        xpDisplay = false;
    }

    public void switchXPDisplay() {
        xpDisplay = !xpDisplay;
        if (xpDisplay)
            player.getInterfaceManager().sendXPDisplay();
        else
            player.getInterfaceManager().closeXPDisplay();
    }

    public void switchXPPopup(boolean silent) {
        xpPopup = !xpPopup ? true : false;
        if (!silent)
            player.getPackets().sendGameMessage("XP pop-ups are now " + (xpPopup ? "en" : "dis") + "abled.");
        if (xpPopup)
            player.getInterfaceManager().sendXPPopup();
        else
            player.getInterfaceManager().closeXPPopup();
    }

    public void restoreSkills() {
        for (int skill = 0; skill < level.length; skill++) {
            level[skill] = (short) getLevelForXp(skill);
            refresh(skill);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
        // temporary
        if (xpTracks == null) {
            xpPopup = true;
            xpTracks = new double[3];
            trackSkills = new boolean[3];
            trackSkillsIds = new byte[3];
            trackSkills[0] = true;
            for (int i = 0; i < trackSkillsIds.length; i++)
                trackSkillsIds[i] = 30;
        }
    }

    public short[] getLevels() {
        return level;
    }

    public double[] getXp() {
        return xp;
    }

    public int getLevel(int skill) {
        return level[skill];
    }

    public double getXp(int skill) {
        return xp[skill];
    }

    public int getTotalLevel(Player player) {
        int totallevel = 0;
        for (int i = 0; i <= 24; i++) {
            totallevel += player.getSkills().getLevelForXp(i);
        }
        return totallevel;
    }

    public int getTotalXP(Player player) {
        int totalXP = 0;
        for (int skill = 0; skill <= 24; skill++) {
            totalXP += player.getSkills().getXp(skill);
        }
        return totalXP;
    }

    public int getXPLampFormula(int skill) {
        int currentLevel = getLevel(skill);
        int formula = (int) Math
                .floor((Math.pow(currentLevel, 3) - 2 * Math.pow(currentLevel, 2) + 100 * currentLevel) / 20);
        player.getSkills().addXp(skill, formula);
        return formula;
    }

    public int getXPForSkill(int skill) {
        skill += player.getSkills().getXp(skill);
        return skill;
    }

    public int getTargetIdByComponentId(int componentId) {
        switch (componentId) {
            case 150: // Attack
                return 0;
            case 9: // Strength
                return 1;
            case 40: // Range
                return 2;
            case 71: // Magic
                return 3;
            case 22: // Defence
                return 4;
            case 145: // Constitution
                return 5;
            case 58: // Prayer
                return 6;
            case 15: // Agility
                return 7;//16
            case 28: // Herblore
                return 8;//15
            case 46: // Theiving
                return 9;
            case 64: // Crafting
                return 10;
            case 84: // Runecrafting
                return 11;
            case 140: // Mining
                return 12;
            case 135: // Smithing
                return 13;
            case 34: // Fishing
                return 14;
            case 52: // Cooking
                return 15;
            case 130: // Firemaking
                return 16;
            case 125: // Woodcutting
                return 17;
            case 77: // Fletching
                return 18;
            case 90: // Slayer
                return 19;
            case 96: // Farming
                return 20;
            case 102: // Construction
                return 21;
            case 108: // Hunter
                return 22;
            case 114: // Summoning
                return 23;
            case 120: // Dungeoneering
                return 24;
            default:
                return -1;
        }
    }

    public int getSkillIdByTargetId(int targetId) {
        switch (targetId) {
            case 0: // Attack
                return ATTACK;
            case 1: // Strength
                return STRENGTH;
            case 2: // Range
                return RANGE;
            case 3: // Magic
                return MAGIC;
            case 4: // Defence
                return DEFENCE;
            case 5: // Constitution
                return HITPOINTS;
            case 6: // Prayer
                return PRAYER;
            case 7: // Agility
                return AGILITY;
            case 8: // Herblore
                return HERBLORE;
            case 9: // Thieving
                return THIEVING;
            case 10: // Crafting
                return CRAFTING;
            case 11: // Runecrafting
                return RUNECRAFTING;
            case 12: // Mining
                return MINING;
            case 13: // Smithing
                return SMITHING;
            case 14: // Fishing
                return FISHING;
            case 15: // Cooking
                return COOKING;
            case 16: // Firemaking
                return FIREMAKING;
            case 17: // Woodcutting
                return WOODCUTTING;
            case 18: // Fletching
                return FLETCHING;
            case 19: // Slayer
                return SLAYER;
            case 20: // Farming
                return FARMING;
            case 21: // Construction
                return CONSTRUCTION;
            case 22: // Hunter
                return HUNTER;
            case 23: // Summoning
                return SUMMONING;
            case 24: // Dungeoneering
                return DUNGEONEERING;
            default:
                return -1;
        }
    }

    public void refreshEnabledSkillsTargets() {
        int value = Utils.get32BitValue(enabledSkillsTargets, true);
        player.getVarsManager().sendVar(1966, value);
    }

    public void refreshUsingLevelTargets() {
        int value = Utils.get32BitValue(skillsTargetsUsingLevelMode, true);
        player.getVarsManager().sendVar(1968, value);
    }

    public void refreshSkillsTargetsValues() {
        for (int i = 0; i < 25; i++) {
            player.getVarsManager().sendVar(1969 + i, skillsTargetsValues[i]);
        }
    }

    public void setSkillTargetEnabled(int id, boolean enabled) {
        enabledSkillsTargets[id] = enabled;
        refreshEnabledSkillsTargets();
    }

    public void setSkillTargetUsingLevelMode(int id, boolean using) {
        skillsTargetsUsingLevelMode[id] = using;
        refreshUsingLevelTargets();
    }

    public void setSkillTargetValue(int skillId, int value) {
        skillsTargetsValues[skillId] = value;
        refreshSkillsTargetsValues();
    }

    public void setSkillTarget(boolean usingLevel, int skillId, int target) {
        setSkillTargetEnabled(skillId, true);
        setSkillTargetUsingLevelMode(skillId, usingLevel);
        setSkillTargetValue(skillId, target);

    }

    public boolean hasRequirements(int... skills) {
        for (int i = 0; i < skills.length; i += 2) {
            int skillId = skills[i];
            int skillLevel = skills[i + 1];
            if (skillId > 24)
                continue;
            if (getLevelForXp(skillId) < skillLevel)
                return false;
        }
        return true;
    }

    public int getCombatLevel() {
        double attack = getLevelForXp(Skills.ATTACK);
        double defence = getLevelForXp(Skills.DEFENCE);
        double strength = getLevelForXp(Skills.STRENGTH);
        double hp = getLevelForXp(Skills.HITPOINTS);
        double prayer = getLevelForXp(Skills.PRAYER);
        double ranged = getLevelForXp(Skills.RANGE);
        double magic = getLevelForXp(Skills.MAGIC);
        double combatLevel = 0;
        double melee = Math.floor(0.25 * (defence + hp + Math.floor(prayer / 2)) + 0.325 * (attack + strength));
        double ranger = Math
                .floor(0.25 * (defence + hp + Math.floor(prayer / 2)) + 0.325 * (Math.floor(ranged / 2) + ranged));
        double mage = Math
                .floor(0.25 * (defence + hp + Math.floor(prayer / 2)) + 0.325 * (Math.floor(magic / 2) + magic));
        if (melee >= ranger && melee >= mage) {
            combatLevel = melee;
        } else if (ranger >= melee && ranger >= mage) {
            combatLevel += ranger;
        } else if (mage >= melee && mage >= ranger) {
            combatLevel += mage;
        }
        return (int) combatLevel;
    }

    public void set(int skill, int newLevel) {
        level[skill] = (short) newLevel;
        refresh(skill);
    }

    public int drainLevel(int skill, int drain) {
        int drainLeft = drain - level[skill];
        if (drainLeft < 0) {
            drainLeft = 0;
        }
        level[skill] -= drain;
        if (level[skill] < 0) {
            level[skill] = 0;
        }
        refresh(skill);
        return drainLeft;
    }

    public int getCombatLevelWithSummoning() {
        return getCombatLevel() + getSummoningCombatLevel();
    }

    public int getSummoningCombatLevel() {
        return getLevelForXp(Skills.SUMMONING) / 8;
    }

    public void drainSummoning(int amt) {
        int level = getLevel(Skills.SUMMONING);
        if (level == 0)
            return;
        set(Skills.SUMMONING, amt > level ? 0 : level - amt);
    }

    public static int getXPForLevel(int level) {
        int points = 0;
        int output = 0;
        for (int lvl = 1; lvl <= level; lvl++) {
            points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
            if (lvl >= level) {
                return output;
            }
            output = (int) Math.floor(points / 4);
        }
        return 0;
    }

    public static int getLevelForXp(double exp, int max) {
        int points = 0;
        int output = 0;
        for (int lvl = 1; lvl <= max; lvl++) {
            points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
            output = (int) Math.floor(points / 4);
            if ((output - 1) >= exp) {
                return lvl;
            }
        }
        return max;
    }

    public int getLevelForXp(int skill) {
        double exp = xp[skill];
        int points = 0;
        int output = 0;
        for (int lvl = 1; lvl <= (skill == DUNGEONEERING ? 120 : 99); lvl++) {
            points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
            output = (int) Math.floor(points / 4);
            if ((output - 1) >= exp) {
                return lvl;
            }
        }
        return skill == DUNGEONEERING ? 120 : 99;
    }

    public void init() {
        for (int skill = 0; skill < level.length; skill++)
            refresh(skill);
        sendXPDisplay();
        if (enabledSkillsTargets == null)
            enabledSkillsTargets = new boolean[25];
        if (skillsTargetsUsingLevelMode == null)
            skillsTargetsUsingLevelMode = new boolean[25];
        if (skillsTargetsValues == null)
            skillsTargetsValues = new int[25];
        refreshEnabledSkillsTargets();
        refreshUsingLevelTargets();
        refreshSkillsTargetsValues();
        if (enabledSkillsTargets == null)
            enabledSkillsTargets = new boolean[25];
        if (skillsTargetsUsingLevelMode == null)
            skillsTargetsUsingLevelMode = new boolean[25];
        if (skillsTargetsValues == null)
            skillsTargetsValues = new int[25];
        refreshEnabledSkillsTargets();
        refreshUsingLevelTargets();
        refreshSkillsTargetsValues();
    }

    public void refresh(int skill) {
        player.getPackets().sendSkillLevel(skill);
    }

    public void sendMilestoneNews(int oldTotal, double oldExp, int oldLevel, int oldCombat, int skill) {
        boolean maxed = true;
        int milestoneLevel = -1;
        int index = 0;
        int[] levelMilestones = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        for (int a : levelMilestones) {
            if (getLevelForXp(Skills.ATTACK) >= a && getLevelForXp(Skills.DEFENCE) >= a && getLevelForXp(Skills.STRENGTH) >= a
                    && getLevelForXp(Skills.HITPOINTS) >= a && getLevelForXp(Skills.RANGE) >= a && getLevelForXp(Skills.MAGIC) >= a
                    && getLevelForXp(Skills.PRAYER) >= a && getLevelForXp(Skills.SUMMONING) >= a && getLevelForXp(Skills.AGILITY) >= a
                    && getLevelForXp(Skills.HERBLORE) >= a && getLevelForXp(Skills.THIEVING) >= a && getLevelForXp(Skills.CRAFTING) >= a
                    && getLevelForXp(Skills.FLETCHING) >= a && getLevelForXp(Skills.SLAYER) >= a && getLevelForXp(Skills.HUNTER) >= a && getLevelForXp(Skills.RUNECRAFTING) >= a
                    && getLevelForXp(Skills.CONSTRUCTION) >= a && getLevelForXp(Skills.DUNGEONEERING) >= a && getLevelForXp(Skills.MINING) >= a
                    && getLevelForXp(Skills.SMITHING) >= a && getLevelForXp(Skills.FISHING) >= a && getLevelForXp(Skills.COOKING) >= a && getLevelForXp(Skills.FIREMAKING) >= a
                    && getLevelForXp(Skills.WOODCUTTING) >= a && getLevelForXp(Skills.FARMING) >= a) {
                milestoneLevel = a;
            }
        }
        if (milestoneLevel != -1 && oldLevel < milestoneLevel) {
            player.getPackets().sendGameMessage("You've reached at least level " + milestoneLevel + " in all skills!", false);
            World.sendWorldMessage(HexColours.Colours.DARK_GREEN.getHex() + "<img=6>News: " + player.getDisplayName()
                    + " has just achieved at least level " + milestoneLevel + " in all skills!", false);
        }
        if (oldLevel < getLevelForXp(skill)) {
            int levels = getLevelForXp(skill) - oldLevel;
            player.getPackets().sendGameMessage("You've just advanced " + (levels > 1 ? levels : "a") + " " + getSkillName(skill)
                    + " level"+ (levels > 1 ? "s" : "") +"! You have reached level " + getLevelForXp(skill) + ".");
        }
        if (oldLevel < 99 && getLevelForXp(skill) == 99 || oldLevel < 120 && getLevelForXp(skill) == 120) {
            player.getPackets()
                    .sendGameMessage("You've reached the highest possible level in " + getSkillName(skill) + ".");
            World.sendWorldMessage(HexColours.Colours.ORANGE.getHex() + "<img=5>News: " + player.getDisplayName() + " has achieved level "
                    + getLevel(skill) + " " + getSkillName(skill) + ".", false);
            if (Settings.discordEnabled) {
                Launcher.getDiscordBot().getChannelByName("public-chat")
                        .sendMessage(":trophy: " + player.getDisplayName() + " has achieved level "
                                + getLevelForXp(skill) + " " + getSkillName(skill) + ".");
            }
        }
        int[] totalMilestones = {25, 50, 75, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1614, 1700, 1800, 1900, 2000, 2100, 2200, 2300, 2400, 2496};
        for (int i : totalMilestones) {
            if (oldTotal < i && getTotalLevel(player) >= i) {
                if (i != 2496)
                player.getPackets()
                        .sendGameMessage("You've reached the total level of " + i + ".");
                if (i >= 1500 && i != 2496)
                    World.sendWorldMessage(
                            HexColours.Colours.GREEN.getHex()+"<img=5>News: " + player.getDisplayName() + " has achieved a total level of " + i + ".",
                            false);
                player.getTemporaryAttributtes().put("MILESTONE", index);
                if (index >= 10) {
                    if (Settings.discordEnabled) {
                        Launcher.getDiscordBot().getChannelByName("public-chat").sendMessage(
                                ":trophy: " + player.getDisplayName() + " has achieved " + i + " total level!");
                    }
                }
                break;
            }
            index++;
        }
        index = 0;
        int[] combatLevels = {3, 5, 10, 15, 25, 50, 75, 90, 100, 110, 120, 126, 130, 138};
        for (int c : combatLevels) {
            if (oldCombat < c && getCombatLevelWithSummoning() >= c) {
                player.getPackets().sendGameMessage("You've reached the Combat level of  " + c + ".");
                player.getTemporaryAttributtes().put("COMBATMILESTONE", index);
                if (oldCombat - getSummoningCombatLevel() < 126 && getCombatLevel() == 126 || getCombatLevelWithSummoning() == 138)
                    World.sendWorldMessage(HexColours.Colours.ORANGE.getHex()+"<img=5><>News: " + player.getDisplayName() + " has achieved combat level " + c + "!", false);
            }
            index++;
        }
        for (int i = 0; i < Skills.SKILL_NAME.length; i++) {
            if (player.getSkills().getLevelForXp(i) < 99) {
                maxed = false;
                break;
            }
        }

        if (maxed) {
            player.getPackets()
                    .sendGameMessage("You've reached the highest possible level in all skills.");
            World.sendWorldMessage(HexColours.Colours.RED.getHex()+"<img=6>News: " + player.getDisplayName()
                    + " has just achieved at least level 99 in all skills!", false);
            if (Settings.discordEnabled) {
                Launcher.getDiscordBot().getChannelByName("public-chat").sendMessage(":trophy: "
                        + player.getDisplayName() + " has just achieved at least level 99 in all skills!");
            }
        }
        index = 0;
        int[] slayerCombatLevels = {3, 20, 40, 70, 85, 100 };
        for (int c : slayerCombatLevels) {
            if (oldCombat < c && getCombatLevelWithSummoning() >= c) {
                player.getTemporaryAttributtes().put("SLAYERCOMBATMILESTONE", index);
            }
            index++;
        }
    }

    /*
     * if(componentId == 33) setCounterSkill(4); else if(componentId == 34)
     * setCounterSkill(2); else if(componentId == 35) setCounterSkill(3); else
     * if(componentId == 42) setCounterSkill(18); else if(componentId == 49)
     * setCounterSkill(11);
     */

    public int getCounterSkill(int skill) {
        switch (skill) {
            case ATTACK:
                return 0;
            case STRENGTH:
                return 1;
            case DEFENCE:
                return 4;
            case RANGE:
                return 2;
            case HITPOINTS:
                return 5;
            case PRAYER:
                return 6;
            case AGILITY:
                return 7;
            case HERBLORE:
                return 8;
            case THIEVING:
                return 9;
            case CRAFTING:
                return 10;
            case MINING:
                return 12;
            case SMITHING:
                return 13;
            case FISHING:
                return 14;
            case COOKING:
                return 15;
            case FIREMAKING:
                return 16;
            case WOODCUTTING:
                return 17;
            case SLAYER:
                return 19;
            case FARMING:
                return 20;
            case CONSTRUCTION:
                return 21;
            case HUNTER:
                return 22;
            case SUMMONING:
                return 23;
            case DUNGEONEERING:
                return 24;
            case MAGIC:
                return 3;
            case FLETCHING:
                return 18;
            case RUNECRAFTING:
                return 11;
            default:
                return -1;
        }

    }

    public String getSkillName(int skill) {
        String skillName = null;
        switch (skill) {
            case 0:
                return "Attack";
            case 1:
                return "Defence";
            case 2:
                return "Strength";
            case 3:
                return "Constitution";
            case 4:
                return "Ranged";
            case 5:
                return "Prayer";
            case 6:
                return "Magic";
            case 7:
                return "Cooking";
            case 8:
                return "Woodcutting";
            case 9:
                return "Fletching";
            case 10:
                return "Fishing";
            case 11:
                return "Firemaking";
            case 12:
                return "Crafting";
            case 13:
                return "Smithing";
            case 14:
                return "Mining";
            case 15:
                return "Herblore";
            case 16:
                return "Agility";
            case 17:
                return "Thieving";
            case 18:
                return "Slayer";
            case 19:
                return "Farming";
            case 20:
                return "Runecrafting";
            case 21:
                return "Hunter";
            case 22:
                return "Construction";
            case 23:
                return "Summoning";
            case 24:
                return "Dungeoneering";
        }
        return skillName;
    }

    public void checkXpMilestones(Player player, int skill, double oldExp) {
        int[] xpMilestones = {5000000, 7500000, 10000000, 20000000, 30000000, 40000000, 50000000, 60000000, 70000000,
                80000000, 90000000, 100000000, 150000000, 200000000};
        for (int i : xpMilestones) {
            if (oldExp < i && xp[skill] >= i) {
                player.getPackets()
                        .sendGameMessage("You've reached a total xp of " + Utils.getFormattedNumber(i, ',') + " xp in " + getSkillName(skill) + ".");
                World.sendWorldMessage(HexColours.Colours.BLUE.getHex() + "<img=5>News: " + player.getDisplayName() + " has achieved "
                        + Utils.getFormattedNumber(i, ',') + " xp in " + getSkillName(skill) + ".", false);
                if (Settings.discordEnabled) {
                    Launcher.getDiscordBot().getChannelByName("public-chat")
                            .sendMessage(":trophy: " + player.getDisplayName() + " has achieved "
                                    + Utils.getFormattedNumber(i, ',') + " xp in " + getSkillName(skill) + ".");
                }
            }
        }
    }

    public double addLampXP(int skill, double exp) {
        int oldTotal = getTotalLevel(player);
        double oldExp = xp[skill];
        int Lamp_XP = 5;
        player.getControlerManager().trackXP(skill, (int) exp);
        if (player.isXpLocked())
            return 0;
        exp *= Lamp_XP;
        int oldLevel = getLevelForXp(skill);
        int oldCombat = getCombatLevelWithSummoning();
        xp[skill] += exp;
        if (xp[skill] > MAXIMUM_EXP)
            xp[skill] = MAXIMUM_EXP;
        for (int i = 0; i < trackSkills.length; i++) {
            if (trackSkills[i]) {
                if (trackSkillsIds[i] == 30
                        || (trackSkillsIds[i] == 29
                        && (skill == Skills.ATTACK || skill == Skills.DEFENCE || skill == Skills.STRENGTH
                        || skill == Skills.MAGIC || skill == Skills.RANGE || skill == Skills.HITPOINTS))
                        || trackSkillsIds[i] == getCounterSkill(skill)) {
                    xpTracks[i] += exp;
                    refreshCounterXp(i);
                }
            }
        }
        if (xp[skill] >= MAXIMUM_EXP) {
            xp[skill] = MAXIMUM_EXP;
        }
        int newLevel = getLevelForXp(skill);
        int levelDifference = newLevel - oldLevel;
        checkXpMilestones(player, skill, oldExp);
        if (newLevel > oldLevel) {
            level[skill] += levelDifference;
            player.getDialogueManager().startDialogue("LevelUp", skill);
            sendMilestoneNews(oldTotal, oldExp, oldLevel, oldCombat, skill);
            sendLevelAttributtes(player, skill, oldLevel, newLevel);
            if (skill == SUMMONING || (skill >= ATTACK && skill <= MAGIC)) {
                player.getAppearence().generateAppearenceData();
                if (skill == HITPOINTS)
                    player.heal(levelDifference * 10);
                else if (skill == PRAYER)
                    player.getPrayer().restorePrayer(levelDifference * 10);
            }
            refresh(skill);
        }
        return exp;
    }

    public void addXpNoBonus(int skill, double exp) {
        if (exp < 1)
            return;
        int oldTotal = getTotalLevel(player);
        double oldExp = xp[skill];
        player.getControlerManager().trackXP(skill, (int) exp);
        int oldLevel = getLevelForXp(skill);
        int oldCombat = getCombatLevelWithSummoning();
        xp[skill] += exp;
        if (xp[skill] > MAXIMUM_EXP)
            xp[skill] = MAXIMUM_EXP;
        for (int i = 0; i < trackSkills.length; i++) {
            if (trackSkills[i]) {
                if (trackSkillsIds[i] == 30
                        || (trackSkillsIds[i] == 29
                        && (skill == Skills.ATTACK || skill == Skills.DEFENCE || skill == Skills.STRENGTH
                        || skill == Skills.MAGIC || skill == Skills.RANGE || skill == Skills.HITPOINTS))
                        || trackSkillsIds[i] == getCounterSkill(skill)) {
                    xpTracks[i] += exp;
                    refreshCounterXp(i);
                }
            }
        }

        if (xp[skill] >= MAXIMUM_EXP) {
            xp[skill] = MAXIMUM_EXP;
            return;
        }
        int newLevel = getLevelForXp(skill);
        int levelDiff = newLevel - oldLevel;
        checkXpMilestones(player, skill, oldExp);
        if (newLevel > oldLevel) {
            level[skill] += levelDiff;
            player.getDialogueManager().startDialogue("LevelUp", skill);
            sendMilestoneNews(oldTotal, oldExp, oldLevel, oldCombat, skill);
            sendLevelAttributtes(player, skill, oldLevel, newLevel);
            if (skill == SUMMONING || (skill >= ATTACK && skill <= MAGIC)) {
                player.getAppearence().generateAppearenceData();
                if (skill == HITPOINTS)
                    player.heal(levelDiff * 10);
                else if (skill == PRAYER)
                    player.getPrayer().restorePrayer(levelDiff * 10);
            }
            // player.getQuestManager().checkCompleted();
        }
        refresh(skill);
    }

    public void addSkillingXp(int skill, double exp, double multiplier) {
        if (exp < 1)
            return;
        int oldTotal = getTotalLevel(player);
        double oldExp = xp[skill];
        if (getXp(skill) >= 200000000) {
            exp *= 1;
        } else {
            if (skill == Skills.SUMMONING) {
                exp *= Settings.SUMMONING_XP_RATE;
            } else if (skill == Skills.DUNGEONEERING) {
                exp *= 50;
            } else if (skill == Skills.PRAYER) {
                exp *= 10;
            }
            exp *= Settings.SKILLING_XP_RATE;
        }
        double bonusExp = 0;
        exp *= player.getBonusExp();
        if (Settings.BONUS_EXP_WEEK_MULTIPLIER > 1) {
            bonusExp = exp * Settings.BONUS_EXP_WEEK_MULTIPLIER - exp;
            exp *= Settings.BONUS_EXP_WEEK_MULTIPLIER;
        } else
            bonusExp = exp * multiplier - exp;
        player.getVarsManager().sendVar(2044, bonusExp > 1 ? (int) (bonusExp * 10) : 0);
        if (player.getAssist().isAssisted()) {
            AssistManager.giveXP(player, skill, exp);
            return;
        }
        player.getControlerManager().trackXP(skill, (int) exp);
        int oldLevel = getLevelForXp(skill);
        int oldCombat = getCombatLevelWithSummoning();
        xp[skill] += exp;
        for (int i = 0; i < trackSkills.length; i++) {
            if (trackSkills[i]) {
                if (trackSkillsIds[i] == 30
                        || (trackSkillsIds[i] == 29
                        && (skill == Skills.ATTACK || skill == Skills.DEFENCE || skill == Skills.STRENGTH
                        || skill == Skills.MAGIC || skill == Skills.RANGE || skill == Skills.HITPOINTS))
                        || trackSkillsIds[i] == getCounterSkill(skill)) {
                    xpTracks[i] += exp;
                    refreshCounterXp(i);
                }
            }
        }
        int newLevel = getLevelForXp(skill);
        int levelDiff = newLevel - oldLevel;
        checkXpMilestones(player, skill, oldExp);
        if (newLevel > oldLevel) {
            level[skill] += levelDiff;
            player.getDialogueManager().startDialogue("LevelUp", skill);
            sendMilestoneNews(oldTotal, oldExp, oldLevel, oldCombat, skill);
            sendLevelAttributtes(player, skill, oldLevel, newLevel);
            if (skill == SUMMONING || (skill >= ATTACK && skill <= MAGIC)) {
                player.getAppearence().generateAppearenceData();
                if (skill == HITPOINTS)
                    player.heal(levelDiff * 10);
                else if (skill == PRAYER)
                    player.getPrayer().restorePrayer(levelDiff * 10);
            }
        }
        refresh(skill);
    }

    public void addXp(int skill, double exp) {
        if (exp < 1)
            return;
        int oldTotal = getTotalLevel(player);
        double oldExp = xp[skill];
        if (getXp(skill) >= 200000000) {
            exp *= 1;
        } else {
            if ((skill >= Skills.ATTACK && skill <= Skills.RANGE) || skill == Skills.MAGIC) {
                if (player.getTemporaryTarget() != null && player.isAtWild()
                        && !(player.getTemporaryTarget() instanceof NPC))
                    exp *= 1;
                else if (player.getTemporaryTarget() != null && player.isAtWild()
                        && (player.getTemporaryTarget() instanceof NPC))
                    exp *= Settings.COMBAT_XP_RATE;
                else
                    exp *= Settings.COMBAT_XP_RATE;
            } else if (skill == Skills.SUMMONING) {
                exp *= Settings.SUMMONING_XP_RATE;
            } else if (skill == Skills.DUNGEONEERING) {
                exp *= 50;
            } else if (skill == Skills.PRAYER) {
                exp *= 10;
            } else {
                exp *= Settings.SKILLING_XP_RATE;
            }
            exp *= 1;
        }
        double normalExp = exp;
        double bonusExp = normalExp * Settings.BONUS_EXP_WEEK_MULTIPLIER - normalExp;
        exp *= player.getBonusExp();
        exp *= Settings.BONUS_EXP_WEEK_MULTIPLIER;
        if (Settings.BONUS_EXP_WEEK_MULTIPLIER > 1) {
            if (skill >= 0 && skill <= 6 && skill != 5) {
                if (player.getTemporaryTarget() != null && player.isAtWild()
                        && !(player.getTemporaryTarget() instanceof NPC)) {
                    player.getVarsManager().sendVar(2044, 0);
                } else
                    player.getVarsManager().sendVar(2044, (int) (bonusExp * 40));
            } else
                player.getVarsManager().sendVar(2044, (int) (bonusExp * 10));
        } else {
            player.getVarsManager().sendVar(2044, 0);
        }
        if (player.getAssist().isAssisted()) {
            AssistManager.giveXP(player, skill, exp);
            return;
        }
        player.getControlerManager().trackXP(skill, (int) exp);
        int oldCombat = getCombatLevelWithSummoning();
        int oldLevel = getLevelForXp(skill);
        xp[skill] += exp;
        for (int i = 0; i < trackSkills.length; i++) {
            if (trackSkills[i]) {
                if (trackSkillsIds[i] == 30
                        || (trackSkillsIds[i] == 29
                        && (skill == Skills.ATTACK || skill == Skills.DEFENCE || skill == Skills.STRENGTH
                        || skill == Skills.MAGIC || skill == Skills.RANGE || skill == Skills.HITPOINTS))
                        || trackSkillsIds[i] == getCounterSkill(skill)) {
                    xpTracks[i] += exp;
                    refreshCounterXp(i);
                }
            }
        }
        int newLevel = getLevelForXp(skill);
        int levelDiff = newLevel - oldLevel;
        checkXpMilestones(player, skill, oldExp);
        if (newLevel > oldLevel) {
            level[skill] += levelDiff;
            player.getDialogueManager().startDialogue("LevelUp", skill);
            sendMilestoneNews(oldTotal, oldExp, oldLevel, oldCombat, skill);
            sendLevelAttributtes(player, skill, oldLevel, newLevel);
            if (skill == SUMMONING || (skill >= ATTACK && skill <= MAGIC)) {
                player.getAppearence().generateAppearenceData();
                if (skill == HITPOINTS)
                    player.heal(levelDiff * 10);
                else if (skill == PRAYER)
                    player.getPrayer().restorePrayer(levelDiff * 10);
            }
        }
        refresh(skill);
    }


    public void sendLevelAttributtes(Player player, int skillId, int oldLevel, int newLevel) {
        player.temporaryAttribute().put("LEVELUP[" + skillId + "]:GAINEDLEVELS", getGainedLevels(player, skillId) + (oldLevel - newLevel));
        player.temporaryAttribute().put("LEVELUP[" + skillId + "]:OLDLEVEL", oldLevel);
        player.temporaryAttribute().put("LEVELUP[" + skillId + "]:NEWLEVEL", newLevel);
    }

    public static int getMilestone(Player player) {
        Integer level = (Integer) player.getTemporaryAttributtes().get("MILESTONE");
        if (level == null || level < 0)
            return -1;
        return level;
    }

    public static int getCombatMilestone(Player player) {
        Integer level = (Integer) player.getTemporaryAttributtes().get("COMBATMILESTONE");
        if (level == null || level < 0)
            return -1;
        return level;
    }

    public static int getSlayerCombatMilestone(Player player) {
        Integer level = (Integer) player.getTemporaryAttributtes().get("SLAYERCOMBATMILESTONE");
        if (level == null || level < 0)
            return -1;
        return level;
    }

    public static int getOldLevel(Player player, int skillId) {
        Integer level = (Integer) player.getTemporaryAttributtes().get("LEVELUP[" + skillId + "]:OLDLEVEL");
        if (level == null || level < 0)
            return -1;
        return level;
    }

    public static int getNewLevel(Player player, int skillId) {
        Integer level = (Integer) player.getTemporaryAttributtes().get("LEVELUP[" + skillId + "]:NEWLEVEL");
        if (level == null || level < 0)
            return -1;
        return level;
    }

    public static int getGainedLevels(Player player, int skillId) {
        Integer level = (Integer) player.getTemporaryAttributtes().get("LEVELUP[" + skillId + "]:GAINEDLEVELS");
        if (level == null)
            return 0;
        return level;
    }


    public static void sendLevelConfigs(Player player, int skillId) {
        int levelupConfig = Skills.SkillData.forId(skillId).getValue();
        int gainedLevelConfig = Skills.SkillData.forId(skillId).getLevelGainedConfig();
        int oldLevel = getOldLevel(player, skillId);
        int newLevel = getNewLevel(player, skillId);
        int gainedLevels = getGainedLevels(player, skillId);
        int milestone = getMilestone(player);
        int combatMilestone = getCombatMilestone(player);
        int slayerCombatMilestones = getSlayerCombatMilestone(player);
        player.getPackets().sendGlobalConfig(gainedLevelConfig, gainedLevels + newLevel);
        player.getVarsManager().sendVarBit(4727, combatMilestone);
        player.getVarsManager().sendVarBit(4728, milestone);
        player.getVarsManager().sendVarBit(4729, levelupConfig);
        player.getVarsManager().sendVarBit(4730, milestone != -1 ? 1 : 0);
        player.getVarsManager().sendVarBit(4731, combatMilestone != -1 ? 1 : 0);
        player.getVarsManager().sendVarBit(5395, slayerCombatMilestones != -1 ? 1 : 0);
    }

    public void resetSkillNoRefresh(int skill) {
        xp[skill] = 0;
        level[skill] = 1;
    }

    public void setXp(int skill, double exp) {
        xp[skill] = exp;
        refresh(skill);
    }

    public boolean hasTwo99s() {
        int count = 0;
        for (int i = 0; i < 25; i++) {
            if (player.getSkills().getLevelForXp(i) < 99) {
                count++;
                if (count >= 24)
                    return false;
            }
        }
        return true;
    }
}
