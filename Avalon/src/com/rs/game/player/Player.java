package com.rs.game.player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import com.rs.CreationKiln;
import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.Region;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.item.itemdegrading.ChargesManager;
import com.rs.game.minigames.clanwars.FfaZone;
import com.rs.game.minigames.clanwars.RequestController;
import com.rs.game.minigames.clanwars.WarControler;
import com.rs.game.minigames.duel.DuelArena;
import com.rs.game.minigames.duel.DuelRules;
import com.rs.game.minigames.godwars.zaros.Nex;
import com.rs.game.minigames.lividfarm.LividFarm;
import com.rs.game.minigames.warriorguild.WarriorsGuild;
import com.rs.game.npc.NPC;
import com.rs.game.npc.NPC.AchievementKills;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.pet.Pet;
import com.rs.game.objects.GlobalObjectAddition;
import com.rs.game.objects.GlobalObjectDeletion;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Ranks.Rank;
import com.rs.game.player.actions.combat.PlayerCombat;
import com.rs.game.player.actions.skills.construction.House;
import com.rs.game.player.actions.skills.farming.FarmingManager;
import com.rs.game.player.actions.skills.hunter.HunterImplings;
import com.rs.game.player.actions.skills.slayer.SlayerManager;
import com.rs.game.player.actions.skills.summoning.Summoning;
import com.rs.game.player.content.AdventuresLog;
import com.rs.game.player.content.ArtisanWorkshop;
import com.rs.game.player.content.Commands;
import com.rs.game.player.content.CustomGear;
import com.rs.game.player.content.FadingScreen;
import com.rs.game.player.content.GreaterRunicStaff;
import com.rs.game.player.content.GrotwormLair;
import com.rs.game.player.content.Ironman;
import com.rs.game.player.content.ItemConstants;
import com.rs.game.player.content.KillScoreBoard;
import com.rs.game.player.content.MoneyPouch;
import com.rs.game.player.content.Notes;
import com.rs.game.player.content.Pots;
import com.rs.game.player.content.PuzzleBox;
import com.rs.game.player.content.Puzzles;
import com.rs.game.player.content.SkillCapeCustomizer;
import com.rs.game.player.content.TicketSystem;
import com.rs.game.player.content.WildernessArtefacts.Artefacts;
import com.rs.game.player.content.clans.ClanMember;
import com.rs.game.player.content.clans.ClansManager;
import com.rs.game.player.content.customshops.CustomStore;
import com.rs.game.player.content.customshops.TradeStore;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.friendschat.FriendChatsManager;
import com.rs.game.player.content.grandexchange.GrandExchange;
import com.rs.game.player.content.grandexchange.GrandExchangeManager;
import com.rs.game.player.content.grandexchange.LimitedGEReader;
import com.rs.game.player.content.pet.PetManager;
import com.rs.game.player.content.pet.Pets;
import com.rs.game.player.content.presets.PresetManager;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.QuestManager;
import com.rs.game.player.content.randomevent.AntiBot;
import com.rs.game.player.content.tasksystem.TaskManager;
import com.rs.game.player.content.treasuretrails.TreasureTrailsManager;
import com.rs.game.player.content.unlockables.UnlockableItems;
import com.rs.game.player.controlers.Controler;
import com.rs.game.player.controlers.CorpBeastControler;
import com.rs.game.player.controlers.CrucibleControler;
import com.rs.game.player.controlers.DTControler;
import com.rs.game.player.controlers.EdgevillePvPControler;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.controlers.GodWars;
import com.rs.game.player.controlers.NomadsRequiem;
import com.rs.game.player.controlers.QueenBlackDragonController;
import com.rs.game.player.controlers.WildernessControler;
import com.rs.game.player.controlers.ZGDControler;
import com.rs.game.player.controlers.castlewars.CastleWarsPlaying;
import com.rs.game.player.controlers.castlewars.CastleWarsWaiting;
import com.rs.game.player.controlers.fightpits.FightPitsArena;
import com.rs.game.player.controlers.pestcontrol.PestControlGame;
import com.rs.game.player.controlers.pestcontrol.PestControlLobby;
import com.rs.game.player.cutscenes.Cutscene;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.teleportation.TeleportsData.TeleportStore;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.net.Session;
import com.rs.net.decoders.WorldPacketsDecoder;
import com.rs.net.decoders.handlers.ButtonHandler;
import com.rs.net.encoders.WorldPacketsEncoder;
import com.rs.utils.EconomyPrices;
import com.rs.utils.HexColours;
import com.rs.utils.HexColours.Colour;
import com.rs.utils.IsaacKeyPair;
import com.rs.utils.Logger;
import com.rs.utils.MachineInformation;
import com.rs.utils.Utils;

public class Player extends Entity {

    public static final int TELE_MOVE_TYPE = 127, WALK_MOVE_TYPE = 1, RUN_MOVE_TYPE = 2;

    private static final long serialVersionUID = 2011932556974180375L;

    /**
     * @Player
     */
    private String username;
    private transient boolean started;
    private transient boolean running;
    private String password;
    private String displayName;

    /**
     * @varpbit
     */

    public HashMap<Integer, Integer> varBitList = new HashMap<>();
    public transient HashMap<Integer, Integer> temporaryVarBits = new HashMap<>();

    /**
     * @PlayerRank
     */

    private PlayerRank playerRank;

    /*
     * @Ironman
     */
    private Ironman ironman;

    public Ironman getIronman() {
        return ironman;
    }

    /**
     * @MoneyPouch
     */
    public MoneyPouch pouch;

    /**
     * @PlayerAppearence
     */
    public Appearence appearence;

    /**
     * @Inventory
     */
    public Inventory inventory;

    /**
     * @Equipment
     */
    public Equipment equipment;

    /**
     * @Bank
     */
    public Bank bank;

    /**
     * @Skills
     */
    private Skills skills;

    /**
     * @MuteCommand
     */
    private String muteFrom, muteReason;
    private long muteStart, muteEnd;

    /**
     * @BanCommand
     */
    private String banFrom, banReason;
    private long banStart, banEnd;
    private long banned;
    private boolean permBanned;

    /**
     * @Presets
     */
    private PresetManager presetManager;

    /**
     * @Drop Settings
     */
    private int valueableDrop = 5000;
    public boolean HighValueOption;
    public transient boolean dropTesting;
    public transient int dropTestingAmount = 1;

    /**
     * @PlantMarker
     */
    private transient boolean marker;

    /**
     * @House
     */
    private House house;

    public House getHouse() {
        return house;
    }

    /**
     * @GreaterRunicStaff
     */
    public Map<Integer, Item[]> staffCharges = new HashMap<>();

    /**
     * @Combat
     */
    public CombatDefinitions combatDefinitions;
    public transient HashMap<Player, Integer> attackedBy = new HashMap<>();
    private int dfsCharges;
    private transient PlayerCombat playerCombat;
    private Hit hitManager;
    private int recoilHits;

    private transient int damage;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * @Lodestones
     */
    public boolean[] lodestone;

    /**
     * @TogglesAttributes
     */
    public HashMap<String, Object> toggles = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T toggles(String key, T fail) { // is this for player attributes? no just toggles this interface
        Object o = toggles.get(key);
        if (o == null) {
            o = fail;
            toggles.put(key, fail);
        }
        return (T) o;
    }

    public String getToggleValue(Object object) {
        Object o = object;
        double tempDouble = 0;
        double intTemp = 0;
        if (o instanceof Double) {
            Double doubleValue = (Double) o;
            tempDouble = doubleValue.doubleValue();
        } else {
            Integer intValue = (Integer) o;
            intTemp = intValue.intValue();
        }
        return tempDouble != 0 ? Double.toString(tempDouble).replace(".0", "") : Integer.toString((int) intTemp);
    }

    /**
     * @KingBlackDragonLair
     */
    public boolean KBDEntrance;

    /**
     * @AdventureLog
     */
    private AdventuresLog advlog;

    /**
     * @Presets
     */
    private CustomGear[] gearSetups;

    public CustomGear[] getSetups() {
        return gearSetups;
    }

    public void resetSetups() {
        gearSetups = null;
        sm("Reseted gear setups.");
    }

    private int getSetupSlots() {
        return 28;
    }

    public boolean removeSetup(String name) {
        if (gearSetups == null)
            return false;
        for (int index = 0; index < getSetups().length; index++) {
            if (getSetups()[index] == null)
                continue;
            if (!getSetups()[index].getName().equals(name))
                continue;
            getSetups()[index] = null;
            return true;
        }
        return false;
    }

    public boolean removeSetup(int id) {
        if (gearSetups == null)
            return false;
        for (int index = 0; index < getSetups().length; index++) {
            if (getSetups()[index] == null)
                continue;
            if (index != id)
                continue;
            getSetups()[index] = null;
            return true;
        }
        return false;
    }

    public boolean addSetup(CustomGear setup) {
        if (gearSetups == null)
            this.gearSetups = new CustomGear[getSetupSlots()];
        for (int index = 0, count = 0; index < getSetups().length; index++) {
            if (getSetups()[index] != null)
                count++;
            if (count >= getSetupSlots())
                break;
            if (index == count) {
                getSetups()[count] = setup;
                getSetups()[count].setId(index);
                return true;
            }
        }
        return false;
    }

    public void setGearSetup(CustomGear[] gearSetups) {
        this.gearSetups = gearSetups;
    }

    /**
     * @Dungeoneering
     */
    private DungManager dungManager;
    private transient DungeonManager dungeonManager;

    /**
     * @CoinMethods
     */
    public boolean canBuy(int price) {
        int pouch = getMoneyPouch().getTotal();
        int total = getTotalCoins();
        if (price == -1)
            return true;
        if (total >= price) {
            if (pouch >= price) {
                getMoneyPouch().takeMoneyFromPouch(price);
                return true;
            } else if (total >= price) {
                int finalPrice = price - pouch;
                getMoneyPouch().takeMoneyFromPouch(pouch);
                getInventory().deleteItem(995, finalPrice);
                return true;
            } else {
                getInventory().deleteItem(995, price);
                return true;
            }
        }
        return false;
    }

    public int getTotalCoins() {
        int coins = getInventory().getAmountOf(995);
        int pouch = getMoneyPouch().getTotal();
        return coins + pouch < 0 ? Integer.MAX_VALUE : coins + pouch;
    }

    /**
     * @TimePieceItem
     */
    private long timePiece;

    /**
     * @LevelUps
     */
    public ArrayList<String> lastlevelUp = new ArrayList<String>();
    public ArrayList<String> lastSkill = new ArrayList<String>();

    /**
     * @BossKillcount
     */
    public HashMap<String, Integer> bossKillcount = new HashMap<>();

    /**
     * @TreasureTrails
     */
    public HashMap<String, Integer> treasureTrailCount = new HashMap<>();
    private transient ItemsContainer<Item> clueScrollRewards;
    private PuzzleBox puzzleBox;

    /**
     * @Slayer
     */
    private SlayerManager slayerManager;
    private Map<Integer, String> slayerTask = new HashMap<Integer, String>();

    /**
     * @Squeal of Fortune
     */
    private int spins;

    /**
     * @Toolbelt
     */
    private Toolbelt toolbelt;

    public Toolbelt getToolbelt() {
        return toolbelt;
    }

    /**
     * @Grotworm lair
     */
    private transient GrotwormLair grotwormLair;

    /**
     * @CapeSettings
     */
    public boolean boughtMaxCape;
    public boolean boughtCompCape;
    public boolean boughtTrimCompCape;

    /**
     * @LunarMagicks
     */
    public boolean isDreaming;

    /**
     * @DuelArena
     */
    public HashMap<Integer, Boolean> CustomDuelRule = new HashMap<Integer, Boolean>();
    public transient int ruleCount;
    private transient DuelRules lastDuelRules;
    private int duelkillCount;
    private int dueldeathCount;
    private int duelkillStreak;

    /**
     * @Instance
     */
    public boolean IsInInstance;

    /**
     * @Summoning
     */
    private transient Familiar familiar;
    public boolean familiarAutoAttack = false;
    public int storedScrolls;
    private transient long familiarDelay;

    /**
     * @Titles
     */
    public boolean isTitle;
    public String title;
    public String color;
    public String clanTag = "";

    /**
     * @PvP
     */
    private int ep;
    public int PKP;
    public int killStreak;
    public int killStreakRecord;
    private transient boolean canPvp;

    /**
     * @SafetyDungeon
     */
    public boolean completedSafety = false;
    public transient boolean safetyLever = false;

    /**
     * @Starter
     */
    public boolean recievedStarter;

    /**
     * @CoalBag TODO
     */
    public int coalStored;

    /**
     * @PlayerDropValues
     */
    private long highestDrop;

    public long getHighestValuedKill() {
        return highestDrop;
    }

    public void setHighestValuedKill(long price) {
        highestDrop = price;
    }

    /**
     * @NPCTalkedBooleans
     */

    private boolean talkedWithMarv;
    public boolean talkedToMrEx;
    public boolean talkedToEstocada;

    public boolean reachedMaxValue;

    /**
     * @Healmode
     */
    public boolean healMode;

    /**
     * @Freeze spell variables
     */
    public transient Entity frozenBy;

    /**
     * @Session
     */
    private transient Session session;

    /**
     * @MapRegion
     */
    private transient boolean clientLoadedMapRegion;
    private Region region;
    private boolean forceNextMapLoadRefresh;

    /**
     * @ScreenGraphic
     */
    private transient int displayMode;
    private transient int screenWidth;
    private transient int screenHeight;

    /**
     * @Clanwars
     */
    private transient FfaZone ffaZone;

    /**
     * @Interface
     */
    private transient InterfaceManager interfaceManager;
    private transient Runnable closeInterfacesEvent;
    private transient Runnable addItemEvent;
    public transient boolean largeSceneView;

    /**
     * @Dialogues
     */
    private transient DialogueManager dialogueManager;
    private transient Dialogue dialogue;

    /**
     * @HintIcons
     */
    private transient HintIconsManager hintIconsManager;

    /**
     * @ActionManager
     */
    private transient ActionManager actionManager;

    /**
     * @Cutscenes
     */
    private transient CutscenesManager cutscenesManager;

    /**
     * @Pricecheck
     */
    private transient PriceCheckManager priceCheckManager;

    /**
     * @Pathing
     */
    private transient CoordsEvent coordsEvent;
    private transient RouteEvent routeEvent;

    /**
     * @Quests
     */
    private int hiredByFred;

    /**
     * @Stronghold of Security Dungeon
     */
    private int skullSkeptreCharges = 5;
    public boolean strongHoldSecurityFloor1, strongHoldSecurityFloor2, strongHoldSecurityFloor3,
            strongHoldSecurityFloor4;

    /**
     * @ClanFriendchat
     */
    private ClanMember clanMember;
    private FriendChatsManager currentFriendChat;
    private ClansManager clanManager, guestClanManager;
    private transient boolean toogleLootShare;
    private transient boolean toogleCoinShare;
    private transient String nextClanMemberUpdate;
    private String currentFriendChatOwner;
    private String clanName;// , guestClanChat;
    private boolean connectedClanChannel;

    /**
     * @Trading
     */
    private transient Trade trade;
    private transient TradeStore tradestore;
    private transient boolean cantTrade;

    /**
     * @Assisting
     */
    private transient AssistManager assist;

    /**
     * @Login
     */
    private transient IsaacKeyPair isaacKeyPair;
    public long lastLoggedIn;
    private String lastIP;

    /**
     * @Pets
     */
    public PetManager petManager;
    public Pet pet;

    /**
     * @Notes
     */
    private Notes notes;

    /**
     * @PestControl
     */
    public int pestControlPoints;
    public int pestControlDamage;
    private int pestPoints;

    /**
     * @Runespan
     */
    public int RunespanPoints;
    public int InventoryPoints;
    public boolean WizardUsed;
    public boolean RunespanLow;
    public boolean RunespanHigh;
    public boolean RunespanHighest;

    /**
     * @Voting
     */
    public boolean processingVote;
    public int votePoints;

    /**
     * @Aid
     */
    private boolean acceptAid;

    /**
     * @Report
     */
    private boolean RCReport;

    /**
     * @Filter
     */
    private boolean filter;

    /**
     * @Packets
     */
    private transient ConcurrentLinkedQueue<LogicPacket> logicPackets;
    private transient long packetsDecoderPing;

    /**
     * @NPCPLAYERUpdating
     */
    private transient LocalPlayerUpdate localPlayerUpdate;
    private transient LocalNPCUpdate localNPCUpdate;

    /**
     * @Walking
     */
    private int temporaryMovementType;
    private boolean updateMovementType;

    /**
     * @CharacterCustomizing
     */
    public boolean hasDesign;

    /**
     * @AvalonPoints
     */
    private int avalonPoints;

    /**
     * @TicketSystem
     */
    public String ticketstaff = "", ticketsender = "";
    public boolean isRequestingChat, isInLiveChat;

    /**
     * @ReferrSystem
     */
    private String referrer;
    private String referredBy;
    private int totalReferred;

    /**
     * @BankPin
     */
    public boolean bypass;
    private int pinpinpin;
    public boolean setPin = false;
    public boolean openPin = false;
    public boolean startpin = false;
    private int[] bankpins = new int[]{0, 0, 0, 0};
    private int[] confirmpin = new int[]{0, 0, 0, 0};
    private int[] openBankPin = new int[]{0, 0, 0, 0};
    private int[] changeBankPin = new int[]{0, 0, 0, 0};

    /**
     * @Resting
     */
    private transient boolean resting;

    /**
     * @PlayerLock
     */
    private transient long lockDelay;

    /**
     * @Specials
     */
    private transient long specDelay;
    public transient long polDelay;

    /**
     * @Thieving
     */
    private transient long thievingDelay;

    /**
     * @Spells
     */
    private transient long spellDelay;
    public transient long teleportDelay;
    public transient boolean castedVeng;

    /**
     * @FoodsPotions
     */
    private transient long foodDelay;
    private transient long brewDelay;
    private transient long combofoodDelay;
    private transient long potDelay;

    /**
     * @Messages
     */
    private transient long lastPublicMessage;

    /**
     * @ItemSwitching
     */
    private transient List<Integer> switchItemCache;
    private transient List<Integer> switchTakeOffItemCache;

    /**
     * @OperativeSystemInfo
     */
    private transient MachineInformation machineInformation;

    /**
     * @FightKiln
     */
    public transient boolean invulnerable;

    /**
     * @Hitpoints
     */
    private transient double hpBoostMultiplier;

    /**
     * @ConfigsVars
     */
    private transient VarsManager varsManager;

    public VarsManager getVarsManager() {
        return varsManager;
    }

    /**
     * @Shops
     */
    private transient CustomStore cstore;

    public CustomStore getCustomStore() {
        return cstore;
    }

    /**
     * @DwarfMultiCannon
     */
    private transient int cannonBalls;
    private boolean lostCannon;

    public int getCannonBalls() {
        return cannonBalls;
    }

    public void addCannonBalls(int cannonBalls) {
        this.cannonBalls += cannonBalls;
    }

    public void removeCannonBalls() {
        this.cannonBalls = 0;
    }

    /**
     * @ItemDefinitions
     */
    private transient ItemDefinitions itemDefinitions;

    /**
     * @Prayer
     */
    private PrayerBook prayer;

    /**
     * @Farming
     */
    private transient FarmingManager farmingManager;

    /**
     * @Controler
     */
    private ControlerManager controlerManager;

    /**
     * @Music
     */
    private MusicsManager musicsManager;

    /**
     * @Emotes
     */
    private EmotesManager emotesManager;

    /**
     * @Hunter
     */
    private HunterImplings hunterImplings;

    /**
     * @Friendslist
     */
    private FriendsIgnores friendsIgnores;

    /**
     * @DominionTower
     */
    private DominionTower dominionTower;

    /**
     * @Auras
     */
    private AuraManager auraManager;

    /**
     * @Quests
     */
    private transient QuestManager questManager;

    /**
     * @Tasks
     */
    private TaskManager taskManager;

    /**
     * @GrandExchange
     */
    private GrandExchangeManager geManager;

    /**
     * @Run
     */
    private byte runEnergy;

    /**
     * @PlayerSettings
     */
    private boolean allowChatEffects;
    private boolean mouseButtons;
    private int privateChatSetup;
    private int friendChatSetup;
    private int clanChatSetup;
    private int guestChatSetup;

    /**
     * @Skull
     */
    public int skullDelay;
    public int skullId;

    /**
     * @Poison
     */
    private long poisonImmune;

    /**
     * @Dragonbreath
     */
    private long fireImmune;
    public long antiFire;
    public long superAntifire;

    /**
     * @QueenBlackDragon
     */
    private boolean killedQueenBlackDragon;

    /**
     * @Mill
     */
    public transient boolean hopper;
    public transient boolean lever;

    /**
     * @Bonfire
     */
    private int lastBonfire;

    /**
     * @Runecrafting
     */
    private int[] pouches;

    private boolean filterGame;

    /**
     * @Experience
     */
    public boolean xpLocked;

    /**
     * @Yell
     */
    private boolean yellOff;

    /**
     * @ChatStatus
     */
    private int publicStatus;
    private int clanStatus;
    private int tradeStatus;
    private int assistStatus;
    private boolean toggleMessages;

    /**
     * @Barrows
     */
    private ItemsContainer<Item> barrowsRewards;
    private boolean[] killedBarrowBrothers;
    private int hiddenBrother;
    private int barrowsKillCount;

    /**
     * @Untradeables
     */
    private ItemsContainer<Item> untradeables;

    /**
     * @Password
     */
    private ArrayList<String> passwordList = new ArrayList<String>();

    /**
     * @PlayerIP
     */
    private ArrayList<String> ipList = new ArrayList<String>();

    /**
     * @Unlock system
     */
    private ArrayList<UnlockableItems> unlockedItems = new ArrayList<>();

    /**
     * @PlayerKillsDeaths
     */
    public int killCount;
    public int deathCount;

    /**
     * @ItemCharges
     */
    private ChargesManager charges;

    /**
     * @MaxCape & completionist cape
     */
    private int[] maxedCapeCustomized;
    private int[] completionistCapeCustomized;

    /**
     * @FightCaves
     */
    private boolean completedFightCaves;

    /**
     * @FightKiln
     */
    private boolean completedFightKiln;

    /**
     * @FightPits
     */
    private boolean wonFightPits;

    /**
     * @Cruicible
     */
    private int crucibleHighScore;

    /**
     * @Voting
     */
    private long voteDelay;

    /**
     * @Afk
     */
    public int afkDelay;
    public long afkTimer = 0;

    private int summoningLeftClickOption;
    private List<String> ownedObjectsManagerKeys;

    /**
     * @RecoverySystem
     */
    public String otherPlayer;
    public String playerPass;
    public String changedPass;

    /**
     * @DominonTower
     */
    public boolean hasDominion;

    /**
     * @DungeoneeringUnlocks
     */
    public boolean hasRigour;
    public boolean hasAugury;
    public boolean hasRenewal;
    public boolean hasEfficiency;

    public boolean oldItemsLook;
    public boolean developerMode;
    public String gameType;

    public String customTitle;
    public transient long restoreDelay;
    public int pouchMoney;
    private double[] warriorPoints;
    private double weight;

    /**
     * @LividFarming
     */

    private LividFarm lividFarm;

    public LividFarm getLivid() {
        return lividFarm;
    }

    public long getWealth() {
        boolean skulled = hasSkull();
        boolean wilderness = isAtWild();
        Integer[][] slots = ButtonHandler.getItemSlotsKeptOnDeath(this, wilderness, skulled,
                getPrayer().usingPrayer(0, 10) || getPrayer().usingPrayer(1, 0));
        Item[][] items = getItemsKeptOnDeath(this, slots);
        long riskedWealth = 0;
        long carriedWealth = 0;
        for (Item item : items[1]) {
            if (item == null)
                continue;
            carriedWealth = riskedWealth += GrandExchange.getPrice(item.getId()) * item.getAmount();
        }
        for (Item item : items[0]) {
            if (item == null)
                continue;
            carriedWealth += GrandExchange.getPrice(item.getId()) * item.getAmount();
        }
        return wilderness ? riskedWealth : carriedWealth;
    }

    public void refreshMoneyPouch() {
        getPackets().sendRunScript(5560, getMoneyPouch().getTotal());
    }

    public boolean hasMoney(int amount) {
        if (getMoneyPouch().getTotal() >= amount)
            return true;
        else if (inventory.getNumberOf(995) >= amount)
            return true;
        else
            return false;
    }

