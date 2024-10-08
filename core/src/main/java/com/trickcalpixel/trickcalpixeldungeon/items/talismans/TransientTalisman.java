package com.trickcalpixel.trickcalpixeldungeon.items.talismans;

import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.Actor;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Buff;
import com.trickcalpixel.trickcalpixeldungeon.actors.buffs.Paralysis;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSpriteSheet;

public class TransientTalisman extends Talisman {
    {
        image = ItemSpriteSheet.ANKH;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar(cell);

        if (ch != null && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)) {
            if (ch != Dungeon.hero) {
                if (Actor.findChar(Dungeon.level.exit()) == null) {
                    ScrollOfTeleportation.teleportToLocation(ch, Dungeon.level.exit());
                    Buff.prolong(ch, Paralysis.class, Paralysis.DURATION * 10f);
                }
            }
        }
    }

    @Override
    public String info() {

        String info = desc();

        if (Actor.findChar(Dungeon.level.exit()) != null) {
            info += "\n\n" + Messages.get(this, "feel");
        }
        return info;
    }
}