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

import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.Actor;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.trickcalmob.Alice;
import com.trickcalpixel.trickcalpixeldungeon.items.trinkets.RatSkull;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;

public class MobSpawner extends Actor {
	{
		actPriority = BUFF_PRIO; //as if it were a buff.
	}

	@Override
	protected boolean act() {

		if (Dungeon.level.mobCount() < Dungeon.level.mobLimit()) {

			if (Dungeon.level.spawnMob(12)){
				spend(Dungeon.level.respawnCooldown());
			} else {
				//try again in 1 turn
				spend(TICK);
			}

		} else {
			spend(Dungeon.level.respawnCooldown());
		}

		return true;
	}

	public void resetCooldown(){
		spend(-cooldown());
		spend(Dungeon.level.respawnCooldown());
	}

	public static ArrayList<Class<? extends Mob>> getMobRotation(int depth ){
		ArrayList<Class<? extends Mob>> mobs = standardMobRotation( depth );
		Random.shuffle(mobs);
		return mobs;
	}

	//returns a rotation of standard mobs, unshuffled.
	private static ArrayList<Class<? extends Mob>> standardMobRotation( int depth ){
		switch(depth){
			case 1: default:
				return new ArrayList<>(Arrays.asList(Alice.class));
		}
	}
}
