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

package com.trickcalpixel.trickcalpixeldungeon.actors.hero;

import com.trickcalpixel.trickcalpixeldungeon.Assets;
import com.trickcalpixel.trickcalpixeldungeon.Badges;
import com.trickcalpixel.trickcalpixeldungeon.Challenges;
import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.QuickSlot;
import com.trickcalpixel.trickcalpixeldungeon.SPDSettings;
import com.trickcalpixel.trickcalpixeldungeon.items.BrokenSeal;
import com.trickcalpixel.trickcalpixeldungeon.items.Item;
import com.trickcalpixel.trickcalpixeldungeon.items.Waterskin;
import com.trickcalpixel.trickcalpixeldungeon.items.armor.ClothArmor;
import com.trickcalpixel.trickcalpixeldungeon.items.artifacts.CloakOfShadows;
import com.trickcalpixel.trickcalpixeldungeon.items.bags.VelvetPouch;
import com.trickcalpixel.trickcalpixeldungeon.items.food.Food;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.PotionOfHealing;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.PotionOfInvisibility;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.PotionOfMindVision;
import com.trickcalpixel.trickcalpixeldungeon.items.potions.PotionOfStrength;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfRage;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.melee.Dagger;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.melee.Gloves;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.melee.Rapier;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.melee.WornShortsword;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.missiles.ThrowingKnife;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.missiles.ThrowingSpike;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.missiles.ThrowingStone;
import com.trickcalpixel.trickcalpixeldungeon.journal.Catalog;
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.watabou.utils.DeviceCompat;

public enum HeroClass {

	AYA( HeroSubClass.BERSERKER, HeroSubClass.GLADIATOR ),
	MAGE( HeroSubClass.BATTLEMAGE, HeroSubClass.WARLOCK ),
	ROGUE( HeroSubClass.ASSASSIN, HeroSubClass.FREERUNNER ),
	HUNTRESS( HeroSubClass.SNIPER, HeroSubClass.WARDEN ),
	DUELIST( HeroSubClass.CHAMPION, HeroSubClass.MONK );

	private HeroSubClass[] subClasses;

	HeroClass( HeroSubClass...subClasses ) {
		this.subClasses = subClasses;
	}

	public void initHero( Hero hero ) {

		hero.heroClass = this;
		Talent.initClassTalents(hero);

		Item i = new ClothArmor().identify();
		if (!Challenges.isItemBlocked(i)) hero.belongings.armor = (ClothArmor)i;

		i = new Food();
		if (!Challenges.isItemBlocked(i)) i.collect();

		new VelvetPouch().collect();
		Dungeon.LimitedDrops.VELVET_POUCH.drop();

		Waterskin waterskin = new Waterskin();
		waterskin.collect();

		new ScrollOfIdentify().identify();

		switch (this) {
			case AYA:
				initWarrior( hero );
				break;

			case MAGE:
				initMage( hero );
				break;

			case ROGUE:
				initRogue( hero );
				break;

			case HUNTRESS:
				initHuntress( hero );
				break;

			case DUELIST:
				initDuelist( hero );
				break;
		}

		if (SPDSettings.quickslotWaterskin()) {
			for (int s = 0; s < QuickSlot.SIZE; s++) {
				if (Dungeon.quickslot.getItem(s) == null) {
					Dungeon.quickslot.setSlot(s, waterskin);
					break;
				}
			}
		}

	}

	public Badges.Badge masteryBadge() {
		switch (this) {
			case AYA:
				return Badges.Badge.MASTERY_WARRIOR;
			case MAGE:
				return Badges.Badge.MASTERY_MAGE;
			case ROGUE:
				return Badges.Badge.MASTERY_ROGUE;
			case HUNTRESS:
				return Badges.Badge.MASTERY_HUNTRESS;
			case DUELIST:
				return Badges.Badge.MASTERY_DUELIST;
		}
		return null;
	}

	private static void initWarrior( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();
		ThrowingStone stones = new ThrowingStone();
		stones.quantity(3).collect();
		Dungeon.quickslot.setSlot(0, stones);

		if (hero.belongings.armor != null){
			hero.belongings.armor.affixSeal(new BrokenSeal());
			Catalog.setSeen(BrokenSeal.class); //as it's not added to the inventory
		}

		new PotionOfHealing().identify();
		new ScrollOfRage().identify();
	}

	private static void initMage( Hero hero ) {
		hero.belongings.weapon.activate(hero);

		new ScrollOfUpgrade().identify();
		new PotionOfLiquidFlame().identify();
	}

	private static void initRogue( Hero hero ) {
		(hero.belongings.weapon = new Dagger()).identify();

		CloakOfShadows cloak = new CloakOfShadows();
		(hero.belongings.artifact = cloak).identify();
		hero.belongings.artifact.activate( hero );

		ThrowingKnife knives = new ThrowingKnife();
		knives.quantity(3).collect();

		Dungeon.quickslot.setSlot(0, cloak);
		Dungeon.quickslot.setSlot(1, knives);

		new ScrollOfMagicMapping().identify();
		new PotionOfInvisibility().identify();
	}

	private static void initHuntress( Hero hero ) {

		(hero.belongings.weapon = new Gloves()).identify();

		new PotionOfMindVision().identify();
		new ScrollOfLullaby().identify();
	}

	private static void initDuelist( Hero hero ) {

		(hero.belongings.weapon = new Rapier()).identify();
		hero.belongings.weapon.activate(hero);

		ThrowingSpike spikes = new ThrowingSpike();
		spikes.quantity(2).collect();

		Dungeon.quickslot.setSlot(0, hero.belongings.weapon);
		Dungeon.quickslot.setSlot(1, spikes);

		new PotionOfStrength().identify();
		new ScrollOfMirrorImage().identify();
	}

	public String title() {
		return Messages.get(HeroClass.class, name());
	}

	public String desc(){
		return Messages.get(HeroClass.class, name()+"_desc");
	}

	public String shortDesc(){
		return Messages.get(HeroClass.class, name()+"_desc_short");
	}

	public HeroSubClass[] subClasses() {
		return subClasses;
	}

	public String spritesheet() {
		switch (this) {
			case AYA: default:
				return Assets.Sprites.AYA;
		}
	}

	public String splashArt(){
		switch (this) {
			case AYA: default:
				return Assets.Splashes.AYA;
			case MAGE:
				return Assets.Splashes.MAGE;
			case ROGUE:
				return Assets.Splashes.ROGUE;
			case HUNTRESS:
				return Assets.Splashes.HUNTRESS;
			case DUELIST:
				return Assets.Splashes.DUELIST;
		}
	}
	
	public boolean isUnlocked(){
		//always unlock on debug builds
		if (DeviceCompat.isDebug()) return true;

		switch (this){
			case AYA: default:
				return true;
			case MAGE:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_MAGE);
			case ROGUE:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_ROGUE);
			case HUNTRESS:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_HUNTRESS);
			case DUELIST:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_DUELIST);
		}
	}
	
	public String unlockMsg() {
		return shortDesc() + "\n\n" + Messages.get(HeroClass.class, name()+"_unlock");
	}

}
