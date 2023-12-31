package vn.edu.tinhoc123.gamewithandroidstudio;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vn.edu.tinhoc123.gamewithandroidstudio.object.Circle;
import vn.edu.tinhoc123.gamewithandroidstudio.object.Enemy;
import vn.edu.tinhoc123.gamewithandroidstudio.object.Player;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Joystick joystick;
    //private final Enemy enemy;
    private GameLoop gameLoop;
    private List<Enemy> enemyList = new ArrayList<Enemy>();


    //player touch event (ctrlshiftA)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //dieu khien khi cham (touch)
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(joystick.isPressed((double) event.getX(),(double) event.getY())){
                    joystick.setIsPressed(true);
                }
                return true;

                //giu~ de dieu khien(hold)
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed()) {
                    joystick.setActuator((double) event.getX(), (double) event.getY());

                }
                return true;
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
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

        //nut dieu khien nguoi choi
        joystick = new Joystick(300,800,120,60);

        //khoi tao nguoi choi (set vi tri xuat hien ban dau tren man hinh)
        player = new Player(getContext(),joystick ,1000, 500, 30);

        //khoi tao ke dich
        //enemy = new Enemy(getContext(),player ,500, 200, 30);

        
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


        joystick.draw(canvas);
        player.draw(canvas);

        for (Enemy enemy : enemyList){
            enemy.draw(canvas);
        }
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
        joystick.update();


        if (Enemy.readyToSpawn()){
            enemyList.add(new Enemy(getContext(),player));
        }
        for (Enemy enemy : enemyList){
            enemy.update();
        }

        Iterator<Enemy> iteratorEnemy = enemyList.iterator();
        while (iteratorEnemy.hasNext()){
            if (Circle.isColliding(iteratorEnemy.next(),player)){
                iteratorEnemy.remove();
            }
        }

    }
}












