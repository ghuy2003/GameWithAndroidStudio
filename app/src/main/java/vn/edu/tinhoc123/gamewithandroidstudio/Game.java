package vn.edu.tinhoc123.gamewithandroidstudio;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.core.content.ContextCompat;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private Context context;

    public Game(Context context) {
        super(context);

        //view surface holder và callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);


        this.context = context;
        gameLoop = new GameLoop( this, surfaceHolder);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

//DRAW METHODS (với khung hình trên giây-FPS và cập nhật trên giây-UPS)
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawFPS(canvas);
        drawUPS(canvas);
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

    public void update() {
    }
}












