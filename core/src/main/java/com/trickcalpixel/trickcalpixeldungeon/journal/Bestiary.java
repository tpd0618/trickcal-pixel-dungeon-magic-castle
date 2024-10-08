/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2024 Evan Debenham
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

package com.trickcalpixel.trickcalpixeldungeon.journal;

import com.trickcalpixel.trickcalpixeldungeon.Badges;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Bee;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.EbonyMimic;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.GoldenMimic;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Mimic;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Piranha;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.npcs.MirrorImage;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.npcs.PrismaticImage;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.npcs.Sheep;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.trickcalmob.Alice;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.AlarmTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.BlazingTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.BurningTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.ChillingTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.ConfusionTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.CorrosionTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.CursingTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.DisarmingTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.DisintegrationTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.FlashingTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.FlockTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.FrostTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.GatewayTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.GeyserTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.GrimTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.GrippingTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.OozeTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.PitfallTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.PoisonDartTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.RockfallTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.ShockingTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.StormTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.SummoningTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.TeleportationTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.ToxicTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.WarpingTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.WeakeningTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.WornDartTrap;
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.trickcalpixel.trickcalpixeldungeon.plants.BlandfruitBush;
import com.trickcalpixel.trickcalpixeldungeon.plants.Blindweed;
import com.trickcalpixel.trickcalpixeldungeon.plants.Earthroot;
import com.trickcalpixel.trickcalpixeldungeon.plants.Fadeleaf;
import com.trickcalpixel.trickcalpixeldungeon.plants.Firebloom;
import com.trickcalpixel.trickcalpixeldungeon.plants.Icecap;
import com.trickcalpixel.trickcalpixeldungeon.plants.Mageroyal;
import com.trickcalpixel.trickcalpixeldungeon.plants.Rotberry;
import com.trickcalpixel.trickcalpixeldungeon.plants.Sorrowmoss;
import com.trickcalpixel.trickcalpixeldungeon.plants.Starflower;
import com.trickcalpixel.trickcalpixeldungeon.plants.Stormvine;
import com.trickcalpixel.trickcalpixeldungeon.plants.Sungrass;
import com.trickcalpixel.trickcalpixeldungeon.plants.Swiftthistle;
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

//contains all the game's various entities, mostly enemies, NPCS, and allies, but also traps and plants
public enum Bestiary {

	REGIONAL,
	BOSSES,
	UNIVERSAL,
	RARE,
	QUEST,
	NEUTRAL,
	ALLY,
	TRAP,
	PLANT;

	//tracks whether an entity has been encountered
	private final LinkedHashMap<Class<?>, Boolean> seen = new LinkedHashMap<>();
	//tracks enemy kills, trap activations, plant tramples, or just sets to 1 for seen on allies
	private final LinkedHashMap<Class<?>, Integer> encounterCount = new LinkedHashMap<>();

	//should only be used when initializing
	private void addEntities(Class<?>... classes ){
		for (Class<?> cls : classes){
			seen.put(cls, false);
			encounterCount.put(cls, 0);
		}
	}

	public Collection<Class<?>> entities(){
		return seen.keySet();
	}

	public String title(){
		return Messages.get(this, name() + ".title");
	}

	public int totalEntities(){
		return seen.size();
	}

	public int totalSeen(){
		int seenTotal = 0;
		for (boolean entitySeen : seen.values()){
			if (entitySeen) seenTotal++;
		}
		return seenTotal;
	}

	static {

		REGIONAL.addEntities(Alice.class);

		UNIVERSAL.addEntities(Piranha.class, Mimic.class, GoldenMimic.class, EbonyMimic.class);

		NEUTRAL.addEntities(Sheep.class, Bee.class);

		ALLY.addEntities(MirrorImage.class, PrismaticImage.class);

		TRAP.addEntities(WornDartTrap.class, PoisonDartTrap.class, DisintegrationTrap.class, GatewayTrap.class,
				ChillingTrap.class, BurningTrap.class, ShockingTrap.class, AlarmTrap.class, GrippingTrap.class, TeleportationTrap.class, OozeTrap.class,
				FrostTrap.class, BlazingTrap.class, StormTrap.class, FlashingTrap.class, WarpingTrap.class,
				ConfusionTrap.class, ToxicTrap.class, CorrosionTrap.class,
				FlockTrap.class, SummoningTrap.class, WeakeningTrap.class, CursingTrap.class,
				GeyserTrap.class, RockfallTrap.class, PitfallTrap.class, DisarmingTrap.class, GrimTrap.class);

		PLANT.addEntities(Rotberry.class, Sungrass.class, Fadeleaf.class, Icecap.class,
				Firebloom.class, Sorrowmoss.class, Swiftthistle.class, Blindweed.class,
				Stormvine.class, Earthroot.class, Mageroyal.class, Starflower.class,
				BlandfruitBush.class);

	}

