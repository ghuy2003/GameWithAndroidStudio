package vn.edu.tinhoc123.gamewithandroidstudio.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import vn.edu.tinhoc123.gamewithandroidstudio.GameLoop;
import vn.edu.tinhoc123.gamewithandroidstudio.R;

public class Performance {
    private GameLoop gameLoop;
    private Context context;

    public Performance(Context context, GameLoop gameLoop){
        this.context = context;
        this.gameLoop = gameLoop;
    }

    public void draw(Canvas canvas){
        drawUPS(canvas);
        drawFPS(canvas);
    }
    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.red);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: "+averageUPS, 100, 100, paint);
    }
    public void drawFPS(Canvas canvas){
        String averagePFS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();

        int color = ContextCompat.getColor(context, R.color.red);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: "+averagePFS, 100, 200, paint);
    }
}
