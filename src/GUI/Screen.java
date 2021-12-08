package GUI;

import java.awt.*;

/**
 * Interface for Start Screen and Instruction screen
 */
interface Screen {
    void drawScreen(Graphics2D g2d);
    void drawContainer(Graphics2D g2d);
    void drawText(Graphics2D g2d);
    void drawButton(Graphics2D g2d);
}


