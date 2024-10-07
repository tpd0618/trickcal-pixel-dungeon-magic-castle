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

import com.trickcalpixel.trickcalpixeldungeon.Challenges;
import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.Hero;
import com.trickcalpixel.trickcalpixeldungeon.effects.FloatingText;
import com.trickcalpixel.trickcalpixeldungeon.items.trinkets.SaltCube;
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.trickcalpixel.trickcalpixeldungeon.sprites.CharSprite;
import com.trickcalpixel.trickcalpixeldungeon.ui.BuffIndicator;
import com.watabou.utils.Bundle;

public class WellFed extends Buff {

	{
		type = buffType.POSITIVE;
		announced = true;
	}
	
	int left;
	
	@Override
	public boolean act() {
		left --;
		if (left < 0){
			detach();
			if (target instanceof Hero) {
				((Hero) target).resting = false;
			}
			return true;
		} else if (left % 18 == 0 && target.HP < target.HT){
			target.HP += 1;
			target.sprite.showStatusWithIcon(CharSprite.POSITIVE, "1", FloatingText.HEALING);

			if (target.HP == target.HT && target instanceof Hero) {
				((Hero) target).resting = false;
			}
		}

		//salt cube does slow this buff down, but doesn't lessen the bonus health
		spend(TICK / SaltCube.hungerGainMultiplier());
		return true;
	}
	
	public void reset(){
		//heals one HP every 18 turns for 450 turns
		//25 HP healed in total
		left = (int)Hunger.STARVING;
		if (Dungeon.isChallenged(Challenges.NO_FOOD)){
			//150 turns if on diet is enabled
			left /= 3;
		}
	}
	
	@Override
	public int icon() {
		return BuffIndicator.WELL_FED;
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (Hunger.STARVING - left) / Hunger.STARVING);
	}

	@Override
	public String iconTextDisplay() {
		int visualLeft = (int)(left / SaltCube.hungerGainMultiplier());
		return Integer.toString(visualLeft+1);
	}
	
	@Override
	public String desc() {
		int visualLeft = (int)(left / SaltCube.hungerGainMultiplier());
		return Messages.get(this, "desc", visualLeft + 1);
	}
	
	private static final String LEFT = "left";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(LEFT, left);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		left = bundle.getInt(LEFT);
	}
}
