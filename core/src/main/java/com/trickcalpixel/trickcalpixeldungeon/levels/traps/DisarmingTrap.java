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

package com.trickcalpixel.trickcalpixeldungeon.levels.traps;

import com.trickcalpixel.trickcalpixeldungeon.Assets;
import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.Hero;
import com.trickcalpixel.trickcalpixeldungeon.effects.CellEmitter;
import com.trickcalpixel.trickcalpixeldungeon.effects.Speck;
import com.trickcalpixel.trickcalpixeldungeon.items.Heap;
import com.trickcalpixel.trickcalpixeldungeon.items.Honeypot;
import com.trickcalpixel.trickcalpixeldungeon.items.Item;
import com.trickcalpixel.trickcalpixeldungeon.items.KindOfWeapon;
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.trickcalpixel.trickcalpixeldungeon.scenes.GameScene;
import com.trickcalpixel.trickcalpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class DisarmingTrap extends Trap{

	{
		color = RED;
		shape = LARGE_DOT;
	}

	@Override
	public void activate() {
		Heap heap = Dungeon.level.heaps.get( pos );

		if (heap != null && heap.type == Heap.Type.HEAP){

			int cell;
			do {
				cell = Dungeon.level.randomRespawnCell(null);
			} while (cell != -1 && Dungeon.level.heaps.get( pos ) != null
						&& Dungeon.level.heaps.get( pos ).type != Heap.Type.HEAP);

			if (cell != -1) {
				Item item = heap.pickUp();
				Heap dropped = Dungeon.level.drop( item, cell );
				dropped.seen = true;
				if (item instanceof Honeypot.ShatteredPot){
					((Honeypot.ShatteredPot)item).movePot(pos, cell);
				}
				for (int i : PathFinder.NEIGHBOURS9) Dungeon.level.visited[cell+i] = true;
				GameScene.updateFog();
				Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
				CellEmitter.get(pos).burst(Speck.factory(Speck.LIGHT), 4);
			}
		}

		if (Dungeon.hero.pos == pos && !Dungeon.hero.flying){
			Hero hero = Dungeon.hero;
			KindOfWeapon weapon = hero.belongings.weapon;

			if (weapon != null && !weapon.cursed) {

				int cell;
				int tries = 20;
				do {
					cell = Dungeon.level.randomRespawnCell( null );
					if (tries-- < 0 && cell != -1) break;

					PathFinder.buildDistanceMap(pos, Dungeon.level.passable);
				} while (cell == -1 || PathFinder.distance[cell] < 10 || PathFinder.distance[cell] > 20);

				hero.belongings.weapon = null;
				Dungeon.quickslot.clearItem(weapon);
				weapon.updateQuickslot();

				Dungeon.level.drop(weapon, cell).seen = true;
				for (int i : PathFinder.NEIGHBOURS9)
					Dungeon.level.mapped[cell+i] = true;
				GameScene.updateFog(cell, 1);

				GLog.w( Messages.get(this, "disarm") );

				Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
				CellEmitter.get(pos).burst(Speck.factory(Speck.LIGHT), 4);

			}
		}
	}
}
