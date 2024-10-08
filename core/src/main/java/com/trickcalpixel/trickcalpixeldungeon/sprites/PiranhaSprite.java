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

package com.trickcalpixel.trickcalpixeldungeon.sprites;

import com.trickcalpixel.trickcalpixeldungeon.Assets;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.scenes.GameScene;
import com.watabou.noosa.TextureFilm;

public class PiranhaSprite extends MobSprite {
	
	public PiranhaSprite() {
		super();

		renderShadow = false;
		perspectiveRaise = 0.2f;
		
		texture( Assets.Sprites.ALICE );

		TextureFilm frames = new TextureFilm( texture, 23, 23 );

		idle = new Animation( 8, true );
		idle.frames( frames, 0);

		run = new Animation( 20, true );
		run.frames( frames, 0);

		attack = new Animation( 20, false );
		attack.frames( frames, 0);

		die = new Animation( 4, false );
		die.frames( frames, 0);

		play( idle );
	}

	@Override
	public void link(Char ch) {
		super.link(ch);
		renderShadow = false;
	}

	@Override
	public void onComplete( Animation anim ) {
		super.onComplete( anim );
		
		if (anim == attack) {
			GameScene.ripple( ch.pos );
		}
	}
}
