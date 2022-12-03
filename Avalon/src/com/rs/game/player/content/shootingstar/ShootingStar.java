package com.rs.game.player.content.shootingstar;

import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.ActivityHandler;
import com.rs.utils.Utils;

public class ShootingStar {

    private final int START_ID = 38661;
    /**
     * vars
     */
    private transient StarLocations location;
    private int skillId;
    private boolean found;
    private boolean canClaim;
    private int stage = 0;
    private boolean isPrismatic;
    private int lifes;


    private transient static StarLocations[] locs = StarLocations.values();

    public static final int[] STAR_DUST_Q = {1200, 700, 439, 250, 175, 80, 40, 25, 15};
    public static final int[] STAR_DUST_XP = {14, 25, 29, 32, 47, 71, 114, 145, 210};

    /*
     * generats a new star (random)
     */
    public ShootingStar() {
        location = locs[Utils.getRandom(locs.length - 1)];
        setFound(false);
        setStage(7);
        setSkillId(Utils.random(0, 24));
        setCanClaim(false);
        spawn();
        lifes = STAR_DUST_Q[getStage() - 1]; //1000 dust per layer atm
        ActivityHandler.setShootingStar("A " + Skills.SKILL_NAME[getSkillId()] + " star has crashed at home.");
    }

    public int getMinedPerc() {
        return 100 - (100 * lifes / STAR_DUST_Q[getStage() - 1]);
    }


    /**
     * discover the rock
     *
     * @param player
     */
    public void findStar(Player player) {
        int exp = player.getSkills().getLevel(getSkillId()) * getSkillId() / player.getSkills().getLevel(getSkillId());
        player.getSkills().addXp(getSkillId(), exp);
        player.sm("You discovered the shooting star and received some bonus exp.");
        setFound(true);
    }


    /**
     * removes the star.
     */
    public void removeStar() {
        World.spawnNPC(8091, getTile(), -1, true, true);
        ActivityHandler.setShootingStar("The star has been mined.");
    }

    public void mine(Player player) {
        if (lifes == 0) {
            if (stage == 0) {
                if (!canClaim) {
                    canClaim = true;
                    World.spawnObject(new WorldObject(123, 10, 0, getTile()));
                    removeStar();
                }
            } else {
                World.spawnObject(new WorldObject(START_ID + (7 - getStage()), 10, 0, getTile()));
                player.animate(new Animation(-1));
                player.setNextForceTalk(new ForceTalk("OW!"));
                player.sm("The explosion forces you to stop");
                stage--;
                lifes = 20 * World.getPlayers().size();
            }
        } else
            lifes--;
    }

    /**
     * spawns the star.
     */
    public void spawn() {
        World.spawnObject(new WorldObject(38660, 10, 0, getTile()), true);
        World.sendWorldMessage("<img=7><col=ff0000>News: A Shooting Star has just crashed in " + getLocation() + " ["
                + (isPrismatic ? "Prismatic" : Skills.SKILL_NAME[getSkillId()]) + "]", false);

    }


    public int getReqLevel() {
        return stage * 10;
    }

    private static enum StarLocations {

        CRAFTING_GUILD_MINING_SITE("Crafting Guild Mining site", new WorldTile(2940, 3278, 0)),

        MINING_GUILD("Mining Guild", new WorldTile(3027, 3349, 0)),

        RIMMINGTON_MINING_SITE("Rimmington Mining site", new WorldTile(2975, 3234, 0)),

        FALADOR_MINING_SITE("Falador Mining site", new WorldTile(2926, 3340, 0)),

        KARAMJA_NORTH_WEST_MINING_SITE("Karamja North-west Mining site", new WorldTile(2732, 3219, 0)),

        BRIMHAVEN_MINING_SITE("Brimhaven Mining site", new WorldTile(2743, 3143, 0)),

        SOUTH_CRANDOR_MINING_SITE("South Crandor Mining site", new WorldTile(2822, 3237, 0)),

        KARAMJA_MINING_SITE("Karamja Mining site", new WorldTile(2847, 3028, 0)),

        SHILO_VILLAGE_SITE("Shilo Village site", new WorldTile(2826, 2996, 0)),

        KELDAGRIM_ENTRANCE_MINING_SITE("Keldagrim Entrance Mining site", new WorldTile(2724, 3698, 0)),

        JATIZSO_MINE("Jatizso mine", new WorldTile(2397, 3815, 0)),

        LUNAR_ISLE_MINE("Lunar Isle mine", new WorldTile(2139, 3938, 0)),

        MISCELLANIA_MINING_SITE("Miscellania Mining site", new WorldTile(2530, 3887, 0)),

        FREMENNIK_ISLES_MINING_SITE("Fremennik Isles Mining site", new WorldTile(2375, 3834, 0)),

