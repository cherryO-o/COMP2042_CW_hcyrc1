package Components;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class RubberBallTest {

    @Test
    void makeBall() {
        Point2D center = new Point2D.Double(6.0,8.0);
        int radiusA = 10;
        int radiusB = 10;

        int x = (int)center.getX() - (radiusA / 2);
        int y = (int)center.getY() - (radiusB / 2);

        assertEquals(1,x);
        assertEquals(3,y);
    }
}