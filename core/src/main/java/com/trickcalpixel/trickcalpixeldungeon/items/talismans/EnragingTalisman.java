package com.trickcalpixel.trickcalpixeldungeon.items.talismans;

import com.trickcalpixel.trickcalpixeldungeon.actors.Actor;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Amok;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Buff;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSpriteSheet;

public class EnragingTalisman extends Talisman {
    {
        image = ItemSpriteSheet.ANKH;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );

        if (ch != null && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)){
            Buff.prolong(ch, Amok.class, 20);
        }
    }
}