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
import com.trickcalpixel.trickcalpixeldungeon.items.Amulet;
import com.trickcalpixel.trickcalpixeldungeon.items.Ankh;
import com.trickcalpixel.trickcalpixeldungeon.items.ArcaneResin;
import com.trickcalpixel.trickcalpixeldungeon.items.BrokenSeal;
import com.trickcalpixel.trickcalpixeldungeon.items.Dewdrop;
import com.trickcalpixel.trickcalpixeldungeon.items.EnergyCrystal;
import com.trickcalpixel.trickcalpixeldungeon.items.Generator;
import com.trickcalpixel.trickcalpixeldungeon.items.Gold;
import com.trickcalpixel.trickcalpixeldungeon.items.Honeypot;
import com.trickcalpixel.trickcalpixeldungeon.items.LiquidMetal;
import com.trickcalpixel.trickcalpixeldungeon.items.Stylus;
import com.trickcalpixel.trickcalpixeldungeon.items.Torch;
import com.trickcalpixel.trickcalpixeldungeon.items.Waterskin;
import com.trickcalpixel.trickcalpixeldungeon.items.armor.Armor;
import com.trickcalpixel.trickcalpixeldungeon.items.bags.MagicalHolster;
import com.trickcalpixel.trickcalpixeldungeon.items.bags.PotionBandolier;
import com.trickcalpixel.trickcalpixeldungeon.items.bags.ScrollHolder;
import com.trickcalpixel.trickcalpixeldungeon.items.bags.VelvetPouch;
import com.trickcalpixel.trickcalpixeldungeon.items.food.Berry;
import com.trickcalpixel.trickcalpixeldungeon.items.food.Blandfruit;
import com.trickcalpixel.trickcalpixeldungeon.items.food.ChargrilledMeat;
import com.trickcalpixel.trickcalpixeldungeon.items.food.Food;
import com.trickcalpixel.trickcalpixeldungeon.items.food.FrozenCarpaccio;
import com.trickcalpixel.trickcalpixeldungeon.items.food.MeatPie;
import com.trickcalpixel.trickcalpixeldungeon.items.food.MysteryMeat;
import com.trickcalpixel.trickcalpixeldungeon.items.food.Pasty;
import com.trickcalpixel.trickcalpixeldungeon.items.food.PhantomMeat;
import com.trickcalpixel.trickcalpixeldungeon.items.food.SmallRation;
import com.trickcalpixel.trickcalpixeldungeon.items.food.StewedMeat;
import com.trickcalpixel.trickcalpixeldungeon.items.food.SupplyRation;
import com.trickcalpixel.trickcalpixeldungeon.items.keys.CrystalKey;
import com.trickcalpixel.trickcalpixeldungeon.items.keys.GoldenKey;
import com.trickcalpixel.trickcalpixeldungeon.items.keys.IronKey;
import com.trickcalpixel.trickcalpixeldungeon.items.keys.SkeletonKey;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.brews.AquaBrew;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.brews.BlizzardBrew;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.brews.InfernalBrew;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.brews.ShockingBrew;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.brews.UnstableBrew;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.elixirs.ElixirOfDragonsBlood;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.elixirs.ElixirOfFeatherFall;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.elixirs.ElixirOfHoneyedHealing;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.elixirs.ElixirOfIcyTouch;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.elixirs.ElixirOfMight;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.elixirs.ElixirOfToxicEssence;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.exotic.ExoticPotion;
import com.trickcalpixel.trickcalpixeldungeon.items.remains.BowFragment;
import com.trickcalpixel.trickcalpixeldungeon.items.remains.BrokenHilt;
import com.trickcalpixel.trickcalpixeldungeon.items.remains.BrokenStaff;
import com.trickcalpixel.trickcalpixeldungeon.items.remains.CloakScrap;
import com.trickcalpixel.trickcalpixeldungeon.items.remains.SealShard;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.trickcalpixel.trickcalpixeldungeon.items.spells.Alchemize;
import com.trickcalpixel.trickcalpixeldungeon.items.spells.MagicalInfusion;
import com.trickcalpixel.trickcalpixeldungeon.items.spells.PhaseShift;
import com.trickcalpixel.trickcalpixeldungeon.items.spells.Recycle;
import com.trickcalpixel.trickcalpixeldungeon.items.spells.TelekineticGrab;
import com.trickcalpixel.trickcalpixeldungeon.items.spells.UnstableSpell;
import com.trickcalpixel.trickcalpixeldungeon.items.trinkets.TrinketCatalyst;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.Weapon;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.missiles.darts.TippedDart;
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;

