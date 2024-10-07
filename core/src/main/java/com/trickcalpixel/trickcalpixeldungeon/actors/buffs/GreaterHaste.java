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

import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.Talent;
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.trickcalpixel.trickcalpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

//currently only applies to the hero
public class GreaterHaste extends Buff {

	{
		type = buffType.POSITIVE;
	}

	private int left;

	@Override
	public boolean act() {

		spendMove();

		spend(TICK);
		return true;
	}

	public void spendMove(){
		left--;
		if (left <= 0){
			detach();
		}
	}

	public void set(int time){
		left = time;
	}

	@Override
	public int icon() {
		return BuffIndicator.HASTE;
	}

	@Override
	public void tintIcon(Image icon) {
		icon.hardlight(1f, 0.3f, 0f);
	}

	@Override
	public float iconFadePercent() {
		//currently tied to the lethal haste talent, as that's the only source
		float duration = 1 + 2*Dungeon.hero.pointsInTalent(Talent.LETHAL_HASTE);
		return Math.max(0, (duration - left) / duration);
	}

	@Override
	public String iconTextDisplay() {
		return Integer.toString(left);
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", left);
	}

}
