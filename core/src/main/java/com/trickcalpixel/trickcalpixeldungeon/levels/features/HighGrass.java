/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
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

package com.trickcalpixel.trickcalpixeldungeon.levels.features;

import com.trickcalpixel.trickcalpixeldungeon.Assets;
import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.ShatteredPixelDungeon;
import com.trickcalpixel.trickcalpixeldungeon.Statistics;
import com.trickcalpixel.trickcalpixeldungeon.actors.Actor;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.Hero;
import com.trickcalpixel.trickcalpixeldungeon.effects.CellEmitter;
import com.trickcalpixel.trickcalpixeldungeon.effects.particles.LeafParticle;
import com.trickcalpixel.trickcalpixeldungeon.items.Dewdrop;
import com.trickcalpixel.trickcalpixeldungeon.items.Generator;
import com.trickcalpixel.trickcalpixeldungeon.items.Item;
import com.trickcalpixel.trickcalpixeldungeon.items.Waterskin;
import com.trickcalpixel.trickcalpixeldungeon.items.armor.glyphs.Camouflage;
import com.trickcalpixel.trickcalpixeldungeon.items.artifacts.SandalsOfNature;
import com.trickcalpixel.trickcalpixeldungeon.levels.Level;
import com.trickcalpixel.trickcalpixeldungeon.levels.Terrain;
import com.trickcalpixel.trickcalpixeldungeon.scenes.GameScene;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class HighGrass {

	//prevents items dropped from grass, from trampling that same grass.
	//yes this is a bit ugly, oh well.
	private static boolean freezeTrample = false;

	public static void trample( Level level, int pos ) {

		if (freezeTrample) return;

		Char ch = Actor.findChar(pos);

		if (level.map[pos] == Terrain.FURROWED_GRASS){
			if (ch instanceof Hero && Statistics.card49){
				//Do nothing
				freezeTrample = true;
			} else {
				Level.set(pos, Terrain.GRASS);
			}

		} else {
			if (ch instanceof Hero && Statistics.card49){
				Level.set(pos, Terrain.FURROWED_GRASS);
				freezeTrample = true;
			} else {
				Level.set(pos, Terrain.GRASS);
			}

			int naturalismLevel = 0;

			if (ch != null) {
				SandalsOfNature.Naturalism naturalism = ch.buff( SandalsOfNature.Naturalism.class );
				if (naturalism != null) {
					if (!naturalism.isCursed()) {
						naturalismLevel = naturalism.itemLevel() + 1;
						naturalism.charge(1);
					} else {
						naturalismLevel = -1;
					}
				}
			}

			if (naturalismLevel >= 0) {
				// Seed, scales from 1/25 to 1/5
				if (Random.Int(25 - (naturalismLevel * 5)) == 0) {
					level.drop(Generator.random(Generator.Category.SEED), pos).sprite.drop();
				}

				// Dew, scales from 1/6 to 1/3
				Waterskin flask = Dungeon.hero.belongings.getItem(Waterskin.class);

				if (Random.Int(24 - naturalismLevel*3) <= 3) {
					if (flask != null && !flask.isFull() && ch instanceof Hero) {
						flask.volume += 1;
						Item.updateQuickslot();
						Sample.INSTANCE.play(Assets.Sounds.DEWDROP);
					} else {
						if (Random.Int(2) == 0) {
							level.drop(new Dewdrop(), pos).sprite.drop();
						}
					}
				}
			}

			//Camouflage
			if (ch instanceof Hero) {
				Hero heroine = (Hero) ch;
				if (heroine.belongings.armor() != null && heroine.belongings.armor().hasGlyph(Camouflage.class, heroine)) {
					Camouflage.activate(heroine, heroine.belongings.armor.buffedLvl());
				}
			}
		}

		freezeTrample = false;

		if (ShatteredPixelDungeon.scene() instanceof GameScene) {
			GameScene.updateMap(pos);

			CellEmitter.get(pos).burst(LeafParticle.LEVEL_SPECIFIC, 4);
			if (Dungeon.level.heroFOV[pos]) Dungeon.observe();
		}
	}
}