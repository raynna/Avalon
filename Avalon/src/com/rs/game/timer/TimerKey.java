package com.rs.game.timer;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Bart on 8/13/2015.
 */
public enum TimerKey {
	
	/**
	 * Key used when eating food.
	 */
	FOOD,
	
	/**
	 * Timer used when drinking potions.
	 */
	POTION,
	
	/**
	 * Ticks before we can attack again. Cooldown period.
	 */
	COMBAT_ATTACK,
	
	/**
	 * Key used when burying bones in the ground.
	 */
	BONE_BURYING,
	
	/**
	 * Key used to recharge special energy, 10% every 30 seconds (aka 50 ticks).
	 */
	SPECIAL_ENERGY_RECHARGE,
	
	/**
	 * Key used to indicate an entity is currently frozen and blocked from moving.
	 */
	FROZEN,
	
	/**
	 * Key used to tell if they've had enough time in between freezes. Aka immunity.
	 */
	REFREEZE,
	
	/**
	 * Key used to manage the teleblock timer
	 */
	
	TELEBLOCK(1, false),
	
	/**
	 * Key used to manage the teleblock immunity timer
	 */
	
	TELEBLOCK_IMMUNITY(28, false),
	
	/**
	 * Key used when replenishing decreased or increased stats. Fired once per minute.
	 */
	STAT_REPLENISH,
	
	/**
	 * Timer started 30 seconds after stat replenish which heals the player if he has rapid heal enabled.
	 */
	RAPID_HEAL_TIMER,
	
	/**
	 * Key used to indicate that the entity is currently stunned.
	 */
	STUNNED,
	
	/**
	 * The amount of immunity time the player has
	 */
	STUN_IMMUNITY,
	
	/*
	 * Key used to indivate that the vengeance spell is on cooldown
	 */
	VENGEANCE_COOLDOWN,
	
	/**
	 * Key used to deduct micro-prayer points and process overhead logic
	 */
	PRAYER_TICK,
	
	/**
	 * Key used to restock the shops every minute
	 */
	SHOP_RESTOCK,
	
	/**
	 * Key used to restock items with 100+ quantity in shops (every 5 seconds)
	 */
	SHOP_RESTOCK_FAST,
	
	/**
	 * Key used to have the 1.8 seconds of fire-chaining in firemaking
	 */
	FIRE_CATCHING,
	
	/**
	 * Timer used to determine if we need to auto-retaliate. >3 = in combat
	 * Be sure to call this every cycle for NPCs in their combat script REGARDLESS of if an attack took place (might not if in range).
	 * Used in the npc logic core for aggression, retaliation, random walking etc.
	 */
	IN_COMBAT,
	
	/**
	 * Timer used for eating karambwan. Uses a separate timer. PvPers use this to comboheal.
	 */
	KARAMBWAN,
	
	/**
	 * Timer used to indicate when our last non-generic damage was dealt. Used to see if we can log out. 10 s (17 ticks)
	 */
	COMBAT_LOGOUT,
	
	/**
	 * Timer key used to indicate after how many ticks we need to force-log because we're disconnected.
	 */
	CONNECTION_FORCE_LOGOUT,
	
	/**
	 * Timer started on 30 ticks, every time it strikes poison is checked. Varp 102 is related to that.
	 */
	POISON,
	
	/**
	 * Key for how long the trade invitation stays open for the other player. Default set to 30s (50 ticks).
	 */
	TRADE_INVITATION_OPEN,
	
	/**
	 * Key used to indicate a duel offer is currently pending.
	 */
	DUEL_INVITATION_OPEN,
	
	/**
	 * Key used to indicate a clan war challenge is currently open.
	 */
	CLANWAR_INVITATION_OPEN,
	
	/**
	 * Timer used for alching of items
	 */
	ALCH_TIMER,

	/**
	 * A cooldown for all noncombat spells.
	 */
	NON_COMBAT_SPELL_TIMER,
	
	/**
	 * An immunity timer that is applied when a player enters 'edgepvp'.
	 */
	EDGEPVP_IMMUNITY,
	
	/**
	 * Timer set by the dragon scimitar special attack, blocking the overhead prayers.
	 */
	OVERHEADS_BLOCKED,
	
	/**
	 * Timer used for stamina potions
	 */
	STAMINA_POTION,
	