//For items, but includes a few item-like effects, such as enchantments
public enum Catalog {

	//EQUIPMENT
	MELEE_WEAPONS,
	ARMOR,
	ENCHANTMENTS,
	GLYPHS,
	THROWN_WEAPONS,
	WANDS,
	RINGS,
	ARTIFACTS,
	TRINKETS,
	MISC_EQUIPMENT,

	//CONSUMABLES
	POTIONS,
	SEEDS,
	SCROLLS,
	STONES,
	FOOD,
	EXOTIC_POTIONS,
	EXOTIC_SCROLLS,
	BOMBS,
	TIPPED_DARTS,
	BREWS_ELIXIRS,
	SPELLS,
	MISC_CONSUMABLES;

	//tracks whether an item has been collected while identified
	private final LinkedHashMap<Class<?>, Boolean> seen = new LinkedHashMap<>();
	//tracks upgrades spent for equipment, uses for consumables
	private final LinkedHashMap<Class<?>, Integer> useCount = new LinkedHashMap<>();
	
	public Collection<Class<?>> items(){
		return seen.keySet();
	}

	//should only be used when initializing
	private void addItems( Class<?>... items){
		for (Class<?> item : items){
			seen.put(item, false);
			useCount.put(item, 0);
		}
	}

	public String title(){
		return Messages.get(this, name() + ".title");
	}

	public int totalItems(){
		return seen.size();
	}

	public int totalSeen(){
		int seenTotal = 0;
		for (boolean itemSeen : seen.values()){
			if (itemSeen) seenTotal++;
		}
		return seenTotal;
	}

	static {

		MELEE_WEAPONS.addItems(Generator.Category.WEP_T1.classes);
		MELEE_WEAPONS.addItems(Generator.Category.WEP_T2.classes);
		MELEE_WEAPONS.addItems(Generator.Category.WEP_T3.classes);
		MELEE_WEAPONS.addItems(Generator.Category.WEP_T4.classes);
		MELEE_WEAPONS.addItems(Generator.Category.WEP_T5.classes);

		ARMOR.addItems(Generator.Category.ARMOR.classes);

		THROWN_WEAPONS.addItems(Generator.Category.MIS_T1.classes);
		THROWN_WEAPONS.addItems(Generator.Category.MIS_T2.classes);
		THROWN_WEAPONS.addItems(Generator.Category.MIS_T3.classes);
		THROWN_WEAPONS.addItems(Generator.Category.MIS_T4.classes);
		THROWN_WEAPONS.addItems(Generator.Category.MIS_T5.classes);

		ENCHANTMENTS.addItems(Weapon.Enchantment.common);
		ENCHANTMENTS.addItems(Weapon.Enchantment.uncommon);
		ENCHANTMENTS.addItems(Weapon.Enchantment.rare);
		ENCHANTMENTS.addItems(Weapon.Enchantment.curses);

		GLYPHS.addItems(Armor.Glyph.common);
		GLYPHS.addItems(Armor.Glyph.uncommon);
		GLYPHS.addItems(Armor.Glyph.rare);
		GLYPHS.addItems(Armor.Glyph.curses);

		WANDS.addItems(Generator.Category.WAND.classes);

		RINGS.addItems(Generator.Category.BRACELET.classes);

		ARTIFACTS.addItems(Generator.Category.ARTIFACT.classes);

		TRINKETS.addItems(Generator.Category.TRINKET.classes);

		MISC_EQUIPMENT.addItems(BrokenSeal.class, Waterskin.class, VelvetPouch.class,
				PotionBandolier.class, ScrollHolder.class, MagicalHolster.class, Amulet.class);



		POTIONS.addItems(Generator.Category.POTION.classes);

		SCROLLS.addItems(Generator.Category.SCROLL.classes);

		SEEDS.addItems(Generator.Category.SEED.classes);

		STONES.addItems(Generator.Category.STONE.classes);

		FOOD.addItems( Food.class, Pasty.class, MysteryMeat.class, ChargrilledMeat.class,
				StewedMeat.class, FrozenCarpaccio.class, SmallRation.class, Berry.class,
				SupplyRation.class, Blandfruit.class, PhantomMeat.class, MeatPie.class );

		EXOTIC_POTIONS.addItems(ExoticPotion.exoToReg.keySet().toArray(new Class[0]));

		EXOTIC_SCROLLS.addItems(ExoticScroll.exoToReg.keySet().toArray(new Class[0]));

		TIPPED_DARTS.addItems(TippedDart.types.values().toArray(new Class[0]));

		BREWS_ELIXIRS.addItems( UnstableBrew.class, InfernalBrew.class, BlizzardBrew.class,
				ShockingBrew.class, AquaBrew.class, ElixirOfHoneyedHealing.class, ElixirOfDragonsBlood.class,
				ElixirOfIcyTouch.class, ElixirOfToxicEssence.class, ElixirOfMight.class, ElixirOfFeatherFall.class);

		SPELLS.addItems( UnstableSpell.class, TelekineticGrab.class, PhaseShift.class,
				Alchemize.class, MagicalInfusion.class, Recycle.class);

		MISC_CONSUMABLES.addItems( Gold.class, EnergyCrystal.class, Dewdrop.class,
				IronKey.class, GoldenKey.class, CrystalKey.class, SkeletonKey.class,
				TrinketCatalyst.class, Stylus.class, Torch.class, Honeypot.class, Ankh.class,
				LiquidMetal.class, ArcaneResin.class,
				SealShard.class, BrokenStaff.class, CloakScrap.class, BowFragment.class, BrokenHilt.class);

	}

