/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
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

package com.trickcalpixel.trickcalpixeldungeon.items.talismans;

import com.trickcalpixel.trickcalpixeldungeon.Statistics;
import com.trickcalpixel.trickcalpixeldungeon.actors.Actor;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.Hero;
import com.trickcalpixel.trickcalpixeldungeon.items.Item;
import com.trickcalpixel.trickcalpixeldungeon.items.bags.Bag;
import com.trickcalpixel.trickcalpixeldungeon.items.bags.MagicalHolster;
import com.trickcalpixel.trickcalpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Talisman extends Item {

    {
        stackable = true;

        bones = true;

        defaultAction = AC_THROW;
        usesTargeting = true;
    }

    public boolean holster;

    public int tier;

    protected MissileWeapon parent;

    @Override
    public ArrayList<String> actions(Hero heroine) {
        ArrayList<String> actions = super.actions(heroine);
        return actions;
    }

    @Override
    public boolean collect(Bag container) {
        if (container instanceof MagicalHolster) holster = true;
        return super.collect(container);
    }

    @Override
    public void doThrow(Hero heroine) {
        parent = null;
        super.doThrow(heroine);
    }

    @Override
    protected void onThrow(int cell) {
        Char enemy = Actor.findChar(cell);
        if (enemy == null || enemy == curUser) {
            parent = null;
            Statistics.talismanUsed += 1;
            super.onThrow(cell);
        }
    }

    @Override
    public Item random() {
        if (!stackable) return this;

        quantity = 2;
        if (Random.Int(2) == 0) {
            quantity++;
            if (Random.Int(2) == 0) {
                quantity++;
            }
        }
        return this;
    }

    protected void rangedMiss(int cell) {
        parent = null;
        super.onThrow(cell);
    } //todo?

    @Override
    public boolean doPickUp(Hero heroine, int pos) {
        parent = null;
        return super.doPickUp(heroine, pos);
    }

    @Override
    public boolean isIdentified() {
        return true;
    }
    public boolean isUpgradable() {return false;}

    @Override
    public int value() {
        return 10 * quantity;
    }

    public static class PlaceHolder extends Talisman {

        {
            image = ItemSpriteSheet.BRACELET_HOLDER;
        }

        @Override
        public boolean isSimilar(Item item) {
            return item instanceof Talisman;
        }

        @Override
        public String info() {

            String info = desc();
            info += Messages.get(this, "desc");
            return info;
        }
    }
}