	/**
	 * Key used to time when to globally announce good stuff.
	 */
	GLOBAL_ANNOUNCEMENT,
	
	/**
	 * Timer used for refilling the players special attack from Surgeon General Tafani
	 */
	RECHARGE_SPECIAL_ATTACK(17, false),
	
	/**
	 * Timer ticking down before spear effect wears off. On zero, if you're stacked with pending spears, you'll be moved gain.
	 */
	SPEAR,
	
	/**
	 * Timer to handle player yells
	 */
	YELL,
	
	/**
	 * Time before a player can accept the duel settings. Stops scamming.
	 */
	DUEL_ACCEPT_DELAY,
	
	/**
	 * Player counting 3,2,1,go for a stake.
	 */
	STAKE_COUNTDOWN,
	
	/**
	 * Timer key used for Staff of the Dead damage reduction.
	 */
	SOTD_DAMAGE_REDUCTION,
	
	/**
	 * Timer used in the duel arena to limit the amount of thrown tomatoes.
	 */
	TOMATO_TIMER,
	
	/**
	 * You can't lose items if this timer is active - super security post duel.
	 */
	POST_ARENA_DEATH_IMMUNITY,
	
	/**
	 * Time before you can re-use the god wars altars. @ 10 minutes.
	 */
	GODWARS_ALTAR_LOCK(2, false),
	
	/**
	 * Timer used to determine how long is left in the Charge spell.
	 */
	CHARGE_SPELL,
	
	/**
	 * Timer used for antifire immunity potions.
	 */
	ANTIFIRE_POTION,
	
	/*
	 * All keys below until the mark are for farming.
	 * Naming: FM_region_patch
	 */
	// (key 1 & 2 already in use)
	FM_CAMELOT_ALLOT_NORTH(3),
	FM_CAMELOT_ALLOT_SOUTH(4),
	FM_ARDY_ALLOT_NORTH(5),
	FM_ARDY_ALLOT_SOUTH(6),
	FM_FALLY_ALLOT_NORTH(7),
	FM_FALLY_ALLOT_SOUTH(8),
	FM_CANIFIS_ALLOT_NORTH(9),
	FM_CANIFIS_ALLOT_SOUTH(10),
	FM_ZEAH_ALLOT_NORTH(11),
	FM_ZEAH_ALLOT_SOUTH(12),
	FM_CAMELOT_HERBS(13),
	FM_ARDY_HERBS(14),
	FM_FALLY_HERBS(15),
	FM_ZEAH_HERBS(16),
	// (key 17 already in use)
	FM_CANIFIS_HERBS(18),
	COMPOST_BIN(19),
	FM_FALLY_FLOWER(27),
	FM_CAMELOT_FLOWER(29),
	FM_ARDY_FLOWER(30),
	FM_CANIFIS_FLOWER(31),
	FM_ZEAH_FLOWER(32),
	
	// Duration to enable packet logging for. Real-time debugging.
	PACKETLOGGING,
	
	// You can only chomp of these cakes once per tick.
	EAT_ROCKCAKE,
	
	// Gives staff freedom to investigate without players harassing.
	COMBAT_IMMUNITY,
	
	/**
	 * Timer used to indicate when Skotizo is going to relocate himself.
	 */
	SKOTIZO_RELOCATE,
	
	/**
	 * Timer used to signify when Skotizo is going to despawn.
	 */
	SKOTIZO_DESPAWN,
	
	// Timer used to deal venom damage to an entity. Every 20 seconds aka 34 ticks.
	VENOM,
	
	//Elder Chaos Druid teleportToMe cooldown
	ELDER_CHAOS_DRUID_TELEPORT,
	
	// the time between each tan hide lunar spell cast
	LUNAR_SPELL_TAN_HIDE,
	
	/**
	 * Timer key used to perform a 5-second (9 tick) 'lockout' to avoid accepting when the other changes things.
	 */
	CLANWARS_MODIFIED,
	
	/**
	 * Timer used to signify when a reanimated monster will despawn.
	 */
	REANIMATED_MONSTER_DESPAWN,
	
	/**
	 * Wintertodt key used to process every 2 ticks for the minigame instance. Singleton event.
	 */
	WINTERTODT_CYCLE,
	
	/**
	 * Timerkey used to hit random players every cycle in the Wintertodt area.
	 */
	WINTERTODT_COLD,
	
