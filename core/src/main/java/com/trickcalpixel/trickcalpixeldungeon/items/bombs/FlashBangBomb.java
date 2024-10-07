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

package com.trickcalpixel.trickcalpixeldungeon.items.bombs;

import com.trickcalpixel.trickcalpixeldungeon.Assets;
import com.trickcalpixel.trickcalpixeldungeon.Badges;
import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.Actor;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.Electricity;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Buff;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Paralysis;
import com.trickcalpixel.trickcalpixeldungeon.effects.CellEmitter;
import com.trickcalpixel.trickcalpixeldungeon.effects.Lightning;
import com.trickcalpixel.trickcalpixeldungeon.effects.particles.SparkParticle;
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.trickcalpixel.trickcalpixeldungeon.scenes.GameScene;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSpriteSheet;
import com.trickcalpixel.trickcalpixeldungeon.tiles.DungeonTilemap;
import com.trickcalpixel.trickcalpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class FlashBangBomb extends Bomb {
	
	{
		image = ItemSpriteSheet.FLASHBANG;
	}

	@Override
	protected int explosionRange() {
		return 2;
	}

	@Override
	public void explode(int cell) {
		super.explode(cell);

		ArrayList<Char> affected = new ArrayList<>();
		PathFinder.buildDistanceMap( cell, BArray.not( Dungeon.level.solid, null ), explosionRange() );
		for (int i = 0; i < PathFinder.distance.length; i++) {
			if (PathFinder.distance[i] < Integer.MAX_VALUE && Actor.findChar(i) != null) {
				affected.add(Actor.findChar(i));
			}
		}

		ArrayList<Lightning.Arc> arcs = new ArrayList<>();
		for (Char ch : affected){
			//25% bonus damage and 10 turns of stun
			int damage = Math.round(Random.NormalIntRange(4 + Dungeon.scalingDepth(), 12 + 3*Dungeon.scalingDepth()) / 4f);
			ch.damage(damage, Electricity.class);
			if (ch.isAlive()) Buff.prolong(ch, Paralysis.class, Paralysis.DURATION);
			arcs.add(new Lightning.Arc(DungeonTilemap.tileCenterToWorld(cell), ch.sprite.center()));

			if (ch == Dungeon.hero){
				GameScene.flash(0x80FFFFFF);
			}

			if (ch == Dungeon.hero && !ch.isAlive()) {
				Badges.validateDeathFromFriendlyMagic();
				GLog.n(Messages.get(this, "ondeath"));
				Dungeon.fail(this);
			}
		}

		CellEmitter.center(cell).burst(SparkParticle.FACTORY, 20);
		Dungeon.hero.sprite.parent.addToFront(new Lightning(arcs, null));
		Sample.INSTANCE.play( Assets.Sounds.LIGHTNING );
	}
	
	@Override
	public int value() {
		//prices of ingredients
		return quantity * (20 + 30);
	}
}
