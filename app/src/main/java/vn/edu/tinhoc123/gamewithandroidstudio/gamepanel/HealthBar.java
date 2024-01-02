package vn.edu.tinhoc123.gamewithandroidstudio.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import vn.edu.tinhoc123.gamewithandroidstudio.GameDisplay;
import vn.edu.tinhoc123.gamewithandroidstudio.R;
import vn.edu.tinhoc123.gamewithandroidstudio.gameobject.Player;

public class HealthBar {

    private Player player;
    private int width, height, margin;
    private Paint borderPaint, healthPaint;

    public HealthBar(Context context, Player player){
        this.player = player;
        this.width = 100;
        this.height = 20;
        this.margin = 2;

        this.borderPaint = new Paint();
        int borderColor = ContextCompat.getColor(context, R.color. healthBarBorder);
        borderPaint.setColor(borderColor);
        this.healthPaint = new Paint();
        int healthColor = ContextCompat.getColor(context, R.color. healthBarHealth);
        healthPaint.setColor(healthColor);
    }
    public void draw(Canvas canvas, GameDisplay gameDisplay){

        float x = (float) player.getPositionX();
        float y = (float) player.getPositionY();
        float distanceToPlayer = 30;
        float healthPointsPercentage = (float) player.getHealthPoints()/player.MAX_HEALTH_POINTS;

        //ve khung
        float borderLeft, borderTop, borderRight,  borderBottom;
        borderLeft = x - width/2;
        borderRight = x + width/2;
        borderBottom = y - distanceToPlayer;
        borderTop = borderBottom - height;
        canvas.drawRect(
                (float) gameDisplay.gameDisplayPositionX(borderLeft),
                (float) gameDisplay.gameDisplayPositionY(borderTop),
                (float) gameDisplay.gameDisplayPositionX(borderRight),
                (float) gameDisplay.gameDisplayPositionY(borderBottom),
                borderPaint
        );


        //ve mau ( health trong khung)
        float healthLeft, healthTop, healthRight, healthBottom, healthWidth, healthHeight;
        healthWidth = width - 2*margin;
        healthHeight = height - 2*margin;
        healthLeft = borderLeft + margin;
        healthRight = healthLeft + healthWidth * healthPointsPercentage;
        healthBottom = borderBottom - margin;
        healthTop = healthBottom - healthHeight;
        canvas.drawRect(
                (float) gameDisplay.gameDisplayPositionX(healthLeft),
                (float) gameDisplay.gameDisplayPositionY(healthTop),
                (float) gameDisplay.gameDisplayPositionX(healthRight),
                (float) gameDisplay.gameDisplayPositionY(healthBottom),
                healthPaint
        );
    }

}
