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

package com.trickcalpixel.trickcalpixeldungeon.items.spells;

import com.trickcalpixel.trickcalpixeldungeon.actors.hero.Hero;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.Talent;
import com.trickcalpixel.trickcalpixeldungeon.items.Item;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.Scroll;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfRage;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfTerror;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.trickcalpixel.trickcalpixeldungeon.items.stones.Runestone;
import com.trickcalpixel.trickcalpixeldungeon.journal.Catalog;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class UnstableSpell extends Spell {

	{
		image = ItemSpriteSheet.UNSTABLE_SPELL;
	}
	
	private static HashMap<Class<? extends Scroll>, Float> scrollChances = new HashMap<>();
	static{
		scrollChances.put( ScrollOfIdentify.class,      3f );
		scrollChances.put( ScrollOfRemoveCurse.class,   2f );
		scrollChances.put( ScrollOfMagicMapping.class,  2f );
		scrollChances.put( ScrollOfMirrorImage.class,   2f );
		scrollChances.put( ScrollOfRecharging.class,    2f );
		scrollChances.put( ScrollOfLullaby.class,       2f );
		scrollChances.put( ScrollOfRetribution.class,   2f );
		scrollChances.put( ScrollOfRage.class,          2f );
		scrollChances.put( ScrollOfTeleportation.class, 2f );
		scrollChances.put( ScrollOfTerror.class,        2f );
		scrollChances.put( ScrollOfTransmutation.class, 1f );
	}

	private static HashSet<Class<? extends Scroll>> nonCombatScrolls = new HashSet<>();
	static {
		nonCombatScrolls.add( ScrollOfIdentify.class );
		nonCombatScrolls.add( ScrollOfRemoveCurse.class );
		nonCombatScrolls.add( ScrollOfMagicMapping.class );
		nonCombatScrolls.add( ScrollOfRecharging.class );
		nonCombatScrolls.add( ScrollOfLullaby.class );
		nonCombatScrolls.add( ScrollOfTeleportation.class );
		nonCombatScrolls.add( ScrollOfTransmutation.class );
	}

	private static HashSet<Class<? extends Scroll>> combatScrolls = new HashSet<>();
	static {
		combatScrolls.add( ScrollOfMirrorImage.class );
		combatScrolls.add( ScrollOfRecharging.class );
		combatScrolls.add( ScrollOfLullaby.class );
		combatScrolls.add( ScrollOfRetribution.class );
		combatScrolls.add( ScrollOfRage.class );
		combatScrolls.add( ScrollOfTeleportation.class );
		combatScrolls.add( ScrollOfTerror.class );
	}
	
	@Override
	protected void onCast(Hero hero) {
		
		detach( curUser.belongings.backpack );
		updateQuickslot();
		
		Scroll s = Reflection.newInstance(Random.chances(scrollChances));

		//reroll the scroll until it is relevant for the situation (whether there are visible enemies)
		if (hero.visibleEnemies() == 0){
			while (combatScrolls.contains(s.getClass())){
				s = Reflection.newInstance(Random.chances(scrollChances));
			}
		} else {
			while (nonCombatScrolls.contains(s.getClass())){
				s = Reflection.newInstance(Random.chances(scrollChances));
			}
		}

		s.anonymize();
		curItem = s;
		s.doRead();

		Catalog.countUse(getClass());
		if (Random.Float() < talentChance){
			Talent.onScrollUsed(curUser, curUser.pos, talentFactor);
		}
	}

	//lower values, as it's cheaper to make
	@Override
	public int value() {
		return 40 * quantity;
	}

	@Override
	public int energyVal() {
		return 8 * quantity;
	}

	public static class Recipe extends com.trickcalpixel.trickcalpixeldungeon.items.Recipe {

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			boolean scroll = false;
			boolean stone = false;

			for (Item i : ingredients){
				if (i instanceof Runestone){
					stone = true;
					//if it is a regular or exotic potion
				} else if (ExoticScroll.regToExo.containsKey(i.getClass())
						|| ExoticScroll.regToExo.containsValue(i.getClass())) {
					scroll = true;
				}
			}

			return scroll && stone;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 1;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {

			for (Item i : ingredients){
				i.quantity(i.quantity()-1);
			}

			return sampleOutput(null);
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			return new UnstableSpell();
		}
	}
}
