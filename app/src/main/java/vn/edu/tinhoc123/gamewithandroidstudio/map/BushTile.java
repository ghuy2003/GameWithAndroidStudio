package vn.edu.tinhoc123.gamewithandroidstudio.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import vn.edu.tinhoc123.gamewithandroidstudio.graphics.Sprite;
import vn.edu.tinhoc123.gamewithandroidstudio.graphics.SpriteSheet;

public class BushTile extends Tile {
    private final Sprite sprite;

    //bi mapLocationRect deu co trong moi Tile => constructor (Tile)
    public BushTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getBushSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
