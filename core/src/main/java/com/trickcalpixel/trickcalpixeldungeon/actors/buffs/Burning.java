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

package com.trickcalpixel.trickcalpixeldungeon.actors.buffs;

import com.trickcalpixel.trickcalpixeldungeon.Badges;
import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.Blob;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.Fire;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.Hero;
import com.trickcalpixel.trickcalpixeldungeon.items.Heap;
import com.trickcalpixel.trickcalpixeldungeon.items.Item;
import com.trickcalpixel.trickcalpixeldungeon.items.armor.Armor;
import com.trickcalpixel.trickcalpixeldungeon.items.armor.glyphs.Brimstone;
import com.trickcalpixel.trickcalpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.trickcalpixel.trickcalpixeldungeon.items.food.ChargrilledMeat;
import com.trickcalpixel.trickcalpixeldungeon.items.food.FrozenCarpaccio;
import com.trickcalpixel.trickcalpixeldungeon.items.food.MysteryMeat;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.Scroll;
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.trickcalpixel.trickcalpixeldungeon.scenes.GameScene;
import com.trickcalpixel.trickcalpixeldungeon.sprites.CharSprite;
import com.trickcalpixel.trickcalpixeldungeon.ui.BuffIndicator;
import com.trickcalpixel.trickcalpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Burning extends Buff implements Hero.Doom {
	
	private static final float DURATION = 8f;
	
	private float left;
	private boolean acted = false; //whether the debuff has done any damage at all yet
	private int burnIncrement = 0; //for tracking burning of hero items
	
	private static final String LEFT	= "left";
	private static final String ACTED	= "acted";
	private static final String BURN	= "burnIncrement";

	{
		type = buffType.NEGATIVE;
		announced = true;
	}
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( LEFT, left );
		bundle.put( ACTED, acted );
		bundle.put( BURN, burnIncrement );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		left = bundle.getFloat( LEFT );
		acted = bundle.getBoolean( ACTED );
		burnIncrement = bundle.getInt( BURN );
	}

	@Override
	public boolean attachTo(Char target) {
		Buff.detach( target, Chill.class);

		return super.attachTo(target);
	}

	@Override
	public boolean act() {

		if (acted && Dungeon.level.water[target.pos] && !target.flying){
			detach();
		} else if (target.isAlive() && !target.isImmune(getClass())) {

			acted = true;
			int damage = Random.NormalIntRange( 1, 3 + Dungeon.scalingDepth()/4 );
			Buff.detach( target, Chill.class);

			if (target instanceof Hero
					&& target.buff(TimekeepersHourglass.timeStasis.class) == null
					&& target.buff(TimeStasis.class) == null) {
				
				Hero hero = (Hero)target;

				hero.damage( damage, this );
				burnIncrement++;

				//at 4+ turns, there is a (turns-3)/3 chance an item burns
				if (Random.Int(3) < (burnIncrement - 3)){
					burnIncrement = 0;

					ArrayList<Item> burnable = new ArrayList<>();
					//does not reach inside of containers
					if (!hero.belongings.lostInventory()) {
						for (Item i : hero.belongings.backpack.items) {
							if (!i.unique && (i instanceof Scroll || i instanceof MysteryMeat || i instanceof FrozenCarpaccio)) {
								burnable.add(i);
							}
						}
					}

					if (!burnable.isEmpty()){
						Item toBurn = Random.element(burnable).detach(hero.belongings.backpack);
						GLog.w( Messages.capitalize(Messages.get(this, "burnsup", toBurn.title())) );
						if (toBurn instanceof MysteryMeat || toBurn instanceof FrozenCarpaccio){
							ChargrilledMeat steak = new ChargrilledMeat();
							if (!steak.collect( hero.belongings.backpack )) {
								Dungeon.level.drop( steak, hero.pos ).sprite.drop();
							}
						}
						Heap.burnFX( hero.pos );
					}
				}
				
			} else {
				target.damage( damage, this );
			}

		} else {

			detach();
		}
		
		if (Dungeon.level.flamable[target.pos] && Blob.volumeAt(target.pos, Fire.class) == 0) {
			GameScene.add( Blob.seed( target.pos, 4, Fire.class ) );
		}
		
		spend( TICK );
		left -= TICK;
		
		if (left <= 0 ||
			(Dungeon.level.water[target.pos] && !target.flying)) {
			
			detach();
		}
		
		return true;
	}
	
	public void reignite( Char ch ) {
		reignite( ch, DURATION );
	}
	
	public void reignite( Char ch, float duration ) {
		if (ch.isImmune(Burning.class)){
			//TODO this only works for the hero, not others who can have brimstone+arcana effect
			// e.g. prismatic image, shadow clone
			if (ch instanceof Hero
					&& ((Hero) ch).belongings.armor() != null
					&& ((Hero) ch).belongings.armor().hasGlyph(Brimstone.class, ch)){
				//generate avg of 1 shield per turn per 50% boost, to a max of 4x boost
				float shieldChance = 2*(Armor.Glyph.genericProcChanceMultiplier(ch) - 1f);
				int shieldCap = Math.round(shieldChance*4f);
				int shieldGain = (int)shieldChance;
				if (Random.Float() < shieldChance%1) shieldGain++;
				if (shieldCap > 0 && shieldGain > 0){
					Barrier barrier = Buff.affect(ch, Barrier.class);
					if (barrier.shielding() < shieldCap){
						barrier.incShield(1);
					}
				}
			}
		}
		left = duration;
		acted = false;
	}
	
	@Override
	public int icon() {
		return BuffIndicator.FIRE;
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (DURATION - left) / DURATION);
	}

	@Override
	public String iconTextDisplay() {
		return Integer.toString((int)left);
	}

	@Override
	public void fx(boolean on) {
		if (on) target.sprite.add(CharSprite.State.BURNING);
		else target.sprite.remove(CharSprite.State.BURNING);
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns(left));
	}

	@Override
	public void onDeath() {
		
		Badges.validateDeathFromFire();
		
		Dungeon.fail( this );
		GLog.n( Messages.get(this, "ondeath") );
	}
}
