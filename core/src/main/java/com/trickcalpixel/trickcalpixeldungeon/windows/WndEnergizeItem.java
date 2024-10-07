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

package com.trickcalpixel.trickcalpixeldungeon.windows;

import com.trickcalpixel.trickcalpixeldungeon.Dungeon;
import com.trickcalpixel.trickcalpixeldungeon.ShatteredPixelDungeon;
import com.trickcalpixel.trickcalpixeldungeon.actors.hero.Hero;
import com.trickcalpixel.trickcalpixeldungeon.items.EnergyCrystal;
import com.trickcalpixel.trickcalpixeldungeon.items.EquipableItem;
import com.trickcalpixel.trickcalpixeldungeon.items.Item;
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.trickcalpixel.trickcalpixeldungeon.scenes.AlchemyScene;
import com.trickcalpixel.trickcalpixeldungeon.scenes.GameScene;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSprite;
import com.trickcalpixel.trickcalpixeldungeon.sprites.ItemSpriteSheet;
import com.trickcalpixel.trickcalpixeldungeon.ui.RedButton;
import com.trickcalpixel.trickcalpixeldungeon.utils.GLog;

public class WndEnergizeItem extends WndInfoItem {

	private static final float GAP		= 2;
	private static final int BTN_HEIGHT	= 18;

	private WndBag owner;

	public WndEnergizeItem(Item item, WndBag owner) {
		super(item);

		this.owner = owner;

		float pos = height;

		if (item.quantity() == 1) {

			RedButton btnEnergize = new RedButton( Messages.get(this, "energize", item.energyVal()) ) {
				@Override
				protected void onClick() {
					energizeAll( item );
					hide();
				}
			};
			btnEnergize.setRect( 0, pos + GAP, width, BTN_HEIGHT );
			btnEnergize.icon(new ItemSprite(ItemSpriteSheet.ENERGY));
			add( btnEnergize );

			pos = btnEnergize.bottom();

		} else {

			int energyAll = item.energyVal();
			RedButton btnEnergize1 = new RedButton( Messages.get(this, "energize_1", energyAll / item.quantity()) ) {
				@Override
				protected void onClick() {
					energizeOne( item );
					hide();
				}
			};
			btnEnergize1.setRect( 0, pos + GAP, width, BTN_HEIGHT );
			btnEnergize1.icon(new ItemSprite(ItemSpriteSheet.ENERGY));
			add( btnEnergize1 );
			RedButton btnEnergizeAll = new RedButton( Messages.get(this, "energize_all", energyAll ) ) {
				@Override
				protected void onClick() {
					energizeAll( item );
					hide();
				}
			};
			btnEnergizeAll.setRect( 0, btnEnergize1.bottom() + 1, width, BTN_HEIGHT );
			btnEnergizeAll.icon(new ItemSprite(ItemSpriteSheet.ENERGY));
			add( btnEnergizeAll );

			pos = btnEnergizeAll.bottom();

		}

		resize( width, (int)pos );

	}

	@Override
	public void hide() {

		super.hide();

		if (owner != null) {
			owner.hide();
			openItemSelector();
		}
	}

	public static void energizeAll(Item item ) {

		if (item.isEquipped( Dungeon.hero ) && !((EquipableItem)item).doUnequip( Dungeon.hero, false )) {
			return;
		}
		item.detachAll( Dungeon.hero.belongings.backpack );
		energize(item);
	}

	public static void energizeOne( Item item ) {

		if (item.quantity() <= 1) {
			energizeAll( item );
		} else {
			energize(item.detach( Dungeon.hero.belongings.backpack ));
		}
	}

	private static void energize(Item item){
		Hero hero = Dungeon.hero;

		if (ShatteredPixelDungeon.scene() instanceof AlchemyScene){

			Dungeon.energy += item.energyVal();
			((AlchemyScene) ShatteredPixelDungeon.scene()).createEnergy();
			if (!item.isIdentified()){
				((AlchemyScene) ShatteredPixelDungeon.scene()).showIdentify(item);
			}

		} else {

			//energizing items doesn't spend time
			hero.spend(-hero.cooldown());
			new EnergyCrystal(item.energyVal()).doPickUp(hero);
			item.identify();
			GLog.h("You energized: " + item.name());

		}
	}

	public static WndBag openItemSelector(){
		if (ShatteredPixelDungeon.scene() instanceof GameScene) {
			return GameScene.selectItem( selector );
		} else {
			WndBag window = WndBag.getBag( selector );
			ShatteredPixelDungeon.scene().addToFront(window);
			return window;
		}
	}

	public static WndBag.ItemSelector selector = new WndBag.ItemSelector() {
		@Override
		public String textPrompt() {
			return Messages.get(WndEnergizeItem.class, "prompt");
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item.energyVal() > 0;
		}

		@Override
		public void onSelect(Item item) {
			if (item != null) {
				WndBag parentWnd = openItemSelector();
				if (ShatteredPixelDungeon.scene() instanceof GameScene) {
					GameScene.show(new WndEnergizeItem(item, parentWnd));
				} else {
					ShatteredPixelDungeon.scene().addToFront(new WndEnergizeItem(item, parentWnd));
				}
			}
		}
	};

}
