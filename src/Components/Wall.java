/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Components;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class Wall {

    private Random rnd;
    private Rectangle area;

    public Brick[] bricks;
    public Ball ball;
    public Player player;
    private Levels wallLevel = new Levels();

    private Brick[][] levels;
    private int level;

    private Point startPoint;
    private static int brickCount;
    private static int ballCount;
    private static boolean ballLost;

    //for score and highscore
    static int score;
    public int levelScore[] = new int[6];

    int i;
    int count = 0;

    /**
     *
     * @param drawArea = the area where the bricks are drawn
     * @param brickCount = number of bricks for each level
     * @param lineCount = lines between bricks
     * @param brickDimensionRatio = size of brick
     * @param ballPos = position of ball
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        levels = wallLevel.makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        makeBall(ballPos);
        int speedX,speedY;
        speedX = 3;
        speedY = -3;

        ball.setSpeed(speedX,speedY);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;
        i=0;

    }

    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }

    public void move(){
        player.move();
        ball.move();
    }

    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            brickCount--;

            if(count == 1) {
                score+=10;
                count = 0;
            }
            else {
                score += 10;
//                score += count;
                  count = 0;
            }
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    public void resetScore() {
        score = 0;
    }

    /**
     *
     * @return
     */
    private boolean impactWall(){
        for(int i = 0; i<bricks.length; i++){
            Brick b = bricks[i];
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    addScore();
                    return b.setImpact(ball.down, Crack.UP);

                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    addScore();
                    return b.setImpact(ball.up, Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    addScore();
                    return b.setImpact(ball.right, Crack.RIGHT);

                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    addScore();
                    return b.setImpact(ball.left, Crack.LEFT);
            }
        }
        return false;
    }

    private void addScore() {
        count++;
        score += count;
        count = 0;
    }

    public void highScore() {
        int score = getScore();
        if(i < 6){
            levelScore[i] = score;
        }
        i++;
    }

    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    public static int getBrickCount(){
        return brickCount;
    }

    public static int getBallCount(){
        return ballCount;
    }

    public static boolean isBallLost(){
        return ballLost;
    }

    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
        speedX = 3;
        speedY = -3;
        ball.setSpeed(speedX,speedY);
        ballLost = false;
    }

    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    public boolean isDone(){
        return brickCount == 0;
    }

    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    public boolean hasLevel(){
        return level < levels.length;
    }

    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    public static void resetBallCount(){
        ballCount = 3;
    }

    public static void ballLost(boolean Lost){
        ballLost = Lost;
    }

    public static void setBrickCount(int count) {
        brickCount = count;
    }

    public static int getScore() {
        return score;
    }
}