	/**
	 * Timerkey used to start the game. Gets fired as a 1:20m (133 ticks) timer on world init and after every game.
	 */
	WINTERTODT_GAME_START,
	
	// Time before the hint arrow expires showing where we last died.
	HINT_ARROW_TO_DEATH_TILE,
	
	// How many ticks left that we've forfeit the risk protection mechanic. Ticks offline.
	RISK_PROTECTION(20, true),
	
	/**
	 * Timerkey used to store how long ago the player left the TzHaar Fight Cave
	 */
	TZHAAR_FIGHT_CAVE(26, true),
	
	/**
	 * Timer key used to clear the temp banned user array for Clan Chat
	 */
	CLAN_CHAT_TEMP_BANNED,
	
	/**
	 * The key used to determine our penalty from the bounty hunter minigame.
	 */
	BOUNTY_HUNTER_PENALTY(21, false),
	
	/**
	 * The key to determine if there is a delay that should prevent us from finding a target and being a viable target.
	 */
	BOUNTY_HUNTER_TARGET_DELAY(22, true),
	
	/**
	 * This timer is applied to whenever an initial target skip is done. Upon finishing, if any target skip values are present, it'll reset them to zero.
	 * Timer starts at 30 min value.
	 */
	BOUNTY_HUNTER_SKIP_RESET(33, true),
	
	/**
	 * Used to update the BH interface on a set interval.
	 */
	BOUNTY_HUNTER_INTERFACE_UPDATE,
	
	/**
	 * A small alternative instead of giving players a 15 min penalty for constant target skipping. We just apply a 1-min cd.
	 */
	BOUNTY_HUNTER_SKIP_COOLDOWN,
	
	/**
	 * The timer key used for tracking the amount of time a player has to return to the wilderness while having an active target.
	 */
	BOUNTY_HUNTER_SAFETY_TIMER,
	
	/**
	 * The timer the player has whilst being outside of the wilderness to return back & having a target.
	 */
	BOUNTY_HUNTER_OUTSIDE,
	
	/**
	 * Timerkey used to determine if our Abyssal Sire Spawn transforms into a Scion.
	 */
	ABYSSAL_SIRE_SPAWN_TRANSFORM,
	
	/**
	 * Timer key used to cycle through all active clan wars instances to process business logic. 5 tick granularity.
	 */
	CLAN_WARS_CYCLE,
	
	/**
	 * Timer key used to store the amt of time left before the Abyssal Sire recovers from being disoriented.
	 */
	ABYSSAL_SIRE_DISORIENTATION,
	
	/**
	 * Timer key used to store the amount of time before the Abyssal Sire releases his current challenger.
	 */
	ABYSSAL_SIRE_CHALLENGER_COUNTDOWN,
	
	/**
	 * Timer key used to store how much time is left before the Abyssal Sire returns to his chamber.
	 */
	ABYSSAL_SIRE_CHAMBER_COUNTDOWN,
	
	/**
	 * Timer key to store the amt of time before a player is able to guess the Halloween riddle again.
	 */
	HALLOWEEN_2016_GUESS_TIMER,
	
	/**
	 * Timer before a Deadman Skull disappears.
	 */
	DEADMAN_SKULL_TIMER(23, false),
	
	/**
	 * When you open a bank you can't eat or drink anything for 3 seconds after the interface is closed.
	 */
	DEADMAN_BANK_COOLDOWN,
	
	/**
	 * Cool down on the Lunar Plank Make spell
	 */
	PLANK_MAKE,
	
	/**
	 * Timer key used to store how long until the Abyssal Sire respawn is visible
	 */
	ABYSSAL_SIRE_RESPAWN,
	
	/**
	 * Timer key used to store the remaining time the challenger has to return in combat range before the Abyssal Sire resets
	 */
	ABYSSAL_SIRE_RESET_TIMER,
	
	/**
	 * 7 minute cooldown timer on the Imbued heart until you can re-use it.
	 */
	IMBUED_HEART_COOLDOWN(24, false),
	
	/**
	 * 2 minute NPC timer that will despawn a superior boss monster if not under attack at that point.
	 */
	SUPERIOR_BOSS_DESPAWN,
	
	/**
	 * World timer used to iterate through blessings. Set to 1 hour each time.
	 */
	NEXT_BLESSING_CYCLE,
	
	/**
	 * Timer key fired every 5 ticks to recompute those with blood keys in wild.
	 */
	BLOODCHEST_RECACHE,
	
