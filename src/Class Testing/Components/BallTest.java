package Components;

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
    private final Color Inner = new Color(2, 2, 2, 2);
    private final Color Border = new Color(1, 1, 1, 1);

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
    @Test
    void testMove() {
        // Setup
        Ball.setCenter(new Point(0,0));
        Ball.setSpeed(2,2);


        // Run the test
        Ball.move();

        // Verify the results
        assertEquals(2,Ball.getCenter().getX());
    }

    @Test
    void testSetXSpeed() {
        // Setup

        // Run the test
        Ball.setXSpeed(0);

        // Verify the results
        assertEquals(0,Ball.getSpeedX());
    }

    @Test
    void testSetYSpeed() {
        // Setup
        // Run the test
        Ball.setYSpeed(0);

        // Verify the results
        assertEquals(0,Ball.getSpeedY());
    }

    @Test
    void testReverseX() {
        // Setup
        // Run the test
        Ball.setXSpeed(2);
        Ball.reverseX();

        // Verify the results
        assertEquals(-2,Ball.getSpeedX());
    }

    @Test
    void testReverseY() {
        // Setup
        // Run the test
        Ball.setYSpeed(2);
        Ball.reverseY();

        // Verify the results
        assertEquals(-2,Ball.getSpeedY());
    }

    @Test
    void testGetBorderColor() {
        // Setup
        final Color expectedResult = new Color(0, 0, 0, 0);
        Ball.setBorderColor(expectedResult);

        // Run the test
        final Color result = Ball.getBorderColor();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetInnerColor() {
        // Setup
        final Color expectedResult = new Color(0, 0, 0, 0);
        Ball.setInnerColor(expectedResult);

        // Run the test
        final Color result = Ball.getInnerColor();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetPosition() {
        // Setup
        final Point2D expectedResult = new Point(10,30);
        Ball.setCenter(new Point(10,20));

        // Run the test
        final Point2D result = Ball.getPosition();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetSpeedX() {
        assertEquals(0, Ball.getSpeedX());
    }

    @Test
    void testGetSpeedY() {
        assertEquals(0, Ball.getSpeedY());
    }

    @Test
    void testSetSpeed() {
        // Setup
        // Run the test
        int x = 0;
        int y = 0;
        Ball.setSpeed(x, y);

        // Verify the results
        assertEquals(x,Ball.getSpeedX());
        assertEquals(y,Ball.getSpeedY());
    }

    @Test
    void testUpdateSpeed() {
        // Setup
        // Run the test
        int x = 0;
        int y = 0;
        Ball.updateSpeed(x, x);

        // Verify the results
        assertEquals(x,Ball.getSpeedX());
        assertEquals(y,Ball.getSpeedY());
    }
}