    public boolean takeMoney(int amount) {
        if (getMoneyPouch().getTotal() >= amount) {
            getMoneyPouch().takeMoneyFromPouch(amount);
            return true;
        } else if (inventory.getNumberOf(995) >= amount) {
            inventory.deleteItem(995, amount);
            return true;
        } else {
            return false;
        }
    }

    public Player(String password) {
        super(Settings.ECONOMY_MODE > 0 ? TeleportStore.EDGEVILLE_PVP_INSTANCE.getTile()
                : Settings.START_PLAYER_LOCATION);
        setHitpoints(Settings.START_PLAYER_HITPOINTS);
        this.password = password;
        farmingManager = new FarmingManager();
        grotwormLair = new GrotwormLair();
        runicStaff = new GreaterRunicStaff();
        ironman = new Ironman();
        advlog = new AdventuresLog();
        appearence = new Appearence();
        dungManager = new DungManager();
        house = new House();
        inventory = new Inventory();
        equipment = new Equipment();
        skills = new Skills();
        combatDefinitions = new CombatDefinitions();
        prayer = new PrayerBook();
        bank = new Bank();
        controlerManager = new ControlerManager();
        musicsManager = new MusicsManager();
        emotesManager = new EmotesManager();
        friendsIgnores = new FriendsIgnores();
        notes = new Notes();
        toolbelt = new Toolbelt();
        dominionTower = new DominionTower();
        charges = new ChargesManager();
        auraManager = new AuraManager();
        taskManager = new TaskManager();
        petManager = new PetManager();
        playerRank = new PlayerRank();
        clueScrollRewards = new ItemsContainer<Item>(10, true);
        varsManager = new VarsManager(this);
        cstore = new CustomStore(this);
        geManager = new GrandExchangeManager();
        slayerManager = new SlayerManager();
        squealOfFortune = new SquealOfFortune();
        treasureTrailsManager = new TreasureTrailsManager();
        godwarsKillcount = new GodwarsKillcount();
        bossKillcount = new HashMap<>();
        treasureTrailCount = new HashMap<>();
        CustomDuelRule = new HashMap<>();
        lividFarm = new LividFarm();
        artisan = new ArtisanWorkshop();
        for (Puzzles puzzle : Puzzles.values()) {
            puzzleBox = new PuzzleBox(this, puzzle.getFirstTileId());
        }
        runEnergy = 100;
        allowChatEffects = true;
        mouseButtons = true;
        acceptAid = true;
        pouches = new int[4];
        warriorPoints = new double[6];
        resetBarrows();
        lodestone = new boolean[]{false, false, false, false, false, false, false, false, false, true, false, false,
                false, false, false};
        SkillCapeCustomizer.resetSkillCapes(this);
        ownedObjectsManagerKeys = new LinkedList<String>();
        passwordList = new ArrayList<String>();
        ipList = new ArrayList<String>();
        unlockedItems = new ArrayList<UnlockableItems>();
        untradeables = new ItemsContainer<Item>(28, false);
        runePouch = new ItemsContainer<Item>(3, false);
        barrowsRewards = new ItemsContainer<Item>(3, false);
        lastSkill = new ArrayList<String>();
        lastlevelUp = new ArrayList<String>();
        slayerTask = new HashMap<Integer, String>();
    }

    /*
     * Claim Untradeables
     */

    public ItemsContainer<Item> getUntradeables() {
        return untradeables;
    }

    private ItemsContainer<Item> runePouch;

    public ItemsContainer<Item> getRunePouch() {
        return runePouch;
    }

    public ItemsContainer<Item> getBarrowsRewards() {
        return barrowsRewards;
    }

    public void init(Session session, String string, IsaacKeyPair isaacKeyPair) {
        username = string;
        this.session = session;
        this.isaacKeyPair = isaacKeyPair;
        interfaceManager = new InterfaceManager(this);
        // startLobby();//this wanst here, i added this cus it wasnt called anywhere
        World.addLobbyPlayer(this);// .addLobbyPlayer(this);
        if (Settings.DEBUG) {
            Logger.log(this, new StringBuilder("Lobby Inited Player: ").append(string).append(", pass: ")
                    .append(password).toString());
        }
    }

    public void startLobby() {
        sendLobbyConfigs(this);
        friendsIgnores.setPlayer(this);
        friendsIgnores.init();
        getPackets().sendFriendsChatChannel();
        friendsIgnores.sendFriendsMyStatus(true);
    }

    public void sendLobbyConfigs(Player player) {
        for (int i = 0; i < Utils.DEFAULT_LOBBY_CONFIGS.length; i++) {
            int val = Utils.DEFAULT_LOBBY_CONFIGS[i];
            if (val != 0) {
                player.getPackets().sendConfig(i, val);
            }
        }
    }

    public void init(Session session, String username, int displayMode, int screenWidth, int screenHeight,
                     MachineInformation machineInformation, IsaacKeyPair isaacKeyPair) {
        if (dominionTower == null)
            dominionTower = new DominionTower();
        if (charges == null)
            charges = new ChargesManager();
        if (grotwormLair == null)
            grotwormLair = new GrotwormLair();
        if (treasureTrailsManager == null)
            treasureTrailsManager = new TreasureTrailsManager();
        if (auraManager == null)
            auraManager = new AuraManager();
        if (playerRank == null)
            playerRank = new PlayerRank();
        if (questManager == null)
            questManager = new QuestManager(this);
        for (Puzzles puzzle : Puzzles.values()) {
            if (getPuzzleBox() == null)
                puzzleBox = new PuzzleBox(this, puzzle.getFirstTileId());
        }
        if (taskManager == null)
            taskManager = new TaskManager();
        if (runicStaff == null)
            runicStaff = new GreaterRunicStaff();
        if (ironman == null)
            ironman = new Ironman();
        if (toolbelt == null)
            toolbelt = new Toolbelt();
        if (petManager == null)
            petManager = new PetManager();
        if (bossKillcount == null)
            bossKillcount = new HashMap<>();
        if (treasureTrailCount == null)
            treasureTrailCount = new HashMap<>();
        if (CustomDuelRule == null)
            CustomDuelRule = new HashMap<>();
        if (geManager == null)
            geManager = new GrandExchangeManager();
        if (squealOfFortune == null)
            squealOfFortune = new SquealOfFortune();
        if (pinpinpin != 1) {
            pinpinpin = 1;
            bankpins = new int[]{0, 0, 0, 0};
            confirmpin = new int[]{0, 0, 0, 0};
            openBankPin = new int[]{0, 0, 0, 0};
            changeBankPin = new int[]{0, 0, 0, 0};
        }
        if (slayerManager == null) {
            skills.resetSkillNoRefresh(Skills.SLAYER);
            slayerManager = new SlayerManager();
        }
        if (artisan == null)
            artisan = new ArtisanWorkshop();

        artisan.setPlayer(this);
        if (godwarsKillcount == null)
            godwarsKillcount = new GodwarsKillcount();
        if (varsManager == null)
            varsManager = new VarsManager(this);
        if (cstore == null)
            cstore = new CustomStore(this);
        if (notes == null)
            notes = new Notes();
        if (dropLogs == null)
            dropLogs = new DropLogs(this);
        if (pouch == null)
            pouch = new MoneyPouch(this);
        if (getPresetManager() == null)
            setPresetManager(new PresetManager());
        if (dungManager == null)
            dungManager = new DungManager();
        if (clueScrollRewards == null) {
            clueScrollRewards = new ItemsContainer<Item>(10, true);
        }
        if (untradeables == null)
            untradeables = new ItemsContainer<Item>(28, false);
        if (runePouch == null)
            runePouch = new ItemsContainer<Item>(3, false);
        if (barrowsRewards == null)
            barrowsRewards = new ItemsContainer<Item>(3, false);
        if (lodestone == null || lodestone[9] != true)
            lodestone = new boolean[]{false, false, false, false, false, false, false, false, false, true, false,
                    false, false, false, false};
        this.session = session;
        this.username = username;
        this.displayMode = displayMode;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.machineInformation = machineInformation;
        this.isaacKeyPair = isaacKeyPair;
        if (lividFarm == null)
            lividFarm = new LividFarm();
        if (varBitList == null)
            varBitList = new HashMap<Integer, Integer>();
        if (temporaryVarBits == null)
            temporaryVarBits = new HashMap<Integer, Integer>();
        if (toggles == null)
            toggles = new HashMap<String, Object>();
        if (attackedBy == null)
            attackedBy = new HashMap<Player, Integer>();
        if (staffCharges == null)
            staffCharges = new HashMap<Integer, Item[]>();
        creationKiln = new CreationKiln(this);
        interfaceManager = new InterfaceManager(this);
        dialogueManager = new DialogueManager(this);
        hintIconsManager = new HintIconsManager(this);
        priceCheckManager = new PriceCheckManager(this);
        localPlayerUpdate = new LocalPlayerUpdate(this);
        localNPCUpdate = new LocalNPCUpdate(this);
        actionManager = new ActionManager(this);
        if (farmingManager == null)
            farmingManager = new FarmingManager();
        farmingManager.setPlayer(this);
        treasureTrailsManager.setPlayer(this);
        lividFarm.setPlayer(this);
        playerRank.setPlayer(this);
        getPresetManager().setPlayer(this);
        dungManager.setPlayer(this);
        cutscenesManager = new CutscenesManager(this);
        runicStaff.setPlayer(this);
        house.setPlayer(this);
        ironman.setPlayer(this);
        toolbelt.setPlayer(this);
        advlog.setPlayer(this);
        notes.setPlayer(this);
        pouch = new MoneyPouch(this);
        trade = new Trade(this);
        tradestore = new TradeStore(this);
        assist = new AssistManager(this);
        appearence.setPlayer(this);
        inventory.setPlayer(this);
        equipment.setPlayer(this);
        skills.setPlayer(this);
        combatDefinitions.setPlayer(this);
        prayer.setPlayer(this);
        bank.setPlayer(this);
        controlerManager.setPlayer(this);
        musicsManager.setPlayer(this);
        emotesManager.setPlayer(this);
        friendsIgnores.setPlayer(this);
        grotwormLair.setPlayer(this);
        dominionTower.setPlayer(this);
        auraManager.setPlayer(this);
        charges.setPlayer(this);
        taskManager.setPlayer(this);
        petManager.setPlayer(this);
        geManager.setPlayer(this);
        slayerManager.setPlayer(this);
        squealOfFortune.setPlayer(this);
        godwarsKillcount.setPlayer(this);
        setDirection(Utils.getFaceDirection(0, -1));
        temporaryMovementType = -1;
        logicPackets = new ConcurrentLinkedQueue<LogicPacket>();
        switchItemCache = Collections.synchronizedList(new ArrayList<Integer>());

        switchTakeOffItemCache = Collections.synchronizedList(new ArrayList<Integer>());

        initEntity();
        packetsDecoderPing = Utils.currentTimeMillis();
        World.addPlayer(this);
        World.updateEntityRegion(this);
        if (passwordList == null)
            passwordList = new ArrayList<String>();
        if (ipList == null)
            ipList = new ArrayList<String>();
        if (unlockedItems == null)
            unlockedItems = new ArrayList<UnlockableItems>();
        if (lastSkill == null)
            lastSkill = new ArrayList<String>();
        if (lastlevelUp == null)
            lastlevelUp = new ArrayList<String>();
        if (slayerTask == null)
            slayerTask = new HashMap<Integer, String>();
        updateIPnPass();
        if (getSkullSkeptreCharges() <= 0)
            setSkullSkeptreCharges(5);
    }

    public boolean hasWildstalker() {
        for (int itemId = 20801; itemId < 20806; itemId++) {
            if (getInventory().containsItem(itemId, 1) || getBank().getItem(itemId) != null
                    || getEquipment().getHatId() == itemId)
                return true;
        }
        return false;
    }

    public boolean hasDuellist() {
        for (int itemId = 20795; itemId < 20800; itemId++) {
            if (getInventory().containsItem(itemId, 1) || getBank().getItem(itemId) != null
                    || getEquipment().getHatId() == itemId)
                return true;
        }
        return false;
    }

    public boolean containsDuellist() {
        for (int itemId = 20795; itemId < 20800; itemId++) {
            if (getInventory().containsItem(itemId, 1) || getEquipment().getHatId() == itemId)
                return true;
        }
        return false;
    }

    public static int ATTACK = Skills.ATTACK;

    public static int STRENGTH = Skills.STRENGTH;

    public static int DEFENCE = Skills.DEFENCE;

    public static int RANGE = Skills.RANGE;

    public static int MAGIC = Skills.MAGIC;

    public static int HITPOINTS = Skills.HITPOINTS;

    public static int PRAYER = Skills.PRAYER;

    public int getMaxedLevel(int skillId) {
        if (skillId == STRENGTH) {
            return maxed_str;
        }
        if (skillId == ATTACK)
            return maxed_atk;
        if (skillId == DEFENCE)
            return maxed_def;
        if (skillId == RANGE)
            return maxed_range;
        if (skillId == MAGIC)
            return maxed_mage;
        if (skillId == HITPOINTS)
            return maxed_hp;
        if (skillId == PRAYER)
            return maxed_prayer;
        return skillId;
    }

    public void setFamiliarBoB(ItemsContainer<Item> items) {
        this.bobItems = items;
    }

    public void setFamiliarPouch(Summoning.Pouch familiarPouch) {
        this.familiarPouch = familiarPouch;
    }

    public enum Limits {

        ATTACK_LEVEL(ATTACK, 60),

        STRENGTH_LEVEL(STRENGTH, 70),

        DEFENCE_LEVEL(DEFENCE, 20),

        RANGE_LEVEL(RANGE, 70),

        MAGIC_LEVEL(MAGIC, 70),

        HITPOINTS_LEVEL(HITPOINTS, 70),

        PRAYER_LEVEL(PRAYER, 43);;

        private int skill;
        private int level;

        Limits(int skill, int level) {
            this.setSkill(skill);
            this.setLevel(level);
        }

        public static Limits forId(int i) {
            for (Limits s : Limits.values()) {
                if (s.getSkill() == i) {
                    return s;
                }
            }
            return null;
        }

        public int getSkill() {
            return skill;
        }

        public void setSkill(int skill) {
            this.skill = skill;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }

    public int maxed_str = 99;

    public int maxed_def = 99;

    public int maxed_atk = 99;

    public int maxed_range = 99;

    public int maxed_mage = 99;

    public int maxed_prayer = 99;

    public int maxed_hp = 99;

    public void startGame(Player player) {
        if (!recievedStarter)
            StarterProtection.sendStarterPack(player);
    }

