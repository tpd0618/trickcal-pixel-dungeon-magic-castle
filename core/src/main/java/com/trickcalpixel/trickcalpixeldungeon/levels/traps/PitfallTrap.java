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

import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.Actor;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Buff;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.FlavourBuff;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Mob;
import com.trickcalpixel.trickcalpixeldungeon.effects.CellEmitter;
import com.trickcalpixel.trickcalpixeldungeon.effects.particles.PitfallParticle;
import com.trickcalpixel.trickcalpixeldungeon.items.Heap;
import com.trickcalpixel.trickcalpixeldungeon.items.Item;
import com.trickcalpixel.trickcalpixeldungeon.levels.features.Chasm;
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.trickcalpixel.trickcalpixeldungeon.scenes.GameScene;
import com.trickcalpixel.trickcalpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class PitfallTrap extends Trap {

	{
		color = RED;
		shape = DIAMOND;
	}

	@Override
	public void activate() {
		
		if( Dungeon.bossLevel() || Dungeon.depth > 25 || Dungeon.branch != 0){
			GLog.w(Messages.get(this, "no_pit"));
			return;
		}

		DelayedPit p = Buff.append(Dungeon.hero, DelayedPit.class, 1);
		p.depth = Dungeon.depth;
		p.branch = Dungeon.branch;

		ArrayList<Integer> positions = new ArrayList<>();
		for (int i : PathFinder.NEIGHBOURS9){
			if (!Dungeon.level.solid[pos+i] || Dungeon.level.passable[pos+i]){
				CellEmitter.floor(pos+i).burst(PitfallParticle.FACTORY4, 8);
				positions.add(pos+i);
			}
		}
		p.setPositions(positions);

		if (pos == Dungeon.hero.pos){
			GLog.n(Messages.get(this, "triggered_hero"));
		} else if (Dungeon.level.heroFOV[pos]){
			GLog.n(Messages.get(this, "triggered"));
		}

	}

	public static class DelayedPit extends FlavourBuff {

		{
			revivePersists = true;
		}

		public int[] positions = new int[0];
		public int depth;
		public int branch;

		public boolean ignoreAllies = false;

		@Override
		public boolean act() {

			boolean herofell = false;
			if (depth == Dungeon.depth && branch == Dungeon.branch && positions != null) {
				for (int cell : positions) {

					if (!Dungeon.level.insideMap(cell)
							|| (Dungeon.level.solid[cell] && !Dungeon.level.passable[cell])){
						continue;
					}

					CellEmitter.floor(cell).burst(PitfallParticle.FACTORY8, 12);

					Char ch = Actor.findChar(cell);
					//don't trigger on flying chars, or immovable neutral chars
					if (ch != null && !ch.flying
							&& !(ch.alignment == Char.Alignment.NEUTRAL && Char.hasProp(ch, Char.Property.IMMOVABLE))
							&& !(ch.alignment == Char.Alignment.ALLY && ignoreAllies)) {
						if (ch == Dungeon.hero) {
							herofell = true;
						} else {
							Chasm.mobFall((Mob) ch);
						}
					}

					Heap heap = Dungeon.level.heaps.get(cell);
					if (heap != null && heap.type != Heap.Type.FOR_SALE
							&& heap.type != Heap.Type.LOCKED_CHEST
							&& heap.type != Heap.Type.CRYSTAL_CHEST) {
						for (Item item : heap.items) {
							Dungeon.dropToChasm(item);
						}
						heap.sprite.kill();
						GameScene.discard(heap);
						heap.sprite.drop();
						Dungeon.level.heaps.remove(cell);
					}

				}
			}

			//process hero falling last
			if (herofell){
				Chasm.heroFall(Dungeon.hero.pos);
			}

			detach();
			return !herofell;
		}

		public void setPositions(ArrayList<Integer> positions){
			this.positions = new int[positions.size()];
			for (int i = 0; i < this.positions.length; i++){
				this.positions[i] = positions.get(i);
			}
		}

		private static final String POSITIONS = "positions";
		private static final String DEPTH = "depth";
		private static final String BRANCH = "branch";

		private static final String IGNORE_ALLIES = "ignore_allies";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(POSITIONS, positions);
			bundle.put(DEPTH, depth);
			bundle.put(BRANCH, branch);
			bundle.put(IGNORE_ALLIES, ignoreAllies);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			positions = bundle.getIntArray(POSITIONS);
			depth = bundle.getInt(DEPTH);
			branch = bundle.getInt(BRANCH);
			ignoreAllies = bundle.getBoolean(IGNORE_ALLIES);
		}

	}
}
