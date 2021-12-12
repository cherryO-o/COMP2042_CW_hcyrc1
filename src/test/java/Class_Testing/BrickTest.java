package Class_Testing;

import Components.Ball;
import Components.Brick;
import Components.RubberBall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;


import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test for brick class
 */
class BrickTest {

    private final Point testBrickPos = new Point (40,40);
    private final Dimension size = new Dimension(20, 10);
    private final Color border = Color.GRAY;
    private final Color inner  = new Color(178, 34, 34).darker();
    private final String name = "Clay Brick";

    private Brick brick;

    /**
     * Create a brick to use for testing
     */
    @BeforeEach
    void setUp() {
        brick = new Brick(name, testBrickPos, size, border,inner,10) {
            @Override
            protected Shape makeBrickFace(Point pos, Dimension size) {
                return new Rectangle(pos,size);
            }

            @Override
            public Shape getBrick() {
                return brick.getBrickFace();
            }
        };
    }

    /**
     * Test setImpact()
     */
    @Test
    void testSetImpact() {
        assertFalse(brick.setImpact(testBrickPos, 1));
    }

    /**
     * Test getBorderColor()
     */
    @Test
    void testGetBorderColor() {

        final Color expectedResult = Color.GRAY;

        final Color result = brick.getBorderColor();

        assertEquals(expectedResult, result);
    }

    /**
     * Test getInnerColor()
     */
    @Test
    void testGetInnerColor() {

        final Color expectedResult = new Color(178, 34, 34).darker();

        final Color result = brick.getInnerColor();

        assertEquals(expectedResult, result);
    }

    /**
     * Test findImpact() on left impact
     */
    @Test
    void testFindImpactWhenBallHitsLeftSideOfBrick() {
        Ball ball = new RubberBall(new Point(35,40));

        int result = brick.findImpact(ball);

        assertEquals(Brick.LEFT_IMPACT, result);
    }

    /**
     * Test findImpact() on right impact
     */
    @Test
    void testFindImpactWhenBallHitsRightSideOfBrick(){
        Ball ball = new RubberBall(new Point(55, 40));

        int result = brick.findImpact(ball);

        assertEquals(Brick.RIGHT_IMPACT, result);
    }

    /**
     * Test findImpact() on up impact
     */
    @Test
    void testFindImpactWhenBallHitsTopOfBrick(){
        Ball ball = new RubberBall(new Point(45,35));

        int result = brick.findImpact(ball);

        assertEquals(Brick.UP_IMPACT, result);
    }

    /**
     * Test findImpact() on down impact
     */
    @Test
    void testFindImpactWhenBallHitsBottomOfBrick(){
        Ball ball = new RubberBall(new Point(45, 50));

        int result = brick.findImpact(ball);

        assertEquals(Brick.DOWN_IMPACT, result);

    }

    /**
     * Test repair()
     */
    @Test
    void testRepair() {
        brick.impact();
        brick.repair();

        assertEquals(10, brick.getStrength());
    }

    /**
     * Test of the ball made impact on brick
     */
    @Test
    void testImpactWhenNotAboutToBreak() {

        int expected = brick.getStrength() - 1;
        brick.impact();

        assertEquals(expected,brick.getStrength());
    }

    /**
     * Test of the brick breaking
     */
    @Test
    void testImpactWhenAboutToBreak() {

        int expected = 0;
        brick.setStrength(1);
        brick.impact();

        assertEquals(expected, brick.getStrength());
    }
}