    public static void archiveChat(Player player, String message) {
        try {
            String location = "";
            location = "data/logs/chat/" + player.getUsername() + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(location, true));
            writer.write("[" + currentTime("dd MMMMM yyyy 'at' hh:mm:ss z") + "] - " + message);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String currentTime(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    public void setWildernessSkull() {
        skullDelay = 2000; // 20minutes
        skullId = 0;
        appearence.generateAppearenceData();
    }

    public void setFightPitsSkull() {
        skullDelay = Integer.MAX_VALUE;
        appearence.generateAppearenceData();
    }

    public void setSkullInfiniteDelay(int skullId) {
        skullDelay = Integer.MAX_VALUE;
        this.skullId = skullId;
        appearence.generateAppearenceData();
    }

    public void removeSkull() {
        skullDelay = -1;
        appearence.generateAppearenceData();
    }

    public boolean hasSkull() {
        return skullDelay > 0;
    }

    public int setSkullDelay(int delay) {
        return this.skullDelay = delay;
    }

    public void addGlobalObjects() {
        List<WorldObject> allObjects = new ArrayList<>();
        for (WorldObject object : GlobalObjectAddition.getObjects()) {
            allObjects.add(object);
        }
        if (allObjects != null) {
            for (WorldObject object : allObjects) {
                World.spawnObject(object);
            }
        }
        //WorldObject altar = new WorldObject(409, 10, 0, 3092, 3488, 0);
        //World.spawnObject(altar);//twice to replace globalobject
    }

    public void removeGlobalObjects() {
        List<WorldTile> allObjects = new ArrayList<>();
        for (WorldTile tile : GlobalObjectDeletion.getTiles()) {
            allObjects.add(tile);
        }
        /**
         * Removes ALL objects in an area
         */
        if (allObjects != null) {
            for (WorldTile tile : allObjects) {
                WorldObject object = World.getStandardWallObject(tile);
                if (object != null) {
                    if (object != null && (object.getId() == 24397))//temp fix for bank booths in edgeville bank
                        object = World.getObjectWithType(tile, 10);
                    else
                        World.removeObject(object);
                    object = null;
                }
                if (object == null)
                    object = World.getStandardFloorObject(tile);
                if (object != null) {
                    World.removeObject(object);
                    object = null;
                }
                if (object == null)
                    object = World.getObjectWithType(tile, 10);
                if (object != null) {
                    World.removeObject(object);
                    object = null;
                }
                if (object == null)
                    object = World.getObjectWithType(tile, 4);
                if (object != null) {
                    World.removeObject(object);
                    object = null;
                }
                if (object == null)
                    object = World.getStandardFloorDecoration(tile);
                if (object != null) {
                    if (object != null && (object.getId() == 39639))//temp fix for bank carpet in edgeville bank
                        object = World.getObjectWithType(tile, 10);
                    else
                        World.removeObject(object);
                    object = null;
                }
                if (object == null)
                    object = World.getStandardWallDecoration(tile);
                if (object != null) {
                    World.removeObject(object);
                    object = null;
                }
                if (object == null) {
                    //World.unclipTile(tile);
                    continue;
                }
                World.removeObject(object);
            }
        }

        /**
         *  used to remove objects in an area, but it skips floordecorations like flowers/grass
         */
        List<WorldTile> allButFloorDecoration = new ArrayList<>();
        for (WorldTile tile : GlobalObjectDeletion.getTiles()) {
            allButFloorDecoration.add(tile);
        }
        for (int x = 3072; x <= 3104; x++) {//north of man house
            for (int y = 3515; y <= 3519; y++) {
                allButFloorDecoration.add(new WorldTile(x, y, 0));
                allButFloorDecoration.add(new WorldTile(x, y, 1));
                allButFloorDecoration.add(new WorldTile(x, y, 2));
                allButFloorDecoration.add(new WorldTile(x, y, 3));
                allButFloorDecoration.add(new WorldTile(x, y, 4));
            }
        }
        if (allButFloorDecoration != null) {
            for (WorldTile tile : allButFloorDecoration) {
                WorldObject object = World.getStandardWallObject(tile);
                if (object != null) {
                    if (object != null)//temp fix for bank booths in edgeville bank
                        object = World.getObjectWithType(tile, 10);
                    else
                        World.removeObject(object);
                    object = null;
                }
                if (object == null)
                    object = World.getStandardFloorObject(tile);
                if (object != null) {
                    World.removeObject(object);
                    object = null;
                }
                if (object == null)
                    object = World.getObjectWithType(tile, 10);
                if (object != null) {
                    World.removeObject(object);
                    object = null;
                }
                if (object == null)
                    object = World.getObjectWithType(tile, 4);
                if (object != null) {
                    World.removeObject(object);
                    object = null;
                }
                if (object == null)
                    object = World.getObjectWithType(tile, 3);
                if (object != null) {
                    World.removeObject(object);
                    object = null;
                }
                if (object == null)
                    object = World.getStandardWallDecoration(tile);
                if (object != null) {
                    World.removeObject(object);
                    object = null;
                }
                if (object == null) {
                    //World.unclipTile(tile);
                    continue;
                }
                World.removeObject(object);
            }
        }
    }

    public void refreshGlobalItems() {
        for (int regionId : getMapRegionsIds()) {
            List<FloorItem> floorItems = World.getRegion(regionId).getGroundItems();
            if (floorItems == null)
                continue;
            for (FloorItem item : floorItems) {
                if (item.isInvisible() && (!item.isSpawned()) || item.getTile().getPlane() != getPlane())
                    continue;
                getPackets().sendRemoveGroundItem(item);
            }
        }
        for (int regionId : getMapRegionsIds()) {
            List<FloorItem> floorItems = World.getRegion(regionId).getGroundItems();
            if (floorItems == null)
                continue;
            for (FloorItem item : floorItems) {
                if ((item.isInvisible()) && (!item.isSpawned()) || item.getTile().getPlane() != getPlane())
                    continue;
                getPackets().sendGroundItem(item);
            }
        }
    }

    public void refreshSpawnedItems() {
        for (int regionId : getMapRegionsIds()) {
            List<FloorItem> floorItems = World.getRegion(regionId).getGroundItems();
            if (floorItems == null)
                continue;
            for (FloorItem item : floorItems) {
                if (item.isInvisible() && (item.hasOwner() && !getUsername().equals(item.getOwner()))
                        || item.getTile().getPlane() != getPlane()
                        || !getUsername().equals(item.getOwner()) && !ItemConstants.isTradeable(item))
                    continue;
                getPackets().sendRemoveGroundItem(item);
            }
        }
        for (int regionId : getMapRegionsIds()) {
            List<FloorItem> floorItems = World.getRegion(regionId).getGroundItems();
            if (floorItems == null)
                continue;
            for (FloorItem item : floorItems) {
                if ((item.isInvisible()) && (item.hasOwner() && !getUsername().equals(item.getOwner()))
                        || item.getTile().getPlane() != getPlane()
                        || !getUsername().equals(item.getOwner()) && !ItemConstants.isTradeable(item))
                    continue;
                getPackets().sendGroundItem(item);
            }
        }
    }

    public void refreshSpawnedObjects() {
        removeGlobalObjects();
        addGlobalObjects();
        for (int regionId : getMapRegionsIds()) {
            List<WorldObject> spawnedObjects = World.getRegion(regionId).getSpawnedObjects();
            if (spawnedObjects != null) {
                for (WorldObject object : spawnedObjects) {
                    if (object.getPlane() == getPlane())
                        getPackets().sendSpawnedObject(object);
                }
            }
            List<WorldObject> removedObjects = World.getRegion(regionId).getRemovedOriginalObjects();
            if (removedObjects != null) {
                for (WorldObject object : removedObjects)
                    if (object.getPlane() == getPlane()) {
                        World.removeObject(object);
                        getPackets().sendDestroyObject(object);
                    }
            }

            /*
             * Region r = World.getRegion(getRegionY() | (getRegionX() << 8)); r =
             * World.getRegion(getLastRegionId(), false); r.loadRegionMap();
             */
        }
    }

    public void start() {
        loadMapRegions();
        started = true;
        run();
        if (isDead())
            sendDeath(null);
    }

    public void stopAll() {
        stopAll(true);
    }

    public void stopAll(boolean stopWalk) {
        stopAll(stopWalk, true);
    }

    public void stopAll(boolean stopWalk, boolean stopInterface) {
        stopAll(stopWalk, stopInterface, true);
    }

    public void stopAll(boolean stopWalk, boolean stopInterfaces, boolean stopActions) {
        coordsEvent = null;
        routeEvent = null;
        if (stopInterfaces)
            closeInterfaces();
        if (stopWalk)
            resetWalkSteps();
        if (stopActions)
            actionManager.forceStop();
        combatDefinitions.resetSpells(false);
    }

    public void stopAll(boolean stopWalk, boolean stopInterfaces, boolean stopActions, boolean stopSpecial) {
        coordsEvent = null;
        routeEvent = null;
        if (stopInterfaces)
            closeInterfaces();
        if (stopWalk)
            resetWalkSteps();
        if (stopActions)
            actionManager.forceStop();
        if (stopSpecial)
            combatDefinitions.desecreaseSpecialAttack(0);
        combatDefinitions.resetSpells(false);
    }

    @Override
    public void reset(boolean attributes) {
        super.reset(attributes);
        refreshHitPoints();
        hintIconsManager.removeAll();
        skills.restoreSkills();
        combatDefinitions.resetSpecialAttack();
        prayer.reset();
        combatDefinitions.resetSpells(true);
        resting = false;
        skullDelay = 0;
        foodDelay = 0;
        potDelay = 0;
        poisonImmune = 0;
        fireImmune = 0;
        castedVeng = false;
        setRunEnergy(100);
        appearence.generateAppearenceData();
    }

    @Override
    public void reset() {
        reset(true);
    }

    public void closeInterfaces() {
        if (interfaceManager.containsScreenInter())
            interfaceManager.closeScreenInterface();
        if (interfaceManager.containsInventoryInter())
            interfaceManager.closeInventoryInterface();
        dialogueManager.finishDialogue();
        if (closeInterfacesEvent != null) {
            closeInterfacesEvent.run();
            closeInterfacesEvent = null;
        }
    }

    public double getBonusPoints() {
        double points = 1.0;
        if (isMember())
            points += 0.50;
        points *= Settings.BONUS_POINTS_WEEK_MULTIPLIER;
        return points;
    }

    public double getBonusExp() {
        double exp = 1.0;
        for (Rank rank : getPlayerRank().getRank()) {
            if (rank == null)
                continue;
            exp += (rank.getXpBoost() - 1.0);
        }
        if (isMember())
            exp += 0.50;
        if (getAuraManager().usingWisdom())
            exp += 0.25;
        return exp;
    }

    public String checkKillcount(String name) {
        return name + ": <col=FFFF00>"
                + (getBossKillcount().get(name) != null
                ? Utils.getFormattedNumber(getBossKillcount().get(name).intValue(), ',')
                : "0");
    }

    public int checkKillsInt(String name) {
        return (getBossKillcount().get(name) != null ? getBossKillcount().get(name).intValue() : 0);
    }

    public void sendFriendsOnline() {
        int online = 0;
        for (Player p2 : World.getPlayers()) {
            if (getFriendsIgnores().getFriends().contains(p2.getUsername()) && p2.getFriendsIgnores().isOnline(p2)) {
                online++;
            }
            getPackets().sendIComponentText(550, 18, "<col=ffc800>~ " + Settings.SERVER_NAME + " ~</col> "
                    + (getFriendsIgnores().getFriends().size() == 0 ? ""
                    : "<br>Friends online: " + online + " / " + getFriendsIgnores().getFriends().size()));
        }
    }

    public String getStaffOnline() {
        String staff = new String();
        for (Player p2 : World.getPlayers()) {
            if (p2.getPlayerRank().isStaff())
                staff += "<img=" + p2.getRights() + ">" + " " + p2.getDisplayName().toString() + "<br>";
        }
        return staff;
    }

    public boolean hasStaffOnline() {
        for (Player p2 : World.getPlayers()) {
            if (p2.getPlayerRank().isStaff())
                return true;
        }
        return false;
    }

    public void refreshTask() {
        if (getSlayerManager().getCurrentTask() != null) {
            int ct = getSlayerManager().getCount();
            String name = getSlayerManager().getCurrentTask().getName().replace("$", "'");
            // String location =
            // getSlayerManager().getCurrentTask().getTips()[0];
            writeTask(ct + " " + name + (ct > 1 ? "'s left" : " left"));
            String[] tipDialouges = getSlayerManager().getCurrentTask().getTips();
            if (tipDialouges != null && tipDialouges.length != 0) {
                String chosenDialouge = tipDialouges[tipDialouges.length - 1];
                writeTaskTip(chosenDialouge);
            }
        } else {
            writeTask(null);
            writeTaskTip(null);
        }
    }

    public void writeTask(String input) {
        slayerTask.put(1, input);
    }

    public void writeTaskTip(String input) {
        slayerTask.put(2, input);
    }

    public String getSlayerTask() {
        return slayerTask.get(1);
    }

    public String getSlayerTaskTip() {
        return slayerTask.get(2);
    }

    public void sendSlayerAssign() {
        getInterfaceManager().sendInterface(161);
    }

    public String grammar(WorldObject object) {
        String name = object.getDefinitions().name;
        for (String vowel : Settings.Vowels)
            if (name.toLowerCase().startsWith(vowel))
                return "an";
        return "a";
    }

    public String grammar(Item item) {
        String name = item.getDefinitions().getName();
        for (String vowel : Settings.Vowels)
            if (name.toLowerCase().startsWith(vowel))
                return "an";
        return "a";
    }

    public String grammar(FloorItem item) {
        String name = item.getDefinitions().getName();
        for (String vowel : Settings.Vowels)
            if (name.toLowerCase().startsWith(vowel))
                return "an";
        return "a";
    }

    public String grammar(NPC npc) {
        String name = npc.getDefinitions().getName();
        for (String vowel : Settings.Vowels)
            if (name.toLowerCase().startsWith(vowel))
                return "an";
        return "a";
    }

    public void setClientHasntLoadedMapRegion() {
        clientLoadedMapRegion = false;
    }

    public boolean hasMaxCapeRequirements() {
        if (getSkills().getLevelForXp(Skills.ATTACK) >= 99 && getSkills().getLevelForXp(Skills.STRENGTH) >= 99
                && getSkills().getLevelForXp(Skills.DEFENCE) >= 99 && getSkills().getLevelForXp(Skills.HITPOINTS) >= 99
                && getSkills().getLevelForXp(Skills.RANGE) >= 99 && getSkills().getLevelForXp(Skills.MAGIC) >= 99
                && getSkills().getLevelForXp(Skills.RUNECRAFTING) >= 99
                && getSkills().getLevelForXp(Skills.FISHING) >= 99 && getSkills().getLevelForXp(Skills.AGILITY) >= 99
                && getSkills().getLevelForXp(Skills.COOKING) >= 99 && getSkills().getLevelForXp(Skills.PRAYER) >= 99
                && getSkills().getLevelForXp(Skills.THIEVING) >= 99 && getSkills().getLevelForXp(Skills.MINING) >= 99
                && getSkills().getLevelForXp(Skills.SMITHING) >= 99 && getSkills().getLevelForXp(Skills.SUMMONING) >= 99
                && getSkills().getLevelForXp(Skills.SLAYER) >= 99 && getSkills().getLevelForXp(Skills.CRAFTING) >= 99
                && getSkills().getLevelForXp(Skills.WOODCUTTING) >= 99
                && getSkills().getLevelForXp(Skills.FIREMAKING) >= 99
                && getSkills().getLevelForXp(Skills.FLETCHING) >= 99
                && getSkills().getLevelForXp(Skills.HERBLORE) >= 99)
            return true;
        return false;
    }

    public void checkMaxCape() {
        for (int skill = 0; skill < 24; skill++) {
            if (getSkills().getLevelForXp(skill) < 99) {
                getDialogueManager().startDialogue("You need at least 99 in all skills to wear max cape.");
                sm("You need at least 99 in all skills to wear max cape.");
                getEquipment().deleteItem(20767, 1);
                getEquipment().deleteItem(20768, 1);
                getEquipment().refresh(1);
                getAppearence().generateAppearenceData();
            }
        }
    }

    public void checkCompletionistTrimmed(Player player) {
        int COMP_HOOD = 20772, COMP_CAPE = 20771;
        if (!player.hasTrimCompReqs()) {
            player.lock(1);
            Dialogue.sendItemDialogueNoContinue(player, COMP_CAPE, 1,
                    "There are new requirements for this cape. If you wish to equip it again, you must complete the requirements. <br><br>This will close in 10...");
            sm("There are new requirements for this cape. If you wish to equip it again, you must complete the requirements.");
            if (player.getEquipment().getHatId() == COMP_HOOD) {
                player.getEquipment().deleteItem(COMP_HOOD, 1);
                if (player.getInventory().hasFreeSlots())
                    player.getInventory().addItem(COMP_HOOD, 1);
                else
                    player.getBank().addItem(COMP_HOOD, 1, true);
            }
            if (player.getEquipment().getCapeId() == COMP_CAPE) {
                player.getEquipment().deleteItem(COMP_CAPE, 1);
                if (player.getInventory().hasFreeSlots())
                    player.getInventory().addItem(COMP_CAPE, 1);
                else
                    player.getBank().addItem(COMP_CAPE, 1, true);
            }
        }
        player.getEquipment().refresh();
        player.getAppearence().generateAppearenceData();
        player.getDialogueManager().startDialogue("SimpleItemMessage", COMP_CAPE,
                "There are new requirements for this cape. If you wish to equip it again, you must complete the requirements.");
    }

    public boolean hasCompletionistStatRequirements() {
        if (getSkills().getLevelForXp(Skills.ATTACK) >= 99 && getSkills().getLevelForXp(Skills.STRENGTH) >= 99
                && getSkills().getLevelForXp(Skills.DEFENCE) >= 99 && getSkills().getLevelForXp(Skills.HITPOINTS) >= 99
                && getSkills().getLevelForXp(Skills.RANGE) >= 99 && getSkills().getLevelForXp(Skills.MAGIC) >= 99
                && getSkills().getLevelForXp(Skills.RUNECRAFTING) >= 99
                && getSkills().getLevelForXp(Skills.FISHING) >= 99 && getSkills().getLevelForXp(Skills.AGILITY) >= 99
                && getSkills().getLevelForXp(Skills.COOKING) >= 99 && getSkills().getLevelForXp(Skills.PRAYER) >= 99
                && getSkills().getLevelForXp(Skills.THIEVING) >= 99 && getSkills().getLevelForXp(Skills.MINING) >= 99
                && getSkills().getLevelForXp(Skills.SMITHING) >= 99 && getSkills().getLevelForXp(Skills.SUMMONING) >= 99
                && getSkills().getLevelForXp(Skills.SLAYER) >= 99 && getSkills().getLevelForXp(Skills.CRAFTING) >= 99
                && getSkills().getLevelForXp(Skills.WOODCUTTING) >= 99
                && getSkills().getLevelForXp(Skills.FIREMAKING) >= 99
                && getSkills().getLevelForXp(Skills.FLETCHING) >= 99 && getSkills().getLevelForXp(Skills.HERBLORE) >= 99
                && getSkills().getLevelForXp(Skills.DUNGEONEERING) >= 120)
            return true;
        return false;
    }

    public boolean hasCompletionistRequirements() {
        if (getSkills().getLevelForXp(Skills.ATTACK) >= 99 && getSkills().getLevelForXp(Skills.STRENGTH) >= 99
                && getSkills().getLevelForXp(Skills.DEFENCE) >= 99 && getSkills().getLevelForXp(Skills.HITPOINTS) >= 99
                && getSkills().getLevelForXp(Skills.RANGE) >= 99 && getSkills().getLevelForXp(Skills.MAGIC) >= 99
                && getSkills().getLevelForXp(Skills.RUNECRAFTING) >= 99
                && getSkills().getLevelForXp(Skills.FISHING) >= 99 && getSkills().getLevelForXp(Skills.AGILITY) >= 99
                && getSkills().getLevelForXp(Skills.COOKING) >= 99 && getSkills().getLevelForXp(Skills.PRAYER) >= 99
                && getSkills().getLevelForXp(Skills.THIEVING) >= 99 && getSkills().getLevelForXp(Skills.MINING) >= 99
                && getSkills().getLevelForXp(Skills.SMITHING) >= 99 && getSkills().getLevelForXp(Skills.SUMMONING) >= 99
                && getSkills().getLevelForXp(Skills.SLAYER) >= 99 && getSkills().getLevelForXp(Skills.CRAFTING) >= 99
                && getSkills().getLevelForXp(Skills.WOODCUTTING) >= 99
                && getSkills().getLevelForXp(Skills.FIREMAKING) >= 99
                && getSkills().getLevelForXp(Skills.FLETCHING) >= 99 && getSkills().getLevelForXp(Skills.HERBLORE) >= 99
                && getSkills().getLevelForXp(Skills.DUNGEONEERING) >= 120 && isCompletedFightKiln()
                && isCompletedFightCaves() && checkKillsInt("King Black Dragon") >= 50
                && checkKillsInt("Corporeal Beast") >= 25 && checkKillsInt("General Graardor") >= 25
                && checkKillsInt("Commander Zilyana") >= 25 && checkKillsInt("K'ril Tsutsaroth") >= 25
                && checkKillsInt("Kree'arra") >= 25 && checkKillsInt("Dagannoth Rex") >= 25
                && checkKillsInt("Dagannoth Prime") >= 25 && checkKillsInt("Dagannoth Supreme") >= 25
                && getTaskManager().hasCompletedAllTasks())
            return true;
        return false;
    }

    public boolean hasTrimCompReqs() {
        if (getSkills().getLevelForXp(Skills.ATTACK) >= 99 && getSkills().getLevelForXp(Skills.STRENGTH) >= 99
                && getSkills().getLevelForXp(Skills.DEFENCE) >= 99 && getSkills().getLevelForXp(Skills.HITPOINTS) >= 99
                && getSkills().getLevelForXp(Skills.RANGE) >= 99 && getSkills().getLevelForXp(Skills.MAGIC) >= 99
                && getSkills().getLevelForXp(Skills.RUNECRAFTING) >= 99
                && getSkills().getLevelForXp(Skills.FISHING) >= 99 && getSkills().getLevelForXp(Skills.AGILITY) >= 99
                && getSkills().getLevelForXp(Skills.COOKING) >= 99 && getSkills().getLevelForXp(Skills.PRAYER) >= 99
                && getSkills().getLevelForXp(Skills.THIEVING) >= 99 && getSkills().getLevelForXp(Skills.MINING) >= 99
                && getSkills().getLevelForXp(Skills.SMITHING) >= 99 && getSkills().getLevelForXp(Skills.SUMMONING) >= 99
                && getSkills().getLevelForXp(Skills.SLAYER) >= 99 && getSkills().getLevelForXp(Skills.CRAFTING) >= 99
                && getSkills().getLevelForXp(Skills.WOODCUTTING) >= 99
                && getSkills().getLevelForXp(Skills.FIREMAKING) >= 99
                && getSkills().getLevelForXp(Skills.FLETCHING) >= 99 && getSkills().getLevelForXp(Skills.HERBLORE) >= 99
                && getSkills().getLevelForXp(Skills.DUNGEONEERING) >= 120 && isCompletedFightKiln()
                && isCompletedFightCaves() && getSlayerManager().getCompletedTasks() >= 50
                && getTaskManager().hasCompletedAllTasks())
            return true;
        return false;
    }

    public void checkCompletionist(Player player) {
        int COMP_HOOD = 20770, COMP_CAPE = 20769;
        if (!player.hasCompletionistRequirements()) {
            player.lock(9);
            Dialogue.sendItemDialogueNoContinue(player, COMP_CAPE, 1,
                    "There are new requirements for this cape. If you wish to equip it again, you must complete the requirements. <br><br>This will close in 10...");
            if (player.getEquipment().getHatId() == COMP_HOOD) {
                player.getEquipment().deleteItem(COMP_HOOD, 1);
                if (player.getInventory().hasFreeSlots())
                    player.getInventory().addItem(COMP_HOOD, 1);
                else
                    player.getBank().addItem(COMP_HOOD, 1, true);
            }
            if (player.getEquipment().getCapeId() == COMP_CAPE) {
                player.getEquipment().deleteItem(COMP_CAPE, 1);
                if (player.getInventory().hasFreeSlots())
                    player.getInventory().addItem(COMP_CAPE, 1);
                else
                    player.getBank().addItem(COMP_CAPE, 1, true);
            }
        }
        player.getEquipment().refresh();
        player.getAppearence().generateAppearenceData();
        WorldTasksManager.schedule(new WorldTask() {
            int close = 10;

            @Override
            public void run() {
                if (close == 0) {
                    player.getInterfaceManager().closeChatBoxInterface();
                    stop();
                    return;
                }
                close--;
                Dialogue.sendItemDialogueNoContinue(player, COMP_CAPE, 1,
                        "There are new requirements for this cape. If you wish to equip it again, you must complete the requirements. <br>This will close in "
                                + close + "...");
            }
        }, 0, 1);
    }

    @Override
    public void loadMapRegions() {
        boolean wasAtDynamicRegion = isAtDynamicRegion();
        super.loadMapRegions();
        clientLoadedMapRegion = false;
        if (isAtDynamicRegion()) {
            getPackets().sendDynamicMapRegion(!started);
            if (!wasAtDynamicRegion)
                localNPCUpdate.reset();
        } else {
            getPackets().sendMapRegion(!started);
            if (wasAtDynamicRegion)
                localNPCUpdate.reset();
        }
        forceNextMapLoadRefresh = false;
    }

    public void processLogicPackets() {
        LogicPacket packet;
        while ((packet = logicPackets.poll()) != null)
            WorldPacketsDecoder.decodeLogicPacket(this, packet);
    }

    public void handleSwitch() {
        final Player instance = this;
        List<Integer> slots = getSwitchItemCache();
        int[] slot = new int[slots.size()];
        for (int i = 0; i < slot.length; i++)
            slot[i] = slots.get(i);
        if (getSwitchItemCache().size() > 0) {
            getSwitchItemCache().clear(); // CLEAN
            ButtonHandler.sendWear(instance, slot);
            itemSwitch = false;
        }
    }

    public void handleTakeoffSwitch() {
        final Player instance = this;
        WorldTasksManager.schedule(new WorldTask() {

            @Override
            public void run() {
                List<Integer> slots = getTakeOffSwitchItemCache();
                int[] slot = new int[slots.size()];
                for (int i = 0; i < slot.length; i++)
                    slot[i] = slots.get(i);
                if (getTakeOffSwitchItemCache().size() > 0) {
                    getTakeOffSwitchItemCache().clear();
                    ButtonHandler.sendRemove2(instance, slot);
                }
                itemSwitch = false;
            }
        }, 0);
    }

    public double getSpecialMaxHit() {
        int weaponId = getEquipment().getWeaponId();
        switch (weaponId) {
            case 11061:
            case 11730:
            case 23690:
            case 11698:
            case 24514:
            case 23681:
            case 11700:
            case 3204:
            case 24516:
                return 1.1;
            case 11696:
            case 24512:
            case 23680:
            case 1215:
            case 5698:
                return 1.15;
            case 11694:
            case 24510:
            case 23679:
                return 1.375;
            case 13899:
            case 13901:
            case 13905:
            case 13907:
            case 1305:
                return 1.25;
            case 13902:
            case 13904:
            case 13883:
            case 13957:
                return 1.20;
            case 1434:
                return 1.45;
            case 11235:
            case 15701:
            case 15702:
            case 15703:
            case 15704:
                return 1.270;
        }
        return 1;
    }

    public int getAttackMode() {
        if (getCombatDefinitions().getAutoCastSpell() > 0 || getCombatDefinitions().getSpellId() > 0)
            return 2;
        else if (PlayerCombat.isRanging(this) > 0)
            return 1;
        return 0;
    }

    public void restoreSkills() {
        for (int skill = 0; skill < 25; skill++) {
            if (skill == Skills.HITPOINTS || skill == Skills.SUMMONING || skill == Skills.PRAYER)
                continue;
            int currentLevel = getSkills().getLevel(skill);
            int normalLevel = getSkills().getLevelForXp(skill);
            if (currentLevel < normalLevel) {
                getSkills().set(skill, currentLevel + 1);

            }
        }
    }

    private void drainSkills() {
        for (int skill = 0; skill < 25; skill++) {
            if (skill == Skills.HITPOINTS)
                continue;
            int currentLevel = getSkills().getLevel(skill);
            int normalLevel = getSkills().getLevelForXp(skill);
            if (currentLevel > normalLevel) {
                getSkills().set(skill, currentLevel - 1);

            }
        }
    }

    private void checkTimers() {
        if (getFreezeDelay() < Utils.currentTimeMillis() && getTeleBlockDelay() < Utils.currentTimeMillis()
                && getVengDelay() < Utils.currentTimeMillis() && getOverloadDelay() < Utils.currentTimeMillis()
                && getDisruptionDelay() < Utils.currentTimeMillis()
                && getPrayerRenewalDelay() < Utils.currentTimeMillis()
                && !OwnedObjectManager.containsObjectValue(this, 6)) {
            if (getInterfaceManager().containsInterface(3039))
                getInterfaceManager().removeInterface(getInterfaceManager().isResizableScreen() ? 26 : 31, 3039);
        } else {
            if (!getInterfaceManager().containsInterface(3039))
                getInterfaceManager().sendTimerInterface();
        }
        if (OwnedObjectManager.containsObjectValue(this, 6)) {
            getPackets().sendHideIComponent(3039, 16, false);
            getPackets().sendHideIComponent(3039, 17, false);
            getPackets().sendIComponentText(3039, 17,
                    (getCannonBalls() == 0 ? "<col=ff5331>" : "") + getCannonBalls() + "");
        } else {
            if (getCannonBalls() != 0) {
                return;
            }
            getPackets().sendHideIComponent(3039, 16, true);
            getPackets().sendHideIComponent(3039, 17, true);
        }
        if (getTeleBlockDelay() >= Utils.currentTimeMillis()) {
            getPackets().sendHideIComponent(3039, 2, false);
            getPackets().sendHideIComponent(3039, 3, false);
            getPackets().sendIComponentText(3039, 3, getTimeLeft(getTeleBlockDelay()) + "");
        } else {
            getPackets().sendHideIComponent(3039, 2, true);
            getPackets().sendHideIComponent(3039, 3, true);
        }
        if (getVengDelay() >= Utils.currentTimeMillis()) {
            getPackets().sendHideIComponent(3039, 4, false);
            getPackets().sendHideIComponent(3039, 5, false);
            getPackets().sendIComponentText(3039, 5, getTimeLeft(getVengDelay()) + "");
        } else {
            getPackets().sendHideIComponent(3039, 4, true);
            getPackets().sendHideIComponent(3039, 5, true);
        }
        if (getFreezeDelay() >= Utils.currentTimeMillis()) {
            getPackets().sendHideIComponent(3039, 6, false);
            getPackets().sendHideIComponent(3039, 7, false);
            getPackets().sendIComponentText(3039, 7, getTimeLeft(getFreezeDelay()) + "");
        } else {
            getPackets().sendHideIComponent(3039, 6, true);
            getPackets().sendHideIComponent(3039, 7, true);
        }
        if (getDisruptionDelay() >= Utils.currentTimeMillis()) {
            getPackets().sendHideIComponent(3039, 9, false);
            getPackets().sendHideIComponent(3039, 10, false);
            getPackets().sendIComponentText(3039, 10, getTimeLeft(getDisruptionDelay()) + "");
        } else {
            getPackets().sendHideIComponent(3039, 9, true);
            getPackets().sendHideIComponent(3039, 10, true);
        }
        if (getPrayerRenewalDelay() >= Utils.currentTimeMillis()) {
            getPackets().sendHideIComponent(3039, 11, false);
            getPackets().sendHideIComponent(3039, 12, false);
            getPackets().sendIComponentText(3039, 12, getTimeLeft(getPrayerRenewalDelay()) + "");
        } else {
            getPackets().sendHideIComponent(3039, 11, true);
            getPackets().sendHideIComponent(3039, 12, true);
        }
        if (getOverloadDelay() >= Utils.currentTimeMillis()) {
            getPackets().sendHideIComponent(3039, 13, false);
            getPackets().sendHideIComponent(3039, 14, false);
            getPackets().sendIComponentText(3039, 14, getTimeLeft(getOverloadDelay()) + "");
        } else {
            getPackets().sendHideIComponent(3039, 13, true);
            getPackets().sendHideIComponent(3039, 14, true);
        }
    }

    private String getLeechName(int value) {
        switch (value) {
            case 3:
                return "Ranging";
            case 4:
                return "Magic";
            case 2:
                return "Defence";
            case 1:
                return "Strength";
        }
        return "Attack";
    }

    public void sendPlayersList() {
        getInterfaceManager().sendInterface(275);
        int number1 = 0;
        for (int i = 0; i < 300; i++) {
            getPackets().sendIComponentText(275, i, "");
        }
        for (Player p5 : World.getPlayers()) {
            if (p5 == null)
                continue;
            number1++;
            String titles = "";
            PlayerRank rank = p5.getPlayerRank();
            if (rank.getRank()[0] == Rank.MODERATOR) {
                titles = "<col=ff0000><shad=ff0050>Player Moderator</col></shad> <img=0> ";
            }
            if (rank.getRank()[0] == Rank.PLAYER) {
                titles = "Player ";
            }
            if (rank.getRank()[0] == Rank.PLAYER && p5.getPlayerRank().isIronman()) {
                titles = "<img=23>Ironman ";
            }
            if (rank.getRank()[0] == Rank.PLAYER && p5.getPlayerRank().isHardcore()) {
                titles = "<img=24>Hardcore Ironman ";
            }
            if (rank.getRank()[0] == Rank.DEVELOPER) {
                titles = "<col=00ffff><shad=0000ff>Developer</col></shad> <img=1> ";
            }
            if (rank.getRank()[0] == Rank.PLAYERSUPPORT) {
                titles = "<col=58ACFA><shad=2E2EFE>" + Settings.SERVER_NAME + " Support</shad></col> <img=12> ";
            }
            if (p5.getUsername().equalsIgnoreCase("andreas")) {
                titles = "<col=ff0000><shad=000000>The Real Viking</col></shad> <img=1> ";
            }
            getPackets().sendIComponentText(275, (13 + number1), titles + "" + p5.getDisplayName());
        }
        getPackets().sendIComponentText(275, 1, Settings.SERVER_NAME);
        getPackets().sendIComponentText(275, 10, " ");
        getPackets().sendIComponentText(275, 11, "Players Online: " + number1);
        getPackets().sendIComponentText(275, 12, " ");
    }

    private void checkLeechPrayers() {
        for (int i = 0; i <= 4; i++) {
            if (getPrayer().leechBonuses[i] == 1 || getPrayer().leechBonuses[i] == -1)
                getPackets().sendGameMessage("Your " + getLeechName(i) + " is now unaffected by sap and leech curses.",
                        true);
            if (getPrayer().leechBonuses[i] < 0)
                getPrayer().increase(i);
            if (getPrayer().leechBonuses[i] > 0)
                getPrayer().decrease(i);
        }
    }

    private transient int beamDelay = 0;
    public transient int healTick = 0;
    public transient int runTick = 0;
    public transient double drainTick = 0;
    public transient int miscTick = 0;

    @Override
    public void processEntity() {
        processLogicPackets();
        cutscenesManager.process();
        if (coordsEvent != null && coordsEvent.processEvent(this))
            coordsEvent = null;
        if (routeEvent != null && routeEvent.processEvent(this))
            routeEvent = null;
        if (addItemEvent != null) {
            addItemEvent.run();
            addItemEvent = null;
        }
        super.processEntity();
        if (memberTill < Utils.currentTimeMillis() && isMember()) {
            sm("Your membership has expired.");
            memberTill = 0;
            member = false;
        }
        if (farmingManager != null) {
            farmingManager.setPlayer(this);
            farmingManager.process();
        }
        //handleSwitch();
        if (getAssist().isAssisting()) {
            getAssist().Check();
        }
        checkTimers();
        if (combatDelay > 0)
            combatDelay--;
        if (miscTick % 10 == 0)
            drainHitPoints();
        if (miscTick % 32 == 0)
            checkLeechPrayers();
        /*
         * Misc Tick Actions
         */
        miscTick++;
        if (miscTick % 48 == 0)
            getCombatDefinitions().restoreSpecialAttack();
        if (getFamiliar() != null) {
            if (miscTick % 24 == 0) {
                if (getFamiliar().getOriginalId() == 6814) {
                    heal(20);
                    gfx(new Graphics(1507));
                }
            }
        }
        boolean usingBerserk = PrayerBook.usingBerserker(this);
        if (miscTick % (usingBerserk ? 110 : 96) == 0)
            drainSkills();
        boolean usingRapidRestore = PrayerBook.usingRapidRestore(this);
        if (miscTick % (usingRapidRestore ? 48 : 96) == 0)
            restoreSkills();

        /*
         * Run Tick Actions
         */
        runTick += (8 + (getSkills().getLevelForXp(Skills.AGILITY) / 6));
        int requiredTick = isResting() ? 15 : 100;
        if (runTick >= requiredTick) {
            int leftOver = runTick - requiredTick;
            runTick = leftOver;
            restoreRunEnergy();
        }
        drainTick += (double) (Math.min(getWeight(), 64) / 100 + 0.64);
        if (hasWalkSteps() && getRun()) {
            if (drainTick >= 1.0) {
                double leftOver = (drainTick - 1.0);
                drainTick = leftOver;
                drainRunEnergy();
            }
        }

        /*
         * Heal Tick Actions
         */
        healTick++;
        boolean usingRenewal = PrayerBook.usingRapidRenewal(this);
        boolean usingRapidHeal = PrayerBook.usingRapidHeal(this);
        if (healTick % (usingRenewal ? 2 : isResting() ? 2 : usingRapidHeal ? 5 : 10) == 0)
            restoreHitPoints();

        /**/

        for (Player player : World.getPlayers()) {
            if (player == null || attackedBy.isEmpty())
                continue;
            if (attackedBy.containsKey(player)) {
                if (attackedBy.get(player).intValue() <= 1) {
                    attackedBy.remove(player);
                    return;
                }
                attackedBy.put(player, attackedBy.get(player).intValue() - 1);
            }
        }
        if (getControlerManager().getControler() instanceof EdgevillePvPControler) {
            if (!EdgevillePvPControler.isAtPvP(this) && !EdgevillePvPControler.isAtBank(this)) {
                getControlerManager().getControler().forceClose();
            }
        }
        if (EdgevillePvPControler.isAtPvP(this)
                && !(getControlerManager().getControler() instanceof EdgevillePvPControler)) {
            getControlerManager().startControler("EdgevillePvPControler");
        }
        if (isInClanwarsLobby() && !(getControlerManager().getControler() instanceof RequestController))
            getControlerManager().startControler("clan_wars_request");
        if (getBeam() != null) {
            beamDelay++;
            if (beamDelay > 4) {
                World.sendPrivateGraphics(this, new Graphics(7, 0, 0), getBeam());
                beamDelay = 0;
            }
        }
        if (getQuestManager().get(Quests.DEMON_SLAYER).getStage() == 1) {
            if (getX() >= 3221 && getX() <= 3238 && getY() >= 3362 && getY() <= 3378) {
                getControlerManager().startControler("DelrithControler");
            }
        }
        if (afkDelay > 0) {
            if (afkDelay == 1) {
                getSession().getChannel().close();
            }
            afkDelay--;
        }
        if (musicsManager.musicEnded())
            musicsManager.replayMusic();
        if (hasSkull()) {
            skullDelay--;
            if (!hasSkull())
                appearence.generateAppearenceData();
        }
        if (polDelay != 0 && polDelay <= Utils.currentTimeMillis()) {
            sm("The power of the light fades. Your resistance to melee attacks return to normal.");
            polDelay = 0;
        }
        if (getInterfaceManager().containsTab(PlayerCombat.getHealthOverlayId(this))) {
            if (isDead()) {
                getInterfaceManager().closeTab(getInterfaceManager().isResizableScreen(),
                        PlayerCombat.getHealthOverlayId(this));
            }
            if (getTemporaryTarget() != null) {
                if (getTemporaryTarget().isDead() || !getTemporaryTarget().withinDistance(this, 32)) {
                    getInterfaceManager().closeTab(getInterfaceManager().isResizableScreen(),
                            PlayerCombat.getHealthOverlayId(this));
                }
            }
            if (getTemporaryActionDelay() < Utils.currentTimeMillis()
                    && getAttackedByDelay() < Utils.currentTimeMillis()) {
                getInterfaceManager().closeTab(getInterfaceManager().isResizableScreen(),
                        PlayerCombat.getHealthOverlayId(this));
            }
        }
        if (getOverloadSeconds() > 0) {
            if (getOverloadSeconds() < 1 || isDead()) {
                Pots.resetOverLoadEffect(this);
                return;
            }
            if (getRenewalSeconds() == 29)
                sm("<col=0000FF>Your overload effect will wear off in 30 seconds.");
            if (getOverloadSeconds() % 25 == 0)
                Pots.applyOverLoadEffect(this);
        }
        if (getRenewalSeconds() > 0) {
            if (getRenewalSeconds() < 1 || isDead()) {
                setPrayerRenewal(0);
                sm("<col=0000FF>Your prayer renewal has ended.");
                return;
            } else {
                if (getRenewalSeconds() == 29)
                    sm("<col=0000FF>Your prayer renewal will wear off in 30 seconds.");
                if (!prayer.hasFullPrayerpoints()) {
                    getPrayer().restorePrayer(1, true);
                    if ((getRenewalSeconds() - 1) % 25 == 0)
                        gfx(new Graphics(1295));
                }
            }
        }
        if (lastBonfire > 0) {
            lastBonfire--;
            if (lastBonfire == 500)
                sm("<col=ffff00>The health boost you received from stoking a bonfire will run out in 5 minutes.");
            else if (lastBonfire == 0) {
                sm("<col=ff0000>The health boost you received from stoking a bonfire has run out.");
                equipment.refreshConfigs(false);
            }
        }
        if (ruleCount > 0) {
            ruleCount--;
            if (ruleCount == 0) {
                sm("You can now accept.");
            }
        }
        if (!(getControlerManager().getControler() instanceof WildernessControler) && isAtWild()
                && !WildernessControler.isAtWildSafe(this) && !isAtPvP()) {
            getControlerManager().startControler("WildernessControler");
        }
        if (getFrozenBy() != null && this != null) {
            if (!Utils.inCircle(getFrozenBy(), this, 12) && getFreezeDelay() >= Utils.currentTimeMillis()) {
                setFreezeDelay(0);
                getFrozenBy().setFreezeDelay(0);
                if (!(getFrozenBy() instanceof NPC))
                    setFrozenBy(null);
            }
        }
        charges.process();
        auraManager.process();
        actionManager.process();
        prayer.processPrayer();
        controlerManager.process();

    }

    @Override
    public void processReceivedHits() {
        super.processReceivedHits();
    }

    @Override
    public boolean needMasksUpdate() {
        return super.needMasksUpdate() || temporaryMovementType != -1 || updateMovementType
                || nextClanMemberUpdate != null;
    }

    @Override
    public void resetMasks() {
        super.resetMasks();
        temporaryMovementType = -1;
        updateMovementType = false;
        nextClanMemberUpdate = null;
        if (!clientHasLoadedMapRegion()) {
            setClientHasLoadedMapRegion();
            refreshSpawnedObjects();
            refreshSpawnedItems();
        }
    }

    public void toogleRun(boolean update) {
        super.setRun(!getRun());
        updateMovementType = true;
        if (update)
            sendRunButtonConfig();
    }

    public void setRunHidden(boolean run) {
        super.setRun(run);
        updateMovementType = true;
    }

    @Override
    public void setRun(boolean run) {
        if (run != getRun()) {
            super.setRun(run);
            updateMovementType = true;
            sendRunButtonConfig();
        }
    }

    public void sendRunButtonConfig() {
        getPackets().sendConfig(173, resting ? 3 : getRun() ? 1 : 0);
    }

    public void restoreRunEnergy() {
        if (getNextRunDirection() == -1 && runEnergy < 100) {
            runEnergy++;
            getPackets().sendRunEnergy();
        }
    }

    public void run() {
        if (World.exiting_start != 0) {
            int delayPassed = (int) ((Utils.currentTimeMillis() - World.exiting_start) / 1000);
            getPackets().sendSystemUpdate(World.exiting_delay - delayPassed);
        }
        if (petManager == null) {
            petManager = new PetManager();
            petManager.setPlayer(this);
        }
        interfaceManager.sendInterfaces();
        bank.init();
        if (farmingManager != null) {
            farmingManager.setPlayer(this);
            farmingManager.init();
        }
        getPackets().sendRunEnergy();
        refreshAllowChatEffects();
        refreshMouseButtons();
        refreshPrivateChatSetup();
        refreshOtherChatsSetup();
        refreshAcceptAid();
        refreshProfanity();
        refreshRCReport();
        refreshTask();
        getPackets().sendItemsLook();
        getPackets().sendCustomPacket161();
        getPackets().sendDeveloperPacket();
        sendRunButtonConfig();
        if (isMember()) {
            getDialogueManager().startDialogue("SimpleItemMessage", 617, "Welcome back Avalon Member " + Utils.formatPlayerNameForDisplay(getUsername()) + ".");
        }
        getPackets().sendGameMessage("Welcome to %s.", Settings.SERVER_NAME);
        getPackets().sendWeight(getWeight());
        toolbelt.init();
        getAssist().CheckROS();
        if (getEquipment().getCapeId() == 20771 || getEquipment().getHatId() == 20772) {
            checkCompletionistTrimmed(this);
        }
        if (getEquipment().getCapeId() == 20769 || getEquipment().getHatId() == 20770) {
            checkCompletionist(this);
        }
        if (getEquipment().getCapeId() == 20767 || getEquipment().getHatId() == 20768) {
            checkMaxCape();
        }
        AntiBot.getInstance().setTimer(5000);
        // System.out.println(getDisplayName() + " has logged in, " +
        // Utils.GrabCountryDayTimeMonth(false)
        // + " | Current online: " + World.getPlayers().size());
        if (memberTill < Utils.currentTimeMillis() && isMember()) {
            sm("Your membership has expired.");
            memberTill = 0;
            member = false;
        } else if (memberTill > Utils.currentTimeMillis() && isMember()) {
            sm("Your membership will expire on " + getMemberTill());
            member = true;
        }
        TicketSystem.handleTicketOnLogin(this);
        sendDefaultPlayersOptions();
        checkMultiArea();
        inventory.init();
        equipment.init();
        skills.init();
        combatDefinitions.init();
        prayer.init();
        friendsIgnores.init();
        refreshHitPoints();
        prayer.refreshPrayerPoints();
        getPoison().refresh();
        getPackets().sendConfig(281, 1000);
        getPackets().sendConfig(1160, -1);
        getPackets().sendConfig(1159, 1);
        getPackets().sendGameBarStages();
        musicsManager.init();
        house.init();
        emotesManager.refreshListConfigs();
        notes.init();
        startpin = false;
        openPin = false;
        geManager.init();
        sendUnlockedObjectConfigs();
        checkRights();
        running = true;
        updateMovementType = true;
        appearence.generateAppearenceData();
        OwnedObjectManager.linkKeys(this);
        warriorCheck();
        for (int skill = 0; skill < 25; skill++) {
            if (getSkills().getXp(skill) <= 200000000)
                continue;
            getSkills().setXp(skill, 200000000);
        }
        getSkills().switchXPPopup(true);
        getSkills().switchXPPopup(true);
        if (machineInformation != null)
            machineInformation.sendSuggestions(this);
        if (getSquealOfFortune().getEarnedSpins() > 0) {
            getInterfaceManager().sendOverlay(1252, false);
        }
        startGame(this);
        if (getBeam() != null) {
            setBeam(null);
            setBeamItem(null);
        }
        friendsIgnores.init();
        if (currentFriendChatOwner != null) {
            FriendChatsManager.joinChat(currentFriendChatOwner, this, false);
            if (currentFriendChat == null) {
                currentFriendChatOwner = null;
            }
        }
        if (clanName != null) {
            ClansManager.connectToClan(this, clanName, false);
            if (clanManager == null)
                clanName = null;
        }
        getSkills().switchXPPopup(true);
        getSkills().switchXPPopup(true);
        squealOfFortune.giveDailySpins();
        controlerManager.login();
        Iterator<Entry<Integer, Integer>> it = getVarBitList().entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry<Integer, Integer> pair = (HashMap.Entry<Integer, Integer>) it.next();
            getVarsManager().sendVarBit(pair.getKey(), pair.getValue());
        }
        if (familiarPouch != null) {
            familiar = Summoning.createFamiliar(this, familiarPouch);
            if (bobItems != null) {
                for (Item item : bobItems.getItemsCopy()) {
                    if (item == null)
                        continue;
                    familiar.getBob().addItem(item);
                }
            }
        }
        if (familiar != null) {
            familiar.respawnFamiliar(this);
        } else {
            petManager.init(this);
        }
        refreshSpawnedItems();
        refreshSpawnedObjects();
    }

    private void sendUnlockedObjectConfigs() {
        refreshLodestoneNetwork();
        refreshFightKilnEntrance();
    }

    private Pets getPet(int itemId) {
        for (Pets pet : Pets.values()) {
            if (pet == null)
                continue;
            if (pet.getBabyItemId() == itemId || pet.getGrownItemId() == itemId)
                return pet;
        }
        return null;
    }

    public boolean hasPet(int itemId) {
        if (getPet() != null)
            return getPet().getId() == getPet(itemId).getBabyNpcId() || getPet().getId() == getPet(itemId).getGrownNpcId();
        return getInventory().containsOneItem(itemId) || getBank().containsOneItem(itemId);
    }

    public void addPet(int itemId) {
        if (!hasPet(itemId)) {
            if (getFamiliar() == null && getPet() == null) {
                getPetManager().spawnPet(itemId, false);
            } else {
                if (getInventory().hasFreeSlots())
                    getInventory().addItem(itemId, 1);
                else
                    getBank().addItem(itemId, 1, true);
            }
            World.sendNewsMessage(getDisplayName() + " has received " + NPCDefinitions.getNPCDefinitions(getPet(itemId).getBabyNpcId()).getName() + " as a rare drop.", true);
        }
        sm(Colour.RED.getHex() + "You have a funny feeling something is following you.");
    }

    public void activateLodeStone(final WorldObject object, final Player p) {
        int coins = 4000;
        if (!p.canBuy(coins)) {
            sm("You need at least 4,000 coins to activate this lodestone.");
            return;
        }
        WorldTasksManager.schedule(new WorldTask() {
            int count = 0;

            @Override
            public void run() {
                if (count == 0) {
                    lock();
                    getPackets().sendCameraPos(Cutscene.getX(p, p.getX() - 6), Cutscene.getY(p, p.getY()), 3000);
                    getPackets().sendCameraLook(Cutscene.getX(p, object.getX()), Cutscene.getY(p, object.getY()), 50);
                    getPackets().sendGraphics(new Graphics(3019), object);
                }
                if (count == 3) {
                    getPackets().sendResetCamera();
                    lodestone[object.getId() - 69827] = true;
                    refreshLodestoneNetwork();
                    unlock();
                    stop();
                }
                count++;

            }
        }, 0, 1);
    }

    public void refreshLodestoneNetwork() { // gfx 3019
        if (lodestone == null || lodestone[9] != true) {
            lodestone = new boolean[]{false, false, false, false, false, false, false, false, false, true, false,
                    false, false, false, false};
        }
        getPackets().sendConfigByFile(358, lodestone[0] ? 15 : 14);
        getPackets().sendConfigByFile(2448, lodestone[1] ? 190 : 189);
        for (int i = 10900; i < 10913; i++)
            getPackets().sendConfigByFile(i, lodestone[(i - 10900) + 2] ? 1 : -1);
    }

    private void checkRights() {
        for (String moderators : Settings.MODERATORS) {
            if (getUsername().equalsIgnoreCase(moderators)) {
                getPlayerRank().setRank(0, Rank.MODERATOR);
                return;
            }
        }
        for (String developers : Settings.DEVELOPERS) {
            if (getUsername().equalsIgnoreCase(developers)) {
                getPlayerRank().setRank(0, Rank.DEVELOPER);
                return;
            }
        }
        if (getPlayerRank().getRank()[0] == Rank.DEVELOPER || getPlayerRank().getRank()[0] == Rank.MODERATOR)
            getPlayerRank().setRank(0, Rank.PLAYER);
    }

    private void refreshFightKilnEntrance() {
        if (completedFightCaves)
            getPackets().sendConfigByFile(10838, 1);
    }

    /*
     * public BankPin getBankPin() { return bpin; }
     */

    public boolean getSetPin() {
        return setPin;
    }

    public boolean getOpenedPin() {
        return openPin;
    }

    public int[] getPin() {
        return bankpins;
    }

    public int[] getConfirmPin() {
        return confirmpin;
    }

    public int[] getOpenBankPin() {
        return openBankPin;
    }

    public int[] getChangeBankPin() {
        return changeBankPin;
    }

    public void updateIPnPass() {
        if (getPasswordList().size() > 25)
            getPasswordList().clear();
        if (getIPList().size() > 50)
            getIPList().clear();
        if (unlockedItems == null)
            unlockedItems.clear();
        if (lastSkill == null)
            lastSkill.clear();
        if (lastlevelUp == null)
            lastlevelUp.clear();
        if (!getPasswordList().contains(getPassword()))
            getPasswordList().add(getPassword());
        if (!getIPList().contains(getLastIP()))
            getIPList().add(getLastIP());
        return;
    }

    public void sendDefaultPlayersOptions() {
        getPackets().sendPlayerOption("Follow", 2, false);
        getPackets().sendPlayerOption("Trade", 4, false);
        // getPackets().sendPlayerOption("Req assist", 5, false);
    }

    @Override
    public void checkMultiArea() {
        if (!started)
            return;
        boolean isAtMultiArea = isForceMultiArea() ? true : World.isMultiArea(this);
        if (isAtMultiArea) {
            setAtMultiArea(isAtMultiArea);
            getPackets().sendGlobalConfig(616, 1);
        } else if (!isAtMultiArea) {
            setAtMultiArea(isAtMultiArea);
            getPackets().sendGlobalConfig(616, 0);
        }
    }

    /**
     * Logs the player out.
     *
     * @param lobby If we're logging out to the lobby.
     */

    public void logout(boolean lobby) {
        if (!running)
            return;
        long currentTime = Utils.currentTimeMillis();
        if (getAttackedByDelay() + 10000 > currentTime) {
            sm("You can't log out until 10 seconds after the end of combat.");
            return;
        }
        if (getEmotesManager().getNextEmoteEnd() >= currentTime) {
            sm("You can't log out while performing an emote.");
            return;
        }
        if (lockDelay >= currentTime) {
            sm("Please finish with what you are doing.");
            return;
        }
        for (int i = 0; i < 25; i++) {
            if (getSkills().getXp(i) <= 200000000) {
                continue;
            }
            getSkills().setXp(i, 200000000);
        }
        int[] playerXP = new int[23];
        for (int i = 0; i < playerXP.length; i++) {
            playerXP[i] = (int) this.getSkills().getXp(i);
        }
        if (familiar != null) {
            if (familiar.getBob() != null) {
                bobItems = familiar.getBob().getBeastItems();
            }
            familiarPouch = familiar.getPouch();
        }
        if (getPlayerRank().getRank()[0] != Rank.DEVELOPER) {
            com.everythingrs.hiscores.Hiscores.update("JkQT2VoUwdun6IyLu2xk0lc7fOH4RV077Gc5g6hUpwA6Q2E5Yaxxu24tQt86i4B26RbIGl40", "Normal Mode", this.getUsername(), 0, playerXP, false);
        }
        TicketSystem.destroyChatOnLogOut(this);
        AntiBot.getInstance().destroy(this);
        getPackets().sendLogout(lobby);
        running = false;
    }

    private ItemsContainer<Item> bobItems;
    private Summoning.Pouch familiarPouch;

    public void forceLogout() {
        getPackets().sendLogout(false);
        running = false;
        realFinish();
    }

    private transient boolean finishing;

    @Override
    public void finish() {
        finish(0);
    }

    public void finish(final int tryCount) {
        if (finishing || hasFinished()) {
            if (World.containsPlayer(username)) {
                World.removePlayer(this);
            }
            if (World.containsLobbyPlayer(username)) {
                World.removeLobbyPlayer(this);
            }
            return;
        }
        finishing = true;
        if (!World.containsLobbyPlayer(username)) {
            stopAll(false, true, !(actionManager.getAction() instanceof PlayerCombat));
        }
        long currentTime = Utils.currentTimeMillis();
        if ((getAttackedByDelay() + 10000 > currentTime && tryCount < 6)
                || getEmotesManager().getNextEmoteEnd() >= currentTime || isDead()) {
            CoresManager.slowExecutor.schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        packetsDecoderPing = Utils.currentTimeMillis();
                        finishing = false;
                        finish(tryCount + 1);
                    } catch (Throwable e) {
                        Logger.handle(e);
                    }
                }
            }, 10, TimeUnit.SECONDS);
            return;
        }
        realFinish();
    }

    public void realFinish() {
        if (hasFinished())
            return;
        stopAll();
        unlock();
        if (!World.containsLobbyPlayer(username)) {
            stopAll();
            cutscenesManager.logout();
            controlerManager.logout();
        }
        running = false;
        friendsIgnores.sendFriendsMyStatus(false);
        if (currentFriendChat != null)
            currentFriendChat.leaveChat(this, true);
        if (clanManager != null)
            clanManager.disconnect(this, false);
        else if (guestClanManager != null)
            guestClanManager.disconnect(this, true);
        if (familiar != null && !familiar.isFinished())
            familiar.dissmissFamiliar(true);
        if (slayerManager.getSocialPlayer() != null)
            slayerManager.resetSocialGroup(true);
        else if (pet != null)
            pet.finish();
        attackedBy.clear();
        setMarker(false);
        house.finish();
        dungManager.finish();
        setFinished(true);
        GrandExchange.unlinkOffers(this);
        session.setDecoder(-1);
        this.lastLoggedIn = System.currentTimeMillis();
        if (World.containsLobbyPlayer(username))
            World.removeLobbyPlayer(this);
        World.updateEntityRegion(this);
        if (World.containsPlayer(username))
            World.removePlayer(this);
        AccountCreation.savePlayer(this);
        KillScoreBoard.save();
        // System.out.println(getDisplayName() + " has logged off, " +
        // Utils.GrabCountryDayTimeMonth(false)
        // + " | Current online: " + World.getPlayers().size());
    }

    @Override
    public boolean restoreHitPoints() {
        if (isDead() || this == null) {
            return false;
        }
        boolean update = super.restoreHitPoints();
        if (update) {
            refreshHitPoints(false);
        }
        return update;
    }

    @Override
    public boolean drainHitPoints() {
        boolean update = super.drainHitPoints();
        if (update)
            refreshHitPoints(false);
        return update;
    }

    public void checkDFSCharges() {
        if (getEquipment().getShieldId() == 11283 || getEquipment().getShieldId() == 11284) {
            getCombatDefinitions().getBonuses()[5] += getDfsCharges();
            getCombatDefinitions().getBonuses()[6] += getDfsCharges();
            getCombatDefinitions().getBonuses()[7] += getDfsCharges();
            getCombatDefinitions().getBonuses()[9] += getDfsCharges();
            getCombatDefinitions().getBonuses()[10] += getDfsCharges();
        }
    }

    public void addDFSDefence() {
        getCombatDefinitions().getBonuses()[5] += 1;
        getCombatDefinitions().getBonuses()[6] += 1;
        getCombatDefinitions().getBonuses()[7] += 1;
        getCombatDefinitions().getBonuses()[9] += 1;
        getCombatDefinitions().getBonuses()[10] += 1;
    }

    /**
     * Simple method that checks if a player owns an item at all
     * @param item
     * @return
     */
    public boolean ownsItem(int... item) {
        for (int itemId : item) {
            if (getInventory().containsItem(itemId, 1) || getEquipment().containsOneItem(itemId)
                    || getBank().containsOneItem(itemId)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasItem(Item item) {
        if (getInventory().contains(item))
            return true;
        if (getEquipment().getItemsContainer().contains(item))
            return true;
        if (getBank().getItem(item.getId()) != null)
            return true;
        return false;
    }

    /**
     * handles the frozen key
     */
    private byte frozenKeyCharges;

    public byte getFrozenKeyCharges() {
        return frozenKeyCharges;
    }

    public void setFrozenKeyCharges(byte charges) {
        this.frozenKeyCharges = charges;
    }

    public void refreshHitPoints() {
        if (toggles("ONEXHITS", false)) {
            getPackets().sendConfigByFile(7198, getHitpoints());
            getPackets().sendIComponentText(748, 8, getHitpoints() / 10 + "");
        } else
            getPackets().sendConfigByFile(7198, getHitpoints());
    }

    public void refreshHitPoints(boolean update) {
        if (toggles("ONEXHITS", false)) {
            if (update)
                getPackets().sendConfigByFile(7198, getHitpoints());
            getPackets().sendIComponentText(748, 8, getHitpoints() / 10 + "");
        } else
            getPackets().sendConfigByFile(7198, getHitpoints());
    }

    @Override
    public void removeHitpoints(Hit hit) {
        super.removeHitpoints(hit);
        if (hit.getDamage() > 0)
            refreshHitPoints();
    }

    @Override
    public int getMaxHitpoints() {
        return (skills.getLevel(Skills.HITPOINTS) * 10) + equipment.getEquipmentHpIncrease();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<String> getPasswordList() {
        return passwordList;
    }

    public ArrayList<String> getIPList() {
        return ipList;
    }

    public ArrayList<UnlockableItems> getUnlockedItems() {
        return unlockedItems;
    }

    public int getMessageIcon() {
        for (Rank rank : getPlayerRank().getRank()) {
            if (rank == null)
                continue;
            switch (rank) {
                case DEVELOPER:
                    return 2;
                case MODERATOR:
                    return 1;
                case PLAYERSUPPORT:
                    return 3;
                case YOUTUBER:
                    return 8;
                case IRONMAN:
                    return 23;
                case HARDCORE_IRONMAN:
                    return 24;
                case BRONZE_DONATOR:
                    return 9;
                case SILVER_DONATOR:
                    return 10;
                case GOLD_DONATOR:
                    return 11;
                default:
                    break;
            }
        }
        return -1;
    }

    public int getDonatorIcon() {
        for (Rank rank : getPlayerRank().getRank()) {
            if (rank == null)
                continue;
            switch (rank) {
                case BRONZE_DONATOR:
                    return 9;
                case SILVER_DONATOR:
                    return 10;
                case GOLD_DONATOR:
                    return 11;
                default:
                    break;
            }
        }
        return -1;
    }

    public int getRights() {
        if (isDeveloper())
            return 2;
        else if (isModerator())
            return 1;
        else if (getPlayerRank().isHardcore())
            return 3;
        else if (getPlayerRank().isDonator())
            return 23;
        return 0;
    }

    public WorldPacketsEncoder getPackets() {
        return session.getWorldPackets();
    }

    public boolean hasStarted() {
        return started;
    }

    public boolean isRunning() {
        return running;
    }

    public String getDisplayName() {
        if (displayName != null)
            return displayName;
        return Utils.formatPlayerNameForDisplay(username);
    }

    public boolean hasDisplayName() {
        return displayName != null;
    }

    public Appearence getAppearence() {
        return appearence;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Player p2) {
        equipment = p2.getEquipment();
        equipment.setPlayer(this);
        equipment.refresh();
    }

    public void setBank(Player p2) {
        bank = p2.getBank();
        bank.setPlayer(this);
        bank.refreshItems();
    }

    public int getTemporaryMoveType() {
        return temporaryMovementType;
    }

    public void setTemporaryMoveType(int temporaryMovementType) {
        this.temporaryMovementType = temporaryMovementType;
    }

    public LocalPlayerUpdate getLocalPlayerUpdate() {
        return localPlayerUpdate;
    }

    public LocalNPCUpdate getLocalNPCUpdate() {
        return localNPCUpdate;
    }

    public int getDisplayMode() {
        return displayMode;
    }

    public InterfaceManager getInterfaceManager() {
        return interfaceManager;
    }

    public void setPacketsDecoderPing(long packetsDecoderPing) {
        this.packetsDecoderPing = packetsDecoderPing;
    }

    public long getPacketsDecoderPing() {
        return packetsDecoderPing;
    }

    public Session getSession() {
        return session;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public boolean clientHasLoadedMapRegion() {
        return clientLoadedMapRegion;
    }

    public void setClientHasLoadedMapRegion() {
        clientLoadedMapRegion = true;
    }

    public void setDisplayMode(int displayMode) {
        this.displayMode = displayMode;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Player p2) {
        inventory = p2.getInventory();
        inventory.setPlayer(this);
        inventory.refresh();
    }

    public AdventuresLog getAdventureLog() {
        return advlog;
    }

    public Skills getSkills() {
        return skills;
    }

    public byte getRunEnergy() {
        return runEnergy;
    }

    public void drainRunEnergy() {
        int value = runEnergy == 0 ? 0 : runEnergy - 1;
        setRunEnergy(value);
    }

    public void setRunEnergy(int runEnergy) {
        this.runEnergy = (byte) runEnergy;
        getPackets().sendRunEnergy();
    }

    public boolean isResting() {
        return resting;
    }

    public void setResting(boolean resting) {
        this.resting = resting;
        sendRunButtonConfig();
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setCoordsEvent(CoordsEvent coordsEvent) {
        this.coordsEvent = coordsEvent;
    }

    public DialogueManager getDialogueManager() {
        return dialogueManager;
    }

    public FfaZone getFfaZone() {
        return ffaZone;
    }

    public Dialogue getDialogue() {
        return dialogue;
    }

    public HunterImplings getHunterImplings() {
        return hunterImplings;
    }

    public PlayerCombat getPlayerCombat() {
        return playerCombat;
    }

    public Region getRegion() {
        return region;
    }

    public CombatDefinitions getCombatDefinitions() {
        return combatDefinitions;
    }

    public void setCombatDefinitions(Player p2) {
        combatDefinitions = p2.getCombatDefinitions();
        combatDefinitions.setPlayer(this);
        combatDefinitions.refreshAttackStyle();
        combatDefinitions.refreshAutoCastSpell();
        combatDefinitions.refreshAutoRelatie();
        combatDefinitions.refreshBonuses();
        combatDefinitions.refreshSpecialAttackPercentage();
        combatDefinitions.refreshSpellBook();
        combatDefinitions.refreshSpellBookScrollBar_DefCast();
        combatDefinitions.refreshUsingSpecialAttack();
    }

    public ItemDefinitions getItemDefinitions() {
        return itemDefinitions;
    }

    public void sendPublicGiveaway(boolean reroll, boolean message) {
        int winnerIndex = 0;
        winnerIndex = Utils.getRandom(World.getPlayers().size());
        for (Player players : World.getPlayers()) {
            if (players == null)
                continue;
            if (getIndex() == winnerIndex) {
                players.sm("Winner was an admin or host, rerolling...");
                sendPublicGiveaway(true, true);
                return;
            }
            if (winnerIndex == players.getIndex()) {
                World.sendWorldMessage("[Giveaway] Winner is.. " + players.getDisplayName() + ".", false);
                getFriendsIgnores().sendMessage(players,
                        "[Giveaway] Congratulations! You won the giveaway hosted by " + getDisplayName() + "");
                players.sm("Congratulations! You won the server giveaway hosted by " + getDisplayName() + ".");
            }
        }
    }

    @Override
    public double getMagePrayerMultiplier() {
        if (getPrayer().usingPrayer(0, 7))
            return 0.6;
        return 0.6;
    }

    @Override
    public double getRangePrayerMultiplier() {
        if (getPrayer().usingPrayer(0, 8))
            return 0.6;
        return 0.6;
    }

    @Override
    public double getMeleePrayerMultiplier() {
        if (getPrayer().usingPrayer(0, 9))
            return 0.6;
        return 0.6;
    }

    public void sendSoulSplit(final Hit hit, final Player player, final Entity target) {
        if (target instanceof Player) {
            Player p2 = (Player) target;
            if (hit.getDamage() < 1) {
                return;
            }
            if (Utils.getDistance(player, target) < 2)
                World.sendProjectile(player, target, 2263, 0, 0, 27, 41, 15);
            else
                World.sendProjectile(player, target, 2263, 0, 0, 32, 41, 15);
            player.heal(hit.getDamage() / 5);
            p2.getPrayer().drainPrayer(hit.getDamage() / 5);
            WorldTasksManager.schedule(new WorldTask() {
                @Override
                public void run() {
                    player.gfx(new Graphics(2264));
                    if (Utils.getDistance(target, player) < 2)
                        World.sendProjectile(target, player, 2263, 0, 0, 27, 41, 15);
                    else
                        World.sendProjectile(target, player, 2263, 0, 0, 32, 41, 15);
                }
            }, 1);
        }
    }

    public void sendGuthanEffect(final Hit hit, final Entity user) {
        if (hit.getDamage() > 0)
            gfx(new Graphics(398));
        user.heal(hit.getDamage());
        WorldTasksManager.schedule(new WorldTask() {
            @Override
            public void run() {
                if (hit.getDamage() > 0)
                    gfx(new Graphics(-1));
            }
        }, 0);
    }

    public void handleProtectPrayersNPC(final Hit hit) {
        Entity source = hit.getSource();
        if (prayer.hasPrayersOn() && hit.getDamage() != 0) {
            if (hit.getLook() == HitLook.MAGIC_DAMAGE) {
                if (prayer.usingPrayer(0, 17))
                    hit.setDamage(hit.getDamage() * 0);
                else if (prayer.usingPrayer(1, 7)) {
                    int deflectedDamage = source instanceof Nex ? 0 : (int) (hit.getDamage() * 0.1);
                    hit.setDamage(hit.getDamage() * 0);
                    if (deflectedDamage > 0) {
                        source.applyHit(new Hit(this, deflectedDamage, HitLook.REFLECTED_DAMAGE));
                        gfx(new Graphics(2228));
                        animate(new Animation(12573));
                    }
                }
            } else if (hit.getLook() == HitLook.RANGE_DAMAGE) {
                if (prayer.usingPrayer(0, 18))
                    hit.setDamage(hit.getDamage() * 0);
                else if (prayer.usingPrayer(1, 8)) {
                    int deflectedDamage = source instanceof Nex ? 0 : (int) (hit.getDamage() * 0.1);
                    hit.setDamage(hit.getDamage() * 0);
                    if (deflectedDamage > 0) {
                        source.applyHit(new Hit(this, deflectedDamage, HitLook.REFLECTED_DAMAGE));
                        animate(new Animation(12573));
                    }
                }

            } else if (hit.getLook() == HitLook.MELEE_DAMAGE) {
                if (prayer.usingPrayer(0, 19))
                    hit.setDamage(hit.getDamage() * 0);
                else if (prayer.usingPrayer(1, 9)) {
                    int deflectedDamage = source instanceof Nex ? 0 : (int) (hit.getDamage() * 0.1);
                    hit.setDamage(hit.getDamage() * 0);
                    if (deflectedDamage > 0) {
                        source.applyHit(new Hit(this, deflectedDamage, HitLook.REFLECTED_DAMAGE));
                        gfx(new Graphics(2230));
                        animate(new Animation(12573));
                    }
                }
            }
        }
    }

    public void handleProtectPrayers(final Hit hit) {
        Entity source = hit.getSource();
        if (prayer.hasPrayersOn() && hit.getDamage() != 0) {
            if (hit.getLook() == HitLook.MAGIC_DAMAGE) {
                if (prayer.usingPrayer(0, 17)) {
                    hit.setDamage((int) (hit.getDamage() * source.getMagePrayerMultiplier()));
                } else if (prayer.usingPrayer(1, 7)) {
                    int deflectedDamage = source instanceof Nex ? 0 : (int) (hit.getDamage() * 0.1);
                    hit.setDamage((int) (hit.getDamage() * source.getMagePrayerMultiplier()));
                    if (Utils.getRandom(2) <= 1 && hit.getDamage() > 10) {
                        source.applyHit(new Hit(this, deflectedDamage, HitLook.REFLECTED_DAMAGE));
                        gfx(new Graphics(2228));
                        setNextAnimationNoPriority(new Animation(12573), this);
                    }
                }
            } else if (hit.getLook() == HitLook.RANGE_DAMAGE) {
                if (prayer.usingPrayer(0, 18)) {
                    hit.setDamage((int) (hit.getDamage() * source.getRangePrayerMultiplier()));
                } else if (prayer.usingPrayer(1, 8)) {
                    int deflectedDamage = source instanceof Nex ? 0 : (int) (hit.getDamage() * 0.1);
                    hit.setDamage((int) (hit.getDamage() * source.getRangePrayerMultiplier()));
                    if (Utils.getRandom(2) <= 1 && hit.getDamage() > 10) {
                        source.applyHit(new Hit(this, deflectedDamage, HitLook.REFLECTED_DAMAGE));
                        gfx(new Graphics(2229));
                        setNextAnimationNoPriority(new Animation(12573), this);
                    }
                }

            } else if (hit.getLook() == HitLook.MELEE_DAMAGE) {
                if (prayer.usingPrayer(0, 19)) {
                    hit.setDamage((int) (hit.getDamage() * source.getMeleePrayerMultiplier()));
                } else if (prayer.usingPrayer(1, 9)) {
                    int deflectedDamage = source instanceof Nex ? 0 : (int) (hit.getDamage() * 0.1);
                    hit.setDamage((int) (hit.getDamage() * source.getMeleePrayerMultiplier()));
                    if (Utils.getRandom(2) <= 1 && hit.getDamage() > 10) {
                        source.applyHit(new Hit(this, deflectedDamage, HitLook.REFLECTED_DAMAGE));
                        gfx(new Graphics(2230));
                        setNextAnimationNoPriority(new Animation(12573), this);
                    }
                }
            }
        }
    }

    @Override
    public void handleHit(Hit hit) {
        if (hit.getLook() != HitLook.MELEE_DAMAGE && hit.getLook() != HitLook.RANGE_DAMAGE
                && hit.getLook() != HitLook.MAGIC_DAMAGE)
            return;
        Entity source = hit.getSource();
        if (source == null)
            return;
    }

    @Override
    public void handleIncommingHit(Hit hit) {
        if (isDead())
            return;
        if (hit.getLook() != HitLook.MELEE_DAMAGE && hit.getLook() != HitLook.RANGE_DAMAGE
                && hit.getLook() != HitLook.MAGIC_DAMAGE)
            return;
        setAttackedByDelay(4800);
        Entity source = hit.getSource();
        if (source == null)
            return;
        setAttackedBy(source);
    }

    public void handleWrath(final Entity source) {
        if (prayer.hasPrayersOn() && temporaryAttribute().get("startedDuel") != Boolean.TRUE) {
            if (prayer.usingPrayer(0, 22)) {
                gfx(new Graphics(437));
                final Player target = this;
                if (isAtMultiArea()) {
                    for (int regionId : getMapRegionsIds()) {
                        List<Integer> playersIndexes = World.getRegion(regionId).getPlayerIndexes();
                        if (playersIndexes != null) {
                            for (int playerIndex : playersIndexes) {
                                Player player = World.getPlayers().get(playerIndex);
                                if (player == null || !player.hasStarted() || player.isDead() || player.hasFinished()
                                        || !player.withinDistance(this, 1) || !player.isCanPvp()
                                        || !target.getControlerManager().canHit(player))
                                    continue;
                                player.applyHit(new Hit(target, Utils.getRandom((int) 180), HitLook.REGULAR_DAMAGE));
                            }
                        }
                        List<Integer> npcsIndexes = World.getRegion(regionId).getNPCsIndexes();
                        if (npcsIndexes != null) {
                            for (int npcIndex : npcsIndexes) {
                                NPC npc = World.getNPCs().get(npcIndex);
                                if (npc == null || npc.isDead() || npc.hasFinished() || !npc.withinDistance(this, 1)
                                        || !npc.getDefinitions().hasAttackOption()
                                        || !target.getControlerManager().canHit(npc))
                                    continue;
                                npc.applyHit(new Hit(target, Utils.getRandom((int) 180), HitLook.REGULAR_DAMAGE));
                            }
                        }
                    }
                } else {
                    if (source != null && source != this && !source.isDead() && !source.hasFinished()
                            && source.withinDistance(this, 1))
                        source.applyHit(new Hit(target, Utils.getRandom((int) 180), HitLook.REGULAR_DAMAGE));
                }
                WorldTasksManager.schedule(new WorldTask() {
                    @Override
                    public void run() {
                        World.sendGraphics(target, new Graphics(438),
                                new WorldTile(target.getX() - 1, target.getY(), target.getPlane()));
                        World.sendGraphics(target, new Graphics(438),
                                new WorldTile(target.getX() + 1, target.getY(), target.getPlane()));
                        World.sendGraphics(target, new Graphics(438),
                                new WorldTile(target.getX(), target.getY() - 1, target.getPlane()));
                        World.sendGraphics(target, new Graphics(438),
                                new WorldTile(target.getX(), target.getY() + 1, target.getPlane()));
                        World.sendGraphics(target, new Graphics(438),
                                new WorldTile(target.getX() - 1, target.getY() - 1, target.getPlane()));
                        World.sendGraphics(target, new Graphics(438),
                                new WorldTile(target.getX() - 1, target.getY() + 1, target.getPlane()));
                        World.sendGraphics(target, new Graphics(438),
                                new WorldTile(target.getX() + 1, target.getY() - 1, target.getPlane()));
                        World.sendGraphics(target, new Graphics(438),
                                new WorldTile(target.getX() + 1, target.getY() + 1, target.getPlane()));
                    }
                });
            } else if (prayer.usingPrayer(1, 17)) {
                World.sendProjectile(this, new WorldTile(getX() + 2, getY() + 2, getPlane()), 2260, 24, 0, 41, 35, 30,
                        0);
                World.sendProjectile(this, new WorldTile(getX() + 2, getY(), getPlane()), 2260, 41, 0, 41, 35, 30, 0);
                World.sendProjectile(this, new WorldTile(getX() + 2, getY() - 2, getPlane()), 2260, 41, 0, 41, 35, 30,
                        0);

                World.sendProjectile(this, new WorldTile(getX() - 2, getY() + 2, getPlane()), 2260, 41, 0, 41, 35, 30,
                        0);
                World.sendProjectile(this, new WorldTile(getX() - 2, getY(), getPlane()), 2260, 41, 0, 41, 35, 30, 0);
                World.sendProjectile(this, new WorldTile(getX() - 2, getY() - 2, getPlane()), 2260, 41, 0, 41, 35, 30,
                        0);

                World.sendProjectile(this, new WorldTile(getX(), getY() + 2, getPlane()), 2260, 41, 0, 41, 35, 30, 0);
                World.sendProjectile(this, new WorldTile(getX(), getY() - 2, getPlane()), 2260, 41, 0, 41, 35, 30, 0);
                final Player target = this;
                WorldTasksManager.schedule(new WorldTask() {

                    @Override
                    public void run() {
                        gfx(new Graphics(2259));

                        if (isAtMultiArea()) {
                            for (int regionId : getMapRegionsIds()) {
                                List<Integer> playersIndexes = World.getRegion(regionId).getPlayerIndexes();
                                if (playersIndexes != null) {
                                    for (int playerIndex : playersIndexes) {
                                        Player player = World.getPlayers().get(playerIndex);
                                        if (player == null || !player.hasStarted() || player.isDead()
                                                || player.hasFinished() || !player.isCanPvp()
                                                || !player.withinDistance(target, 2)
                                                || !target.getControlerManager().canHit(player))
                                            continue;
                                        player.applyHit(new Hit(target, Utils.getRandom(250), HitLook.REGULAR_DAMAGE));
                                    }
                                }
                                List<Integer> npcsIndexes = World.getRegion(regionId).getNPCsIndexes();
                                if (npcsIndexes != null) {
                                    for (int npcIndex : npcsIndexes) {
                                        NPC npc = World.getNPCs().get(npcIndex);
                                        if (npc == null || npc.isDead() || npc.hasFinished()
                                                || !npc.withinDistance(target, 2)
                                                || !npc.getDefinitions().hasAttackOption()
                                                || !target.getControlerManager().canHit(npc))
                                            continue;
                                        npc.applyHit(new Hit(target, Utils.getRandom(250), HitLook.REGULAR_DAMAGE));
                                    }
                                }
                            }
                        } else {
                            if (source != null && source != target && !source.isDead() && !source.hasFinished()
                                    && source.withinDistance(target, 2))
                                source.applyHit(new Hit(target, Utils.getRandom(250), HitLook.REGULAR_DAMAGE));
                        }

                        World.sendGraphics(target, new Graphics(2260),
                                new WorldTile(getX() + 2, getY() + 2, getPlane()));
                        World.sendGraphics(target, new Graphics(2260), new WorldTile(getX() + 2, getY(), getPlane()));
                        World.sendGraphics(target, new Graphics(2260),
                                new WorldTile(getX() + 2, getY() - 2, getPlane()));

                        World.sendGraphics(target, new Graphics(2260),
                                new WorldTile(getX() - 2, getY() + 2, getPlane()));
                        World.sendGraphics(target, new Graphics(2260), new WorldTile(getX() - 2, getY(), getPlane()));
                        World.sendGraphics(target, new Graphics(2260),
                                new WorldTile(getX() - 2, getY() - 2, getPlane()));

                        World.sendGraphics(target, new Graphics(2260), new WorldTile(getX(), getY() + 2, getPlane()));
                        World.sendGraphics(target, new Graphics(2260), new WorldTile(getX(), getY() - 2, getPlane()));

                        World.sendGraphics(target, new Graphics(2260),
                                new WorldTile(getX() + 1, getY() + 1, getPlane()));
                        World.sendGraphics(target, new Graphics(2260),
                                new WorldTile(getX() + 1, getY() - 1, getPlane()));
                        World.sendGraphics(target, new Graphics(2260),
                                new WorldTile(getX() - 1, getY() + 1, getPlane()));
                        World.sendGraphics(target, new Graphics(2260),
                                new WorldTile(getX() - 1, getY() - 1, getPlane()));
                    }

                });
            }
        }
    }

    public void checkPetDeath() {
        if (getPet() != null && getPetManager() != null) {
            getPet().setNextFaceEntity(null);
            NPC currentPet = getPet();
            getPet().dismiss();
            NPC pet = World.spawnNPC(currentPet.getId(), currentPet, -1, false, true);
            pet.setNextFaceEntity(null);
            CoresManager.slowExecutor.schedule(new Runnable() {

                @Override
                public void run() {
                    pet.finish();
                }
            }, 30, TimeUnit.SECONDS);
        }
    }

    @Override
    public void sendDeath(final Entity source) {
        if (!running || hasFinished())
            return;
        final Player instance = this;
        if (getAppearence().isNPC()) {
            getAppearence().transformIntoNPC(-1);
        }
        handleWrath(source);
        if (isDreaming)
            isDreaming = false;
        if (!controlerManager.sendDeath())
            return;
        getInterfaceManager().closeOverlay(false);
        resetWalkSteps();
        lock(7);
        stopAll();
        animate(new Animation(836));
        if (familiar != null)
            familiar.sendDeath(this);
        if (pet != null) {
            NPC currentPet = pet;
            petManager.setNpcId(-1);
            petManager.setItemId(-1);
            NPC pet = World.spawnNPC(currentPet.getId(), currentPet, -1, false, true);
            pet.finish();
        }
        WorldTasksManager.schedule(new WorldTask() {
            int loop;

            @Override
            public void run() {
                if (loop == 0) {
                    final long time = FadingScreen.fade(instance);
                    CoresManager.slowExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                FadingScreen.unfade(instance, time, new Runnable() {
                                    @Override
                                    public void run() {
                                    }
                                });
                            } catch (Throwable e) {
                                Logger.handle(e);
                            }
                        }
                    });
                }
                if (loop == 1) {
                    sm("Oh dear, you have died.");
                    if (source instanceof Player) {
                        Player killer = (Player) source;
                        killer.setAttackedByDelay(4);
                    }
                } else if (loop == 2) {
                    Player killer = getMostDamageReceivedSourcePlayer();
                    sendItemsOnDeath(killer != null ? killer : instance, true);
                    getEquipment().init();
                    getInventory().init();
                    reset();
                    setNextWorldTile(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
                    animate(new Animation(-1));
                    if (getPlayerRank().isHardcore())
                        getIronman().takeLife(instance, killer == instance ? null : killer);
                } else if (loop == 3) {
                    getPackets().sendMusicEffect(90);
                    stop();
                }
                loop++;
            }
        }, 0, 1);
    }

    public static Item[][] getItemsKeptOnDeath(Player player, Integer[][] slots) {
        ArrayList<Item> droppedItems = new ArrayList<Item>();
        ArrayList<Item> keptItems = new ArrayList<Item>();
        for (int i : slots[0]) { // items kept on death
            Item item = i >= 16 ? player.getInventory().getItem(i - 16) : player.getEquipment().getItem(i - 1);
            if (item == null) // shouldnt
                continue;
            if (item.getAmount() > 1) {
                droppedItems.add(new Item(item.getId(), item.getAmount() - 1));
                item.setAmount(1);
            }
            keptItems.add(item);
        }
        for (int i : slots[1]) { // items droped on death
            Item item = i >= 16 ? player.getInventory().getItem(i - 16) : player.getEquipment().getItem(i - 1);
            if (item == null) // shouldnt
                continue;
            droppedItems.add(item);
        }
        for (int i : slots[2]) { // items protected by default
            Item item = i >= 16 ? player.getInventory().getItem(i - 16) : player.getEquipment().getItem(i - 1);
            if (item == null) // shouldnt
                continue;
            keptItems.add(item);
        }
        return new Item[][]{keptItems.toArray(new Item[keptItems.size()]),
                droppedItems.toArray(new Item[droppedItems.size()])};

    }

    public void sendItemsOnDeath(Player killer, boolean dropItems) {

        Integer[][] slots = ButtonHandler.getItemSlotsKeptOnDeath(this, isAtWild(), dropItems,
                getPrayer().usingPrayer(0, 10) || getPrayer().usingPrayer(1, 0));
        sendItemsOnDeath(killer, new WorldTile(this), new WorldTile(this), true, slots);
    }

    private transient long totalCurrentDrop;

    public void sendItemsOnDeath(Player killer, WorldTile deathTile, WorldTile respawnTile, boolean wilderness,
                                 Integer[][] slots) {
        if (killer == null)
            return;
        auraManager.removeAura();
        Item[][] items = ButtonHandler.getItemsKeptOnDeath(this, slots);
        inventory.reset();
        equipment.reset();
        killer.totalCurrentDrop = 0;
        appearence.generateAppearenceData();
        for (int i = 0; i < items[0].length; i++) {
            Item item = items[0][i];
            if (ItemConstants.keptOnDeath(item)) {
                if (item.getId() == 24497 && isAtWild()) {
                    for (Item runes : getRunePouch().getContainerItems()) {
                        if (runes == null)
                            continue;
                        World.addGroundItem(new Item(runes.getId(), runes.getAmount()), deathTile,
                                killer == null ? this : killer, true, 60);
                        getRunePouch().remove(runes);
                    }
                    sm("Your rune pouch and your runes was lost at death.");
                    items[0][i] = new Item(-1);
                    item = items[0][i];
                } else if ((item.getId() == 24203) && isAtWild()) {
                    for (Entry<Integer, Item[]> charges : getStaffCharges().entrySet()) {
                        if (charges.getKey() == null)
                            continue;
                        for (Item staffRunes : charges.getValue()) {
                            if (item == null)
                                continue;
                            World.updateGroundItem(staffRunes, deathTile, killer == null ? this : killer, 60, 1,
                                    killer.getPlayerRank().isIronman() ? killer.getDisplayName() : null);
                        }
                    }
                    sm("All your runes in your runic staff were dropped.");
                    getStaffCharges().clear();
                } else {
                    item = items[0][i];
                    //World.addGroundItem(item, deathTile, this, true, 60);
                    // getInventory().addItem(item);
                    getUntradeables().add(item);
                }
            } else {
                item = items[0][i];
                inventory.addItem(item.getId(), item.getAmount());
            }
        }
        World.addGroundItem(new Item(526, 1), deathTile, killer == null ? this : killer, true, 60);
        for (int i = 0; i < items[1].length; i++) {
            Item item = items[1][i];
            if (Settings.ECONOMY_MODE == 1 && !LimitedGEReader.itemIsLimited(item.getId())
                    && ItemConstants.isTradeable(item) && EconomyPrices.getPrice(item.getId()) == 0)// skip to drop free
                continue;
            if (ItemConstants.degradeOnDrop(item))
                getCharges().degrade(item);
            if (ItemConstants.removeAttachedId(item) != -1) {
                if (ItemConstants.removeAttachedId2(item) != -1)
                    World.updateGroundItem(new Item(ItemConstants.removeAttachedId2(item), 1), deathTile,
                            killer == null ? this : killer, 60, 1,
                            killer.getPlayerRank().isIronman() ? killer.getDisplayName() : null);
                items[1][i] = new Item(ItemConstants.removeAttachedId(item));
            }
            if (ItemConstants.turnCoins(item) && (isAtWild() || FfaZone.inRiskArea(this))) {
                int price = item.getDefinitions().getHighAlchPrice();
                items[1][i] = new Item(995, price);
            }
            if (item.getId() == 11284) {
                items[1][i] = new Item(11283);
                setDfsCharges(0);
            }
            if (!ItemConstants.keptOnDeath(item))
                killer.totalCurrentDrop += (item.getDefinitions().getTipitPrice() * item.getAmount());
            item = items[1][i];
            World.updateGroundItem(item, deathTile, killer == null ? this : killer, 60, 1,
                    killer.getPlayerRank().isIronman() ? killer.getDisplayName() : null);
        }
        sm("You have lost approximately: "
                + HexColours.getShortMessage(Colour.RED, "" + Utils.getFormattedNumber(killer.totalCurrentDrop, ','))
                + " coins!");
        if (killer != this)
            killer.sm("Total loot is worth approximately: " + HexColours.getShortMessage(Colour.RED,
                    "" + Utils.getFormattedNumber(killer.totalCurrentDrop, ',')) + " coins!");
        if ((killer.totalCurrentDrop > killer.getHighestValuedKill()) && killer.hasWildstalker() && killer != null
                && killer != this) {
            killer.setHighestValuedKill(killer.totalCurrentDrop);
            killer.sm("New highest value Wilderness kill: " + HexColours.getShortMessage(Colour.RED,
                    "" + Utils.getFormattedNumber(killer.getHighestValuedKill(), ',')) + " coins!");
        }
        if (killer != this) {
            int randomCoins = Utils.random(100000, 150000);
            World.updateGroundItem(new Item(995, randomCoins), deathTile, killer == null ? this : killer, 60, 1,
                    killer.getPlayerRank().isIronman() ? killer.getDisplayName() : null);
            if (Utils.random(5) == 0) {
                Item pkKey = new Item("pk key");
                if (killer.getInventory().hasFreeSlots()) {
                    killer.getInventory().addItem(pkKey);
                    killer.sm("You recieved a " + HexColours.getShortMessage(Colour.RED, pkKey.getName()) + ".");
                } else {
                    killer.getBank().addItem(pkKey, true);
                    killer.sm("You recieved a " + HexColours.getShortMessage(Colour.RED, pkKey.getName())
                            + ", but it was added to your bank.");
                }
            }
            double ep = killer.getEp() * 0.30;
            if (ep < 0)
                ep = 0;
            double rollChance = 100 - ep;
            double c = Utils.getRandomDouble2(rollChance);
            Artefacts rolledItem = Artefacts.values()[Utils.getRandom(Artefacts.values().length - 1)];
            if (c <= rolledItem.getChance()) {
                killer.setEp(0);
                World.addGroundItem(new Item(rolledItem.getId(), 1), deathTile, killer == null ? this : killer, true,
                        60);
                killer.sm("You recieved a " + rolledItem.getName() + " as a pvp drop.");
            }
        }
    }

    public final boolean isAtWild() {
        return isAtPvP() || (getX() >= 3011 && getX() <= 3132 && getY() >= 10052 && getY() <= 10175)
                || (getX() >= 2940 && getX() <= 3395 && getY() >= 3525 && getY() <= 4000)
                || (getX() >= 3264 && getX() <= 3279 && getY() >= 3279 && getY() <= 3672)
                || (getX() >= 3158 && getX() <= 3181 && getY() >= 3679 && getY() <= 3697)
                || (getX() >= 3280 && getX() <= 3183 && getY() >= 3885 && getY() <= 3888)
                || (getX() >= 3012 && getX() <= 3059 && getY() >= 10303 && getY() <= 10351)
                || (getX() >= 3060 && getX() <= 3072 && getY() >= 10251 && getY() <= 10263);
    }

    public final boolean isAtPvP() {
        return EdgevillePvPControler.isAtPvP(this) && !EdgevillePvPControler.isAtBank(this);
    }

    public final boolean isInClanwars() {
        return (getX() >= 2981 && getX() <= 3006 && getY() >= 9664 && getY() <= 9694)
                || (getX() >= 2947 && getX() <= 3070 && getY() >= 5506 && getY() <= 5630)
                || (getX() >= 2755 && getX() <= 2876 && getY() >= 5506 && getY() <= 5630);
    }

    public final boolean isInRedPortal() {
        return (getX() >= 2948 && getX() <= 3069 && getY() >= 5507 && getY() <= 5629);
    }

    public final boolean isInClanwarsLobby() {
        return (getX() >= 2981 && getX() <= 3006 && getY() >= 9664 && getY() <= 9694);
    }

    public final boolean atJail() {
        return (getX() >= 1385 && getX() <= 3198 && getY() >= 9816 && getY() <= 9837);
    }

    public final boolean isAtTourny() {
        return (getX() >= 4441 && getX() <= 4474 && getY() >= 4121 && getY() <= 4158);
    }

    public final boolean isAtNonprod() {
        return (getX() >= 1859 && getX() <= 1915 && getY() >= 3215 && getY() <= 3249);
    }

    private enum WildStalker {

        TIER2(20802, 20801, 99), TIER3(20803, 20802, 499), TIER4(20804, 20803, 1999), TIER5(20805, 20804, 4999);

        private int id, oldID, kc;

        WildStalker(int id, int oldID, int kc) {
            this.id = id;
            this.oldID = oldID;
            this.kc = kc;
        }

        public int getHelmID() {
            return id;
        }

        public int getOldHelmID() {
            return oldID;
        }

        public int getKC() {
            return kc;
        }

    }

    public void upgradeWildstalker() {
        for (WildStalker helm : WildStalker.values()) {
            if (getKillCount() == helm.getKC()) {
                if (getBank().getItem(helm.getOldHelmID()) != null) {
                    getBank().removeItem(helm.getOldHelmID());
                    getBank().addItem(new Item(helm.getHelmID(), 1), true);
                } else if (getInventory().containsItem(helm.getOldHelmID(), 1)) {
                    getInventory().deleteItem(helm.getOldHelmID(), 1);
                    getInventory().addItem(helm.getHelmID(), 1);
                }
                sm("Your wildstalker helmet has been upgraded.");
            }
        }
    }

    public String randomKillMessage(Player killed) {
        int random = Utils.getRandom(12);
        switch (random) {
            case 0:
                return "With a crushing blow, you defeat " + killed.getDisplayName() + ".";
            case 1:
                return "It's a humiliating defeat for " + killed.getDisplayName() + ".";
            case 2:
                return "" + killed.getDisplayName() + " didn't stand a chance against you.";
            case 3:
                return "You have defeated " + killed.getDisplayName() + ".";
            case 4:
                return "It's all over for " + killed.getDisplayName() + ".";
            case 5:
                return "" + killed.getDisplayName() + " regrets the day they met you in combat.";
            case 6:
                return "" + killed.getDisplayName() + " falls before your might.";
            case 7:
                return "Can anyone defeat you? Certainly not " + killed.getDisplayName() + ".";
            case 8:
                return "You were clearly a better fighter than " + killed.getDisplayName() + ".";
            case 9:
                return killed.getDisplayName() + " was not match for your power.";
            case 10:
                return "You have proven your superority over " + killed.getDisplayName();
            case 11:
                return "It's official: you are far more awesome than " + killed.getDisplayName();
            case 12:
                return "If " + killed.getDisplayName() + " was an orange, you'd be the juicer.";
            default:
                return "You have killed " + killed.getDisplayName() + ".";
        }
    }

    public static String currentTimes(String dateFormat) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    public static void archiveKills(Player player, Player p2) {
        try {
            String location = "";
            location = "data/logs/kills/" + player.getUsername() + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(location, true));
            writer.write("[" + currentTime("dd MMMMM yyyy 'at' hh:mm:ss z") + "] - " + player.getUsername() + " killed "
                    + p2.getUsername() + "");
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void archiveDeaths(Player player, Player p2) {
        try {
            String location = "";
            location = "data/logs/kills/" + player.getUsername() + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(location, true));
            writer.write("[" + currentTime("dd MMMMM yyyy 'at' hh:mm:ss z") + "] - " + player.getUsername()
                    + " died to " + p2.getUsername() + "");
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void increaseKillCount(Player killed) {
        if (killed == this)
            return;
        archiveKills(this, killed);
        archiveDeaths(killed, this);
        if (hasWildstalker())
            upgradeWildstalker();
        killStreak++;
        killCount++;
        if (!getPlayerRank().isStaff())
            KillScoreBoard.checkRank(this);
        int totalPts = Utils.random(400, 600) + ((killStreak * 200) / 2);
        if (isMember())
            totalPts *= 1.20;
        if (totalPts > 10000)
            totalPts = Utils.random(9500, 10000);
        PKP += totalPts;
        getAdventureLog().addActivity("I have killed " + killed.getDisplayName() + " in a PvP zone.");
        sm("You now have a killstreak of " + HexColours.getShortMessage(Colour.RED, "" + killStreak)
                + (killStreak > 1 ? " kills." : " kill.")
                + (killStreak > killStreakRecord ? " " + HexColours.getShortMessage(Colour.RED, "New Record!") : ""));
        if (killed.killStreak >= 5)
            World.sendNewsMessage(getDisplayName() + " has ended " + killed.getDisplayName()
                            + (killed.getDisplayName().endsWith("s") ? "'" : "s") + " killstreak of " + killed.killStreak + "!",
                    false);
        sm("You gained " + HexColours.getShortMessage(Colour.RED, "" + Utils.getFormattedNumber(totalPts, ','))
                + " pk points, you now have "
                + HexColours.getShortMessage(Colour.RED, "" + Utils.getFormattedNumber(getPKP(), ','))
                + " pk points.");
        if (killStreak > killStreakRecord)
            killStreakRecord++;
        killed.deathCount++;
        killed.killStreak = 0;
        sm("" + randomKillMessage(killed));
    }

    public void increaseKillCountSafe(Player killed) {
        if (killed == this)
            return;
        killCount++;
        sm("" + randomKillMessage(killed));
    }

    public void sendRandomJail(Player p) {
        p.resetWalkSteps();
        switch (Utils.getRandom(3)) {
            case 0:
                p.setNextWorldTile(new WorldTile(3189, 9822, 0));
                break;
            case 1:
                p.setNextWorldTile(new WorldTile(3193, 9819, 0));
                break;
            case 2:
                p.setNextWorldTile(new WorldTile(3188, 9820, 0));
                break;
            case 3:
                p.setNextWorldTile(new WorldTile(3190, 9822, 0));
                break;
        }
    }

    @Override
    public int getSize() {
        return appearence.getSize();
    }

    public boolean isCanPvp() {
        return canPvp;
    }

    public void setCanPvp(boolean canPvp) {
        this.canPvp = canPvp;
        appearence.generateAppearenceData();
        getPackets().sendPlayerOption(canPvp ? "Attack" : "null", 1, true);
        getPackets().sendPlayerUnderNPCPriority(canPvp);
    }

    public PrayerBook getPrayer() {
        return prayer;
    }

    public long getLockDelay() {
        return lockDelay;
    }

    public long getSpecDelay() {
        return specDelay;
    }

    public boolean isLocked() {
        return lockDelay > Utils.currentTimeMillis();
    }

    public long getThievingDelay() {
        return thievingDelay;
    }

    public void setThievingDelay(long time) {
        thievingDelay = Utils.currentTimeMillis() + (time * 600);
    }

    public boolean hasSpellDelay() {
        return spellDelay > Utils.currentTimeMillis();
    }

    public void lock() {
        lockDelay = Long.MAX_VALUE;
    }

    public void lock(long time) {
        lockDelay = Utils.currentTimeMillis() + (time * 600);
    }

    private transient long teleDelay;

    public boolean isTeleporting() {
        return teleDelay >= Utils.currentTimeMillis();
    }

    public void tele(long time) {
        teleDelay = Utils.currentTimeMillis() + (time * 600);
    }

    public void specDelay(long time) {
        specDelay = Utils.currentTimeMillis() + (time * 600);
    }

    public void castSpellDelay(long time) {
        spellDelay = Utils.currentTimeMillis() + (time * 600);
    }

    public void teleportBlock(long time) {
        teleportDelay = Utils.currentTimeMillis() + (time * 600);
    }

    public void unlock() {
        lockDelay = 0;
    }

    public void startteleporting() {
        teleportDelay = Long.MAX_VALUE;
    }

    public void endteleporting() {
        teleportDelay = 0;
    }

    public void teleporting(long time) {
        teleportDelay = Utils.currentTimeMillis() + (time * 600);
    }

    public void movePlayer(final WorldTile dest, int useDelay, int totalDelay) {
        movePlayer(dest, useDelay, totalDelay, null);
    }

    public void movePlayer(final WorldTile dest, int useDelay, int totalDelay, final String message) {
        stopAll();
        lock(totalDelay);
        if (useDelay == 0)
            setNextWorldTile(dest);
        else {
            WorldTasksManager.schedule(new WorldTask() {
                @Override
                public void run() {
                    if (isDead())
                        return;
                    setNextWorldTile(dest);
                    if (message != null)
                        sm(message);
                }
            }, useDelay - 1);
        }
    }

    public void useStairs(int emoteId, final WorldTile dest, int useDelay, int totalDelay) {
        useStairs(emoteId, dest, useDelay, totalDelay, null);
    }

    public void useStairs(int emoteId, final WorldTile dest, int useDelay, int totalDelay, final String message) {
        stopAll();
        lock(totalDelay);
        if (emoteId != -1)
            animate(new Animation(emoteId));
        if (useDelay == 0)
            setNextWorldTile(dest);
        else {
            WorldTasksManager.schedule(new WorldTask() {
                @Override
                public void run() {
                    if (isDead())
                        return;
                    setNextWorldTile(dest);
                    if (message != null)
                        sm(message);
                }
            }, useDelay - 1);
        }
    }

    public void useStairs(int emoteId, final WorldTile dest, int useDelay, int totalDelay, final String message, final boolean resetAnimation) {
        stopAll();
        lock(totalDelay);
        if (emoteId != -1)
            animate(new Animation(emoteId));
        if (useDelay == 0)
            setNextWorldTile(dest);
        else {
            WorldTasksManager.schedule(new WorldTask() {
                @Override
                public void run() {
                    if (isDead())
                        return;
                    if (resetAnimation)
                        animate(new Animation(-1));
                    setNextWorldTile(dest);
                    if (message != null)
                        getPackets().sendGameMessage(message);
                }
            }, useDelay - 1);
        }
    }

    public Bank getBank() {
        return bank;
    }

    public ControlerManager getControlerManager() {
        return controlerManager;
    }

    public void switchMouseButtons() {
        mouseButtons = !mouseButtons;
        refreshMouseButtons();
    }

    public void switchAllowChatEffects() {
        allowChatEffects = !allowChatEffects;
        refreshAllowChatEffects();
    }

    public void refreshAllowChatEffects() {
        getPackets().sendConfig(171, allowChatEffects ? 0 : 1);
    }

    public void refreshMouseButtons() {
        getPackets().sendConfig(170, mouseButtons ? 0 : 1);
    }

    public void refreshPrivateChatSetup() {
        getPackets().sendConfig(287, privateChatSetup);
    }

    public void refreshOtherChatsSetup() {
        getVarsManager().setVarBit(9188, friendChatSetup);
        getVarsManager().setVarBit(3612, clanChatSetup);
        getVarsManager().forceSendVarBit(9191, guestChatSetup);
    }

    public void setClanChatSetup(int clanChatSetup) {
        this.clanChatSetup = clanChatSetup;
    }

    public void setGuestChatSetup(int guestChatSetup) {
        this.guestChatSetup = guestChatSetup;
    }

    public void setPrivateChatSetup(int privateChatSetup) {
        this.privateChatSetup = privateChatSetup;
    }

    public void setFriendChatSetup(int friendChatSetup) {
        this.friendChatSetup = friendChatSetup;
    }

    public int getPrivateChatSetup() {
        return privateChatSetup;
    }

    public boolean isForceNextMapLoadRefresh() {
        return forceNextMapLoadRefresh;
    }

    public void setForceNextMapLoadRefresh(boolean forceNextMapLoadRefresh) {
        this.forceNextMapLoadRefresh = forceNextMapLoadRefresh;
    }

    public FriendsIgnores getFriendsIgnores() {
        return friendsIgnores;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void addPotDelay(long time) {
        potDelay = time + Utils.currentTimeMillis();
    }

    public long getPotDelay() {
        return potDelay;
    }

    public void addFoodDelay(long time) {
        foodDelay = time + Utils.currentTimeMillis();
    }

    public void setFoodDelay(long time) {
        foodDelay = time;
    }

    public long getFoodDelay() {
        return foodDelay;
    }

    public void addBrewDelay(long time) {
        brewDelay = time + Utils.currentTimeMillis();
    }

    public long getBrewDelay() {
        return brewDelay;
    }

    public void addFamiliarDelay(long time) {
        familiarDelay = time + Utils.currentTimeMillis();
    }

    public long getFamiliarDelay() {
        return familiarDelay;
    }

    // RuneSpan
    public void setRunespanPoints(int RunespanPoints) {
        this.RunespanPoints = RunespanPoints;
    }

    public int getRunespanPoints() {
        return RunespanPoints;
    }

    public void setInventoryPoints(int InventoryPoints) {
        this.InventoryPoints = InventoryPoints;
    }

    public int getInventoryPoints() {
        return InventoryPoints;
    }

    public void addComboFoodDelay(long time) {
        combofoodDelay = time + Utils.currentTimeMillis();
    }

    public void setComboFoodDelay(long time) {
        combofoodDelay = time;
    }

    public long getComboFoodDelay() {
        return combofoodDelay;
    }

    public void setPoisonImmune(long time) {
        poisonImmune = time;
    }

    public void addPoisonImmune(long time) {
        poisonImmune = time + Utils.currentTimeMillis();
        getPoison().reset();
    }

    public long getPoisonImmune() {
        return poisonImmune;
    }

    public void addFireImmune(long time) {
        fireImmune = time + Utils.currentTimeMillis();
    }

    public long getFireImmune() {
        return fireImmune;
    }

    public void addAntifire(long time) {
        antiFire = time + Utils.currentTimeMillis();
    }

    public long getAntifire() {
        return antiFire;
    }

    public void addSuperAntifire(long time) {
        superAntifire = time + Utils.currentTimeMillis();
    }

    public long getSuperAntifire() {
        return superAntifire;
    }

    public long getLastLoggedIn() {
        return lastLoggedIn;
    }

    @Override
    public void heal(int ammount, int extra) {
        super.heal(ammount, extra);
        refreshHitPoints();
    }

    public MusicsManager getMusicsManager() {
        return musicsManager;
    }

    public HintIconsManager getHintIconsManager() {
        return hintIconsManager;
    }

    public boolean isInCombat(int milliseconds) {
        return getAttackedByDelay() + milliseconds > Utils.currentTimeMillis();
    }

    public Map<Integer, Item[]> getStaffCharges() {
        return staffCharges;
    }

    public boolean isCastVeng() {
        return castedVeng;
    }

    public void setVengeance(boolean castVengeance) {
        temporaryAttribute().put("vengeanceActivated", castVengeance);
    }

    public void setDisruption(boolean castDisruption) {
        temporaryAttribute().put("disruptionActivated", castDisruption);
    }

    public void setVengeance(long vengDelay) {
        temporaryAttribute().put("vengDelay", vengDelay + Utils.currentTimeMillis());
    }

    public void setTeleBlockDelay(long teleblockDelay) {
        temporaryAttribute().put("teleblockDelay", teleblockDelay + Utils.currentTimeMillis());
    }

    public void setTeleBlockImmune(long teleblockImmune) {
        temporaryAttribute().put("teleblockImmune", teleblockImmune + Utils.currentTimeMillis());
    }

    public void setPrayerRenewal(long renewalDelay) {
        temporaryAttribute().put("renewalDelay", renewalDelay + Utils.currentTimeMillis());
    }

    public void setOverload(long overloadDelay) {
        temporaryAttribute().put("overloadDelay", overloadDelay + Utils.currentTimeMillis());
    }

    public void setDisruption(long disruptionDelay) {
        temporaryAttribute().put("disruptionDelay", disruptionDelay + Utils.currentTimeMillis());
    }

    public void setTemporaryTarget(Entity target) {
        temporaryAttribute().put("temporaryTarget", target);
    }

    public long getTemporaryActionDelay() {
        Long temporaryActionDelay = (Long) temporaryAttribute().get("temporaryActionDelay");
        if (temporaryActionDelay == null)
            return 0;
        return temporaryActionDelay;
    }

    public Entity getTemporaryTarget() {
        Entity temporaryTarget = (Entity) temporaryAttribute().get("temporaryTarget");
        if (temporaryTarget == null)
            return null;
        return temporaryTarget;
    }

    public int getOverloadSeconds() {
        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(getOverloadDelay() - Utils.currentTimeMillis());
        return seconds;
    }

    public int getRenewalSeconds() {
        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(getPrayerRenewalDelay() - Utils.currentTimeMillis());
        return seconds;
    }

    public long getOverloadDelay() {
        Long overloadDelay = (Long) temporaryAttribute().get("overloadDelay");
        if (overloadDelay == null)
            return 0;
        return overloadDelay;
    }

    public long getPrayerRenewalDelay() {
        Long renewalDelay = (Long) temporaryAttribute().get("renewalDelay");
        if (renewalDelay == null)
            return 0;
        return renewalDelay;
    }

    public long getVengDelay() {
        Long vengDelay = (Long) temporaryAttribute().get("vengDelay");
        if (vengDelay == null)
            return 0;
        return vengDelay;
    }

    public long getDisruptionDelay() {
        Long disruptionDelay = (Long) temporaryAttribute().get("disruptionDelay");
        if (disruptionDelay == null)
            return 0;
        return disruptionDelay;
    }

    public long getTeleBlockDelay() {
        Long teleblock = (Long) temporaryAttribute().get("teleblockDelay");
        if (teleblock == null)
            return 0;
        return teleblock;
    }

    public long getTeleBlockImmune() {
        Long teleimmune = (Long) temporaryAttribute().get("teleblockImmune");
        if (teleimmune == null)
            return 0;
        return teleimmune;
    }

    public void setFreezeDelay(long freezeDelay) {
        temporaryAttribute().put("freezeDelay", freezeDelay + Utils.currentTimeMillis());
    }

    public void addFreezeDelay(long freezeDelay, boolean entangle) {
        temporaryAttribute().put("freezeDelay", freezeDelay + Utils.currentTimeMillis());
        if (getFreezeDelay() >= Utils.currentTimeMillis()) {
            resetWalkSteps();
            if (this instanceof Player) {
                Player p = (Player) this;
                p.resetWalkSteps();
                if (!entangle)
                    p.sm("You have been frozen.");
            }
        } else {
            if (entangle) {
                if (this instanceof Player) {
                    Player p = (Player) this;
                    p.getPackets().sendGameMessage("This player is already effected by this spell.", true);
                }
            }
        }
    }

    public void setFreezeImmune(long freezeImmune) {
        temporaryAttribute().put("freezeImmune", freezeImmune + Utils.currentTimeMillis());
    }

    public void resetFreezeDelay() {
        temporaryAttribute().remove("freezeDelay");
    }

    public long getFreezeDelay() {
        Long freezeDelay = (Long) temporaryAttribute().get("freezeDelay");
        if (freezeDelay == null)
            return 0;
        return freezeDelay;
    }

    public long getFreezeImmuneDelay() {
        Long freezeImmune = (Long) temporaryAttribute().get("freezeImmune");
        if (freezeImmune == null)
            return 0;
        return freezeImmune;
    }

    public int getKillCount() {
        return killCount;
    }

    public int getBarrowsKillCount() {
        return barrowsKillCount;
    }

    public int setBarrowsKillCount(int barrowsKillCount) {
        return this.barrowsKillCount = barrowsKillCount;
    }

    public int setKillCount(int killCount) {
        return this.killCount = killCount;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public int setDeathCount(int deathCount) {
        return this.deathCount = deathCount;
    }

    public void setCloseInterfacesEvent(Runnable closeInterfacesEvent) {
        this.closeInterfacesEvent = closeInterfacesEvent;
    }

    public void addItemEvent(Runnable addItemEvent) {
        this.addItemEvent = addItemEvent;
    }

    public boolean isPermBanned() {
        return permBanned;
    }

    public void setPermBanned(boolean permBanned) {
        this.permBanned = permBanned;
    }

    public long getBanned() {
        return banned;
    }

    public void setBanned(long banned) {
        this.banned = banned;
    }

    public ChargesManager getCharges() {
        return charges;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean[] getKilledBarrowBrothers() {
        return killedBarrowBrothers;
    }

    public void setHiddenBrother(int hiddenBrother) {
        this.hiddenBrother = hiddenBrother;
    }

    public int getHiddenBrother() {
        return hiddenBrother;
    }

    public void resetBarrows() {
        hiddenBrother = -1;
        killedBarrowBrothers = new boolean[7]; // includes new bro for future
        // use
        barrowsKillCount = 0;
    }

    public boolean isToggleMessages() {
        return toggleMessages;
    }

    public void setToggleMessages(boolean toggleMessages) {
        this.toggleMessages = toggleMessages;
    }

    @SuppressWarnings("deprecation")
    public void makeMember(int days) {
        if (memberTill < Utils.currentTimeMillis())
            memberTill = Utils.currentTimeMillis();
        Date date = new Date(memberTill);
        date.setDate(date.getDate() + days);
        memberTill = date.getTime();
    }

    @SuppressWarnings("deprecation")
    public String getMemberTill() {
        return "<col=" + color + ">" + (new Date(memberTill).toGMTString()) + ".";
    }

    public int[] getPouches() {
        return pouches;
    }

    public EmotesManager getEmotesManager() {
        return emotesManager;
    }

    public String getLastIP() {
        return lastIP;
    }

    public void setPKP(int PKP) {
        this.PKP = PKP;
    }

    public int getPKP() {
        return PKP;
    }

    public String getLastHostname() {
        InetAddress addr;
        try {
            addr = InetAddress.getByName(getLastIP());
            String hostname = addr.getHostName();
            return hostname;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PriceCheckManager getPriceCheckManager() {
        return priceCheckManager;
    }

    public void setPestPoints(int pestPoints) {
        this.pestPoints = pestPoints;
    }

    public int getPestPoints() {
        return pestPoints;
    }

    public boolean isUpdateMovementType() {
        return updateMovementType;
    }

    public long getLastPublicMessage() {
        return lastPublicMessage;
    }

    public void setLastPublicMessage(long lastPublicMessage) {
        this.lastPublicMessage = lastPublicMessage;
    }

    public CutscenesManager getCutscenesManager() {
        return cutscenesManager;
    }

    public void kickPlayerFromClanChannel(String name) {
        if (clanManager == null)
            return;
        clanManager.kickPlayerFromChat(this, name);
    }

    public void sendClanChannelMessage(String message) {
        if (clanManager == null)
            return;
        clanManager.sendMessage(this, message);
    }

    public void sendGuestClanChannelMessage(String message) {
        if (guestClanManager == null)
            return;
        guestClanManager.sendMessage(this, message);
    }

    public void sendClanChannelQuickMessage(QuickChatMessage message) {
        if (clanManager == null)
            return;
        clanManager.sendQuickMessage(this, message);
    }

    public void sendGuestClanChannelQuickMessage(QuickChatMessage message) {
        if (guestClanManager == null)
            return;
        guestClanManager.sendQuickMessage(this, message);
    }

    public void kickPlayerFromFriendsChannel(String name) {
        if (currentFriendChat == null)
            return;
        currentFriendChat.kickPlayerFromChat(this, name);
    }

    public void sendFriendsChannelMessage(String message) {
        if (currentFriendChat == null)
            return;
        currentFriendChat.sendMessage(this, message);
    }

    public void sendFriendsChannelQuickMessage(QuickChatMessage message) {
        if (currentFriendChat == null)
            return;
        currentFriendChat.sendQuickMessage(this, message);
    }

    public void sendPublicChatMessage(PublicChatMessage message) {
        for (int regionId : getMapRegionsIds()) {
            List<Integer> playersIndexes = World.getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player p = World.getPlayers().get(playerIndex);
                if (p == null || !p.hasStarted() || p.hasFinished()
                        || p.getLocalPlayerUpdate().getLocalPlayers()[getIndex()] == null)
                    continue;
                p.getPackets().sendPublicMessage(this, message);
            }
        }
        if (Settings.discordEnabled) {
            // Launcher.getDiscordBot().getChannelByName("server-ingame-chat")
            //      .sendMessage(this.getDisplayName().toString() + ": " + message.getMessage().toString());
        }
    }

    public int[] getCompletionistCapeCustomized() {
        return completionistCapeCustomized;
    }

    public void setCompletionistCapeCustomized(int[] skillcapeCustomized) {
        this.completionistCapeCustomized = skillcapeCustomized;
    }

    public int[] getMaxedCapeCustomized() {
        return maxedCapeCustomized;
    }

    public void setMaxedCapeCustomized(int[] maxedCapeCustomized) {
        this.maxedCapeCustomized = maxedCapeCustomized;
    }

    public void setSkullId(int skullId) {
        this.skullId = skullId;
    }

    public int getSkullId() {
        return skullId;
    }

    public boolean isFilterGame() {
        return filterGame;
    }

    public void setFilterGame(boolean filterGame) {
        this.filterGame = filterGame;
    }

    public void addLogicPacketToQueue(LogicPacket packet) {
        for (LogicPacket p : logicPackets) {
            if (p.getId() == packet.getId()) {
                logicPackets.remove(p);
                break;
            }
        }
        logicPackets.add(packet);
    }

    public DominionTower getDominionTower() {
        return dominionTower;
    }

    public void setAfkDelay(int delay) {
        this.afkDelay = delay;
    }

    public long getVoteDelay() {
        return voteDelay;
    }

    public void setVoteDelay(long voteDelay) {
        this.voteDelay = voteDelay;
    }

    public void setRouteEvent(RouteEvent routeEvent) {
        this.routeEvent = routeEvent;
    }

    public Trade getTrade() {
        return trade;
    }

    public TradeStore getTradeStore() {
        return tradestore;
    }

    public AssistManager getAssist() {
        return assist;
    }

    public double setWeight(double d) {
        return weight = d;
    }

    public double getWeight() {
        return weight;
    }

    public String getTeleBlockTimeleft() {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(getTeleBlockDelay() - Utils.currentTimeMillis());
        long seconds = 1 + TimeUnit.MILLISECONDS.toSeconds(getTeleBlockDelay() - Utils.currentTimeMillis());
        String secondsMessage = (seconds != 1 ? seconds + " seconds" : "second");
        String minutesMessage = (minutes != 1 ? minutes + " minutes" : "minute");
        return (minutes > 0 ? minutesMessage : secondsMessage);
    }

    public String getTimeLeft(long value) {
        long seconds = 1 + TimeUnit.MILLISECONDS.toSeconds(value - Utils.currentTimeMillis());
        long minutes = TimeUnit.SECONDS.toMinutes(seconds);
        String message = (minutes > 0 ? minutes + "m" : seconds + "s");
        return message;
    }

    public String getTimePieceTimer() {
        return Utils.getFormatedTimeShort3(timePiece);
    }

    public void setChargeDelay(long chargeDelay) {
        temporaryAttribute().put("Charge", chargeDelay + Utils.currentTimeMillis());
    }

    public void setChargeImmune(long chargeImmune) {
        temporaryAttribute().put("ChargeImmune", chargeImmune + Utils.currentTimeMillis());
    }

    public long getChargeDelay() {
        Long charge = (Long) temporaryAttribute().get("Charge");
        if (charge == null)
            return 0;
        return charge;
    }

    public long getChargeImmune() {
        Long chargeimmune = (Long) temporaryAttribute().get("ChargeImmune");
        if (chargeimmune == null)
            return 0;
        return chargeimmune;
    }

    public String getChargeTimeleft() {
        long minutes = 1 + TimeUnit.MILLISECONDS.toMinutes(getChargeDelay() - Utils.currentTimeMillis());
        long seconds = 1 + TimeUnit.MILLISECONDS.toSeconds(getChargeDelay() - Utils.currentTimeMillis());
        String secondsMessage = (seconds != 1 ? seconds + " seconds" : "second");
        String minutesMessage = (minutes != 1 ? minutes + " minutes" : "minute");
        return (minutes > 0 ? minutesMessage : secondsMessage);
    }

    public void setPrayerDelay(long prayDelay) {
        temporaryAttribute().put("PrayerBlocked", prayDelay + Utils.currentTimeMillis());
        prayer.closeProtectPrayers();
    }

    public long getPrayerDelay() {
        Long prayblock = (Long) temporaryAttribute().get("PrayerBlocked");
        if (prayblock == null)
            return 0;
        return prayblock;
    }

    public Familiar getFamiliar() {
        return familiar;
    }

    public void setFamiliar(Familiar familiar) {
        this.familiar = familiar;
    }

    public FriendChatsManager getCurrentFriendChat() {
        return currentFriendChat;
    }

    public void setCurrentFriendChat(FriendChatsManager currentFriendChat) {
        this.currentFriendChat = currentFriendChat;
    }

    public String getCurrentFriendChatOwner() {
        return currentFriendChatOwner;
    }

    public String getReferrer() {
        return referrer;
    }

    public String putReferrer(String rsn) {
        return referrer = rsn;
    }

    public String getReferred() {
        return referredBy;
    }

    public String putReferred(String rsn) {
        return referredBy = rsn;
    }

    public int AddRefPoint() {
        return totalReferred += 1;
    }

    public int getTotalRefs() {
        return totalReferred;
    }

    public void setCurrentFriendChatOwner(String currentFriendChatOwner) {
        this.currentFriendChatOwner = currentFriendChatOwner;
    }

    public int getSummoningLeftClickOption() {
        return summoningLeftClickOption;
    }

    public void setSummoningLeftClickOption(int summoningLeftClickOption) {
        this.summoningLeftClickOption = summoningLeftClickOption;
    }

    public boolean canUseCommand() {
        if (isAtWild() || getControlerManager().getControler() instanceof FightPitsArena
                || getControlerManager().getControler() instanceof CorpBeastControler
                || getControlerManager().getControler() instanceof PestControlLobby
                || getControlerManager().getControler() instanceof PestControlGame
                || getControlerManager().getControler() instanceof ZGDControler
                || getControlerManager().getControler() instanceof GodWars
                || getControlerManager().getControler() instanceof DTControler
                || getControlerManager().getControler() instanceof DuelArena
                || getControlerManager().getControler() instanceof CastleWarsPlaying
                || getControlerManager().getControler() instanceof CastleWarsWaiting
                || getControlerManager().getControler() instanceof FightCaves
                || getControlerManager().getControler() instanceof FightKiln || FfaZone.inPvpArea(this)
                || getControlerManager().getControler() instanceof NomadsRequiem
                || getControlerManager().getControler() instanceof QueenBlackDragonController
                || getControlerManager().getControler() instanceof WarControler) {
            return false;
        }
        if (getControlerManager().getControler() instanceof CrucibleControler) {
            CrucibleControler controler = (CrucibleControler) getControlerManager().getControler();
            return !controler.isInside();
        }
        return true;
    }

    public long getPolDelay() {
        return polDelay;
    }

    public void addPolDelay(long delay) {
        polDelay = delay + Utils.currentTimeMillis();
    }

    public void setPolDelay(long delay) {
        this.polDelay = delay;
    }

    public List<Integer> getSwitchItemCache() {
        return switchItemCache;
    }

    public List<Integer> getTakeOffSwitchItemCache() {
        return switchTakeOffItemCache;
    }

    public AuraManager getAuraManager() {
        return auraManager;
    }

    public int getMovementType() {
        if (getTemporaryMoveType() != -1)
            return getTemporaryMoveType();
        return getRun() ? RUN_MOVE_TYPE : WALK_MOVE_TYPE;
    }

    public List<String> getOwnedObjectManagerKeys() {
        if (ownedObjectsManagerKeys == null) // temporary
            ownedObjectsManagerKeys = new LinkedList<String>();
        return ownedObjectsManagerKeys;
    }

    public boolean hasInstantSpecial(final int weaponId) {
        switch (weaponId) {
            case 4153:
            case 14679:
            case 15486:
            case 11736:
            case 4675:
            case 18355:
            case 6914:
            case 22207:
            case 22209:
            case 22211:
            case 22213:
            case 1377:
            case 13472:
            case 35:// Excalibur
            case 8280:
            case 14632:
                return true;
            default:
                return false;
        }
    }

    public void sm(String string) {
        if (string == null)
            return;
        getPackets().sendGameMessage(string);
    }

    public void addXp(int skillId, double exp) {
        getSkills().addXp(skillId, exp);
    }

    public void addItem(int itemId, int amount) {
        getInventory().addItem(itemId, amount);
    }

    public void addItemDrop(int itemId, int amount) {
        getInventory().addItemDrop(itemId, amount);
    }

    public void removeItem(int itemId, int amount) {
        getInventory().deleteItem(itemId, amount);
    }

    public void performInstantSpecial(final int weaponId) {
        int specAmt = PlayerCombat.getSpecialAmmount(weaponId);
        if (combatDefinitions.hasRingOfVigour())
            specAmt *= 0.9;
        if (combatDefinitions.getSpecialAttackPercentage() < specAmt) {
            sm("You don't have enough power left.");
            combatDefinitions.desecreaseSpecialAttack(0);
            return;
        }
        switch (weaponId) {
            case 4153:
            case 14679:
                combatDefinitions.usingSpecialAttack = true;
                combatDefinitions.setInstantAttack(true);
                Entity target = (Entity) temporaryAttribute().get("last_target");
                if (target != null && target.temporaryAttribute().get("last_attacker") == this) {
                    if (!(getActionManager().getAction() instanceof PlayerCombat)
                            || ((PlayerCombat) getActionManager().getAction()).getTarget() != target) {
                        getActionManager().setAction(new PlayerCombat(target));
                    }
                }
                break;
            case 1377:
            case 13472:
                animate(new Animation(1056));
                gfx(new Graphics(246));
                setNextForceTalk(new ForceTalk("Raarrrrrgggggghhhhhhh!"));
                int defence = (int) (skills.getLevelForXp(Skills.DEFENCE) * 0.90D);
                int attack = (int) (skills.getLevelForXp(Skills.ATTACK) * 0.90D);
                int range = (int) (skills.getLevelForXp(Skills.RANGE) * 0.90D);
                int magic = (int) (skills.getLevelForXp(Skills.MAGIC) * 0.90D);
                int strength = (int) (skills.getLevelForXp(Skills.STRENGTH) * 1.2D);
                skills.set(Skills.DEFENCE, defence);
                skills.set(Skills.ATTACK, attack);
                skills.set(Skills.RANGE, range);
                skills.set(Skills.MAGIC, magic);
                skills.set(Skills.STRENGTH, strength);
                combatDefinitions.desecreaseSpecialAttack(specAmt);
                if (getUsername().equalsIgnoreCase("tristam")) {
                    specAmt += 100;
                }
                break;
            case 35:// Excalibur
            case 8280:
            case 14632:
                animate(new Animation(1168));
                gfx(new Graphics(247));
                setNextForceTalk(new ForceTalk("For Camelot!"));
                final boolean enhanced = weaponId == 14632;
                skills.set(Skills.DEFENCE, enhanced ? (int) (skills.getLevelForXp(Skills.DEFENCE) * 1.15D)
                        : (skills.getLevel(Skills.DEFENCE) + 8));
                WorldTasksManager.schedule(new WorldTask() {
                    int count = 5;

                    @Override
                    public void run() {
                        if (isDead() || hasFinished() || getHitpoints() >= getMaxHitpoints()) {
                            stop();
                            return;
                        }
                        heal(enhanced ? 80 : 40);
                        if (count-- == 0) {
                            stop();
                            return;
                        }
                    }
                }, 4, 2);
                combatDefinitions.desecreaseSpecialAttack(specAmt);
                break;

            case 18355:
            case 4675:
            case 6914:
                combatDefinitions.desecreaseSpecialAttack(0);
                return;

            case 15486:
            case 11736:
            case 22207:
            case 22209:
            case 22211:
            case 22213:
                animate(new Animation(12804));
                gfx(new Graphics(2319));// 2320
                gfx(new Graphics(2321));
                addPolDelay(60000);
                combatDefinitions.desecreaseSpecialAttack(specAmt);
                break;
        }
    }

    public ClansManager getClanManager() {
        return clanManager;
    }

    public ClanMember getClanMembers() {
        return clanMember;
    }

    public Hit getHitManager() {
        return hitManager;
    }

    public void setClanManager(ClansManager clanManager) {
        this.clanManager = clanManager;
    }

    public ClansManager getGuestClanManager() {
        return guestClanManager;
    }

    public void setGuestClanManager(ClansManager guestClanManager) {
        this.guestClanManager = guestClanManager;
    }

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    public boolean isConnectedClanChannel() {
        return connectedClanChannel;
    }

    public void setConnectedClanChannel(boolean connectedClanChannel) {
        this.connectedClanChannel = connectedClanChannel;
    }

    public boolean isDisruptionActivated() {
        Boolean disruptionActivated = (Boolean) temporaryAttribute().get("disruptionActivated");
        if (disruptionActivated == null)
            return false;
        return disruptionActivated;
    }

    public boolean isVengeanceActivated() {
        Boolean vengeanceActivated = (Boolean) temporaryAttribute().get("vengeanceActivated");
        if (vengeanceActivated == null)
            return false;
        return vengeanceActivated;
    }

    public int getPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(int publicStatus) {
        this.publicStatus = publicStatus;
    }

    public int getClanStatus() {
        return clanStatus;
    }

    public void setClanStatus(int clanStatus) {
        this.clanStatus = clanStatus;
    }

    public int getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(int tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public int getAssistStatus() {
        return assistStatus;
    }

    public void setAssistStatus(int assistStatus) {
        this.assistStatus = assistStatus;
    }

    public Notes getNotes() {
        return notes;
    }

    public IsaacKeyPair getIsaacKeyPair() {
        return isaacKeyPair;
    }

    public QuestManager getQuestManager() {
        return questManager;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public boolean isCompletedFightCaves() {
        return completedFightCaves;
    }

    public ArrayList<String> getIpList() {
        return ipList;
    }

    public void setCompletedFightCaves() {
        if (!completedFightCaves) {
            completedFightCaves = true;
            refreshFightKilnEntrance();
        }
    }

    public boolean isCompletedFightKiln() {
        return completedFightKiln;
    }

    public void setCompletedFightKiln() {
        completedFightKiln = true;
    }

    public int getRuleCount() {
        return ruleCount;
    }

    public void setRuleCount(int ruleCount) {
        this.ruleCount = ruleCount;
    }

    public boolean isWonFightPits() {
        return wonFightPits;
    }

    public void setWonFightPits() {
        wonFightPits = true;
    }

    public boolean isCantTrade() {
        return cantTrade;
    }

    public void setCantTrade(boolean canTrade) {
        this.cantTrade = canTrade;
    }

    private String customName;

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomTitle() {
        return customTitle;
    }

    public void setCustomTitle(String customTitle) {
        this.customTitle = customTitle;
    }

    private String customTitle2;

    public String getCustomTitle2() {
        return customTitle2;
    }

    public void setCustomTitle2(String customTitle2) {
        this.customTitle2 = customTitle2;
    }

    /**
     * Gets the pet.
     *
     * @return The pet.
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * Sets the pet.
     *
     * @param pet The pet to set.
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    /**
     * Gets the petManager.
     *
     * @return The petManager.
     */
    public PetManager getPetManager() {
        return petManager;
    }

    /**
     * Sets the petManager.
     *
     * @param petManager The petManager to set.
     */
    public void setPetManager(PetManager petManager) {
        this.petManager = petManager;
    }

    public boolean isXpLocked() {
        return xpLocked;
    }

    public void setXpLocked(boolean locked) {
        this.xpLocked = locked;
    }

    public int getLastBonfire() {
        return lastBonfire;
    }

    public void setLastBonfire(int lastBonfire) {
        this.lastBonfire = lastBonfire;
    }

    public boolean isYellOff() {
        return yellOff;
    }

    public void setYellOff(boolean yellOff) {
        this.yellOff = yellOff;
    }

    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    public double getHpBoostMultiplier() {
        return hpBoostMultiplier;
    }

    public void setHpBoostMultiplier(double hpBoostMultiplier) {
        this.hpBoostMultiplier = hpBoostMultiplier;
    }

    public boolean isToogleLootShare() {
        return toogleLootShare;
    }

    public GrotwormLair grotwormLair() {
        return grotwormLair;
    }

    public void disableLootShare() {
        if (isToogleLootShare())
            toogleLootShare();
    }

    public void toogleLootShare() {
        this.toogleLootShare = !toogleLootShare;
        refreshToogleLootShare();
    }

    public void refreshToogleLootShare() {
        varsManager.forceSendVarBit(4071, toogleLootShare ? 1 : 0);
    }

    public boolean isToogleCoinShare() {
        return toogleCoinShare;
    }

    public void disableCoinShare() {
        if (isToogleCoinShare())
            toogleCoinShare();
    }

    public void toogleCoinShare() {
        this.toogleCoinShare = !toogleCoinShare;
        refreshToogleCoinShare();
    }

    public void refreshToogleCoinShare() {
        varsManager.forceSendVarBit(4071, toogleCoinShare ? 1 : 0);
    }

    /**
     * Gets the killedQueenBlackDragon.
     *
     * @return The killedQueenBlackDragon.
     */
    public boolean isKilledQueenBlackDragon() {
        return killedQueenBlackDragon;
    }

    public transient boolean itemSwitch;

    public boolean isProcessingVote() {
        return this.processingVote;
    }

    public int getVotePoints() {
        return this.votePoints;
    }

    public void setProcessingVote(boolean state) {
        this.processingVote = state;
    }

    public double[] getWarriorPoints() {
        return warriorPoints;
    }

    public void setWarriorPoints(int index, double pointsDifference) {
        warriorPoints[index] += pointsDifference;
        if (warriorPoints[index] < 0) {
            Controler controler = getControlerManager().getControler();
            if (controler == null || !(controler instanceof WarriorsGuild))
                return;
            WarriorsGuild guild = (WarriorsGuild) controler;
            guild.inCyclopse = false;
            setNextWorldTile(WarriorsGuild.CYCLOPS_LOBBY);
            warriorPoints[index] = 0;
        } else if (warriorPoints[index] > 65535)
            warriorPoints[index] = 65535;
        refreshWarriorPoints(index);
    }

    public void refreshWarriorPoints(int index) {
        varsManager.sendVarBit(index + 8662, (int) warriorPoints[index]);
    }

    private void warriorCheck() {
        if (warriorPoints == null || warriorPoints.length != 6)
            warriorPoints = new double[6];
    }

    public boolean member;

    public long memberTill;

    public boolean isMember() {
        return member;
    }

    public void setVotePoints(int votepoints) {
        this.votePoints = votepoints;
    }

    public void sendCompReqMessages() {
        if (!hasCompletionistStatRequirements()) {

            StringBuffer text = new StringBuffer();
            text.append("You need level 99 in the following: ").append("<br><br>");
            for (int skill = 0; skill < 19; skill++) {
                if (getSkills().getLevelForXp(skill) >= 99)
                    continue;
                text.append(getSkills().getSkillName(skill)).append("<br>");
            }
            if (getSkills().getLevelForXp(20) < 99)
                text.append(getSkills().getSkillName(20)).append("<br>").append("<br>");
            if (getSkills().getLevelForXp(23) < 99)
                text.append(getSkills().getSkillName(23)).append("<br>").append("<br>");
            if (getSkills().getLevelForXp(Skills.DUNGEONEERING) != 120) {
                text.append("You need level 120 in the following: ").append("<br>").append("<br>");
                text.append(getSkills().getSkillName(24)).append("<br>").append("<br>");
            }
            getInterfaceManager().sendInterface(275);
            for (int i = 0; i < 150; i++)
                getPackets().sendIComponentText(275, i, "");
            getPackets().sendIComponentText(275, 1, "*Completionist Requirements*");
            getPackets().sendIComponentText(275, 10, text.toString());
        }
        if (!isCompletedFightKiln())
            sm("You must have completed the Fight kiln.");
        if (!isCompletedFightCaves())
            sm("You must have completed the Fight caves.");
        checkRequirement("Corporeal Beast");
        checkRequirement("Kree'arra");
        checkRequirement("K'ril_Tsutsaroth");
        checkRequirement("General Graardor");
        checkRequirement("Commander Zilyana");
        checkRequirement("King Black Dragon");
        checkRequirement("Dagannoth Rex");
        checkRequirement("Dagannoth Prime");
        checkRequirement("Dagannoth Supreme");

    }

    public void checkRequirement(String name) {
        int totalKills = 0;
        for (AchievementKills achievement : AchievementKills.values()) {
            if (achievement.name().replace("_", " ").replace("'", "").equalsIgnoreCase(name)) {
                totalKills = (getBossKillcount().get(name) != null ? getBossKillcount().get(name).intValue() : 0);
                if (totalKills < achievement.getKills()) {
                    sm("You must have killed at least " + achievement.getKills() + " " + name + ", "
                            + (achievement.getKills() - totalKills) + " left.");
                }
            }
        }
    }

    /**
     * Sets the killedQueenBlackDragon.
     *
     * @param killedQueenBlackDragon The killedQueenBlackDragon to set.
     */
    public void setKilledQueenBlackDragon(boolean killedQueenBlackDragon) {
        this.killedQueenBlackDragon = killedQueenBlackDragon;
    }

    public boolean hasLargeSceneView() {
        return largeSceneView;
    }

    public void setLargeSceneView(boolean largeSceneView) {
        this.largeSceneView = largeSceneView;
    }

    public boolean isDeveloperMode() {
        return developerMode;
    }

    public String getGameType() {
        return gameType;
    }

    public boolean isOldItemsLook() {
        return oldItemsLook;
    }

    private boolean shiftDrop;

    public boolean isShiftDrop() {
        return shiftDrop;
    }

    public void switchShiftDrop() {
        shiftDrop = !shiftDrop;
        getPackets().sendCustomPacket161();
    }

    private boolean slowDrag;

    public boolean isSlowDrag() {
        return slowDrag;
    }

    public void switchSlowDrag() {
        slowDrag = !slowDrag;
        getPackets().sendCustomPacket161();
    }

    public void switchDeveloperMode() {
        developerMode = !developerMode;
        getPackets().sendDeveloperPacket();
    }

    public void setGameType(String name) {
        gameType = name;
        getPackets().sendDeveloperPacket();
    }

    private boolean zoom;

    public boolean isZoom() {
        return zoom;
    }

    public void switchZoom() {
        zoom = !zoom;
        getPackets().sendCustomPacket161();
    }

    public void switchItemsLook() {
        oldItemsLook = !oldItemsLook;
        getPackets().sendItemsLook();
    }

    public DuelRules getLastDuelRules() {
        return lastDuelRules;
    }

    public void setLastDuelRules(DuelRules duelRules) {
        this.lastDuelRules = duelRules;
    }

    public boolean isTalkedWithMarv() {
        return talkedWithMarv;
    }

    public void setTalkedWithMarv() {
        talkedWithMarv = true;
    }

    public int getCrucibleHighScore() {
        return crucibleHighScore;
    }

    public void increaseCrucibleHighScore() {
        crucibleHighScore++;
    }

    private GodwarsKillcount godwarsKillcount;

    public GodwarsKillcount getGodwarsKillcount() {
        return godwarsKillcount;
    }

    public transient Entity combatTarget;

    public transient ObjectScript objectScript;

    public Entity setTargetName(Player player) {
        return combatTarget = player;
    }

    public Entity getTarget() {
        return combatTarget;
    }

    public static void setWeapon(Player player, Item weapon) {
        player.getEquipment().getItems().set(Equipment.SLOT_WEAPON, weapon);
        player.getEquipment().refresh(Equipment.SLOT_WEAPON);
        player.getAppearence().generateAppearenceData();
    }

    public static void setHat(Player player, Item hat) {
        player.getEquipment().getItems().set(Equipment.SLOT_HAT, hat);
        player.getEquipment().refresh(Equipment.SLOT_HAT);
        player.getAppearence().generateAppearenceData();
    }

    public static void setCape(Player player, Item cape) {
        player.getEquipment().getItems().set(Equipment.SLOT_CAPE, cape);
        player.getEquipment().refresh(Equipment.SLOT_CAPE);
        player.getAppearence().generateAppearenceData();
    }

    public static void setAmulet(Player player, Item cape) {
        player.getEquipment().getItems().set(Equipment.SLOT_AMULET, cape);
        player.getEquipment().refresh(Equipment.SLOT_AMULET);
        player.getAppearence().generateAppearenceData();
    }

    public static void setChest(Player player, Item chest) {
        player.getEquipment().getItems().set(Equipment.SLOT_CHEST, chest);
        player.getEquipment().refresh(Equipment.SLOT_CHEST);
        player.getAppearence().generateAppearenceData();
    }

    public static void setShield(Player player, Item shield) {
        player.getEquipment().getItems().set(Equipment.SLOT_SHIELD, shield);
        player.getEquipment().refresh(Equipment.SLOT_SHIELD);
        player.getAppearence().generateAppearenceData();
    }

    public static void setLegs(Player player, Item legs) {
        player.getEquipment().getItems().set(Equipment.SLOT_LEGS, legs);
        player.getEquipment().refresh(Equipment.SLOT_LEGS);
        player.getAppearence().generateAppearenceData();
    }

    public static void setHands(Player player, Item hands) {
        player.getEquipment().getItems().set(Equipment.SLOT_HANDS, hands);
        player.getEquipment().refresh(Equipment.SLOT_HANDS);
        player.getAppearence().generateAppearenceData();
    }

    public static void setFeet(Player player, Item feet) {
        player.getEquipment().getItems().set(Equipment.SLOT_FEET, feet);
        player.getEquipment().refresh(Equipment.SLOT_FEET);
        player.getAppearence().generateAppearenceData();
    }

    public static void setRing(Player player, Item ring) {
        player.getEquipment().getItems().set(Equipment.SLOT_RING, ring);
        player.getEquipment().refresh(Equipment.SLOT_RING);
        player.getAppearence().generateAppearenceData();
    }

    public static void setArrows(Player player, Item arrows) {
        player.getEquipment().getItems().set(Equipment.SLOT_ARROWS, arrows);
        player.getEquipment().refresh(Equipment.SLOT_ARROWS);
        player.getAppearence().generateAppearenceData();
    }

    public static void setAura(Player player, Item aura) {
        player.getEquipment().getItems().set(Equipment.SLOT_AURA, aura);
        player.getEquipment().refresh(Equipment.SLOT_AURA);
        player.getAppearence().generateAppearenceData();
    }

    public boolean isDeveloper() {
        return getPlayerRank().getRank()[0] == Rank.DEVELOPER;
    }

    public boolean isModerator() {
        return getPlayerRank().getRank()[0] == Rank.MODERATOR;
    }

    public boolean isStaff() {
        return getPlayerRank().isStaff();

    }

    public void switchRCReport() {
        RCReport = !RCReport;
        refreshRCReport();
        appearence.generateAppearenceData();
        getPackets().sendPlayerOption(RCReportEnabled() ? "Report" : "null", 6, false);
    }

    public void refreshRCReport() {
        getVarsManager().sendVar(1056, RCReportEnabled() ? 9982 : 0);
    }

    public boolean ChangeRCReport(boolean value) {
        return RCReport = value;
    }

    public boolean RCReportEnabled() {
        return RCReport;
    }

    public void switchProfanity() {
        filter = !filter;
        refreshProfanity();
    }

    public boolean isFilteredProfanity() {
        return filter;
    }

    public void setProfanity(boolean Censor) {
        this.filter = Censor;
    }

    public void refreshProfanity() {
        getPackets().sendConfig(1438, isFilteredProfanity() ? 0 : 32);
    }

    public boolean isAcceptAid() {
        return acceptAid;
    }

    public void setAcceptAid(boolean acceptAid) {
        this.acceptAid = acceptAid;
    }

    public void switchAcceptAid() {
        acceptAid = !acceptAid;
        refreshAcceptAid();
    }

    public void refreshAcceptAid() {
        getPackets().sendConfig(427, acceptAid ? 1 : 0);
    }

    public ItemsContainer<Item> getClueScrollRewards() {
        return clueScrollRewards;
    }

    public void setClueScrollRewards(ItemsContainer<Item> clueScrollRewards) {
        this.clueScrollRewards = clueScrollRewards;
    }

    public HashMap<String, Integer> getBossKillcount() {
        return bossKillcount;
    }

    public HashMap<String, Integer> getTreasureTrailCompleted() {
        return treasureTrailCount;
    }

    public HashMap<Integer, Boolean> getCustomDuelRule() {
        return CustomDuelRule;
    }

    public int getDfsCharges() {
        return dfsCharges;
    }

    public void setDfsCharges(int dfsCharges) {
        this.dfsCharges = dfsCharges;
    }

    public void setGeManager(GrandExchangeManager geManager) {
        this.geManager = geManager;
    }

    public double getDropRate() {
        return Settings.DROP_RATE;
    }

    public int getAvalonPoints() {
        return avalonPoints;
    }

    public void setAvalonPoints(int avalonPoints) {
        this.avalonPoints = avalonPoints;
    }

    public GrandExchangeManager getGeManager() {
        return geManager;
    }

    public SlayerManager getSlayerManager() {
        return slayerManager;
    }

    public String getNextClanMemberUpdate() {
        return nextClanMemberUpdate;
    }

    public void setNextClanMemberUpdate(String nextClanMemberUpdate) {
        this.nextClanMemberUpdate = nextClanMemberUpdate;
    }

    public int getDuelkillCount() {
        return duelkillCount;
    }

    public void setDuelkillCount(int duelkillCount) {
        this.duelkillCount = duelkillCount;
    }

    public int getDueldeathCount() {
        return dueldeathCount;
    }

    public void setDueldeathCount(int dueldeathCount) {
        this.dueldeathCount = dueldeathCount;
    }

    public int getDuelkillStreak() {
        return duelkillStreak;
    }

    public void setDuelkillStreak(int duelkillStreak) {
        this.duelkillStreak = duelkillStreak;
    }

    public MoneyPouch getMoneyPouch() {
        return pouch;
    }

    public int getMoneyPouchValue() {
        return pouchMoney;
    }

    public void setMoneyPouchValue(int money) {
        this.pouchMoney = money;
    }

    public void mute(String from, String reason, int days) {
        muteFrom = from;
        muteReason = reason;
        muteStart = System.currentTimeMillis();
        muteEnd = days == -1 ? -1 : muteStart + (days * 24 * 60 * 60 * 1000);
    }

    public void liftMute(boolean timeServed, Player moderator) {
        if (!isMuted())
            return;
        if (!muteFrom.equalsIgnoreCase(moderator.getUsername()) && !moderator.isDeveloper() && muteEnd == -1) {
            moderator.getPackets()
                    .sendGameMessage("Only " + muteFrom + " or an Administrator can undo this punishment.");
            return;
        }
        muteFrom = muteReason = null;
        muteStart = muteEnd = 0;
        sm("Your mute has been lifted, please read the rules to avoid future infractions.");
    }

    public boolean isMuted() {
        return muteStart < muteEnd || muteEnd == -1;
    }

    public String getMuteTime() {
        return Utils.getTime(muteStart, muteEnd);
    }

    public String getMuteFrom() {
        return muteFrom;
    }

    public String getMuteReason() {
        return muteReason;
    }

    public Entity setFrozenBy(Entity target) {
        return frozenBy = (Entity) target;
    }

    public Entity getFrozenBy() {
        return frozenBy;
    }

    public void ban(String from, String reason, int days) {
        banFrom = from;
        banReason = reason;
        banStart = System.currentTimeMillis();
        banEnd = days == -1 ? -1 : banStart + (days * 24 * 60 * 60 * 1000);
    }

    public void liftBan(boolean timeServed) {
        if (!isBanned())
            return;
        banFrom = banReason = null;
        banStart = banEnd = 0;
    }

    public boolean isBanned() {
        return banStart < banEnd || banEnd == -1;
    }

    public String getBanTime() {
        return Utils.getTime(banStart, banEnd);
    }

    public String getBanFrom() {
        return banFrom;
    }

    public String getBanReason() {
        return banReason;
    }

    private DropLogs dropLogs;

    public boolean hasCustomTitle() {
        return isTitle;
    }

    public DropLogs getDropLogs() {
        return dropLogs;
    }

    public WorldTile getTile() {
        return new WorldTile(getX(), getY(), getPlane());
    }

    public WorldTile getLocation() {
        return new WorldTile(getX(), getY(), getPlane());
    }

    public void log(String dataPath, String data) {
        try {
            String location = "";
            location = "data/system/" + dataPath + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(location, true));
            writer.write("[" + Commands.currentTime("dd MMMMM yyyy 'at' hh:mm:ss z") + "] " + data);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getEp() {
        return ep;
    }

    public void setEp(int ep) {
        this.ep = ep;
    }

    public void rememberChoice(boolean KBDEntrance) {
        this.KBDEntrance = KBDEntrance;
    }

    public void lostCannon(boolean lostCannon) {
        this.lostCannon = lostCannon;
    }

    public boolean getLostCannon() {
        return lostCannon;
    }

    public int getRecoilHits() {
        return recoilHits;
    }

    public void setRecoilHits(int recoilHits) {
        this.recoilHits = recoilHits;
    }

    public PuzzleBox getPuzzleBox() {
        return puzzleBox;
    }

    public void setPuzzleBox(int puzzleId) {
        this.puzzleBox = new PuzzleBox(this, puzzleId);
    }

    public void setSpins(int spins) {
        this.spins = spins;
    }

    public int getSpins() {
        return spins;
    }

    public int getSkullSkeptreCharges() {
        return skullSkeptreCharges;
    }

    public void setSkullSkeptreCharges(int skullSkeptreCharges) {
        this.skullSkeptreCharges = skullSkeptreCharges;
    }

    public long getTimePiece() {
        return timePiece;
    }

    public void setTimePiece(long timePiece) {
        this.timePiece = timePiece;
    }

    public boolean hasMarker() {
        return marker;
    }

    public void setMarker(boolean marker) {
        this.marker = marker;
    }

    private WorldTile beam;

    public void setBeam(WorldTile worldTile) {
        beam = worldTile;
    }

    public WorldTile getBeam() {
        return beam;
    }

    private Item beamItem;

    public void setBeamItem(Item item) {
        beamItem = item;
    }

    public Item getBeamItem() {
        return beamItem;
    }

    private WorldTile lastWalkStep;

    public void setLastWalkstep(WorldTile tile) {
        lastWalkStep = tile;
    }

    public WorldTile getLastWalkStep() {
        return lastWalkStep;
    }

    private SquealOfFortune squealOfFortune;

    public SquealOfFortune getSquealOfFortune() {
        return squealOfFortune;
    }

    private GreaterRunicStaff runicStaff;

    public GreaterRunicStaff getRunicStaff() {
        return runicStaff;

    }

    public Item getRareItem() {
        Item item = (Item) getTemporaryAttributtes().get("RARE_ITEM");
        if (item == null)
            return null;
        return item;
    }

    public WorldTile getRareItemTile() {
        WorldTile tile = (WorldTile) getTemporaryAttributtes().get("RARE_ITEM_TILE");
        if (tile == null)
            return null;
        return tile;
    }

    public boolean hasRareDrop() {
        Integer rarity = (Integer) getTemporaryAttributtes().get("RARITY_NODE");
        if (rarity == null)
            return false;
        return rarity > 1;
    }

    public void setRareDrop(Item item, WorldTile tile) {
        getTemporaryAttributtes().put("RARE_ITEM", item);
        getTemporaryAttributtes().put("RARE_ITEM_TILE", tile);
    }

    public int getValueableDrop() {
        return valueableDrop;
    }

    public void setValueableDrop(int valueableDrop) {
        this.valueableDrop = valueableDrop;
    }

    public DungManager getDungManager() {
        return dungManager;
    }

    public DungeonManager getDungeonManager() {
        return dungeonManager;
    }

    public void setDungManager(DungManager dungManager) {
        this.dungManager = dungManager;
    }

    private TreasureTrailsManager treasureTrailsManager;

    public boolean spokenWithGiles;

    public TreasureTrailsManager getTreasureTrailsManager() {
        return treasureTrailsManager;
    }

    public FarmingManager getFarmingManager() {
        return farmingManager;
    }

    public PresetManager getPresetManager() {
        return presetManager;
    }

    public void setPresetManager(PresetManager presetManager) {
        this.presetManager = presetManager;
    }

    public int isHiredByFred() {
        return hiredByFred;
    }

    public void setHiredByFred(int hiredByFred) {
        this.hiredByFred = hiredByFred;
    }

    private String lastMessage;

    public void setLastMsg(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    private boolean claimBox;

    public boolean canClaimBox() {
        return claimBox;
    }

    public PlayerRank getPlayerRank() {
        return playerRank;
    }

    public void setClaimBox(boolean claimBox) {
        this.claimBox = claimBox;
    }

    public int getFamiliarScroll() {
        int scrollId = Summoning.getScrollId(getFamiliar().getPouch().getRealPouchId());
        return scrollId;
    }

    public void setLivid(LividFarm lividFarm) {
        this.lividFarm = lividFarm;
    }

    public HashMap<Integer, Integer> getVarBitList() {
        return varBitList;
    }

    public  HashMap<Integer, Integer> getTemporaryVarBits() {
        return temporaryVarBits;
    }

    private transient CreationKiln creationKiln;

    public CreationKiln getCreationKiln() {
        return creationKiln;
    }

    public void drainLevel(int skill, int value) {
        getSkills().drainLevel(skill, value);
    }

    public void sendVar(int id, int value) {
        getVarsManager().sendVar(id, value);
    }

    public void sendVarBit(int id, int value) {
        getVarsManager().sendVarBit(id, value, false);
    }

    public void sendVarBit(int id, int value, boolean save) {
        getVarsManager().sendVarBit(id, value, save);
    }

    private int combatDelay;

    //pyra;id plunder
    public boolean pyramidReward;

    public void setCombatDelay(int ticks) {
        combatDelay = ticks;
    }

    public int getCombatDelay() {
        return combatDelay;
    }

    // artisant
    private ArtisanWorkshop artisan;
    public ArtisanWorkshop getArtisanWorkshop() {
        return artisan;
    }

    public int lavaflowCrustsMined;

}