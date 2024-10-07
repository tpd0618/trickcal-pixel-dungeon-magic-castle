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

package com.trickcalpixel.trickcalpixeldungeon.levels.rooms.special;

import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Mimic;
import com.trickcalpixel.trickcalpixeldungeon.items.Gold;
import com.trickcalpixel.trickcalpixeldungeon.items.Heap;
import com.trickcalpixel.trickcalpixeldungeon.items.Item;
import com.trickcalpixel.trickcalpixeldungeon.items.keys.IronKey;
import com.trickcalpixel.trickcalpixeldungeon.items.trinkets.MimicTooth;
import com.trickcalpixel.trickcalpixeldungeon.items.trinkets.TrinketCatalyst;
import com.trickcalpixel.trickcalpixeldungeon.levels.Level;
import com.trickcalpixel.trickcalpixeldungeon.levels.Terrain;
import com.trickcalpixel.trickcalpixeldungeon.levels.painters.Painter;
import com.watabou.utils.Random;

public class TreasuryRoom extends SpecialRoom {

	public void paint( Level level ) {
		
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY );
		
		Painter.set( level, center(), Terrain.STATUE );
		
		Heap.Type heapType = Random.Int( 2 ) == 0 ? Heap.Type.CHEST : Heap.Type.HEAP;
		
		int n = Random.IntRange( 2, 3 );
		float mimicChance = 1/5f * MimicTooth.mimicChanceMultiplier();
		for (int i=0; i < n; i++) {
			Item item = level.findPrizeItem(TrinketCatalyst.class);
			if (item == null) item = new Gold().random();

			int pos;
			do {
				pos = level.pointToCell(random());
			} while (level.map[pos] != Terrain.EMPTY || level.heaps.get( pos ) != null || level.findMob(pos) != null);
			if (heapType == Heap.Type.CHEST && Dungeon.depth > 1 && Random.Float() < mimicChance){
				level.mobs.add(Mimic.spawnAt(pos, item));
			} else {
				level.drop( item, pos ).type = heapType;
			}
		}
		
		if (heapType == Heap.Type.HEAP) {
			for (int i=0; i < 6; i++) {
				int pos;
				do {
					pos = level.pointToCell(random());
				} while (level.map[pos] != Terrain.EMPTY);
				level.drop( new Gold( Random.IntRange( 5, 12 ) ), pos );
			}
		}
		
		entrance().set( Door.Type.LOCKED );
		level.addItemToSpawn( new IronKey( Dungeon.depth ) );
	}
}
