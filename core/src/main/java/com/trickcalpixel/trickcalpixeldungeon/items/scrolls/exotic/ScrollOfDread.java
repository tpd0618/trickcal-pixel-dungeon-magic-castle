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

package com.trickcalpixel.trickcalpixeldungeon.items.scrolls.exotic;

import com.trickcalpixel.trickcalpixeldungeon.Assets;
import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Buff;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Dread;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Terror;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Mob;
import com.trickcalpixel.trickcalpixeldungeon.effects.Flare;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;

public class ScrollOfDread extends ExoticScroll {
	
	{
		icon = ItemSpriteSheet.Icons.SCROLL_DREAD;
	}
	
	@Override
	public void doRead() {

		detach(curUser.belongings.backpack);
		new Flare( 5, 32 ).color( 0xFF0000, true ).show( curUser.sprite, 2f );
		Sample.INSTANCE.play( Assets.Sounds.READ );

		for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
			if (mob.alignment != Char.Alignment.ALLY && Dungeon.level.heroFOV[mob.pos]) {
				if (!mob.isImmune(Dread.class)){
					Buff.affect( mob, Dread.class ).object = curUser.id();
				} else {
					Buff.affect( mob, Terror.class, Terror.DURATION ).object = curUser.id();
				}
			}
		}

		identify();
		
		readAnimation();
	}
}
