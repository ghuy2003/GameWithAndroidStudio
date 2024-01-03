package vn.edu.tinhoc123.gamewithandroidstudio.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import vn.edu.tinhoc123.gamewithandroidstudio.graphics.Sprite;
import vn.edu.tinhoc123.gamewithandroidstudio.graphics.SpriteSheet;

abstract public class Tile {

    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }

    public enum TileType{
        GRASS_TILE,
        BUSH_TILE,
        BUSHES_TILE,
        ROCK_TILE,
        TREE_TILE
    }

    public static Tile getTile(int indexTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {

        switch (TileType.values()[indexTileType]) {
            case GRASS_TILE:
                return new GrassTile(spriteSheet, mapLocationRect);
            case BUSH_TILE:
                return new BushTile(spriteSheet, mapLocationRect);
            case BUSHES_TILE:
                return new BushesTile(spriteSheet, mapLocationRect);
            case ROCK_TILE:
                return new RockTile(spriteSheet, mapLocationRect);
            case TREE_TILE:
                return new TreeTile(spriteSheet, mapLocationRect);
            default:
                return null;
        }

    }

    public abstract void draw(Canvas canvas);

}
