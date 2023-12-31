package vn.edu.tinhoc123.gamewithandroidstudio.object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import vn.edu.tinhoc123.gamewithandroidstudio.GameLoop;
import vn.edu.tinhoc123.gamewithandroidstudio.Joystick;
import vn.edu.tinhoc123.gamewithandroidstudio.R;

public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 700.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final Joystick joystick;
    



    public Player(Context context,Joystick joystick, double positionX,double positionY,double radius){
        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius);
        this.joystick = joystick;
    }

    public void update() {
        //cap nhat van toc cua nguoi choi dua tren can dieu khien
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;
        //cap nhat vi tri nguoi choi di chuyen
        positionX += velocityX;
        positionY += velocityY;
    }
    public void setPosition(double positionX, double positionY) {
        this.positionX=positionX;
        this.positionY=positionY;
    }
}
