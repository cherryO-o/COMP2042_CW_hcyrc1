package Class_Testing;

import Components.Ball;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test for ball class
 */
class BallTest {

    private final Point2D Center = new Point(20, 20);
    private final Color Inner = new Color(5, 5, 5);
    private final Color Border = new Color(1, 1, 1);

    private Ball Ball;


    @BeforeEach
    void setUp() {
        Ball = new Ball(Center, 10, 10, Inner, Border) {
            @Override
            protected Shape makeBall(Point2D center, int radiusA, int radiusB) {
                double x = center.getX() - (radiusA * 0.5);
                double y = center.getY() - (radiusB * 0.5);

                return new Ellipse2D.Double(x, y, radiusA, radiusB);
            }
        };
    }

    /**
     * Test on move()
     */
    @Test
    void testMove() {
        Ball.setCenter(new Point(0,0));
        Ball.setSpeed(2,2);

        Ball.move();

        assertEquals(2,Ball.getCenter().getX());
    }

    /**
     * Test setXSpeed()
     */
    @Test
    void testSetXSpeeed(){
        Ball.setXSpeed(0);

        assertEquals(0,Ball.getSpeedX());
    }

    /**
     * Test setYSpeed()
     */
    @Test
    void testSetYSpeed() {
        Ball.setYSpeed(0);

        assertEquals(0,Ball.getSpeedY());
    }

    /**
     * Test reverseX()
     */
    @Test
    void testReverseX() {
        Ball.setXSpeed(2);
        Ball.reverseX();

        assertEquals(-2,Ball.getSpeedX());
    }

    /**
     * Test reverseY()
     */
    @Test
    void testReverseY() {
        Ball.setYSpeed(2);
        Ball.reverseY();

        assertEquals(-2,Ball.getSpeedY());
    }

    /**
     * Test getBorderColor()
     */
    @Test
    void testGetBorderColor() {
        final Color expectedResult = new Color(5, 5, 5);
        Ball.setBorderColor(expectedResult);

        final Color result = Ball.getBorderColor();

        assertEquals(expectedResult, result);
    }

    /**
     * Test getInnerColor()
     */
    @Test
    void testGetInnerColor() {
        final Color expectedResult = new Color(5, 5, 5);
        Ball.setInnerColor(expectedResult);

        final Color result = Ball.getInnerColor();

        assertEquals(expectedResult, result);
    }

    /**
     * Test getPosition()
     */
    @Test
    void testGetPosition() {
        final Point2D expectedResult = new Point(10,40);
        Ball.setCenter(new Point(10,40));

        final Point2D result = Ball.getPosition();

        assertEquals(expectedResult, result);
    }

    /**
     * Test getSpeedX()
     */
    @Test
    void testGetSpeedX() {
        assertEquals(0, Ball.getSpeedX());
    }

    /**
     * Test getSpeedY()
     */
    @Test
    void testGetSpeedY() {
        assertEquals(0, Ball.getSpeedY());
    }

    /**
     * Test setSpeed()
     */
    @Test
    void testSetSpeed() {
        int x = 0;
        int y = 0;
        Ball.setSpeed(x, y);

        assertEquals(x,Ball.getSpeedX());
        assertEquals(y,Ball.getSpeedY());
    }

    /**
     * Test updateSpeed()
     */
    @Test
    void testUpdateSpeed() {
        int x = 0;
        int y = 0;
        Ball.updateSpeed(x, x);

        assertEquals(x,Ball.getSpeedX());
        assertEquals(y,Ball.getSpeedY());
    }
}
