package Components;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

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

//    public  boolean setImpact(Point2D point , int dir){
//        if(super.isBroken()){
//            if (speedX == 5 && speedY == -5) {
//                return false;
//            }
//            else{
//                speedX++;
//                speedY--;
//                Ball.updateSpeed(speedX,speedY);
//                return false;
//            }
//        }
//        impact();
//        return  super.isBroken();
//    }

    public void impact(){
        super.impact();
        speedX += 1;
        speedY -= 1;
        System.out.println("SpeedX: " + speedX);
        System.out.println("SpeedY: " + speedY);
        Ball.updateSpeed(speedX, speedY);

    }
}

