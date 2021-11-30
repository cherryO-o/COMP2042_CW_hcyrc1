package Components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CementBrickTest {

    @Test
    void Broken() {
        boolean broken;
        Brick.brokenTest = true;
        broken = Brick.isBrokenTest();
        assertEquals(broken, Brick.brokenTest);
    }
}