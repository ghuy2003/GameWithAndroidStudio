package vn.edu.tinhoc123.gamewithandroidstudio;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vn.edu.tinhoc123.gamewithandroidstudio.gameobject.Circle;
import vn.edu.tinhoc123.gamewithandroidstudio.gameobject.Enemy;
import vn.edu.tinhoc123.gamewithandroidstudio.gameobject.Player;
import vn.edu.tinhoc123.gamewithandroidstudio.gameobject.Spell;
import vn.edu.tinhoc123.gamewithandroidstudio.gamepanel.GameOver;
import vn.edu.tinhoc123.gamewithandroidstudio.gamepanel.Joystick;
import vn.edu.tinhoc123.gamewithandroidstudio.gamepanel.Performance;
import vn.edu.tinhoc123.gamewithandroidstudio.graphics.Animator;
import vn.edu.tinhoc123.gamewithandroidstudio.graphics.SpriteSheet;
import vn.edu.tinhoc123.gamewithandroidstudio.map.Tilemap;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Joystick joystick;
    private final Tilemap tilemap;
    //private final Enemy enemy;
    private GameLoop gameLoop;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Spell> spellList = new ArrayList<Spell>();
    private int joystickPointerID = 0;
    private int numberOfSpellsToCast = 0;
    private GameOver gameOver;
    private Performance performance;
    private GameDisplay gameDisplay;



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

        gameOver = new GameOver(context);
        //nut dieu khien nguoi choi
        joystick = new Joystick(300,800,120,60);

        performance = new Performance(context, gameLoop);


        SpriteSheet spriteSheet = new SpriteSheet(context);
        Animator animator = new Animator(spriteSheet.getPlayerSpriteArray());

        //khoi tao nguoi choi (set vi tri xuat hien ban dau tren man hinh)
        player = new Player(context,joystick ,1000, 500, 32, animator);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);


        tilemap = new Tilemap(spriteSheet);

        setFocusable(true);
    }






    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (gameLoop.getState().equals(Thread.State.TERMINATED)){
            gameLoop = new GameLoop(this, holder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("Game.java", "surfaceChanged()");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceDestroyed()");
    }




//DRAW METHODS (với khung hình trên giây-FPS và cập nhật trên giây-UPS)
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //draw tilemap
        tilemap.draw(canvas, gameDisplay);

        joystick.draw(canvas);
        performance.draw(canvas);
        player.draw(canvas, gameDisplay);

        for (Enemy enemy : enemyList){
            enemy.draw(canvas, gameDisplay);
        }
        for (Spell spell : spellList){
            spell.draw(canvas, gameDisplay);
        }

        //ve ra man hinh chu "Game Over" neu mau <= 0
        if (player.getHealthPoints() <= 0 ){
            gameOver.draw(canvas);
        }


    }



    public void update() {

        //tam dung update game khi "gameOver" (tam dung moi game object khoi update)
        if (player.getHealthPoints() <= 0){
            return;
        }

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

        gameDisplay.update();

    }

    public void pause() {
        gameLoop.stopLoop();
    }
}












