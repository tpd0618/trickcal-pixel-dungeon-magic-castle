package com.trickcalpixel.trickcalpixeldungeon.items.talismans;

import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.Actor;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Buff;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Speed;
import com.trickcalpixel.trickcalpixeldungeon.effects.Speck;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSpriteSheet;

public class JoanTalisman extends Talisman {
    {
        image = ItemSpriteSheet.ANKH;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar( cell );
        if (ch != null) {
            for (Buff bch : ch.buffs()) {
                if (bch.type == Buff.buffType.POSITIVE && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)) {
                    bch.detach();
                    curUser.HP = Math.min(curUser.HP + 8000, curUser.HT);
                    Dungeon.hero.sprite.emitter().start(Speck.factory(Speck.HEALING), 0.2f, 3);
                    Buff.prolong(curUser, Speed.class, Speed.DURATION);
                }
            }
        }
    }
}