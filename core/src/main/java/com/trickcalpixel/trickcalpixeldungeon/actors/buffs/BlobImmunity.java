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

package com.trickcalpixel.trickcalpixeldungeon.actors.buffs;

import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.Blizzard;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.ConfusionGas;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.CorrosiveGas;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.Electricity;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.Fire;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.Freezing;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.Inferno;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.ParalyticGas;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.Regrowth;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.SmokeScreen;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.StenchGas;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.StormCloud;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.ToxicGas;
import com.trickcalpixel.trickcalpixeldungeon.actors.blobs.Web;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Tengu;
import com.trickcalpixel.trickcalpixeldungeon.levels.rooms.special.MagicalFireRoom;
import com.trickcalpixel.trickcalpixeldungeon.ui.BuffIndicator;

public class BlobImmunity extends FlavourBuff {
	
	{
		type = buffType.POSITIVE;
	}
	
	public static final float DURATION	= 20f;
	
	@Override
	public int icon() {
		return BuffIndicator.IMMUNITY;
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (DURATION - visualcooldown()) / DURATION);
	}

	{
		//all harmful blobs
		immunities.add( Blizzard.class );
		immunities.add( ConfusionGas.class );
		immunities.add( CorrosiveGas.class );
		immunities.add( Electricity.class );
		immunities.add( Fire.class );
		immunities.add( MagicalFireRoom.EternalFire.class );
		immunities.add( Freezing.class );
		immunities.add( Inferno.class );
		immunities.add( ParalyticGas.class );
		immunities.add( Regrowth.class );
		immunities.add( SmokeScreen.class );
		immunities.add( StenchGas.class );
		immunities.add( StormCloud.class );
		immunities.add( ToxicGas.class );
		immunities.add( Web.class );

		immunities.add(Tengu.FireAbility.FireBlob.class);
	}

}