	//old badges for pre-2.5
	public static LinkedHashMap<Catalog, Badges.Badge> catalogBadges = new LinkedHashMap<>();
	static {
		catalogBadges.put(MELEE_WEAPONS, Badges.Badge.ALL_WEAPONS_IDENTIFIED);
		catalogBadges.put(ARMOR, Badges.Badge.ALL_ARMOR_IDENTIFIED);
		catalogBadges.put(WANDS, Badges.Badge.ALL_WANDS_IDENTIFIED);
		catalogBadges.put(RINGS, Badges.Badge.ALL_RINGS_IDENTIFIED);
		catalogBadges.put(ARTIFACTS, Badges.Badge.ALL_ARTIFACTS_IDENTIFIED);
		catalogBadges.put(POTIONS, Badges.Badge.ALL_POTIONS_IDENTIFIED);
		catalogBadges.put(SCROLLS, Badges.Badge.ALL_SCROLLS_IDENTIFIED);
	}

	public static ArrayList<Catalog> equipmentCatalogs = new ArrayList<>();
	static {
		equipmentCatalogs.add(MELEE_WEAPONS);
		equipmentCatalogs.add(ARMOR);
		equipmentCatalogs.add(ENCHANTMENTS);
		equipmentCatalogs.add(GLYPHS);
		equipmentCatalogs.add(THROWN_WEAPONS);
		equipmentCatalogs.add(WANDS);
		equipmentCatalogs.add(RINGS);
		equipmentCatalogs.add(ARTIFACTS);
		equipmentCatalogs.add(TRINKETS);
		equipmentCatalogs.add(MISC_EQUIPMENT);
	}

	public static ArrayList<Catalog> consumableCatalogs = new ArrayList<>();
	static {
		consumableCatalogs.add(POTIONS);
		consumableCatalogs.add(SCROLLS);
		consumableCatalogs.add(SEEDS);
		consumableCatalogs.add(STONES);
		consumableCatalogs.add(FOOD);
		consumableCatalogs.add(EXOTIC_POTIONS);
		consumableCatalogs.add(EXOTIC_SCROLLS);
		consumableCatalogs.add(BOMBS);
		consumableCatalogs.add(TIPPED_DARTS);
		consumableCatalogs.add(BREWS_ELIXIRS);
		consumableCatalogs.add(SPELLS);
		consumableCatalogs.add(MISC_CONSUMABLES);
	}
	