	/**
	 * Timer which is set to 30 seconds indicating one is Hunted.
	 */
	BLOODCHEST_HUNTED,
	
	/**
	 * Every 24 hours we clear the "total voted sites" player attribute.
	 */
	VOTE_COOLDOWN(25, true),
	
	/**
	 * Since we don't use an attack timer for their special
	 */
	THROWING_AXE_DELAY,
	
	/**
	 * Timer used by the world to empty and announce how many votes were claimed
	 */
	VOTING_ANNOUNCEMENT,
	
	/**
	 * Timer used to delay dialogues by 1 tick
	 */
	DIALOGUE_CONTINUE,
	
	/**
	 * Timer used by lottery system for announcements
	 */
	LOTTERY_TICK,
	
	/**
	 * The key used when a player has commenced a successful dragonfire special.
	 */
	DRAGONFIRE_SPECIAL,
	
	/**
	 * Every 5 ticks we check for combat and if so, degrade barrows/serp helm. See mod Ash's tweet on the serp helm wiki page.
	 */
	COMBAT_DEGRADE_CHECK,
	/*
	 * The magic attack of Zilyana stops her walking for ~5 ticks.
	 */
	ZILY_SPEC_COOLDOWN,
	
	/**
	 * Like a teleblock. W2 protection mechanic. Stops instant teleporting after using your special attack, you prick.
	 */
	BLOCK_SPEC_AND_TELE,
	
	/**
	 * Vetion reborn is active for 5 mins until he transmogs back to regular vetion (if not killed).
	 */
	VETION_REBORN_TIMER,
	
	/**
	 * Timer used to indicate when the next Wilderness boss event happens.
	 */
	WILDERNESS_BOSS_TIMER,
	
	/**
	 * Timer used to indicate when the wilderness boss event (forcefully) ends.
	 */
	WILDERNESS_BOSS_TIMEOUT,
	
	/**
	 * Every 1 tick move wall in inferno
	 */
	GLYPH_MOVE,

	/**
	 * Timer used to handle the loyalty chest spree/cooldown
	 */
	LOYALTY_CHEST_TIMER(34, true),
	LOYALTY_CHEST_SPREE_TIMER(35, true),

	/**
	 * Tournament timers
	 */
	TOURNAMENT_TIMER,
	TOURNAMENT_INSCRIPTION_TIMER,
	TOURNAMENT_FIGHT_TIMER,

	/**
	 * Active volcano
	 */
	ACTIVE_VOLCANO_SPAWN,
	ACTIVE_VOLCANO_DESPAWN,

	;
	
	public static final TimerKey[] cachedValues = values();
	
	private boolean persistent;
	private int persistencyKey;
	private boolean ticksOffline;
	
	private TimerKey() {
		// Empty default constructor (non-persistent)
	}
	
	private TimerKey(int persistencyKey) {
		persistent = true;
		this.persistencyKey = persistencyKey;
		ticksOffline = true;
	}
	
	private TimerKey(int persistencyKey, boolean ticksOffline) {
		persistent = true;
		this.persistencyKey = persistencyKey;
		this.ticksOffline = ticksOffline;
	}
	
	public boolean persistent() {
		return persistent;
	}
	
	public int persistencyKey() {
		return persistencyKey;
	}
	
	public boolean ticksOffline() {
		return ticksOffline;
	}
	
	public static Optional<TimerKey> forPersistencyKey(int key) {
		for (TimerKey tk : cachedValues) {
			if (tk.persistencyKey == key)
				return Optional.of(tk);
		}
		
		return Optional.empty();
	}
	
	public static void verifyIntegrity() {
		// Critical check for overlapping timer keys - that'd be disastrous.
		Set<Integer> uniques = new HashSet<>();
		for (TimerKey key : values()) {
			if (key.persistent()) {
				if (uniques.contains(key.persistencyKey)) {
					System.err.println("=================================== WARNING ===================================");
					System.err.println("WARNING: DUPLICATE TIMER KEY " + key.persistencyKey + " ON " + key.name() + "!");
					System.err.println("REJECTING SERVER BOOT FOR OBVIOUS REASONS.");
					System.err.println("=================================== WARNING ===================================");
					System.exit(1);
				} else {
					uniques.add(key.persistencyKey);
				}
			}
		}
	}
	
}
