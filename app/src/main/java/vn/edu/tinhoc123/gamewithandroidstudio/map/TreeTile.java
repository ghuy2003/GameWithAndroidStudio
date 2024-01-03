
package vn.edu.tinhoc123.gamewithandroidstudio.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import vn.edu.tinhoc123.gamewithandroidstudio.graphics.Sprite;
import vn.edu.tinhoc123.gamewithandroidstudio.graphics.SpriteSheet;

public class TreeTile extends Tile {
    private final Sprite grassSprite;
    private final Sprite treeSprite;

    //bi mapLocationRect deu co trong moi Tile => constructor (Tile)
    public TreeTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        grassSprite = spriteSheet.getGrassSprite();
        treeSprite = spriteSheet.getTreeSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        grassSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
        treeSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
