package vn.edu.tinhoc123.gamewithandroidstudio.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import vn.edu.tinhoc123.gamewithandroidstudio.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 64;
    private static final int SPRITE_HEIGHT_PIXELS = 64;
    private Bitmap bitmap;

    public SpriteSheet(Context context){
        //duong dan den hinh anh
        BitmapFactory.Options bitmapOptions =new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }

    public Sprite[] getPlayerSpriteArray(){
        Sprite[] spriteArray = new Sprite[4];
        spriteArray[0] = new Sprite(this, new Rect(0*64, 0, 1*64, 64));
        spriteArray[1] = new Sprite(this, new Rect(1*64, 0, 2*64, 64));
        spriteArray[2] = new Sprite(this, new Rect(2*64, 0, 3*64, 64));
        spriteArray[3] = new Sprite(this, new Rect(3*64, 0, 4*64, 64));
        return spriteArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getGrassSprite() {
        return getSpriteByIndex(1, 0);
    }
    public Sprite getBushSprite() {
        return getSpriteByIndex(1, 1);
    }public Sprite getBushesSprite() {
        return getSpriteByIndex(1, 2);
    }public Sprite getRockSprite() {
        return getSpriteByIndex(1, 3);
    }public Sprite getTreeSprite() {
        return getSpriteByIndex(1, 4);
    }





    private Sprite getSpriteByIndex(int idxRow, int idxCol) {
        return new Sprite(this, new Rect(
                idxCol*SPRITE_WIDTH_PIXELS,
                idxRow*SPRITE_HEIGHT_PIXELS,
                idxCol*SPRITE_WIDTH_PIXELS + SPRITE_WIDTH_PIXELS,
                idxRow*SPRITE_HEIGHT_PIXELS + SPRITE_HEIGHT_PIXELS
        ));
    }
}
