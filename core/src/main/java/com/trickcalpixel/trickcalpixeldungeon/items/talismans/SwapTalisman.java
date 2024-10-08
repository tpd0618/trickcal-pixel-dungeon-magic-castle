package com.trickcalpixel.trickcalpixeldungeon.items.talismans;

import com.trickcalpixel.trickcalpixeldungeon.actors.Actor;
import com.trickcalpixel.trickcalpixeldungeon.actors.Char;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.Hero;
import com.trickcalpixel.trickcalpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.trickcalpixel.trickcalpixeldungeon.mechanics.Ballistica;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSpriteSheet;

public class SwapTalisman extends Talisman {
    {
        image = ItemSpriteSheet.ANKH;
    }

    private static Ballistica throwPath;

    @Override
    public int throwPos(Hero user, int dst) {
        throwPath = new Ballistica(user.pos, dst, Ballistica.PROJECTILE);
        return throwPath.collisionPos;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar(cell);

        if (ch != null && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)) {
            cell = throwPath.path.get(throwPath.dist);
            throwPath = null;
            super.onThrow(cell);

            ch.move(curUser.pos);
            ch.sprite.move(cell, curUser.pos);

            ScrollOfTeleportation.teleportToLocation(curUser, cell);
        }
    }
}