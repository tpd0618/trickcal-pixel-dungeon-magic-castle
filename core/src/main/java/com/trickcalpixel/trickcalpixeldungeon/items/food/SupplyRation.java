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

package com.trickcalpixel.trickcalpixeldungeon.items.food;

import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Hunger;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.Hero;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.Talent;
import com.trickcalpixel.trickcalpixeldungeon.effects.FloatingText;
import com.trickcalpixel.trickcalpixeldungeon.items.artifacts.CloakOfShadows;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.trickcalpixel.trickcalpixeldungeon.sprites.CharSprite;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSpriteSheet;

public class SupplyRation extends Food {

	{
		image = ItemSpriteSheet.SUPPLY_RATION;
		energy = 2*Hunger.HUNGRY/3f; //200 food value

		bones = false;
	}

	@Override
	protected float eatingTime(){
		if (Dungeon.hero.hasTalent(Talent.IRON_STOMACH)
				|| Dungeon.hero.hasTalent(Talent.ENERGIZING_MEAL)
				|| Dungeon.hero.hasTalent(Talent.MYSTICAL_MEAL)
				|| Dungeon.hero.hasTalent(Talent.INVIGORATING_MEAL)
				|| Dungeon.hero.hasTalent(Talent.FOCUSED_MEAL)){
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	protected void satisfy(Hero hero) {
		super.satisfy(hero);

		hero.HP = Math.min(hero.HP + 5, hero.HT);
		hero.sprite.showStatusWithIcon( CharSprite.POSITIVE, "5", FloatingText.HEALING );

		CloakOfShadows cloak = hero.belongings.getItem(CloakOfShadows.class);
		if (cloak != null) {
			ScrollOfRecharging.charge(hero);
		}
	}

	@Override
	public int value() {
		return 10 * quantity;
	}

}
