package Class_Testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing on Player
 */
class PlayerTest {

    @Test
    void moveLeft() {
        int DEF_MOVE_AMOUNT = 5;
        int moveAmount;
        moveAmount = -DEF_MOVE_AMOUNT;
        assertEquals(-5,moveAmount);
    }

    @Test
    void movRight() {
        int DEF_MOVE_AMOUNT = 5;
        int moveAmount;
        moveAmount = DEF_MOVE_AMOUNT;
        assertEquals(5,moveAmount);
    }
}