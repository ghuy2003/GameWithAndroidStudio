package vn.edu.tinhoc123.gamewithandroidstudio.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import vn.edu.tinhoc123.gamewithandroidstudio.GameDisplay;
import vn.edu.tinhoc123.gamewithandroidstudio.GameLoop;
import vn.edu.tinhoc123.gamewithandroidstudio.gamepanel.HealthBar;
import vn.edu.tinhoc123.gamewithandroidstudio.gamepanel.Joystick;
import vn.edu.tinhoc123.gamewithandroidstudio.R;
import vn.edu.tinhoc123.gamewithandroidstudio.Utils;
import vn.edu.tinhoc123.gamewithandroidstudio.graphics.Animator;
import vn.edu.tinhoc123.gamewithandroidstudio.graphics.Sprite;

//ngay mai add design cua playera
public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 700.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    public static final int MAX_HEALTH_POINTS = 10;
    private final Joystick joystick;
    private HealthBar healthBar;
    private int healthPoints;
    private Animator animator;
    private PlayerState playerState;


    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius, Animator animator){
        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius);
        this.joystick = joystick;
        this.healthBar = new HealthBar(context,this);
        this.healthPoints = MAX_HEALTH_POINTS;

        this.animator = animator;
        this.playerState = new PlayerState(this);
    }

    public void update() {
        //cap nhat van toc cua nguoi choi dua tren can dieu khien
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;
        //cap nhat vi tri nguoi choi di chuyen
        positionX += velocityX;
        positionY += velocityY;
        if (velocityX != 0 || velocityY!= 0){
            double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX,velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }

        playerState.update();

    }


    public void setPosition(double positionX, double positionY) {
        this.positionX=positionX;
        this.positionY=positionY;
    }


    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        animator.draw(canvas, gameDisplay, this);

        healthBar.draw(canvas, gameDisplay);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        //khong duoc am mau (health)
        if (healthPoints >= 0)
            this.healthPoints = healthPoints;
    }

    public PlayerState getPlayerState(){
        return playerState;
    }
}
