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

package com.trickcalpixel.trickcalpixeldungeon.items.rings;

import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSpriteSheet;

public class RingOfArcana extends Ring {

	{
		icon = ItemSpriteSheet.Icons.RING_ARCANA;
	}

	public String statsInfo() {
		if (isIdentified()){
			String info = Messages.get(this, "stats",
					Messages.decimalFormat("#.##", 100f * (Math.pow(1.175f, soloBuffedBonus()) - 1f)));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						Messages.decimalFormat("#.##", 100f * (Math.pow(1.175f, combinedBuffedBonus(Dungeon.hero)) - 1f)));
			}
			return info;
		} else {
			return Messages.get(this, "typical_stats", Messages.decimalFormat("#.##", 17.5f));
		}
	}

	public String upgradeStat1(int level){
		if (cursed) level = Math.min(-1, level-3);
		return Messages.decimalFormat("#.##", 100f * (Math.pow(1.175f, level+1)-1f)) + "%";
	}

	@Override
	protected RingBuff buff( ) {
		return new Arcana();
	}

	public static float enchantPowerMultiplier(Char target ){
		return (float)Math.pow(1.175f, getBuffedBonus(target, Arcana.class));
	}

	public class Arcana extends RingBuff {
	}

}
