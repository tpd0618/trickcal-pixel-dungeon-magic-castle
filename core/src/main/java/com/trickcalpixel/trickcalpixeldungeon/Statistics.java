/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.trickcalpixel.trickcalpixeldungeon;

import com.watabou.utils.Bundle;

public class Statistics {

	public static int talismanUsed;
	public static int goldCollected;
	public static int goldPickedup;
	public static int highestFloor;
	public static int highestAscent;
	public static int enemiesSlain;
	public static int foodEaten;
	public static int potionsCooked;
	public static int itemsCrafted;
	public static int murasasKilled;
	public static int kisumesKilled;
	public static int trap_act_count;
	public static int bamboo;
	public static int chimata;

	public static Class exterminatedEnemy;

	public static int upgradesUsed;
	public static int sneakAttacks;
	public static int thrownAssists;

	public static int timeReset;
	public static int tenshiEarthquake;
	public static int bordercount;

	public static int HT_bonus;
	public static int life_count;
	public static int bomb_count;
	public static int dismantle_count;

	public static int power;
	public static int value;
	public static int life;
	public static int lifefragment;
	public static int spellcard;
	public static int spellcardfragment;

	public static int tenshiattackstep;
	public static int eirinelixircount;

	public static int nextvalue;

	public static int lifefragmentkill;
	public static int spellcardfragmentkill;

	public static int difficulty;

	public static int double_speed_upgrade;
	public static float duration;

	public static boolean identify_use;
	public static boolean fate_use;
	public static boolean earth_use;
	public static boolean exorcism_use;
	public static boolean transmute_use;

	public static boolean elixir_trigger = false;

	public static boolean remi_countdown = false;

	public static boolean scorelife1 = false;
	public static boolean scorelife2 = false;
	public static boolean scorelife3 = false;
	public static boolean scorelife4 = false;
	public static boolean scorelife5 = false;
	public static boolean scorelife6 = false;
	public static boolean scorelife7 = false;
	public static boolean scorelife8 = false;

	public static boolean lifelose = false;
	public static boolean spellcarduse = false;
	public static boolean abcarduse = false;
	public static boolean suwakorelic_active = false;

	//boss appear check
	public static boolean boss1 = false;
	public static boolean boss2 = false;
	public static boolean boss3 = false;
	public static boolean boss4 = false;
	public static boolean boss5 = false;
	public static boolean boss6 = false;
	public static boolean boss7 = false;
	public static boolean boss8 = false;
	public static boolean boss9 = false;

	public static boolean card1 = false;
	public static boolean card2 = false;
	public static boolean card3 = false;
	public static boolean card4 = false;
	public static boolean card5 = false;
	public static boolean card6 = false;
	public static boolean card7 = false;
	public static boolean card8 = false;
	public static boolean card9 = false;
	public static boolean card10 = false;
	public static boolean card11 = false;
	public static boolean card12 = false;
	public static boolean card13 = false;
	public static boolean card14 = false;
	public static boolean card15 = false;
	public static boolean card16 = false;
	public static boolean card17 = false;
	public static boolean card18 = false;
	public static boolean card19 = false;
	public static boolean card20 = false;
	public static boolean card21 = false;
	public static boolean card22 = false;
	public static boolean card23 = false;
	public static boolean card24 = false;
	public static boolean card25 = false;
	public static boolean card26 = false;
	public static boolean card27 = false;
	public static boolean card28 = false;
	public static boolean card29 = false;
	public static boolean card30 = false;
	public static boolean card31 = false;
	public static boolean card32 = false;
	public static boolean card33 = false;
	public static boolean card34 = false;
	public static boolean card35 = false;
	public static boolean card36 = false;
	public static boolean card37 = false;
	public static boolean card38 = false;
	public static boolean card39 = false;
	public static boolean card40 = false;
	public static boolean card41 = false;
	public static boolean card42 = false;
	public static boolean card43 = false;
	public static boolean card44 = false;
	public static boolean card45 = false;
	public static boolean card46 = false;
	public static boolean card47 = false;
	public static boolean card48 = false;
	public static boolean card49 = false;
	public static boolean card50 = false;
	public static boolean card51 = false;
	public static boolean card52 = false;
	public static boolean card53 = false;
	public static boolean card54 = false;
	public static boolean card55 = false;
	public static boolean card56 = false;
	public static boolean card57 = false;
	public static boolean card58 = false;
	public static boolean card59 = false;
	public static boolean card60 = false;
	public static boolean card61 = false;
	public static boolean card62 = false;
	public static boolean card63 = false;
	public static boolean card64 = false;
	public static boolean card65 = false;
	public static boolean card66 = false;
	public static boolean card67 = false;
	public static boolean card68 = false;
	public static boolean card69 = false;
	public static boolean card70 = false;

