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

package com.trickcalpixel.trickcalpixeldungeon.items.bags;

import com.trickcalpixel.trickcalpixeldungeon.items.Item;
import com.trickcalpixel.trickcalpixeldungeon.items.stones.Runestone;
import com.trickcalpixel.trickcalpixeldungeon.plants.Plant;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSpriteSheet;

public class VelvetPouch extends Bag {

	{
		image = ItemSpriteSheet.POUCH;
	}

	@Override
	public boolean canHold( Item item ) {
		if (item instanceof Plant.Seed || item instanceof Runestone){
			return super.canHold(item);
		} else {
			return false;
		}
	}

	public int capacity(){
		return 19;
	}
	
	@Override
	public int value() {
		return 30;
	}

}
