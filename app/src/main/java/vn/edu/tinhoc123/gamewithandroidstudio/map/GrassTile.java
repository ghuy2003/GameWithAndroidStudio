package vn.edu.tinhoc123.gamewithandroidstudio.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import vn.edu.tinhoc123.gamewithandroidstudio.graphics.Sprite;
import vn.edu.tinhoc123.gamewithandroidstudio.graphics.SpriteSheet;

public class GrassTile extends Tile {
    private final Sprite sprite;

    //bi mapLocationRect deu co trong moi Tile => constructor (Tile)
    public GrassTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getGrassSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