	//some mobs and traps have different internal classes in some cases, so need to convert here
	private static final HashMap<Class<?>, Class<?>> classConversions = new HashMap<>();
	static {
	}

	public static boolean isSeen(Class<?> cls){
		for (Bestiary cat : values()) {
			if (cat.seen.containsKey(cls)) {
				return cat.seen.get(cls);
			}
		}
		return false;
	}

	public static void setSeen(Class<?> cls){
		if (classConversions.containsKey(cls)){
			cls = classConversions.get(cls);
		}
		for (Bestiary cat : values()) {
			if (cat.seen.containsKey(cls) && !cat.seen.get(cls)) {
				cat.seen.put(cls, true);
				Journal.saveNeeded = true;
			}
		}
		Badges.validateCatalogBadges();
	}

	public static int encounterCount(Class<?> cls) {
		for (Bestiary cat : values()) {
			if (cat.encounterCount.containsKey(cls)) {
				return cat.encounterCount.get(cls);
			}
		}
		return 0;
	}

	//used primarily when bosses are killed and need to clean up their minions
	public static boolean skipCountingEncounters = false;

	public static void countEncounter(Class<?> cls){
		countEncounters(cls, 1);
	}

	public static void countEncounters(Class<?> cls, int encounters){
		if (skipCountingEncounters){
			return;
		}
		if (classConversions.containsKey(cls)){
			cls = classConversions.get(cls);
		}
		for (Bestiary cat : values()) {
			if (cat.encounterCount.containsKey(cls) && cat.encounterCount.get(cls) != Integer.MAX_VALUE){
				cat.encounterCount.put(cls, cat.encounterCount.get(cls)+encounters);
				if (cat.encounterCount.get(cls) < -1_000_000_000){ //to catch cases of overflow
					cat.encounterCount.put(cls, Integer.MAX_VALUE);
				}
				Journal.saveNeeded = true;
			}
		}
	}

	private static final String BESTIARY_CLASSES    = "bestiary_classes";
	private static final String BESTIARY_SEEN       = "bestiary_seen";
	private static final String BESTIARY_ENCOUNTERS = "bestiary_encounters";

	public static void store( Bundle bundle ){

		ArrayList<Class<?>> classes = new ArrayList<>();
		ArrayList<Boolean> seen = new ArrayList<>();
		ArrayList<Integer> encounters = new ArrayList<>();

		for (Bestiary cat : values()) {
			for (Class<?> entity : cat.entities()) {
				if (cat.seen.get(entity) || cat.encounterCount.get(entity) > 0){
					classes.add(entity);
					seen.add(cat.seen.get(entity));
					encounters.add(cat.encounterCount.get(entity));
				}
			}
		}

		Class<?>[] storeCls = new Class[classes.size()];
		boolean[] storeSeen = new boolean[seen.size()];
		int[] storeEncounters = new int[encounters.size()];

		for (int i = 0; i < storeCls.length; i++){
			storeCls[i] = classes.get(i);
			storeSeen[i] = seen.get(i);
			storeEncounters[i] = encounters.get(i);
		}

		bundle.put( BESTIARY_CLASSES, storeCls );
		bundle.put( BESTIARY_SEEN, storeSeen );
		bundle.put( BESTIARY_ENCOUNTERS, storeEncounters );

	}

	public static void restore( Bundle bundle ){

		if (bundle.contains(BESTIARY_CLASSES)
				&& bundle.contains(BESTIARY_SEEN)
				&& bundle.contains(BESTIARY_ENCOUNTERS)){
			Class<?>[] classes = bundle.getClassArray(BESTIARY_CLASSES);
			boolean[] seen = bundle.getBooleanArray(BESTIARY_SEEN);
			int[] encounters = bundle.getIntArray(BESTIARY_ENCOUNTERS);

			for (int i = 0; i < classes.length; i++){
				for (Bestiary cat : values()){
					if (cat.seen.containsKey(classes[i])){
						cat.seen.put(classes[i], seen[i]);
						cat.encounterCount.put(classes[i], encounters[i]);
					}
				}
			}
		}

	}

}
