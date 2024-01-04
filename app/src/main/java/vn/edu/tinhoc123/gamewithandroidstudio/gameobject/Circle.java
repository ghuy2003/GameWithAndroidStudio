package vn.edu.tinhoc123.gamewithandroidstudio.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import vn.edu.tinhoc123.gamewithandroidstudio.GameDisplay;

public abstract class Circle extends GameObject {
    protected double radius;
    protected Paint paint;
    public Circle(Context context,int color, double positionX, double positionY,double radius) {
        super(positionX, positionY);

        this.radius = radius;

        paint = new Paint();
        paint.setColor(color);
    }


    //kiểm tra va chạm giữa 2 hình tròn
    public static boolean isColliding(Circle obj1, Circle obj2) {
        double distance = getDistanceBetweenObjects(obj1,obj2);
        double distanceToCollision = obj1.getRadius() + obj2.getRadius();
        if (distance < distanceToCollision)
            return true;
        else
            return false;
    }

    private double getRadius() {
        return radius;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawCircle(
                (float)gameDisplay.gameDisplayPositionX(positionX),
                (float)gameDisplay.gameDisplayPositionY(positionY),
                (float)radius,
                paint
        );
    }
}
