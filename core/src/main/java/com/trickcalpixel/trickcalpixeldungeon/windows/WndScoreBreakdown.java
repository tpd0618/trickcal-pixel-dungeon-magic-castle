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
import com.trickcalpixel.trickcalpixeldungeon.messages.Messages;
import com.trickcalpixel.trickcalpixeldungeon.scenes.PixelScene;
import com.trickcalpixel.trickcalpixeldungeon.ui.Icons;
import com.trickcalpixel.trickcalpixeldungeon.ui.RenderedTextBlock;
import com.trickcalpixel.trickcalpixeldungeon.ui.Window;
import com.watabou.noosa.Group;

import java.text.NumberFormat;

public class WndScoreBreakdown extends Window {

	private static final int WIDTH			= 115;

	private int GAP	= 4;

	public WndScoreBreakdown(){

		IconTitle title = new IconTitle( Icons.get(Icons.INFO), Messages.get(this, "title"));
		title.setRect(0, 0, WIDTH, 16);
		add(title);

		float pos = title.bottom()+2;

		NumberFormat num = NumberFormat.getInstance(Messages.locale());

		if (Dungeon.initialVersion <= ShatteredPixelDungeon.v1_2_3){
			pos = addInfo(this, Messages.get(this, "old_score_desc"), pos);
		}

		resize(WIDTH, (int)pos);

	}

	private float statSlot(Group parent, String label, String value, float pos, boolean highlight ) {

		RenderedTextBlock txt = PixelScene.renderTextBlock( label, 7 );
		if (highlight) txt.hardlight(Window.TITLE_COLOR);
		txt.setPos(0, pos);
		parent.add( txt );

		txt = PixelScene.renderTextBlock( value, 7 );
		if (highlight) txt.hardlight(Window.TITLE_COLOR);
		txt.setPos(WIDTH * 0.7f, pos);
		PixelScene.align(txt);
		parent.add( txt );

		return pos + GAP + txt.height();
	}

	private float addInfo(Group parent, String info, float pos){

		RenderedTextBlock txt = PixelScene.renderTextBlock( info, 5 );
		txt.maxWidth(WIDTH);
		txt.hardlight(0x999999);
		txt.setPos(0, pos-2);
		parent.add( txt );

		return pos - 2 + GAP + txt.height();

	}


}
