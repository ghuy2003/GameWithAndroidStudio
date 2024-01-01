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
import vn.edu.tinhoc123.gamewithandroidstudio.object.Spell;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Joystick joystick;
    //private final Enemy enemy;
    private GameLoop gameLoop;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Spell> spellList = new ArrayList<Spell>();
    private int joystickPointerID = 0;
    private int numberOfSpellsToCast = 0;


    //player touch event (ctrlshiftA)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //dieu khien khi cham (touch)
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if(joystick.getIsPressed()) {
                    numberOfSpellsToCast++;
                } else if (joystick.isPressed((double) event.getX(),(double) event.getY())){
                    joystickPointerID = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                }else {
                    numberOfSpellsToCast++;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joystick.getIsPressed()){
                    joystick.setActuator((double) event.getX(),(double) event.getY());
                } return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (joystickPointerID == event.getPointerId(event.getActionIndex())){
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }
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
        for (Spell spell : spellList){
            spell.draw(canvas);
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
        while (numberOfSpellsToCast > 0){
            spellList.add(new Spell(getContext(),player));
            numberOfSpellsToCast --;
        }
        for (Enemy enemy : enemyList){
            enemy.update();
        }
        for (Spell spell : spellList){
            spell.update();
        }

        Iterator<Enemy> iteratorEnemy = enemyList.iterator();
        while (iteratorEnemy.hasNext()){
            Circle enemy = iteratorEnemy.next();
            if (Circle.isColliding(enemy, player)){
                iteratorEnemy.remove();
                player.setHealthPoints(player.getHealthPoints() - 1);
                continue;
            }




            Iterator<Spell> iteratorSpell = spellList.iterator();
            while (iteratorSpell.hasNext()){
                Circle spell = iteratorSpell.next();
                if (Circle.isColliding(spell, enemy)){
                    iteratorSpell.remove();
                    iteratorEnemy.remove();
                    break;
                }
            }
        }

    }
}












