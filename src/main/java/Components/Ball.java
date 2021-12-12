package Components;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Has characteristics of a ball
 */
abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

    private Color border;
    private Color inner;

    private static int speedX;
    private static int speedY;

    private double width;
    private double height;

    /**
     * Ball constructor
     * @param center = gets location of ball
     * @param radiusA = ball radius
     * @param radiusB = ball radius
     * @param inner = inner ball color
     * @param border = ball border color
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);

        setColor(inner, border);
        zeroSpeed();
    }

    /**
     * Sets speed to 0
     */
    private void zeroSpeed() {
        speedX = 0;
        speedY = 0;
    }

    /**
     * Sets color of ball
     * @param inner = inner ball color
     * @param border = border color
     */
    private void setColor(Color inner, Color border) {
        this.border = border;
        this.inner  = inner;
    }
    /**
     * Creates the ball
     * @param center = the center point of ball
     * @param radiusA = radius of ball
     * @param radiusB = radius of ball
     * @return
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * Allows ball to move
     */
    public void move(){
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        setLocation();
        setPoints(width,height);

    }

    /**
     * Allows the ball to move to calculated location
     * @param p = gets location of ball
     */
    public void moveTo(Point p){
        center.setLocation(p);
        setLocation();
    }

    /**
     * Duplicated code in move() and moveTo()
     */
    private void setLocation() {
        RectangularShape tmp = (RectangularShape) ballFace;
        width = tmp.getWidth();
        height = tmp.getHeight();

        tmp.setFrame((center.getX() -(width / 2)),(center.getY() - (height / 2)),width,height);
        ballFace = tmp;
    }

    /**
     * Set the location of ball
     * @param width
     * @param height
     */
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * Set speed of ball
     * @param x = horizontal speed
     * @param y = vertical speed
     */
    public static void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * Increases speed of ball
     * @param x = horizontal speed
     * @param y = vertical speed
     */
    public static void updateSpeed(int x, int y) {
        speedX = x;
        speedY = y;
    }

    /**
     * Set horizontal speed
     * @param s
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * Set vertical speed
     * @param s
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    public void reverseX(){
        speedX *= -1;
    }

    public void reverseY(){
        speedY *= -1;
    }

    /**
     * Get ball's border color
     * @return
     */
    public Color getBorderColor(){
        return border;
    }

    public void setBorderColor(Color border) {
        this.border = border;
    }

    /**
     * Get ball's color
     * @return
     */
    public Color getInnerColor(){
        return inner;
    }

    public void setInnerColor(Color inner) {
        this.inner = inner;
    }

    /**
     * Get ball's position
     * @return
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * Get shape of ball
     * @return
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * Returns horizontal speed
     * @return speedX
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * Returns vertical speed
     * @return speedY
     */
    public int getSpeedY(){
        return speedY;
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

}
