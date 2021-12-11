package Components;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 *Has characteristics of a brick
 */
abstract public class Brick  {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;


    private static Random rnd;

    private String name;
    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;

    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /**
     * Return random value
     * @return
     */
    public static Random getRND() {
        return rnd;
    }

    /**
     * Create the brick
     * @param pos = brick position
     * @param size = brick size
     * @return
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * Check if brick is broken
     * @param point
     * @param dir
     * @return
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    public abstract Shape getBrick();

    public Color getBorderColor(){
        return  border;
    }

    public Color getInnerColor(){
        return inner;
    }


    /**
     * Find the location of impact made by ball
     * @param b = the ball
     * @return
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    public final boolean isBroken(){
        return broken;
    }

    /**
     * Resets brick strength and redraw destroyed bricks
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     *Decreases strength of brick & updates state of brick (broken or not)
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    public Shape getBrickFace() {
        return brickFace;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int i) {
        strength = i;
    }
}





