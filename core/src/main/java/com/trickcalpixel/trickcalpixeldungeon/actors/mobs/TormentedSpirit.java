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

package com.trickcalpixel.trickcalpixeldungeon.actors.mobs;

import com.trickcalpixel.trickcalpixeldungeon.Assets;
import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.effects.Speck;
import com.trickcalpixel.trickcalpixeldungeon.effects.particles.ShaftParticle;
import com.trickcalpixel.trickcalpixeldungeon.items.Generator;
import com.trickcalpixel.trickcalpixeldungeon.items.Item;
import com.trickcalpixel.trickcalpixeldungeon.items.armor.Armor;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.Weapon;
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.trickcalpixel.trickcalpixeldungeon.sprites.TormentedSpiritSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class TormentedSpirit extends Wraith {

	{
		spriteClass = TormentedSpiritSprite.class;
	}

	//50% more damage scaling than regular wraiths
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1 + Math.round(1.5f*level)/2, 2 + Math.round(1.5f*level) );
	}

	//50% more accuracy (and by extension evasion) scaling than regular wraiths
	@Override
	public int attackSkill( Char target ) {
		return 10 + Math.round(1.5f*level);
	}

	public void cleanse(){
		Sample.INSTANCE.play( Assets.Sounds.GHOST );
		yell(Messages.get(this, "thank_you"));

		//50/50 between weapon or armor, always uncursed & enchanted, 50% chance to be +1 if level 0
		Item prize;
		if (Random.Int(2) == 0){
			prize = Generator.randomWeapon(true);
			((Weapon)prize).enchant();
		} else {
			prize = Generator.randomArmor();
			((Armor) prize).inscribe();
		}
		prize.cursed = false;
		prize.cursedKnown = true;

		if (prize.level() == 0 && Random.Int(2) == 0){
			prize.upgrade();
		}

		Dungeon.level.drop(prize, pos).sprite.drop();

		destroy();
		sprite.die();
		sprite.tint(1, 1, 1, 1);
		sprite.emitter().start( ShaftParticle.FACTORY, 0.3f, 4 );
		sprite.emitter().start( Speck.factory( Speck.LIGHT ), 0.2f, 3 );

	}

}