	public static void reset() {

		talismanUsed	= 0;
		goldCollected	= 0;
		goldPickedup	= 0;
		highestFloor = 0;
		highestAscent	= 0;
		enemiesSlain	= 0;
		foodEaten		= 0;
		potionsCooked	= 0;
		itemsCrafted    = 0;
		murasasKilled = 0;
		kisumesKilled   = 0;
		trap_act_count = 0;
		bamboo = 0;
		chimata = 0;
		exterminatedEnemy = null;

		upgradesUsed    = 0;
		sneakAttacks    = 0;
		thrownAssists   = 0;

		timeReset = 0;
		tenshiEarthquake = 0;
		bordercount = 0;

		HT_bonus = 0;
		life_count = 0;
		bomb_count = 0;
		dismantle_count = 0;

		power = 100;
		value = 0;
		life = 2;
		lifefragment = 0;
		spellcard = 3;
		spellcardfragment = 0;

		tenshiattackstep = 0;
		eirinelixircount = 0;

		nextvalue = 500;

		lifefragmentkill = 0;
		spellcardfragmentkill = 0;

		difficulty = 1; //easy

		double_speed_upgrade = 0;
		duration = 0;

		identify_use = false;
		fate_use = false;
		earth_use = false;
		exorcism_use = false;
		transmute_use = false;

		elixir_trigger = false;

		remi_countdown = false;

		scorelife1 = false;
		scorelife2 = false;
		scorelife3 = false;
		scorelife4 = false;
		scorelife5 = false;
		scorelife6 = false;
		scorelife7 = false;
		scorelife8 = false;

		lifelose = false;
		spellcarduse = false;
		abcarduse = false;
		suwakorelic_active = false;
	}

	private static final String TALISMANUSED		= "talismanused";
	private static final String GOLD		= "score";
	private static final String GOLDPICKEDUP		= "goldpickedup";
	private static final String DEEPEST		= "maxDepth";
	private static final String HIGHEST		= "maxAscent";
	private static final String SLAIN		= "enemiesSlain";
	private static final String FOOD		= "foodEaten";
	private static final String ALCHEMY		= "potionsCooked";
	private static final String KISUMES	    = "kisumeskilled";
	private static final String MURASAS = "priranhas";
	private static final String TRAP_ACT_COUNT		= "trap_act_count";
	private static final String BAMBOO		= "bamboo";
	private static final String EXTERMINATED_ENEMY		= "exterminated_enemy";
	private static final String CHIMATA		= "chimata";

	private static final String UPGRADES	= "upgradesUsed";
	private static final String SNEAKS		= "sneakAttacks";
	private static final String THROWN		= "thrownAssists";

	private static final String TIMERESET		= "timeReset";
	private static final String TENSHIEARTHQUAKE		= "tenshiearthquake";
	private static final String BORDERCOUNT		= "bordercount";

	private static final String MAXHP_DOWN		= "maxhp_down";
	private static final String LIFE_COUNT = "life_count";
	private static final String BOMB_COUNT = "bomb_count";
	private static final String DISMANTLE_COUNT = "dismantle_count";

	private static final String POWER	= "power";
	private static final String VALUE	= "value";
	private static final String LIFE	= "life";
	private static final String LIFEFRAGMENT	= "lifefragment";
	private static final String SPELLCARD	= "spellcard";
	private static final String SPELLCARDFRAGMENT	= "spellcardfragment";

	private static final String TENSHIATTACKSTEP	= "tenshiattackstep";
	private static final String EIRINELIXIRCOUNT	= "eirinelixircount";

	private static final String NEXTVALUE	= "nextvalue";

	private static final String LIFEFRAGMENTKILL	= "lifefragmentkill";
	private static final String SPELLCARDFRAGMENTKILL	= "spellcardfragmentkill";

	private static final String DIFFICULTY	= "difficulty";

	private static final String DOUBLE_SPEED_UPGRADE	= "double_speed_upgrade";
	private static final String DURATION	= "duration";