	public static boolean isSeen(Class<?> cls){
		for (Catalog cat : values()) {
			if (cat.seen.containsKey(cls)) {
				return cat.seen.get(cls);
			}
		}
		return false;
	}
	
	public static void setSeen(Class<?> cls){
		for (Catalog cat : values()) {
			if (cat.seen.containsKey(cls) && !cat.seen.get(cls)) {
				cat.seen.put(cls, true);
				Journal.saveNeeded = true;
			}
		}
		Badges.validateCatalogBadges();
	}

	public static int useCount(Class<?> cls){
		for (Catalog cat : values()) {
			if (cat.useCount.containsKey(cls)) {
				return cat.useCount.get(cls);
			}
		}
		return 0;
	}

	public static void countUse(Class<?> cls){
		countUses(cls, 1);
	}

	public static void countUses(Class<?> cls, int uses){
		for (Catalog cat : values()) {
			if (cat.useCount.containsKey(cls) && cat.useCount.get(cls) != Integer.MAX_VALUE) {
				cat.useCount.put(cls, cat.useCount.get(cls)+uses);
				if (cat.useCount.get(cls) < -1_000_000_000){ //to catch cases of overflow
					cat.useCount.put(cls, Integer.MAX_VALUE);
				}
				Journal.saveNeeded = true;
			}
		}
	}

	private static final String CATALOG_CLASSES = "catalog_classes";
	private static final String CATALOG_SEEN    = "catalog_seen";
	private static final String CATALOG_USES    = "catalog_uses";
	
	public static void store( Bundle bundle ){

		ArrayList<Class<?>> classes = new ArrayList<>();
		ArrayList<Boolean> seen = new ArrayList<>();
		ArrayList<Integer> uses = new ArrayList<>();
		
		for (Catalog cat : values()) {
			for (Class<?> item : cat.items()) {
				if (cat.seen.get(item) || cat.useCount.get(item) > 0){
					classes.add(item);
					seen.add(cat.seen.get(item));
					uses.add(cat.useCount.get(item));
				}
			}
		}

		Class<?>[] storeCls = new Class[classes.size()];
		boolean[] storeSeen = new boolean[seen.size()];
		int[] storeUses = new int[uses.size()];

		for (int i = 0; i < storeCls.length; i++){
			storeCls[i] = classes.get(i);
			storeSeen[i] = seen.get(i);
			storeUses[i] = uses.get(i);
		}
		
		bundle.put( CATALOG_CLASSES, storeCls );
		bundle.put( CATALOG_SEEN, storeSeen );
		bundle.put( CATALOG_USES, storeUses );
		
	}

	//pre-v2.5
	private static final String CATALOG_ITEMS = "catalog_items";
	
	public static void restore( Bundle bundle ){

		//old logic for pre-v2.5 catalog-specific badges
		Badges.loadGlobal();
		for (Catalog cat : values()){
			if (Badges.isUnlocked(catalogBadges.get(cat))){
				for (Class<?> item : cat.items()){
					cat.seen.put(item, true);
				}
			}
		}
		if (bundle.contains(CATALOG_ITEMS)) {
			for (Class<?> cls : Arrays.asList(bundle.getClassArray(CATALOG_ITEMS))){
				for (Catalog cat : values()) {
					if (cat.seen.containsKey(cls)) {
						cat.seen.put(cls, true);
					}
				}
			}
		}
		//end of old logic

		if (bundle.contains(CATALOG_CLASSES)){
			Class<?>[] classes = bundle.getClassArray(CATALOG_CLASSES);
			boolean[] seen = bundle.getBooleanArray(CATALOG_SEEN);
			int[] uses = bundle.getIntArray(CATALOG_USES);

			for (int i = 0; i < classes.length; i++){
				for (Catalog cat : values()) {
					if (cat.seen.containsKey(classes[i])) {
						cat.seen.put(classes[i], seen[i]);
						cat.useCount.put(classes[i], uses[i]);
					}
				}

			}
		}

	}
	
}
