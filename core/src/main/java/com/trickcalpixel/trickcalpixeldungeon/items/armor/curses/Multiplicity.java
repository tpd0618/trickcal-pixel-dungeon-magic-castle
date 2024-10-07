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

package com.trickcalpixel.trickcalpixeldungeon.items.armor.curses;

import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.Actor;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.PinCushion;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.Hero;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.abilities.Ratmogrify;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.DwarfKing;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Mimic;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Mob;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Statue;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.Thief;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.npcs.MirrorImage;
import com.trickcalpixel.trickcalpixeldungeon.actors.mobs.npcs.NPC;
import com.trickcalpixel.trickcalpixeldungeon.items.armor.Armor;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.trickcalpixel.trickcalpixeldungeon.scenes.GameScene;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;

public class Multiplicity extends Armor.Glyph {

	private static ItemSprite.Glowing BLACK = new ItemSprite.Glowing( 0x000000 );

	@Override
	public int proc(Armor armor, Char attacker, Char defender, int damage) {

		float procChance = 1/20f * procChanceMultiplier(defender);
		if ( Random.Float() < procChance ) {
			ArrayList<Integer> spawnPoints = new ArrayList<>();

			for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
				int p = defender.pos + PathFinder.NEIGHBOURS8[i];
				if (Actor.findChar( p ) == null && (Dungeon.level.passable[p] || Dungeon.level.avoid[p])) {
					spawnPoints.add( p );
				}
			}

			if (spawnPoints.size() > 0) {

				Mob m = null;
				if (Random.Int(2) == 0 && defender instanceof Hero){
					m = new MirrorImage();
					((MirrorImage)m).duplicate( (Hero)defender );

				} else {
					Char toDuplicate = attacker;

					if (toDuplicate instanceof Ratmogrify.TransmogRat){
						toDuplicate = ((Ratmogrify.TransmogRat)attacker).getOriginal();
					}

					//FIXME should probably have a mob property for this
					if (!(toDuplicate instanceof Mob)
							|| toDuplicate.properties().contains(Char.Property.BOSS) || toDuplicate.properties().contains(Char.Property.MINIBOSS)
							|| toDuplicate instanceof Mimic || toDuplicate instanceof Statue || toDuplicate instanceof NPC) {
						m = Dungeon.level.createMob();
					} else {
						Actor.fixTime();

						m = (Mob)Reflection.newInstance(toDuplicate.getClass());
						
						if (m != null) {
							
							Bundle store = new Bundle();
							attacker.storeInBundle(store);
							m.restoreFromBundle(store);
							m.pos = 0;
							m.HP = m.HT;

							//don't duplicate stuck projectiles
							m.remove(m.buff(PinCushion.class));
							//don't duplicate pending damage to dwarf king
							m.remove(DwarfKing.KingDamager.class);
							
							//If a thief has stolen an item, that item is not duplicated.
							if (m instanceof Thief) {
								((Thief) m).item = null;
							}
						}
					}
				}

				if (m != null) {

					if (Char.hasProp(m, Char.Property.LARGE)){
						for ( int i : spawnPoints.toArray(new Integer[0])){
							if (!Dungeon.level.openSpace[i]){
								//remove the value, not at the index
								spawnPoints.remove((Integer) i);
							}
						}
					}

					if (!spawnPoints.isEmpty()) {
						m.pos = Random.element(spawnPoints);
						GameScene.add(m);
						ScrollOfTeleportation.appear(m, m.pos);
					}
				}

			}
		}

		return damage;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return BLACK;
	}

	@Override
	public boolean curse() {
		return true;
	}
}