	private static final String IDENTIFY_USE	= "identify_use";
	private static final String FATE_USE	= "fate_use";
	private static final String EARTH_USE	= "earth_use";
	private static final String EXORCISM_USE	= "exorcism_use";
	private static final String TRANSMUTE_USE	= "transmute_use";

	private static final String ELIXIRTRIGGER	= "elixirtrigger";

	private static final String REMI_COUNTDOWN	= "remi_countdown";

	private static final String SCORELIFE1		= "scorelife1";
	private static final String SCORELIFE2		= "scorelife2";
	private static final String SCORELIFE3		= "scorelife3";
	private static final String SCORELIFE4		= "scorelife4";
	private static final String SCORELIFE5		= "scorelife5";
	private static final String SCORELIFE6		= "scorelife6";
	private static final String SCORELIFE7		= "scorelife7";
	private static final String SCORELIFE8		= "scorelife8";

	private static final String LIFELOSE		= "lifelose";
	private static final String SPELLCARDUSE		= "spellcarduse";
	private static final String ABCARDUSE		= "abcarduse";
	private static final String SUWAKORELIC_ACTIVE = "suwakorelic_active";

	public static void storeInBundle( Bundle bundle ) {
		bundle.put( TALISMANUSED,		talismanUsed );
		bundle.put( GOLD,		goldCollected );
		bundle.put( GOLDPICKEDUP,		goldPickedup );
		bundle.put( DEEPEST, highestFloor);
		bundle.put( HIGHEST,	highestAscent );
		bundle.put( SLAIN,		enemiesSlain );
		bundle.put( FOOD,		foodEaten );
		bundle.put( ALCHEMY,    itemsCrafted );
		bundle.put( MURASAS, murasasKilled);
		bundle.put( KISUMES,	kisumesKilled );
		bundle.put( TRAP_ACT_COUNT, trap_act_count);
		bundle.put( BAMBOO, bamboo);
		bundle.put( CHIMATA, chimata);
		bundle.put( EXTERMINATED_ENEMY, exterminatedEnemy );

		bundle.put( UPGRADES,   upgradesUsed );
		bundle.put( SNEAKS,		sneakAttacks );
		bundle.put( THROWN,		thrownAssists );

		bundle.put( TIMERESET,		timeReset );
		bundle.put( TENSHIEARTHQUAKE,		tenshiEarthquake );
		bundle.put( BORDERCOUNT,		bordercount );

		bundle.put( MAXHP_DOWN, HT_bonus);
		bundle.put( LIFE_COUNT, life_count);
		bundle.put( BOMB_COUNT, bomb_count);
		bundle.put( DISMANTLE_COUNT, dismantle_count);

		bundle.put( POWER,	power );
		bundle.put( VALUE,	value );
		bundle.put( LIFE,	life );
		bundle.put( LIFEFRAGMENT,	lifefragment );
		bundle.put( SPELLCARD,	spellcard );
		bundle.put( SPELLCARDFRAGMENT,	spellcardfragment );

		bundle.put( TENSHIATTACKSTEP, tenshiattackstep);
		bundle.put( EIRINELIXIRCOUNT, eirinelixircount);

		bundle.put( NEXTVALUE,	nextvalue );

		bundle.put( LIFEFRAGMENTKILL,	lifefragmentkill );
		bundle.put( SPELLCARDFRAGMENTKILL,	spellcardfragmentkill );

		bundle.put( DIFFICULTY,	difficulty );

		bundle.put( IDENTIFY_USE,	identify_use );
		bundle.put( FATE_USE,	fate_use );
		bundle.put( EARTH_USE,	earth_use );
		bundle.put( EXORCISM_USE,	exorcism_use );
		bundle.put( TRANSMUTE_USE,	transmute_use );

		bundle.put( DOUBLE_SPEED_UPGRADE,	double_speed_upgrade );
		bundle.put( DURATION,	duration );

		bundle.put( ELIXIRTRIGGER, elixir_trigger);

		bundle.put( REMI_COUNTDOWN, remi_countdown);

		bundle.put( SCORELIFE1,	scorelife1 );
		bundle.put( SCORELIFE2,	scorelife2 );
		bundle.put( SCORELIFE3,	scorelife3 );
		bundle.put( SCORELIFE4,	scorelife4 );
		bundle.put( SCORELIFE5,	scorelife5 );
		bundle.put( SCORELIFE6,	scorelife6 );
		bundle.put( SCORELIFE7,	scorelife7 );
		bundle.put( SCORELIFE8,	scorelife8 );

		bundle.put( LIFELOSE,	lifelose );
		bundle.put( SPELLCARDUSE,	spellcarduse );
		bundle.put( SUWAKORELIC_ACTIVE, suwakorelic_active);
		bundle.put( ABCARDUSE,	abcarduse );
	}

