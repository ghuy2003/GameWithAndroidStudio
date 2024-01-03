package vn.edu.tinhoc123.gamewithandroidstudio;

import android.graphics.Rect;

import vn.edu.tinhoc123.gamewithandroidstudio.gameobject.GameObject;

public class GameDisplay {
    public final Rect DISPLAY_RECT;
    private final int heightPixels;
    private final int widthPixels;
    private double gameDisplayPositionOffsetX;
    private double gameDisplayPositionOffsetY;
    private double displayCenterX;
    private double gameCenterX;
    private double displayCenterY;
    private double gameCenterY;
    private GameObject centerObject;

    public GameDisplay(int widthPixels, int heightPixels, GameObject centerObject){
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;

        DISPLAY_RECT = new Rect(0, 0, widthPixels, heightPixels);

        this.centerObject = centerObject;


        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;
    }

    public void update(){
        gameCenterX = centerObject.getPositionX();
        gameCenterY = centerObject.getPositionY();

        gameDisplayPositionOffsetX = displayCenterX - gameCenterX;
        gameDisplayPositionOffsetY = displayCenterY - gameCenterY;
    }

    public double gameDisplayPositionX(double x) {
        return x + gameDisplayPositionOffsetX;
    }

    public double gameDisplayPositionY(double y) {
        return y + gameDisplayPositionOffsetY;
    }

    public Rect getGameRect() {
        return new Rect(
                (int) (gameCenterX - widthPixels/2),
                (int) (gameCenterY - heightPixels/2),
                (int) (gameCenterX + widthPixels/2),
                (int) (gameCenterY + heightPixels/2)
        );
    }
}
