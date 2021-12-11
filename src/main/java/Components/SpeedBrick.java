package Components;

import java.awt.*;
import java.awt.Point;

/**
 * Newly created brick type
 */
public class SpeedBrick extends Brick{

    private static final String NAME = "Speed Brick";
    private static final Color DEF_INNER = new Color(102,178,225).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int SPEED_STRENGTH = 1;
    private int speedX = 2;
    private int speedY = -2;

    public SpeedBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,SPEED_STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return super.brickFace;
    }

    /**
     * Increases speed of ball upon impact on this brick
     */
    public void impact(){
        super.impact();
        speedX += 1;
        speedY -= 1;
        Ball.updateSpeed(speedX, speedY);

    }
}

