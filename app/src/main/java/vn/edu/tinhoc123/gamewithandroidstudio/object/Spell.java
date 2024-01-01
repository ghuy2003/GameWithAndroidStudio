package vn.edu.tinhoc123.gamewithandroidstudio.object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import vn.edu.tinhoc123.gamewithandroidstudio.GameLoop;
import vn.edu.tinhoc123.gamewithandroidstudio.R;

public class Spell extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 700.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    public Spell(Context context, Player spellcaster) {
        super(
                context,
                ContextCompat.getColor(context, R.color.spell),
                spellcaster.getPositionX(),
                spellcaster.getPositionY(),

                25
        );
        velocityX = spellcaster.getDirectionX()*MAX_SPEED;
        velocityY = spellcaster.getDirectionY()*MAX_SPEED;

    }

    @Override
    public void update() {
        positionX += velocityX;
        positionY += velocityY;
    }
}