        RELLEKKA_MINNING_SITE("Rellekka Mining site", new WorldTile(2683, 3699, 0)),

        LEGENDS_GUILD_MINNING_SITE("Legends Guild Mining site", new WorldTile(2702, 3331, 0)),

        SOUTH_EAST_ARDOUGNE_MINNING_SITE("South-east Ardougne Mining site", new WorldTile(2610, 3220, 0)),

        COAL_TRUCKS("Coal trucks", new WorldTile(2585, 3477, 0)),

        YANNILLE_BANK("Yanille bank", new WorldTile(2603, 3086, 0)),

        FIGHT_ARENA_MINING_SITE("Fight arena mining site", new WorldTile(2628, 3134, 0)),

        ALKHARID_BANK("Al-khardi bank", new WorldTile(3285, 3181, 0)),

        ALKHARID_MINING_SITE("Al-kharid Mining site", new WorldTile(3297, 3297, 0)),

        DUEL_ARENA_BANK_CHEST("Duel arena bank chest", new WorldTile(3388, 3268, 0)),

        UZER_MINING_SITE("Uzer Mining site", new WorldTile(3456, 3136, 0)),

        NARDAH_MINING_SITE("Nardah Mining site", new WorldTile(3456, 3136, 0)),

        NARDAH_BANK("Nardah Bank", new WorldTile(3434, 2888, 0)),

        WESTERN_DESERT_MINING_SITE("Western Desert Mining site", new WorldTile(3169, 2911, 0)),

        SOUTH_EAST_VARROCK_MINING_SITE("South east Varrock Mining site", new WorldTile(3293, 3352, 0)),

        LUMBRIDGE_SWAMP_TRAINING_MINING_SITE("Lumbridge Swamp Mining site", new WorldTile(3231, 3155, 0)),

        SOUTH_WEST_VARROCK_MINING_SITE("South West Varrock Mining site", new WorldTile(3174, 3361, 0)),

        VARROCK_EAST_BANK("Varrock East Bank", new WorldTile(3257, 3408, 0)),

        BURGH_DE_ROTT_BANK("Burgh De Rott Bank", new WorldTile(3501, 3215, 0)),

        CANIFIS_BANK("Canifis Bank", new WorldTile(3505, 3485, 0)),

        MOS_LE_HARMLESS_BANK("Mos Le'Harmless Bank", new WorldTile(3685, 2967, 0)),

        GNOME_STRONGHOLD_BANK("Gnome Stronghold", new WorldTile(2449, 3436, 0)),

        LTETYA_BANK("Ltetya Bank", new WorldTile(2329, 3163, 0)),

        PISCATORIS_MINING_SITE("Piscatoris", new WorldTile(2336, 3636, 0)),

        NORTH_EDGEVILLE_MINING_SITE("Near Zamorak Mage in the wilderness (lvl 7)", new WorldTile(3108, 3569, 0)),

        SOUTHERN_WILDERNESS_MINING_SITE("Southern Wilderness Mining site (lvl 10)", new WorldTile(3019, 3594, 0)),

        WILDERNESS_VOLCANO("East of Wilderness Volcano (lvl 22)", new WorldTile(3188, 3690, 0)),

        BANDIT_CAMP_MINING_SITE("South of Lava Maze Mining site (lvl 35)", new WorldTile(3031, 3795, 0)),

        LAVA_MAZE_RUNITE_MINING_SITE("Wilderness Rune Rocks (lvl 47)", new WorldTile(3059, 3888, 0)),

        PIRATES_HIDEOUT_MINING_SITE("Pirate Hut in Wilderness (54)", new WorldTile(3048, 3944, 0)),

        MAGE_ARENA_BANK("Mage Bank (lvl 56)", new WorldTile(3091, 3962, 0));

        private String name;
        private WorldTile location;

        private StarLocations(String name, WorldTile location) {
            this.name = name;
            this.location = location;
        }

    }

    /**
     * @return the tile
     */
    public WorldTile getTile() {
        return location.location;
    }

    public String getLocation() {
        return location.name;
    }

    /**
     * @return the skillId
     */
    public int getSkillId() {
        return skillId;
    }

    /**
     * @param skillId the skillId to set
     */
    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    /**
     * @return the stage
     */
    public int getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(int stage) {
        this.stage = stage;
    }

    /**
     * @return the canClaim
     */
    public boolean isCanClaim() {
        return canClaim;
    }

    /**
     * @param canClaim the canClaim to set
     */
    public void setCanClaim(boolean canClaim) {
        this.canClaim = canClaim;
    }

    /**
     * @return the found
     */
    public boolean isFound() {
        return found;
    }

    /**
     * @param found the found to set
     */
    public void setFound(boolean found) {
        this.found = found;
    }

}
