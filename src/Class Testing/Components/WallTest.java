package Components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing on Wall
 */
class WallTest {

    @Test
    void getBrickCount() {
        int count = 5;
        int brickCount;
        Wall.setBrickCount(count);
        brickCount = Wall.getBrickCount();
        assertEquals(count, brickCount);
    }

    @Test
    void isBallLost() {
        boolean Lost = true;
        boolean ans;
        Wall.ballLost(Lost);
        ans = Wall.isBallLost();
        assertEquals(Lost,ans);
    }

    @Test
    void resetBallCount() {
        int ballCount;
        Wall.resetBallCount();
        ballCount = Wall.getBallCount();
        assertEquals(3,ballCount);
    }
}