	public static void restoreFromBundle( Bundle bundle ) {
		talismanUsed = bundle.getInt(TALISMANUSED);
		goldCollected = bundle.getInt(GOLD);
		goldPickedup = bundle.getInt(GOLDPICKEDUP);
		highestFloor = bundle.getInt(DEEPEST);
		highestAscent = bundle.getInt(HIGHEST);
		enemiesSlain = bundle.getInt(SLAIN);
		foodEaten = bundle.getInt(FOOD);
		itemsCrafted = bundle.getInt(ALCHEMY);
		kisumesKilled = bundle.getInt(KISUMES);
		murasasKilled = bundle.getInt(MURASAS);
		trap_act_count = bundle.getInt(TRAP_ACT_COUNT);
		bamboo = bundle.getInt(BAMBOO);
		chimata = bundle.getInt(CHIMATA);
		exterminatedEnemy = bundle.getClass(EXTERMINATED_ENEMY);

		upgradesUsed = bundle.getInt(UPGRADES);
		sneakAttacks = bundle.getInt(SNEAKS);
		thrownAssists = bundle.getInt(THROWN);

		timeReset = bundle.getInt(TIMERESET);
		tenshiEarthquake = bundle.getInt(TENSHIEARTHQUAKE);
		bordercount = bundle.getInt(BORDERCOUNT);

		HT_bonus = bundle.getInt(MAXHP_DOWN);
		life_count = bundle.getInt(LIFE_COUNT);
		bomb_count = bundle.getInt(BOMB_COUNT);
		dismantle_count = bundle.getInt(DISMANTLE_COUNT);

		power = bundle.getInt(POWER);
		value = bundle.getInt(VALUE);
		life = bundle.getInt(LIFE);
		lifefragment = bundle.getInt(LIFEFRAGMENT);
		spellcard = bundle.getInt(SPELLCARD);
		spellcardfragment = bundle.getInt(SPELLCARDFRAGMENT);

		tenshiattackstep = bundle.getInt(TENSHIATTACKSTEP);
		eirinelixircount = bundle.getInt(EIRINELIXIRCOUNT);

		nextvalue = bundle.getInt(NEXTVALUE);

		lifefragmentkill = bundle.getInt(LIFEFRAGMENTKILL);
		spellcardfragmentkill = bundle.getInt(SPELLCARDFRAGMENTKILL);

		difficulty = bundle.getInt(DIFFICULTY);

		double_speed_upgrade = bundle.getInt(DOUBLE_SPEED_UPGRADE);
		duration = bundle.getFloat(DURATION);

		identify_use = bundle.getBoolean(IDENTIFY_USE);
		fate_use = bundle.getBoolean(FATE_USE);
		earth_use = bundle.getBoolean(EARTH_USE);
		exorcism_use = bundle.getBoolean(EXORCISM_USE);
		transmute_use = bundle.getBoolean(TRANSMUTE_USE);

		elixir_trigger = bundle.getBoolean(ELIXIRTRIGGER);

		remi_countdown = bundle.getBoolean(REMI_COUNTDOWN);

		scorelife1 = bundle.getBoolean(SCORELIFE1);
		scorelife2 = bundle.getBoolean(SCORELIFE2);
		scorelife3 = bundle.getBoolean(SCORELIFE3);
		scorelife4 = bundle.getBoolean(SCORELIFE4);
		scorelife5 = bundle.getBoolean(SCORELIFE5);
		scorelife6 = bundle.getBoolean(SCORELIFE6);
		scorelife7 = bundle.getBoolean(SCORELIFE7);
		scorelife8 = bundle.getBoolean(SCORELIFE8);

		lifelose = bundle.getBoolean(LIFELOSE);
		spellcarduse = bundle.getBoolean(SPELLCARDUSE);
		abcarduse = bundle.getBoolean(ABCARDUSE);
		suwakorelic_active = bundle.getBoolean(SUWAKORELIC_ACTIVE);
	}

	public static void preview(GamesInProgress.Info info, Bundle bundle ){
		info.goldCollected  = bundle.getInt( GOLD );
		info.maxDepth = bundle.getInt( DEEPEST );
	}
}