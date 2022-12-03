package com.rs.game.item;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.utils.Utils;

public class ProcessManualGroundItems {
	
	private static HashMap<Integer, Long> items = new HashMap<>();
	
	
	
	
	public static void processGameTick() {
		if (items.get(0) < Utils.currentTimeMillis()){
			items.put(0, Utils.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10)); //will spawn a bucket every 2 mins
			//THIS IS LUMBRIDGE BUCKET
			//world.spawn(new Item(bucketId) worldtile);
			World.addGlobalGroundItem(new Item(946, 1), new WorldTile(3214, 9625, 0), 0, true);
		}
	}
	
//	private int LUMBRIDGE_BASEMENT_KNIFE = 946;
//	private int LUMBRIDGE
	
//	
//	// Knife, added by: andreas
//	946 1 - 3215 9625 0 - 60
//	// Cabbage, added by: andreas
//	1965 1 - 3285 3175 0 - 20
//	// Jug of water, added by: andreas
//	1937 1 - 3302 3170 0 - 20
//	// Leather boots, added by: andreas
//	1061 1 - 3301 3189 0 - 20
//	// Bucket, added by: andreas
//	1925 1 - 3307 3195 0 - 20
//	// Bronze mace, added by: andreas
//	1422 1 - 3320 3137 0 - 20
//	// Mind rune, added by: andreas
//	558 1 - 3206 3208 0 - 20
//	// Jug of water, added by: andreas
//	1937 1 - 3211 3212 0 - 20
//	// Empty pot, added by: andreas
//	1931 1 - 3209 3214 0 - 20
//	// Bowl, added by: andreas
//	1923 1 - 3208 3214 0 - 20
//	// Knife, added by: andreas
//	946 1 - 3205 3212 0 - 60
//	// Leather boots, added by: andreas
//	1061 1 - 3210 9615 0 - 60
//	// Leather boots, added by: andreas
//	1061 1 - 3208 9620 0 - 60
//	// Cabbage, added by: andreas
//	1965 1 - 3217 9622 0 - 60
//	// Bucket, added by: andreas
//	1925 1 - 3216 9625 0 - 60
//	// Jug, added by: andreas
//	1935 1 - 3211 9625 0 - 60
//	// Bronze arrow, added by: andreas
//	882 1 - 3205 3227 0 - 60
//	// Bronze dagger, added by: andreas
//	1205 1 - 3213 3216 1 - 60
//	// Logs, added by: andreas
//	1511 1 - 3205 3224 2 - 60
//	// Logs, added by: andreas
//	1511 1 - 3205 3226 2 - 60
//	// Logs, added by: andreas
//	1511 1 - 3208 3225 2 - 60
//	// Logs, added by: andreas
//	1511 1 - 3209 3224 2 - 60
//	// Iron dagger, added by: andreas
//	1203 1 - 3248 3245 0 - 60
//	// Egg, added by: andreas
//	1944 1 - 3229 3299 0 - 60
//	// Bucket, added by: andreas
//	1925 1 - 3225 3294 0 - 60
//	// Jug, added by: andreas
//	1935 1 - 3272 3409 0 - 60
//	// Leather body, added by: andreas
//	1129 1 - 3244 3398 0 - 60
//	// Logs, added by: andreas
//	1511 1 - 3243 3397 0 - 60
//	// Logs, added by: andreas
//	1511 1 - 3243 3395 0 - 60
//	// Empty pot, added by: andreas
//	1931 1 - 3232 3399 0 - 60
//	// Bronze pickaxe, added by: andreas
//	1265 1 - 3081 3429 0 - 60
//	// Empty pot, added by: andreas
//	1931 1 - 3074 3431 0 - 60
//	// Beer, added by: andreas
//	1917 1 - 3077 3439 0 - 60
//	// Cooked meat, added by: andreas
//	2142 1 - 3077 3441 0 - 60
//	// Cooked meat, added by: andreas
//	2142 1 - 3080 3443 0 - 60
//	// Beer, added by: andreas
//	1917 1 - 3080 3441 0 - 60
//	// Beer, added by: andreas
//	1917 1 - 3077 3443 0 - 60
//	// Leather gloves, added by: andreas
//	1059 1 - 3097 3486 0 - 60
//	// Monk's robe, added by: andreas
//	544 1 - 3059 3488 1 - 60
//	// Monk's robe, added by: andreas
//	542 1 - 3059 3487 1 - 60
//	// Bones, added by: andreas
//	526 1 - 3093 9879 0 - 60
//	// Bones, added by: andreas
//	526 1 - 3093 9884 0 - 60
//	// Bones, added by: andreas
//	526 1 - 3098 9886 0 - 60
//	// Coins, added by: andreas
//	995 4 - 3088 9898 0 - 60
//	// Coins, added by: andreas
//	995 1 - 3088 9899 0 - 60
//	// Bones, added by: andreas
//	526 1 - 3097 9901 0 - 60
//	// Bones, added by: andreas
//	526 1 - 3094 9906 0 - 60
//	// Bronze arrow, added by: andreas
//	882 3 - 3130 9903 0 - 60
//	// Bronze arrow, added by: andreas
//	882 1 - 3135 9916 0 - 60
//	// Bones, added by: andreas
//	526 1 - 3143 9878 0 - 60
//	// Bones, added by: andreas
//	526 1 - 3141 9879 0 - 60
//	// Bones, added by: andreas
//	526 1 - 3142 9880 0 - 60
//	// Bones, added by: andreas
//	526 1 - 3138 9880 0 - 60
//	// Brass necklace, added by: andreas
//	1009 1 - 3122 9881 0 - 60
//	// Brass key, added by: andreas
//	983 1 - 3132 9862 0 - 60
//	// Bones, added by: andreas
//	526 1 - 3110 9825 0 - 60
//	// Bones, added by: andreas
//	526 1 - 3109 9823 0 - 60
//	// Bones, added by: andreas
//	526 1 - 3107 9823 0 - 60
//	// Bones, added by: andreas
//	526 1 - 3102 9826 0 - 60
//	// Bronze arrow, added by: andreas
//	882 2 - 2944 3332 0 - 60
//	// Bronze pickaxe, added by: andreas
//	1265 1 - 3009 3342 0 - 60
//	// Coins, added by: andreas
//	995 1 - 3003 9801 0 - 60
//	// Coins, added by: andreas
//	995 1 - 3001 9798 0 - 60
//	// Bronze chainbody, added by: andreas
//	1103 1 - 2985 9817 0 - 60
//	// Pie dish, added by: andreas
//	2313 1 - 2993 9847 0 - 60
//	// Spade, added by: andreas
//	952 1 - 2981 3370 0 - 60
//	// Bucket, added by: andreas
//	1925 1 - 2958 3510 0 - 60
//	// Wine of zamorak, added by: andreas
//	245 1 - 2946 3473 0 - 60
//	// Skull, added by: andreas
//	964 1 - 2977 3529 0 - 60
//	// Skull, added by: andreas
//	964 1 - 2978 3531 0 - 60
//	// Body rune, added by: andreas
//	559 1 - 3193 3496 0 - 60
//	// Coins, added by: andreas
//	995 3 - 3228 3504 0 - 60
//	// Coins, added by: andreas
//	995 4 - 3232 3500 0 - 60
//	// Pie dish, added by: andreas
//	2313 1 - 3222 3494 0 - 60
//	// Bucket, added by: andreas
//	1925 1 - 3221 3497 1 - 60
//	// Bucket, added by: andreas
//	1925 1 - 3222 3491 1 - 60
//	// Coins, added by: andreas
//	995 3 - 3195 9834 0 - 60
//	// Gold bar, added by: andreas
//	2357 1 - 3192 9822 0 - 60
//	// Ruby ring, added by: andreas
//	1641 1 - 3196 9822 0 - 60
//	// Gold ore, added by: andreas
//	444 1 - 3195 9821 0 - 60
//	// Coins, added by: andreas
//	995 4 - 3195 9820 0 - 60
//	// Coins, added by: andreas
//	995 25 - 3191 9821 0 - 60
//	// Coins, added by: andreas
//	995 25 - 3190 9819 0 - 60
//	// Coins, added by: andreas
//	995 25 - 3189 9819 0 - 60
//	// Coins, added by: andreas
//	995 25 - 3188 9819 0 - 60
//	// Coins, added by: andreas
//	995 25 - 3188 9820 0 - 60
//	// Garlic, added by: andreas
//	1550 1 - 3101 3270 1 - 60
//	// Gold necklace, added by: andreas
//	1654 1 - 3192 9821 0 - 60
//	// Brass necklace, added by: andreas
//	1009 1 - 3191 9820 0 - 60
//	// Rope, added by: andreas
//	954 1 - 2904 3152 0 - 60
//	// Knife, added by: andreas
//	946 1 - 2903 3148 0 - 60
//	// Jug, added by: andreas
//	1935 1 - 2905 3146 0 - 60
//	// Banana, added by: andreas
//	1963 1 - 2907 3146 0 - 60
//	// Red spiders' eggs, added by: andreas
//	223 1 - 2832 9586 0 - 60
//	// Fire rune, added by: andreas
//	554 1 - 2836 9566 0 - 60
//	// Fire rune, added by: andreas
//	554 1 - 2833 9561 0 - 60
//	// Fire rune, added by: andreas
//	554 1 - 2838 9552 0 - 60
//	// Fire rune, added by: andreas
//	554 1 - 2842 9552 0 - 60
//	// Fire rune, added by: andreas
//	554 1 - 2843 9560 0 - 60
//	// Plank, added by: andreas
//	960 1 - 2851 3239 0 - 60
//	// Plank, added by: andreas
//	960 1 - 2847 3238 0 - 60
//	// Plank, added by: andreas
//	960 1 - 2847 3232 0 - 60
//	// Plank, added by: andreas
//	960 1 - 2857 3236 0 - 60
//	// Plank, added by: andreas
//	960 1 - 2856 3231 0 - 60
//	// Cheese, added by: andreas
//	1985 1 - 3082 3262 0 - 60
//	// Tomato, added by: andreas
//	1982 1 - 3083 3262 0 - 60
//	// Logs, added by: andreas
//	1511 1 - 3087 3266 0 - 60
//	// Logs, added by: andreas
//	1511 1 - 3087 3265 0 - 60
//	// Rubber tube, added by: andreas
//	276 1 - 3111 3367 0 - 60
//	// Poison, added by: andreas
//	273 1 - 3098 3366 0 - 60
//	// Spade, added by: andreas
//	952 1 - 3120 3361 0 - 60
//	// Bucket, added by: andreas
//	1925 1 - 3121 3362 0 - 60
//	// Bronze helm, added by: andreas
//	1139 1 - 3122 3363 0 - 60
//	// Shears, added by: andreas
//	1735 1 - 3126 3359 0 - 60
//	// Fish food, added by: andreas
//	272 1 - 3108 3356 1 - 60
//	// Tinderbox, added by: andreas
//	590 1 - 3112 3369 2 - 60
//	// White apron, added by: andreas
//	7957 1 - 3016 3229 0 - 60
//	// White apron, added by: andreas
//	1005 1 - 3009 3204 0 - 60
//	// Banana, added by: andreas
//	1963 1 - 3009 3207 0 - 60
//	// Coins, added by: andreas
//	995 1 - 2988 9584 0 - 60
//	// Coins, added by: andreas
//	995 1 - 2987 9583 0 - 60
//	// Coins, added by: andreas
//	995 1 - 2988 9582 0 - 60
//	// Brass key, added by: andreas
//	983 1 - 3132 9862 0 - 60
//
////	
	

}
