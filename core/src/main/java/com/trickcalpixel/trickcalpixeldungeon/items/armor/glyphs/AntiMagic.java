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

package com.trickcalpixel.trickcalpixeldungeon.items.armor.glyphs;

import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Charm;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Degrade;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Hex;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.MagicalSleep;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Vulnerable;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Weakness;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.abilities.duelist.ElementalStrike;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.abilities.mage.ElementalBlast;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.abilities.mage.WarpBeacon;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.CrystalWisp;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.DM100;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Eye;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Shaman;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Warlock;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.YogFist;
import com.trickcalpixel.trickcalpixeldungeon.items.armor.Armor;
import com.trickcalpixel.trickcalpixeldungeon.items.bombs.ArcaneBomb;
import com.trickcalpixel.trickcalpixeldungeon.items.bombs.HolyBomb;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.trickcalpixel.trickcalpixeldungeon.items.wands.CursedWand;
import com.trickcalpixel.trickcalpixeldungeon.items.wands.WandOfBlastWave;
import com.trickcalpixel.trickcalpixeldungeon.items.wands.WandOfDisintegration;
import com.trickcalpixel.trickcalpixeldungeon.items.wands.WandOfFireblast;
import com.trickcalpixel.trickcalpixeldungeon.items.wands.WandOfFrost;
import com.trickcalpixel.trickcalpixeldungeon.items.wands.WandOfLightning;
import com.trickcalpixel.trickcalpixeldungeon.items.wands.WandOfLivingEarth;
import com.trickcalpixel.trickcalpixeldungeon.items.wands.WandOfMagicMissile;
import com.trickcalpixel.trickcalpixeldungeon.items.wands.WandOfPrismaticLight;
import com.trickcalpixel.trickcalpixeldungeon.items.wands.WandOfTransfusion;
import com.trickcalpixel.trickcalpixeldungeon.items.wands.WandOfWarding;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.enchantments.Blazing;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.enchantments.Grim;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.enchantments.Shocking;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.missiles.darts.HolyDart;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.DisintegrationTrap;
import com.trickcalpixel.trickcalpixeldungeon.levels.traps.GrimTrap;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.Random;

import java.util.HashSet;

public class AntiMagic extends Armor.Glyph {

	private static ItemSprite.Glowing TEAL = new ItemSprite.Glowing( 0x88EEFF );
	
	public static final HashSet<Class> RESISTS = new HashSet<>();
	static {
		RESISTS.add( MagicalSleep.class );
		RESISTS.add( Charm.class );
		RESISTS.add( Weakness.class );
		RESISTS.add( Vulnerable.class );
		RESISTS.add( Hex.class );
		RESISTS.add( Degrade.class );
		
		RESISTS.add( DisintegrationTrap.class );
		RESISTS.add( GrimTrap.class );

		RESISTS.add( ArcaneBomb.class );
		RESISTS.add( HolyBomb.HolyDamage.class );
		RESISTS.add( ScrollOfRetribution.class );
		RESISTS.add( ScrollOfPsionicBlast.class );
		RESISTS.add( ScrollOfTeleportation.class );
		RESISTS.add( HolyDart.class );

		RESISTS.add( ElementalBlast.class );
		RESISTS.add( CursedWand.class );
		RESISTS.add( WandOfBlastWave.class );
		RESISTS.add( WandOfDisintegration.class );
		RESISTS.add( WandOfFireblast.class );
		RESISTS.add( WandOfFrost.class );
		RESISTS.add( WandOfLightning.class );
		RESISTS.add( WandOfLivingEarth.class );
		RESISTS.add( WandOfMagicMissile.class );
		RESISTS.add( WandOfPrismaticLight.class );
		RESISTS.add( WandOfTransfusion.class );
		RESISTS.add( WandOfWarding.Ward.class );

		RESISTS.add( ElementalStrike.class );
		RESISTS.add( Blazing.class );
		RESISTS.add( WandOfFireblast.FireBlastOnHit.class );
		RESISTS.add( Shocking.class );
		RESISTS.add( WandOfLightning.LightningOnHit.class );
		RESISTS.add( Grim.class );

		RESISTS.add( WarpBeacon.class );
		
		RESISTS.add( DM100.LightningBolt.class );
		RESISTS.add( Shaman.EarthenBolt.class );
		RESISTS.add( CrystalWisp.LightBeam.class );
		RESISTS.add( Warlock.DarkBolt.class );
		RESISTS.add( Eye.DeathGaze.class );
		RESISTS.add( YogFist.BrightFist.LightBeam.class );
		RESISTS.add( YogFist.DarkFist.DarkBolt.class );
	}
	
	@Override
	public int proc(Armor armor, Char attacker, Char defender, int damage) {
		//no proc effect, see:
		// Hero.damage
		// GhostHero.damage
		// Shadowclone.damage
		// ArmoredStatue.damage
		// PrismaticImage.damage
		return damage;
	}
	
	public static int drRoll( Char ch, int level ){
		return Random.NormalIntRange(
				Math.round(level * genericProcChanceMultiplier(ch)),
				Math.round((3 + (level*1.5f)) * genericProcChanceMultiplier(ch)));
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return TEAL;
	}

}