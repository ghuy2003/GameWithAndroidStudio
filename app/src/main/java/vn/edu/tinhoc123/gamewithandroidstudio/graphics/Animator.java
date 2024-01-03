package vn.edu.tinhoc123.gamewithandroidstudio.graphics;

import android.graphics.Canvas;

import vn.edu.tinhoc123.gamewithandroidstudio.GameDisplay;
import vn.edu.tinhoc123.gamewithandroidstudio.gameobject.Player;
import vn.edu.tinhoc123.gamewithandroidstudio.gameobject.PlayerState;

public class Animator {
    private Sprite[] playerSpriteArray;
    private int indexNotDiChuyenFrame = 0;
    private int indexDiChuyenFrame = 1;
    private int updateFrame;
    private static final int MAX_UPDATE_FRAME = 5;

    public Animator(Sprite[] playerSpriteArray) {
        this.playerSpriteArray = playerSpriteArray;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {
        switch (player.getPlayerState().getState()){
            case NOT_DICHUYEN:
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[indexNotDiChuyenFrame]);
                break;
            case STARTED_DICHUYEN:
                updateFrame = MAX_UPDATE_FRAME;
                drawFrame(canvas,gameDisplay,player,playerSpriteArray[indexDiChuyenFrame]);
                break;
            case IS_DICHUYEN:
                updateFrame--;
                if (updateFrame == 0){
                    updateFrame = MAX_UPDATE_FRAME;
                    toggleIndexDiChuyenFrame();
                }
                drawFrame(canvas,gameDisplay,player,playerSpriteArray[indexDiChuyenFrame]);
                break;
            default:
                break;
        }
    }

    private void toggleIndexDiChuyenFrame() {
        if(indexDiChuyenFrame == 1)
            indexDiChuyenFrame = 2;
        else
            indexDiChuyenFrame = 1;
    }

    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Player player, Sprite sprite){
        //thay nhan vat tu circle thanh sprite
        sprite.draw(
                canvas,
                (int) gameDisplay.gameDisplayPositionX(player.getPositionX()) - sprite.getWidth()/2,
                (int) gameDisplay.gameDisplayPositionY(player.getPositionY()) - sprite.getHeight()/2
        );
    }
}
