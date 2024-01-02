package vn.edu.tinhoc123.gamewithandroidstudio;

import vn.edu.tinhoc123.gamewithandroidstudio.gameobject.GameObject;

public class GameDisplay {
    private double gameDisplayPositionOffsetX;
    private double gameDisplayPositionOffsetY;
    private double displayCenterX;
    private double gameCenterX;
    private double displayCenterY;
    private double gameCenterY;
    private GameObject centerObject;

    public GameDisplay(int widthPixels, int heightPixels, GameObject centerObject){
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
}
