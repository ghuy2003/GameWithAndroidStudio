package vn.edu.tinhoc123.gamewithandroidstudio;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.core.content.ContextCompat;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private GameLoop gameLoop;


    //player touch event (ctrlshiftA)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //dieu khien khi cham (touch)
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                player.setPosition((double) event.getX(),(double) event.getY());
                return true;

                //giu~ de dieu khien(hold)
            case MotionEvent.ACTION_MOVE:
                player.setPosition((double) event.getX(),(double) event.getY());
                return true;
        }

        return super.onTouchEvent(event);
    }






    public Game(Context context) {
        super(context);

        //view surface holder và callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop( this, surfaceHolder);

        //khoi tao nguoi choi
        player = new Player(getContext(), 1000, 500, 30);

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

        player.draw(canvas);

    }
    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.red);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: "+averageUPS, 100, 100, paint);
    }
    public void drawFPS(Canvas canvas){
        String averagePFS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();

        int color = ContextCompat.getColor(getContext(), R.color.red);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: "+averagePFS, 100, 200, paint);
    }

    public void update() {
        player.update();
    }
}












