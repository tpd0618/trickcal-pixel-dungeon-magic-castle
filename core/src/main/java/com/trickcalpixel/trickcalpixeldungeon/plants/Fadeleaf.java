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

package com.trickcalpixel.trickcalpixeldungeon.plants;

import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.Hero;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.HeroSubClass;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Mob;
import com.trickcalpixel.trickcalpixeldungeon.effects.CellEmitter;
import com.trickcalpixel.trickcalpixeldungeon.effects.Speck;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.trickcalpixel.trickcalpixeldungeon.levels.Level;
import com.trickcalpixel.trickcalpixeldungeon.scenes.InterlevelScene;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.Game;

public class Fadeleaf extends Plant {
	
	{
		image = 10;
		seedClass = Seed.class;
	}
	
	@Override
	public void activate( final Char ch ) {
		
		if (ch instanceof Hero) {
			
			((Hero)ch).curAction = null;
			
			if (((Hero) ch).subClass == HeroSubClass.WARDEN && Dungeon.interfloorTeleportAllowed()){

				Level.beforeTransition();
				InterlevelScene.mode = InterlevelScene.Mode.RETURN;
				InterlevelScene.returnDepth = Math.max(1, (Dungeon.depth - 1));
				InterlevelScene.returnBranch = 0;
				InterlevelScene.returnPos = -2;
				Game.switchScene( InterlevelScene.class );
				
			} else {
				ScrollOfTeleportation.teleportChar(ch, Fadeleaf.class);
			}
			
		} else if (ch instanceof Mob && !ch.properties().contains(Char.Property.IMMOVABLE)) {

			ScrollOfTeleportation.teleportChar(ch, Fadeleaf.class);

		}
		
		if (Dungeon.level.heroFOV[pos]) {
			CellEmitter.get( pos ).start( Speck.factory( Speck.LIGHT ), 0.2f, 3 );
		}
	}
	
	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.SEED_FADELEAF;

			plantClass = Fadeleaf.class;
		}
	}
}
